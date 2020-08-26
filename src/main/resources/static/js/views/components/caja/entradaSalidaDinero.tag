<entrada-salida>


<div id='modalEntradaSalidaDinero' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border " style="color:blue;background:{colorTemp} color: #ffffff;" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> {titulo}</h1>
            </div>
            <div class="modal-body">
                <form  >
                    <div class="row">    
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Motivo</label>
                            <input type="text" class="form-control tamanoClienteNuevo descripEntradaSalidaDinero modalInputCambioPrecio " id="descripEntradaSalidaDinero" >

                        </div>                            
                    </div>

                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Monto a Registrar </label>
                            <input type="number" class="form-control montoEntradaSalidaDinero tamanoClienteNuevo modalInputCambioPrecio"  id="montoEntradaSalidaDinero" name="montoEntradaSalidaDinero" autofocus="autofocus"   autocomplete="off">
                        </div>
                    </div>
 
                </form>  
                <div  id="listarentradaContainer" class="scrlollT">
                      <div id="listarEntradas">
                      <div  id =  "tituloListaentradas">
                          <div><span>Accion</span></div>
                          <div><span>Descripcion</span></div>
                          <div><span>Total</span></div>                      
                      </div>
                        <div class ="detalleListarEntradas"  each={listado}>
                            <div><button id="{id}" name="{id}"  onclick={__removeEntraSalidas} class="btn btn-danger btn-xs btn-block">X</button></div>
                            <div><span>{descripcion} </span></div>
                            <div><span>{totalSTR}</span></div> 
                        </div>
                    </div>    

                </div>  
                <div  id="totalEntradasSalidas">
                    <div></div>
                   
                    <div></div>
                     <div>
                        <span>Total:</span>
                        <span>{totalEntradasOrSalida}</span>
                    </div>               
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button data-dismiss="modal"   type="button" class="btn-ATRAS btn-back  pull-left modalCambioPrecioBotones"  >
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button show={parametro.tipo == 1} onclick={__RegistrarEntrada}   class=" btn-entrada pull-right modalCambioPrecioBotones"  > Entrada Dinero </button>
                    <button show={parametro.tipo == 2}  onclick={__RegistrarSalida}   class=" btn-salida pull-right modalCambioPrecioBotones" > Salida Dinero </button>
                </div>
            </div>
        </div>
    </div>
</div>






<style type ="text/css">
.scrlollT {
		overflow-y: scroll;
		overflow-x: scroll;
		height: 320px;
	}
	
	
    .btn-ATRAS {
        background-color: #3D3E42;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-left: 20px;
        padding-right: 20px;
        font-size: 26px;
        font-weight: bold;
        margin-right: 15px;
        border: none;
        float: right;
        cursor: pointer;
    }
    .btn-entrada {
        background-color: #4cae4c;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-left: 20px;
        padding-right: 20px;
        font-size: 27px;
        font-weight: bold;
        margin-right: 15px;
        border: none;
        float: right;
        cursor: pointer;
    }

    .btn-salida {
        background-color: #dd4b39;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-left: 20px;
        padding-right: 20px;
        font-size: 27px;
        font-weight: bold;
        margin-right: 15px;
        border: none;
        float: right;
        cursor: pointer;
    }

    .colorEntrada{
        background: #d1304e;
        color: #ffffff;
    }
    .colorSalida{
        background: #30d13c;
        color: #ffffff;
    }
    .tamanoClienteNuevo{
        font-size: 30px;
        font-weight: 600;
        color: black;
        height: 10%;
    }
    .modalTitleCambioPrecio{
        color: white;
    }
    .modalInputCambioPrecioCodigoDescripcion{
       border-radius: 10px !important;
       font-size: 40px !important;
       text-align: center !important;
    
    }
    .modalInputCambioPrecio{
        font-size: 40px !important;
        color:blue !important;
        border-radius: 16px !important;
    }
    .modalCambioPrecioBotones{
         border-radius: 16px !important;
    }
    #listarentradaContainer{
        display:flex;
        flex-direction: column;
    }
  
    #tituloListaentradas{
        font-size: 20px;
        font-weight: 800;
        font-family: Verdana, arial, sans-serif;
        display: flex;
        justify-content: space-between;
        flex-direction: row;
        padding-top: 20px;
    }
    .detalleListarEntradas{
        display:flex;
        justify-content: space-between;
        font-size: 20px;
        font-family: Verdana, arial, sans-serif;
    }
    #listarEntradas{
        display: flex;
        flex: 1;
        flex-direction: column;

    }
     #listarEntradas > div{
       justify-content: space-between;
       display: flex;
       font-size: 20px;
       font-family: Verdana, arial, sans-serif;
        align-items: center;
        padding-bottom: 5px;
            padding-top: 5px;
     }
    #listarEntradas > div > button{
        font-size: 18px;
        font-family: Verdana, arial, sans-serif;
        padding-left: 15px;
        padding-right: 15px;
        padding-left: 15px;
     }
    #totalEntradasSalidas{
        display: flex;
        flex-direction: row-reverse;
    }
    #totalEntradasSalidas > div >span{
      font-size: 20px;
      font-weight: 800;
      font-family: Verdana, arial, sans-serif;
 
    }

    </style>
