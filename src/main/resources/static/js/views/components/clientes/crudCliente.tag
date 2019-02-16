<cliente-crud>

<div class="row center " show ={mostrarFormulario} >
        <div class="col-md-12 col-lg-18 col-sx-12 col-sm-18">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {cliente.id > 0 ? $.i18n.prop("titulo.modificar.cliente")   :$.i18n.prop("titulo.agregar.cliente")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{cliente.id}">
                        <input type="hidden" id="descuento" name="descuento" value="{cliente.descuento}"  >
                        <input type="hidden"  id="celular" name="celular" value="{cliente.celular}"  >
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                             <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.nombreCompleto")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control nombreCompleto" placeHolder ="{$.i18n.prop("cliente.nombreCompleto")}" id="nombreCompleto" name="nombreCompleto" value="{cliente.nombreCompleto}"  >

                            </div>
                             <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.tipoCedula")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control tipoCedula " id="tipoCedula" name="tipoCedula" >
                                    <option  each={tipoCedulas.data}  value="{valor}" selected="{cliente.tipoCedula ==valor?true:false}"  >{descripcion}</option>
                                </select>
                            </div>                            
                             <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.cedula")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control cedula" id="cedula" name="cedula" placeHolder ="{$.i18n.prop("cliente.cedula")}" value="{cliente.cedula}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.nombreComercial")} </label>
                                <input type="text" class="form-control nombreComercial" placeHolder ="{$.i18n.prop("cliente.nombreComercial")}" id="nombreComercial" name="nombreComercial" value="{cliente.nombreComercial}"  >
                            </div>
                            
                        </div>
                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label  >{$.i18n.prop("cliente.codigoPais")} <span class="requeridoDato">*</span> </label>
                                <input type="text" class="form-control codigoPais" placeHolder ="{$.i18n.prop("cliente.codigoPais.ejemplo")}" id="codigoPais" name="codigoPais" value="{cliente.codigoPais}"  >
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label  >{$.i18n.prop("cliente.telefono")} </label>
                                <input type="text" class="form-control telefono" placeHolder ="{$.i18n.prop("cliente.telefono")}" id="telefono" name="telefono" value="{cliente.telefono}"  >
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label  >{$.i18n.prop("cliente.correoElectronico")}</label>
                                <input type="text" class="form-control correoElectronico" placeHolder ="{$.i18n.prop("cliente.correoElectronico")}" id="correoElectronico" name="correoElectronico" value="{cliente.correoElectronico}"  >
                            </div>
                         
                        </div>
                                              
                        <div class="row">    
                             <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label  >{$.i18n.prop("cliente.correoElectronico1")}</label>
                                <input type="text" class="form-control correoElectronico1" placeHolder ="{$.i18n.prop("cliente.correoElectronico1")}" id="correoElectronico1" name="correoElectronico1" value="{cliente.correoElectronico1}"  >
                            </div>
                             <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label  >{$.i18n.prop("cliente.correoElectronico2")}</label>
                                <input type="text" class="form-control correoElectronico2" placeHolder ="{$.i18n.prop("cliente.correoElectronico2")}" id="correoElectronico2" name="correoElectronico2" value="{cliente.correoElectronico2}"  >
                            </div>
                             <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label  >{$.i18n.prop("cliente.correoElectronico3")}</label>
                                <input type="text" class="form-control correoElectronico3" placeHolder ="{$.i18n.prop("cliente.correoElectronico3")}" id="correoElectronico3" name="correoElectronico3" value="{cliente.correoElectronico3}"  >
                            </div>
                              
                        </div>

                        <div class="row">    

                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label  >{$.i18n.prop("cliente.identificacionExtranjero")}</label>
                                <input type="text" class="form-control identificacionExtranjero" placeHolder ="{$.i18n.prop("cliente.identificacionExtranjero")}" id="identificacionExtranjero" name="identificacionExtranjero" value="{cliente.identificacionExtranjero}"  >
                            </div>

                            <div class= "col-md-8 col-sx-12 col-sm-8 col-lg-8">
                                <label  >{$.i18n.prop("cliente.otraSena")}</label>
                                <textarea maxlength="250" placeHolder ="{$.i18n.prop("cliente.otraSena")}" class="form-control otraSena" id="otraSena" name="otraSena" value="{cliente.otraSena}" > </textarea> 
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label >{$.i18n.prop("cliente.estado")}</label>
                                <select  class="form-control" id="estado" name="estado" >
                                    <option  each={estados}  value="{codigo}" selected="{cliente.estado ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                             <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label >{$.i18n.prop("cliente.otros")}</label>
                                <input type="text" class="form-control observacionVenta" placeHolder ="{$.i18n.prop("cliente.otros")}" id="observacionVenta" name="observacionVenta" value="{cliente.observacionVenta}"  >                            </div>
                             </div>

                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                        <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                                {$.i18n.prop("btn.volver")}
                            </button>
                        </div>
                        <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" > {$.i18n.prop("btn.modificar")}</button>
                            <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" > {$.i18n.prop("btn.agregar")}</button>
                        </div>
                    </div>    
                               
                  
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
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
            font-size: 16px;
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
     .scroller {
            width: 200px; height: 600px; overflow-y: scroll;
        }
