package com.atacadao.util;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import com.atacadao.dao.FuncionarioDAO;
import com.atacadao.dao.GerenteDAO;
import com.atacadao.dao.ProdutoDAO;
import com.atacadao.dao.UsuarioDAO;
import com.atacadao.model.Funcionario;
import com.atacadao.model.Gerente;
import com.atacadao.model.Produto;
import com.atacadao.model.Usuario;

public class Teste {

    public static void main(String[] args) {
        int op = -1;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.printf("%-25s %-25s %-25s %-25s %n", " 1 - Listar usuários", " 2 - Adicionar usuário",
            " 3 - Remover usuario", " 4 - Alterar usuario");
           

            System.out.printf("%-25s %-25s %-25s %-25s %n", " 4 - Listar Produtos", " 6 - Adicionar Produto",
            " 7 - Remover Produto", " 8 - Alterar Produto");

            System.out.printf("%-25s %-25s %-25s %-25s %n", " 9 - Listar Gerentes", "10 - Adicionar Gerente",
            "11 - Remover Gerente", "12 - Alterar Gerente");

            System.out.printf("%-25s %-25s %-25s %-25s %n", "13 - Listar Funcionários", "14 - Adicionar Funcionário",
            "15 - Remover Funcionário", "16 - Alterar Funcionário");
           
            System.out.print("> ");

            try {
                op = scanner.nextInt();
                scanner.nextLine(); // consumir ENTER
                switch (op) {
                    case 1:
                        listar_usuarios();
                        break;
                    case 2:
                        adicionar_usuario(scanner);
                        break;
                    case 3:
                        remover_usuario(scanner);
                        break;
                    case 4:
                        alterar_usuario(scanner);
                        break;
                    case 5:
                        listar_produtos();
                        break;
                    case 6:
                        inserir_produto(scanner);;
                        break;
                    case 7:
                        remover_produto(scanner);;
                        break;
                    case 8:
                        editar_produto(scanner);
                        break;
                    case 9:
                        listar_gerentes();
                        break;
                    case 10: 
                        inserir_gerente(scanner);
                        break;
                    case 11:
                        remover_gerente(scanner);
                        break;
                    case 12:
                        alterar_gerente(scanner);
                        break;
                    case 13:
                        listar_funcionarios();
                        break;
                    case 14:
                        inserir_funcionario(scanner);
                        break;
                    case 15:
                        remover_funcionario(scanner);
                        break;
                    case 16:
                        alterar_funcionario(scanner);
                        break;
                    case -1:
                        System.out.println("Encerrando...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida para um campo que espera um tipo numérico " + e.getMessage());
                scanner.nextLine(); // limpar buffer
            } catch (NumberFormatException e){
                System.out.println("erro ao converter string para um valor numérico, oque digitou não é numero" + 
                e.getMessage());
            }
        }
    }

    public static void listar_usuarios() {
        UsuarioDAO udao = new UsuarioDAO();
        ArrayList<Usuario> usuarios = udao.listarUsuarios();

        System.out.printf("%-20s %-25s %-15s %-10s %-15s %-15s%n",
                "Nome", "Email", "Celular", "Salário", "Senha", "CPF");
        System.out.println("---------------------------------------------------------------------------------------------");

        Iterator<Usuario> it = usuarios.iterator();
        while (it.hasNext()) {
            Usuario u = it.next();
            System.out.printf("%-20s %-25s %-15s %-10.2f %-15s %-15s%n", u.getNome(),
                u.getEmail(), u.getCelular(), u.getSalario(), u.getSenha(), u.getCpf());
        }
    }

    public static void adicionar_usuario(Scanner scanner) throws 
    NumberFormatException, InputMismatchException {
        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = new Usuario();

        System.out.print("Digite o nome do usuário: ");
        u.setNome(scanner.nextLine());

        System.out.print("Digite o email: ");
        u.setEmail(scanner.nextLine());

        System.out.print("Digite o celular: ");
        u.setCelular(scanner.nextLine());

        System.out.println("digite o salario: ");
        u.setSalario(Double.parseDouble(scanner.nextLine())); //lança uma exception

        System.out.print("Digite a senha: ");
        u.setSenha(scanner.nextLine());

        System.out.print("Digite o CPF: ");
        u.setCpf(scanner.nextLine());

        udao.inserirUsuario(u);
    }

    public static void remover_usuario(Scanner scanner){
        UsuarioDAO udao = new UsuarioDAO();
        System.out.println("digite o cpf do usuario a ser removido: ");
        String cpf = scanner.nextLine();
        udao.removerUsuario(cpf);
    }

