<cerrada-caja>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-lock"></i>&nbsp {$.i18n.prop("usuarioCaja.titulo.cajas.cerradas")} {mostrarListadoFacturasXCaja ==true?"--Facturas" :""} {mostrarDetalleDeFactura ==true?"-->" + factura.tipoDoc  :""} </h1>
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
                                <th class="table-header" >{$.i18n.prop("usuarioCaja.caja")}          </th>
                                <th class="table-header" >{$.i18n.prop("usuarioCaja.created_at")}    </th>
                                <th class="table-header" >{$.i18n.prop("usuarioCaja.updated_at")}    </th>
                                <th class="table-header" >{$.i18n.prop("usuarioCaja.usuario")}       </th>
                                <th class="table-header" >{$.i18n.prop("usuarioCaja.fondoIncial")}   </th>
                                <th class="table-header" >{$.i18n.prop("usuarioCaja.totalNeto")}     </th>
                                <th class="table-header" >{$.i18n.prop("usuarioCaja.estado")}        </th>
                                <th class="table-header" >{$.i18n.prop("listado.acciones")}          </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>{$.i18n.prop("usuarioCaja.caja")}          </th>
                                <th>{$.i18n.prop("usuarioCaja.created_at")}    </th>
                                <th>{$.i18n.prop("usuarioCaja.updated_at")}    </th>
                                <th>{$.i18n.prop("usuarioCaja.usuario")}       </th>
                                <th>{$.i18n.prop("usuarioCaja.fondoIncial")}   </th>
                                <th>{$.i18n.prop("usuarioCaja.totalNeto")}     </th>
                                <th>{$.i18n.prop("usuarioCaja.estado")}        </th>
                                <th>                                    </th>
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
                                <input type="number" step="any" class="form-control totalFondoInicial" id="totalFondoInicial" name="totalFondoInicial" value="{usuarioCaja.totalFondoInicial.toFixed(2)}"  >
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
<div >
    <div class="row center " show ={mostrarVerDetalle} >
    <div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-8 col-lg-8 col-sx-12 col-sm-8">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-search"></i>&nbsp {$.i18n.prop("titulo.mostrar.usuarioCaja")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formularioUsuarioCaja" name ="formularioUsuarioCaja"   class="advanced-search-form ">
                        <input type="hidden" name="id" id="id" value="{usuarioCaja.id}">
                        <input type="hidden" name="caja" id="caja" value="{usuarioCaja.caja.id}">
                        
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.caja")}  </label>
                                <input type="text"  class="form-control"  value="{usuarioCaja.caja.descripcion}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.usuario")}  </label>
                                <input type="text"  class="form-control"  value="{usuarioCaja.usuario.nombre}"  readonly>
                            </div>

                        </div>

                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.fondoIncial")}  </label>
                                <input type="text" class="form-control "  value="{usuarioCaja.totalFondoInicialSTR}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalEfectivo")}  </label>
                                <input type="text" class="form-control "  value="{usuarioCaja.totalEfectivoSTR}" readonly >
                            </div>

                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalTarjeta")}  </label>
                                <input type="text" class="form-control "  value="{usuarioCaja.totalTarjetaSTR}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalBanco")}  </label>
                                <input type="text" class="form-control "  value="{usuarioCaja.totalBancoSTR}" readonly >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalServicio")}  </label>
                                <input type="text" class="form-control "  value=" {usuarioCaja.totalServicioSTR}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                               <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalAbono")}  </label>
                               <input type="text" class="form-control "  value=" {usuarioCaja.totalAbonoSTR}" readonly >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6 ">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalNeto")}  </label>
                                <input type="text" class="form-control "  value=" {usuarioCaja.totalNetoSTR}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                               <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalDolares")}  </label>
                               <input type="text" class="form-control "  value=" {usuarioCaja.totalDolaresSTR}" readonly >
                            </div>                            
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left "  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>

                  
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>
</div>
<!-- Listado  -->
<div classs="contenedor-listar container" id="container"  show={mostrarListadoFacturasXCaja}  >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                    <table id="tableListarFacturasXCaja" class="display table responsive table-hover nowrap table-condensed tableListarFacturasXCaja"   cellspacing="0" width="100%">
                         <thead>
                            <tr>
                                <th class = "table-header" >{$.i18n.prop("usuario.nombreUsuario")}            </th>
                                <th class = "table-header" >{$.i18n.prop("factura.fecha.emision")}            </th>
                                <th class = "table-header" >{$.i18n.prop("factura.documento")}                </th>
                                <th class = "table-header" >{$.i18n.prop("factura.cliente")}                  </th>
                                <th class = "table-header" >{$.i18n.prop("factura.resumen.efectivo")}         </th>
                                <th class = "table-header" >{$.i18n.prop("factura.resumen.tarjeta")}          </th>
                                <th class = "table-header" >{$.i18n.prop("factura.resumen.banco")}            </th>
                                <th class = "table-header" >{$.i18n.prop("factura.total")}                    </th>
                                <th class = "table-header" >{$.i18n.prop("listado.acciones")}                 </th>
                            </tr>
                            </thead>
                            <tfoot style="display: table-header-group;">
                                <tr>
                                    <th>{$.i18n.prop("usuario.nombreUsuario")}            </th>
                                    <th>{$.i18n.prop("factura.fecha.emision")}            </th>
                                    <th>{$.i18n.prop("factura.documento")}                </th>
                                    <th>{$.i18n.prop("factura.cliente")}                  </th>
                                    <th>{$.i18n.prop("factura.resumen.efectivo")}         </th>
                                    <th>{$.i18n.prop("factura.resumen.tarjeta")}          </th>
                                    <th>{$.i18n.prop("factura.resumen.banco")}            </th>
                                    <th>{$.i18n.prop("factura.total")}                    </th>
                                    <th>              </th>
                                </tr>
                            </tfoot>
                    </table>
                       <button onclick ={__regresarAlListadoCajaCerradas}  type="button" class="btn-dark-gray btn-back pull-left" >
                                {$.i18n.prop("btn.volver")}
                </button>
            </div>
        </div>    
