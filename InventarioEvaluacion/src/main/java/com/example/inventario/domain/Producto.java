package com.example.inventario.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    
    @NotEmpty
    private String nombreProducto;
    
    @PositiveOrZero
    private int cantidad;
    
    private int estatus;
    
    public Producto(){
        this.estatus = 1;
    }
}
