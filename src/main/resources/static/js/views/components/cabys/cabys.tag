<cabys-crud>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-cog"></i>Catalogo Cabys  </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
    
<!-- Listado  -->
<div classs="contenedor " show={mostrarListado}  >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " >
                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar"   cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="table-header" style="width: 10%;">Codigo</th>
                                <th class="table-header" style="width: 60%;">Descripcion</th>
                                <th class="table-header" style="width: 10%;">Fecha Creacion</th>
                                <th class="table-header" style="width: 10%;">Origen </th>
                                <th class="table-header" style="width: 10%;">{$.i18n.prop("categoria.estado")}</th>
                                <th class="table-header" style="width: 10%;">{$.i18n.prop("listado.acciones")}</th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>Codigo</th>
                                <th>{$.i18n.prop("categoria.descripcion")}</th>
                                <th>Fecha Creacion </th>
                                <th>Origen </th>
                                <th>{$.i18n.prop("categoria.estado")}</th>
                                <th></th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>    
</div>
<!-- Fin del Listado -->

    

<div  >
    <div class="row center " show ={mostrarFormulario} >
    <div class=" col-sx-12 col-lg-12 "></div>
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {cabys.id > 0 ? $.i18n.prop("cabys.modificar")   :$.i18n.prop("cabys.agregar")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{cabys.id}">
                        <input type="hidden" id='codigo' name='codigo' value="{cabys.codigo}" >
                        <input type="hidden" id='descripcion' name='descripcion' value="{cabys.descripcion}" >
                        <input type="hidden" id='origen' name='origen' value="{cabys.origen}" >
                        <input type="hidden" id='origenSTR' name='origenSTR' value="{cabys.origenSTR}" >
                        
                        <input type="hidden" id='impuesto' name='impuesto' value="{cabys.impuesto}" >
                        <input type="hidden" id='uri' name='uri' value="{cabys.uri}" >
                        <input type="hidden" id='empresa' name='empresa' value="{cabys.empresa}" >

                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label>codigo <span class="requeridoDato">*</span></label>
                                <input onclick={__ConsultaHaciendaCabys}  type="text" class="form-control codigo" placeHolder ="Codigo"  value="{cabys.codigo}"  readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label>{$.i18n.prop("cabys.descripcion")}  <span class="requeridoDato">*</span></label>
                                <input  type="text" class="form-control descripcion" placeHolder ="{$.i18n.prop("cabys.descripcion")}" value="{cabys.descripcion}"  readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label>Impuesto<span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control impuesto" placeHolder ="Impuesto"  value="{cabys.impuesto}" readonly >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label>Origen<span class="requeridoDato">*</span></label>
                                <textarea rows="6"  class="form-control origenSTR" placeHolder ="Origen del cabys"  value="{cabys.origenSTR}" readonly >
                                </textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label>{$.i18n.prop("cabys.estado")}</label>
                                <select  class="form-control" id="estado" name="estado" >
                                    <option  each={estados}  value="{codigo}" selected="{cabys.estado ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                                {$.i18n.prop("btn.volver")}
                            </button>
                       </div>
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right " >  {$.i18n.prop("btn.modificar")}</button>
                            <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" > {$.i18n.prop("btn.agregar")}</button>
                       </div>
                    </div>   
                  
                </div>
            </div>   
        </div>
        <div class="col-lg-4 "></div>
    </div>
</div>
<!--Modal mostrar Articulos de la empresa -->
<div id='modalHaciendaCabys' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
    <div class="modal-dialog modal-lg" >
        <div class="modal-content" style="width:1395px;">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> Lista de Cabys desde Hacienda y el Banco Central de Costa Rica(cabys@hacienda.go.cr) </h4>
            </div>
            <div class="modal-body">
                    <div class= "container">  
                        <form id="formularioParametros" name ="formularioParametros" >
                            <div class="row">
                                <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                    <label  >Digite la descripcion del producto a buscar</label>
                                    <input type="text" class="form-control descArticulo "   id="descArticulo" name="descArticulo" onkeypress={__ConsultaCodigoCabysEnter} autofocus="autofocus" autocomplete="off">
                                </div>
                                <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                    <label  >Digite el codigo</label>
                                    <input type="text" class="form-control codigoCabys "   id="codigoCabys" name="codigoCabys" onkeypress={__ConsultaCodigoCabysEnter} autofocus="autofocus" autocomplete="off">
                                </div>
                                <div class= "col-md-2 col-sx-12 col-sm-2 col-lg-2">
                                    <label>cantidad</label>
                                    <select  class="form-control" id="cantidad" name="cantidad" >
                                        <option  each={cantidades}  value="{codigo}" selected="{cabys.estado ==codigo?true:false}" >{descripcion}</option>
                                    </select>
                                </div>
                                <div class= "col-md-2 col-sx-12 col-sm-2 col-lg-2">
                                    <button  onclick={__ConsultaCodigoCabys} id = "filtroBotonCabys" type="button" class="btn-green pull-right"  >Consultar</button>
                                </div>                    
                            </div> 
                        </form>    
                        <br>    
                         <div class = "tablaDiseno">
                           
                                <table id="tableListarHaciendaCabys" class="table table-hover  tableListarHaciendaCabys "  >
                                    <thead>
                                        <th class="table-header colum1"  >{$.i18n.prop("listado.acciones")}</th>
                                        <th class="table-header colum2"  >{$.i18n.prop("articulo.codigo")}</th>
                                        <th class="table-header colum2"  >{$.i18n.prop("articulo.descripcion")}</th>
                                        <th class="table-header colum2"  >Impuesto</th>
                                        
                                    </thead>
                                    <tfoot style="display: table-header-group;">
                                        <tr class="headt">
                                            <th class="colum1"></th>
                                            <th class="colum2">{$.i18n.prop("articulo.codigo")}</th>
                                            <th class="colum2">{$.i18n.prop("articulo.descripcion")}</th>
                                           
                                            <th class="colum2">Impuesto</th>
                                            
                                        </tr>
                                    </tfoot>
                        </table>
                    </div>    
                    </div>
            </div>        
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left" data-dismiss="modal" >{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>


<div id='modalVerOrigen' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalVerOrigen formatoTitulo" id="title-add-note"> <i class='fa fa-cal '></i> Ver Origen</h1>
            </div>
            <div class="modal-body">
                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-6 col-lg-12">
                            <label class="tituloClienteNuevo" >Origen</label>
                            <textarea rows="8"  class="form-control origen" placeHolder ="Origen del cabys"  value="{origen}" readonly >
                            </textarea>

                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button data-dismiss="modal"  type="button" class="btn-dark-gray btn-back  pull-left modalCambioPrecioBotones"  >
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->
<style type ="text/css">
table {
    table-layout: unset!important;
}
.tablaDiseno{
    position: relative;
}
.colum1{
    width: 54px!important;
    
    }
    .colum2{
    width: 140px!important;
    
    }

     .container {
    display: flex!important;
    width: 100%!important;
    padding: 10px 0px 20px;
    min-height: 350px;
    flex-direction: column!important;
    flex-wrap: nowrap!important;
}
.contenedor{
    display:flex;
}
    #filtroBotonCabys{
        margin-top: 25px;
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
        
        
        
        .formatoTitulo{
                color: white!important;
        }
        .btn-mostrar {
            background-color: #4cae4c;
            color: #FFF;
            border-radius: 5px;
            padding-bottom: 5px;
            padding-top: 5px;
            padding-left: 10px;
            padding-right: 10px;
            font-size: 12px;
            font-weight: bold;
            /* margin-right: 15px; */
            border: none;
            float: right;
            cursor: pointer;
        }
.form-input {
    display: block;
   
    height: 34px;
    padding: 6px 12px;
    font-size: 12px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
    -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
.selectLeo{
    width: 60px;
}
.textAreaLeo{
    margin: 2px 0px !important;
    width: 189px!important;
    height: 68px !important;
    font-size: 11px!important;
    font-weight: 900!important;
}
	table td {
		text-align: left !important;
		font-size: 12px !important;
	}
	
	table th {
		text-align: !important;
		font-size: 12px !important;
	}
	
	th, td {
		white-space: break-spaces !important;;
	}
    .formCabys{
        width: 100%;
    height: 30px;
    font-size: 16px;
    font-weight: 800;
    }

    </style>
<script>
    var self = this;
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del Listado de la Tabla 
    self.empresas                  = {aaData:[]}
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.origen = ""
    self.cabys = {
        id:null,
        descripcion:"",
        estado:"",
        codigo:""
    }

    cabysHacienda = {
        codigo:"",
        descripcion:"",
        categorias:[],
        impuesto:0,
        uri:"",
    }
    
    self.listaCabys  = {aaData:[]}
self.on('mount',function(){
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListar')
    __listado()
    includeActions('.dataTables_wrapper','.dataTables_length')
    __MantenimientoAgregar()
    __Eventos()
    __ComboEstados()
    __ComboCantidades()
    __OrgienDAtos()
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
             disableF5(evento);
        }, false );
    
})
 function disableF5(e) {

        if ((e.which || e.keyCode) == 116) e.preventDefault();
        if ((e.which || e.keyCode) == 114) e.preventDefault();
        if ((e.which || e.keyCode) == 112) e.preventDefault();
        if ((e.which || e.keyCode) == 117) e.preventDefault();

        }

