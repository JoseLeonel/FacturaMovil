<cliente-crud>
<div id="tituloBotones" show={mostrarFormulario}>
    <div class="articulo-title"><i class="fa fa-edit"></i>&nbsp {cliente.id > 0 ? $.i18n.prop("titulo.modificar.cliente")   :$.i18n.prop("titulo.agregar.cliente")}  </div>
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
                            <h4 class="panel-title"><span class="fa fa-user col-md-offset-5"></span> Datos del Cliente</h4>
                        </div>
                        </a>
                        <div id="collapse1" class="panel-collapse collapse abrirDatos">
                            <div class="panel-body">
                            
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
                                        <label  >{$.i18n.prop("cliente.cedula")} <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control cedula" id="cedula" name="cedula" placeHolder ="{$.i18n.prop("cliente.cedula")}" value="{cliente.cedula}"  onkeypress = {__ConsultarHacienda} onBlur ={__ConsultarHaciendaBlur}  autocomplete="off">
                                    </div>
                                    <div class= "col-md-2 col-sx-12 col-sm-2 col-lg-2">
                                        <label  >{$.i18n.prop("cliente.tipoCedula")}  <span class="requeridoDato">*</span></label>
                                        <select  class="form-control tipoCedula " id="tipoCedula" name="tipoCedula" >
                                            <option  each={tipoCedulas.data}  value="{valor}" selected="{cliente.tipoCedula ==valor?true:false}"  >{descripcion}</option>
                                        </select>
                                    </div>                            

                                    <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                        <label  >{$.i18n.prop("cliente.nombreCompleto")}  <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control nombreCompleto" placeHolder ="{$.i18n.prop("cliente.nombreCompleto")}" id="nombreCompleto" name="nombreCompleto" value="{cliente.nombreCompleto}"  autocomplete="off" >

                                    </div>
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("cliente.nombreComercial")} </label>
                                        <input type="text" class="form-control nombreComercial" placeHolder ="{$.i18n.prop("cliente.nombreComercial")}" id="nombreComercial" name="nombreComercial" value="{cliente.nombreComercial}"   autocomplete="off">
                                    </div>
                                    
                                </div>
                                <div class="row">
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label >Cedula Extranjero</label>
                                        <input type="text" class="form-control identificacionExtranjero" placeHolder ="Cedula Extranjero" id="identificacionExtranjero" name="identificacionExtranjero" value="{cliente.identificacionExtranjero}"  autocomplete="off">
                                    </div>

                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("cliente.codigoPais")} <span class="requeridoDato">*</span> </label>
                                        <input type="text" class="form-control codigoPais" placeHolder ="{$.i18n.prop("cliente.codigoPais.ejemplo")}" id="codigoPais" name="codigoPais" value="{cliente.codigoPais}"  autocomplete="off">
                                    </div>
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("cliente.telefono")} </label>
                                        <input type="text" class="form-control telefono" placeHolder ="{$.i18n.prop("cliente.telefono")}" id="telefono" name="telefono" value="{cliente.telefono}"  autocomplete="off">
                                    </div>
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("cliente.correoElectronico")}</label>
                                        <input type="text" class="form-control correoElectronico" placeHolder ="{$.i18n.prop("cliente.correoElectronico")}" id="correoElectronico" name="correoElectronico" value="{cliente.correoElectronico}"  autocomplete="off">
                                    </div>
                                </div>
                                                    
                                <div class="row">    
                                    

                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label >{$.i18n.prop("cliente.correoElectronico1")}</label>
                                        <input type="text" class="form-control correoElectronico1" placeHolder ="{$.i18n.prop("cliente.correoElectronico1")}" id="correoElectronico1" name="correoElectronico1" value="{cliente.correoElectronico1}"  autocomplete="off">
                                    </div>
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("cliente.correoElectronico2")}</label>
                                        <input type="text" class="form-control correoElectronico2" placeHolder ="{$.i18n.prop("cliente.correoElectronico2")}" id="correoElectronico2" name="correoElectronico2" value="{cliente.correoElectronico2}"  autocomplete="off">
                                    </div>
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label  >{$.i18n.prop("cliente.correoElectronico3")}</label>
                                        <input type="text" class="form-control correoElectronico3" placeHolder ="{$.i18n.prop("cliente.correoElectronico3")}" id="correoElectronico3" name="correoElectronico3" value="{cliente.correoElectronico3}"  autocomplete="off">
                                    </div>
                                    <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                        <label >{$.i18n.prop("cliente.estado")}</label>
                                        <select  class="form-control" id="estado" name="estado" >
                                            <option  each={estados}  value="{codigo}" selected="{cliente.estado ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="row">    
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                        <label  >{$.i18n.prop("cliente.otraSena")}</label>
                                        <textarea maxlength="250" placeHolder ="{$.i18n.prop("cliente.otraSena")}"  class="form-control otraSena" id="otraSena" name="otraSena" value="{cliente.otraSena}" > </textarea> 
                                    </div>
                                    <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                        <label >{$.i18n.prop("cliente.otros")}</label>
                                        <textarea maxlength="250" placeHolder ="{$.i18n.prop("cliente.otros")}"  class="form-control observacionVenta" id="observacionVenta" name="observacionVenta" value="{cliente.observacionVenta}" > </textarea>                                
                                    </div>
                                    <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                        <label>Limite Credito</label>
                                        <input type="number" step="any" class="form-control limiteCredito " id="limiteCredito" name="limiteCredito" value="{cliente.limiteCredito}" autocomplete="off" >
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>             
        </div> 
        <div class="row" >  
            <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default" >
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" >
                            <div class="panel-heading" style="background: #3c8dbc; color: white;">
                                <h4 class="panel-title"><span class="fa fa-bank  col-md-offset-5"></span> Exoneracion con afiliacion a Exonet/Exento de IVA,Extensiones articulo 8 de la ley 9635</h4>
                            </div>
                        </a>
                        <div id="collapse2" class="panel-collapse collapse abrirDatos1">
                            <div class="panel-body">
                                <div class="row">    
                                    <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                        <label  >{$.i18n.prop("factura.tipo.documento")}</label>
                                        <select  class="form-control tipoDocumentoExoneracion" id="tipoDocumentoExoneracion" name="tipoDocumentoExoneracion"   >
                                            <option each={comboTipoDocumentoExonerados} value="{estado}" selected="{cliente.tipoDocumentoExoneracion ==estado?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>
                                    <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                        <label >Numero Documento Autorizacion </label> 
                                        <input  type="text"  class="form-control numeroDocumentoExoneracion" placeHolder ="Formato si es Exonet :AL-XXXXXXXX-19" id="numeroDocumentoExoneracion" name = "numeroDocumentoExoneracion" autofocus="autofocus"  value ="{cliente.numeroDocumentoExoneracion}">                    
                                    </div>
                                    <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                        <label >Fecha Autorizacion  </label> 
                                           <div  class="input-group date datepickerfechaEmisionExoneracionSTR" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                                <input type="text" class="form-control fechaEmisionExoneracionSTR" id="fechaEmisionExoneracionSTR"  name= "fechaEmisionExoneracionSTR" readonly>
                                                <div class="input-group-addon">
                                                  <span class="glyphicon glyphicon-th"></span>
                                                </div>
                                            </div>	                             
                                    </div>
                                </div>
                                <div class="row">
                                    <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                        <label >Nombre de la Institucion que autoriza  </label> 
                                        <input type="text"  class="form-control nombreInstitucionExoneracion" id="nombreInstitucionExoneracion" name = "nombreInstitucionExoneracion" autofocus="autofocus"  value ="{cliente.nombreInstitucionExoneracion}">                    
                                    </div>
                                    <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                        <label >% Porcentaje Autorizacion  </label> 
                                        <input  type="number"  class="form-control porcentajeExoneracion" id="porcentajeExoneracion" name = "porcentajeExoneracion" autofocus="autofocus"  value ="{cliente.porcentajeExoneracion}">                    
                                    </div>
                               
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>        
        </div>   
          <div class="row" >  
            <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default" >
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse3" >
                            <div class="panel-heading" style="background: #3c8dbc; color: white;">
                                <h4 class="panel-title"><span class="fa fa-bank  col-md-offset-5">XII del Decreto  ejecutivo NO. 41779 .Pesca y Agropecuarios Ley  </span> </h4>
                            </div>
                        </a>
                        <div id="collapse3" class="panel-collapse collapse abrirDatos1">
                            <div class="panel-body">
                                <div class="row">    
                                    <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                        <label  >Autorizacion del Mag</label>
                                        <select  class="form-control tipoMag" id="tipoMag" name="tipoMag"   >
                                            <option  each={estadosMag}  value="{codigo}" selected="{cliente.tipoMag ==codigo?true:false}" >{descripcion}</option>
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



