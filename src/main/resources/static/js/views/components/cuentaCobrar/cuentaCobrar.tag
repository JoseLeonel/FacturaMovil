<cuenta-cobrar>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("cuentaCobrar.titulo")}  </h1>
        </div>
        <div class=" col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
  <br>
    <br><br>

 <!-- Modal correo alternativo-->
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
	                <button onclick ={__regresarAlListadoAlternativo}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
	                    {$.i18n.prop("btn.volver")}
	                </button>
	            </div>
	            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
	                <button  onclick={__EnviarCorreoAlternativo}   class="btn-green btn-correo pull-right" >  {$.i18n.prop("btn.enviar.correo")}</button>
	            </div>
	         </div>		       
	      </div>
	    </div>
	  </div>
	</div>
    


    <!-- Inicio Filtros-->
        <div class="row" show={mostrarListado}>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;  cursor: pointer;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <label  >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                    <div  class="form-group input-group date datepickerFechaInicial " data-provide="datepicker"    data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control fechaInicio" id="fechaInicio"  name= "fechaInicio" readonly>
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>	                             
                                </div>  
                            </div>             
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label  >{$.i18n.prop("fecha.final")} <span class="requeridoDato">*</span></label>
                                        <div  class="form-group input-group date datepickerFechaFinal" data-provide="datepicker"    data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaFinal" id="fechaFinal"  name= "fechaFinal" readonly>
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>	                             
                                    </div>
                                </div>  
                            </div>
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <label>{$.i18n.prop("cliente.titulo")} </label>  
                                    <select  class="form-control selectCliente idCliente" id="idCliente" name="idCliente" data-live-search="true">
                                        <option  data-tokens="{$.i18n.prop("todos.select")}"  value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option  data-tokens="{nombreCompleto}" each={clientes.data}  value="{id}"  >{nombreCompleto}</option>
                                    </select>
                                </div>  
                            </div>                      
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <label>{$.i18n.prop("combo.estado")} </label>  
                                    <select  class="form-control selectEstado estado" id="estado" name="estado" >
                                        <option  data-tokens="{$.i18n.prop("todos.select")}"  value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option   each={estados}  value="{codigo}"  >{descripcion}</option>
                                    </select>
                                </div>  
                            </div>                      

                        </div>
                        

                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
                <a   show={hay_datos==true} class=" btn btn-primary btn-bajar"  target="_blank" title="Descargar detalle transacciones" href="DescargarDetalleTotalCuentasXCobrarAjax.do?fechaInicioParam={fechaInicio}&fechaFinParam={fechaFin}&idClienteParam={cliente}&estadoParam={estado}"> Descargar</a>        
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
            	<button onclick ={__limpiarFiltros} show={mostrarFiltros} class="btn btn-warning btnLimpiarFiltros" title="LimpiarCampos" type="button"><i id="clear-filters" class="fa fa-eraser clear-filters"></i></button>            
            </div>
        </div>
  
<!-- Fin Filtros-->
<br>


<!-- Listado de abonos  -->
<div classs="contenedor-listar container" id="container"  show={mostrarListadoAbonos}   >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                <div class="box box-solid box-primary">
                    <div class="box-header with-border">
                       <h1 class="box-title" ><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("abono.detalle")}{cuentaCobrar.id}  {$.i18n.prop("cuentaCobrar.total")}:{cuentaCobrar.totalSTR}  {$.i18n.prop("cuentaCobrar.totalSaldo")}: {cuentaCobrar.totalSaldoSTR} </h1>
                    </div>
                    <div class="box-body">
                        <div class="planel-body" >
                            <div class="row" >
                                <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                                    <table id="tableListaAbonos" class="display table responsive table-hover nowrap table-condensed tableListaAbonos"   cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th class="table-header" >{$.i18n.prop("abono.id")}           </th>
                                                <th class="table-header" >{$.i18n.prop("abono.fechaPago")}     </th>
                                                <th class="table-header" >{$.i18n.prop("abono.transferencia")} </th>
                                                <th class="table-header" >{$.i18n.prop("abono.recibo")}        </th>
                                                <th class="table-header" >{$.i18n.prop("abono.total")}         </th>
                                                <th class="table-header" >{$.i18n.prop("abono.estado")}        </th>
                                                <th class="table-header" >{$.i18n.prop("abono.created_at")}    </th>
                                                <th class="table-header" >{$.i18n.prop("abono.updated_at")}    </th>
                                                <th class="table-header">{$.i18n.prop("listado.acciones")}        </th>
                                            </tr>
                                        </thead>
                                        <tfoot style="display: table-header-group;">
                                            <tr>
                                                <th >{$.i18n.prop("abono.id")}            </th>
                                                <th >{$.i18n.prop("abono.fechaPago")}     </th>
                                                <th >{$.i18n.prop("abono.transferencia")} </th>
                                                <th >{$.i18n.prop("abono.recibo")}        </th>
                                                <th >{$.i18n.prop("abono.total")}         </th>
                                                <th >{$.i18n.prop("abono.estado")}        </th>
                                                <th >{$.i18n.prop("abono.created_at")}    </th>
                                                <th >{$.i18n.prop("abono.updated_at")}    </th>
                                                <th >                 </th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                     <h2 class="pull-right"> {$.i18n.prop("cuentaCobrar.totalAbono")}: {cuentaCobrar.totalAbonoSTR} </h2>
                                </div>    
                            </div>
                        </div>    
                        <div class="box-footer">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                                {$.i18n.prop("btn.volver")}
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>
<!-- Fin del Listado de abonos-->

