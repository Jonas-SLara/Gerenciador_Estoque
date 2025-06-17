package com.atacadao.controller;

import com.atacadao.model.Usuario;
import com.atacadao.service.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/usuarioServlet")
public class UsuarioServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String op = req.getParameter("op");
        String nome, senha, cpf, celular, email;
        Usuario u = new Usuario();

        switch (op) {
            case "cadastrar":
                nome = req.getParameter("nome");
                senha = req.getParameter("senha");
                cpf = req.getParameter("cpf");
                celular = req.getParameter("celular");
                email = req.getParameter("email");
                //instancia um novo usuario
                u.setNome(nome);
                u.setSenha(senha);
                u.setCpf(cpf);
                u.setCelular(celular);
                u.setEmail(email);
                boolean sucesso = UsuarioService.cadastrarUsuario(u);
                String msg = (sucesso)?
                "Cadastrado com sucesso! usuario(a): " + u.getEmail() + "  Comunique com o RH para finalizar"
                : "erro ao cadastrar usuario tente outro CPF";

                req.setAttribute("msg", msg);
                req.getRequestDispatcher("pages/cadastro.jsp").forward(req, resp);
                break;

            case "buscar":
                cpf = req.getParameter("cpf");
                u = UsuarioService.buscarUsuario(cpf);
                if(u!=null){
                    req.setAttribute("usuarioEncontrado", u);
                    //agora ele verifica se é um funcionario ou gerente
                    boolean livre = UsuarioService.eUsuarioNaoCadastrado(cpf);
                    if(!livre){
                        req.setAttribute("erro", "este usuário já tem um vínculo no sistema");
                    }
                }else{
                    req.setAttribute("erro", "nenhum usuário com este cpf foi encontrado");
                }
                req.getRequestDispatcher("/WEB-INF/cadastrarFuncionario.jsp").forward(req, resp);
                break;
            default:

                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest resq, HttpServletResponse resp)
            throws ServletException, IOException{
        String op = resq.getParameter("acao");
        switch (op) {
            case "irLogin":
                resp.sendRedirect(resq.getContextPath() + "/pages/login.jsp");
                break;
            case "irCadastro":
                resp.sendRedirect(resq.getContextPath() + "/pages/cadastro.jsp");
                break;
            case "irIndex":
                resp.sendRedirect(resq.getContextPath() + "/");
                break;
            case "sair":
                HttpSession session = resq.getSession(false);
                if(session != null){
                session.invalidate();
                }
                resp.sendRedirect(resq.getContextPath() + "/pages/login.jsp");
                break;
            default:
                break;
        }
    }
}