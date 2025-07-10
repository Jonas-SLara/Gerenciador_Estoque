package com.atac.atac.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atac.atac.model.Produto;
import com.atac.atac.model.Usuario;
import com.atac.atac.model.Funcionario;
import com.atac.atac.service.FuncionarioService;
import com.atac.atac.service.GerenteService;
import com.atac.atac.service.ProdutoService;
import com.atac.atac.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    @PostMapping("/perfil/editar")
    public String editarPerfil(Usuario u, HttpSession session){
        boolean sucesso = new UsuarioService().editar(u);
        //busca o gerente com as novas atualizações e seta a sessão dele
        if(sucesso){
            Funcionario f = new FuncionarioService().buscarFuncionario(u);
            session.setAttribute("funcionarioLogado", f);
        }else{
            System.out.println("erro ao editar perfil");
        }
        return "redirect:/dashboardFuncionario";
    }
    
    @PostMapping("/estoque/{idGerente}/editar")
    public String editarProduto(@PathVariable int idGerente, Produto p) {
        new ProdutoService().editar(p);
        return "redirect:/funcionario/estoque/" + idGerente;
    }
    
    @GetMapping("/estoque/{idGerente}")
    public String getMethodName(@PathVariable int idGerente, Model model) {
        //pega a lista de produtos do id de seu gerente
        model.addAttribute("produtos", new GerenteService().lista_Produtos(idGerente));
        return "view/funcionario/estoque";
    }
    
}
