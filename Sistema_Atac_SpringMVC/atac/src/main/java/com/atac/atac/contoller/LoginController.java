package com.atac.atac.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.atac.atac.model.Gerente;
import com.atac.atac.model.Usuario;
import com.atac.atac.model.Funcionario;
import com.atac.atac.service.FuncionarioService;
import com.atac.atac.service.GerenteService;
import com.atac.atac.service.LoginService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    //action para logar o usuario
    @PostMapping("/login")
    public String login(HttpSession session, Model model, String cpf, String senha) {
        System.out.println(cpf);

        Usuario usuario = new LoginService().autenticar(cpf, senha);
        if(usuario == null){
            System.out.println("usuario não encontrado");
            return "login";
        }

        /*Após o POST, envie uma resposta HTTP de redirecionamento para que o navegador 
        faça uma nova requisição GET para a URL /dashboardGerente
        Esse padrão é conhecido como PRG – Post/Redirect/Get, muito usado em formulários.
        Evita reenvio de formulário ao atualizar a página.
        se simplesmente retornar a view, o navegador ficará na página /login*/
        Gerente gerente = new GerenteService().buscarGerente(usuario);
        if(gerente != null){
            System.out.println("gerente logado");
            session.setAttribute("gerenteLogado", gerente);
            return "redirect:/dashboardGerente";
        }

        Funcionario funcionario = new FuncionarioService().buscarFuncionario(usuario);
        if(funcionario != null){
            System.out.println("funcionario logado");
            session.setAttribute("funcionarioLogado", funcionario);
            return "redirect:/dashboardFuncionario";
        }

        //caso tenha se cadastrado mas não é funcionário nem gerente
        System.out.println("usuario não cadastrado");

        return "login";
    }

    @GetMapping("/dashboardGerente")
    public String dashboardGerente() {
        return "view/gerente/dashboardGerente";
    }
    
    @GetMapping("/dashboardFuncionario")
    public String dashboardFuncion() {
        return "view/funcionario/dashboardFuncionario";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        System.out.println("usuario saiu");
        return "redirect:/";
    }
    
}