<!-- Formulario del Abono-->
<div show ={mostrarCrearAbono} >
    <div class="row center ">
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-1">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title" show={abono.id == null}><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("abono.detalle.agregar")} {cuentaCobrar.id}  {$.i18n.prop("cuentaCobrar.total")}:{cuentaCobrar.total}  {$.i18n.prop("cuentaCobrar.totalSaldo")}:{cuentaCobrar.totalSaldo.toFixed(2)} </h1>
                    <h1 class="box-title" show={abono.id != null}><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("abono.detalle.id")} {abono.id} {$.i18n.prop("abono.detalle.cuenta")} {cuentaCobrar.id} {$.i18n.prop("cuentaCobrar.total")}:{cuentaCobrar.total.toFixed(2)} {$.i18n.prop("cuentaCobrar.totalSaldo")}:{cuentaCobrar.totalSaldo.toFixed(2)}</h1>
                </div>
                <div class="box-body">
                    <form id = "formularioAbono" name ="formularioAbono " class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{abono.id}">
                        <input type="hidden" name="cuentaCobrar" id="cuentaCobrar" value="{abono.cuentaCobrar.id}">
                        <input type="hidden" name="idCuentaCobrar" id="idCuentaCobrar" value="{cuentaCobrar.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label"  show={abono.id==0}>{$.i18n.prop("mensaje.campos.obligatorios")}</label>
                                
                            </div>
                            <div class="col-md-6  col-sm-6 col-lg-6"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label >{$.i18n.prop("cuentaCobrar.cliente")} </label>
                                <input type="text" class="form-control" value="{cuentaCobrar.cliente.nombreCompleto}" readonly={cuentaCobrar.id > 0} >                        
                            </div>
                           
                           
                        </div>
                        <div class="row">
                            <div class="col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abono.fechaPago")} <span class="requeridoDato">*</span></label>
                                 <div  class="form-group input-group date " data-provide="datepicker"   data-date-format="dd/mm/yyyy">
                                    <input type="text" class="form-control fechaPago" placeHolder ="{$.i18n.prop("abono.fechaPago")}" id="fechaPago" name="fechaPago"  value="{abono.fechaPago}"  readonly={abono.id > 0}>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abono.recibo")} </label>
                                <input type="text" class="form-control recibo" placeHolder ="{$.i18n.prop("abono.recibo")}"  id="recibo" name="recibo"  value="{abono.recibo}" readonly={abono.id > 0}>                        
                            </div>
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abono.transferencia")} </label>
                                <input type="text" class="form-control transferencia" placeHolder ="{$.i18n.prop("abono.transferencia")}" id="transferencia" name="transferencia" value="{abono.transferencia}" readonly={abono.id > 0}>                        
                            </div>
                            <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("cuentaCobrar.totalSaldo")} </label>
                                <input  type="number" step="any" class="form-control" placeHolder ="{$.i18n.prop("cuentaCobrar.totalSaldo")}"  value="{ cuentaCobrar.totalSaldo.toFixed(2)}" readonly>                        
                            </div>
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abono.totalEfectivo")}</span> </label>
                                <input  type="number" step="any" placeHolder ="{$.i18n.prop("abono.totalEfectivo")}" id="totalEfectivo" name="totalEfectivo" onkeyup = {__TotalEfectivo}  class="form-control totalEfectivo"   value="{abono.totalEfectivo}" readonly={abono.id > 0}>                        
                            </div>
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abono.totalTarjeta")}</span> </label>
                                <input  type="number" step="any" class="form-control totalTarjeta" placeHolder ="{$.i18n.prop("abono.totalTarjeta")}"  id="totalTarjeta" name="totalTarjeta" onkeyup = {__TotalTarjeta} value="{abono.totalTarjeta}" readonly={abono.id > 0}>                        
                            </div>
                        </div>    
                        <div class="row">    
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abono.totalBanco")}</span> </label>
                                <input  type="number" step="any" class="form-control totalBanco" placeHolder ="{$.i18n.prop("abono.totalBanco")}"  id="totalBanco" name="totalBanco" onkeyup = {__TotalBanco}  value="{abono.totalBanco}" readonly={abono.id > 0}>                        
                            </div>
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abono.total")} <span class="requeridoDato">*</span> </label>
                                <input  type="number" step="any" class="form-control total" placeHolder ="{$.i18n.prop("abono.total")}" id="total" name="total"  value="{abono.total}"   readonly>                        
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label  >{$.i18n.prop("abono.nota")} </label>
                                <input type="textArea" class="form-control nota" placeHolder ="{$.i18n.prop("abono.nota")}" id="nota" name="nota" value="{abono.nota}"  readonly={abono.id > 0}>
                            </div>
                        </div>
                        <br>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListadoAbono}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}</buuton>
                    </button> <button show={botonAgregar} onclick={__agregarAbono}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>
                  
                </div>
            </div>   
        </div>
    </div>
