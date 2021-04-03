function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}

function porcentajes(){
    var primvalor = document.problema02.salariomen.value;
    var primresultado = parseInt(primvalor);

    var pagopv = primresultado * 0.30;
    var salarioto = primresultado + pagopv;

    document.problema02.pagoporv.value = "$"+pagopv;
    document.problema02.salariototal.value = "$"+salarioto;
    
}

function borrar(){
    document.problema02.salariomen.value= "";
    document.problema02.pagoporv.value= "";
    document.problema02.salariototal.value= "";
}