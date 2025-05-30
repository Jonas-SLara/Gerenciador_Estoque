package com.atacadao.controller;

import com.atacadao.model.Usuario;
import com.atacadao.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/usuarioServlet")
public class UsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        UsuarioService us = new UsuarioService();

        //caso a opção seja cadastrar
        if(op.equals("cadastrar")){
            String nome = req.getParameter("nome");
            String senha = req.getParameter("senha");
            String cpf = req.getParameter("cpf");
            String celular = req.getParameter("celular");
            String email = req.getParameter("email");
            //instancia um novo usuario
            Usuario u = new Usuario();
            u.setNome(nome);
            u.setSenha(senha);
            u.setCpf(cpf);
            u.setCelular(celular);
            u.setEmail(email);

            boolean sucesso = us.cadastrarUsuario(u);
            if(sucesso){
                req.setAttribute("msg1", "Cadastrado com sucesso! usuario:  " + u.getNome() + " " + u.getSenha());
                req.setAttribute("msg2", "Comunique O RH para finalizar o cadastro");
                RequestDispatcher dispatcher = req.getRequestDispatcher("pages/cadastro.jsp");
                dispatcher.forward(req, resp);
            }else{
                req.setAttribute("msg", "erro ao cadastrar");
                RequestDispatcher dispatcher = req.getRequestDispatcher("pages/cadastro.jsp");
                dispatcher.forward(req, resp);
            }
            return;
        }
    }
}