__ConsultaCodigoCabysEnter(e){
    if (e.keyCode != 13) {
        return;
    }
    __ListaDeHaciendaCabys()
}
/**
*  Crear el combo de estados
**/
function __ComboCantidades(){
    self.cantidades =[]
    self.update()
    self.cantidades.push({
        codigo: null,
        descripcion: "Todos"
     });
    self.cantidades.push({
        codigo: 5,
        descripcion: 5
     });
    self.cantidades.push({
        codigo: 10,
        descripcion: 10
     });
    self.cantidades.push({
        codigo: 20,
        descripcion: 20
     });
    



     self.update();

}
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			descripcion : {
				required : true,
                maxlength:501,
                minlength:1,
			},
			codigo : {
				required : true,
			}                                    
                        
		},
		ignore : []

	});
	return validationOptions;
};

/**
* Limpiar
**/
function Limpiar(){
    
     self.cabys = {
        id:null,
        descripcion:"",
        origen:"",
        impuesto:0,
        estado:"",
       	codigo:""
    }
    self.update()
    __ComboEstados()
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
*  Activar Eventos
**/

function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#descripcion").attr("maxlength", 80);
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
    Limpiar()
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
        self.update();
        //Inicializar el Formulario
        Limpiar()
        __Eventos()
   
    })
}