</style>
<script>
    var self = this;
    self.parametros          = opts.parametros;  
    self.tipoCedulas               = {data:[]}  // definir el data del datatable
    self.botonModificar            = false
    self.botonAgregar              = false
    self.cliente                   ={
		id:null,
    	nombreCompleto:"",
		cedula:"",
		provincia:"",
		celular:"",
		telefono:"",
		otraSena:"",
		correoElectronico:"",
        correoElectronico1:"",
        correoElectronico2:"",
        correoElectronico3:"",
     	descuento:0,
		estado:"",
		sucursal:{
            id:null
        }
    }
self.on('mount',function(){
    _incializarCampos()
    __Eventos()
    __ComboEstados()
    __listadoTipoCedulas()
     //modificar cliente
    if(self.parametros.tipoEjecucion == 1){
       __modificarCliente()
    }
     //Agregar
    if(self.parametros.tipoEjecucion == 2){
       __Agregar()
    }
 
    window.addEventListener( "keydown", function(evento){
       $(".errorServerSideJgrid").remove();
    }, false );
})

/**
**/
function  __Agregar(){
	//Inicializar el Formulario
	self.mostrarFormulario  = true 
	//desahabilita boton modificar
	self.botonModificar     = false;
	// habilita el formulario
	self.botonAgregar       = true;
	_incializarCampos()
}
/**
 * Funcion para Modificar del Listar
 */
function __modificarCliente(){
    _incializarCampos()
    self.cliente = self.parametros.data
    self.mostrarFormulario = true
    self.update()
    __consultar()
}
/**
* Limpiar campos
**/
function _incializarCampos(){
    $("#tipoCedula").val($("#tipoCedula option:first").val());
    $('.nombreCompleto').val(null)
    $('.cedula').val(null)
    $('.identificacionExtranjero').val(null)
    $('.telefono').val(null)
    $('.codigoPais').val(506)
    $('.correoElectronico').val(null)
    $('.correoElectronico1').val(null)
    $('.correoElectronico2').val(null)
    $('.correoElectronico3').val(null)
    $('.otraSena').val(null)
    $('.nombreComercial').val(null)
    $(".errorServerSideJgrid").remove();
    $("#formulario").validate(reglasDeValidacion());
    self.cliente = {
        id:null,
        nombreCompleto:"",
        identificacionExtranjero:"",
        barrio:"",
        distrito:"",
        codigoPais:"506",
        cedula:"",
        provincia:"",
        celular:"",
        telefono:"",
        otraSena:"",
        correoElectronico:"",
        correoElectronico1:"",
        correoElectronico2:"",
        correoElectronico3:"",
        descuento:0,
        estado:"",
        sucursal:{
           id:null
        }
    }
    self.update()
    
}

