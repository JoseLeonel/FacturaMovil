<actividad-empresa>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-cog"></i>&nbsp {$.i18n.prop("actividadComercial.titulo")}  </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
<!-- Listado  -->
<div classs="contenedor-listar container" id="container"  show={mostrarListado}  >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar"   cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="table-header" >{$.i18n.prop("actividadComercial.descripcion")}  </th>
                                <th class="table-header" >{$.i18n.prop("actividadComercial.codigoComercial")} </th>
                                <th class="table-header">{$.i18n.prop("actividadComercial.principal")} </th>
                                <th class="table-header" > {$.i18n.prop("listado.acciones")}   </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>{$.i18n.prop("actividadComercial.descripcion")}  </th>
                                <th>{$.i18n.prop("actividadComercial.codigoComercial")} </th>
                                <th>{$.i18n.prop("actividadComercial.principal")} </th>
                                <th>  </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>    
</div>
<!-- Fin del Listado -->
<div  >
    <div class="row center " show ={mostrarFormulario} >
    <div class=" col-lg-4 "></div>
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {actividadComercial.id > 0 ? $.i18n.prop("actividadComercial.modificar")   :$.i18n.prop("actividadComercial.agregar")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{empresaActividadComercial.id}">
                        <input type="hidden" name="codigo" id="codigo" value="{empresaActividadComercial.codigo}">
                        <input type="hidden" name="principal" id="principal" value="{empresaActividadComercial.principal}">
                        <input type="hidden" name="descripcion" id="descripcion" value="{empresaActividadComercial.descripcion}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label class="tamanoLetra" >{$.i18n.prop("actividadComercial.principal")}  <span class="requeridoDato">*</span></label>
                                <select  class="form-control selectPrincipal"  name="selectPrincipal" id="selectPrincipal" >
                                    <option  each={estados}  value="{valor}"   selected="{empresaActividadComercial.principal ==valor?true:false}"  >{descripcion}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row" show = "{empresaActividadComercial.id == null}">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6 has-success">
                                <label class="tamanoLetra" >{$.i18n.prop("actividadComercial.actividades.asociadas")}  <span class="requeridoDato">*</span></label>
                                <select onchange= {__AsignarActividad} class="form-control selectActividadComercial"  name="selectActividadComercial" id="selectActividadComercial" >
                                    <option data-descripcion = "{descripcion}"  each={actividadComerciales.aaData}  value="{codigo}" selected="{empresaActividadComercial.codigo ==codigo?true:false}"  >{codigo}-{descripcion}</option>
                                </select>
                            </div>
                        </div>

                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left "  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                                {$.i18n.prop("btn.volver")}
                            </button>
                       </div>
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" >  {$.i18n.prop("btn.modificar")}</button>
                            <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" > {$.i18n.prop("btn.agregar")}</button>
                       </div>
                </div>
            </div>   
        </div>
        <div class="col-lg-4 "></div>
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
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.estados                   = []
    self.actividadComerciales    = {aaData:[]}

     self.empresa              = {}
    self.empresaActividadComercial = {
        id:null,
        descripcion:"",
        codigo:"",
        principal:0
    }
self.on('mount',function(){
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListar')
    __listado()
    includeActions('.dataTables_wrapper','.dataTables_length')
    __MantenimientoAgregar()
    
    __ComboEstados()
    _Empresa()
    
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
})

__AsignarActividad(e){
    var codigo =$('#selectActividadComercial').val()
    $.each(self.actividadComerciales.aaData, function( index, modeloTabla ) {
        if(modeloTabla.codigo == codigo  ){
           self.empresaActividadComercial.descripcion = modeloTabla.codigo +"-" + modeloTabla.descripcion
            self.empresaActividadComercial.codigo =  codigo
            self.update()

        }

    })



}

/**
* Consultar la empresa
**/
function _Empresa(){
     $.ajax({
        url: "ParametrosEmpresaAjax.do",
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
                       self.empresa =   modeloTabla
                       __listadoActividadComercial()
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
*  Crear el combo comanda
**/
function __ComboEstados(){
    self.estados =[]
    self.update()
    self.estados.push({
        valor: 0,
       descripcion: $.i18n.prop("boolean.no") 
     });
    self.estados.push({
        valor: 1,
        descripcion:$.i18n.prop("boolean.si")
     });
     self.update();
}


function __listadoActividadComercial(){
    $.ajax({
         url: "https://api.hacienda.go.cr/fe/ae?identificacion="+self.empresa.cedula,
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.actividades.length > 0){
                getActivas(result.actividades)
                self.update();
               
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}

function getActivas(actividades){
    $.each(actividades, function( index, modeloTabla ) {
        if(modeloTabla.estado =='A'){
            self.actividadComerciales.aaData.push({
                descripcion:modeloTabla.descripcion,
                codigo:modeloTabla.codigo
            })
        }

    })
    self.update()
}

/**
*  Regresar al listado
**/
__regresarAlListado(){
    self.mostrarListado     = true;
    self.botonAgregar       = false;
    self.botonModificar     = false;
    self.mostrarFormulario  = false 
    self.update()
    __listado();
}
// Mostrar formulario de mantenimiento Agregar
function __MantenimientoAgregar(){
   
  
      //Inicializar el Formulario
    $('.dataTables_wrapper').on('click','.btn-agregar',function(e){
        //desahabilita  listado 
        self.mostrarListado   = false;
        self.mostrarFormulario  = true 
        //desahabilita boton modificar
        self.botonModificar   = false;
        // habilita el formulario
        self.botonAgregar     = true;
          self.empresaActividadComercial = {
            id:null,
            descripcion:"",
            codigo:"",
            principal:0
        }
        self.update();
    })
}
/**
 * Funcion para Modificar del Listar
 */
function __modificarRegistro_Listar(){
	$('#tableListar').on('click','.btnModificar',function(e){
        $(".errorServerSideJgrid").remove();
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
          //desahabilita  listado 
        self.mostrarListado   = false;
        self.mostrarFormulario  = true 
        //desahabilita boton modificar
        self.botonModificar   = true;
        // habilita el formulario
        self.botonAgregar     = false;
        self.empresaActividadComercial  = data
        self.update()
	});
}

/**
*   Agregar 
**/
__agregar(){
    if(self.empresaActividadComercial.codigo.length == 0){
      self.empresaActividadComercial.codigo = $('.selectActividadComercial').val()    
      self.empresaActividadComercial.descripcion = $('.selectActividadComercial').text();
      self.empresaActividadComercial.descripcion = self.empresaActividadComercial.codigo +"-"+self.empresaActividadComercial.descripcion 
    }
   
    self.empresaActividadComercial.principal = __valorNumerico($('.selectPrincipal').val())
    self.update()
 var formulario = $("#formulario").serialize();
    $.ajax({
        type : "POST",
        dataType : "json",
        data : formulario,
        url : 'AgregarEmpresaActividadComercialAjax.do',
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
        }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}         
/**
** Modificar la Empresa
**/
__Modificar(){
    self.error = false;
    self.exito = false;
   if(self.empresaActividadComercial.codigo.length == 0){
      self.empresaActividadComercial.codigo = $('.selectActividadComercial').val()    
      self.empresaActividadComercial.descripcion = $('.selectActividadComercial').text();
      self.empresaActividadComercial.descripcion = self.empresaActividadComercial.codigo +"-"+self.empresaActividadComercial.descripcion 
    }
   
    self.empresaActividadComercial.principal = __valorNumerico($('.selectPrincipal').val())
    self.update()    
    self.update();
    __modificarRegistro("#formulario",$.i18n.prop("actividadComercial.mensaje.alert.modificar"),'ModificarEmpresaActividadComercialAjax.do','ListarEmpresaActividadComercialAjax.do','#tableListar')
}
/**
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarEmpresaActividadComercialAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                __InformacionDataTable();
                loadListar(".tableListar",idioma_espanol,self.informacion_tabla,result.aaData)
                includeActions('.dataTables_wrapper','.dataTables_length')
                agregarInputsCombos();
                __MantenimientoAgregar()
                    //Activar filtros
                ActivarEventoFiltro(".tableListar")
                __modificarRegistro_Listar()
             } 
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
}
/**
*Formato del listado de los cambios
**/
function __InformacionDataTable(){
    self.informacion_tabla = [ 
                               {'data' :'descripcion'    ,"name":"descripcion"     ,"title" : $.i18n.prop("actividadComercial.descripcion") ,"autoWidth" :true ,
                                 "render":function(id,type, row){
                                      return row.descripcion.length > 80?row.descripcion.substring(1, 80):row.descripcion;
                                 }
                                 },

                               {'data' :'codigo'   ,"name":"codigo"    ,"title" : $.i18n.prop("actividadComercial.codigoComercial") ,"autoWidth" :true },
                               {'data' :'principal'   ,"name":"principal"    ,"title" : $.i18n.prop("actividadComercial.principal") ,"autoWidth" :true ,
                                                                "render":function(id,type, row){
                                      return row.principal == 1 ?"Si":"No";
                                 }

                               },
                               {'data' : 'id'            ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}
/**
* Opciones listado de los clientes
*/
function __Opciones(){
  var modificar  = '<a href="#"  title="Modificar" class="btn btn-warning  btn-edit btnModificar" role="button"> </a>';
  return  modificar ;
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 3    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
        
    })
}
</script>
</actividad-empresa>