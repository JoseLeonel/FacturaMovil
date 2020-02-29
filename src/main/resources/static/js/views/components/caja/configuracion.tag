<config-caja>
  
<div id='modalConfiguracion' class="modal fade  " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" width=800px;>
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Indique los Correos Electronicos que desea enviar el cierre caja </h1>
            </div>
            <div class="modal-body">
                 <form class="form-horizontal formulario" name= "formulario" id="formulario">
                    <div class="row">    
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Digite el Correo Uno </label>
                            <input type="email" class="form-control correoCaja1 tamanoClienteNuevo modalInputCambioPrecio"  id="correoCaja1" name="correoCaja1" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                    </div>
                    <div class="row">    
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="tituloClienteNuevo" >Digite el Correo Dos </label>
                            <input type="email" class="form-control correoCaja2 tamanoClienteNuevo modalInputCambioPrecio"  id="correoCaja2" name="correoCaja2" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                    </div>
                </form>    

            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button onclick={__Regresar}   type="button" class="btn-dark-gray btn-back  pull-left modalCambioPrecioBotones"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__actualizar}   class=" btn-green pull-right modalCambioPrecioBotones" > Aplicar </button>
                </div>
            </div>
        </div>
    </div>
</div>




<style type ="text/css">
.modalBox {
   background: #fff !important;
   border-radius: 16px !important;
   position: absolute !important;
   top: 44% !important;
   left: 50% !important;
   transform: translate(-50%, -50%) !important;
   max-height: 90% !important;
   overflow-y: auto !important;
   padding: 30px !important;
   box-sizing: border-box !important;
   max-width: 90% !important;
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
        border-color: green;
    }
    .modalCambioPrecioBotones{
         border-radius: 16px !important;
         font-size: 35px !important;;
    }

    .fontSumarRestar{
        font-size: 20px;
    }
    .fondoEncabezado {
        background: #00539B;
        color: #f9fafc;
    }
  .requeridoDato {
            color: red;
            text-align: left;
            font-weight: 500;
            font-size: 13px;
        }
    
        .wrap{
            max-width:1100px;
            width:100%;
        }
        body {
            overflow: hidden;
            background:white;
            font-size: 12px !important;
        }
        .contenedor-listar{
            width:100%;
        }
        .input-table-search{
            margin-left: 15px;
            margin-right: 15px;
            width:100%;
        }
        .botonConsulta{
            margin-top:28px;
        }
        
        table td{ 
            text-align: center;
            font-size: 12px;
            
                }
        table th {
                text-align: center;
                font-size: 12px;
        }
        th, td {
            white-space: nowrap;
        }
    </style>
<script>
    var self = this;
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del Listado de la Tabla 
    self.mostrarListado            = true 
    self.cajas                  = {aaData:[]}
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarVerDetalle         = false
    self.mostrarConsultaComanda    = false
    self.empresa = {
        id:null,
        descripcion:"",
        estado:""
    }
   
self.on('mount',function(){
    $("#formulario").validate(reglasDeValidacion());
    $(".errorServerSideJgrid").remove();
    __consultar()

})

var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			                                                 
            correoCaja1 : {
                required : true,
                maxlength:60,
                minlength:1,
                email:true
			},                                                
            correoCaja2 : {
                maxlength:60,
                minlength:1,
                email:true
			}                                          
                        
		},
		ignore : []

	});
	return validationOptions;
};

function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#correoCaja1").attr("maxlength", 100);
    $("#correoCaja2").attr("maxlength", 100);
    
}

__Regresar(){
    hidemodal()
}

function hidemodal(){
    $('#modalConfiguracion').modal('hide')
}


/**
*  Regresar al listado
**/
__regresarAlListado(){
    self.mostrarListado     = true;
    self.botonAgregar       = false;
    self.mostrarFormulario  = false 
    self.update()
    __listado();
}
function showModal(){
    $('#modalConfiguracion').modal({backdrop: 'static', keyboard: false}) 
    $('#modalConfiguracion').on('shown.bs.modal', function () {
        $(".correoCaja1").val( self.empresa.correoCaja1)
        $(".correoCaja2").val( self.empresa.correoCaja2)
        $(".correoCaja1").focus()
        $(".correoCaja1").select()
    })  

}
/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(){
    $.ajax({
        url: "ParametrosEmpresaAjax.do",
        datatype: "json",
        method:"Get",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia( data.message);
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.empresa  = modeloTabla
                        self.update()
                    });
                    __Eventos()
                    showModal()
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
}
__actualizar(){
    if ($("#formulario").valid()) {
        __actualizarCorreo()
    }else{
        mensajeErrorTiempo("Error Faltan datos requeridos")
        return true
    }
    
}



/**
*Cerrar caja
**/
function __actualizarCorreo(){
    self.empresa.correoCaja1 = $("#correoCaja1").val();
    self.empresa.correoCaja2 = $("#correoCaja2").val();
    self.update()
        swal({
           title: '',
           text: 'Desea  actualizar los correos electronicos de cierre de caja',
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
                    data : self.empresa,
                    url : 'ConfiguracionCorreoCierreCaja.do',
                    success : function(data) {
                        if (data.status != 200) {
                        	serverMessageJson(data);
                            if (data.message != null && data.message.length > 0) {
                                mensajeAdvertencia(data.message)
                            }
                        } else {
                            mensajeToasExito(data.message)
                             hidemodal()
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
</config-caja>