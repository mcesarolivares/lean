package com.lean.repository;

import com.lean.data.Candidate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * M&eacute;todos de modelado de datos de candidato.
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
public interface IRepositoryCandidate extends JpaRepository<Candidate, Integer> {

    @Query("Select c from Candidate c "
            + "where c.name = :name "
            + "and c.lastname = :lastname")
    Candidate findByNameLastname(@Param("name") String name, @Param("lastname") String lastname);
}
