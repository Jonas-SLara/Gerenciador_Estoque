package com.atacadao.controller;

import java.io.IOException;

import com.atacadao.model.Funcionario;
import com.atacadao.model.Gerente;
import com.atacadao.model.Usuario;
import com.atacadao.model.Produto;

import com.atacadao.service.FuncionarioService;
import com.atacadao.service.GerenteService;
import com.atacadao.service.UsuarioService;
import java.util.ArrayList;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest resq, HttpServletResponse resp)
            throws ServletException, IOException{

        String cpf = resq.getParameter("cpf");
        String senha = resq.getParameter("senha");
        
        UsuarioService uService = new UsuarioService();
        GerenteService gs = new GerenteService();
        
        if(uService.autenticar(cpf, senha)){
            //login bem sucedido agora valida se é funcionario ou gerente 
            Usuario u = uService.buscarUsuario(cpf);

            //primeiro guarda o objeto usuario em uma session
            HttpSession session = resq.getSession();
            session.setAttribute("usuario", u);

           
            Funcionario f = FuncionarioService.getFuncionario(cpf);
            if(f!=null){
                //guarda o objeto funcionario em uma seção para ser usado posteriormente
                session.setAttribute("funcionario", f);
                
                //monta a lista de produtos da sua seção
                ArrayList<Produto> produtos = gs.listProdutos(f.getIdGerente());
                resq.setAttribute("listaProdutos", produtos);

                RequestDispatcher dispatcher = resq.getRequestDispatcher("/WEB-INF/homeFuncionario.jsp");
                dispatcher.forward(resq, resp);
                return;
            }
            Gerente g = gs.getGerente(cpf);

            if(g!=null){
                //guarda o objeto gerente em uma seção para ser usado posteriormente
                session.setAttribute("gerente", g);
                
                RequestDispatcher dispatcher = resq.getRequestDispatcher("/WEB-INF/homeGerente.jsp");
                dispatcher.forward(resq, resp);
                return;
            }
            //se nao encontrado então o usuario ainda nao foi cadastrado
            resq.setAttribute("erro", "Seu CPF nao foi cadastrado como integrante da empresa ainda, fale com o RH");
            resq.getRequestDispatcher("/pages/login.jsp").forward(resq, resp);
            return;
        }
        
        else{
            //dados invalidos
            resq.setAttribute("erro", "CPF ou senha incorretos");
            resq.getRequestDispatcher("/pages/login.jsp").forward(resq, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest resq, HttpServletResponse resp)
            throws ServletException, IOException{
        //redireciona para login
        resp.sendRedirect("/pages/login.jsp");
    }
}