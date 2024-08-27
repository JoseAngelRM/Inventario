
package com.example.inventario.services;

import com.example.inventario.dao.IProductoDao;
import com.example.inventario.domain.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private IProductoDao productoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void eliminar(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto encontrarProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public Boolean aumentarInventario(Long idProductoSearch, int cantidadAumentada) {
        try{
            productoDao.aumentarInventario(idProductoSearch, cantidadAumentada);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    @Override
    @Transactional
    public Boolean reducirInventario(Long idProductoSearch, int cantidadAumentada) {
        try{
            productoDao.reducirInventario(idProductoSearch, cantidadAumentada);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
