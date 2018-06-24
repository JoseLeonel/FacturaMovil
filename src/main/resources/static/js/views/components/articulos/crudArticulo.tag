<articulo-crud>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 show = {mostrarTituloArticulo}><i class="fa fa-cog"></i>&nbsp {$.i18n.prop("articulo.titulo")}  </h1>
            <h1 show = {mostrarTituloInventario}><i class="fa fa-cog"></i>&nbsp {$.i18n.prop("inventario.titulo")}  </h1>
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
                                <th class="table-header" >{$.i18n.prop("articulo.empresa")}       </th>
                                <th class="table-header" >{$.i18n.prop("articulo.codigo")}          </th>
                                <th class="table-header" >{$.i18n.prop("articulo.descripcion")}     </th>
                                <th class="table-header" >{$.i18n.prop("articulo.costo")}           </th>
                                <th class="table-header" >{$.i18n.prop("articulo.impuesto")}             </th>
                                <th class="table-header" >{$.i18n.prop("articulo.precioPublico")}   </th>
                                <th class="table-header" >{$.i18n.prop("articulo.precioMayorista")} </th>
                                <th class="table-header" >{$.i18n.prop("articulo.precioEspecial")}  </th>
                                <th class="table-header" >{$.i18n.prop("articulo.contable")}        </th>
                                <th class="table-header" >{$.i18n.prop("articulo.estado")}          </th>
                                <th class="table-header" > {$.i18n.prop("listado.acciones")}        </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>{$.i18n.prop("articulo.empresa")}       </th>
                                <th>{$.i18n.prop("articulo.codigo")}          </th>
                                <th>{$.i18n.prop("articulo.descripcion")}     </th>
                                <th>{$.i18n.prop("articulo.costo")}           </th>
                                <th>{$.i18n.prop("articulo.impuesto")}             </th>
                                <th>{$.i18n.prop("articulo.precioPublico")}   </th>
                                <th>{$.i18n.prop("articulo.precioMayorista")} </th>
                                <th>{$.i18n.prop("articulo.precioEspecial")}  </th>
                                <th>{$.i18n.prop("articulo.contable")}        </th>
                                <th>{$.i18n.prop("articulo.estado")}          </th>
                                <th >                                         </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>    
