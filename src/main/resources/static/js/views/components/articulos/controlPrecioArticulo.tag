<control-precio>

			
<!-- Titulos -->
    <div  class="row "  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>Productos con cambio de precio</h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"> </div>
    </div>
  
				
 <!-- Inicio Filtros-->
    <div>
        <div class="row" show={mostrarListado}>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="knob-label" >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                    <div  class="form-group input-group date datepickerFechaInicial" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control fechaInicio" id="fechaInicio"  name= "fechaInicio" readonly >
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>	                             
                                </div>  
                            </div>             
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label class="knob-label" >{$.i18n.prop("fecha.final")} <span class="requeridoDato">*</span></label>
                                        <div  class="form-group input-group date datepickerFechaFinal" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaFinal" id="fechaFinal"  name= "fechaFinal" readonly>
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>	                             
                                    </div>
                                </div>  
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>{$.i18n.prop("articulo.articulo")} </label>  
                                    <input onclick = {__ListaDecodigos}  id="codigoArticulo" name="codigoArticulo" class="form-control" type="text" value="{articulo.descripcion}" placeholder="XXXXXXXXXXX" readonly/>
                                    <input  type="hidden"   class="form-control idArticulo" id="idArticulo" name="idArticulo" value="{articulo.codigo}"/>
                                </div>  
                            </div>                      
                        </div>
                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
            	<button onclick ={__limpiar} show={mostrarFiltros} class="btn btn-warning btnLimpiarFiltros" title="LimpiarCampos" type="button"><i id="clear-filters" class="fa fa-eraser clear-filters"></i></button>            
            </div>
        </div>
    </div>    
<!-- Fin Filtros-->

    
    <br>
  <!-- Listado  -->
    <div classs="contenedor-listar "  show={mostrarListado} >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 ">
                <div class="box">
                    <div class="box-body">
                        <div class="planel-body" >
                            <div class="row" >        
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12" >
                                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar "   cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th class="table-header">{$.i18n.prop("controlPrecio.fecha.creacion")}  </th>
                                                <th class="table-header">{$.i18n.prop("controlPrecio.responsable.creacion")}    </th>
                                                <th class="table-header">Codigo    </th>
                                                <th class="table-header">{$.i18n.prop("controlPrecio.descripcion")}    </th>
                                                <th class="table-header">{$.i18n.prop("cotrolPrecio.precio.anterior")} </th>
                                                <th class="table-header">{$.i18n.prop("controlPrecio.precio.nuevo")}</th>
                                                <th class="table-header">{$.i18n.prop("controlPrecio.diferencia")}</th>
                                                <th class="table-header">{$.i18n.prop("controlPrecio.compra")}</th>
                                                
                                               
                                            </tr>
                                        </thead>
                                        <tfoot style="display: table-header-group;">
                                            <tr>
                                                <th >{$.i18n.prop("controlPrecio.fecha.creacion")}  </th>
                                                <th >{$.i18n.prop("controlPrecio.responsable.creacion")}    </th>
                                                <th >Codigo </th>
                                                <th >{$.i18n.prop("controlPrecio.descripcion")}    </th>
                                                <th >{$.i18n.prop("cotrolPrecio.precio.anterior")} </th>
                                                <th >{$.i18n.prop("controlPrecio.precio.nuevo")}</th>
                                                <th >{$.i18n.prop("controlPrecio.diferencia")}</th>
                                                <th >{$.i18n.prop("controlPrecio.compra")}</th>
                                               
                                                
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>   
                            </div>   
                        </div>    
                    </div>
                </div>
            </div>
            <div class="col-md-1 col-lg-1 "> </div>
        </div>
    </div>
    <!-- Fin del Listado -->

<!--Modal mostrar Articulos de la empresa -->
<div id='modalInventario' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border encabezado-pantalla " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("articulo.listar")} </h4>
            </div>
            <div class="modal-body">
                <form id="formularioParametros" name ="formularioParametros" >
                    <div class="row">
                        <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <label  >{$.i18n.prop("articulo.codigo")}  </label>
                            <input type="text" class="form-control codigoArt" id="codigoArt" name="codigoArt"  onkeypress={__ConsultarProductosCod} >
                        </div>
                        <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <label  >{$.i18n.prop("articulo.descripcion")}</label>
                            <input type="text" class="form-control descArticulo "   id="descArticulo" name="descArticulo" onkeypress={__ConsultarProductosDesc}>
                        </div>
                    </div> 
                </form>    
                <br>                   
                <table id="tableListarArticulos" class="display table responsive table-hover nowrap table-condensed tableListarArticulos " cellspacing="0" width="100%">
                    <thead>
                         <th class="table-header">{$.i18n.prop("articulo.codigo")}        </th>
                        <th class="table-header">{$.i18n.prop("articulo.descripcion")}   </th>
                        <th class="table-header">{$.i18n.prop("inventario.cantidad")}    </th>
                        <th class="table-header">{$.i18n.prop("articulo.precioPublico")} </th>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}       </th>

                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th >{$.i18n.prop("articulo.codigo")}         </th>
                            <th >{$.i18n.prop("articulo.descripcion")}   </th>
                            <th >{$.i18n.prop("inventario.cantidad")}    </th>
                            <th >{$.i18n.prop("articulo.precioPublico")} </th>
                            <th >                                        </th>
                        </tr>
                    </tfoot>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->
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
    var self = this
    self.idiomaDataTable      = []         // idioma de la datatable nuevo
    self.formato_tabla        = []         // Formato del Listado de la Tabla 
    self.kardex               = {data:[]}
    self.articulos            = {data:[]}
    self.articulo = {
        id:null,
        descripcion:""
    }
    self.mostrarListado       = true
    
    self.on('mount',function(){
        $("#filtros").validate(reglasDeValidacionParametros());
       
        __InicializarTabla('.tableListar')
        
        
        agregarInputsCombos()
        limpiarFiltros()
         

        window.addEventListener( "keydown", function(evento){
                $(".errorServerSideJgrid").remove();
            }, false );
    })