<script>
    var selfEntradaDinero = this;
    selfEntradaDinero.parametro   = opts.parametros;  
    selfEntradaDinero.salida = false;
    selfEntradaDinero.entrada = false;
    selfEntradaDinero.motivo = ""
    selfEntradaDinero.colorTemp = "#30d13c;"
    selfEntradaDinero.colorBoton = "#4cae4c;"
    selfEntradaDinero.listado = []
    selfEntradaDinero.totalEntradasOrSalida = "0.00"

    selfEntradaDinero.valorTitulo = ""
    selfEntradaDinero.on('mount',function(){
            //Entrada de Dinero
        if(selfEntradaDinero.parametro.tipo == 1){
            selfEntradaDinero.entrada = true;
            selfEntradaDinero.colorTemp = "#30d13c;"
            selfEntradaDinero.motivo = "Entrada de Dinero"
            selfEntradaDinero.titulo = "Registrar Entrada de Efectivo"
        }
        // Salida de Dinero
        if(selfEntradaDinero.parametro.tipo == 2){
            selfEntradaDinero.salida = true;
            selfEntradaDinero.colorBoton = "#dd4b39;"
            selfEntradaDinero.colorTemp = "#d1304e;"
            selfEntradaDinero.titulo = "Registrar Salida de Efectivo"
            selfEntradaDinero.motivo = "Salida de Dinero"
        }
        __listado_entrada(selfEntradaDinero.parametro.tipo,null)
        selfEntradaDinero.update()
        showModal(selfEntradaDinero.motivo)
         var xTriggered1 = 0;
         $( "#montoEntradaSalidaDinero" ).keyup(function( event ) {
            xTriggered1++;
            var msg = "Handler for .keyup() called " + xTriggered1 + " time(s).";
        }).keydown(function( event ) {
            
            if ( event.which == 13 ) {
              if(selfEntradaDinero.entrada  == true){
                  registrarEntradaSalidaAjax(1,$.i18n.prop("entradaSalidaDinero.mensaje.alert.entrada"))
              }
              if(selfEntradaDinero.salida  == true){
                  registrarEntradaSalidaAjax(2,$.i18n.prop("entradaSalidaDinero.mensaje.alert.salida"))
              }

           }
        });

})

__removeEntraSalidas(e){
    __listado_entrada(selfEntradaDinero.parametro.tipo,e.item.id)
}

function showModal(motivo){
    $('#modalEntradaSalidaDinero').modal({backdrop: 'static', keyboard: true}) 
    $('#modalEntradaSalidaDinero').on('shown.bs.modal', function () {
        $(".descripEntradaSalidaDinero").val(motivo)
        $(".montoEntradaSalidaDinero").val(0)
        $(".montoEntradaSalidaDinero").focus()
        $(".montoEntradaSalidaDinero").select()
    })  

}
                         




__RegistrarEntrada(){
    registrarEntradaSalidaAjax(1,$.i18n.prop("entradaSalidaDinero.mensaje.alert.entrada"))
}



__RegistrarSalida(){
    registrarEntradaSalidaAjax(2,$.i18n.prop("entradaSalidaDinero.mensaje.alert.salida"))
}


/**
*Cerrar caja
**/
function registrarEntradaSalidaAjax(tipo,mensaje){
        var valor =__valorNumerico($(".montoEntradaSalidaDinero").val())
        if(valor == 0){
            mensajeAdvertencia("Digite el monto a registrar")
            $(".montoEntradaSalidaDinero").val(0)
            $(".montoEntradaSalidaDinero").focus()
            $(".montoEntradaSalidaDinero").select()
            return
        } 
        if(valor == null){
            mensajeAdvertencia("Digite el monto a registrar")
            $(".montoEntradaSalidaDinero").val(0)
            $(".montoEntradaSalidaDinero").focus()
            $(".montoEntradaSalidaDinero").select()
            return
            
        }
        if(valor > 5000000){
            mensajeAdvertencia("Monto mayor o igual a 5 millones no es permitido")
            $(".montoEntradaSalidaDinero").val(0)
            $(".montoEntradaSalidaDinero").focus()
            $(".montoEntradaSalidaDinero").select()
            return
            
        }
    
        var parametros = {
             descripcion:$(".descripEntradaSalidaDinero").val(),
             total:$(".montoEntradaSalidaDinero").val(),
             tipo :tipo
         }
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : parametros,
                    url : 'AgregarSalidaEntradaDineroAjax.do',
                    success : function(data) {
                        if (data.status != 200) {
                        	serverMessageJson(data);
                            if (data.message != null && data.message.length > 0) {
                                mensajeAdvertencia(data.message)
                            }
                        } else {
                            mensajeToasExito(data.message)
                            hidemodal()
                            $.each(data.listaObjetos, function( index, modeloTabla ) {
                                __ImprimirSalidaDinero(modeloTabla)  
                            })

                            

                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
                    }
                });
}

 function hidemodal(){
   $( '#modalEntradaSalidaDinero' ).remove();
   $( '.modal-backdrop' ).remove();
   $( 'body' ).removeClass( "modal-open" );
 //  $( '.modalEntradaSalidaDinero' ).hide();
 }

 function __ImprimirSalidaDinero(salidaEntradaDinero){
     var parametros = {
        salidaEntradaDinero:salidaEntradaDinero,
    }
     
    riot.mount('impentrada-salida',{parametros:parametros});
 }

 /**
*  Mostrar listado Entrada o salida
**/
function __listado_entrada(tipo,idEntradaSalida){
     var parametros = {
             idTipoEntrada :tipo,
             idEntradaSalida:idEntradaSalida
         }
    selfEntradaDinero.listado = []
    selfEntradaDinero.update()     
    $.ajax({
        url: "listarEntradasOrSalidas.do",
        datatype: "json",
        global: false,
        data : parametros,
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
               selfEntradaDinero.listado = result.aaData
               selfEntradaDinero.update() 
               sumarTotales()
             } 
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
}



function sumarTotales(){
    var total =  0
	$.each(selfEntradaDinero.listado , function( index, modeloTabla ) {
        total = total + modeloTabla.total
    })
    selfEntradaDinero.totalEntradasOrSalida  =  formatoDecimales(total,2)
    selfEntradaDinero.update()
}

</script>
</entrada-salida>