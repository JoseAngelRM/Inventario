package com.example.inventario.services;

import com.example.inventario.dao.UsuarioDao;
import com.example.inventario.domain.Usuario;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Transactional (readOnly = true)
    public Usuario loadUser(String correo){
        return usuarioDao.findByCorreo(correo);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByCorreo(correo);
        
        if (usuario == null) {
            throw new UsernameNotFoundException(correo);
        }

        var roles = new ArrayList<GrantedAuthority>();

        if (usuario.getIdRol() == 1) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
        } else {
            roles.add(new SimpleGrantedAuthority("ROLE_ALMACENISTA"));
        }

        return new User(usuario.getCorreo(), usuario.getContrasena(), roles);
    }
}