</div>
<!-- Fin del Listado -->

 <div class="box box-solid box-primary" show={mostrarDetalleDeFactura}>
        <div class="box-body">
             <div class="box-header with-border">
                <div class="row">
                  <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">  
                  <div class="box-tools ">
                  </div>
                  </div>
                </div>  
                  <br>
             </div>
            <div  class="contenedor-compra " >
                <div class="cabecera-izquierda">
                        <form id="formularioFactura">
                            <input id="id" name="id" type="hidden" value="{compra.id}">
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("factura.condicion.pago")} </label> 
                                        <input type="text" class="form-control"  value="{factura.condicionVenta}"  readonly>
                                    </div>
 
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label for="pago_tipoVentaL">{$.i18n.prop("factura.tipo.documento")} </label> 
                                       <input type="text" class="form-control"  value="{factura.tipoDoc}" readonly >
                                    </div>
 
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label for="pago_tipoVentaL">{$.i18n.prop("factura.estado")} </label> 
                                        <input type="text" class="form-control"  value="{factura.estado}" readonly >
                                    </div>
                                </div>
                            </div>    
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("factura.cliente")}</label> 
                                        <input type="text"  class="form-control"  value="{cliente.nombreCompleto}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.cedula")}</label> 
                                        <input type="text" class="form-control " value="{cliente.cedula}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.celular")}</label> 
                                        <input type="text" class="form-control " value="{cliente.celular}" readonly>
                                    </div>
                                </div>
                            </div>    
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.telefono")}</label> 
                                        <input   type="text"  class="form-control"  value="{cliente.telefono}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.correoElectronico")}</label> 
                                        <input type="text" class="form-control " value="{cliente.correoElectronico}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.descuento")}</label> 
                                        <input type="text" class="form-control " value="{cliente.descuento}" readonly>
                                    </div>
                                </div>
                            </div>                                
                            <div class="row">
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("cliente.otraSena")}</label> 
                                        <input type="text" class="form-control " value="{cliente.otraSena}" readonly>
                                    </div>
                                </div>
                            </div> 
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("factura.vendedor")}</label> 
                                        <input type="text"  class="form-control"  value="{vendedor.nombreCompleto}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("vendedor.correoElectronico")}</label> 
                                        <input type="text" class="form-control " value="{vendedor.correoElectronico}" readonly>
                                    </div>
                                </div>
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("vendedor.celular")}</label> 
                                        <input type="text" class="form-control " value="{vendedor.celular}" readonly>
                                    </div>
                                </div>
                            </div>      
                            <div class="row">
                                <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                    <div show = {factura.fechaCredito} class="form-group has-success">
                                        <label >{$.i18n.prop("compra.fecha.credito")}</label> 
                                        <input type="text" class="form-control" id="fechaCredito" value="{factura.fechaCredito}" readonly >
                                    </div>    
                                </div>
                            </div>                             
                            <div class="row">
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <div class="form-group ">
                                        <label >{$.i18n.prop("factura.nota")}</label> 
                                        <input type="text" class="form-control" id="nota" name="nota" value="{factura.direccion}" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <div class="form-group ">
                                        <label >{$.i18n.prop("factura.direccion")}</label> 
                                        <input type="text" class="form-control direccion" id="direccion" name="direccion" value="{factura.direccion}" readonly>
                                    </div>
                                </div>
                            </div>

                        </form>   
                        <br>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>{$.i18n.prop("factura.linea.detalle.linea")}                         </th>
                            <th>{$.i18n.prop("factura.linea.detalle.codigo")}                        </th>
                            <th style="width:20%;">{$.i18n.prop("compra.linea.detalle.descripcion")} </th>
                            <th >{$.i18n.prop("factura.linea.detalle.cantidad")}                     </th>
                            <th >{$.i18n.prop("factura.linea.detalle.precio")}                       </th>
                            <th >{$.i18n.prop("factura.linea.detalle.descuento")}                    </th>
                            <th >{$.i18n.prop("factura.linea.detalle.impuesto")}                     </th>
                            <th >{$.i18n.prop("factura.linea.detalle.subTotal")}                     </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td>{numeroLinea}</td>
                            <td>{codigo}</td>
                            <td>{descripcion}</td>
                            <td class="text-right">
                                <input  class="form-control " type="number" placeholder="Cantidad Detalle" value = {cantidad} readonly/>
                            </td>
                            <td class="text-right">
                                <input   class="form-control" type="text"  value = "{precioUnitario}" readonly />
                            </td>
                            <td class="text-right">
                                <input   class="form-control" type="text"  value = "{montoDescuento}" readonly/>
                            </td>
                                                        
                            <td class="text-right">
                                <input  class="form-control" type="text"  value = "{montoImpuesto}" readonly/>
                            </td>

                            <td class="text-righ">
                                <input  class="form-control" type="text"  value = "{montoTotalLinea}" readonly/>
                            </td>
                        </tr>
                        </tbody>
                    </table>     
                    <button onclick ={__regresarAlListadoFacturaEspecifica}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
     
                </div>
                <section class="cabecera-derecha">
				    <!--right sidebar-->
                     <div class="row">
                            <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">
                    <aside class="left-sidebar">
                            <!--Booking details-->
                        <article class="booking-details clearfix">
                            <div  id="btnGrandePagar" class="head green well" style="color: #fff; font-size: 25px;  padding-top:8px !important; padding-bottom:8px !important; margin-bottom: 8px;">
                                <table id="pagarTable" width="100%">
                                    <tbody>
                                        <tr>
                                            <td width="30%" id="">
                                                <div id="pagarTitulo">{$.i18n.prop("factura.total")}</div>
                                            </td>
                                            <td width="70%" id="">
                                            
                                                <div id="">
                                                    <span class="label label-info textShadow" id="total-show"> {factura.totalComprobanteSTR}</span>
                                                </div>
                                            </td>
                                        </tr>                     
                                    </tbody>
                                </table>
                            </div>
                        </article>
                    </aside>
                    </div>

                    </div>
                    
                </section>
                      
            </div><!-- fin contenedor-compra-->
            
        </div><!-- fin box-body-->
	</div><!-- fin box -->

