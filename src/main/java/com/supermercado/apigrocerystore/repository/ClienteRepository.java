package com.supermercado.apigrocerystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermercado.apigrocerystore.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
