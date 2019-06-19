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
                    <button  onclick={__Imprimir} title="Imprimir codigo  y precio" class="btn-imprimirCambioPrecio btn-print pull-right" > &nbsp {$.i18n.prop("btn.imprimir")}</button>
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{articulo.id}">
                        <input type="hidden" id="precioMayorista" name="precioMayorista" value="{articulo.precioMayorista}"  >
                        <input type="hidden" id="gananciaPrecioMayorista" name="gananciaPrecioMayorista" value="{articulo.gananciaPrecioMayorista}">
                        <input type="hidden"  id="gananciaPrecioEspecial" name="gananciaPrecioEspecial" value="{articulo.gananciaPrecioEspecial}"  >
                        <input type="hidden"  id="precioEspecial" name="precioEspecial" value="{articulo.precioEspecial}" >
                        <input type="hidden"  id="maximo" name="maximo" value="{articulo.maximo}"  >
                        <input type="hidden"  id="minimo" name="minimo" value="{articulo.minimo}"  >
                        <input type="hidden"  id="cantidad" name="cantidad" value="{articulo.cantidad}"  >
                        <input type="hidden"  id="prioridad" name="prioridad" value="{articulo.prioridad}"  >
                        <input type="hidden"  id="pesoTransporte" name="pesoTransporte" value="{articulo.pesoTransporte}"  >
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.codigo")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="campoNumerico codigo" id="codigo" name="codigo" value="{articulo.codigo}"  onkeypress={__Consulta} autofocus="autofocus">
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.descripcion")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="campo descripcion" id="descripcion" name="descripcion" value="{articulo.descripcion}"  >
                            </div>
                             <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.categoria")}  <span class="requeridoDato">*</span></label>
                                 <select  class="campo selectCategoria"   name="categoria"  data-live-search="true">
                                    <option  each={categorias.aaData}  data-tokens ={descripcion} value="{id}" selected="{articulo.categoria.id ==id?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales">{$.i18n.prop("articulo.tipoCodigo")}<span class="requeridoDato">*</span></label>
                                <select  class="campo selectTipoCodigo" id="tipoCodigo" name="tipoCodigo"  >
                                    <option  each={tipoCodigos}  value="{codigo}" selected="{articulo.tipoCodigo ==codigo?true:false}"  >{descripcion}</option>
                                </select>
                            </div>

                        </div>                        
                      
                        <div class="row">
                            <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales">{$.i18n.prop("articulo.tipoImpuesto")}<span class="requeridoDato">*</span></label>
                                <select onchange= {__asignarImpuesto} class="campo selectTipoImpuesto" id="tipoImpuesto" name="tipoImpuesto"  >
                                   
                                    <option  each={impuestos}  value="{codigo}" selected="{articulo.tipoImpuesto ==codigo?true:false}"  >{descripcion}</option>
                                </select>
                            </div>

                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.impuesto")}  </label>
                                <input type="number" step="any" class="campoNumerico impuesto" id="impuesto" name="impuesto" value="{articulo.impuesto}"  onkeyup ={__ActualizarPreciosImpuestos}>
                            </div>
            
                            <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales">{$.i18n.prop("articulo.tipoImpuesto1")}</label>
                                <select onchange= {__asignarImpuesto1} class="campo selectTipoImpuesto1" id="tipoImpuesto1" name="tipoImpuesto1"  >
                                    <option  each={impuestos1}  value="{codigo}" selected="{articulo.tipoImpuesto1 ==codigo?true:false}"  >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label  class="tamanoLetraTotales">{$.i18n.prop("articulo.impuesto1")}  </label>
                                <input type="number" step="any" class=" impuesto1 campoNumerico" id="impuesto1" name="impuesto1" value="{articulo.impuesto1}"  onkeyup ={__CalculoImpuesto1}>
                            </div>
                        </div>    

                        <div class="row">
                          
                            
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales"  >{$.i18n.prop("articulo.costo")} </label>
                                <input type="number" step="any" class="campoNumerico costo" id="costo" name="costo" value="{articulo.costo}"  onkeyup ={__ActualizarPreciosCosto}>
                            </div>

                           <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                 <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.gananciaPrecioPublico")}%  </label>
                                <input type="number" step="any" class="campoNumerico gananciaPrecioPublico" id="gananciaPrecioPublico" name="gananciaPrecioPublico"  value="{articulo.gananciaPrecioPublico}"  onkeyup ={__CalculoGananciaSinPrecioPublico}>
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.precioPublico")}  <span class="requeridoDato">*</span></label>
                                <input type="number" step="any" class="campoNumerico precioPublico" id="precioPublico" name="precioPublico" onkeyup ={__CalculoGananciaPublico} value="{articulo.precioPublico}"  >
                            </div>
                           


                        </div>    
                        <div class="row">
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.unidadMedida")}  <span class="requeridoDato">*</span></label>
                                 <select  class="campo selecTipoUnidad has-success" name="unidadMedida" >
                                    <option   each={tipoUnidades.aaData}  value="{codigo}"  selected="{articulo.unidadMedida ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>

                             <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales">{$.i18n.prop("articulo.contable")}</label>
                                <select  class="campo" id="contable" name="contable" >
                                    <option  each={contables}  value="{codigo}" selected="{articulo.contable ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>

                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.marca")}  <span class="requeridoDato">*</span></label>
                                 <select  class="campo selectMarca"  name="marca" data-live-search="true">
                                    <option  each={marcas.aaData}  value="{id}" data-tokens ={descripcion} selected="{articulo.marca.id ==id?true:false}"  >{descripcion}</option>
                                </select>
                            </div>
         
                           <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label  class="tamanoLetraTotales">{$.i18n.prop("articulo.pesoTransporte")} </label>
                                <input type="number" step="any" class="campoNumerico pesoTransporte" id="pesoTransporte" name="pesoTransporte" value="{articulo.pesoTransporte}"  >
                            </div>
         
                        </div>
                        <div class="row">
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label  class="tamanoLetraTotales">{$.i18n.prop("articulo.consecutivoCompra")} </label>
                                <input type="text" step="any" class="campo form-control "  value="{articulo.consecutivoCompra}"  readonly>
                            </div>
 
         
                           <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.fechaUltimaCompra")} </label>
                                <input type="text" step="any" class="campo form-control "  value="{articulo.fechaUltimaCompraSTR}"  readonly>
                            </div>
 

                           <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.updated_at")}  </label>
                                <input type="text" class="form-control campo"  value="{articulo.updated_atSTR}" readonly >
                            </div>
                                <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
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
        .campoNumerico {
            display: block;
            width: 100%;
            height: 45px;
            padding: 8px 18px;
            font-size: 10px;
            line-height: 1.42857143;
            color:red;
            
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
            font: 20px verdana, arial, helvetica, sans-serif;
            margin: 2px 0;
            padding: 1px 2px;
            overflow: visible;
            font-size: 40px;
            color: #ec351f;
    font-weight: bold;
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
    __Impuestos1() 
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
 __Consulta(e){
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
                         $('.precioPublico').focus().select()
                        
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
        self.articulo.impuesto = 13
        self.update()
    }else{
        $('.impuesto').val(null)
        self.articulo.impuesto = 0
        self.articulo.tipoImpuesto =$('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
        self.update()
    } 
    __ActualizarPrecios()      
}
/**
* Asignar el Impuesto
**/
__asignarImpuesto1(){
    $('.impuesto1').val(null)
    self.articulo.impuesto1 = 0
    self.articulo.tipoImpuesto1 =$('#tipoImpuesto1').val() == "Sin impuesto"?"":$('#tipoImpuesto1').val()
    self.update()
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
        codigo: '98',
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
     if(validarPrecios()){
         return
     }
        var AplicoImpuesto1 = false
        var AplicoImpuesto2 = false
       if ($("#formulario").valid()) {
        var tipo = $('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
        if (tipo !=""){
            if($('#impuesto').val()==0){
                mensajeError($.i18n.prop("error.articulo.indicar.tipo.impuesto"))
                return 
            }else{
                AplicoImpuesto1 = true
            }
        }else{
            if($('#impuesto').val()>0){
                mensajeError($.i18n.prop("error.articulo.no.tipo.impuesto"))
                return 
            }
        }
        tipo = $('#tipoImpuesto1').val() == "Sin impuesto"?"":$('#tipoImpuesto1').val()
        if (tipo !=""){
            if($('#impuesto1').val()==0){
                mensajeError($.i18n.prop("error.articulo.indicar.tipo.impuesto1"))
                return 
            }else{
                AplicoImpuesto2 = true
            }
        }else{
            if($('#impuesto1').val()>0){
                mensajeError($.i18n.prop("error.articulo.no.tipo.impuesto1"))
                return 
            }
        }
        if(AplicoImpuesto2 == true && AplicoImpuesto1 == false){
            mensajeError($.i18n.prop("error.articulo.no.impuesto1"))
            return 

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
        if (tipo !=""){
            if($('#impuesto').val()==0){
                mensajeError($.i18n.prop("error.articulo.indicar.tipo.impuesto"))
                return 
            }else{
                AplicoImpuesto1 = true
            }
        }else{
            if($('#impuesto').val()>0){
                mensajeError($.i18n.prop("error.articulo.no.tipo.impuesto"))
                return 
            }
        }
        tipo = $('#tipoImpuesto1').val() == "Sin impuesto"?"":$('#tipoImpuesto1').val()
        if (tipo !=""){
            if($('#impuesto1').val()==0){
                mensajeError($.i18n.prop("error.articulo.indicar.tipo.impuesto1"))
                return 
            }else{
                AplicoImpuesto2 = true
            }
        }else{
            if($('#impuesto1').val()>0){
                mensajeError($.i18n.prop("error.articulo.no.tipo.impuesto1"))
                return 
            }
        }
        if(AplicoImpuesto2 == true && AplicoImpuesto1 == false){
            mensajeError($.i18n.prop("error.articulo.no.impuesto1"))
            return 

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