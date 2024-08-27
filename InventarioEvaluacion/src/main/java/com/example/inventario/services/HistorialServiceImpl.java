
package com.example.inventario.services;

import com.example.inventario.dao.HistorialDao;
import com.example.inventario.domain.Historial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistorialServiceImpl implements HistorialService{

    @Autowired
    private HistorialDao historialDao;

    @Override
    @Transactional
    public void guardar(Historial historial) {
         historialDao.save(historial);
    }
}
