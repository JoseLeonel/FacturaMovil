<venta-factura>

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
                    <input  type="password"  class="form-control claveSistema"  name="claveSistema" id="claveSistema"  value="{validarRolCommand.claveSistema}">
                </div>      
            </form>
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger"  onclick ="{__SeguridadVentas}" >Autorizar</button>
        </div>
      </div>
    </div>
  </div>
<!--fin validar rol de usuario-->
<!--Modal Cambiar Cantidad-->
<div id='modalCambiarCantidad' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.cantidad")}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-12 col-md-12 col-lg-12 col-sm-12">
                        <div class="form-group has-success">
                            <label class="knob-label" >{$.i18n.prop("inventario.cantidad")}</label>
                            <input  type="number"  class="campo tamanoLetraTotales cambiarCantidadArticulo" id="cambiarCantidadArticulo" name = "cambiarCantidadArticulo" autofocus="autofocus">
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
    <div class="modal-dialog modal-lg">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.descripcion")}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-12 col-md-12 col-lg-12 col-sm-12">
                        <div class="form-group has-success">
                            <label class="knob-label" >{$.i18n.prop("articulo.descripcion")}</label>
                            <input  type="text" class="campo tamanoLetraTotales cambiarDescripcionArticulo" id="cambiarDescripcionArticulo" name = "cambiarDescripcionArticulo" autofocus="autofocus">
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
<div id="pagina1">
   <div class="row">
        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
            <div class="box-tools ">
                <a class="pull-left" href="#"    onclick = {_ListaFacturasDia} title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f5")}</span></a>
                <a class="pull-left" href="#"    onclick = {__Limpiar} title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f10")}</span></a>
                <a class="pull-left" href="#"    onclick = {__ImprimirTiquete}  title="{$.i18n.prop("imprimir.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f7")}</span></a>
                <a class="pull-left" href="#"    onclick = {__MostrarFormularioDePago}  title="{$.i18n.prop("crear.ventas")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f8")}</span></a>
                <a class="pull-left" href="#"    onclick= { __CrearFacturaTemporal}  title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f9")}</span></a>
                <a class="pull-left" href="#" show={mostarAbrirCajon == true}   onclick = {__AbrirCajon} title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("abrir.cajon")}</span></a>
                <a class="pull-right" href="#"   title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-articulos">{descripcionArticulo}</span></a>
                   
            </div>
        </div>      
    </div>    
           
    <div>
        <form id="FormPaginacion">
            <input type="hidden" name="id" id="id" value="{parametrosPaginacion.cantidadPorPagina}">
            <input type="hidden" name="cantidadPorPagina" id="cantidadPorPagina" value="{parametrosPaginacion.cantidadPorPagina}">
            <input type="hidden" name="paginaActual" id="paginaActual" value="{parametrosPaginacion.paginaActual}">
            <input type="hidden" name="tipoVenta" id="tipoVenta" value="{parametrosPaginacion.tipoVenta}">
            <input type="hidden" name="total" id="total" value="{parametrosPaginacion.total}">
            <input type="hidden" name="categoria" id="categoria" value="{categoria.id}">
        </form>
    </div>
<div>    
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
                        <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("vendedor.cedula")}           </th>
                            <th>{$.i18n.prop("vendedor.nombreCompleto")}   </th>
                            <th>{$.i18n.prop("vendedor.correoElectronico")}</th>
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
<div show={mostarParaCrearNuevaVentas} class="contenedor-venta">
    <div class="container-fluid">
        <div class="row no-space">
            <div class="col-md-5 col-sm-5 col-lg-5 col-xs-12 pull-right" style="padding: 2px 12px">
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
                    <div id="listadoProdcutos">{$.i18n.prop("titulo.listado.venta")}   {factura.id>0?factura.id:'' } {factura.nombreFactura}</div>
                    <hr style="margin: 2px 0px 0px 0px; border-color: #e4e4e4; margin-top: 0px">
                    <div class="data-fluid">
                        <div id="listaProductos" style="height:340px; overflow-x: hidden; width:100%">
                            <table id="tablaListaProductos"  cellpadding="0" cellspacing="0" width="100%" class="table lcnp table-dark">
                                <thead>
                                    <tr >
                                        <td width="50%"> Descripcion</td>
                                        <td width="10%">Cant</td>
                                        <td width="10%">Precio.U</td>
                                        <td width="10%">Desc</td>
                                        <td width="10%">IV</td>
                                        <td width="19%">Total</td>
                                        
                                        <td width="15%"></td>
                                    </tr>
                                </thead>
                                <tbody height="70%" id="productos-detail">
                                    <tr style="" each={detail}>
                                        <td>
                                           <diV onclick ={__CambiarDescripcion}>
                                            <span   class="title-detalle text-info clickable clearfix">
                                            {descripcion}</span>
                                           <div> 
                                        </td>
                                        
                                        <td >
                                            <span onclick ={__CambiarCantidad} class="labelDetalleVenta label-success cantidad clickable">{cantidad.toFixed(3)}</span>
                                        </td>
                                        <td >
                                            <span  onclick ={__CambiarPrecio}   class="labelDetalleVenta label-success precio-prod" >{precioUnitario.toFixed(2)}</span>
                                        </td>
                                        <td >
                                            <span onclick ={__CambiarDescuento} class="labelDetalleVenta label-success precio-prod" >{porcentajeDesc.toFixed(2)}</span>
                                        </td>
                                        <td >
                                            <span class="labelDetalleVenta label-success " >{impuesto.toFixed(2)}</span>
                                        </td>
                                        <td>
                                            <span class="labelDetalleVenta precio-calc">{montoTotalLinea.toFixed(2)}</span>
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
                                    <td width="30%" id="bordeBevelLeft"> 
                                        <span id="pagarInfo"> {$.i18n.prop("factura.resumen.subTotal")} </span>
                                        <span id="cantidad-total">{subTotalGeneral  } </span> 
                                    </td>
                                    <td width="35%" id="bordeBevelLeft"> 
                                        <span id="pagarInfo">{$.i18n.prop("factura.resumen.descuento")}  </span>
                                        <span id="sigPeso">   </span>
                                        <span id="iva-total">{totalDescuentos}</span> 
                                    </td>
                                    <td width="35%" id="bordeBevelRight"> 
                                        <span id="pagarInfo">{$.i18n.prop("factura.resumen.impuesto")}  </span>
                                        <span id="sigPeso">      </span>
                                        <span id="subtotal">{totalImpuesto}</span> 
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <hr style="margin: 0px; border-color: #e4e4e4; margin-top: 0px">                    
                    
                </div>
                <div  class="row  ">
                    <div class="col-md-12 col-sx-12 col-lg-12 col-sm-12 "  style="padding:10px 15px 18px 15px; ">
                        <div class="input-group">
                            <span onclick = {__CambiarNombreTiquete} title="Cambiar Nombre Tiquete" class="input-group-addon btnClientes" id="add-new-client"> 
                                <small class="fa fa-plus" style="margin-top:0px; position: absolute; left: 8px; top:8px"></small>
                                <span class="fa fa-user" aria-hidden="true" style="margin-left:5px; margin-top: 3px;"></span> 
                            </span>
                            <input onclick={_EscogerClientes} type="text"  placeholder="Cliente" value="{cliente.nombreCompleto}"  name="datos_cliente" id="datos_cliente" autocomplete="off" >
                        </div>
                        <!--Vendedor o Nuevo Vendedor-->
                        <div class="input-group">
                            <span title="Vendedor" class="input-group-addon " > 
                                <span class="fa fa-user" aria-hidden="true" style="margin:3px 4px 0px 2px"></span> 
                            </span>
                            <input type="text" onclick={_EscogerVendedores} placeholder="Vendedor" value="{vendedor.nombreCompleto}"  name="v_vendedor" id="v_vendedor" autocomplete="off" >
                        </div>
                        <a class="pull-right" href="#"   title="{factura.nombreFactura}"> <span class="label-titulos-articulo">Tikete a :{factura.nombreFactura}</span></a>
                    </div>
                </div> 
                <hr style="margin: 0px; border-color: #e4e4e4;">
                <div  class="row  ">
                    <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12 " >   
                        <section class="contenedor-opciones">
                            <a href="#" class="opciones-menu" onclick = {__CrearFacturaTemporal} >
                                <i class="fa fa-clock-o">{$.i18n.prop("venta.en.espera")}</i>
                            </a>
                            <a  href="#" class="opciones-limpiar" onclick = {__ImprimirTiquete} >
                                <i class="fa fa-print">Tiquete</i>
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
                                <span class="label-titulos-espera"> {nombreFactura ==null || nombreFactura == '' ?"T#"+id:""}  {nombreFactura}</span>
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
                                        &nbsp;&nbsp; {$.i18n.prop("titulo.lector")} </h3> 
                                    </li>
                                    <li onclick = {__PantallaCategorias} id="navegador" class=""> <h3>
                                        <i class="glyphicon glyphicon-refresh" aria-hidden="true"></i>
                                        <img  src="{urlImagenNavegador}" width="40px" height="15px">
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
                            <img  style = "width:100px;" alt="" class="img-responsive " src="data:image/png;base64,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">
                             <span class="label-titulos-articulo">{descripcion}</span>
                        </div>
                    </section>
                    <!--Seccion de articulos-->
                    <section show= {mostrarArticulosXCategoria} class="lista-articulos clickable" >
                        <div class="product-item"  each ={inventariosXCategoria.data}   onclick={__AgregarProductoDePantalla}>
                            <img  style = "width:75px;" alt="" class="img-responsive " src="data:image/png;base64,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">
                            <span class="label-titulos-articulo">{descripcion}</span>
                        </div>
                    </section>
                    <div onclick = {__BotonAnterior} show= {mostrarNavegador}  class="pull-left btnNavegacion " >
                        <i class="fa fa-arrow-left"></i>{$.i18n.prop("btn.Anterior")}
                    </div>
                    <div onclick = {__BotonSiguiente} show= {mostrarNavegador}  class="pull-right btnNavegacion " >
                        <i class="fa fa-arrow-right"></i>{$.i18n.prop("btn.siguiente")}
                    </div>
                    <!--Fin Seccion de articulos-->
                    <!--Seccion de codigo de barra-->
                    <section show={mostrarCodigoBarra} class="codigo-barra clickable" >
                        <div class="barra">
                           <input onkeypress = {__addProductToDetail} type="text" class="form-control" id="codigoBarra" autofocus="autofocus" placeholder="{$.i18n.prop('titulo.digite.codigo.barra')}">
                       </div>    
                    </section>
                    <!--Fin Seccion de codigo de barra-->
                </div> 
                 
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
              <div class="row">
                    <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <table id="tableListarFacturasDia" class="display table responsive table-hover nowrap table-condensed tableListarFacturasDia "   cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class = "table-header" >{$.i18n.prop("factura.fecha.emision")}            </th>
                                <th class = "table-header" >{$.i18n.prop("factura.documento")}                </th>
                                <th class = "table-header" >{$.i18n.prop("factura.cliente")}                  </th>
                                <th class = "table-header" >{$.i18n.prop("factura.linea.detalle.impuesto")}   </th>
                                <th class = "table-header" >{$.i18n.prop("factura.linea.detalle.descuento")}  </th>
                                <th class = "table-header" >{$.i18n.prop("factura.total")}                    </th>
                                <th class = "table-header" >{$.i18n.prop("listado.acciones")}                 </th>
                            </tr>
                            </thead>
                                <tfoot style="display: table-header-group;">
                                    <tr>
                                        <th>{$.i18n.prop("factura.fecha.emision")}            </th>
                                        <th>{$.i18n.prop("factura.documento")}                </th>
                                        <th>{$.i18n.prop("factura.cliente")}                  </th>
                                        <th>{$.i18n.prop("factura.linea.detalle.impuesto")}   </th>
                                        <th>{$.i18n.prop("factura.linea.detalle.descuento")}  </th>
                                        <th>{$.i18n.prop("factura.total")}                    </th>
                                        <th>                                                  </th>
                                    </tr>
                                </tfoot>
                            </table>
                    </div>    
                </div>    

            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--Modal mostrar  -->  

