/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unit.spring.dao;

import com.unit.spring.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ageu
 */
public interface UsuarioDao extends JpaRepository<Usuario, String> {
    
    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    public Usuario findByUsername(@Param("username") String username);
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario findByEmail(@Param("email") String email);
    
}