__Busqueda(){
     $("#filtros").validate(reglasDeValidacionParametros());
     if ($("#filtros").valid()) {
        var formulario = $("#filtros").serialize();
        $("#tableListar").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListar')  
        listadoControlPrecios();
     }
}
function listadoControlPrecios() {
    self.controlPrecios = {aaData:[]}
    self.detail = []
    self.update()
    var fechaFinal =  $('.fechaFinal').val()
   
    var fechaInicial = $('.fechaInicio').val()

    var idArticulo = $('.idArticulo').val()
	obtenerControlPrecio(fechaInicial,fechaFinal,idArticulo)
    .then(res => {
        self.controlPrecios.aaData = res
        $.each(self.controlPrecios.aaData , function( index, modeloTabla ) {
            self.detail.push(modeloTabla);
             
          
            self.mostrarDetalles = true;
        })
        
        self.update()

        unBlockUIStop();
        if(typeof self.detail !== "undefined" && self.detail.length > 0 ){
          var informacion = _informacionData()
            loadListar(".tableListar",idioma_espanol,informacion,self.detail)
            agregarInputsCombos_Kardex();
            ActivarEventoFiltro(".tableListar")
   
        }
      
     })
     .catch(err=>{
         unBlockUIStop();
         console.error(err)
     })
	



    
}


/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
     	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
    })
} 

/**
* Camps requeridos
**/
var reglasDeValidacionParametros = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			fechaInicio : {
				required : true,
			},
			fechaFinal : {
				required : true,
			}
            
                        
		},
		ignore : []

	});
	return validationOptions;
};
/*
 * Muestra los filtros avanzados
 */
 __mostrarFiltros(){
    if(self.mostrarFiltros){
        self.mostrarFiltros = false;
        self.valorMarginBottom  = '10px'
    }else{
        self.mostrarFiltros = true;
        self.valorMarginBottom  = '0px'
    }
    self.update();
}

/**
* limpiar los filtros
**/
__limpiar(){
    limpiarFiltros()
}

function limpiarFiltros(){
    $('.fechaInicio').val(null)
    $('.fechaFinal').val(null)
    $('.datepickerFechaFinal').datepicker(
        {
            format: 'yyyy-mm-dd',
            todayHighlight:true,
        }
    );
    $('.datepickerFechaInicial').datepicker(
        {
           format: 'yyyy-mm-dd',
           todayHighlight:true,
        }
    );
    $('.articulo').val(null)
    $('.idArticulo').val(null)
    self.articulo = {}
    self.update()

}
/**
*  Busqueda de la informacion por rango de fechas
**/

/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Kardex(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
    })
} 

 
/**
* Definicion de la tabla articulos 
**/
function _informacionData(){
 var resltado =  [	
     {'data' : 'created_atSTR'         ,"name":"created_atSTR" ,"title" : $.i18n.prop("controlPrecio.responsable.creacion") ,"autoWidth":false},
     {'data' : 'responbleAceptarPrecio.nombreUsuario' ,"name":"responbleAceptarPrecio.nombreUsuario"  ,"title" : $.i18n.prop("controlPrecio.fecha.creacion")  ,"autoWidth":false},
     
     {'data' : 'codigo',"name":"codigo","title" : 'Codigo'  ,"autoWidth":false},
     {'data' : 'descripcion',"name":"descripcion","title" : $.i18n.prop("controlPrecio.descripcion")  ,"autoWidth":false},
     {'data' : 'precioPublicoAnterior' ,"name":"precioPublicoAnterior","title" : $.i18n.prop("cotrolPrecio.precio.anterior")  ,"autoWidth":false},
     {'data' : 'precioPublicoNuevo'  ,"name":"precioPublicoNuevo","title" : $.i18n.prop("controlPrecio.precio.nuevo")   ,"autoWidth":false},
     {'data' : 'diferenciaSTR' ,"name":"diferenciaSTR"   ,"title" : $.i18n.prop("controlPrecio.diferencia"),"autoWidth":false,
                                          "render":function(diferenciaSTR,type, row){
                                              
                                               return  verprecioColor(row.diferencia,diferenciaSTR);
                                            }
                                        },
     
     {'data' : 'consecutivo',"name":"consecutivo" ,"title" : $.i18n.prop("controlPrecio.compra"),"autoWidth":false},
    
                                
                                    ];
    return resltado;
    
}
function verprecioColor(precio,diferenciaSTR){
var resultado = precio > 0 ? true:false;
                                                        
return   resultado == true ? "<span class= 'colorPositivoControlPrecio'>"+ diferenciaSTR+"</span>":"<span class= 'colorNegaticoControlPrecio'>"+ diferenciaSTR+"</span>"

}
/**
 * Opciones listado de los clientes
 */
