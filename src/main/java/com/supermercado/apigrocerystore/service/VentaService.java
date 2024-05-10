package com.supermercado.apigrocerystore.service;

import java.math.BigDecimal;
import java.util.List;

import com.supermercado.apigrocerystore.model.Venta;

public interface VentaService {

    Venta crearVenta(Long clienteId, List<Long> teniIds, BigDecimal total);

    Venta buscarVentaId(Long ventaId);

    List<Venta> buscarTodasVentas();

    void eliminarVentaId(Long ventaId);

    Venta actualizarVenta(Long ventaId, Venta venta);

}