<script>
    var self = this;
    self.parametros          = opts.parametros;  
    self.tipoCedulas               = {data:[]}  // definir el data del datatable
    self.botonModificar            = false
    self.botonAgregar              = false
    self.estadosMag   = []
    self.comboTipoDocumentoExonerados   = []
    self.cliente                   ={
		id:null,
    	nombreCompleto:"",
        tipoDocExonerado:"",
        fechaEmisionExoneracion:null,
        tipoDocExonerado:"",
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
    __ComboLibreImpuesto()
    __listadoTipoCedulas()
     //modificar cliente
    if(self.parametros.tipoEjecucion == 1){
       __modificarCliente()
    }
     //Agregar
    if(self.parametros.tipoEjecucion == 2){
       __Agregar()
    }
    __ComboTipoDocumentoExonerados()
    //configuracionDatePicker()
   
    $('.datepickerfechaEmisionExoneracionSTR').datepicker(
    {
        format: 'yyyy-mm-dd',
        todayHighlight:true,
    }
    );
    __ComboEstadosMag()
    window.addEventListener( "keydown", function(evento){
       $(".errorServerSideJgrid").remove();
    }, false );
})
/**
*  Crear el combo de estados
**/
function __ComboEstadosMag(){
    self.estadosMag =[]
    self.update()
    self.estadosMag.push({
        codigo: 0,
        descripcion:$.i18n.prop("combo.estadoMag.inactivo")
     });

    self.estadosMag.push({
        codigo: 1,
        descripcion:$.i18n.prop("combo.estadoMag.agro")
     });
    self.estadosMag.push({
        codigo: 2,
        descripcion: $.i18n.prop("combo.estadoMag.pesca")
     });
     self.update();

}

