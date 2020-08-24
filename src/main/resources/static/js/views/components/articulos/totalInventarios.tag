<total-inventarios>

<div class='scrlollT'>
  
    <!-- Titulos -->
    <div  id="titulo-encabezado"  >
        <div>
		    <h1><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("titulo.inventario.totales")} </h1>
		</div>
        <div id="totalsGenerales">
                <div>
               	    <div >
                         <span>Total Costo   </span>  
                        <input type="text"  value={totalCostoSTR} readonly >
                     </div>
                </div>
                <div>
               	    <div >
                         <span>Total Venta Publico   </span>  
                        <input type="text"  value={totalPublicoSTR} readonly >
                     </div>
                </div>
                <div>
               	    <div >
                         <span>Total Venta Especial   </span>  
                        <input type="text"  value={totalEspecialSTR} readonly >
                     </div>
                </div>

	    </div>
    </div>

    
    <!-- Inicio Filtros-->
    <div>
        <div class= 'row'>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                <form id="filtros" name="filtros">       
                    <div class= "row">
                        <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3">
                            <div class="form-group">
                                <label class="knob-label" >Fecha <span class="requeridoDato">*</span></label>
                                <div  class="form-group input-group date datepickerFechaInicial" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                    <input type="text" class="form-control fechaInicial " id="fechaInicial"  name= "fechaInicial" readonly>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>	                             
                            </div>  
                        </div>             
                       

                    </div>    
                </form>
            </div>
        </div>
        <div class= 'row'>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-right">
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada formatoBoton" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
            </div>
        </div>
    </div>    
   <!-- Fin Filtros-->
    <div >
        <div  show={mostrarDescarga == true}>
            <a show = {mostrarDescargas == true} class="fa fa-download btn btn-success" target="_blank" title="Descargar el inventario existente" href="DescargarInventarioAjax.do?fechaInicio={fechaInicio}"> Todo el Inventario</a> &nbsp       
            <a show = {mostrarDescargas == true} class="fa fa-download btn btn-primary" target="_blank" title="Descargar el inventario con las existencias para contabilizar contra el inventario fisico" href="DescargarInventarioExistenciasAjax.do?fechaInicio={fechaInicio}"> Inventario con Existencias</a>        
        </div>
    </div>

            <div class="box">
                <div class="box-body">
                    <div class="planel-body" >
                        <div class="row " >        
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12" >
                                <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar"   cellspacing="0" width="98.5%">
                                 <thead>
                                <tr>
                                    <th style="width:10%" class="table-header"  th:text="#{articulo.categoria}" >Categoria   </th>
                                    <th class="table-header" style ="width:5%" >Codigo</th>
                                    <th style="width:35%"  class="table-header" >Descripcion</th>
                                    <th style="width:9%"  class="table-header" >Costo</th>
                                    <th style="width:7%"  class="table-header" >IVAI</th>
                                    <th style="width:9%"  class="table-header" >Precio</th>
                                    <th style="width:6%"  class="table-header" >Cantidad</th>
                                    <th class="table-header" style ="width:6%">Contable</th>
                                    <th style="width:8%"  class="table-header" >Estado </th>
                                </tr>
                                </thead>
                                <tfoot style="display: table-header-group;">
                                <tr>
                                    <th style="width:10%" >Categoria       </th>
                                    <th>Codigo          </th>
                                    <th style="width:35%"  >Descripcion     </th>
                                    <th style="width:9%" >Costo           </th>
                                    <th style="width:7%" >Impuesto        </th>
                                    <th style="width:9%"  >Precio          </th>
                                    <th style="width:6%" >Cantidad        </th>
                                    <th>Contable        </th>
                                    <th style="width:8%" >Estado          </th>
                                </tr>
                                </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                 </div>
            </div>
        </div> 
<style type="text/css">
.titulo{
    font-size: 20px;
}
  .formatoBoton{
      margin-right: 19px;
  }
  .scrlollT{
	overflow: scroll;
	}
	html {
         scroll-behavior: smooth;
    }
	
</style>

