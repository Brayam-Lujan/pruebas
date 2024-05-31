package com.supermercado.apigrocerystore.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.supermercado.apigrocerystore.controller.ClienteController;
import com.supermercado.apigrocerystore.model.Cliente;
import com.supermercado.apigrocerystore.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.extension.ExtendWith;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClienteController.class)
public class ClienteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private Cliente cliente1;
    private Cliente cliente2;
    private List<Cliente> clienteList;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();

        cliente1 = new Cliente(1L, "Miguel Jose", "Zapata", "Carrera 34A DD 56", "miguel@gmail.com", "3211234132", "Efectivo");
        cliente2 = new Cliente(2L, "Juan Sebastian", "Lopera", "Carrera 65A#204 DD 76", "juan@gmail.com", "321454234", "Consignación");
        clienteList = Arrays.asList(cliente1, cliente2);
    }

    @Test
    public void testGetAllClients() throws Exception {
        when(clienteService.getAll()).thenReturn(clienteList);
        mockMvc.perform(get("/apiTenis/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].clienteId").value(cliente1.getClienteId()))
                .andExpect(jsonPath("$[1].clienteId").value(cliente2.getClienteId()));
    }

    @Test
    public void testGetById_Success() throws Exception {
        when(clienteService.getByClientId(anyLong())).thenReturn(cliente1);
        mockMvc.perform(get("/apiTenis/clientes/{clienteId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value(cliente1.getClienteId()));
    }

    @Test
    public void testGetById_NotFound() throws Exception {
        when(clienteService.getByClientId(anyLong())).thenReturn(null);
        mockMvc.perform(get("/apiTenis/clientes/{clienteId}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCrearCliente() throws Exception {
        when(clienteService.addClient(any(Cliente.class))).thenReturn(cliente1);

        mockMvc.perform(post("/apiTenis/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cliente1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clienteId").value(cliente1.getClienteId()));
    }

    @Test
    public void testActualizarClientePorId_Success() throws Exception {
        when(clienteService.updateClient(anyLong(), any(Cliente.class))).thenReturn(cliente1);
        mockMvc.perform(put("/apiTenis/clientes/{clienteId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cliente1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value(cliente1.getClienteId()));
    }

    @Test
    public void testActualizarClientePorId_NotFound() throws Exception {
        when(clienteService.updateClient(anyLong(), any(Cliente.class))).thenReturn(null);

        mockMvc.perform(put("/apiTenis/clientes/{clienteId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cliente1)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testElimiarClientePorId() throws Exception {
        mockMvc.perform(delete("/apiTenis/clientes/{clienteId}", 1L))
                .andExpect(status().isNoContent());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}