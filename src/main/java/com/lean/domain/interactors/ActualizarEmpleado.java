package com.lean.domain.interactors;

import com.lean.data.Candidate;
import com.lean.data.Employee;
import com.lean.domain.UseCase;
import com.lean.domain.adapters.Mapper;
import com.lean.domain.dto.EmployeeDto;
import com.lean.domain.entity.EmployeeEntity;
import com.lean.domain.exception.GeneralException;
import com.lean.repository.IRepositoryCandidate;
import com.lean.repository.IRepositoryEmployee;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para actualizar empleado.
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
public class ActualizarEmpleado extends UseCase<EmployeeEntity, Integer> {

    @Autowired
    private IRepositoryEmployee repositoryEmployee;

    @Autowired
    private ValidarDatosEmpleado validarDatosEmpleado;

    @Autowired
    private IRepositoryCandidate repositoryCandidate;

    @Override
    protected Integer constructUseCase(EmployeeEntity entity) {
        log.info("Inicio actualizar empleado");
        EmployeeDto buscar = entity.getEmployee();

        log.info("Validar si existe");
        if (!repositoryEmployee.existsById(buscar.getId())) {
            log.warn("No existe empleado: {}", buscar.getId());
            throw new GeneralException("Empleado no existe");
        }

        validarDatosEmpleado.ejecutar(entity);

        log.info("Actualizar datos de candidato");
        Candidate candidato = Mapper.mapper(Candidate.class, buscar.getCandidate());
        repositoryCandidate.save(candidato);

        log.info("Actualizar datos de empleado");
        Employee employee = Mapper.mapper(Employee.class, buscar);
        repositoryEmployee.save(employee);

        log.info("Fin actualizar empleado");
        return employee.getId();
    }

}
