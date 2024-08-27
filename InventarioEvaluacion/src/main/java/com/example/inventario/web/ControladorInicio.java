
package com.example.inventario.web;

import com.example.inventario.domain.*;
import com.example.inventario.services.*;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UsuarioService usuariosService;
    @Autowired
    private HistorialService historialService;
    @Autowired
    private HistorialFormatService historialFormatService;
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/errores/403")
    public String accesDenied(){
        return "errores/403";
    }
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        var productos = productoService.listarProductos();
        model.addAttribute("productos", productos);
        return "index";
    }
    
    @GetMapping("/agregar")
    public String agregar(Producto producto){
        return "modificar";
    }

    @GetMapping("/historial")
    public String historial(Model model, @AuthenticationPrincipal User user){
        var listHistorialForm = historialFormatService.listarHistorial();
        model.addAttribute("listHistorialForm", listHistorialForm);
        return "historial";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto, Errors errores){

        log.info(errores.toString());
        if (errores.hasErrors()) {
            return "aumentarInventario";
        }
        productoService.guardar(producto);
        return "redirect:/";
    }
    
    @PostMapping("/sumarInventario")
    public String guardarSuma(@Valid Producto producto, Errors errores, @AuthenticationPrincipal User user){
        if (errores.hasErrors()) {
            return "aumentarInventario";
        }
        
        Usuario usuario = usuariosService.loadUser(user.getUsername());
        var historial = new Historial();
        historial.setIdUsuario(usuario.getIdUsuario());
        historial.setIdProducto(producto.getIdProducto());
        historial.setCantidad(producto.getCantidad());
        historial.setTipoAccion("ENTRADA");
        
        LocalDate local = LocalDate.now();
        java.sql.Date localSql = java.sql.Date.valueOf(local);
        historial.setFecha(localSql);
       
        LocalTime localTime = LocalTime.now(ZoneId.of("America/Mexico_City"));
        java.sql.Time localTimeSql = java.sql.Time.valueOf(localTime);
        historial.setHora(localTimeSql);
        
        productoService.aumentarInventario(producto.getIdProducto(), producto.getCantidad());
        historialService.guardar(historial);
        return "redirect:/";
    }
    
    @GetMapping("/editarSuma/{idProducto}")
    public String editarSuma(Producto producto, Model model){
        producto = productoService.encontrarProducto(producto);
        model.addAttribute(producto);
        return "aumentarInventario";
    }
    
    @PostMapping("/restaInventario")
    public String guardarResta(@Valid Producto producto, Errors errores, @AuthenticationPrincipal User user){
        
        log.info(errores.toString());
        if (errores.hasErrors()) {
            return "aumentarInventario";
        }
        
        Usuario usuario = usuariosService.loadUser(user.getUsername());
        var historial = new Historial();
        historial.setIdUsuario(usuario.getIdUsuario());
        historial.setIdProducto(producto.getIdProducto());
        historial.setCantidad(producto.getCantidad());
        historial.setTipoAccion("SALIDA");
        
        LocalDate local = LocalDate.now();
        java.sql.Date localSql = java.sql.Date.valueOf(local);
        historial.setFecha(localSql);
       
        LocalTime localTime = LocalTime.now(ZoneId.of("America/Mexico_City"));
        java.sql.Time localTimeSql = java.sql.Time.valueOf(localTime);
        historial.setHora(localTimeSql);
        
        productoService.reducirInventario(producto.getIdProducto(), producto.getCantidad());
        historialService.guardar(historial);
        return "redirect:/";
    }
    
    @GetMapping("/editarResta/{idProducto}")
    public String editarResta(Producto producto, Model model){
        producto = productoService.encontrarProducto(producto);
        model.addAttribute(producto);
        return "restarInventario";
    }
    

    @GetMapping("/eliminar")
    public String eliminar(Producto producto){
        producto = productoService.encontrarProducto(producto);
        if (producto.getEstatus() == 0) {
            producto.setEstatus(1);
        }else{
            producto.setEstatus(0);
        }
        productoService.guardar(producto);
        return "redirect:/";
    }

    /*@GetMapping("/items")
    public String getItems(Model model, @RequestParam(required = false) String filter) {
        List<Item> items = myService.getItems(filter);
        model.addAttribute("items", items);
        model.addAttribute("filter", filter);
        return "items";
    }*/
}