package com.supermercado.apigrocerystore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supermercado.apigrocerystore.model.Cliente;
import com.supermercado.apigrocerystore.service.ClienteService;


@RestController
@RequestMapping("/apiTenis/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> mostrarClientes() {
        return clienteService.getAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> clienteId (@PathVariable Long clienteId) {
        Cliente cliente = clienteService.getByClientId(clienteId);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente (@RequestBody Cliente cliente) {
        Cliente newCliente = clienteService.addClient(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> actualizarClienteId(@PathVariable Long clienteId, @RequestBody Cliente cliente){
        Cliente updaCliente = clienteService.updateClient(clienteId, cliente);
        if (updaCliente != null) {
            return ResponseEntity.ok(updaCliente);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> eliminarClienteId(@PathVariable Long clienteId){
        clienteService.deleteClient(clienteId);
        return ResponseEntity.noContent().build();
    }

}