<imprimir-caja></imprimir-caja>

<script>
    var self = this;
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del Listado de la Tabla 
    self.cajas                  = {aaData:[]}
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarVerDetalle         = false
    self.mostrarListadoFacturasXCaja = false
    self.mostrarDetalleDeFactura  = false
    self.caja = {
        id:null,
        descripcion:"",
        estado:""
    }
self.on('mount',function(){
    __InicializarTabla('.tableListar')
    __InicializarTabla('.tableListarFacturasXCaja')
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListar')
    ActivarEventoFiltro('.tableListarFacturasXCaja')
    __listado()
    __Eventos()
    __listadoCajasActivas()
})
/**
*Regresar al listado de la caja cerrada
**/
__regresarAlListadoCajaCerradas(){
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarVerDetalle         = false
    self.mostrarListadoFacturasXCaja = false
    self.mostrarDetalleDeFactura  = false
    self.update()
}
/**
Factura especifica 
**/
__regresarAlListadoFacturaEspecifica(){
    self.mostrarListado            = false
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarVerDetalle         = false
    self.mostrarListadoFacturasXCaja = true
    self.mostrarDetalleDeFactura  = false
    
    self.update()
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
};
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
    self.botonModificar     = false;
    self.mostrarFormulario  = false 
    self.mostrarVerDetalle  = false
    self.update()
    __listado();
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
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.mostrarVerDetalle = true
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
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarUsuariosCajasCerradasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                __InformacionDataTable();
                loadListar(".tableListar",idioma_espanol,self.informacion_tabla,result.aaData)
                agregarInputsCombos();
                    //Activar filtros
                ActivarEventoFiltro(".tableListar")
                __VerDetalle()
                __Eventos()
                __Imprimir()
                __VerDetalleFacturaXCaja()
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
                                {'data' : 'updated_atSTR'        ,"name":"updated_atSTR" ,"title" : $.i18n.prop("usuarioCaja.updated_at")  ,"autoWidth" :false
                                },
                               {'data' : 'usuario'       ,"name":"usuario"         ,"title" : $.i18n.prop("usuarioCaja.usuario")     ,"autoWidth" :false,
                                    "render":function(usuario,type, row){
                                        return usuario.nombreUsuario;
                                    }
                               },
                               {'data' : 'totalFondoInicialSTR' ,"name":"totalFondoInicialSTR"  ,"title" : $.i18n.prop("usuarioCaja.fondoIncial")  ,"autoWidth" :false},
                               {'data' : 'totalNetoSTR'            ,"name":"totalNetoSTR"        ,"title" : $.i18n.prop("usuarioCaja.totalNeto")      ,"autoWidth" :false},
                               {'data' : 'estado'        ,"name":"estado"          ,"title" : $.i18n.prop("usuarioCaja.estado")      ,"autoWidth" :false},
                               {'data' : 'id'            ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
}
/**
* Formato de montos
**/
function FormatoMontos(valor){
    var resultado = __valorNumerico(valor)
    return resultado;
}
/**
*  retorna el valor numerico o cero sino es numerico
**/
function __valorNumerico(valor){
    return isNumber(valor)?parseFloat(valor):0 ;
}
/**
*  Validar si es numero
**/
function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}
/**
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
      var dateTime = new Date(fecha);
      return moment(dateTime).format('DD/MM/YYYY h:mm:ss');
}
/**
* Opciones listado de los clientes
*/
function __Opciones(id,type, row){
    let menu = '<div class="dropdown">' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    menu += '<li><a href="#"  title="Mostrar" class="   btnVerDetalle" >Ver Detalle</a></li>'
    menu += '<li><a href="#"  title="Imprimir" class="  btnImprimir" >Imprimir</a></li>'
    menu += '<li><a href="#"  title="Facturas" class="  btnFacturas" >Facturas</a></li>'
  //  menu += '<li><a href="#"  title="Facturas" class="  btnProductos">Productos</a></li>'
    menu += "</ul></div>"  
    return menu;          
}
/**                                  LISTADO DE FACTURAS POR CAJA                                               **/
/**
 * Funcion para Listar Facturas asociadas a un caja
 */
