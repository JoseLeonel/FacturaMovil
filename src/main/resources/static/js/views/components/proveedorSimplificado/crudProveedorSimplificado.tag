<proveedorsimplificado-crud>
<div class="tituloBotones" show={mostrarFormulario}>
    <div class="articulo-title"><i class="fa fa-edit"></i>&nbsp {proveedorSimplificado.id > 0 ? $.i18n.prop("titulo.modificar.proveedorSimplificado")   :$.i18n.prop("titulo.agregar.proveedorSimplificado")}  </div>
    <div class="botones">
        <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-right"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
            {$.i18n.prop("btn.volver")}
        </button>
        <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
        <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>
    </div>
</div>    

<div  show ={mostrarFormulario} >
    <form class="form-horizontal formulario" name= "formulario" id="formulario">
        <div class="row">
            <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default" id="cuentas">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" >
                        <div class="panel-heading" style="background: #3c8dbc; color: white;">
                            <h4 class="panel-title"><span class="fa fa-user col-md-offset-5"></span> Datos del Proveedor Simplificado</h4>
                        </div>
                        </a>
                        <div id="collapse1" class="panel-collapse collapse">
                            <div class="panel-body">
                            
                                <input type="hidden" name="identificacionExtranjero" id="identificacionExtranjero" value="{proveedorSimplificado.identificacionExtranjero}">
                                <input type="hidden" name="id" id="id" value="{proveedorSimplificado.id}">
                                <input type="hidden" id="descuento" name="descuento" value="{proveedorSimplificado.descuento}"  >
                                <input type="hidden"  id="celular" name="celular" value="{proveedorSimplificado.celular}"  >
                                <div class="row">
                                    <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                        <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("proveedorSimplificado.cedula")} <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control cedula" id="cedula" name="cedula" placeHolder ="{$.i18n.prop("proveedorSimplificado.cedula")}" value="{proveedorSimplificado.cedula}" onkeypress = {__ConsultarHacienda} onBlur ={__ConsultarHaciendaBlur} >
                                    </div>
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("proveedorSimplificado.nombreCompleto")}  <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control nombreCompleto" placeHolder ="{$.i18n.prop("proveedorSimplificado.nombreCompleto")}" id="nombreCompleto" name="nombreCompleto" value="{proveedorSimplificado.nombreCompleto}"  readonly>
                                    </div>
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("proveedorSimplificado.tipoCedula")}  <span class="requeridoDato">*</span></label>
                                        <select  class="form-control tipoCedula " id="tipoCedula" name="tipoCedula" >
                                            <option  each={tipoCedulas.data}  value="{valor}" selected="{proveedorSimplificado.tipoCedula ==valor?true:false}"  >{descripcion}</option>
                                        </select>
                                    </div>                            
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label>Actividad Comercial<span class="requeridoDato">*</span></label>
                                        <select  class="form-control codigoActividad " id="codigoActividad" name="codigoActividad" >
                                            <option  each={clienteHacienda.actividades}  selected="{proveedorSimplificado.codigoActividad ==codigo?true:false}" value="{codigo}"  >{descripcion}</option>
                                        </select>
                                    </div>    
                                </div>
                                <div class="row">
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >Regimen <span class="requeridoDato">*</span> </label>
                                        <input type="text" class="form-control " value="{clienteHacienda.regimen.descripcion}" readonly >
                                    </div>

                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("proveedorSimplificado.codigoPais")} <span class="requeridoDato">*</span> </label>
                                        <input type="text" class="form-control codigoPais" placeHolder ="{$.i18n.prop("proveedorSimplificado.codigoPais.ejemplo")}" id="codigoPais" name="codigoPais" value="{proveedorSimplificado.codigoPais}"  >
                                    </div>
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("proveedorSimplificado.telefono")} </label>
                                        <input type="text" class="form-control telefono" placeHolder ="{$.i18n.prop("proveedorSimplificado.telefono")}" id="telefono" name="telefono" value="{proveedorSimplificado.telefono}"  >
                                    </div>
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("proveedorSimplificado.correoElectronico")}</label>
                                        <input type="text" class="form-control correoElectronico" placeHolder ="{$.i18n.prop("proveedorSimplificado.correoElectronico")}" id="correoElectronico" name="correoElectronico" value="{proveedorSimplificado.correoElectronico}"  >
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label >{$.i18n.prop("proveedorSimplificado.estado")}</label>
                                        <select  class="form-control" id="estado" name="estado" >
                                            <option  each={estados}  value="{codigo}" selected="{proveedorSimplificado.estado ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>
                                </div>     
                            </div>
                        </div>
                    </div>
                </div>
            </div>             
        </div> 
    </form>            
