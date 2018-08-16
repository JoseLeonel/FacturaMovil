<venta-factura>
<div>
    <form id="FormPaginacion">
        <input type="hidden" name="id" id="id" value="{parametrosPaginacion.cantidadPorPagina}">

        <input type="hidden" name="cantidadPorPagina" id="cantidadPorPagina" value="{parametrosPaginacion.cantidadPorPagina}">
        <input type="hidden" name="paginaActual" id="paginaActual" value="{parametrosPaginacion.paginaActual}">
        <input type="hidden" name="total" id="total" value="{parametrosPaginacion.total}">
        <input type="hidden" name="categoria" id="categoria" value="{categoria.id}">
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
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->

<!--Inicio de la Venta-->
<div show={mostarParaCrearNuevaVentas}>
    <div class="content">
        <div class="row-fluid no-space">
            <div class="col-md-5 col-sm-5 col-lg-5 col-xs-12 pull-right" style="padding: 0px 10px">
                <div class="block panel newPanel newContPrecio">                    
                    <div id="listadoProdcutos">Listado Productos {factura.id>0?' Id:'+factura.id:'' }</div>
                    <hr style="margin: 2px 0px 0px 0px; border-color: #e4e4e4; margin-top: 0px">
                    <div class="data-fluid">
                        <div id="listaProductos" style="height:200px; overflow-x: hidden; width:100%">
                            <table id="tablaListaProductos" cellpadding="0" cellspacing="0" width="100%" class="dtable lcnp">
                                <thead>
                                    <tr style=" display: none">
                                        <td width="50%"></td>
                                        <td width="10%"></td>
                                        <td width="10%"></td>
                                        <td width="10%"></td>
                                        <td width="10%"></td>
                                        <td width="19%"></td>
                                        <td width="1%"></td>
                                        <td width="10%"></td>
                                    </tr>
                                </thead>
                                <tbody height="50px" id="productos-detail">
                                    <tr style="" each={detail}>
                                        <td>
                                            <span onclick ={__CambiarDescripcion} class="title-detalle text-info">
                                            {descripcion}</span>
                                        </td>
                                        <td >
                                            <span onclick ={__CambiarCantidad} class="label label-success cantidad">{cantidad.toFixed(3)}</span>
                                        </td>
                                        <td class="contCalc">
                                            <span onclick ={__CambiarPrecio}  class="label label-success precio-prod" >{precioUnitario.toLocaleString('de-DE')}</span>
                                        </td>
                                        <td class="contCalc">
                                            <span onclick ={__CambiarDescuento} class="label label-success precio-prod" >{porcentajeDesc.toLocaleString('de-DE')}</span>
                                        </td>
                                        <td class="contCalc">
                                            <span class="label label-success " >{impuesto}</span>
                                        </td>
                                        <td>
                                            <span class="precio-calc">{montoTotalLinea.toLocaleString('de-DE')}</span>
                                        </td>
                                        <td>
                                            <button  onclick={__removeProductFromDetail} class="btn_eliminar_detalle btn-danger btn-xs btn-block">X</button>
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
                                        <td width="30%" id="bordeBevelLeft"> 
                                            <span id="pagarInfo"> {$.i18n.prop("factura.resumen.subTotal")}: </span>
                                            <span id="cantidad-total">{subTotalGeneral.toLocaleString('de-DE')  } </span> 
                                        </td>
                                        <td width="35%" id="bordeBevelLeft"> 
                                            <span id="pagarInfo">{$.i18n.prop("factura.resumen.descuento")} : </span>
                                            <span id="sigPeso">   </span>
                                            <span id="iva-total">{factura.totalDescuentos.toLocaleString('de-DE')}</span> 
                                        </td>
                                        <td width="35%" id="bordeBevelRight"> 
                                            <span id="pagarInfo">{$.i18n.prop("factura.resumen.impuesto")}  </span>
                                            <span id="sigPeso">      </span>
                                            <span id="subtotal">{factura.totalImpuesto.toLocaleString('de-DE')}</span> 
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <hr style="margin: 0px; border-color: #e4e4e4; margin-top: 0px">                    
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
                                                <span class="label label-info textShadow" id="total-show">{factura.totalComprobante.toLocaleString('de-DE')}</span>
                                            </div>
                                        </td>
                                    </tr>                     
                                </tbody>
                            </table>
                        </div>
                        <hr style="margin: 0px; border-color: #e4e4e4;">
                    </div>
                    <div  class="row  ">
                        <div class="col-md-12 "  style="padding:10px 15px 18px 15px; ">
                            <!--Cliente o Nuevo Cliente-->
                            <div class="input-group">
                                <span onclick = {__MostrarFormularioNuevoCliente} title="Nuevo Cliente" class="input-group-addon btnClientes" id="add-new-client"> 
                                    <small class="fa fa-plus" style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                    <span class="fa fa-user" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"></span> 
                                </span>
                                <input onclick={_EscogerClientes} type="text"  placeholder="Cliente" value="{cliente.nombreCompleto}"  name="datos_cliente" id="datos_cliente" autocomplete="off" >
                            </div>
                            <!--Fin Cliente o Nuevo Cliente-->
                            <!--Vendedor o Nuevo Vendedor-->
                            <div class="input-group">
                                <span title="Vendedor" class="input-group-addon " > 
                                    <span class="fa fa-user" aria-hidden="true" style="margin:3px 4px 0px 2px"></span> 
                                </span>
                                <input type="text" onclick={_EscogerVendedores} placeholder="Vendedor" value="{vendedor.nombreCompleto}"  name="v_vendedor" id="v_vendedor" autocomplete="off" >
                            </div>
                        </div>
                    </div> 
                    <hr style="margin: 0px; border-color: #e4e4e4;">
                    <div  class="row  ">
                        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12 " >   
                            <section class="contenedor-opciones">
                                <a href="#" class="opciones-menu" onclick = {__CrearFacturaTemporal} >
                                    <i class="fa fa-clock-o">{$.i18n.prop("venta.en.espera")}</i>
                                </a>
                                <a  href="#" class="opciones-limpiar" onclick = {__Limpiar} >
                                    <i class="fa fa-trash">{$.i18n.prop("btn.limpiar")}</i>
                                </a>                              
                            </section>
                        </div>    
                    </div> 
                    <div  class="row">
                        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12" >
                            <section class="lista-facturas-espera">
                                <div id="botones"  each={facturas_espera.data}  onclick={__CargarFacturaEspera}>
                                    <a href="#" class="factura-espera"  title="{cliente !=null?cliente.nombreCompleto:""}">T# {id}</a>
                                </div>         
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
                                            <img  src="{urlImagenBuscador}" width="45px" height="15px" >&nbsp;&nbsp; {$.i18n.prop("btn.consultar")}  
                                        </h3>
                                    </li>
                                    <li onclick = {__PantallaCodigoBarra} id="codificalo" class=""> <h3>
                                        <i class="glyphicon glyphicon-barcode" aria-hidden="true"></i>
                                        <img  src="{urlImagenLector}" width="40px" height="15px">
                                        &nbsp;&nbsp; LECTOR </h3> 
                                    </li>
                                    <li onclick = {__PantallaCategorias} id="navegador" class=""> <h3>
                                        <i class="glyphicon glyphicon-refresh" aria-hidden="true"></i>
                                        <img  src="{urlImagenNavegador}" width="40px" height="15px">
                                        &nbsp;CATEGORIAS</h3>  
                                    </li>
                                </ul>
                            </form>
                        </div>
                    </div>
                </div>    
            </div>
        </div>
        <!--Ventana de los productos-->
                <div   class="col-sx-12 col-sm-7 col-md-7 col-lg-7 " >
                    <!--Seccion de categorias-->
                    <section show= {mostrarCategorias} class="lista-articulos" >
                        <div show= {mostrarCategorias} id="item-categorias"class="product-item"  each ={categorias.data}  onclick={__ArticulosXCategorias}>
                            <img  style = "width:150px;" alt="" class="img-responsive " src="/dist/img/carrito1.png">
                            <a href="#">{descripcion}</a>
                        </div>
                    </section>
                    <!--Seccion de articulos-->
                    <section show= {mostrarArticulosXCategoria} class="lista-articulos" >
                        <div class="product-item"  each ={inventariosXCategoria.data}   onclick={__AgregarProductoDePantalla}>
                            <img  style = "width:150px;" alt="" class="img-responsive " src="/dist/img/carrito3.png">
                            <a href="#">{descripcion}</a>
                        </div>
                    </section>
                    <div onclick = {__BotonAnterior} show= {mostrarNavegador}  class="pull-left btnNavegacion " >
                        <i class="fa fa-arrow-left"></i>Anterior
                    </div>
                    <div onclick = {__BotonSiguiente} show= {mostrarNavegador}  class="pull-right btnNavegacion " >
                        <i class="fa fa-arrow-right"></i>Siguiente
                    </div>
                    <!--Fin Seccion de articulos-->
                    <!--Seccion de codigo de barra-->
                    <section show={mostrarCodigoBarra} class="codigo-barra" >
                        <div class="barra">
                           <input onkeypress = {__addProductToDetail} type="text" class="form-control" id="codigoBarra" autofocus="autofocus" placeholder="Digite codigo Barra...">
                       </div>    
                       
                    </section>
                    <!--Fin Seccion de codigo de barra-->
                            
                </div> 
    </div>       
</div>
        <!--Fin Ventana de los productos-->

  


<!--Modal Cambiar Cantidad-->
<div id='modalCambiarCantidad' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.cantidad")}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                        <div class="form-group has-success">
                            <label >Cantidad:</label>
                            <input  type="number" class="form-control cambiarCantidadArticulo" id="cambiarCantidadArticulo" name = "cambiarCantidadArticulo" autofocus="autofocus">
                        </div>
                    </div>
                </div> 
            </div>
            <div class="modal-footer">
                <button type="button" onclick ="{__recalculacionDelDetalle}" class="btn-green btn-edit pull-right">{$.i18n.prop("btn.aplicar")}</button>
            </div>
        </div>
    </div>
</div>

<!--Fin Cambiar Cantidad-->


<!--Modal Cambiar Descripcion-->
<div id='modalCambiarDescripcion' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.descripcion")}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                        <div class="form-group has-success">
                            <label >Descripcion:</label>
                            <input  type="text" class="form-control cambiarDescripcionArticulo" id="cambiarDescripcionArticulo" name = "cambiarDescripcionArticulo" autofocus="autofocus">
                        </div>
                    </div>
                </div> 
            </div>
            <div class="modal-footer">
                <button type="button" onclick ="{__cambiarDescripcionDetalle}" class="btn-green btn-edit pull-right">{$.i18n.prop("btn.aplicar")}</button>
            </div>
        </div>
    </div>
</div>

<!--Fin Cambiar descripcion-->



<!--Modal Cambiar precio-->
<div id='modalCambiarPrecio' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.precio")}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                        <div class="form-group has-success">
                            <label >Precio:</label>
                            <input  type="number" class="form-control cambiarprecioArticulo" id="cambiarprecioArticulo" name = "cambiarprecioArticulo" autofocus="autofocus">
                        </div>
                    </div>
                </div> 
            </div>
            <div class="modal-footer">
                <button type="button" onclick ="{__cambiarElPrecio}" class="btn-green btn-edit pull-right">{$.i18n.prop("btn.aplicar")}</button>
            </div>
        </div>
    </div>
