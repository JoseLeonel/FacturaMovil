<venta-restaurante>


<!-- The Modal -->
  <div class="modal fade" id="modalRolUsuario">
    <div class="modal-dialog">
      <div class="modal-content">
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Seguridad</h4>
        </div>
        <!-- Modal body -->
        <div class="modal-body">
           <form id="formularioModalRolUsuario">
                <div class="form-group ">
                    <label>Usuario</label> 
                    <input  type="text"  class="form-control usuarioSistema"      id="usuarioSistema" name="usuarioSistema" value="{validarRolCommand.usuarioSistema}">
                </div>      
                <div class="form-group ">
                    <label>Clave</label> 
                    <input  type="password"  class="form-control claveSistema password"  name="claveSistema" id="claveSistema"  value="{validarRolCommand.claveSistema}" autocomplete="off">
                </div>      
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn-dark-gray  btn_big  btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
          
          <button type="button" class="btn btn_big btn-danger pull-right"  onclick ="{__SeguridadVentas}" >Autorizar</button>
        </div>
      </div>
    </div>
  </div>
<!--fin validar rol de usuario-->

<!--Modal abrirCajon sin comanda-->
<div id='modalabrirCajon' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-body">
                <div id="imprAbriCajon" name ="imprAbriCajon">
                    <div class="row">
                        <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                            <div class="form-group has-success">
                                <input  type="text"  class="form-control" value={informacionAbrirCajon}>
                            </div>
                        </div>
                    </div> 
                </div>    
            </div>
        </div>
    </div>
</div>


    

<!--Fin Cambiar descripcion-->
<div id="pagina1" style="padding-bottom:15px" show={mostarParaCrearNuevaVentas}>
            <div class="botonContainer ">
                
                <a class="botones-funcionales" href="#"    onclick = {_ListaFacturasDia} title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f5")}</span></a>
                <a class="botones-funcionales" href="#"    onclick = {__Limpiar} title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f10")}</span></a>
                <a class="botones-funcionales" href="#"    onclick = {__ImprimirTiquete}  title="{$.i18n.prop("imprimir.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f7")}</span></a>
                <a class="botones-funcionales" href="#"    onclick = {__MostrarFormularioDePago}  title="{$.i18n.prop("crear.ventas")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f8")}</span></a>
                <a class="botones-funcionales" href="#"    onclick= {__CrearFacturaTemporal}  title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f9")}</span></a>
                <a class="botones-funcionales" href="#" onclick = {__EntradaDinero} title="Entrada"> <span class="label label-limpiar">Entrada Dinero</span></a>
                <a class="botones-funcionales" href="#" onclick = {__SalidaDinero} title="Entrada"> <span class="label label-limpiar">Salida Dinero</span></a>

                <a class="botones-funcionales" href="#" show={mostarAbrirCajon == true}   onclick = {__AbrirCajon} title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("abrir.cajon")}</span></a>
                <a class="botones-funcionales" href="#"   title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-articulos">{descripcionArticulo}</span></a>

            </div>
</div>
 <div>
     <form id="FormPaginacion">
         <input type="hidden" name="id" id="id" value="{parametrosPaginacion.cantidadPorPagina}">
         <input type="hidden" name="cantidadPorPagina" id="cantidadPorPagina" value="{parametrosPaginacion.cantidadPorPagina}">
         <input type="hidden" name="paginaActual" id="paginaActual" value="{parametrosPaginacion.paginaActual}">
         <input type="hidden" name="total" id="total" value="{parametrosPaginacion.total}">
         <input type="hidden" name="categoria" id="categoria" value="{categoria.id}">
         <input type="hidden" name="mesa" id="mesa" value="{mesa.id}">
         <input type="hidden" name="tipoVenta" id="tipoVenta" value="{parametrosPaginacion.tipoVenta}">
     </form>
 </div>
<!--Modal mostrar  -->
<div id="modalVendedor" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("vendedor.lista")}   </h4>
            </div>
            <div class="modal-body">
                <table id="tableListaVendedor" class="table responsive display table-striped table-hover nowrap tableListaVendedor " cellspacing="0" width="100%">
                   <thead>
                        <th class="table-header">{$.i18n.prop("vendedor.cedula")}            </th>
                        <th class="table-header">{$.i18n.prop("vendedor.nombreCompleto")}    </th>
                        <th class="table-header">{$.i18n.prop("vendedor.correoElectronico")} </th>
                        <th class="table-header">{$.i18n.prop("vendedor.telefono")}          </th>
                        <th class="table-header">{$.i18n.prop("vendedor.celular")}           </th>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("vendedor.cedula")}           </th>
                            <th>{$.i18n.prop("vendedor.nombreCompleto")}   </th>
                            <th>{$.i18n.prop("vendedor.correoElectronico")}</th>
                            <th>{$.i18n.prop("vendedor.telefono")}         </th>
                            <th>{$.i18n.prop("vendedor.celular")}          </th>
                            <th>                                           </th>
                        </tr>
                    </tfoot>                    
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray  btn_big  btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->

<!--Modal comandas pendientes  -->
<div id="modalComandasPendientes" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("comanda.lista.pendientes")}   </h4>
            </div>
            <div class="modal-body">
                <table id="tableListaComandaPendientes" class="table responsive display table-striped table-hover nowrap tableListaComandaPendientes " cellspacing="0" width="100%">
                   <thead>
                        <th class="table-header">{$.i18n.prop("articulo.codigo")}</th>
                        <th class="table-header">{$.i18n.prop("articulo.descripcion")}</th>
                        <th class="table-header">{$.i18n.prop("articulo.comentario")} </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("articulo.codigo")}</th>
                            <th>{$.i18n.prop("articulo.descripcion")}</th>
                            <th>{$.i18n.prop("articulo.comentario")}</th>
                        </tr>
                    </tfoot>                    
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back  btn_big  pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
                <button onclick={__ComentariosComanda} type="button" class="btn-green  btn_big  btn-edit pull-right"     data-dismiss="modal">{$.i18n.prop("btn.aceptar")}</button>                
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->

<!--Inicio mostrar mesas-->
<div show={mostrarMesas}>
<div id="pagina1" style="padding-bottom:15px" >
   <div class="row">
        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
            <div class="box-tools ">
                
                <a class="pull-left" href="#"    onclick = {_ListaFacturasDia} title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f5")}</span></a>

                <a class="pull-left" href="#" show={mostarAbrirCajon == true}   onclick = {__AbrirCajon} title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("abrir.cajon")}</span></a>
                

            </div>
        </div>      
    </div>              
</div>
   	<div class="container-fluid">
           <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12" style="padding: 0px 10px">
             <!--Ventana de los productos-->
			 <!--Seccion de mesas-->
             <section class="lista-mesas clickable" >
             	<div id="item-mesas" class="product-item col-sx-6 col-sm-6 col-md-3 col-lg-3"  each ={mesas.data}  onclick={__FacturasXMesa}>
                	<div class="containerImage">
	                	<img  style = "width:100px;" alt="" class="img-responsive " src="data:image/png;base64,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">
	                	<div class="top-right">{tieneFacturas}</div>
                	</div>                	 
					<div>
		                <span class="label-titulos-articulo"><i class="fa text-white label-titulos-articulo" style="font-size: 25px !important">{descripcion} - {impuestoServicioStr}</i></span>           			
                    </div>
                </div>
             </section>
           </div>    
           <div onclick = {__BotonAnteriorMesas}  class="pull-left btnNavegacion "  >
           		<i class="fa fa-arrow-left"></i>{$.i18n.prop("btn.Anterior")}
           </div>
           <div onclick = {__BotonSiguienteMesas} class="pull-right btnNavegacion "  >
           		<i class="fa fa-arrow-right"></i>{$.i18n.prop("btn.siguiente")}
           </div>
     </div>
</div>       
<!--Fin Ventana de los productos-->

<!--Inicio Separar Cuenta-->
<div show={mostrarSepararCuenta}>
    <div class="container-fluid">
        <div class="row no-space">           
            <div class="col-md-10 col-sm-10 col-lg-10 col-xs-12 pull-left" style="padding: 0px 12px">
                <div class="block panel ">                    
                    <div onclick = {__NombreDividirFactura} id="btnGrandePagar" class="head gray well" style="color: #fff; font-size: 55px;  padding-top:8px !important; padding-bottom:8px !important; margin-bottom: 8px;">
                        <table id="pagarTable" width="100%">
                            <tbody>
                                <tr>
                                    <td width="70%" id="">
                                        <div id="pagarTitulo"><i class="fa fa-scissors">     {$.i18n.prop("separar.cuenta.procesar")}</i></div>
                                    </td>
                                </tr>                     
                            </tbody>
                        </table>
                    </div>
                </div>    
			</div>
		</div>	
        <div class="row no-space">           
            <div class="col-md-5 col-sm-5 col-lg-5 col-xs-12 pull-left" style="padding: 0px 12px">
                <div class="block panel ">                    
                    <div id="btnGrandePagar" onclick={__MostrarFormularioDePagoFactPrincipal} class="head well" style="color: #fff; font-size: 55px;  padding-top:8px !important; padding-bottom:8px !important; margin-bottom: 8px;">
                        <table id="pagarTable" width="100%">
                            <tbody>
                                <tr>
                                    <td width="30%" id="">
                                        <div id="pagarTitulo">{$.i18n.prop("factura.total")}:</div>
                                    </td>
                                    <td width="70%" id="">
                                        <div id="">
                                            <span id="total_show_peso" class="textShadow">  </span>
                                            <span class="label label-info textShadow" id="total-show">{totalComprobantePorSeparar}</span>
                                       </div>
                                    </td>
                                </tr>                     
                            </tbody>
                        </table>
                    </div>
                    <hr style="margin: 0px; border-color: #e4e4e4;">
                    <div id="listadoProdcutos">{$.i18n.prop("titulo.listado.venta")}   {factura.id>0?factura.id:'' } {factura.nombreFactura} </div>
                    <!--Mesa seleccionada-->
                    <div class="input-group">
                        <span title="Vendedor" class="input-group-addon " > 
                            <span onclick={__FacturasXMesaTiqueteEncabezado} class="fa fa-glass" aria-hidden="true" style="margin:3px 4px 0px 2px"> Mesa: {factura.mesa.id>0?factura.mesa.descripcion:''} </span> 
                        </span>
                    </div>
                    <hr style="margin: 2px 0px 0px 0px; border-color: #e4e4e4; margin-top: 0px">
                    <div class="data-fluid">
                        <div id="listaProductos" style="height:300px; overflow-x: hidden; width:100%">
                            <table id="tablaListaProductos"  cellpadding="0" cellspacing="0" width="100%" class="table lcnp table-dark">
                                <thead>
                                    <tr>
                                        <td width="50%">Descripcion</td>
                                        <td width="10%">Cant</td>
                                        <td width="10%">Precio.U</td>
                                        <td width="10%">Desc</td>                                       
                                        <td width="19%">Total</td>                                        
                                    </tr>
                                </thead>
                                <tbody height="60%" id="productos-detail">
                                    <tr each={detailPorSeparar}>
                                        <td>
                                           <div class="block_container_comanda">
                                          		<span class="title-detalle text-info clickable clearfix">{descripcion}</span>
                                          </div> 
                                        </td>
                                        <td>
                                            <span class="cantidad clickable">{cantidad.toFixed(3)}</span>
                                        </td>
                                        <td>
                                            <span class="precio-prod" >{precioUnitario.toFixed(2)}</span>
                                        </td>
                                        <td>
                                            <span class="precio-prod clickable" >{porcentajeDesc.toFixed(2)}</span>
                                        </td>
                                        <td>
                                            <span class="precio-calc">{montoTotalLinea.toFixed(2)}</span>
                                        </td>
                                        <td>
                                            <button  onclick={__addCuentaSeparada} class="btn_eliminar_detalle btn-danger btn-xs btn-block clickable">+</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <hr style="margin: 0px; border-color: #e4e4e4;">
                        <div id="bordeBevelTop">
	                        <table id="pagarTableInfo" width="100%">
	                            <tbody>
	                                <tr style="height:30px;">
	                                    <td width="25%" id="bordeBevelLeft"> 
	                                        <span id="pagarInfo"> {$.i18n.prop("factura.resumen.subTotal")} </span>
	                                        <span id="cantidad-total">{subTotalGeneralPorSeparar} </span> 
	                                    </td>
	                                    <td width="25%" id="bordeBevelLeft"> 
	                                        <span id="pagarInfo">{$.i18n.prop("factura.resumen.descuento")}  </span>
	                                        <span id="sigPeso">   </span>
	                                        <span id="iva-total">{totalDescuentosPorSeparar}</span> 
	                                    </td>
	                                    <td width="25%" id="bordeBevelRight"> 
	                                        <span id="pagarInfo">{$.i18n.prop("factura.resumen.impuesto")}  </span>
	                                        <span id="sigPeso">      </span>
	                                        <span id="subtotal">{totalImpuestoPorSeparar}</span> 
	                                    </td>
	                                    <td width="25%" id="bordeBevelRight"> 
	                                        <span id="pagarInfo">{$.i18n.prop("factura.resumen.impuestoServ")}  </span>
	                                        <span id="sigPeso">      </span>
	                                        <span id="subtotal">{totalImpuestoServPorSeparar}</span> 
	                                    </td>
	                                </tr>
	                            </tbody>
	                        </table>
                    	</div>
                   		<hr style="margin: 0px; border-color: #e4e4e4; margin-top: 0px">                    
                	</div>
                	<hr style="margin: 0px; border-color: #e4e4e4;">
            	</div>
            </div>  

            <div class="col-md-5 col-sm-5 col-lg-5 col-xs-12 pull-left" style="padding: 0px 12px">
                <div class="block panel ">                    
                    <div id="btnGrandePagarGray" class="head well" style="color: #fff; font-size: 55px;  padding-top:8px !important; padding-bottom:8px !important; margin-bottom: 8px;">
                        <table id="pagarTable" width="100%">
                            <tbody>
                                <tr>
                                    <td width="30%" id="">
                                        <div id="pagarTitulo">{$.i18n.prop("factura.total")}:</div>
                                    </td>
                                    <td width="70%" id="">
                                        <div id="">
                                            <span id="total_show_peso" class="textShadow">  </span>
                                            <span class="label label-info textShadow" id="total-show">{totalComprobanteFactSeparada}</span>
                                       </div>
                                    </td>
                                </tr>                     
                            </tbody>
                        </table>
                    </div>
                    <hr style="margin: 0px; border-color: #e4e4e4;">
                    <div id="listadoProdcutos">{$.i18n.prop("titulo.listado.venta")}   {factura.id>0?factura.id:'' } {factura.nombreFactura} </div>
                    <!--Mesa seleccionada-->
                    <div class="input-group">
                        <span title="Vendedor" class="input-group-addon " > 
                            <span onclick={__FacturasXMesaTiqueteEncabezado} class="fa fa-glass" aria-hidden="true" style="margin:3px 4px 0px 2px"> Mesa: {factura.mesa.id>0?factura.mesa.descripcion:''} </span> 
                        </span>
                    </div>
                    <hr style="margin: 2px 0px 0px 0px; border-color: #e4e4e4; margin-top: 0px">
                    <div class="data-fluid">
                        <div id="listaProductos" style="height:300px; overflow-x: hidden; width:100%">
                            <table id="tablaListaProductos"  cellpadding="0" cellspacing="0" width="100%" class="table lcnp table-dark">
                                <thead>
                                    <tr>
                                        <td width="50%">Descripcion</td>
                                        <td width="10%">Cant</td>
                                        <td width="10%">Precio.U</td>
                                        <td width="10%">Desc</td>                                       
                                        <td width="19%">Total</td>                                        
                                    </tr>
                                </thead>
                                <tbody height="70%" id="productos-detail">
                                    <tr each={detailFacturaSeparada}>
                                        <td>
                                           <div class="block_container_comanda">
                                          		<span class="title-detalle text-info clickable clearfix">{descripcion}</span>
                                          </div> 
                                        </td>
                                        <td>
                                            <span class="cantidad clickable">{cantidad.toFixed(3)}</span>
                                        </td>
                                        <td>
                                            <span class="precio-prod" >{precioUnitario.toFixed(2)}</span>
                                        </td>
                                        <td>
                                            <span class="precio-prod clickable" >{porcentajeDesc.toFixed(2)}</span>
                                        </td>
                                        <td>
                                            <span class="precio-calc">{montoTotalLinea.toFixed(2)}</span>
                                        </td>
                                        <td>
                                            <button  onclick={__removeCuentaSeparada} class="btn_eliminar_detalle btn-danger btn-xs btn-block clickable">-</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <hr style="margin: 0px; border-color: #e4e4e4;">
                        <div id="bordeBevelTop">
	                        <table id="pagarTableInfo" width="100%">
	                            <tbody>
	                                <tr style="height:30px;">
	                                    <td width="25%" id="bordeBevelLeft"> 
	                                        <span id="pagarInfo"> {$.i18n.prop("factura.resumen.subTotal")} </span>
	                                        <span id="cantidad-total">{subTotalGeneralFactSeparada} </span> 
	                                    </td>
	                                    <td width="25%" id="bordeBevelLeft"> 
	                                        <span id="pagarInfo">{$.i18n.prop("factura.resumen.descuento")}  </span>
	                                        <span id="sigPeso">   </span>
	                                        <span id="iva-total">{totalDescuentosFactSeparada}</span> 
	                                    </td>
	                                    <td width="25%" id="bordeBevelRight"> 
	                                        <span id="pagarInfo">{$.i18n.prop("factura.resumen.impuesto")}  </span>
	                                        <span id="sigPeso">      </span>
	                                        <span id="subtotal">{totalImpuestoFactSeparada}</span> 
	                                    </td>
	                                    <td width="25%" id="bordeBevelRight"> 
	                                        <span id="pagarInfo">{$.i18n.prop("factura.resumen.impuestoServ")}  </span>
	                                        <span id="sigPeso">      </span>
	                                        <span id="subtotal">{totalImpuestoServFactSeparada}</span> 
	                                    </td>
	                                </tr>
	                            </tbody>
	                        </table>
                    	</div>
                   		<hr style="margin: 0px; border-color: #e4e4e4; margin-top: 0px">                    
                	</div>
                	<hr style="margin: 0px; border-color: #e4e4e4;">
            	</div>
            </div>  
        </div>
    	<div class="row">
			<div class="col-md-10 col-sm-10 col-lg-10 col-sx-12 ">
				<div class="box-footer">
                     <button onclick={__AtrasSepararCuentas} class="btn-dark-gray btn-back pull-left">  {$.i18n.prop("btn.volver")}</button>
	            </div>
			</div>
		</div>
    </div>
</div>       
<!--Fin Separar Venta-->

<!--Inicio Cambiar Mesa-->
<div show={mostrarCambiarMesa}>
    <div class="container-fluid">
        <div class="row no-space">           
            <div class="col-md-10 col-sm-10 col-lg-10 col-xs-12 pull-left" style="padding: 0px 12px">
                <div class="block panel ">                    
                    <div id="btnGrandePagar" class="head gray well" style="color: #fff; font-size: 55px;  padding-top:8px !important; padding-bottom:8px !important; margin-bottom: 8px;">
                        <table id="pagarTable" width="100%">
                            <tbody>
                                <tr>
                                    <td width="70%" id="">
                                        <div id="pagarTitulo"><i class="fa fa-exchange"> {$.i18n.prop("titulo.cambiar.mesa")}</i></div>
                                    </td>
                                </tr>                     
                            </tbody>
                        </table>
                    </div>
                </div>    
			</div>
		</div>	
        <div class="row no-space">           
			<div class="container-fluid">
			       <div class="row">
				       <div class="col-md-10 col-sm-10 col-lg-10 col-xs-10" style="padding: 0px 10px">
			             <!--Ventana de los productos-->
						 <!--Seccion de mesas-->
			             <section class="lista-mesas clickable" >
			             	<div id="item-mesas" class="product-item col-sx-12 col-sm-12 col-md-3 col-lg-3"  each ={mesasCambiar.data}  onclick={__CambiarTiqueteMesa}>
			                	<div class="containerImage">
				                	<img  style = "width:100px;" alt="" class="img-responsive " src="/dist/img/mesaAzul.png">
				                	<div class="top-right">{tieneFacturas}</div>
			                	</div>                	 
								<div>
					                <span class="label-titulos-articulo"><i class="fa text-white label-titulos-articulo" style="font-size: 25px !important">{descripcion} - {impuestoServicioStr}</i></span>           			
			                    </div>
			                </div>
			             </section>
			           </div>    			       
			       </div>
			       <div class="row">
			       	   <div class="col-md-10 col-sm-10 col-lg-10 col-xs-10" >
				           <div onclick = {__BotonAnteriorMesasCambiar}  class="pull-left btnNavegacion " >
				           		<i class="fa fa-arrow-left"></i>{$.i18n.prop("btn.Anterior")}
				           </div>
				           <div onclick = {__BotonSiguienteMesasCambiar} class="pull-right btnNavegacion " >
				           		<i class="fa fa-arrow-right"></i>{$.i18n.prop("btn.siguiente")}
				           </div>			       	   
			       	   </div>
			       </div>
		     </div>
        </div>
    	<div class="row">
			<div class="col-md-10 col-sm-10 col-lg-10 col-sx-12 ">
				<div class="box-footer">
                     <button onclick={__AtrasSepararCuentas} class="btn-dark-gray btn-back pull-left">  {$.i18n.prop("btn.volver")}</button>
	            </div>
			</div>
		</div>
    </div>
</div>       
<!--Fin Cambiar Mesa-->

