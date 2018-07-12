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
                            <th class="table-header" >{$.i18n.prop("empresa.numeroConsecutivo")} </th>
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
                            <th>{$.i18n.prop("empresa.numeroConsecutivo")} </th>
                            <th>{$.i18n.prop("empresa.estado")}            </th>
                            <th>                                           </th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>    
</div>
<!-- Fin del Listado -->


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
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("empresa.tipoCedula")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control" id="tipoCedula" name="tipoCedula" >
                                    <option  each={tipoCedulas.data}  value="{valor}" selected="{empresa.tipoCedula ==valor?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("empresa.cedula")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control cedula" placeHolder ="{$.i18n.prop("empresa.cedula")}" id="cedula" name="cedula" value="{empresa.cedula}"  >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("empresa.nombre")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control nombre" placeHolder ="{$.i18n.prop("empresa.nombre")}" id="nombre" name="nombre" value="{empresa.nombre}"  >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("empresa.nombreComercial")}</span></label>
                                <input type="text" class="form-control nombreComercial" placeHolder ="{$.i18n.prop("empresa.nombreComercial")}"  id="nombreComercial" name="nombreComercial" value="{empresa.nombreComercial}"  >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("empresa.representante")}</label>
                                <input type="text" class="form-control representante" placeHolder ="{$.i18n.prop("empresa.representante")}" id="representante" name="representante" value="{empresa.representante}"  >
                            </div>
                        </div>
                        <div class="row">    
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.codigoPais")}</label>
                                <input type="text" class="form-control codigoPais" placeHolder ="{$.i18n.prop("empresa.codigoPais.ejemplo")}" id="codigoPais" name="codigoPais" value="{empresa.codigoPais}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.telefono")} </label>
                                <input type="text" class="form-control telefono" placeHolder ="{$.i18n.prop("empresa.telefono")}" id="telefono" name="telefono" value="{empresa.telefono}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.casa.matriz")} </label>
                                <input type="text" class="form-control cazaMatriz" placeHolder ="{$.i18n.prop("empresa.casa.matriz")}" id="cazaMatriz" name="cazaMatriz" value="{empresa.cazaMatriz}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.numeroConsecutivo")}</label>
                                <input type="text" class="form-control numeroConsecutivo" placeHolder ="{$.i18n.prop("empresa.numeroConsecutivo")}" id="numeroConsecutivo" name="numeroConsecutivo" value="{empresa.numeroConsecutivo}"  >
                            </div>
                        </div>
                        <div class="row">    
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.provincia")} </label>
                                <select onchange= {__cargaCantones}  class="form-control" id="provincia" name="provincia" >
                                    <option  each={provincias.data}  value="{codigo}" selected="{empresa.provincia ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.canton")} </label>
                                <select onchange= {__cargaDistritos}    class="form-control" id="canton" name="canton" >
                                    <option  each={cantones.data}  value="{codigo}" selected="{empresa.canton ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.distrito")} </label>
                                <select  onchange= {__cargaBarrios}    class="form-control" id="distrito" name="distrito" >
                                    <option  each={distritos.data}  value="{codigo}" selected="{empresa.distrito ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.barrio")} </label>
                                <select     class="form-control" id="barrio" name="barrio" >
                                    <option  each={barrios.data}  value="{codigo}" selected="{empresa.barrio ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            
                        </div>
                        <div class="row">    
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.llave.criptografica")}</label>
                                <input type="text" class="form-control nombreLlaveCriptografica" placeHolder ="{$.i18n.prop("empresa.llave.criptografica.ejemplo")}" id="nombreLlaveCriptografica" name="nombreLlaveCriptografica" value="{empresa.nombreLlaveCriptografica}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.clave.llave.criptografica")}</label>
                                <input type="text" class="form-control claveLlaveCriptografica" placeHolder ="{$.i18n.prop("empresa.clave.llave.criptografica.ejemplo")}" id="claveLlaveCriptografica" name="claveLlaveCriptografica" value="{empresa.claveLlaveCriptografica}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.codigoSeguridad")}</label>
                                <input type="text" class="form-control codigoSeguridad" placeHolder ="{$.i18n.prop("empresa.codigoSeguridad.ejemplo")}" id="codigoSeguridad" name="codigoSeguridad" value="{empresa.codigoSeguridad}"  >
                            </div>
                             <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("empresa.correoElectronico")}</label>
                                <input type="text" class="form-control correoElectronico" placeHolder ="{$.i18n.prop("empresa.correoElectronico")}" id="correoElectronico" name="correoElectronico" value="{empresa.correoElectronico}"  >
                            </div>

                        </div>

                        <div class="row">    
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("empresa.otraSenas")}</label>
                                <textarea maxlength="250" placeHolder ="{$.i18n.prop("empresa.otraSenas")}" class="form-control otraSenas" id="otraSenas" name="otraSenas" value="{empresa.otraSenas}" > </textarea> 
                            </div>
                        </div>    
                        <div class="row">   
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("empresa.pagina.web")}</label>
                                <input type="text" class="form-control web" placeHolder ="{$.i18n.prop("empresa.pagina.web")}" id="web" name="web" value="{empresa.web}"  >
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
                    <div class="row">
                        <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
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
    self.tipoCedulas               = {data:[]}  // definir el data del datatable
    self.provincias                = {data:[]}  // definir el data del datatable
    self.cantones                  = {data:[]}  // definir el data del datatable
    self.distritos                 = {data:[]}  // definir el data del datatable
    self.barrios                   = {data:[]}  // definir el data del datatable
      
    self.comboProvincias = []
    self.empresa  = {
        id:null,
        nombre:"",
        nombreComercial:"",
        numeroConsecutivo:0000000000
    }


