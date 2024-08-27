
package com.example.inventario.dao;

import com.example.inventario.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IProductoDao extends JpaRepository<Producto, Long>{

    @Modifying
    @Query( value="{ call sp_aumentar_inventario(:idProductoSearch, :cantidadAumentada) }", nativeQuery=true)
    @Transactional
    public void aumentarInventario(Long idProductoSearch, int cantidadAumentada);

    @Modifying
    @Query( value="{ call sp_reducir_inventario(:idProductoSearch, :cantidadAumentada) }", nativeQuery=true)
    @Transactional
    public void reducirInventario(Long idProductoSearch, int cantidadAumentada);

}
