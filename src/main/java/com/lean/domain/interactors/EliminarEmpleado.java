package com.lean.domain.interactors;

import com.lean.data.Employee;
import com.lean.domain.UseCase;
import com.lean.domain.dto.EmployeeDto;
import com.lean.domain.entity.EmployeeEntity;
import com.lean.domain.exception.GeneralException;
import com.lean.repository.IRepositoryCandidate;
import com.lean.repository.IRepositoryEmployee;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para eliminar empleado.
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
public class EliminarEmpleado extends UseCase<EmployeeEntity, Integer> {

    @Autowired
    private IRepositoryEmployee repositoryEmployee;

    @Autowired
    private IRepositoryCandidate repositoryCandidate;

    @Override
    protected Integer constructUseCase(EmployeeEntity entity) {
        log.info("Inicio eliminar empleado");
        EmployeeDto buscar = entity.getEmployee();

        log.info("Validar si existe");
        Employee employee = repositoryEmployee.findById(buscar.getId()).orElse(null);
        if (employee == null) {
            log.warn("No existe empleado: {}", buscar.getId());
            throw new GeneralException("No existe empleado");
        }

        log.info("Eliminar candidato");
        repositoryCandidate.deleteById(employee.getCandidate().getId());

        log.info("Eliminar empleado");
        repositoryEmployee.deleteById(employee.getId());

        log.info("Fin eliminar empleado");
        return 0;
    }

}