<!--Inicio de la Venta-->
<div show={mostarParaCrearNuevaVentas}>
    <div class="container-fluid">
        <div class="row no-space">           
            <div class="col-md-5 col-sm-5 col-lg-5 col-xs-12 pull-right" style="padding: 0px 12px">
                <div class="block panel ">                    
                    <div  onclick = {__MostrarFormularioDePago} id="btnGrandePagar" class="head green well" style="color: #fff; font-size: 55px;  padding-top:8px !important; padding-bottom:8px !important; margin-bottom: 8px;">
                        <table id="pagarTable" width="100%">
                            <tbody>
                                <tr>
                                    <td width="30%" id="">
                                        <div id="pagarTitulo">{$.i18n.prop("factura.total")}:</div>
                                    </td>
                                    <td width="70%" id="">
                                        <div id="">
                                            <span id="total_show_peso" class="textShadow">  </span>
                                            <span class="label label-info textShadow" id="total-show">{totalComprobante}</span>
                                       </div>
                                    </td>
                                </tr>                     
                            </tbody>
                        </table>
                    </div>
                    <hr style="margin: 0px; border-color: #e4e4e4;">
                    
                    <div id="listadoProdcutos">{$.i18n.prop("titulo.listado.venta")}   {factura.id>0?factura.id:'' } {factura.nombreFactura} </div>
                    <!--Mesa seleccionada-->
                    <div class="input-group">
                        <span title="Vendedor" class="input-group-addon " > 
                            <span onclick={__FacturasXMesaTiqueteEncabezado} class="fa fa-glass" aria-hidden="true" style="margin:3px 4px 0px 2px"> Mesa: {factura.mesa.id>0?factura.mesa.descripcion:''} </span> 
                        </span>
                    </div>
                    <hr style="margin: 2px 0px 0px 0px; border-color: #e4e4e4; margin-top: 0px">
                    <div class="data-fluid">
                        <div id="listaProductos" style="height:300px; overflow-x: hidden; width:100%">
                            <table id="tablaListaProductos"  cellpadding="0" cellspacing="0" width="100%" class="table lcnp table-dark">
                                <thead>
                                    <tr >
                                        <td width="50%">Descripcion</td>
                                        <td width="10%">Cant</td>
                                        <td width="10%">Precio.U</td>
                                        <td width="10%">Desc</td>                                       
                                        <td width="19%">Total</td>                                        
                                    </tr>
                                </thead>
                                <tbody height="60%" id="productos-detail">
                                    <tr each={detail}>
                                        <td>
                                          <div class="block_container_comanda" onclick ={__CambiarDescripcion}>
                                          	<span class="title-detalle text-info clickable clearfix">{descripcion}</span>
                                          </div> 
                                          <div class="block_container_comanda" show={__MostrarIconoComandasPendientes(codigo)} onclick={__MostrarComandasPendientes}>
	                       					<img style="width:30px;" alt="" src="/dist/img/cocina.png">
	                                      </div>
                                        </td>
                                        <td >
                                            <span onclick ={__CambiarCantidad} class="labelDetalleVenta label-success cantidad clickable">{cantidad.toFixed(3)}</span>
                                        </td>
                                        <td >
                                            <span    class="labelDetalleVenta label-success precio-prod" >{precioUnitario.toFixed(2)}</span>
                                        </td>
                                        <td >
                                            <span onclick ={__CambiarDescuento} class="labelDetalleVenta label-success precio-prod clickable" >{porcentajeDesc.toFixed(2)}</span>
                                        </td>
                                       
                                        <td>
                                            <span class="precio-calc">{montoTotalLinea.toFixed(2)}</span>
                                        </td>
                                        <td>
                                            <button  onclick={__removeProductFromDetail} class="btn_eliminar_detalle btn-danger btn-xs btn-block clickable">X</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <hr style="margin: 0px; border-color: #e4e4e4;">
                        <div id="bordeBevelTop">
                        <table id="pagarTableInfo" width="100%">
                            <tbody>
                                <tr style="height:30px;">
                                    <td width="25%" id="bordeBevelLeft"> 
                                        <span id="pagarInfo"> {$.i18n.prop("factura.resumen.subTotal")} </span>
                                        <span id="cantidad-total">{subTotalGeneral  } </span> 
                                    </td>
                                    <td width="25%" id="bordeBevelLeft"> 
                                        <span id="pagarInfo">{$.i18n.prop("factura.resumen.descuento")}  </span>
                                        <span id="sigPeso">   </span>
                                        <span id="iva-total">{totalDescuentos}</span> 
                                    </td>
                                    <td width="25%" id="bordeBevelRight"> 
                                        <span id="pagarInfo">{$.i18n.prop("factura.resumen.impuesto")}  </span>
                                        <span id="sigPeso">      </span>
                                        <span id="subtotal">{totalImpuesto}</span> 
                                    </td>
                                    <td width="25%" id="bordeBevelRight"> 
                                        <span id="pagarInfo">{$.i18n.prop("factura.resumen.impuestoServ")}  </span>
                                        <span id="sigPeso">      </span>
                                        <span id="subtotal">{totalImpuestoServ}</span> 
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <hr style="margin: 0px; border-color: #e4e4e4; margin-top: 0px">                    
                </div>
                <div  class="row ">
                    <!-- <div class="col-md-12 col-sx-12 col-lg-12 col-sm-12 "  style="padding:10px 15px 18px 15px; ">
                        <div class="input-group">
                            <span onclick = {_EscogerClientes} title="Cambiar Nombre Tiquete" class="input-group-addon btnClientes" id="add-new-client"> 
                                <small class="fa fa-plus" style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                <span class="fa fa-user" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"></span> 
                            </span>
                            <input onclick={_EscogerClientes} type="text"  placeholder="Cliente" value="{cliente.nombreCompleto}"  name="datos_cliente" id="datos_cliente" autocomplete="off" >
                        </div>
                        Vendedor o Nuevo Vendedor
                        <div class="input-group">
                            <span  onclick={_EscogerVendedores} title="Vendedor" class="input-group-addon " > 
                                <span class="fa fa-user" aria-hidden="true" style="margin:3px 4px 0px 2px"></span> 
                            </span>
                            <input type="text" onclick={_EscogerVendedores} placeholder="Vendedor" value="{vendedor.nombreCompleto}"  name="v_vendedor" id="v_vendedor" autocomplete="off" >
                        </div>
                    </div> -->                                                                        
                    <div class="col-md-12 col-sx-12 col-lg-12 col-sm-12 "  style="padding:10px 15px 18px 15px; ">
                        <a class="pull-left" href="#"> <span onclick = {__CambiarNombreTiquete} class="label-titulos-articulo">Tikete a :{factura.nombreFactura}</span></a>
                    </div>                                                                        
                </div> 
                <hr style="margin: 0px; border-color: #e4e4e4;">
                <div  class="row  ">
                    <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12 " >   
                        <section class="contenedor-opciones">
                            <a href="#" class="opciones-menu" onclick = {__CrearFacturaTemporal} >
                                <i class="fa fa-clock-o">{$.i18n.prop("venta.en.espera")}</i>
                            </a>
                            <a  href="#" class="opciones-menu" onclick = {__ImprimirTiquete} >
                                <i class="fa fa-print">Tiquete</i>
                            </a> 
                            <a  href="#" class="opciones-menu" onclick = {__Limpiar} >
                                <i class="fa fa-trash">{$.i18n.prop("btn.limpiar")}</i>
                            </a>
                        </section>
                    </div>    
                </div>                 
                <div  class="row ">
                    <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12 " >   
                        <section class="contenedor-opciones">
                               <a show="{factura.id > 0}" href="#" class="opciones-menu" onclick = {__PantallaCambiarMesa} >
                                <i class="fa fa-exchange">{$.i18n.prop("titulo.cambiar.mesa")}</i>
                          	</a>
                           	<a show={separarCuenta} href="#" class="opciones-menu" onclick = {__MostrarSeperarCuentas} >
                                <i class="fa fa-scissors">{$.i18n.prop("separar.cuenta")}</i>
                          	</a>
                          

                        </section>
                    </div>    
                </div>                 
            </div>
            </div>  
            <div class="col-md-7 col-sm-7 col-lg-7 col-xs-12" style="padding: 0px 10px">
                <div class="block ">
                    <div class="head" style="text-align: center;">
                        <div class="row-form panel newPanel newContNavegacion" style="padding-left: 0px;padding-right: 0px; padding-top:6px;">
                            <form>
                                <ul id="tipo-busqueda">
                                    <li id="buscalo"  onclick = {__ListaDecodigos} class="">
                                        <h3><i class="glyphicon glyphicon-search" aria-hidden="true"></i>
                                            <img  src="{urlImagenBuscador}" width="40px" height="15px" >&nbsp;&nbsp; {$.i18n.prop("btn.consultar")}  
                                        </h3>
                                    </li>
                                    <li onclick = {__PantallaMesas} id="codificalo" class=""> <h3>
                                        <i class="glyphicon glyphicon-glass" aria-hidden="true"></i>
                                        <img  src="{urlImagenLector}" width="30px" height="15px">
                                        &nbsp;&nbsp; {$.i18n.prop("mesa.titulo")} </h3> 
                                    </li>                                    
                                    <li onclick = {__PantallaCategorias} id="navegador" class=""> <h3>
                                        <i class="glyphicon glyphicon-refresh" aria-hidden="true"></i>
                                        <img  src="{urlImagenNavegador}" width="30px" height="15px">
                                        &nbsp;{$.i18n.prop("titulo.categoria")}</h3>  
                                    </li>                                   
                                </ul>
                            </form>
                        </div>
                    </div>
                    
                </div>   
                <!--Ventana de los productos-->
                <div   class="col-sx-12 col-sm-12 col-md-12 col-lg-12 " >
                    <!--Seccion de categorias-->
                    <section show= {mostrarCategorias} class="lista-articulos clickable" >
                        <div show= {mostrarCategorias} id="item-categorias"class="product-item"  each ={categorias.data}  onclick={__ArticulosXCategorias}>
                            <img  style = "width:95px;height:95px;" alt="" class="img-responsive " src="data:image/png;base64,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">
                             <span class="label-titulos-articulo">{descripcion}</span>
                        </div>
                    </section>					
					<!--Seccion facturas por mesas-->
                    <section show= {mostrarFacturasMesas} class="lista-articulos clickable" >
                        <div show= {mostrarFacturasMesas} id="item-mesas-facturas" class="product-item"  each ={facturasXMesa.data}  onclick={__CargarFacturaEspera}>
                            <img  style = "width:120px;" alt="" class="img-responsive " src="/dist/img/factura.jpeg">
                             <span class="label-titulos-articulo">{nombreFactura}</span>
                        </div>
                    </section>
                    <!--Seccion de articulos-->
                    <section show= {mostrarArticulosXCategoria} class="lista-articulos clickable" >
                        <div class="product-item"  each ={inventariosXCategoria.data}   onclick={__AgregarProductoDePantalla}>
                            <img  style = "width:95px;height:95px;" alt="" class="img-responsive " src="data:image/png;base64,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">
                            <span class="label-titulos-articulo">{descripcion}</span>
                        </div>
                    </section>
                </div>    
                    <div onclick = {__BotonAnterior} show= {mostrarNavegador}  class="pull-left btnNavegacion " >
                        <i class="fa fa-arrow-left"></i>{$.i18n.prop("btn.Anterior")}
                    </div>
                    <div onclick = {__BotonSiguiente} show= {mostrarNavegador}  class="pull-right btnNavegacion " >
                        <i class="fa fa-arrow-right"></i>{$.i18n.prop("btn.siguiente")}
                    </div>
                    <!--Fin Seccion de articulos-->
                    <!--Seccion de codigo de barra-->
                    <section show={mostrarCodigoBarra} class="codigo-barra" >
                        <div class="barra">
                           <input onkeypress = {__addProductToDetail} type="text" class="form-control" id="codigoBarra" autofocus="autofocus" placeholder="{$.i18n.prop('titulo.digite.codigo.barra')}">
                       </div>    
                    </section>
                    <!--Fin Seccion de codigo de barra-->
                </div> 
                 
            </div>
    </div>
</div>       
<!--Fin Ventana de los productos-->
<!--Modal mostrar Facturas del Dias -->
<div id='modalFacturasDia' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("factura.listar.dia")} </h4>
            </div>
            <div class="modal-body">
                <table id="tableListarFacturasDia" class="display table responsive table-hover nowrap table-condensed tableListarFacturasDia "   cellspacing="0" width="100%">
                   <thead>
                    <tr>
                        <th class = "table-header" >{$.i18n.prop("listado.acciones")}                 </th>
                        <th class = "table-header" >{$.i18n.prop("factura.fecha.emision")}            </th>
                        <th class = "table-header" >{$.i18n.prop("factura.documento")}                </th>
                        <th class = "table-header" >{$.i18n.prop("factura.cliente")}                  </th>
                        <th class = "table-header" >{$.i18n.prop("factura.total")}                    </th>
                    </tr>
                    </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>                                                  </th>
                                <th>{$.i18n.prop("factura.fecha.emision")}            </th>
                                <th>{$.i18n.prop("factura.documento")}                </th>
                                <th>{$.i18n.prop("factura.cliente")}                  </th>
                                <th>{$.i18n.prop("factura.total")}                    </th>
                            </tr>
                        </tfoot>
                    </table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray  btn_big  btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--Modal mostrar  -->  




<!--fin Modal agregar el nombre de el tiquete temporal-->

<!-- Modal Tiquete - Nombre Dividir Cuenta-->
<div class="modal fade" id="ModalNombreTiqueteSepararCuenta" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="box-title"><i class="fa fa-user-o"></i>&nbsp {$.i18n.prop("factura.digite.nombreFactura")}     </h1>
            </div>
            <div class="modal-body">
                <form id = "formularioAgregarNombreTiquete" name ="formularioAgregarNombreTiquete "   class="advanced-search-form">
                    <div class="row">   
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="knob-label" >{$.i18n.prop("factura.nombreFactura")}</label>
                            <input type="text" class="campo tamanoLetraTotales cambioNombreSepararCuenta"  id="cambioNombreSepararCuenta" name="cambioNombreSepararCuenta" autofocus="autofocus" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="row">
                    <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <button type="button" class="btn-dark-gray btn-back  btn_big  pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
                        <button  onclick={__DividirFactura}   class="btn-green  btn_big  btn-add pull-right" >  {$.i18n.prop("btn.aplicar")}</button>
                    </div>
                </div>
            </div>
        </div>
     </div>
</div>
<!--fin Modal agregar el nombre de el tiquete temporal-->

<!--Modal Cambiar Cantidad-->


<!--Modal Cambiar precio-->
<div id='modalCambiarPrecio' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.precio")}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-12 col-md-12 col-lg-12 col-sm-12">
                        <div class="form-group has-success">
                            <label class="tituloDescuento">Precio:</label>
                            <input  type="number" class="form-control cambiarprecioArticulo" id="cambiarprecioArticulo" name = "cambiarprecioArticulo" autofocus="autofocus" min="0">
                        </div>
                    </div>
                </div> 
            </div>
            <div class="modal-footer">
                <button type="button" onclick ="{__cambiarElPrecio}" class="btn-green  btn_big  btn-edit pull-right">{$.i18n.prop("btn.aplicar")}</button>
            </div>
        </div>
    </div>
</div>

<!--Fin Cambiar precio-->




<!--Modal mostrar Articulos de la empresa -->
<div id='modalInventario' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("articulo.listar")} </h4>
            </div>
            <div class="modal-body">
                <form id="formularioParametros" name ="formularioParametros" >
                    <div class="row">
                        <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <label  >{$.i18n.prop("articulo.codigo")}  </label>
                            <input type="text" class="form-control codigoArt" id="codigoArt" name="codigoArt"  onkeypress={__ConsultarProductosCod} >
                        </div>
                        <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <label  >{$.i18n.prop("articulo.descripcion")}</label>
                            <input type="text" class="form-control descArticulo "   id="descArticulo" name="descArticulo" onkeypress={__ConsultarProductosDesc}>
                        </div>
                    </div> 
                </form>    
                <br>                   
                <table id="tableListarArticulos" class="display table responsive table-hover nowrap table-condensed tableListarArticulos " cellspacing="0" width="100%">
                    <thead>
                        <th class="table-header">{$.i18n.prop("articulo.codigo")}        </th>
                        <th class="table-header">{$.i18n.prop("articulo.descripcion")}   </th>
                        <th class="table-header">{$.i18n.prop("inventario.cantidad")}    </th>
                        <th class="table-header">{$.i18n.prop("articulo.precioPublico")} </th>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}       </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th >{$.i18n.prop("articulo.codigo")}         </th>
                            <th >{$.i18n.prop("articulo.descripcion")}   </th>
                            <th >{$.i18n.prop("inventario.cantidad")}    </th>
                            <th >{$.i18n.prop("articulo.precioPublico")} </th>
                            <th >                                        </th>
                        </tr>
                    </tfoot>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray  btn_big  btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->
<!--Modal mostrar Clientes de una sucursal -->
<div id="modalClientes" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("cliente.lista")}   </h4>
            </div>
            <div class="modal-body">
                <table id="tableListaCliente" class="table responsive display table-striped table-hover nowrap tableListaCliente " cellspacing="0" width="100%">
                   <thead>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                        <th class="table-header">{$.i18n.prop("cliente.cedula")}            </th>
                        <th class="table-header">{$.i18n.prop("cliente.nombreCompleto")}    </th>
                        <th class="table-header">{$.i18n.prop("cliente.nombreComercial")}   </th>
                        <th class="table-header">{$.i18n.prop("cliente.correoElectronico")} </th>
                        <th class="table-header">{$.i18n.prop("cliente.telefono")}          </th>
                        <th class="table-header">{$.i18n.prop("cliente.celular")}           </th>
                        
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>                                          </th>
                            <th>{$.i18n.prop("cliente.cedula")}           </th>
                            <th>{$.i18n.prop("cliente.nombreCompleto")}   </th>
                            <th>{$.i18n.prop("cliente.nombreComercial")}   </th>
                            <th>{$.i18n.prop("cliente.correoElectronico")}</th>
                            <th>{$.i18n.prop("cliente.telefono")}         </th>
                            <th>{$.i18n.prop("cliente.celular")}          </th>
                            
                        </tr>
                    </tfoot>                    
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back  btn_big  pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->






