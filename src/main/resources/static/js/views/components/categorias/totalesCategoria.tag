<totales-categoria>
      <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-filter"></i>&nbsp Totales por Categoria  </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
   <!-- Inicio Filtros-->
        <div class="row" >
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;  cursor: pointer;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                     <form id="filtros" name="filtros">              
                        <div class= "containerFiltro">
                            <div class="elementoFiltro">
                                <div class="form-group">
        	                         <label > Categorias </label>  
                                    <select  class="form-control selectCategoria categoria" id="categoria" name="categoria" data-live-search="true">
                                        <option  data-tokens="{$.i18n.prop("todos.select")}"  value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option  data-tokens="{descripcion}" each={categorias.data}  value="{id}"  >{descripcion}</option>
                                    </select>
                  				</div>                        
                            </div>             
                            <div class="elementoFiltro">
                                <div class="form-group">
			                        <label > Estado </label>  
                                    <select  class="form-control  estado" id="estado" name="estado" >
                                        <option   each={estados}  value="{codigo}"  >{descripcion}</option>
                                    </select>
                    			 </div>
                            </div>
                            <div class="elementoFiltro">
                                 <div class="form-group">
			                         <label > Minimo/Maximo </label>  
			                         
                                    <select  class="form-control  minimoMaximo" id="minimoMaximo" name="minimoMaximo" >
                                        <option   each={minimoMaximos}  value="{codigo}"  >{descripcion}</option>
                                    </select>
                     			</div>
                            </div>    
                        </div>
                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
                <a   show={hay_datos==true} class=" btn btn-primary btn-bajar"  target="_blank" title="Descargar" href="DescargarArticuloXCategoriaAjax.do?idCategoria={idCategoria}&estado={estado}&minimoMaximo={minimoMaximo}"> Descargar</a>        
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
            	
            </div>
        </div>
  
<!-- Fin Filtros-->
<div class="containerTotales">
   	<div class="elementoTotales">
   	    <label> Total Costo</label>
        <input type="text" readonly="readonly" class="form-control "  value="{totalCosto}" >
   	</div>
   	<div class="elementoTotales">
   	    <label> Total Impuesto Esperado</label>
        <input type="text" readonly="readonly" class="form-control" value="{totalImpuestaEsperada}">
   	</div>
   	<div class="elementoTotales">
   	    <label> Total Venta Esperada</label>
        <input type="text" readonly="readonly" class="form-control"  value="{totalVentaEsperada}">
   	</div>
   	<div class="elementoTotales">
   	    <label> Total Ganancia Esperada</label>
        <input type="text" readonly="readonly" class="form-control " value="{totalGananciaEsperada}" >
   	</div>
</div>
<br>

<div class="row">
    <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 scroller " style="width:98.50%;">
        <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar"   cellspacing="0" width="98.5%">
            <thead>
            <tr>
                <th class="table-header" style ="width:5%" >Codigo        </th>
                <th style="width:40%"  class="table-header" >Descripcion  </th>
                <th style="width:6%"  class="table-header" >Cantidad      </th>
                <th style="width:6%"  class="table-header" >Minimo        </th>
                <th style="width:6%"  class="table-header" >Maximo        </th>
                <th style="width:6%"  class="table-header" >Costo         </th>
                <th style="width:6%"  class="table-header" >Precio Publico</th>
                <th style="width:6%"  class="table-header" >Total Costo   </th>
                <th style="width:6%"  class="table-header" >Impuesto Esperada</th>
                <th style="width:6%"  class="table-header" >Venta Esperada   </th>
                <th style="width:6%"  class="table-header" >Ganancia Esperada</th>
            </tr>
            </thead>
        </table>
    </div>
</div>    

<style type ="text/css">
   
    .containerFiltro{
        display: flex; /* or inline-flex */
        justify-content:space-between;  
        flex-wrap:wrap;
    }
    .elementoFiltro{
        width:30%;
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
        .containerTotales{
           display: flex; /* or inline-flex */
          justify-content:space-between;  
          flex-wrap:wrap;
        }
        .elementoTotales{
          
         
        }
    </style>
<script>
    var self = this;
    self.idiomaDataTable   = []         // idioma de la datatable nuevo
    self.formato_tabla     = []         // Formato del Listado de la Tabla 
    self.empresas          = {aaData:[]}
    self.categorias        = {data:[]}
    self.dataResultado     = {data:[]}
    self.estados           =[]
    self.minimoMaximos     =[]
    self.minimoMaximo      = ""
    self.estado            = "";
    self.idCategoria       = 0
    self.mostrarListado    = true 
    self.hay_datos         = false
    self.botonModificar    = false
    self.botonAgregar      = false
    self.mostrarFiltros    = true;
    self.categoria = {
        id:null,
        descripcion:"",
        estado:"",
        prioridad:0
    }
    self.totalCosto            = 0
    self.totalImpuestaEsperada = 0
    self.totalVentaEsperada    =0
    self.totalGananciaEsperada =0
self.on('mount',function(){
    __InicializarTabla('.tableListar')
    __listadoCategoriasActivas();
    __ComboEstados();
    __ComboMinimoMaximo();
    window.addEventListener( "keydown", function(evento){
        $(".errorServerSideJgrid").remove();
    }, false );
    
})
__Busqueda(){
    __ListaArticulos()
}
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
*  Mostrar listado datatable Categorias activas
**/
function __listadoCategoriasActivas(){
    self.categorias = {data:[]}
    self.update()
    $.ajax({
       url: "ListarCategoriasActivasAjax.do",
      datatype: "json",
      global: false,
      method:"GET",
      success: function (result) {
        if(result.aaData.length > 0){
            self.categorias.data = result.aaData
            self.update()
             $('.selectCategoria').selectpicker();
        }
      },
      error: function (xhr, status) {
          console.log(xhr);
           mensajeErrorServidor(xhr, status);
      }
  });

}
/**
*  Crear el combo de estados
**/
function __ComboEstados(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: "0",
        descripcion:$.i18n.prop("todos.select")
    });
    self.estados.push({
        codigo: $.i18n.prop("estado.Activo"),
        descripcion:$.i18n.prop("estado.Activo")
    });
    self.estados.push({
        codigo: $.i18n.prop("estado.Inactivo"),
        descripcion:$.i18n.prop("estado.Inactivo")
    });
  
    self.update();
}
/**
*  Crear el combo de minimo maximo
**/
function __ComboMinimoMaximo(){
    self.minimoMaximos =[]
    self.update()
    self.minimoMaximos.push({
        codigo: "0",
        descripcion:$.i18n.prop("todos.select")
    });
    self.minimoMaximos.push({
        codigo:"1",
        descripcion:$.i18n.prop("inventario.menor.igual.minimo")
    });
    self.minimoMaximos.push({
        codigo:"2",
        descripcion:$.i18n.prop("inventario.mayor.igual.minimo")
    });
  
    self.update();
}

