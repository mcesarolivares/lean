package com.lean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lean.controller.request.RegistrarCargoRequest;

import java.net.URL;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LeanApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    private String CARGO = "Developer" + new Random().nextInt(500);
    
    @Test
    @Order(2)
    void listarCargosTest() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/positions").toString(), String.class);
            
        assertEquals(200, response.getStatusCodeValue());
	}
    
    @Test
    @Order(3)
    void listarCargosPaginadoTest() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/positions?page=0&rows=10").toString(), String.class);
            
        assertEquals(200, response.getStatusCodeValue());
    }
    
    @Test
    @Order(4)
    void registrarCargo400Test() throws Exception {
        RegistrarCargoRequest req = new RegistrarCargoRequest();
        ResponseEntity<String> response = restTemplate.postForEntity(
                new URL("http://localhost:" + port + "/positions").toString(), req, String.class);
            
        assertEquals(400, response.getStatusCodeValue());
    }
    
    @Test
    @Rollback(false)
    @Order(5)
    void registrarCargoOkTest() throws Exception {
        RegistrarCargoRequest req = new RegistrarCargoRequest();
        req.setName(CARGO);
        ResponseEntity<String> response = restTemplate.postForEntity(
                new URL("http://localhost:" + port + "/positions").toString(), req, String.class);
        assertEquals(200, response.getStatusCodeValue());
    }
    
}