<!--Formulario de Pago-->
<!---Datos Final cuando no es un venta de Crucero -->
<div show={mostrarFormularioPago}>
		<div class="row">
			<div class="col-md-8 col-sm-8 col-lg-8 col-sx-12 ">
				<div class="box">
					<div class="box-header with-border fondoEncabezado">
						<h3 class="box-title">{$.i18n.prop("ventas.titulo")} </h3>
                        <h3 class="box-title pull-right ">{$.i18n.prop("ventas.tipo.cambio.titulo")} {tipoCambio.total} </h3>
					</div>
					<div class="box-body">
                        <form id="formularioFactura">
                            <div class="row">
                                <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                    <div class="row">
                                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                            <div class="form-group ">
                                                <label class="titleListaPrecio">Actividades Comerciales </label>  
                                                <select onchange= {__AsignarActividad} class="form-control selectActividadComercial"  name="selectActividadComercial" id="selectActividadComercial" >
                                                    <option  each={empresaActividadComercial}  value="{codigo}"   >{codigo}-{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="row">
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group ">
                                                <label>{$.i18n.prop("factura.condicion.pago")} </label> 
                                                <select  onchange= {__formaPago} class="form-control condicionVenta" id="condicionVenta" name="condicionVenta">
                                                    <option each={comboCondicionPagos} value="{estado}" selected="{factura.condicionVenta ==estado?true:false}" >{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>    
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group ">
                                                <label for="pago_tipoVentaL">{$.i18n.prop("factura.tipo.documento")} </label> 
                                                <select class="form-control tipoDoc" id="tipoDoc" name="tipoDoc"   >
                                                    <option each={comboTipoDocumentos} value="{estado}" selected="{factura.tipoDoc ==estado?true:false}" >{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label >{$.i18n.prop("factura.nota")}</label> 
                                        <input type="text" class="form-control nota" id="nota" name="nota" value="{factura.nota}">
                                    </div>
                                    <h3> <p class="text-primary">{$.i18n.prop("factura.emisor")}</p></h3>
                                    <div class="form-group ">
                                        <input   type="hidden" class="form-control" id="cliente" name="cliente" value="{cliente.id}">
                                        <label>{$.i18n.prop("factura.cliente")}</label> 
                                        <input onclick = {_EscogerClientes}  type="text" id="nombreCliente" name="nombreCliente" class="form-control"  value="{cliente.nombreCompleto}" readonly>
                                    </div>
                                    <div class="row">
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group ">
                                                <label>{$.i18n.prop("factura.nombreFactura")}</label> 
                                                <input type="text" id="nombreFactura" name="nombreFactura" class="form-control"  value="{factura.nombreFactura}" >
                                            </div>
                                        </div>
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group ">
                                                <label>{$.i18n.prop("factura.correoAlternativo")}</label> 
                                                <input type="email" id="correoAlternativo" name="correoAlternativo" class="form-control"  value="" >
                                            </div>
                                        </div>

                                    </div>        
                                    <div show = {mostrarCamposIngresoContado == false} class="form-group ">
                                        <label >{$.i18n.prop("factura.fecha.credito")}</label> 
                                        <div  class="form-group input-group date datepickerFechaCredito" data-provide="datepicker"  data-date-start-date="0d" data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaCredito" name="fechaCredito" id="fechaCredito" value="{factura.fechaCredito}" >
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>    
                                    <div class="form-group " show = {mostrarCamposIngresoContado ==false}>
                                        <label>{$.i18n.prop("factura.plazoCredito")}</label> 
                                        <input type="number" id = "plazoCreditoL"  name "plazoCreditoL" class="form-control plazoCreditoL" value="{factura.plazoCredito}" >
                                    </div>
                                </div>
                                <div  class= "col-md-6 col-sx-6 col-sm-6 col-lg-6" >
                                    <div class="form-group has-success">
                                        <label for="pago_transporteL">{$.i18n.prop("factura.resumen.efectivo")} </label> 
                                        <input onkeyup={ __TotalDeEfectivoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="campo tamanoLetraTotales totalEfectivo " id="totalEfectivo" name="totalEfectivo" >
                                    </div>
                                    <div  class="form-group has-success">
                                        <label for="pago_efectivoL">{$.i18n.prop("factura.resumen.tarjeta")} <span class="teclashift">(Tecla =shift )</span></label> 
                                        <input onkeyup={ __TotalDeTarjetaAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="campo tamanoLetraTotales totalTarjeta" id="totalTarjeta" name="totalTarjeta"   >
                                    </div>
                                    <div  class="form-group has-success">
                                        <label for="pago_tarjetaL">{$.i18n.prop("factura.resumen.banco")} </label> 
                                            <input onkeyup={ __TotalDeBancoAPagar } onBlur = {__CalculaCambioAEntregarOnblur} onkeypress = {__CalculaCambioAEntregarKeyPress} type="number" step="any" class="campo tamanoLetraTotales totalBanco" id="totalBanco" name = "totalBanco"  >
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" id='codigoActividad' name='codigoActividad'  value="{factura.codigoActividad}" >
                            <input type="hidden" id='id'                      name='id'                      value="{factura.id}" >
                            <input type="hidden" id='mesa'                    name='mesa'                    value="{mesa.id}" >
                            <input type="hidden" id='estado'                  name='estado'                  value="{factura.estado}" >
                            <input type="hidden" id='plazoCredito'            name='plazoCredito'            value="{factura.plazoCredito}" >
                            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
                            <input type="hidden" id='subTotal'                name='subTotal'                value="{factura.subTotal}" >
                            <input type="hidden" id='totalComprobante'        name='totalComprobante'        value="{factura.totalComprobante}" >
                            <input type="hidden" id='totalServGravados'       name='totalServGravados'       value="{factura.totalServGravados}" >
                            <input type="hidden" id='totalServExentos'        name='totalServExentos'        value="{factura.totalServExentos}" >
                            <input type="hidden" id='totalMercanciasGravadas' name='totalMercanciasGravadas' value="{factura.totalMercanciasGravadas}" >
                            <input type="hidden" id='totalMercanciasExentas'  name='totalMercanciasExentas'  value="{factura.totalMercanciasExentas}" >
                            <input type="hidden" id='totalGravado'            name='totalGravado'            value="{factura.totalGravado}" >
                            <input type="hidden" id='totalExento'             name='totalExento'             value="{factura.totalExento}" >
                            <input type="hidden" id='totalVenta'              name='totalVenta'              value="{factura.totalVenta}" >
                            <input type="hidden" id='totalDescuentos'         name='totalDescuentos'         value="{factura.totalDescuentos}" >
                            <input type="hidden" id='totalVentaNeta'          name='totalVentaNeta'          value="{factura.totalVentaNeta}" >
                            <input type="hidden" id='totalImpuesto'           name='totalImpuesto'           value="{factura.totalImpuesto}" >
                            <input type="hidden" id='totalImpuestoServ'       name='totalImpuestoServ'       value="{factura.totalImpuestoServ}" >
                            <input type="hidden" id='totalCambioPagar'        name='totalCambioPagar'        value="{factura.totalCambioPagar}" >
                            <input type="hidden" id='detalleFactura'          name='detalleFactura'          value="{factura.detalleFactura}" >
                        </form>   
                    </div>
                    <div class="box-footer">
                        <button onclick={_AtrasFacturaFinal} class="btn-dark-gray btn-back pull-left">  {$.i18n.prop("btn.volver")}</button>
                        <button onclick={__AplicarYcrearFactura}  class="btn-green btn-add pull-right"> </i> {$.i18n.prop("btn.aplicar")}</button>
                    </div>
                </div>
                   
            </div>
            <div class="col-md-4 col-sm-4 col-lg-4 col-sx-12 ">
		        <div class="box">
				    <div class="box-header with-border"><h1 class="box-title">Detalles Facturacion</h1></div>
				    <div class="box-body">
				    <!--right sidebar-->
                        <aside class="right-sidebar">
                            <!--Booking details-->
                            <article class="booking-details clearfix">
                                <h1><span id="lblSCS">{$.i18n.prop("factura.resumen.venta")}</span></h1>
                                    <div class="containerTotales">
                                        <div class="elementoTotales" >
                                           <div class="tituloTotal">
                                             {$.i18n.prop("factura.resumen.subTotal")}
                                           </div>
                                           <div class="valorTotal">  {subTotalGeneral} </div>
                                        </div>
                                        <div class="elementoTotales" >
                                           <div class="tituloTotal">{$.i18n.prop("factura.resumen.descuento")}</div>
                                           <div class="valorTotal">  {totalDescuentos}  </div>
                                        </div>
                                        <div class="elementoTotales" >
                                           <div class="tituloTotal">{$.i18n.prop("factura.resumen.impuesto")}</div>
                                           <div class="valorTotal">  {totalImpuesto}  </div>
                                        </div>   
                                        <div class="elementoTotales" >
                                           <div class="tituloTotal">{$.i18n.prop("factura.resumen.exoneracion")}</div>
                                           <div class="valorTotal">  {montoExoneracion}  </div>
                                        </div>   
                                        <div class="elementoTotales" >   
                                           <div class="tituloTotal">{$.i18n.prop("factura.resumen.impuestoServ")}</div>
                                           <div class="valorTotal">  {totalImpuestoServ}   </div>
                                        </div>
                                        <div class="elementoTotales" >   
                                           <div class="tituloTotal">{$.i18n.prop("factura.resumen.total")}</div>
                                           <div class="valorTotal">  {totalComprobante}  </div>
                                        </div>
                                        <div class="elementoTotales" show={mostrarCamposIngresoContado}>   
                                           <div class="tituloTotal">{$.i18n.prop("factura.resumen.cambio")}</div>
                                           <div class="valorTotal">{totalCambioPagarSTR} </div>
                                        </div>
                                        
                                    </div>
                                    <div class="containerBotonesPagar">
                                        <div class="elementoPagar">
                                             <button onclick={__MoverMontoTarjeta}  class="btn-Pagar btn-PagarICON pull-right"> </i> Pagar Tarjeta</button>
                                        </div>
                                    </div>
                            </article>
                        </aside>
                    </div><!-- fin box-body-->
				</div><!-- fin box -->
		    </div>
        </div>  
</div>  
<!--Fin Ventana de los billetes-->   
 <!--Ventana de los billetes-->
            <div class="row" show={mostrarFormularioPago}>
                <div   class="col-sx-12 col-sm-12 col-md-12 col-lg-12 " >
                    <!--Seccion de Billetes-->
                    <section  class="lista-articulos" >
                        <div class="billetes1" each={billetes}   onclick={_sumarBilletes}>
                            <img  alt="" class="sizeBilletes" src="{imagen}">
                            <a href="#">{modena} {descripcion}</a>
                        </div>
                    </section>
                   <!--Fin Seccion de Billetes-->
                </div> 
            </div>       
        <!--Fin Ventana de los billetes--> 
<!--Modal Cambiar Cantidad-->

<div id='modalCambiarCantidad' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Cambiar Cantidad</h1>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <label class="tituloClienteNuevo" >Cantidad </label>
                        <input type="number" class="form-control cambiarCantidadArticulo tamanoClienteNuevo modalInputCambioPrecio"  id="cambiarCantidadArticulo" name="cambiarCantidadArticulo"   autofocus="autofocus" min="0" autocomplete="off">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__recalculacionDelDetalle}   class=" btn-green pull-right modalCambioPrecioBotones" > Aplicar </button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--Fin Cambiar Cantidad-->


<div id='modalCambiarDescuento' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Aplicar el Descuento al Cliente</h1>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <label class="tituloClienteNuevo" >Digite el Porcentaje % </label>
                        <input type="number" class="form-control aplicarDescuento tamanoClienteNuevo modalInputCambioPrecio"  id="aplicarDescuento" name="aplicarDescuento"   autofocus="autofocus" min="0" autocomplete="off">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__actualizarDescuento}   class=" btn-green pull-right modalCambioPrecioBotones" > Aplicar </button>
                </div>
            </div>
        </div>
    </div>
</div>


<div id='ModalAgregarNombreTiquete' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Crear Proforma</h1>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <label class="tituloClienteNuevo" >Digite el nombre </label>
                        <input type="text" class="form-control cambioNombreFactura tamanoClienteNuevo modalInputCambioPrecio"  id="cambioNombreFactura" name="cambioNombreFactura"     autocomplete="off">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                     <button type="button" class="btn-dark-gray btn-back  btn_big  pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__AgregarNombreFacturaTemporal}   class="btn-green  btn_big  btn-add pull-right" >  {$.i18n.prop("btn.aplicar")}</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div id='ModalCambiarNombreTiquete' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Modificar el Nombre Factura</h1>
            </div>
            <div class="modal-body">
                <input type="hidden" id='idFactura'  name='idFactura'  value="{factura.id}" >
                <input type="hidden" id='nombreFactura'  name='nombreFactura'  value="{factura.nombreFactura}" >
                <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <label class="tituloClienteNuevo" >Digite el nombre </label>
                        <input type="text" class="form-control cambioNombreTiquete tamanoClienteNuevo modalInputCambioPrecio"  id="cambioNombreTiquete" name="cambioNombreTiquete"     autocomplete="off">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                     <button type="button" class="btn-dark-gray btn-back  btn_big  pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__ModificarNombreTiquete}   class="btn-green  btn_big  btn-add pull-right" >  {$.i18n.prop("btn.aplicar")}</button>
                </div>
            </div>
        </div>
    </div>
</div>




<!--Modal Cambiar Descripcion-->

<div id='modalCambiarDescripcion' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Modificar Descripcion del Articulo</h1>
            </div>
            <div class="modal-body">
                <input type="hidden" id='idFactura'  name='idFactura'  value="{factura.id}" >
                <input type="hidden" id='nombreFactura'  name='nombreFactura'  value="{factura.nombreFactura}" >
                <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <label class="tituloClienteNuevo" >Digite la descripcion nueva </label>
                        <input type="text" class="form-control cambiarDescripcionArticulo tamanoClienteNuevo modalInputCambioPrecio"  id="cambiarDescripcionArticulo" name="cambiarDescripcionArticulo"     autocomplete="off">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                     <button type="button" class="btn-dark-gray btn-back  btn_big  pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
                </div>
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6" >
                    <button  onclick={__cambiarDescripcionDetalle}   class="btn-green  btn_big  btn-add pull-right" >  {$.i18n.prop("btn.aplicar")}</button>
                </div>
            </div>
        </div>
    </div>
</div>





<style type="text/css">
  div.labelBotones:hover{
        color:#30ed17 !important;

    }
       .labelBotones {
        font-weight: 600 !important;
        font-size: 16px !important;
        font-family: Roboto,sans-serif !important;
        color: #ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        line-height: 30px;
        border-collapse: separate;
        text-align: center;
        cursor: pointer;
        text-align: center !important;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
    }
     
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
    }
    .modalInputCambioPrecio{
        font-size: 70px !important;
        color:blue !important;
        border-radius: 16px !important;
    }
    .modalCambioPrecioBotones{
         border-radius: 16px !important;
         font-size: 60px !important;
    }

    .botones-funcionales {
        float: left;
        margin-left: 6px;
        margin-top: 16px;
    }
    .btn-PagarICON:before {
        font-family: FontAwesome;
        content: "\f09d ";
    }
    .btn-Pagar {
        background-color: #4cae4c;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-left: 24px;
        padding-right: 20px;
        font-size: 30px;
        font-weight: bold;
        margin-right: 15px;
        border: none;
        float: right;
        cursor: pointer;
    }
    .containerBotonesPagar{
        display:flex;
        margin-top: 2%;
    }
    .elementoPagar{

    }
    .teclashift {
        font-weight: 700;
        font-size: 27px !important;
        text-align: center;
        color: red;

    }
    .tituloDescuento{
        font-size: 50px;
    }
    .cambiarDescripcionArticulo{
        font-size: 40px;
        font-weight: 800;
        color: black;
        height: 3%;
        text-align: center;

    }
    .cambiarCantidadArticulo{
        font-size: 80px;
        font-weight: 800;
        color: black;
        height: 3%;
        text-align: center;

    }
    .cambiarprecioArticulo{
        font-size: 40px;
        font-weight: 800;
        color: black;
        height: 3%;
        text-align: center;

    }
    .aplicarDescuento{
        font-size: 40px;
        font-weight: 800;
        color: black;
        height: 3%;
        text-align: center;
    }
    .billetes1{
        margin-left: 1%;
        /* margin-bottom: 9px; */
        box-shadow: 0px 0px 6px 0px rgba(0, 0, 0, 0.22);
        display: flex;
        flex-direction: column;
        align-items: center;
        align-self: flex-start;
    }
    .tituloTotal{
        font-weight: 600 !important;
        font-size: 30px !important;
        font-family: Roboto, sans-serif !important;
        color:#d3ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
         border-collapse: separate;
        text-align: center;
        border: none;
        text-align: center !important;
    }
    .valorTotal{
        font-weight: 600 !important;
        font-size: 30px !important;
        font-family: Roboto, sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        border-collapse: separate;
        text-align: center;
        border: none;
        text-align: center !important;
    }
    .containerTotales{
        display:flex;
        flex-direction: column;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
    }
    .elementoTotales{
        display: flex;
        justify-content: space-around;
    
    }
    .sizeBilletes{
        height:90px;
        width:160px;
    }

    @media only screen and (max-width: 1024px) and (min-width:768px)  {
         div.labelBotones:hover{
        color:#30ed17 !important;

    }
       .labelBotones {
        font-weight: 600 !important;
        font-size: 16px !important;
        font-family: Roboto,sans-serif !important;
        color: #ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        line-height: 30px;
        border-collapse: separate;
        text-align: center;
        cursor: pointer;
        text-align: center !important;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
    }
     
    .sizeBilletes{
        height:90px;
        width:160px;
    }
    .tituloTotal{
        font-weight: 600 !important;
        font-size: 20px !important;
        font-family: Roboto, sans-serif !important;
        color:#d3ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        border-collapse: separate;
        text-align: center;
        border: none;
        text-align: center !important;

    }
    .valorTotal{
        font-weight: 600 !important;
        font-size: 20px !important;
        font-family: Roboto, sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        border-collapse: separate;
        text-align: center;
        border: none;
        text-align: center !important;

    }
    .containerTotales{
        display:flex;
        flex-direction: column;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
    }



    }

        .clickable {
            cursor: pointer;
        }
        .modal {
    position: fixed;
    top: 3%;
    right: 3%;
    left: 3%;
    bottom: 3%;
    width: auto;
    margin: 0;
    }
    .modal-body {
    max-height: 350px;
    padding: 15px;
    overflow-y: auto;
    -webkit-overflow-scrolling: touch;
    }

    .modal-body { height: 60%; }

    @media (max-width: 480px) 
    {
        .modal.fade.in {
            top: 10px;
    }
    }

    td.col-1, th.col-1 {
    width: 8.33333%; }

    td.col-2, th.col-2 {
    width: 16.66667%; }

    td.col-3, th.col-3 {
    width: 25%; }

    td.col-4, th.col-4 {
    width: 33.33333%; }

    td.col-5, th.col-5 {
    width: 41.66667%; }

    td.col-6, th.col-6 {
    width: 50%; }

    td.col-7, th.col-7 {
    width: 58.33333%; }

    td.col-8, th.col-8 {
    width: 66.66667%; }

    td.col-9, th.col-9 {
    width: 75%; }

    td.col-10, th.col-10 {
    width: 83.33333%; }

    td.col-11, th.col-11 {
    width: 91.66667%; }

    td.col-12, th.col-12 {
    width: 100%; }

    td.col-sm-1, th.col-sm-1 {
    width: 8.33333%; }

    td.col-sm-2, th.col-sm-2 {
    width: 16.66667%; }

    td.col-sm-3, th.col-sm-3 {
    width: 25%; }

    td.col-sm-4, th.col-sm-4 {
    width: 33.33333%; }

    td.col-sm-5, th.col-sm-5 {
    width: 41.66667%; }

    td.col-sm-6, th.col-sm-6 {
    width: 50%; }

    td.col-sm-7, th.col-sm-7 {
    width: 58.33333%; }

    td.col-sm-8, th.col-sm-8 {
    width: 66.66667%; }

    td.col-sm-9, th.col-sm-9 {
    width: 75%; }

    td.col-sm-10, th.col-sm-10 {
    width: 83.33333%; }

    td.col-sm-11, th.col-sm-11 {
    width: 91.66667%; }

    td.col-sm-12, th.col-sm-12 {
    width: 100%; }

    td.col-md-1, th.col-md-1 {
    width: 8.33333%; }

    td.col-md-2, th.col-md-2 {
    width: 16.66667%; }

    td.col-md-3, th.col-md-3 {
    width: 25%; }

    td.col-md-4, th.col-md-4 {
    width: 33.33333%; }

    td.col-md-5, th.col-md-5 {
    width: 41.66667%; }

    td.col-md-6, th.col-md-6 {
    width: 50%; }

    td.col-md-7, th.col-md-7 {
    width: 58.33333%; }

    td.col-md-8, th.col-md-8 {
    width: 66.66667%; }

    td.col-md-9, th.col-md-9 {
    width: 75%; }

    td.col-md-10, th.col-md-10 {
    width: 83.33333%; }

    td.col-md-11, th.col-md-11 {
    width: 91.66667%; }

    td.col-md-12, th.col-md-12 {
    width: 100%; }

    td.col-lg-1, th.col-lg-1 {
    width: 8.33333%; }

    td.col-lg-2, th.col-lg-2 {
    width: 16.66667%; }

    td.col-lg-3, th.col-lg-3 {
    width: 25%; }

    td.col-lg-4, th.col-lg-4 {
    width: 33.33333%; }

    td.col-lg-5, th.col-lg-5 {
    width: 41.66667%; }

    td.col-lg-6, th.col-lg-6 {
    width: 50%; }

    td.col-lg-7, th.col-lg-7 {
    width: 58.33333%; }

    td.col-lg-8, th.col-lg-8 {
    width: 66.66667%; }

    td.col-lg-9, th.col-lg-9 {
    width: 75%; }

    td.col-lg-10, th.col-lg-10 {
    width: 83.33333%; }

    td.col-lg-11, th.col-lg-11 {
    width: 91.66667%; }

    td.col-lg-12, th.col-lg-12 {
    width: 100%; }

    td.col-xl-1, th.col-xl-1 {
    width: 8.33333%; }

    td.col-xl-2, th.col-xl-2 {
    width: 16.66667%; }

    td.col-xl-3, th.col-xl-3 {
    width: 25%; }

    td.col-xl-4, th.col-xl-4 {
    width: 33.33333%; }

    td.col-xl-5, th.col-xl-5 {
    width: 41.66667%; }

    td.col-xl-6, th.col-xl-6 {
    width: 50%; }

    td.col-xl-7, th.col-xl-7 {
    width: 58.33333%; }

    td.col-xl-8, th.col-xl-8 {
    width: 66.66667%; }

    td.col-xl-9, th.col-xl-9 {
    width: 75%; }

    td.col-xl-10, th.col-xl-10 {
    width: 83.33333%; }

    td.col-xl-11, th.col-xl-11 {
    width: 91.66667%; }

    td.col-xl-12, th.col-xl-12 {
    width: 100%; }
    
</style> 

<script>
    var self = this;
    // Detalle de la factura es una coleccion de articulos
    self.detail                = []
    self.detailPorSeparar      = []
    self.detailFacturaSeparada = []
    self.pendientesComanda     = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.comboEstados          = []
    self.comboCondicionPagos   = []
    self.comboTipoDocumentos   = []
    self.factura                = {
        id:null,
	    fechaCredito:null,
	    fechaEmision:null,
	    condicionVenta:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalImpuestoServ:0,
	    totalComprobante:0,
	    totalEfectivo:0,
        totalTarjeta:0,
        totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
	    codigoMoneda:"",
	    estado:1,
	    cliente:{
            id:0,
            nombreCompleto:""
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        },
        mesa:{
            id:0,
            descripcion:"",
            impuestoServicio:false
       }

    }
    self.facturaRespaldo                = {
            id:null,
    	    fechaCredito:null,
    	    fechaEmision:null,
    	    condicionVenta:"",
    	    plazoCredito:0,
    	    tipoDoc:"",
    	    medioPago:"",
    	    nombreFactura:"",
    	    direccion:"",
    	    nota:"",
    	    comanda:"",
    	    subTotal:0,
    	    totalTransporte:0,
    	    total:0,
    	    totalServGravados:0,
    	    totalServExentos:0,
    	    totalMercanciasGravadas:0,
    	    totalMercanciasExentas:0,
    	    totalGravado:0,
    	    totalExento:0,
    	    totalVenta:0,
    	    totalDescuentos:0,
    	    totalVentaNeta:0,
    	    totalImpuesto:0,
    	    totalImpuestoServ:0,
    	    totalComprobante:0,
    	    totalEfectivo:0,
            totalTarjeta:0,
            totalCambioPagar:0,
    	    totalBanco:0,
    	    totalCredito:0,
    	    montoCambio:0,
    	    totalCambio:0,
    	    codigoMoneda:"",
    	    estado:0,
    	    cliente:{
                id:0,
                nombreCompleto:"",
            },
    	    vendedor:{
                id:0,
                nombreCompleto:""
            },
            mesa:{
                id:0,
                descripcion:"",
                impuestoServicio:false
           }
    }
    self.empresa              = {}
    self.separarCuenta  	  = false;
    
    self.item                  = null;
    self.comentarioComanda     = "";
    self.articulo              = null;
    self.articulos             = {data:[]}
    self.clientes              = {data:[]}
    self.detalleFactura        = {data:[]}
    self.detalleComanda        = {data:[]}
    self.cliente               = {}
    self.vendedor              = {
        id:null,
        nombreCompleto:""
    };
    self.informacion_tabla             = []
    self.informacion_tabla_articulo    = []
    self.informacion_tabla_clientes    = []
    self.idiomaDataTable               = {}
    
    self.subTotalGeneral               = 0
    self.totalDescuentos               = 0
    self.totalImpuesto                 = 0
    self.totalImpuestoServ             = 0

    self.totalComprobantePorSeparar    = 0	
    self.subTotalGeneralPorSeparar     = 0
    self.totalDescuentosPorSeparar     = 0
    self.totalImpuestoPorSeparar       = 0
    self.totalImpuestoServPorSeparar   = 0

    self.totalComprobanteFactSeparada  = 0	
    self.subTotalGeneralFactSeparada   = 0
    self.totalDescuentosFactSeparada   = 0
    self.totalImpuestoFactSeparada     = 0
    self.totalImpuestoServFactSeparada = 0
    
    self.totalComprobante              = 0;
    self.totalCambioPagar              = 0;
    self.totalCambioPagarSTR           = 0;
    self.todasProvincias               = {data:[]}
    self.todosCantones                 = {data:[]}
    self.todosDistritos                = {data:[]}
    self.todosBarrios                  = {data:[]}
    self.cantones                      = []
    self.distritos                     = []
    self.barrios                       = []
    self.montoExoneracion     = 0

    self.parametrosPaginacion = {
        id:null,
        paginaActual:0,
        cantidadPorPagina:10,
        total:0,
        tipoVenta:1,

    }
    self.categorias                  = {
        data:[],
        pagination:{
            total:0,
            current_page:0,
            per_page:0,
            last_page:0,
            from:0,
            to:0
        }
    }
    self.categoria = {
        id:null,
        descripcion:""
    }
    self.inventariosXCategoria = {
        data:[],
        pagination:{
            total:0,
            current_page:0,
            per_page:0,
            last_page:0,
            from:0,
            to:0
        }
    }
    
 	self.mesa = {
         id:null,
         descripcion:"",
         impuestoServicio:false,
     }
     self.mesas  = {
         data:[],
         pagination:{
             total:0,
             current_page:0,
             per_page:0,
             last_page:0,
             from:0,
             to:0
         }
     }
     self.mesasCambiar  = {
         data:[],
         pagination:{
            total:0,
            current_page:0,
            per_page:0,
            last_page:0,
            from:0,
            to:0
        }
     }
     self.facturasXMesa = {
         data:[],
         pagination:{
             total:0,
             current_page:0,
             per_page:0,
             last_page:0,
             from:0,
             to:0
         }
     }

    self.mostrarOrdenesCocinaPendientes = false
    self.mostarParaCrearNuevaVentas     = false //true
    self.mostrarSepararCuenta		    = false //true
    self.mostrarCambiarMesa             = false;

    self.mostrarCodigoBarra             = false //true
    self.mostrarFormularioPago          = false
    self.mostarParaCrearNuevaFactura    = false //true
    self.mostrarCamposIngresoContado    = true //true
    self.mostrarReferencias             = false 
    self.mostrarListadoArticulos        = false
    self.mostrarImpuestoServicio        = false
    self.factEspera =null
    self.urlImagenNavegador   = '/dist/img/navegador.png';
    self.urlImagenLector      = '/dist/img/codigo_barra.png';
    self.urlImagenBuscador    = '/dist/img/buscador.png';
    self.urlImagenChange      = '/dist/img/change.jpeg';
    
    
    self.primeraVezBilleteClick = false
    self.tipoCambio = {
        total:0,
        id:null
    }
    self.rol = {
        rolAdministrador:0
    }
     self.validarRolCommand = {
                        usuarioSistema : "",
                        claveSistema:""
                    }   
    self.actividadesComerciales        = []
    self.mostarAbrirCajon = true 
    self.pesoPrioridad  = 1
    self.numeroLinea    = 1
    self.cantArticulos  = 1
     self.actividadComercial = {
        codigo:"",
        descripcion:""
    }
    self.tipoCambio = {
        total:0,
        id:null
    }
    self.on('mount',function(){
        __ListaActividadesComercales()
         var xTriggered = 0;
           window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
             __Teclas(evento.keyCode,event)
            disableF5(evento);
        }, false );
        $( "#cambiarCantidadArticulo" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
               cambiarCantidadModal()
           }
        });

        $( "#aplicarDescuento" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
               aplicarModalDescuento()
           }
        });

        $( "#cambioNombreFactura" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
               __AplicarCambioNombreModal()
           }
        });
        $( "#cambioNombreTiquete" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
               __cambioNombreTiqueteModal()
           }
        });

         $( "#cambiarDescripcionArticulo" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
               __cambiarDescripcionDetalleModal()
           }
        });
        
    })

    function disableF5(e) { 
     //   alert(e.keyCode)
        if ((e.which || e.keyCode) == 116) e.preventDefault(); 
        if ((e.which || e.keyCode) == 114) e.preventDefault(); //f3
        if ((e.which || e.keyCode) == 112) e.preventDefault(); //f1
        if ((e.which || e.keyCode) == 117) e.preventDefault(); 
    };

__AsignarActividad(e){
    BuscarActividadComercial()
}


__EntradaDinero(){
  modalEntradaSalidaDinero(1)
}   
__SalidaDinero(){
    modalEntradaSalidaDinero(2)
}

function modalEntradaSalidaDinero(tipo){
 var parametros = {
        tipo:tipo,
    }
    riot.mount('entrada-salida',{parametros:parametros});
}


function BuscarActividadComercial(){
    var codigo =$('.selectActividadComercial').val()
    if(self.empresaActividadComercial == null){
       return    
    }
    if(self.empresaActividadComercial.length == 0){
       return    
    }
    $.each(self.empresaActividadComercial, function( index, modeloTabla ) {
        if(modeloTabla.codigo == codigo  ){
           self.actividadComercial.descripcion = modeloTabla.codigo +"-" + modeloTabla.descripcion
            self.actividadComercial.codigo =  codigo
            self.factura.codigoActividad = codigo
            self.update()
        }

    })
}

/**
*  Lista de los clientes
**/
function __ListaActividadesComercales(){
    $.ajax({
        url: 'ListaEmpresaActividadComercialPorPricipalAjax.do',
        datatype: "json",
        global: false,
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.empresaActividadComercial   = result.aaData
                self.update()
                BuscarActividadComercial()
                 $("#formularioFactura").validate(reglasDeValidacionFactura());
                $("#formularioAgregarNombreTiquete").validate(reglasAgregarNombre());
                $("#formularioAgregarNombreTiquete1").validate(reglasAgregarNombre());
                $("#formularioModalCambiarNombreTiquete").validate(reglasCambiarNombre());
                _Empresa()
                __informacionData()
                __informacionData_vendedores()
                __InicializarTabla('.tableListaCliente')
                __InicializarTabla('.tableListaInventario')
                __InicializarTabla('.tableListaVendedor')
                agregarInputsCombos_Articulo()
                __comboCondicionPago()
                __ComboTipoDocumentos(0)
           //     __ListaDeClientes()
                __ListaDeVendedores()
                 __TipoCambio()
                __ListaMesas()
                __RolAdministrador()   
                cargaBilletes()
                $(".nota").attr("maxlength", 80);
                $('.datepickerFechaCredito').datepicker(
                    {
                    format: 'yyyy-mm-dd',
                    startDate: '-0d',
                    todayHighlight:true,
                    }
                );  
                __agregarArticulos()      
                var retrievedObject = JSON.parse(localStorage.getItem('DetallesNueva'));
                if(retrievedObject !=null){
                    self.detail = retrievedObject
                    var facturaObject = JSON.parse(localStorage.getItem('facturaNueva'));
                    self.factura = facturaObject
                    var clienteObject = JSON.parse(localStorage.getItem('cliente'));
                    self.cliente = clienteObject
                    self.update()
                    __calculate()
                }

            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
    return
}
/**
* Verificar el Rol Admnistrador
**/
function __RolAdministrador(){
    $.ajax({
        url: "RolUsuarioAjax.do",
        datatype: "json",
        global: false,
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message);
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.rol = modeloTabla
                      // if(self.rol.rolAdministrador == 0){
                      //   self.separarCuenta = false;
                      // }
                       self.update()
                    });
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
        }
    });

}
/**
* Validar seguridad de ruta autorizada
**/ 
__SeguridadVentas(){
    if(self.rol.rolAdministrador == 1){
        return true
    }
   __validarRolAdministrador('#formularioModalRolUsuario','validarRolAdministradorAjax.do');
    
}

