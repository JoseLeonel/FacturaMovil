<empresa-crud>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-cog"></i>&nbsp {$.i18n.prop("empresa.titulo")}  </h1>
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
                                <th class="table-header" >{$.i18n.prop("empresa.cedula")}            </th>
                                <th class="table-header" >{$.i18n.prop("empresa.nombre")}            </th>
                                <th class="table-header" >{$.i18n.prop("empresa.nombreComercial")}   </th>
                                <th class="table-header" >{$.i18n.prop("empresa.representante")}     </th>
                                <th class="table-header" >{$.i18n.prop("empresa.correoElectronico")} </th>
                                <th class="table-header" >{$.i18n.prop("empresa.estado")}            </th>
                                <th class="table-header" > {$.i18n.prop("listado.acciones")}         </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>{$.i18n.prop("empresa.cedula")}            </th>
                                <th>{$.i18n.prop("empresa.nombre")}            </th>
                                <th>{$.i18n.prop("empresa.nombreComercial")}   </th>
                                <th>{$.i18n.prop("empresa.representante")}     </th>
                                <th>{$.i18n.prop("empresa.correoElectronico")} </th>
                                <th>{$.i18n.prop("empresa.estado")}            </th>
                                <th>                                            </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>    
</div>
<!-- Fin del Listado -->

<div  >
    <div class="row center " show ={mostrarFormulario} >
    <div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-8 col-lg-8 col-sx-12 col-sm-8">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {empresa.id > 0 ? $.i18n.prop("empresa.modificar")   :$.i18n.prop("empresa.agregar")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{empresa.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                               
                            </div>
                            
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("empresa.clave")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control clave" id="clave" name="clave" value="{empresa.clave}"  >

                            </div>
                             <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("empresa.tipoCedula")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control" id="tipoCedula" name="tipoCedula" >
                                    <option  each={tipoCedulas.data}  value="{valor}"  >{descripcion}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("empresa.cedula")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control cedula" id="cedula" name="cedula" value="{empresa.cedula}"  >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("empresa.nombre")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control nombre" id="nombre" name="nombre" value="{empresa.nombre}"  >
                            </div>

                        </div>

                         <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("empresa.nombreComercial")}</span></label>
                                <input type="text" class="form-control nombreComercial" id="nombreComercial" name="nombreComercial" value="{empresa.nombreComercial}"  >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("empresa.representante")}</label>
                                <input type="text" class="form-control representante" id="representante" name="representante" value="{empresa.representante}"  >
                            </div>
                        </div>
                        <div class="row">    
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.codigoPais")}</label>
                                <input type="text" class="form-control codigoPais" id="codigoPais" name="codigoPais" value="{empresa.codigoPais}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.telefono")} </label>
                                <input type="text" class="form-control telefono" id="telefono" name="telefono" value="{empresa.telefono}"  >
                            </div>
                            
                        </div>
                        <div class="row">    
                            <div class= "col-md-3 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("empresa.otraSenas")}</label>
                                <input type="text" class="form-control otraSenas" id="otraSenas" name="otraSenas" value="{empresa.otraSenas}"  >
                            </div>
                        </div>
                        <div class="row">    
                            <div class= "col-md-3 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("empresa.pagina.web")}</label>
                                <input type="text" class="form-control web" id="web" name="web" value="{empresa.web}"  >
                            </div>
                        </div>
                        <div class="row">    
                            <div class= "col-md-3 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("empresa.numeroConsecutivo")}</label>
                                <input type="number" class="form-control numeroConsecutivo" id="numeroConsecutivo" name="numeroConsecutivo" value="{empresa.numeroConsecutivo}"  >
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label">{$.i18n.prop("empresa.estado")}</label>
                                <select  class="form-control" id="estado" name="estado" >
                                    <option  each={estados}  value="{codigo}" selected="{empresa.estado ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            

                        </div>

                    </form>    
                </div>
                <div class="box-footer">
                     <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
                     <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>
                    <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>

                  
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
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
    var self = this;
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del Listado de la Tabla 
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.tipoCedulas              = {data:[]}  // definir el data del datatable
    self.empresa  = {
        id:null,
        nombre:"",
        nombreComercial:""
    }


self.on('mount',function(){
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListar')
    __listado()
    __MantenimientoAgregar()
    __Eventos()
    __listadoTipoCedulas()
    __ComboEstados()
    
})

/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			nombre : {
				required : true,
                maxlength:79,
                minlength:1,
			},
			nombreComercial : {
				required : true,
                maxlength:80,
                minlength:1,
			},
			clave : {
				required : true,
                maxlength:50,
                minlength:1,
			},
			tipoCedula : {
				required : true,
			},
			otraSenas : {
                maxlength:160,
                minlength:1,
			},
			codigoPais : {
                maxlength:3,
                minlength:1,
			},
            telefono : {
                maxlength:9,
                minlength:1,
                telefonoFormat:true
			},  
           correoElectronico : {
                maxlength:160,
                minlength:1,
                email:true,
			},   
            representante : {
                maxlength:80,
                minlength:1,
			},                                    
            web : {
                maxlength:80,
                minlength:1,
              
			},
            numeroConsecutivo:{
                minlength:20,
                required : true,

            }                                    
                        
		},
		ignore : []

	});
	return validationOptions;
};


