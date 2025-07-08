package com.atac.atac.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atac.atac.model.Funcionario;
import com.atac.atac.model.Gerente;
import com.atac.atac.model.Produto;
import com.atac.atac.model.Usuario;
import com.atac.atac.service.GerenteService;
import com.atac.atac.service.ProdutoService;
import com.atac.atac.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


// Diz ao Spring que essa classe é um controlador web, ou seja, vai tratar requisições HTTP.
@Controller

// Coloca um prefixo em todas as rotas da classe
@RequestMapping("/gerente")
public class GerenteController {

    @GetMapping("/funcionarios/{idGerente}")  //equivale a GET /gerente/funcionarios
    public String listaUsuarios(@PathVariable int idGerente, Model model) {
        model.addAttribute("funcionarios", new GerenteService().lista_Funcionarios(idGerente));
        model.addAttribute("funcionario", new Funcionario());
        return "view/gerente/funcionarios";
    }

    @GetMapping("/produtos/{idGerente}")
    public String listaProdutos(@PathVariable int idGerente, Model model) {
        model.addAttribute("produtos", new GerenteService().lista_Produtos(idGerente));
        model.addAttribute("produto", new Produto());
        return "view/gerente/produtos";
    }

    /* action para cadastrar produto, o spring permite passar um objeto inteiro com formularios
       desde que tenham os name nas inputs, aqui passamos um objeto do tipo Produto para o controller
    */
    @PostMapping("/produtos/{ig}/cadastrar")
    public String cadastrarProduto(@PathVariable int ig, Produto p) {
        boolean sucesso = new ProdutoService().inserirProduto(ig, p);
        if(sucesso){
            System.out.println("produto cadastrado com sucesso");
        }
        else{
            System.out.println("não foi possivel cadastrar o produto");
        }
        return "redirect:/gerente/produtos/" + ig;
    }
    
    @GetMapping("produtos/{ig}/excluir/{ip}")
    public String excluirProduto(@PathVariable int ig, @PathVariable int ip) {
        System.out.println(ig + " " + ip + "será excluido");
        boolean sucesso = new ProdutoService().excluirProduto(ip);
        if(sucesso){
            System.out.println("produto excluido do estoque");
        }
        else{
            System.out.println("não foi possível remove-lo do estoque");
        }
        return "redirect:/gerente/produtos/" + ig;
    }
    
    @PostMapping("produtos/{ig}/editar")
    public String editarProduto(@PathVariable int ig, Produto p) {
        boolean sucesso = new ProdutoService().editar(p);    
        if(sucesso){
            System.out.println("produto alterado");
        }
        else{
            System.out.println("não foi possível fazer as alterções");
        }
        return "redirect:/gerente/produtos/" + ig;
    }
    
    @PostMapping("perfil/{ig}/editar")
    public String postMethodName(@PathVariable int ig, Usuario u, HttpSession session) {
        boolean sucesso = new UsuarioService().editar(u);
        //busca o gerente com as novas atualizações e seta a sessão dele
        if(sucesso){
            Gerente g = new GerenteService().buscarGerente(u);
            session.setAttribute("gerenteLogado", g);
        }else{
            System.out.println("erro ao editar perfil");
        }
        return "redirect:/dashboardGerente";
    }
    
}
