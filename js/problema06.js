function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}

function calcularedad(){
    var primvalor = document.problema06.añonaci.value;
    var primresultado = parseInt(primvalor);
    var edad = 2021-primresultado;

    document.problema06.edad.value = edad;
    
}

function borrar(){
    document.problema06.añonaci.value= "";
    document.problema06.edad.value= "";
}