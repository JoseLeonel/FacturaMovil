<abrir-cajaMovil>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-unlock"></i>&nbsp {$.i18n.prop("usuarioCaja.titulo")}  </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
    
<!-- Listado  -->
<div classs="contenedor-listar container" id="container"  show={mostrarListado}  >
    <div class="box">
		<div class="box-body">
			<div class="planel-body">
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
                                <th class="table-header"  style="width:10%">Ventas   </th>
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
                                <th style="width:10%">Ventas   </th>
                                <th  > </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>   
         </div>   
          </div>   
           </div>    
</div>
<br>
<br>
<br>


<div id='modalCerrarCaja' class="modal fade tamanoModal " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header with-border encabezado-pantalla " >
                <span class="modal-title tituloModalCierreCaja" id="title-add-note"> <i class='fa fa-cal '></i> {usuarioCaja.id != null ?"Cierre de Caja de las ventas": "Apertura de la Caja"} </span>
            </div>
            <div class="modal-body">
                
                     <input type="hidden" name="totalFondoInicial" id="totalFondoInicial" value="{totalFondoInicial}">
                      <input type="hidden" name="totalConteoManual" id="totalConteoManual" value="{totalConteoManual}">
                    <div class="principal-totales">
                        <div class= "elemento-total">
                            <div><span class="" >FondoInicial</span></div>
                            <div><span class="colorTotal" >{totalFondoInicialSTR}</span></div>
                        </div>
                   
                    
                        <div class= "elemento-total" show = {usuarioCaja.id != null ? true:false}>
                            <div><span class="" >Total Conteo Manual</span></div>
                            <div><span class="colorTotal" >{totalConteoManualSTR}</span></div>
                            
                            
                        </div>
                    </div>

                    <div class="principal">    
                        <div class= "elemento">
                            <span  >Billetes 50,000</span>
                            <input type="number" class=" billete50000  "  id="billete50000" name="billete50000" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "elemento">
                            <span class="" >Billetes 20,000</span>
                            <input type="number" class="billete20000  "  id="billete20000" name="billete20000" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "elemento">
                            <span class="" >Billetes 10,000</span>
                            <input type="number" class="billete10000  "  id="billete10000" name="billete10000" autofocus="autofocus"   autocomplete="off">
                        </div>
                        <div class= "elemento">
                            <span class="" >Billetes 5,000</span>
                            <input type="number" class=" billete5000  "  id="billete5000" name="billete5000" autofocus="autofocus"   autocomplete="off">
                        </div>                              
                    
                                                  
                        <div class= "elemento">
                            <span class="" >Billetes 2,000</span>
                            <input type="number" class="billete2000  "  id="billete2000" name="billete2000" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "elemento">
                            <span class="" >Billetes 1,000</span>
                            <input type="number" class=" billete1000  "  id="billete1000" name="billete1000" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "elemento">
                            <span class="" >Moneda 500</span>
                            <input type="number" class=" moneda500  "  id="moneda500" name="moneda500" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "elemento">
                            <span class="" >Moneda 100</span>
                            <input type="number" class="moneda100  "  id="moneda100" name="moneda100" autofocus="autofocus"   autocomplete="off">
                        </div>                            
    
                  
                        <div class= "elemento">
                            <span class="" >Moneda 50</span>
                            <input type="number" class=" moneda50  "  id="moneda50" name="moneda50" autofocus="autofocus"   autocomplete="off">
                        </div>    
                          <div class= "elemento">
                            <span class="" >Moneda 25</span>
                            <input type="number" class=" moneda25  "  id="moneda25" name="moneda25" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "elemento">
                            <span class="" >Moneda 10</span>
                            <input type="number" class=" moneda10  "  id="moneda10" name="moneda10" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "elemento">
                            <span class="" >Moneda 5</span>
                            <input type="number" class=" moneda5  "  id="moneda5" name="moneda5" autofocus="autofocus"   autocomplete="off">
                        </div>                          
                   
                 
                
                        <div class= "elemento" show = {usuarioCaja.id != null ? true:false}>
                            <span class="" >Cierre Datafono + SINPE</span>
                            <input type="number" class="form-control conteoTarjeta tamnnoinput  "  id="conteoTarjeta" name="conteoTarjeta" autofocus="autofocus"   autocomplete="off">
                        </div>                            

                        <div class= "elemento" show = {usuarioCaja.id != null ? true:false}>
                            <span class="" >Tipo Cambio Dolar Recibido</span>
                            <input type="number" class="form-control tipoCambio  tamnnoinput"  id="tipoCambio" name="tipoCambio" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                        <div class= "elemento" show = {usuarioCaja.id != null ? true:false}>
                            <span class="" >Cantidad Dolares</span>
                            <input type="number" class="form-control conteoDolar  tamnnoinput "  id="conteoDolar" name="conteoDolar" autofocus="autofocus"   autocomplete="off">
                        </div>                            
                     </div>

            </div>
                    <div class="modal-footer">
                <div class="row">    
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button onclick={__Regresar}   type="button" class="btn-dark-gray btn-back  pull-left modalCambioPrecioBotones"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  show = "{usuarioCaja.id == null ? true:false}"  onclick={__agregar}   class=" btn-green pull-right modalCambioPrecioBotones" > Agregar Apertura </button>
                    <button  show = "{usuarioCaja.id != null ? true:false}"  onclick={__cierre}   class=" btn-green pull-right modalCambioPrecioBotones" > Cerrar Caja </button>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>




