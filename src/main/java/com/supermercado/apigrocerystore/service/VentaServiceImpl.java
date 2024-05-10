package com.supermercado.apigrocerystore.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermercado.apigrocerystore.model.Cliente;
import com.supermercado.apigrocerystore.model.Teni;
import com.supermercado.apigrocerystore.model.Venta;
import com.supermercado.apigrocerystore.repository.ClienteRepository;
import com.supermercado.apigrocerystore.repository.TeniRepository;
import com.supermercado.apigrocerystore.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaServiceImpl implements VentaService{
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TeniRepository productRepository;

    @Override
    @Transactional
    public Venta crearVenta(Long clienteId, List<Long> teniIds, BigDecimal total) {
        // Crea una nueva instancia de Sale
        Venta venta = new Venta();
        venta.setTotal(total);

        // Establece el usuario que realizó la compra
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("We can't find the client"));
        venta.setClienteId(cliente);
        // Establece los productos que se compraron
        List<Teni> tenis = productRepository.findAllById(teniIds);
        venta.setTenis(tenis);

        // Guarda la venta en la base de datos
        return ventaRepository.save(venta);
    }

    @Override
    public Venta buscarVentaId(Long ventaId) {
        // Busca una venta en la base de datos por su identificador
        return ventaRepository.findById(ventaId).orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    @Override
    public List<Venta> buscarTodasVentas() {
        // Busca todas las ventas en la base de datos
        return ventaRepository.findAll();
    }

    @Override
    public void eliminarVentaId(Long ventaId) {
        // Elimina una venta de la base de datos por su identificador
        ventaRepository.deleteById(ventaId);
    }

    @Override
    @Transactional
    public Venta actualizarVenta(Long ventaId, Venta venta) {
        // Busca una venta en la base de datos por su identificador
        Venta existenteVenta = ventaRepository.findById(ventaId).orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // Actualiza los atributos de la venta
        existenteVenta.setTotal(venta.getTotal());
        existenteVenta.setClienteId(venta.getClienteId());
        existenteVenta.setTenis(venta.getTenis());

        // Guarda los cambios en la base de datos
        return ventaRepository.save(existenteVenta);
    }

}
