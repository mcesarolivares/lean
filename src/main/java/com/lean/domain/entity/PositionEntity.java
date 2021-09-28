package com.lean.domain.entity;

import com.lean.domain.EntityBase;
import com.lean.domain.dto.PositionDto;
import com.lean.domain.exception.GeneralException;

import lombok.Getter;

/**
 * Objeto de negocio de cargo.
 * 
 * @author <b>Developer</b>: Cesar Olivares<br />
 *         <b>Cambios</b>:<br />
 *         <ul>
 *            <li>25-09-2021 - Creaci&oacute;n</li>
 *         </ul>
 * @version 1.0
 *
 */
@Getter
public class PositionEntity extends EntityBase {

    private final PositionDto position;

    /**
     * Constructos base.
     * @param position datos de cargo
     */
    public PositionEntity(PositionDto position) {
        if (position == null) {
            throw new GeneralException("Debe agregar cargo");
        }
        this.position = position;
    }
}