</div>

<!--Fin Cambiar precio-->


<!--Modal Cambiar Descuento-->
<div id='modalCambiarDescuento' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.descuento")}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                        <div class="form-group has-success">
                            <label >{$.i18n.prop("factura.linea.detalle.descuento")}</label>
                            <input  type="number" class="form-control aplicarDescuento" id="aplicarDescuento" name = "aplicarDescuento" autofocus="autofocus">
                        </div>
                    </div>
                </div> 

            </div>
            <div class="modal-footer">
                <button type="button" onclick ="{__actualizarDescuento}" class="btn-green btn-edit pull-right">{$.i18n.prop("btn.aplicar")}</button>
            </div>
        </div>
    </div>
</div>
<!--Fin Cambiar Descuento-->

<!--Modal mostrar Articulos de la empresa -->
<div id='modalInventario' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("articulo.listar")} </h4>
            </div>
            <div class="modal-body">
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
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->
<!--Modal mostrar Clientes de una sucursal -->
<<div id="modalClientes" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("cliente.lista")}   </h4>
            </div>
            <div class="modal-body">
                <table id="tableListaCliente" class="table responsive display table-striped table-hover nowrap tableListaCliente " cellspacing="0" width="100%">
                   <thead>
                        <th class="table-header">{$.i18n.prop("cliente.cedula")}            </th>
                        <th class="table-header">{$.i18n.prop("cliente.nombreCompleto")}    </th>
                        <th class="table-header">{$.i18n.prop("cliente.correoElectronico")} </th>
                        <th class="table-header">{$.i18n.prop("cliente.descuento")}         </th>
                        <th class="table-header">{$.i18n.prop("cliente.telefono")}          </th>
                        <th class="table-header">{$.i18n.prop("cliente.celular")}           </th>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("cliente.cedula")}           </th>
                            <th>{$.i18n.prop("cliente.nombreCompleto")}   </th>
                            <th>{$.i18n.prop("cliente.correoElectronico")}</th>
                            <th>{$.i18n.prop("cliente.descuento")}        </th>
                            <th>{$.i18n.prop("cliente.telefono")}         </th>
                            <th>{$.i18n.prop("cliente.celular")}          </th>
                            <th>                                          </th>
                        </tr>
                    </tfoot>                    
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->







