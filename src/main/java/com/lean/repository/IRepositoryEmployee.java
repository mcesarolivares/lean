package com.lean.repository;

import com.lean.data.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * M&eacute;todos de modelado de datos de empleado.
 * 
 * @author <b>Developer</b>: Cesar Olivares<br />
 *         <b>Cambios</b>:<br />
 *         <ul>
 *            <li>25-09-2021 - Creaci&oacute;n</li>
 *         </ul>
 * @version 1.0
 *
 */
public interface IRepositoryEmployee extends JpaRepository<Employee, Integer> {

    @Query("Select e from Employee e "
            + "where e.candidate.name like %:search% "
            + "or e.position.name like %:search% "
            + "or e.candidate.lastname like %:search% "
            + "order by e.salary")
    Page<Employee> findBy(@Param("search") String search, Pageable pageable); 

}
