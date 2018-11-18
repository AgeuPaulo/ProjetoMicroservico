/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unit.spring.resources;

import com.unit.spring.dao.UsuarioDao;
import com.unit.spring.entidades.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ageu
 */
@Api(value = "API REST Usuarios")
@RestController
@RequestMapping("/usuario")
public class UsuarioService {

    @Autowired
    private UsuarioDao dao;

    @ApiOperation(value="Retorna uma lista com todos os usuarios cadastrados")
    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    @GetMapping(produces = "application/jason")
    public @ResponseBody
    List<Usuario> getListaUsuarios() {
        List<Usuario> lista = dao.findAll();

        return lista;
    }

    @ApiOperation(value="Retorna um usuário cadastrado atraves do Email")
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    @ResponseBody
    public Usuario getEmail(@PathVariable(name = "email")String email) {
        return dao.findByEmail(email);
    }

    @ApiOperation(value="Retorna um usuário cadastrado atraves do Username")
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    @ResponseBody
    public Usuario getUsername(@PathVariable(name = "username") String username) {
        return dao.findByUsername(username);
    }

    @ApiOperation(value="Salva um usuário")
    @RequestMapping(value = "/salva", method = RequestMethod.POST)
    @PostMapping(produces = "application/jason")
    public Usuario salvarUsuario(@RequestBody @Valid Usuario u) {
        return dao.save(u);
    }
    
    @ApiOperation(value="Atualiza um usuário atraves do Username")
    @RequestMapping(value = "/atualiza/{username}", method = RequestMethod.PUT)
    @ResponseBody
    public Usuario atualizarUsuario(@PathVariable(name = "username") String username, @Valid @RequestBody Usuario attUsuario) {
        Usuario u = dao.findByUsername(username);
        
        u.setEmail(attUsuario.getEmail());
        u.setUsername(attUsuario.getUsername());
        u.setSenha(attUsuario.getSenha());
        
        Usuario usuario = dao.save(u);
        
        return usuario;  
    }

    @ApiOperation(value="Deleta um usuário")
    @RequestMapping(value = "/deleta", method = RequestMethod.DELETE)
    @DeleteMapping(produces = "application/jason")
    public Usuario deletarUsuario(@RequestBody @Valid Usuario u) {
        dao.delete(u);
        return u;
    }

}