</div>
<!-- Fin Formulario -->   
    <!-- Listado  -->
    <div classs="contenedor-listar container" id="container"  show={mostrarListado}  >
        <div class= "row">
            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                <div class="form-group">
                    <label  >{$.i18n.prop("titulo.total")} </label>
                    <input type="text" class="form-control " value="{total}" readonly>
                </div>  
            </div>                             
            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                <div class="form-group">
                    <label  >{$.i18n.prop("titulo.abono")} </label>
                    <input type="text" class="form-control" value="{totalAbono}" readonly>
                </div>  
            </div>                             
            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                <div class="form-group">
                    <label  >{$.i18n.prop("titulo.saldo")} </label>
                    <input type="text" class="form-control" value="{totalSaldo}" readonly>
                </div>  
            </div>                             
        </div>

        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar"   cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="table-header">{$.i18n.prop("cuentaCobrar.created_at")} </th>
                                <th class="table-header">{$.i18n.prop("cuentaCobrar.id")}         </th>
                                <th class="table-header">{$.i18n.prop("cuentaCobrar.cliente")}    </th>
                                <th class="table-header">{$.i18n.prop("cuentaCobrar.factura")}    </th>
                                <th class="table-header">{$.i18n.prop("cuentaCobrar.tipo")}       </th>
                                <th class="table-header">{$.i18n.prop("cuentaCobrar.total")}      </th>
                                <th class="table-header">{$.i18n.prop("cuentaCobrar.totalAbono")} </th>
                                <th class="table-header">{$.i18n.prop("cuentaCobrar.totalSaldo")} </th>
                                <th class="table-header">{$.i18n.prop("cuentaCobrar.estado")}     </th>
                                <th class="table-header">{$.i18n.prop("listado.acciones")}        </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th >{$.i18n.prop("cuentaCobrar.created_at")} </th> 
                                <th>{$.i18n.prop("cuentaCobrar.id")}         </th>
                                <th>{$.i18n.prop("cuentaCobrar.cliente")}    </th>
                                <th>{$.i18n.prop("cuentaCobrar.factura")}    </th>
                                <th>{$.i18n.prop("cuentaCobrar.tipo")}       </th>
                                <th>{$.i18n.prop("cuentaCobrar.total")}      </th>
                                <th>{$.i18n.prop("cuentaCobrar.totalAbono")} </th>
                                <th>{$.i18n.prop("cuentaCobrar.totalSaldo")} </th>
                                <th>{$.i18n.prop("cuentaCobrar.estado")}     </th>
                                <th>                                         </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>    
    </div>
<!-- Fin del Listado -->