<!--Modal agregar Nota a la factura -->
<div id='modalNotaNuevo' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
       <div class="modal-content">
            <div class="modal-header with-border titulos-modal-vender" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-users'></i>Agregar Nota a la Factura  </h4>
            </div>
            <div class="modal-body">
                <div show={mostrarErrorClienteNuevo} class="alert alert-warning" >
                    <div each={errorClientes}><strong>{campo}:</strong> {mensaje}</div>
                </div>
                <form id="client-form">
                    <div class="row-form">
                        <div class="span2">Nota:</div>
                        <div class="span4">
                            <textarea rows="5" id="nota_factura" name="nota_factura" cols="60" value="{nota.notaFactura}"></textarea> 
                        </div>
                        <div class="span2">Nota Comanda:</div>
                        <div class="span4">
                            <textarea rows="5" id="nota_comanda" name="nota_comanda" cols="60" value="{nota.notaComanda}"></textarea> 
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left" style="float: left;" data-dismiss="modal">Volver</button>
                <button onclick = {__AgregarNotaALaFactura} type="button" class="btn-green btn-add pull-right" style="float: right;" >Agregar</button>
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
                                                <select onchange= {__formaReferencias} class="form-control tipoDoc" id="tipoDoc" name="tipoDoc"   >
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
                                    <div show = {!mostrarCamposIngresoContado || factura.fechaCredito} class="form-group ">
                                        <label >{$.i18n.prop("factura.fecha.credito")}</label> 
                                        <div  class="form-group input-group date" data-provide="datepicker"  data-date-start-date="0d" data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaCredito" name="fechaCredito" id="fechaCredito" value="{factura.fechaCredito}" >
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>    
                                    <div class="form-group " show = {!mostrarCamposIngresoContado || factura.fechaCredito}>
                                        <label>{$.i18n.prop("factura.plazoCredito")}</label> 
                                        <input type="number" id = "plazoCredito"  name "plazoCredito" class="form-control plazoCredito" value="{factura.plazoCredito}" >
                                    </div>
                                  
                                   

                                </div>
                                <div  class= "col-md-6 col-sx-6 col-sm-6 col-lg-6" >
                                    <div class="form-group has-success">
                                        <label for="pago_transporteL">{$.i18n.prop("factura.resumen.efectivo")} </label> 
                                        <input onkeyup={ __TotalDeEfectivoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control tamanoLetraTotales totalEfectivo " id="totalEfectivo" name="totalEfectivo" value="{factura.totalEfectivo}">
                                    </div>
                                    <div  class="form-group has-success">
                                        <label for="pago_efectivoL">{$.i18n.prop("factura.resumen.tarjeta")} </label> 
                                        <input onkeyup={ __TotalDeTarjetaAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control tamanoLetraTotales totalTarjeta" id="totalTarjeta" name="totalTarjeta"   value="{factura.totalTarjeta}">
                                    </div>
                                    <div  class="form-group has-success">
                                        <label for="pago_tarjetaL">{$.i18n.prop("factura.resumen.banco")} </label> 
                                        <input onkeyup={ __TotalDeTarjeta } onBlur = {__CalculaCambioAEntregarOnblur} onkeypress = {__CalculaCambioAEntregarKeyPress} type="number" step="any" class="form-control totalBanco" id="totalBanco" name = "totalBanco"  value="{factura.totalBanco}">
                                    </div>

                                    
                                </div>
                            </div>
                            <input type="hidden" id='id'                      name='id'                      value="{factura.id}" >
                            <input type="hidden" id='estado'                  name='estado'                      value="{factura.estado}" >
                            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
                            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
                            <input type="hidden" id='subTotal'                name='subTotal'                value="{factura.subTotal}" >
                            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
                            <input type="hidden" id='totalComprobante'        name='totalComprobante'        value="{factura.totalComprobante}" >
                            <input type="hidden" id='totalServGravados'       name='totalServGravados'       value="{factura.totalServGravados}" >
                            <input type="hidden" id='totalServExentos'        name='totalServExentos'        value="{factura.totalServExentos}" >
                            <input type="hidden" id='totalMercanciasGravadas' name='totalMercanciasGravadas' value="{factura.totalMercanciasGravadas}" >
                            <input type="hidden" id='totalMercanciasExentas'  name='totalMercanciasExentas'  value="{factura.totalMercanciasExentas}" >
                            <input type="hidden" id='totalServGravados'       name='totalServGravados'       value="{factura.totalServGravados}" >
                            <input type="hidden" id='totalServExentos'        name='totalServExentos'        value="{factura.totalServExentos}" >
                            <input type="hidden" id='totalGravado'            name='totalGravado'            value="{factura.totalGravado}" >
                            <input type="hidden" id='totalExento'             name='totalExento'             value="{factura.totalExento}" >
                            <input type="hidden" id='totalVenta'              name='totalVenta'              value="{factura.totalVenta}" >
                            <input type="hidden" id='totalDescuentos'          name='totalDescuentos'          value="{factura.totalDescuentos}" >
                            <input type="hidden" id='totalVentaNeta'          name='totalVentaNeta'          value="{factura.totalVentaNeta}" >
                            <input type="hidden" id='totalImpuesto'           name='totalImpuesto'           value="{factura.totalImpuesto}" >
                            <input type="hidden" id='totalEfectivo'           name='totalEfectivo'           value="{factura.totalEfectivo}" >
                            <input type="hidden" id='totalTarjeta'            name='totalTarjeta'            value="{factura.totalTarjeta}" >
                            <input type="hidden" id='totalBanco'              name='totalBanco'              value="{factura.totalBanco}" >
                            <input type="hidden" id='totalCambioPagar'        name='totalCambioPagar'        value="{factura.totalCambioPagar}" >
                            <input type="hidden" id='detalleFactura'          name='detalleFactura'          value="{factura.detalleFactura}" >
                        </form>   
                    </div>
                    <div class="box-footer">
                        <button onclick={_AtrasFacturaFinal} class="btn-dark-gray btn-back pull-left">  {$.i18n.prop("btn.volver")}</button>
                        <button onclick={__AplicarYcrearFactura}  class="btn-green btn-add pull-right"> </i> {$.i18n.prop("btn.aplicar")}</button>
                    </div>
                </div>
                    <!--Ventana de los billetes-->
                    <div class="container">
                        <div class="row">
                            <div   class="col-sx-12 col-sm-12 col-md-12 col-lg-12 " >
                                <!--Seccion de Billetes-->
                                <section  class="lista-articulos" >
                                    <div class="product-item" each={billetes}   onclick={_sumarBilletes}>
                                        <img style = "height:100px;width:250px" alt="" class="img-responsive " src="{imagen}">
                                        <a href="#">{modena} {descripcion}</a>
                                    </div>
                                </section>
                            <!--Fin Seccion de Billetes-->
                            </div> 
                        </div>       
                    </div>
                    <!--Fin Ventana de los billetes-->      
                
            </div>
            <div class="col-md-4 col-sm-4 col-lg-4 col-sx-12 ">
		        <div class="box">
				    <div class="box-header with-border"><h1 class="box-title">Detalles Facturacion</h1></div>
				    <div class="box-body">
				    <!--right sidebar-->
                        <aside class="right-sidebar">
                            <!--Booking details-->
                            <article class="booking-details clearfix">
                                <h3><span id="lblSCS">{$.i18n.prop("factura.resumen.venta")}</span></h3>
                                    <div class="booking-info">
                                        <p style="text-align:right">{$.i18n.prop("factura.resumen.subTotal")} : <span id="lblSubtotal"> {subTotalGeneral.toLocaleString('de-DE')  } </span></p>
                                        <p style="text-align:right">{$.i18n.prop("factura.resumen.descuento")} : <span id="lblSubtotal"> {factura.totalDescuento.toLocaleString('de-DE')} </span></p>
                                        <p style="text-align:right">{$.i18n.prop("factura.resumen.impuesto")}  : <span id="lblSubtotal"> {factura.totalImpuesto.toLocaleString('de-DE')} </span></p>
                                        
                                    </div>
                                    <div class="precioTotalFactura">
                                        <p class="total" style="text-align:right;">{$.i18n.prop("factura.resumen.total")}  : <span id="lblTotal">{factura.totalComprobante.toLocaleString('de-DE')}</span></p>
                                        
                                    </div>
                                    <div class="{claseCambioDinero}" show={mostrarCamposIngresoContado}>
                                        <p class="total" style="text-align:right;">{$.i18n.prop("factura.resumen.cambio")} <span id="lblTotal">{factura.totalCambioPagar.toLocaleString('de-DE')}</span></p>    
                                    </div>
                            </article>
                        </aside>
                    </div><!-- fin box-body-->
				</div><!-- fin box -->

		    </div>

        </div>  
</div>  
<!--Fin Ventana de los billetes-->      





<style type="text/css">
  .fondoEncabezado {
        background: #00539B;
        color: #f9fafc;
    }
    
    .wrap{
        max-width:1100;
        width:90%;
        margin:auto;
    }
    .wrap >h1{
        color: #494B4D;
	font-weight: 400;
	display: flex;
	flex-direction: column;
	text-align: center;
	margin: 15px 0px;
    }
    .wrap > h1:after{
	content: '';
	width: 100%;
	height: 1px;
	background: #C7C7C7;
	margin: 20px 0;
}
    .pantalla-imprimir{
        display: flex;
	    flex-wrap: wrap;
    }
    .botones-imprimir{
        display: flex;
	    flex-direction: column;
	    width: 20%;
    }
     .botones-imprimir .boton-imprimir{
        cursor: pointer;
        padding: 10px;
        margin: 15px;
        border: none;
        color: #fff;
        text-decoration:none;
        font-size: 30px;  
        padding-top:8px !important; 
        padding-bottom:8px !important; 
        margin-bottom: 8px;
        text-align: center !important;
        background-color: #6dca42 !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 5px 0 rgba(0, 0, 0, 0.20);
        border-radius: 30px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
     }


     .zona-impresion{
        width: 80%;
	    display: flex;
	    flex-wrap: wrap;
      
     }
     .zona-impresion .forma-impresion{
	width: 80%;
	margin-left: 1%;
	margin-bottom: 45px;
	box-shadow: 0px 0px 6px 0px rgba(0,0,0,0.70);

	display: flex;
	flex-direction: column;
	align-items: center;
	align-self: flex-start;
    background-color: white !important;
	transition: all .4s;
     
}


    .ticket {
        height: auto;
        width: 377px;
        margin: 0px;
        padding: 0px;
        float: left;
        font-style: normal;
        line-height: normal;
        font-weight: normal;
        font-variant: normal;
        text-transform: none;
        color: #000;
        font-size: 14px;
        font-family: 'Times New Roman';
        
        max-width: 377px;
       
    }
    .ticket > table{
        border-top: 0px solid black;
        border-collapse: collapse;
    }
    .forma-table {
    border-top:1px solid black;
    border-collapse: collapse;
    }
    .ticket > td.producto,th.producto {
    width: 377px;
    max-width: 75px;
    }
    .ticket > td.cantidad,th.cantidad {
    width: 377px;
    
    word-break: break-all;
    }
    .ticket > td.precio,th.precio {
    width: 377px;
    
    word-break: break-all;
    }
    .encabezado {
    text-align: left;
    align-content: left;
    }
    .ticket > img {
    max-width: inherit;
    width: inherit;
    }
    
    @page{
    margin: 0;
    }
    /* manejar las opciones nota , limpiar Venta en espera*/
    .contenedor-opciones{
        padding: 12px 20px 12px 20px;

    }

    .contenedor-opciones {
        width:100%;
        flex-wrap:wrap;
         display:flex;
    }

    .contenedor-opciones .opciones-menu {
        width:30%;
        margin-left:3%;
        display:flex;
        flex-direction:column;
        align-items:center;
        align-self:flex-start;
        font-weight: 400;
        font-size: 14px;
        float: left !important; 
        color : #5eaf38;
    }
    .contenedor-opciones .opciones-limpiar {
        width:30%;
        margin-left:3%;
        display:flex;
        flex-direction:column;
        align-items:center;
        align-self:flex-start;
        font-weight: 400;
        font-size: 14px;
        float: left !important; 
        color : red;
    }
    /* pantalla de los articulos*/
   
    .lista-articulos{
        width:100%;

        display:flex;
        flex-wrap:wrap;
    }
    .lista-articulos .product-item{
        width:22%;
        margin-left:1%;
        margin-bottom:25px;
        box-shadow:0px 0px 6px 0px rgba(0,0,0,0.22);
        display:flex;
        flex-direction:column;
        align-items:center;
        align-self:flex-start;
    }
    .lista-articulos .product-item img{
        width:100%;
    }

    .lista-articulos .product-item a{
        display:block;
        width:100%;
        padding:8px 0;
        background:#2D3E50;
        color:#fff;
        text-align:center;
        text-decoration:none;
    }

    .codigo-barra .barra input {__AgregarProductoDePantalla
        width: 100%;
        padding: 30px 20px;
        margin: 8px 0;
        border: 2px solid 2D3E50;
        box-sizing: border-box;
        color:black;
        text-align:left;
        text-decoration:none;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        font-size:40px;
        
    
    }
    /* Lista de facturas en espera*/
        .lista-facturas-espera{
            width:90%;
            display:flex;
            flex-wrap:wrap;

        }
        .lista-facturas-espera .factura-espera{
            display:block;
            width:90%;
            padding:6px 0;
            margin-bottom:5px;
            margin-left:10px;
            margin-right:10px;
            background:#6dca42 !important;;
            text-align:center;
            text-decoration:none;
            color:#ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            font-style: italic;
        }

        .btnNavegacion{
            cursor: pointer;
            padding: 10px;
            margin: 15px;
            border: none;
            color: #fff;
            text-decoration:none;
            font-size: 25px;  
            padding-top:8px !important; 
            padding-bottom:8px !important; 
            margin-bottom: 8px;
            text-align: center !important;
            background-color: #6dca42 !important;
            box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 5px 0 rgba(0, 0, 0, 0.20);
            border-radius: 10px;

            -webkit-transition: background-color 100ms linear;
            -moz-transition: background-color 100ms linear;
            -o-transition: background-color 100ms linear;
            -ms-transition: background-color 100ms linear;
            transition: background-color 100ms linear;

        } 

         


    /*fin de la lisa de facturas en espera*/

 .vertical-scrollbar
    {
    overflow-x: auto; /*for hiding horizontal scroll bar*/
    overflow-y: auto; /*for vertical scroll bar*/
    white-space: nowrap;
    overflow-y:scroll;
        width:00px;
        height:500px;
    }
    @media (min-width: 992px) {
    .container-scroll > .row {
        overflow-x: auto;
        white-space: nowrap;
    }
    .container-scroll > .row > .col-md-2 {
        display: inline-block;
        float: none;
    }
    }

    .container-scroll > .row {
    margin-top: 40px;
    }
    .container-scroll > .row > .col-md-2 {
    font-weight: bold;
    text-align: center;
    }

    /* The heart of the matter */
    .testimonial-group > .row {
    overflow-x: auto;
    overflow-y: scroll; /* se habilita el scroll vertical */
    white-space: nowrap;
    }
    .testimonial-group > .row > .col-xs-4 {
    display: inline-block;
    float: none;
    }
    .floating-box {
        float: left;
        width: 150px;
        height: 75px;
        margin: 10px;
    
    }



    #cod-item{
        text-align: center;
    }

    #cod-item-descripcion{
        text-align: center;
        margin-left: 10px;
    }

    #cod-item-descripcion strong{
        margin-right: 5px;
        font-weight: 700;
    }

    #cod-container img{
        width: 266px;
        height: 232px;
        border:2px dotted #EBEBEB;/*//68af27*/
    }

    #cod-container #cod-nombre{
        color: #68AF27;
        margin-top: 0px;
        margin-bottom: 0px;
    }

    #cod-container #cod-precio{
        margin-top: 3px;
        color: rgb(41, 41, 173);
        text-align: center;
        display: block;
        background: #5327AF;
        color: white;/*
        -webkit-border-radius: 8px;
        -moz-border-radius: 8px;
        border-radius: 8px;*/
        padding-top: 5px;
        padding-bottom: 5px;
        width: 95%;
    }

    #vitrina{
        display: none;
        padding-bottom: 30px;
    }

    #contenedor-vitrina #cbx-category{
        margin-top: -10px;
        margin-bottom:10px; 
        display: none;
    }

    .vitrina-item{
        border:1px solid #EBEBEB;
        float: left;
        margin-left: 7px;
        margin-top: 8px;
        cursor:pointer; cursor: hand;
        width: 134px;
        height: 175px;
    }

    .vitrina-item:hover{
        -webkit-box-shadow: 1px 0px 5px 0px rgba(117,117,117,0.5);
        -moz-box-shadow: 1px 0px 5px 0px rgba(117,117,117,0.5);
        box-shadow: 1px 0px 5px 0px rgba(117,117,117,0.5);
    }

    #item-nombre{
        font-size: 10px !important;
        color: #ec6400 !important;
    }
    

    .#pie-item{
        margin-top: 0px;
        padding-top: 5px;
        padding-bottom: 5px;
        text-align: center;
        color: white;
        background: rgba(104,175,39,0.9); 
        overflow: hidden;
    }

    #item-nombre,#item-precio{
        float: left;
    }

    #item-nombre{#total-show {
            padding: 0px;
            font-weight: 400;
            background: none!important;
            font-size: 40px;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            padding-top#total-show {
            padding: 0px;
            font-weight: 400;
            background: none!important;
            font-size: 40px;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            padding-top: 0px;
            line-height: 40px;
        }#total-show {
            padding: 0px;
            font-weight: 400;
            background: none!important;
            font-size: 40px;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            padding-top: 0px;
            line-height: 40px;
        }: 0px;
            line-height: 40px;
        }
        width: 53%;
        font-size: 12px;
        padding-left: 7px;
        text-align: left;
       
    }
    #item-precio{
        width: 39%;
        display: block;
        background: #5327AF;
        color: white;
        -webkit-border-radius: 8px;
        -moz-border-radius: 8px;
        border-radius: 8px;
        margin-right: 3px;
        font-size: 13px;
        /*   background: red;*/
    }

    #vitrina{
        overflow: hidden;
    }#total-show {
            padding: 0px;
            font-weight: 400;
            background: none!important;
            font-size: 40px;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            padding-top: 0px;
            line-height: 40px;
        }

    .vitrina-item img{
        width: 170px;
        height: 130px;
    }

    .vitrina-item h5{
        margin: 0;
    }#total-show {
            padding: 0px;
            font-weight: 400;
            background: none!important;
            font-size: 40px;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            padding-top: 0px;
            line-height: 40px;
        }

    #tipo-busqueda{
        list-style: none;
        margin-left: 0px;
        overflow: hidden;
    }

    #tipo-busqueda li{
        height: 65px;
        background: #68AF27;
        width: 33.1%;
        float: left;
        text-align: center;
        color: white;
        border-left: white 1px solid;
        cursor:pointer; cursor: hand;
    }

    #tipo-busqueda li.active{
        background: #316800!important;
    }

    #tipo-busqueda li h3{
        font-family: "Segoe UI", arial, sans-serif;
        font-weight: 400;
    }
    #total-show {
            padding: 0px;
            font-weight: 400;
            background: #total-show {
            padding: 0px;
            font-weight: 400;
            background: none!important;
            font-size: 40px;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            padding-top: 0px;
            line-height: 40px;
        }none!important;
            font-size: 40px;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            padding-top: 0px;
            line-height: 40px;
        }
    #categorias{#total-show {
            padding: 0px;
            font-weight: 400;
            background: none!important;
            font-size: 40px;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            padding-top: 0px;
            line-height: 40px;
        }
        padding-top: 15px;
        padding-bottom: 15px;
        font-size: 14px;
        background: #4C8D13;
        overflow: hidden;
        display: none;
    }

    #nav-categoria{

        list-style: none;
        margin: 0;
        overflow: hidden;
        float: left;
        margin-left: 10px;
        width: 91%;

    }

    #nav-categoria li{
        float: left;
        background: #67AF27;
        margin-right: 10px;
        height: 90px;
        color: white;
        cursor:pointer; cursor: hand;
        text-align: center;
        width: 12%;
    }

    #nav-categoria li:hover{
        background: #83C44A;
    }

    #nav-categoria li img{
        width: 100%;
        height: 70px;
    }

    .btn-control{
        width: 4%;
        height: 100px;
        float: left;
        background: #83C44A;
    }

   
 </style>   
