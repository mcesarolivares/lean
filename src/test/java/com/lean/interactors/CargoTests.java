package com.lean.interactors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.lean.LeanApplicationTests;
import com.lean.domain.dto.PositionDto;
import com.lean.domain.entity.PositionEntity;
import com.lean.domain.exception.GeneralException;
import com.lean.domain.interactors.RegistrarCargo;

import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

class CargoTests extends LeanApplicationTests {

    @Autowired
    private RegistrarCargo registrarCargo;

    private final static String CARGO = "Developer" + new Random().nextInt(500);

    @Test
    @Rollback(false)
    @Order(11)
    void registrarCargoOk() {
        PositionDto pos = new PositionDto();
        pos.setName(CARGO);
        registrarCargo.ejecutar(new PositionEntity(pos));
        assertTrue(true);
    }

    @Test
    @Order(12)
    void registrarCargoExiste() {
        PositionDto pos = new PositionDto();
        pos.setName(CARGO);
        assertThrows(GeneralException.class, () -> 
        registrarCargo.ejecutar(new PositionEntity(pos))
                );
    }

    @Test
    void useCaseException() {
        assertThrows(GeneralException.class, () -> 
        registrarCargo.ejecutar(new PositionEntity(null))
                );
    }

}
