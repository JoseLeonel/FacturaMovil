<abrir-caja>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-unlock"></i>&nbsp {$.i18n.prop("usuarioCaja.titulo")}  </h1>
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
                                <th class="table-header" style="width:2%" >{$.i18n.prop("usuarioCaja.caja")}          </th>
                                <th class="table-header" style="width:10%">{$.i18n.prop("usuarioCaja.created_at")}    </th>
                                <th class="table-header" style="width:10%">{$.i18n.prop("usuarioCaja.updated_at")}    </th>
                                <th class="table-header" style="width:10%">{$.i18n.prop("usuarioCaja.usuario")}       </th>
                                <th class="table-header"  style="width:10%">{$.i18n.prop("usuarioCaja.fondoIncial")}   </th>
                                <th class = "table-header" >{$.i18n.prop("listado.acciones")}                 </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th style="width:10%">{$.i18n.prop("usuarioCaja.caja")}          </th>
                                <th style="width:10%">{$.i18n.prop("usuarioCaja.created_at")}    </th>
                                <th style="width:10%">{$.i18n.prop("usuarioCaja.updated_at")}    </th>
                                <th style="width:10%">{$.i18n.prop("usuarioCaja.usuario")}       </th>
                                <th style="width:10%">{$.i18n.prop("usuarioCaja.fondoIncial")}   </th>
                                <th  > </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>    
</div>
<!-- Fin del Listado -->
<div >
    <div class="row center " show ={mostrarFormulario} >
    <div class="col-md-2 col-sx-12 col-lg-4 col-sm-2"></div>
        <div class="col-md-12 col-lg-4 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {$.i18n.prop("titulo.agregar.usuarioCaja")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{usuarioCaja.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.caja")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control" id="caja" name="caja" >
                                    <option  each={cajas.aaData}  value="{id}"  >{descripcion}</option>
                                </select>
                            </div>
                        </div>    
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.fondoIncial")}  <span class="requeridoDato">*</span></label>
                                <input type="number" step="any" class="form-control totalFondoInicial" id="totalFondoInicial" name="totalFondoInicial" value="{usuarioCaja.totalFondoInicial}"  >
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado} type="button" class="btn-dark-gray btn-back pull-left " >
                                {$.i18n.prop("btn.volver")}
                            </button>
                       </div>
                        <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                          <button onclick={__agregar} class="btn-green btn-add pull-right" > {$.i18n.prop("btn.agregar")}</button>
                        </div>
                        
                    </div>   
                  
                </div>
            </div>   
        </div>
        <div class=" col-lg-4 "></div>
    </div>
</div>


<div id='modalCerrarCaja' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Cierre de Caja y Conteo Manual de los billetes / monedas</h1>
            </div>
            <div class="modal-body">
                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-6 col-lg-12">
                            <label class="tituloClienteNuevo" >FondoInicial</label>
                            <input type="number" class="form-control totalFondoInicial tamanoClienteNuevo modalInputCambioPrecioCodigoDescripcion " readonly  id="totalFondoInicial" name="totalFondoInicial">
                        </div>
                    </div>
                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-6 col-lg-12">
                            <label class="tituloClienteNuevo" >Total Conteo Manual</label>
                            <input type="number" class="form-control totalConteoManual tamanoClienteNuevo modalInputCambioPrecioCodigoDescripcion " readonly  id="totalConteoManual" name="totalConteoManual">
                        </div>
                    </div>

                    <div class="row">    
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Billetes 50,000</label>
                            <input type="number" class="form-control billete50000 tamanoClienteNuevo modalInputCambioPrecio"  id="billete50000" name="billete50000" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Billetes 20,000</label>
                            <input type="number" class="form-control billete20000 tamanoClienteNuevo modalInputCambioPrecio"  id="billete20000" name="billete20000" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Billetes 10,000</label>
                            <input type="number" class="form-control billete10000 tamanoClienteNuevo modalInputCambioPrecio"  id="billete10000" name="billete10000" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                    </div>
                    <div class="row">    
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Billetes 5,000</label>
                            <input type="number" class="form-control billete5000 tamanoClienteNuevo modalInputCambioPrecio"  id="billete5000" name="billete5000" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Billetes 2,000</label>
                            <input type="number" class="form-control billete2000 tamanoClienteNuevo modalInputCambioPrecio"  id="billete2000" name="billete2000" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Billetes 1,000</label>
                            <input type="number" class="form-control billete1000 tamanoClienteNuevo modalInputCambioPrecio"  id="billete1000" name="billete1000" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                    </div>
                    <div class="row">    
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Moneda 500</label>
                            <input type="number" class="form-control moneda500 tamanoClienteNuevo modalInputCambioPrecio"  id="moneda500" name="moneda500" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Moneda 100</label>
                            <input type="number" class="form-control moneda100 tamanoClienteNuevo modalInputCambioPrecio"  id="moneda100" name="moneda100" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Moneda 50</label>
                            <input type="number" class="form-control moneda50 tamanoClienteNuevo modalInputCambioPrecio"  id="moneda50" name="moneda50" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                    </div>
                    <div class="row">    
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Moneda 25</label>
                            <input type="number" class="form-control moneda25 tamanoClienteNuevo modalInputCambioPrecio"  id="moneda25" name="moneda25" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Moneda 5</label>
                            <input type="number" class="form-control moneda5 tamanoClienteNuevo modalInputCambioPrecio"  id="moneda5" name="moneda5" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Cierre Datafono/Tarjeta</label>
                            <input type="number" class="form-control conteoTarjeta tamanoClienteNuevo modalInputCambioPrecio"  id="conteoTarjeta" name="conteoTarjeta" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                        </div>                            
                    </div>

            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button onclick={__RegresarInputCodigo}   type="button" class="btn-dark-gray btn-back  pull-left modalCambioPrecioBotones"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__AplicarCambioPrecioUltimoArticulo}   class=" btn-green pull-right modalCambioPrecioBotones" > Cerrar Caja </button>
                </div>
            </div>
        </div>
    </div>
