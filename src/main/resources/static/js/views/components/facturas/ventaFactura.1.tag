<venta-factura>




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
                                        <span class="title-detalle text-info">
                                        <input type="hidden" value="0" class="detalles-impuesto">{descripcion}</span>
                                        </td>
                                        <td >
                                        <span onclick ={__CambiarCantidad} class="label label-success cantidad">{cantidad.toFixed(3)}</span>
                                        </td>
                                        <td class="contCalc">
                                        <span class="label label-success precio-prod" >{precio.toLocaleString('de-DE')}</span>
                                        </td>
                                        <td class="contCalc">
                                        <span onclick ={__CambiarDescuento} class="label label-success precio-prod" >{descuento}</span>
                                        </td>
                                        <td class="contCalc">
                                        <span class="label label-success " >{impuesto}</span>
                                        </td>
                                        <td>
                                        <span class="precio-calc">{subTotal.toLocaleString('de-DE')}</span>
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
                                            <span id="pagarInfo"> Subtotal: </span>
                                            <span id="cantidad-total">{factura.subTotal.toLocaleString('de-DE')} </span> 
                                        </td>
                                        <td width="35%" id="bordeBevelLeft"> 
                                            <span id="pagarInfo">Desc: </span>
                                            <span id="sigPeso">₡   </span>
                                            <span id="iva-total">{factura.totalDescuento.toLocaleString('de-DE')}</span> 
                                        </td>
                                        <td width="35%" id="bordeBevelRight"> 
                                            <span id="pagarInfo">IVA  </span>
                                            <span id="sigPeso">₡      </span>
                                            <span id="subtotal">{factura.iva.toLocaleString('de-DE')}</span> 
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
                                            <div id="pagarTitulo">Pagar</div>
                                        </td>
                                        <td width="70%" id="">
                                            <div id="">
                                                <span id="total_show_peso" class="textShadow"> ₡ </span>
                                                <span class="label label-info textShadow" id="total-show">{factura.total.toLocaleString('de-DE')}</span>
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
                                <input onclick={__BuscarEnListaClientes} type="text"  placeholder="Cliente" value="{cliente.nombreCompleto}"  name="datos_cliente" id="datos_cliente" autocomplete="off" >
                            </div>
                            <!--Fin Cliente o Nuevo Cliente-->
                            <!--Vendedor o Nuevo Vendedor-->
                            <div class="input-group">
                                <span title="Vendedor" class="input-group-addon " > 
                                    <span class="fa fa-user" aria-hidden="true" style="margin:3px 4px 0px 2px"></span> 
                                </span>
                                <input type="text" onclick={__BuscarEnListaVendedores} placeholder="Vendedor" value="{vendedor.nombreCompleto}"  name="v_vendedor" id="v_vendedor" autocomplete="off" >
                            </div>
                        </div>
                    </div> 
                    <hr style="margin: 0px; border-color: #e4e4e4;">
                    <div  class="row  ">
                        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12 " >   
                            <section class="contenedor-opciones">
                                
                                <a href="#" class="opciones-menu" onclick = {__crearTiqueteEnEspera} >
                                    <i class="fa fa-clock-o">Ventas en Espera</i>
                                </a>

                            
                                <a  href="#" class="opciones-limpiar" onclick = {__Limpieza_General_Todo} >
                                    <i class="fa fa-trash">Limpiar</i>
                                </a>                              
                            </section>
                        </div>    
                    </div> 
                    <div  class="row">
                        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12" >
                            <section class="lista-facturas-espera">
                                <div id="botones"  each={ventas_espera}  onclick={__CargarFacturaEspera}>
                                    <a href="#" class="factura-espera" title="{nombreCompleto}-{nota}">Venta # {id} - {total}</a>
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
                                    <li id="buscalo"  onclick = {__ListaDeProductos} class="">
                                        <h3><i class="glyphicon glyphicon-search" aria-hidden="true"></i>
                                            <img  src="{urlImagenBuscador}" width="45px" height="15px" >&nbsp;&nbsp; BUSCADOR 
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
                                        &nbsp;DEPARTAMENTOS</h3>  
                                    </li>
                                </ul>
                            </form>
                        </div>
                    </div>
                    
                </div>    
            </div>
        </div>
        <!--Ventana de los productos-->
        <div class="container">
            <div class="row">
                <div   class="col-sx-12 col-sm-7 col-md-7 col-lg-7 " >
                    <!--Seccion de categorias-->
                    <section show= {mostrarCategorias} class="lista-articulos" >
                        <div show= {mostrarCategorias} id="item-categorias"class="product-item"  each ={categorias.data}  onclick={__ArticulosXCategorias}>
                            <img  style = "width:150px;" alt="" class="img-responsive " src="https://pos.vendty.com/uploads/81LcrgMpIcL._SL1500__4.jpg">
                            <a href="#">{descripcion}</a>
                        </div>
                    </section>
                    <!--Seccion de articulos-->
                    <section show= {mostrarArticulosXCategoria} class="lista-articulos" >
                        <div class="product-item"  each ={inventariosXCategoria.data}   onclick={__AgregarProductoDePantalla}>
                            <img  style = "width:150px;" alt="" class="img-responsive " src="https://pos.vendty.com/uploads/81LcrgMpIcL._SL1500__4.jpg">
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
                           <input onkeypress = {__agregarInventarioCogigoBarra} type="text" class="form-control" id="codigoBarra" autofocus="autofocus" placeholder="Digite codigo Barra...">
                       </div>    
                       
                    </section>
                    <!--Fin Seccion de codigo de barra-->
                            
                </div> 
            </div>       
        </div>
        <!--Fin Ventana de los productos-->

    </div>    
</div>


<!--Modal Cambiar Cantidad-->
<div id='modalCambiarCantidad' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>Cambiar Cantidad </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                        <div class="form-group has-success">
                            <label >Cantidad:</label>
                            <input  onkeypress={__recalculacionDelDetalle} type="number" class="form-control" id="cambiarCantidad" autofocus="autofocus">
                        </div>
                    </div>
                </div> 
            </div>
            <div class="modal-footer">
                <button onclick={__recalculacionDelDetalle} onkeypress={__recalculacionDelDetalle}  type="button" class="btn btn-basic" style="float: right;" ><i class="fa fa-arrow-right"></i>Cambiar</button>
            </div>
        </div>
    </div>
</div>

<!--Fin Cambiar Cantidad-->

<!--Modal Cambiar Cantidad-->
<div id='modalCambiarDescuento' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>Descuento</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                        <div class="form-group has-success">
                            <label >Cantidad:</label>
                            <input  onkeypress={__actualizarDescuento} type="number" class="form-control" id="aplicarDescuento" autofocus="autofocus">
                        </div>
                    </div>
                </div> 

            </div>
            <div class="modal-footer">
                <button onclick={__actualizarDescuento} onkeypress={__actualizarDescuento} type="button" class="btn-dark-gray btn-back pull-left" style="float: right;" >Volver</button>
            </div>
        </div>
    </div>
</div>
<!--Fin Cambiar Cantidad-->

<!--Modal mostrar Articulos de una sucursal -->
<div id='modalInventario' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
       <div class="modal-content">
            <div class="modal-header with-border fondoEncabezado" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>Lista los Articulos del inventario </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-12 col-md-12 col-lg-12 col-sm-12">
                        <table id="tableListaInventario" class="table blueTable responsive display table-striped table-hover nowrap tableListaInventario " cellspacing="0" width="100%">
                            <thead>
                                <th>Codigo          </th>
                                <th>Descripcion     </th>
                                <th>Cantidad        </th>
                                <th>Precio Publico  </th>
                                <th>Precio Mayorista</th>
                                <th>Precio Especial </th>
                                <th>Opciones        </th>
                            </thead>
                        </table>
                    </div>
                </div>        
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left" style="float: right;" data-dismiss="modal">Volver</button>
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


