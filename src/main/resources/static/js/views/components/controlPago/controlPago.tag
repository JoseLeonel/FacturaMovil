<controlPago-crud>
<div show ={mostrarFormulario}>
<div class="tituloBotones">
    <div class="articulo-title"><i class="fa fa-edit"></i>&nbsp {controlPago.id !=null  ? $.i18n.prop("titulo.modificar.controlPago")   :$.i18n.prop("titulo.agregar.controlPago")}  </div>
    <div class="botones">
        <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-right"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
            {$.i18n.prop("btn.volver")}
        </button>
        <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
        <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>

    </div>
</div>            

 <div class="row scrollerT"  >
    <div class="col-sx-12 col-sm-12 col-md-8 col-lg-8">
        <div id="divFormulario" >
            <!--Form-->
            <form class="form-horizontal formulario" name= "formulario" id="formulario">
                <input type="hidden" name="id" id="id" value="{controlPago.id}">
                <input type="hidden" id="tipoCambio" name="tipoCambio"   value="{tipoCambio.total}">

                <div class="panel-group" id="accordion">
                    <div class="panel panel-default" id="cuentas">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" >
                            <div class="panel-heading tituloContainer" style="background: #3c8dbc; color: white;">
                                 <div class="elemento1"><h4 class="panel-title">Control de Pago  </h4></div>
                                 <div class="elemento1"><h4 class='panel-title pull-right'>Tipo Cambio Dolar:{tipoCambio.total}</h4></div>
                                 
                            </div>
                        </a>             
                        <div id="collapse1" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12 has-success">
                                        <label  class="knob-label" >{$.i18n.prop("controlPago.empresa")}  <span class="requeridoDato">*</span></label>
                                        <select  class="form-control empresa"   name="empresa" id='empresa' data-live-search="true">
                                            <option  each={empresas.data}  data-tokens ={nombreComercial.length >0?nombreComercial:nombre} value="{id}" selected="{controlPago.empresa.id ==id?true:false}" >{nombreComercial.length >0?nombreComercial:nombre}</option>
                                        </select>
                                    </div>   
                                </div>
                                <div class="row">    
                                    <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6 has-success">
                                        <label class="knob-label" >{$.i18n.prop("controlPago.fechaPago")} <span class="requeridoDato">*</span></label>
                                        <div  class="input-group date datepickerFechaPago" data-provide="datepicker" data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaPagoT" id="fechaPagoT"  name= "fechaPagoT" readonly value="{controlPago.fechaPagoSTR}" >
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>	                             
                                    </div>
                                    <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6 has-success">
                                        <label class="knob-label" >{$.i18n.prop("controlPago.fechaLimite")}</label>
                                        <div  class="input-group date datepickerFechaLimite" data-provide="datepicker" data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaLimiteT" id="fechaLimiteT"  name= "fechaLimiteT" readonly  value="{controlPago.fechaLimiteSTR}" >
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>	                             
                                    </div>
                                  
                                </div> 
                                <div class="row">   
                                  <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("controlPago.mensaje")}  <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control mensaje campoNumerico" id="mensaje" name="mensaje" value="{controlPago.mensaje}"  >
                                    </div>
                                </div>    
                                <div class="row">   
                                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12 has-success">
                                        <label  class="knob-label">{$.i18n.prop("controlPago.tipoPago")}</label>
                                        <select  class="form-control selectTipoPago " id="tipoPago" name="tipoPago"  >
                                            <option  each={tipoPagos}  value="{codigo}" selected="{controlPago.tipoPago ==codigo?true:false}"  >{descripcion}</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">    
                                    <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("controlPago.total.dolares")} </label>
                                        <input onkeyup ={__CalculoColones} type="number" step="any" class="form-control totalDolar campoNumerico" id="totalDolar" name="totalDolar" value="{controlPago.totalDolar}"  >
                                    </div>
                                    <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("controlPago.total.colones")} </label>
                                        <input onkeyup ={__CalculoDolares}  type="number" step="any" class="form-control totalColones campoNumerico" id="totalColones" name="totalColones" value="{controlPago.totalColones}" >
                                    </div>
                                    <div class="col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra">{$.i18n.prop("controlPago.estado")}</label>
                                        <select  class="form-control campoNumerico" id="estado" name="estado"  >
                                            <option  each={estados}  value="{codigo}" selected="{controlPago.estado ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>  
                                       <div class="col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra">{$.i18n.prop("controlPago.bloqueo")}</label>
                                        <select  class="form-control campoNumerico" id="bloqueo" name="bloqueo"  >
                                            <option  each={bloqueos}  value="{codigo}" selected="{controlPago.bloqueo ==codigo?true:false}" >{descripcion}</option>
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
    .tituloContainer{
        display:flex;
        flex-direction: row;
    }
    .elemento1{
        flex: 1;
    }
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
        fechaPago:'',
        fechaLimite:'',
        mensaje:'',
        tipoPago:'',
        totalDolar:'',
        totalColones:'',
        estado:0,
        empresa:{
            id:0
        }

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
    self.bloqueos                  = []
    self.tipoPagos =[]
    self.estados =[]
    self.tipoCambio ={
        total:0,
        totalCompra:0
    }