</div>
<!-- Fin del Listado -->
<div  >
    <div class="row center " show ={mostrarFormulario} >
   
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {articulo.id > 0 ? $.i18n.prop("titulo.modificar.articulo")   :$.i18n.prop("titulo.agregar.articulo")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{articulo.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.empresa")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control selectEmpresa" onchange= {__cargarCombos}  name="empresa"   >
                                    <option  each={empresas.aaData}  value="{id}" selected="{articulo.empresa.id ==id?true:false}" >{nombre}</option>
                                </select>
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.categoria")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control selectCategoria"   name="categoria" >
                                    <option  each={categorias.aaData}  value="{id}" selected="{articulo.categoria.id ==id?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        </div>    
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.marca")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control selectMarca"  name="marca">
                                    <option  each={marcas.aaData}  value="{id}" selected="{articulo.marca.id ==id?true:false}"  >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.unidadMedida")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control selecTipoUnidad" name="unidadMedida" >
                                    <option   each={tipoUnidades.aaData}  value="{codigo}"  selected="{articulo.unidadMedida ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        </div>    

                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.codigo")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control codigo" id="codigo" name="codigo" value="{articulo.codigo}"  >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.descripcion")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control descripcion" id="descripcion" name="descripcion" value="{articulo.descripcion}"  >
                            </div>
                        </div>

                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.serie")}  </label>
                                <input type="text" class="form-control serie" id="serie" name="serie" value="{articulo.serie}" >
                            </div>

                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.costo")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control costo" id="costo" name="costo" value="{articulo.costo}"  onkeyup ={__ActualizarPreciosCosto}>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.impuesto")}  </label>
                                <input type="text" class="form-control impuesto" id="impuesto" name="impuesto" value="{articulo.impuesto}"  onkeyup ={__ActualizarPreciosImpuestos}>
                            </div>

                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.precioPublico")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control precioPublico" id="precioPublico" name="precioPublico" onkeyup ={__CalculoGananciaPublico} value="{articulo.precioPublico}"  >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.gananciaPrecioPublico")}  </label>
                                <input type="text" class="form-control gananciaPrecioPublico" id="gananciaPrecioPublico" name="gananciaPrecioPublico" value="{articulo.gananciaPrecioPublico}"  readonly>
                            </div>

                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.precioMayorista")}  </label>
                                <input type="text" class="form-control precioMayorista" id="precioMayorista" name="precioMayorista" value="{articulo.precioMayorista}" onkeyup={__CalculoGananciaMayorista} >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.gananciaPrecioMayorista")}  </label>
                                <input type="text" class="form-control gananciaPrecioMayorista" id="gananciaPrecioMayorista" name="gananciaPrecioMayorista" value="{articulo.gananciaPrecioMayorista}"  readonly>
                            </div>

                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.precioEspecial")}  </label>
                                <input type="text" class="form-control precioEspecial" id="precioEspecial" name="precioEspecial" value="{articulo.precioEspecial}"  onkeyup={__CalculoGananciaEspecial}>
                            </div>
                        </div>

                        <div class="row">
                            
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label class="knob-label" >{$.i18n.prop("articulo.gananciaPrecioEspecial")}  </label>
                                <input type="text" class="form-control gananciaPrecioEspecial" id="gananciaPrecioEspecial" name="gananciaPrecioEspecial" value="{articulo.gananciaPrecioEspecial}"  readonly>
                            </div>
                            <div class="col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label class="knob-label">{$.i18n.prop("articulo.contable")}</label>
                                <select  class="form-control" id="contable" name="contable" >
                                    <option  each={contables}  value="{codigo}" selected="{articulo.contable ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        
                            <div class="col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label class="knob-label">{$.i18n.prop("articulo.estado")}</label>
                                <select  class="form-control" id="estado" name="estado"  >
                                    <option  each={estados}  value="{codigo}" selected="{articulo.estado ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            

                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label">{$.i18n.prop("articulo.tipoImpuesto")}</label>
                                <select  class="form-control selectTipoImpuesto" id="tipoImpuesto" name="tipoImpuesto"  >
                                    <option  each={impuestos}  value="{codigo}" selected="{articulo.tipoImpuesto ==codigo?true:false}"  >{descripcion}</option>
                                </select>
                            </div>
                      
                            

                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                     <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
                     <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>
                  
                </div>
            </div>   
        </div>
       
    </div>
</div>
<!-- Listado  de inventario-->
<div classs="contenedor-listar container" id="container"  show={mostrarListadoInventario}  >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                    <table id="tableListarInventario" class="display table responsive table-hover nowrap table-condensed tableListarInventario"   cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="table-header" >{$.i18n.prop("articulo.codigo")}       </th>
                                <th class="table-header" >{$.i18n.prop("articulo.descripcion")}  </th>
                                
                                <th class="table-header" >{$.i18n.prop("inventario.cantidad")}    </th>
                                <th class="table-header" >{$.i18n.prop("inventario.minimo")}      </th>
                                <th class="table-header" >{$.i18n.prop("inventario.maximo")}      </th>
                                <th class="table-header" >{$.i18n.prop("inventario.created_at")}  </th>
                                <th class="table-header" >{$.i18n.prop("inventario.updated_at")}  </th>
                                <th class="table-header" >{$.i18n.prop("inventario.estado")}      </th>
                                <th class="table-header" >{$.i18n.prop("listado.acciones")}       </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>{$.i18n.prop("articulo.codigo")}       </th>
                                <th>{$.i18n.prop("articulo.descripcion")}  </th>
                                <th>{$.i18n.prop("inventario.cantidad")}    </th>
                                <th>{$.i18n.prop("inventario.minimo")}     </th>
                                <th>{$.i18n.prop("inventario.maximo")}     </th>
                                <th>{$.i18n.prop("inventario.created_at")}  </th>
                                <th>{$.i18n.prop("inventario.updated_at")} </th>
                                <th>{$.i18n.prop("inventario.estado")}     </th>
                                <th>  </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>  
        <div class="box-footer">
            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
               {$.i18n.prop("btn.volver")}
            </button>
                  
        </div>  
</div>
<!-- fin Listado  de inventario-->
<!-- Agregar inventario-->
<div  >
    <div class="row center " show ={mostrarFormularioInventario} >
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {inventario.id > 0 ? $.i18n.prop("titulo.modificar.inventario")   :$.i18n.prop("titulo.agregar.inventario")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formularioInventario" name ="formularioInventario"   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{inventario.id}">
                        
                        <input type="hidden" name="IdArticulo" id="IdArticulo" value="{articulo.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.codigo")} </label>
                                <input type="text" class="form-control codigo_inventario" id="codigo" name="codigo" value="{articulo.codigo}"  readonly>
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.descripcion")}  </label>
                                <input type="text" class="form-control descripcion" id="descripcion" name="descripcion" value="{articulo.descripcion}"  readonly>
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("articulo.serie")}  </label>
                                <input type="text" class="form-control serie" id="serie" name="serie" value="{articulo.serie}" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("inventario.minimo")} </label>
                                <input type="text" class="form-control minimo" id="minimo" name="minimo" value="{inventario.minimo}"  >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("inventario.maximo")} </label>
                                <input type="text" class="form-control maximo" id="maximo" name="maximo" value="{inventario.maximo}"  >
                            </div>
                        </div>

                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("inventario.cantidad")} </label>
                                <input type="text" class="form-control cantidad" id="cantidad" name="cantidad" value="{inventario.cantidad}" readonly = "{inventario.id>0?true:false}" >
                            </div>

                            <div class="col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label class="knob-label">{$.i18n.prop("articulo.estado")}</label>
                                <select  class="form-control" id="estado" name="estado" >
                                    <option  each={estados}  value="{codigo}" selected="{inventario.estado ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        </div>

                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListadoInventario}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                     <button  onclick={__ModificarInventario} show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
                     <button show = {botonAgregar}   onclick={__agregarInventario}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>
                  
                </div>
            </div>   
        </div>
       
    </div>
</div>
<!-- fin Agregar inventario-->
<!-- Formulario de crear una Entrada-->
<div  >
    <div class="row center " show={mostrarFormularioEntrada}>
        <div class="col-md-2 col-sx-12 col-sm-2 col-lg-2"></div>
        <div class="col-md-8 col-sx-12 col-sm-8 col-lg-8">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-search"></i>&nbsp{$.i18n.prop("kardex.agregar.entrada")}  </h1>
                </div>
                <div class="box-body">
                    <form id = "formularioEntrada" name ="formularioEntrada " class="advanced-search-form">
                        <input type="hidden" name="IdInventario" id="IdInventario" value="{inventario.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")}</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("articulo.codigo")}</label>
                                <input type="text" class="form-control"   value="{inventario.articulo.codigo}"   readonly>
                            </div>

                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("articulo.descripcion")}</label>
                                <input type="textArea" class="form-control" value="{inventario.articulo.descripcion}" readonly>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label">{$.i18n.prop("kardex.motivos")} <span class="requeridoDato">*</span></label>
                                <select  class="form-control selectEntrada" id="motivo" name="motivo" data-live-search="true">
                                    <option data-tokens="{descripcion}" each={motivoEntradas.data}  value="{descripcion}"  >{descripcion}</option>
                                </select>
                            </div>
                           
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("kardex.cantidadActual")}</label>
                                <input type="number" step="any" class="form-control"  value="{inventario.cantidad}"  readonly >
                            </div>
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("kardex.cantidadNueva")}<span class="requeridoDato">*</span> </label>
                                <input onkeyup= {MostrarBotonAgregarEntrada} type="number" step="any"  class="form-control cantidadNueva_entrada" id="cantidadNueva" name="cantidadNueva"   >
                            </div>

                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" > {$.i18n.prop("kardex.observacion")}<span class="requeridoDato">*</span></label>
                                <textarea class="form-control observacion_entrada " rows="5" id="observacion" name="observacion"></textarea>
                            </div>
                        </div>

                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListadoInventario}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                     <button show ={mostrarBotonAgregarEntrada} onclick={__agregarEntradaInventario}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>
                  
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-sx-12 col-sm-2 col-lg-2"></div>
    </div>