<!--Modal mostrar venedores de una sucursal -->
<div id='modalListaDeVendedores' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg ">
       <div class="modal-content">
            <div class="modal-header with-border fondoEncabezado " >
                <h4 class="box-title" > <i class='fa fa-users'></i> {$.i18n.prop("titulo.listar.vendedor")} </h4>
            </div>
            <div class="modal-body">
                <table id="tableListaVendedores" class="table responsive display table-striped table-bordered tableListaVendedores " cellspacing="0" width="100%">
                    <thead>
                        <th class = "fondoEncabezado">{$.i18n.prop("vendedor.nombreCompleto")}    </th>
                        <th class = "fondoEncabezado">{$.i18n.prop("vendedor.correoElectronico")} </th>
                        <th class = "fondoEncabezado">{$.i18n.prop("vendedor.telefono")}          </th>
                        <th class = "fondoEncabezado">{$.i18n.prop("vendedor.celular")}           </th>
                        <th class = "fondoEncabezado">{$.i18n.prop("vendedor.descuento")}         </th>
                        <th class = "fondoEncabezado">{$.i18n.prop("listado.acciones")}           </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("vendedor.nombreCompleto")}    </th>
                            <th>{$.i18n.prop("vendedor.correoElectronico")} </th>
                            <th>{$.i18n.prop("vendedor.telefono")}          </th>
                            <th>{$.i18n.prop("vendedor.celular")}           </th>
                            <th>{$.i18n.prop("vendedor.descuento")}         </th>
                            <th>  </th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->


<!--Modal agregar Clientes nuevos de una sucursal -->
<div id='modalAgregarClienteNuevo' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog  modal-lg">
       <div class="modal-content">
            <div class="modal-header with-border titulos-modal-vender" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-users'></i>Agregar Nuevo Cliente  </h4>
            </div>
            <div class="modal-body">
                <div show={mostrarErrorClienteNuevo} class="alert alert-warning" >
                    <div each={errorClientes}><strong>{campo}:</strong> {mensaje}</div>
                </div>
                <form id= "formularioCliente" name = "formularioCliente" class="advanced-search-form">
                    <div class="row-fluid">
                        <div class="span12">
                            Todos campos son requeridos.
                        </div>
                    </div>
                    <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >Cedula <span class="requeridoDato">*</span> </label>
                                <input type="text" class="form-control cedula" id="cedula" name="cedula" value="{cliente.cedula}"  required>
                            </div>
    
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >Nombre <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control nombreCompleto" id="nombreCompleto" name="nombreCompleto" value="{cliente.nombreCompleto}"  >
                            </div>
    
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >correo </label>
                                <input type="text" class="form-control correoElectronico" id="correoElectronico" name="correoElectronico" value="{cliente.correoElectronico}"  >
                            </div>

                        </div>

                        <div class="row">
                            <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3">
                                <label class="knob-label" >Telefono </label>
                                <input type="text" class="form-control telefono" id="telefono" name="telefono" value="{cliente.telefono}"  >
                            </div>
                            <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3">
                                <label class="knob-label" >Fax </label>
                                <input type="text"  class="form-control fax" id="fax" name="fax" value="{cliente.fax}" >
                            </div>
                            <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3">
                                <label class="knob-label" >Descuento <span class="requeridoDato">%</span></label>
                                <input type="number" step="any" class="form-control descuento" id="descuento" name="descuento" value="{cliente.descuento}"  >
                            </div>

                        </div>                    
                        <div class="row">
                            <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3">
                                <label class="knob-label" >Celular </label>
                                <input type="text"  class="form-control movil" id="movil" name="movil" value="{cliente.movil}"  >
                            </div>
                            <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3">
                                <label class="knob-label" >codigo Pais </label>
                                <input type="text"  class="form-control codigoPais" id="codigoPais" name="codigoPais" value="{cliente.codigoPais}"  >
                            </div>
                            <div class="col-md-3 col-sx-3 col-sm-3 col-lg-3">
                                <label class="knob-label">Provincia</label>
                                <select  class="form-control" id="provincia" name="provincia" >
                                    <option  each={provincias}  value="{codigo}"  selected="{cliente.provincia ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-3 col-sx-3 col-sm-3 col-lg-3">
                                <label class="knob-label">Estado</label>
                                <select  class="form-control" id="estado" name="estado" >
                                    <option  each={estados}  value="{codigo}" selected="{cliente.estado ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label">Tipo Cedula</label>
                                <select  class="form-control" id="estado" name="estado" >
                                    <option  each={tipoCedulas}  value="{codigo}" selected="{cliente.tipoCedula ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6"></div>

                        </div>
                    <div class="row">
                        <div class= "col-md-9 col-sx-12 col-sm-9 col-lg-9">
                            <label class="knob-label" >Direccion </label>
                             <textarea class="form-control otraSena" rows="5" id="otraSena" name = "otraSena" value="{cliente.otraSena}"></textarea>
                        </div>
                    </div>
                        <br>
                    <div show={errorCliente}>
                        <div  class="alert alert-info" role="alert"  >
                            <h4 each={errorsCliente}> {mensaje} </h4>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left" style="float: left;" data-dismiss="modal">Volver</button>
                <button onclick = {__AgregarNuevoCliente} type="button" class="btn-green btn-add pull-right" style="float: right;" >Agregar</button>
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
						<h3 class="box-title">Facturar la Venta  </h3>
                        <h3 class="box-title pull-right ">Tipo Cambio {tipoCambio} </h3>
					</div>
					<div class="box-body">
                        <form>
                            <div class="row">
                                <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                    <div class="row">
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group has-success">
                                                <label > Forma Pago </label> 
                                                <select  onchange= {__tipoCobro} class="form-control" id="pago_tipoPago" >
                                                    <option each={comboTipoPagos} value="{estado}" selected="{factura.tipoPago ==estado?true:false}" >{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>    
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group has-success">
                                                <label for="pago_tipoVentaL">Tipo Documento </label> 
                                                <select class="form-control" id="pago_tipoVenta" name="pago_tipoVenta"  >
                                                    <option each={comboTipoDocumentos} value="{estado}" selected="{factura.tipoDocumento ==estado?true:false}" >{descripcion}</option>                                        
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6" show={mostrarCamposIngresoContado == false}>
                                            <div class="form-group has-success">
                                                <label > #Recibo </label> 
                                                <input type="text" class="form-control" id="pago_reciboDinero" name="pago_reciboDinero" >
                                            </div>
                                        </div>    
                                        <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                            <div class="form-group has-success">
                                                <label for="pago_tipoVentaL">#Transferencia </label> 
                                                <input type="text" class="form-control" id="pago_transferencia" name="pago_transferencia" >
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group has-success">
                                        <label for="pago_nombrefacturaL">Nombre Factura </label> 
                                        <input type="text" class="form-control" id="pago_nombrefactura" name="pago_nombrefactura" value="{cliente.nombreCompleto}">
                                    </div>
                                    <div  class="form-group has-success" show={mostrarCamposIngresoContado == false}>
                                        <label >Fecha Credito</label> 
                                        <div  class="form-group input-group date" data-provide="datepicker"  data-date-start-date="0d" data-date-format="yyyy/mm/dd">
                                            <input type="text" class="form-control" id="pago_fechaCredito" value="" >
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>    
                                    <div class="form-group has-success">
                                        <label >Direccion </label> 
                                        <input type="text" class="form-control" id="pago_direccion" name="pago_direccion">
                                    </div>
                                   

                                </div>
                                <div  class= "col-md-6 col-sx-6 col-sm-6 col-lg-6" >
                                    <div class="form-group has-success" show={mostrarCamposIngresoContado == false}>
                                        <label >#Contrato </label> 
                                        <input type="text" class="form-control" id="pago_Contrato" name="pago_Contrato">
                                    </div>
                                    <div class="form-group has-success">
                                        <label for="pago_transporteL">Transporte </label> 
                                        <input onkeyup={ __TotalDeTransporte } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any" class="form-control" id="pago_transporte"  value="{factura.totalTransporte}">
                                    </div>
                                    <div  class="form-group has-success">
                                        <label for="pago_efectivoL">Efectivo </label> 
                                        <input onkeyup={ __TotalDeEfectivo } onBlur = {__CalculaCambioAEntregarOnblur} onkeypress = {__CalculaCambioAEntregarKeyPress} type="number" step="any" class="form-control" id="pago_efectivo"  value="{factura.totalEfectivo}">
                                    </div>
                                    <div  class="form-group has-success">
                                        <label for="pago_tarjetaL">Tarjeta </label> 
                                        <input onkeyup={ __TotalDeTarjeta } onBlur = {__CalculaCambioAEntregarOnblur} onkeypress = {__CalculaCambioAEntregarKeyPress} type="number" step="any" class="form-control" id="pago_tarjeta"  value="{factura.totalTarjeta}">
                                    </div>
                                    <div  class="form-group has-success">
                                        <label for="pago_bancoL">Bancos</label> 
                                        <input onkeyup={ __TotalDeBanco } onBlur = {__CalculaCambioAEntregarOnblur} onkeypress = {__CalculaCambioAEntregarKeyPress} type="number" step="any" class="form-control" id="pago_banco"  value="{factura.totalBanco}">
                                    </div>

                                    
                                </div>
                            </div>
                        </form>   
                    </div>
                    <div class="box-footer">
                        <button onclick={_AtrasFacturarFinal} class="btn-dark-gray btn-back pull-left">  Volver</button>
                        <button onclick={__AplicarYCrearFacturaNueva}  class="btn-green btn-add pull-right"> </i> Finalizar Venta(F8)</button>
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
                                <h3><span id="lblSCS">Resumen de la Venta</span></h3>
                                    <div class="booking-info">
                                        <p style="text-align:right">Sub Total : <span id="lblSubtotal"> {factura.subTotal.toLocaleString('de-DE')} </span></p>
                                        <p style="text-align:right">Descuento : <span id="lblSubtotal"> {factura.totalDescuento.toLocaleString('de-DE')} </span></p>
                                        <p style="text-align:right">Impuesto  : <span id="lblSubtotal"> {factura.iva.toLocaleString('de-DE')} </span></p>
                                        <p style="text-align:right">Transporte: <span id="lblSubtotal"> {factura.totalTransporte.toLocaleString('de-DE')} </span></p>
                                    </div>
                                    <div class="precioTotalFactura">
                                        <p class="total" style="text-align:right;">Total  : <span id="lblTotal">{factura.total.toLocaleString('de-DE')}</span></p>
                                        
                                    </div>
                                    <div class="{claseCambioDinero}" show={mostrarCamposIngresoContado}>
                                        <p class="total" style="text-align:right;">Pago con : <span id="lblTotal">{totalPagoCon.toLocaleString('de-DE')}</span></p>    
                                        <p class="total" style="text-align:right;">Cambio : <span id="lblTotal">{totalPagoCon !=0?totalCambio.toLocaleString('de-DE'):0}</span></p>    
                                    </div>
                            </article>
                        </aside>
                    </div><!-- fin box-body-->
				</div><!-- fin box -->
		    </div>
        <div>  
          <!--Ventana de los billetes-->
        <div class="container">
            <div class="row">
                <div   class="col-sx-12 col-sm-7 col-md-7 col-lg-7 " >
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

    .codigo-barra .barra input {
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
    self.detail                = [];
    self.provincias                =[];
    self.estados                   =[];
    self.tipoCedulas               =[];

    self.nota                  = {notaFactura:null,
                                  notaComanda:null}    //Notas a la factura  
    self.factura               = {
        id : 0,
        tipoDocumento:0,
        tipoPago:0,
        iva: 0,
        totalDescuento:0,
        subTotal:0,  
        total:0,
        totalTarjeta:0,
        totalEfectivo:0,
        totalBanco:0,
        totalTransporte:0,
        sucursal:{
            empresa:{}
        }
    }   
    self.comboTipoDocumentos   = []        
    self.comboTipoPagos        = []                       
    self.totalCambio           = 0         // Total del Cambio de  la factura
    self.item                  = null;      // item temporal para crear una lidea detalle en la factura
    self.inventario            = null;      // articulo consultado para incluir en el detalle
    self.inventarios             = {data:[]}   //Lista de los articulos por almacen
    self.clientes              = {data:[]}   // lista de los clientes por alamacen    
    self.vendedores            = {data:[]}   // lista de los vendedores por almancen 
    self.facturas              = {data:[]}   // lista de las facturas por almancen y usuario 
    self.listaFacturasPorUsuario  = {data:[]}   // lista de las facturas por usuario
    self.cliente               = {};         // Objeto del cliente para asociar a la factura            
    self.vendedor               = {};        // objeto del vendedor para asociar a la factura  
    self.errorClientes         = []          // vector de errores para cuando se agrega un cliente nuevo
    self.ventas_espera         = []   // lista de facturas en espera por sucursal y usuario
    self.informacion_tabla     = []          // mapeo generico de una tabla
    self.informacion_tabla_clientes = []     // mapeo de los clientes en el datatable
    self.informacion_tabla_vendedores = []   // mapeo de los vendedores en el datatable
    self.informacion_tabla_facturas = []     // mapeo de los facturas en el datatable
    self.idiomaDataTable       = {}          // Idioma del datatable
    self.mostrarFormularioPago = false       // mostrar el formulario de pago de la factura y creacion
    self.mostrarErrorClienteNuevo = false    // mostrar los errores al ingresar un cliente nuevo
    self.mostrarCamposIngresoContado = true
    
    self.mostarParaCrearNuevaVentas  = true //muestra modulo de crear una nueva venta
    self.mostrarCodigoBarra          = true;
    self.mostrarNavegador            = false
    self.mostrarCategorias           = false //muestra la pantalla de imagenes de articulos   
    self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias   
    self.mostrarFacturaImprimir      = false
    self.errorCliente                     = false
    self.errorsCliente                    = [];

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
    self.botonAnteriorCategoria      = 0     // boton para pedir el anterior de las categorias
    self.botonSiguienteCategoria     = 0
    self.totalCategorias             = 0
    
    self.urlImagenNavegador   = '/dist/img/navegador.png';
    self.urlImagenLector      = '/dist/img/codigo_barra.png';
    self.urlImagenBuscador    = '/dist/img/buscador.png';
    self.claseCambioDinero    = 'entregarCambioPositivo'
       
    self.totalPagoCon         = 0;
    self.on('mount',function(){
    __informacionData()
    __InformacionTabla_lista_clientes()
    __InformacionTabla_lista_articulos()
    __InformacionTablaVendedores()
    
    __InicializarTabla('.tableListaInventario')
    __InicializarTabla('.tableListaClientes')
    __InicializarTabla('.tableListaVendedores')
    __InicializarTabla('.tableListaFacturas')
    
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListaVendedores')
    // evento de agregar en el modal de articulos del inventario
    __agregarInventarios()
    //** evento del datatable clientes para seleccionar un cliente
     __seleccionarCliente()
    // evento del datatable vendedores  para seleciconar un vendedor 
    __seleccionarVendedores()
    // Listar de clientes asociados a una sucursal
    __ListaClientes()
     // Listar de clientes asociados a una sucursal
    __ListaVendedores()
    //Lista de Facturas en espera
    __ListaFacturasEnEspera()
    // Tipo de Pagos
    __ComboTipoPagos()
    //Tipos de Documentos
    __ComboTipoDocumentos()
    //Imprimr Factura
    __seleccionarImprimir()

    //funciones de teclas
    __Teclas()
    //carag de billetes

    cargaBilletes()
    $('.telefono').mask('0000-0000');
    $('.movil').mask('0000-0000');
    $('.fax').mask('0000-0000');
    $('.descuento').mask('000');
    $('.codigoPais').mask('00');
    $(".cedula").attr("maxlength",18);
    $(".nombreCompleto").attr("maxlength",100);
    $(".correoElectronico").attr("maxlength",100);
    $(".otraSena").attr("maxlength",180);
    __cargaProvincia()
    __CargaEstados()
    __CargaTipoCedulas()
    

 })

/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListaVendedores tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 5    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
 
    })

}

 /**
* Carga de tipo Cedulas
**/    
function __CargaTipoCedulas(){
    self.tipoCedulas = []
    self.tipoCedulas.push({
        codigo:"01",
        descripcion:"Fisica"
    })
    self.tipoCedulas.push({
        codigo:"02",
        descripcion:"Juridica"
    })
    self.tipoCedulas.push({
        codigo:"03",
        descripcion:"Pasaporte"
    })

    self.update()
}

