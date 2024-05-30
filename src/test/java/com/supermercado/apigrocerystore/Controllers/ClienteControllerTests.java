package com.supermercado.apigrocerystore.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import com.supermercado.apigrocerystore.controller.ClienteController;
import com.supermercado.apigrocerystore.model.Cliente;
import com.supermercado.apigrocerystore.repository.ClienteRepository;
import com.supermercado.apigrocerystore.service.ClienteService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTests {
    
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private ClienteService clienteService;

    @BeforeEach
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testMostrarClientes() throws Exception{
        when(clienteRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/ApiTenis/clientes"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testMostrarClienteId() throws Exception{
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        mockMvc.perform(MockMvcRequestBuilders.get("/ApiTenis/clientes/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.clienteId").value(1));

            verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    public void testCrearCliente() throws Exception{
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        mockMvc.perform(post("/ApiTenis/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"clienteId\": null, \"nombre\": null, \"apellido\": null, \"direccion\": null, \"correo\": null, \"numero\": null, \"info_pago\"}")
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.clienteId").value(1));
        verify(clienteRepository, times(1).save(any(Cliente.class)));
    }

    @Test
    public void testActualizarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        when(clienteService.updateClient(any(Long.class), any(Cliente.class))).thenReturn(cliente);
        mockMvc.perform(MockMvcRequestBuilders.put("/apicarsales/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\": null, \"nombre\": null, \"apellido\": null, \"direccion\": null, \"correo\": null, \"numero\": null, \"info_pago\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(1));
        verify(clienteService, times(1)).updateClient(any(Long.class), any(Cliente.class));
    }

    @Test
    public void testEliminarCliente() throws Exception {
        doNothing().when(clienteService).deleteClient(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/apicarsales/clientes/1"))
                .andExpect(status().isNoContent());
        verify(clienteService, times(1)).deleteClient(1L);
    }
}