<style type ="text/css">
.tituloModalCierreCaja{
    font-size: 20px;
    font-weight: 800;
}
.colorTotal{
    color: #48a66a!important;
    font-weight: 900!important;
}
.principal-totales{
    display: flex;
    justify-content: space-between;
    flex-wrap: nowrap;
    flex-direction: row;
}
.elemento-total{
    display: flex;
    flex-direction: column;
}
.elemento-total > input{
     font-size: 16px !important;
    font-weight: 600;
    height: 80px;
    width: 180px;
    color:#2624aa!important
}
.elemento-total{
    font-size:16px;
    text-align: center;
}
.principal{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-around;
}
.elemento-total > span{
    font-size: 16px !important;
    font-weight: 600;
}
.elemento{  
    margin-right: 5px;
    margin-bottom: 5px;
    display: flex;
    flex-direction: column;
}
.elemento > input{
    font-size: 16px !important;
    font-weight: 500;
    height: 35px;
    width: 100px;
    color:#2624aa!important;
    text-align: center!important;
}

.tamnnoinput{
    width: 185px!important;
}
.elemento > span{
    font-size: 16px !important;
    font-weight: 500;
}
.tamanoModal{
   
}
.table-headerEntrada {
    background: #399c58;
    color: #ffffff;
}
.table-headerSalida {
    background: red;
    color: #ffffff;
}

