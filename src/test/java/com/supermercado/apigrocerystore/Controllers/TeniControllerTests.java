package com.supermercado.apigrocerystore.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.supermercado.apigrocerystore.controller.TeniController;
import com.supermercado.apigrocerystore.model.Cliente;
import com.supermercado.apigrocerystore.model.Teni;
import com.supermercado.apigrocerystore.repository.TeniRepository;
import com.supermercado.apigrocerystore.service.TeniService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeniController.class)
public class TeniControllerTests {
    
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext; 
    @Autowired
    private TeniRepository teniRepository ;
    @MockBean
    private TeniService teniService;

    @BeforeEach
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testBuscarTeni() throws Exception{
        when(teniRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/ApiTenis/tenis"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));

        verify(teniRepository, times(1)).findAll();
    }

    @Test
    public void obtenerTeniPorIdTest() throws Exception{
        Teni teni = new Teni();
        teni.setTeniId(1L);
        when(teniRepository.findById(1L)).thenReturn(Optional.of(teni));

        mockMvc.perform(MockMvcRequestBuilders.get("/ApiTenis/tenis/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.clienteId").value(1));

            verify(teniRepository, times(1)).findById(1L);
    }

    @Test
    public void testCrearCliente() throws Exception{
        Teni teni = new Teni();
        teni.setTeniId(1L);
        when(teniRepository.save(any(Teni.class))).thenReturn(teni);
        mockMvc.perform(post("/ApiTenis/tenis")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"teniId\": null, \"numSerie\": null, \"marca\": null, \"modelo\": null, \"precioCompra\": null, \"precioVenta\": null, \"stock\": null, \"color\": null, \"descripcion\"}")
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.clienteId").value(1));
        verify(teniRepository, times(1).save(any(Teni.class)));
    }

    @Test
    public void testActualizarTeni() throws Exception {
        Teni teni = new Teni();
        teni.setTeniId(1L);
        when(teniService.updateProduct(any(Long.class), any(Teni.class))).thenReturn(teni);
        mockMvc.perform(MockMvcRequestBuilders.put("/apicarsales/tenis/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\": null, \"nombre\": null, \"apellido\": null, \"direccion\": null, \"correo\": null, \"numero\": null, \"info_pago\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(1));
        verify(teniService, times(1)).updateProduct(any(Long.class), any(Teni.class));
    }

    @Test
    public void testEliminarTeni() throws Exception {
        doNothing().when(teniService).deleteProduct(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/apicarsales/tenis/1"))
                .andExpect(status().isNoContent());
        verify(teniService, times(1)).deleteProduct(1L);
    }

}
