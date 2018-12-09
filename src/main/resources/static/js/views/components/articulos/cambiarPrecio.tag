<cambiar-precio>

   
<div>
    <div class="row center container-fluid"  >
        <div class="col-md-12 col-lg-12 col-sm-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {$.i18n.prop("titulo.cambiar.precio")}     </h1>
                </div>
                <div class="box-body">
                    <button show = {botonAgregar} title="Agregar un Nuevo Articulo"  onclick={__agregar}   class="btn-green btn-add pull-right" >&nbsp Nuevo</button>
                     <button  onclick={__Modificar} title="modificar el Articulo" show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{articulo.id}">
                        <input type="hidden" id="precioMayorista" name="precioMayorista" value="{articulo.precioMayorista}"  >
                        <input type="hidden" id="gananciaPrecioMayorista" name="gananciaPrecioMayorista" value="{articulo.gananciaPrecioMayorista}">
                        <input type="hidden"  id="gananciaPrecioEspecial" name="gananciaPrecioEspecial" value="{articulo.gananciaPrecioEspecial}"  >
                        <input type="hidden"  id="precioEspecial" name="precioEspecial" value="{articulo.precioEspecial}" >
                        <input type="hidden"  id="maximo" name="maximo" value="{articulo.maximo}"  >
                        <input type="hidden"  id="minimo" name="minimo" value="{articulo.minimo}"  >
                        <input type="hidden"  id="cantidad" name="cantidad" value="{articulo.cantidad}"  >
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.codigo")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="campo codigo" id="codigo" name="codigo" value="{articulo.codigo}"  onkeypress={__addPrecioDetail} autofocus="autofocus">
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.descripcion")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="campo descripcion" id="descripcion" name="descripcion" value="{articulo.descripcion}"  >
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.precioPublico")}  <span class="requeridoDato">*</span></label>
                                <input type="number" step="any" class="campo precioPublico" id="precioPublico" name="precioPublico" onkeyup ={__CalculoGananciaPublico} value="{articulo.precioPublico}"  >
                            </div>
                            
                        </div>                        
                      
                        <div class="row">
                            <div class="col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales">{$.i18n.prop("articulo.tipoImpuesto")}<span class="requeridoDato">*</span></label>
                                <select onchange= {__asignarImpuesto} class="campo selectTipoImpuesto" id="tipoImpuesto" name="tipoImpuesto"  >
                                   
                                    <option  each={impuestos}  value="{codigo}" selected="{articulo.tipoImpuesto ==codigo?true:false}"  >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.impuesto")}  </label>
                                <input type="number" step="any" class="campo impuesto" id="impuesto" name="impuesto" value="{articulo.impuesto}"  onkeyup ={__ActualizarPreciosImpuestos}>
                            </div>
            
                            <div class="col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales">{$.i18n.prop("articulo.tipoCodigo")}<span class="requeridoDato">*</span></label>
                                <select  class="campo selectTipoCodigo" id="tipoCodigo" name="tipoCodigo"  >
                                    <option  each={tipoCodigos}  value="{codigo}" selected="{articulo.tipoCodigo ==codigo?true:false}"  >{descripcion}</option>
                                </select>
                            </div>

                        </div>    

                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.marca")}  <span class="requeridoDato">*</span></label>
                                 <select  class="campo selectMarca"  name="marca">
                                    <option  each={marcas.aaData}  value="{id}" selected="{articulo.marca.id ==id?true:false}"  >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.unidadMedida")}  <span class="requeridoDato">*</span></label>
                                 <select  class="campo selecTipoUnidad has-success" name="unidadMedida" >
                                    <option   each={tipoUnidades.aaData}  value="{codigo}"  selected="{articulo.unidadMedida ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            <div class="col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales">{$.i18n.prop("articulo.contable")}</label>
                                <select  class="campo" id="contable" name="contable" >
                                    <option  each={contables}  value="{codigo}" selected="{articulo.contable ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        </div>    
                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.categoria")}  <span class="requeridoDato">*</span></label>
                                 <select  class="campo selectCategoria"   name="categoria" >
                                    <option  each={categorias.aaData}  value="{id}" selected="{articulo.categoria.id ==id?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales"  >{$.i18n.prop("articulo.costo")} </label>
                                <input type="number" step="any" class="campo costo" id="costo" name="costo" value="{articulo.costo}"  onkeyup ={__ActualizarPreciosCosto}>
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                 <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.gananciaPrecioPublico")}%  </label>
                                <input type="number" step="any" class="campo gananciaPrecioPublico" id="gananciaPrecioPublico" name="gananciaPrecioPublico"  value="{articulo.gananciaPrecioPublico}"  onkeyup ={__CalculoGananciaSinPrecioPublico}>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.estado")}</label>
                                <select  class="campo" id="estado" name="estado"  >
                                    <option  each={estados}  value="{codigo}" selected="{articulo.estado ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>                          

                        </div>
                      
                      
                    </form>    
                </div>
            </div>   
        </div>
       
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
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del Listado de la Tabla 
    self.categorias                = {aaData:[]}
    self.marcas                    = {aaData:[]}
    self.tipoUnidades              = {aaData:[]}
    self.impuestos =[]
    self.tipoCodigos =[]
    self.contables                 = []
    self.estados                   = []
    self.botonModificar            = true
    self.botonAgregar              = false
    self.mostrarTituloArticulo     = true
    self.articulo = {
		id:null,
        codigo:"",
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
        }
    }    