/**
* Carga de estados 
**/    
function __CargaEstados(){
    self.estados = []
    self.estados.push({
        codigo:"Activo",
        descripcion:"Activo"
    })
    self.estados.push({
        codigo:"Inactivo",
        descripcion:"Inactivo"
    })
    self.update()
}
/**
*  Carga las provincias  de Costa Rica
**/    
function __cargaProvincia(){
    self.provincias = []
    self.update()
    __Provincias("01","San Jose")
    __Provincias("02","Alajuela")
    __Provincias("03","Cartago")
    __Provincias("04","Heredia")
    __Provincias("05","Guanacaste")
    __Provincias("06","Puntarenas")
    __Provincias("07","Limon")
    self.update()
}

function __Provincias(codigo,descripcion){
    
    
    self.provincias.push({
        codigo:codigo,
        descripcion:descripcion
    })
    self.update()
}

/**
* Contabilizar los billetes de acuerdo a como se vayan dando click en la pantalla
*/
_sumarBilletes(e){
    
    var item = e.item
    if(item.valor == 0 ){
       self.factura.totalEfectivo = 0
       self.factura.totalTarjeta  = 0
       self.factura.totalEfectivo = 0
       self.totalCambio           = 0
       self.totalPagoCon          = 0
       self.claseCambioDinero     = 0 

    }else{
        self.factura.totalEfectivo = __valorNumerico(self.factura.totalEfectivo) + __valorNumerico(item.valor) 
        self.update()
        var sumaMontosEntregadosParaCambios =__valorNumerico(self.factura.totalTarjeta)
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco) 
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo) 
        self.totalCambio = 0
        self.totalCambio = sumaMontosEntregadosParaCambios > self.factura.total ? sumaMontosEntregadosParaCambios - __valorNumerico(self.factura.total):sumaMontosEntregadosParaCambios - __valorNumerico(self.factura.total)    
        self.claseCambioDinero  = __valorNumerico(sumaMontosEntregadosParaCambios) > __valorNumerico(self.factura.total)?'entregarCambioPositivo':'entregarCambioNegativo'
        self.totalPagoCon = sumaMontosEntregadosParaCambios

    }
    
    self.update()
    
    

}