</div>



<style type ="text/css">
    .tamanoClienteNuevo{
        font-size: 30px;
        font-weight: 600;
        color: black;
        height: 10%;

    }
    .modalTitleCambioPrecio{
        color: white;
    }
    .modalInputCambioPrecioCodigoDescripcion{
       border-radius: 10px !important;
       font-size: 40px !important;
       text-align: center !important;
    
    }
    .modalInputCambioPrecio{
        font-size: 70px !important;
        color:blue !important;
        border-radius: 16px !important;
    }
    .modalCambioPrecioBotones{
         border-radius: 16px !important;
    }

    .fontSumarRestar{
        font-size: 20px;
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
    self.cajas                  = {aaData:[]}
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarVerDetalle         = false
    self.mostrarConsultaComanda    = false
    self.caja = {
        id:null,
        descripcion:"",
        estado:""
    }
self.on('mount',function(){
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListar')
    __listado()
    includeActions('.dataTables_wrapper','.dataTables_length')
    __MantenimientoAgregar()
    __Eventos()
    __listadoCajasActivas()
    eventoKeyPressConteo()






})

function eventoKeyPressConteo(){
     var xTriggered = 0;
     $( "#billete50000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });
     $( "#billete20000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });

     $( "#billete10000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });

     $( "#billete5000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });

     $( "#billete2000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });

     $( "#billete1000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });

        $( "#moneda500" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });
       $( "#moneda100" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });
     $( "#moneda50" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });
     $( "#moneda25" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });
     $( "#moneda10" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });
     $( "#moneda5" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });
        $( "#conteoTarjeta" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
               conteoDinero();
        });

}

