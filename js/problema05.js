function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}

function porcentajes(){
    var primvalor = document.problema05.numestud.value;
    var primresultado = parseInt(primvalor);

    var segvalor = document.problema05.numhomb.value;
    var segresultado = parseInt(segvalor);

    var tervalor = document.problema05.nummuj.value;
    var teresultado = parseInt(tervalor);

    var porcentajehom = (segresultado*100)/primresultado;
    var porcentajemuj = (teresultado*100)/primresultado;

    document.problema05.porcentah.value = porcentajehom+"%";
    document.problema05.porcentam.value = porcentajemuj+"%";
    
}

function borrar(){
    document.problema05.numestud.value= "";
    document.problema05.numhomb.value= "";
    document.problema05.nummuj.value= "";
    document.problema05.porcentah.value= "";
    document.problema05.porcentam.value= "";
}