<!-- Modal cambiar nombre del tiquete-->
<div class="modal fade" id="ModalCambiarNombreTiquete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="box-title"><i class="fa fa-user-o"></i>&nbsp {$.i18n.prop("factura.digite.nombreFactura")}     </h1>
            </div>
            <div class="modal-body">
                <form id = "formularioModalCambiarNombreTiquete" name ="formularioModalCambiarNombreTiquete "   class="advanced-search-form">
                    <input type="hidden" id='idFactura'  name='idFactura'  value="{factura.id}" >
                    <input type="hidden" id='nombreFactura'  name='nombreFactura'  value="{factura.nombreFactura}" >
                    <div class="row">   
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="knob-label" >{$.i18n.prop("factura.nombreFactura")}</label>
                            <input type="text" class="campo tamanoLetraTotales cambioNombreTiquete"  id="cambioNombreTiquete" name="cambioNombreTiquete" autofocus="autofocus" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="row">
                    <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
                        <button  onclick={__ModificarNombreTiquete}   class="btn-green btn-add pull-right" >  {$.i18n.prop("btn.aplicar")}</button>
                    </div>
                </div>
            </div>
        </div>
     </div>
</div>
<!--fin Modal agregar el nombre de el tiquete temporal-->

<!-- Modal correo alternativo-->
<div class="modal fade" id="ModalAgregarNombreTiquete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="box-title"><i class="fa fa-user-o"></i>&nbsp {$.i18n.prop("factura.digite.nombreFactura")}     </h1>
            </div>
            <div class="modal-body">
                <form id = "formularioAgregarNombreTiquete" name ="formularioAgregarNombreTiquete "   class="advanced-search-form"  autocomplete="off" >
                    <div class="row">   
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="knob-label" >{$.i18n.prop("factura.nombreFactura")}</label>
                            <input type="text" class="campo tamanoLetraTotales cambioNombreFactura"  id="cambioNombreFactura" name="cambioNombreFactura" autofocus="autofocus"  > 
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="row">
                    <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                        <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
                        <button  onclick={__AgregarNombreFacturaTemporal}   class="btn-green btn-add pull-right" >  {$.i18n.prop("btn.aplicar")}</button>
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
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.precio")}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-12 col-md-12 col-lg-12 col-sm-12">
                        <div class="form-group has-success">
                            <label >Precio:</label>
                            <input  type="number"  class="form-control cambiarprecioArticulo" id="cambiarprecioArticulo" name = "cambiarprecioArticulo" autofocus="autofocus" min="0">
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
    <div class="modal-dialog modal-md">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.descuento")}</h4>
            </div>
            <div class="modal-body">
                <form id="formularioDescuento" name="formularioDescuento">
                <div class="row">
                    <div class="col-sx-12 col-md-12 col-lg-12 col-sm-12">
                        <div class="form-group has-success">
                            <label >{$.i18n.prop("factura.linea.detalle.descuento")}</label>
                            <input  type="number" class="campo tamanoLetraTotales aplicarDescuento" id="aplicarDescuento" name = "aplicarDescuento" autofocus="autofocus" min="0">
                        </div>
                    </div>
                </div> 
                </form>

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
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
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
              <div class="row">
                     <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12 ">
             
                <table id="tableListaCliente" class="table responsive display table-striped table-hover nowrap tableListaCliente " cellspacing="0" width="100%">
                   <thead>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                        <th  style="width:5%" class="table-header">{$.i18n.prop("cliente.cedula")}            </th>
                        <th class="table-header">{$.i18n.prop("cliente.nombreCompleto")}    </th>
                        <th class="table-header">{$.i18n.prop("cliente.nombreComercial")}    </th>
                        <th  style="width:8%" class="table-header">{$.i18n.prop("cliente.correoElectronico")} </th>
                        
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>                                          </th>
                            <th  style="width:5%">{$.i18n.prop("cliente.cedula")}           </th>
                            <th>{$.i18n.prop("cliente.nombreCompleto")}   </th>
                            <th>{$.i18n.prop("cliente.nombreComercial")}   </th>
                            <th style="width:8%">{$.i18n.prop("cliente.correoElectronico")}</th>
                           
                        </tr>
                    </tfoot>                    
                </table>
            </div>
            </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
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
                                                <input type="text" id="nombreFactura" name="nombreFactura" class="form-control nombreFactura"  value="{factura.nombreFactura}" > 
                                            </div>
                                        </div>    
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group ">
                                                <label>{$.i18n.prop("factura.correoAlternativo")}</label> 
                                                <input type="email" id="correoAlternativo" name="correoAlternativo" class="form-control correoAlternativo"  value="{factura.correoAlternativo}" >
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
                                    <div class="form-group " show = "{mostrarCamposIngresoContado == false}">
                                        <label>{$.i18n.prop("factura.plazoCredito")}</label> 
                                        <input type="number" id = "plazoCreditoL"  name "plazoCreditoL" class="form-control plazoCreditoL" value="{factura.plazoCredito}" >
                                    </div>
                                  
                                   

                                </div>
                                <div  class= "col-md-6 col-sx-6 col-sm-6 col-lg-6" >
                                    <div class="form-group has-success">
                                        <label for="pago_transporteL">{$.i18n.prop("factura.resumen.efectivo")} </label> 
                                        <input onkeyup={ __TotalDeEfectivoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="campo tamanoLetraTotales totalEfectivo " id="totalEfectivo" name="totalEfectivo" value="{factura.totalEfectivo}">
                                    </div>
                                    <div  class="form-group has-success">
                                        <label for="pago_efectivoL">{$.i18n.prop("factura.resumen.tarjeta")} </label> 
                                        <input onkeyup={ __TotalDeTarjetaAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="campo tamanoLetraTotales totalTarjeta" id="totalTarjeta" name="totalTarjeta"   value="{factura.totalTarjeta}">
                                    </div>
                                    <div  class="form-group has-success">
                                        <label for="pago_tarjetaL">{$.i18n.prop("factura.resumen.banco")} </label> 
                                        <input onkeyup={ __TotalDeBancoAPagar } onBlur = {__CalculaCambioAEntregarOnblur} onkeypress = {__CalculaCambioAEntregarKeyPress} type="number" step="any" class="campo tamanoLetraTotales totalBanco" id="totalBanco" name = "totalBanco"  value="{factura.totalBanco}">
                                    </div>
                               
                                    
                                </div>
                            </div>
                            <input type="hidden" id='codigoActividad' name='codigoActividad'  value="{factura.codigoActividad}" >
                            <input type="hidden" id='id'                      name='id'                      value="{factura.id}" >
                            <input type="hidden" id='plazoCredito'            name='plazoCredito'            value="{factura.plazoCredito}" >
                            <input type="hidden" id='estado'                  name='estado'                  value="{factura.estado}" >
                            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
                            <input type="hidden" id='subTotal'                name='subTotal'                value="{factura.subTotal}" >
                            <input type="hidden" id='totalComprobante'        name='totalComprobante'        value="{factura.totalComprobante}" >
                            <input type="hidden" id='totalServGravados'       name='totalServGravados'       value="{factura.totalServGravados}" >
                            <input type="hidden" id='totalMercanciasGravadas' name='totalMercanciasGravadas' value="{factura.totalMercanciasGravadas}" >
                            <input type="hidden" id='totalMercanciasExentas'  name='totalMercanciasExentas'  value="{factura.totalMercanciasExentas}" >
                            <input type="hidden" id='totalServExentos'        name='totalServExentos'        value="{factura.totalServExentos}" >
                            <input type="hidden" id='totalGravado'            name='totalGravado'            value="{factura.totalGravado}" >
                            <input type="hidden" id='totalExento'             name='totalExento'             value="{factura.totalExento}" >
                            <input type="hidden" id='totalVenta'              name='totalVenta'              value="{factura.totalVenta}" >
                            <input type="hidden" id='totalDescuentos'          name='totalDescuentos'          value="{factura.totalDescuentos}" >
                            <input type="hidden" id='totalVentaNeta'          name='totalVentaNeta'          value="{factura.totalVentaNeta}" >
                            <input type="hidden" id='totalImpuesto'           name='totalImpuesto'           value="{factura.totalImpuesto}" >
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
                                        
                                        <div class="elementoTotales"  >   
                                           <div class="tituloTotal">{$.i18n.prop("factura.resumen.exoneracion")}</div>
                                           <div class="valorTotal">  {montoExoneracion}   </div>
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
<style type="text/css">
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
    /* padding-left: 20px; */
    /* line-height: 30px; */
    border-collapse: separate;
    text-align: center;
    /* cursor: pointer; */
    /* padding: 10px; */
    /* margin: 20px; */
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
    /* padding-left: 20px; */
    /* line-height: 30px; */
    border-collapse: separate;
    text-align: center;
    /* cursor: pointer; */
    /* padding: 10px; */
    /* margin: 20px; */
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
.labelDetalleVenta {
    display: inline;
    padding: .2em .6em .3em;
    font-weight: 700;
    line-height: 1;
    color: black;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
    border-radius: .25em;
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
    self.mostarParaCrearNuevaVentas = true
    self.mostrarCodigoBarra    = true
    self.mostrarCodigoBarra          = true;
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.comboEstados          = []
    self.comboCondicionPagos        = []
    self.comboTipoDocumentos   = []
     self.validarRolCommand = {
                        usuarioSistema : "",
                        claveSistema:""
                    }   
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
    self.item                  = null;
    self.articulo              = null;
    self.articulos             = {data:[]}
    self.clientes              = {data:[]}
    self.detalleFactura        = {data:[]}
    self.cliente               = {}
    self.vendedor              = {
        id:null,
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
    self.totalDescuentos               = 0
    self.totalImpuesto                 = 0
    self.totalComprobante              = 0;
    self.totalCambioPagar              = 0;
    self.totalCambioPagarSTR              = 0;
    self.todasProvincias               = {data:[]}
    self.todosCantones                 = {data:[]}
    self.todosDistritos                = {data:[]}
    self.todosBarrios                  = {data:[]}
    self.cantones                      = []
    self.distritos                     = []
    self.barrios                       = []
     self.mostrarListadoArticulos == false
    self.parametrosPaginacion = {
        id:null,
        paginaActual:0,
        cantidadPorPagina:10,
        total:0,
        tipoVenta:2

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
    self.primeraVezBilleteClick = false
    self.mostrarImpuestoServicio = false

    self.urlImagenNavegador   = '/dist/img/navegador.png';
    self.urlImagenLector      = '/dist/img/codigo_barra.png';
    self.urlImagenBuscador    = '/dist/img/buscador.png';

    self.tipoCambio = {
        total:0,
        id:null
    }
     self.rol = {
        rolAdministrador:0
    }
    self.actividadesComerciales        = []
    self.actividadComercial = {
        codigo:"",
        descripcion:""
    }
    self.mostarAbrirCajon = true 
    self.montoExoneracion     = 0
    self.montoExoneracion1     = 0
    self.on('mount',function(){
        $("#formularioFactura").validate(reglasDeValidacionFactura());
        $("#formularioAgregarNombreTiquete").validate(reglasAgregarNombre());
        $("#formularioModalCambiarNombreTiquete").validate(reglasCambiarNombre());
        __informacionData()
        __informacionData_vendedores()
        __InicializarTabla('.tableListaCliente')
        __InicializarTabla('.tableListaInventario')
        __InicializarTabla('.tableListaVendedor')
        agregarInputsCombos_Articulo()
        __ListaFacturasEnEspera()
        _Empresa()
        __comboCondicionPago()
        __RolAdministrador()
        __ListaDeClientes()
       __ListaDeVendedores()
       __Teclas()
       __TipoCambio()
        cargaBilletes()
        mostrarCategorias()
        __ListaActividadesComercales()
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
        if(retrievedObject != null){
            self.detail = retrievedObject
            var facturaObject = JSON.parse(localStorage.getItem('facturaNueva'));
            self.factura = facturaObject
            self.update()
            __calculate()   

        }
        
         window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
     
    })

__AsignarActividad(e){
    BuscarActividadComercial()
}

function BuscarActividadComercial(){
    var codigo =$('#selectActividadComercial').val()
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
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.empresaActividadComercial   = result.aaData
                self.update()
                BuscarActividadComercial()

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
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.rol = modeloTabla
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
                	 swal({
                         type: 'error',
                         title:"No autorizado",
                         showConfirmButton: false,
                         timer: 1500
                     });
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
                    swal({
                        type: 'error',
                        title:"No autorizado",
                        showConfirmButton: false,
                        timer: 1500
                    })      
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
* agregar producto desde la pantalla de articulos
**/

__AgregarProductoDePantalla(e){
    
    var item =  e.item
    self.articulo = item;
    self.update()
    __agregarArticulo(1)
    //__buscarcodigo(self.articulo.codigo,1);
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
        self.parametrosPaginacion.tipoVenta = 2
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
        self.parametrosPaginacion.tipoVenta = 2
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
/**
*  Limpiar Formulario
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
           refrescarPagina()
        }

    }else{
        refrescarPagina()
    }

}
__LimpiarFormulario(){
    __SeguridadLimpiar()
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
    $('.tableListarFacturasDia tfoot th').each( function (e) {
        var title = $('.tableListarFacturasDia thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 5    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
/**
* formato del listado de facturas del dia
**/
function __InformacionDataTableDia(){
    self.formato_tabla_dias = [ 
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
                               {'data' :'totalImpuestoSTR'               ,"name":"totalImpuestoSTR"        ,"title" : $.i18n.prop("factura.linea.detalle.impuesto")     ,"autoWidth" :true },
                               {'data' :'totalDescuentosSTR'             ,"name":"totalDescuentosSTR"      ,"title" : $.i18n.prop("factura.linea.detalle.descuento")  ,"autoWidth" :true },
                               {'data' :'totalComprobanteSTR'            ,"name":"totalComprobanteSTR"     ,"title" : $.i18n.prop("factura.total") ,"autoWidth" :true },
                               {'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
                               },
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
        //var factura = data
        consultaParaReimprimir(data,1)
       // riot.mount('ptv-imprimir',{factura:data});
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
                    sweetAlert("", data.message, "error");
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
    self.rutaAutorizada = '';
    self.update()
     if(self.item.codigo =="8888"){
        return true
    } 
    if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        self.rutaAutorizada = '#modalCambiarDescuento';
        self.update()
        $("#usuarioSistema").val("")
        $("#claveSistema").val("")
        $('#modalRolUsuario').modal({backdrop: 'static', keyboard: true}) 
        $('#modalRolUsuario').modal('show')     
    }else{
         $( "#aplicarDescuento" ).focus()
        $( "#aplicarDescuento" ).val(null)
        $('#modalCambiarDescuento').modal({backdrop: 'static', keyboard: true}) 
        $('#modalCambiarDescuento').modal('show')      
    }
}
/**
*Cambiar Cantidad del Articulo
**/
__CambiarCantidad(e){
   var cantidad = e.currentTarget.value;
   self.item = e.item; 
   self.rutaAutorizada = '';
   self.update()
   if(self.item.codigo =="8888"){
        return true
    } 
   if(self.empresa.seguridadEnVentas == 1 && self.rol.rolAdministrador == 0){
        self.rutaAutorizada = '#modalCambiarCantidad';
        self.update()
        $("#usuarioSistema").val("")
        $("#claveSistema").val("")
        $('#modalRolUsuario').modal({backdrop: 'static', keyboard: true}) 
        $('#modalRolUsuario').modal('show')     
   }else{
        $( "#cambiarCantidadArticulo" ).focus()
        $( "#cambiarCantidadArticulo" ).val(self.item.cantidad)
        $('#modalCambiarCantidad').modal()                      // initialized with defaults
        $('#modalCambiarCantidad').modal({backdrop: 'static', keyboard: false})     // initialized with no keyboard
        $('#modalCambiarCantidad').modal('show')                // initializes and invokes show immediately   
   }
}
/**
*Cambiar descripcion
**/
__CambiarDescripcion(e){
   self.item = e.item; 
   self.update()
   if(self.item.codigo =="8888"){
        return true
    } 
  
   $( "#cambiarDescripcionArticulo" ).focus()
   $( "#cambiarDescripcionArticulo" ).val(self.item.descripcion)
   $('#modalCambiarDescripcion').modal()                      // initialized with defaults
   $('#modalCambiarDescripcion').modal({backdrop: 'static', keyboard: false})     // initialized with no keyboard
   $('#modalCambiarDescripcion').modal('show')                // initializes and invokes show immediately


}
/**
*Cambiar precio del producto
**/
__CambiarPrecio(e){
   self.item = e.item; 
   if(self.item.codigo =="8888"){
        return true
    } 
   self.update()
   $( "#cambiarprecioArticulo" ).focus()
   $( "#cambiarprecioArticulo" ).val( e.item.precioUnitario)
   $('#modalCambiarPrecio').modal('show')      
}
/**
* Tipo Cambio de moneda
**/
function __TipoCambio(){
    self.tipoCambio = {
        total:0,
        id:null
    }
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
    consultaParaReimprimir(factura,1)
    //riot.mount('ptv-imprimir',{factura:factura});
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
    riot.mount('tiquete-imprimir',{factura:factura});
}
/**
*  Obtiene el valor de lo digitado en el campo de efectivo
**/
__TotalDeEfectivoAPagar(e){
    self.factura.totalEfectivo = __valorNumerico(e.target.value) 
    self.update()
    _calculoEnterPago()
}
/**
*  Obtiene el valor de lo digitado en el campo de Tarjeta
**/
__TotalDeTarjetaAPagar(e){
    self.factura.totalTarjeta = __valorNumerico(e.target.value) 
    self.update()
    _calculoEnterPago()
}
/**
*  Obtiene el valor de lo digitado en el campo de Banco
**/
__TotalDeBancoAPagar(e){
    self.factura.totalBanco = __valorNumerico(e.target.value) 
    self.update()
    _calculoEnterPago()
}
/**
*   Calculo del cambio entregar en el evento onblur
**/
__CalculaCambioAEntregarOnblur(e){
    self.totalCambioPagar    = 0
   // self.totalCambioPagarSTR = 0
    self.update()
   _calculoEnterPago()
}
/**
*   Calculo del cambio entregar en el evento keyPress
**/
__CalculaCambioAEntregarKeyPress(e){
    self.totalCambioPagar    = 0
   // self.totalCambioPagarSTR = 0
    self.update()
   // if (e.keyCode == 13) {
       _calculoEnterPago()
   // }
}


function _calculoEnterPago(){
    var sumaMontosEntregadosParaCambios =0
    sumaMontosEntregadosParaCambios  = __valorNumerico($('.totalTarjeta').val())
    sumaMontosEntregadosParaCambios += __valorNumerico($('.totalBanco').val()) 
    sumaMontosEntregadosParaCambios += __valorNumerico($('.totalEfectivo').val())
    if(sumaMontosEntregadosParaCambios == 0){
        self.factura.totalCambioPagar = self.factura.totalComprobante * -1
        self.totalCambioPagarSTR =formatoDecimales(self.factura.totalCambioPagar,2)
        self.update()
        return
    }
    self.factura.totalCambioPagar = 0
    var totalEntregado = __valorNumerico(redondeoDecimales(sumaMontosEntregadosParaCambios,2))
    var totalFactura   = __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2))
    totalEntregado     = __valorNumerico(totalEntregado)
    totalFactura       = __valorNumerico(totalFactura)  
    self.claseCambioDinero = totalEntregado > totalFactura?"entregarCambioPositivo":"entregarCambioNegativo"
    self.factura.totalCambioPagar = totalEntregado - totalFactura
    self.factura.totalCambioPagar =__valorNumerico(self.factura.totalCambioPagar)   
    self.totalCambioPagar =    __valorNumerico(redondeoDecimales(self.factura.totalCambioPagar,2))
    self.totalCambioPagarSTR = formatoDecimales(self.totalCambioPagar,2)
    self.update()
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
              //  __agregarArticulos()
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
*  Buscar la Factura Pendiente en espera
**/
__CargarFacturaEspera(e){
    self.factEspera = e.item
    self.factura = e.item
    self.update()
    if(self.factura.id !=null){
      if(self.seIncluyoUnArticulo !=null){
        aplicarFactura(1)  
       
      }  
      
    }else{
        if(self.detail.length != 0 ){
            $('#ModalAgregarNombreTiquete').modal('show') 
            $('.cambioNombreFactura').focus()
            return
         }
       
    }
    
__FacturaEnEspera(e.item)
}

/**
* Cambiar Nombre del tiquete
**/
__CambiarNombreTiquete(){
    if(self.factura.id ==null){
        return
    }
    $('.cambioNombreTiquete').focus()
    $('.cambioNombreTiquete').val(self.factura.nombreFactura)
    $('#ModalCambiarNombreTiquete').modal({backdrop: 'static', keyboard: false})     // initialized with no keyboard
    $('#ModalCambiarNombreTiquete').modal('show') 
    
}

/**
*  Crear la factura temporal o espera
**/
__CrearFacturaTemporal(){
     if(self.detail.length == 0 ){
        mensajeError($.i18n.prop("factura.alert.sin.detalles"))
        return
    }
    if(self.factura.id == null){
        
        if(self.detail.length != 0 ){
            $('.cambioNombreTiquete').val(self.factura.nombreFactura)
            $('.cambioNombreFactura').focus()
            $('#ModalAgregarNombreTiquete').modal() 
         //   $('#ModalCambiarNombreTiquete').modal({ keyboard: false })     // initialized with no keyboard
             $('#ModalAgregarNombreTiquete').modal('show') 
            return
        }

    }else{
        self.seIncluyoUnArticulo = 1
        self.update()
        aplicarFactura(1)    
    }
    
   // __FacturaEnEspera(self.factEspera)
    self.factEspera =null
    self.update()
}

/**
* cCambiar el nombre de la factura
**/
__ModificarNombreTiquete(){
    if ($("#formularioModalCambiarNombreTiquete").valid()) {
    self.factura.nombreFactura = $('.cambioNombreTiquete').val()
    self.update()

    var formulario = $("#formularioModalCambiarNombreTiquete").serialize();
    if(self.factura.id == null){
        return
    }
      $.ajax({
        url: "ModificarNombreTiquteAjax",
        datatype: "json",
        data: formulario,
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                sweetAlert("", $.i18n.prop("error.factura.no.existe"), "error");

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
                         sweetAlert("", $.i18n.prop("error.factura.no.existe"), "error");
                    }else{
                        sweetAlert("", data.message, "info");
                    }
                }
                 $('#ModalCambiarNombreTiquete').modal('hide') 
                __ListaFacturasEnEspera()
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
    }
}
/**
*  Agregar el nombre a la factura temporal
**/
__AgregarNombreFacturaTemporal(){
    $('#ModalAgregarNombreTiquete').modal('hide') 
    self.factura.nombreFactura = $('.cambioNombreFactura').val()==null?"":$('.cambioNombreFactura').val();
    self.update()
    aplicarFactura(1)      
    if(self.factEspera !=null){
      __FacturaEnEspera(self.factEspera) 
    }
    self.factEspera =null
    self.update()
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
        if($('#plazoCreditoL').val() < 0 || $('#plazoCreditoL').val() == null || $('#plazoCreditoL').val() == 0){
           mensajeError($.i18n.prop("factura.alert.plazoCredito"))
            return
        }
    }else{
        // Si no es credito y el estado no es pendiente se debe verificar si ingresaron el monto a pagar
        if(estado !=1){
            if(__valorNumerico($('#totalTarjeta').val()) == 0 && __valorNumerico($('#totalBanco').val()) == 0 && __valorNumerico($('#totalEfectivo').val()) == 0){
                mensajeError($.i18n.prop("error.factura.monto.ingresado"))
                return
            }
            var montoEntregado = __valorNumerico($('#totalTarjeta').val())  + __valorNumerico($('#totalBanco').val()) + __valorNumerico($('#totalEfectivo').val())
            montoEntregado = redondeoDecimales(__valorNumerico(montoEntregado),2)
            montoEntregado = __valorNumerico(montoEntregado)
            var resultado  = redondeoDecimales( __valorNumerico(self.factura.totalComprobante),2)
            resultado = __valorNumerico(resultado);
            if(resultado > montoEntregado){
                mensajeError($.i18n.prop("error.factura.monto.ingresado.es.menor.ala.venta"))
                return
            }
            //Si el cliente esta pagando con tajeta, banco debe ser igual a la venta
            if(self.factura.totalTarjeta != 0 || self.factura.totalBanco !=0){
                if(resultado != montoEntregado  ){
                    mensajeError($.i18n.prop("error.factura.monto.tarjeta.banco.igual.venta"))
                   return
                }
            }
        }
    } 
    if ($("#formularioFactura").valid()) {
        crearFactura(estado)  
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
    self.primeraVezBilleteClick = false
    self.mostrarListadoArticulos == false
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.comboEstados          = []
    self.comboCondicionPagos        = []
    self.comboTipoDocumentos   = []
    self.facturas_espera       = {data:[]}  
    self.descripcionArticulo = ""
    $('.cambioNombreFactura').val(null)
    self.item                  = null;
    self.articulo              = null;
    self.clientes              = {data:[]}
    self.detalleFactura        ={data:[]}
    self.cliente               = {};
    self.vendedor = {
        id:0,
        nombreCompleto:""
    }
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
        totalCambioPagar:0,
	    codigoMoneda:"",
	    estado:1,
	    cliente:{
            id:null,
            nombreCompleto:"",
        },
	    vendedor:{
            id:null,
            nombreCompleto:""
        }
    }
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
            total:0,
            tipoVenta:2
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
    //totales
    self.totalComprobante     = 0;
    self.subTotalGeneral  =0;
    self.totalComprobante =0;
    self.totalDescuentos  =0;
    self.totalImpuesto    =0;
    self.totalCambioPagar =0;
    self.montoExoneracion     = 0
    self.update()
    $('#condicionVenta').prop("selectedIndex", 0);
    $('#tipoDoc').prop("selectedIndex", 0);
    $('#estado').prop("selectedIndex", 0);
    $(".totalBanco").val(null)   
    $(".totalTarjeta").val(null)   
    $(".totalEfectivo").val(null)   
    $("#plazoCreditoL").val(null)
    $(".nombreFactura").val(null)
    $(".correoAlternativo").val(null)
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
    $("#cambiarCantidadArticulo").val(null)
    $("#aplicarDescuento").val(null)
    // Tipo de Pagos
     __comboCondicionPago()
     //Tipos de Documentos
      __ComboTipoDocumentos(0)
      //Estados
      __ComboEstados()
     __ListaFacturasEnEspera()
     mostrarCategorias()
     
     $('#codigoBarra').val(null)
     $('#codigoBarra').focus()
    localStorage.setItem('DetallesNueva', JSON.stringify(self.detail));
    localStorage.setItem('facturaNueva', JSON.stringify(self.factura));
}
/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(factura){
    if(factura ==null){
        return
    }
     __Init()
    $.ajax({
        url: "ListarDetlleByFacturaAjax.do",
        datatype: "json",
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
     $('.nota').val(null);
    $('.correoAlternativo').val(null);
    $('.nombreFactura').val(null);
    self.detail = [];
    self.numeroLinea =  0
    self.cantArticulos =  0
    self.pesoPrioridad = 0
    self.update()
    $.each(data, function( index, modeloTabla ) {
        self.factura = modeloTabla.factura
        $('.nota').val(self.factura.nota);
        $('.correoAlternativo').val(self.factura.correoAlternativo);
        $('.nombreFactura').val(self.factura.nombreFactura);
        self.factura.fechaCredito = self.factura.fechaCredito !=null?__displayDate_detail(self.factura.fechaCredito):null
        self.cliente              = modeloTabla.factura.cliente
        self.vendedor             = modeloTabla.factura.vendedor
        self.update()
        self.detail.push({
            numeroLinea     : modeloTabla.numeroLinea,
            pesoPrioridad    :modeloTabla.numeroLinea,
            codigo          : modeloTabla.codigo,
            tipoImpuesto    : modeloTabla.tipoImpuesto,
            tipoImpuesto1   : modeloTabla.tipoImpuesto1,
            descripcion     : modeloTabla.descripcion,
            cantidad        : parseFloat(modeloTabla.cantidad),
            precioUnitario  : parseFloat(modeloTabla.precioUnitario),
            impuesto        : parseFloat(modeloTabla.impuesto),
            montoImpuesto   : parseFloat(modeloTabla.montoImpuesto),
            montoImpuesto1  : parseFloat(modeloTabla.montoImpuesto1),
            montoDescuento  : parseFloat(modeloTabla.montoDescuento),
            porcentajeDesc  : parseFloat(modeloTabla.porcentajeDesc),
            subTotal        : parseFloat(modeloTabla.subTotal),
            montoTotalLinea : parseFloat(modeloTabla.montoTotalLinea),
            montoTotal      : parseFloat(modeloTabla.montoTotal),
            costo           : parseFloat(modeloTabla.costo),
            porcentajeGanancia :parseFloat(modeloTabla.porcentajeGanancia),
            montoExoneracion:parseFloat(modeloTabla.montoExoneracion),
            montoExoneracion1:parseFloat(modeloTabla.montoExoneracion1),
            porcentajeExoneracion:parseFloat(modeloTabla.porcentajeExoneracion),
            fechaEmisionExoneracion:modeloTabla.fechaEmisionExoneracion,
            nombreInstitucionExoneracion:modeloTabla.nombreInstitucionExoneracion,
            numeroDocumentoExoneracion:modeloTabla.numeroDocumentoExoneracion,
            tipoDocumentoExoneracion:modeloTabla.tipoDocumentoExoneracion
        });
        self.update()
        self.numeroLinea   = self.numeroLinea + 1
        self.cantArticulos = self.cantArticulos + 1
        self.pesoPrioridad = self.numeroLinea
    })
    self.factura.totalCambioPagar = self.factura.totalComprobante;
    self.totalCambioPagar         = self.factura.totalComprobante
    self.detail.sort(function(a,b) {
    if ( a.pesoPrioridad > b.pesoPrioridad )
        return -1;
    if ( a.pesoPrioridad < b.pesoPrioridad )
        return 1;
    return 0;
    } );
    self.update()
    $(".nombreFactura").val(self.factura.nombreFactura)
    $(".correoAlternativo").val(self.factura.correoAlternativo)
    $('#totalEfectivo').val(self.factura.totalComprobante)
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    $('#totalEfectivo').focus()
    $('#totalEfectivo').select()
   
    __aplicarExoneracionPorCliente()
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
    BuscarActividadComercial()
    if( self.factura.codigoActividad.length == 0 ){
      mensajeError($.i18n.prop("error.factura.actividad.comercial.no.existe"))
      return
    }
    self.detalleFactura.data =self.detail
    self.update() 
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
           if(self.facturaImprimir.estado == 2 || self.facturaImprimir.estado == 3 || self.facturaImprimir.estado == 4){
                __Init()
                //Envia a la pantalla de impresion
                self.facturaReimprimir = modeloTabla
                self.update()
                if(self.vueltoImprimir == 0){
                    var mensaje = "Cons# :"+   self.facturaImprimir.numeroConsecutivo        
                    swal({
                        position: 'top-end',
                        type: 'success',
                        title: mostrarMensajeCreacionConsecutivo(self.facturaImprimir),
                        showConfirmButton: false,
                        timer: 1500
                     })
                }else{
                    var parametros = {
                          factura:modeloTabla,
                          facturaDia:0
                      }
                      console.log("consultaFactura")
                      riot.mount('ptv-imprimir',{parametros:parametros});
                }
               
            }else{
                swal({
                position: 'top-end',
                type: 'success',
                title:mostrarMensajeCreacionConsecutivo(self.facturaImprimir),
                showConfirmButton: false,
                timer: 1000
                })
               
            }
             self.seIncluyoUnArticulo =null
                self.update()
                __Init()
                __ListaFacturasEnEspera()
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
    $('.totalEfectivo').val(null)
    $('.totalTarjeta').val(null)
    $('.totalBanco').val(null)
    self.factura.totalEfectivo =0
    self.factura.totalTarjeta =0
    self.factura.totalBanco =0
    self.totalCambioPagar =0
    self.mostrarFormularioPago = false
    self.mostarParaCrearNuevaVentas = true;
    self.mostarParaCrearNuevaFactura = true
    self.primeraVezBilleteClick = false
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
        swal("Verificar","No hay detalles en la factura ", "info")
        return
    }
    self.mostarParaCrearNuevaVentas = false
      self.factura.totalEfectivo = self.factura.totalComprobante
        self.update()
    $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(3))
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    getSubTotalGeneral()
    self.mostarParaCrearNuevaVentas = false
    self.factura.totalCambioPagar =0
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
            self.item.cantidad = self.item.cantidad + parseFloat(cantidadAct)
            self.update();
            ActualizarLineaDEtalle()
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
        global: false,
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
                            mensajeError($.i18n.prop("error.articulo.inactivo.inventario"))
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
     if(self.detail.length == 0){ // first element
        __nuevoArticuloAlDetalle(cantidad);
        self.seIncluyoUnArticulo = 0
        self.update()
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
      
    var resultaMontoImpuesto = parseFloat(self.articulo.impuesto)
    var precioUnitario  = getPrecioUnitario(resultadoPrecio,resultaMontoImpuesto)
    resultaMontoImpuesto = parseFloat(self.articulo.impuesto1) 
    precioUnitario      = getPrecioUnitario(precioUnitario,resultaMontoImpuesto)
    var montoTotal      = getMontoTotal(precioUnitario,cantidad)
    var montoDescuento  = 0
    var naturalezaDescuento = ""
    var subTotal        = montoTotal
    var montoImpuesto1  = _calcularImpuesto(subTotal,parseFloat(self.articulo.impuesto1) ==null?0:parseFloat(self.articulo.impuesto1))
    var montoImpuesto   = _calcularImpuesto(subTotal+montoImpuesto1,parseFloat(self.articulo.impuesto) ==null?0:parseFloat(self.articulo.impuesto))
    var montoTotalLinea = subTotal + montoImpuesto + montoImpuesto1  
    self.pesoPrioridad  =  self.pesoPrioridad + 1
    self.numeroLinea    = self.numeroLinea + 1
    self.cantArticulos  = self.cantArticulos + 1
    var costoTotal      = parseFloat(self.articulo.costo) > precioUnitario ?0:parseFloat(self.articulo.costo); 
    var ganancia        = __ObtenerGananciaProductoNuevoIngresado(0,precioUnitario,self.articulo.costo ==null?0:parseFloat(self.articulo.costo),cantidad)
    self.detail.push({
       numeroLinea     : parseFloat(self.numeroLinea),
       pesoPrioridad   : self.pesoPrioridad,  
       tipoImpuesto    : self.articulo.tipoImpuesto ==null?" ":self.articulo.tipoImpuesto,
       tipoImpuesto1   : self.articulo.tipoImpuesto1 ==null?" ":self.articulo.tipoImpuesto1,
       iva             : parseFloat(self.articulo.impuesto),
       iva1            : parseFloat(self.articulo.impuesto1),
       codigo          : self.articulo.codigo,
       descripcion     : self.articulo.descripcion,
       cantidad        : parseFloat(cantidad),
       precioUnitario  : parseFloat(precioUnitario),
       impuesto        : parseFloat(self.articulo.impuesto),
       impuesto1        : parseFloat(self.articulo.impuesto1),
       montoImpuesto   : parseFloat(montoImpuesto),
       montoImpuesto1  : parseFloat(montoImpuesto1),
       montoDescuento  : 0,
       porcentajeDesc  : 0,
       ganancia        : parseFloat(ganancia),
       montoGanancia   : parseFloat(ganancia),
       subTotal        : parseFloat(subTotal),
       montoTotalLinea : parseFloat(montoTotalLinea),
       montoTotal      : parseFloat(montoTotal),
       costo           : costoTotal,
       porcentajeGanancia :   getListaPrecioGanancia(self.articulo) ==null?0:parseFloat(getListaPrecioGanancia(self.articulo)),
       pesoTransporte :  parseFloat(self.articulo.pesoTransporte),
       pesoTransporteTotal :parseFloat(self.articulo.pesoTransporte),
       montoExoneracion:0,
       montoExoneracion1:0,
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
    self.totalGananciaByProducto = formatoDecimales(parseFloat(ganancia),2)
    self.update()
}

function getListaPrecio(articulo){
    //Precio Publico
    var resultado=  parseFloat(articulo.precioPublico )
    return resultado > 0 ?resultado:parseFloat(articulo.precioPublico )

}

function getListaPrecioGanancia(articulo){
    //Precio Publico
     var resultado=  parseFloat(articulo.gananciaPrecioPublico )
    return resultado > 0 ?resultado:parseFloat(articulo.gananciaPrecioEspecial )

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
    cantidad = __valorNumerico(redondeoDecimales(cantidad,3))
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
__cambiarElPrecio(){
    var precio = $(".cambiarprecioArticulo").val();
     self.item.precioUnitario = __valorNumerico(precio);
     self.update()
    agregarPrecioAlDetalle(precio)
}
/**
* Cambiar el precio en el detalle
**/
function agregarPrecioAlDetalle(precio){
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
}
/**
*Monto en el descuento
**/
function getMontoDescuento(precioUnitario,cantidad,porcentajeDesc,porcentajeGanancia){
    if(porcentajeDesc == 0){
        return 0
    }
    if(porcentajeDesc > 100){
        porcentajeDesc = 100
    }
    self.item.porcentajeDesc = porcentajeDesc
    self.update()

    var porcentaje =  porcentajeGanancia;
    if(porcentajeDesc != porcentajeGanancia){
       porcentaje =  porcentajeDesc;
    }
    porcentaje = porcentaje/ 100;
    if(porcentajeDesc ==100){
        porcentaje = 0
    }
    var totalDescuento =  precioUnitario * cantidad
    var resultado = porcentaje >0?totalDescuento * porcentaje:totalDescuento;
    return resultado
}
/**
*Actualizar linea en el detalle
**/
function ActualizarLineaDEtalle(){
    var montoTotal             = getMontoTotal(self.item.precioUnitario,self.item.cantidad)
    var montoDescuento         = getMontoDescuento(self.item.precioUnitario,self.item.cantidad,self.item.porcentajeDesc,self.item.porcentajeGanancia)
    var subTotal               = montoTotal > montoDescuento?montoTotal - montoDescuento: montoDescuento-montoTotal
    montoImpuesto1             = _calcularImpuesto(subTotal,self.item.impuesto1 ==null?0:self.item.impuesto1)
    var resultadoMontoImpuesto1 = montoImpuesto1 + subTotal;
    var montoImpuesto          = _calcularImpuesto(resultadoMontoImpuesto1,self.item.impuesto ==null?0:self.item.impuesto)
    var montoTotalLinea        = subTotal + montoImpuesto + montoImpuesto1    
    self.item.montoTotal       = montoTotal
    self.item.montoDescuento   = montoDescuento
    self.item.subTotal         = subTotal
    self.item.montoImpuesto    = montoImpuesto
    self.item.montoImpuesto1   = montoImpuesto1
    self.item.montoTotalLinea  = montoTotalLinea
    self.item.ganancia         = __ObtenerGananciaProductoNuevoIngresado(montoDescuento,self.item.precioUnitario,self.item.costo ==null?0:parseFloat(self.item.costo),self.item.cantidad)
    self.totalGananciaByProducto = formatoDecimales(parseFloat(self.item.ganancia),2)
    self.update()
}
/**
* Agregar en la cantidad la Venta
**/
function agregarCantidadAlaVenta(cantidad){
    self.item.cantidad = cantidad
    var ganancia        = __ObtenerGananciaProductoNuevoIngresado(0,self.item.precioUnitario,self.item.costo ==null?0:parseFloat(self.item.costo),cantidad)
    self.item.ganancia = ganancia
    self.totalGananciaByProducto = formatoDecimales(parseFloat(ganancia),2)
    self.update()
    ActualizarLineaDEtalle()
    aplicarCambioLineaDetalle() 
    cambiarCantidadArticulo.value = 0
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
__actualizarDescuento(e){
     if ($("#formularioDescuento").valid()) {
        _actualizarDesc(e)
     }
}
/**
*Actualizar el descuento
**/
function _actualizarDesc(e){
    var index     = self.detail.indexOf(self.item);
    var descuento = $(".aplicarDescuento").val();
     if(descuento > 100){
         swal('',"Error el descuento no puede ser mayor al 100%",'error');
         return false
    }
    if(descuento > 100){
        descuento =100
    }
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
    calcularImpuestoServicio()
    self.factura.total            = 0;
    self.factura.totalDescuentos  = 0;
    self.factura.totalImpuesto    = 0;
    self.factura.subTotal         = 0;
    self.update()
    totalVenta     = 0
    subTotal       = 0
    totalDescuento = 0
    totalImpuesto  = 0
    totalImpuesto1  = 0
    totalMercanciasGravadas = 0
    totalMercanciasExentas  = 0
    totalServGravados       = 0
    totalServExentos        = 0
    totalGravado            = 0
    totalExento             = 0
    totalComprobante        = 0
    totalventaNeta          = 0
    self.cantArticulos      = 0
    var montoExoneracion = 0
    self.detail.forEach(function(e){
        totalMercanciasGravadas += e.montoImpuesto > 0 && e.tipoImpuesto != "07"?e.montoTotal:0
        totalMercanciasGravadas += e.montoImpuesto1 > 0 && e.tipoImpuesto1 != "07"?e.montoTotal:0
        totalMercanciasExentas  += e.impuesto == 0 && e.tipoImpuesto != "07"?e.montoTotal:0
        totalMercanciasExentas  += e.impuesto1 == 0 && e.tipoImpuesto1 != "07"?e.montoTotal:0
        totalServGravados       += e.montoImpuesto > 0 && e.tipoImpuesto == "07"?e.montoTotal:0
        totalServGravados       += e.montoImpuesto1 > 0 && e.tipoImpuesto1 == "07"?e.montoTotal:0
        totalServExentos        += e.impuesto == 0 && e.tipoImpuesto == "07"?e.montoTotal:0
        totalServExentos        += e.impuesto1 == 0 && e.tipoImpuesto1 == "07"?e.montoTotal:0
        totalGravado            += e.impuesto > 0 ?e.montoTotal:0
        totalGravado            += e.impuesto1 > 0 ?e.montoTotal:0
        totalExento             += e.impuesto == 0 && e.impuesto1 == 0?e.montoTotal:0
        totalComprobante        += e.montoTotalLinea
        subTotal                += e.subTotal >0?e.subTotal:0
        totalDescuento          += e.montoDescuento >0?e.montoDescuento:0
        totalImpuesto           += __valorNumerico(e.montoImpuesto)
        totalImpuesto1          += __valorNumerico(e.montoImpuesto1)
        totalVenta              += e.montoTotal
        self.cantArticulos      += esEntero(e.cantidad) == true? e.cantidad:1 
         montoExoneracion        += parseFloat(e.montoExoneracion) 
         montoExoneracion        += parseFloat(e.montoExoneracion1) 
    });
    self.factura.totalMercanciasGravadas = __valorNumerico(totalMercanciasGravadas)
    self.factura.totalMercanciasExentas  = __valorNumerico(totalMercanciasExentas)
    self.factura.totalServGravados       = __valorNumerico(totalServGravados)
    self.factura.totalServExentos        = __valorNumerico(totalServExentos)
    self.factura.totalGravado            = __valorNumerico(totalGravado)
    self.factura.totalExento             = __valorNumerico(totalExento)
    //cuando se aplica descuentos
    self.factura.totalVenta              = __valorNumerico(totalVenta)
    self.factura.totalDescuentos         = __valorNumerico(totalDescuento)
    self.factura.subTotal                = __valorNumerico(subTotal)
    self.factura.totalImpuesto           = __valorNumerico(totalImpuesto) + __valorNumerico(totalImpuesto1)
    self.factura.totalVentaNeta          = __valorNumerico(totalVenta-totalDescuento)
    self.factura.totalComprobante        = __valorNumerico(totalComprobante)
    self.totalComprobante                = formatoDecimales(self.factura.totalComprobante,2);
    self.totalDescuentos                 = formatoDecimales(self.factura.totalDescuentos,2);
    self.totalImpuesto                   = formatoDecimales(self.factura.totalImpuesto,2);
     self.montoExoneracion                = formatoDecimales(montoExoneracion,2);
    self.update(); 
    $('.precioVenta').val(null)
    $('.codigo').val(null)
    $('.codigo').select()
    $('.codigo').focus()
    getSubTotalGeneral()
    localStorage.setItem('DetallesNueva', JSON.stringify(self.detail));
    localStorage.setItem('facturaNueva', JSON.stringify(self.factura));

}
/**
*calculo  de impuesto servicio
**/
function calcularImpuestoServicio(){
    var resultado = 0
    var montoDescuentoTotal = 0
     self.detail.forEach(function(e){
        resultado += e.codigo != "8888" ?e.montoTotal:0;
        montoDescuentoTotal += e.montoDescuento;
    });
    //Impuesto del servicio
    resultado = resultado - montoDescuentoTotal; 
    resultado = resultado * 0.10
    resultado = Math.round(__valorNumerico(resultado))
     for (var count = 0; count < self.detail.length; count++) {
        if (self.detail[count].codigo == "8888" ){
            self.item          = self.detail[count];
            self.item.precioUnitario = resultado
            self.item.subTotal       = resultado
            self.item.montoTotal     = resultado
            self.item.montoTotalLinea = resultado
            self.update();
            ActualizarLineaDEtalle()
            self.detail[count] = self.item;
            self.update();
        }
    }
}
/**
*  Sub Total Generar de la factura
**/
function getSubTotalGeneral(){
    var resultado = __valorNumerico(self.factura.subTotal) + __valorNumerico(self.factura.totalDescuentos)
    self.subTotalGeneral = formatoDecimales(resultado,2)
    self.totalDescuentos = formatoDecimales(self.factura.totalDescuentos,2)
    var resultadoTotalImpuesto = __valorNumerico(self.factura.totalImpuesto) + __valorNumerico(self.factura.totalImpuesto1)
    self.totalImpuesto   = formatoDecimales(resultadoTotalImpuesto,2)
    self.update()
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
                                        {'data' : 'cedula'           ,"name":"cedula"            ,"title" : $.i18n.prop("cliente.cedula")            ,"autoWidth":false},
                                        {'data' : 'nombreCompleto'   ,"name":"nombreCompleto"    ,"title" : $.i18n.prop("cliente.nombreCompleto")    ,"autoWidth":false},
                                        {'data' : 'nombreComercial'   ,"name":"nombreComercial"    ,"title" : $.i18n.prop("cliente.nombreComercial")    ,"autoWidth":false},
                                        {'data' : 'correoElectronico',"name":"correoElectronico" ,"title" : $.i18n.prop("cliente.correoElectronico") ,"autoWidth":false},
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
        __aplicarExoneracionPorCliente()
        
        
         $('#modalClientes').modal('hide') 
       
        
    });
}

/**
* Aplicar la exoneracion de detalles
**/
function __aplicarExoneracionPorCliente(){
    var aplicaExo = false
    var porcentaje = self.cliente.libreImpuesto == 1?1:self.cliente.porcentajeExoneracion / 100
    var valorTotal = 0
    for (var count = 0;count < self.detail.length; count++) {
        self.item = self.detail[count];
        self.cliente.porcentajeExoneracion = parseFloat(self.cliente.porcentajeExoneracion)
            if(self.item.montoImpuesto > 0 || self.item.montoImpuesto1 > 0 ){
                if(self.cliente.porcentajeExoneracion > 0 || self.cliente.libreImpuesto == 1 ){
                    self.item.porcentajeExoneracion = self.cliente.libreImpuesto == 1?100:parseFloat(self.cliente.porcentajeExoneracion)
                    self.item.fechaEmisionExoneracion = self.cliente.fechaEmisionExoneracion
                    self.item.nombreInstitucionExoneracion = self.cliente.libreImpuesto == 1?self.cliente.nombreCompleto:self.cliente.nombreInstitucionExoneracion
                    self.item.numeroDocumentoExoneracion = self.cliente.numeroDocumentoExoneracion
                    self.item.tipoDocumentoExoneracion = self.cliente.libreImpuesto == 1?"AA9999999BBB":self.cliente.tipoDocumentoExoneracion
                    valorTotal = parseFloat(self.item.montoImpuesto1) * parseFloat(porcentaje)  
                    self.item.montoExoneracion1 = valorTotal
                     valorTotal = parseFloat(self.item.montoImpuesto) * parseFloat(porcentaje)  
                    self.item.montoExoneracion = valorTotal
                    self.item.ImpuestoNeto = self.item.montoImpuesto + self.item.montoImpuesto1
                    self.item.ImpuestoNeto = self.item.ImpuestoNeto - self.item.montoExoneracion1
                    self.item.ImpuestoNeto = self.item.ImpuestoNeto - self.item.montoExoneracion  
                    self.item.montoTotalLinea = self.item.subTotal +  self.item.ImpuestoNeto
                    self.detail[count] = self.item;
                    self.update();
                    aplicaExo= true
                }else{
                    //Cliente no tiene exoneracion
                    self.item.porcentajeExoneracion = 0
                    self.item.fechaEmisionExoneracion = null
                    self.item.nombreInstitucionExoneracion = ""
                    self.item.numeroDocumentoExoneracion = ""
                    self.item.tipoDocumentoExoneracion = ""
                    self.item.montoExoneracion = 0
                    self.item.montoExoneracion1 = 0
                    self.item.ImpuestoNeto = parseFloat(self.item.montoImpuesto) + parseFloat(self.item.montoImpuesto1) 
                    self.item.montoTotalLinea = self.item.subTotal +  self.item.ImpuestoNeto
                    self.detail[count] = self.item;
                    self.totalCambioPagar = 0
                    self.totalCambioPagarSTR = 0
                    self.factura.totalEfectivo =0
                    self.factura.totalTarjeta =0
                    self.factura.totalBanco =0
                    self.factura.totalCambioPagar = self.factura.totalComprobante
                    self.update();
                    $('#totalEfectivo').val(0)
                    $('#totalTarjeta').val(null)
                    $('#totalBanco').val(null)
                    $('#totalEfectivo').focus()
                    $('#totalEfectivo').select()
                    aplicaExo = true
                }
               
            }
    }
    __calculate()
    if(aplicaExo == true){
       $('#totalEfectivo').val(0)
       $('#totalTarjeta').val(null)
       $('#totalBanco').val(null)
       $('#totalEfectivo').focus()
       $('#totalEfectivo').select()
       self.factura.totalCambioPagar = self.factura.totalComprobante
       self.factura.totalEfectivo =0
       self.factura.totalTarjeta =0
       self.factura.totalBanco =0
       self.totalCambioPagar = 0
       self.totalCambioPagarSTR = 0
       self.update();

    }
    if(verificarClienteFrecuente()){
      __ComboTipoDocumentos()
    }else{
        __ComboTipoDocumentosSinClienteFrecuente()
    }

}
function verificarClienteFrecuente(){
    if(self.cliente == null){
        return false;
    }
    if(self.cliente.nombreCompleto.indexOf("CLIENTE_FRECUENTE") != -1){
        return true;        
    }
    if(self.cliente.cedula.indexOf("999999999999") != -1){
       return true; 
    }
    return false;
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
    // Tipo documento unicamente proforma y factura 
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
        self.comboTipoDocumentos.push({
            estado:"88",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.proforma")
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
    self.comboTipoDocumentos.push({
         estado:"88",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.proforma")
    })

    }
    self.update()
}

function __ComboTipoDocumentosSinClienteFrecuente(){
    self.comboTipoDocumentos = []
    self.update()
    // Tipo documento unicamente proforma y factura 
        self.comboTipoDocumentos.push({
            estado:"01",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
        })
        self.comboTipoDocumentos.push({
            estado:"04",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
        })
        self.comboTipoDocumentos.push({
            estado:"88",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.proforma")
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
        if ( $(this).index() != 0    ){
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
        if ( $(this).index() != 3    ){
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
      if(self.mostrarFormularioPago == false && self.mostarParaCrearNuevaVentas == true){
        self.factura.totalCambioPagar =__valorNumerico(self.factura.totalComprobante)   
        self.totalCambioPagar = redondeoDecimales(self.factura.totalComprobante,2)
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
       __Init()
    }
      //Insert = abrir Cajon
    if(tecla ==45){
       __OpcionAbrirCajon()
    }
    }, false );
}

function refrescarPagina(){
   __Init()
     $('.codigo').select()
      $(".codigo").focus()
 
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
    }else{
       self.factura.totalEfectivo += __valorNumerico(item.valor) 
       $('.efectivo').val(self.factura.totalEfectivo)
        self.update()
        var sumaMontosEntregadosParaCambios =__valorNumerico(self.factura.totalTarjeta)
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco) 
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo) 
        self.factura.totalCambioPagar = 0
        self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios - __valorNumerico(self.factura.totalComprobante)
        self.claseCambioDinero  = __valorNumerico(sumaMontosEntregadosParaCambios) > __valorNumerico(self.factura.totalComprobante)?'entregarCambioPositivo':'entregarCambioNegativo'
        self.totalCambioPagar = redondeoDecimales(self.factura.totalCambioPagar,2)
        self.totalCambioPagarSTR =formatoDecimales(self.totalCambioPagar,2)
        $(".totalEfectivo").val(self.factura.totalEfectivo) 
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
                    sweetAlert("", data.message, "error");
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
</venta-factura>