function __Opciones(id, type, row) {
  //  let menu = '<div class="dropdown">'
  //  menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
  //  menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>'
  //  menu += '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
  //  menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
  //  menu += '<li><a href="#"  title="Cambia al precio anterior" class="  btnReversar" >Reversar Precio</a></li>'
  
  //  menu += "</ul></div>"
    return "";
}



/**
 * Listar codigos  llamado del modal para presentar los articulos
 **/   
 __ListaDecodigos(){
    $('.descArticulo').val(null)
    $('.codigoArt').val(null)
    $(".tableListarArticulos").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
    $('#modalInventario').modal('show')    
    
 }

 /**
* consultando por descripcion
**/
__ConsultarProductosDesc(e){
 if (e.keyCode != 13) {
        return;
    } 
 __ListaDeArticulosPorDescripcion($("#codigoArt").val(),e.currentTarget.value)   
}    

/**
*Consultando por codigo
**/
__ConsultarProductosCod(e){
 if (e.keyCode != 13) {
        return;
    } 
 __ListaDeArticulosPorDescripcion(e.currentTarget.value,$("#descArticulo").val())   
}   

/**
* mostrar la lista de articulos de la empresa
**/
function __ListaDeArticulosPorDescripcion(){
    if($('#codigoArt').val() =='' && $('#descArticulo').val() =='' ){
        return
    }
    $(".tableListarArticulos").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
    var formulario = $('#formularioParametros').serialize();
    $.ajax({
        url: 'ListarPorDescripcionCodigoArticuloAjax.do',
        datatype: "json",
        method:"GET",
        data :formulario,
        success: function (result) {
            if(result.aaData.length > 0){
                _informacionData_Articulo()
                self.articulos.data           = result.aaData
                self.update()
                loadListar(".tableListarArticulos",idioma_espanol,self.informacion_tabla_articulo,self.articulos.data)
                agregarInputsCombos_Articulo()
                __agregarArticulos()
                ActivarEventoFiltro(".tableListarArticulos")
             
                
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

/**
* Definicion de la tabla articulos 
**/
function _informacionData_Articulo(){
   self.informacion_tabla_articulo = [	{'data' : 'codigo'         ,"name":"codigo"          ,"title" : $.i18n.prop("articulo.codigo")       ,"autoWidth":false},
                                        {'data' : 'descripcion'    ,"name":"descripcion"     ,"title" : $.i18n.prop("articulo.descripcion")  ,"autoWidth":false},
                                        {'data' : 'cantidad'       ,"name":"cantidad"        ,"title" : $.i18n.prop("inventario.cantidad")   ,"autoWidth":false},
                                        {'data' : 'precioPublico'  ,"name":"precioPublico"   ,"title" : $.i18n.prop("articulo.precioPublico"),"autoWidth":false,
                                          "render":function(precioPublico,type, row){
                                              var resultado = formatoDecimales(__valorNumerico(precioPublico))
                                               return  resultado;
                                            }
                                        },
                                        {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
                                            "render":function(id,type, row){
                                                    return __OpcionesArticulos(id,type,row);
                                                }	 
                                        },
                              ];
    
 self.update()        
}


/**
* Opciones del modal de articulos
*/
function __OpcionesArticulos(){
  var agregar  = '<a href="#"   class="btn btnAgregar btn-success form-control" title="Seleccionar" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;

}

/**
* Agregar codigos a la compra desde modal de articulos
**/
function __agregarArticulos() {
     $('#tableListarArticulos').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListarArticulos').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	     }
        self.articulo = data;
        
        self.update();  
	       $('#modalInventario').modal('hide')
    });
}


/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Articulo(){
     // Agregar los input de busqueda 
    $('.tableListarArticulos tfoot th').each( function (e) {
        var title = $('.tableListarArticulos thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 4    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
</script>
</control-precio>