/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    
    
    $('.funcionario').hide();
    $('.comerciante').hide();
    $('.creditoPenhor').hide();

    $('#destino').change(function(){
        validar_credito($(this).val());
    });

    $('.func').hide();
});

$(".load_modal").click(function(){
    $('#credits_modal').modal();
})

function active_func() {
    $('.func').show();
}

function new_record(item) {

    if (item=="criar"){
        $('#instuicao_modal').modal();
    }
}

function validar_credito(v){

    if(v == 1){
        $('.funcionario').show('');
        $('.comerciante').hide();
        $('.creditoPenhor').hide();
    }

    if(v == 2){
        $('.comerciante').show('');
        $('.funcionario').hide();
        $('.creditoPenhor').hide();
    }
    if (v == 3){
        $('.creditoPenhor').show('');
        $('.funcionario').hide();
        $('.comerciante').hide();
    }

    $('.modal_credits').click(function () {
        $('#modal_credits').modal();
    })
}






