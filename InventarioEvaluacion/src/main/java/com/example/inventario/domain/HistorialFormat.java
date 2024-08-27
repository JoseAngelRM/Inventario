
package com.example.inventario.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Time;
import java.sql.Date;
import lombok.Data;

@Entity
@Data
public class HistorialFormat {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;
    @Column
    private String nombre;
    @Column
    private String nombreProducto;
    @Column
    private int cantidad;
    @Column
    private Date fecha;
    @Column
    private Time hora;
    @Column
    private String tipoAccion;
    
}
