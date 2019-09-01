<empresa-config>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-cog"></i>&nbsp {$.i18n.prop("empresa.titulo")}  </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
    


<div class="row center "  >
    <div class="col-md-12 col-sx-12 col-lg-12 col-sm-12"></div>
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp  Configuracion</h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{empresa.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label  >{$.i18n.prop("configuracion.correo.ventas")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control cedula" placeHolder ="{$.i18n.prop("empresa.cedula")}" id="cedula" name="cedula" value="{empresa.cedula}"  >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label  >{$.i18n.prop("configuracion.correo.compras")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control nombre" placeHolder ="{$.i18n.prop("empresa.nombre")}" id="nombre" name="nombre" value="{empresa.nombre}"  >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label  >{$.i18n.prop("configuracion.correo.cuenta.cobrar.pagar")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control cedula" placeHolder ="{$.i18n.prop("empresa.cedula")}" id="cedula" name="cedula" value="{empresa.cedula}"  >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label  >{$.i18n.prop("configuracion.correo.compras")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control nombre" placeHolder ="{$.i18n.prop("empresa.nombre")}" id="nombre" name="nombre" value="{empresa.nombre}"  >
                            </div>
                        </div>                        
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label  >{$.i18n.prop("configuracion.cuenta.bancaria.uno")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control cedula" placeHolder ="{$.i18n.prop("empresa.cedula")}" id="cedula" name="cedula" value="{empresa.cedula}"  >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label  >{$.i18n.prop("configuracion.cuenta.bancaria.dos")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control nombre" placeHolder ="{$.i18n.prop("empresa.nombre")}" id="nombre" name="nombre" value="{empresa.nombre}"  >
                            </div>
                        </div>                             
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label  >{$.i18n.prop("configuracion.cuenta.bancaria.tres")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control cedula" placeHolder ="{$.i18n.prop("empresa.cedula")}" id="cedula" name="cedula" value="{empresa.cedula}"  >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label  >{$.i18n.prop("configuracion.cuenta.bancaria.cuatro")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control nombre" placeHolder ="{$.i18n.prop("empresa.nombre")}" id="nombre" name="nombre" value="{empresa.nombre}"  >
                            </div>
                        </div>                             
                        
                        
                         
                        
                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                        <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                                {$.i18n.prop("btn.volver")}
                            </button>
                        </div>
                        <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button  onclick={__Modificar}   class="btn-green btn-edit pull-right" >  {$.i18n.prop("btn.modificar")}</button>
                        </div>
                    </div>
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>
</div>
 




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
    border-width: 5px;
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
            text-align: left;
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
    self.botonModificar            = false
      
    self.empresa  = {
        id:null,
        nombre:"",
        nombreComercial:"",
        numeroConsecutivo:0000000000
    }


self.on('mount',function(){
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
			nombre : {
				required : true,
                maxlength:79,
                minlength:1,
			},
			nombreComercial : {
				
                maxlength:80,
                minlength:1,
			},
			tipoCedula : {
				required : true,
			},
            
			cedula : {
				required : true,
                maxlength:12,
                minlength:1,

			},
			otraSenas : {
                maxlength:250,
                minlength:1,
			},  
           correoElectronico : {
                maxlength:250,
                minlength:1,
                email:true,
			},   
            representante : {
                maxlength:80,
                minlength:1,
			},                                    
            web : {
                maxlength:80,
                minlength:1,
			},
            numeroConsecutivo:{
                minlength:10,
                required : true,
                numeroMayorCero:true,
            },
            notacConsecutivo:{
                minlength:10,
                required : true,
                numeroMayorCero:true,
            },
            notadConsecutivo:{
                minlength:10,
                required : true,
                numeroMayorCero:true,
            },
            tiqueteConsecutivo:{
                minlength:10,
                required : true,
                numeroMayorCero:true,
            },
            cazaMatriz:{
                minlength:3,
                maxlength:3,
                required : true,
                numeroMayorCero:true,
            },
            codigoSeguridad:{
                required : true,
                minlength:8,
                numeroMayorCero:true,

            },
            usuarioEnvioComprobante:{
                required : true,
                maxlength:250,
            },
            passwordEnvioComprobante:{
                required : true,
                maxlength:250,
            }
		},
		ignore : []

	});
	return validationOptions;
};


function _limpiar(){
   
   $("#tipoCedula").val($("#tipoCedula option:first").val());
   $("#provincia").val($("#provincia option:first").val());
   $("#canton").val($("#canton option:first").val());
   $("#distrito").val($("#distrito option:first").val());
   $("#barrio").val($("#barrio option:first").val());

   $('#usuarioEnvioComprobante').val(null)
   $('#passwordEnvioComprobante').val(null)
   $('#cedula').val(null)
   $('#logo').val(null)
   $("#nombre").val(null)
   $("#nombreComercial").val(null)
   $("#representante").val(null)
   $("#codigoPais").val(506)
   $("#telefono").val(null)
   $("#cazaMatriz").val(null)
   $("#otraSenas").val(null)
   $("#web").val(null)
   $("#numeroConsecutivo").val(null)
   $("#notacConsecutivo").val(null)
   $("#notadConsecutivo").val(null)
   $("#tiqueteConsecutivo").val(null)
    $("#numeroConsecutivo").val(null)
   $("#aceptadoConsecutivo").val(null)
   $("#aceptadoParcialConsecutivo").val(null)
   $("#rechazadoConsecutivo").val(null)
   
   $("#nombreLlaveCriptografica").val(null)
   $("#claveLlaveCriptografica").val(null)
   $("#codigoSeguridad").val(null)
   $(".errorServerSideJgrid").remove();
   $("#formulario").validate(reglasDeValidacion())
    self.empresa  = {
        id:null,
        nombre:"",
        nombreComercial:"",
        numeroConsecutivo:0000000000
    }
    self.update()
}
/**
*  Activar Eventos
**/
function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    //$("#nombre").attr("maxlength", 78);
    $("#telefono").attr("maxlength", 9);
    $("#cedula").attr("maxlength", 12);
    $("#nombreComercial").attr("maxlength", 80);
    $("#usuarioEnvioComprobante").attr("maxlength",250);
    $("#passwordEnvioComprobante").attr("maxlength",250);
    $("#otraSenas").attr("maxlength", 250);
    $("#logo").attr("maxlength", 250);
    $("#codigoPais").attr("maxlength", 3);
    $("#cazaMatriz").attr("maxlength", 3);
    $("#correoElectronico").attr("maxlength", 250);
    $("#representante").attr("maxlength", 80);
    $("#web").attr("maxlength", 80);
    $("#codigoSeguridad").attr("maxlength", 8);
    $('#cazaMatriz').mask('000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    }); 
   $('#codigoSeguridad').mask('00000000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    });
    $('#claveLlaveCriptografica').mask('0000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    }); 
    $('#codigoPais').mask('000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    }); 
    $('#numeroConsecutivo').mask('0000000000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    }); 
    $('#notacConsecutivo').mask('0000000000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    });         
    $('#notadConsecutivo').mask('0000000000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    });  

     $('#tiqueteConsecutivo').mask('0000000000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    });         

    if($("#tipoCedula").val() == "01" ){
        $('#cedula').mask('000000000000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	    })

    
    }
    $('#telefono').mask('00000000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	})    
    // Patron de entrada de datos
    $("#tipoCedula").click(function() {
        // tipos de formato de cedula
        
        $("#tipoCedula").val() == "01" ? $('#cedula').mask('000000000000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
        }) : $('#cedula').mask('000000000000', {
            'translation' : {
                0 : {
                    pattern : /[A-Za-z0-9]/
                }
            }
        });
    });
}

/**
*  Regresar al listado
**/
__regresarAlListado(){
    swal({
        title: "", 
        text: $.i18n.prop("mensaje.alert.regresar.listado"), 
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: '#00539B',
        cancelButtonColor: '#d33',
        confirmButtonText:$.i18n.prop("confirmacion.si"),
        cancelButtonText: $.i18n.prop("confirmacion.no"),
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger'
        }).then(function (isConfirm) {
            if(isConfirm){
                self.mostrarListado     = true;
                self.botonAgregar       = false;
                self.botonModificar     = false;
                self.mostrarFormulario  = false 
                self.update()
                _limpiar()
                __listado();

            }
    });    
}

/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(){
    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarEmpresaAjax.do",
        datatype: "json",
        data: formulario,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                    //desahabilita  listado 
                        _limpiar()
                        self.mostrarListado   = false;
                        self.mostrarFormulario  = true 
                        //desahabilita boton modificar
                        self.botonModificar   = true;
                        // habilita el formulario
                        self.botonAgregar     = false;                        
                        self.empresa  =  modeloTabla
                        self.empresa.numeroConsecutivo = zfill(self.empresa.numeroConsecutivo,10)
                        self.empresa.notacConsecutivo = zfill(self.empresa.notacConsecutivo,10)
                        self.empresa.notadConsecutivo = zfill(self.empresa.notadConsecutivo,10)
                        self.empresa.tiqueteConsecutivo = zfill(self.empresa.tiqueteConsecutivo,10)

                        self.update()
                       $("#usuarioEnvioComprobante").val(self.empresa.usuarioEnvioComprobante);
                       $("#passwordEnvioComprobante").val(self.empresa.passwordEnvioComprobante);
                        $("#tipoCedula").val(self.empresa.tipoCedula);
                        $("#provincia").val(self.empresa.provincia);
                        $("#canton").val(self.empresa.canton);
                        $("#distrito").val(self.empresa.distrito);
                        $("#codigoSeguridad").val(zfill(self.empresa.codigoSeguridad,8));
                        $("#barrio").val(self.empresa.barrio);
                        $('#cedula').val(self.empresa.cedula)
                        $('#logo').val(self.empresa.logo)
                        $("#nombre").val(self.empresa.nombre)
                        $("#nombreComercial").val(self.empresa.nombreComercial)
                        $("#representante").val(self.empresa.representante)
                        $("#codigoPais").val(self.empresa.codigoPais)
                        $("#telefono").val(self.empresa.telefono)
                        $("#cazaMatriz").val(self.empresa.cazaMatriz)
                        $("#otraSenas").val(self.empresa.otraSenas)
                        $("#web").val(self.empresa.web)
                        $("#nombreLlaveCriptografica").val(self.empresa.nombreLlaveCriptografica)
                        $("#claveLlaveCriptografica").val(self.empresa.claveLlaveCriptografica)
                        $("#numeroConsecutivo").val(self.empresa.numeroConsecutivo)
                        $("#notadConsecutivo").val(self.empresa.notadConsecutivo)
                        $("#notacConsecutivo").val(self.empresa.notacConsecutivo)
                        $("#tiqueteConsecutivo").val(self.empresa.tiqueteConsecutivo)
                        
                        __ComboEstados()
                    });
                }
            }
            
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
}





</script>
</empresa-config>