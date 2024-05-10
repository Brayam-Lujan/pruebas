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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermercado.apigrocerystore.model.Teni;
import com.supermercado.apigrocerystore.service.TeniService;

@RestController
@RequestMapping("/apiTenis/tenis")
public class TeniController {
    
    @Autowired
    private TeniService teniService;

    @GetMapping
    public List<Teni> buscarTeni(@RequestParam(required = false) String numSerie){
        if (numSerie != null && !numSerie.isEmpty()) {
            return teniService.getByCodigo(numSerie);
        }else{
            return teniService.getAll();
        }
    }

    @GetMapping("/{teniId}")
    public ResponseEntity<Teni> obtenerTeniPorId(@PathVariable Long teniId){
        Teni teni = teniService.getById(teniId);
        if (teni != null) {
            return ResponseEntity.ok(teni);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Teni> crearTeni(@RequestBody Teni teni){
        Teni nuevoTeni = teniService.addProduct(teni);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTeni);
    }

    @PutMapping("/{teniId}")
    public ResponseEntity<Teni> actualizarTeni (@PathVariable Long teniId, @RequestBody Teni newTeni){
        Teni teniActualizado = teniService.updateProduct(teniId, newTeni);
        if (teniActualizado != null) {
            return ResponseEntity.ok(teniActualizado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{teniId}")
    public ResponseEntity<Void> eliminarTeni(@PathVariable Long teniId){
        teniService.deleteProduct(teniId);
        return ResponseEntity.noContent().build();
    }

}
