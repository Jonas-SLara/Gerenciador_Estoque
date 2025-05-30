package com.atacadao.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.atacadao.model.Funcionario;
import com.atacadao.model.Gerente;
import com.atacadao.model.Produto;
import com.atacadao.service.GerenteService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/gerenteServlet")
public class GerenteServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        //pega a session atual se houver ou null pois o parametro create é false
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("gerente")==null){//se não existir
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
            return;
        }

        //retorna tipo Object então é nescessario o cast
        Gerente gerente = (Gerente) session.getAttribute("gerente"); 
        //acao passada pela url
        String acao = req.getParameter("acao");
        GerenteService gs = new GerenteService();

        if("listarFuncionarios".equals(acao)){
            ArrayList<Funcionario> funcionarios = gs.listFuncionarios(gerente.getId());
            req.setAttribute("listaFuncionarios", funcionarios);

            //encaminha a requisição para o devido jsp
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/verFuncionarios.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if("listarProdutos".equals(acao)){
            ArrayList<Produto> produtos = gs.listProdutos(gerente.getId());
            req.setAttribute("listaProdutos", produtos);

            //encaminha a requisição para o devido jsp
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/verProdutos.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if("cadastrarFuncionario".equals(acao)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/verFuncionarios.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if ("cadastrarProduto".equals(acao)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/verProdutos.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if ("voltar".equals(acao)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/homeGerente.jsp");
            dispatcher.forward(req, resp);
        }

        
    }
}