/**
* Regresar a la venta despues de imprimir
**/    
__RegresarVentaImprimir(){
    self.mostrarCamposIngresoContado = false
    
    self.mostarParaCrearNuevaVentas  = true 
    self.mostrarFacturaImprimir      = false
    self.nota                  = {notaFactura:null,
                                  notaComanda:null}    //Notas a la factura  
    self.detail                = []
    self.factura               = {
        id : 0,
        tipoDocumento:0,
        tipoPago:0,
        iva: 0,
        totalDescuento:0,
        subTotal:0,  
        total:0,
        totalTarjeta:0,
        totalEfectivo:0,
        totalBanco:0,
        totalTransporte:0,
        sucursal:{
            empresa:{}
        }
    }   
     
    self.update()
    $( "#codigoBarra" ).val(null)
    $( "#codigoBarra" ).focus()
    cargaBilletes()
}



/**
*Imprimir factura
**/    
__ImprimirFactura(){
    var objeto=document.getElementById('imprimeme');  //obtenemos el objeto a imprimir
          var ventana=window.open('','_blank');  //abrimos una ventana vacía nueva
          ventana.document.write(objeto.innerHTML);  //imprimimos el HTML del objeto en la nueva ventana
          ventana.document.close();  //cerramos el documento
          ventana.print();  //imprimimos la ventana
          ventana.close();  //cerramos la ventana

           $("#boton-regresar").focus()
}

/**
*  boton anterior de la pantalla de categorias or articulos
**/    
__BotonAnterior(){
    
    if(self.categoria.id == 0){//cuando esta usando la pantalla de categorias
        self.categorias.pagination.current_page = self.categorias.pagination.current_page - 1
        self.categorias.pagination.current_page = self.categorias.pagination.current_page > 1?self.categorias.pagination.current_page:1;
        self.update()
        __ListaCategorias()

    }else{// cuando esta usando la pantalla de articulos
        self.inventariosXCategoria.pagination.current_page = self.inventariosXCategoria.pagination.current_page - 1
        self.inventariosXCategoria.pagination.current_page = self.inventariosXCategoria.pagination.current_page > 1?self.inventariosXCategoria.pagination.current_page:1;
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
        self.update()
        __ListaCategorias()

        }

    }else{ //cuando esta usando la pantalla de articulos
        if(self.inventariosXCategoria.pagination.current_page <  self.inventariosXCategoria.pagination.last_page){
        self.inventariosXCategoria.pagination.current_page = self.inventariosXCategoria.pagination.current_page + 1
        self.update()
        __ListaArticulosXCategorias()

        }
    }
            
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
    self.mostrarNavegador            = true
    self.mostrarCategorias           = false //muestra la pantalla de imagenes de articulos   
    self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias   
   
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
* agregar producto desde la pantalla de articulos
**/

__AgregarProductoDePantalla(e){
    
    var item =  e.item
    self.inventario = item;
    self.update()
    
    __buscarProducto(self.inventario.codigo,1);
}


/**
*    Muesta los campos de efectivo , banco, tarjeta cuando es al contado pero si es a Credito no muestra dicho valores
**/
__tipoCobro(e){
    //Contado
    if(e.currentTarget.value == 'Contado'){
        self.mostrarCamposIngresoContado = true
    }
    //Credito
    if(e.currentTarget.value == 'Credito'){
        self.mostrarCamposIngresoContado = false
    }
}
/**
*  Limpieza de los valores excenciales de  la Creacion de la factura
**/

__Limpieza_General_Todo(){
   __Init();
}
/**
*  Al darle click en cualquier factura en espera llava a la funciona
* para realizar la carga de la factura en el front end
**/
__CargarFacturaEspera(e){
    __FacturaEnEspera(e.item.id) 
    
}

/**
*  cambiar el cliente onchange
**/
__cambiarCliente(e){
    self.cliente =  {}
    self.update()
}
/**
*   Retrocer a los ingresos de los productos desde el formulario de ingresar el valor dinero a pagar
**/
_AtrasFacturarFinal(){
   self.mostrarFormularioPago = false
   self.mostarParaCrearNuevaVentas = true
   self.update()
}
/**
*   Buscar un cliente de lista
**/
__BuscarEnListaClientes(){
   $('#modalClientes').modal('show');  
}

/**
*     Agregar Nota a la Factura
**/
__MostrarFormularioParaAgregarNotaAFactura(){
   $('#modalNotaNuevo').modal('show');  
}
/**
*  Agregar Nota a la factura 
**/
__AgregarNotaALaFactura(){
  self.nota.notaFactura = nota_factura.value
  self.nota.notaComanda = nota_comanda.value
  self.update()
  $('#modalNotaNuevo').modal('hide') 
}
/**
*   Lista de vendedores
**/
__BuscarEnListaVendedores(){
   $('#modalListaDeVendedores').modal('show');  
}
/**
*   Agregar un cliente nuevo desde un formulario modal
**/
__MostrarFormularioNuevoCliente(){
    self.errorsCliente = []
    self.errorCliente = false
    self.mostrarErrorClienteNuevo = false
    self.update()
    $('#modalAgregarClienteNuevo').modal('show');  
}
/**
*        Agregar nuevo Cliente 
**/
/**
*   Agregar 
**/
__AgregarNuevoCliente(){
    self.error        = false
    self.errors       = [];
    self.update()

    if(nombreCompleto.value == null || nombreCompleto.value.length == 0  ){
        swal("Nombre"," Digite el nombre  ", "info")
        return 
    }   
     if(cedula.value == null || cedula.value.length == 0  ){
        swal("Codigo"," Digite la cedula  ", "info")
        return 
    }   

    swal({
      title: "Esta seguro?", 
      text: "Esta seguro que quiere agregar?", 
      type: "warning",
      showCancelButton: true,
      closeOnConfirm: false,
      confirmButtonText: "Si, Agregar!",
      confirmButtonColor: "#ec6c62",
      showLoaderOnConfirm: true,
    }, function() {
        var formulario = $('#formularioCliente').serialize();
        $.ajax({
           type: 'get',
            url: ('clientes/agregarNuevoAjax'),
            datatype:'json',
            data: formulario,
            success: function(resultado){
                console.log(resultado)
                if ((resultado.errors)) {
                   __Error_Incluir_Mensajes(resultado.errors)
                    swal("Se presento un incoveniente .", "");
                    return
                } 
                if(resultado.errorCrear){
                    swal(resultado.msg, "error");
                }
                if(resultado.exito != null){
                    swal({
                        position: 'top-right',
                        type: 'success',
                        title: resultado.exito,
                        showConfirmButton: false,
                        timer: 1500
                    });
                  
                }
                 swal("Agregar!", "Su transaccion fue realizada exitosamente !", "success");
                
            },          
            error:function(data) {
               console.log(data);          
               swal("", "Disculpa el incoveniente pero tenemos un problema con el Servidor!", "error");
            }
        
      });
    });     
    
}


/**
*  generar el listado de categorias de acuerdo a la pagina solicitada
**/
function __ListaCategorias(){
    
    $.ajax({
        type: 'GET',
        url: 'categorias/listadoCategorias',
        datatype:'json',
        data: {page:self.categorias.pagination.current_page},
        success: function(resultado){
            
            self.categorias.data =resultado.categorias.data
            self.categorias.pagination =resultado.pagination
            self.mostrarCodigoBarra          = false;
            self.mostrarNavegador            = true
            self.mostrarCategorias           = true //muestra la pantalla de imagenes de articulos   
            self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias   

            
            self.update()
        },
        error: function (xhr, status) {
            console.log(xhr);
        }
    });
}