    public static void alterar_usuario(Scanner scanner) throws
        NumberFormatException, InputMismatchException{
        System.out.println("digite o cpf para buscar usuario: ");
        String cpf = scanner.nextLine();
        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = udao.buscar_por_cpf(cpf);
        if(u == null) return;

        System.out.printf("%-20s %-25s %-15s %-10s %-15s %-15s%n",
            "Nome", "Email", "Celular", "Salário", "Senha", "CPF");

        System.out.printf("%-20s %-25s %-15s %-10.2f %-15s %-15s%n", u.getNome(),
            u.getEmail(), u.getCelular(), u.getSalario(), u.getSenha(), u.getCpf());
        
        System.out.println("\nalterando dados, pressione ENTER para manter o valor");
        String input;

        System.out.println("digite o novo nome: ");
        input = scanner.nextLine();
        if(!input.trim().isEmpty()) u.setNome(input); //se a entrada tiver vazia muda

        System.out.println("digite o novo email: ");
        input = scanner.nextLine();
        if(!input.trim().isEmpty()) u.setEmail(input);

        System.out.println("digite o novo salario: ");
        input = scanner.nextLine();
        if(!input.trim().isEmpty()) u.setSalario(Double.parseDouble(input));

        System.out.println("digite o novo celular: ");
        input = scanner.nextLine();
        if(!input.trim().isEmpty()) u.setCelular(input);

        System.out.println("digite a nova senha");
        input = scanner.nextLine();
        if(!input.trim().isEmpty()) u.setSenha(input);

        udao.alterarUsuario(u);        
    }

    public static void listar_produtos(){
        ProdutoDAO pdao = new ProdutoDAO();
        ArrayList<Produto> produtos = pdao.listar_produtos();
        Iterator<Produto> it = produtos.iterator();
        System.out.printf("%-20s %-10s %-10s %-5s %-5s%n", "Nome", "Valor", "Quantidade", "ID", "Id Gerente");

        while (it.hasNext()) {
            Produto p = it.next();
            System.out.printf("%-20s %-10s %-10s %-5s %-5s%n", p.getNome(),
                p.getValor(), p.getQuantidade(), p.getId(), p.getIdGerente());
        }
    }

    public static void remover_produto(Scanner scanner) throws NumberFormatException{
        ProdutoDAO pdao = new ProdutoDAO();
        int id = Integer.parseInt(scanner.nextLine());
        
        pdao.remover_produto(id);
    }

    public static void inserir_produto(Scanner scanner) throws 
    NumberFormatException, InputMismatchException{
        ProdutoDAO pdao = new ProdutoDAO();
        Produto p = new Produto();

        System.out.println("insira o nome do produto");
        p.setNome(scanner.nextLine());

        System.out.println("insira o valor");
        p.setValor(Double.parseDouble(scanner.nextLine()));

        System.out.println("insira a quantidade");
        p.setQuantidade(Integer.parseInt(scanner.nextLine()));

        System.out.println("insira o id_gerente");
        p.setIdGerente(Integer.parseInt(scanner.nextLine()));

        pdao.inserir_produto(p);
    }

