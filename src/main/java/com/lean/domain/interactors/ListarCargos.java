package com.lean.domain.interactors;

import com.lean.data.Employee;
import com.lean.data.Position;
import com.lean.domain.UseCase;
import com.lean.domain.adapters.Mapper;
import com.lean.domain.boundories.ResultInfo;
import com.lean.domain.dto.EmployeeDto;
import com.lean.domain.dto.PositionDto;
import com.lean.domain.entity.ListPositionsEntity;
import com.lean.repository.IRepositoryEmployee;
import com.lean.repository.IRepositoryPosition;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para listar cargos.
 * 
 * @author <b>Developer</b>: Cesar Olivares<br />
 *         <b>Cambios</b>:<br />
 *         <ul>
 *            <li>25-09-2021 - Creaci&oacute;n</li>
 *         </ul>
 * @version 1.0
 *
 */
@Slf4j
@Service
public class ListarCargos extends UseCase<ListPositionsEntity, ResultInfo<PositionDto>> {

    @Autowired
    private IRepositoryPosition repositoryPosition;
    
    @Autowired
    private IRepositoryEmployee repositoryEmployee;

    @Override
    protected ResultInfo<PositionDto> constructUseCase(ListPositionsEntity entity) {
        log.info("Inicio de lista de cargos");
        Pageable pageable = PageRequest.of(entity.getPage(), entity.getRows() == 0 ? 10 : entity.getRows());
        Page<Position> result = repositoryPosition.findAll(pageable);

        ResultInfo<PositionDto> resultado = new ResultInfo<>();

        resultado.setCount(result.getTotalElements());
        
        List<PositionDto> positions = Mapper.mapper(PositionDto.class, result.getContent());
        resultado.setData(positions);
        
        if (!positions.isEmpty()) {
            List<Employee> employees = repositoryEmployee.findAll(Sort.by(Sort.Direction.DESC, "salary"));
            positions.forEach(p -> 
                p.setEmployees(employees.stream()
                        .filter(e -> e.getPosition().getId() == p.getId())
                        .map(e -> {
                            e.setPosition(null);
                            return Mapper.mapper(EmployeeDto.class, e);
                        })
                        .collect(Collectors.toList()))
            );
        }
        resultado.setData(positions);
        log.info("Fin de lista de cargos");
        return resultado;
    }

}