<style type="text/css">
    .precioTotalFactura{
        font-weight:bold;
        font-size:20px;
        color: #0C9C22;
        border-top: 1px solid #DFDCD1;
        padding: 0 0 5px;
        padding: 15px 0 0;
        margin: 10px 0 0;

    }
    .entregarCambioPositivo{
        font-weight:bold;
        font-size:20px;
        color: #0C9C22;
        border-top: 1px solid #DFDCD1;
        padding: 0 0 5px;
        padding: 15px 0 0;
        margin: 10px 0 0;

    }
    .entregarCambioNegativo{
        font-weight:bold;
        font-size:20px;
        color: #ff0000;
        border-top: 1px solid #DFDCD1;
        padding: 0 0 5px;
        padding: 15px 0 0;
        margin: 10px 0 0;

    }
    .total{
        font-weight:bold;
        font-size:20px;

    }

    .booking-details h1 {
        font-size: 1.5em;
        color: #666;
        text-shadow: none;
    }
    .booking-details .booking-info {
        border-top: 1px solid #DFDCD1;
        padding: 15px 0 0;
        margin: 15px 0 0;
        display: inline-block;
        width: 100%;
    }

   

    .titulos-modal-vender{
            position: relative;
            color: #fff;
            box-shadow: 0 1px 0 #fff;
            background: #28cc28;
            -moz-border-radius: 4px 4px 0px 0px;
            -webkit-border-radius: 4px 4px 0px 0px;
            border-radius: 4px 4px 0px 0px;
            padding: 15px 10px;
            text-align: left;
        }
        body {
            overflow-x: hidden;
        }

        .ui-dialog{
            z-index: 10000!important;
        }

        #total-show{
            font-weight: bold;
            background: none!important;
            font-size: 20px;
        }

        #contenedor-lista-clientes{
            display: none;
            position: absolute;
            width: 247px;
        }
        #buscar-cliente{
            width: 217px!important;
        }

        

        #cod-container{
            display: none;
            width: 40%;
            padding: 10px 7px;
            border:1px solid #EBEBEB;
            overflow: hidden;
            color: #757575;
            margin-left: 25%;
        }

        #cod-container:hover{
            background: #F9F9F9;
            cursor:pointer; cursor: hand;
        }

        #cod-item,#cod-item-descripcion{
            overflow: hidden;
        }

        #cod-item{
            text-align: center;
        }

        #cod-item-descripcion{
            text-align: center;
            margin-left: 10px;
        }

        #cod-item-descripcion strong{
            margin-right: 5px;
            font-weight: 700;
        }

        #cod-container img{
            width: 266px;
            height: 232px;
            border:2px dotted #EBEBEB;/*//68af27*/
        }

        #cod-container #cod-nombre{
            color: #68AF27;
            margin-top: 0px;
            margin-bottom: 0px;
        }

        #cod-container #cod-precio{
            margin-top: 3px;
            color: rgb(41, 41, 173);
            text-align: center;
            display: block;
            background: #5327AF;
            color: white;/*
            -webkit-border-radius: 8px;
            -moz-border-radius: 8px;
            border-radius: 8px;*/
            padding-top: 5px;
            padding-bottom: 5px;
            width: 95%;
        }

        

        #item-nombre,#item-precio{
            float: left;
        }

        #item-nombre{
            width: 53%;
            font-size: 12px;
            padding-left: 7px;
            text-align: left;
            /*  background: blue;*/
        }
        #item-precio{
            width: 39%;
            display: block;
            background: #5327AF;
            color: white;
            -webkit-border-radius: 8px;
            -moz-border-radius: 8px;
            border-radius: 8px;
            margin-right: 3px;
            font-size: 13px;
            /*   background: red;*/
        }

        

        #tipo-busqueda{
            list-style: none;
            margin-left: 0px;
            overflow: hidden;
        }

        #tipo-busqueda li{
            height: 65px;
            background: #68AF27;
            width: 33.1%;
            float: left;
            text-align: center;
            color: white;
            border-left: white 1px solid;
            cursor:pointer; cursor: hand;
        }

        #tipo-busqueda li.active{
            background: #316800!important;
        }

        #tipo-busqueda li h3{
            font-family: "Segoe UI", arial, sans-serif;
            font-weight: 400;
        }

        

        .btn-control{
            width: 4%;
            height: 100px;
            float: left;
            background: #83C44A;
        }

    

</style>


<style type="text/css">

        #v2Cont.panel {margin-bottom: 0px !important; border: none !important; box-shadow: none !important;}
        #v2Cont.panel,.body,.wrapper
        {
            margin: 0px;
            padding: 0px;
            background-color: transparent;
        }

        .panel-title{
            padding: 5px;
        }

    
    
        #cod-barras-sep{
            border-color: #dedede !important;
        }
        #cod-container #cod-img{
            margin-bottom: 10px;
        }

        #cod-precio{
            background-color: transparent !important;
            color: rgb(98, 203, 49) !important;
            font-weight: 500;
            font-size: 28px !important;
            padding: 0px !important;
            margin: 0px !important;
            width: 100% !important;
        }
        #cod-item-descripcion{
            margin: 0px !important;
        }

        
        .nombre_producto{
            font-weight: 500 !important;
            color: #000;
        }

    

        #tipo-busqueda h3{
            color: #131212 !important;
        }

        #botones .popover-content{
            padding: 10px;
            margin-bottom: 10px;
            height: 40px;
        }
        #botones .popover-content .btn-warning{
            background-color: #c74646 !important;
            margin-left: 4px;
        }
        #botones .popover-content .btn{
            padding: 3px 6px;
        }

        .funcLista{
            text-decoration: none !important;
            color: #53a52a;
        }
        .funcLista:hover span.textFunc{
            text-decoration: underline;
        }
        .textFunc{
            margin-left: 4px;
            font-weight: 400;
            text-decoration: none !important;
        }

        #faqSearch{
            padding-bottom: 1px;
        }

        #tablaListaProductos td a {        
            font-size: 10px;
            margin: 0px;
            padding-bottom: 2px !important;
        }



        #tablaListaProductos td a {
            color: #C22439;
            background-color: #f4f4f4 !important;
            padding: 3px 5px 0px 5px;
            border-radius: 4px;

            -webkit-transition: color 200ms linear, background-color 200ms linear;
            -moz-transition: color 200ms linear, background-color 200ms linear;
            -o-transition: color 200ms linear, background-color 200ms linear;
            -ms-transition: color 200ms linear, background-color 200ms linear;
            transition: color 200ms linear, background-color 200ms linear;

        }

        #tablaListaProductos td a:hover {
            background-color: #C22439 !important;
            color: #fff  !important;
        }



        .label-success, .badge-success, .green {
            background: #797979 !important;
        }

        .label-success:hover{
            background: #68AF27 !important;
            -webkit-transition: background-color 200ms linear;
            -moz-transition: background-color 200ms linear;
            -o-transition: background-color 200ms linear;
            -ms-transition: background-color 200ms linear;
            transition: background-color 200ms linear;
        }


        #categorias{
            background-color: #fff;
        }    

        .newPanel{
            background-color: #fff;
        }

        .newContNavegacion{
            padding: 5px 0px 5px 0px;
            margin-bottom: 5px !important;
        }

        #nav-categoria li{
            background-color: #f6f6f6 !important;
            color: #555;
        }

        #next,#next-triangulo{ background-color: #eee !important; }    
        #next-triangulo{ border-color: transparent transparent transparent #ccc; }    

        #next:hover,#next:hover #next-triangulo{ background-color: #ddd !important; }
        #next:hover #next-triangulo{ border-color: transparent transparent transparent #bbb; }

        #nav-categoria li:hover{
            background-color: #eee !important;
        }    

        .newTexto{
            color: #555 !important;
            height: 25px !important;
        }

        .block .head.green *{
            color: #555 !important;
            font-size: 14px;
        }    

        
        #buscalo,#codificalo,#navegador{
            background-color: transparent !important;
            height: auto !important;
            border-left: transparent 0px solid !important;        
        }    

        #tipo-busqueda li.active {
            background-color: transparent !important;
        }
        #tipo-busqueda li.active h3{
            color: #66B12F !important;
        }    

        #codificalo{
            border-left: rgba(0,0,0,0.1) 1px solid !important;
            border-right: rgba(0,0,0,0.1) 1px solid !important;
        }

        #tipo-busqueda{
            height: auto !important;
            margin: 0px !important;
        }
        #tipo-busqueda li {
            height: 30px !important;
            padding-top: 6px;
        }
        #tipo-busqueda li h3 {
            margin: 0px !important;
            padding: 0px !important;
            font-size: 16px !important;
            transition: color 0.1s linear !important;
        }
        #tipo-busqueda li h3:hover {
            color: #66B12F !important;
        }


        #tipo-busqueda img{
            display: none;
        }

        .text-info {
            color: #68AF27;
        }    

            

        .head.green.well tr td:first-child span{
            font-size: 40px !important;
            font-weight: bold !important;
        }
        .site-navbar {
            /*display: none !important;*/
        }
        .navbar .btn, .navbar .btn-group {
            margin-top: 0px;
        }


        .btn-nuevaFactura {
            border-color: #008CBA;
            color: #008CBA !important;
            border-radius: 8px;
            
        }
        .btn-nuevaFactura:hover {
           background-color: #008CBA;
            color: white !important;
        }

        .btn_eliminar_detalle {
            border-color: #bc4044;
            background: #bc4044 !important;
            margin-bottom: 0px !important;
            color: #fff !important;
        }

        #pagar{
            color:#fff;
            background-color: #006699 !important;
            float: none !important;

        }


        #cancelarVenta{
            color: #c5272d !important;
        }

        .clearBoth{
            clear: both;
        }

        .newPanel{
            margin-bottom: 10px !important;
        }

        .textShadow{
            text-shadow: 0px 0px 1px #000000;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
        }
        #pagarTitulo{
            font-weight: 600 !important;
            font-size: 30px !important;
            font-family: Roboto,sans-serif !important;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            font-style: italic;
            text-align: left;
            padding-left: 20px;
            line-height: 30px;
        }

        #pagarTable,#pagarTableInfo{
            border-collapse: separate;
        }
        #pagarTableInfo{
            background-color: #f2f2f2;
            color: #000;
            text-align: center;
        }

        #bordeBevelLeft{
            border-top: none;
            border-bottom: none;
            border-left: none;
            border-right: 1px solid #DBDBDB !important;
        }
        #bordeBevelRight{
            border-top: none;
            border-bottom: none;
            border-left: 1px solid #FFFFFF !important;
            border-right: none;
        }
        #bordeBevelTop{
            border-top: 1px solid #FFFFFF !important;   

        }
        #bordeBevelBottom{
            border-bottom: 1px solid #DBDBDB !important;
        }    
        #total-show {
            padding: 0px;
            font-weight: 400;
            background: none!important;
            font-size: 40px;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            padding-top: 0px;
            line-height: 40px;
        }

        #total_show_peso{
            font-size: 28px !important;
            color: #ffffff !important;
            text-shadow: 0px 0px 1px #ffffff;
            line-height: 10px;
        }
        
        #btnGrandePagar,#btnGrandePagar2{
            cursor: pointer;
            padding: 0px;
            margin: 10px;
            border: none;

            text-align: center !important;
            background-color: #6dca42 !important;
            box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
            border-radius: 5px;

            -webkit-transition: background-color 100ms linear;
            -moz-transition: background-color 100ms linear;
            -o-transition: background-color 100ms linear;
            -ms-transition: background-color 100ms linear;
            transition: background-color 100ms linear;

        } 


        #pagarInfo,#iva-total,#subtotal,#sigPeso{
            font-weight: 100 !important;
            font-size: 14px !important;
        }
        #pagarInfo{
            font-size: 12px !important;
        }
        #sigPeso{
            margin-left: 8px;
            margin-right: 2px;
        }
        #sigPeso,#iva-total,#subtotal{
            font-weight: 500 !important;
        }

        #listaProductos{
            background-color: #f9f9f9;
        }

        tr.nothing td{
            text-align: center;
            padding-top: 100px !important;
            height:100px !important;
            color: #aaa !important;
        }

        #tablaListaProductos {   
            border-collapse: separate !important;
            overflow-x: hidden !important;
        }  

        #tablaListaProductos td{
            height: 30px;
            border-bottom: 1px solid #e2e2e2 !important;
            border-top: 1px solid #fff !important;
            background-color: #f4f4f4;
            padding-left: 12px;
        }



        #tablaListaProductos .text-info {
            color: #232323;
            font-size: 11px;
            font-weight: 400;
        }
        #tablaListaProductos .precio-calc {
            color: #232323;
        }    

        #tablaListaProductos tr.nothing td{
            border-bottom: none !important;
            background-color: transparent;
            border: none;
        }

        #tablaListaProductos tr td:first-child {
            padding-left: 20px;
        }

        #tablaListaProductos tr td:last-child {
            padding-top: 3px;
            padding-left: 0px;
        }
        #tablaListaProductos tr td:last-child {
            width: 30px;
        }
        #tablaListaProductos .title-detalle {
            word-break: break-all !important;
        }


        .input-group-addon{
            padding: 5px 12px 5px 12px;
        }

        .input-group-addon.btnClientes{
            color: #66b12f;
            cursor: pointer;
        }

        .input-group input{
            background-color: #f9fcfd !important;
            height: 30px;
            border-radius: 0px 3px 3px 0px;
            border: 1px solid #eaeaea;
            z-index: -1;
        }
        .input-group .wb-user{
            font-size:14px !important;
        }

        #formLeft{
            border-right: 1px solid #eaeaea;
        }
        #botones div.btn{
            border: none;
            background: #545454 !important;
            width: inherit !important;
            height: inherit !important;
            padding: 4px 10px !important;
            font-size: 12px;

            -webkit-transition: background-color 200ms linear;
            -moz-transition: background-color 200ms linear;
            -o-transition: background-color 200ms linear;
            -ms-transition: background-color 200ms linear;
            transition: background-color 200ms linear;
        }
        #botones div.btn:active,
        #botones div.btn:visited,
        #botones div.btn:focus{
            background: #545454 !important;        
        } 
        #botones div.btn:hover{
            background: #5E8C47 !important;        
        }    
        #botones table{
            display:none;
        }
        .btn.funcLista{
            width: 80%;
            text-decoration: none;
            background: #6dca42 !important;
            margin-top: 5px;
            padding: 2px;
        }
        #listadoProdcutos{
            color: #555;
            text-align: center;
            padding: 6px;
            font-weight: 400;
            font-size: 18px;
        }
        
