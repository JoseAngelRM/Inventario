
package com.example.inventario.services;

import com.example.inventario.domain.Producto;
import java.util.List;

public interface ProductoService {
    
    public List<Producto> listarProductos();
    public void guardar(Producto producto);
    public void eliminar(Producto producto);
    public Producto encontrarProducto(Producto producto);
    public Boolean aumentarInventario(Long idProductoSearch, int cantidadAumentada);
    public Boolean reducirInventario(Long idProductoSearch, int cantidadAumentada);
    
}