/**
 * Funcion para Modificar del Listar
 */
function __modificarRegistro_Listar(){
	$('#tableListar').on('click','.btnModificar',function(e){
        $("#formulario").validate(reglasDeValidacion());
        $(".errorServerSideJgrid").remove();
        $("#descripcion").val(null);
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        Limpiar()
        self.cabys  = data
        self.update()
        $("#descripcion").val(self.cabys.descripcion);
        $("#codigo").val(self.cabys.codigo);
        __consultar(data)
	});
}
/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(data){
    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarCabysAjax.do",
        datatype: "json",
        data: {idCabys:data.id},
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                    //desahabilita  listado 
                        Limpiar()
                        self.mostrarListado   = false;
                        self.mostrarFormulario  = true 
                        self.botonModificar   = true;
                      
                        // habilita el formulario
                        self.botonAgregar     = false;                        
                        self.cabys  =  modeloTabla
                        self.update()
                        //$("#descripcion").val(self.cabys.descripcion);
                       // $("#codigo").val(self.cabys.codigo);
                        __ComboEstados()
                        __Eventos()
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
*   Agregar 
**/
__agregar(){
    if ($("#formulario").valid()) {
         swal({
           title: '',
           text: "Desea agregar el codigo Cabys al Sistema?",
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
                agregarBackEnd()
            }
        })     
    }
}