</style>

<script>
    var self = this;
    // Detalle de la factura es una coleccion de articulos
    self.mostarParaCrearNuevaVentas = true
    self.mostrarCodigoBarra    = true
    self.mostrarCodigoBarra          = true;
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.comboEstados          = []
    self.comboCondicionPagos        = []
    self.comboTipoDocumentos   = []
    self.factura                = {
        id:0,
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
            id:0,
            nombreCompleto:""
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        }

    }                   
    self.item                  = null;
    self.articulo              = null;
    self.articulos             = {data:[]}
    self.clientes              = {data:[]}
    self.detalleFactura        = {data:[]}
    self.cliente               = {}
    self.vendedor              = {
        id:0,
        nombreCompleto:""
    };
    self.facturas_espera       = {data:[]}  
    self.informacion_tabla             = []
    self.informacion_tabla_articulo    = []
    self.informacion_tabla_clientes    = []
    self.idiomaDataTable               = {}
    self.mostrarFormularioPago         = false
    self.mostarParaCrearNuevaFactura   = true
    self.mostrarCamposIngresoContado   = true
    self.mostrarReferencias            = false 
    self.subTotalGeneral               = 0
    self.todasProvincias               = {data:[]}
    self.todosCantones                 = {data:[]}
    self.todosDistritos                = {data:[]}
    self.todosBarrios                  = {data:[]}
    self.cantones                      = []
    self.distritos                     = []
    self.barrios                       = []
    self.parametrosPaginacion = {
        id:null,
        paginaActual:0,
        cantidadPorPagina:10,
        total:0

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

    self.on('mount',function(){
         
         
        $("#formularioFactura").validate(reglasDeValidacionFactura());
         
        __informacionData()
        __informacionData_vendedores()
        __InicializarTabla('.tableListaCliente')
        __InicializarTabla('.tableListaInventario')
        __InicializarTabla('.tableListaVendedor')
        agregarInputsCombos_Articulo()
        __ListaFacturasEnEspera()
       // setInterval(function() {
            // triggering the "ready" event will resolve the promise
        //    __ListaFacturasEnEspera()
        //}.bind(this), 10000)
          
        __comboCondicionPago()
        __ComboTipoDocumentos()
        __ListaDeClientes()
       __ListaDeVendedores()
       __Teclas()
       __TipoCambio()
       __cargaUbicacion()
       __comboCondicionPagoRef()

        cargaBilletes()
         window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
     
    })

 /**
* agregar producto desde la pantalla de articulos
**/

__AgregarProductoDePantalla(e){
    
    var item =  e.item
    self.articulo = item;
    self.update()
    __buscarcodigo(self.articulo.codigo,1);
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
    
    if(self.categoria.id == 0){//cuando esta usando la pantalla de categorias
        self.categorias.pagination.current_page = self.categorias.pagination.current_page - 1
        self.categorias.pagination.current_page = self.categorias.pagination.current_page > 1?self.categorias.pagination.current_page:1;
        self.parametrosPaginacion.paginaActual = self.parametrosPaginacion.paginaActual > 1?self.parametrosPaginacion.paginaActual - 12:0 
        self.update()
        __ListaCategorias()

    }else{// cuando esta usando la pantalla de articulos
        self.inventariosXCategoria.pagination.current_page = self.inventariosXCategoria.pagination.current_page - 1
        self.inventariosXCategoria.pagination.current_page = self.inventariosXCategoria.pagination.current_page > 1?self.inventariosXCategoria.pagination.current_page:1;
         self.parametrosPaginacion.paginaActual = self.parametrosPaginacion.paginaActual > 1?self.parametrosPaginacion.paginaActual - 12:0 


        self.update()
        __ListaArticulosXCategorias()

    }
            
}

/**
*  boton siguiente de la pantalla de categorias or articulos
**/    
__BotonSiguiente(){
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
               self.update()
            }else{
                sweetAlert("",$.i18n.prop("articulo.por.categoria"), "info");
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
*  Mostrar pantalla de codigo de barra
**/
__PantallaCategorias(){
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
   
    self.update()
    $('#codigoBarra').focus()

}

__LimpiarFormulario(){
    $(".plazoCredito").val(null)   
    $(".fechaCredito").val(null)   
     $(".totalEfectivo").val(null)   
    $(".totalTarjeta").val(null)   
    $(".totalBanco").val(null)   

    
    $(".nota").val(null)   
    $(".direccion").val(null)   
    $(".referenciaFechaEmision").val(null)
    $('.referenciaNumero').val(null)
    $('.referenciaRazon').val(null)
    $('.referenciaTipoDoc').prop("selectedIndex", 0);
    $('.referenciaCodigo').prop("selectedIndex", 0);
    $('.condicionVenta').prop("selectedIndex", 0);
    $('.tipoDoc').prop("selectedIndex", 0);
    self.cliente               = {}
    self.vendedor              = {
        id:0,
        nombreCompleto:""
    };
    self.mostrarCamposIngresoContado = true
    self.todosBarrios                  = {data:[]}
    self.cantones                      = []
    self.distritos                     = []
    self.barrios                       = []

    self.update()
     
}

__formaReferencias(e){
    if($('#tipoDoc').val() !="01" && $('#tipoDoc').val() !="04"){
       self.mostrarReferencias            = true
       self.update()  
    }else{
        self.mostrarReferencias            = false
        self.update()
        $(".referenciaFechaEmision").val(null)
        $('.referenciaNumero').val(null)
        $('.referenciaRazon').val(null)
        $('.referenciaTipoDoc').prop("selectedIndex", 0);
        $('.referenciaCodigo').prop("selectedIndex", 0);
    }
    
    
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



/**
Consultar el consecutivo que se hace referencia
**/
__consultarConsecutivo(e){
    if (e.keyCode != 13) {
        return;
    } 
    __referenciaConsecutivo(e.currentTarget.value);
}

/**
*  Informacion del consecutivo de la factura para las notas de creditos y debito
**/
function __referenciaConsecutivo(consecutivo){
   
    $(".referenciaFechaEmision").val(null)
    $('.referenciaTipoDoc').prop("selectedIndex", 0);
    $('.referenciaCodigo').prop("selectedIndex", 0);
     
   $.ajax({
        url: "ConsultarConsecutivoAjax",
        datatype: "json",
        data: {consecutivo:consecutivo},
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.factura.referenciaFechaEmision = modeloTabla.fechaEmision
                       self.factura.referenciaTipoDoc      = modeloTabla.referenciaTipoDoc
                      
                       self.update()
                        
                        $("referenciaFechaEmision").val(modeloTabla.fechaEmision)
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
*  Consulta la provicias , cantonces y distritos en uno solo 
**/
function __cargaUbicacion(){
      self.todasProvincias  = {data:[]}
      self.update()
     $.ajax({
         url: "ListarProvinciasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.todasProvincias.data =  result.aaData
                self.update();
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
   self.todosCantones  = {data:[]}
    self.update()
     $.ajax({
         url: "ListarCantonesTodosAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.todosCantones.data =  result.aaData
                self.update()
                 
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
    self.todosDistritos  = {data:[]}
    self.update()
     $.ajax({
         url: "ListarDistritosTodosAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.todosDistritos.data =  result.aaData
                self.update()
               
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
    self.todosBarrios  = {data:[]}
    self.update()
     $.ajax({
         url: "ListarDistritosTodosAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.todosBarrios.data =  result.aaData
                self.update()
               
            

            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
   

}



function _provincias(){
    self.provincias = self.todasProvincias.data
    self.update()
}

/**
*  Cantones
**/
function _ConsultarCantonesByProvincias(idProvincia){
    let lista     = self.todosCantones.data;
    self.cantones  = []
    self.update()
    self.cantones = jsonPath(lista,"$[?(@.codigo_provincia=='"+idProvincia+"')]");
    self.update();

}

/**
* buscar los distritos por canton y provincia
**/

function _consultarDistritosByCantoAndProvincia(idProvincia,idCanton){
    let lista     = self.todosDistritos.data;
    self.distritos  = []
    self.update()
    self.distritos = jsonPath(lista,"$[?(@.codigoProvincia=='"+idProvincia+"' && @.codigoCanton=='"+idCanton+"')]");
    self.update();

}

function _consultarBarriosByCantoAndProvinciaAndDistritos(idProvincia,idCanton,idDistritos){
    let lista     = self.todosBarrios.data;
    self.barrios  = []
    self.update()
    self.barrios = jsonPath(lista,"$[?(@.codigoProvincia=='"+idProvincia+"' && @.codigoCanton=='"+idCanton+"' && @.codigoDistrito=='"+idDistritos+"')]");
    self.update();

}
/**
* Aplicar el descuento
**/
__CambiarDescuento(e){
    self.item = e.item; 
    self.update()
    $('#modalCambiarDescuento').modal('show')      
    $('#aplicarDescuento').focus()
}

/**
*Cambiar Cantidad del Articulo
**/
__CambiarCantidad(e){
   self.item = e.item; 
   self.update()
   $( "#cambiarCantidadArticulo" ).focus()
   $( "#cambiarCantidadArticulo" ).val(self.item.cantidad)
   $('#modalCambiarCantidad').modal('show')      
}

/**
*Cambiar descripcion
**/
__CambiarDescripcion(e){
   self.item = e.item; 
   self.update()
   $( "#cambiarDescripcionArticulo" ).focus()
   $( "#cambiarDescripcionArticulo" ).val(self.item.descripcion)
   $('#modalCambiarDescripcion').modal('show')      
}



/**
*Cambiar precio del producto
**/
__CambiarPrecio(e){
   self.item = e.item; 
   self.update()
   $( "#cambiarprecioArticulo" ).focus()
   $( "#cambiarprecioArticulo" ).val( e.item.precioUnitario)
   $('#modalCambiarPrecio').modal('show')      
}

/**
* Tipo Cambio de moneda
**/
function __TipoCambio(){
    self.tipoCambio = {}
    self.update()
    $.ajax({
        url: "MostrarTipoCambioActivoAjax.do",
        datatype: "json",
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.tipoCambio = modeloTabla
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
    riot.mount('ptv-imprimir',{factura:factura});
}
/**
*  Obtiene el valor de lo digitado en el campo de efectivo
**/
__TotalDeEfectivoAPagar(e){
    self.factura.totalEfectivo = __valorNumerico(e.target.value) 
    self.update()
}
/**
*  Obtiene el valor de lo digitado en el campo de Tarjeta
**/
__TotalDeTarjetaAPagar(e){
    self.factura.totalTarjeta = __valorNumerico(e.target.value) 
    self.update()
}
/**
*  Obtiene el valor de lo digitado en el campo de Banco
**/
__TotalDeBancoAPagar(e){
    self.factura.totalBanco = __valorNumerico(e.target.value) 
    self.update()
}
/**
*   Calculo del cambio entregar en el evento onblur
**/
__CalculaCambioAEntregarOnblur(e){
    var sumaMontosEntregadosParaCambios =__valorNumerico(self.factura.totalTarjeta)
    sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco) 
    sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo) 
    //Si no ingresado montos no realiza las operaciones de calculos
    if(sumaMontosEntregadosParaCambios == 0){
        return
    }
    self.factura.totalCambioPagar = 0
    self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios > self.factura.totalComprobante ? sumaMontosEntregadosParaCambios - self.factura.totalComprobante:sumaMontosEntregadosParaCambios - self.factura.totalComprobante    
    self.update()
}
/**
*   Calculo del cambio entregar en el evento keyPress
**/
__CalculaCambioAEntregarKeyPress(e){
    var sumaMontosEntregadosParaCambios =0
    if (e.keyCode == 13) {
        sumaMontosEntregadosParaCambios  = __valorNumerico(self.factura.totalTarjeta)
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco) 
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo) 
        if(sumaMontosEntregadosParaCambios == 0){
            return
        }
        self.factura.totalCambioPagar = 0
        self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios > self.factura.totalComprobante ? sumaMontosEntregadosParaCambios - self.factura.totalComprobante:sumaMontosEntregadosParaCambios - self.factura.totalComprobante    
        self.update()
    }
}
/**
 * Listar codigos  llamado del modal para presentar los articulos
 **/   
 __ListaDecodigos(){
     __ListaDeArticulosPorEmpresa();
 }
/**
*  Buscar la Factura Pendiente en espera
**/
__CargarFacturaEspera(e){
   __FacturaEnEspera(e.item)
}

/**
*  Crear la factura temporal o espera
**/
__CrearFacturaTemporal(){
   
   aplicarFactura(1)   
}
/**
** Se aplica o se crea una Factura cargada en la pantalla
**/
__AplicarYcrearFactura(){
 aplicarFactura(2)
}

/**
* Aplicar la factura
**/
function aplicarFactura(estado){
    if(self.detail.length == 0 ){
        mensajeError($.i18n.prop("factura.alert.sin.detalles"))
        return
    }
    if($('#condicionVenta').val() == "02"  ){
        if($('#fechaCredito').val() == null || $('#fechaCredito').val() == 0){
           mensajeError($.i18n.prop("factura.alert.fechaCredito"))
            return
        }
        if($('#plazoCredito').val() < 0 || $('#plazoCredito').val() == null || $('#plazoCredito').val() == 0){
           mensajeError($.i18n.prop("factura.alert.plazoCredito"))
            return
        }
    }else{
        // Si no es credito y el estado no es pendiente se debe verificar si ingresaron el monto a pagar
        if(estado !=1){
            if(self.factura.totalTarjeta == 0 && self.factura.totalBanco == 0 && self.factura.totalEfectivo == 0){
                mensajeError($.i18n.prop("error.factura.monto.ingresado"))
                return
            }
            var montoEntregado = self.factura.totalTarjeta + self.factura.totalBanco + self.factura.totalEfectivo
            montoEntregado = __valorNumerico(montoEntregado)
            if(self.factura.totalVentaNeta > montoEntregado  ){
                mensajeError($.i18n.prop("error.factura.monto.ingresado.es.menor.ala.venta"))
                return
            }
            //Si el cliente esta pagando con tajeta, banco debe ser igual a la venta
            if(self.factura.totalTarjeta != 0 || self.factura.totalBanco !=0){
                if(self.factura.totalVentaNeta != montoEntregado  ){
                    mensajeError($.i18n.prop("error.factura.monto.tarjeta.banco.igual.venta"))
                return
                    
                }
            }
            
        }
    } 
if ($("#formularioFactura").valid()) {
        swal({
           title: '',
           text: $.i18n.prop("factura.alert.crear"),
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
               crearFactura(estado)  
              
            }
        });
    }


}


/**
* Limpiar Pantalla
**/
__Limpiar(){

    __Init()
}
/**
*  Inicializar las variables de trabajos
**/
function __Init(){
    
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.comboEstados          = []
    self.comboCondicionPagos        = []
    self.comboTipoDocumentos   = []
    self.facturas_espera       = {data:[]}  
    self.factura                = {
        id:0,
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
        totalCambioPagar:0,
	    codigoMoneda:"",
	    estado:0,
	    cliente:{
            id:0,
            nombreCompleto:"",
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        }
    }                            
    self.item                  = null;
    self.articulo              = null;
    self.articulos             = {data:[]}
    self.clientes              = {data:[]}
    self.detalleFactura        ={data:[]}
    self.cliente               = {};
    self.vendedor              = {
        id:0,
        nombreCompleto:""
    }
    self.tipoCambio                    = {}
    self.informacion_tabla             = []
    self.informacion_tabla_articulo    = []
    self.informacion_tabla_clientes    = []
    self.idiomaDataTable               = {}
    self.mostrarFormularioPago         = false
    self.mostarParaCrearNuevaFactura    = true
    self.mostrarCamposIngresoContado   = true;

    self.mostarParaCrearNuevaVentas = true
    self.mostrarCodigoBarra    = true
    self.mostrarCodigoBarra          = true;

    self.mostrarFormularioPago         = false
    self.mostarParaCrearNuevaFactura   = true
    self.mostrarCamposIngresoContado   = true
    self.mostrarReferencias            = false 

    self.parametrosPaginacion = {
            id:null,
            paginaActual:0,
            cantidadPorPagina:10,
            total:0

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

    self.update();

    $(".referenciaNumero").val(null)
    $(".referenciaFechaEmision").val(null)
    $('.referenciaTipoDoc').prop("selectedIndex", 0);
    $('.referenciaCodigo').prop("selectedIndex", 0);
    $('#condicionVenta').prop("selectedIndex", 0);
    $('#tipoDoc').prop("selectedIndex", 0);
    $('#estado').prop("selectedIndex", 0);
    $('#provincia').prop("selectedIndex", 0);
    $('#canton').prop("selectedIndex", 0);
    $('#distrito').prop("selectedIndex", 0);
    $('#barrio').prop("selectedIndex", 0);
     $(".totalBanco").val(null)   
    $(".totalTarjeta").val(null)   
    $(".totalEfectivo").val(null)   


    $("#plazoCredito").val(null)
    $("#nota").val(null)
    $("#fechaCredito").val(null)
    $("#cambiarCantidadArticulo").val(null)
    $("#aplicarDescuento").val(null)

    
    // Tipo de Pagos
     __comboCondicionPago()
     __comboCondicionPagoRef()
     //Tipos de Documentos
      __ComboTipoDocumentos()
      //Estados
      __ComboEstados()
     __ListaFacturasEnEspera()
    
}

/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(factura){
     __Init()
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
                       self.factura.totalEfectivo = 0
                       self.factura.totalBanco = 0
                       self.factura.totalTarjeta = 0
                       self.factura.totalCambioPagar = 0
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
*  Cargar detalles Factura en espera
**/
function cargarDetallesFacturaEnEspera(){
    self.detail = [];
    self.update()
    self.factura.detalles.forEach(function(e){
        self.detail.push({
            codigo          : e.codigo,
            tipoImpuesto    : e.tipoImpuesto,
            descripcion     : e.descripcion,
            cantidad        : redondearDecimales(parseFloat(e.cantidad),5),
            precioUnitario  : redondearDecimales(parseFloat(e.precioUnitario),5),
            impuesto        : redondearDecimales(parseFloat(e.impuesto),5),
            montoImpuesto   : redondearDecimales(parseFloat(e.montoImpuesto),5),
            montoDescuento  : redondearDecimales(parseFloat(e.montoDescuento),5),
            porcentajeDesc  : redondearDecimales(parseFloat(e.porcentajeDesc),5),
            subTotal        : redondearDecimales(parseFloat(e.subTotal),5),
            montoTotalLinea : redondearDecimales(parseFloat(e.montoTotalLinea),5),
            montoTotal      : redondearDecimales(parseFloat(e.montoTotal),5)
        });
        self.update()
    })
    self.update()
     __calculate(); 
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
function crearFactura(estado){
    self.detalleFactura.data =self.detail
    self.update() 
    var fechaCreditoTemporal =condicionVenta.value == "02"?fechaCredito.value:new Date() 
    var fechaReferencia =$('#referenciaFechaEmision').val() !=null?referenciaFechaEmision.value:new Date() 
     var JSONDetalles = JSON.stringify( self.detalleFactura );
    
    self.factura.id = self.factura.id
    self.factura.condicionVenta = $('#condicionVenta').val()
    self.factura.fechaCredito =fechaCreditoTemporal.toString()
    self.factura.referenciaFechaEmision =fechaReferencia
    self.factura.totalEfectivo =$('#totalEfectivo').val()
    self.factura.totalTarjeta = redondearDecimales(__valorNumerico($('#totalTarjeta').val())) 
    self.factura.totalBanco = redondearDecimales(__valorNumerico($('#totalBanco').val()))
    self.factura.detalleFactura =JSONDetalles
    self.factura.estado = estado
    self.update();
    
    var formulario = $("#formularioFactura").serialize();
     
                    
    $.ajax({
        type : "POST",
        dataType : "json",
        data : formulario,
        url : "CrearFacturaAjax",
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                    mensajeError(data.message)
                }
            } else {
               	serverMessageJsonClase(data);
                evaluarFactura(data)
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

/**
*Si fue facturada o tiquete
**/
function evaluarFactura(data){
   if (data.message != null && data.message.length > 0) {
        $.each(data.listaObjetos, function( index, modeloTabla ) {
            self.facturaImprimir   = modeloTabla
            self.update()
            if(self.facturaImprimir.estado == 2){
                __Init()
                //Envia a la pantalla de impresion
                __ListaFacturasEnEspera()
                self.facturaImprimir   = modeloTabla
                self.update()
            
                 riot.mount('ptv-imprimir',{factura:self.facturaImprimir});
                 
            }else{
                swal({
	                title: '',
	                text: data.message,
	                type: 'success',
	                showCancelButton: false,
	                confirmButtonText: $.i18n.prop("btn.aceptar"),
                })
                __Init()
                __ListaFacturasEnEspera()
            }
        });
    }


}
/**
*  Lista de las facturas pendientes por el usuario
**/
function __ListaFacturasEnEspera(){
     self.facturas_espera       = {data:[]}  
     self.update()
    $.ajax({
        url: 'ListarFacturasEsperaActivasAjax',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
               self.facturas_espera.data =  result.aaData;  
               self.update(); 
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });    
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
   self.mostrarFormularioPago = false
   self.mostarParaCrearNuevaVentas = true;
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
}
/**
*   funcion para grabar la Factura en el back end
**/
__MostrarFormularioDePago(){
    self.mostarParaCrearNuevaVentas = false
    mostrarPAgo()
}


function mostrarPAgo(){
     //No hay detalles registrados en la Factura
    if(self.detail.length == 0 ){
        swal("Verificar","No hay detalles en la factura ", "info")
        return
    }
    
    $('#totalEfectivo').val(null)
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    getSubTotalGeneral()
    self.mostarParaCrearNuevaVentas = false
    self.factura.totalCambioPagar =0
    self.mostarParaCrearNuevaFactura = false
    self.mostrarFormularioPago = true
    self.update()
    $('#totalEfectivo').focus()
    self.factura.cambioMoneda = self.factura.totalVentaNeta / self.tipoCambio.total
    self.update()

}
/** 
*
*Agregar codigos al detalle de la Factura
*
*/
__addProductToDetail(e){
    if (e.keyCode != 13) {
        return;
    } 
    var codigo = e.currentTarget.value
    var codigoActual = ""
    var cantidadAct =""
    var existe = false
    for(i=0; i<codigo.length; i++){
       if(existe == false){
          existe = codigo.charAt(i) == "*"?true : false  
          if(codigo.charAt(i) !="*"){
              codigoActual = codigoActual + codigo.charAt(i)  

          }
       }else{
           cantidadAct = cantidadAct + codigo.charAt(i)
       }
        console.log("pos=", i, "valor=", codigo.charAt(i));
    }

    __buscarcodigo(codigoActual,__valorNumerico(cantidadAct));
    $('#codigoBarra').val(null)
    $('#codigoBarra').focus()
}
/**
* Buscar codigo
**/
__agregarArticuloBotonAgregar(){
   __buscarcodigo($( "#codigo" ).val(),1);
}
/**
* mostrar la lista de articulos de la empresa
**/
function __ListaDeArticulosPorEmpresa(){
    $.ajax({
        url: 'ListarArticuloAjax.do',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                _informacionData_Articulo()
                self.articulos.data           = result.aaData
                self.update()
                loadListar(".tableListarArticulos",idioma_espanol,self.informacion_tabla_articulo,self.articulos.data)
                agregarInputsCombos_Articulo()
                __agregarArticulos()
                ActivarEventoFiltro(".tableListarArticulos")
                $('#modalInventario').modal('show')    
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
/**
*  Muestra la lista de clientes
**/
_EscogerClientes(){
    $('#modalClientes').modal('show')  
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
    self.articulo = null;
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
                        //Articulo no puede agregarse si no hay en el inventario
                        if(modeloTabla.contable == "Si"){
                            if(modeloTabla.cantidad < 0 || modeloTabla.cantidad == 0 ){
                                mensajeError($.i18n.prop("error.articulo.sin.existencia.en.inventario"))
                                return
                            }
                            if(modeloTabla.cantidad < cantidad ){
                                mensajeError($.i18n.prop("error.articulo.tiene.menor.existencia.en.inventario.a.la.venta"))
                                return
                            }

                        }
                        self.articulo  = modeloTabla
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
        encontrado = true;
    }else{//Se busca el articulo si existe se incrementa la cantidad
        for (var count = 0; count < self.detail.length; count++) {
            if (self.detail[count].codigo == self.articulo.codigo ){
               self.item          = self.detail[count];
               self.item.cantidad = self.item.cantidad + parseFloat(cantidad)
               self.update();
               ActualizarLineaDEtalle()
               self.detail[count] = self.item;
               encontrado = true;
              self.update();
            }
        }
    
    }
    // si no existe se agrega como un codigo nuevo
    if(encontrado == false){ // add elemen
      __nuevoArticuloAlDetalle(cantidad);
    }
    __calculate(); 
}
/**
* eliminar un detalle factura
**/
__removeProductFromDetail(e) {
    var item = e.item;
    index = this.detail.indexOf(item);
    this.detail.splice(index, 1);
    var cont = 0 ;
    self.detail.forEach(function(elemen){
            elemen.numeroLinea = cont + 1
            cont = elemen.numeroLinea
        }
    )
    self.update()
     __calculate();
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
    var precioUnitario  = getPrecioUnitario(self.articulo.precioPublico,self.articulo.impuesto)
    
    var montoTotal      = getMontoTotal(precioUnitario,cantidad)
    var montoDescuento  = 0
    var naturalezaDescuento = ""
    var subTotal        = montoTotal
    var montoImpuesto   = _calcularImpuesto(subTotal,parseFloat(self.articulo.impuesto) ==null?0:parseFloat(self.articulo.impuesto))
    var montoTotalLinea = subTotal + montoImpuesto 
    self.detail.push({
       numeroLinea     : 1,
       tipoImpuesto    : self.articulo.tipoImpuesto,
       iva             : parseFloat(self.articulo.impuesto),
       codigo          : self.articulo.codigo,
       descripcion     : self.articulo.descripcion,
       cantidad        : parseFloat(cantidad),
       precioUnitario  : precioUnitario,
       impuesto        : parseFloat(self.articulo.impuesto),
       montoImpuesto   : montoImpuesto,
       montoDescuento  : 0,
       porcentajeDesc  : 0,
       subTotal        : subTotal,
       montoTotalLinea : montoTotalLinea,
       montoTotal      :montoTotal
    });
    var cont = 0;
    self.detail.forEach(function(elemen){
          elemen.numeroLinea = cont + 1
          cont = elemen.numeroLinea
        }
    )
    self.update()
}

function getMontoTotal(precioUnitario,cantidad){
    var resultado = parseFloat(precioUnitario) * parseFloat(cantidad)
    return redondearDecimales(resultado ,5);
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


   return redondearDecimales(resultado,5)     
  
}
/**
 * calculo del impuesto iva
 * */
function _calcularImpuesto(precio,iva){
    if(iva == 0){
        return 0;
    }
    var impuesto = iva > 0 ?parseFloat(iva)/100:0
    impuesto = impuesto > 0 ?impuesto:0
    var total = precio * impuesto
    return redondearDecimales(total ,5)
}
   
 /**
 * Cuando se aplica un cambio de cantidad en un detalle
 * Se aplica una recalculacion de todo el detalle y Factura
 **/ 
 __recalculacionDelDetalle(e){
      var cantidad = $(".cambiarCantidadArticulo").val();
    //Cantidad del detalle se verifica si es null o espacio por defecto se deja en 1
    cantidad =__valorNumerico(cantidad);
    if(cantidad == 0){
       cantidad = 1;
    }
    __ValidarCantidadArticulo(self.item.codigo,cantidad)
  }


/**
* Cambiar el precio del detalle de la factura
**/
__cambiarDescripcionDetalle(e){
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


function agregarPrecioAlDetalle(precio){
    self.item.precioUnitario = precio
    self.update()
    ActualizarLineaDEtalle()
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
                        //Articulo no puede agregarse si no hay en el inventario
                        if(modeloTabla.cantidad < 0 || modeloTabla.cantidad == 0 ){
                            mensajeError($.i18n.prop("error.articulo.sin.existencia.en.inventario"))
                            return 
                        }
                        if(modeloTabla.cantidad < cantidad ){
                            mensajeError($.i18n.prop("error.articulo.tiene.menor.existencia.en.inventario.a.la.venta"))
                            return 
                        }
                        agregarCantidadAlaVenta(cantidad)
                    })
                }
            }
        },
	    error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

function getMontoDescuento(precioUnitario,cantidad,porcentajeDesc){
    var porcentaje = porcentajeDesc / 100;
    var total =  precioUnitario * cantidad
    var resultado = total * porcentaje

    return redondearDecimales(resultado ,5)
}

function ActualizarLineaDEtalle(){
  var montoTotal             = getMontoTotal(self.item.precioUnitario,self.item.cantidad)
    var montoDescuento         = getMontoDescuento(self.item.precioUnitario,self.item.cantidad,self.item.porcentajeDesc)
    var subTotal               = redondearDecimales(montoTotal - montoDescuento,5)
    var montoImpuesto          = _calcularImpuesto(subTotal,self.item.iva ==null?0:self.item.iva)
    var montoTotalLinea        = redondearDecimales(subTotal + montoImpuesto,5)    
    self.item.montoTotal       = montoTotal
    self.item.montoDescuento   = redondearDecimales(montoDescuento,5)
    self.item.subTotal         = redondearDecimales(subTotal,5)
    self.item.montoImpuesto    = redondearDecimales(montoImpuesto,5)
    self.item.montoTotalLinea  = redondearDecimales(montoTotalLinea ,5)
    self.update()
}

function agregarCantidadAlaVenta(cantidad){
    self.item.cantidad = cantidad
    self.update()
    ActualizarLineaDEtalle()
    aplicarCambioLineaDetalle() 
    cambiarCantidadArticulo.value = 0
    $('#modalCambiarCantidad').modal('hide') 
}

function aplicarCambioLineaDetalle(){
    var index    = self.detail.indexOf(self.item);
    self.detail[index] = self.item;
    self.update()
    __calculate()
}

/**
* Actualizar el descuento del codigo
**/
__actualizarDescuento(e){
    _actualizarDesc(e)
}



function _actualizarDesc(e){
//    self.item     = e.item; 
    var index     = self.detail.indexOf(self.item);
    var descuento = $(".aplicarDescuento").val();
    //Descuento se verifica si es null o espacios por defecto se deja en cero
     descuento =__valorNumerico(descuento);
      //Descuento
    if(self.item.porcentajeDesc != descuento){
       self.item.porcentajeDesc =  parseFloat(descuento);  
    }    
    self.update()
    ActualizarLineaDEtalle()  
    aplicarCambioLineaDetalle()
    $('#modalCambiarDescuento').modal('hide') 
    aplicarDescuento.value = 0
}

/**
* Monto a pagar en la linea el cliente
**/
function getMontoTotalLinea(subTotal,totalImpuesto){
  return subTotal == 0?0:redondearDecimales(subTotal + totalImpuesto,5)
}
/**
*  Obtener el subtotal sin el impuesto
**/
function getSubTotal(precio,cantidad){
    var valor = __valorNumerico(precio) * __valorNumerico(cantidad)
    return redondearDecimales(valor,5) 
}
/**
* calcular el descuento
**/
function getTotalDescuento(precio,cantidad,porcentajeDesc){
    var porcentaje = __valorNumerico(porcentajeDesc)/100
    var valor =  __valorNumerico(precio) * porcentaje
    return redondearDecimales(valor * cantidad,5)
}
/**
* calculacion de los detalle de la factura 
**/
function __calculate() {
    self.factura.total           = 0;
    self.factura.totalDescuentos  = 0;
    self.factura.totalImpuesto   = 0;
    self.factura.subTotal        = 0;
    self.update()
    totalVenta     = 0
    subTotal       = 0
    totalDescuento = 0
    totalImpuesto  = 0
    totalMercanciasGravadas = 0
    totalMercanciasExentas  = 0
    totalServGravados       = 0
    totalServExentos        = 0

    totalGravado            = 0
    totalExento             = 0
    totalComprobante        = 0
    totalventaNeta          = 0
    self.detail.forEach(function(e){
        totalMercanciasGravadas += e.montoImpuesto > 0 && e.tipoImpuesto != "07"?e.montoTotal:0
        totalMercanciasExentas  += e.impuesto == 0 && e.tipoImpuesto != "07"?e.montoTotal:0
        totalServGravados       += e.montoImpuesto > 0 && e.tipoImpuesto == "07"?e.montoTotal:0
        totalServExentos        += e.impuesto == 0 && e.tipoImpuesto == "07"?e.montoTotal:0
        totalGravado            += e.impuesto > 0 ?e.montoTotal:0
        totalExento             += e.impuesto == 0?e.montoTotal:0
        totalComprobante        += e.montoTotalLinea
        subTotal                += e.subTotal >0?e.subTotal:0
        totalDescuento          += e.montoDescuento >0?e.montoDescuento:0
        totalImpuesto           += e.montoImpuesto >0?e.montoImpuesto:0
        totalVenta              += e.montoTotal
    });
    self.factura.totalMercanciasGravadas = redondearDecimales(__valorNumerico(totalMercanciasGravadas),5)
    self.factura.totalMercanciasExentas  = redondearDecimales(__valorNumerico(totalMercanciasExentas),5)
    self.factura.totalServGravados       = redondearDecimales(__valorNumerico(totalServGravados),5)
    self.factura.totalServExentos        = redondearDecimales(__valorNumerico(totalServExentos),5)

    self.factura.totalGravado            = redondearDecimales(__valorNumerico(totalGravado),5)
    self.factura.totalExento             = redondearDecimales(__valorNumerico(totalExento),5)
    //cuando se aplica descuentos
    self.factura.totalVenta              = redondearDecimales(__valorNumerico(totalVenta),5)
    self.factura.totalDescuentos          = redondearDecimales(__valorNumerico(totalDescuento),5)
    self.factura.subTotal                = redondearDecimales(__valorNumerico(subTotal),5)
    self.factura.totalImpuesto           = redondearDecimales(__valorNumerico(totalImpuesto),5)
    self.factura.totalVentaNeta          = redondearDecimales(__valorNumerico(totalVenta-totalDescuento),5)
    self.factura.totalComprobante        = redondearDecimales(__valorNumerico(totalComprobante),5)
    self.articulo              = null;
    self.update(); 
    $( "#codigo" ).val(null);
    $( "#quantity" ).val(null);
    getSubTotalGeneral()
}

function getSubTotalGeneral(){
    var resultado = __valorNumerico(self.factura.subTotal) + __valorNumerico(self.factura.totalDescuento)
    self.subTotalGeneral = redondearDecimales(resultado,5)
    self.update()
}

function redondearDecimales(numero, decimales) {
    numeroRegexp = new RegExp('\\d\\.(\\d){' + decimales + ',}');   // Expresion regular para numeros con un cierto numero de decimales o mas
    if (numeroRegexp.test(numero)) {         // Ya que el numero tiene el numero de decimales requeridos o mas, se realiza el redondeo
        return Number(numero.toFixed(decimales));
    } else {
        return Number(numero.toFixed(decimales)) === 0 ? 0 : numero;  // En valores muy bajos, se comprueba si el numero es 0 (con el redondeo deseado), si no lo es se devuelve el numero otra vez.
    }
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
                                               return  "₡" + precioPublico.toLocaleString('de-DE');
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
        if(self.articulo.contable == "Si"){
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
                                        {'data' : 'cedula'           ,"name":"cedula"            ,"title" : $.i18n.prop("cliente.cedula")            ,"autoWidth":false},
                                        {'data' : 'nombreCompleto'   ,"name":"nombreCompleto"    ,"title" : $.i18n.prop("cliente.nombreCompleto")    ,"autoWidth":false},
                                        {'data' : 'correoElectronico',"name":"correoElectronico" ,"title" : $.i18n.prop("cliente.correoElectronico") ,"autoWidth":false},
                                        {'data' : 'descuento'        ,"name":"descuento"         ,"title" : $.i18n.prop("cliente.descuento")         ,"autoWidth":false},                                
                                        {'data' : 'telefono'         ,"name":"telefono"          ,"title" : $.i18n.prop("cliente.telefono")          ,"autoWidth":false},                                
                                        {'data' : 'celular'          ,"name":"celular"           ,"title" : $.i18n.prop("cliente.celular")           ,"autoWidth":false},                                
                                        {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									            "render":function(id,type, row){
										            return __Opcionesclientes(id,type,row);
	 							                }	 
								            },
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
        _provincias()  
        _ConsultarCantonesByProvincias(self.cliente.provincia)
        _consultarDistritosByCantoAndProvincia(self.cliente.provincia,self.cliente.canton)
        _consultarBarriosByCantoAndProvinciaAndDistritos(self.cliente.provincia,self.cliente.canton,self.cliente.distrito)
        self.cliente = data
        self.update();
    });
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
* cargar los codigos de referencias
**/
function __comboCondicionPagoRef(){
    self.codigosReferencias = []
    self.update()
    self.codigosReferencias.push({
        estado:"01",
        descripcion:$.i18n.prop("referencia.anula.documento")
    })
    self.codigosReferencias.push({
        estado:"02",
        descripcion:$.i18n.prop("referencia.corrige.texto.documento")
    })    
    self.codigosReferencias.push({
        estado:"03",
        descripcion:$.i18n.prop("referencia.corrige.texto.documento")
    })   
    self.codigosReferencias.push({
        estado:"04",
        descripcion:$.i18n.prop("referencia.otro.documento")
    })    
    self.codigosReferencias.push({
        estado:"05",
        descripcion:$.i18n.prop("referencia.sustituye.comprobante.documento")
    })    
    self.codigosReferencias.push({
        estado:"99",
        descripcion:$.i18n.prop("referencia.otros.documento")
    })    

    self.update()
}
/**
* cargar los tipos de Documento de la factura
**/
function __ComboTipoDocumentos(){
    self.comboTipoDocumentos = []
    self.update()
    self.comboTipoDocumentos.push({
        estado:"04",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
    })
    self.comboTipoDocumentos.push({
         estado:"01",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
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
    $('.tableListarArticulos tfoot th').each( function (e) {
        var title = $('.tableListarArticulos thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 4    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Clientes(){
     // Agregar los input de busqueda 
    $('.tableListaCliente tfoot th').each( function (e) {
        var title = $('.tableListaCliente thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 5    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 

/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Vendedores(){
     // Agregar los input de busqueda 
    $('.tableListaVendedor tfoot th').each( function (e) {
        var title = $('.tableListaVendedor thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 5    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 




/**
*  teclas de la pantalla
**/      
function __Teclas(){
    window.addEventListener( "keydown", function(evento){
        var tecla = evento.keyCode; 
    if(tecla ==119){
      mostrarPAgo()     
   
    }   
    //aplicar crear Factura
    if (self.mostrarFormularioPago == true ){
   //    aplicarFactura()   
    } 

   
    }, false );
  
   

}

/**
* Contabilizar los billetes de acuerdo a como se vayan dando click en la pantalla
*/
_sumarBilletes(e){
    
    var item = e.item
    if(item.valor == 0 ){
       self.factura.totalEfectivo = 0
       self.factura.totalTarjeta  = 0
       self.factura.totalBanco    = 0
       self.factura.totalCambioPagar  = 0
       self.claseCambioDinero     = "entregarCambioPositivo"

    }else{
        self.factura.totalEfectivo = __valorNumerico(self.factura.totalEfectivo) + __valorNumerico(item.valor) 
        self.update()
        var sumaMontosEntregadosParaCambios =__valorNumerico(self.factura.totalTarjeta)
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco) 
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo) 
        self.factura.totalCambioPagar = 0
        self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios > self.factura.totalComprobante ? sumaMontosEntregadosParaCambios - __valorNumerico(self.factura.totalComprobante):sumaMontosEntregadosParaCambios - __valorNumerico(self.factura.totalComprobante)    
        self.claseCambioDinero  = __valorNumerico(sumaMontosEntregadosParaCambios) > __valorNumerico(self.factura.totalComprobante)?'entregarCambioPositivo':'entregarCambioNegativo'
        

    }
    
    self.update()
    
    

}

function cargaBilletes(){
    self.billetes = []
    self.update()
    _incluirBilletes("₡","50,000",50000,'/dist/img/billete50000.jpg')
    _incluirBilletes("₡","20,000",20000,'/dist/img/billete20000.jpg')
    _incluirBilletes("₡","10,000",10000,'/dist/img/billete10000.jpg')
    _incluirBilletes("₡","5,000",5000,'/dist/img/billete5000.jpg')
    _incluirBilletes("₡","2,000",2000,'/dist/img/billete2000.jpg')
    _incluirBilletes("₡","1000",1000,'/dist/img/billete1000.jpg')
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

</script>
</venta-factura>