function __VerDetalleFacturaXCaja(){
	$('#tableListar').on('click','.btnFacturas',function(e){
    	var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.usuarioCaja  = data
        self.mostrarListado            = false
        self.botonModificar            = false
        self.botonAgregar              = false
        self.mostrarVerDetalle         = false
        self.mostrarListadoFacturasXCaja = true
        self.update()
        __listarFacturasXCajas()
	});
}
/**
 * Funcion para Modificar del Listar
 */
function __VerDetalle(){
	$('#tableListar').on('click','.btnVerDetalle',function(e){
    	var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.usuarioCaja  = data
        self.update()
        __consultar()
	});
}
/**
 * Funcion para Modificar del Listar
 */
function __Imprimir(){
	$('#tableListar').on('click','.btnImprimir',function(e){
    	var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.usuarioCaja  = data
        self.update()
        riot.mount('imprimir-caja',{usuarioCaja:self.usuarioCaja});
	});
}
/**
*  Busqueda de la informacion por rango de fechas
**/
function __listarFacturasXCajas(){
    self.listaFacturas = []
    self.update()
    var parametros = {
        id:self.usuarioCaja.id
    };
    $("#tableListarFacturasXCaja").dataTable().fnClearTable(); 
    __InicializarTabla('.tableListarFacturasXCaja')  
    $.ajax({
            url: "ListarUsuariosCajasFacturasNoAnuladasAjax.do",
            datatype: "json",
            data:parametros ,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTableFacturasXCaja();
                    loadListar(".tableListarFacturasXCaja",idioma_espanol,self.formato_tabla_facturaXCaja,result.aaData)
                    self.listaFacturas = result.aaData
                    self.update()
                    agregarInputsCombosXCaja();
                    ActivarEventoFiltro(".tableListarFacturasXCaja")
                    __VerDetalleXFactura()
                    __BajarPDF()
                    __imprimirPTV()
                }else{
                    __InformacionDataTableFacturasXCaja();
                     agregarInputsCombosXCaja();
                }           
            },
            error: function (xhr, status) {
                mensajeErrorServidor(xhr, status);
                console.log(xhr);
            }
    });
}
/**
*Formato del listado 
**/
function __InformacionDataTableFacturasXCaja(){
    self.formato_tabla_facturaXCaja = [ 
                               {'data' :'nombreUsuario' ,"name":"nombreUsuario" ,"title" : $.i18n.prop("usuario.nombreUsuario")     ,"autoWidth" :true },
                               {'data' :'fechaEmision'  ,"name":"fechaEmision"  ,"title" : $.i18n.prop("factura.fecha.emision")     ,"autoWidth" :true },
                               {'data' :'numeroFactura' ,"name":"numeroFactura" ,"title" : $.i18n.prop("factura.documento")         ,"autoWidth" :true ,
                                "render":function(numeroFactura,type, row){
                                      return __TipoDocumentos(numeroFactura,row);
                                 }
                               
                               },
                               {'data' :'nombreCliente' ,"name":"nombreCliente" ,"title" : $.i18n.prop("factura.cliente")           ,"autoWidth" :true },
                               {'data' :'totalEfectivo' ,"name":"totalEfectivo" ,"title" : $.i18n.prop("factura.resumen.efectivo")  ,"autoWidth" :true  },
                               {'data' :'totalTarjeta'  ,"name":"totalTarjeta"  ,"title" : $.i18n.prop("factura.resumen.tarjeta")   ,"autoWidth" :true },
                               {'data' :'totalBanco'    ,"name":"totalBanco"    ,"title" : $.i18n.prop("factura.resumen.banco")     ,"autoWidth" :true },
                               {'data' :'total'         ,"name":"total"         ,"title" : $.i18n.prop("factura.total")  ,"autoWidth" :true },
                               {'data' : 'id'           ,"name":"id"       ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __OpcionesFacturas(id,type,row);
                                 }
	      		            }];
    self.update();
   
}
/**
Opcions del menu
**/
function __OpcionesFacturas(id,type,row){
  let menu = '<div class="dropdown">' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
    menu += '<li><a href="#"  title="Mostrar" class="  btnImprimir" >Imprimir</a></li>'
    menu += '<li><a href="#"  title="Bajar PDF" class="  btnPDF" >Bajar PDF</a></li>'
    menu += "</ul></div>"  
     return menu;          
}
 /**
*  imprimir impresora punto de venta
**/
function __imprimirPTV(){
	$('.tableListarFacturasXCaja').on('click','.btnImprimir',function(e){
		var table = $('#tableListarFacturasXCaja').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.factura = data
        self.update()
        riot.mount('ptv-imprimir',{factura:self.factura});    
	});
}