<script>
	self = this;
    self.fechaInicio = ''
    self.totalCosto = 0;
    self.totalPublico = 0;
    self.totalEspecial = 0;
    self.totalCostoSTR = 0;
    self.totalPublicoSTR = 0;
    self.totalEspecialSTR = 0;

	self.mostrarDetalle        = false
	self.inventario ={				
		totalCostoSTR:"0",
		totalVentasSTR:"0",
	}
    self.valorMarginBottom  = '10px'		

	self.mostrarDescarga = false
		self.empresa = {
			id:0
	}
    self.mostrarDescargas = false
	//Se cargan al montar el tag
	self.on('mount',function(){
         agregarInputsCombos();
        __InformacionDataTable()
        __InicializarTabla('.tableListar')
         $("#filtros").validate(reglasDeValidacion());
         $('.datepickerFechaInicial').datepicker(
        {
            format: 'yyyy-mm-dd',
            todayHighlight:true,
        }
        );
		_Empresa();
	})
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			fechaInicial : {
				required : true,
			},
		},ignore : []

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
*  Busqueda de la informacion por rango de fechas
**/
__Busqueda(){
    self.mostrarDescargas = false
    self.update();
 
    var parametros = {
       	fechaInicio:$('#fechaInicial').val(),
    };
     if ($("#filtros").valid()) {
        __ConsultaArticulos()
     }
}

/**
*  Busqueda de la informacion por rango de fechas
**/
function __ConsultaArticulos(){
    var parametros = {
       	fechaInicio:$('#fechaInicial').val(),
    };
     self.fechaInicio = $('#fechaInicial').val()
     self.totalCostoSTR = ''
    self.totalPublicoSTR = ''
    self.totalEspecialSTR = ''
    self.totalCosto = 0;
    self.totalPublico = 0;
    self.totalEspecial = 0;
    self.update()
     if ($("#filtros").valid()) {
        $.ajax({
                url: "ListarArticulosActivosFechaAjax.do",
                datatype: "json",
                data:parametros ,
                method:"POST",
                success: function (result) {
                    if(result.aaData.length > 0){
                        self.mostrarDescargas = true
                        self.update();

                        TotalesGenerales(result.aaData);
                        __InformacionDataTable();
                        loadListar(".tableListar",idioma_espanol,self.formato_tabla,result.aaData)
                        agregarInputsCombos()
                        ActivarEventoFiltro(".tableListar")
                    }else{
                        __InformacionDataTable();
                        agregarInputsCombos()
                    } 
                },
                error: function (xhr, status) {
                    console.log(xhr);
                    mensajeErrorServidor(xhr, status);
                }
        });
     }
}