self.on('mount',function(){
    __Eventos()
    __ComboEstados()
    __ComboContables()
    __listadoTipoUnidadesActivas()   
    __listadoMarcasActivas()
    __Impuestos() 
    __tipoCodigo()
    LimpiarArticulo()
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
})
/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
 __addPrecioDetail(e){
    if (e.keyCode != 13) {
        return;
    } 
     var codigo = $('#codigo').val()
     
    var parametros = {
        codigo:$('.codigo').val(),
        precioPublico : $('.precioPublico').val()
    }
    $.ajax({
        url: "MostrarPorCodigoAjax",
        datatype: "json",
        data: parametros,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    serverMessageJsonClase(data);
                    self.botonModificar            = false
                    self.botonAgregar              = true
                    self.update()
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                    //desahabilita  listado 
                        LimpiarArticulo()
                        self.articulo = modeloTabla
                        if(self.articulo !=null){
                            self.botonModificar            = true
                            self.botonAgregar              = false
                        }
                        self.update()
                        $('.codigo').val(modeloTabla.codigo)
                        $('.descripcion').val(modeloTabla.descripcion)
                        $('.precioPublico').val(modeloTabla.precioPublico)
                        $(".precioPublico").focus() 
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
        }
    }    
    self.update() 
   $('.selectTipoImpuesto').prop("selectedIndex", 0);
   $('.selectTipoCodigo').prop("selectedIndex", 0);
   $('.selecTipoUnidad').prop("selectedIndex", 0);
   $('.selectMarca').prop("selectedIndex", 0);
   $('.selectCategoria').prop("selectedIndex", 0);
   $("#categoria").val($("#categoria option:first").val()); 
   $("#marca").val($("#marca option:first").val()); 
   $("#unidadMedida").val($("#unidadMedida option:first").val()); 
   $("#contable").val($("#contable option:first").val()); 
   $('.codigo').val(null)
   $('.descripcion').val(null)
   $('.costo').val(0)
   $('.impuesto').val(0)
   $('.precioPublico').val(0)
   $('.gananciaPrecioPublico').val(0)
   $(".errorServerSideJgrid").remove();
   $("#formulario").validate(reglasDeValidacion());
   enviarCargarCombos()
}
/**
*  Envia  a llamar a los eventos ajax de cada combo para actualizarlo de acuerdo a la empresa
**/
__cargarCombos(){
  enviarCargarCombos()
}
/**
* Enviar a consultar al back end 
**/
function enviarCargarCombos(){
    __listadoCategoriasActivas()
    __listadoMarcasActivas()
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
                                                   
                        
		},
		ignore : []

	});
	return validationOptions;
};