/**
*  Mostrar listado datatable TipoCedulas
**/
function __listadoTipoCedulas(){
    self.tipoCedulas               = {data:[]}  // definir el data del datatable
    self.update()
    self.tipoCedulas.data.push({
        valor:"01",
        descripcion:$.i18n.prop("tipo.cedula.fisica")
    })
   self.tipoCedulas.data.push({
        valor:"02",
        descripcion:$.i18n.prop("tipo.cedula.juridica")
    })
   self.tipoCedulas.data.push({
        valor:"03",
        descripcion:$.i18n.prop("tipo.cedula.dimex")
    })
     self.tipoCedulas.data.push({
        valor:"04",
        descripcion:$.i18n.prop("tipo.cedula.nite")
    })
    self.update()
}
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			cedula : {
				required : true,
                maxlength:12,
                minlength:9,
			},

			nombreCompleto : {
				required : true,
                maxlength:80,
                minlength:1,
                lettersOnly : true
			},
            nombreComercial : {
		        maxlength:80,
                 lettersOnly : true
			},
			otraSenas : {
                maxlength:160,
                minlength:1,
                lettersOnly : true
			},
			correoElectronico : {
                required : true,
                maxlength:80,
                minlength:1,
                email:true
			},
            
			correoElectronico1 : {
                maxlength:80,
                minlength:1,
                email:true
			},
			correoElectronico2 : {
                maxlength:80,
                minlength:1,
                email:true
			},
			correoElectronico3 : {
                maxlength:80,
                minlength:1,
                email:true
			},
            
			correoElectronico3 : {
                maxlength:80,
                minlength:1,
                email:true
			},
            observacionVenta : {
                maxlength:80,
                lettersOnly : true
			},
            codigoPais : {
                required : true,
                minlength:3,

			},
            identificacionExtranjero : {
                maxlength:20,
                minlength:9,
			}                       
		},
		ignore : []

	});
	return validationOptions;
};
/**
*  Crear el combo de estados
**/
function __ComboEstados(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: "Activo",
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
    self.estados.push({
        codigo: "Inactivo",
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
     self.update();

}
/**
*  Activar Eventos
**/
function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#nombreCompleto").attr("maxlength", 80);
    $("#nombreComercial").attr("maxlength", 80);
    $("#cedula").attr("maxlength", 20);
    $("#identificacionExtranjero").attr("maxlength", 20);
    $("#correoElectronico").attr("maxlength", 80);
    $("#correoElectronico1").attr("maxlength", 80);
    $("#correoElectronico2").attr("maxlength", 80);
    $("#correoElectronico3").attr("maxlength", 80);
    $("#otraSenas").attr("maxlength", 80);
    $("#telefono").attr("maxlength", 8);
    $('#codigoPais').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
    $('#cedula').mask('000000000000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
    $('.telefono').mask('00000000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	})


}
/**
*  Regresar al listado
**/
__regresarAlListado(){
    self.botonAgregar       = false;
    self.botonModificar     = false;
    self.mostrarFormulario  = false 
    self.update()
    _incializarCampos()
    mostrarListadoPrincipal()

}

function validarTamanosCedulas(){
    //fisicas
    if($("#tipoCedula").val()=="01"){
        var resul = $("#cedula").val()
        if(resul.length < 9 || resul.length > 9  ){
            swal({
      	        title: '',
      	        text: "Formato Invalido , Cedula Fisica es de 9 digitos",
      	        type: 'error',
      	        showCancelButton: false,
      	        confirmButtonText: 'Aceptar',
            })
            return true

        }
    }

     //juridicas
    if($("#tipoCedula").val()=="02"){
        var resul = $("#cedula").val()
        if(resul.length < 10 || resul.length > 10  ){
            swal({
      	        title: '',
      	        text: "Formato Invalido , Cedula Juridicas es de 10 digitos",
      	        type: 'error',
      	        showCancelButton: false,
      	        confirmButtonText: 'Aceptar',
            })
            return true

        }
    }
     //juridicas
    if($("#tipoCedula").val()=="03"){
        var resul = $("#cedula").val()
        if(resul.length < 12 || resul.length > 12  ){
            swal({
      	        title: '',
      	        text: "Formato Invalido , Cedula Dimex es de 12 digitos",
      	        type: 'error',
      	        showCancelButton: false,
      	        confirmButtonText: 'Aceptar',
            })
            return true

        }
    }

    return false
}

/**
** Modificar la Empresa
**/
__Modificar(){
    if(validarTamanosCedulas()){
        return
    }
    self.error = false;
    self.exito = false;
    self.update();
   __modificarRegistro("#formulario",$.i18n.prop("cliente.mensaje.alert.modificar"),'ModificarClienteAjax.do','ListarClientesAjax.do','#tableListar')
}
/**
*   Agregar 
**/
__agregar(){
    if(validarTamanosCedulas()){
        return
    }
    resultado = __agregarRegistro("#formulario",$.i18n.prop("cliente.mensaje.alert.agregar"),'AgregarClienteAjax.do','ListarClientesAjax.do','#tableListar')
     if ($("#formulario").valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formulario").serialize();
        swal({
           title: '',
           text: $.i18n.prop("cliente.mensaje.alert.agregar"),
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
                    url : 'AgregarClienteAjax.do',
                    success : function(data) {
                        if (data.status != 200) {
                        	serverMessageJson(data);
                            if (data.message != null && data.message.length > 0) {
                            	swal({
      	                           title: '',
      	                           text: data.message,
      	                           type: 'error',
      	                           showCancelButton: false,
      	                           confirmButtonText: 'Aceptar',
      	                                	  
      	                         })
                            }
                        } else {
                        	serverMessageJson(data);
                             swal({
	                           title: '',
	                           text: data.message,
	                           type: 'success',
	                           showCancelButton: false,
	                           confirmButtonText: 'Aceptar',
	                                	  
	                         })
	                         _incializarCampos()
                            self.cliente = data
                            self.update()
                            __Eventos()
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
}


/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(){
    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarClienteAjax.do",
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
                        //Inicializar el Formulario
                        _incializarCampos()
                        self.mostrarFormulario  = true 
                        //desahabilita boton modificar
                        self.botonModificar   = true;
                        // habilita el formulario
                        self.botonAgregar = false;
                        self.cliente  =  modeloTabla
                        self.update()
                        $('#nombreCompleto').val(self.cliente.nombreCompleto)
                        $('#cedula').val(self.cliente.cedula)
                        $('#telefono').val(self.cliente.telefono)
                        $('#correoElectronico').val(self.cliente.correoElectronico)
                        $('#correoElectronico1').val(self.cliente.correoElectronico1)
                        $('#correoElectronico2').val(self.cliente.correoElectronico2)
                        $('#correoElectronico3').val(self.cliente.correoElectronico3)
                        $('#otraSena').val(self.cliente.otraSena)
                        $('.identificacionExtranjero').val(self.cliente.identificacionExtranjero)
                        
                        $('.codigoPais').val(self.cliente.codigoPais)
                       
                        $('.nombreComercial').val(self.cliente.nombreComercial)
                        __ComboEstados()
                        __Eventos()
                        
                         $('.tipoCedula').val(self.cliente.tipoCedula)
                        
                        
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
</cliente-crud>