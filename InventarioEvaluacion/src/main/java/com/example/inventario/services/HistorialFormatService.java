
package com.example.inventario.services;

import com.example.inventario.domain.HistorialFormat;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialFormatService {
    public List<HistorialFormat> listarHistorial();
    
}

