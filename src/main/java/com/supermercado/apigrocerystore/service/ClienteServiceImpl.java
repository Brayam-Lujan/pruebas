package com.supermercado.apigrocerystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermercado.apigrocerystore.model.Cliente;
import com.supermercado.apigrocerystore.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
    
    @Autowired
    private ClienteRepository clientRepository;

    @Override
    public List<Cliente> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Cliente getByClientId(Long clienteId){
        return clientRepository.findById(clienteId).orElse(null);
    }

    @Override
    public Cliente addClient(Cliente cliente) {
        return clientRepository.save(cliente);
    }

    @Override
    public Cliente updateClient(Long clienteId, Cliente newcliente){
        Cliente existentClient = getByClientId(clienteId);
        if (existentClient != null) {
            newcliente.setClienteId(clienteId);
            return clientRepository.save(newcliente);
        }
        return null;
    }

    @Override
    public void deleteClient(Long clienteId) {
        clientRepository.deleteById(clienteId);
    }

}