<div class="row center " show ={mostrarFormulario} >
    <div class="col-md-2 col-lg-2 col-sm-2"></div>
        <div class="col-md-8 col-lg-8 col-sx-12 col-sm-8">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp Consulta de la cuenta por cobrar     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{cuentaCobrar.id}">
                        <input type="hidden" name="cliente" id="cliente" value="{cuentaCobrar.cliente.id}">
                        <input type="hidden" name="vendedor" id="vendedor" value="{cuentaCobrar.vendedor.id}">
                        <input type="hidden" id="letraCambio" name="letraCambio" value="{cuentaCobrar.letraCambio}"  >
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">    
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12" >
                                <label  >{$.i18n.prop("cuentaCobrar.cliente")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control "  value="{cuentaCobrar.cliente.nombreCompleto}" readonly>
                            </div>
                        </div>
                        <div class="row">    
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label  >{$.i18n.prop("cuentaCobrar.vendedor")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control "  value="{cuentaCobrar.vendedor.nombreCompleto}" readonly>
                            </div>
                        </div>
                        <div class="row">    
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                    <label  >{$.i18n.prop("cuentaCobrar.fechaEntrega")} <span class="requeridoDato">*</span></label>
                                    <input type="text" class="form-control fechaEntrega" placeHolder ="{$.i18n.prop("cuentaCobrar.fechaEntrega")}"  id="fechaEntrega"  name= "fechaEntrega" value="{cuentaCobrar.fechaEntrega}"  readonly>
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label  >{$.i18n.prop("cuentaCobrar.fechaPlazo")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control" placeHolder ="{$.i18n.prop("cuentaCobrar.fechaPlazo")}" id="fechaPlazo" name="fechaPlazo"  value="{cuentaCobrar.fechaPlazo}" readonly>
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
                    </div>   
                
                  
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>




<imprimir-abono></imprimir-abono>

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
            border-width: 5px;cliente
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
    var self = this
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del LiUtils.roundFactura(tado de la Tabla 
    self.estados                   =[]
    self.vendedores                = {data:[]}
    self.clientes                  = {data:[]}
    self.empresas                  = {data:[]}
    self.hay_datos                 = false           
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarListadoAbonos      = false
    self.mostrarCrearAbono         = false
    self.total                     = 0
    self.totalAbono                = 0
    self.totalSaldo                = 0
    self.cuentaCobrar                   ={
        id:null,
        recibo:"",
        letraCambio:"",
        factura:"",
        facturaManual:"",
        totalComision:0,
        descuento:0,
        cantidadPagos:0,
        montoCouta:0,
        total:0,
        totalAbono:0,
        totalSaldo:0,
        descripcionArticulo:"",
        nota:"",
        tipo:"",
        estado:"",
        fechaPlazo:null,
        fechaEntrega:null,
        cliente:{
            id:null
        },
         vendedor:{
            id:null
        }
    }
    self.abono ={
	   id:null,
       nota:"",
       recibo:"",
       transferencia:"",
       fechaPago:null,
       totalEfectivo:0,
       totalTarjeta:0,
	   totalBanco:0,
       total:0,
       estado:"",
	   cuentaCobrar:{
           id:null
       }
    }
    self.fechaInicio =null
    self.fechaFin =null
    self.cliente =null
    self.estado = null
    self.on('mount',function(){
        $("#filtros").validate(reglasDeValidacionParametros());
        $("#formulario").validate(reglasDeValidacion());
        $("#formularioAbono").validate(reglasDeValidacionAbono());
        $("#formulario").validate(reglasDeValidacionCorreo());	
        __InicializarTabla('.tableListar')
        __InicializarTabla  ('.tableListaAbonos')
        agregarInputsCombos();
        listaClientesActivos() 
        
        __Eventos()
        __MantenimientoAgregarAbono()
        __verAbono()
        __Anular()
        __ComboEstadosCuentaCobrar()
         $('.datepickerFechaFinal').datepicker(
       	{
            format: 'yyyy-mm-dd',
            todayHighlight:true,
       	}
         );
        $('.datepickerFechaInicial').datepicker(
        {
            format: 'yyyy-mm-dd',
            todayHighlight:true,
        }
        );

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
			recibo : {
                maxlength:25,
                minlength:1,
			},
            letraCambio : {
                maxlength:25,
                minlength:1,
			},
            facturaManual : {
                numeroMayorCero:true,
                maxlength:11,
                minlength:1,
			},
            total: {
                numeroMayorCero:true,
                required : true,
			},
            totalComision : {
                numeroMayorCero:true,
			},
            descuento : {
                maxlength:8,
			},
            cantidadPagos : {
                numeroMayorCero:true,
                required : true,
			},
            montoCouta : {
                required : true,
                numeroMayorCero:true,
			},
            descripcionArticulo : {
                required : true,
                maxlength:255,
                minlength:1,
			},
            nota : {
                maxlength:255,
                minlength:1,
			},
            fechaEntrega : {
                required : true,
			},
            fechaPlazo : {
                required : true,
			},
		},
		ignore : []

	});
	return validationOptions;
};
/**
*  Reglas de validacion para el abono
**/
var reglasDeValidacionAbono = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			recibo : {
                maxlength:25,
                minlength:1,
			},
            total: {
                numeroMayorCero:true,
                required : true,
			},
            totalEfectivo: {
                number:true,
             
			},            
            totalTarjeta: {
                number:true,
               
			},            
            totalBanco: {
                number:true,
               
			},            

            nota : {
                maxlength:255,
                minlength:1,
			},
            fechaPago : {
                required : true,
			}
		},
		ignore : []

	});
	return validationOptions;
}

