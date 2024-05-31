package com.supermercado.apigrocerystore.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.supermercado.apigrocerystore.controller.TeniController;
import com.supermercado.apigrocerystore.model.Teni;
import com.supermercado.apigrocerystore.service.TeniService;

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
@WebMvcTest(TeniController.class)
public class TeniControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeniService teniService;

    @InjectMocks
    private TeniController teniController;

    private Teni teni1;
    private Teni teni2;
    private List<Teni> teniList;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(teniController).build();

        teni1 = new Teni(1L, "1234", "Adidas", "Sport", 125000, 150000, 42, "azul", "Comodos");
        teni2 = new Teni(2L, "12345", "Nike", "Air", 150000, 200000, 32, "Verde", "algo simples");
        teniList = Arrays.asList(teni1, teni2);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        when(teniService.getAll()).thenReturn(teniList);

        mockMvc.perform(get("/apigrocerystore/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(teni1.getTeniId()))
                .andExpect(jsonPath("$[1].id").value(teni2.getTeniId()));
    }

    @Test
    public void testGetProductById_Success() throws Exception {
        when(teniService.getById(anyLong())).thenReturn(teni1);

        mockMvc.perform(get("/apiTenis/tenis/{teniId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(teni1.getTeniId()));
    }

    @Test
    public void testGetProductById_NotFound() throws Exception {
        when(teniService.getById(anyLong())).thenReturn(null);
        mockMvc.perform(get("/apiTenis/tenis/{teniId}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateProduct() throws Exception {
        when(teniService.addProduct(any(Teni.class))).thenReturn(teni1);

        mockMvc.perform(post("/apiTenis/tenis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(teni1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(teni1.getTeniId()));
    }

    @Test
    public void testUpdateProductById_Success() throws Exception {
        when(teniService.updateProduct(anyLong(), any(Teni.class))).thenReturn(teni1);

        mockMvc.perform(put("/apiTenis/tenis/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(teni1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(teni1.getTeniId()));
    }

    @Test
    public void testUpdateProductById_NotFound() throws Exception {
        when(teniService.updateProduct(anyLong(), any(Teni.class))).thenReturn(null);

        mockMvc.perform(put("/apiTenis/tenis/{teniId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(teni1)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteProductById() throws Exception {
        mockMvc.perform(delete("/apiTenis/tenis/{teniId}", 1L))
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