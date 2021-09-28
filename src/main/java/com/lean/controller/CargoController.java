package com.lean.controller;

import com.lean.controller.request.RegistrarCargoRequest;
import com.lean.domain.adapters.Mapper;
import com.lean.domain.boundories.ResultInfo;
import com.lean.domain.dto.PositionDto;
import com.lean.domain.entity.ListPositionsEntity;
import com.lean.domain.entity.PositionEntity;
import com.lean.domain.interactors.ListarCargos;
import com.lean.domain.interactors.RegistrarCargo;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controladora de cargo.
 * 
 * @author <b>Developer</b>: Cesar Olivares<br />
 *         <b>Cambios</b>:<br />
 *         <ul>
 *            <li>25-09-2021 - Creaci&oacute;n</li>
 *         </ul>
 * @version 1.0
 *
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/positions")
public class CargoController {

    @Autowired
    private ListarCargos listarCargos;

    @Autowired
    private RegistrarCargo registrarCargo;

    /**
     * Listar cargos.
     * @param page p&aacute;gina
     * @param rows filas
     * @return lista de cargos
     */
    @GetMapping
    public ResponseEntity<ResultInfo<PositionDto>> list(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "rows", required = false, defaultValue = "0") int rows) {

        ListPositionsEntity entity = new ListPositionsEntity();
        entity.setPage(page);
        entity.setRows(rows);

        return ResponseEntity.ok().body(listarCargos.ejecutar(entity));
    }

    /**
     * Registrar nuevo cargo.
     * @param request datos del request
     * @return estado existoso
     */
    @PostMapping
    public HttpStatus insert(@Valid @RequestBody RegistrarCargoRequest request) {
        registrarCargo.ejecutar(new PositionEntity(Mapper.mapper(PositionDto.class, request)));
        return HttpStatus.CREATED;
    }
}
