package com.supermercado.apigrocerystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermercado.apigrocerystore.model.Teni;
import com.supermercado.apigrocerystore.repository.TeniRepository;

@Service
public class TeniServiceImpl implements TeniService{
    
    @Autowired
    private TeniRepository teniRepository;

    @Override
    public List<Teni> getAll() {
        return teniRepository.findAll();
    }

    @Override
    public Teni getById(Long teniId) {
        return teniRepository.findById(teniId)
        .orElseThrow(() -> new IllegalArgumentException("The tenis with ID " + teniId + " doesn't exist."));
    }

    @Override
    public List<Teni> getByCodigo(String numSerie){
        return teniRepository.findByCodigo(numSerie);
    }

    @Override
    public Teni addProduct(Teni teni){
        return teniRepository.save(teni);
    }

    @Override
    public Teni updateProduct(Long teniId, Teni newTeni){
        if (teniRepository.existsById(null)) {
            newTeni.setTeniId(teniId);
            return teniRepository.save(newTeni);
        }else{
            throw new IllegalArgumentException("The tenis with ID " + teniId + " doesn't exist.");
        }
    }

    @Override
    public void deleteProduct(Long teniId){
        teniRepository.deleteById(teniId);
    }

}