/**
*  generar el listado de articulos por categoria
**/
function __ListaArticulosXCategorias(){
    
    $.ajax({
        type: 'GET',
        url: 'inventarios/listadoInventariosByCategoria',
        datatype:'json',
        data: {idCategoria:self.categoria.id,page:self.inventariosXCategoria.pagination.current_page},
        success: function(resultado){
            self.inventariosXCategoria.data       = resultado.inventarios.data
            self.inventariosXCategoria.pagination = resultado.pagination
            self.mostrarCodigoBarra          = false;
            self.mostrarNavegador            = true
            self.mostrarCategorias           = false //muestra la pantalla de imagenes de articulos   
            self.mostrarArticulosXCategoria  = true //muestra la pantalla de imagenes de categorias   
            
            self.update()
            
        },
        error: function (xhr, status) {
            console.log(xhr);
        }
    });
}

/**
*        Incluir mensajes de errores retornados del back end
**/
function __Error_Incluir_Mensajes(errores){
    self.errorCliente                     = false
    self.errorsCliente = []
    for (var i=0; i < errores.Mensaje.length; i++){
       self.errorsCliente.push({
            mensaje:errores.Mensaje[i]
        })
    }
    self.errorCliente = true
    self.update()
}

/**
*   funcion para grabar la factura en el back end
**/
__MostrarFormularioDePago(){
     //No hay detalles registrados en la factura
    if(self.detail.length == 0 ){
        swal("Verificar","No hay detalles en la factura ", "info")
        return
    }
    self.mostarParaCrearNuevaVentas = false;
    self.mostrarFormularioPago = true
    self.mostrarCamposIngresoContado = true
    self.update()
    $("#pago_efectivo").focus()
}
/** 
*
*Agregar productos al detalle de la factura
*
*/
__agregarInventarioCogigoBarra(e){
    if (e.keyCode != 13) {
        return;
    } 
    __buscarProducto(e.currentTarget.value);
    $( "#codigoBarra" ).val(null)
    $( "#codigoBarra" ).focus()
}
/**
** Boton para agregar el producto a la factura desde listado de productos
**/
__agregarInventarioBotonAgregar(){
   __buscarProducto($( "#producto" ).val(),$( "#quantty" ).val());
}

/**
*  Obtiene el valor de lo digitado en el campo de transporte
**/
__TotalDeTransporte(e){
    self.factura.totalTransporte = __valorNumerico(e.target.value) 
    self.update()
    __calculate()
}
/**
*  Obtiene el valor de lo digitado en el campo de efectivo
**/
__TotalDeEfectivo(e){
    self.factura.totalEfectivo = __valorNumerico(e.target.value) 
    self.update()
}
/**
*  Obtiene el valor de lo digitado en el campo de Tarjeta
**/
__TotalDeTarjeta(e){
    self.factura.totalTarjeta = __valorNumerico(e.target.value) 
    self.update()
}

/**
*  Obtiene el valor de lo digitado en el campo de Banco
**/
__TotalDeBanco(e){
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
    self.totalCambio = 0
    self.totalCambio = sumaMontosEntregadosParaCambios > self.factura.total ? sumaMontosEntregadosParaCambios - self.factura.total:sumaMontosEntregadosParaCambios - self.factura.total    
    self.claseCambioDinero  = __valorNumerico(sumaMontosEntregadosParaCambios) > __valorNumerico(self.factura.total)?'entregarCambioPositivo':'entregarCambioNegativo'
    self.totalPagoCon = sumaMontosEntregadosParaCambios
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
        self.totalCambio = 0
        self.totalCambio = sumaMontosEntregadosParaCambios > self.factura.total ? sumaMontosEntregadosParaCambios - self.factura.total:sumaMontosEntregadosParaCambios - self.factura.total    
        self.claseCambioDinero  = __valorNumerico(sumaMontosEntregadosParaCambios) > __valorNumerico(self.factura.total)?'entregarCambioPositivo':'entregarCambioNegativo'
        self.totalPagoCon = sumaMontosEntregadosParaCambios
        self.update()
    }
}
/** 
*  Inicializar
**/
function __Init(){
     // Detalle de la factura es una coleccion de articulos de inventario
    self.detail                = []
    self.factura               = {
        id : 0,
        tipoDocumento:0,
        tipoPago:0,
        iva: 0,
        totalDescuento:0,
        subTotal:0,  
        total:0,
        totalTarjeta:0,
        totalEfectivo:0,
        totalBanco:0,
        totalTransporte:0,
        sucursal:{
            empresa:{}
        }
    }   
    self.comboTipoDocumentos   = []
    self.comboTipoPagos        = []
    self.nota                  = {notaFactura:null,
                                  notaComanda:null}    //Notas a la factura  
    self.totalCambio           = 0         // Total del Cambio de  la factura
    self.item                  = null      // item temporal para crear una lidea detalle en la factura
    self.inventario            = null      // articulo consultado para incluir en el detalle
    self.inventarios           = {data:[]}   //Lista de los articulos por almacen
    self.clientes              = {data:[]}   // lista de los clientes por alamacen    
    self.vendedores            = {data:[]}   // lista de los vendedores por almancen 
    self.listaFacturasPorUsuario  = {data:[]}   // lista de las facturas por usuario
    self.cliente               = {}         // Objeto del cliente para asociar a la factura            
    self.vendedor               = {}        // objeto del vendedor para asociar a la factura  
    self.errorClientes         = []          // vector de errores para cuando se agrega un cliente nuevo
    self.informacion_tabla     = []          // mapeo generico de una tabla
    self.informacion_tabla_clientes = []     // mapeo de los clientes en el datatable
    self.informacion_tabla_vendedores = []   // mapeo de los vendedores en el datatable
    self.informacion_tabla_facturas = []   // mapeo de los vendedores en el datatable
    self.idiomaDataTable       = {}          // Idioma del datatable
    self.mostrarFormularioPago = false       // mostrar el formulario de pago de la factura y creacion
    self.mostrarErrorClienteNuevo = false    // mostrar los errores al ingresar un cliente nuevo
    self.update()
    pago_transporte.value               = null
    pago_efectivo.value                 =null
    pago_tarjeta.value                  = null
    pago_banco.value                    = null
    pago_direccion.value                = null
    pago_nombrefactura.value            = null
    aplicarDescuento.value              = null
    nombreCompleto.value   = null
    email.value            = null
    celular.value          = null
    telefono.value         = null
    descuento.value        = null
    // Tipo de Pagos
    __ComboTipoPagos()
    //Tipos de Documentos
    __ComboTipoDocumentos()

    
}
/**
*  Validar si es numero
**/
function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}

/**
**         Se aplica o se crea una factura cargada en la pantalla
**/
__AplicarYCrearFacturaNueva(){
    crearFactura(pago_tipoVenta.value,"Creacion de la Factura de Venta");
}

/**
**         Se aplica o se crea una factura cargada en la pantalla
**/
__crearTiqueteEnEspera(){
    crearFactura('Tiquete',"Creacion del tiquete en Espera");
    //Inicializar Pantalla despues de crear
   //  __Init()

    //Lista de Facturas en espera
    __ListaFacturasEnEspera()
}

/**
*  Crear Factura  parametros estado , mensaje
**/

