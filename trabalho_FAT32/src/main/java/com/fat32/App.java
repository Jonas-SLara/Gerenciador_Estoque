//@author Jonas.Silva
package com.fat32;

public class App {

  public static void main(String[] args) {
      try {
        // Inicializa o disco
        Disco disco = new Disco();

        // Cria sistema de arquivos
        Fat32 fs = new Fat32(disco);

        // Arquivo de teste
        String nomeArquivo = "teste.txt";
        byte[] conteudoInicial = "Olá, mundo!".getBytes();
        byte[] conteudoAdicional = " Isso é um append.".getBytes();

        // CREATE
        fs.create(nomeArquivo, conteudoInicial);
        System.out.println("Arquivo criado com conteúdo inicial.");

        // READ
        byte[] lido = fs.read(nomeArquivo, 0, 100);
        System.out.println("Conteúdo lido: " + new String(lido));

        // APPEND
        fs.append(nomeArquivo, conteudoAdicional);
        System.out.println("Conteúdo adicional adicionado.");

        // READ novamente
        byte[] lido2 = fs.read(nomeArquivo, 0, 200);
        System.out.println("Conteúdo final do arquivo: " 
            + new String(lido2));

        // REMOVE
        fs.remove(nomeArquivo);
        System.out.println("Arquivo removido.");

        // Tenta ler após remoção (espera falha)
        try {
            fs.read(nomeArquivo, 0, 100);
        } catch (Exception e) {
          System.out.println("Erro esperado ao tentar ler arquivo" +
              " removido: " + e.getMessage());
        }
      } catch (Exception e) {
          System.err.println("Erro " + e.getMessage());
      }
    }
}

