function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}

function encontrar(){
    var primvalor = document.problema08.primnum.value;
    var primresultado = parseFloat(primvalor);
    var segvalor = document.problema08.segnum.value;
    var segresultado = parseFloat(segvalor);
    var tervalor = document.problema08.ternum.value;
    var teresultado = parseFloat(tervalor);

    if((primresultado > segresultado) && (primresultado > teresultado)){
        document.problema08.mayor.value = primresultado;
    }
    if((segresultado > primresultado) && (segresultado > teresultado)){
        document.problema08.mayor.value = segresultado; 
    }
    if((teresultado > primresultado) && (teresultado > segresultado)){
        document.problema08.mayor.value = teresultado;
    }
    
}

function borrar(){
    document.problema08.primnum.value= "";
    document.problema08.segnum.value= "";
    document.problema08.ternum.value= "";
    document.problema08.mayor.value= "";
}