</div>
<!-- Fin Formulario -->   

<!-- Formulario de crear una Salida-->
<div  >
    <div class="row center " show={mostrarFormularioSalida}>
        <div class="col-md-2 col-sx-12 col-sm-2 col-lg-2"></div>
        <div class="col-md-8 col-sx-12 col-sm-8 col-lg-8">
            <div class="box box-solid box-danger">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-search"></i>&nbsp {$.i18n.prop("kardex.agregar.salida")}   </h1>
                </div>
                <div class="box-body">
                    <form id = "formularioSalida" name ="formularioSalida " class="advanced-search-form">
                        
                        <input type="hidden" name="IdInventario" id="IdInventario" value="{inventario.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")}</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("articulo.codigo")}  </label>
                                <input type="text" class="form-control"  value="{inventario.articulo.codigo}"  readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("articulo.descripcion")}</label>
                                <input type="text" class="form-control " value="{inventario.articulo.descripcion}"  readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label">{$.i18n.prop("kardex.motivos")} <span class="requeridoDato">*</span></label>
                                <select  class="form-control selectMotivoSalida" id="motivo" name="motivo" data-live-search="true">
                                    <option data-tokens="{descripcion}"  each={motivoSalidas.data}  value="{descripcion}"  >{descripcion}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("kardex.cantidadActual")} </label>
                                <input type="number" step="any" class="form-control" id="cantidadActual" name="cantidadActual" value="{inventario.cantidad}" disabled readonly >
                            </div>
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("kardex.cantidadDismunuir")}<span class="requeridoDato">*</span> </label>
                                <input onkeyup= {MostrarBotonAgregarSalida} type="number" step="any"  class="form-control cantidadNueva_salida cantidadNueva" id="cantidadNueva" name="cantidadNueva"   >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("kardex.observacion")} <span class="requeridoDato">*</span></label>
                                <textarea class="form-control observacion_salida" rows="5" id="observacion" name="observacion"></textarea>
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListadoInventario}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                     <button  show={mostrarBotonAgregarSalida} onclick={__agregarSalidaInventario}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>
                  
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-sx-12 col-sm-2 col-lg-2"></div>
    </div>
</div>
<!-- Fin Formulario -->   

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
    border-width: 5px;
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
    self.empresas                  = {aaData:[]}
    self.categorias                = {aaData:[]}
    self.marcas                    = {aaData:[]}
    self.tipoUnidades              = {aaData:[]}
    self.motivoEntradas              = {data:[]}
    self.motivoSalidas              = {data:[]}
      self.impuestos =[]
    self.contables                 = []
    self.estados                   = []
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarTituloArticulo     = true
    self.articulo = {
		id:null,
        codigo:"",
		descripcion:"",
        serie:"",
		unidadMedida:"",
		costo:"",
		impuesto:0,
        minimo:0,
        maximo:0,
		precioPublico:null,
		gananciaPrecioPublico:null,
		precioMayorista:null,
		gananciaPrecioMayorista:null,
		precioEspecial:null,
		gananciaPrecioEspecial:null,
		estado:"",
		marca:{
            id:null
        },
		categoria:{
            id:null
        },
		empresa:{
            id:null
        }
    }    
    self.inventario = {
        id:null,
		cantidad:0,
		minimo:0,
		maximo:0,
		estado:"",
		articulo:{
            id:null
        }
    }
    // variables para modulo de inventario 
    self.mostrarListadoInventario = false
    self.mostrarTituloInventario     = false
    self.mostrarFormularioInventario = false
    self.mostrarFormularioEntrada    = false
    self.mostrarFormularioSalida     = false
    self.mostrarBotonAgregarEntrada  = false
    self.mostrarBotonAgregarSalida   = false 
    self.formato_tabla_inventario = []         // Formato del Listado de la Tabla 


self.on('mount',function(){
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListar')
    __listado()
    includeActionsArticulo('.dataTables_wrapper','.dataTables_length')
    __listadoEmpresasActivas()
    __MantenimientoAgregarInventario()
    __MantenimientoAgregar()
    __Eventos()
    __ComboEstados()
    __ComboContables()
    __listadoTipoUnidadesActivas()   
    __Impuestos() 
    
    $('.selectTipoImpuesto').prop("selectedIndex", 0);
    $('.selecTipoUnidad').prop("selectedIndex", 0);
    $('.selectMarca').prop("selectedIndex", 0);
    $('.selectCategoria').prop("selectedIndex", 0);
    $('.selectEmpresa').prop("selectedIndex", 0);
})
/**
*  Envia  a llamar a los eventos ajax de cada combo para actualizarlo de acuerdo a la empresa
**/
__cargarCombos(){
  enviarCargarCombos()
}
/**
* Enviar a consultar al back end 
**/
function enviarCargarCombos(){
    __listadoCategoriasActivas($('.selectEmpresa').val())
    __listadoMarcasActivas($('.selectEmpresa').val())
}
/**
* incluir el boton agregar en cada mantenimiento 
**/
function includeActionsArticulo(dataTables_wrapper,dataTables_length) {
    $( ".btn-agregar" ).remove();
    $( ".btn-agregarInventario" ).remove();
    var parent = $(dataTables_wrapper);
    var header_pointer = $(dataTables_length);
    var header_length = header_pointer.html();
    var new_header = "<div  class='new-header-with-actions' style='padding-top:0px; padding-bottom:0px;'>";
    new_header += "<div class='add-new btn-agregar' ><i class='fa fa-plus'></i> Agregar</div>";
    new_header += "</div>";
    parent.prepend(new_header);
}
/**
*incluir el boton agreagar de inventario
**/
function includeActionsInventario(dataTables_wrapper,dataTables_length) {
    $( ".btn-agregarInventario" ).remove();
    $( ".btn-agregar" ).remove();
    var parent = $(dataTables_wrapper);
    var header_pointer = $(dataTables_length);
    var header_length = header_pointer.html();
    var new_header = "<div  class='new-header-with-actions' style='padding-top:0px; padding-bottom:0px;'>";
    new_header += "<div class='add-new btn-agregarInventario' ><i class='fa fa-plus'></i> Agregar</div>";
    new_header += "</div>";
    parent.prepend(new_header);
    
    
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
            codigo : {
				required : true,
                maxlength:20,
                minlength:1,
			} ,
            costo : {
				required : true,
                numeroMayorCero:true,
                number:true,
			} ,                                                
            marca : {
				required : true,
			},                                                
            categoria : {
				required : true,
			},                                                
            unidadMedida : {
				required : true,
			},                                                
            precioPublico : {
				required : true,
                numeroMayorCero:true,
                number:true,
			} ,                                                
            gananciaPrecioPublico : {
				required : true,
			}                                              
                        
		},
		ignore : []

	});
	return validationOptions;
};
/**
* Camps requeridos Entrada
**/
var reglasDeValidacionEntrada = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			observacion : {
				required : true,
                maxlength:255,
			},
            cantidadNueva : {
				required : true,
                maxlength:20,
                numeroMayorCero:true,
                minlength:1,
			} 
		},
		ignore : []

	});
	return validationOptions;
};
/**
* Camps requeridos Salida
**/
var reglasDeValidacionSalida = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			observacion : {
				required : true,
                maxlength:255,
			},
            cantidadNueva : {
				required : true,
                maxlength:20,
                numeroMayorCero:true,
                minlength:1,
			} 
		},
		ignore : []

	});
	return validationOptions;
};

