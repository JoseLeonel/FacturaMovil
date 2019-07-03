<cambiar-precio>

   






<style type ="text/css">
.contenedor {
  width: 95%;
  max-width: 1200px;
  overflow-x: scroll;
  overflow-y: scroll;
   height:100%;
}
.scrollBarras {
    background-color: #3c8dbc;
    color: white;
    font-size: 28px;
    font-weight: 600;
     margin: 2px;
}
.contenScroll{
background-color: white;
    color: #2b2727;
    padding-left: 5px;
    padding-right: 5px;
    margin-bottom: 10px;
    border-radius: 3px;
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
        
        
        .campoNumerico {
            display: flex;
            flex:1;
            width: 100%;
            height: 40px;
            padding: 8px 18px;
            line-height: 1.42857143;
            
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 2px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
            -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
            background-color: #fcfcfc;
            border: 1px solid #ccc;
            font-size: 25px;
            margin: 2px 0;
            padding: 1px 2px;
            overflow: visible;
            color: #ec351f;
    font-weight: bold;
}
.campoNormal {
    display: flex;
    width: 100%;
    height: 40px;
    padding: 8px 18px;
    line-height: 1.42857143;
    color: #555;
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
    font-size: 25px;
    margin: 2px 0;
    padding: 1px 2px;
    overflow: visible;
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
    self.impuestos1 =[]
    self.tipoCodigos =[]
    self.contables                 = []
    self.estados                   = []
    self.botonModificar            = true
    self.botonAgregar              = false
    self.mostrarTituloArticulo     = true
    self.precioPantalla            = true
    
    self.impuestosIVAIPantalla     = false
    self.tabprecio = true
    self.tabImpuestos = false
    self.tabOtros = false
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
    self.tarifas1    = {aaData:[]}
   self.tarifas2    = {aaData:[]}
     self.baseImponibles =[]
self.on('mount',function(){
    __Eventos()
    __ComboEstados()
    __ComboContables()
    __listadoTipoUnidadesActivas()   
    __listadoMarcasActivas()
    __Impuestos() 
    __Impuestos1() 
    __tipoCodigo()
    LimpiarArticulo()
    __ComboBaseImponibles()
    $('#panelFiltros').click(function () {
        var advanced_search_section = $('#filtrosAvanzados');
        advanced_search_section.slideToggle(750);
    });
     $('#panelFiltrosImpuestos').click(function () {
        var advanced_search_section = $('#filtrosAvanzadosImpuestos');
        advanced_search_section.slideToggle(750);
    });
    $('#panelFiltrosOtros').click(function () {
        var advanced_search_section = $('#filtrosAvanzadosOtros');
        advanced_search_section.slideToggle(750);
    });
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
})
/**
*  Crear el combo base imponible
**/
function __ComboBaseImponibles(){
    self.baseImponibles =[]
    self.update()
    self.baseImponibles.push({
        codigo: 1,
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
    self.baseImponibles.push({
        codigo: 0,
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
     self.update();
}
__AsignarTarifa(){
    self.articulo.impuesto = getMontoImpuesto(self.articulo.tipoImpuesto,$('#codigoTarifa').val(),self.tarifas1.aaData)
    self.update()
    
    actualizarPreciosImpuestosMayorista()
    actualizarPreciosImpuestosPublico()
    actualizarPreciosImpuestosEspecial()
}
__AsignarTarifa1(){
    self.articulo.impuesto1 = getMontoImpuesto(self.articulo.tipoImpuesto1,$('#codigoTarifa1').val(),self.tarifas2.aaData)
    self.update()
    
    actualizarPreciosImpuestosMayorista()
    actualizarPreciosImpuestosPublico()
    actualizarPreciosImpuestosEspecial()
}
precioPantallaClick(){
    self.precioPantalla = true
    self.impuestosIVAIPantalla = false
    self.otrosPantalla = false
    self.update()
    $( ".nav-itemPrecio" ).addClass( "Active" );
}
impuestosIVAIPantallaClick(){
    self.impuestosIVAIPantalla = true
    self.precioPantalla = false
    self.otrosPantalla = false
    self.update()
}
otrosPantallaClick(){
    self.impuestosIVAIPantalla = false
    self.precioPantalla = false
    self.otrosPantalla = true
    self.update()

}
function getMontoTarifa(tipoImpuesto,codigoTarifa,array) {
  return array.filter(
    function(data) {
      return data.tipoImpuesto == tipoImpuesto && data.tarifaIVAI.codigoTarifa == codigoTarifa?data.monto:0
    }
  );
}
function getMontoImpuesto(tipoImpuesto,codigoTarifa,array){
    if(tipoImpuesto.length ==0){
        return 0
    }
    if(tipoImpuesto ==null){
        return 0
    }
    var valor = getMontoTarifa(tipoImpuesto,codigoTarifa,array);
    valor = valor !=null?valor[0]:null
    return valor == null?0:valor.monto
}
/**
*  Mostrar listado datatable Categorias Actimpuestos
**/
function __listadoTarifasByTipoImpuesto(tipoImpuesto,indicador){
   if (typeof tipoImpuesto == 'undefined') {
        return
    }
    if (tipoImpuesto == "" ){
        return
    }
    if (tipoImpuesto == " ") {
        return
    }
    var selector = ""
    $.ajax({
         url: "ListarTarifasByTipoImpuestoAjax.do",
        datatype: "json",
         data: {tipoImpuesto:tipoImpuesto},
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                // Tipo de impuesto 1
                if(indicador ==1 ){
                    self.tarifas1 =  result
                    self.update()
                    self.articulo.impuesto = getMontoImpuesto(self.articulo.tipoImpuesto,$('#codigoTarifa').val(),self.tarifas1.aaData)
                    self.update()
                }
                // Tipo de impuesto 2
                if(indicador ==2 ){
                    self.tarifas2 =  result
                    self.update()
                    self.articulo.impuesto1 = getMontoImpuesto(self.articulo.tipoImpuesto1,$('#codigoTarifa1').val(),self.tarifas1.aaData)
                    self.update();
                }
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}

function actualizarPreciosImpuestosPublico(){
    var ganancia = __valorNumerico($('#gananciaPrecioPublico').val())
    var impuesto   = __valorNumerico($('#impuesto').val())/100
    impuesto = impuesto > 0 ? 1+impuesto:0
    var impuesto1  = __valorNumerico($('#impuesto1').val())/100
    impuesto1 = impuesto1 > 0?1+impuesto1:0
    var costo      = __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioPublico  = ganancia
    self.update()
    //calcular el precio publico
    var resultado = ganancia / 100
    resultado = 1-resultado
    //resultado costo + ganancia
    var total = costo  / resultado
    if(impuesto1 > 0){
      total = total * impuesto1
    } 
    if(impuesto>0){
        total = total * impuesto
    }
    self.articulo.precioPublico = total>0?total:self.articulo.precioPublico
    self.update()
    $('.precioPublico').val(self.articulo.precioPublico)


}

function actualizarPreciosImpuestosMayorista(){
    var ganancia = __valorNumerico($('#gananciaPrecioMayorista').val())
    if(ganancia == 0){
        return
    }
    var impuesto   = __valorNumerico($('#impuesto').val())/100
    impuesto = impuesto > 0 ? 1+impuesto:0
    var impuesto1  = __valorNumerico($('#impuesto1').val())/100
    impuesto1 = impuesto1 > 0?1+impuesto1:0
    var costo      = __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioMayorista  = ganancia
    self.update()
    //calcular el precio publico
    var resultado = ganancia / 100
    resultado = 1-resultado
    //resultado costo + ganancia
    var total = costo  / resultado
    if(impuesto1 > 0){
      total = total * impuesto1
    } 
    if(impuesto>0){
        total = total * impuesto
    }
    self.articulo.precioMayorista = total>0?total:self.articulo.precioMayorista
    self.articulo.precioMayorista =__valorNumerico(self.articulo.precioMayorista)
    self.articulo.precioMayorista =  self.articulo.precioMayorista.toFixed(2) 
    self.update()
    $('.precioMayorista').val(self.articulo.precioMayorista)
}


function actualizarPreciosImpuestosEspecial(){
    var ganancia = __valorNumerico($('#gananciaPrecioEspecial').val())
    if(ganancia == 0){
        return
    }
    var impuesto   = __valorNumerico($('#impuesto').val())/100
    impuesto = impuesto > 0 ? 1+impuesto:0
    var impuesto1  = __valorNumerico($('#impuesto1').val())/100
    impuesto1 = impuesto1 > 0?1+impuesto1:0
    var costo      = __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioEspecial  = ganancia
    self.update()
    //calcular el precio publico
    var resultado = ganancia / 100
    resultado = 1-resultado
    //resultado costo + ganancia
    var total = costo  / resultado
    if(impuesto1 > 0){
      total = total * impuesto1
    } 
    if(impuesto > 0){
        total = total * impuesto
    }
    self.articulo.precioEspecial = total>0?total:self.articulo.precioEspecial
    self.update()
    $('.precioEspecial').val(self.articulo.precioEspecial)
}
/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
 __Consulta(e){
    self.tabprecio = true
    self.tabImpuestos = false
    self.tabOtros = false

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
                            self.tabprecio = true
                            self.tabImpuestos = true
                            self.tabOtros = true

                        }
                        self.tarifas1    = {aaData:[]}
                        self.tarifas2    = {aaData:[]}
                        self.update()
                        $('.codigo').val(modeloTabla.codigo)
                        $('.descripcion').val(modeloTabla.descripcion)
                        $('.precioPublico').val(modeloTabla.precioPublico)
                        $('.impuesto1').val(modeloTabla.impuesto1)
                        $('.impuesto').val(modeloTabla.impuesto)
                        $('.precioPublico').focus().select()
                        __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto,1)
                        __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto1,2)
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
        impuesto1:0,
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
    self.tarifas1    = {aaData:[]}
   self.tarifas2    = {aaData:[]}
    self.update() 
   $('.selectTipoImpuesto').prop("selectedIndex", 0);
   $('.selectTipoImpuesto1').prop("selectedIndex", 0);
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
   $('.impuesto1').val(0)
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
*imprimir el codigo y precio
**/
__Imprimir(){
   if(self.articulo.id == null){
       return
   }
   location.href = "PDFGondolaAjax.do?idArticulo=" + self.articulo.id
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
    _CalculoPrecio()
}
/**
* Porcentaje de ganancia de Precio al Publico
**/
__CalculoGananciaSinPrecioPublico(e){
   __ActualizarPrecios()

}

function __ActualizarPrecios(){
    var ganancia = __valorNumerico($('#gananciaPrecioPublico').val())
    var impuesto   = __valorNumerico($('#impuesto').val())/100
    impuesto = impuesto > 0 ? 1+impuesto:0
    var impuesto1  = __valorNumerico($('#impuesto1').val())/100
    impuesto1 = impuesto1 > 0?1+impuesto1:0
    var costo      = __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioPublico  = ganancia
    self.update()
    //calcular el precio publico
    var resultado = ganancia / 100
    resultado = 1-resultado
    //resultado costo + ganancia
    var total = costo  / resultado
    if(impuesto1 > 0){
      total = total * impuesto1
    } 
    if(impuesto>0){
        total = total * impuesto
    }
    self.articulo.precioPublico = total
    self.update()
    $('.precioPublico').val(self.articulo.precioPublico)

}
/**
* Asigna el impuesto 13 cuando es valor igual 01
**/
__asignarImpuesto(){
    if($('.selectTipoImpuesto').val()=="01"){
        self.articulo.tipoImpuesto ="01"
        self.articulo.impuesto = 0
    }else{
        $('.impuesto').val(null)
        self.articulo.impuesto = 0
        self.articulo.tipoImpuesto =$('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
    } 
    self.tarifas1  = {aaData:[]}
    self.update()
     __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto,1)
    __ActualizarPrecios()      
}
/**
* Asignar el Impuesto
**/
__asignarImpuesto1(){
    $('.impuesto1').val(null)
    self.articulo.impuesto1 = 0
    self.articulo.tipoImpuesto1 =$('#tipoImpuesto1').val() == "Sin impuesto"?"":$('#tipoImpuesto1').val()
    self.tarifas2  = {aaData:[]}
    self.update()
     __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto1,2)
    __ActualizarPrecios()

}

__CalculoImpuesto1(e){
__ActualizarPrecios()
}
/**
* Porcentaje de ganancia de Precio al Publico
**/
__CalculoGananciaPublico(e){
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var impuesto1 =  __valorNumerico($('#impuesto1').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico    =  __valorNumerico($('#precioPublico').val())
    self.articulo.gananciaPrecioPublico    = precioPublico >0?_porcentajeGanancia(costo,impuesto,impuesto1,precioPublico):0
    self.articulo.precioPublico = precioPublico
    self.update()
}

/**
* Actualizar costo
**/
__ActualizarPreciosCosto(e){
    var impuesto  =  __valorNumerico($('#impuesto').val())/100
    var impuesto1 =  __valorNumerico($('#impuesto1').val())/100
    var costo     =  __valorNumerico($('#costo').val())
    var gananciaPublica = __valorNumerico($('#gananciaPrecioPublico').val())/100
    var precioPublico =  __valorNumerico($('#precioPublico').val())
     //  Costo , ganancia digitada , impuestos digitados  Altera el precio
    if(gananciaPublica > 0){
       var resultadoPorcentajeGanancia = 1-gananciaPublica
       var resultadoImpuesto = 0
       precioPublico =  costo /resultadoPorcentajeGanancia
       if(impuesto1 > 0){
         resultadoImpuesto =  impuesto1 + 1 
         precioPublico = precioPublico * resultadoImpuesto  
       }
       if(impuesto > 0){
        resultadoImpuesto =  impuesto + 1 
        precioPublico = precioPublico * resultadoImpuesto  
       }
       self.articulo.precioPublico = precioPublico
       self.articulo.costo = costo
       self.update()     
    }else{
        var total = 0
        var totalGanancia = 0
        //Ganancia es cero
        if(precioPublico > 0){
           var resultado = impuesto + impuesto1 ;
           resultado = resultado / 100
           resultado = resultado > 0?resultado + 1:0
           // se le quita impuestos
            if(resultado > 0){
               total = precioPublico / resultado
            }else{
              total = precioPublico  
            }
            if(total > costo){
                totalGanancia = costo / total 
                totalGanancia = 1- totalGanancia
                self.articulo.gananciaPrecioPublico    =  totalGanancia
            }else{
                self.articulo.gananciaPrecioPublico    = 0  
            }
 
        }
    }
}
/**
* calculo de Precio
**/
function _CalculoPrecio(){
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var impuesto1 =  __valorNumerico($('#impuesto1').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico    =  __valorNumerico($('#precioPublico').val())
    self.articulo.gananciaPrecioPublico    = precioPublico >0?_porcentajeGanancia(costo,impuesto,impuesto1,precioPublico):0
    self.articulo.precioPublico = _PrecioPublicoConGanancia(costo,impuesto,impuesto1,self.articulo.gananciaPrecioPublico)
    self.update()
 
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
                 $('.selectCategoria').selectpicker(
                    {
                         style: 'btn-info',
                        size:10,
                        liveSearch: true
                    }
                );
                $('.selectCategoria').selectpicker('refresh');
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
                 $('.selectMarca').selectpicker(
                    {
                         style: 'btn-info',
                        size:10,
                        liveSearch: true
                    }
                );
                $('.selectMarca').selectpicker('refresh');
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
        global: false,
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
        codigo: '02',
        descripcion:$.i18n.prop("tipo.impuesto.consumo")
     });
    self.impuestos.push({
        codigo: '07',
        descripcion:$.i18n.prop("tipo.impuesto.servicio")
     });
     self.impuestos.push({
        codigo: '06',
        descripcion:$.i18n.prop("tipo.impuesto.tabaco")
     });
    self.impuestos.push({
        codigo: '12',
        descripcion:$.i18n.prop("tipo.impuesto.cemento")
     });
    self.impuestos.push({
        codigo: '99',
        descripcion:$.i18n.prop("tipo.impuesto.otros")
     });
   
     self.update();
}
/**
* Combo para verificar si es contabilizado en el inventario o no
**/
function __Impuestos1(){
    self.impuestos1 =[]
    self.update()
     self.impuestos1.push({
        codigo: "",
        descripcion:"Sin impuesto"
     });

    self.impuestos1.push({
        codigo: '02',
        descripcion:$.i18n.prop("tipo.impuesto.consumo")
     });
    self.impuestos1.push({
        codigo: '07',
        descripcion:$.i18n.prop("tipo.impuesto.servicio")
     });
     self.impuestos1.push({
        codigo: '06',
        descripcion:$.i18n.prop("tipo.impuesto.tabaco")
     });
    self.impuestos1.push({
        codigo: '12',
        descripcion:$.i18n.prop("tipo.impuesto.cemento")
     });
    self.impuestos1.push({
        codigo: '99',
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
     if(validarPrecios()){
         return
     }
        var AplicoImpuesto1 = false
        var AplicoImpuesto2 = false
       if ($("#formulario").valid()) {
        var tipo = $('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
        if(tipo == "07"){
                var baseImponible = $('#baseImponible').val()
                if(baseImponible == 0){
                   mensajeError("Debe actualizar la base imponible debe ser Activo")
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
    if(validarPrecios()){
         return
     }
    var AplicoImpuesto1 = false
    var AplicoImpuesto2 = false
    if ($("#formulario").valid()) {
                var tipo = $('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
        if(tipo == "07"){
                var baseImponible = $('#baseImponible').val()
                if(baseImponible == 0){
                   mensajeError("Debe actualizar la base imponible debe ser Activo")
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
    
}

function validarPrecios(){
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var impuesto1 =  __valorNumerico($('#impuesto1').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico    =  __valorNumerico($('#precioPublico').val())
    var resultadoImpuesto =  impuesto + impuesto1
    resultadoImpuesto =resultadoImpuesto /100
    resultadoImpuesto = resultadoImpuesto + 1
    var total = precioPublico / resultadoImpuesto
    if(precioPublico > 0 && resultadoImpuesto > 0){
        if(total < costo ){
            swal({
                title: '',
                text: 'El Precio Publico es menor al Costo',
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
*  Sumar
**/
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