/** Fin  funciones de inventario ----------------------------------------------------------------------------**/

/**
* Actualizar ganancias al digitar el impuesto
**/
__ActualizarPreciosImpuestos(e){
    let impuesto  = __valorNumerico(e.target.value)
    if(impuesto ==0){
        return
    }
    let costo     =  __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioEspecial   = self.articulo.precioEspecial > 0 ? _porcentajeGanancia(costo,impuesto,self.articulo.precioEspecial):0
    self.articulo.gananciaPrecioMayorista  = self.articulo.precioMayorista > 0 ? _porcentajeGanancia(costo,impuesto,self.articulo.precioMayorista):0
    self.articulo.gananciaPrecioPublico    = self.articulo.precioPublico > 0 ? _porcentajeGanancia(costo,impuesto,self.articulo.precioPublico):0
    self.update()
}

/**
* Porcentaje de ganancia de Precio al Publico
**/
__CalculoGananciaSinPrecioPublico(e){
   var ganancia = __valorNumerico(e.target.value)
   
    var impuesto      = __valorNumerico($('#impuesto').val())
    var costo         = __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioPublico  = ganancia
    self.articulo.precioPublico = _PrecioPublicoConGanancia(costo,impuesto,ganancia)
    self.update()
}

/**
* Asigna el impuesto 13 cuando es valor igual 01
**/
__asignarImpuesto(){
    if($('.selectTipoImpuesto').val()=="01"){
        self.articulo.tipoImpuesto ="01"
        self.articulo.impuesto = 13
        self.update()
        var resultado = 13/100
            resultado = 1 + resultado 
            if(self.articulo.precioPublico > self.articulo.costo){
            //    self.articulo.precioPublico = self.articulo.precioPublico * resultado
                self.articulo.gananciaPrecioPublico = self.articulo.precioPublico >0?_porcentajeGanancia(self.articulo.costo,self.articulo.impuesto,self.articulo.precioPublico):0
            }else{
                self.articulo.precioPublico = __valorNumerico(redondeoDecimales(self.articulo.precioPublico * resultado,8)); 
                self.articulo.gananciaPrecioPublico = 0
            }
            self.update()   
        
    }else{
        $('.impuesto').val(null)
        self.articulo.tipoImpuesto =$('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
        
        var resultado = self.articulo.impuesto/100
        self.articulo.impuesto = 0
        resultado = resultado == 0 ?0:1 + resultado 
        if(self.articulo.precioPublico > 0 && self.articulo.gananciaPrecioPublico == 0 ){
           self.articulo.precioPublico =  __valorNumerico(redondeoDecimales(self.articulo.precioPublico / resultado,8));               
        }
        self.update()
        if(self.articulo.precioPublico > self.articulo.costo){
            self.articulo.gananciaPrecioPublico = self.articulo.precioPublico >0?_porcentajeGanancia(self.articulo.costo,self.articulo.impuesto,self.articulo.precioPublico):0
        }else{
            self.articulo.gananciaPrecioPublico = 0
        }
         self.update()
      
    }
      
}


/**
* Porcentaje de ganancia de Precio al Publico
**/

__CalculoGananciaPublico(e){
 var precioPublico = __valorNumerico(e.target.value)
    if(precioPublico ==0){
       return
    }
    
    var impuesto      = __valorNumerico($('#impuesto').val())
    var costo         = __valorNumerico($('#costo').val())
    if(precioPublico == costo){
        self.articulo.tipoImpuesto =$('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
        $('#tipoImpuesto').val("Sin impuesto")  
        self.articulo.impuesto = 0
        impuesto = 0 
    }
    self.articulo.gananciaPrecioPublico  = _porcentajeGanancia(costo,impuesto,precioPublico)
    self.articulo.precioPublico = precioPublico
    self.update()
}

function _PrecioPublicoConGanancia(costo,impuesto,ganancia){
  if(ganancia == 0){
      return 0
  } 
  if(costo == 0){
      return 0
  } 
  var porcentajeGanancia = ganancia/100;
  porcentajeGanancia = porcentajeGanancia < 1 ?1 - porcentajeGanancia:porcentajeGanancia
  var totalImpuesto = impuesto == 0 ?0:impuesto / 100
  totalImpuesto = totalImpuesto == 0 ?0:totalImpuesto + 1
  var precio  = 0
  if(porcentajeGanancia < 1){
    precio = costo / porcentajeGanancia
  }else{
      if(porcentajeGanancia == 1){
        precio = costo * 2 
      }else{
        precio = costo * porcentajeGanancia
      }
  }
  precio = totalImpuesto >0? precio * totalImpuesto:precio;
  return __valorNumerico(redondeoDecimales(precio,5));
}
/**
* Actualizar el precio costo
**/
__ActualizarPreciosCosto(e){
    let costo    = __valorNumerico(e.target.value)
    
    let impuesto =  __valorNumerico($('#impuesto').val())
    self.articulo.costo = costo 
    self.articulo.gananciaPrecioEspecial   = self.articulo.precioEspecial > 0?_porcentajeGanancia(costo,impuesto,self.articulo.precioEspecial):0
    self.articulo.gananciaPrecioMayorista  = self.articulo.precioMayorista>0?_porcentajeGanancia(costo,impuesto,self.articulo.precioMayorista):0
    self.articulo.gananciaPrecioPublico    = self.articulo.precioPublico >0?_porcentajeGanancia(costo,impuesto,self.articulo.precioPublico):0
     
    self.update()

    _CalculoPrecio(costo,impuesto)
}

function _CalculoPrecio(costo,impuesto){
      self.articulo.precioPublico = _PrecioPublicoConGanancia(costo,self.articulo.impuesto,self.articulo.gananciaPrecioPublico)
    self.update()

}
/**
* autor : Leonel Hernandez Chaverri
* Fecha : 23-06-17
* obtener la ganancia del precio en decimal
**/
function _porcentajeGanancia(costo,impuesto,precioVenta) {
  var porcentajeGanancia = 0;
  var precioSinImpuesto  = 0;
  if(costo == 0){
      return 0
  } 
  if(precioVenta == 0){
    return 0;
  }
  if(costo == precioVenta){
      return 0
  }
  var resultado = 0
  if(impuesto == 0 || impuesto == null ){
      if(costo == precioVenta){
          resultado = 0
      }else{
        resultado =  precioVenta / costo
        resultado = resultado  - 1
      }
    porcentajeGanancia  = resultado;
  }else{ 
    if(costo == precioVenta){
       porcentajeGanancia  = 0; 
    }else{
        precioSinImpuesto = __valorNumerico(redondeoDecimales(precioVenta/((impuesto/100) + 1),5));
        if(precioSinImpuesto ==  costo){
            resultado = 0
        }else{
        resultado =  precioSinImpuesto / costo
        resultado = resultado  - 1
        }
        porcentajeGanancia  = resultado;

    } 
  }
  return __valorNumerico(porcentajeGanancia * 100);
}
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
*  Mostrar listado datatable Categorias activas
**/
function __listadoCategoriasActivas(){
     self.categorias                = {aaData:[]}
     self.update()
    $.ajax({ 
         url: "ListarCategoriasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.categorias.aaData =  result.aaData
                self.update();
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}
/**
*  Mostrar listado datatable Categorias Actimpuestos
**/
function __listadoMarcasActivas(){
    self.marcas                    = {aaData:[]}
    self.update()
    $.ajax({
         url: "ListarMarcasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.marcas.aaData =  result.aaData
                self.update();
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}
/**
*  Mostrar listado datatable unidades de medidas activas
**/
function __listadoTipoUnidadesActivas(){
    $.ajax({
         url: "ListarTipoUnidadesAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.tipoUnidades.aaData =  result.aaData
                self.update();
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}
/**
*  Crear el combo de estados
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
* Combo para verificar si es contabilizado en el inventario o no
**/
function __ComboContables(){
    self.contables =[]
    self.update()
    self.contables.push({
        codigo: $.i18n.prop("boolean.no"),
        descripcion: $.i18n.prop("boolean.no")
     });
    self.contables.push({
        codigo: $.i18n.prop("boolean.si"),
        descripcion:$.i18n.prop("boolean.si")
     });
     self.update();
}
/**
* Combo para verificar si es contabilizado en el inventario o no
**/
function __Impuestos(){
    self.impuestos =[]
    self.update()
     self.impuestos.push({
        codigo: "",
        descripcion:"Sin impuesto"
     });
    self.impuestos.push({
        codigo: '01',
        descripcion:$.i18n.prop("tipo.impuesto.ventas")
     });
    self.impuestos.push({
        codigo: '07',
        descripcion:$.i18n.prop("tipo.impuesto.servicio")
     });
    self.impuestos.push({
        codigo: '12',
        descripcion:$.i18n.prop("tipo.impuesto.cemento")
     });
    self.impuestos.push({
        codigo: '98',
        descripcion:$.i18n.prop("tipo.impuesto.otros")
     });
   
     self.update();
}
/**
* Tipo codigo del producto/servicio del articulo
**/
function __tipoCodigo(){
    self.tipoCodigos =[]
    self.update()
    self.tipoCodigos.push({
        codigo: '01',
        descripcion:$.i18n.prop("articulo.tipo.codigo.vendedor")
    });
    self.tipoCodigos.push({
        codigo: '04',
        descripcion:$.i18n.prop("articulo.tipo.codigo.uso.interno")
     });
     self.update();
}
/**
*   Agregar 
**/
__agregar(){
        if ($("#formulario").valid()) {
            var tipo = $('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
            if (tipo !=""){
                if($('#impuesto').val()==0){
                    mensajeError($.i18n.prop("error.articulo.indicar.tipo.impuesto"))
                    return 
                }
            }else{
                if($('#impuesto').val()>0){
                    mensajeError($.i18n.prop("error.articulo.no.tipo.impuesto"))
                    return 
                }
        }
        if(self.articulo.costo > self.articulo.precioPublico){
            mensajeError("No se puede agregar el precio Publico es menor al costo")
            return 
        }
        self.articulo.id = null
        self.update()    
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
                            LimpiarArticulo()
                              __Eventos()
                            self.botonModificar            = true
                            self.botonAgregar              = false
                            self.update()

                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
                    }
                });
    }
}
/**
** Modificar la Empresa
**/
__Modificar(){
     var tipo = $('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
    if (tipo !=""){
        if($('#impuesto').val()==0){
            mensajeError($.i18n.prop("error.articulo.indicar.tipo.impuesto"))
            return 
        }
    }else{
        if($('#impuesto').val()>0){
            mensajeError($.i18n.prop("error.articulo.no.tipo.impuesto"))
            return 
        }
    }
     if(self.articulo.costo > self.articulo.precioPublico){
            mensajeError("No se puede modificar el Articulo el precio Publico es menor al costo")
            return 
        }
    self.error = false;
    self.exito = false;
    self.update();
    if ($("#formulario").valid()) {
        var formulario = $("#formulario").serialize();
        $.ajax({
            type : "POST",
            dataType : "json",
            data : formulario,
            url : "ModificarArticuloAjax.do",
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
                    serverMessageJson(data)
                    swal({
	               	    title: '',
	                    text: data.message,
	                    type: 'success',
	                    showCancelButton: false,
	                    confirmButtonText: 'Aceptar',
	                })
                    self.botonModificar            = true
                    self.botonAgregar              = false
                    self.update()
                }
            },
            error : function(xhr, status) {
                 mensajeErrorServidor(xhr, status);
            }
        });
    }
}


function sumar(){
    self.totalCosto = 0
    self.totalPrecioPublico = 0
    self.update()
    $.each(self.listaArticulos, function( index, modeloTabla ) {
          self.totalCosto += modeloTabla.costo * modeloTabla.cantidad
          self.totalPrecioPublico += modeloTabla.precioPublico * modeloTabla.cantidad
    })
    self.totalCosto         = redondearDecimales(self.totalCosto,2)
    self.totalPrecioPublico = redondearDecimales(self.totalPrecioPublico,2)
    self.update()
}


</script>

</cambiar-precio>