    
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

        //abre o modal2 para excluir e já passa o id do produto
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

            document.getElementById('name-edit').value = botao.dataset.nome;
           
            document.getElementById('codigo').value= botao.dataset.id;

            document.getElementById('valor-edit').value=botao.dataset.valor;

            document.getElementById('quantidade-edit').value=botao.dataset.quantidade;
            console.log(botao);
        }

        function fecharModal3(){
            document.querySelector('.modal3').style.display='none';
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