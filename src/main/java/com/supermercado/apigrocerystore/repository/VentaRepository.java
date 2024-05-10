package com.supermercado.apigrocerystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermercado.apigrocerystore.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{
    
}
