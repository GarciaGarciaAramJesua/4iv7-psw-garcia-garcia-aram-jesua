function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}

function calificacionfinal(){
    var primvalor = document.problema04.primparcial.value;
    var primresultado = parseFloat(primvalor);
    var segvalor = document.problema04.segparcial.value;
    var segresultado = parseFloat(segvalor);
    var tervalor = document.problema04.terparcial.value;
    var teresultado = parseFloat(tervalor);
    var promedio = (primresultado + segresultado + teresultado)/3;
    var primcrit = promedio*0.55;

    var cuarvalor = document.problema04.examfinal.value;
    var cuarresultado = parseFloat(cuarvalor);
    var segcrit = cuarresultado*0.30;

    var quinvalor = document.problema04.trabafinal.value;
    var quinresultado = parseFloat(quinvalor);
    var tercrit = quinresultado*0.15;

    var calificacion = primcrit + segcrit + tercrit;

    document.problema04.califinal.value = calificacion;
}

function borrar(){
    document.problema04.primparcial.value= "";
    document.problema04.segparcial.value= "";
    document.problema04.terparcial.value= "";
    document.problema04.examfinal.value= "";
    document.problema04.trabafinal.value= "";
    document.problema04.califinal.value= "";
}