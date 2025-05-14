//@author Jonas.Silva
package com.fat32;
import java.util.Scanner;

public class App {

  public static int apresentar() {
    int op =0;
    System.out.println("Digite uma opção desejada: ");
    System.out.println("[1] Criar um arquivo");
    System.out.println("[2] Ler um arquivo");
    System.out.println("[3] Remover um arquivo");
    System.out.println("[4] Escrever no final do arquivo");
    System.out.println("[5] Mostrar espaço livre do Disco");
    System.out.println("[6] Mostrar os arquivos no disco");
    System.out.println("[7] Area de trabalho");//um bônus
    System.out.println("-------------------------------------------");
    System.out.println("Sua resposta:   ");
    Scanner sc = new Scanner(System.in);
    op = sc.nextInt();
    return op;
  }

  public static void testeCriarArquivo(Fat32 fat){
    Scanner sc = new Scanner(System.in);
    System.out.println("Digite o nome para o arquivo: ");
    String arquivo = sc.nextLine();
    System.out.println("escreva o conteúdo do arquivo: ");
    String conteudoInicial = sc.nextLine();

    byte[] conteudo = conteudoInicial.getBytes();

    /*chamada do metodo create da classe que implementa o sistema de arquivos*/
    fat.create(arquivo, conteudo);
  }

  public static void testeLerArquivo(Fat32 fat){
    Scanner sc = new Scanner(System.in);
    System.out.println("Digite o nome para o arquivo: ");
    String nomeArquivo = sc.nextLine();

    /*chamada do metodo ler da classe que implementa o sistema de arquivos*/
    byte[] lido = fat.read(nomeArquivo, 0, 100);
    System.out.println("Conteúdo lido: " + new String(lido));
  }

  public static void testeAppendArquivo(Fat32 fat){
    Scanner sc = new Scanner(System.in);
    System.out.println("Digite o nome para o arquivo a ser lido: ");
    String nomeArquivo = sc.nextLine();
    System.out.println("Digite o conteúdo adicional: ");
    String conteudoAdicional = sc.nextLine();
    fat.append(nomeArquivo, conteudoAdicional.getBytes());
  }

  public static void testeRemoverArquivo(Fat32 fat){
    Scanner sc = new Scanner(System.in);
    System.out.println("Digite o nome para o arquivo a ser removido: ");
    String nomeArquivo = sc.nextLine();
    fat.remove(nomeArquivo);
  }

  public static void testeMostrarArquivos(Fat32 fat){
    fat.listarArquivos();
  }

  public static void testeMostrarEspacoLivre(Fat32 fat){
    int bytes_livres = fat.freeSpace();
    System.out.println("Free space: " + bytes_livres + "B "+ bytes_livres/1024 + "KB " + bytes_livres/1024/1024 + "MB");
  }

  public static void main(String[] args) {
    Disco disco;
    Fat32 fat;

    try {
      // Inicializa o disco
      disco = new Disco();

      // Cria sistema de arquivos
      fat = new Fat32(disco);
    } catch (Exception e) {
      String message = e.getMessage();
      System.out.println(message);
      return;
    }

    boolean parar = false;
    int opc = 0;

    while (!parar) {
      opc = apresentar();
        switch (opc) {
          case 1:
            try {
              testeCriarArquivo(fat);
            }catch (Exception e) {
              System.out.println("Erro ao criar arquivo: " + e.getMessage());
            }

            break;

          case 2:
            try {
              testeLerArquivo(fat);
            }catch (Exception e) {
              System.out.println("Erro ao ler arquivo: " + e.getMessage());
            }

            break;

          case 3:
            try {
              testeRemoverArquivo(fat);
            }catch (Exception e) {
              System.out.println("Erro ao remover arquivo: " + e.getMessage());
            }
            break;

          case 4:
            try {
              testeAppendArquivo(fat);
            }catch (Exception e) {
              System.out.println("Erro ao adicionar arquivo: " + e.getMessage());
            }
            break;

          case 5:
           try {
             testeMostrarEspacoLivre(fat);
           }catch (Exception e) {
             System.out.println("Erro ao mostrar espaço livre: " + e.getMessage());
           }
            break;

          case 6:
            try {
              testeMostrarArquivos(fat);
            }catch (Exception e){
              System.out.println("Erro ao mostrar arquivos: " + e.getMessage());
            }
            break;

          case 0:
            parar = true;
            break;
        }
      }
    System.out.println("Programa finalizado!");
  }
}
