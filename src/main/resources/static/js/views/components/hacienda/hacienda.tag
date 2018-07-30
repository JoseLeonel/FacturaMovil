<resultado-hacienda>

    <!-- Titulos -->
    <div  class="row " show="mostrarListado" >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-share"></i>&nbsp {$.i18n.prop("hacienda.titulo")} </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
        </div>
    </div>
    <br>
    <br><br>
    <!-- Inicio Filtros-->
    <div>
        <div class="row" show={mostrarListado}>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="knob-label" >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                    <div  class="form-group input-group date" data-provide="datepicker"   data-date-format="dd/mm/yyyy">
                                        <input type="text" class="form-control fechaInicial" id="fechaInicial"  name= "fechaInicial" readonly>
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
                                        <div  class="form-group input-group date" data-provide="datepicker"   data-date-format="dd/mm/yyyy">
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
                                    <label>{$.i18n.prop("cliente.titulo")} </label>  
                                    <select  class="form-control selectCliente" id="cliente" name="cliente" data-live-search="true">
                                        <option  data-tokens="{$.i18n.prop("todos.select")}"   value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option  data-tokens="{nombreCompleto}" each={clientes}  value="{id}"  >{nombreCompleto}</option>
                                    </select>
                                </div>  
                            </div>                      
                        </div>
                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
                <a onclick ={__crearArchivoExcel} id="btnDownload" class="btn btn-success" title ="Descargar" > <i class="fa fa-download"></i></a>
            	<button onclick ={__limpiarFiltros} show={mostrarFiltros} class="btn btn-warning btnLimpiarFiltros" title="LimpiarCampos" type="button"><i id="clear-filters" class="fa fa-eraser clear-filters"></i></button>            
            </div>
        </div>
    </div>    
<!-- Fin Filtros-->
  <br>
  <!-- Listado  -->
    <div classs="contenedor-listar "  >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                <div class="box">
                    <div class="box-body">
                        <div class="planel-body" >
                            <div class="row" >        
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12" >
                                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar "   cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th class = "table-header" >{$.i18n.prop("hacienda.fechaEmision")}            </th>
                                                <th class = "table-header" >{$.i18n.prop("hacienda.tipoDocumento")}           </th>
                                                <th class = "table-header" >{$.i18n.prop("hacienda.consecutivo")}             </th>
                                                <th class = "table-header" >{$.i18n.prop("hacienda.cliente")}                 </th>
                                                <th class = "table-header" >{$.i18n.prop("hacienta.monto")}                   </th>
                                                <th class = "table-header" >{$.i18n.prop("hacienda.estado")}                  </th>
                                                <th class = "table-header" >{$.i18n.prop("listado.acciones")}                 </th>
                                            </tr>
                                        </thead>
                                        <tfoot style="display: table-header-group;">
                                            <tr>
                                                <th>{$.i18n.prop("hacienda.fechaEmision")}            </th>
                                                
                                                <th>{$.i18n.prop("hacienda.tipoDocumento")}           </th>
                                                <th>{$.i18n.prop("hacienda.consecutivo")}             </th>
                                                <th>{$.i18n.prop("hacienda.cliente")}                 </th>
                                                <th>{$.i18n.prop("hacienta.monto")}                   </th>

                                                <th >{$.i18n.prop("hacienda.estado")}                 </th>
                                                <th>                                                  </th>
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

<script>
    self = this
    self.detail                = []
    self.mostrarListado        = true
    self.mostrarDetalle        = false

self.on('mount',function(){
     __InicializarTabla('.tableListar')
    agregarInputsCombos() 
    listaClientesActivos()

})   

function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 6    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
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
*  Obtiene la lista de los clientes activos
**/
function listaClientesActivos(){
    self.clientes                  = {data:[]}
    self.update()
    $.ajax({
        url: "ListarClientesActivosAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                 self.clientes.data = result.aaData
                 self.update()
                   $('.selectCliente').selectpicker();
             } 
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
}


/**
*  Regresar al listado
**/
__regresarAlListado(){
    swal({
        title: "", 
        text: $.i18n.prop("mensaje.alert.regresar.listado"), 
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: '#00539B',
        cancelButtonColor: '#d33',
        confirmButtonText:$.i18n.prop("confirmacion.si"),
        cancelButtonText: $.i18n.prop("confirmacion.no"),
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger'
        }).then(function (isConfirm) {
            if(isConfirm){
                self.detail                = []
                self.mostrarListado        = true
                self.mostrarDetalle        = false
                self.update()
                __listado();

            }
    });    
}

/**
* limpiar los filtros
**/
__limpiarFiltros(){
    $('#fechaInicial').val(null)
    $('#fechaFinal').val(null)
}

/**
*  Busqueda de la informacion por rango de fechas
**/
__Busqueda(){
    var inicial  =$('.fechaInicial').val()
     if ($("#filtros").valid()) {
        var parametros = {
            fechaInicio:inicial,
            fechaFin:$('.fechaFinal').val(),
            cedulaReceptor:$('#cliente').val(),
        };
        $("#tableListar").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListar')  
        $.ajax({
            url: "ListarHaciendasAjax.do",
            datatype: "json",
            data:parametros ,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                     __InformacionDataTable();
                    loadListar(".tableListar",idioma_espanol,self.formato_tabla,result.aaData)
                    agregarInputsCombos()
                    ActivarEventoFiltro(".tableListar")
                    __EnviarHacienda()
                    __EnviarAceptarHacienda()
                    __EnviarCorreos()
                    __BajarDocumentoXML()
                }else{
                    __InformacionDataTable()
                    agregarInputsCombos()
                }           
            },
            error: function (xhr, status) {
                mensajeErrorServidor(xhr, status);
                console.log(xhr);
            }
        });

     }

}


