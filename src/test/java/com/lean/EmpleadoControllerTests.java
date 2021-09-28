package com.lean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lean.controller.request.ActualizarEmpleadoRequest;
import com.lean.controller.request.RegistrarEmpleadoRequest;

import java.net.URL;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

class EmpleadoControllerTests extends LeanApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void listarEmpleadosTest() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/employees").toString(), String.class);
            
        assertEquals(200, response.getStatusCodeValue());
    }
    
    @Test
    void listarEmpleadosPaginadoTest() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/employees?page=0&rows=10").toString(), String.class);
            
        assertEquals(200, response.getStatusCodeValue());
    }
    
    @Test
    void listarEmpleadosSearchTest() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/employees?search=texto").toString(), String.class);
            
        assertEquals(200, response.getStatusCodeValue());
    }
    
    @Test
    void listarEmpleadosPorIdTest() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/employees/20").toString(), String.class);
            
        assertEquals(400, response.getStatusCodeValue());
    }
    
    @Test
    @Order(20)
    void registrarEmpleado400Test() throws Exception {
        RegistrarEmpleadoRequest req = new RegistrarEmpleadoRequest();
        ResponseEntity<String> response = restTemplate.postForEntity(
                new URL("http://localhost:" + port + "/employees").toString(), req, String.class);
            
        assertEquals(400, response.getStatusCodeValue());
    }
    
    @Test
    @Order(21)
    void actualizarEmpleado400Test() throws Exception {
        ActualizarEmpleadoRequest req = new ActualizarEmpleadoRequest();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ActualizarEmpleadoRequest> entity = new HttpEntity<ActualizarEmpleadoRequest>(req, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                new URL("http://localhost:" + port + "/employees").toString(), 
                HttpMethod.PUT,
                entity,
                String.class);
            
        assertEquals(400, response.getStatusCodeValue());
    }
    
    @Test
    @Order(22)
    void eliminarEmpleadosPorIdTest() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                new URL("http://localhost:" + port + "/employees/20").toString(), 
                HttpMethod.DELETE,
                entity,
                String.class);
            
        assertEquals(400, response.getStatusCodeValue());
    }
    
    @Test
    @Order(23)
    void registrarEmpleadoNoExisteCargoTest() throws Exception {
        RegistrarEmpleadoRequest req = new RegistrarEmpleadoRequest();
        req.setName("Juan");
        req.setLastname("Perez");
        req.setAddress("Ancon");
        req.setCellphone("987845321");
        req.setCityName("Lima");
        req.setSalary(200);
        req.setPositionId(20);
        ResponseEntity<String> response = restTemplate.postForEntity(
                new URL("http://localhost:" + port + "/employees").toString(), req, String.class);
            
        assertEquals(400, response.getStatusCodeValue());
    }
    
    @Test
    @Order(24)
    void actualizarEmpleadoNoExisteTest() throws Exception {
        ActualizarEmpleadoRequest req = new ActualizarEmpleadoRequest();
        req.setCandidateId(50);
        req.setId(90);
        req.setName("Juan");
        req.setLastname("Perez");
        req.setAddress("Ancon");
        req.setCellphone("987845321");
        req.setCityName("Lima");
        req.setSalary(200);
        req.setPositionId(20);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ActualizarEmpleadoRequest> entity = new HttpEntity<ActualizarEmpleadoRequest>(req, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                new URL("http://localhost:" + port + "/employees").toString(), 
                HttpMethod.PUT,
                entity,
                String.class);
            
        assertEquals(400, response.getStatusCodeValue());
    }
}
