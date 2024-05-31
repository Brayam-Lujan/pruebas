package com.supermercado.apigrocerystore.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.supermercado.apigrocerystore.controller.VentaController;
import com.supermercado.apigrocerystore.model.Cliente;
import com.supermercado.apigrocerystore.model.Teni;
import com.supermercado.apigrocerystore.model.Venta;
import com.supermercado.apigrocerystore.service.VentaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
@WebMvcTest(VentaController.class)
public class VentaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaService ventaService;

    @InjectMocks
    private VentaController ventaController;

    private Venta venta1;
    private Venta venta2;
    private List<Venta> ventaList;
    private Cliente cliente;
    private Teni teni1;
    private Teni teni2;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ventaController).build();

        cliente = new Cliente(1L, "Miguel Jose", "Zapata", "Carrera 34A DD 56", "miguel@gmail.com", "3211234132", "Efectivo");
        teni1 = new Teni(1L, "1234", "Adidas", "Sport", 125000, 150000, 42, "azul", "Comodos");
        teni2 = new Teni(2L, "12345", "Nike", "Air", 150000, 200000, 32, "Verde", "algo simples");

        venta1 = new Venta(1L, cliente, Arrays.asList(teni1, teni2), new BigDecimal("100.00"), LocalDateTime.now());
        venta2 = new Venta(2L, cliente, Arrays.asList(teni1), new BigDecimal("50.00"), LocalDateTime.now());
        ventaList = Arrays.asList(venta1, venta2);
    }

    @Test
    public void testCrearVenta() throws Exception {
        when(ventaService.crearVenta(anyLong(), anyList(), any(BigDecimal.class))).thenReturn(venta1);

        mockMvc.perform(post("/apiTenis/ventas")
                        .param("clienteId", "1")
                        .param("teniIds", "1,2")
                        .param("total", "100.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(venta1.getVentaId()));
    }

    @Test
    public void testBuscarVentaId_Success() throws Exception {
        when(ventaService.buscarVentaId(anyLong())).thenReturn(venta1);
        mockMvc.perform(get("/apigrocerystore/sales/{ventaId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(venta1.getVentaId()));
    }

    @Test
    public void testBuscarVentaId_NotFound() throws Exception {
        when(ventaService.buscarVentaId(anyLong())).thenReturn(null);

        mockMvc.perform(get("/apiTenis/ventas/{ventaId}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testBuscarVentas() throws Exception {
        when(ventaService.buscarTodasVentas()).thenReturn(ventaList);
        mockMvc.perform(get("/apiTenis/ventas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(venta1.getVentaId()))
                .andExpect(jsonPath("$[1].id").value(venta2.getVentaId()));
    }

    @Test
    public void testElimiarVentaPorId() throws Exception {
        mockMvc.perform(delete("/apiTenis/ventas/{ventaId}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testActualizarVenta_Success() throws Exception {
        when(ventaService.actualizarVenta(anyLong(), any(Venta.class))).thenReturn(venta1);
        mockMvc.perform(put("/apiTenis/ventas/{ventaId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(venta1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(venta1.getVentaId()));
    }

    @Test
    public void testUpdateSale_NotFound() throws Exception {
        when(ventaService.actualizarVenta(anyLong(), any(Venta.class))).thenReturn(null);
        mockMvc.perform(put("/apiTenis/vemtas/{ventaId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(venta1)))
                .andExpect(status().isNotFound());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
