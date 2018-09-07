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
<!-- Modal -->
<div class="modal fade" id="ModalCorreoAlternativo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
          
                <h1 class="box-title"><i class="btn-correo"></i>&nbsp {$.i18n.prop("hacienda.titulo.correo.alternativo")}     </h1>
          
      </div>
      <div class="modal-body">
        <form id = "formulario" name ="formulario "   class="advanced-search-form">
            <div class="row">   
                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                    <label class="knob-label" >{$.i18n.prop("hacienda.correo")}</label>
                    <input type="email" class="form-control correoAlternativo" placeHolder ="{$.i18n.prop("hacienda.correo.ejemplo")}" id="correoAlternativo" name="correoAlternativo" value=""  >
                </div>
            </div>
        </form>
      </div>
      <div class="modal-footer">
        <div class="row">
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                    {$.i18n.prop("btn.volver")}
                </button>
            </div>
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                <button  onclick={__Enviar}   class="btn-green btn-correo pull-right" >  {$.i18n.prop("btn.enviar.correo")}</button>
            </div>
         </div>
       
      </div>
    </div>
  </div>
</div>


<script>
    self = this
    self.detail                = []
    self.mostrarListado        = true
    self.mostrarDetalle        = false

self.on('mount',function(){
    $("#formulario").validate(reglasDeValidacion());
     __InicializarTabla('.tableListar')
    agregarInputsCombos() 
    listaClientesActivos()
     window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );

})   

/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			correoAlternativo : {
				required : true,
                email:true,
                maxlength:240,
                minlength:1,
			}                                   
                        
		},
		ignore : []

	});
	return validationOptions;
};

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

/**
* Enviar el correo
**/
__Enviar(){

     if ($("#formulario").valid()) {
         enviarCorreoAlternativo()
     }

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
    $('#ModalCorreoAlternativo').modal('hide')
    
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
                    __RespuestaHacienda()
                    __BajarPDFHacienda()
                    __CorreoAlternativo()
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
                               {'data' :'fechaEmisorSTR'   ,"name":"fechaEmisorSTR"    ,"title" : $.i18n.prop("hacienda.fechaEmision")     ,"autoWidth" :true },
                             
                               {'data' :'tipoDoc'        ,"name":"tipoDoc"      ,"title" : $.i18n.prop("hacienda.tipoDocumento")   ,"autoWidth" :true },
                               {'data' :'consecutivo'    ,"name":"consecutivo"    ,"title" : $.i18n.prop("hacienda.consecutivo") ,"autoWidth" :true},
                               {'data' :'nombreReceptor' ,"name":"nombreReceptor" ,"title" : $.i18n.prop("hacienda.cliente")     ,"autoWidth" :true},
                               {'data' :'totalReceptor'  ,"name":"totalReceptor"  ,"title" : $.i18n.prop("hacienta.monto")       ,"autoWidth" :true ,
                                    "render":function(totalReceptor,type, row){
									    return redondeoDecimales(totalReceptor,2);
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
    
    if(row.estado == 2){
      menu += '<li><a href="#"  title="Envio Manual a Tributacion" class="  btnEnvioManual" >Envio Manual</a></li>'
    }
    
    if(row.estado == "Aceptado"){
        menu += '<li><a href="#"  title="Envio del correo al cliente" class="  btnEnvioCorreoCliente" >Envio Correo al Cliente</a></li>'
        menu += '<li><a href="#"  title="Bajar XML" class="  btnBajarXML" >XML Documentos</a></li>'
        menu += '<li><a href="#"  title="Bajar XML Respuesta de Triburacion" class="  btnRespuestaHacienda" >XML Respuesta</a></li>'
       menu += '<li><a href="#"  title="Envio de correo Alternativo" class="  btnEnvioCorreoAlternativo" >Envio de correo Alternativo</a></li>'

    }
    if(row.estado == "Enviado"){
        menu += '<li><a href="#"  title="Aceptacion Manual a Tributacion" class="  btnAceptacionManual" >Aceptacion Manual</a></li>'
    }

    menu += '<li><a href="#"  title="Bajar PDF" class="  btnBajarPDF" >PDF Documentos</a></li>'
    
    menu += "</ul></div>"  

     return menu;          
}

/**
*  Enviar a correo alternativo
**/
function __CorreoAlternativo(){
	$('.tableListar').on('click','.btnEnvioCorreoAlternativo',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.hacienda = data
        self.update()
        $('.correoAlternativo').val(null)
        $('#ModalCorreoAlternativo').modal('show')      
	});
}

/**
* Enviar correo
**/
function enviarCorreoAlternativo(){
    $.ajax({
        url: "EnviarCorreoAlternativoAjax",
        datatype: "json",
        data: {idHacienda:self.hacienda.id,correo:$('.correoAlternativo').val()},
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
 * Bajar PDF
 */
function __BajarPDFHacienda(){
	$('.tableListar').on('click','.btnBajarPDF',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        BajarArchivos("bajarPDFComprobanteAjax",data)
	});
}


/**
 * Respuesta de Hacienda
 */
function __RespuestaHacienda(){
	$('.tableListar').on('click','.btnRespuestaHacienda',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        BajarArchivos("bajarXMLRespuestaAjax",data)
	});
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
function formatoFechaHora(fecha) {
    return fecha == null?"":moment(fecha).format('DD/MM/YYYY h:mm:ss a');
}

</script>
</resultado-hacienda>