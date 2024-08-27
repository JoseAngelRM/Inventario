
package com.example.inventario.services;

import com.example.inventario.domain.Historial;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialService {
    
    public void guardar (Historial historial);
}
