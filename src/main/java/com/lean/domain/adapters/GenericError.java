package com.lean.domain.adapters;

import java.util.Map;

import lombok.Getter;

/**
 * Clase base de la estructura de errores.
 * 
 * @author <b>Developer</b>: Cesar Olivares<br />
 *         <b>Cambios</b>:<br />
 *         <ul>
 *            <li>25-09-2021 - Creaci&oacute;n del proyecto</li>
 *         </ul>
 * @version 1.0
 *
 */
@Getter
public class GenericError {

    private int errorCode;
    private String detail;
    private Map<String, String> errors;

    /**
     * Constructor base.
     * 
     * @param errorCode c&oacute;digo de error de control interno
     * @param detail    detalle del mensaje de error
     */
    public GenericError(int errorCode, String detail) {
        super();
        this.errorCode = errorCode;
        this.detail = detail;
    }

    /**
     * Constructor base alterno.
     * 
     * @param errorCode c&oacute;digo de error de control interno
     * @param errors    mensajes de validaciones (javax.validation) de
     *                  <code>@RequestBody</code>
     */
    public GenericError(int errorCode, Map<String, String> errors) {
        super();
        this.errorCode = errorCode;
        this.errors = errors;
    }
}
