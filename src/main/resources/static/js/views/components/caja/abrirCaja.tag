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


<div id='modalCerrarCaja' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> {usuarioCaja.id != null ?"Cierre de Caja y Conteo Manual de los billetes / monedas": "Apertura de la Caja"} </h1>
            </div>
            <div class="modal-body">
                    <div class="row">
                        <div class= "col-md-12 col-sx-12 col-sm-6 col-lg-12">
                            <label class="tituloClienteNuevo" >FondoInicial</label>
                            <input type="number" class="form-control totalFondoInicial tamanoClienteNuevo modalInputCambioPrecioCodigoDescripcion " readonly  id="totalFondoInicial" name="totalFondoInicial">
                        </div>
                    </div>
                    <div class="row" show = {usuarioCaja.id != null ? true:false}>
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
                            <label class="tituloClienteNuevo" >Moneda 10</label>
                            <input type="number" class="form-control moneda10 tamanoClienteNuevo modalInputCambioPrecio"  id="moneda10" name="moneda10" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Moneda 5</label>
                            <input type="number" class="form-control moneda5 tamanoClienteNuevo modalInputCambioPrecio"  id="moneda5" name="moneda5" autofocus="autofocus"   autocomplete="off">
                        </div>                            

                    </div>
                    <div class="row" show = {usuarioCaja.id != null ? true:false}>    
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Cierre Datafono/Tarjeta</label>
                            <input type="number" class="form-control conteoTarjeta tamanoClienteNuevo modalInputCambioPrecio"  id="conteoTarjeta" name="conteoTarjeta" autofocus="autofocus"   autocomplete="off">
                        </div>                            

                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Tipo Cambio Dolar</label>
                            <input type="number" class="form-control tipoCambio tamanoClienteNuevo modalInputCambioPrecio"  id="tipoCambio" name="tipoCambio" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            <label class="tituloClienteNuevo" >Dolares</label>
                            <input type="number" class="form-control conteoDolar tamanoClienteNuevo modalInputCambioPrecio"  id="conteoDolar" name="conteoDolar" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                    </div>

            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button onclick={__Regresar}   type="button" class="btn-dark-gray btn-back  pull-left modalCambioPrecioBotones"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  show = {usuarioCaja.id == null ? true:false}  onclick={__agregar}   class=" btn-green pull-right modalCambioPrecioBotones" > Agregar Apertura </button>
                    <button  show = {usuarioCaja.id != null ? true:false}  onclick={__cierre}   class=" btn-green pull-right modalCambioPrecioBotones" > Cerrar Caja </button>
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
        font-size: 40px !important;
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
    self.usuarioCaja = {
            id:null,
            totalFondoInicial:0
        }
self.on('mount',function(){
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListar')
    __listado()
    includeActions('.dataTables_wrapper','.dataTables_length')
    __MantenimientoAgregar()
    __listadoCajasActivas()
    eventoKeyPressConteo()

})

__Regresar(){
    hidemodal()
}

function hidemodal(){
    $('#modalCerrarCaja').modal('hide')
}


function eventoKeyPressConteo(){
     var xTriggered = 0;
     $( "#billete50000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });
     $( "#billete20000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });

     $( "#billete10000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });

     $( "#billete5000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });

     $( "#billete2000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });

     $( "#billete1000" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });

        $( "#moneda500" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });
       $( "#moneda100" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });
     $( "#moneda50" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });
     $( "#moneda25" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });
     $( "#moneda10" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });
     $( "#moneda5" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
            conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });
        $( "#conteoTarjeta" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });

}