/**
*  Crear el combo de estados
**/
function __ComboEstados(){
    self.estados =[]
    self.estados.push({
        codigo: 1,
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
    self.estados.push({
        codigo: 2,
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
     self.update();

}
/**
*  Activar Eventos
**/

function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    //$("#nombre").attr("maxlength", 78);
    $("#telefono").attr("maxlength", 9);
    $("#cedula").attr("maxlength", 12);
    
    $("#nombreComercial").attr("maxlength", 80);
    $("#clave").attr("maxlength", 50);
    $("#otraSenas").attr("maxlength", 160);
    $("#codigoPais").attr("maxlength", 3);
    
    $("#correoElectronico").attr("maxlength", 160);
    $("#representante").attr("maxlength", 80);
    $("#web").attr("maxlength", 80);
    
    $('#codigoPais').mask('000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    }); 
    $('#numeroConsecutivo').mask('00000000000000000000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    });         

    if($("#tipoCedula").val() == "01" ){
        $('#cedula').mask('0-0000-0000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	    })

    
    }
    
 $('#telefono').mask('0000-0000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	})    
    // Patron de entrada de datos
    $("#tipoCedula").click(function() {
        // tipos de formato de cedula
        
        $("#tipoCedula").val() == "01" ? $('#cedula').mask('0-0000-0000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
        }) : $('#cedula').mask('000000000000000000', {
            'translation' : {
                0 : {
                    pattern : /[A-Za-z0-9]/
                }
            }
        });
    });
}

/**
*  Mostrar listado datatable TipoCedulas
**/
function __listadoTipoCedulas(){

    self.tipoCedulas.data.push({
        valor:"1",
        descripcion:"Fisica"
    })
   self.tipoCedulas.data.push({
        valor:"3",
        descripcion:"Juridica"
    })

}

/**
* Carga de estados 
**/    
function __ComboEstados(){
    self.estados = []
    self.estados.push({
        codigo:"Activo",
        descripcion:"Activo"
    })
    self.estados.push({
        codigo:"Inactivo",
        descripcion:"Inactivo"
    })
    self.update()
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
                self.mostrarListado     = true;
                self.botonAgregar       = false;
                self.botonModificar     = false;
                self.mostrarFormulario  = false 
                self.update()
                __listado();

            }
    });    
}

// Mostrar formulario de mantenimiento Agregar
function __MantenimientoAgregar(){
      //Inicializar el Formulario
    $('.dataTables_wrapper').on('click','.btn-agregar',function(e){
        self.empresa  = {
            id:null,
            nombre:"",
            nombreComercial:""
        }
        //desahabilita  listado 
        self.mostrarListado   = false;
        self.mostrarFormulario  = true 
        //desahabilita boton modificar
        self.botonModificar   = false;
        // habilita el formulario
        self.botonAgregar     = true;
        self.update();
        //Inicializar el Formulario
        $(".errorServerSideJgrid").remove();
        $("#formulario").validate(reglasDeValidacion());
   
    })
}





/**
 * Funcion para Modificar del Listar
 */
function __modificarRegistro_Listar(){
	$('#tableListar').on('click','.btnModificar',function(e){
        $("#formulario").validate(reglasDeValidacion());
        $(".errorServerSideJgrid").remove();

		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.empresa  = data
        self.update()
        __consultar()
	});
}
/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(){
    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarEmpresaAjax.do",
        datatype: "json",
        data: formulario,
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
                        self.mostrarListado   = false;
                        self.mostrarFormulario  = true 
                        //desahabilita boton modificar
                        self.botonModificar   = true;
                        // habilita el formulario
                        self.botonAgregar     = false;                        
                        self.empresa  =  modeloTabla
                        self.update()
                        __ComboEstados()
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
    __agregarRegistro("#formulario",$.i18n.prop("empresa.mensaje.alert.agregar"),'AgregarEmpresaAjax.do','ListarEmpresasAjax.do','#tableListar')
}

/**
** Modificar la Empresa
**/

__Modificar(){
    self.error = false;
    self.exito = false;
    self.update();
    __modificarRegistro("#formulario",$.i18n.prop("empresa.mensaje.alert.modificar"),'ModificarEmpresaAjax.do','ListarEmpresasAjax.do','#tableListar')
}

/**
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarEmpresasAjax.do",
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
    self.informacion_tabla = [ {'data' :'cedula'          ,"name":"cedula"               ,"title" : $.i18n.prop("empresa.cedula")            ,"autoWidth" :true },
                            {'data' :'nombre'             ,"name":"nombre"               ,"title" : $.i18n.prop("empresa.nombre")            ,"autoWidth" :false },
                            {'data' :'nombreComercial'    ,"name":"nombreComercial"      ,"title" : $.i18n.prop("empresa.nombreComercial")   ,"autoWidth" :false },
                            {'data' :'representante'      ,"name":"representante"        ,"title" : $.i18n.prop("empresa.representante")     ,"autoWidth" :false },
                            {'data' : 'correoElectronico' ,"name":"correoElectronico"    ,"title" : $.i18n.prop("empresa.correoElectronico") ,"autoWidth" :false},
                            {'data' : 'numeroConsecutivo' ,"name":"numeroConsecutivo"    ,"title" : $.i18n.prop("empresa.numeroConsecutivo") ,"autoWidth" :false},
                            {'data' : 'estado'            ,"name":"estado"               ,"title" : $.i18n.prop("empresa.estado")            ,"autoWidth" :false},
                            {'data' : 'id'                ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
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
        if ( $(this).index() != 6    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
 
    })

}



</script>
</empresa-crud>