function crearFactura(tipoDocumento,mensaje){
//No hay detalles registrados en la factura
    if(self.detail.length == 0 ){
        swal("Verificar","No hay detalles en la factura ", "info")
        return
    }
    //validar la fecha credito
    if(tipoDocumento =='Factura'){
        if(pago_tipoPago.value == 'Credito'  ){
            if(pago_fechaCredito.value == null || pago_fechaCredito.value.length == 0){
                swal("Verificar","Debes indicar la fecha de credito ", "error")
                return
            }
        }
    }
    if(tipoDocumento =='Factura'){
        //Contado
        if(pago_tipoPago.value == 'Contado'  ){
            var montoEntregado = 0
            var total          = __valorNumerico(self.factura.total)
            montoEntregado += __valorNumerico(pago_efectivo.value)
            montoEntregado += __valorNumerico(pago_tarjeta.value)
            montoEntregado += __valorNumerico(pago_banco.value)
            if(montoEntregado < total ){
                swal("Verificar","El dinero entregado deber ser igual o mayor al monto total ", "error")
                return
            }
        }
    }
    
    var informacion = {
        id:self.factura.id,       
        nota:            self.nota.notaFactura,
        comanda:         self.nota.notaComanda,
        subTotal:        __valorNumerico(self.factura.subTotal),
        totalDescuento:  __valorNumerico(self.totalDescuentoDetalle),
        iva:             __valorNumerico(self.factura.iva),
        total:           __valorNumerico(self.factura.total),
        tipoPago:        pago_tipoPago.value,
        totalTransporte: pago_transporte.value !=null?__valorNumerico(pago_transporte.value):0,
        totalEfectivo:   pago_efectivo.value !=null?__valorNumerico(pago_efectivo.value):0,
        totalTarjeta:    pago_tarjeta.value !=null?__valorNumerico(pago_tarjeta.value):0,
        totalBanco:      pago_banco.value  !=null?__valorNumerico(pago_banco.value):0,
        totalCredito:    0,
        direccion:       pago_direccion.value,
        tipoDocumento:   tipoDocumento,
        cliente_id:      self.cliente.id,
        vendedor_id:     self.vendedor.id,
        nombreFactura:   pago_nombrefactura.value,
        estado:pago_tipoVenta.value == 'Factura'?'Aplicada':'Pendiente',
        fechaCredito:pago_fechaCredito.value,
        detail :         self.detail,
        reciboDinero:pago_reciboDinero.value,
        transferencia:pago_transferencia.value,


     }
     

     $.ajax({
            type: 'post',
            url: 'facturas/saves',
            datatype:'json',
            data: informacion,
            success: function(resultado){
                if(resultado.exito != null){
                    swal({
                        position: 'top-right',
                        type: 'success',
                        title: mensaje,
                        showConfirmButton: false,
                        timer: 1500
                    });
                    self.mostrarFormularioPago = false       // mostrar el formulario de pago de la factura y creacion
                    self.mostrarErrorClienteNuevo = false    // mostrar los errores al ingresar un cliente nuevo
                    self.mostrarCamposIngresoContado = false
                   
                    self.mostarParaCrearNuevaVentas  = tipoDocumento == 'Factura' || tipoDocumento == 'Proforma' || tipoDocumento == 'Orden' ?false:true //muestra modulo de crear una nueva venta
                    self.mostrarCodigoBarra          = true;
                    self.mostrarNavegador            = false
                    self.mostrarCategorias           = false //muestra la pantalla de imagenes de articulos   
                    self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias   
                    self.mostrarFacturaImprimir      = tipoDocumento == 'Factura' || tipoDocumento == 'Proforma' || tipoDocumento == 'Orden' ?true:false  
                    self.factura                     = resultado.factura                 
                     self.update()
                    __FacturaEnEspera(resultado.factura.id)
                    __ListaFacturasEnEspera()
                    $("#boton-regresar").focus()
                }
            },
	         error: function (xhr, status) {
	              console.log(xhr);
        }
       });
}



/**
*    Inicializar las tablas
**/
function __Inicializar_Tablas(nombreTabla){
    $(nombreTabla).dataTable({
        destroy: true,
        "sDom": 'lfrtip',
        "lengthMenu": [[5,10, 25, 50, -1], [5,10, 25, 50, "All"]],
        "order": [0, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
         "language" : self.idiomaDataTable,
     });
}

/**
*  Lista de los clientes
**/
function __ListaClientes(){
      $.ajax({
        url: 'ListarClientesActivosAjax.do',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                __informacionData()
          //      loadListar(".tableListaCliente",idioma_espanol,self.informacion_tabla_clientes,result.aaData)
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
        self.update();
    });
}

/**
*  Lista de los clientes
**/
function __ListaVendedores(){
 $.ajax({
        url: "ListarVendedoresActivosAjax.do", 
        datatype: "json",
        method:"GET",
        success: function (result) {
           if(result.aaData.length > 0){
                __InformacionTablaVendedores()
                loadListar(".tableListaVendedores",idioma_espanol,self.informacion_tabla_vendedores,result.aaData)
               self.vendedores.data =  result.data;  
               self.update(); 
               agregarInputsCombos()
                ActivarEventoFiltro('.tableListaVendedores')
           }
            else{
                $('#tableListaVendedores').dataTable().fnClearTable();
            }

        },
        error: function (xhr, status) {
            console.log(xhr);
            swal("Oops", "Disculpa el incoveniente pero tenemos un problema con el Servidor!", "error");
        }
    });
}
/**
*  Lista de las facturas en espera estado pendiente aplicadas hoy por el usuario en la sucursal
**/
function __ListaFacturasEnEspera(){
    return
    $.ajax({
        url: "facturas/getListaFacturasPorUsuarioYSucursalYEspera",
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.data.length > 0){
               self.ventas_espera =  result.data;  
               self.update(); 
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            swal("Oops", "Disculpa el incoveniente pero tenemos un problema con el Servidor!", "error");
        }
    });
}
/**
*  Factura en espera ,cliente y sus  detalles desde back end
**/
function __FacturaEnEspera(idFactura){
    self.detail         = []
    self.nota           = {notaFactura:null,
                                  notaComanda:null}  
    self.cliente        = {}         
    self.vendedor       = {}        
    self.totalCambio    = 0         
    self.update()
    var parametros = {idFactura:idFactura}
    $.ajax({
        url: "facturas/getFacturaEnEspera",
        datatype: "json",
        method:"GET",
        data: parametros,
        success: function (resultado) {
           
           cargarDetallesFacturaEnEspera(resultado.factura)
           
        },
        error: function (xhr, status) {
            console.log(xhr);
            swal("Oops", "Disculpa el incoveniente pero tenemos un problema con el Servidor!", "error");
        }
    });
}

/**
*  Cargar detalles Factura Espera
**/
function cargarDetallesFacturaEnEspera(factura){
    self.factura.id              = factura.id
    self.factura.consecutivo     = factura.consecutivo
    self.nota.notaFactura        = factura.nota
    self.factura.iva             = factura.iva
    self.factura.tipoDocumento   = factura.tipoDocumento
    self.factura.tipoPago        = factura.tipoPago
    self.factura.subTotal        = factura.subtotal
    self.factura.totalDescuento  = factura.totalDescuento
    self.factura.total           = factura.total
    self.factura.totalTransporte = factura.totalTransporte  
    self.cliente          = factura.cliente
    self.vendedor         = factura.vendedor  
    factura.detalles.forEach(function(e){
        self.detail.push({
            id_inventario   : e.inventario.id,
            costo           : __valorNumerico(e.inventario.costo) ,
            descripcion     : e.inventario.articulo.descripcion,
            descuento       : e.porcentajeDesc,
            totalDescuentoDetalle  : e.totalDesc,
            iva             : __ObtenerImpuestoByProducto(e.inventario.impuesto,e.inventario.precioPublico,e.cantidad),
            cantidad        : __valorNumerico(e.cantidad),
            precio          : __valorNumerico(e.inventario.precioPublico) ,
            subTotal        : __valorNumerico(e.inventario.precioPublico) * __valorNumerico(e.cantidad) 
        })
    })
    self.update()
     __calculate(); 
}

/**
*Cambiar Cantidad del Articulo
**/
__CambiarCantidad(e){
   self.item = e.item; 
   self.update()
   $( "#cambiarCantidad" ).focus()
   $('#modalCambiarCantidad').modal('show')      
}

/**
* Aplicar el descuento
**/
__CambiarDescuento(e){
    self.item = e.item; 
    self.update()
    $('#modalCambiarDescuento').modal('show')      
}
/**
* mostrar la lista de articulos de la empresa
**/
__ListaDeProductos(){
    $.ajax({
        url: "inventarios/getListaInventarioPorAlmacen",
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.data.length > 0){
                $("#tableListaInventario").dataTable().fnClearTable();
                $('#tableListaInventario').DataTable().destroy();
                $('#tableListaInventario').dataTable({
                    destroy: true,
                    "lengthMenu": [[5,10, 25, 50, -1], [5,10, 25, 50, "All"]],
                    "language": self.idiomaDataTable,
                    "sDom": 'lfrtip',
                    "order": [0, 'desc'],
                    "bPaginate": true,
                    'responsive': true,
                    "bAutoWidth": true,
                    "lengthChange": true,
                    "columns": self.informacion_tabla,
                })
                __InformacionTabla_lista_articulos()
               self.inventarios.data =  result.data;  
               self.update(); 
               $("#tableListaInventario").dataTable().fnAddData(self.inventarios.data)
               $('#modalInventario').modal('show')      
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            swal("Oops", "Disculpa el incoveniente pero tenemos un problema con el Servidor!", "error");
        }
    });
}


