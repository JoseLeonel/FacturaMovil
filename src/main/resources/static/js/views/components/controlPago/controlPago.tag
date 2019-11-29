<controlPago-crud>
<div show ={mostrarFormulario}>
<div class="tituloBotones">
    <div class="articulo-title"><i class="fa fa-edit"></i>&nbsp {controlPago.id !=null  ? $.i18n.prop("titulo.modificar.controlPago")   :$.i18n.prop("titulo.agregar.controlPago")}     </div>
    <div class="botones">
        <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-right"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
            {$.i18n.prop("btn.volver")}
        </button>
        <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
        <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>

    </div>
</div>            

 <div class="row scrollerT"  >
    <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">
        <div id="divFormulario" >
            <!--Form-->
            <form class="form-horizontal formulario" name= "formulario" id="formulario">
                <input type="hidden" name="id" id="id" value="{articulo.id}">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default" id="cuentas">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" >
                            <div class="panel-heading" style="background: #3c8dbc; color: white;">
                                <h4 class="panel-title"><span class="fa fa-bank col-md-offset-5"></span> Control de Pago</h4>
                            </div>
                        </a>             
                        <div id="collapse1" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                        <div class="form-group">
                                            <label  >{$.i18n.prop("controlPago.empresa")}  <span class="requeridoDato">*</span></label>
                                            <select  class="form-control selectEmpresa  "   name="selectEmpresa" data-live-search="true">
                                                 <option  each={empresas.data}  data-tokens ={nombre} value="{id}" selected="{controlPago.empresa.id ==id?true:false}" >{nombre}</option>
                                            </select>
                                        </div>
                                    </div>   
                                    <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                        <div class="form-group">
                                            <label class="tamanoLetra">{$.i18n.prop("controlPago.tipoPago")}</label>
                                            <select  class="form-control selectTipoPago " id="tipoPago" name="tipoPago"  >
                                                <option  each={tipoPagos}  value="{codigo}" selected="{controlPago.tipoCodigo ==codigo?true:false}"  >{descripcion}</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                        <div class="form-group">
                                            <label class="knob-label" >{$.i18n.prop("controlPago.fechaPago")} <span class="requeridoDato">*</span></label>
                                            <div  class="form-group input-group date datepickerFechaPago" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                                <input type="text" class="form-control fechaPago" id="fechaPago"  name= "fechaPago" readonly>
                                                <div class="input-group-addon">
                                                    <span class="glyphicon glyphicon-th"></span>
                                                </div>
                                            </div>	                             
                                        </div>  
                                                          
                                    </div>
                                    <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                        <div class="form-group">
                                            <label class="knob-label" >{$.i18n.prop("controlPago.fechaLimite")}<span class="requeridoDato">*</span></label>
                                            <div  class="form-group input-group date datepickerFechaPago" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                                <input type="text" class="form-control fechaPago" id="fechaPago"  name= "fechaPago" readonly>
                                                <div class="input-group-addon">
                                                    <span class="glyphicon glyphicon-th"></span>
                                                </div>
                                            </div>	                             
                                        </div>  
                                    </div>
                                  
                                </div> 
                                <div class="row">   
                                  <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("controlPago.mensaje")}  <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control descripcion campoNumerico" id="descripcion" name="descripcion" value="{articulo.descripcion}"  >
                                    </div>
                                </div>    
                                <div class="row">   
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("controlPago.total.dolares")} </label>
                                        <input type="number" step="any" class="form-control costo campoNumerico" id="costo" name="costo" value="{articulo.costo}"  onkeyup ={__ActualizarPreciosCosto}>
                                    </div>
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("controlPago.tipoCambio")}  <span class="requeridoDato">*</span></label>
                                        <input type="number" step="any" class="form-control precioPublico campoNumerico" id="precioPublico" name="precioPublico" onkeyup ={__CalculoGananciaPublico} value="{articulo.precioPublico}"  >
                                    </div>
                                    <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra">{$.i18n.prop("controlPago.estado")}</label>
                                        <select  class="form-control campoNumerico" id="estado" name="estado"  >
                                            <option  each={estados}  value="{codigo}" selected="{articulo.estado ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>     


                                </div>    
                            </div>

                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>                                       

</div>


<!-- Fin Formulario -->   

