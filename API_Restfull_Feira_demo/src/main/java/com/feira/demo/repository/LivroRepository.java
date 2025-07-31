package com.feira.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feira.demo.model.Livro;

public interface LivroRepository extends JpaRepository<Livro,Long>{

}