/**
* Buscar el codigo del producto  en la base de datos
**/

function __buscarProducto(codigo,cantidad){
    
    self.inventario  = null;
    $.ajax({
            type: 'get',
            url: "inventarios/buscarArticuloAndInventarioByCodigo",
            datatype:'json',
            data: {
               'codigo': codigo,
             },
            success: function(data){
                console.log(data)
                $.each(data, function( index, inventario ) {
                    self.inventario = inventario;
                    self.update();
                });
                if(self.inventario !=null){
                  __agregarInventario(cantidad)
                }
            },
	        error:function(data) {
	              console.log("error:"+data);
                  swal("Oops", "Disculpa el incoveniente pero tenemos un problema con el Servidor!", "error");
	    
        }
    });
}
/**
*  Agregar un articulo si existe se suma la cantidad y no existe se agrega en el detalle
**/
function __agregarInventario(cantidad){
    
    if(self.inventario == null){
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
        
        __nuevoInventarioAlDetalle(cantidad);
        encontrado = true;
    }else{//Se busca el articulo si existe se incrementa la cantidad
        for (var count = 0; count < self.detail.length; count++) {
            if (self.detail[count].id_inventario == self.inventario.idInventario ){
               self.item          = self.detail[count];
               self.item.cantidad = __valorNumerico(self.item.cantidad) + __valorNumerico(cantidad)
               
               //Verificar si aplica impuesto al producto
               if(self.inventario.iva =="Activo"){
                 self.item.iva = __ObtenerImpuestoByProducto(self.inventario.impuesto,self.item.precio,self.item.cantidad)
               }else{
                 self.item.iva = 0
               }
               self.update();
               __actualizarItemArray();
               self.detail[count] = self.item;
               encontrado = true;
              self.update();
            }
        }
    
    }
    // si no existe se agrega como un producto nuevo
    if(encontrado == false){ // add elemen
       
      __nuevoInventarioAlDetalle(cantidad);
    }
    
    __calculate(); 
    
}

/**
*  retorna el valor numerico o cero sino es numerico
**/
function __valorNumerico(valor){
    
    return isNumber(valor)?parseFloat(valor):0 ;
}

/**
*   agregar Articulos nuevos en el detalle de la factura
**/
function __nuevoInventarioAlDetalle(cantidad){
    if(self.inventario.descripcion == null){
        return;
    }
    if(self.inventario.descripcion == ""){
        return;
    }
    self.descuento      = 0;
    var impuestos       = 0;
    self.detail.push({
       id_inventario   : self.inventario.idInventario,
       costo           : __valorNumerico(self.inventario.costo) ,
       descripcion     : self.inventario.descripcion,
       descuento       : 0,
       totalDescuentoDetalle  : 0,
       iva             : __ObtenerImpuestoByProducto(self.inventario.impuesto,self.inventario.precioPublico,cantidad),
       cantidad        : __valorNumerico(cantidad),
       precio          : __valorNumerico(self.inventario.precioPublico) ,
       subTotal        : __valorNumerico(self.inventario.precioPublico) * __valorNumerico(cantidad) 
    });
    self.update()
}

/**
*       Obtener el impuesto de un producto 
        Se debe conocer el costo y la ganancia
**/

function __ObtenerImpuestoByProducto(impuesto,precio,cantidad){
   var precioSinImpuesto = 0
   //Si el articulo no tiene el procentaje del impuesto se retorna cero
   if(impuesto == 0){
       return 0;
   }else{
      precioSinImpuesto = __valorNumerico(precio)  / (1+ __valorNumerico(impuesto/100))
      totalImpuestos    = __valorNumerico(precio)  -  __valorNumerico(precioSinImpuesto) 
      return totalImpuestos * __valorNumerico(cantidad)
   }
}

/**
* eliminar un detalle factura
**/
__removeProductFromDetail(e) {
    var item = e.item,
    index = this.detail.indexOf(item);
    this.detail.splice(index, 1);
     __calculate();
 }
   
 /**
 * Cuando se aplica un cambio de cantidad en un detalle
 * Se aplica una recalculacion de todo el detalle y factura
 **/ 
 __recalculacionDelDetalle(e){
     if (e.keyCode != 13) {
        return;
    } 
    var cantidad = cambiarCantidad.value
    var index = self.detail.indexOf(self.item);
    //Cantidad del detalle se verifica si es null o espacio por defecto se deja en 1
    if(cantidad == ""){
       cantidad = 1;
    }
    if(cantidad == null){
       cantidad = 1; 
    }
    self.item.cantidad = parseFloat(cantidad);  
    __actualizarItemArray();
    self.detail[index] = self.item;
    self.update();
     $('#modalCambiarCantidad').modal('hide') 
     cambiarCantidad.value = 0
 }
/**
* Actualizar el descuento del producto
**/
__actualizarDescuento(e){
    if (e.keyCode != 13) {
        return;
    } 
    var descuento = __valorNumerico(aplicarDescuento.value) 
    //Si es mayor al 100 % no se acepta el descuento
    if(descuento >100){
        return
    }
    var index     = self.detail.indexOf(self.item);
    //Descuento se verifica si es null o espacios por defecto se deja en cero
    if(descuento == ""){
       descuento = 0;
    }
    if(descuento == null){
       descuento = 0; 
    }
    //Descuento
    if(self.item.descuento != descuento){
       self.item.descuento =  __valorNumerico(descuento)  
    }    
    __actualizarItemArray();
    self.detail[index] = self.item;
    self.update();
      $('#modalCambiarDescuento').modal('hide') 
     aplicarDescuento.value = 0
}

/**
* Actualizar item en el array
**/
function __actualizarItemArray(){
    // se convierte en decimal el porcentaje del descuento
    self.item.totalDescuentoDetalle = parseFloat((self.item.precio * (self.item.descuento / 100)) * self.item.cantidad );
    //Subtotal del Detalle
    self.item.subTotal    = parseFloat((self.item.precio * self.item.cantidad) - self.item.totalDescuentoDetalle);
    self.update()
    //calculo de la factura de todos los items
    __calculate();
}
/**
* calculacion de los detalle de la factura 
**/
function __calculate() {
    self.factura.subTotal = 0;
    self.factura.total    = 0;
    self.factura.iva      = 0;
    self.totalDescuentoDetalle =0;
    self.update()
    self.detail.forEach(function(e){
         self.factura.subTotal      += e.subTotal >0?e.subTotal:0
         self.factura.iva           += e.iva >0?e.iva:0
         self.totalDescuentoDetalle += e.totalDescuentoDetalle >0?e.totalDescuentoDetalle:0
    });
    self.factura.subTotal = self.factura.subTotal - self.factura.iva ;
    self.factura.subTotal = self.factura.subTotal - self.totalDescuentoDetalle ;
    self.factura.total    = self.factura.subTotal + self.factura.iva + self.factura.totalTransporte ;

    self.id_inventario = 0;
    self.precio        = 0;
    self.inventario    = null;
    self.descripcion   = '';
    self.update(); 
    $( "#producto" ).val(null);
    $( "#quantity" ).val(null);
}


/**
*-----------------Funciones para agregar articulos desde modal de articulos por sucursal
**/