function agregarBackEnd(){
    var formulario = $("#formulario").serialize();
    $.ajax({
        type : "POST",
        dataType : "json",
        data : formulario,
        url : 'AgregarCabysAjax.do',
        success : function(data) {
            if (data.status != 200) {
              	serverMessageJson(data);
                if (data.message != null && data.message.length > 0) {
                   mensajeAdvertencia(data.message)
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
                    Limpiar()
                }
            }
        ,
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
    self.update();
    __modificarRegistro("#formulario",$.i18n.prop("cabys.mensaje.alert.modificar"),'ModificarCabysAjax.do','ListarCabysActivasAjax.do','#tableListar')
}

/**
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarCabysAjax.do",
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
                __Mostrar_Listar()
                __Eventos()
             }else{
                 __Eventos()
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
                               {'data' : 'codigo',"name":"codigo" ,"title" : "Codigo","autoWidth" :false},
                               {'data' :'descripcion',"name":"descripcion" ,"title" : "Descripcion","autoWidth" :true },     
                               {'data' :'created_atSTR',"name":"created_atSTR" ,"title" : "Fecha Creacion","autoWidth" :true },
                               {'data' :'origenSTRCorte',"name":"origenSTRCorte" ,"title" : "Origen","autoWidth" :true ,
                                    "render":function(origenSTRCorte,type, row){
                                        return origenCabys(origenSTRCorte,row);//factura.js
                                }},     
                                {'data' : 'estado',"name":"estado"  ,"title" : "Estado","autoWidth" :false,
                                "render":function(estado,type, row){
                                 return estadosActivoInactivo(estado,row);//factura.js
                                }},
                               {'data' : 'id'            ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}

function origenCabys(origenSTRCorte,row){
  var mensaje  = '<a href="#"  title="Ver origen" class="btbVerorigen" role="button">Ver Origen </a>';
  return  mensaje ;   
}

/**
 * Funcion para Modificar del Listar
 */
function __OrgienDAtos(){
	$('#tableListar').on('click','.btbVerorigen',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.origen = data.origenSTR
        self.update()
        $('#modalVerOrigen').modal('show')


	});
}
                                    
/**
* Opciones listado de los clientes
*/
function __Opciones(){
   let menu = '<div class="dropdown">' 
	menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
	menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
	menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    menu += '<li><a href="#"  title="Modificar" class="  btnModificar" >Modificar</a></li>'
    menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
  return  menu;
}
/**
 * Funcion para Modificar del Listar
 */
function __Mostrar_Listar(){
	$('#tableListar').on('click','.btnMostrar',function(e){
        $("#formulario").validate(reglasDeValidacion());
        $(".errorServerSideJgrid").remove();
        $("#descripcion").val(null);
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        Limpiar()
        self.modoConsulta = true
        self.cabys = data
        self.mostrarListado = false;
        self.mostrarFormulario = true 
        self.botonModificar = false;
        self.botonAgregar = false;                        
        self.update()
	});
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 5    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="formCabys"  placeholder="'+title+'" />' );
	    }

        // Select
    	if ($(this).index() == 4  ){
    	    var select = $('<select id="combo" class="form-input"><option value="">Todos</option></select>');
    	    // se cargan los valores por defecto que existen en el combo
    	   	select.append( '<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+'</option>' );
            select.append( '<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+'</option>' );
    	   	$(this).html(select);
    	}
 
    })

}

/*******************************Lista de cabys de hacienda ***************/

__ConsultaHaciendaCabys(e){
    if(self.botonModificar == false){
        ListarCodigosCabysModal()
    }
    
}

function ListarCodigosCabysModal(){
    $(".tableListarHaciendaCabys").dataTable().fnClearTable();
    $(".tableListarHaciendaCabys").DataTable().destroy();
    $('#modalHaciendaCabys').modal('show')
    $('#modalHaciendaCabys').on('shown.bs.modal', function () {
        $('.descArticulo').select()
        $('.descArticulo').focus()
    })
 }
 
__ConsultaCodigoCabys(e){
    __ListaDeHaciendaCabys()
}


function __ListaDeHaciendaCabys(){
    if( $('#descArticulo').val() =='' && $('#codigoCabys').val() =='' ){
        return
    }
    var cantidadTemp = $('#cantidad').val() == 'Todos'?0:$('#cantidad').val()
    var  encontro = false
    $(".tableListarHaciendaCabys").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
    var parametros = {
        descArticulo :$('#descArticulo').val(),
        cantidad: cantidadTemp,
        codigo: $('#codigoCabys').val()
    };

    $.ajax({
        url: 'ListarCabysDeHaciendaAjax.do',
        datatype: "json",
        method:"GET",
        data :parametros,
        success: function (result) {
            if(result.aaData.length > 0){
                __InformacionDataTable_cabys()
                $.each(result.aaData, function( index, modeloTabla ) {
                   if(modeloTabla.cabys.length){
                      self.listaCabys.aaData =modeloTabla.cabys    
                      encontro = true 
                   } 
                   
                })
                self.update()
                if(encontro == true){
                   __cargarTablaCompras()
                }
                

            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

function __cargarTablaCompras() {
    __InicializarTabla('.tableListarHaciendaCabys')  
    $("#tableListarHaciendaCabys").dataTable().fnClearTable();
    __InformacionDataTable_cabys();
    $('#tableListarHaciendaCabys').DataTable().destroy();
    $("#tableListarHaciendaCabys").DataTable({
        destroy: true,
        "aLengthMenu": [
            [5, 10, 15, 25, -1],
            [5, 10, 15, 25, "All"]
        ],
        "language": idioma_espanol,
        "sDom": 'lfrtip',
        "order": [],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        "columns": self.informacion_tabla_cabys ,
    })
    $("#tableListarHaciendaCabys").dataTable().fnAddData(self.listaCabys.aaData);
    agregarInputsCombosCabys()
    ActivarEventoFiltro(".tableListarHaciendaCabys")
    __SeleccionarCabys()
    

}


/**
*Formato del listado de los cambios
**/
function __InformacionDataTable_cabys(){
    self.informacion_tabla_cabys = [ 
                               {'data' : 'id',"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : false,
                                "render":function(id,type, row){
                                      return __OpcionesCabys(id,type,row);
                                 }
	      		            },
                               {'data' : 'codigo',"name":"codigo" ,"title" : "Codigo","autoWidth" :false},
                               {'data' :'descripcion',"name":"descripcion" ,"title" : "Descripcion","autoWidth" :false ,
                                "render":function(descripcion,type, row){
                                      return __DescripcionCabys(descripcion,type,row);
                                 }

                               },
                               {'data' :'impuesto',"name":"impuesto" ,"title" : "Impuesto","autoWidth" :false }, 
];
    self.update();
/*                                   {'data' : 'categorias',"name":"categorias" ,"title" : "Origen del Articulo","bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(categorias,type, row){
                                      return __CategoriasCabys(categorias,type,row);
                                 }},
*/
   
}
function __CategoriasCabys(categorias,type,row){
    var categoriasString =  ""
    $.each(categorias, function( index, modeloTabla ) {
        categoriasString = categoriasString + modeloTabla ;
        categoriasString =  categoriasString +" \n"
    })
    return "<div class= 'categoriasdesc'>"+"<span>"+"<pre>"+categoriasString +"</pre>"+"</span>"+"</div>";

}
function __DescripcionCabys(descripcion,type,row){
   
    return "<div class= 'categoriasdesc'>"+"<textarea class='textAreaLeo' rows='2' readonly>"+descripcion +"</textarea>"+"</div>";

}

/**
* Opciones listado de los cabys
*/
function __OpcionesCabys(){
  var agregar  = '<a href="#"  class="btn btnAgregar btn-success selectLeo" title="Seleccionar" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar ;
}
function __SeleccionarCabys() {
     $('#tableListarHaciendaCabys').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListarHaciendaCabys').DataTable();
		if(table.row(this).child.isShown()){

	       var data = table.row(this).data();
	    }else{
	       var data = table.row($(this).parents("tr")).data();
	     }
        if(data !=null){
            moverDatos(data,function(resultado){
                console.log(resultado)
                $('#modalHaciendaCabys').modal('hide')
            })
            
            return
        }
    });
}
function moverDatos(data,callback){
   self.cabys.codigo = data.codigo
   self.cabys.descripcion = data.descripcion
   self.cabys.impuesto = data.impuesto
   self.cabys.uri = data.uri
    var categoriasString =  ""
    $.each(data.categorias, function( index, modeloTabla ) {
        categoriasString = categoriasString + modeloTabla ;
        categoriasString = categoriasString +" \n"
    })
    self.cabys.origen = categoriasString
    self.cabys.origenSTR = categoriasString
    
    self.cabys.estado = data.estado
    self.update()
    callback("Datos movidos")
}



function agregarInputsCombosCabys(){
    $('.tableListarHaciendaCabys tfoot th').each( function (e) {
        var title = $('.tableListarHaciendaCabys thead th').eq($(this).index()).text();

        if ( $(this).index() != 0    ){
	      	$(this).html( '<input  type="text" class="form-input"  placeholder="'+title+'" />' );
	    }
    })
}

</script>
</cabys-crud>