self.on('mount',function(){
    __InicializarTabla('.tableListar')
    __cargaProvincias()
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListar')
    __listado()
    __MantenimientoAgregar()
    __Eventos()
    __listadoTipoCedulas()
    __ComboEstados()
   window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
    
})

/**
*  Provincias
**/
function __cargaProvincias(){
    self.provincias                = {data:[]}  // definir el data del datatable
    self.update()
     $.ajax({
         url: "ListarProvinciasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.provincias.data =  result.aaData
                self.update();
                _ConsultarCantonesByProvincias()
            

            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })

}

/**
* cuando cambia la provincia cambia los cantones
**/
__cargaCantones(){
    _ConsultarCantonesByProvincias()
}
/**
*  Cantones
**/
function _ConsultarCantonesByProvincias(){
    var provincia = {
        id:null,
        codigo:$('#provincia').val(),
        descripcion:""
    }
    self.cantones  = {data:[]}
    self.update()
     $.ajax({
         url: "ListarCantonesAjax.do",
        datatype: "json",
        method:"GET",
        data : provincia,
        success: function (result) {
             if(result.aaData.length > 0){
                self.cantones.data =  result.aaData
                self.update();
                _ConsultarDistritosByCanton()

            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })

}

/**
* cuando cambia los cantones cambia los distritos
**/

__cargaDistritos(){
    _ConsultarDistritosByCanton()

}

/**
*  Cantones
**/
function _ConsultarDistritosByCanton(){
    var canton = {
        id:null,
        codigo:$('#canton').val(),
        codigo_provincia:$('#provincia').val(),
        descripcion:""
    }
    self.distritos  = {data:[]}
    self.update()
     $.ajax({
         url: "ListarDistritosAjax.do",
        datatype: "json",
        method:"GET",
        data : canton,
        success: function (result) {
             if(result.aaData.length > 0){
                self.distritos.data =  result.aaData
                self.update();
                _ConsultarBarriosByDistrito()
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })

}
/**
* cuando cambia los distritos cambia los barrios
**/
__cargaBarrios(){
    _ConsultarBarriosByDistrito()

}

/**
*  Cantones
**/
function _ConsultarBarriosByDistrito(){
    var distrito = {
        id:null,
        codigo:$('#distrito').val(),
        codigoProvincia:$('#provincia').val(),
        codigoCanton:$('#canton').val(),
        descripcion:""
    }
    self.barrios  = {data:[]}
    self.update()
     $.ajax({
         url: "ListarBarriosAjax.do",
        datatype: "json",
        method:"GET",
        data : distrito,
        success: function (result) {
             if(result.aaData.length > 0){
                self.barrios.data =  result.aaData
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
			tipoCedula : {
				required : true,
			},
            
			cedula : {
				required : true,
                maxlength:12,
                minlength:1,

			},
			otraSenas : {
                maxlength:250,
                minlength:1,
			},
			codigoPais : {
                maxlength:3,
                minlength:3,
                required : true,
                numeroMayorCero:true,
			},
            telefono : {
                maxlength:9,
                minlength:1,
                required : true,
                telefonoFormat:true
			},  
           correoElectronico : {
                maxlength:250,
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
                minlength:10,
                required : true,
                numeroMayorCero:true,
            },
            cazaMatriz:{
                minlength:3,
                maxlength:3,
                required : true,
                numeroMayorCero:true,
            },
            nombreLlaveCriptografica:{
                required : true,
            },
            claveLlaveCriptografica:{
                required : true,
            },
            codigoSeguridad:{
                required : true,
                minlength:8,
                numeroMayorCero:true,

            }
		},
		ignore : []

	});
	return validationOptions;
};


function _limpiar(){
   
   $("#tipoCedula").val($("#tipoCedula option:first").val());
   $("#provincia").val($("#provincia option:first").val());
   $("#canton").val($("#canton option:first").val());
   $("#distrito").val($("#distrito option:first").val());
   $("#barrio").val($("#barrio option:first").val());

   $('#cedula').val(null)
   $("#nombre").val(null)
   $("#nombreComercial").val(null)
   $("#representante").val(null)
   $("#codigoPais").val(506)
   $("#telefono").val(null)
   $("#cazaMatriz").val(null)
   $("#otraSenas").val(null)
   $("#web").val(null)
   $("#numeroConsecutivo").val(null)
   $("#nombreLlaveCriptografica").val(null)
   $("#claveLlaveCriptografica").val(null)
   $("#codigoSeguridad").val(null)
   $(".errorServerSideJgrid").remove();
   $("#formulario").validate(reglasDeValidacion())
    self.empresa  = {
        id:null,
        nombre:"",
        nombreComercial:"",
        numeroConsecutivo:0000000000
    }
    self.update()


}



/**
*  Crear el combo de estados
**/
function __ComboEstados(){
    self.estados =[]
    self.update()
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
    
    $("#otraSenas").attr("maxlength", 250);
    $("#codigoPais").attr("maxlength", 3);
    $("#cazaMatriz").attr("maxlength", 3);
    
    $("#correoElectronico").attr("maxlength", 250);
    $("#representante").attr("maxlength", 80);
    $("#web").attr("maxlength", 80);
    $("#codigoSeguridad").attr("maxlength", 8);

    $('#cazaMatriz').mask('000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    }); 
   $('#codigoSeguridad').mask('00000000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    });


   

    $('#claveLlaveCriptografica').mask('0000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    }); 
   
   

    $('#codigoPais').mask('000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    }); 
    $('#numeroConsecutivo').mask('0000000000', {
	    'translation' : {
            0 : {
                pattern : /[0-9]/
            }
    	}
    });         

    if($("#tipoCedula").val() == "01" ){
        $('#cedula').mask('000000000000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	    })

    
    }
    
    
 $('#telefono').mask('00000000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	})    
    // Patron de entrada de datos
    $("#tipoCedula").click(function() {
        // tipos de formato de cedula
        
        $("#tipoCedula").val() == "01" ? $('#cedula').mask('000000000000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
        }) : $('#cedula').mask('000000000000', {
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
    self.tipoCedulas               = {data:[]}  // definir el data del datatable
    self.update()
    self.tipoCedulas.data.push({
        valor:"01",
        descripcion:$.i18n.prop("tipo.cedula.fisica")
    })
   self.tipoCedulas.data.push({
        valor:"02",
        descripcion:$.i18n.prop("tipo.cedula.juridica")
    })
   self.tipoCedulas.data.push({
        valor:"03",
        descripcion:$.i18n.prop("tipo.cedula.dimex")
    })
     self.tipoCedulas.data.push({
        valor:"04",
        descripcion:$.i18n.prop("tipo.cedula.nite")
    })
    self.update()
}

/**
* Carga de estados 
**/    
function __ComboEstados(){
    self.estados = []
    self.update()
    self.estados.push({
        codigo:"Activo",
        descripcion:$.i18n.prop("combo.estado.Activo")
    })
    self.estados.push({
        codigo:"Inactivo",
        descripcion:$.i18n.prop("combo.estado.Inactivo")
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
                _limpiar()
                __listado();

            }
    });    
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
        _limpiar()
   
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
        _limpiar()
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
                        _limpiar()
                        self.mostrarListado   = false;
                        self.mostrarFormulario  = true 
                        //desahabilita boton modificar
                        self.botonModificar   = true;
                        // habilita el formulario
                        self.botonAgregar     = false;                        
                        self.empresa  =  modeloTabla
                        self.empresa.numeroConsecutivo = zfill(self.empresa.numeroConsecutivo,10)

                        self.update()
                       
                        $("#tipoCedula").val(self.empresa.tipoCedula);
                        $("#provincia").val(self.empresa.provincia);
                        $("#canton").val(self.empresa.canton);
                        $("#distrito").val(self.empresa.distrito);
                        $("#codigoSeguridad").val(zfill(self.empresa.codigoSeguridad,8));
                        $("#barrio").val(self.empresa.barrio);
                        $('#cedula').val(self.empresa.cedula)
                        $("#nombre").val(self.empresa.nombre)
                        $("#nombreComercial").val(self.empresa.nombreComercial)
                        $("#representante").val(self.empresa.representante)
                        $("#codigoPais").val(self.empresa.codigoPais)
                        $("#telefono").val(self.empresa.telefono)
                        $("#cazaMatriz").val(self.empresa.cazaMatriz)
                        $("#otraSenas").val(self.empresa.otraSenas)
                        $("#web").val(self.empresa.web)
                        $("#nombreLlaveCriptografica").val(self.empresa.nombreLlaveCriptografica)
                        $("#claveLlaveCriptografica").val(self.empresa.claveLlaveCriptografica)
                        $("#numeroConsecutivo").val(self.empresa.numeroConsecutivo)
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

function zfill(number, width) {
    number = number ==null?0:number
    var numberOutput = Math.abs(number); /* Valor absoluto del número */
    var length = number.toString().length; /* Largo del número */ 
    var zero = "0"; /* String de cero */  
    
    if (width <= length) {
        if (number < 0) {
             return ("-" + numberOutput.toString()); 
        } else {
             return numberOutput.toString(); 
        }
    } else {
        if (number < 0) {
            return ("-" + (zero.repeat(width - length)) + numberOutput.toString()); 
        } else {
            return ((zero.repeat(width - length)) + numberOutput.toString()); 
        }
    }
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
* Opciones listado de los empresas
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
        if ( $(this).index() != 7    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
         // Select
    	if ($(this).index() == 6  ){
    	    var select = $('<select id="combo" class="form-control"><option value="">Todos</option></select>');
    	    // se cargan los valores por defecto que existen en el combo
    	   	select.append( '<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+'</option>' );
            select.append( '<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+'</option>' );
    	   	$(this).html(select);
    	}
 
    })

}


</script>
</empresa-crud>