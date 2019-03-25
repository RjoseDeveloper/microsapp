/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {


    $('.funcionario').hide();
    $('.comerciante').hide();
    $('.creditoPenhor').hide();
    $('.creditovip').hide();

    $('#destino').change(function () {
        validar_credito($(this).val());
    });

    $('.func').hide();
});

$(".load_modal").click(function () {
    $('#credits_modal').modal();
})

function active_func() {
    $('.func').show();
}

function new_record(item) {

    if (item == "criar") {
        $('#instuicao_modal').modal();
    }
}

function validar_credito(v) {
    
     $.ajax({
        url: "/microsapp/GetJson",
        type: 'GET',
        dataType: 'json',
        data: {acao: 1, idtipocredito: v},
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) {
            
            $('#datai').val(data.di);
            $('#dataf').val(data.df);
        },
    });

    if (v == 1) {
        $('.results').html($('.funcionario').show('slow'));
    }

    if (v == 2) {
        $('.results').html($('.comerciante').show('slow'));
    }
    if (v == 3) {
        $('.results').html($('.creditoPenhor').show('slow'));
    }

    if (v == 4) {
        $('.results').html($('.creditovip').show('slow'));
    }

    
   
}

function get_val(item) {

    $.ajax({
        url: "/microsapp/GetJson",
        type: 'GET',
        dataType: 'json',
        data: {acao: 1, idtipocredito: item},
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) {
            $('#juro').val(data.juro);
            $('#qtdmes').val(data.pgto);
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}






