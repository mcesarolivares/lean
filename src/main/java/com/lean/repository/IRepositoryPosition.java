package com.lean.repository;

import com.lean.data.Position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * M&eacute;todos de modelado de datos de posici&oacute;n del empleado.
 * 
 * @author <b>Developer</b>: Cesar Olivares<br />
 *         <b>Cambios</b>:<br />
 *         <ul>
 *            <li>25-09-2021 - Creaci&oacute;n</li>
 *         </ul>
 * @version 1.0
 *
 */
@Repository
public interface IRepositoryPosition extends JpaRepository<Position, Integer> {

    Position findByName(String name);
}
