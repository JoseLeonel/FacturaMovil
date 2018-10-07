<venta-factura>




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
                            <label class="knob-label" >{$.i18n.prop("inventario.cantidad")}</label>
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
                <a class="pull-left" href="#"    onclick = {__ImprimirTiquete}  title="{$.i18n.prop("imprimir.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f7")}</span></a>
                <a class="pull-left" href="#"    onclick = {__MostrarFormularioDePago}  title="{$.i18n.prop("crear.ventas")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f8")}</span></a>
                <a class="pull-left" href="#"    onclick= { __CrearFacturaTemporal}  title="{$.i18n.prop("btn.tiquete")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f9")}</span></a>
                <a class="pull-left" href="#"    onclick = {__Limpiar} title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-limpiar">{$.i18n.prop("factura.f10")}</span></a>
                <a class="pull-right" href="#"   title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-articulos">{descripcionArticulo}</span></a>
            </div>
        </div>      
    </div>              
    <div>
        <form id="FormPaginacion">
            <input type="hidden" name="id" id="id" value="{parametrosPaginacion.cantidadPorPagina}">
            <input type="hidden" name="cantidadPorPagina" id="cantidadPorPagina" value="{parametrosPaginacion.cantidadPorPagina}">
            <input type="hidden" name="paginaActual" id="paginaActual" value="{parametrosPaginacion.paginaActual}">
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
<div show={mostarParaCrearNuevaVentas}>
    <div class="container-fluid">
        <div class="row no-space">
            <div class="col-md-5 col-sm-5 col-lg-5 col-xs-12 pull-right" style="padding: 0px 12px">
                <div class="block panel ">                    
                    <div id="listadoProdcutos">{$.i18n.prop("titulo.listado.venta")}   {factura.id>0?factura.id:'' } {factura.nombreFactura}</div>
                    <hr style="margin: 2px 0px 0px 0px; border-color: #e4e4e4; margin-top: 0px">
                    <div class="data-fluid">
                        <div id="listaProductos" style="height:200px; overflow-x: hidden; width:100%">
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
                                            <span onclick ={__CambiarCantidad} class="label label-success cantidad clickable">{cantidad.toFixed(3)}</span>
                                        </td>
                                        <td >
                                            <span  onclick ={__CambiarPrecio}   class="label label-success precio-prod" >{precioUnitario.toFixed(2)}</span>
                                        </td>
                                        <td >
                                            <span onclick ={__CambiarDescuento} class="label label-success precio-prod" >{porcentajeDesc.toFixed(2)}</span>
                                        </td>
                                        <td >
                                            <span class="label label-success " >{impuesto.toFixed(2)}</span>
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
                        <a class="pull-right" href="#"   title="{$.i18n.prop("btn.limpiar")}"> <span class="label-titulos-articulo">Tikete a :{factura.nombreFactura}</span></a>
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
                                <span class="label-titulos-espera"> {nombreFactura ==null?"T#"+id:""}  {nombreFactura}</span>
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
                    <section show= {mostrarCategorias} class="lista-articulos" >
                        <div show= {mostrarCategorias} id="item-categorias"class="product-item"  each ={categorias.data}  onclick={__ArticulosXCategorias}>
                            <img  style = "width:120px;" alt="" class="img-responsive " src="/dist/img/carrito1.png">
                             <span class="label-titulos-articulo">{descripcion}</span>
                        </div>
                    </section>
                    <!--Seccion de articulos-->
                    <section show= {mostrarArticulosXCategoria} class="lista-articulos" >
                        <div class="product-item"  each ={inventariosXCategoria.data}   onclick={__AgregarProductoDePantalla}>
                            <img  style = "width:80px;" alt="" class="img-responsive " src="/dist/img/carrito3.png">
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
                <form id = "formularioAgregarNombreTiquete" name ="formularioAgregarNombreTiquete "   class="advanced-search-form">
                    <div class="row">   
                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                            <label class="knob-label" >{$.i18n.prop("factura.nombreFactura")}</label>
                            <input type="text" class="campo tamanoLetraTotales cambioNombreFactura"  id="cambioNombreFactura" name="cambioNombreFactura" autofocus="autofocus" >
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
                            <input  type="number"  class="form-control cambiarprecioArticulo" id="cambiarprecioArticulo" name = "cambiarprecioArticulo" autofocus="autofocus">
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
                <div class="row">
                    <div class="col-sx-12 col-md-12 col-lg-12 col-sm-12">
                        <div class="form-group has-success">
                            <label >{$.i18n.prop("factura.linea.detalle.descuento")}</label>
                            <input  type="number" class="campo tamanoLetraTotales aplicarDescuento" id="aplicarDescuento" name = "aplicarDescuento" autofocus="autofocus">
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
<div id="modalClientes" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
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
                        <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("cliente.cedula")}           </th>
                            <th>{$.i18n.prop("cliente.nombreCompleto")}   </th>
                            <th>{$.i18n.prop("cliente.correoElectronico")}</th>
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
                                     <div class="form-group ">
                                        <label>{$.i18n.prop("factura.nombreFactura")}</label> 
                                        <input type="text" id="nombreFactura" name="nombreFactura" class="form-control"  value="{factura.nombreFactura}" >
                                    </div>
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("factura.correoAlternativo")}</label> 
                                        <input type="email" id="correoAlternativo" name="correoAlternativo" class="form-control"  value="" >
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
                            <input type="hidden" id='id'                      name='id'                      value="{factura.id}" >
                            <input type="hidden" id='plazoCredito'            name='plazoCredito'            value="{factura.plazoCredito}" >
                            <input type="hidden" id='estado'                  name='estado'                  value="{factura.estado}" >
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
                                        <a href="#" class="label-totales">{modena} {descripcion}</a>
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
                                <h1><span id="lblSCS">{$.i18n.prop("factura.resumen.venta")}</span></h1>
                                    <div class="booking-info">
                                        <p class="total label-totales" style="text-align:right">{$.i18n.prop("factura.resumen.subTotal")}  <span id="lblSubtotal"> {subTotalGeneral} </span></p>
                                        <p class="total label-totales" style="text-align:right">{$.i18n.prop("factura.resumen.descuento")}  <span id="lblSubtotal"> {totalDescuentos} </span></p>
                                        <p class="total label-totales" style="text-align:right">{$.i18n.prop("factura.resumen.impuesto")}   <span id="lblSubtotal"> {totalImpuesto} </span></p>
                                    </div>
                                    <div class="precioTotalFactura">
                                        <p class="total label-totales" style="text-align:right;">{$.i18n.prop("factura.resumen.total")}   <span id="lblTotal">{totalComprobante}</span></p>
                                        
                                    </div>
                                    <div class="{claseCambioDinero}" show={mostrarCamposIngresoContado}>
                                        <p class="total label-totales" style="text-align:right;">{$.i18n.prop("factura.resumen.cambio")} <span id="lblTotal">{totalCambioPagar}</span></p>    
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
    self.mostrarImpuestoServicio = false

    self.urlImagenNavegador   = '/dist/img/navegador.png';
    self.urlImagenLector      = '/dist/img/codigo_barra.png';
    self.urlImagenBuscador    = '/dist/img/buscador.png';

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
       
       
     
        cargaBilletes()
        mostrarCategorias()
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
__LimpiarFormulario(){
    $(".plazoCredito").val(null)   
    $(".fechaCredito").val(null)   
     $(".totalEfectivo").val(null)   
    $(".totalTarjeta").val(null)   
    $(".totalBanco").val(null)   
    $(".nota").val(null)   
    $(".direccion").val(null)   
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
                               {'data' :'fechaEmisionSTR'   ,"name":"fechaEmisionSTR"    ,"title" : $.i18n.prop("factura.fecha.emision")     ,"autoWidth" :true 
                               },
                             
                               {'data' :'numeroConsecutivo'                    ,"name":"numeroConsecutivo"                     ,"title" : $.i18n.prop("factura.documento")   ,"autoWidth" :true ,
                                   "render":function(numeroConsecutivo,type, row){
									    return __TipoDocumentos(numeroConsecutivo,row)
	 							    }
                               },
                               {'data' :'cliente'                    ,"name":"cliente"                     ,"title" : $.i18n.prop("factura.cliente")   ,"autoWidth" :true ,
                                   "render":function(cliente,type, row){
									    return cliente ==null?"":cliente.nombreCompleto;
	 							    }
                               },
                               {'data' :'totalImpuesto'       ,"name":"totalImpuesto"        ,"title" : $.i18n.prop("factura.linea.detalle.impuesto")     ,"autoWidth" :true ,
                                    "render":function(totalImpuesto,type, row){
									    return  formatoDecimales(totalImpuesto,2);
	 							    }
                               },
                               {'data' :'totalDescuentos'                ,"name":"totalDescuentos"                 ,"title" : $.i18n.prop("factura.linea.detalle.descuento")  ,"autoWidth" :true ,
                                    "render":function(totalDescuentos,type, row){
									    return  formatoDecimales(totalDescuentos,2);
	 							    }
                               },
                               {'data' :'totalComprobante'               ,"name":"totalComprobante"                ,"title" : $.i18n.prop("factura.total") ,"autoWidth" :true ,
                                    "render":function(totalComprobante,type, row){
									    return  formatoDecimales(totalComprobante,2);
	 							    }
                               },
                               {'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
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
        var factura = data
        consultaParaReimprimir(data)
	});
}