/**  funciones de inventario ----------------------------------------------------------------------------**/
/**
*  Agregar entrada al inventario 
**/
__agregarEntradaInventario(){
      if ($("#formularioEntrada").valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formularioEntrada").serialize();
        swal({
           title: '',
           text: $.i18n.prop("kardex.mensaje.alert.agregar.entrada"),
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
                    url : 'AgregarEntradaKardex.do',
                    success : function(data) {
                        console.log(data);
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
                            $(".errorServerSideJgrid").remove();
                            
                            $('#observacion_entrada').val(null)
                            $('#cantidadNueva_entrada').val(null)
                            self.mostrarListado            = false
                            self.mostrarTituloArticulo     = false
                            self.botonModificar            = false
                            self.botonAgregar              = false
                            self.mostrarFormularioInventario = false 
                            self.mostrarListadoInventario = true
                            self.mostrarTituloInventario  = true
                            self.mostrarFormularioEntrada    = false
                            self.mostrarFormularioSalida     = false
                           self.update()
                            __listadoInventario();                            
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
*  Agregar salida al inventario 
**/
__agregarSalidaInventario(){
   if ($("#formularioSalida").valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formularioSalida").serialize();
        swal({
           title: '',
           text: $.i18n.prop("kardex.mensaje.alert.agregar.salida"),
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
                    url : 'AgregarSalidaKardex.do',
                    success : function(data) {
                        console.log(data);
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
	                           confirmButtonText: 'Aceptar',
	                         })
                            $(".errorServerSideJgrid").remove();
                            $('#observacion_salida').val(null)
                            $('#cantidadNueva_salida').val(null)
                            self.mostrarListado            = false
                            self.mostrarTituloArticulo     = false
                            self.botonModificar            = false
                            self.botonAgregar              = false
                            self.mostrarFormularioInventario = false 
                            self.mostrarListadoInventario = true
                            self.mostrarTituloInventario  = true
                            self.mostrarFormularioEntrada    = false
                            self.mostrarFormularioSalida     = false
                            self.update()
                            __listadoInventario();                            
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
*   Agregar 
**/
__agregarInventario(){
     if ($("#formularioInventario").valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formularioInventario").serialize();
        swal({
           title: '',
           text: $.i18n.prop("inventario.mensaje.alert.agregar"),
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
                    url : 'AgregarInventarioAjax.do',
                    success : function(data) {
                        console.log(data);
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
                            $(".errorServerSideJgrid").remove();
                            $('#maximo').val(null)
                            $('#minimo').val(null)
                            $('#cantidad').val(null)
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
** Modificar Inventario
**/
__ModificarInventario(){
    self.error = false;
    self.exito = false;
    self.update();
    __modificarClase("#formularioInventario",$.i18n.prop("inventario.mensaje.alert.modificar"),'ModificarInventarioAjax.do','ListarInventarioAjax.do','#tableListarInventario')
}
/** Listado de inventario **/  
function __listadoInventario(){
     var formulario = $('#formulario').serialize();
    $("#tableListarInventario").dataTable().fnClearTable(); 
    __InicializarTabla('.tableListarInventario')  
    __MantenimientoAgregarInventario()
    $.ajax({
        url: "ListarInventarioAjax.do",
        datatype: "json",
        method:"GET",
        data: formulario,
        success: function (result) {
            console.log(result)
             if(result.aaData.length > 0){
                __InformacionDataTable_Inventario();
                loadListar(".tableListarInventario",idioma_espanol,self.formato_tabla_inventario,result.aaData)
                agregarInputsCombosInventario();
                ActivarEventoFiltro(".tableListarInventario")
                __MantenimientoAgregarInventario()
                __agregarEntradaAlInventario()
                __agregarSalidaAlInventario()
                __modificar_Inventario()
                includeActionsInventario('.dataTables_wrapper','.dataTables_length')

             }else{
                 includeActionsInventario('.dataTables_wrapper','.dataTables_length')
             }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
}
/**
*Formato del listado de inventario
**/
function __InformacionDataTable_Inventario(){
    self.formato_tabla_inventario = [ 
                               {'data' :'id'         ,"name":"id"         ,"title" : $.i18n.prop("articulo.codigo")           ,"autoWidth" :true ,
                                    "render":function(id,type, row){
                                        return row.articulo !=null?row.articulo.codigo:"";
                                    }
                               },
                               {'data' :'id'    ,"name":"id"    ,"title" : $.i18n.prop("articulo.descripcion")      ,"autoWidth" :true ,
                                    "render":function(articulo,type, row){
                                        return row.articulo !=null?row.articulo.descripcion:"";
                                    }
                               },
                               {'data' :'cantidad'                ,"name":"cantidad"                ,"title" : $.i18n.prop("inventario.cantidad")     ,"autoWidth" :true },
                               {'data' :'minimo'                  ,"name":"minimo"                  ,"title" : $.i18n.prop("inventario.minimo")       ,"autoWidth" :true },
                               {'data' :'maximo'                  ,"name":"maximo"                  ,"title" : $.i18n.prop("inventario.maximo")       ,"autoWidth" :true },
                               {'data' :'created_at'              ,"name":"created_at"              ,"title" : $.i18n.prop("inventario.created_at")   ,"autoWidth" :false,
                                    "render":function(created_at,type, row){
                                        return __displayDate_detail(created_at);
                                    }
                                },
                                {'data' : 'updated_at'            ,"name":"updated_at"              ,"title" : $.i18n.prop("inventario.updated_at")         ,"autoWidth" :false,
                                    "render":function(updated_at,type, row){
                                        return __displayDate_detail(updated_at);
                                    }
                                },
                               {'data' : 'estado'                 ,"name":"estado"                  ,"title" : $.i18n.prop("inventario.estado")         ,"autoWidth" :false},
                               {'data' : 'id'            ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __OpcionesInventario(id,type,row);
                                 }
	      		            }];
    self.update();
   
}
/**
* Opciones listado de los clientes
*/
function __OpcionesInventario(id,type,row){
    let menu = '<div class="dropdown">' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    
    menu += '<li><a href="#"  title="Modificar" class="  btnModificar" >Modificar</a></li>'
        menu += '<li><a href="#"  title="Entrada al inventario"  class="  btnEntrada" >Entrada</a></li>'
    if(row.cantidad > 0 ){
       menu += '<li><a href="#"  title="Salida al inventario" class="  btnSalida" >Salida</a></li>'
    }
     menu += "</ul></div>"  

     return menu;          
}
 
/**
 * Funcion para Modificar del Listar
 */
function __modificar_Inventario(){
	$('#tableListarInventario').on('click','.btnModificar',function(e){
		var table = $('#tableListarInventario').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        $(".errorServerSideJgrid").remove();
        $('#maximo').val(null)
        $('#minimo').val(null)
        $('#cantidad').val(null)
        self.inventario  = data
        self.mostrarListado            = false 
        self.botonModificar            = true
        self.botonAgregar              = false
        self.mostrarTituloArticulo     = false
        $(".errorServerSideJgrid").remove();
        $('#maximo').val(self.inventario.maximo)
        $('#minimo').val(self.inventario.minimo)
        $('#cantidad').val(self.inventario.cantidad)
    // variables para modulo de inventario 
        self.mostrarListadoInventario = false
        self.mostrarTituloInventario     = true
        self.mostrarFormularioInventario = true
        self.mostrarFormularioEntrada    = false
        self.mostrarFormularioSalida     = false
        self.mostrarBotonAgregarEntrada  = false
        self.mostrarBotonAgregarSalida   = false 
        self.update()
	});
}
/**
 * Funcion agregar una entrada
 */
function __agregarEntradaAlInventario(){
	$('#tableListarInventario').on('click','.btnEntrada',function(e){
        $("#formularioEntrada").validate(reglasDeValidacionEntrada());
        $(".errorServerSideJgrid").remove();
		var table = $('#tableListarInventario').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        $(".observacion_entrada").val(null)
        $(".cantidadNueva_entrada").val(null)
        self.inventario  = data
        self.mostrarListado            = false 
        self.botonModificar            = false
        self.botonAgregar              = false
        self.mostrarTituloArticulo     = false
        // variables para modulo de inventario 
        self.mostrarListadoInventario = false
        self.mostrarTituloInventario     = true
        self.mostrarFormularioInventario = false
        self.mostrarFormularioEntrada    = true
        self.mostrarFormularioSalida     = false
        self.update()
        _ListaMotivoEntradasActivas(self.inventario.articulo.empresa.id)
	});
}
/**
 * Funcion agregar una entrada
 */
function __agregarSalidaAlInventario(){
	$('#tableListarInventario').on('click','.btnSalida',function(e){
        $("#formularioSalida").validate(reglasDeValidacionSalida());
        $(".errorServerSideJgrid").remove();
		var table = $('#tableListarInventario').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        $(".observacion_salida").val(null)
        $(".cantidadNueva_salida").val(null)
        self.inventario  = data
        self.mostrarListado            = false 
        self.botonModificar            = false
        self.botonAgregar              = false
        self.mostrarTituloArticulo     = false
        // variables para modulo de inventario 
        self.mostrarListadoInventario = false
        self.mostrarTituloInventario     = true
        self.mostrarFormularioInventario = false
        self.mostrarFormularioEntrada    = false
        self.mostrarFormularioSalida     = true
        self.update()
        _ListaMotivoSalidasActivas(self.inventario.articulo.empresa.id)
	});
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombosInventario(){
     // Agregar los input de busqueda 
    $('.tableListarInventario tfoot th').each( function (e) {
        var title = $('.tableListarInventario thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 11    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}
/**
*  Regresar al listado Inventario
**/
__regresarAlListadoInventario(){
    swal({
        title: "", 
        text: $.i18n.prop("mensaje.alert.regresar.listado"), 
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: '#00539B',
        cancelButtonColor: '#d33',
        confirmButtonText:$.i18n.prop("confirmacion.si"),
        cancelButtonText: $.i18n.prop("confirmacion.no"),
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger'
        }).then(function (isConfirm) {
            if(isConfirm){
                self.mostrarListado            = false
                self.mostrarTituloArticulo     = false
                self.botonModificar            = false
                self.botonAgregar              = false
                // variables para modulo de inventario
                self.mostrarFormularioInventario = false 
                self.mostrarListadoInventario = true
                self.mostrarTituloInventario  = true
                self.mostrarFormularioEntrada    = false
                self.mostrarFormularioSalida     = false
                self.update()
                __listadoInventario();

            }
    });    
}

// Mostrar formulario de mantenimiento Agregar
function __MantenimientoAgregarInventario(){
      //Inicializar el Formulario
    $('.dataTables_wrapper').on('click','.btn-agregarInventario',function(e){
        inicializar_inventario()                // modelo o domain   
        //desahabilita  listado 
        self.mostrarListado   = false;
        self.mostrarFormulario  = false 
        //desahabilita boton modificar
        self.botonModificar   = false;
        // habilita el formulario
        self.botonAgregar     = true;
        self.mostrarListadoInventario = false
        self.mostrarTituloInventario     = false
        self.mostrarFormularioInventario = true
        self.mostrarFormularioEntrada    = false
        self.mostrarFormularioSalida     = false
        self.update();
        //Inicializar el Formulario
        $(".errorServerSideJgrid").remove();
    })
}
/**
*inicializar modelo de inventario
**/
function inicializar_inventario(){
    self.inventario = {
        id:null,
		cantidad:0,
		minimo:0,
		maximo:0,
		estado:"",
		articulo:{
            id:null
        }
    }
    self.update()
}
/**
*  Lista de motivos de Salidas activas 
**/
function _ListaMotivoSalidasActivas(empresa){
    $.ajax({
         url: "ListarMotivoSalidasActivasAjax.do",
        datatype: "json",
        method:"GET",
        contentType: "application/json; charset=utf-8",
        data:{idEmpresa:empresa},
        success: function (result) {
            if(result.aaData.length > 0){
                self.motivoSalidas.data =  result.aaData
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
*  Lista de motivos de Entradas activas 
**/
function _ListaMotivoEntradasActivas(empresa){
    $.ajax({
         url: "ListarMotivoActivasEntradasAjax.do",
        datatype: "json",
        method:"GET",
        data:{idEmpresa:empresa},
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if(result.aaData.length > 0){
                self.motivoEntradas.data =  result.aaData
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
* muestra el boton de agregar en una entrada hasta que el valor de cantidad nueva sea mayor a cero
**/
MostrarBotonAgregarEntrada(e){
 let cantidad        = __valorNumerico(e.target.value)
 self.mostrarBotonAgregarEntrada = cantidad > 0 ? true:false
 self.update()
}
/**
* muestra el boton de agregar en una salida hasta que el valor de cantidad nueva sea mayor a cero o sea menor o igual cantidad inventario
**/
MostrarBotonAgregarSalida(e){
 let cantidad        = __valorNumerico(e.target.value)
 self.mostrarBotonAgregarSalida = cantidad > 0 ? true:false
 self.mostrarBotonAgregarSalida = cantidad > self.inventario.cantidad ? false:self.mostrarBotonAgregarSalida
 self.update()
}
/** Fin  funciones de inventario ----------------------------------------------------------------------------**/
/**
*  Ganancia del precio mayorista
**/
__CalculoGananciaMayorista(e){
  let precio        = __valorNumerico(e.target.value)
  if(precio == 0 ){
      return
  }
  let impuesto      = __valorNumerico($('#impuesto').val())
  let costo         = __valorNumerico($('#costo').val())
  self.articulo.gananciaPrecioMayorista  = costo > 0? _porcentajeGanancia(costo,impuesto,precio):0
  self.articulo.precioMayorista = precio
  self.update()
    
}
/**
* ganancia del precio especial
**/
__CalculoGananciaEspecial(e){
  let precio = __valorNumerico(e.target.value)
  if(precio == 0){
      return
  }
  let impuesto = __valorNumerico($('#impuesto').val())
  let costo    = __valorNumerico($('#costo').val())
  self.articulo.gananciaPrecioEspecial  = costo > 0?_porcentajeGanancia(costo,impuesto,precio):0
  self.articulo.precioEspecial = precio
  self.update()
}
/**
* Actualizar ganancias al digitar el impuesto
**/
__ActualizarPreciosImpuestos(e){
    let impuesto  = __valorNumerico(e.target.value)
    if(impuesto ==0){
        return
    }
    let costo     =  __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioEspecial   = self.articulo.precioEspecial > 0 ? _porcentajeGanancia(costo,impuesto,self.articulo.precioEspecial):0
    self.articulo.gananciaPrecioMayorista  = self.articulo.precioMayorista > 0 ? _porcentajeGanancia(costo,impuesto,self.articulo.precioMayorista):0
    self.articulo.gananciaPrecioPublico    = self.articulo.precioPublico > 0 ? _porcentajeGanancia(costo,impuesto,self.articulo.precioPublico):0
    self.update()
}
/**
* Porcentaje de ganancia de Precio al Publico
**/
__CalculoGananciaPublico(e){
    let precioPublico = __valorNumerico(e.target.value)
    if(precioPublico ==0){
        return
    }
    let impuesto      = __valorNumerico($('#impuesto').val())
    let costo         = __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioPublico  = costo > 0 ?_porcentajeGanancia(costo,impuesto,precioPublico):0
    self.articulo.precioPublico = precioPublico
    self.update()
}
/**
* Actualizar el precio costo
**/
__ActualizarPreciosCosto(e){
    let costo    = __valorNumerico(e.target.value)
    if(costo == 0){
        self.articulo.gananciaPrecioEspecial   = 0
        self.articulo.gananciaPrecioMayorista  = 0
        self.articulo.gananciaPrecioPublico    = 0
        self.update()
        return
    }
    let impuesto =  __valorNumerico($('#impuesto').val())
    self.articulo.costo = costo 
    self.articulo.gananciaPrecioEspecial   = self.articulo.precioEspecial > 0?_porcentajeGanancia(costo,impuesto,self.articulo.precioEspecial):0
    self.articulo.gananciaPrecioMayorista  = self.articulo.precioMayorista>0?_porcentajeGanancia(costo,impuesto,self.articulo.precioMayorista):0
    self.articulo.gananciaPrecioPublico    = self.articulo.precioPublico >0?_porcentajeGanancia(costo,impuesto,self.articulo.precioPublico):0
    self.update()
}
/**
* autor : Leonel Hernandez Chaverri
* Fecha : 23-06-17
* obtener la ganancia del precio en decimal
**/
function _porcentajeGanancia(costo,impuesto,precioVenta) {
  let porcentajeGanancia = 0;
  let precioSinImpuesto  = 0;
  if(costo == 0){
    return 0;
  }
  if(precioVenta == 0){
    return 0;
  }
  if(impuesto == 0 || impuesto == null ){
     porcentajeGanancia  = 1-(costo/precioVenta);
  }else{
     precioSinImpuesto = precioVenta/((impuesto/100) + 1);
     porcentajeGanancia  = (1-(costo/precioSinImpuesto));
  }
  return porcentajeGanancia * 100;
}
/**
*  retorna el valor numerico o cero sino es numerico
**/
function __valorNumerico(valor){
    if(valor ==null){
        return
    }
    return isNumber(valor)?parseFloat(valor):0 ;
}
/**
*  Validar si es numero
**/
function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}
/**
*  Actimpuestor validaciones del formulario
**/
function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#descripcion").attr("maxlength", 80);
    $("#codigo").attr("maxlength", 20);
    $('#impuesto').mask('00', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});

}
/**
*  Mostrar listado datatable Empresas activas
**/
function __listadoEmpresasActivas(){
    $.ajax({
         url: "ListarEmpresasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.empresas.aaData =  result.aaData
                self.update();
                 enviarCargarCombos()
              
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}
/**
*  Mostrar listado datatable Categorias activas
**/
function __listadoCategoriasActivas(empresa){
     self.categorias                = {aaData:[]}
     self.update()
    $.ajax({
         url: "ListarCategoriasActivasAjax.do",
        datatype: "json",
        method:"GET",
        data:{idEmpresa:empresa},
        success: function (result) {
             if(result.aaData.length > 0){
                self.categorias.aaData =  result.aaData
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
*  Mostrar listado datatable Categorias Actimpuestos
**/
function __listadoMarcasActivas(empresa){
    self.marcas                    = {aaData:[]}
    self.update()
    $.ajax({
         url: "ListarMarcasActivasAjax.do",
        datatype: "json",
        method:"GET",
        data:{idEmpresa:empresa},
        success: function (result) {
            if(result.aaData.length > 0){
                self.marcas.aaData =  result.aaData
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
*  Mostrar listado datatable unidades de medidas activas
**/
function __listadoTipoUnidadesActivas(){
    $.ajax({
         url: "ListarTipoUnidadesAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.tipoUnidades.aaData =  result.aaData
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
*  Crear el combo de estados
**/
function __ComboEstados(){
    self.estados =[]
    self.estados.push({
        codigo: $.i18n.prop("combo.estado.Activo"),
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
    self.estados.push({
        codigo: $.i18n.prop("combo.estado.Inactivo"),
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
     self.update();
}
/**
* Combo para verificar si es contabilizado en el inventario o no
**/
function __ComboContables(){
    self.contables =[]
    self.contables.push({
        codigo: $.i18n.prop("boolean.si"),
        descripcion:$.i18n.prop("boolean.si")
     });
    self.contables.push({
        codigo: $.i18n.prop("boolean.no"),
        descripcion: $.i18n.prop("boolean.no")
     });
     self.update();
}

/**
* Combo para verificar si es contabilizado en el inventario o no
**/
function __Impuestos(){
    self.impuestos =[]
    self.impuestos.push({
        codigo: '01',
        descripcion:$.i18n.prop("tipo.impuesto.ventas")
     });
    self.impuestos.push({
        codigo: '02',
        descripcion:$.i18n.prop("tipo.impuesto.consumo")
     });
    self.impuestos.push({
        codigo: '03',
        descripcion:$.i18n.prop("tipo.impuesto.combustible")
     });
    self.impuestos.push({
        codigo: '04',
        descripcion:$.i18n.prop("tipo.impuesto.bebidas.alcoholicas")
     });
    self.impuestos.push({
        codigo: '05',
        descripcion:$.i18n.prop("tipo.impuesto.bebidas.envasadas")
     });
    self.impuestos.push({
        codigo: '06',
        descripcion:$.i18n.prop("tipo.impuesto.tabaco")
     });
    self.impuestos.push({
        codigo: '07',
        descripcion:$.i18n.prop("tipo.impuesto.servicio")
     });
    self.impuestos.push({
        codigo: '12',
        descripcion:$.i18n.prop("tipo.impuesto.cemento")
     });
    self.impuestos.push({
        codigo: '98',
        descripcion:$.i18n.prop("tipo.impuesto.otros")
     });
   
     self.update();
}
/**
*  Regresar al listado
**/
__regresarAlListado(){
    swal({
        title: "", 
        text: $.i18n.prop("mensaje.alert.regresar.listado"), 
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: '#00539B',
        cancelButtonColor: '#d33',
        confirmButtonText:$.i18n.prop("confirmacion.si"),
        cancelButtonText: $.i18n.prop("confirmacion.no"),
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger'
        }).then(function (isConfirm) {
            if(isConfirm){
                self.mostrarTituloArticulo = true
                self.mostrarListado     = true;
                self.botonAgregar       = false;
                self.botonModificar     = false;
                self.mostrarFormulario  = false 
                self.mostrarListadoInventario = false
                self.mostrarTituloInventario     = false
                self.mostrarFormularioInventario = false
                self.mostrarFormularioEntrada    = false
                self.mostrarFormularioSalida     = false

                self.update()
                __listado();

            }
    });    
}
/**
* Mostrar formulario de mantenimiento Agregar
**/
function __MantenimientoAgregar(){
      //Inicializar el Formulario
    $('.dataTables_wrapper').on('click','.btn-agregar',function(e){
        _inicializarArticulo()             // modelo o domain   
        self.mostrarListado   = false;
        self.mostrarFormulario  = true 
        self.botonModificar   = false;
        self.botonAgregar     = true;
        self.mostrarListadoInventario = false
        self.mostrarTituloInventario     = false
        self.mostrarFormularioEntrada    = false
        self.mostrarFormularioSalida     = false
        self.update();
        $(".errorServerSideJgrid").remove();
        $('#codigo').val(null)
        $('#descripcion').val(null)
        $('#serie').val(null)
        $('#costo').val(null)
        $('#impuesto').val(null)
        $('#precioPublico').val(null)
        $('#gananciaPrecioPublico').val(null)
        $('#precioMayorista').val(null)
        $('#gananciaPrecioMayorista').val(null)
        $('#precioEspecial').val(null)
        $('#gananciaPrecioEspecial').val(null)
        $("#tipoImpuesto").val(null)
       __Eventos()
        $("#formulario").validate(reglasDeValidacion());
    })
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
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.articulo  = data
        self.update()
    
        __consultar()
	});
}

/**
 * Funcion mostrar los inventarios por almacen
 */
function __articuloXInventarios_Listar(){
	$('#tableListar').on('click','.btnInventario',function(e){
        $(".errorServerSideJgrid").remove();
        includeActionsInventario('.dataTables_wrapper','.dataTables_length')
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.articulo  = data
        self.mostrarListado            = false
        self.mostrarTituloArticulo     = false
        self.botonModificar            = false
        self.botonAgregar              = false
        self.mostrarFormularioEntrada    = false
        self.mostrarFormularioSalida     = false
        // variables para modulo de inventario 
        self.mostrarListadoInventario = true
        self.mostrarTituloInventario  = true
        self.mostrarFormularioInventario = false
        self.update()

         agregarInputsCombosInventario();
        __listadoInventario()
	});
}
/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(){

    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarArticuloAjax.do",
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
                        self.mostrarListado   = false;
                        self.mostrarFormulario  = true 
                        self.botonModificar   = true;
                        self.mostrarFormularioEntrada    = false
                        self.botonAgregar     = false;                        
                        self.articulo  =  modeloTabla
                        self.update()
                      //  __listadoEmpresasActivas()
                        __listadoCategoriasActivas(self.articulo.empresa.id)
                        __listadoMarcasActivas(self.articulo.empresa.id)
                        __ComboContables()
                        __ComboEstados()
                        __Impuestos() 
                        $('.selectTipoImpuesto').val(self.articulo.tipoImpuesto);  
                        $('.selecTipoUnidad').val(self.articulo.unidadMedida) 
                        $('.selectMarca').val(self.articulo.marca.id)
                        $('.selectCategoria').val(self.articulo.categoria.id)
                        $('.selectEmpresa').val(self.articulo.empresa.id)
                        $("#formulario").validate(reglasDeValidacion());
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
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formulario").serialize();
        swal({
           title: '',
           text: $.i18n.prop("articulo.mensaje.alert.agregar"),
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
                    url : 'AgregarArticuloAjax.do',
                    success : function(data) {
                        console.log(data);
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
	                        $("#formulario").validate(reglasDeValidacion());
                            $(".errorServerSideJgrid").remove();
                            $('#codigo').val(null)
                            $('#descripcion').val(null)
                            $('#serie').val(null)
                            $('#costo').val(null)
                            $('#impuesto').val(null)
                            $('#precioPublico').val(null)
                            $('#gananciaPrecioPublico').val(null)
                            $('#precioMayorista').val(null)
                            $('#gananciaPrecioMayorista').val(null)
                            $('#precioEspecial').val(null)
                            $('#gananciaPrecioEspecial').val(null)
                              __Eventos()
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
** Modificar la Empresa
**/
__Modificar(){
    self.error = false;
    self.exito = false;
    self.update();
    __modificarRegistro("#formulario",$.i18n.prop("articulo.mensaje.alert.modificar"),'ModificarArticuloAjax.do','ListarArticuloAjax.do','#tableListar')
}
/**
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarArticuloAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                __InformacionDataTable();
                loadListar(".tableListar",idioma_espanol,self.informacion_tabla,result.aaData)
                includeActionsArticulo('.dataTables_wrapper','.dataTables_length')
                agregarInputsCombos();
                __MantenimientoAgregar()
                    //Actimpuestor filtros
                ActivarEventoFiltro(".tableListar")
                __modificarRegistro_Listar()
                __Eventos()
                __articuloXInventarios_Listar()
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
                               {'data' :'empresa.nombre'   ,"name":"empresa.nombre"  ,"title" : $.i18n.prop("articulo.empresa")        ,"autoWidth" :true },
                               {'data' :'codigo'                  ,"name":"codigo"                 ,"title" : $.i18n.prop("articulo.codigo")           ,"autoWidth" :true },
                               {'data' :'descripcion'             ,"name":"descripcion"            ,"title" : $.i18n.prop("articulo.descripcion")      ,"autoWidth" :true },
                               {'data' :'costo'                   ,"name":"costo"                  ,"title" : $.i18n.prop("articulo.costo")            ,"autoWidth" :true },
                               {'data' :'impuesto'                     ,"name":"impuesto"                    ,"title" : $.i18n.prop("articulo.impuesto")              ,"autoWidth" :true },
                               {'data' :'precioPublico'           ,"name":"precioPublico"          ,"title" : $.i18n.prop("articulo.precioPublico")    ,"autoWidth" :true },
                               {'data' :'precioMayorista'         ,"name":"precioMayorista"        ,"title" : $.i18n.prop("articulo.precioMayorista")  ,"autoWidth" :true },
                               {'data' :'precioEspecial'          ,"name":"precioEspecial"         ,"title" : $.i18n.prop("articulo.precioEspecial")   ,"autoWidth" :true },
                               {'data' :'contable'                ,"name":"contable"               ,"title" : $.i18n.prop("articulo.contable")         ,"autoWidth" :false },
                               {'data' : 'estado'        ,"name":"estado"          ,"title" : $.i18n.prop("articulo.estado")      ,"autoWidth" :false},
                               {'data' : 'id'            ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
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
      return moment(dateTime).format('DD/MM/YYYY h:mm:ss');
}
/**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
    let menu = ' <div class="dropdown"> ' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    
    menu += '<li><a href="#"  title="Modificar" class="  btnModificar" >Modificar</a></li>'
    if(row.contable =='Si'){
        menu += '<li><a href="#"  title="Ingresar Inventario"  class="  btnInventario" >Inventario</a></li>'
    }
     menu += "</ul></div>"  
     return menu;          
}
/**
* Inicializar el articulo
**/
function _inicializarArticulo(){
    self.articulo = {
		id:null,
        codigo:"",
		descripcion:"",
        serie:"",
		unidadMedida:"",
		costo:"",
		impuesto:0,
        minimo:0,
        maximo:0,
		precioPublico:null,
		gananciaPrecioPublico:null,
		precioMayorista:null,
		gananciaPrecioMayorista:null,
		precioEspecial:null,
		gananciaPrecioEspecial:null,
		estado:"",
		marca:{
            id:null
        },
		categoria:{
            id:null
        },
		empresa:{
            id:null
        }
    }    
    self.update()
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 11    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}
</script>
</articulo-crud>