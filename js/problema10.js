function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}

function calcularsalarioanual(){
    var primvalor = document.problema10.añostiem.value;
    var primresultado = parseFloat(primvalor);

    if(primresultado < 1){
        var salariomen = 4251 * 1.05;
        var salarioanual = ((primresultado * 12) * salariomen);
    }
    if(1 <= primresultado < 2){
        var salariomen = 4251 * 1.07;
        var salarioanual = ((primresultado * 12) * salariomen);
    }
    if(2 <= primresultado < 5){
        var salariomen = 4251 * 1.10;
        var salarioanual = ((primresultado * 12) * salariomen);
    }
    if(5 <= primresultado < 10){
        var salariomen = 4251 * 1.15;
        var salarioanual = ((primresultado * 12) * salariomen);
    }
    if(primresultado >= 10){
        var salariomen = 4251 * 1.20;
        var salarioanual = ((primresultado * 12) * salariomen);
    }
    
    document.problema10.salarioan.value = "$"+salarioanual;

}

function borrar(){
    document.problema10.añostiem.value= "";
    document.problema10.salarioan.value= "";
}