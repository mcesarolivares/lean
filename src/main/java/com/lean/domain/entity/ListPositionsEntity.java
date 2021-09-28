package com.lean.domain.entity;

import com.lean.domain.EntityBase;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto de negocio para listado de cargos.
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
@Setter
public class ListPositionsEntity extends EntityBase {

    private int page;
    private int rows;
}
