package com.supermercado.apigrocerystore.Controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.supermercado.apigrocerystore.controller.VentaController;
import com.supermercado.apigrocerystore.model.Venta;
import com.supermercado.apigrocerystore.repository.VentaRepository;
import com.supermercado.apigrocerystore.service.VentaService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VentaController.class)
public class VentaControllerTests {
    
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private VentaRepository ventaRepository;
    @MockBean
    private VentaService ventaService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateSale() throws Exception {
        Venta venta = new Venta();
        venta.setVentaId(1L);

        when(ventaService.crearVenta(any(Long.class), anyList(), any(BigDecimal.class))).thenReturn(venta);

        mockMvc.perform(MockMvcRequestBuilders.post("/apicarsales/sales")
                .contentType(MediaType.APPLICATION_JSON)
                .param("clientId", "1")
                .param("carIds", "1,2,3")
                .param("total", "10000.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(ventaService, times(1)).crearVenta(any(Long.class), anyList(), any(BigDecimal.class));
    }

    @Test
    public void testBuscarVentaId() throws Exception {
        Venta venta = new Venta();
        venta.setId(1L);

        when(ventaService.buscarVentaId(1L)).thenReturn(venta);

        mockMvc.perform(MockMvcRequestBuilders.get("/apiTenis/ventas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(ventaService, times(1)).buscarVentaId(1L);
    }

    @Test
    public void testFindAllSales() throws Exception {
        List<Venta> ventas = new ArrayList<>();
        Venta venta1 = new Venta();
        venta1.setId(1L);
        Venta venta2 = new Venta();
        venta2.setId(2L);
        ventas.add(venta1);
        ventas.add(venta2);

        when(ventaService.buscarTodasVentas()).thenReturn(ventas);

        mockMvc.perform(MockMvcRequestBuilders.get("/apitenis/ventas"))
                .andExpect(x|().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(ventaService, times(1)).buscarTodasVentas();
    }

    @Test
    public void testEliminarVentaPorId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/apicarsales/sales/1"))
                .andExpect(status().isOk());
        verify(ventaService, times(1)).eliminarVentaId(1L);
    }

    @Test
    public void testActualizarVenta() throws Exception {
        Venta venta = new Venta();
        venta.setId(1L);

        when(VentaService.actualizarVenta(any(Long.class),any(Venta.class))).thenReturn(venta);

        mockMvc.perform(MockMvcRequestBuilders.put("/apicarsales/sales/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"total\": 15000.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(ventaService, times(1)).actualizarVenta(any(Long.class), any(Venta.class));
}
}

