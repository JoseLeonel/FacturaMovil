<articulo-imprimir>

<div class="modal fade imprimirModalArticulo" id="imprimirModalArticulo" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <div class="pantalla-imprimir">
            <section class="zona-impresion" id="imprimirArticulo" name ="imprimirArticulo">
                    <div class="imprimirArticulo" id="imprimirArticulo" name="imprimirArticulo" > 
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <h1 >{articulo.codigo}<h1>
                                    <h1>{articulo.descripcion}<h1>
                                <h1 >Precio: {articulo.precioPublico}<h1>

                            </div>
                        </div> 

                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <div id="barcode"></div>

                            </div>
                        </div>                         

                </div>
            </section>
            
        </div>
    </div>    
    <div class="modal-footer">
        <div class="botones-imprimir pull-right">
            <a href="#" class="boton-imprimir"  onclick = {__ImprimirTiquete} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir</a>
        </div>
    </div>
    </div>
  </div>
</div>

 



<style type="text/css">
    .fondoEncabezado
    {
        background: #00539B;
        color: #f9fafc;
    }
    .wrap
    {
        margin: auto;
        max-width: 1100;
        width: 90%;
    }
    .wrap >h1
    {
        color: #494B4D;
        display: flex;
        flex-direction: column;
        font-weight: 400;
        margin: 15px 0px;
        text-align: center;
    }
    .wrap > h1:after
    {
        background: #C7C7C7;
        content: '';
        height: 1px;
        margin: 20px 0;
        width: 100%;
    }
    .pantalla-imprimir
    {
        display: flex;
        flex-wrap: wrap;
    }
    .botones-imprimir
    {
        display: flex;
        flex-direction: column;
        width: 20%;
    }
    .botones-imprimir .boton-imprimir
    {
        background-color: #6dca42 !important;
        border: none;
        border-radius: 30px;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 5px 0 rgba(0, 0, 0, 0.20);
        color: #fff;
        cursor: pointer;
        font-size: 18px;
        margin: 15px;
        margin-bottom: 8px;
        moz-transition: background-color 100ms linear;
        ms-transition: background-color 100ms linear;
        o-transition: background-color 100ms linear;
        padding: 10px;
        padding-bottom: 8px !important;
        padding-top: 8px !important;
        text-align: center !important;
        text-decoration: none;
        transition: background-color 100ms linear;
        webkit-transition: background-color 100ms linear;
    }

  
    .imprimirArticulo
    {
        color: #000;
        float: left;
        font-family: 'Times New Roman';
        font-size: 14px;
        font-style: normal;
        font-variant: normal;
        font-weight: normal;
        height: auto;
        line-height: normal;
        margin: 0px;
        max-width: 377px;
        padding: 0px;
        text-transform: none;
        width: 377px;
    }
    .imprimirArticulo > table
    {
        border-collapse: collapse;
        border-top: 0px solid black;
    }
    .forma-table
    {
        border-collapse: collapse;
        border-top: 1px solid black;
    }
    .imprimirArticulo > td.producto,th.producto
    {
        max-width: 75px;
        width: 377px;
    }
    .imprimirArticulo > td.cantidad,th.cantidad
    {
        width: 377px;
        word-break: break-all;
    }
    .imprimirArticulo > td.precio,th.precio
    {
        width: 377px;
        word-break: break-all;
    }
    .encabezado
    {
        align-content: left;
        text-align: left;
    }
    .imprimirArticulo > img
    {
        flex-wrap: wrap;
        width: 80%;
    }
    .zona-impresion .forma-impresion
    {
        align-items: center;
        align-self: flex-start;
        background-color: white !important;
        box-shadow: 0px 0px 6px 0px rgba(0,0,0,0.70);
        display: flex;
        flex-direction: column;
        margin-bottom: 45px;
        margin-left: 1%;
        transition: all .4s;
        width: 80%;
    }
    .encabezado
    {
        align-content: left;
        text-align: left;
    }
  @media print {
* {
    -webkit-print-color-adjust: exact !important; /*Chrome, Safari */
    color-adjust: exact !important;  /*Firefox*/
  }
}
@media only print
{
    body * { display: none !important; }
    body:after { content: "Don't waste paper!"; }
}

</style>    
<script>
var self = this;
self.articulo   = opts.articulo;  
self.on('mount',function(){
    if(self.articulo.id > 0){
       self.articulo.precioPublico = __valorNumerico(self.articulo.precioPublico) 
       self.articulo.precioPublico = self.articulo.precioPublico > 0 ?formatoDecimales(self.articulo.precioPublico,2):0  
       self.update()
       $("#barcode").barcode(
          self.articulo.codigo, // Valor del codigo de barras
          "codabar" // tipo (cadena)
        );
       $('.imprimirModalArticulo').modal('show'); 
    }
})
/**
*Imprimir facturaImpresa
**/    
__ImprimirTiquete(){
    $('#imprimirModalArticulo').modal('toggle') 
    __imprimirTique()
    $("#boton-regresar").focus()
}
/**
*imprimir
**/
function __imprimirTique(){
    var objeto=document.getElementById('imprimirArticulo');  //obtenemos el objeto a imprimir
    var div = document.querySelector("#imprimirArticulo");
    imprimirTiqueteT(div)
}
/**
*imprimir el tiquete
**/
function imprimirTiqueteT(elemento){
  var ventana = window.open('', 'PRINT', 'height=400,width=600');
  ventana.document.write('<html><head><title>' + "" + '</title>');
  ventana.document.write('</head><body >');
  ventana.document.write(elemento.innerHTML);
  ventana.document.write('</body></html>');
  ventana.document.close();
  ventana.focus();
  ventana.print();
  ventana.close();
  return true;
}


/**
*  retorna el valor numerico o cero sino es numerico
**/
function __valorNumerico(valor){
    return isNumber(valor)?parseFloat(valor):0 ;
}
/**
*  Validar si es numero
**/
function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}

function redondearDecimales(numero, decimales) {
    numeroRegexp = new RegExp('\\d\\.(\\d){' + decimales + ',}');   // Expresion regular para numeros con un cierto numero de decimales o mas
    if (numeroRegexp.test(numero)) {         // Ya que el numero tiene el numero de decimales requeridos o mas, se realiza el redondeo
        return Number(numero.toFixed(decimales));
    } else {
        return Number(numero.toFixed(decimales)) === 0 ? 0 : numero;  // En valores muy bajos, se comprueba si el numero es 0 (con el redondeo deseado), si no lo es se devuelve el numero otra vez.
    }
}

</script>
</articulo-imprimir>