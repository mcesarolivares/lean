package com.lean.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * Request de registro de empleado.
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
public class RegistrarEmpleadoRequest {

    @NotEmpty
    @Size(max = 10, message = "El nombre debe contener máximo 10 caracteres")
    @Pattern(regexp = "[A-Za-z0-9\\s]*", message = "No se permite caracteres extraños")
    private String name;

    @NotEmpty
    @Size(max = 10, message = "El apellido debe contener máximo 10 caracteres")
    @Pattern(regexp = "[A-Za-z0-9\\s]*", message = "No se permite caracteres extraños")
    private String lastname;

    @NotEmpty
    @Size(max = 15, message = "El dirección debe contener máximo 15 caracteres")
    @Pattern(regexp = "[A-Za-z0-9-_\\s]*", message = "No se permite caracteres extraños")
    private String address;

    @NotEmpty
    @Pattern(regexp = "[9][0-9]{8}", message = "Ingrese un teléfono celular valido")
    private String cellphone;

    @NotEmpty
    @Size(max = 10, message = "La ciudad debe contener máximo 10 caracteres")
    @Pattern(regexp = "[A-Za-z0-9\\s]*", message = "No se permite caracteres extraños")
    private String cityName;

    @Positive
    private int positionId;

    @Positive
    private double salary;
}