/**
* Camps requeridos
**/
var reglasDeValidacionParametros = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			fechaInicio : {
				required : true,
			},
			fechaFinal : {
				required : true,
			}                                   
                        
		},
		ignore : []

	});
	return validationOptions;
};


		
		__CorreoAlternativo(){
			$('#correoAlternativo').val(null);
		    $('#ModalCorreoAlternativo').modal({
			    backdrop: 'static',
		        keyboard: false
		    });
		    $('#ModalCorreoAlternativo').modal('show');      
		}

		/**
		* Enviar el correo
		**/
		__EnviarCorreoAlternativo(){
		     if ($("#formulario").valid()) {
		    	 __EnviarPorCorreo()
		     }
		}
		
		__EnviarCorreoEmpresa(){
		   	 __EnviarPorCorreo()
		}
		
		/**
		*  Regresar al listado
		**/
		__regresarAlListadoAlternativo(){
		    $('#ModalCorreoAlternativo').modal('hide')
		}
		
		/**
		* Camps requeridos
		**/
		var reglasDeValidacionCorreo = function() {
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
		

	/**
		*  Busqueda de la informacion y la envia por correo
		**/
		function __EnviarPorCorreo(){
		    if ($("#filtros").valid()) {
		        var parametros = {
		        	correoAlternativo:$('#correoAlternativo').val(),		
		        	fechaInicioParam:$('#fechaInicial').val(),
		        	fechaFinParam:$('#fechaFinal').val(),
                    idClienteParam:$('#idCliente').val(),
                    estadoParam = $('.estado').val()
		        };
		        $.ajax({
		            url: "EnvioDetalleCuentasXCobrarCorreoAjax.do",
		            datatype: "json",
		            data:parametros ,
		            method:"POST",
		            success: function (data) {
					    
			        },
			        error: function (xhr, status) {
			            console.log(xhr);
			            mensajeErrorServidor(xhr, status);
			        }
		        });
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
* limpiar los filtros
**/
__limpiarFiltros(){
    $('#fechaInicio').val(null)
    $('#fechaFinal').val(null)
    self.fechaInicio =null
    self.fechaFin =null
    self.idCliente =null
    self.estado = null
    self.hay_datos                 = false  
    self.update()


}

/**
*  Busqueda de la informacion por rango de fechas
**/
__Busqueda(){
	$("#filtros").validate(reglasDeValidacion());
     if ($("#filtros").valid()) {
         listadoConsulta()

     }

}

function listadoConsulta(){
    self.fechaInicio =$('.fechaInicio').val()
    self.fechaFin =$('.fechaFinal').val()
    self.cliente =$('#idCliente').val()
    self.estado = $('.estado').val()
    self.update()
        var formulario = $("#filtros").serialize();
        $("#tableListar").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListar')  
        $.ajax({
            url: "ListarCuentaCobrarAjax.do",
            datatype: "json",
            data:formulario ,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable();
                    loadListar(".tableListar",idioma_espanol,self.informacion_tabla,result.aaData)
                    agregarInputsCombos();
                    ActivarEventoFiltro(".tableListar")
                    __modificarRegistro_Listar()
                    __mostrarListadoAbonos()
                    __MantenimientoAgregarAbono()
                    TotalesGenerales(result.aaData)
                    self.hay_datos  = true
                    self.update()
                }else{
                    __InformacionDataTable();
                     agregarInputsCombos();
                    self.hay_datos  = false
                    self.update()


                }           
            },
            error: function (xhr, status) {
                mensajeErrorServidor(xhr, status);
                console.log(xhr);
            }
        });

}

/**
*  Suma de totales de cuenta por cobrar 
**/
function TotalesGenerales(data){
    
    for(var i in data) { 
        self.total      += data[i].total;
        self.totalAbono += data[i].totalAbono;
        self.totalSaldo += data[i].totalSaldo;
     }
     self.total = formatoDecimales(self.total,2)
     self.totalAbono = formatoDecimales(self.totalAbono,2)
     self.totalSaldo = formatoDecimales(self.totalSaldo,2)
     
     self.update()
}


/**
*  Suma del monto de tarjeta
**/
__TotalTarjeta(e){
    self.abono.totalTarjeta = __valorNumerico(e.target.value) 
    self.abono.total = self.abono.totalBanco + self.abono.totalEfectivo + self.abono.totalTarjeta
    self.update()
}

/**
* Suma del monto de Efectivo
**/
__TotalEfectivo(e){
    self.abono.totalEfectivo = __valorNumerico(e.target.value) 
    self.abono.total = self.abono.totalBanco  + self.abono.totalEfectivo + self.abono.totalTarjeta
    self.update()
}

/**
* Suma del monto de cheque
**/
__TotalBanco(e){
    self.abono.totalBanco = __valorNumerico(e.target.value) 
    self.abono.total = self.abono.totalBanco  + self.abono.totalEfectivo + self.abono.totalTarjeta
    self.update()
}

/**
*  Regresar al listado
**/
__regresarAlListado(){
   
    self.mostrarListado       = true
    self.botonAgregar         = false
    self.botonModificar       = false   
    self.mostrarFormulario    = false 
    self.mostrarListadoAbonos = false
    self.mostrarCrearAbono    = false
    self.update()
    listadoConsulta();

   
}
/**
* Regresar al listado de los abonos 
**/
__regresarAlListadoAbono(){
    __regresar()
}
/**
* Regresar al listado de abonos
**/
function __regresar(){
   
    self.mostrarListado       = true
    self.botonAgregar         = false
    self.botonModificar       = false   
    self.mostrarFormulario    = false 
    self.mostrarListadoAbonos = false
    self.mostrarCrearAbono    = false
    self.update()
    listadoConsulta();

}

/**
* Calculo del monto de la cuota con base al total a pagar
**/
__MontoCuota(e){
    self.cuentaCobrar.total = __valorNumerico($('.total_cuentaCobrar').val()) 
    if(__valorNumerico($('.cantidadPagos').val())==0){
        return
    }
    self.cuentaCobrar.montoCouta =self.cuentaCobrar.total / __valorNumerico($('.cantidadPagos').val())
    self.update()
}

/**
*   Agregar 
**/
__agregar(){
    __agregarRegistro(1,"#formulario",$.i18n.prop("cuentaCobrar.mensaje.alert.agregar"),'AgregarCuentaCobrarManualAjax.do','ListarCuentaCobrarAjax.do','#tableListar')
}
/**
** Modificar la Empresa
**/
__Modificar(){
    self.error = false;
    self.exito = false;
    self.update();
    __modificarRegistro("#formulario",$.i18n.prop("cuentaCobrar.mensaje.alert.modificar"),'ModificarCuentaCobrarjax.do','ListarCuentaCobrarAjax.do','#tableListar')
}
/**
*   Agregar 
**/
__agregarAbono(){
    __agregarRegistro(2,"#formularioAbono",$.i18n.prop("abono.mensaje.alert.agregar"),'AgregarAbonoAjax.do','ListarAbonosPorCuentaCobrarAjax.do','#tableListaAbonos')
}
/**
*   Agregar 
**/
function __agregarRegistro(transaccion,formulario,mensajeAlerAgregar,urlAgregar,urlListar,nombretable){
    if ($(formulario).valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $(formulario).serialize();
        swal({
           title: '',
           text: mensajeAlerAgregar,
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
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : urlAgregar,
                    success : function(data) {

                        console.log(data.listaObjetos);
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
                             if(transaccion == 1){
                                __LimpiarCuentasPorCobrar() 
                             }else{
                                 __LimpiarAbonos()
                                  listaAbonosPorCuentaPorCobrar()
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
   
}
/**
*  Activar Eventos de cuentas por cobrar
**/
function __Eventos(){
   $("#formulario").validate(reglasDeValidacion());
}
/**
* Eventos de los abonos
**/
function __Eventos_Abonos(){
    $("#formularioAbono").validate(reglasDeValidacionAbono());
    $("#recibo").attr("maxlength", 25);
    $("#nota").attr("maxlength", 255);


    $('#descuento').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
    

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
*Formato del listado de los cambios
**/
function __InformacionDataTable(){
    self.informacion_tabla = [ 
                            {'data' :'created_atSTR'             ,"name":"created_atSTR"              ,"title" : $.i18n.prop("cuentaCobrar.created_at")   ,"autoWidth" :true 
                            },
                            {'data' :'id'                     ,"name":"id"                      ,"title" : $.i18n.prop("cuentaCobrar.id")           ,"autoWidth" :true },
                            {'data' :'cliente.nombreCompleto' ,"name":"cliente.nombreCompleto"  ,"title" : $.i18n.prop("cuentaCobrar.cliente")      ,"autoWidth" :false },
                            {'data' :'factura'                ,"name":"factura"                 ,"title" : $.i18n.prop("cuentaCobrar.factura")      ,"autoWidth" :false },
                            {'data' :'fechaPlazo'             ,"name":"fechaPlazo"              ,"title" : $.i18n.prop("cuentaCobrar.fechaPlazo")   ,"autoWidth" :false ,
                                "render":function(fechaPlazo,type, row){
									return  __displayDate_detail(fechaPlazo);
                                 }
                            },
                            {'data' : 'totalSTR'                 ,"name":"totalSTR"                   ,"title" : $.i18n.prop("cuentaCobrar.total")        ,"autoWidth" :false},
                            {'data' : 'totalAbonoSTR'            ,"name":"totalAbonoSTR"              ,"title" : $.i18n.prop("cuentaCobrar.totalAbono")   ,"autoWidth" :false },
                            {'data' : 'totalSaldoSTR'            ,"name":"totalSaldoSTR"              ,"title" : $.i18n.prop("cuentaCobrar.totalSaldo")   ,"autoWidth" :false},
                            {'data' : 'estado'                   ,"name":"estado"                  ,"title" : $.i18n.prop("cuentaCobrar.estado")       ,"autoWidth" :false},
                            {'data' : 'id'                       ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}

/**
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY ');
}

/**
*Limpiar cuentas por cobrar
**/
function __LimpiarCuentasPorCobrar(){
    self.cuentaCobrar  ={
        id:null,
        recibo:"",
        letraCambio:"",
        factura:"",
        facturaManual:"",
        totalComision:0,
        descuento:0,
        cantidadPagos:0,
        montoCouta:0,
        total:0,
        totalAbono:0,
        totalSaldo:0,
        descripcionArticulo:"",
        nota:"",
        tipo:"",
        estado:"",
        fechaPlazo:null,
        fechaEntrega:null,
        cliente:{
            id:null
        },
        vendedor:{
            id:null
        }
    }
    self.total                     = 0
    self.totalAbono                = 0
    self.totalSaldo                = 0

    self.update()  
    $("#fechaEntrega").val(null)
    $("#fechaPlazo").val(null)
    $("#recibo").val(null)
    $("#letraCambio").val(null)
    $("#facturaManual").val(null)
    $("#totalComision").val(null)
    $("#descuento").val(null)
    $("#cantidadPagos").val(null)
    $("#montoCouta").val(null)
    $("#total_cuentaCobrar").val(null)
    $("#descripcionArticulo").val(null)
    $("#nota").val(null)  
}
/**
* Limpiar Abonos
**/             
function __LimpiarAbonos(){
    self.abono ={
        id:null,
        nota:"",
        recibo:"",
        transferencia:"",
        fechaPago:null,
        totalEfectivo:0,
        totalTarjeta:0,
        totalBanco:0,
        total:0,
        estado:"",
        cuentaCobrar:{
                id:null
        }
    }
    self.update()
    $('#fechaPago').val(null)
    $('#recibo').val(null)
    $("#transferencia").val(null)
    $("#totalEfectivo").val(null)
    $("#totalTarjeta").val(null)
    $("#totalBanco").val(null)
    $("#total").val(null)
    $("#nota").val(null)
}                      
/**
* Opciones listado de los clientes
*/
function __Opciones(){
  var modificar  = '<a href="#"  title="Modificar" class="btn btn-primary  btn-buscar btnModificar" role="button"> </a>';
  var abono   = '<a href="#"  title="Ver/Crear abonos" class="btn btn-success btnlistdoAbono"  role="button"><i class="fa fa-bank"></i></a>';
  return  modificar +" " + abono;
}
/**
*  Mostrar listado de abonos
**/
function __mostrarListadoAbonos(){
    $('.tableListar').on('click','.btnlistdoAbono',function(e){
        var table = $('.tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        $(".tableListaAbonos").dataTable().fnClearTable()
        includeActionsAbono('.dataTables_wrapper','.dataTables_length')
        __LimpiarCuentasPorCobrar() 
        self.cuentaCobrar = data    
        self.error                     = false
        self.errors                    = [];
        self.modificar                 = false
        self.agregar                   = false 
        self.mostrarFormulario         = false
        self.mostrarListado            = false 
        self.mostrarListadoAbonos      = true
        self.mostrarCrearAbono         = false
        self.update()
        listaAbonosPorCuentaPorCobrar()
    })
}

/**
*  incluir el boto agregar en el modulo de abonos
**/
function includeActionsAbono(dataTables_wrapper,dataTables_length) {
    $( ".btn-agregarAbono" ).remove();
    $( ".btn-agregar" ).remove();
    var parent = $(dataTables_wrapper);
    var header_pointer = $(dataTables_length);
    var header_length = header_pointer.html();
    var new_header = "<div  class='new-header-with-actions' style='padding-top:0px; padding-bottom:0px;'>";
    new_header += "<div class='add-new btn-agregarAbono' ><i class='fa fa-plus'></i> Agregar</div>";
    new_header += "</div>";
    parent.prepend(new_header);
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
        __LimpiarCuentasPorCobrar() 
        self.cuentaCobrar = data
        self.update()
        __consultar()
	});
}
/**
* Consultar una cuenta por cobrar
**/
function __consultar(){
    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarCuentaCobrarAjax.do",
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
                        $(".errorServerSideJgrid").remove();
                        $("#formulario").validate(reglasDeValidacion());
                        self.mostrarListado   = false;
                        self.mostrarFormulario  = true 
                        self.botonModificar   = true;
                        self.botonAgregar = false;
                        self.cuentaCobrar  =  modeloTabla
                        self.cuentaCobrar.fechaPlazo = __displayDate_detail(self.cuentaCobrar.fechaPlazo)
                        self.cuentaCobrar.fechaEntrega = __displayDate_detail(self.cuentaCobrar.fechaEntrega)
                        self.update()
                         listaClientesActivos() 
                        __ComboEstadosCuentaCobrar()
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
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY ');
}
/**
*  Crear el combo de estados
**/
function __ComboEstadosCuentaCobrar(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: "Pendiente",
        descripcion:$.i18n.prop("cuentaCobrar.estado.pendiente")
     });
    self.estados.push({
        codigo: "Cerrada",
        descripcion:$.i18n.prop("cuentaCobrar.estado.cerrada")
     });
    
    self.update();
}

/**
*Funciones de los abonos de la cuenta por cobrar
**/
function listaAbonosPorCuentaPorCobrar(){
    if(self.cuentaCobrar.totalSaldo == 0){
        $( ".btn-agregarAbono" ).remove();
    }
    $('.tableListaAbonos').DataTable().destroy();
     $('.tableListaAbonos').dataTable().fnClearTable();
    var parametros = {idCuentaCobrar:self.cuentaCobrar.id}

    $.ajax({
        url: "ListarAbonosPorCuentaCobrarAjax.do",
        datatype: "json",
        method:"GET",
        data:parametros,
        success: function (result) {
            if(result.aaData.length > 0){
                __InformacionTabla_lista_Abonos();
                loadListar(".tableListaAbonos",idioma_espanol,self.informacion_tabla_abonos,result.aaData)
                if(self.cuentaCobrar.totalSaldo == 0){
                    $( ".btn-agregarAbono" ).remove();
                }else{
                   includeActionsAbono('.dataTables_wrapper','.dataTables_length')  
                }
                agregarInputsCombosAbonos();
                ActivarEventoFiltro(".tableListaAbonos")
            }else{
                includeActionsAbono('.dataTables_wrapper','.dataTables_length')  
                agregarInputsCombosAbonos();
                ActivarEventoFiltro(".tableListaAbonos")

            }
            __MantenimientoAgregarAbono()
            __verAbono()
            __Anular()
            __imprimirAbono()
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
}

/**
*  Informacion de la lista de los abonos
**/
function __InformacionTabla_lista_Abonos(){
    self.informacion_tabla_abonos  = [ 
                                       {'data':  'id'             ,"name":"id"            ,"title" : $.i18n.prop("abono.id")},
                                       {'data' : 'fechaPago'      ,"name":"fechaPago"     ,"title" : $.i18n.prop("abono.fechaPago"),
									     "render":function(fechaPago,type, row){
										        return __displayDate_detail(fechaPago);
	 							             }
                                       },
                                       {'data' : 'transferencia'  ,"name":"transferencia" ,"title" : $.i18n.prop("abono.transferencia")},
                                       {'data' : 'recibo'         ,"name":"recibo"        ,"title" : $.i18n.prop("abono.recibo")},
                                       {'data' : 'totalSTR'       ,"name":"totalSTR"      ,"title" : $.i18n.prop("abono.total")},
                                       {'data' : 'estado'         ,"name":"estado"        ,"title" : $.i18n.prop("abono.estado")},
                                       {'data' : 'created_atSTR'  ,"name":"created_atSTR"    ,"title" : $.i18n.prop("abono.created_at")
                                       },
                                       {'data' : 'updated_atSTR'  ,"name":"updated_atSTR"    ,"title" : $.i18n.prop("abono.updated_at") 
                                       },
                                       {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"name" : "id",
									     "render":function(id,type, row){
										        return __OpcionesAbonos(id,type,row);
	 							             }
							        }];
    self.update();
}
/**
* Opciones listado de los Abonos
*/
function __OpcionesAbonos(id,type,row){
  var verAbono  = '<a href="#"  title="Ver abonos" class="btn btn-success verAbono"  role="button"><i class="fa fa-search-plus"></i></a>';
  var anular  = '<a href="#"  title="Anular abono" class="btn btn-danger anularAbono"  role="button"><i class="fa fa-trash"></i></a>';
  var imprimir  = '<a href="#"  title="Imprimir" class="btn btn-primary  btn-imprimir btnImprimir" role="button"> </a>';
  anular = row.estado =="Anulado"?"":anular
  self.cuentaCobrar = row.cuentaCobrar
  self.update()
  return  verAbono + " " + anular + " "+ imprimir;
}

/**
 * mostrar la abono
 */
function __imprimirAbono(){
	$('.tableListaAbonos').on('click','.btnImprimir',function(e){
		var table = $('#tableListaAbonos').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        riot.mount('imprimir-abono',{abono:data});
	});
}

/**
 * mostrar la abono
 */
function __verAbono(){
	$('#tableListaAbonos').on('click','.verAbono',function(e){
        $("#formularioAbono").validate(reglasDeValidacion());
        $(".errorServerSideJgrid").remove();
		var table = $('#tableListaAbonos').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.abono = data
        self.update()
        consultaAbono()
	});
}

/**
* Consultar los Abonos
**/
function consultaAbono(){
    var formulario = $('#formularioAbono').serialize();
    $.ajax({
        url: "MostrarAbonoAjax.do",
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
                        //Inicializar el Formulario
                        $(".errorServerSideJgrid").remove();
                        $("#formularioAbono").validate(reglasDeValidacionAbono());
                        self.mostrarListado            = false
                        self.botonModificar            = false
                        self.botonAgregar              = false
                        self.mostrarListadoAbonos      = false
                        self.mostrarCrearAbono         = true
                        __LimpiarAbonos()
                        self.abono  =  modeloTabla
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
 * mostrar la abono
 */
function __Anular(){
	$('#tableListaAbonos').on('click','.anularAbono',function(e){
        $("#formularioAbono").validate(reglasDeValidacion());
        $(".errorServerSideJgrid").remove();
		var table = $('#tableListaAbonos').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.abono = data
        self.update()
        __agregarRegistro(2,"#formularioAbono",$.i18n.prop("abono.mensaje.alert.anulada"),'anularAbonoAjax.do','ListarAbonosPorCuentaCobrarAjax.do','#tableListaAbonos')
        
	});
}
/**
* Mostrar formulario de mantenimiento Agregar
**/
function __MantenimientoAgregarAbono(){
      //Inicializar el Formulario
    $('.dataTables_wrapper').on('click','.btn-agregarAbono',function(e){
        __LimpiarAbonos()
        
        self.mostrarListado       = false
        self.botonAgregar         = true
        self.botonModificar       = false   
        self.mostrarFormulario    = false 
        self.mostrarListadoAbonos = false
        self.mostrarCrearAbono    = true
        self.update()
        $(".errorServerSideJgrid").remove();
        $("#formularioAbono").validate(reglasDeValidacionAbono());

    })
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 9    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
 
    })

}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombosAbonos(){
     // Agregar los input de busqueda 
    $('.tableListaAbonos tfoot th').each( function (e) {
        var title = $('.tableListaAbonos thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 8    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
 
    })

}

</script>    
</cuenta-cobrar>