function __validarRolAdministrador(formulario,url){
    var resultado = false;	
    if ($(formulario).valid()) {
        var formulario = $(formulario).serialize();
        $.ajax({
          type : "POST",
          sync: true,
          dataType : "json",
          data : formulario,
          url : url,
          success : function(data) {
             if (data.status != 200) {
            	 serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                	  mensajeAdvertencia("No Autorizado,Su usuario no es una administrador")
                 }
                return resultado;
             } else {
            	 var modelTabla = {};
            	 data.listaObjetos.forEach(function(modelo) {
            		 modeloTabla = modelo;
            	 })
               	if(modeloTabla.aceptacion === 1){
                    self.validarRolCommand = {
                        usuarioSistema : "",
                        claveSistema:""
                    }   
                    self.update()
               	    $('#modalRolUsuario').modal('hide') 
                    $('.modal-backdrop').remove();
                    if(self.autorizarBorrado == 0){
                        $(self.rutaAutorizada).modal({backdrop: 'static', keyboard: true}) 
                        $(self.rutaAutorizada).modal('show')    	
                    }
                    if(self.autorizarBorrado == 1){
                        self.autorizarBorrado = 0
                        self.update()
                        eliminarDetalle()
                    }
                    if(self.autorizarBorrado == 2){
                        self.autorizarBorrado = 0
                        self.update()
                        refrescarPagina()
                    }

                    return true;
               	}else{
                    self.rutaAutorizada = '';
                    self.update()
                    mensajeAdvertencia("No autorizado")
                    return true;
                }
          
             }
          },
          error : function(xhr, status) {
             console.log(status);
             mensajeErrorServidor(xhr, status);
          }
        });
    }
    
}    

/**
* refrescar una pagina
**/
function refrescarPagina(){
     __Init()
   
}
 /**
* agregar producto desde la pantalla de articulos
**/
__AgregarProductoDePantalla(e){
   var item = e.item
   self.articulo = item;
   self.update()
    __agregarArticulo(1)
    $('#codigoBarra').val(null)
}   
/**
*  Mostrar si escoge una categorias
**/
__ArticulosXCategorias(e){
    var item = e.item
    self.categoria = item
    self.update()
    __ListaArticulosXCategorias()
}
/**
*  boton anterior de la pantalla de categorias or articulos
**/    
__BotonAnterior(){
	if (self.mostrarFacturasMesas){
        self.facturasXMesa.pagination.current_page = self.facturasXMesa.pagination.current_page - 1
        self.facturasXMesa.pagination.current_page = self.facturasXMesa.pagination.current_page > 1?self.facturasXMesa.pagination.current_page:0;
        self.parametrosPaginacion.paginaActual = self.parametrosPaginacion.paginaActual > 1?self.parametrosPaginacion.paginaActual - 12:0 
        self.update()
      //  __ListaFacturasXMesas()
    }else {
        if(self.categoria.id == 0){//cuando esta usando la pantalla de categorias
            self.categorias.pagination.current_page = self.categorias.pagination.current_page - 1
            self.categorias.pagination.current_page = self.categorias.pagination.current_page > 1?self.categorias.pagination.current_page:0;
            self.parametrosPaginacion.paginaActual = self.parametrosPaginacion.paginaActual > 1?self.parametrosPaginacion.paginaActual - 12:0 
            self.update()
            __ListaCategorias()

        }else{// cuando esta usando la pantalla de articulos
            self.inventariosXCategoria.pagination.current_page = self.inventariosXCategoria.pagination.current_page - 1
            self.inventariosXCategoria.pagination.current_page = self.inventariosXCategoria.pagination.current_page > 1?self.inventariosXCategoria.pagination.current_page:0;
             self.parametrosPaginacion.paginaActual = self.parametrosPaginacion.paginaActual > 1?self.parametrosPaginacion.paginaActual - 12:0 
            self.update()
            __ListaArticulosXCategorias()
        }    	
    }  
}
/**
*  boton siguiente de la pantalla de categorias or articulos
**/    
__BotonSiguiente(){
	if (self.mostrarFacturasMesas){
        if(self.facturasXMesa.pagination.current_page <  self.facturasXMesa.pagination.last_page){
	        self.facturasXMesa.pagination.current_page = self.facturasXMesa.pagination.current_page + 1
	        self.parametrosPaginacion.paginaActual += 12
	        self.update()
	       	//__ListaFacturasXMesas()
        }
    }else {
	    if(self.categoria.id == 0){//cuando esta usando la pantalla de categorias
	        if(self.categorias.pagination.current_page <  self.categorias.pagination.last_page){
	        self.categorias.pagination.current_page = self.categorias.pagination.current_page + 1
	        self.parametrosPaginacion.paginaActual += 12
	        self.update()
	        __ListaCategorias()
	        }
	    }else{ //cuando esta usando la pantalla de articulos
	        if(self.inventariosXCategoria.pagination.current_page <  self.inventariosXCategoria.pagination.last_page){
	        	self.inventariosXCategoria.pagination.current_page = self.inventariosXCategoria.pagination.current_page + 1
	        	self.parametrosPaginacion.paginaActual += 12
	        	self.update()
	        	__ListaArticulosXCategorias()
	        }
	    }
    }
}
/**
*  Lista de los clientes
**/
function __ListaCategorias(){
    //Primera vez 
    if( self.categorias.pagination.current_page == 0){
        self.inventariosXCategoria.pagination.current_page = 0    
        self.parametrosPaginacion.cantidadPorPagina = 12
        self.parametrosPaginacion.paginaActual = 0
        self.parametrosPaginacion.total = 0
        self.parametrosPaginacion.tipoVenta = 1
        self.update()
    }
    $('#cantidadPorPagina').val(self.parametrosPaginacion.cantidadPorPagina)
    $('#paginadaActual').val(self.parametrosPaginacion.paginaActual )
    var formulario = $('#FormPaginacion').serialize();
    $.ajax({
        url: 'ListarPaginacionCategoriasAjax.do',
        datatype: "json",
        data: formulario,
        method:"POST",
        success: function (result) {
            if(result.aaData.length > 0){
               self.categorias.data = result.aaData
               self.categorias.pagination.total = result.recordsTotal
               self.categorias.pagination.last_page = Math.round(result.recordsTotal/10)
               self.mostrarCodigoBarra          = false;
               self.mostrarNavegador            = true
               self.mostrarCategorias           = true //muestra la pantalla de imagenes de articulos   
               self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias   
               self.mostrarMesas	            = false //muestra la pantalla mesas
               self.mostrarFacturasMesas        = false //muestra las facturas por mesa
               self.update()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
/**
*  Lista de los clientes
**/
function __ListaArticulosXCategorias(){
    //Primera vez 
    if( self.inventariosXCategoria.pagination.current_page == 0){
        self.inventariosXCategoria.pagination.current_page = 0    
        self.parametrosPaginacion.cantidadPorPagina = 12
        self.parametrosPaginacion.paginaActual = 0
        self.parametrosPaginacion.total = 0
        self.parametrosPaginacion.tipoVenta = 1
        self.update()
    }
    $('#cantidadPorPagina').val(self.parametrosPaginacion.cantidadPorPagina)
    $('#paginadaActual').val(self.parametrosPaginacion.paginaActual )
    $('#categoria').val(self.categoria.id )
    var formulario = $('#FormPaginacion').serialize();
    $.ajax({
        url: 'ListarPaginacionArticuloAjax.do',
        datatype: "json",
        data: formulario,
        method:"POST",
        success: function (result) {
            if(result.aaData.length > 0){
               self.inventariosXCategoria.pagination.total = result.recordsTotal
               self.inventariosXCategoria.pagination.last_page = Math.round(result.recordsTotal/10)
               self.inventariosXCategoria.data = result.aaData
               self.mostrarCodigoBarra          = false;
               self.mostrarNavegador            = true
               self.mostrarCategorias           = false //muestra la pantalla de imagenes de articulos   
               self.mostrarArticulosXCategoria  = true //muestra la pantalla de imagenes de categorias
               self.mostrarMesas	            = false //muestra la pantalla mesas               
               self.mostrarFacturasMesas        = false //muestra las facturas por mesa
               self.update()
            }else{
                mensajeAdvertencia($.i18n.prop("articulo.por.categoria"))
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
function getCantidadDePaginas(total){
    return total / 10;
}
/**
*  Evento click para mostrar las categorias de los productos
**/
__PantallaCategorias(){
    mostrarCategorias()
}
/**
*  Mostrar las categorias de los productos
**/
function mostrarCategorias(){
    self.categoria = {
        id:0,
        descripcion:""
    }
    self.categorias  = {
        data:[],
        pagination:{
            total:0,
            current_page:0,
            per_page:0,
            last_page:0,
            from:0,
            to:0
        }
    }
     self.inventariosXCategoria = {
        data:[],
        pagination:{
            total:0,
            current_page:0,
            per_page:0,
            last_page:0,
            from:0,
            to:0
        }
    }
    self.mostrarNavegador            = true
    self.mostrarCategorias           = true //muestra la pantalla de imagenes de articulos   
    self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias   
    self.mostrarCodigoBarra          = false
    self.mostrarMesas	             = false //muestra la pantalla mesas
    self.mostrarFacturasMesas        = false //muestra las facturas por mesa
    self.update()
    __ListaCategorias()

}
/**
*  Mostrar pantalla de codigo de barra
**/
__PantallaCodigoBarra(){
    self.mostrarCodigoBarra          = true;
    self.mostrarNavegador            = false
    self.mostrarCategorias           = false //muestra la pantalla de imagenes de articulos   
    self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias   
    self.mostrarMesas	             = false //muestra la pantalla mesas
    self.mostrarFacturasMesas        = false //muestra las facturas por mesa
    self.update()
    $('#codigoBarra').focus()
}

/**
* Camps requeridos
**/
var reglasDeValidacionFactura = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
            nota:{
                 maxlength:255,
             },
             direccion:{
                 maxlength:255,
             }         
		},
		ignore : []

	});
	return validationOptions;
};

var reglasAgregarNombre = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
            cambioNombreFactura : {
				required : true,
                maxlength:200,
                minlength:2,
			},   
		},
		ignore : []

	});
	return validationOptions;
};
var reglasCambiarNombre = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
            cambioNombreTiquete : {
				required : true,
                maxlength:200,
                minlength:2,
			},   
		},
		ignore : []

	});
	return validationOptions;
};
/**
* Facturas por Dia
**/
_ListaFacturasDia(){
    ListadoFacturasDelDia()
}
/**
*  Facturas del Dia
**/
function ListadoFacturasDelDia(){
      $("#tableListarFacturasDia").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListarFacturasDia')  
        $.ajax({
            url: "ListarFacturasDelDiaAjax.do",
            datatype: "json",
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTableDia();
                    loadListar(".tableListarFacturasDia",idioma_espanol,self.formato_tabla_dias,result.aaData)
                    agregarInputsCombos_Facturas_Dias();
                    ActivarEventoFiltro(".tableListarFacturasDia")
                     $('#modalFacturasDia').modal('show')    
                     __reimprimir()
                }else{
                    __InformacionDataTableDia();
                     agregarInputsCombos_Facturas_Dias();
                }           
            },
            error: function (xhr, status) {
                mensajeErrorServidor(xhr, status);
                console.log(xhr);
            }
        });
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Facturas_Dias(){
     // Agregar los input de busqueda 
     var cont = 50
    $('.tableListarFacturasDia tfoot th').each( function (e) {
        var title = $('.tableListarFacturasDia thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        cont = cont +1
        if ( $(this).index() != 0    ){
	      	$(this).html( '<input id = "filtroCampos'+cont+'" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
/**
* formato del listado de facturas del dia
**/
function __InformacionDataTableDia(){
    self.formato_tabla_dias = [ 
                               {'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
                               },

                               {'data' :'fechaEmisionSTR'   ,"name":"fechaEmisionSTR"    ,"title" : $.i18n.prop("factura.fecha.emision")     ,"autoWidth" :true ,
                               },
                             
                               {'data' :'numeroConsecutivo'                    ,"name":"numeroConsecutivo"                     ,"title" : $.i18n.prop("factura.documento")   ,"autoWidth" :true ,
                                   "render":function(numeroConsecutivo,type, row){
									    return __TipoDocumentos(numeroConsecutivo,row)
	 							    }
                               },
                                {'data' :'cliente'                    ,"name":"cliente"                     ,"title" : $.i18n.prop("factura.cliente")   ,"autoWidth" :true ,
                                   "render":function(cliente,type, row){
									    return cliente ==null?"":row.cedula != "999999999999"?cliente:row.nombreFactura;
	 							    }
                               },
                               {'data' :'totalComprobanteSTR'            ,"name":"totalComprobanteSTR"     ,"title" : $.i18n.prop("factura.total") ,"autoWidth" :true },
	      		            ];
    self.update();
   
}

function __Opciones(){
var agregar  = '<a href="#"  class="btn btnReimprimir btn-primary form-control" title="Imprimir" role="button"> <i class="glyphicon glyphicon glyphicon-print"></i></a>';
  return  agregar;
}
/**
*  tipo de documento
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
    case "88":
        return  "Proforma:"+row.id
        break;
    case "87":
        return  "TiqueteInterno:"+row.id
        break;
    case "86":
        return  "NC.Interno:"+row.numeroConsecutivo
        break;

    default:
        return  numeroConsecutivo
    }
}
/**
* Reimprimir factura
**/
function __reimprimir(){
	$('#tableListarFacturasDia').on('click','.btnReimprimir',function(e){
		var table = $('#tableListarFacturasDia').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        consultaParaReimprimir(data,1)
	});
}
/**
*Consulta la Reimprimir
**/
function consultaParaReimprimir(data,tipoImpresion){
     $.ajax({
        url: "MostrarFacturaAjax",
        datatype: "json",
        data: {idFactura:data.id},
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia( data.message);
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                    var parametros = {
                          factura:modeloTabla,
                          facturaDia:tipoImpresion
                      }
                       riot.mount('ptv-imprimir',{parametros:parametros});
                    });
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}
/**
* Camps requeridos
**/
var reglasDescuentoAplicar = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			aplicarDescuento : {
		        numeroMayorIgualCero:true,
			}           
		},
		ignore : []

	});
	return validationOptions;
};
/**
* Aplicar el descuento
**/
__CambiarDescuento(e){
    $("#aplicarDescuento").attr("maxlength", 7);
    $("#formularioDescuento").validate(reglasDescuentoAplicar());
    self.item = e.item; 
    $('#modalCambiarDescuento').modal({backdrop: 'static', keyboard: true}) 
    $('#modalCambiarDescuento').on('shown.bs.modal', function () {
        $( "#aplicarDescuento" ).val(0)
        $( "#aplicarDescuento" ).focus()
        $( "#aplicarDescuento" ).select()
        

    })
    
}
/**
*Cambiar Cantidad del Articulo
**/
__CambiarCantidad(e){
    var obj = self.pendientesComanda.find(o => o.key === self.item.codigo);
    if(typeof obj !== "undefined"){
        mensajeAdvertencia($.i18n.prop("comanda.mensaje.elimina.articulo"));
        return true;    
    }		
    var cantidad = e.currentTarget.value;
   self.item = e.item; 
   self.rutaAutorizada = '';
   self.update()
   if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        self.rutaAutorizada = '#modalCambiarCantidad';
        self.update()
        $("#usuarioSistema").val("")
        $("#claveSistema").val("")
        $('#modalRolUsuario').modal({backdrop: 'static', keyboard: true}) 
        $('#modalRolUsuario').modal('show')     
   }else{
        $('#modalCambiarCantidad').modal({backdrop: 'static', keyboard: false}) 
        $('#modalCambiarCantidad').on('shown.bs.modal', function () {
          $("#cambiarCantidadArticulo" ).val(self.item.cantidad) 
          $("#cambiarCantidadArticulo" ).focus()
          $("#cambiarCantidadArticulo" ).select()
        })
   }
}
/**
*Cambiar descripcion
**/
__CambiarDescripcion(e){
   self.item = e.item; 
   self.update()
   self.rutaAutorizada = '';
   self.update()
    if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){

    self.rutaAutorizada = '#modalCambiarDescripcion';
    self.update()
    $("#usuarioSistema").val("")
        $("#claveSistema").val("")
        $('#modalRolUsuario').modal({backdrop: 'static', keyboard: false}) 
        $('#modalRolUsuario').modal('show')     
   }else{
        $('#modalCambiarDescripcion').modal({backdrop: 'static', keyboard: false}) 
        $('#modalCambiarDescripcion').on('shown.bs.modal', function () {
            $("#cambiarDescripcionArticulo" ).val(self.item.descripcion)
            $("#cambiarDescripcionArticulo" ).focus()
            $("#cambiarDescripcionArticulo" ).select()
        })
        
   }
}
/**
*Cambiar precio del producto
**/
__CambiarPrecio(e){
   self.item = e.item; 
   self.update()
   $( "#cambiarprecioArticulo" ).focus()
   $( "#cambiarprecioArticulo" ).val( e.item.precioUnitario)
   $('#modalCambiarPrecio').modal({backdrop: 'static', keyboard: true})   
}
/**
* Tipo Cambio de moneda
**/
function __TipoCambio(){
    
    $.ajax({
        url: "MostrarTipoCambioActivoAjax.do",
        datatype: "json",
        global: false,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message);
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.tipoCambio = modeloTabla
                       self.tipoCambio.total = formatoDecimales(self.tipoCambio.total,2)
                       self.update()
                    });
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}
/**
* Imprimir 
**/
__Imprimir(){
    var factura = self.factura
    consultaParaReimprimir(factura,0)
}
/**
* Imprimir tikete
**/
__ImprimirTiquete(){
    imprimirTiquete()
}
/**
*imprimir tiquete
**/
function imprimirTiquete(){
    var factura = self.factura
    self.update()
    var parametros = {
        facturaParametro:self.factura,
        impuestoServicio:self.ImpuestoServicio
    }
    riot.mount('tiquete-imprimir',{parametros:parametros});

}
/**
*  Obtiene el valor de lo digitado en el campo de efectivo
**/
__TotalDeEfectivoAPagar(e){
    self.totalCambioPagar    = 0
    self.totalCambioPagarSTR =0
    self.update()
    _calculoEnterPago()  
}
/**
*  Obtiene el valor de lo digitado en el campo de Tarjeta
**/
__TotalDeTarjetaAPagar(e){
    self.totalCambioPagar    = 0
    self.totalCambioPagarSTR =0
    self.update()
    _calculoEnterPago()  
}
/**
*  Obtiene el valor de lo digitado en el campo de Banco
**/
__TotalDeBancoAPagar(e){
    self.totalCambioPagar    = 0
    self.totalCambioPagarSTR =0
    self.update()
    _calculoEnterPago()  
}
/**
*   Calculo del cambio entregar en el evento onblur
**/
__CalculaCambioAEntregarOnblur(e){
    self.totalCambioPagar    = 0
    self.totalCambioPagarSTR =0
    self.update()
   _calculoEnterPago()  
}
/**
*   Calculo del cambio entregar en el evento keyPress
**/
__CalculaCambioAEntregarKeyPress(e){
    self.totalCambioPagar    = 0
    self.totalCambioPagarSTR =0
    self.update()
     _calculoEnterPago()  
}

