package com.supermercado.apigrocerystore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "teni")
@Data
public class Teni {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teniId;

    private String numSerie;

    private String marca;

    private String modelo;

    private double precioCompra;

    private Integer precioVenta;

    private Boolean stock;

    private String color;

    private String descripcion; 

}
