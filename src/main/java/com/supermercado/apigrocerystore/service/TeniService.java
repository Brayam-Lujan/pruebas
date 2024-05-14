package com.supermercado.apigrocerystore.service;

import java.util.List;

import com.supermercado.apigrocerystore.model.Teni;

public interface TeniService {
    
    List<Teni> getAll();
    Teni getById(Long teniId);
    List<Teni> getByNumSerie(String numSerie);
    Teni addProduct(Teni teni);
    Teni updateProduct(Long teniId, Teni newTeni);
    void deleteProduct(Long teniId);

}