function TotalesGenerales(data){
    self.totalCostoSTR = ''
    self.totalPublicoSTR = ''
    self.totalEspecialSTR = ''
    self.totalCosto = 0;
    self.totalPublico = 0;
    self.totalEspecial = 0;
    self.update()
    for(var i in data) { 
        self.totalCosto   += data[i].costo * data[i].cantidadActualReal;
        self.totalPublico +=  data[i].precioPublico * data[i].cantidadActualReal;
        self.totalEspecial +=  data[i].precioEspecial * data[i].cantidadActualReal;
     }
     self.totalCostoSTR = formatoDecimales(self.totalCosto,2)
     self.totalPublicoSTR = formatoDecimales(self.totalPublico,2)
     self.totalEspecialSTR = formatoDecimales(self.totalEspecial,2)
     
     self.update()
}
/**
* Consultar la empresa
**/
function _Empresa(){
     $.ajax({
        url: "ParametrosEmpresaAjax.do",
        datatype: "json",
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.empresa =   modeloTabla
					   if (self.empresa.descargarInventario == 1) {
                          self.mostrarDescarga = true
					   }
                       self.update()
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
 * Eventos del filtro
 */
function EventoFiltro(){
   // Busquedas por Inpus
   var table = $('#tableListar').DataTable();
   table.columns().every( function () {
   var dataTableColumns = this
   $( 'input', this.footer() ).keypress(function (event) {
        if ( event.which == 13 ) {
             if ( dataTableColumns.search() !== this.value ) {
                dataTableColumns.search( this.value ).draw();
             }
        }
   });
   var searchTextBoxes = $(this.header()).find('input');
     searchTextBoxes.on('keyup change',function(){
        dataTableColumns.search(this.value).draw();
   });
   $( 'select', this.footer() ).click(function (event) {
      if ( dataTableColumns.search() !== this.value ) {
         dataTableColumns.search( this.value ).draw();
      }
   });
   var searchTextBoxesSelect = $(this.header()).find('select');
     searchTextBoxes.on('keyup change',function(){
        dataTableColumns.search(this.value).draw();
   });
   searchTextBoxesSelect.on('click',function(e){
        e.stopPrapagation();
   });
   searchTextBoxes.on('click',function(e){
        e.stopPrapagation();
   });
 } );
}
/**
*Formato del listado 
**/
function __InformacionDataTable(){
    self.formato_tabla = [ 
                               {'data' :'categoria'               ,"name":"categoria"              ,"title" : "Categoria"        ,"autoWidth" :true},
                               
                                {'data' :'codigo'                  ,"name":"codigo"                 ,"title" : "Codigo"           ,"autoWidth" :true },
                               {'data' :'descripcion'             ,"name":"descripcion"            ,"title" : "Descripcion"      ,"autoWidth" :true },
                               {'data' :'costo'                   ,"name":"costo"                  ,"title" : "Costo"            ,"autoWidth" :true
                               ,
                               "render":function(costo,type, row){
                                     return costo ==null?0:costo;
                                },
                            },
                               {'data' :'impuesto'                ,"name":"impuesto"               ,"title" : "IVA"         ,"autoWidth" :true },
                               {'data' :'precioPublico'           ,"name":"precioPublico"          ,"title" : "Precio"           ,"autoWidth" :true ,
                               "render":function(precioPublico,type, row){
                                     return precioPublico ==null?0:precioPublico >0?precioPublico:0;
                                },
                            },
                               {'data' :'cantidadActualSTR' ,"name":"cantidadActualSTR" ,"title" : "Cantidad"  ,"autoWidth" :true,
                               "render":function(cantidad,type, row){
                                     return cantidad ==null?0:cantidad;
                                },
                            },
                               {'data' :'contable'                ,"name":"contable"               ,"title" : "Contable"         ,"autoWidth" :false },
                               {'data' : 'estado'                 ,"name":"estado"          ,"title" : "Estado"      ,"autoWidth" :false,
                               "render":function(estado,type, row){
                                 return estadosActivoInactivo(estado,row);//factura.js
                                }
                                },
	      		            ];
    self.update();
   
}


/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 9    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
           // Select
    	if ($(this).index() == 8 ){
    	    var select = $('<select id="combo1" class="form-control"><option value="">Todos</option></select>');
    	    // se cargan los valores por defecto que existen en el combo
    	   	select.append( '<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+'</option>' );
            select.append( '<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+'</option>' );
    	   	$(this).html(select);
       }
       if ($(this).index() == 7 ){
         var select = $('<select id="combo2" class="form-control"><option value="">Todos</option></select>');
         // se cargan los valores por defecto que existen en el combo
           select.append( '<option value="'+$.i18n.prop("boolean.si")+'">'+$.i18n.prop("boolean.si")+'</option>' );
          select.append( '<option value="'+$.i18n.prop("boolean.no")+'">'+ $.i18n.prop("boolean.no") +'</option>' );
           $(this).html(select);
     }
       if ($(this).index() == 0 ){
         var select = $('<select id="combo3"   class="form-control"><option value="">Todos</option></select>');
         // se cargan los valores por defecto que existen en el combo
         select = __listadoCategoriasActivas(select);
         $(this).html(select);
     }
    })
}
/**
*  Mostrar listado datatable Categorias activas
**/
function __listadoCategoriasActivas(select){
  $.ajax({
       url: "ListarCategoriasActivasAjax.do",
      datatype: "json",
      method:"GET",
      success: function (result) {
           if(result.aaData.length > 0){
            $.each(result.aaData, function( index, modeloTabla ) {
               select.append( '<option value="'+modeloTabla.descripcion+'">'+modeloTabla.descripcion+'</option>' );       
            })
         }
      },
      error: function (xhr, status) {
          console.log(xhr);
           mensajeErrorServidor(xhr, status);
      }
  })
  return select;
}

		
	</script>
</total-inventarios>
