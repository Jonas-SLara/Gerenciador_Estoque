package com.atac.atac.contoller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atac.atac.model.Usuario;
import com.atac.atac.service.UsuarioService;


/*
 * Anotações principais do Spring explicadas
    @Controller  -> Marca a classe como controlador web do Spring MVC
    @RestController -> Igual ao @Controller, mas todos os métodos retornam JSON por padrão
    @GetMapping("/caminho") -> Mapeia uma requisição HTTP GET para um método
    Também existe @PostMapping, @PutMapping, @DeleteMapping
    @RequestParam -> Pega os parâmetros da URL ou do corpo da requisição
    @ModelAttribute -> Pega automaticamente os dados de um formulário e preenche um objeto
    @PathVariable -> Pega um valor direto da URL:
 */

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        System.out.println("hello world!");
        return "index"; // Vai resolver para /WEB-INF/Index.jsp
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }
    
    /*
     * usando redirect:/cadastro, e isso perde os atributos do Model (msg ou err) na hora de redirecionar.
     * solução: usar RedirectAttributes
     */
    @PostMapping("/cadastroUsuario")
    public String cadastroUsuario(Usuario u, RedirectAttributes redirectAttributes){
        System.out.println(u.getNome() + " " + u.getCpf() + " será cadastrada");
        boolean sucesso = new UsuarioService().cadastrar(u);
        
        if(sucesso){
            redirectAttributes.addFlashAttribute("msg", "Usuário cadastrado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("err", "CPF já utilizado ou erro no sistema.");
        }

        return "redirect:/cadastro";
    }
}