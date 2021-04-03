function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}

function calcularsalario(){
    var primvalor = document.problema09.horastra.value;
    var primresultado = parseInt(primvalor);

    if(primresultado <= 40){
        var resultado = primresultado * 17.7125;
    }
    if(40 < primresultado <= 48){
        var resultado = (40 * 17.7125) + ((primresultado-40) * 35.425);
    }
    if(primresultado > 48){
        var resultado = (40 * 17.7125) + (8 * 35.425) + ((primresultado-48) * 53.1375);
    }
    
    document.problema09.salario.value = "$"+resultado;

}

function borrar(){
    document.problema09.horastra.value= "";
    document.problema09.salario.value= "";
}