/**
 * mostrar la abono
 */
function __BajarPDF(){
	$('.tableListarFacturasXCaja').on('click','.btnPDF',function(e){
		var table = $('#tableListarFacturasXCaja').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        location.href = "generaFacturaPDF?idFactura=" + data.id
	});
}


/**
Tipos de documentos
**/
function __TipoDocumentos(numeroConsecutivo,row){
    switch(row.tipoDoc) {
    case "04":
          return  "Tiq:"+numeroConsecutivo
        break;
    case "01":
        return  "Fact:"+numeroConsecutivo
        break;
    case "02":
        return  "N.Debito:"+numeroConsecutivo
        break;
    case "03":
        return  "N.Credito:"+numeroConsecutivo
        break;

    default:
        return  numeroConsecutivo
}
}
/**
 * mostrar la abono
 */
function __VerDetalleXFactura(){
	$('.tableListarFacturasXCaja').on('click','.btnMostrar',function(e){
		var table = $('#tableListarFacturasXCaja').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.factura = data
        self.mostrarListado            = false 
        self.botonModificar            = false
        self.botonAgregar              = false
        self.mostrarVerDetalle         = false
        self.mostrarListadoFacturasXCaja = false
        self.mostrarDetalleDeFactura  = true
        self.update()
        __FacturaEnEspera(self.factura)
	});
}
/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(factura){

 
    $.ajax({
        url: "MostrarFacturaAjax",
        datatype: "json",
        data: {idFactura:factura.id},
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.factura = modeloTabla
                       self.factura.fechaCredito = self.factura.fechaCredito !=null?__displayDate_detail(self.factura.fechaCredito):null
                       self.cliente  = modeloTabla.cliente
                       self.vendedor = modeloTabla.vendedor
                       self.update()
                    });
                }
                cargarDetallesFacturaEnEspera()
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}

