<cambio-Clave>
<!-- Formulario-->
    <div class=" col-sm-3 col-md-3 col-lg-3"></div>
    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
       <div class="box box-solid box-primary">
            <div class="box-header with-border">
                <h1  class="box-title"><i class="fa fa-user"></i>&nbsp{$.i18n.prop("titulo.cambioClave.usuario")} </h1>
            </div>
            <div class="box-body">
                <form id = "formulario" name ="formulario " class="advanced-search-form">
                    <div class="row">
                        <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                            <label class="knob-label">{$.i18n.prop("usuario.contrasena")} &nbsp<span class="requeridoDato">*</span></label>
                            <input type="text" class="form-control " placeholder = "{$.i18n.prop("usuario.contrasena")}" title="{$.i18n.prop("usuario.contrasena")}" name="password" id= "password"   maxlength="55">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                            <label class="knob-label">{$.i18n.prop("usuario.contrasena.confirmar")} &nbsp<span class="requeridoDato">*</span></label>
                            <input type="text" class="form-control " placeholder = "{$.i18n.prop("usuario.contrasena.confirmar")}" title="{$.i18n.prop("usuario.contrasena.confirmar")}" name="passwordConfirm" id= "passwordConfirm"    maxlength="55">
                        </div>
                    </div>

                </form>    
            </div>
            <div class="box-footer">
                <button onclick ={__salvar}   type="button"  class="btn-add btn-green pull-right" >
                    {$.i18n.prop("btn.modificar")}
                </button>
            </div>
       </div>   
    </div>
    <div class="col-sm-3 col-md-3 col-lg-3"></div> 
<!-- Fin Formulario -->

<style type ="text/css">
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
        .fondoFacturacion {
            background: rgb(247, 244, 244);
            color: #f9fafc;
            border-style: solid;
            border-width: 5px;cliente
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
    self.on('mount',function(){
         $("#formulario").validate(reglasDeValidacion());

        window.addEventListener( "keydown", function(evento){
            $(".errorServerSideJgrid").remove();
        }, false );
    })


/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			password : {
				required : true,
                maxlength:255,
                minlength:7,
			},
			passwordConfirm : {
				required : true,
                maxlength:255,
                minlength:7,
                equalTo: "#password"
			}
		},
		ignore : []

	});
	return validationOptions;
};
/**
*  Salvar la contrasena
**/
__salvar(){
      if ($('#formulario').valid()) {
            // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $('#formulario').serialize();
        swal({
                title: "", 
                text: $.i18n.prop("usuario.mensaje.alert.cambio.clave"), 
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: '#00539B',
                cancelButtonColor: '#d33',
                confirmButtonText:$.i18n.prop("confirmacion.si"),
                cancelButtonText: $.i18n.prop("confirmacion.no"),
                confirmButtonClass: 'btn btn-success',
                cancelButtonClass: 'btn btn-danger',
                }).then(function (isConfirm) {
                    //Ajax
                    if(isConfirm){
                        $.ajax({
                            type : "POST",
                            dataType : "json",
                            data : formulario,
                            url : "CambioAjax.do",
                            success : function(data) {
                                console.log(data)
                                if (data.status != 200) {
                                    serverMessageJson(data);
                                    if (data.message != null && data.message.length > 0) {
                                       // swal('',data.message,'error');
                                        swal({
             	                           title: '',
             	                           text: data.message,
             	                           type: 'error',
             	                           showCancelButton: false,
             	                           confirmButtonText: 'Aceptar',
             	                                	  
             	                         })
                                    }
                                } else {
                                    // Mensaje de exito
                                    serverMessageJson(data)
                                
	                                swal({
	                                	  title: '',
	                                	  text: data.message,
	                                	  type: 'success',
	                                	  showCancelButton: false,
	                                	  confirmButtonText: 'Aceptar',
	                                	  
	                                	})
                                }
                        },
                            error : function(xhr, status) {
                            console.log(status);
                            mensajeErrorServidor(xhr, status);
                        }
                    });
                }
            });    
        
    }

}




</script>
</cambio-Clave>