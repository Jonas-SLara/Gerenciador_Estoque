package com.atac.atac.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atac.atac.model.*;
import com.atac.atac.service.*;

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
    
    @PostMapping("perfil/editar")
    public String editarPerfil(Usuario u, HttpSession session) {
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
    
    //passa o cpf e o idGerente por post, busca e redireciona com redirect para /gerente/funcionarios de volta
    @PostMapping("/funcionarios/buscar")
    public String buscarFuncionario(String cpf, int ig, Model model){
        Usuario u = new UsuarioService().buscarUsuario(cpf);
        if(u!=null){
            System.out.println("usuario encontrado");
            model.addAttribute("usuarioBuscado", u);
        }
        else{
            System.out.println("usuario não encontrado");
            model.addAttribute("usuarioErr", "usuario não encontrado");
        }
        model.addAttribute("funcionarios", new GerenteService().lista_Funcionarios(ig));
        return "view/gerente/funcionarios";
    }

    @PostMapping("/funcionarios/cadastrar")
    public String cadastrarFuncionario(Funcionario f, double salario, RedirectAttributes ra) {
        //primeiro valida se já está cadastrado este usuario
        Usuario u = new Usuario();
        u.setCpf(f.getCpfUsuario());
        boolean valido = new UsuarioService().usuarioEspecializado(u);
        if(!valido){
            System.out.println("este usuario já tem outra função");
            ra.addFlashAttribute("usuarioErr",
                "este usuario já tem outra função");
            return "redirect:/gerente/funcionarios/" + f.getIdGerente();
        }

        //terminando de inserir os dados para o objeto que representa um funcionario no dao
        f.setUsuario(u);
        f.getUsuario().setSalario(salario);
        boolean sucesso = new FuncionarioService().cadastrarFuncionario(f);
        if(sucesso){
            System.out.println("novo funcionario cadastrado");
        }else{
            System.out.println("não foi possivel cadastrar este usuario");
        }
        return "redirect:/gerente/funcionarios/" + f.getIdGerente();
    }
    
    /*edita o funcionario e redireciona para a pagina de funcionarios do gerente, só que aqui o idGerente
    já está no objeto funcionario passado pelo POST, exceto o salario*/
    @PostMapping("/funcionarios/editar")
    public String editarFuncionario(Funcionario f, double salario) {
        //no funcionario dao é preciso saber o salario do usuario do funcionario
        f.setUsuario(new Usuario());
        f.getUsuario().setSalario(salario);
        boolean sucesso = new FuncionarioService().editarFuncionario(f);
        if(!sucesso){
            System.out.println("não foi possível alterar este funcionário");
        }
        System.out.println("alterações do funcionário guardadas");
        return "redirect:/gerente/funcionarios/" + f.getIdGerente();
    }
    
    @GetMapping("/funcionarios/{ig}/excluir/{idF}")
    public String excluirFuncionario(@PathVariable int ig, @PathVariable int idF) {
        boolean sucesso = new FuncionarioService().excluirFuncionario(idF);
        if(sucesso){
            System.out.println("funcionario deletado!");
        }else{
            System.out.println("erro ao deletar funcionario!");
        }
        return "redirect:/gerente/funcionarios/" + ig;
    }
}