.modal-body{
    height: 400px;
    overflow-y: auto;
}
label {
    display: inline-block!important;
    max-width: 100%!important;
    margin-bottom: 5px!important;
    font-size: 20px!important;
}
.modalInputCambioPrecio {
    font-size: 26px !important;
    color: blue !important;
    border-radius: 16px !important;
    border-color: green;
    font-weight: 800!important;
}

    .tamanoClienteNuevo{
        font-size: 26px;
        font-weight: 600;
        color: black;
        height: 10%;

    }
    .modalTitleCambioPrecio{
        color: white;
    }
    .modalInputCambioPrecioCodigoDescripcion{
       border-radius: 10px !important;
       font-size: 28px !important;
       text-align: center !important;
    
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
        .btn-cerrar {
          background-color: #dc2a14;
          color: #FFF;
          border-radius: 5px;
          padding-bottom: 10px;
          padding-top: 10px;
          padding-left: 20px;
          padding-right: 20px;
          font-size: 14px;
          font-weight: bold;
           border: none;
           cursor: pointer;
        }
        .btn-entrada {
          background-color: green;
          color: #FFF;
          border-radius: 5px;
          padding-bottom: 10px;
          padding-top: 10px;
          padding-left: 20px;
          padding-right: 20px;
          font-size: 14px;
          font-weight: bold;
           border: none;
           cursor: pointer;
        }
        .btn-green {
    background-color: #4cae4c;
    color: #FFF;
    border-radius: 5px;
    padding-bottom: 10px;
    padding-top: 5px;
    padding-left: 5px;
    padding-right: 5px;
    font-size: 20px !important;
    font-weight: bold;
    margin-right: 9px;
    border: none;
    float: right;
    cursor: pointer;
}
body{
    font-size: 24px !important;
}
.modalInputCambioPrecio {
    font-size: 42px !important;
    color: blue !important;
    border-radius: 16px !important;
    border-color: green;
}
.modalInputCambioPrecio, [data-is="abrir-caja"] .modalInputCambioPrecio {
    font-size: 42px !important;
    color: blue !important;
    border-radius: 16px !important;
    border-color: green;
        font-weight: 800!important;
}
label {
    display: inline-block!important;
    max-width: 100%!important;
    margin-bottom: 5px!important;
    
    font-size: 23px!important;
}
.btn-dark-gray {
    background-color: #3D3E42;
    color: #FFF;
    border-radius: 5px;
    padding-bottom: 10px;
    padding-top: 5px;
    padding-left: 5px;
    padding-right: 5px;
    font-size: 20px !important;
    font-weight: bold;
    margin-right: 9px;
    border: none;
    float: right;
    cursor: pointer;
}
    </style>
<script>
    var self = this;
    self.totalFondoInicial = 0;
    self.totalConteoManual = 0;
    self.totalFondoInicialSTR = 0;
    self.totalConteoManualSTR = 0;
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
         $( "#conteoDolar" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
             conteoDinero();
        }).keydown(function( event ) {
               conteoDinero();
        });
         $( "#tipoCambio" ).keyup(function( event ) {
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
    $(".totalConteoManual").val(null)
    self.totalConteoManualSTR = 0;
    self.totalConteoManual= 0
    self.update()
    
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

    var tipoCambio = __valorNumerico($(".tipoCambio").val())
    var conteoDolar = __valorNumerico($(".conteoDolar").val())
   
    
    if(self.usuarioCaja.id != null ){
        var conteoTarjeta = __valorNumerico($(".conteoTarjeta").val())
        self.totalConteoManual = billete50000 + billete10000 + billete20000 + billete5000 + billete2000 + billete1000  + moneda500 + moneda100 + moneda50 + moneda25  + moneda10 + moneda5 + conteoTarjeta +(tipoCambio * conteoDolar );
        self.totalConteoManualSTR = billete50000 + billete10000 + billete20000 + billete5000 + billete2000 + billete1000  + moneda500 + moneda100 + moneda50 + moneda25  + moneda10 + moneda5 + conteoTarjeta +(tipoCambio * conteoDolar );
         self.totalConteoManualSTR = formatMiles(self.totalConteoManual);
        self.update()
    }else{
        
        
        self.totalFondoInicial = billete50000 + billete10000 + billete20000 + billete5000 + billete2000 + billete1000  + moneda500 + moneda100 + moneda50 + moneda25  + moneda10 + moneda5;
        self.totalFondoInicialSTR = billete50000 + billete10000 + billete20000 + billete5000 + billete2000 + billete1000  + moneda500 + moneda100 + moneda50 + moneda25  + moneda10 + moneda5;
        self.totalFondoInicialSTR = formatMiles(self.totalFondoInicial);
        self.update()
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
        self.update()
        showModal()
    })
}
function showModal(){
    $('#modalCerrarCaja').modal({backdrop: 'static', keyboard: true}) 
    $('#modalCerrarCaja').on('shown.bs.modal', function () {
        __limpiar()
    
        self.totalFondoInicial = self.usuarioCaja.totalFondoInicial;
        self.totalFondoInicialSTR = formatMiles(self.usuarioCaja.totalFondoInicial)
        self.update()
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
                    mensajeAdvertencia( data.message);
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
                             
                              $.each(data.listaObjetos, function( index, modeloTabla ) {
                                self.usuarioCaja = modeloTabla    
                                self.update()
                            })
                            var parametros  = {
                                usuarioCaja:self.usuarioCaja,
                                tipo:1
                            }
                            hidemodal()
                            riot.mount('imprimir-caja',{parametros:parametros});
                            __listado()
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
        global: false,
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
               // $('.btn-agregar').hide()
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
                               {'data' : 'totalNetoSTR'        ,"name":"totalNetoSTR"  ,"title" : "Ventas"  ,"autoWidth" :false},
                               {'data' : 'id'           ,"name":"id"       ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __OpcionesFacturas(id,type,row);
                                 }}

	      		            ];
    self.update();
   
}

/**
*  Mostrar listado Enrada
**/
function __listado_entrada(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarUsuariosCajasAjax.do",
        datatype: "json",
        global: false,
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                __InformacionDataTableEntrada();
                loadListar(".tableListar",idioma_espanol,self.informacion_tabla,result.aaData)
                agregarInputsCombos();
                ActivarEventoFiltro(".tableListar")
             } 
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
} 
/**
*Formato Salida
**/
function __InformacionDataTableSalida(){
    self.informacion_tabla_salida = [ 
                               {'data' : 'id'        ,"name":"id"  ,"title" : "Id"  ,"autoWidth" :false},
                               {'data' : 'descripcion'        ,"name":"descripcion"  ,"title" : "Descripcion"  ,"autoWidth" :false },
                               {'data' : 'totalSTR'        ,"name":"totalSTR"  ,"title" : "Total"  ,"autoWidth" :false},

	      		            ];
    self.update();
   
}
/**
*Formato Entrada
**/
function __InformacionDataTableEntrada(){
    self.informacion_tabla_Entrada = [ 
                               {'data' : 'id'        ,"name":"id"  ,"title" : "Id"  ,"autoWidth" :false},
                               {'data' : 'descripcion'        ,"name":"descripcion"  ,"title" : "Descripcion"  ,"autoWidth" :false },
                               {'data' : 'totalSTR'        ,"name":"totalSTR"  ,"title" : "Total"  ,"autoWidth" :false},

	      		            ];
    self.update();
   
}