self.on('mount',function(){
    __Eventos()
    __Limpiar()
    __Consulta()
    listaEmpresasActivas()
    getTipoCambioDolar()
    __tipoPagos() 
    __ComboEstados()
     __ComboBloqueos()

    $('.collapse').collapse("show")
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
})
function __ComboBloqueos(){
    self.bloqueos =[]
    self.update()
    self.bloqueos.push({
        codigo: 0,
        descripcion:"No"
     });
    self.bloqueos.push({
        codigo: 1,
        descripcion: "Si"
     });
     self.update();
}

__CalculoColones(e){
    var resultado  =  __valorNumerico($('#totalDolar').val()) * self.tipoCambio.total
    $('#totalColones').val(financial(resultado))
}

__CalculoDolares(e){
    var resultado  =  __valorNumerico($('#totalColones').val()) / self.tipoCambio.total
    $('#totalDolar').val(financial(resultado))
}



/**
*Tipo Cambio del Dolar 
**/
function getTipoCambioDolar(){
    $.ajax({
    "url": "https://api.hacienda.go.cr/indicadores/tc",
     global: false,
    "method": "GET",
    statusCode: {
        
        404: function() {
            __TipoCambio()
        }
    }
    }).done(function (response) {
         self.tipoCambio.total = __valorNumerico(response.dolar.venta.valor)
         self.tipoCambio.totalCompra = __valorNumerico(response.dolar.compra.valor)
         self.update()
    }).fail(function () {
        __TipoCambio()
      console.log('fail')
    });;
}
/**
* Tipo Cambio de moneda
**/
function __TipoCambio(){
    self.tipoCambio = {
        total:0,
        totalCompra:0,
        id:null
    }
    self.update()
    $.ajax({
        url: "MostrarTipoCambioActivoAjax.do",
        datatype: "json",
        global: false,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.tipoCambio = modeloTabla
                       self.tipoCambio.totalCompra = self.tipoCambio.total
                       self.update()
                    });
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}

/**
* Limpiar Pantalla
**/
function __Limpiar(){
    self.controlPago = {
        id:null,
        fechaPago:'',
        fechaLimite:'',
        mensaje:'',
        tipoPago:'',
        totalDolar:'',
        totalColones:'',
        estado:0,
        empresa:{
            id:0
        }

    }
    self.update()
     $("#formulario").validate(reglasDeValidacion());
     __Eventos()
}
/**
*  Crear el combo comanda
**/
function __ComboEstados(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: 0,
        descripcion:"Pendiente"
     });
    self.estados.push({
        codigo: 1,
        descripcion: "Pagado"
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
    __Limpiar()

    //Agregar
    if(self.parametros.tipoEjecucion == 1){
        self.mostrarTituloArticulo = true  
        self.mostrarFormulario = true
        self.botonModificar = false
        self.botonAgregar = true
        self.update()
    }   
    //modificar
    if(self.parametros.tipoEjecucion == 2){
        self.controlPago = self.parametros.controlPago
        self.mostrarTituloArticulo = true  
        self.mostrarFormulario = true
        self.botonModificar = true
        self.mostrarFormulario = true 
        self.botonModificar   = true;
        self.botonAgregar  = false;            
        self.update()
       
    }  
}
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			mensaje : {
				required : true,
                maxlength:250,
                minlength:1,
			},                                                
            fechaPagoT : {
				required : true,
			},                                                
            fechaLimiteT : {
				required : true,
			},                                                

            totalDolar : {
				required : true,
                numeroMayorCero:true,
                number:true,
			},                                                
            totalColones : {
				required : true,
                numeroMayorCero:true,
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
    $("#mensaje").attr("maxlength", 250);
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
    //controlPago.js se encuentra rutina
    __mostrarListado()
}
/**
*   Agregar 
**/
__agregar(){
    
        if ($("#formulario").valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formulario").serialize();
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : 'AgregarControlPagoAjax.do',
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
                             __Limpiar()
                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
                    }
                });
    }else{
          sweetAlert("", "Falta ingresar datos del control de pago que son obligatorios, verificar lo indicado en ROJO", "error");
      }
}
/**
** Modificar la Empresa
**/
__Modificar(){
     

    var AplicoImpuesto1 = false
    var AplicoImpuesto2 = false
    if ($("#formulario").valid()) {
        self.error = false;
        self.exito = false;
        self.update();
        __modificarRegistro("#formulario",$.i18n.prop("controlPago.mensaje.alert.modificar"),'ModificarControlPagoAjax.do','ListarControlPagoAjax.do','#tableListar')

    }   else{
          sweetAlert("", "Falta ingresar datos del control de pago que son obligatorios, verificar lo indicado en ROJO", "error");
      }
    
}


</script>
</controlPago-crud>