package com.lean.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Objeto de transferencia de candidato.
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
@NoArgsConstructor
public class CandidateDto {

    private int id;
    private String name;
    private String lastname;
    private String address;
    private String cellphone;
    private String cityName;
}
