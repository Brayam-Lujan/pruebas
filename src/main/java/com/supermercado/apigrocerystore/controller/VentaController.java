package com.supermercado.apigrocerystore.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermercado.apigrocerystore.model.Venta;
import com.supermercado.apigrocerystore.service.VentaService;

@RestController
@RequestMapping("/apiTenis/ventas")
public class VentaController extends ApiBaseController{
    
    @Autowired
    private VentaService ventaService;

    @PostMapping
    public Venta crear(@RequestParam("cliente Id") Long clienteId,
                           @RequestParam("tenis Ids") List<Long> teniIds,
                           @RequestParam("total") BigDecimal total) {
        return ventaService.crearVenta(clienteId, teniIds, total);
    }

    @GetMapping("/{ventaId}")
    public Venta buscarVentaId(@PathVariable("id") Long ventaId) {
        return ventaService.buscarVentaId(ventaId);
    }

    @GetMapping
    public List<Venta> buscarTodasVentas() {
        return ventaService.buscarTodasVentas();
    }

    @DeleteMapping("/{ventaId}")
    public void eliminarVentaId(@PathVariable("id") Long ventaId) {
        ventaService.eliminarVentaId(ventaId);
    }

    @PutMapping("/{ventaId}")
    public Venta actualizarVenta(@PathVariable("id") Long ventaId,
                           @RequestBody Venta venta) {
        return ventaService.actualizarVenta(ventaId, venta);
    }

}