/**
*Consulta la Reimprimir
**/
function consultaParaReimprimir(data){
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
                    //   $('#modalFacturasDia').modal('hide') 
                       riot.mount('ptv-imprimir',{factura:modeloTabla});
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
* Aplicar el descuento
**/
__CambiarDescuento(e){
    self.item = e.item; 
    self.update()
     $('#modalCambiarDescuento').modal({backdrop: 'static', keyboard: false})     // initialized with no keyboard
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
   $('#modalCambiarCantidad').modal()                      // initialized with defaults
   $('#modalCambiarCantidad').modal({backdrop: 'static', keyboard: false})     // initialized with no keyboard
   $('#modalCambiarCantidad').modal('show')                // initializes and invokes show immediately
}
/**
*Cambiar descripcion
**/
__CambiarDescripcion(e){
    console.log(e)
   self.item = e.item; 
   self.update()
  
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
    riot.mount('ptv-imprimir',{factura:factura});
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
        self.factura.totalCambioPagar = self.factura.totalComprobante * -1
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
            self.factura.totalCambioPagar = self.factura.totalComprobante * -1
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
        self.update()
    }
}
/**
 * Listar codigos  llamado del modal para presentar los articulos
 **/   
 __ListaDecodigos(){
     self.mostrarListadoArticulos = true
     self.update()
     __ListaDeArticulosPorEmpresa();
     
 }