/**
*  Lista de los artculos
**/
function __ListaArticulos(){
    self.dataResultado     = {data:[]}
    self.totalCosto            = 0
    self.totalImpuestaEsperada = 0
    self.totalVentaEsperada    = 0
    self.totalGananciaEsperada = 0
    self.hay_datos         = false
    self.minimoMaximo      = $("#minimoMaximo").val()
    self.idCategoria       = $("#categoria").val()
    self.estado            = $("#estado").val();
    self.update()
      __InicializarTabla('.tableListar')  
   var formulario = {
       categoria:$("#categoria").val(),
       estado:$("#estado").val(),
       minimoMaximo:$("#minimoMaximo").val(),
   }
   console.log(formulario)
   $.ajax({
       url: 'ListarArticuloXCategoriaAjax.do',
       datatype: "json",
       data : formulario,
       
       method:"POST",
       success: function (result) {
           if(result.aaData.length > 0){
               __InformacionDataTable();
               self.dataResultado.data =result.aaData;
               self.hay_datos         = true
               self.update()
               loadListar(".tableListar",idioma_espanol,self.informacion_tabla, self.dataResultado.data);
               
               __TotalesGenerales();
           }
       },
       error: function (xhr, status) {
           console.log(xhr);
           mensajeErrorServidor(xhr, status);
       }
   });
   return
}
/**
 * Funcion para obtener el data y realizar la suma de los totales
 */
function __TotalesGenerales(){
   var totalImpuesto = 0;
   var totalVenta = 0;
   var totalGanancia = 0;
   var totalCosto = 0;
   $.each(self.dataResultado.data, function( index, modeloTabla ) {
      totalImpuesto = __valorNumerico(modeloTabla.totalImpuesto) + totalImpuesto;
      totalVenta = __valorNumerico(modeloTabla.totalVenta) + totalVenta;
      totalGanancia = __valorNumerico(modeloTabla.totalGanancia) + totalGanancia;
      totalCosto =  __valorNumerico(modeloTabla.totalCosto) + totalCosto;
   });
   self.totalCosto            = formatoDecimales(totalCosto,2)
   self.totalImpuestaEsperada = formatoDecimales(totalImpuesto,2)
   self.totalVentaEsperada    = formatoDecimales(totalVenta,2)
   self.totalGananciaEsperada = formatoDecimales(totalGanancia,2)
   self.update()
}
/**
*Formato del listado de los cambios
**/
function __InformacionDataTable(){
    self.informacion_tabla = [ 
                           {'data' :'codigo'           ,"name":"codigo"           ,"title" : "Codigo"            ,"autoWidth" :true , "bSortable": false },
                           {'data' :'descripcion'      ,"name":"descripcion"      ,"title" : "Descripcion"       ,"autoWidth" :true , "bSortable": false },
                           {'data' :'cantidadSTR'      ,"name":"cantidad"         ,"title" : "Cantidad"          ,"autoWidth" :true, "bSortable": false },
                           {'data' :'minimo'           ,"name":"minimo"           ,"title" : "Minimo"            ,"autoWidth" :true, "bSortable": false },
                           {'data' :'maximo'           ,"name":"maximo"           ,"title" : "Maximo"            ,"autoWidth" :true, "bSortable": false },
                           {'data' :'costoSTR'         ,"name":"costo"            ,"title" : "Costo"             ,"autoWidth" :true, "bSortable": false },
                           {'data' :'precioPublicoSTR' ,"name":"precioPublico"    ,"title" : "Precio Publico"    ,"autoWidth" :true , "bSortable": false },
                           {'data' :'totalCostoSTR'    ,"name":"totalCostoSTR"    ,"title" : "Total Costo"       ,"autoWidth" :true , "bSortable": false },
                           {'data' :'totalImpuestoSTR' ,"name":"totalImpuestoSTR" ,"title" : "Impuesto Esperado" ,"autoWidth" :true , "bSortable": false },
                           {'data' :'totalVentaSTR'    ,"name":"totalVentaSTR"    ,"title" : "Venta Esperada"    ,"autoWidth" :true, "bSortable": false },
                           {'data' :'totalGananciaSTR' ,"name":"totalGananciaSTR" ,"title" : "Ganancia Esperada" ,"autoWidth" :true, "bSortable": false },
                        ];
    self.update();
   
}

</script>
</totales-categoria>