//@author: Jonas.Silva

package com.fat32;

import java.io.File;  //representa arquivos e diretorios do sistema
import java.io.IOException;   //exceções para a leitura ou escrita
import java.io.RandomAccessFile;
//permite ler e escrever em qualquer parte de um arquivo


/*a ideia princiapal é simular um disco real dentro de um arquivo
 *chamado disco.dat onde os dados são organizados em blocos de 64KB
 como em um sistema FAT32 de verdade*/
public class Disco{
  
  //cada bloco tem 64 KB 
  public static final int TAMANHO_BLOCO = 64 * 1024;
  public static final int NUMERO_BLOCOS = 1024;
  //logo o tamanho do disco será de 64KB * 1024 que é 64MB

  private RandomAccessFile raf;
  /*raf é o objeto que vai controlar o acesso ao arquivo disco.dat
  *ele permite ler ou escrever em qualquer posição do arquivo como 
  *em um disco físico faz*/

  public Disco(){}

  /* Método init
   * Cria o arquivo disco.dat se ele não existir
   * Define seu tamanho para simular um disco de 64MB
   * retorna true se já existia ou false*/
  public boolean init() throws IOException{
    File f = new File("disco.dat");

    boolean exists = f.exists(); //verifica se já existia o .dat
                                 //
    raf = new RandomAccessFile(f, "rws");/*abre o arquivo para 
    *leitura e escrita com sincronização no disco */
    
    if(!exists){
      System.out.println("Arquivo -> " + f.getName() + 
          " sendo criado");
      //cria o disco com o tamnho total -> 64MB
      raf.setLength(NUMERO_BLOCOS * TAMANHO_BLOCO);
    }
    else{
      System.out.println("o disco já foi inicializado anteriormente");
    }
    return exists; //existia antes ou não
  }
  
  /* Método read
   * le o conteúdo de um bloco específico do disco
   * Usa seek() que é um método de RandomAccessFile par pular direto
   * para o início do bloco e depois Lê 64KB de dados e devolve como byte
  */
  public byte[] read(int numBloco) throws IOException{
    if(numBloco < 0 || numBloco > NUMERO_BLOCOS){
      throw new IllegalArgumentException("numero de bloco invalido");
    }
    raf.seek(numBloco * TAMANHO_BLOCO);//vai até o bloco certo no disco
    
    byte[] read = new byte[TAMANHO_BLOCO];//cria um buffer para leitura
    System.out.println("Buffer de " + TAMANHO_BLOCO + " KB criado");

    raf.read(read);//le os dados do disco para o buffer
    System.out.println("dados de leitura do disco -> buffer de leitura");

    return read;
  }

  /* Método write
   * Grava dados em um bloco específico do disco verificando se
   * O bloco é válido e se os daos não são nulos ou grandes demais
   * Usa seek() para pular para o inicio do próximo bloco
   * Usa write para gravar os dados*/
  public void write(int numBloco, byte[] data) throws IOException{
    if(numBloco < 0 || numBloco > NUMERO_BLOCOS){
      throw new IllegalArgumentException("numero de bloco invalido " +
          (NUMERO_BLOCOS -1));
    }
    if(data == null || data.length == 0 || data.length > TAMANHO_BLOCO)    {
      throw new IllegalArgumentException("data invalido " + 
          (TAMANHO_BLOCO -1));
    }
    raf.seek(numBloco * TAMANHO_BLOCO); //vai para o bloco certo
    raf.write(data);

    System.out.println("foram gravados " + data.length + "B de dados");
  }
}