function _calculoEnterPago(){
    var sumaMontosEntregadosParaCambios =0
    sumaMontosEntregadosParaCambios  = __valorNumerico($('.totalTarjeta').val())
    sumaMontosEntregadosParaCambios += __valorNumerico($('.totalBanco').val()) 
    sumaMontosEntregadosParaCambios += __valorNumerico($('.totalEfectivo').val())
    //Si no ingresado montos no realiza las operaciones de calculos
    if(sumaMontosEntregadosParaCambios == 0){
        self.factura.totalCambioPagar = self.factura.totalComprobante * -1
        self.totalCambioPagarSTR =formatoDecimales(self.factura.totalCambioPagar,2)
        self.update()
        return
    }
    self.factura.totalCambioPagar = 0
    var totalEntregado = redondeoDecimales(sumaMontosEntregadosParaCambios,2)
    var totalFactura   = redondeoDecimales(self.factura.totalComprobante,2)
    totalEntregado     = __valorNumerico(totalEntregado)
    totalFactura       = __valorNumerico(totalFactura)  
    self.factura.totalCambioPagar = totalEntregado - totalFactura
    self.factura.totalCambioPagar =__valorNumerico(self.factura.totalCambioPagar)   
    self.totalCambioPagar = redondeoDecimales(self.factura.totalCambioPagar,2)
    self.totalCambioPagarSTR =formatoDecimales(self.totalCambioPagar,2)
    self.update()
}
/**
* consultando por descripcion
**/
__ConsultarProductosDesc(e){
 if (e.keyCode != 13) {
        return;
    } 
 __ListaDeArticulosPorDescripcion($("#codigoArt").val(),e.currentTarget.value)   
} 
/**
*Consultando por codigo
**/
__ConsultarProductosCod(e){
 if (e.keyCode != 13) {
        return;
    } 
 __ListaDeArticulosPorDescripcion(e.currentTarget.value,$("#descArticulo").val())   
}
/**
* mostrar la lista de articulos de la empresa
**/
function __ListaDeArticulosPorDescripcion(){
    if($('#codigoArt').val() =='' && $('#descArticulo').val() =='' ){
        return
    }
    $(".tableListarArticulos").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
    var formulario = $('#formularioParametros').serialize();
    $.ajax({
        url: 'ListarPorDescripcionCodigoArticuloAjax.do',
        datatype: "json",
        method:"GET",
        data :formulario,
        success: function (result) {
            if(result.aaData.length > 0){
                _informacionData_Articulo()
                self.articulos.data           = result.aaData
                self.update()
                loadListar(".tableListarArticulos",idioma_espanol,self.informacion_tabla_articulo,self.articulos.data)
                agregarInputsCombos_Articulo()
                ActivarEventoFiltro(".tableListarArticulos")
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
/**
 * Listar codigos  llamado del modal para presentar los articulos
 **/   
 __ListaDecodigos(){
     self.mostrarListadoArticulos = true
     self.update()
    $('.descArticulo').val(null)
    $('.codigoArt').val(null)
    $(".tableListarArticulos").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
    $('#modalInventario').modal('show')    
 }
/**
*  Buscar la Factura Pendiente en espera
**/
__CargarFacturaEspera(e){
    self.pendientesComanda     = []
    self.factEspera = e.item
    self.factura = e.item
    self.update()
   __FacturaEnEspera(e.item)
}
/**
* Cambiar Nombre del tiquete
**/
__CambiarNombreTiquete(){
    if(self.factura.id ==null){
        return
    }
    $('#ModalCambiarNombreTiquete').modal({backdrop: 'static', keyboard: false}) 
    $('#ModalCambiarNombreTiquete').on('shown.bs.modal', function () {
        $('.cambioNombreTiquete').val(self.factura.nombreFactura)
        $('.cambioNombreTiquete').focus()
        $('.cambioNombreTiquete').select()
    })
    
}
/**
*  Crear la factura temporal o espera
**/
__CrearFacturaTemporal(){
	__CrearFacturaTemporalFunc();
}
/**
*Crear Factura Temporal

**/
function __CrearFacturaTemporalFunc(){
    self.seIncluyoUnArticulo = null
    self.update()
 	if(self.factura.id == null){
        if(self.detail.length != 0 ){
            $('#ModalAgregarNombreTiquete').modal({backdrop: 'static', keyboard: true}) 
            $('#ModalAgregarNombreTiquete').on('shown.bs.modal', function () {
                $('.cambioNombreFactura').focus()
                $('.cambioNombreFactura').select()
            })
            return
        }
    }else{
        self.seIncluyoUnArticulo = 1
        self.update()
        aplicarFactura(1)    
    }
    self.factEspera = null
    self.update()
}
/**
* cCambiar el nombre de la factura
**/
__ModificarNombreTiquete(){
   
    __cambioNombreTiqueteModal()
}

function __cambioNombreTiqueteModal(){
  if(!$('#ModalCambiarNombreTiquete').is(':visible')){
         return 
     }
    self.factura.nombreFactura = $('.cambioNombreTiquete').val()
    self.update()
    if(self.factura.id == null){
        return
    }
    var parametros = {
        idFactura : self.factura.id,
        nombreFactura : self.factura.nombreFactura
    }

      $.ajax({
        url: "ModificarNombreTiquteAjax",
        datatype: "json",
        data: parametros,
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                mensajeAdvertencia($.i18n.prop("error.factura.no.existe"));
                return
            }else{
                if (data.message != null && data.message.length > 0) {
                    self.factura = {
                        id:null
                    }
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.factura = modeloTabla
                       self.update()
                    });
                    if(self.factura.id == null){
                         mensajeAdvertencia($.i18n.prop("error.factura.no.existe"));
                    }else{
                        mensajeToasExito(data.message);
                    }
                }
                 $('#ModalCambiarNombreTiquete').modal('hide') 
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}
/**
*  Agregar el nombre a la factura temporal
**/
__AgregarNombreFacturaTemporal(){
    __AplicarCambioNombreModal()
}
function __AplicarCambioNombreModal(){
    $('#ModalAgregarNombreTiquete').modal('hide') 
    self.factura.nombreFactura = $('.cambioNombreFactura').val()==null?"":$('.cambioNombreFactura').val();
    self.mesa.id = self.factura.mesa.id
    self.update()
    aplicarFactura(1)      
    self.factEspera = null
    self.update()
    self.mostrarCategorias           = false //muestra la pantalla de imagenes de articulos   
    self.mostrarFacturasMesas        = true //muestra las facturas por mesa
    self.mostrarMesas	             = false //muestra la pantalla mesas               
    self.mostrarCodigoBarra          = false;
    self.mostrarNavegador            = true
    self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias
    self.mostarParaCrearNuevaVentas  = true
    self.mostrarSepararCuenta		 = false //true
    self.mostrarCambiarMesa = false;

    self.update()        

}
/**
** Se aplica o se crea una Factura cargada en la pantalla
**/
__AplicarYcrearFactura(){
 	if(self.detailFacturaSeparada.length > 0 ){
 	 	aplicarFactura(2, true)
 		self.mostrarFormularioPago = false;
 	 	self.update();
 	 	//Se carga la factura principal
 	 	__FacturaEnEspera(self.facturaRespaldo); 	
 	 	mostrarSeperarCuentasFun();
 		self.update(); 		
 	}else{
 	 	aplicarFactura(2) 		
 	}
}
/**
* Aplicar la factura
**/
function aplicarFactura(estado){
	aplicarFactura(estado, false);
}
/**
* Aplicar la factura
**/
function aplicarFactura(estado, separarFactura){
    if(self.detail.length == 0 ){
        mensajeAdvertencia($.i18n.prop("factura.alert.sin.detalles"))
        return
    }
    if($('#condicionVenta').val() == "02"  ){
        if($('#fechaCredito').val() == null || $('#fechaCredito').val() == 0){
            mensajeAdvertencia($.i18n.prop("factura.alert.fechaCredito"))
            return
        }
        if($('#plazoCreditoL').val() < 0 || $('#plazoCreditoL').val() == null || $('#plazoCreditoL').val() == 0){
            mensajeAdvertencia($.i18n.prop("factura.alert.plazoCredito"))
            return
        }
    }else{
        // Si no es credito y el estado no es pendiente se debe verificar si ingresaron el monto a pagar
        if(estado !=1){
            if(__valorNumerico($('#totalTarjeta').val()) == 0 && __valorNumerico($('#totalBanco').val()) == 0 && __valorNumerico($('#totalEfectivo').val()) == 0){
                 mensajeAdvertencia($.i18n.prop("error.factura.monto.ingresado"))
                return
            }
            var montoEntregado = __valorNumerico($('#totalTarjeta').val())  + __valorNumerico($('#totalBanco').val()) + __valorNumerico($('#totalEfectivo').val())
            montoEntregado = redondeoDecimales(__valorNumerico(montoEntregado),2)
            var resultado  = redondeoDecimales( __valorNumerico(self.factura.totalComprobante),2)
            if(__valorNumerico(resultado) > __valorNumerico(montoEntregado)  ){
                mensajeAdvertencia($.i18n.prop("error.factura.monto.ingresado.es.menor.ala.venta"))
                return
            }
            //Si el cliente esta pagando con tajeta, banco debe ser igual a la venta
            var tarjeta = __valorNumerico($('#totalTarjeta').val())
            var banco = __valorNumerico($('#totalBanco').val())
            if(tarjeta != 0 || banco !=0){
                if(resultado != montoEntregado  ){
                    mensajeAdvertencia($.i18n.prop("error.factura.monto.tarjeta.banco.igual.venta"))
                    return
                        
                }
            }
        }
    } 
    if ($("#formularioFactura").valid()) {
        crearFactura(estado, separarFactura)  
    }
}
/**
* Limpiar Pantalla
**/
__Limpiar(){
    __SeguridadLimpiar()
}
/**
*Seguridad
**/
function __SeguridadLimpiar(){
     self.autorizarBorrado = 2
    self.update()
    if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        if(self.detail.length > 0){
            self.rutaAutorizada = '';
            self.update()
            $("#usuarioSistema").val("")
            $("#claveSistema").val("")
            $('#modalRolUsuario').modal({backdrop: 'static', keyboard: true}) 
            $('#modalRolUsuario').modal('show')     
        }else{
           __Init()
        }
    }else{
        __Init()
    }
}
/**
*  Inicializar las variables de trabajos
**/
function __Init(){
    self.primeraVezBilleteClick = false
    self.mostrarListadoArticulos == false
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.comboEstados          = []
    self.comboCondicionPagos        = []
    self.comboTipoDocumentos   = []
    self.montoExoneracion              = 0
    self.descripcionArticulo = ""
    $('.cambioNombreFactura').val(null)
    self.factura                = {
        id:null,
	    fechaCredito:null,
	    fechaEmision:null,
	    condicionVenta:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalImpuestoServ:0,
	    totalComprobante:0,
	    totalEfectivo:0,
        totalTarjeta:0,
        totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
        codigoMoneda:"",
	    estado:0,
	    cliente:{
            id:0,
            nombreCompleto:"",
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        },
        mesa:{
            id:0,
            descripcion:"",
            impuestoServicio:false
       }
    }
    self.factura.mesa = self.mesa;
    localStorage.setItem('DetallesNueva', JSON.stringify(self.detail));
    localStorage.setItem('facturaNueva', JSON.stringify(self.factura));
     localStorage.setItem('cliente', JSON.stringify(self.factura.cliente));
    self.item                  = null;
    self.articulo              = null;
    self.clientes              = {data:[]}
    self.detalleFactura        ={data:[]}
    self.cliente               = {};
    self.vendedor = {
        id:0,
        nombreCompleto:""
    }
    self.informacion_tabla             = []
    self.informacion_tabla_articulo    = []
    self.informacion_tabla_clientes    = []
    self.idiomaDataTable               = {}
    self.mostrarFormularioPago         = false
    self.mostarParaCrearNuevaFactura   = true
    self.mostrarCamposIngresoContado   = true;
    self.mostarParaCrearNuevaVentas    = true
    self.mostrarSepararCuenta		    = false //true
    self.mostrarCambiarMesa = false;

    self.mostrarCodigoBarra            = true
    self.mostrarCodigoBarra            = true;
    self.mostrarFormularioPago         = false
    self.mostarParaCrearNuevaFactura   = true
    
    self.mostrarReferencias            = false 
    self.mostrarMesas	           	   = true //muestra la pantalla mesas
    self.mostrarFacturasMesas          = false
    	self.parametrosPaginacion = {
            id:null,
            paginaActual:0,
            cantidadPorPagina:10,
            total:0,
            tipoVenta:1
        }
        self.categorias = {
            data:[],
            pagination:{
                total:0,
                current_page:0,
                per_page:0,
                last_page:0,
                from:0,
                to:0
            }
        }
        self.categoria = {
            id:0,
            descripcion:""
        }
        self.inventariosXCategoria = {
            data:[],
            pagination:{
                total:0,
                current_page:0,
                per_page:0,
                last_page:0,
                from:0,
                to:0
            }
        }
    self.urlImagenNavegador   = '/dist/img/navegador.png';
    self.urlImagenLector      = '/dist/img/codigo_barra.png';
    self.urlImagenBuscador    = '/dist/img/buscador.png';
    self.urlImagenChange      = '/dist/img/change.jpeg';
    
    //totales
    self.totalComprobante     = 0;
    self.subTotalGeneral  =0;
    self.totalComprobante =0;
    self.totalDescuentos  =0;
    self.totalImpuesto    =0;
    self.totalImpuestoServ=0;
    self.totalCambioPagar =0;
    self.montoExoneracion              = 0
    self.totalCambioPagarSTR =0
    self.pesoPrioridad =  0
    self.numeroLinea =0
    self.cantArticulos =0
    self.update()
    $('#condicionVenta').prop("selectedIndex", 0);
    $('#tipoDoc').prop("selectedIndex", 0);
    $('#estado').prop("selectedIndex", 0);
    $(".totalBanco").val(null)   
    $(".totalTarjeta").val(null)   
    $(".totalEfectivo").val(null)   
    $("#plazoCreditoL").val(null)
    $("#correoAlternativo").val(null)
    $("#nombreFactura").val(null)
    $("#nota").val(null)
    $("#fechaCredito").val(null)
    $('.datepickerFechaCredito').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-0d',
              todayHighlight:true,
            }
    );
    $(".nota").attr("maxlength", 80);
    $("#aplicarDescuento").val(null)
    // Tipo de Pagos
     __comboCondicionPago()
     //Tipos de Documentos
      __ComboTipoDocumentos(0)
      //Estados
      __ComboEstados()
     mostrarCategorias()
     $('#codigoBarra').val(null)
     $('#codigoBarra').focus()
}
/**
*  Inicializar las variables de trabajos
**/
function __InitDatos(){
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.descripcionArticulo = ""
    $('.cambioNombreFactura').val(null)
    self.factura                = {
        id:null,
	    fechaCredito:null,
	    fechaEmision:null,
	    condicionVenta:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalImpuestoServ:0,
	    totalComprobante:0,
	    totalEfectivo:0,
        totalTarjeta:0,
        totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
	    codigoMoneda:"",
	    estado:0,
	    cliente:{
            id:0,
            nombreCompleto:"",
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        },
        mesa:{
            id:0,
            descripcion:"",
            impuestoServicio:false
       }
    }
    self.factura.mesa = self.mesa;
    self.item                  = null;
    self.articulo              = null;
    self.clientes              = {data:[]}
    self.detalleFactura        ={data:[]}
    self.cliente               = {};
    self.vendedor = {
        id:0,
        nombreCompleto:""
    }
    self.informacion_tabla             = []
    self.informacion_tabla_articulo    = []
    self.informacion_tabla_clientes    = []
    self.idiomaDataTable               = {}
    self.categorias = {
        data:[],
        pagination:{
            total:0,
            current_page:0,
            per_page:0,
            last_page:0,
            from:0,
            to:0
        }
    }
    self.categoria = {
        id:0,
        descripcion:""
    }
    self.inventariosXCategoria = {
        data:[],
        pagination:{
            total:0,
            current_page:0,
            per_page:0,
            last_page:0,
            from:0,
            to:0
        }
    }
    //totales
    self.totalComprobante     = 0;
    self.subTotalGeneral  =0;
    self.totalComprobante =0;
    self.totalDescuentos  =0;
    self.totalImpuesto    =0;
    self.totalImpuestoServ =0;		  
    self.totalCambioPagar =0;
    self.totalCambioPagarSTR =0
    self.pesoPrioridad =  0
    self.numeroLinea =0
    self.cantArticulos =0
    self.update()    
}
/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(factura){
    if(factura == null){
        return
    }
     __Init()
    $.ajax({
        url: "ListarDetlleByFacturaAjax.do",
        datatype: "json",
        async:false,
        data: {idFactura:factura.id},
        method:"POST",
        success: function (data) {
            if(data.aaData.length > 0){
               cargarDetallesFacturaEnEspera(data.aaData)
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
    $('#codigoBarra').val(null)
    $('#codigoBarra').focus()
}
/**
*  Cargar detalles Factura en espera
**/
function cargarDetallesFacturaEnEspera(data){
    self.detail = [];
    self.factura = null
    self.update()
    $.each(data, function( index, modeloTabla ) {
        if(self.factura ==null){
            self.factura = modeloTabla.factura
            self.totalComprobante         = formatoDecimales(modeloTabla.factura.totalComprobante,2);
            self.factura.totalEfectivo    = 0
            self.factura.totalBanco       = 0
            self.factura.totalTarjeta     = 0
            self.factura.totalCambioPagar = 0
            self.totalCambioPagarSTR =0
            self.factura.fechaCredito = self.factura.fechaCredito !=null?__displayDate_detail(self.factura.fechaCredito):null
            self.cliente  = self.factura.cliente
            self.vendedor = self.factura.vendedor    
        }
        self.detail.push({
            numeroLinea     : modeloTabla.numeroLinea,
            pesoPrioridad    :modeloTabla.numeroLinea,
            codigo          : modeloTabla.codigo,
            tipoImpuesto    : modeloTabla.tipoImpuesto,
            tipoImpuestoMag   : "",
            impuestoMag:0,
            montoImpuestoMag:0,
            precio:0,
            codigoTarifaMag:'',
            descripcion     : modeloTabla.descripcion,
            cantidad        : __valorNumerico(modeloTabla.cantidad),
            precioUnitario  : __valorNumerico(modeloTabla.precioUnitario),
            impuesto        : __valorNumerico(modeloTabla.impuesto),
            montoImpuesto   : __valorNumerico(modeloTabla.montoImpuesto),
            montoImpuesto1  : 0,
            montoDescuento  : __valorNumerico(modeloTabla.montoDescuento),
            porcentajeDesc  : __valorNumerico(modeloTabla.porcentajeDesc),
            subTotal        : __valorNumerico(modeloTabla.subTotal),
            montoTotalLinea : __valorNumerico(modeloTabla.montoTotalLinea),
            montoTotal      : __valorNumerico(modeloTabla.montoTotal),
            costo           : __valorNumerico(modeloTabla.costo),
            porcentajeGanancia :__valorNumerico(modeloTabla.porcentajeGanancia),
            montoExoneracion:__valorNumerico(modeloTabla.montoExoneracion),
            porcentajeExoneracion:__valorNumerico(modeloTabla.porcentajeExoneracion),
            fechaEmisionExoneracion:modeloTabla.fechaEmisionExoneracion,
            nombreInstitucionExoneracion:modeloTabla.nombreInstitucionExoneracion,
            numeroDocumentoExoneracion:modeloTabla.numeroDocumentoExoneracion,
            tipoDocumentoExoneracion:modeloTabla.tipoDocumentoExoneracion
        });
        self.update()
    })
    self.totalCambioPagar = 0
    self.totalCambioPagarSTR =0
    self.update()
    $(".nombreFactura").val(self.factura.nombreFactura)
    $(".correoAlternativo").val(self.factura.correoAlternativo)
    $('#totalEfectivo').val(self.factura.totalComprobante)
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    $('#totalEfectivo').focus()
    $('#totalEfectivo').select()
    __ComboTipoDocumentos(0)
    __aplicarExoneracionPorCliente()
    __calculate()
}
/** 
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('YYYY-MM-DD ');
}
/**
*  Crear Factura nueva
**/
function crearFactura(estado, separarFactura){
    BuscarActividadComercial()
    if( self.factura.codigoActividad.length == 0 ){
      mensajeAdvertencia($.i18n.prop("error.factura.actividad.comercial.no.existe"))
      return
    }
    self.enviarCocina = false
    if(estado == 1){
        self.enviarCocina = true
    }
    self.detalleFactura.data = self.detail    
    self.update() 
    if(estado ==1){
        //factura.js
        if(!verificarSiClienteFrecuente(self.cliente)){
           self.factura.tipoDoc = "01";
        }else{
            self.factura.tipoDoc = "04";
        }
    }
    var fechaCreditoTemporal =condicionVenta.value == "02"?fechaCredito.value:new Date() 
    var fechaReferencia =$('#referenciaFechaEmision').val() !=null?referenciaFechaEmision.value:new Date() 
    var JSONDetalles = JSON.stringify( self.detalleFactura );
    self.factura.id = self.factura.id
    self.factura.condicionVenta = $('#condicionVenta').val()
    self.factura.fechaCredito =fechaCreditoTemporal.toString()
    self.factura.referenciaFechaEmision =fechaReferencia
    self.factura.totalEfectivo =__valorNumerico($('#totalEfectivo').val())
    self.factura.totalTarjeta = __valorNumerico($('#totalTarjeta').val()) 
    self.factura.totalBanco = __valorNumerico($('#totalBanco').val())
    self.factura.plazoCredito = __valorNumerico($('#plazoCreditoL').val())
    self.factura.detalleFactura =JSONDetalles
    self.factura.estado = estado 
    self.update();
    var formulario = $("#formularioFactura").serialize();
    $.ajax({
        type : "POST",
        dataType : "json",
        data : formulario,
        async : false,
        url : "CrearFacturaAjax",
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                     mensajeAdvertencia(data.message)
                }
            } else {
               	serverMessageJsonClase(data);
                evaluarFactura(data, separarFactura)
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
    if(self.empresa.abrirSinComanda == 1){
      //Abrir cajon sin comanda
      abrirCajonDineroSinComanda()
    }  
}
/**
*Si fue facturada o tiquete
**/
function evaluarFactura(data, separarFactura){
   if (data.message != null && data.message.length > 0) {
        $.each(data.listaObjetos, function( index, modeloTabla ) {
            self.facturaImprimir   = modeloTabla
            self.facturaImpresa    =modeloTabla
           	self.factura.id = self.facturaImprimir !=null?self.facturaImprimir.id:null;
            self.update()
            if(self.facturaImprimir.estado == 2){
            	if(!separarFactura){
                    __Init()                	
                }
                //Envia a la pantalla de impresion
                var parametros = {
                          factura:modeloTabla,
                          facturaDia:0
                      }
                riot.mount('ptv-imprimir',{parametros:parametros});
            }else{
                mensajeToasExito(mostrarMensajeCreacionConsecutivoRestaurante(self.facturaImprimir))
                if(self.enviarCocina == true){
                //Se envian los datos a la comanda
                __EnviarCocina();
                }
                if(!separarFactura){
                    __Init()                	
                }
            }
        });
    }
}
/**
*  Obtiene el valor de lo digitado en el campo de Descuento
**/
__TotalDeDescuento(e){
    self.factura.porcentajeDesc = __valorNumerico(e.target.value) 
    self.update()
    __calculate()
}
/**
*   Retrocer a los ingresos de los codigos desde el formulario de ingresar el valor dinero a pagar
**/
_AtrasFacturaFinal(){
    $('.totalEfectivo').val(null)
    $('.totalTarjeta').val(null)
    $('.totalBanco').val(null)
    self.primeraVezBilleteClick = false
    self.factura.totalEfectivo =0
    self.factura.totalTarjeta =0
    self.factura.totalBanco =0
    self.totalCambioPagar =0
    self.totalCambioPagarSTR =0
   self.mostrarFormularioPago = false
   self.mostarParaCrearNuevaVentas = true;
   self.mostrarSepararCuenta	= false //true
   self.mostrarCambiarMesa = false;

   self.mostarParaCrearNuevaFactura = true
   self.error = false
   self.update()
}
/**
*    Muesta el campo de la fecha de credito
**/
__formaPago(e){
    //Contado /sin cobro
    if(e.currentTarget.value == 1 || e.currentTarget.value == 3){
        self.mostrarCamposIngresoContado = true
    }
    //Credito
    if(e.currentTarget.value == 2){
        self.mostrarCamposIngresoContado = false
    }
    self.update()
    $('.fechaCredito').val(null)
     $('.datepickerFechaCredito').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-0d',
              todayHighlight:true,
            }
    );  
    $('.plazoCreditoL').val(0)      
}
/**
*   funcion para grabar la Factura en el back end
**/
__MostrarFormularioDePago(){
    mostrarPAgo()
}

/**
* Mostrar el pago
**/
function mostrarPAgo(){
     //No hay detalles registrados en la Factura
    if(self.detail.length == 0 ){
        mensajeAdvertencia("Verificar,No hay detalles en la factura ")
        return
    }
    self.mostarParaCrearNuevaVentas = false
    self.mostrarSepararCuenta		    = false //true
    self.mostrarCambiarMesa = false;
    self.factura.totalEfectivo = self.factura.totalComprobante
    self.update()
    $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(3))
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
   // getSubTotalGeneral()
    self.primeraVezBilleteClick = false    
    self.primeraVezBilleteClick = false
    self.mostarParaCrearNuevaVentas = false
    self.factura.totalCambioPagar =0
    self.totalCambioPagarSTR =0
    self.mostarParaCrearNuevaFactura = false
    self.mostrarFormularioPago = true
    self.totalCambioPagar =0
    self.update()
    $('#totalEfectivo').focus()
    $('#totalEfectivo').select()
    self.factura.cambioMoneda = self.factura.totalVentaNeta / self.tipoCambio.total
    self.update()
}
/** 
*Agregar codigos al detalle de la Factura
*/
__addProductToDetail(e){
    if (e.keyCode != 13) {
        return;
    } 
    var codigo = e.currentTarget.value
    var codigoActual = ""
    var cantidadAct =""
    var existe = false
    var existeMas = ""
    for(i=0; i<codigo.length; i++){
        existeMas = codigo.charAt(i) == "+"?true : false
       if(existe == false){
          existe = codigo.charAt(i) == "*"?true : false  
          if(codigo.charAt(i) !="*"){
              codigoActual = codigoActual + codigo.charAt(i)  
          }
       }else{
           cantidadAct = cantidadAct + codigo.charAt(i)
       }
    }
    // esto es para cuando un cliente quiere sumar varios productos
    if(existeMas == true){
       __sumarMasArticulo(codigo)
       return  
    }
    __buscarcodigo(codigoActual,__valorNumerico(cantidadAct));
    $('#codigoBarra').val(null)
    $('#codigoBarra').focus()
}
/**
*sumar mas cantidad al ultimor articulo ingresado
**/
function __sumarMasArticulo(codigo){
    if(self.articulo == null){
        return;
    }
    var cantidadAct =""
    var existe = false
    for(i=0; i<codigo.length; i++){
       existe = codigo.charAt(i) == "+"?true : false
       if(existe == false){
          cantidadAct = cantidadAct + codigo.charAt(i)
       }
    }
   for (var count = 0; count < self.detail.length; count++) {
        if (self.detail[count].codigo == self.articulo.codigo ){
            self.item          = self.detail[count];
            self.item.cantidad = self.item.cantidad + __valorNumerico(cantidadAct)
            self.update();
               //factura.js
            self.item = ActualizarLineaDEtalle(self.item) 
            self.update()

            self.detail[count] = self.item;
            self.update();
        }
    }
    __calculate(); 
}
/**
* Buscar codigo
**/
__agregarArticuloBotonAgregar(){
   __buscarcodigo($( "#codigoBarra" ).val(),1);
}

/**
*  Muestra la lista de clientes
**/
_EscogerClientes(){
    __ListaDeClientes()
}
/**
*  Muestra la lista de vendedores
**/
_EscogerVendedores(){
    $('#modalVendedor').modal('show')  
}
/**
*  Lista de los vendedores
**/
function __ListaDeVendedores(){
    $.ajax({
        url: 'ListarVendedoresActivosAjax.do',
        datatype: "json",
        global: false,
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                __informacionData_vendedores()
                loadListar(".tableListaVendedor",idioma_espanol,self.informacion_tabla_vendedores,result.aaData)
                agregarInputsCombos_Vendedores()
                ActivarEventoFiltro(".tableListaVendedor")
                __seleccionarVendedor()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
/**
*  Lista de los clientes
**/
function __ListaDeClientes(){
    $.ajax({
        url: 'ListarClientesActivosAjax.do',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                __informacionData()
                loadListar(".tableListaCliente",idioma_espanol,self.informacion_tabla_clientes,result.aaData)
                agregarInputsCombos_Clientes()
                ActivarEventoFiltro(".tableListaCliente")
                __seleccionarClientes()
                $('#modalClientes').modal('show')   
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
/**
* Buscar el codigo del codigo  en la base de datos
**/
function __buscarcodigo(idArticulo,cantidad){
    $.ajax({
        type: 'GET',
        url: 'findArticuloByCodigojax.do',
        method:"GET",
        data:{codigoArticulo:idArticulo},
        success: function(data){
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    swal('',data.message,'error');
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.articulo  = modeloTabla
                         if(modeloTabla.estado  == "Inactivo"){
                            mensajeAdvertencia($.i18n.prop("error.articulo.inactivo.inventario"))
                            return
                        }
                        self.descripcionArticulo = modeloTabla.descripcion
                        self.update()
                        __agregarArticulo(cantidad)
                    });
                }
            }
        },
	    error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
/**
*  Agregar un articulo si existe se suma la cantidad y no existe se agrega en el detalle
**/
function __agregarArticulo(cantidad){
    if(self.articulo == null){
        return;
    }
    if(cantidad == null){
        cantidad = 1
    }
    if(cantidad == 0){
        cantidad = 1
    }
    var encontrado = false;
    if(self.detail[0] == null){ // first element
        __nuevoArticuloAlDetalle(cantidad);
    	//Se almacena en los casos de articulos de comanda
    	if(self.articulo.comanda == 1 || self.articulo.comanda == 2 ){	
            __nuevoArticuloComanda(cantidad, self.articulo.codigo, self.articulo.descripcion,self.articulo.comanda);
    	}		

        self.seIncluyoUnArticulo = 0
        self.update()
        encontrado = true;
    }else{//Se busca el articulo si existe se incrementa la cantidad
        for (var count = 0; count < self.detail.length; count++) {
            if (self.detail[count].codigo == self.articulo.codigo ){
               self.item          = self.detail[count];
               self.item.cantidad = self.item.cantidad + __valorNumerico(cantidad)
               self.update();
	  	      	if(self.articulo.comanda == 1 || self.articulo.comanda == 2){	
	  	            __nuevoArticuloComanda(cantidad, self.articulo.codigo, self.articulo.descripcion,self.articulo.comanda);
	  	    	}	
                  //factura.js
                self.item = ActualizarLineaDEtalle(self.item) 
                self.update()
  	            self.detail[count] = self.item;
               encontrado = true;
               self.seIncluyoUnArticulo = 0
               self.update()
            }
        }
    }
    // si no existe se agrega como un codigo nuevo
    if(encontrado == false){ // add elemen
       self.seIncluyoUnArticulo = 0
       self.update()

      __nuevoArticuloAlDetalle(cantidad);
	  	if(self.articulo.comanda == 1 || self.articulo.comanda == 2){	
	        __nuevoArticuloComanda(cantidad, self.articulo.codigo, self.articulo.descripcion,self.articulo.comanda);
		}		
    }
    __calculate(); 
}


/**
* eliminar un detalle factura
**/
__removeProductFromDetail(e) {
    self.autorizarBorrado = 1
    self.itemEliminar = e.item;
    self.update()
    if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        self.rutaAutorizada = '';
        self.update()
        $("#usuarioSistema").val("")
        $("#claveSistema").val("")
        $('#modalRolUsuario').modal({backdrop: 'static', keyboard: true}) 
        $('#modalRolUsuario').modal('show')     
    }else{
        eliminarDetalle()
    }
 }
 /**
*    Eliminar detalle
**/
function  eliminarDetalle(){
    index = self.detail.indexOf(self.itemEliminar);
    self.detail.splice(index, 1);
    self.cantArticulos = self.cantArticulos > 0?self.cantArticulos - 1:0
    var num = 0
    for (var count = 0; count < self.detail.length; count++) {
         num = num + 1 
    }
    if(num > 0){
        var cont  = 0
       self.detail.forEach(function(elemen){
            elemen.numeroLinea = num 
            num = num > 0?num -1:1
            cont =  cont + 1
        })  
        self.numeroLinea =  cont
    }else{
      self.numeroLinea =  0  
    }
     self.seIncluyoUnArticulo = 1
    self.update()
     __calculate();
      eliminaArticuloComanda(self.itemEliminar.codigo);

 }
/**
*   agregar Articulos nuevos en el detalle de la factura
**/
function __nuevoArticuloAlDetalle(cantidad){
    if(self.articulo.descripcion == null){
        return;
    }
    if(self.articulo.descripcion == ""){
        return;
    }
    if(self.detail == null){
        __storege()
    }
      //Determinar el precio a incluir
    var resultadoPrecio = getListaPrecio(self.articulo)
      
    var resultaMontoImpuesto = __valorNumerico(self.articulo.impuesto)
    var precioUnitario  = getPrecioUnitario(resultadoPrecio,resultaMontoImpuesto)
    resultaMontoImpuesto = 0 
    precioUnitario      = getPrecioUnitario(precioUnitario,resultaMontoImpuesto)
    var montoTotal      = getMontoTotal(precioUnitario,cantidad)
    var montoDescuento  = 0
    var naturalezaDescuento = ""
    var subTotal        = montoTotal
    var montoImpuesto1  = 0
    var montoImpuesto   = _calcularImpuesto(subTotal+montoImpuesto1,__valorNumerico(self.articulo.impuesto) ==null?0:__valorNumerico(self.articulo.impuesto))
    var montoTotalLinea = subTotal + montoImpuesto + montoImpuesto1  
    self.pesoPrioridad  =  self.pesoPrioridad + 1
    self.numeroLinea    = self.numeroLinea + 1
    self.cantArticulos  = self.cantArticulos + 1
    var costoTotal      = __valorNumerico(self.articulo.costo) > precioUnitario ?0:__valorNumerico(self.articulo.costo); 
    var ganancia        = __ObtenerGananciaProductoNuevoIngresado(0,precioUnitario,self.articulo.costo ==null?0:__valorNumerico(self.articulo.costo),cantidad)
    self.detail.push({
       numeroLinea     : __valorNumerico(self.numeroLinea),
       pesoPrioridad   : self.pesoPrioridad,  
       tipoImpuesto    : self.articulo.tipoImpuesto ==null?"":self.articulo.tipoImpuesto,
       tipoImpuesto1   : "",
       iva             : __valorNumerico(self.articulo.impuesto),
       iva1            : 0,
       codigo          : self.articulo.codigo,
       descripcion     : self.articulo.descripcion,
       cantidad        : __valorNumerico(cantidad),
       precioUnitario  : __valorNumerico(precioUnitario),
       impuesto        : __valorNumerico(self.articulo.impuesto),
       impuesto1        : 0,
       montoImpuesto   : __valorNumerico(montoImpuesto),
       montoImpuesto1  : __valorNumerico(montoImpuesto1),
       montoDescuento  : 0,
       porcentajeDesc  : 0,
       ganancia        : __valorNumerico(ganancia),
       montoGanancia   : __valorNumerico(ganancia),
       subTotal        : __valorNumerico(subTotal),
       montoTotalLinea : __valorNumerico(montoTotalLinea),
       montoTotal      : __valorNumerico(montoTotal),
       costo           : costoTotal,
       porcentajeGanancia :   getListaPrecioGanancia(self.articulo) ==null?0:__valorNumerico(getListaPrecioGanancia(self.articulo)),
       pesoTransporte :  __valorNumerico(self.articulo.pesoTransporte),
       pesoTransporteTotal :__valorNumerico(self.articulo.pesoTransporte),
        montoExoneracion:0,
       porcentajeExoneracion:0,
       fechaEmisionExoneracion:null,
       nombreInstitucionExoneracion:"",
       numeroDocumentoExoneracion:"",
       tipoDocumentoExoneracion:""
    });
    self.detail.sort(function(a,b) {
    if ( a.pesoPrioridad > b.pesoPrioridad )
        return -1;
    if ( a.pesoPrioridad < b.pesoPrioridad )
        return 1;
    return 0;
    } );
    self.cantidadEnterFacturar = 0
    self.totalGananciaByProducto = formatoDecimales(__valorNumerico(ganancia),2)
    self.update()
}

function getListaPrecio(articulo){
    //Precio Publico
    var resultado=  __valorNumerico(articulo.precioPublico )
    return resultado > 0 ?resultado:__valorNumerico(articulo.precioPublico )

}

function getListaPrecioGanancia(articulo){
    //Precio Publico
     var resultado=  __valorNumerico(articulo.gananciaPrecioPublico )
    return resultado > 0 ?resultado:__valorNumerico(articulo.gananciaPrecioEspecial )

}
function __storege(){
    self.detail = []
    self.factura                = {
        id:null,
	   fechaCredito:null,
	   fechaEmision:null,
	   condicionVenta:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalComprobante:0,
	    totalEfectivo:0,
        totalTarjeta:0,
        totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
	    codigoMoneda:"",
	    estado:1,
	    cliente:{
            id:null,
            nombreCompleto:""
        },
	    vendedor:{
            id:null,
            nombreCompleto:""
        }

    }   
    self.update()
}
/**
* Monto Total de la Facturra 
**/
function getMontoTotal(precioUnitario,cantidad){
    var resultado = __valorNumerico(precioUnitario) * __valorNumerico(cantidad)
    return resultado;
}
/**
* Obtiene el precio unitario sin descuento sin impuesto
**/
function getPrecioUnitario(precio ,impuesto){
   var porcentajeImpuesto = 0
   var resultado  = 0
   if(impuesto > 0){
      porcentajeImpuesto = impuesto / 100
      porcentajeImpuesto =  porcentajeImpuesto + 1
      resultado  =  precio  / porcentajeImpuesto
   }else{
       resultado  =  precio
   }
   return resultado    
}
/**
 * calculo del impuesto iva
 * */
function _calcularImpuesto(precio,iva){
    if(iva == 0){
        return 0;
    }
    var impuesto = iva > 0 ?__valorNumerico(iva)/100:0
    impuesto = impuesto > 0 ?impuesto+1:0
    var total = precio * impuesto
    var total = total - precio 
    return total
}
 /**
 * Cuando se aplica un cambio de cantidad en un detalle
 * Se aplica una recalculacion de todo el detalle y Factura
 **/ 
 __recalculacionDelDetalle(e){
     cambiarCantidadModal()
  }

  function cambiarCantidadModal(){
    var cantidad = $(".cambiarCantidadArticulo").val();
    //Cantidad del detalle se verifica si es null o espacio por defecto se deja en 1
    cantidad =__valorNumerico(cantidad);
    if(cantidad == 0){
       cantidad = 1;
    }
    cantidad = __valorNumerico(redondeoDecimales(cantidad,3))
    //__actualizaArticuloComanda(cantidad, self.item.codigo, self.item.descripcion, self.item.cantidad);
    __ValidarCantidadArticulo(self.item.codigo,cantidad)		
   // $('#modalCambiarCantidad').modal('hide')


  }
/**
* Cambiar el precio del detalle de la factura
**/
__cambiarDescripcionDetalle(e){
    __cambiarDescripcionDetalleModal()
}

function __cambiarDescripcionDetalleModal(){
    var descripcion = $(".cambiarDescripcionArticulo").val();
    self.item.descripcion = descripcion
    self.update()
    $(".cambiarDescripcionArticulo").val(null);
    $('#modalCambiarDescripcion').modal('hide') 

}
/**
* Cambiar el precio del detalle de la factura
**/
__cambiarElPrecio(e){
    var precio = $(".cambiarprecioArticulo").val();
    agregarPrecioAlDetalle(precio)
}
/**
* Cambiar el precio en el detalle
**/
function agregarPrecioAlDetalle(precio){
    self.item.precioUnitario = precio
    self.update()
       //factura.js
    self.item = ActualizarLineaDEtalle(self.item) 
    self.update()

    aplicarCambioLineaDetalle() 
    $(".cambiarprecioArticulo").val(null);
    $('#modalCambiarPrecio').modal('hide') 
}
/**
* Buscar el codigo del codigo  en la base de datos
**/
function __ValidarCantidadArticulo(idArticulo,cantidad){
    agregarCantidadAlaVenta(cantidad)
    return
}


/**
* Agregar en la cantidad la Venta
**/
function agregarCantidadAlaVenta(cantidad){
     self.item.cantidad = cantidad
    var ganancia        = __ObtenerGananciaProductoNuevoIngresado(0,self.item.precioUnitario,self.item.costo ==null?0:parseFloat(self.item.costo),cantidad)
    self.item.ganancia = ganancia
    self.item.montoGanancia    = self.item.ganancia
    self.update()
    //factura.js
    self.item = ActualizarLineaDEtalle(self.item) 
    self.update()
    aplicarCambioLineaDetalle() 
    $('#modalCambiarCantidad').modal('hide') 
     
}
/**
* Aplicar cambios linea Detalle
**/
function aplicarCambioLineaDetalle(){
    var index    = self.detail.indexOf(self.item);
    self.detail[index] = self.item;
    self.update()
    __calculate()
}

/**
* Actualizar el descuento del codigo
**/
__actualizarDescuento(){
        _actualizarDesc()
}
function aplicarModalDescuento(){
  _actualizarDesc()
}
/**
*Actualizar el descuento
**/
function _actualizarDesc(){
    var index     = self.detail.indexOf(self.item);
    var descuento = $(".aplicarDescuento").val();
    //Descuento se verifica si es null o espacios por defecto se deja en cero
     descuento =__valorNumerico(descuento);
     if(descuento > 100){
          mensajeAdvertencia("Error el descuento no puede ser mayor al 100% ")
         return false
    }
      //Descuento
    if(self.item.porcentajeDesc != descuento){
       self.item.porcentajeDesc =  parseFloat(descuento);  
    }    
    self.update()
       //factura.js
    self.item = ActualizarLineaDEtalle(self.item) 
    self.update()

    aplicarCambioLineaDetalle()
    $('#modalCambiarDescuento').modal('hide') 
    aplicarDescuento.value = 0
}
/**
* Monto a pagar en la linea el cliente
**/
function getMontoTotalLinea(subTotal,totalImpuesto){
  return subTotal == 0?0:subTotal + totalImpuesto
}
/**
*  Obtener el subtotal sin el impuesto
**/
function getSubTotal(precio,cantidad){
    var valor = __valorNumerico(precio) * __valorNumerico(cantidad)
    return valor
}
/**
* calcular el descuento
**/
function getTotalDescuento(precio,cantidad,porcentajeDesc){
    var porcentaje = __valorNumerico(porcentajeDesc)/100
    var valor =  __valorNumerico(precio) * porcentaje
    return valor * cantidad
}
/**
* calculacion de los detalle de la factura 
**/
function __calculate() {
    
    
    self.factura.total            = 0;
    self.factura.totalDescuentos  = 0;
    self.factura.totalImpuesto    = 0;
    self.factura.totalImpuestoServ = 0; 
    self.factura.subTotal          = 0;
    self.update()
             //Factura.js
    var resultado = __ResumenFactura(self.detail,self.factura);
    self.factura = resultado.factura
    self.cantArticulos = resultado.cantArticulos
    self.totalGananciaByProducto = formatoDecimales(parseFloat(resultado.totalGananciaByProducto),2)
    self.totalPesoByFactura = __valorNumerico(resultado.totalPesoByFactura)
    self.totalPesoByFacturaSTR = formatoDecimales(resultado.totalPesoByFactura,2);
    self.totalComprobante = formatoDecimales(self.factura.totalComprobante,2);
    self.totalDescuentos = formatoDecimales(self.factura.totalDescuentos,2);
    self.totalImpuesto = formatoDecimales(self.factura.totalImpuesto,2);
    self.montoExoneracion = resultado.montoExoneracion > 0 ?formatoDecimales(resultado.montoExoneracion,2):"";
    self.subTotalGeneral = formatoDecimales(resultado.subTotalGeneral,2)
    self.totalDescuentos = formatoDecimales(self.factura.totalDescuentos,2)
    var resultadoTotalImpuesto = __valorNumerico(self.factura.totalImpuesto) 
    self.totalImpuesto = formatoDecimales(resultado.totalImpuesto,2)
    self.totalImpuestoServ = formatoDecimales(self.factura.totalImpuestoServ,2);
    self.ImpuestoServicio = self.factura.totalImpuestoServ;
    self.montoExoneracion = formatoDecimales(resultado.montoExoneracion,2);
    self.update(); 
    $( "#codigoBarra" ).val(null);
    $( "#quantity" ).val(null);
    localStorage.setItem('DetallesNueva', JSON.stringify(self.detail));
    localStorage.setItem('facturaNueva', JSON.stringify(self.factura));
    localStorage.setItem('cliente', JSON.stringify(self.factura.cliente));
}	

/**
* Definicion de la tabla articulos 
**/
function _informacionData_Articulo(){
   self.informacion_tabla_articulo = [	{'data' : 'codigo'         ,"name":"codigo"          ,"title" : $.i18n.prop("articulo.codigo")       ,"autoWidth":false},
                                        {'data' : 'descripcion'    ,"name":"descripcion"     ,"title" : $.i18n.prop("articulo.descripcion")  ,"autoWidth":false},
                                        {'data' : 'cantidad'       ,"name":"cantidad"        ,"title" : $.i18n.prop("inventario.cantidad")   ,"autoWidth":false},
                                        {'data' : 'precioPublico'  ,"name":"precioPublico"   ,"title" : $.i18n.prop("articulo.precioPublico"),"autoWidth":false,
                                          "render":function(precioPublico,type, row){
                                               return  "₡" + formatoDecimales(precioPublico);
                                            }
                                        },
                                        {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
                                            "render":function(id,type, row){
                                                    return __OpcionesArticulos(id,type,row);
                                                }	 
                                        },
                              ];
    
 self.update()        
}
/**
* Opciones del modal de articulos
*/
function __OpcionesArticulos(){
  var agregar  = '<a href="#"  class="btn btnAgregar btn-success form-control" title="Seleccionar" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;

}
/**
* Agregar codigos a la Factura desde modal de articulos
**/
function __agregarArticulos() {
     $('#tableListarArticulos').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListarArticulos').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	     }
        self.articulo = data;
        self.update();  
        if(self.articulo.contable == "si"){
           __buscarcodigo(self.articulo.codigo,1)
        }else{
            __agregarArticulo(1)
        }
	    
    });
}
/**
* formato de la tabla de clientes
**/
function __informacionData_vendedores(){
    self.informacion_tabla_vendedores = [	
                                        {'data' : 'cedula'           ,"name":"cedula"            ,"title" : $.i18n.prop("vendedor.cedula")            ,"autoWidth":false},
                                        {'data' : 'nombreCompleto'   ,"name":"nombreCompleto"    ,"title" : $.i18n.prop("vendedor.nombreCompleto")    ,"autoWidth":false},
                                        {'data' : 'correoElectronico',"name":"correoElectronico" ,"title" : $.i18n.prop("vendedor.correoElectronico") ,"autoWidth":false},
                                        {'data' : 'telefono'         ,"name":"telefono"          ,"title" : $.i18n.prop("vendedor.telefono")          ,"autoWidth":false},                                
                                        {'data' : 'celular'          ,"name":"celular"           ,"title" : $.i18n.prop("vendedor.celular")           ,"autoWidth":false},                                
                                        {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									            "render":function(id,type, row){
										            return __OpcionesVendedores(id,type,row);
	 							                }	 
								            },
                                        ];                              
   
}
/**
* Opciones del modal de clientes
*/
function __OpcionesVendedores(){
  var agregar  = '<a href="#"  title="Seleccionar Vendedor" class="btn btnVendedor btn-success form-control" title="Seleccione el vendedor" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;
}
/**
* Seleccionar el vendedor de la factura
**/
function __seleccionarVendedor() {
     $('#tableListaVendedor').on('click', '.btnVendedor', function (e) {
         var table = $('#tableListaVendedor').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
         }
        self.vendedor = data
        self.update();
    });
}
/**
* formato de la tabla de clientes
**/
function __informacionData(){
    self.informacion_tabla_clientes = [	
                                      {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									            "render":function(id,type, row){
										            return __Opcionesclientes(id,type,row);
	 							                }	 
								            },
                                        {'data' : 'cedula'           ,"name":"cedula"            ,"title" : $.i18n.prop("cliente.cedula")            ,"autoWidth":false,
        									"render":function(cedula,type, row){
        										return stringVacio(cedula)?cedula:row.identificacionExtranjero;
        									}
                                        
                                        },
                                        
                                        {'data' : 'nombreCompleto'   ,"name":"nombreCompleto"    ,"title" : $.i18n.prop("cliente.nombreCompleto")    ,"autoWidth":false},
                                        {'data' : 'nombreComercial'   ,"name":"nombreComercial"    ,"title" : $.i18n.prop("cliente.nombreComercial")    ,"autoWidth":false},
                                        {'data' : 'correoElectronico',"name":"correoElectronico" ,"title" : $.i18n.prop("cliente.correoElectronico") ,"autoWidth":false},
                                        {'data' : 'telefono'         ,"name":"telefono"          ,"title" : $.i18n.prop("cliente.telefono")          ,"autoWidth":false},                                
                                        {'data' : 'celular'          ,"name":"celular"           ,"title" : $.i18n.prop("cliente.celular")           ,"autoWidth":false},                                
                                       
                                        ];                              
   
}
/**
* Opciones del modal de clientes
*/
function __Opcionesclientes(){
  var agregar  = '<a href="#"  title="Seleccionar Cliente" class="btn btnAgregar btn-success form-control" title="Seleccione el cliente de la factura" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;
}
/**
* Agregar codigos a la factura desde modal de articulos
**/
function __seleccionarClientes() {
     $('#tableListaCliente').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListaCliente').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	     }
        self.cliente = data
        self.update();
       //  if(self.cliente.cedula != data.cedula){
            self.cliente = data
            self.update();
         //   __aplicarExoneracionPorCliente()

        //}
        $('#modalClientes').modal('hide') 
        if(!verificarSiClienteFrecuente(self.cliente)){
            self.factura.tipoDoc ='01'
            __aplicarExoneracionPorCliente()
            if(stringVacio(self.cliente.identificacionExtranjero)== false){
               self.factura.tipoDoc ='01'
               self.update()
                if(self.item != null){
                if(self.item.tipoDocumentoExoneracion !=null){
                    if(self.item.tipoDocumentoExoneracion =='02'){
                        self.factura.tipoDoc ='04'  
                        self.update()
                        }
                }
               }

               
            }else{
               self.factura.tipoDoc ='04'
               self.update()
            }
           __ComboTipoDocumentos(1)
        }else{
            self.factura.tipoDoc = "04";
            __ComboTipoDocumentos(0)
        }
        self.update()
       $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
       $('#totalTarjeta').val(null)
       $('#totalBanco').val(null)
       $('#totalEfectivo').focus()
       $('#totalEfectivo').select()
    });
}
/**
* Aplicar la exoneracion de detalles
**/
function __aplicarExoneracionPorCliente(){
    var aplicaExo = false
    var porcentaje = __valorNumerico(self.cliente.porcentajeExoneracion )
    if(porcentaje == 0){
        return
    }
    var valorTotal = 0
    for (var count = 0; count < self.detail.length; count++) {
        self.item          = self.detail[count];
        self.cliente.porcentajeExoneracion = __valorNumerico(self.cliente.porcentajeExoneracion)
            if(self.item.montoImpuesto > 0 || self.item.montoImpuesto1 > 0 ){
                if(self.cliente.porcentajeExoneracion > 0  ){
                    self.item.porcentajeExoneracion = getPorcentajeExoneracion(__valorNumerico(self.cliente.porcentajeExoneracion),self.item.impuesto )
                    self.item.fechaEmisionExoneracion = self.cliente.fechaEmisionExoneracion
                    self.item.nombreInstitucionExoneracion = self.cliente.nombreInstitucionExoneracion
                    self.item.numeroDocumentoExoneracion = self.cliente.numeroDocumentoExoneracion
                    self.item.tipoDocumentoExoneracion = self.cliente.tipoDocumentoExoneracion
                    self.item.montoExoneracion = getMontoExoneracionSubTotal(self.item.tipoDocumentoExoneracion,self.item.impuesto, self.item.porcentajeExoneracion, self.item.subTotal, self.item.montoImpuesto)   
                    self.item.ImpuestoNeto = self.item.montoImpuesto - self.item.montoExoneracion
                    self.item.montoTotalLinea = self.item.subTotal +  self.item.ImpuestoNeto
                    self.detail[count] = self.item;
                    self.update();
                    aplicaExo= true
                }else{

                    self.item.porcentajeExoneracion = 0
                    self.item.fechaEmisionExoneracion = null
                    self.item.nombreInstitucionExoneracion = ""
                    self.item.numeroDocumentoExoneracion = ""
                    self.item.tipoDocumentoExoneracion = ""
                    self.item.montoExoneracion = 0
                    self.item.montoExoneracion1 = 0
                    self.item.ImpuestoNeto = __valorNumerico(self.item.montoImpuesto) 
                    self.item.montoTotalLinea = self.item.subTotal +  self.item.ImpuestoNeto
                    self.detail[count] = self.item;
                    self.totalCambioPagar = 0
                    self.totalCambioPagarSTR = 0
                    self.factura.totalEfectivo =0
                    self.factura.totalTarjeta =0
                    self.factura.totalBanco =0
                    self.factura.totalCambioPagar = self.factura.totalComprobante
                    self.update();

                    aplicaExo = true
                }

            }else{
                self.item.porcentajeExoneracion = 0
                self.item.fechaEmisionExoneracion = null
                self.item.nombreInstitucionExoneracion = ""
                self.item.numeroDocumentoExoneracion = ""
                self.item.tipoDocumentoExoneracion = ""
                self.item.montoExoneracion = 0
                self.item.montoExoneracion1 = 0

            }
    }
    
    __calculate()
    if(aplicaExo == true){
       self.factura.totalCambioPagar = self.factura.totalComprobante
       self.factura.totalEfectivo =0
       self.factura.totalTarjeta =0
       self.factura.totalBanco =0
       self.totalCambioPagar = 0
       self.totalCambioPagarSTR = 0
       self.update();
    }
}

/**
* cargar los estados de la factura
**/
function __comboCondicionPago(){
    self.comboCondicionPagos = []
    self.update()
    self.comboCondicionPagos.push({
        estado:"01",
        descripcion:$.i18n.prop("factura.codicion.venta.contado")
    })
    self.comboCondicionPagos.push({
        estado:"02",
        descripcion:$.i18n.prop("factura.codicion.venta.credito")
    })
    self.update()
}
/**
* cargar los tipos de Documento de la factura
**/
function __ComboTipoDocumentos(valor){
    self.comboTipoDocumentos = []
    self.update()
       //Prioridad de orden
    if(self.empresa.prioridadFacturar == 1 ){
        self.comboTipoDocumentos.push({
            estado:"01",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
        })
        self.comboTipoDocumentos.push({
            estado:"04",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
        })
 
    }else{
    self.comboTipoDocumentos.push({
        estado:"04",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
    })
    self.comboTipoDocumentos.push({
         estado:"01",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
    })
 
    }
      self.update()
}
function __ComboTipoDocumentosSinClienteFrecuente(){
    self.comboTipoDocumentos = []
    self.update()
    self.comboTipoDocumentos.push({
        estado:"01",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
    })
    self.comboTipoDocumentos.push({
        estado:"04",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
    })
 
    self.update()
}

/**
* cargar los estados de la factura
**/
function __ComboEstados(){
    self.comboEstados = []
    self.comboEstados.push({
        estado:2,
        descripcion:$.i18n.prop("factura.estado.facturado")
    })
    self.comboEstados.push({
        estado:1,
        descripcion:$.i18n.prop("factura.estado.pendiente")
    })
    self.update()
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Articulo(){
     // Agregar los input de busqueda 
     var cont = 1
    $('.tableListarArticulos tfoot th').each( function (e) {
        cont = cont +1 ;
        var title = $('.tableListarArticulos thead th').eq($(this).index()).text();      
        if ( $(this).index() != 5    ){
	      	$(this).html( '<input id = "filtroCampos'+cont+'" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Clientes(){
     // Agregar los input de busqueda 
    var cont = 15
    $('.tableListaCliente tfoot th').each( function (e) {
        var title = $('.tableListaCliente thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        cont = cont +1 ;
        if ( $(this).index() != 0    ){
	      	$(this).html( '<input id = "filtroCampos'+cont+'" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Vendedores(){
     // Agregar los input de busqueda 
     var cont= 30;
    $('.tableListaVendedor tfoot th').each( function (e) {
        var title = $('.tableListaVendedor thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        cont=cont+1
        if ( $(this).index() != 5    ){
	      	$(this).html( '<input id = "filtroCampos'+cont+'" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
/**
*  teclas de la pantalla
**/      
function __Teclas(tecla,event){
     if($('#modalCambiarCantidad').is(':visible')){
        return 
     }   
    if(tecla ==119){
      if(self.mostrarFormularioPago == false && self.mostarParaCrearNuevaVentas == true){
        self.factura.totalCambioPagar =__valorNumerico(self.factura.totalComprobante)   
        self.totalCambioPagar = redondeoDecimales(self.factura.totalComprobante,2)
        self.totalCambioPagarSTR =formatoDecimales(self.totalCambioPagar,2)
         self.primeraVezBilleteClick == false
        self.update()
        $(".totalEfectivo").val(self.totalCambioPagar)

         mostrarPAgo()     
      }else if (self.mostrarFormularioPago == true && self.mostarParaCrearNuevaVentas == false ){
           self.primeraVezBilleteClick == false
           self.update()
            aplicarFactura(2)   
        }  
    }  
    //Factura en espera
    if(tecla ==120){
      aplicarFactura(1)   
    }
    //F7
    if(tecla ==118){
      imprimirTiquete()   
    }
    //Limpiar
    if(tecla ==113){
      __SeguridadLimpiar()
    }
     //Insert = abrir Cajon
    if(tecla ==45){
       __OpcionAbrirCajon()
    }
    if(tecla ==16){
       moverPagoTarjeta()
      return 
    }
     
   
}

__MoverMontoTarjeta(){
    if($('#modalAgregarClienteNuevo').is(':visible')){
           return
    }
     var resultado = __valorNumerico($(".totalTarjeta").val())
    if(resultado > 0){
        $('.totalTarjeta').select()
        $('.totalTarjeta').focus()
        return

    }
    var resultado = __valorNumerico($(".totalEfectivo").val())
    if(resultado > 0){
        self.factura.totalTarjeta = resultado
        self.factura.totalEfectivo = 0
        self.factura.totalBanco = 0
        self.update()  
        $(".totalEfectivo").val(null)
        $(".totalTarjeta").val(self.factura.totalTarjeta) 
        $('.totalTarjeta').select()
        $('.totalTarjeta').focus()
        return
    } 
    self.factura.totalEfectivo = __valorNumerico(self.factura.totalComprobante)
    self.factura.totalBanco = 0
    self.factura.totalTarjeta = 0
    self.update()  
    $(".totalEfectivo").val(null)
    $(".totalTarjeta").val(self.factura.totalComprobante) 
    $('.totalTarjeta').select()
    $('.totalTarjeta').focus()
    return
}

function moverPagoTarjeta(){
        if($('#modalAgregarClienteNuevo').is(':visible')){
           return
        }
        var resultado = __valorNumerico($(".totalEfectivo").val())
        if(resultado > 0){
          self.factura.totalTarjeta = resultado
          self.factura.totalEfectivo = 0
          self.factura.totalBanco = 0
          self.update()  
          $(".totalEfectivo").val(null)
          $(".totalTarjeta").val(self.factura.totalTarjeta) 
          $('.totalTarjeta').select()
          $('.totalTarjeta').focus()
          return
        } 
        resultado = __valorNumerico($(".totalTarjeta").val())
        if(resultado > 0){
            self.factura.totalBanco = self.empresa.pantChino == 0?resultado:0
            self.factura.totalEfectivo = self.empresa.pantChino == 1?resultado:0
            self.factura.totalTarjeta = 0
            self.update()  
            $(".totalBanco").val(null)
            $(".totalTarjeta").val(null)
            $(".totalEfectivo").val(self.factura.totalEfectivo) 
            $('.totalEfectivo').select()
            $('.totalEfectivo').focus()
            return
         } 
        resultado = __valorNumerico($(".totalBanco").val())
        if(resultado > 0){
            self.factura.totalEfectivo = resultado
            self.factura.totalBanco = 0
            self.factura.totalTarjeta = 0
            self.update()  
            $(".totalBanco").val(null)
            $(".totalTarjeta").val(null)
            $(".totalEfectivo").val(self.factura.totalEfectivo) 
            $('.totalEfectivo').select()
            $('.totalEfectivo').focus()
            return
        }    
        self.factura.totalEfectivo = __valorNumerico(self.factura.totalComprobante)
        self.factura.totalBanco = 0
        self.factura.totalTarjeta = 0
        self.update()  
        $(".totalBanco").val(null)
        $(".totalTarjeta").val(null)
        $(".totalEfectivo").val(self.factura.totalEfectivo) 
        $('.totalEfectivo').select()
        $('.totalEfectivo').focus()

}
/**
* Contabilizar los billetes de acuerdo a como se vayan dando click en la pantalla
*/
_sumarBilletes(e){
     if(self.primeraVezBilleteClick == false){
      
        self.factura.totalEfectivo = 0
        self.primeraVezBilleteClick = true
        self.update()

    }
    var item = e.item
    if(item.valor == 0 ){
      self.factura.totalEfectivo = 0
       self.factura.totalTarjeta  = 0
       self.factura.totalBanco    = 0
       self.factura.totalCambioPagar  = 0
       self.totalCambioPagar = 0
       self.totalCambioPagarSTR =0
       self.claseCambioDinero     = "entregarCambioPositivo"
       $(".totalEfectivo").val(null) 
    }else{
       self.factura.totalEfectivo = __valorNumerico(item.valor) + __valorNumerico(self.factura.totalEfectivo)
       $('.efectivo').val(self.factura.totalEfectivo)
        self.update()
        var sumaMontosEntregadosParaCambios =__valorNumerico(self.factura.totalTarjeta)
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco) 
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo) 
        self.factura.totalCambioPagar = 0
        self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios - __valorNumerico(self.factura.totalComprobante)
        self.claseCambioDinero  = __valorNumerico(sumaMontosEntregadosParaCambios) > __valorNumerico(self.factura.totalComprobante)?'entregarCambioPositivo':'entregarCambioNegativo'
        self.totalCambioPagar = redondeoDecimales(self.factura.totalCambioPagar,2)
         $(".totalEfectivo").val(self.factura.totalEfectivo)
        $('.efectivo').val(self.totalCambioPagar)
        self.totalCambioPagarSTR =formatoDecimales(self.totalCambioPagar,2)


    }
    self.update()
}
/**
* Cargar Billetes
**/
function cargaBilletes(){
    self.billetes = []
    self.update()
   _incluirBilletes("₡","1000",1000,'/dist/img/billete1000.jpg')
    _incluirBilletes("₡","2,000",2000,'/dist/img/billete2000.jpg')
    _incluirBilletes("₡","5,000",5000,'/dist/img/billete5000.jpg')
    
    _incluirBilletes("₡","10,000",10000,'/dist/img/billete10000.jpg')
    _incluirBilletes("₡","20,000",20000,'/dist/img/billete20000.jpg')
     _incluirBilletes("₡","50,000",50000,'/dist/img/billete50000.jpg')
     _incluirBilletes("","Limpiar",0,'/dist/img/limpiar.png')
    
}
/**
*    Incluir a los billetes
**/
function _incluirBilletes(modena,descripcion,valor,imagen){
    self.billetes.push(
        {
            modena:modena,
            descripcion:descripcion,
            imagen:imagen,
            valor:valor
        }
    )
    self.update()
}

/**
*  Evento click para mostrar las mesas 
**/
__PantallaMesas(){
    self.mesa = {
         id:null,
         descripcion:"",
         impuestoServicio:false,
     }
     self.mesas  = {
         data:[],
         pagination:{
             total:0,
             current_page:0,
             per_page:0,
             last_page:0,
             from:0,
             to:0
         }
     }
     self.update()
	__ListaMesas()
}

/**
*  Evento click para mostrar el cambio de tiquetes en las mesas 
**/
__PantallaCambiarMesa(){
     self.mesasCambiar  = {
         data:[],
         pagination:{
             total:0,
             current_page:0,
             per_page:0,
             last_page:0,
             from:0,
             to:0
         }
     }
     self.mostrarNavegador            = false
     self.mostrarMesas	              = false
     self.mostrarCategorias           = false //muestra la pantalla de imagenes de articulos   
     self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias   
     self.mostrarFacturasMesas        = false //muestra las facturas por mesa
     self.mostarParaCrearNuevaVentas  = false
     self.mostrarSepararCuenta		  = false 
     self.mostrarCambiarMesa = true;
     self.update()     
	__ListaMesasCambio()
}

__BotonAnteriorMesasCambiar(){
	  self.mesasCambiar.pagination.current_page = self.mesasCambiar.pagination.current_page - 1
	  self.mesasCambiar.pagination.current_page = self.mesasCambiar.pagination.current_page > 1?self.mesasCambiar.pagination.current_page:0;
	  self.parametrosPaginacion.paginaActual = self.parametrosPaginacion.paginaActual > 1?self.parametrosPaginacion.paginaActual - 12:0 
	  self.update()
	  __ListaMesasCambio()
}

__BotonSiguienteMesasCambiar(){
	if(self.mesasCambiar.pagination.current_page <  self.mesasCambiar.pagination.last_page){
		self.mesasCambiar.pagination.current_page = self.mesasCambiar.pagination.current_page + 1
	    self.parametrosPaginacion.paginaActual += 12
	    self.update()
	    __ListaMesasCambio() 
	}
}

__BotonAnteriorMesas(){
	  self.mesas.pagination.current_page = self.mesas.pagination.current_page - 1
	  self.mesas.pagination.current_page = self.mesas.pagination.current_page > 1?self.mesas.pagination.current_page:0;
	  self.parametrosPaginacion.paginaActual = self.parametrosPaginacion.paginaActual > 1?self.parametrosPaginacion.paginaActual - 12:0 
	  self.update()
	  __ListaMesas()
}
 
__BotonSiguienteMesas(){
	if(self.mesas.pagination.current_page <  self.mesas.pagination.last_page){
		self.mesas.pagination.current_page = self.mesas.pagination.current_page + 1
	    self.parametrosPaginacion.paginaActual += 12
	    self.update()
	    __ListaMesas() 
	}
}


__CambiarTiqueteMesa(e){
	console.log(self.factura.id);
	console.log(e.item);
	//Se tiene que hacer el camvbio de mesa
	$.ajax({
        url: 'CambiarFacturaMesa.do',
        datatype: "json",
        data: {
        	idFactura:self.factura.id,
        	idMesa:e.item.id,        	
        },
        method:"POST",
        success: function (result) {
        	self.pendientesComanda     = []
            var item = e.item
            self.mesa = item
            self.factura.mesa = item    
            self.update()
            __ListaFacturasXMesas()
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });	
}

/**
*  Lista las mesas
**/
function __ListaMesas(){
	__InitDatos();
	self.mesas.data = []
	//Primera vez 
    if( self.mesas.pagination.current_page == 0){
        self.mesas.pagination.current_page = 0    
        self.parametrosPaginacion.cantidadPorPagina = 12
        self.parametrosPaginacion.paginaActual = 0
        self.parametrosPaginacion.total = 0
        self.parametrosPaginacion.tipoVenta = 1
        
        self.update()
    }
    $('#cantidadPorPagina').val(self.parametrosPaginacion.cantidadPorPagina)
    $('#paginadaActual').val(self.parametrosPaginacion.paginaActual )
    var formulario = $('#FormPaginacion').serialize();
    $.ajax({
        url: 'ListarPaginacionMesasAjax.do',
        datatype: "json",
        data: formulario,
        method:"POST",
        success: function (result) {
            if(result.aaData.length > 0){
               self.mesas.data = result.aaData
               self.mesas.pagination.total = result.recordsTotal
               self.mesas.pagination.last_page = Math.round(result.recordsTotal/10)
               self.mostrarNavegador            = false
               self.mostrarMesas	            = true
               self.mostrarCategorias           = false //muestra la pantalla de imagenes de articulos   
               self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias   
               self.mostrarFacturasMesas        = false //muestra las facturas por mesa
               self.mostarParaCrearNuevaVentas  = false
               self.mostrarSepararCuenta		    = false //true
               self.mostrarCambiarMesa = false;

               self.update()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

/**
*  Lista las mesas para cambio de tiquete
**/
function __ListaMesasCambio(){
	self.mesasCambiar.data = []
	//Primera vez 
    if( self.mesasCambiar.pagination.current_page == 0){
        self.mesasCambiar.pagination.current_page = 0    
        self.parametrosPaginacion.cantidadPorPagina = 12
        self.parametrosPaginacion.paginaActual = 0
        self.parametrosPaginacion.total = 0
        self.parametrosPaginacion.tipoVenta = 1        
        self.update()
    }
    $('#cantidadPorPagina').val(self.parametrosPaginacion.cantidadPorPagina)
    $('#paginadaActual').val(self.parametrosPaginacion.paginaActual )
    var formulario = $('#FormPaginacion').serialize();
    $.ajax({
        url: 'ListarPaginacionMesasAjax.do',
        datatype: "json",
        data: formulario,
        method:"POST",
        success: function (result) {
            if(result.aaData.length > 0){
               self.mesasCambiar.data = result.aaData
               self.mesasCambiar.pagination.total = result.recordsTotal
               self.mesasCambiar.pagination.last_page = Math.round(result.recordsTotal/10)
               self.mostrarNavegador            = false
               self.mostrarMesas	            = false
               self.mostrarCategorias           = false //muestra la pantalla de imagenes de articulos   
               self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias   
               self.mostrarFacturasMesas        = false //muestra las facturas por mesa
               self.mostarParaCrearNuevaVentas  = false
               self.mostrarSepararCuenta		= false //true
               self.update()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}


//Se presentan las facturas para cada mesa
__FacturasXMesa(e){
    self.pendientesComanda     = []
    var item = e.item
    self.mesa = item
    self.factura.mesa = item    
    self.update()
    __ListaFacturasXMesas()
}

//Se presentan las facturas para cada mesa
__FacturasXMesaTiqueteEncabezado(){
    self.pendientesComanda     = []
    self.update()
    __Init()
    __ListaFacturasXMesas()
}
/**
*  Lista las facturas de una mesa
**/
function __ListaFacturasXMesas(){

	//Primera vez 
    if( self.facturasXMesa.pagination.current_page == 0){
        self.facturasXMesa.pagination.current_page = 0    
        self.parametrosPaginacion.cantidadPorPagina = 40
        self.parametrosPaginacion.paginaActual = 0
        self.parametrosPaginacion.total = 0
        self.parametrosPaginacion.tipoVenta = 1
        self.update()
    }
    $('#cantidadPorPagina').val(self.parametrosPaginacion.cantidadPorPagina)
    $('#paginadaActual').val(self.parametrosPaginacion.paginaActual )
    $('#mesa').val(self.mesa.id )

    var formulario = $('#FormPaginacion').serialize();
	self.facturasXMesa.data = []
    $.ajax({
        url: 'ListarFacturasEsperaPorMesaAjax',
        datatype: "json",
        data: formulario,
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
               self.facturasXMesa.data = result.aaData
               self.facturasXMesa.pagination.total = result.recordsTotal
               self.facturasXMesa.pagination.last_page = Math.round(result.recordsTotal/10)
               self.mostrarCategorias        = false //muestra la pantalla de imagenes de articulos   
               self.mostrarFacturasMesas     = true //muestra las facturas por mesa
            }else{
               self.mostrarCategorias       = true //muestra la pantalla de imagenes de articulos   
               self.mostrarFacturasMesas    = false //muestra las facturas por mesa
               mostrarCategorias();
            }
            self.mostrarMesas	             = false //muestra la pantalla mesas               
            self.mostrarCodigoBarra          = false;
            self.mostrarNavegador            = true
            self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias
            self.mostarParaCrearNuevaVentas  = true
            self.mostrarSepararCuenta		    = false //true
            self.mostrarCambiarMesa = false;
            self.update()        
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

//------------------------------------------------------------------------------------
//--------------Nuevos Metodos ---------------------------------
//------------------------------------------------------------------------------------
__MostrarIconoComandasPendientes(codigoArticulo){
	var obj = self.pendientesComanda.find(o => o.key === codigoArticulo);
	if(typeof obj !== "undefined"){
		return true;	
	}else{
		return false;	
	}
}

/**
*  Lista las comandas pendientes y las muestra en pantalla
**/
__MostrarComandasPendientes(e){	
    //Se busca la comanda pendiente por codigo de articulo
    var obj = self.pendientesComanda.find(o => o.key === e.item.codigo);
	if(typeof obj !== "undefined"){
	    var detalles = [];
	    $('#modalComandasPendientes').modal('show')  

	    //Se recorren las comandas pendientes y se presentan para modificar el comentario
		obj.data.forEach(function(elemen){
			detalles.push({	        
				codigo          : elemen.codigo,
			    descripcion     : elemen.descripcion,
			    comentario      : elemen.comentario,
			});			
		});
	    
	    //Valores del data table
	    self.informacion_tabla_comandasPendientes = [	
	        {'data' : 'codigo'           ,"name":"codigo"            ,"title" : $.i18n.prop("articulo.codigo")            ,"autoWidth":false},
	        {'data' : 'descripcion'      ,"name":"descripcion"       ,"title" : $.i18n.prop("articulo.descripcion")       ,"autoWidth":false},
	        {'data' : 'comentario'       ,"name":"comentario"        ,"title" : $.i18n.prop("articulo.comentario"),        "autoWidth":false,
                "render": function(data, type, row, meta){
                	return '<input  id="' + row.codigo + '" data-index="' + meta.row + '" value="' + data + '" type="text" class="form-control dataTableComentario"/>'
                }
            },
	    ]; 	 	
	 	loadListar(".tableListaComandaPendientes",idioma_espanol, self.informacion_tabla_comandasPendientes, detalles)
        ActivarEventoFiltro(".tableListaComandaPendientes")
	}
}

__ComentariosComanda(e){
	
	//Se obtienen todos los inputs y se actualizan los valores de la consola
	$(".dataTableComentario").each(function() {
		var valor = this.value;
		var idx = this.getAttribute("data-index");
		var idArticulo = this.getAttribute("id");
		var obj = self.pendientesComanda.find(o => o.key === idArticulo);
		if(typeof obj !== "undefined"){
			obj.data[idx].comentario = valor;
		}
	 } 
	);
  	$('#modalComandasPendientes').modal('hide') 
}

/**
*   Agregar el articulo a la lista de pendientes en la comanda en session
**/
function __nuevoArticuloComanda(cantidad, codigo, descripcion,commanda){	
	
    
	//Se almacenan los productos por separados en la comanda
	for (var i = 0; i < cantidad; i++) {
		//Se busca si existe en el mapa, se actualiza la lista o se hace el put inicial
		var obj = self.pendientesComanda.find(o => o.key === codigo);
		if(typeof obj !== "undefined"){
			obj.data.push({	        
		        codigo          : codigo,
		        descripcion     : descripcion,
		        comentario      : "",
                comanda         : commanda,
		    });	
		}else{
			var datos = [];
			datos.push({	        
		        codigo          : codigo,
		        descripcion     : descripcion,
		        comentario      : "",
                comanda         : commanda,
		    });		
			self.pendientesComanda.push({	        
				key : codigo,
				data : datos
		    });	
		}
        self.update()
	} 	
    
 }
 
/**
*   Elimina el articulo de la comanda en session, se elimina el detalle
**/
function eliminaArticuloComanda(codigoArticulo){
	//Se elimina de los pendientes en session para no almacenarlo
	self.pendientesComanda.splice(self.pendientesComanda.findIndex(o => o.key === codigoArticulo), 1); 
    self.update()
} 


/**
*   Envia a la comanda de la cocina, se imprimen mediante el local host
**/
function __EnviarCocina(){
	
	//Se forman los detalles a enviar a la comanda
    var url = self.IpImprmirComanda.length > 0 ? 'http://'+self.IpImprmirComanda+':8033/service/CrearOrdenCocinaAjax':'http://localhost:8033/service/CrearOrdenCocinaAjax';
	var detalles_cocina_1 = [];
    var detalles_cocina_2 = [];
	self.pendientesComanda.forEach(function(elemenKey){
		elemenKey.data.forEach(function(elemen){
            if(esCocina1(elemen.comanda) ){
                detalles_cocina_1.push({	        
                    codigo          : elemen.codigo,
                    descripcion     : elemen.descripcion,
                    comentario      : elemen.comentario,
                });			
            }
            if(esCocina2(elemen.comanda) ){
                detalles_cocina_2.push({	        
                    codigo          : elemen.codigo,
                    descripcion     : elemen.descripcion,
                    comentario      : elemen.comentario,
                });			
            }

		});
	});
	if(detalles_cocina_1.length > 0){
		//Se forman los datos genales para la comanda
		var informacion = {
			mesa: self.mesa.descripcion,        	
			mesero: "",        	
		    nombreImpresora:self.empresa.impresoraCocina,
		    cantidadCaracteresLinea:"40",
		    formatoTiquete:"",
		    detalles:detalles_cocina_1
		}    

        

		var JSONData = JSON.stringify(informacion);		
        enviarImpresoraCocina(url,JSONData);
        registrarComanda(self.empresa.impresoraFactura,detalles_cocina_1,self.facturaImpresa,self.mesa)
	}
    if(detalles_cocina_2.length > 0){
		//Se forman los datos genales para la comanda
		var informacion = {
			mesa: self.mesa.descripcion,        	
			mesero: "",        	
		    nombreImpresora:self.empresa.impresoraFactura,
		    cantidadCaracteresLinea:"40",
		    formatoTiquete:"",
		    detalles:detalles_cocina_2
		}    

		var JSONData = JSON.stringify(informacion);		
        enviarImpresoraCocina(url,JSONData);
        registrarComanda(self.empresa.impresoraFactura,detalles_cocina_2,self.facturaImpresa,self.mesa)
	}
} 
/**
*  Registrar la comanda
**/
function registrarComanda(impresora,detalles,factura,mesa){
    var parametros = {
        impresora:impresora,
        detalles:detalles,
        idFactura:factura.id,
        idMesa:mesa.id,
        descMesa:mesa.descripcion
    }
		//Envia a imprimir a la comanda
	    $.ajax({
	        contentType: 'application/json',
	        url: 'AgregarComandaCocina.do',
	        datatype: "json",
            global: false,
	        data : parametros,
	        method:"POST",
	        success: function (result) {
	        },
	        error: function (xhr, status) {
	            console.log(xhr);
                console.log(status);
	            //mensajeErrorServidor(xhr, status);
	        }
	    });		

}
/**
*Enviar a las impresoras de cocina
**/
function enviarImpresoraCocina(url,parametros){
		//Envia a imprimir a la comanda
	    $.ajax({
	        contentType: 'application/json',
	        url: url,
	        datatype: "json",
            global: false,
	        data : parametros,
	        method:"POST",
	        success: function (result) {
	      	   $('#modalComandasPendientes').modal('hide')  
	      	   self.pendientesComanda = []
	      	   self.update()
	        },
	        error: function (xhr, status) {
	            console.log(xhr);
                console.log(status);
	            //mensajeErrorServidor(xhr, status);
	        }
	    });		

}
 
__MostrarSeperarCuentas(){
	mostrarSeperarCuentasFun();
}

function mostrarSeperarCuentasFun(){
	self.facturaRespaldo                = {
	    id:null,
	    fechaCredito:null,
	    fechaEmision:null,
	    condicionVenta:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalImpuestoServ:0,
	    totalComprobante:0,
	    totalEfectivo:0,
	    totalTarjeta:0,
	    totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
	    codigoMoneda:"",
	    estado:0,
	    cliente:{
	        id:null,
	        nombreCompleto:"",
	    },
	    vendedor:{
	        id:null,
	        nombreCompleto:""
	    },
	    mesa:{
	        id:null,
	        descripcion:"",
	        impuestoServicio:false
	   }
	} 	
	self.mostrarSepararCuenta		    = true;
	self.mostarParaCrearNuevaVentas		= false;
	self.mostarParaCrearNuevaFactura    = false;
	self.mostrarCambiarMesa = false;	
    self.detailPorSeparar      = []
    self.detailFacturaSeparada = []

    self.detail.forEach(function(elemen){
    	self.detailPorSeparar.push(elemen);
    });
    
    self.totalComprobantePorSeparar    = 0	
    self.subTotalGeneralPorSeparar     = 0
    self.totalDescuentosPorSeparar     = 0
    self.totalImpuestoPorSeparar       = 0
    self.totalImpuestoServPorSeparar   = 0

    self.totalComprobanteFactSeparada  = 0	
    self.subTotalGeneralFactSeparada   = 0
    self.totalDescuentosFactSeparada   = 0
    self.totalImpuestoFactSeparada     = 0
    self.totalImpuestoServFactSeparada = 0

    self.totalComprobantePorSeparar = self.totalComprobante;
    self.subTotalGeneralPorSeparar = self.subTotalGeneral;
    self.totalDescuentosPorSeparar = self.totalDescuentos;
    self.totalImpuestoPorSeparar = self.totalImpuesto;
    self.totalImpuestoServPorSeparar = self.totalImpuestoServ;
	self.update();
}

__addCuentaSeparada(e) {    
	//Se busca el index del item 
	var item = e.item;
    var indexPorSeparar = self.detailPorSeparar.indexOf(item);
    var indexSeparada = -1;

    //Se recorren los detalles separados
    self.detailFacturaSeparada.forEach(function(elemen){
    	if(elemen.codigo == self.detailPorSeparar[indexPorSeparar].codigo){
    		indexSeparada = self.detailFacturaSeparada.indexOf(elemen);
    	}
    });

    //Se encontro
    if(indexSeparada >= 0){    	
    	//Se modifica la cantidad
    	self.detailFacturaSeparada[indexSeparada].cantidad = self.detailFacturaSeparada[indexSeparada].cantidad + 1;
    }else{
    	//Se agrega el elmento
    	self.detailFacturaSeparada.push({
             codigo          : self.detailPorSeparar[indexPorSeparar].codigo,
             tipoImpuesto    : self.detailPorSeparar[indexPorSeparar].tipoImpuesto,
             descripcion     : self.detailPorSeparar[indexPorSeparar].descripcion,
             cantidad        : parseFloat(1),
             precioUnitario  : parseFloat(self.detailPorSeparar[indexPorSeparar].precioUnitario),
             impuesto        : parseFloat(self.detailPorSeparar[indexPorSeparar].impuesto),
             montoImpuesto   : parseFloat(0),
             montoDescuento  : parseFloat(0),
             porcentajeDesc  : parseFloat(self.detailPorSeparar[indexPorSeparar].porcentajeDesc),
             subTotal        : parseFloat(0),
             montoTotalLinea : parseFloat(0),
             montoTotal      : parseFloat(0),
             costo           : parseFloat(self.detailPorSeparar[indexPorSeparar].costo),
            porcentajeGanancia : parseFloat(self.detailPorSeparar[indexPorSeparar].gananciaPrecioPublico),
        });
    }

    //Se verifica si la cantidad es mayor a 1
    if(self.detailPorSeparar[indexPorSeparar].cantidad > 1){
    	//Se resta
    	self.detailPorSeparar[indexPorSeparar].cantidad = self.detailPorSeparar[indexPorSeparar].cantidad - 1;
    }else{
    	//Se elimina del vector
        this.detailPorSeparar.splice(indexPorSeparar, 1);
    }    
    
    actualizaDetallesFacturasPorSeparar();
    __calculatePorSeparar();
    self.update();
}


__removeCuentaSeparada(e) {    
	//Se busca el index del item 
	var item = e.item;
    var indexPorSeparar = self.detailFacturaSeparada.indexOf(item);
    var indexSeparada = -1;

    //Se recorren los detalles separados
    self.detailPorSeparar.forEach(function(elemen, index){
    	if(elemen.codigo == self.detailFacturaSeparada[indexPorSeparar].codigo){
    		indexSeparada = index;
    	}
    });

    //Se encontro
    if(indexSeparada >= 0){    	
    	//Se modifica la cantidad
    	self.detailPorSeparar[indexSeparada].cantidad = self.detailPorSeparar[indexSeparada].cantidad + 1;
    }else{
    	//Se agrega el elmento
    	self.detailPorSeparar.push({
             codigo          : self.detailFacturaSeparada[indexPorSeparar].codigo,
             tipoImpuesto    : self.detailFacturaSeparada[indexPorSeparar].tipoImpuesto,
             descripcion     : self.detailFacturaSeparada[indexPorSeparar].descripcion,
             cantidad        : parseFloat(1),
             precioUnitario  : parseFloat(self.detailFacturaSeparada[indexPorSeparar].precioUnitario),
             impuesto        : parseFloat(self.detailFacturaSeparada[indexPorSeparar].impuesto),
             montoImpuesto   : parseFloat(0),
             montoDescuento  : parseFloat(0),
             porcentajeDesc  : parseFloat(self.detailFacturaSeparada[indexPorSeparar].porcentajeDesc),
             subTotal        : parseFloat(0),
             montoTotalLinea : parseFloat(0),
             montoTotal      : parseFloat(0),
             costo           : parseFloat(self.detailPorSeparar[indexPorSeparar].costo),
             porcentajeGanancia : parseFloat(self.detailPorSeparar[indexPorSeparar].gananciaPrecioPublico),
        });
    }

    //Se verifica si la cantidad es mayor a 1
    if(self.detailFacturaSeparada[indexPorSeparar].cantidad > 1){
    	//Se resta
    	self.detailFacturaSeparada[indexPorSeparar].cantidad = self.detailFacturaSeparada[indexPorSeparar].cantidad - 1;
    }else{
    	//Se elimina del vector
        this.detailFacturaSeparada.splice(indexPorSeparar, 1);
    }    

    actualizaDetallesFacturasPorSeparar();
    __calculatePorSeparar();
    self.update();
}

__MostrarFormularioDePagoFactPrincipal(){
	if(self.detailFacturaSeparada.length > 0 ){
		mensajeAdvertencia($.i18n.prop("factura.alert.con.detalles.separar"))
		return
	}else{
		mostrarPAgo()		
	}
}
__NombreDividirFactura(){
	if(self.detailFacturaSeparada.length == 0 ){
	   mensajeAdvertencia($.i18n.prop("factura.alert.sin.detalles.separar"))
	   return
	}else if(self.detailPorSeparar.length == 0 ){
		mensajeAdvertencia($.i18n.prop("factura.alert.sin.detalles.principal"))
		return
	}else{
		$('#ModalNombreTiqueteSepararCuenta').modal('show') 
	    $('.cambioNombreSepararCuenta').focus()
        return
	}
}

function cargaFacturaRespaldo(){
	self.facturaRespaldo.id = self.factura.id;
	self.facturaRespaldo.fechaCredito = self.factura.fechaCredito;
	self.facturaRespaldo.fechaEmision = self.factura.fechaEmision;
	self.facturaRespaldo.condicionVenta = self.factura.condicionVenta;
	self.facturaRespaldo.plazoCredito = self.factura.plazoCredito;
	self.facturaRespaldo.tipoDoc = self.factura.tipoDoc;
	self.facturaRespaldo.medioPago = self.factura.medioPago;
	self.facturaRespaldo.nombreFactura = self.factura.nombreFactura;
	self.facturaRespaldo.direccion = self.factura.direccion;
	self.facturaRespaldo.nota = self.factura.nota;
	self.facturaRespaldo.comanda = self.factura.comanda;
	self.facturaRespaldo.subTotal = self.factura.subTotal;
	self.facturaRespaldo.totalTransporte = self.factura.totalTransporte;
	self.facturaRespaldo.total = self.factura.total;
	self.facturaRespaldo.totalVenta = self.factura.totalVenta;
	self.facturaRespaldo.totalDescuentos = self.factura.totalDescuentos;
    self.facturaRespaldo.totalVentaNeta = self.factura.totalVentaNeta;
    self.facturaRespaldo.totalImpuesto = self.factura.totalImpuesto;
    self.facturaRespaldo.totalImpuestoServ = self.factura.totalImpuestoServ;
    self.facturaRespaldo.totalComprobante = self.factura.totalComprobante;
    self.facturaRespaldo.totalEfectivo = self.factura.totalEfectivo;
    self.facturaRespaldo.totalTarjeta = self.factura.totalTarjeta;
    self.facturaRespaldo.totalCambioPagar = self.factura.totalCambioPagar;
    self.facturaRespaldo.totalBanco = self.factura.totalBanco;
    self.facturaRespaldo.totalCredito = self.factura.totalCredito;
    self.facturaRespaldo.montoCambio = self.factura.montoCambio;
    self.facturaRespaldo.totalCambio = self.factura.totalCambio;
    self.facturaRespaldo.codigoMoneda = self.factura.codigoMoneda;
    self.facturaRespaldo.estado = self.factura.estado;
    self.facturaRespaldo.cliente.id = self.factura.cliente.id;
    self.facturaRespaldo.cliente.nombreCompleto = self.factura.cliente.nombreCompleto;
   	self.facturaRespaldo.vendedor.id = self.factura.vendedor.id;
   	self.facturaRespaldo.vendedor.nombreCompleto = self.factura.vendedor.nombreCompleto;
    self.facturaRespaldo.mesa.id = self.factura.mesa.id;
    self.facturaRespaldo.mesa.descripcion = self.factura.mesa.descripcion;
    self.facturaRespaldo.mesa.impuestoServicio = self.factura.mesa.impuestoServicio;
}

function cargaFacturaConRespaldo(){
	self.factura.id = self.facturaRespaldo.id;
	self.factura.fechaCredito = self.facturaRespaldo.fechaCredito;
	self.factura.fechaEmision = self.facturaRespaldo.fechaEmision;
	self.factura.condicionVenta = self.facturaRespaldo.condicionVenta;
	self.factura.plazoCredito = self.facturaRespaldo.plazoCredito;
	self.factura.tipoDoc = self.facturaRespaldo.tipoDoc;
	self.factura.medioPago = self.facturaRespaldo.medioPago;
	self.factura.nombreFactura = self.facturaRespaldo.nombreFactura;
	self.factura.direccion = self.facturaRespaldo.direccion;
	self.factura.nota = self.facturaRespaldo.nota;
	self.factura.comanda = self.facturaRespaldo.comanda;
	self.factura.subTotal = self.facturaRespaldo.subTotal;
	self.factura.totalTransporte = self.facturaRespaldo.totalTransporte;
	self.factura.total = self.facturaRespaldo.total;
	self.factura.totalVenta = self.facturaRespaldo.totalVenta;
	self.factura.totalDescuentos = self.facturaRespaldo.totalDescuentos;
    self.factura.totalVentaNeta = self.facturaRespaldo.totalVentaNeta;
    self.factura.totalImpuesto = self.facturaRespaldo.totalImpuesto;
    self.factura.totalImpuestoServ = self.facturaRespaldo.totalImpuestoServ;
    self.factura.totalComprobante = self.facturaRespaldo.totalComprobante;
    self.factura.totalEfectivo = self.facturaRespaldo.totalEfectivo;
    self.factura.totalTarjeta = self.facturaRespaldo.totalTarjeta;
    self.factura.totalCambioPagar = self.facturaRespaldo.totalCambioPagar;
    self.factura.totalBanco = self.facturaRespaldo.totalBanco;
    self.factura.totalCredito = self.facturaRespaldo.totalCredito;
    self.factura.montoCambio = self.facturaRespaldo.montoCambio;
    self.factura.totalCambio = self.facturaRespaldo.totalCambio;
    self.factura.codigoMoneda = self.facturaRespaldo.codigoMoneda;
    self.factura.estado = self.facturaRespaldo.estado;
    self.factura.cliente.id = self.facturaRespaldo.cliente.id;
    self.factura.cliente.nombreCompleto = self.facturaRespaldo.cliente.nombreCompleto;
   	self.factura.vendedor.id = self.facturaRespaldo.vendedor.id;
   	self.factura.vendedor.nombreCompleto = self.facturaRespaldo.vendedor.nombreCompleto;
    self.factura.mesa.id = self.facturaRespaldo.mesa.id;
    self.factura.mesa.descripcion = self.facturaRespaldo.mesa.descripcion;
    self.factura.mesa.impuestoServicio = self.facturaRespaldo.mesa.impuestoServicio;
}
__DividirFactura(){

	//Se respalda la factura original para tomar los datos principales de ahi
	//self.facturaRespaldo = self.factura;
	cargaFacturaRespaldo();
	
	//============== Se actualiza la factura 
	//Se actualiza el detalle de la factura.
	self.detail = self.detailPorSeparar;
	
	//Se calculan el detalle de la factura nuevamente y se crea el tiquete temporal
	__calculate();
	__CrearFacturaTemporalFunc();
	self.update();
	
	//============== Se crea una nueva factura 
	//Se crea un nuevo tiquete
	//self.factura = self.facturaRespaldo;
	cargaFacturaConRespaldo();
	//self.factura.cliente = self.facturaRespaldo.cliente; 
	//self.factura.vendedor = self.facturaRespaldo.vendedor; 
	self.factura.mesa = self.mesa;
	self.factura.id = null;
	self.factura.detalles = [];
	self.nombreFactura = "";
	self.detail = [];	
    self.detailFacturaSeparada.forEach(function(elemen){
    	self.detail.push(elemen);
    });
 	self.update();

 	//Se calculan el detalle de la factura nuevamente y se crea el tiquete temporal
	__calculate();
 	
 	//Se actualiza el nombre del nuevo tiquete
	$('#ModalNombreTiqueteSepararCuenta').modal('hide') 
    self.factura.nombreFactura = $('.cambioNombreSepararCuenta').val() == null ? "" :$('.cambioNombreSepararCuenta').val();
	self.nombreFactura =  $('.cambioNombreSepararCuenta').val()==null?"":$('.cambioNombreSepararCuenta').val();
 	self.mesa.id = self.factura.mesa.id
    self.update()
    
    //Se crea la factura
    aplicarFactura(1, true)      
    self.update()
    $(".plazoCreditoL").val(null)   
    $(".fechaCredito").val(null)
    self.factura.fechaCredito = null;
    mostrarPAgo();
    self.update();
}

function actualizaDetallesFacturasPorSeparar(){
	//Se recorren los detalles por separar
    self.detailPorSeparar.forEach(function(elemen, index){
        self.item = elemen;
           //factura.js
        self.item = ActualizarLineaDEtalle(self.item) 
        self.detailPorSeparar[index] = self.item;
    });
	
	//Se recorren los detalles por separar
    self.detailFacturaSeparada.forEach(function(elemen, index){
        self.item = elemen;
           //factura.js
        self.item = ActualizarLineaDEtalle(self.item) 
        self.detailFacturaSeparada[index] = self.item;
    });
}


function __calculatePorSeparar() {

    self.totalComprobantePorSeparar    = 0	
    self.subTotalGeneralPorSeparar     = 0
    self.totalDescuentosPorSeparar     = 0
    self.totalImpuestoPorSeparar       = 0
    self.totalImpuestoServPorSeparar   = 0

    self.totalComprobanteFactSeparada  = 0	
    self.subTotalGeneralFactSeparada   = 0
    self.totalDescuentosFactSeparada   = 0
    self.totalImpuestoFactSeparada     = 0
    self.totalImpuestoServFactSeparada = 0

	//Se calcula los totales factura por separar
	totalVenta     = 0
    subTotal       = 0
    totalDescuento = 0
    totalImpuesto  = 0
    totalImpuestoServ  = 0
    totalComprobante        = 0
    totalventaNeta          = 0

    self.detailPorSeparar.forEach(function(e){
        totalComprobante        += e.montoTotalLinea
        subTotal                += e.subTotal >0?e.subTotal:0
        totalDescuento          += e.montoDescuento >0?e.montoDescuento:0
        totalImpuesto           += e.montoImpuesto >0?e.montoImpuesto:0
        totalVenta              += e.montoTotal
    });
    
    //cuando se aplica descuentos
    self.totalDescuentosPorSeparar       = formatoDecimales(Math.round(__valorNumerico(totalDescuento)),2);
    self.subTotalGeneralPorSeparar       = formatoDecimales(Math.round(__valorNumerico(subTotal)),2);
    self.totalImpuestoPorSeparar         = formatoDecimales(Math.round(__valorNumerico(totalImpuesto)),2);
    //Se verifica si la mesa tiene impuestos
    if(self.factura.mesa.impuestoServicio  == true){
        totalImpuestoServ = Math.round(__valorNumerico(subTotal * 0.10));
        self.totalImpuestoServPorSeparar = formatoDecimales(totalImpuestoServ,2);
        self.totalComprobantePorSeparar = formatoDecimales(Math.round(__valorNumerico(totalComprobante + totalImpuestoServ)),2);
    }else{
        self.totalComprobantePorSeparar = formatoDecimales(Math.round(__valorNumerico(totalComprobante)),2);
    }
    self.update(); 
    
	//Se calcula los totales factura separada
	totalVenta     = 0
    subTotal       = 0
    totalDescuento = 0
    totalImpuesto  = 0
    totalImpuestoServ  = 0
    totalComprobante        = 0
    totalventaNeta          = 0

    self.detailFacturaSeparada.forEach(function(e){
        totalComprobante        += e.montoTotalLinea
        subTotal                += e.subTotal >0?e.subTotal:0
        totalDescuento          += e.montoDescuento >0?e.montoDescuento:0
        totalImpuesto           += e.montoImpuesto >0?e.montoImpuesto:0
        totalVenta              += e.montoTotal
    });
    
    //cuando se aplica descuentos
    self.totalDescuentosFactSeparada       = formatoDecimales(Math.round(__valorNumerico(totalDescuento)),2);
    self.subTotalGeneralFactSeparada       = formatoDecimales(Math.round(__valorNumerico(subTotal)),2);
    self.totalImpuestoFactSeparada         = formatoDecimales(Math.round(__valorNumerico(totalImpuesto)),2);
    //Se verifica si la mesa tiene impuestos
    if(self.factura.mesa.impuestoServicio  == true){
        totalImpuestoServ = Math.round(__valorNumerico(subTotal * 0.10));
        self.totalImpuestoServFactSeparada = formatoDecimales(totalImpuestoServ,2);
        self.totalComprobanteFactSeparada = formatoDecimales(Math.round(__valorNumerico(totalComprobante + totalImpuestoServ)),2);
    }else{
        self.totalComprobanteFactSeparada = formatoDecimales(Math.round(__valorNumerico(totalComprobante)),2);
    }
    self.update(); 
}	

__AtrasSepararCuentas(){
	__FacturaEnEspera(self.factura); 	
	self.update()
    $('.totalEfectivo').val(null)
    $('.totalTarjeta').val(null)
    $('.totalBanco').val(null)
    self.primeraVezBilleteClick = false
    self.factura.totalEfectivo =0
    self.factura.totalTarjeta =0
    self.factura.totalBanco =0
    self.totalCambioPagar =0
    self.totalCambioPagarSTR =0
   self.mostrarFormularioPago = false
   self.mostarParaCrearNuevaVentas = true;
   self.mostrarSepararCuenta	= false //true
   self.mostrarCambiarMesa = false;
   self.mostarParaCrearNuevaFactura = true
   self.error = false
   self.update()
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
                    mensajeAdvertencia( data.message);
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.empresa = modeloTabla
                       if(self.empresa.separarCuenta == 1){
                    	   self.separarCuenta = true;
                       }
                       if(self.empresa.abrirSinComanda == 0 && self.empresa.abrirConComanda == 0){
                         self.mostarAbrirCajon = false
                       }
                       self.IpImprmirComanda = self.empresa.ipImprimirComanda;
                       self.update()
                    });
                    __ComboTipoDocumentos(0)
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
}
__AbrirCajon(){
  __OpcionAbrirCajon()  
}

/**
*  Opcion para abrir la comanda
**/
function __OpcionAbrirCajon(){
    self.informacionAbrirCajon = "."
    self.update()
    if(self.empresa.abrirSinComanda == 1){
        abrirCajonDineroSinComanda()  
    }
    if(self.empresa.abrirConComanda == 1){
      abrirCajonDineroConComanda()
    }

}

/**
*Abrir el cajon de dinero sin comanda
**/
function abrirCajonDineroSinComanda(){
  var div = document.querySelector("#imprAbriCajon");
  var ventana = window.open('', 'PRINT', 'height=20,width=20');
  ventana.document.write('<html><head><title>' + "" + '</title>');
  ventana.document.write('</head><body >');
  ventana.document.write(div.innerHTML);
  ventana.document.write('</body></html>');
  ventana.document.close();
  ventana.focus();
  ventana.print();
  ventana.close();
  return true;
}

/**
* Abrir con con comanda
**/
function abrirCajonDineroConComanda(){
//Se forman los detalles a enviar a la comanda
		var informacion = {
			mesa: "Abrir Cajon",        	
			mesero: "abrirCajon",        	
		    nombreImpresora:self.empresa.impresoraFactura,
		    cantidadCaracteresLinea:"40",
		    formatoTiquete:"",
		    detalles:""
		}    

		var JSONData = JSON.stringify(informacion);		
		//Envia a imprimir a la comanda
	    $.ajax({
	        contentType: 'application/json',
	        url: 'http://localhost:8033/service/abrirCajonDinero',
	        datatype: "json",
            global: false,
	        data : JSONData,
	        method:"POST",
	        success: function (result) {
	      	  
	        },
	        error: function (xhr, status) {
	            console.log(xhr);
	            mensajeErrorServidor(xhr, status);
	        }
	    });		
	
}

</script>

</venta-restaurante>