    public static void editar_produto(Scanner scanner) throws 
    InputMismatchException, NumberFormatException{
        System.out.println("digite o id do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProdutoDAO pdao = new ProdutoDAO();
        Produto p = pdao.buscar_por_id(id);
        if(p == null)return;

        System.out.printf("%-20s %-8s %-10s %-4s %10s %n", "Nome", "Valor", "Quantidade", "Id", "Id_gerente");
        System.out.printf("%-20s %-8s %-10s %-4s %10s %n", p.getNome(), p.getValor(),
            p.getQuantidade(), p.getId(), p.getIdGerente());

        System.out.println("altere o valor ou deixe em branco para continuar");
        String input;

        System.out.println("novo nome: ");
        input = scanner.nextLine();
        if(!input.trim().isEmpty()) p.setNome(input);

        System.out.println("novo valor: ");
        input = scanner.nextLine();
        if(!input.trim().isEmpty()) p.setValor(Double.parseDouble(input));

        System.out.println("nova quantidade: ");
        input = scanner.nextLine();
        if (!input.trim().isEmpty()) p.setQuantidade(Integer.parseInt(input));

        System.out.println("novo gerente(id)");
        input = scanner.nextLine();
        if(!input.trim().isEmpty()) p.setIdGerente(Integer.parseInt(input));

        pdao.alterar_produto(p);
    }

    public static void listar_gerentes(){
        GerenteDAO gdao = new GerenteDAO();
        ArrayList<Gerente> gerentes = gdao.listar_gerentes();
        System.out.printf("%-4s %-10s %-11s %n", "Id", "cpf_usuario", "bonificação");
        for (Gerente g : gerentes) {
            System.out.printf("%-4s %-10s %-11s %n", g.getId(), g.getCpfUsuario(), g.getBonificacao());
        }
    }

    public static void remover_gerente(Scanner scan)throws
    NumberFormatException, InputMismatchException{
        System.out.println("id do gerente a ser removido: ");
        int id = Integer.parseInt(scan.nextLine());
        new GerenteDAO().remover_gerente(id);
    }

    public static void alterar_gerente(Scanner scan)throws
    NumberFormatException, InputMismatchException{
        System.out.println("digite o id do gerente a ser alterado: ");
        int id = Integer.parseInt(scan.nextLine());

        GerenteDAO gdao = new GerenteDAO();
        Gerente g = gdao.buscar_por_id(id); //cópia de funcionario
        if(g==null) return;//não achou

        System.out.printf("%-4s %-15s %-11s %n", "ID", "cpf_usuario", "bonificação");
        System.out.printf("%-4s %-15s %-11s %n", g.getId(), g.getCpfUsuario(), g.getBonificacao());

        String input;
        System.out.println("digite o novo valor ou deixa em branco para manter: ");

        System.out.println("nova bonificação ");
        input = scan.nextLine();
        if(!input.trim().isEmpty()) g.setBonificacao(Double.parseDouble(input));
        
        gdao.alterar_gerente(g);
    }

    public static void inserir_gerente(Scanner scan) throws 
    NumberFormatException, InputMismatchException{
        Gerente g = new Gerente();
        System.out.println("insira o cpf do usuario para cadastrar: ");
        g.setCpfUsuario(scan.nextLine());
        System.out.println("valor da bonificação inicial: ");
        g.setBonificacao(Double.parseDouble(scan.nextLine()));

        GerenteDAO gdao = new GerenteDAO();
        gdao.inserir_gerente(g);
    }

    public static void listar_funcionarios(){
        FuncionarioDAO fdao = new FuncionarioDAO();
        ArrayList<Funcionario> funcionarios = fdao.listar_funcionarios();
        System.out.printf("%-4s %-10s %-10s %-20s %n", "Id", "cpf_usuario", "id_gerente", "cargo");
        for (Funcionario f : funcionarios) {
            System.out.printf("%-4s %-10s %-10s %-20s %n", f.getId(), f.getCpf_usuario(), 
            f.getIdGerente(), f.getCargo());
        }
    }

    public static void inserir_funcionario(Scanner scan) throws 
    NumberFormatException, InputMismatchException{
        Funcionario f = new Funcionario();
        System.out.println("insira o cpf do usuario para cadastrar: ");
        f.setCpf_usuario(scan.nextLine());
        System.out.println("insira o id do seu gerente");
        f.setIdGerente(Integer.parseInt(scan.nextLine()));
        System.out.println("insira o seu cargo:");
        f.setCargo(scan.nextLine());

        new FuncionarioDAO().inserir_funcionario(f);
    }

    public static void remover_funcionario(Scanner scan) throws
    NumberFormatException, InputMismatchException{
        System.out.println("id do funcionario a ser removido: ");
        int id = Integer.parseInt(scan.nextLine());
        new FuncionarioDAO().remover_funcionario(id);
    }

    public static void alterar_funcionario(Scanner scan) throws
    NumberFormatException, InputMismatchException{
        System.out.println("digite o id do funcionario a ser alterado: ");
        int id = Integer.parseInt(scan.nextLine());

        FuncionarioDAO fdao = new FuncionarioDAO();
        Funcionario f = fdao.buscar_por_id(id); //cópia de funcionario
        if(f==null) return;//não achou

        System.out.printf("%-4s %-10s %-15s %-25s %n", "ID", "ID_gerente", "cpf_usuario", "cargo");
        System.out.printf("%-4s %-10s %-15s %-25s %n", f.getId(), f.getIdGerente(), f.getCpf_usuario(),
        f.getCargo());
        String input;
        System.out.println("digite o novo valor ou deixa em branco para manter: ");

        System.out.println("novo cargo: ");
        input = scan.nextLine();
        if(!input.trim().isEmpty()) f.setCargo(input);

        System.out.println("novo gerente(id): ");
        input = scan.nextLine();
        if(!input.trim().isEmpty()) f.setIdGerente(Integer.parseInt(input));
        
        fdao.alterar_funcionario(f);
    }
}
