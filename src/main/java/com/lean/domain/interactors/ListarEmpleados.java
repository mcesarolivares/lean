package com.lean.domain.interactors;

import com.lean.data.Employee;
import com.lean.domain.UseCase;
import com.lean.domain.adapters.Mapper;
import com.lean.domain.boundories.ResultInfo;
import com.lean.domain.dto.EmployeeDto;
import com.lean.domain.entity.ListEmployeesEntity;
import com.lean.repository.IRepositoryEmployee;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para listar empleados.
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
public class ListarEmpleados extends UseCase<ListEmployeesEntity, ResultInfo<EmployeeDto>> {

    @Autowired
    private IRepositoryEmployee repositoryEmployee;

    @Override
    protected ResultInfo<EmployeeDto> constructUseCase(ListEmployeesEntity entity) {
        log.info("Inicio de lista de empleados");
        Pageable pageable = PageRequest.of(entity.getPage(), entity.getRows() == 0 ? 10 : entity.getRows());

        Page<Employee> result = null;
        if (entity.getSearch() != null && !entity.getSearch().isEmpty()) {
            log.info("Listar por nombre o cargo");
            result = repositoryEmployee.findBy(entity.getSearch(), pageable);
        } else {
            log.info("Listar todos");
            result = repositoryEmployee.findAll(pageable);
        }

        ResultInfo<EmployeeDto> resultado = new ResultInfo<>();
        resultado.setCount(result.getTotalElements());
        resultado.setData(Mapper.mapper(EmployeeDto.class, result.getContent()));
        log.info("Fin de lista de empleados");
        return resultado;
    }

}
