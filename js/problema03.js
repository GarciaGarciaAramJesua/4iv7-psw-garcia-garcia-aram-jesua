function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}

function descuento(){
    var valor = document.problema03.cantidad.value;
    var resultado = parseInt(valor);
    var total = resultado*0.85;

    document.problema03.compratd.value="$"+total;
}

function borrar(){
    document.problema03.cantidad.value= "";
    document.problema03.compratd.value= "";
}