function __limpiar(){
    $(".billete50000").val(null)
    $(".billete20000").val(null)
    $(".billete10000").val(null)
    $(".billete5000").val(null)
    $(".billete2000").val(null)
    $(".billete1000").val(null)
    $(".moneda500").val(null)
    $(".moneda100").val(null)
    $(".moneda50").val(null)
    $(".moneda25").val(null)
    $(".moneda10").val(null)
    $(".moneda5").val(null)
    $(".conteoTarjeta").val(null)
    $(".tipoCambio").val(null)
    $(".conteoDolar").val(null)
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
    
    if(self.usuarioCaja.id != null){
        var conteoTarjeta = __valorNumerico($(".conteoTarjeta").val())
        $(".totalConteoManual").val(billete50000 + billete10000 + billete20000 + billete5000 + billete2000 + billete1000  + moneda500 + moneda100 + moneda50 + moneda25  + moneda10 + moneda5 + conteoTarjeta)
    }else{
        $(".totalFondoInicial").val(billete50000 + billete10000 + billete20000 + billete5000 + billete2000 + billete1000  + moneda500 + moneda100 + moneda50 + moneda25  + moneda10 + moneda5 )
    }
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
        showModal()
    })
}
function showModal(){
    $('#modalCerrarCaja').modal({backdrop: 'static', keyboard: true}) 
    $('#modalCerrarCaja').on('shown.bs.modal', function () {
        __limpiar()
        $(".totalFondoInicial").val( self.usuarioCaja.totalFondoInicial)
        $(".billete50000").val(0)
        $(".billete50000").focus()
        $(".billete50000").select()
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
        var formulario = {
             denominacion :JSON.stringify(__CrearListaMonedas(1))
         }
        swal({
           title: '',
           text: "Desea abrir la caja con ese monto  digitado",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
        }).then(function (isConfirm) {
            //Ajax__inicializarTabla();
            if(isConfirm){
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : 'AgregarUsuarioCajaAjax.do',
                    success : function(data) {
                        if (data.status != 200) {
                        	serverMessageJson(data);
                            if (data.message != null && data.message.length > 0) {
                                mensajeAdvertencia(data.message)
                            }
                        } else {
                            mensajeToasExito(data.message)
                             __listado()
                             hidemodal()
                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
                    }
                });
            }
        });
      
  
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
        showModal()
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

__cierre(){
    cerrarCajaAjax()
}



/**
*Cerrar caja
**/
function cerrarCajaAjax(){
        var formulario = {
             id:self.usuarioCaja.id,
             caja:self.usuarioCaja.caja.id,
             conteoTarjeta:$(".conteoTarjeta").val(),
             tipoCambio:$(".tipoCambio").val(),
             conteoDolar:$(".conteoDolar").val(),
             denominacion :JSON.stringify(__CrearListaMonedas(2))
         }
        swal({
           title: '',
           text: $.i18n.prop("usuarioCaja.mensaje.alert.cerrar"),
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
        }).then(function (isConfirm) {
            //Ajax__inicializarTabla();
            if(isConfirm){
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : 'CerrarUsuarioCajaAjax.do',
                    success : function(data) {
                        if (data.status != 200) {
                        	serverMessageJson(data);
                            if (data.message != null && data.message.length > 0) {
                                mensajeAdvertencia(data.message)
                            }
                        } else {
                            mensajeToasExito(data.message)
                             __listado()
                             hidemodal()
                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
                    }
                });
            }
        });
}

/**
* Tipo  2 = Conteo de Cierre  1 = Conteo de Apertura
**/
function __CrearListaMonedas(tipo){
    var lista = {data:[]};
    lista.data.push({
        tipo:tipo,
        denominacion: "50,000",
        cantidad: __valorNumerico($(".billete50000").val() ),
        total:__valorNumerico($(".billete50000").val()) * 50000
    })
    lista.data.push({
        tipo:tipo,
        denominacion: "20,000",
        cantidad: __valorNumerico($(".billete20000").val() ),
        total:__valorNumerico($(".billete20000").val()) * 20000
    })
    lista.data.push({
        tipo:tipo,
        denominacion: "10,000",
        cantidad: __valorNumerico($(".billete10000").val() ),
        total:__valorNumerico($(".billete10000").val()) * 10000
    })
    lista.data.push({
        tipo:tipo,
        denominacion: "5,000",
        cantidad: __valorNumerico($(".billete5000").val() ),
        total:__valorNumerico($(".billete5000").val()) * 5000
    })
    lista.data.push({
        tipo:tipo,
        denominacion: "2,000",
        cantidad: __valorNumerico($(".billete2000").val() ),
        total:__valorNumerico($(".billete2000").val()) * 2000
    })
    lista.data.push({
        tipo:tipo,
        denominacion: "1,000",
        cantidad: __valorNumerico($(".billete1000").val() ),
        total:__valorNumerico($(".billete1000").val()) * 1000
    })
    lista.data.push({
        tipo:tipo,
        denominacion: "500",
        cantidad: __valorNumerico($(".moneda500").val() ),
        total:__valorNumerico($(".moneda500").val()) * 500
    })
    lista.data.push({
        tipo:tipo,
        denominacion: "100",
        cantidad: __valorNumerico($(".moneda100").val() ),
        total:__valorNumerico($(".moneda100").val()) * 100
    })
    lista.data.push({
        tipo:tipo,
        denominacion: "50",
        cantidad: __valorNumerico($(".moneda50").val() ),
        total:__valorNumerico($(".moneda50").val()) * 50
    })
    lista.data.push({
        tipo:tipo,
        denominacion: "25",
        cantidad: __valorNumerico($(".moneda25").val() ),
        total:__valorNumerico($(".moneda25").val()) * 25
    })
    lista.data.push({
        tipo:tipo,
        denominacion: "10",
        cantidad: __valorNumerico($(".moneda10").val() ),
        total:__valorNumerico($(".moneda10").val()) * 10
    })
    lista.data.push({
        tipo:tipo,
        denominacion: "5",
        cantidad: __valorNumerico($(".moneda5").val() ),
        total:__valorNumerico($(".moneda5").val()) * 5
    })

    return lista;

}

 
</script>
</abrir-caja>