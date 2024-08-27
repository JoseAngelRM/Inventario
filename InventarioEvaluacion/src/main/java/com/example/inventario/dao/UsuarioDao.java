
package com.example.inventario.dao;

import com.example.inventario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
    Usuario findByCorreo(String correo);
}
