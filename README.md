# @Jonas-SLara 2025
# Visão Geral dos meus projetos em curso

## Sistema de estoque

### Descrição da tecnologia: 
- um projeto desenvolvido em maven com archetype webapp para desenvimento
de aplicações para web em java usando as especificações do jakarta EE, e 
usando servidor wildfly nas versões 30+ com o postgresq configurados via
docker, para um sistema de estoque de uma empresa chamada atacadão.

### Cenário
- A empresa atacadão dejesa ter um sistema de gerenciamento de estoque para
que seu usuarios que serão os gerentes e os funcionários possam usar para
organizar suas tarefas e ter visões gerais do sistema

- O gerente após fazer o login tem uma visão de seus produtos cadastrados e
de seus funcionários cadastrados por ele, o gerente dejesa pode mudar dados
de seus produtos e de seus funcionários.

- O funcionário quando realiza o login tem a visão dos produtos cadastrados
por seu gerente, podendo apenas visualizar.

- ambos precisam fazer o login e logout

- na pagina principal da empresa o usuario tem a opção de fazer o login ou
de se cadastrar, ao se cadastrar ele deve ligar para o rh da empresa que ficara
responsável de avisar ao gerente qual usuario deve ser cadastrado como 
funcionário. se for o caso de um novo gerente ser cadastrado este deve ser feito
pelo administrador do sistema

- o administrador do sistema tem a visão geral de tudo e pode alterar e cadastrar
novos gerentes
