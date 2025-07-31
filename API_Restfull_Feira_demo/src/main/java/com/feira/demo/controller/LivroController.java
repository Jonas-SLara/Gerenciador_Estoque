package com.feira.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feira.demo.model.Livro;
import com.feira.demo.service.LivroService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(value = "/livros")

public class LivroController {

    @Autowired
    private LivroService service;
    
    @PostMapping
    public ResponseEntity<Livro> create(@RequestBody Livro obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> getId(@PathVariable Long id){
        Livro livro = service.getById(id);
        return ResponseEntity.ok().body(livro);
    }
    
    @GetMapping
    public ResponseEntity<List<Livro>> getAll(){
        return ResponseEntity.ok().body(service.getAllLivros());
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(service.update(obj));
    }
    
}