/**
* cargar los tipos de Documento de la factura
**/
function __ComboTipoDocumentoExonerados(){
    self.comboTipoDocumentoExonerados = []
    self.update()
    self.comboTipoDocumentoExonerados.push({
        estado:"01",
        descripcion:$.i18n.prop("tipo.documento.exonerado.compras.autorizadas")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"02",
        descripcion:$.i18n.prop("tipo.documento.exonerado.venta.exenta.diplomado")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"03",
        descripcion:$.i18n.prop("tipo.documento.exonerado.autorizado.por.ley.hacienda")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"04",
        descripcion:$.i18n.prop("tipo.documento.exonerado.execciones.direccion.general.hacienda")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"05",
        descripcion:$.i18n.prop("tipo.documento.exonerado.transitorio.v")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"06",
        descripcion:$.i18n.prop("tipo.documento.exonerado.transitorio.ix")
    })
    self.comboTipoDocumentoExonerados.push({
        estado:"07",
        descripcion:$.i18n.prop("tipo.documento.exonerado.transitorio.xvii")
    })

    self.comboTipoDocumentoExonerados.push({
        estado:"99",
        descripcion:$.i18n.prop("tipo.documento.exonerado.otros")
    })

    self.update()
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
    self.cliente = self.parametros.data
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
    $('.identificacionExtranjero').val(null)
    $('.telefono').val(null)
    $('.codigoPais').val(506)
    $('.correoElectronico').val(null)
    $('.correoElectronico1').val(null)
    $('.correoElectronico2').val(null)
    $('.correoElectronico3').val(null)
    $('.porcentajeExoneracion').val(null)
    $('.fechaEmisionExoneracion').val(null)
    $('.numeroDocumentoExoneracion').val(null)
    $('.otraSena').val(null)
    $('.nombreComercial').val(null)
    $(".errorServerSideJgrid").remove();
    $("#formulario").validate(reglasDeValidacion());
   // configuracionDatePicker()
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
        montoExoneracion:0,
        porcentajeExoneracion:0,
        fechaEmisionExoneracion:null,
        nombreInstitucionExoneracion:"",
        numeroDocumentoExoneracion:"",
        tipoDocumentoExoneracion:"",

        descuento:0,
        estado:"",
        sucursal:{
           id:null
        }
    }
    self.update()
    
}


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
    
    getClienteHacienda()
}