<style type ="text/css">
    .scrollerT {
        width: 100% !important;
        height: 650px;
        overflow-y: scroll;
    }
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
        .campoNumerico {
        display: block;
        width: 100%;
        height: 45px;
        padding: 8px 18px;
        line-height: 1.42857143;
        color:black;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        border-radius: 2px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
        -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
        background-color: #fcfcfc;
        border: 1px solid #ccc;
        font-size: 28px;
        margin: 2px 0;
        padding: 1px 2px;
        overflow: visible;
        font-weight: bold;
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
    .tamanoLetra {
        font-size: 16px;
    }
 

</style>
<script>
    var self = this;
    self.controlPago = {
        id:null,

    }
    self.parametros   = opts.parametros;  
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del Listado de la Tabla 
    self.estados                   = []
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarTituloArticulo     = true
    self.precioPantalla = true
    self.impuestosIVAIPantalla = false;
    self.otrosPantalla = false;
   
    // variables para modulo de inventario 
    self.mostrarFormularioEntrada    = false
    self.mostrarFormularioSalida     = false
    self.mostrarBotonAgregarEntrada  = false
    self.mostrarBotonAgregarSalida   = false 
    self.empresas               = {data:[]}
    self.tipoPagos =[]
    self.estados =[]

self.on('mount',function(){
    __Eventos()
    __Consulta()
    listaEmpresasActivas()
    __tipoPagos() 
    __ComboEstados()

    $('.collapse').collapse("show")
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
})
/**
*  Crear el combo comanda
**/
function __ComboEstados(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: $.i18n.prop("combo.estado.Activo"),
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
    self.estados.push({
        codigo: $.i18n.prop("combo.estado.Inactivo"),
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
     self.update();
}
function __tipoPagos(){
    self.tipoPagos =[]
    self.update()
    self.tipoPagos.push({
        codigo:1,
        descripcion:'Efectivo'
     });
    self.tipoPagos.push({
        codigo:2,
        descripcion:'Transferencia'
     });
     self.update();
}
/**
*  Obtiene la lista de las empresas activas
**/
function listaEmpresasActivas(){
	$.ajax({
		 url: "ListarEmpresasAjax.do",
		 datatype: "json",
		 global: false,
		 method:"GET",
		 success: function (result) {
				if(result.aaData.length > 0){
                   self.empresas.data = result.aaData
                   self.update() 
					$('.selectEmpresa').selectpicker();
				} 
		 },
		 error: function (xhr, status) {
			  mensajeErrorServidor(xhr, status);
			  console.log(xhr);
		 }
	})
}

/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
 function __Consulta(){
    //Agregar
    if(self.parametros.tipoEjecucion ==1){
        LimpiarArticulo()
        self.mostrarTituloArticulo = true  
        self.mostrarFormulario = true
        self.botonModificar = false
        self.mostrarFormularioEntrada = false
        self.botonAgregar = true
        self.precioPantalla = true
        self.impuestosIVAIPantalla = false
        self.otrosPantalla = false                        
        self.update()
        $("#formulario").validate(reglasDeValidacion());     
    }   
    //modificar
    if(self.parametros.tipoEjecucion ==2){
        self.articulo = self.parametros.articulo
        self.mostrarTituloArticulo = true  
        self.mostrarFormulario = true
        self.botonModificar = true
        self.mostrarFormulario = true 
        self.botonModificar   = true;
        self.mostrarFormularioEntrada = false
        self.botonAgregar  = false;            
        self.update()
       // __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto1,2)
        __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto,1)
        $("#formulario").validate(reglasDeValidacion());     
       
    }  
}

/**
* Limpiar Articulo
**/
function LimpiarArticulo(){
   self.articulo = {
		id:null,
        codigo:"",
        tipoCodigo:"",
		descripcion:"",
        serie:"",
		unidadMedida:"",
		costo:null,
		impuesto:null,
        impuesto1:null,
        codigoTarifa:null,
        codigoTarifa1:null,
        minimo:0,
        maximo:0,
		precioPublico:null,
		gananciaPrecioPublico:null,
		precioMayorista:null,
		gananciaPrecioMayorista:null,
		precioEspecial:null,
		gananciaPrecioEspecial:null,
        tipoImpuesto:null,
		estado:"",
		marca:{
            id:null
        },
		categoria:{
            id:null
        },
		empresa:{
            id:null
        }
    }    
    self.update() 
   $('.precioEspecial').val(null)
   $('.gananciaPrecioEspecial').val(null)
   $('.prioridad').val(null)
   $('.pesoTransporte').val(null)
   $(".errorServerSideJgrid").remove();
   $("#formulario").validate(reglasDeValidacion());
}
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			descripcion : {
				required : true,
                maxlength:80,
                minlength:1,
                lettersOnly : true
			},
            codigo : {
				required : true,
                maxlength:20,
                minlength:1,
                lettersOnly : true
			},                                                
            marca : {
				required : true,
			},                                                
            categoria : {
				required : true,
			},                                                
            unidadMedida : {
				required : true,
			},                                                
            precioPublico : {
				required : true,
                numeroMayorCero:true,
                number:true,
			} ,                                                
                                                             
            precioMayorista : {
                number:true,
			},                                                
            precioEspecial : {
                number:true,
			}                                          
                        
		},
		ignore : []

	});
	return validationOptions;
};
/**
*  Actimpuestor validaciones del formulario
**/
function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#descripcion").attr("maxlength", 80);
    $("#codigo").attr("maxlength", 20);
    $('#impuesto').mask('00', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
}
/**
*  Crear el combo comanda
**/
function __ComboEstados(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: $.i18n.prop("combo.estado.Activo"),
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
    self.estados.push({
        codigo: $.i18n.prop("combo.estado.Inactivo"),
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
     self.update();
}
/**
*  Regresar al listado
**/
__regresarAlListado(){
    self.mostrarTituloArticulo = true
    self.mostrarListado     = true;
    self.botonAgregar       = false;
    self.botonModificar     = false;
    self.mostrarFormulario  = false 
    self.mostrarFormularioEntrada    = false
    self.mostrarFormularioSalida     = false
    self.update()
    //articulo.js se encuentra rutina
    __mostrarListado()
   // LimpiarArticulo()
}
/**
*   Agregar 
**/
__agregar(){
    if(validarPrecios()){
         return
     }

        if ($("#formulario").valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formulario").serialize();
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : 'AgregarArticuloAjax.do',
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
	                        $("#formulario").validate(reglasDeValidacion());
                            $(".errorServerSideJgrid").remove();
                            $('#codigo').val(null)
                            $('#descripcion').val(null)
                            $('#serie').val(null)
                            $('#costo').val(null)
                            $('#impuesto').val(null)
                            $('#impuesto1').val(null)
                            $('#precioPublico').val(null)
                            $('#gananciaPrecioPublico').val(null)
                            $('#precioMayorista').val(null)
                            $('#gananciaPrecioMayorista').val(null)
                            $('#precioEspecial').val(null)
                            $('#gananciaPrecioEspecial').val(null)
                              __Eventos()
                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
                    }
                });
    }else{
          sweetAlert("", "Falta ingresar datos del articulo que son obligatorios, verificar lo indicado en ROJO", "error");
      }
}
/**
** Modificar la Empresa
**/
__Modificar(){
     if(validarPrecios()){
         return
     }

    var AplicoImpuesto1 = false
    var AplicoImpuesto2 = false
    if ($("#formulario").valid()) {
        self.error = false;
        self.exito = false;
        self.update();
        __modificarRegistro("#formulario",$.i18n.prop("articulo.mensaje.alert.modificar"),'ModificarArticuloAjax.do','ListarArticuloAjax.do','#tableListar')

    }   else{
          sweetAlert("", "Falta ingresar datos del articulo que son obligatorios, verificar lo indicado en ROJO", "error");
      }
    
}

/**
* Inicializar el articulo
**/
function _inicializarArticulo(){
    self.articulo = {
		id:null,
        codigo:"",
        tipoCodigo :"",
		descripcion:"",
        serie:"",
		unidadMedida:"",
		costo:"",
		impuesto:0,
        minimo:0,
        maximo:0,
		precioPublico:null,
		gananciaPrecioPublico:null,
		precioMayorista:null,
		gananciaPrecioMayorista:null,
		precioEspecial:null,
		gananciaPrecioEspecial:null,
		estado:"",
		marca:{
            id:null
        },
		categoria:{
            id:null
        },
		empresa:{
            id:null
        },
        prioridad:1
    }    
    self.update()
}
</script>
</controlPago-crud>