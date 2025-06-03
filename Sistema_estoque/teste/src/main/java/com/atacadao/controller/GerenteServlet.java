package com.atacadao.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.print.DocFlavor.STRING;

import com.atacadao.model.*;
import com.atacadao.service.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/gerenteServlet")
public class GerenteServlet extends HttpServlet{
    /*toda requisição que esta servlet receber deve verificar se o gerente ainda
     * esta logado através de uma session chamada gerente
     * o parametro usado para determinar qual ação a servlet faz sera o 'acao'
    */

    //recebe requisiçoes post para cadastrar, excluir e editar
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("gerente")==null){//se não existir
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
            return;
        }

        Gerente gerente = (Gerente) session.getAttribute("gerente"); 
        String acao = req.getParameter("acao");
        int id_gerente;

        switch (acao) {
            case "cadastrarProduto":
                String nome = req.getParameter("nome");
                String valor = req.getParameter("valor");
                String quantidade = req.getParameter("quantidade");
                id_gerente = gerente.getId();
                //pode gerar erros de conversão ou do banco
                try {
                    double valorConvertido = Double.parseDouble(valor);
                    int quantidadeConvertida = Integer.parseInt(quantidade);

                    Produto p = new Produto();

                    p.setIdGerente(id_gerente);
                    p.setNome(nome);
                    p.setValor(valorConvertido);
                    p.setQuantidade(quantidadeConvertida);

                    boolean sucesso = new ProdutoService().cadastrarProduto(p);
                    String msg = (sucesso)?
                    "novo produto " + p.getNome() + " cadastrado "
                    : "erro ao cadastrar produto, notifique o ADM";
                    req.setAttribute("msg", msg);

                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/cadastrarProduto.jsp").forward(req, resp);
                break;

            case "cadastrarFuncionario":
                String cpf = req.getParameter("cpf_usuario");
                String cargo = req.getParameter("cargo");
                String salario = req.getParameter("salario");
                Funcionario f = new Funcionario();
                id_gerente = gerente.getId();

                try{
                    f.setCargo(cargo);
                    cpf = UsuarioService.formatarCPF(cpf);
                    f.setCpfUsuario(cpf);
                    f.setIdGerente(id_gerente);
                    //buscar o usuario relacionado a este funcionario para inserir novos dados preferenciais
                    Usuario u = new UsuarioService().buscarUsuario(cpf);
                    u.setSalario(Double.parseDouble(salario));
                    //guarda o usuario correspondente no funcionario para ajudar a guardar dados
                    f.setUsuario(u);

                    boolean sucesso = FuncionarioService.cadastrarFuncionario(f);
                    String msg = (sucesso) ? "novo funcionario cadastrado " + u.getNome() + " cadastrado"
                    : "não foi possivel cadastrar este funcionario, notifique o ADM";
                    req.setAttribute("msg", msg);
                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/cadastrarFuncionario.jsp").forward(req, resp);

                break;

            case "editarProduto":
                break;

            case "editarFuncionario":
                break;
        }
    }

    //recebe requisições get como ir para as páginas de cadastrar e de listar e também para voltar e excluir
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("gerente")==null){//se não existir
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
            return;
        }

        Gerente gerente = (Gerente) session.getAttribute("gerente"); 
        String acao = req.getParameter("acao");
        GerenteService gs = new GerenteService();
        ProdutoService ps = new ProdutoService();
        ArrayList<Funcionario> funcionarios = null;
        ArrayList<Produto> produtos = null;

        switch (acao) {
            case "listarFuncionarios":
                funcionarios = gs.listFuncionarios(gerente.getId());
                req.setAttribute("listaFuncionarios", funcionarios);
                req.getRequestDispatcher("/WEB-INF/verFuncionarios.jsp").forward(req, resp);
                break;

            case "listarProdutos":
                produtos = gs.listProdutos(gerente.getId());
                req.setAttribute("listaProdutos", produtos);
                req.getRequestDispatcher("/WEB-INF/verProdutos.jsp").forward(req, resp);
                break;

            case "irCadastrarProduto":
                req.getRequestDispatcher("/WEB-INF/cadastrarProduto.jsp").forward(req, resp);
                break;
            
            case "irCadastrarFuncionario" :
                req.getRequestDispatcher("/WEB-INF/cadastrarFuncionario.jsp").forward(req, resp);
            break;

            case "excluirProduto":
                String info = req.getParameter("info");
                try {
                    int id = Integer.parseInt(info);
                    //obter o produto para consulta e excluir
                    Produto p = ps.buscarProduto(id);
                    ps.excluirProduto(id);

                    //precisa obter a nova lista de produtos atualizada
                    produtos = gs.listProdutos(gerente.getId());

                    //envia os dados para a página de volta
                    req.setAttribute("listaProdutos", produtos);
                    req.setAttribute("produtoExcluido", p);

                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    e.printStackTrace();
                }
               
                req.getRequestDispatcher("/WEB-INF/verProdutos.jsp").forward(req, resp);
            break;

            case "removerFuncionario":
                info = req.getParameter("info");
                try{
                    int id = Integer.parseInt(info);
                    Funcionario f = FuncionarioService.buscarFuncionarioId(id);
                    FuncionarioService.excluirFuncionario(id);
                    
                    funcionarios = gs.listFuncionarios(gerente.getId());
                    req.setAttribute("listaFuncionarios", funcionarios);
                    req.setAttribute("funcionarioExcluido", f);
                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    e.printStackTrace();
                }
                
                req.getRequestDispatcher("/WEB-INF/verFuncionarios.jsp").forward(req, resp);
            break;

            case "voltar":
                req.getRequestDispatcher("/WEB-INF/homeGerente.jsp").forward(req, resp);
                break;

            default:
                System.err.println("opção de servlet para gerente invalida");
                break;
        }
    }
}