/**
Opcions del menu
**/
function __OpcionesFacturas(id,type,row){
   var cerrar  = '<a href="#"  title="Cerrar Caja" class="btn btn-cerrar  btn-cerrar btnCerrarCaja" role="button">  </a>';
   var entrada  = '<a href="#"  title="Entrada de Dinero" class="btn btn-entrada  btnEntradaCaja" role="button"> Entrada</a>';
   var salida  = '<a href="#"  title="Salida Dinero" class="btn btn-cerrar  btnSalidaCaja" role="button"> Salida</a>';
   cerrar = row.estado =="Activo"?cerrar :""
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
        if ( $(this).index() !=6    ){
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
                            self.usuarioCaja = null
                             __listado()
                             hidemodal()
                             $('.btn-agregar').show()
                             $.each(data.listaObjetos, function( index, modeloTabla ) {
                                self.usuarioCaja = modeloTabla    
                                self.update()
                            })
                            if(self.usuarioCaja != null){
                                var parametros  = {
                                    usuarioCaja:self.usuarioCaja,
                                    tipo:2
                                }
                                riot.mount('imprimir-caja',{parametros:parametros});
                                hidemodal()
                                                     

                            }

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
        moneda:50000,
        denominacion: "50,000",
        cantidad: __valorNumerico($(".billete50000").val() ),
        total:__valorNumerico($(".billete50000").val()) * 50000
    })
    lista.data.push({
        tipo:tipo,
        moneda:20000,
        denominacion: "20,000",
        cantidad: __valorNumerico($(".billete20000").val() ),
        total:__valorNumerico($(".billete20000").val()) * 20000
    })
    lista.data.push({
        tipo:tipo,
        moneda:10000,
        denominacion: "10,000",
        cantidad: __valorNumerico($(".billete10000").val() ),
        total:__valorNumerico($(".billete10000").val()) * 10000
    })
    lista.data.push({
        tipo:tipo,
        moneda:5000,
        denominacion: "5,000",
        cantidad: __valorNumerico($(".billete5000").val() ),
        total:__valorNumerico($(".billete5000").val()) * 5000
    })
    lista.data.push({
        tipo:tipo,
        moneda:2000,
        denominacion: "2,000",
        cantidad: __valorNumerico($(".billete2000").val() ),
        total:__valorNumerico($(".billete2000").val()) * 2000
    })
    lista.data.push({
        tipo:tipo,
        moneda:1000,
        denominacion: "1,000",
        cantidad: __valorNumerico($(".billete1000").val() ),
        total:__valorNumerico($(".billete1000").val()) * 1000
    })
    lista.data.push({
        tipo:tipo,
        moneda:500,
        denominacion: "500",
        cantidad: __valorNumerico($(".moneda500").val() ),
        total:__valorNumerico($(".moneda500").val()) * 500
    })
    lista.data.push({
        tipo:tipo,
        moneda:100,
        denominacion: "100",
        cantidad: __valorNumerico($(".moneda100").val() ),
        total:__valorNumerico($(".moneda100").val()) * 100
    })
    lista.data.push({
        tipo:tipo,
        moneda:50,
        denominacion: "50",
        cantidad: __valorNumerico($(".moneda50").val() ),
        total:__valorNumerico($(".moneda50").val()) * 50
    })
    lista.data.push({
        tipo:tipo,
        moneda:25,
        denominacion: "25",
        cantidad: __valorNumerico($(".moneda25").val() ),
        total:__valorNumerico($(".moneda25").val()) * 25
    })
    lista.data.push({
        tipo:tipo,
        moneda:10,
        denominacion: "10",
        cantidad: __valorNumerico($(".moneda10").val() ),
        total:__valorNumerico($(".moneda10").val()) * 10
    })
    lista.data.push({
        tipo:tipo,
        moneda:5,
        denominacion: "5",
        cantidad: __valorNumerico($(".moneda5").val() ),
        total:__valorNumerico($(".moneda5").val()) * 5
    })
    return lista;
} 
</script>
</abrir-cajaMovil>