function conteoDinero(){
    var billete50000 = __valorNumerico($(".billete50000").val()) * 50000
    var billete20000 = __valorNumerico($(".billete20000").val()) * 20000
    var billete10000 = __valorNumerico($(".billete10000").val()) * 10000
    var billete5000 = __valorNumerico($(".billete5000").val()) * 5000
    var billete2000 = __valorNumerico($(".billete2000").val()) * 2000
    var billete1000 = __valorNumerico($(".billete1000").val()) * 1000
    var moneda500 = __valorNumerico($(".moneda500").val()) * 500
    var moneda100 = __valorNumerico($(".moneda100").val()) * 100
    var moneda50 = __valorNumerico($(".moneda50").val()) * 50
    var moneda25 = __valorNumerico($(".moneda25").val()) * 25
    var moneda10 = __valorNumerico($(".moneda10").val()) * 10
    var moneda5 = __valorNumerico($(".moneda5").val()) * 5
    var conteoTarjeta = __valorNumerico($(".conteoTarjeta").val())
    $(".totalConteoManual").val(billete50000 + billete10000 + billete20000 + billete5000 + billete2000 + billete1000  + moneda500 + moneda100 + moneda50 + moneda25  + moneda10 + moneda5 + conteoTarjeta)



    
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
			},                                   
            terminal : {
				required : true,
                maxlength:3,
                minlength:3,
			}                             
		},
		ignore : []

	});
	return validationOptions;
}
/**
*  Mostrar listado datatable Cajas Activas
**/
function __listadoCajasActivas(){
    self.cajas                  = {aaData:[]}
    $.ajax({
         url: "ListarCajasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.cajas.aaData =  result.aaData
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
*  Activar Eventos
**/
function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#descripcion").attr("maxlength", 80);
    $('#terminal').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
}
/**
*  Regresar al listado
**/
__regresarAlListado(){
    self.mostrarListado     = true;
    self.botonAgregar       = false;
    self.mostrarFormulario  = false 
    self.update()
    __listado();
}
// Mostrar formulario de mantenimiento Agregar
function __MantenimientoAgregar(){
      //Inicializar el Formulario
    $('.dataTables_wrapper').on('click','.btn-agregar',function(e){
        self.caja    = {};                // modelo o domain   
        self.usuarioCaja = {
            id:null,
            totalFondoInicial:0
        }
        //desahabilita  listado 
        self.mostrarListado   = false;
        self.mostrarFormulario  = true 
        // habilita el formulario
        self.botonAgregar     = true;
        self.update();
        //Inicializar el Formulario
        $(".errorServerSideJgrid").remove();
        $("#formulario").validate(reglasDeValidacion());
    })
}
/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(){
    var formulario = $('#formularioUsuarioCaja').serialize();
    $.ajax({
        url: "MostrarUsuarioCajaAjax.do",
        datatype: "json",
        data: formulario,
        method:"Post",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.mostrarVerDetalle = true
                        self.mostrarConsultaComanda = false
                        if(modeloTabla.caja.empresa.comandaEmpresa != null && modeloTabla.caja.empresa.comandaEmpresa > 0){
                            self.mostrarConsultaComanda = true                        	
                        }
                        self.mostrarListado   = false;
                        self.usuarioCaja  = modeloTabla
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
*   Agregar 
**/
__agregar(){
    if ($("#formulario").valid()) {
        var formulario = $("#formulario").serialize();
        $.ajax({
            type : "POST",
            dataType : "json",
            data : formulario,
            url : 'AgregarUsuarioCajaAjax.do',
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJson(data);
                if (data.message != null && data.message.length > 0) {
                   	swal({
                        title: '',
                        text: data.message,
                        type: 'error',
                        showCancelButton: false,
                        confirmButtonText: $.i18n.prop("btn.aceptar"),
                    })
                }
            } else {
               	serverMessageJson(data);
                swal({
	                title: '',
	                text: data.message,
	                type: 'success',
	                showCancelButton: false,
	                confirmButtonText: $.i18n.prop("btn.aceptar"),
	            })
	            $("#formulario").validate(reglasDeValidacion());
                $(".errorServerSideJgrid").remove();
                $("#descripcion").val(null);
                $("#terminal").val(null);
                __Eventos()
            }
        },
        error : function(xhr, status) {
            mensajeErrorServidor(xhr, status);
        }
    });
    }
}
/**
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarUsuariosCajasAjax.do",
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
                __cerrarCaja()
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
                               {'data' : 'caja'        ,"name":"caja"  ,"title" : $.i18n.prop("usuarioCaja.caja")  ,"autoWidth" :false,
                                    "render":function(caja,type, row){
                                        return caja == null?"":caja.descripcion;
                                    }
                                },
                               {'data' : 'created_atSTR'        ,"name":"created_atSTR"  ,"title" : $.i18n.prop("usuarioCaja.created_at")  ,"autoWidth" :false
                                },
                                {'data' : 'updated_atSTR'        ,"name":"updated_atSTR" ,"title" : $.i18n.prop("usuarioCaja.updated_at")  ,"autoWidth" :false},
                               {'data' : 'usuario'       ,"name":"usuario"         ,"title" : $.i18n.prop("usuarioCaja.usuario")     ,"autoWidth" :false,
                                    "render":function(usuario,type, row){
                                        return usuario.nombreUsuario;
                                    }
                               },
                               {'data' : 'totalFondoInicialSTR'        ,"name":"totalFondoInicialSTR"  ,"title" : $.i18n.prop("usuarioCaja.fondoIncial")  ,"autoWidth" :false},
                               {'data' : 'id'           ,"name":"id"       ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __OpcionesFacturas(id,type,row);
                                 }}

	      		            ];
    self.update();
   
}
/**
Opcions del menu
**/
function __OpcionesFacturas(id,type,row){
   var cerrar  = '<a href="#"  title="Cerrar Caja" class="btn btn-danger  btn-cerrar btnCerrarCaja" role="button"> </a>';
   cerrar = row.estado =="Activo"?cerrar:""
  return  cerrar ;

}

/**
Formato de montos
**/
function FormatoMontos(valor){
   var resultado = __valorNumerico(valor)
   return resultado;
}

/**
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY h:mm:ss');
}                                    

/**
 * Funcion para Modificar del Listar
 */
function __cerrarCaja(){
	$('#tableListar').on('click','.btnCerrarCaja',function(e){
    	var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.usuarioCaja  = data
        self.update()
        $('#modalCerrarCaja').modal({backdrop: 'static', keyboard: true}) 
        $('#modalCerrarCaja').on('shown.bs.modal', function () {
            $(".totalFondoInicial").val( self.usuarioCaja.totalFondoInicial)
            $(".billete50000").val(0)
            $(".billete50000").focus()
            $(".billete50000").select()
        })  
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
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
 
    })

}

 
</script>
</abrir-caja>