
package com.example.inventario.dao;

import com.example.inventario.domain.HistorialFormat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HistorialFormatDao extends JpaRepository<HistorialFormat, Object>
{
    @Query( value="{ call sp_listar_historial() }", nativeQuery=true)
    @Transactional(readOnly = true)
    public List<HistorialFormat> listarHistorial();
}