function getClienteHacienda(cedula){
    var cedula = $('#cedula').val()
    if(stringVacio($(".cedula").val()) == false){
       return    
    }
    $('.correoElectronico').val('')
    $('.correoElectronico1').val('')
    $('.correoElectronico2').val('')
    $('.correoElectronico3').val('')
    $('.numeroDocumentoExoneracion').val('')
    $('.nombreInstitucionExoneracion').val('')
    $('.nombreComercial').val('')
    $('.porcentajeExoneracion').val(0)
    self.clienteHacienda= {
        nombre:"",
        tipoIdentificacion:"",
        regimen:{
            codigo:"",
            descripcion:""
        },
        actividades:[]
    }
    self.update()

    $.ajax({
        url: "clienteHacienda.do",
        datatype: "json",
        data: {cedula:cedula},
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeErrorTiempo( "Cedula no se encuentra registrada en Registro Nacional de Costa Rica" )
                    __listadoTipoCedulas()
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.clienteHacienda = modeloTabla
                        self.update()
                        __listadoTipoCedulas()
                         $('#nombreCompleto').val(self.clienteHacienda.nombre)
                        
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
    if(self.tipoCedulas.data.length == 0){
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
			
                maxlength:12,
                minlength:9,
			},
            nombreCompleto : {
				
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
                maxlength:60,
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
                minlength:6,
			},
            porcentajeExoneracion:{
             number:true,
             porcentajes:true
            },
            numeroDocumentoExoneracion:{
              maxlength:40,
            },
            tipoDocumentoExoneracion:{
              maxlength:2,
            },
            nombreInstitucionExoneracion:{
              maxlength:160,
            }                          
		},
		ignore : []

	});
	return validationOptions;
};
/**
* Libre de Impuesto
**/
function __ComboLibreImpuesto(){
    self.comboLibreImpuesto =[]
    self.update()
    self.comboLibreImpuesto.push({
        estado: "0",
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });

    self.comboLibreImpuesto.push({
        estado: "1",
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
     self.update();

}
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

    $('.porcentajeExoneracion').mask('000', {
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
    var valiExtranjero = stringVacio($(".identificacionExtranjero").val())
    if(valiExtranjero == true){
        return false
    }

    if($("#tipoCedula").val()=="01"){
        var resul = $("#cedula").val()
        if(resul.length < 9 || resul.length > 9  ){
            mensajeErrorTiempo("Formato Invalido , Cedula Fisica es de 9 digitos")
            return true

        }
    }

     //juridicas
    if($("#tipoCedula").val()=="02"){
        var resul = $("#cedula").val()
        if(resul.length < 10 || resul.length > 10  ){
            mensajeErrorTiempo("Formato Invalido , Cedula Juridicas es de 10 digitos")
            return true

        }
    }
     //juridicas
    if($("#tipoCedula").val()=="03"){
        var resul = $("#cedula").val()
        if(resul.length < 12 || resul.length > 12  ){
            mensajeErrorTiempo("Formato Invalido , Cedula Dimex es de 12 digitos")
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
    if(validaExoneracion()){
        return
    }
    self.error = false;
    self.exito = false;
    self.update();
   __modificarRegistro("#formulario",$.i18n.prop("cliente.mensaje.alert.modificar"),'ModificarClienteAjax.do','ListarClientesAjax.do','#tableListar')
}

function validaExoneracion(){
   valor  = __valorNumerico($('#porcentajeExoneracion').val())
   if(valor > 100){
        sweetAlert("", "El porcentaje debe ser menor o igual al 100%", "error");
       return true
   }
   return false;

}
/**
*   Agregar 
**/
__agregar(){
    if(validarTamanosCedulas()){
        return
    }
    if(validaExoneracion()){
        return
    }
    if ($("#formulario").valid()) {
        aplicarCreacion()
    }else{
        mensajeErrorTiempo("Error Faltan datos requeridos")
        return true
    }
}

function aplicarCreacion(){
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
                                mensajeErrorTiempo(data.message)
                            }
                        } else {
                        	serverMessageJson(data);
                            mensajeExito(data.message)
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
        })        

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
                    mensajeErrorTiempo("", data.message, "error");
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
                         $("#fechaEmisionExoneracionSTR").val(self.cliente.fechaEmisionExoneracionSTR)
                         $("#numeroDocumentoExoneracion").val(self.cliente.numeroDocumentoExoneracion)
                         $("#porcentajeExoneracion").val(self.cliente.porcentajeExoneracion)
                        
                        
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
