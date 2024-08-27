
package com.example.inventario.services;

import com.example.inventario.dao.HistorialFormatDao;
import com.example.inventario.domain.HistorialFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistorialFormatImpl implements HistorialFormatService{

    @Autowired
    private HistorialFormatDao historialFormatDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<HistorialFormat> listarHistorial() {
        return historialFormatDao.listarHistorial();
    }
}
