package com.lean.domain.exception;

/**
 * Captura de expeciones controladas.
 * 
 * @author <b>Developer</b>: Cesar Olivares<br />
 *         <b>Cambios</b>:<br />
 *         <ul>
 *            <li>25-09-2021 - Creaci&oacute;n del proyecto</li>
 *         </ul>
 * @version 1.0
 *
 */
public class ControlException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ControlException(Throwable cause) {
        super(cause);
    }
}
