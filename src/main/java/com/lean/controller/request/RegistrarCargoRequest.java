package com.lean.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * Request de registro de cargo.
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
public class RegistrarCargoRequest {

    @NotEmpty
    @Size(max = 15, message = "El nombre debe contener máximo 15 caracteres")
    @Pattern(regexp = "[A-Za-z0-9\\s]*", message = "No se permite caracteres extraños")
    private String name;
}