</div>


<style type ="text/css">

    .botones{
        margin-bottom: 0.5%;
    }
    .tituloBotones{
        display: flex;
    }
    .articulo-title{
        font-size: 20px;
        font-weight: 600;
        flex: 1;
    }
    
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
        border-width: 5px;proveedorSimplificado
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
    self.comboTipoDocumentoExonerados   = []
    self.proveedorSimplificado ={
		id:null,
    	nombreCompleto:"",
    	cedula:"",
		celular:"",
		telefono:"",
		correoElectronico:"",
        correoElectronico1:"",
    	estado:"",
		sucursal:{
            id:null
        }
    }
    self.clienteHacienda= {
        nombre:"",
        tipoIdentificacion:"",
        regimen:{
            codigo:"",
            descripcion:""
        },
        actividades:[]
    }
self.on('mount',function(){
    _incializarCampos()
    __Eventos()
    __ComboEstados()
     $('.collapse').collapse("show")
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
*Consulta hacienda
**/
__ConsultarHaciendaBlur(){
    var cedula = $('#cedula').val()
    getClienteHacienda(cedula)

}

__ConsultarHacienda(e){
     if (e.keyCode != 13) {
        return;
    } 
    var cedula = $('#cedula').val()
    getClienteHacienda(cedula)
}
function getClienteHacienda(cedula){
    $.ajax({
    "url": "https://api.hacienda.go.cr/fe/ae?identificacion="+ cedula,
    "method": "GET",
    statusCode: {
        404: function() {
            alert( "No se puede conectar con Hacienda" )
        },
        404: function() {
            alert( "Cedula invalidad" )
        }
    }
    }).done(function (response) {
        self.clienteHacienda = response
        self.update()
        __listadoTipoCedulas()
         $('#nombreCompleto').val(self.clienteHacienda.nombre)
    });
}


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
    self.proveedorSimplificado = self.parametros.data
    self.mostrarFormulario = true
    self.update()
    __consultar()
}
/**
* Limpiar campos
**/
function _incializarCampos(){
    $('#libreImpuesto').prop("selectedIndex", 0);
    $("#tipoCedula").val($("#tipoCedula option:first").val());
    $('.nombreCompleto').val(null)
    $('.cedula').val(null)
    $('.correoElectronico').val(null)
    $('.nombreComercial').val(null)
    $(".errorServerSideJgrid").remove();
    $("#formulario").validate(reglasDeValidacion());
    self.proveedorSimplificado = {
        id:null,
        nombreCompleto:"",
        identificacionExtranjero:"",
        cedula:"",
        celular:"",
        telefono:"",
        correoElectronico:"",
        estado:"",
    }
    self.update()
    
}

/**
*  Mostrar listado datatable TipoCedulas
**/
function __listadoTipoCedulas(){
    self.tipoCedulas               = {data:[]}  // definir el data del datatable
    self.update()
    if(self.clienteHacienda.tipoIdentificacion == "01") {
        self.tipoCedulas.data.push({
            valor:"01",
            descripcion:$.i18n.prop("tipo.cedula.fisica")
        })
    }
    if(self.clienteHacienda.tipoIdentificacion == "02") {
        self.tipoCedulas.data.push({
            valor:"02",
            descripcion:$.i18n.prop("tipo.cedula.juridica")
        })
    }
    if(self.clienteHacienda.tipoIdentificacion == "03" ){
        self.tipoCedulas.data.push({
            valor:"03",
            descripcion:$.i18n.prop("tipo.cedula.dimex")
        })
    }    
    if(self.clienteHacienda.tipoIdentificacion == "04" ){
     self.tipoCedulas.data.push({
        valor:"04",
        descripcion:$.i18n.prop("tipo.cedula.nite")
    })
    }
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
			correoElectronico : {
                required : true,
                maxlength:60,
                minlength:1,
                email:true
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
*
*  Crear el combo de estados
*
**/
function __ComboEstados(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: "1",
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
    self.estados.push({
        codigo: "0",
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
    $("#correoElectronico").attr("maxlength", 80);    $('#cedula').mask('000000000000', {
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
    var valor = $("#estado").val()
    if(validarRegimenModificar(1) && valor == 1){
        return false
    }
    self.error = false;
    self.exito = false;
    self.update();
   __modificarRegistro("#formulario",$.i18n.prop("proveedorSimplificado.mensaje.alert.modificar"),'ModificarProveedorSimplificadoAjax.do','ListarProveedorSimplificadoActivosAjax.do','#tableListar')
}

function validarRegimenModificar(ejecucion){
   if(self.clienteHacienda.regimen.codigo == "1" && ejecucion == 1){
       sweetAlert("", $.i18n.prop("error.proveedorSimplificado.regimen.tradicional.modificar"), "error");
       return true
   }   
   if(self.clienteHacienda.regimen.codigo == "1" && ejecucion == 2){
       sweetAlert("", $.i18n.prop("error.proveedorSimplificado.regimen.tradicional.agregar"), "error");
       return true
   }   
   return false

}
/**
*   Agregar 
**/
__agregar(){
    if(validarTamanosCedulas(2)){
        return
    }
     if(validarRegimenModificar(2) ){
        return false
    }
     if ($("#formulario").valid()) {
        aplicarCreacion()
    }else{
        swal({
  	        title: '',
   	        text: "Error Faltan datos requeridos",
   	        type: 'error',
   	        showCancelButton: false,
   	        confirmButtonText: 'Aceptar',
        })
        return true
    }
}

function aplicarCreacion(){
        // Permite obtener todos los valores de los elementos del form del jsp
        //resultado = __agregarRegistro("#formulario",$.i18n.prop("proveedorSimplificado.mensaje.alert.agregar"),'AgregarClienteAjax.do','ListarClientesAjax.do','#tableListar')
        var formulario = $("#formulario").serialize();
        swal({
           title: '',
           text: $.i18n.prop("proveedorSimplificado.mensaje.alert.agregar"),
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
                    url : 'AgregarProveedorSimplificadoAjax.do',
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
                            self.proveedorSimplificado = data
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
        })        

}

/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(){
    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarProveedorSimplificadoAjax.do",
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
                        _incializarCampos()
                        self.mostrarFormulario  = true 
                        self.botonModificar   = true;
                        self.botonAgregar = false;
                        self.proveedorSimplificado  =  modeloTabla
                        self.update()
                        getClienteHacienda(self.proveedorSimplificado.cedula)
                        $('#nombreCompleto').val(self.proveedorSimplificado.nombreCompleto)
                        $('#cedula').val(self.proveedorSimplificado.cedula)
                        $('#telefono').val(self.proveedorSimplificado.telefono)
                        $('#correoElectronico').val(self.proveedorSimplificado.correoElectronico)
                        $('.nombreComercial').val(self.proveedorSimplificado.nombreComercial)
                        __ComboEstados()
                        __Eventos()
                         $('.tipoCedula').val(self.proveedorSimplificado.tipoCedula)
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
</proveedorsimplificado-crud>
