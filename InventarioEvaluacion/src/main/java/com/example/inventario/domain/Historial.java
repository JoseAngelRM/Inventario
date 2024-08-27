
package com.example.inventario.domain;

import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "historial")
public class Historial {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;
    
    private Long idUsuario;
    private Long idProducto;
    private int cantidad;
    private String tipoAccion;
    private Date fecha;
    private Time hora;

}
