package com.lean.domain.interactors;

import com.lean.data.Position;
import com.lean.domain.UseCase;
import com.lean.domain.adapters.Mapper;
import com.lean.domain.entity.PositionEntity;
import com.lean.domain.exception.GeneralException;
import com.lean.repository.IRepositoryPosition;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para registrar cargos.
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
public class RegistrarCargo extends UseCase<PositionEntity, Integer> {

    @Autowired
    private IRepositoryPosition repositoryPosition;

    @Override
    protected Integer constructUseCase(PositionEntity entity) {
        log.info("Inicio registro de cargo");
        Position position = repositoryPosition.findByName(entity.getPosition().getName());
        if (position != null) {
            log.warn("Cargo ya existe");
            throw new GeneralException("Cargo ya existe");
        }

        position = repositoryPosition.save(Mapper.mapper(Position.class, entity.getPosition()));
        log.info("Fin registro de cargo");
        return position.getId();
    }

}