/**
*  Buscar la Factura Pendiente en espera
**/
__CargarFacturaEspera(e){
    self.factEspera = e.item
    self.update()
    if(self.factura.id !=null){
      if(self.seIncluyoUnArticulo !=null){
        aplicarFactura(1)  
        self.seIncluyoUnArticulo =null
        self.update()
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
    if(self.factura.id == null){
        
        if(self.detail.length != 0 ){
            $('#ModalAgregarNombreTiquete').modal('show') 
            $('.cambioNombreFactura').focus()
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
            if(self.factura.totalTarjeta == 0 && self.factura.totalBanco == 0 && self.factura.totalEfectivo == 0){
                mensajeError($.i18n.prop("error.factura.monto.ingresado"))
                return
            }
            var montoEntregado = __valorNumerico($('#totalTarjeta').val())  + __valorNumerico($('#totalBanco').val()) + __valorNumerico($('#totalEfectivo').val())
            montoEntregado = redondeoDecimales(__valorNumerico(montoEntregado),2)
            var resultado  = redondeoDecimales( __valorNumerico(self.factura.totalComprobante),2)
            if(__valorNumerico(resultado) > __valorNumerico(montoEntregado)  ){
                mensajeError($.i18n.prop("error.factura.monto.ingresado.es.menor.ala.venta"))
                return
            }
            //Si el cliente esta pagando con tajeta, banco debe ser igual a la venta
            if(self.factura.totalTarjeta != 0 || self.factura.totalBanco !=0){
                if(self.factura.totalComprobante != montoEntregado  ){
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
    self.update()
    $('#condicionVenta').prop("selectedIndex", 0);
    $('#tipoDoc').prop("selectedIndex", 0);
    $('#estado').prop("selectedIndex", 0);
    $(".totalBanco").val(null)   
    $(".totalTarjeta").val(null)   
    $(".totalEfectivo").val(null)   
    $("#plazoCreditoL").val(null)
    $("#nota").val(null)
    $("#fechaCredito").val(null)
    $("#cambiarCantidadArticulo").val(null)
    $("#aplicarDescuento").val(null)
    // Tipo de Pagos
     __comboCondicionPago()
     //Tipos de Documentos
      __ComboTipoDocumentos()
      //Estados
      __ComboEstados()
     __ListaFacturasEnEspera()
     mostrarCategorias()
     
     $('#codigoBarra').val(null)
     $('#codigoBarra').focus()
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
                       self.totalComprobante         = formatoDecimales(modeloTabla.totalComprobante,2);
                       self.factura.totalEfectivo    = 0
                       self.factura.totalBanco       = 0
                       self.factura.totalTarjeta     = 0
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
    $('#codigoBarra').val(null)
    $('#codigoBarra').focus()
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
            cantidad        : parseFloat(e.cantidad),
            precioUnitario  : parseFloat(e.precioUnitario),
            impuesto        : parseFloat(e.impuesto),
            montoImpuesto   : parseFloat(e.montoImpuesto),
            montoDescuento  : parseFloat(e.montoDescuento),
            porcentajeDesc  : parseFloat(e.porcentajeDesc),
            subTotal        : parseFloat(e.subTotal),
            montoTotalLinea : parseFloat(e.montoTotalLinea),
            montoTotal      : parseFloat(e.montoTotal)
        });
        self.update()
    })
    self.totalCambioPagar = 0
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
            if(self.facturaImprimir.estado == 2){
                __Init()
                //Envia a la pantalla de impresion
                __ListaFacturasEnEspera()
                self.facturaImprimir   = modeloTabla
                self.update()
                riot.mount('ptv-imprimir',{factura:self.facturaImprimir});
                 
            }else{
               
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
     $('#totalEfectivo').val(self.factura.totalComprobante)
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
* mostrar la lista de articulos de la empresa
**/
function __ListaDeArticulosPorEmpresa(){
    $.ajax({
        url: 'ListarArticulosActivosAjax.do',
        datatype: "json",
        method:"POST",
        success: function (result) {
            if(result.aaData.length > 0){
                _informacionData_Articulo()
                self.articulos.data           = result.aaData
                self.update()
                loadListar(".tableListarArticulos",idioma_espanol,self.informacion_tabla_articulo,self.articulos.data)
                agregarInputsCombos_Articulo()
                __agregarArticulos()
                ActivarEventoFiltro(".tableListarArticulos")
                if(self.mostrarListadoArticulos == true){
                  $('#modalInventario').modal('show')    
                }
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
    var item = e.item;
    index = this.detail.indexOf(item);
    this.detail.splice(index, 1);
    var cont = 0 ;
    self.detail.forEach(function(elemen){
            elemen.numeroLinea = cont + 1
            cont = elemen.numeroLinea
        }
    )
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
    var precioUnitario  = getPrecioUnitario(self.articulo.precioPublico,self.articulo.impuesto)
    var montoTotal      = getMontoTotal(precioUnitario,cantidad)
    var montoDescuento  = 0
    var naturalezaDescuento = ""
    var subTotal        = montoTotal
    var montoImpuesto   = _calcularImpuesto(subTotal,parseFloat(self.articulo.impuesto) ==null?0:parseFloat(self.articulo.impuesto))
    var montoTotalLinea = subTotal + montoImpuesto 
    self.detail.push({
       numeroLinea     : 1,
        tipoImpuesto    : self.articulo.tipoImpuesto ==null?" ":self.articulo.tipoImpuesto,
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
/**
* Monto Total de la Facturra 
**/
function getMontoTotal(precioUnitario,cantidad){
    var resultado = parseFloat(precioUnitario) * parseFloat(cantidad)
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
    var impuesto = iva > 0 ?parseFloat(iva)/100:0
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
function getMontoDescuento(precioUnitario,cantidad,porcentajeDesc){
    var porcentaje = porcentajeDesc / 100;
    var total =  precioUnitario * cantidad
    var resultado = total * porcentaje
    return resultado
}
/**
*Actualizar linea en el detalle
**/
function ActualizarLineaDEtalle(){
  var montoTotal               = getMontoTotal(self.item.precioUnitario,self.item.cantidad)
    var montoDescuento         = getMontoDescuento(self.item.precioUnitario,self.item.cantidad,self.item.porcentajeDesc)
    var subTotal               = montoTotal - montoDescuento
    var montoImpuesto          = _calcularImpuesto(subTotal,self.item.impuesto ==null?0:self.item.impuesto)
    var montoTotalLinea        = subTotal + montoImpuesto    
    self.item.montoTotal       = montoTotal
    self.item.montoDescuento   = montoDescuento
    self.item.subTotal         = subTotal
    self.item.montoImpuesto    = montoImpuesto
    self.item.montoTotalLinea  = montoTotalLinea
    self.update()
}
/**
* Agregar en la cantidad la Venta
**/
function agregarCantidadAlaVenta(cantidad){
    self.item.cantidad = cantidad
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
    _actualizarDesc(e)
}
/**
*Actualizar el descuento
**/
function _actualizarDesc(e){
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
    self.factura.totalMercanciasGravadas = __valorNumerico(totalMercanciasGravadas)
    self.factura.totalMercanciasExentas  = __valorNumerico(totalMercanciasExentas)
    self.factura.totalServGravados       = __valorNumerico(totalServGravados)
    self.factura.totalServExentos        = __valorNumerico(totalServExentos)
    self.factura.totalGravado            = __valorNumerico(totalGravado)
    self.factura.totalExento             = __valorNumerico(totalExento)
    //cuando se aplica descuentos
    self.factura.totalVenta              = Math.round(__valorNumerico(totalVenta))
    self.factura.totalDescuentos         = Math.round(__valorNumerico(totalDescuento))
    self.factura.subTotal                = Math.round(__valorNumerico(subTotal))
    self.factura.totalImpuesto           = Math.round(__valorNumerico(totalImpuesto))
    self.factura.totalVentaNeta          = Math.round(__valorNumerico(totalVenta-totalDescuento))
    self.factura.totalComprobante        = Math.round(__valorNumerico(totalComprobante))
    self.totalComprobante                = formatoDecimales(self.factura.totalComprobante,2);
    self.totalDescuentos                 = formatoDecimales(self.factura.totalDescuentos,2);
    self.totalImpuesto                   = formatoDecimales(self.factura.totalImpuesto,2);

  //  self.articulo              = null;
    self.update(); 
    $( "#codigoBarra" ).val(null);
    $( "#quantity" ).val(null);
    getSubTotalGeneral()
}

function calcularImpuestoServicio(){
    var resultado = 0
     self.detail.forEach(function(e){
        resultado += e.montoTotal
    });
    //Impuesto del servicio
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
    self.totalImpuesto   = formatoDecimales(self.factura.totalImpuesto,2)
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
        self.cliente = data
        self.update();
    });
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
        if ( $(this).index() != 3    ){
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
        self.update()
         mostrarPAgo()     
      }else if (self.mostrarFormularioPago == true && self.mostarParaCrearNuevaVentas == false ){
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
    if(tecla ==121){
      __Init()
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
        self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios - __valorNumerico(self.factura.totalComprobante)
        self.claseCambioDinero  = __valorNumerico(sumaMontosEntregadosParaCambios) > __valorNumerico(self.factura.totalComprobante)?'entregarCambioPositivo':'entregarCambioNegativo'
        self.totalCambioPagar = redondeoDecimales(self.factura.totalCambioPagar,2)

    }
    self.update()
}
/**
* Cargar Billetes
**/
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