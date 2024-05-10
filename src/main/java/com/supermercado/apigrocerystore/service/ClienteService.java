package com.supermercado.apigrocerystore.service;

import java.util.List;

import com.supermercado.apigrocerystore.model.Cliente;

public interface ClienteService {
    
    List<Cliente> getAll();
    Cliente getByClientId(Long clienteId);
    Cliente addClient(Cliente cliente);
    Cliente updateClient(Long clienteIed, Cliente newcliente);
    void deleteClient(Long clienteId);

}
