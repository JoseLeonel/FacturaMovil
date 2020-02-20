<imprimir-caja>

<div class="modal fade imprimirModalCorte" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <h1>{$.i18n.prop("imprimir.caja.titulo")}#{usuarioCaja.id}<h1>
        <div class="pantalla-imprimir">
            <div class="botones-imprimir">
                <a href="#" class="boton-imprimir"  onclick = {__ImprimirFactura} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir(F8)</a>
                <a href="#" class="boton-imprimir" id="boton-regresar" onclick = {__RegresarVentaImprimir}><i class="glyphicon glyphicon-arrow-left"></i>&nbsp;Regresar(Esc)</a>
            </div>
            <section class="zona-impresion" id="imprimemeCorte">
                <div class="forma-impresion">
                    <div class="ticket" > 
                        <form id = "formularioUsuarioCajaImprimir" name ="formularioUsuarioCajaImprimir"   class="advanced-search-form formularioUsuarioCajaImprimir">
                           <input type="hidden" name="id" id="id" value="{usuarioCaja.id}">
                        </form>   
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.titulo")}     </strong><br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.usuario")}       </strong><br></div>
                        <div class="encabezado"><strong> </strong>{usuarioCaja.usuario.nombre} {usuarioCaja.usuario.primerApellido} {usuarioCaja.usuario.segundoApellido}<br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.caja")} :         </strong>{usuarioCaja.caja.descripcion}<br></div>
                        <div class="encabezado"><strong> Apertura de Caja:{usuarioCaja.created_atSTR}</strong></div>    
                        <div class="encabezado" ><div class= "tituloCierre"><strong> Fondo Inicial : {usuarioCaja.totalFondoInicialSTR}</strong></div></div>    
                        <br show ={apertura }>
                        <table class = "forma-table" show ={apertura }>
                           <thead>
                                <tr class = "forma-table">
                                    <th class="cantidad">Cant  </th>
                                    <th class="producto">Moneda/Billete</th>
                                    <th class="precio">Total </th>
                                </tr>
                            </thead>
                             <tbody>
                                <tr each={usuarioCaja.aperturaCaja} class="detalleTables">
                                    <td class="valorPro" >{cantidad}</td>
                                    <td class="valorPro" >{denominacion}</td>
                                    <td class="valorPro" >{total}</td>
                                </tr>
                                
                            </tbody>
                            </table> 
                            <br show ={apertura }>
                             <div class="encabezado" show = {parametros.tipo == 2 }><div class= "tituloCierre"><strong> Cierre : {usuarioCaja.cierreCajaSTR}</strong></div></div>    
                            <div class="encabezado" show = {parametros.tipo == 2 }><div  class= "tituloCierre"><strong> Conteo Cierre : {usuarioCaja.conteoManualSTR}</strong></div></div>    
                            <table class = "forma-table" show = {cierre}>
                           <thead>
                                <tr class = "forma-table">
                                    <th class="cantidad">Cant  </th>
                                    <th class="producto">Moneda/Billete</th>
                                    <th class="precio"> Total </th>
                                </tr>
                            </thead>
                             <tbody>
                                <tr  each={usuarioCaja.cierreCaja} class="detalleTables">
                                    <td class="valorPro" >{cantidad}</td>
                                    <td class="valorPro" >{denominacion}</td>
                                    <td class="valorPro" >{total}</td>
                                </tr>
                                
                            </tbody>
                            </table> 
                        <br show = {cierre}>
                        <table class = "forma-table" show = {parametros.tipo == 2 }>
                           <thead>
                                <tr class = "forma-table">
                                    <th class="titulo">  </th>
                                    <th class="titulo"></th>
                                    <th class="titulo"> </th>
                                </tr>
                            </thead>
                             <tbody>
                                <tr class="detalleTables">
                                    <td class="valorPro" >{$.i18n.prop("imprimir.caja.totalEfectivo")}:</td>
                                    <td class="valorPro" >{usuarioCaja.totalEfectivoSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > {$.i18n.prop("imprimir.caja.totalTarjeta")} :</td>
                                    <td class="valorPro" >{usuarioCaja.totalTarjetaSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > {$.i18n.prop("imprimir.caja.totalBanco")}    : </td>
                                    <td class="valorPro" >{usuarioCaja.totalBancoSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > {$.i18n.prop("imprimir.caja.totalAbono")} : </td>
                                    <td class="valorPro" >{usuarioCaja.totalAbonoSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > Ventas:   </td>
                                    <td class="valorPro" >{usuarioCaja.totalNetoSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > + Entradas:   </td>
                                    <td class="valorPro" >{usuarioCaja.sumaEntradasSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > - Salidas:   </td>
                                    <td class="valorPro" >{usuarioCaja.sumaSalidaSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > Dolares a Colones:   </td>
                                    <td class="valorPro" >{usuarioCaja.conteoDolarConversionSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > {$.i18n.prop("imprimir.caja.totalNeto")}   </td>
                                    <td class="valorPro" >{usuarioCaja.totalGeneralSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > Diferencia   </td>
                                    <td class="valorPro" >{usuarioCaja.diferenciaTotalSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > Venta Dolares    </td>
                                    <td class="valorPro" >{usuarioCaja.totalDolaresSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > Conteo Dolares    </td>
                                    <td class="valorPro" >{usuarioCaja.conteoDolarSTR}</td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" > Tipo Cambio Recibido    </td>
                                    <td class="valorPro" >{usuarioCaja.tipoCambioSTR}</td>
                                </tr>

                                <tr class="detalleTables">
                                    <td class="valorPro" >  {$.i18n.prop("usuarioCaja.totalServicio")}     </td>
                                    <td class="valorPro" >{usuarioCaja.totalServicioSTR}</td>
                                </tr>
                                
                                <tr class="detalleTables">
                                    <td class="valorPro" >      </td>
                                    <td class="valorPro" ></td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" >    *** *** ***   </td>
                                </tr>
                            </tbody>
                            </table> 

                   </div>
                </div>
            </section>
            
        </div>
    </div>    

    </div>
  </div>
</div>


<style type="text/css">
  .tituloCierre{
      font-size:16px!important;
  }
  .valorPro{
      font-size:15px!important;
      padding: 2%!important;;
  }
    .titulo{
        font-size:16px!important;
        padding-right: 2%;

    }

  .fondoEncabezado {
        background: #00539B;
        color: #f9fafc;
        display:flex!important;
    }
    .elemen1{
        font-size:18px!important;
    }
    
    .wrap{
        max-width:1100;
        width:90%;
        margin:auto;
    }
    .wrap >h1{
        color: #494B4D;
        font-weight: 400;
        display: flex;
        flex-direction: column;
        text-align: center;
        margin: 15px 0px;
    }
    .wrap > h1:after{
        content: '';
        width: 100%;
        height: 1px;
        background: #C7C7C7;
	    margin: 20px 0;
    }
    .pantalla-imprimir{
        display: flex;
	    flex-wrap: wrap;
    }
    .botones-imprimir{
        display: flex;
	    flex-direction: column;
	    width: 20%;
    }
    .botones-imprimir .boton-imprimir{
        cursor: pointer;
        padding: 10px;
        margin: 15px;
        border: none;
        color: #fff;
        text-decoration:none;
        font-size: 18px;  
        padding-top:8px !important; 
        padding-bottom:8px !important; 
        margin-bottom: 8px;
        text-align: center !important;
        background-color: #6dca42 !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 5px 0 rgba(0, 0, 0, 0.20);
        border-radius: 30px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
     }
   .ticket {
        height: auto;
        width: 377px;
        margin: 0px;
        padding: 0px;
        float: left;
        font-style: normal;
        line-height: normal;
        font-weight: normal;
        font-variant: normal;
        text-transform: none;
        color: #000;
        font-size: 14px;
        font-family: 'Times New Roman';
        
        max-width: 377px;
       
    }
    .ticket > table{
        border-top: 0px solid black;
        border-collapse: collapse;
    }
    .forma-table {
        border-top:1px solid black;
        border-collapse: collapse;
    }
    .ticket > td.producto,th.producto {
        width: 377px;
        max-width: 75px;
    }
    .ticket > td.cantidad,th.cantidad {
        width: 377px;
        word-break: break-all;
    }
    .ticket > td.precio,th.precio {
        width: 377px;
        word-break: break-all;
    }
    .encabezado {
        text-align: left;
        align-content: left;
    }
    .ticket > img {
        max-width: inherit;
        width: inherit;
    }
    @page{
       margin: 0;
       
    }
    
    .zona-impresion{
        width: 80%;
	    display: flex;
	    flex-wrap: wrap;
     }
     .zona-impresion .forma-impresion{
        width: 80%;
        margin-left: 1%;
        margin-bottom: 45px;
        box-shadow: 0px 0px 6px 0px rgba(0,0,0,0.70);
        display: flex;
        flex-direction: column;
        align-items: center;
        align-self: flex-start;
        background-color: white !important;
        transition: all .4s;
     
    }
    .encabezado {
        text-align: left;
        align-content: left;
    }
    

</style>    

<script>

var self = this;
self.parametros   = opts.parametros;  
self.cierre =false;

self.usuarioCaja = {}


self.on('mount',function(){
    self.usuarioCaja = self.parametros.usuarioCaja
    self.update()
   consultaCaja()
    __Teclas()
   

})

/**
*consultar Facturas por Consecutivo
**/
function consultaCaja(){
   var formulario = $('#formularioUsuarioCajaImprimir').serialize();
     $.ajax({
        url: "MostrarUsuarioCajaAjax.do",
        datatype: "json",
        data: formulario,
        method:"Post",
        success: function (data) {
            self.usuarioCaja = null
            self.update()
            $.each(data.listaObjetos, function( index, modeloTabla ) {
                self.usuarioCaja = modeloTabla    
                self.usuarioCaja.created_at = displayDate_detail(self.usuarioCaja.created_at)
                self.usuarioCaja.updated_at = displayDate_detail(new Date())
                
                self.usuarioCaja.aperturaCaja =getDesglose(self.usuarioCaja.conteoManualCajas,1)
                self.usuarioCaja.aperturaCaja = self.usuarioCaja.aperturaCaja.sort(compare)
                self.usuarioCaja.cierreCaja =getDesglose(self.usuarioCaja.conteoManualCajas,2)
                self.usuarioCaja.cierreCaja = self.usuarioCaja.cierreCaja.sort(compare)
                self.cierre =self.usuarioCaja.cierreCaja.length > 0 ?true:false 
                self.apertura =self.usuarioCaja.aperturaCaja.length > 0?true:false 
                self.update()
                
                
            })
            if(self.usuarioCaja !=null){
               $('.imprimirModalCorte').modal('show');
            }
             
       
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
     

}

function getDesglose(conteoManualCajas,tipo){
    var desglose = []

    $.each(conteoManualCajas, function( index, modeloTabla ) {
        if(modeloTabla.tipo == tipo){
            desglose.push({
                id:modeloTabla.id,
                moneda:modeloTabla.moneda,
                cantidad : modeloTabla.cantidad,
                total:modeloTabla.total,
                denominacion : modeloTabla.denominacion
            })
        }

    })
    return desglose;
}
/**
*   ordena de mayor a menor
**/
function compare(a, b) {
  if (a.moneda < b.moneda) return 1;
  if (b.moneda < a.moneda) return -1;

  return 0;
}

/**
*  teclas de la pantalla
**/      
function __Teclas(){
    window.addEventListener( "keydown", function(evento){
        var tecla = evento.keyCode; 
    if(tecla ==119){
        //Pantalla de imprimir F8 imprimir
        var objeto=document.getElementById('imprimemeCorte');  //obtenemos el objeto a imprimir
        var ventana=window.open('','_blank');  //abrimos una ventana vacía nueva
        ventana.document.write(objeto.innerHTML);  //imprimimos el HTML del objeto en la nueva ventana
        ventana.document.close();  //cerramos el documento
        ventana.print();  //imprimimos la ventana
        ventana.close();  //cerramos la ventana
         }    

    
    }, false );
  
   

}

function displayDate_detail(fecha) {
    return fecha == null?"":moment(fecha).format('DD/MM/YYYY h:mm:ss a');
}

/**
*Imprimir factura
**/    
__ImprimirFactura(){
    var objeto=document.getElementById('imprimemeCorte');  //obtenemos el objeto a imprimir
          var ventana=window.open('','_blank');  //abrimos una ventana vacía nueva
          ventana.document.write(objeto.innerHTML);  //imprimimos el HTML del objeto en la nueva ventana
          ventana.document.close();  //cerramos el documento
          ventana.print();  //imprimimos la ventana
          ventana.close();  //cerramos la ventana

           $("#boton-regresar").focus()
}

</script>



</imprimir-caja>