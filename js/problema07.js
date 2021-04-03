function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}

function operaciones(){
    var primvalor = document.problema07.primnum.value;
    var primresultado = parseFloat(primvalor);
    var segvalor = document.problema07.segnum.value;
    var segresultado = parseFloat(segvalor);

    if(primresultado == segresultado){
        var resultado = primresultado * segresultado;
    }
    else{ if(primresultado > segresultado){
        var resultado = primresultado - segresultado; 
        }
        else{
            var resultado = primresultado + segresultado;
        }
    }

    document.problema07.resultado.value = resultado;
    
}

function borrar(){
    document.problema07.primnum.value= "";
    document.problema07.segnum.value= "";
    document.problema07.resultado.value= "";
}