/**
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY ');
}

/**
*  Cargar detalles Factura en espera
**/
function cargarDetallesFacturaEnEspera(){
    self.factura.tipoDoc = __TipoDocumentos(self.factura.numeroConsecutivo,self.factura)
    self.detail                = []
    self.factura.detalles.forEach(function(e){
        self.detail.push({
            numeroLinea     : e.numeroLinea,
            codigo          : e.codigo,
            descripcion     : e.descripcion,
            cantidad        : e.cantidadSTR,
            precioUnitario  : e.precioUnitarioSTR,
            impuesto        : e.impuesto,
            montoImpuesto   : e.montoImpuestoSTR,
            montoDescuento  : e.montoDescuentoSTR,
            porcentajeDesc  : e.porcentajeDesc,
            subTotal        : e.subTotalSTR,
            montoTotalLinea : e.montoTotalLineaSTR,
            montoTotal      : e.montoTotalSTR
        });
    })
    self.update()
    __comboCondicionPago()
    __ComboEstados()
    
     
}


/**
* cargar los estados de la compra
**/
function __ComboEstados(){
    
 switch(self.factura.estado) {
    case 1:
          self.factura.estado=  $.i18n.prop("factura.estado.pendiente")
        break;
    case 2:
         self.factura.estado=  $.i18n.prop("factura.estado.facturado")
        break;

    default:
        self.factura.condicionVenta
    }
    self.update()
}

function __comboCondicionPago(){
    switch(self.factura.condicionVenta) {
    case "01":
          self.factura.condicionVenta =  $.i18n.prop("factura.codicion.venta.contado")
        break;
    case "02":
         self.factura.condicionVenta=  $.i18n.prop("factura.codicion.venta.credito")
        break;

    default:
        self.factura.condicionVenta
    }
    self.update()
} 
 


/**                                  FIN LISTADO DE FACTURAS POR CAJA                                               **/
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
 
    })
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombosXCaja(){
     // Agregar los input de busqueda 
    $('.tableListarFacturasXCaja tfoot th').each( function (e) {
        var title = $('.tableListarFacturasXCaja thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 8    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}
</script>
</cerrada-caja>