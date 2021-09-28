package com.lean.domain.entity;

import com.lean.domain.EntityBase;
import com.lean.domain.dto.EmployeeDto;
import com.lean.domain.exception.GeneralException;

import lombok.Getter;

/**
 * Objeto de negocio de empleado.
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
public class EmployeeEntity extends EntityBase {

    private final EmployeeDto employee;

    /**
     * Constructor base.
     * @param employee datos de empleado
     */
    public EmployeeEntity(EmployeeDto employee) {
        if (employee == null) {
            throw new GeneralException("Debe agregar empleado");
        }
        this.employee = employee;
    }
}
