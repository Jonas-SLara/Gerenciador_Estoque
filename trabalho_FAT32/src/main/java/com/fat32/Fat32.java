package com.fat32;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Fat32 implements GerenciadorArquivos {
  //cria o arquivo que simula o disco rígido
  private Disco disco = new Disco();
  
  /* Vetor de inteiros que representa a tabela fat
   * Cada indice é um número de bloco*/
  private int[] fat = new int[Disco.NUMERO_BLOCOS];

  private static final int BLOCO_FAT = 0;
  private static final int BLOCO_DIRETORIO = 1;
  private static final int BLOCO_OCUPADO = -1;

  private List<EntradaDiretorio> diretorio = new ArrayList<>();

  private class EntradaDiretorio{//um para cada arquivo
    private String fileName;
    private int tamanhoArquivo;
    private int blocoInicial;
  }

  //@constructor
  public Fat32(Disco disco){
    this.disco = disco; //seta o disco pra ser usado pela fat32
    try{
      if(!this.disco.init()){//se o arquivo disco.dat for novo
        this.inicializaFat();
        this.inicializaDiretorio();
      }else{
        this.leFat();
        this.leDiretorio();
      }
    }catch(IOException e){
      e.printStackTrace();
    }
  }
  
  /* Método inicializaFat Reserva os dois primeiros blocos e depois 
     escreve a tabela FAT no disco com gravaFat()*/
  private void inicializaFat() throws IOException{
    fat[BLOCO_FAT] = BLOCO_OCUPADO;
    fat[BLOCO_DIRETORIO]= BLOCO_OCUPADO;
    this.gravaFat();
  }
  
  /* Grava toda a FAT no bloco 0 do disco*/
  private void gravaFat() throws IOException {
    //usa ByteBuffer para empacotar os dados como ints de 4bytes cada
    ByteBuffer buffer = ByteBuffer.allocate(Disco.TAMANHO_BLOCO);

    for(int i : fat){
      buffer.putInt(i); //grava cada valor como int (4 bytes)
    }
    disco.write(BLOCO_FAT, buffer.array());

    System.out.println(buffer.array().length + " B gravados " +
         "no bloco da FAT"); 
  }
  
  /* Método leFat le a fat que esta no disco
   * Reconstroi o Vetor fat[] devolta em inteiros */
  private void leFat() throws IOException{
    byte[] bloco = disco.read(BLOCO_FAT); //le o bloco da FAT
                                          
    ByteBuffer buffer = ByteBuffer.wrap(bloco);
    
    for(int i=0; i < fat.length; i++){
      fat[i] = buffer.getInt();
    }
  }
  
  /* implementacão do diretorio e arquivos*/

  /*
   * Inicializar a estrutura de diretório vazia
   * Serializar essa estrutura como byte[]
   * Gravar no disco no bloco 1 reservado para o diretório
   * Marcar o bloco como ocupado na FAT*/
  private void inicializaDiretorio(){
    try{
      // Começamos com diretório vazio
      diretorio.clear();
      // Cria um buffer para o bloco do diretório
      ByteBuffer buffer = ByteBuffer.allocate(Disco.TAMANHO_BLOCO);

      // Grava quantos arquivos temos no diretório (0 no inicio)
      buffer.putInt(0);
      
      // Grava o buffer no bloco 1
      disco.write(BLOCO_DIRETORIO, buffer.array());

      // Marca o bloco como ocupado na FAT
      fat[BLOCO_DIRETORIO] =  BLOCO_OCUPADO;
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  /* Método leDiretorio            disco --> memoria(diretorio)
   * O primeiro int do bloco do diretório armazena quantas entradas
   * de arquivos estão salvas e cada entrada tem 
   * Nome do arquivo 8caracteres 8bytes
   * extensão 3 caracteres 3bytes
   * Tamanho int 4bytes
   * Bloco_inicial int 4bytes  Total: 19bytes por diretorio*/
  private void leDiretorio() throws IOException{
    byte[] bloco = disco.read(BLOCO_DIRETORIO);
    ByteBuffer buffer = ByteBuffer.wrap(bloco);

    int numero_entradas = buffer.getInt();//le a quantidade de arquivos
    diretorio.clear(); //limpa a lista atual de arquivos

    //iterar sobre todas as entradas no bloco onde esta o diretório
    for(int i=0; i < numero_entradas; i++){
      byte[] nomeBytes = new byte[8];
      byte[] extBytes = new byte[3];

      buffer.get(nomeBytes);//pega os proximos 8bytes --> nomeBytes
      buffer.get(extBytes);//pega os proximos 3bytes --> extBytes

      String nome = new String(nomeBytes).trim();
      String ext = new String(extBytes).trim();

      int tamanho = buffer.getInt();//pega os proximos 4bytes
      int blocoInicial = buffer.getInt();//pega os proximos 4bytes

      //cria uma nova entrada e adiciona na lista de entradas
      EntradaDiretorio entrada = new EntradaDiretorio();
      entrada.fileName = nome + "." + ext;
      entrada.tamanhoArquivo = tamanho;
      entrada.blocoInicial = blocoInicial;
      
      diretorio.add(entrada);
    }
  }

  /* Método gravaDiretorio     memoria(diretorio) --> disco
   * */
  private void gravaDiretorio(){
    //cria array de bytes do tamanho de um bloco do disco
    ByteBuffer buffer = ByteBuffer.allocate(Disco.TAMANHO_BLOCO);
    
    buffer.putInt(diretorio.size()); //salva o número de arquivos

    for(EntradaDiretorio entrada : diretorio){
      //divide o nome e extensão
      String[] partes = entrada.fileName.split("\\.");
      String nome = partes[0];
      String ext = partes[1];

      //Garante tamanho fixo de 8 para nome e 3 para ext
      nome = String.format("%-8s", nome).substring(0, 8);
      ext = String.format("%-3s", ext).substring(0, 3);
      
      try{
        buffer.put(nome.getBytes()); //grava o nome
        buffer.put(ext.getBytes()); //grava a extensão
      }catch(Exception e){
        System.out.println("erro ao codificar nome e extensão: " + 
            e.getMessage());
      }

      buffer.putInt(entrada.tamanhoArquivo);
      buffer.putInt(entrada.blocoInicial);

      try{
        disco.write(BLOCO_DIRETORIO, buffer.array());
      }catch(IOException e){
        System.err.println("Erro ao gravar Diretório: " + 
            e.getMessage());
      }
    }
  }

  /*Método listar arquivos*/
  public void listarArquivos(){
    System.out.println("***Listar Arquivos***");
    System.out.println("Pegando dados ...");
    for(EntradaDiretorio entrada : diretorio){
      System.out.println("Arquivo: "+entrada.fileName+" Tamanho: "
          +entrada.tamanhoArquivo+" bloco: "+entrada.blocoInicial);
    }
    System.out.println("________________________\n");
  } 
  
  @Override
  public void create(String fileName, byte[] data){
    System.out.println("****Novo Arquivo****");
    //verifica duplicidade
    for(EntradaDiretorio e : diretorio){
      if(e.fileName.equals(fileName)){
        throw new IllegalArgumentException("Arquivo já existe");
      }
    }

    System.out.println("Arquivo: "+fileName+" B: "+data.length); 

    //encontra a quantidade de blocos necessarios
    int blocosNecessarios =
      (data.length + Disco.TAMANHO_BLOCO -1)/Disco.TAMANHO_BLOCO;
    List<Integer> blocosLivres = new ArrayList<>();
    
    System.out.println("Blocos necessarios: "+blocosNecessarios);

    //encontra a quantidade de blocos livres começando do 2
    for(int i=2; i < fat.length &&
        blocosLivres.size() < blocosNecessarios; i++){
      if(fat[i] == 0){
        blocosLivres.add(i);
      }
    }
    
    System.out.println("Blocos Livres: "+blocosLivres.size());

    //valida o espaço se é suficiente
    if(blocosLivres.size() < blocosNecessarios){
      throw new IllegalStateException("Espaço insuficiente");
    }
    

    //grava blocos e atualiza FAT
    int offset = 0;
    System.out.println("Iniciando processo de gravacão em blocos...");
    for(int i = 0; i < blocosNecessarios; i++){
      int blocoAtual = blocosLivres.get(i);
      int blocoSeguinte = (i < blocosNecessarios -1) ?
        blocosLivres.get(i + 1) : -1;

      //na tabela da fat gravar o indice para o proximo bloco no indice do bloco atual, se for o ultimo bloco do arquivo será -1
      fat[blocoAtual] = blocoSeguinte;

      //Qual é menor o tamanho do bloco ou o arquivo?
      int tamanho = 
        Math.min(Disco.TAMANHO_BLOCO, data.length - offset);

      byte[] parte = new byte[Disco.TAMANHO_BLOCO];
      System.arraycopy(data, offset, parte, 0, tamanho);
      offset += tamanho;
      
      //escreve a parte do arquivo que cabe num bloco, no disco
      try{
        disco.write(blocoAtual, parte);//onde? no bloco livre atual
      }catch(IOException e){
        throw new RuntimeException("Erro ao escrever bloco");
      }
    }
    System.out.println("...Proceso de gravação concluido");
    System.out.println("criando nova entrada no diretorio...");
    EntradaDiretorio entrada = new EntradaDiretorio();
    entrada.fileName = fileName;
    entrada.blocoInicial = blocosLivres.get(0);
    entrada.tamanhoArquivo = data.length;
    System.out.println("Gravando a entrada no bloco Diretório..."); 
    diretorio.add(entrada);
    try{
      gravaFat();
      gravaDiretorio();
    }catch(IOException e){
      throw new RuntimeException("Erro ao salvar metadados");
    }
    System.out.println("...Gravação concluida");
  }
  
  @Override
  public void append(String fileName, byte[] data){
    EntradaDiretorio entrada = null;

    for (EntradaDiretorio e : diretorio) {
        if (e.fileName.equals(fileName)) {
            entrada = e;
            break;
        }
    }

    if (entrada == null) {
        throw new IllegalArgumentException("Arquivo não encontrado.");
    }

    int blocosNecessarios = 
      (data.length + Disco.TAMANHO_BLOCO - 1) / Disco.TAMANHO_BLOCO;
    List<Integer> blocosLivres = new ArrayList<>();

    for (int i = 2; i < fat.length && 
        blocosLivres.size() < blocosNecessarios; i++) {
        if (fat[i] == 0) {
            blocosLivres.add(i);
        }
    }

    if (blocosLivres.size() < blocosNecessarios) {
        throw new IllegalStateException("Espaço insuficiente.");
    }

    // Encontrar o último bloco da cadeia
    int blocoAtual = entrada.blocoInicial;
    while (fat[blocoAtual] != -1) {
        blocoAtual = fat[blocoAtual];
    }

    // Escreve novos blocos
    int offset = 0;
    for (int i = 0; i < blocosNecessarios; i++) {
        int blocoNovo = blocosLivres.get(i);
        int proximo = 
          (i < blocosNecessarios - 1) ? blocosLivres.get(i + 1) : -1;

        fat[blocoAtual] = blocoNovo;
        fat[blocoNovo] = proximo;

        byte[] parte = new byte[Disco.TAMANHO_BLOCO];
        int tamanho = 
          Math.min(Disco.TAMANHO_BLOCO, data.length - offset);
        System.arraycopy(data, offset, parte, 0, tamanho);
        offset += tamanho;

        try {
            disco.write(blocoNovo, parte);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever bloco.");
        }

        blocoAtual = blocoNovo;
    }

    entrada.tamanhoArquivo += data.length;

    try {
        gravaFat();
        gravaDiretorio();
    } catch (IOException e) {
        throw new RuntimeException("Erro ao atualizar metadados.");
    }
  }

  @Override
  public byte[] read(String fileName, int offset, int limit){
    System.out.println("***iniciando leitura de arquivo no disco***");
    //Obter a entrada se existir e validar argumentos
    EntradaDiretorio entrada = null;

    for(EntradaDiretorio e : diretorio){
      if(e.fileName.equals(fileName)){
        entrada = e;
        System.out.println("Arquivo encontrado...");
        break;
      }
    }

    if(entrada == null){
      throw new IllegalArgumentException("Arquivo não encontrado");
    }

    if(offset >= entrada.tamanhoArquivo){
      throw new IllegalArgumentException("offset maior que o arquivo");
    }
    
    //se o limite for maior que o tamanho que falta escolha oque falta
    int realLimit = Math.min(limit, entrada.tamanhoArquivo - offset);

    //aloca espaço para leitura de dados em byte
    byte[] resultado = new byte[realLimit];
    System.out.println("buffer de leitura para dados criado...");

    int bloco = entrada.blocoInicial;
    /*após obter o bloco de inicio avançemos até o bloco que tem os
     * dados que queremos que estao delimitados com o offset*/
    int skip = offset / Disco.TAMANHO_BLOCO;

    System.out.println("Bloco inicial do arquivo: "+bloco+" ...");
    System.out.println("indice de onde será lido: "+offset+" ...");
    System.out.println("total B a serem lidos: "+realLimit+" ...");

    int startInBloco = offset % Disco.TAMANHO_BLOCO;

    System.out.println("Blocos até chegar na leitura: "+skip+" ...");

    System.out.println("iterando até o bloco Para a leitura ...");
    
    for (int i = 0; i < skip; i++) {
      System.out.println("fat[bloco] --> " + bloco);
      bloco = fat[bloco];

    }

    System.out.println("Bloco para começar leitura: " + bloco);
    int pos = 0;
    while (pos < realLimit && bloco != -1){
      System.out.println("lendo dados do bloco: " + bloco);
      
      try{
        byte[] dados = disco.read(bloco);
        int bytesLer = 
          Math.min(realLimit - pos,Disco.TAMANHO_BLOCO - startInBloco);
        /*temos que saber quantos bytes devem ser lidos e vai depender
        * se oque falta é todo o bloco que esta sendo lido ou até
        * uma parte dele*/
      
        System.arraycopy(dados, startInBloco, resultado, pos, bytesLer);
        pos += bytesLer; //ajusta a posição para onde gravar no resul
        bloco = fat[bloco];//vai para o proximo bloco da fat
        startInBloco = 0; //no próximo bloco sempre começa do 0
      }catch(IOException e){
        throw new RuntimeException("erro ao ler bloco");
      }
    }

    return resultado;
  }
  
  @Override
  public void remove(String fileName){
    System.out.println("***Deletando arquivo***");
    EntradaDiretorio entrada = null;

    for(EntradaDiretorio e : diretorio){
      if(e.fileName.equals(fileName)){
        entrada = e;
        System.out.println("arquivo encontrado...");
        break;
      }
    }

    if(entrada == null){
      throw new IllegalArgumentException("Arquivo não encontrado");
    }

    System.out.println("Liberando blocos na FAT...");
    int bloco = entrada.blocoInicial;
    while(bloco != -1){
      System.out.println("bloco a ser excluido: " + bloco);
      int proximo = fat[bloco];
      fat[bloco] = 0; //bloco livre
      bloco = proximo;
    }

    System.out.println("Removendo entrada do diretorio");
    diretorio.remove(entrada);
    
    try{
      gravaFat();
      gravaDiretorio();
    }catch(IOException e){
      throw new RuntimeException("Erro ao salvar metadados");
    }
  }
  
  @Override
  public int freeSpace(){
    int livres = 0;
    for(int i : fat){
      if(i == 0){
        livres++;
      }
    }
    return livres * Disco.TAMANHO_BLOCO;
  }

}
