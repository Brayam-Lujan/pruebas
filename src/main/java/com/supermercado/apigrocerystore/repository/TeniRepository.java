package com.supermercado.apigrocerystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermercado.apigrocerystore.model.Teni;

import java.util.List;

public interface TeniRepository extends JpaRepository<Teni, Long>{
    List<Teni> findByNumSerie(String numSerie);
}
