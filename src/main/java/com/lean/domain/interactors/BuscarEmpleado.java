package com.lean.domain.interactors;

import com.lean.data.Employee;
import com.lean.domain.UseCase;
import com.lean.domain.adapters.Mapper;
import com.lean.domain.dto.EmployeeDto;
import com.lean.domain.entity.EmployeeEntity;
import com.lean.domain.exception.GeneralException;
import com.lean.repository.IRepositoryEmployee;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para buscar empleado.
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
public class BuscarEmpleado extends UseCase<EmployeeEntity, EmployeeDto> {

    @Autowired
    private IRepositoryEmployee repositoryEmployee;

    @Override
    protected EmployeeDto constructUseCase(EmployeeEntity entity) {
        log.info("Inicio buscar empleado");
        Employee employee = repositoryEmployee.findById(entity.getEmployee().getId()).orElse(null);
        if (employee == null) {
            log.warn("No existe empleado: {}", entity.getEmployee().getId());
            throw new GeneralException("No existe empleado");
        }
        log.info("Fin buscar empleado");
        return Mapper.mapper(EmployeeDto.class, employee);
    }

}
