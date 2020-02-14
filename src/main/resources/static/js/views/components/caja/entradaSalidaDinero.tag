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
                            <input type="text" class="form-control tamanoClienteNuevo descripEntradaSalidaDinero " id="descripEntradaSalidaDinero" >

                        </div>                            
                    </div>

                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Monto a Registrar </label>
                            <input type="number" class="form-control montoEntradaSalidaDinero tamanoClienteNuevo modalInputCambioPrecio"  id="montoEntradaSalidaDinero" name="montoEntradaSalidaDinero" autofocus="autofocus"   autocomplete="off">
                        </div>
                    </div>
 
                </form>    
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button data-dismiss="modal"   type="button" class="btn-dark-gray btn-back  pull-left modalCambioPrecioBotones"  >
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__AplicarCambioPrecioUltimoArticulo}   class=" btn-green pull-right modalCambioPrecioBotones" > Entrada Dinero </button>
                </div>
            </div>
        </div>
    </div>
</div>
<style type ="text/css">
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

   
    </style>
<script>
    var selfEntradaDinero = this;
    selfEntradaDinero.parametro   = opts.parametros;  
    selfEntradaDinero.salida = false;
    selfEntradaDinero.entrada = false;
    selfEntradaDinero.motivo = ""
    selfEntradaDinero.colorTemp = "#30d13c;"
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
     selfEntradaDinero.colorTemp = "#d1304e;"
     selfEntradaDinero.titulo = "Registrar Salida de Efectivo"
     selfEntradaDinero.motivo = "Salida de Dinero"
  }
  selfEntradaDinero.update()
  showModal(selfEntradaDinero.motivo)
})



function showModal(motivo){
    $('#modalEntradaSalidaDinero').modal({backdrop: 'static', keyboard: true}) 
    $('#modalEntradaSalidaDinero').on('shown.bs.modal', function () {
        $(".descripEntradaSalidaDinero").val(motivo)
        $(".montoEntradaSalidaDinero").val(0)
        $(".descripEntradaSalidaDinero").focus()
        $(".descripEntradaSalidaDinero").select()
    })  

}
                         




__cierre(){
    cerrarCajaAjax()
}



/**
*Cerrar caja
**/
function cerrarCajaAjax(){
        var formulario = {
             id:self.usuarioCaja.id,
             caja:self.usuarioCaja.caja.id,
             conteoTarjeta:$(".conteoTarjeta").val(),
             tipoCambio:$(".tipoCambio").val(),
             conteoDolar:$(".conteoDolar").val(),
             denominacion :JSON.stringify(__CrearListaMonedas(2))
         }
        swal({
           title: '',
           text: $.i18n.prop("usuarioCaja.mensaje.alert.cerrar"),
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
        }).then(function (isConfirm) {
            //Ajax__inicializarTabla();
            if(isConfirm){
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : 'CerrarUsuarioCajaAjax.do',
                    success : function(data) {
                        if (data.status != 200) {
                        	serverMessageJson(data);
                            if (data.message != null && data.message.length > 0) {
                                mensajeAdvertencia(data.message)
                            }
                        } else {
                            mensajeToasExito(data.message)
                             __listado()
                             hidemodal()
                             $.each(data.listaObjetos, function( index, modeloTabla ) {
                                self.usuarioCaja = modeloTabla    
                                self.update()
                            })
                            riot.mount('imprimir-caja',{usuarioCaja:self.usuarioCaja});

                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
                    }
                });
            }
        });
}

</script>
</entrada-salida>