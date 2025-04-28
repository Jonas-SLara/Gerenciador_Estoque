------------------------
COMO USAR ESTE PROJETO
------------------------

# Como executar as dependencia para o projeto
    1. execute na raiz do projeto onde esta o docker compose:
    --> docker compose up -d
    2. para parar execute
    --> docker compose down
    3. para ver mais informações use o docker ps ou docker inspect
    4. OPCIONAL MAS IMPORTANTE:
    você pode adicionar o pgadmin como serviço, escolha a imagem certa
    e as variaveis de ambiente e as portas mapeadas, exemplo do meu:

   pgadmin:
    image: dpage/pgadmin4
    container_name: pgadmin
    restart: always
    environment:
      PGADMIN_DEFAULT_EMAIL: admin@admin.com
      PGADMIN_DEFAULT_PASSWORD: admin1234
    ports:
      - "5050:80"
    depends_on:
      - postgres
 

# Como compilar e executar e empacotar o projeto para war
    1. todo o tutorial de maven esta no tutorialMaven.txt

# como exportar e importar o banco de dados para o projeto
    1. abra a pasta BD que tera um backup do banco de dados estoque em 
    formato sql, nunca exclua ele!
    --> docker exec -t <container_do_postgres> pg_dump -U <usuario_do_banco> <nome_do_banco> > <nome_do_banco>.sql
    SEGUINDO OS DADOS QUE ESTÃO NO DOCKER COMPOSE IA FICAR:
    --> docker exec -t postgres pg_dump -U admin estoque > estoque_dump.sql
*
    2. como não há volumes entao deve ser feito importações do banco de dados que esta na pasta db
    ou outro backup que voce fez dele, para isso o comando:

    --> docker exec -i postgres psql -U admin -d estoque < bd/dump_estoque.sql 
    'lembre-se de estar na raiz do projeto'

    --ou use o pgadmin para importar os dados ou exportar mas para isso adicione o pgadmin
    como serviço no docker compose como sugerido antes