/**
*Formato del listado 
**/
function __InformacionDataTable(){
    self.formato_tabla = [ 
                               {'data' :'fechaEmisor'   ,"name":"fechaEmisor"    ,"title" : $.i18n.prop("hacienda.fechaEmision")     ,"autoWidth" :true ,
                                  "render":function(fechaEmisor,type, row){
									    return displayDate_detail(fechaEmisor);
	 							    }
                               },
                             
                               {'data' :'tipoDoc'   ,"name":"tipoDoc"      ,"title" : $.i18n.prop("hacienda.tipoDocumento")   ,"autoWidth" :true },
                               {'data' :'consecutivo'    ,"name":"consecutivo"    ,"title" : $.i18n.prop("hacienda.consecutivo") ,"autoWidth" :true},
                               {'data' :'nombreReceptor' ,"name":"nombreReceptor" ,"title" : $.i18n.prop("hacienda.cliente")     ,"autoWidth" :true},
                               {'data' :'totalReceptor'  ,"name":"totalReceptor"  ,"title" : $.i18n.prop("hacienta.monto")       ,"autoWidth" :true ,
                                    "render":function(totalReceptor,type, row){
									    return "₡" + totalReceptor.toLocaleString('de-DE');
	 							    }
                               },
                               {'data' :'estado' ,"name":"estado" ,"title" : $.i18n.prop("hacienda.estado")     ,"autoWidth" :true},
                               {'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}


/**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
    let menu = '<div class="dropdown">' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    
    menu += '<li><a href="#"  title="Envio Manual a Tributacion" class="  btnEnvioManual" >Envio Manual</a></li>'
    
    menu += '<li><a href="#"  title="Aceptacion Manual a Tributacion" class="  btnAceptacionManual" >Aceptacion Manual</a></li>'
    menu += '<li><a href="#"  title="Envio del correo al cliente" class="  btnEnvioCorreoCliente" >Envio Correo al Cliente</a></li>'
    menu += '<li><a href="#"  title="Bajar XML" class="  btnBajarXML" >Bajar XML</a></li>'
    menu += '<li><a href="#"  title="Bajar PDF" class="  btnBajarPDF" >Bajar PDF</a></li>'
    menu += '<li><a href="#"  title="Bajar XML Aceptacion de Triburacion" class="  btnMostrar" >Bajar XML Aceptacion</a></li>'
    menu += '<li><a href="#"  title="Envio de correo Alternativo" class="  btnEnvioCorreoAlternativo" >Envio de correo Alternativo</a></li>'
    
    menu += "</ul></div>"  

     return menu;          
}


/**
 * Envio Manual hacia Hacienda
 */
function __EnviarHacienda(){
	$('.tableListar').on('click','.btnEnvioManual',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        __consultar("EnviarAceptarHaciendaAjax",data)
	});
}

/**
 * Envio Manual para revisar la aceptacion del documento por Hacienda
 */
function __EnviarAceptarHacienda(){
	$('.tableListar').on('click','.btnAceptacionManual',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        __consultar("AceptarHaciendaAjax",data)
	});
}

/**
 * Envio del correo al emisor y receptor
 */
function __EnviarCorreos(){
	$('.tableListar').on('click','.btnEnvioCorreoCliente',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        __consultar("EnviarCorreoClienteAndEmisorAjax",data)
	});
}


/**
 * bajar el xml del documento
 */
function __BajarDocumentoXML(){
	$('.tableListar').on('click','.btnBajarXML',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        alert(1)
        BajarArchivos("bajarXMLComprobanteAjax",data)
	});
}



/**
*  Enviar 
**/
function __consultar(url,objeto){
    var parametros = {

    }
    $.ajax({
        url: url,
        datatype: "json",
        data: {idHacienda:objeto.id},
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                sweetAlert("", data.message, "info");
            }
            
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
}



/**
*  BajarDocumentos 
**/
function BajarArchivos(url,objeto){
   location.href = url + "?idHacienda=" + objeto.id
}


/**
*Formato de la fecha con hora
**/
function displayDate_detail(fecha) {
    return fecha == null?"":moment(fecha).format('DD/MM/YYYY h:mm:ss a');
}

function redondearDecimales(numero, decimales) {
    numeroRegexp = new RegExp('\\d\\.(\\d){' + decimales + ',}');   // Expresion regular para numeros con un cierto numero de decimales o mas
    if (numeroRegexp.test(numero)) {         // Ya que el numero tiene el numero de decimales requeridos o mas, se realiza el redondeo
        return Number(numero.toFixed(decimales));
    } else {
        return Number(numero.toFixed(decimales)) === 0 ? 0 : numero;  // En valores muy bajos, se comprueba si el numero es 0 (con el redondeo deseado), si no lo es se devuelve el numero otra vez.
    }
}
</script>
</resultado-hacienda>