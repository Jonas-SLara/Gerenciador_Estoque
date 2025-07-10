    
    /*todas as funções para abrir os pop-up na tela de produtos e passar os devidos
    atributos a cada formulario ou botão que enviará as requisições por GET ou POST
    para o controller
    */
        function abrirModal(){
            document.querySelector('.modal').style.display='block';
            document.querySelector('.overlay').style.display='block';
        }

        function fecharModal(){
            document.querySelector('.modal').style.display='none';
            document.querySelector('.overlay').style.display='none';
        }

        //abre o modal2 para excluir e já passa o id do item a ser excluido, funciona para produto e funcionario
        function abrirModal2(botao){
            
            console.log(botao);
            const btn_excluir = document.getElementById('btn-excluir');
            btn_excluir.href=botao.dataset.url;
            document.querySelector('.modal2').style.display='block';
            document.querySelector('.overlay').style.display='block';
        }

        function fecharModal2(){
            document.querySelector('.modal2').style.display='none';
            document.querySelector('.overlay').style.display='none';
        }

//abrir o modal 3 para editar o produto
function abrirModal3(botao){
        
    document.querySelector('.modal3').style.display='block';
    document.querySelector('.overlay').style.display='block';

    const nameEdit = document.getElementById('name-edit');
    const codigo = document.getElementById('codigo');
    const valorEdit = document.getElementById('valor-edit');
    const quantidadeEdit = document.getElementById('quantidade-edit');
    //se existirem no formulario as opções acima entao setar elas com o valor padrão
    if(nameEdit) nameEdit.value = botao.dataset.nome;
    if(codigo) codigo.value = botao.dataset.id;
    if(valorEdit) valorEdit.value = botao.dataset.valor;
    if(quantidadeEdit) quantidadeEdit.value = botao.dataset.quantidade;
    console.log(botao);
}

function fecharModal3(){
    document.querySelector('.modal3').style.display='none';
    document.querySelector('.overlay').style.display='none';
}

//abrir o modal 4 para editar o funcionario
function abrirModal4(botao){
        
    document.querySelector('.modal4').style.display='block';
    document.querySelector('.overlay').style.display='block';

    document.getElementById('salario-edit').value = botao.dataset.salario;
    document.getElementById('cargo-edit').value = botao.dataset.cargo;
    document.getElementById('cpf-edit').value = botao.dataset.cpf;
    document.getElementById('id-edit').value= botao.dataset.id;

    console.log(botao);
}

function fecharModal4(){
    document.querySelector('.modal4').style.display='none';
    document.querySelector('.overlay').style.display='none';
}

function abrirModalPerfil(){
    document.querySelector('.modalPerfil').style.display='block';
    document.querySelector('.overlay').style.display='block';
}

function fecharModalPerfil(){
    document.querySelector('.modalPerfil').style.display='none';
    document.querySelector('.overlay').style.display='none';
}

function fecharModal5(){
    document.querySelector('.modal5').style.display='none';
    document.querySelector('.overlay2').style.display='none';
}

function fecharModal6(){
    document.querySelector('.modal6').style.display='none';
    document.querySelector('.overlay2').style.display='none';
}