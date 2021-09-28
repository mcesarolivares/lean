package com.lean.controller;

import com.lean.controller.request.ActualizarEmpleadoRequest;
import com.lean.controller.request.RegistrarEmpleadoRequest;
import com.lean.domain.adapters.Mapper;
import com.lean.domain.boundories.ResultInfo;
import com.lean.domain.dto.CandidateDto;
import com.lean.domain.dto.EmployeeDto;
import com.lean.domain.dto.PositionDto;
import com.lean.domain.entity.EmployeeEntity;
import com.lean.domain.entity.ListEmployeesEntity;
import com.lean.domain.interactors.ActualizarEmpleado;
import com.lean.domain.interactors.BuscarEmpleado;
import com.lean.domain.interactors.EliminarEmpleado;
import com.lean.domain.interactors.ListarEmpleados;
import com.lean.domain.interactors.RegistrarEmpleado;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controladora de empleado.
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
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private ListarEmpleados listarEmpleados;

    @Autowired
    private RegistrarEmpleado registrarEmpleado;

    @Autowired
    private ActualizarEmpleado actualizarEmpleado;

    @Autowired
    private EliminarEmpleado eliminarEmpleado;

    @Autowired
    private BuscarEmpleado buscarEmpleado;

    /**
     * Listado de empleados.
     * @param search b&uacute;squeda de texto
     * @param page p&aacute;gina
     * @param rows filas
     * @return lista de empleados
     */
    @GetMapping
    public ResponseEntity<ResultInfo<EmployeeDto>> list(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "rows", required = false, defaultValue = "0") int rows) {

        ListEmployeesEntity entity = new ListEmployeesEntity();
        entity.setSearch(search);
        entity.setPage(page);
        entity.setRows(rows);

        return ResponseEntity.ok().body(listarEmpleados.ejecutar(entity));
    }

    /**
     * Buscar empleado por id.
     * @param id id de empleado
     * @return empleado
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> find(@PathVariable int id) {
        EmployeeDto employee = new EmployeeDto();
        employee.setId(id);
        return ResponseEntity.ok(buscarEmpleado.ejecutar(new EmployeeEntity(employee)));
    }

    /**
     * Registrar nuevo empleado.
     * @param request datos del request
     * @return estado existoso
     */
    @PostMapping
    public HttpStatus insert(@Valid @RequestBody RegistrarEmpleadoRequest request) {
        CandidateDto candidate = Mapper.mapper(CandidateDto.class, request);
        PositionDto position = new PositionDto();
        position.setId(request.getPositionId());
        EmployeeDto employee = new EmployeeDto();
        employee.setCandidate(candidate);
        employee.setPosition(position);
        employee.setSalary(request.getSalary());
        registrarEmpleado.ejecutar(new EmployeeEntity(employee));
        return HttpStatus.CREATED;
    }

    /**
     * Actualizar empleado.
     * @param request datos del request
     * @return estado existoso
     */
    @PutMapping
    public HttpStatus update(@Valid @RequestBody ActualizarEmpleadoRequest request) {
        CandidateDto candidate = Mapper.mapper(CandidateDto.class, request);
        candidate.setId(request.getCandidateId());
        PositionDto position = new PositionDto();
        position.setId(request.getPositionId());
        EmployeeDto employee = new EmployeeDto();
        employee.setId(request.getId());
        employee.setCandidate(candidate);
        employee.setPosition(position);
        employee.setSalary(request.getSalary());
        actualizarEmpleado.ejecutar(new EmployeeEntity(employee));
        return HttpStatus.OK;
    }

    /**
     * Eliminar empleado por id.
     * @param id id de empleado
     * @return estado exitoso
     */
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable int id) {
        EmployeeDto employee = new EmployeeDto();
        employee.setId(id);
        eliminarEmpleado.ejecutar(new EmployeeEntity(employee));
        return HttpStatus.OK;
    }
}