function __informacionData(){
    self.idiomaDataTable = {
                "sProcessing":     "Procesando...",
                "sLengthMenu":     "Mostrar _MENU_ registros",
                "sZeroRecords":    "No se encontraron resultados",
                "sEmptyTable":     "Ningún dato disponible en esta tabla",
                "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
                "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
                "sInfoPostFix":    "",
                "sSearch":         "Buscar:",
                "sUrl":            "",
                "sInfoThousands":  ",",
                "sLoadingRecords": "Cargando...",
                "oPaginate": {
                    "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"
                },
                "oAria": {
                    "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                }
    }                              
    self.update()                      
}


/**
*  Informacion de la lista de articulos
**/
function __InformacionTabla_lista_articulos(){
 self.informacion_tabla = [	 {'data' : 'articulo'         ,"name":"articulo"         ,"title" : "Codigo"         ,"autoWidth":false,
                                    'render': function (articulo) {
                                             return articulo.codigo;
                                    }
                                },                                
                                {'data' : 'articulo'     ,"name":"articulo"     ,"title" : "Descripcion"     ,"autoWidth":false,
                                    'render': function (articulo) {
                                             return articulo.descripcion;
                                    }
                                },
                                {'data' : 'cantidad'        ,"name":"cantidad"        ,"title" : "Cantidad"        ,"autoWidth":false},
                                {'data' : 'precioPublico'   ,"name":"precioPublico"   ,"title" : "Precio Publico"  ,"autoWidth":false},
                                {'data' : 'precioMayorista' ,"name":"precioMayorista" ,"title" : "Precio Mayorista"  ,"autoWidth":false},
                                {'data' : 'precioEspecial'  ,"name":"precioEspecial"  ,"title" : "Precio Especial"  ,"autoWidth":false},
                                {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									 "render":function(id,type, row){
										    return __OpcionesInventarios(id,type,row);
	 							         }	 
								},
                                
                              ];
}


/**
*  Informacion de la lista de los clientes 
**/
function __InformacionTabla_lista_clientes(){
    self.informacion_tabla_clientes = [	
                                    {'data' : 'nombreCompleto'  ,"name":"nombreCompleto" ,"title" : "Nombre"   ,"autoWidth":false},
                                    {'data' : 'correoElectronico'           ,"name":"correoElectronico"          ,"title" : "Email"    ,"autoWidth":false},                                
                                    {'data' : 'telefono'        ,"name":"telefono"       ,"title" : "Telefono" ,"autoWidth":false},                                
                                    {'data' : 'movil'           ,"name":"movil"        ,"title" : "Celular"  ,"autoWidth":false},                                
                                    {'data' : 'descuento'       ,"name":"descuento"      ,"title" : "Descuento" ,"autoWidth":false},                                
                                    {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									     "render":function(id,type, row){
										    return __OpcionesClientes(id,type,row);
	 							         }	 
								    },
                              ];     

    self.update()
}



/**
*   Valores de Tipo Documento
**/

function __ValorTipoDocumento(tipoDocumento){
    if(tipoDocumento == "Tiquete"){
        return '<span class="label label-success">'+ "Tiquete" +'</span>';
    }

    if(tipoDocumento == "Proforma"){
        return '<span class="label label-success">'+ "Proforma" +'</span>';
    }

    if(tipoDocumento == "Factura"){
        return '<span class="label label-success">'+ "Factura" +'</span>';
    }
    if(tipoDocumento == "Orden"){
        return '<span class="label label-success">'+ "Orden" +'</span>';
    }

    return tipoDocumento

}

/**
*   Valores del Estado
**/

function __ValorEstado(estado){
    if(estado == 'Pendiente'){
        return '<span class="label label-success">'+ "Pendiente" +'</span>';
    }
    if(estado == 'Aplicada'){
        return '<span class="label label-success">'+ "Aplicada" +'</span>';
    }
    if(estado == 'Anulada'){
        return '<span class="label label-success">'+ "Anulada" +'</span>';
    }
    return estado
}
/**
*   Valores del Medio Pago
**/

function __ValorTipoPago(tipoPago){
    if(tipoPago == 'Contado'){
        return '<span class="label label-success">'+ "Contado" +'</span>';
    }
    if(tipoPago == 'Credito'){
        return '<span class="label label-success">'+ "Credito" +'</span>';
    }

    return tipoPago
}

/**
*    informacion de vendedores
**/
function  __InformacionTablaVendedores(){
    self.informacion_tabla_vendedores  = [	
                                    {'data' : 'nombreCompleto'     ,"name":"nombreCompleto"     ,"title" : $.i18n.prop("vendedor.nombreCompleto")   ,"autoWidth":false},
                                    {'data' : 'correoElectronico'  ,"name":"correoElectronico"  ,"title" : $.i18n.prop("vendedor.correoElectronico")    ,"autoWidth":false},                                
                                    {'data' : 'telefono'           ,"name":"telefono"           ,"title" : $.i18n.prop("vendedor.telefono") ,"autoWidth":false},                                
                                    {'data' : 'celular'            ,"name":"celular"            ,"title" : $.i18n.prop("vendedor.celular")  ,"autoWidth":false},                                
                                    {'data' : 'descuento'          ,"name":"descuento"          ,"title" : $.i18n.prop("vendedor.descuento") ,"autoWidth":false},                                
                                    {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									     "render":function(id,type, row){
										    return __OpcionesVendedores(id,type,row);
	 							         }	 
								    },
                              ];                                                            

    self.update()

}

/**
* Opciones del modal de articulos
*/
function __OpcionesClientes(){
  var agregar  = '<a href="#"  title="Seleccionar Cliente" class="btn btnAgregar btn-success form-control" title="Seleccione el cliente de la factura" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;
}

/**
* Opciones del modal de articulos
*/
function __OpcionesInventarios(){
  var agregar  = '<a href="#"  title="Agregar" class="btn btnAgregar btn-success form-control" title="Modificar" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;
}

/**
* Opciones del modal de vendedores
*/
function __OpcionesVendedores(){
  var agregar  = '<a href="#"  title="Agregar" class="btn btnAgregar btn-success form-control" title="Agregar Factura un Vendedor" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;
}



/**
* Agregar productos a la factura desde modal de articulos
**/
function __agregarInventarios() {
     $('#tableListaInventario').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListaInventario').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	     }
	    __buscarProducto(data.articulo.codigo)
    });
}




/**
* Agregar productos a la factura desde modal de articulos
**/
function __seleccionarVendedores() {
    $('#tableListaVendedores').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListaVendedores').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	     }
	    self.vendedor = data
        self.update();
        $('#modalListaDeVendedores').modal('hide') 
 
       
    });
}


/**
* Imprimir Factura
**/
function __seleccionarImprimir() {
    $('#tableListaFacturas').on('click', '.btnImprimir', function (e) {
         var table = $('#tableListaFacturas').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	     }
	   
        
        self.mostarParaCrearNuevaVentas  = false //muestra modulo de crear una nueva venta
        self.mostrarCodigoBarra          = true;
        self.mostrarNavegador            = false
        self.mostrarCategorias           = false //muestra la pantalla de imagenes de articulos   
        self.mostrarArticulosXCategoria  = false //muestra la pantalla de imagenes de categorias   
        self.mostrarFacturaImprimir      = true
        self.update()
        $("#boton-regresar").focus()
       
    });
}


/**
* cargar los estados de la compra
**/
function __ComboTipoPagos(){
    self.comboTipoPagos = []
    self.comboTipoPagos.push({
        estado:'Contado',
        descripcion:"Contado"
    })
    self.comboTipoPagos.push({
        estado:'Credito',
        descripcion:"Credito"
    })
    self.update()
}


/**
* cargar los tipos de Documento de la compra
**/
function __ComboTipoDocumentos(){
    self.comboTipoDocumentos = []
    self.comboTipoDocumentos.push({
        estado:'Factura',
        descripcion:"Factura"
    })

    
    self.comboTipoDocumentos.push({
        estado:'Proforma',
        descripcion:"Proforma"
    })
    self.comboTipoDocumentos.push({
        estado:'Orden',
        descripcion:"Orden"
    })


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

/**
*  teclas de la pantalla
**/      
function __Teclas(){
    window.addEventListener( "keydown", function(evento){
        var tecla = evento.keyCode; 
    if(tecla ==119){
        //Pantalla de imprimir F8 imprimir
        if( self.mostrarFacturaImprimir == true ){
          var objeto=document.getElementById('imprimeme');  //obtenemos el objeto a imprimir
          var ventana=window.open('','_blank');  //abrimos una ventana vacía nueva
          ventana.document.write(objeto.innerHTML);  //imprimimos el HTML del objeto en la nueva ventana
          ventana.document.close();  //cerramos el documento
          ventana.print();  //imprimimos la ventana
          ventana.close();  //cerramos la ventana
           $("#boton-regresar").focus()
        }
        // Facturar Modo de Pago
        if(self.mostrarFormularioPago == true){
          crearFactura(2,"Creacion de la Factura de Venta");  
        } 

        //llamar al modulo de pago desde la venta
        if(self.mostarParaCrearNuevaVentas == true){
             //No hay detalles registrados en la factura
            if(self.detail.length == 0 ){
                swal("Verificar","No hay detalles en la factura ", "info")
                return
            }
            self.mostarParaCrearNuevaVentas = false;
            self.mostrarFormularioPago = true
            self.update()
            $("#pago_efectivo").focus()

        }
        


    }    

    if(tecla ==27){//ESC
        //Pantalla imprimir regresar
        if( self.mostrarFacturaImprimir == true ){
            self.mostrarCamposIngresoContado = false
            
            self.mostarParaCrearNuevaVentas  = true 
            self.mostrarFacturaImprimir      = false
            
            self.update()
        }

    }
    }, false );
  
   

}

</script>
</venta-factura>