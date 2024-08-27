
package com.example.inventario.dao;

import com.example.inventario.domain.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialDao extends JpaRepository<Historial, Long> {
    
}
