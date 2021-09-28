package com.lean.domain.interactors;

import com.lean.data.Candidate;
import com.lean.data.Position;
import com.lean.domain.UseCase;
import com.lean.domain.dto.EmployeeDto;
import com.lean.domain.entity.EmployeeEntity;
import com.lean.domain.exception.GeneralException;
import com.lean.repository.IRepositoryCandidate;
import com.lean.repository.IRepositoryPosition;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para validar datos de empleado.
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
public class ValidarDatosEmpleado extends UseCase<EmployeeEntity, Integer> {

    @Autowired
    private IRepositoryPosition repositoryPosition;

    @Autowired
    private IRepositoryCandidate repositoryCandidate;

    @Override
    protected Integer constructUseCase(EmployeeEntity entity) {
        log.info("Inicio validar datos de empleado");
        EmployeeDto buscar = entity.getEmployee();
        log.info("Validar cargo");
        Optional<Position> position = repositoryPosition.findById(buscar.getPosition().getId());
        if (!position.isPresent()) {
            log.warn("Cargo no existe");
            throw new GeneralException("Cargo no existe");
        }

        log.info("Validar candidato");
        Candidate candidate = repositoryCandidate.findByNameLastname(
                buscar.getCandidate().getName(), 
                buscar.getCandidate().getLastname());

        if (candidate != null && buscar.getCandidate().getId() != candidate.getId()) {
            log.warn("Candidato ya existe");
            throw new GeneralException("Candidato ya existe");
        }

        log.info("Fin validar datos de empleado");
        return buscar.getId();
    }

}
