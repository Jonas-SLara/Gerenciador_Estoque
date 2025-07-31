package com.feira.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.feira.demo.model.Livro;
import com.feira.demo.repository.LivroRepository;

@Service
public class LivroService {

    private LivroRepository repository;

    public LivroService(LivroRepository lr){
        this.repository = lr;
    }

    public Livro create(Livro obj){
        return repository.save(obj);
    }

    //deletar um livro pelo id
    public void delete(Long id){
        Optional<Livro> obj = repository.findById(id);
        if(obj.isPresent()){
            repository.deleteById(id);
        }
        throw new RuntimeException("Livro com id " + id + " não encontrado para o delete");
    }

    //obter um livro pelo id
    public Livro getById(Long id){
        /*
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Livro com ID " + id + " não encontrado"));
        */
        Optional<Livro> obj = repository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        throw new RuntimeException("Livro com o " + id + " Não encontrado! -ControllerAdvice");
    }

    public List<Livro> getAllLivros(){
        return repository.findAll();
    }

    private void updateLivro(Optional<Livro> newObj, Livro obj){
        newObj.get().setNome(obj.getNome());
    }

    public Livro update(Livro obj){
        Optional<Livro> newObj = repository.findById(obj.getId());
        if(newObj.isPresent()){
            updateLivro(newObj, obj);
            return repository.save(newObj.get());
        }else{
            throw new RuntimeException("livro com Id não encontrado: " +obj.getId());
        }
    }
}
