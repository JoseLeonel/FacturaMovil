<articulo-nuevo>
<div show ={mostrarFormulario}>
<div class="tituloBotones">
    <div class="articulo-title"><i class="fa fa-edit"></i>&nbsp {articulo.id > 0 ? $.i18n.prop("titulo.modificar.articulo")   :$.i18n.prop("titulo.agregar.articulo")}     </div>
    <div class="botones">
            <button  title = "Limpiar Pantalla" onclick={__LimpiarPantalla}   class="btn-salida btn-edit pull-right" > &nbsp Limpiar</button>
            <button  title = "Salida del inventario" onclick={__AplicarSalida} show={articulo.cantidad > 0}  class="btn-salida btn-edit pull-right" > &nbsp Salida</button>
            <button  title = "Entrada al inventario" onclick={__AplicarEntrada} show={botonEntrada}  class="btn-entrada btn-edit pull-right" > &nbsp Entrada</button>
            <button  title = "Modificar el Articulo" onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
            <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>

    </div>
</div>            

 <div class="row scrollerT"  show={mostrarFormularioPrincipal}>
    <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">
        <div id="divFormulario" >
            <!--Form-->
            <form class="form-horizontal formulario" name= "formulario" id="formulario">
                <input type="hidden" name="id" id="id" value="{articulo.id}">
                <input type="hidden" name="idPaquete" id="idPaquete" value="{articulo.cantidadPaquete}">
                <input type="hidden" name="esPaquete" id="esPaquete" value="0">
                <input type="hidden" name="codigoSecundario" id="codigoSecundario" value="{articulo.codigoSecundario}">
                
                
                <input type="hidden" name="tipoFacturar" id="tipoFacturar" value="{articulo.tipoFacturar}">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default" id="cuentas">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" >
                            <div class="panel-heading" style="background: #3c8dbc; color: white;">
                                <h4 class="panel-title"><span class="fa fa-bank col-md-offset-5"></span> Maestro Articulo</h4>
                            </div>
                        </a>             
                        <div id="collapse1" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                  <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.codigo")}  <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control codigo campoNumerico" id="codigo" name="codigo" value="{articulo.codigo}" onkeypress = {__ConsultarCodigo} autocomplete="off" autofocus="autofocus">
                                    </div>
                                    <div class= "col-md-4 col-sx-8 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.descripcion")}  <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control descripcion campoNumerico" id="descripcion" name="descripcion" value="{articulo.descripcion}"  >
                                    </div>
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetraTotales" >Cabys <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control campoNumerico codigoCabys" id="codigoCabys" name="codigoCabys" value="{articulo.codigoCabys}"  onclick={__ConsultaHaciendaCabys}  autocomplete="off">
                                    </div>
                                    
                                  
                                  
                                </div> 
                                <div class="row">   
                                  
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.categoria")}  <span class="requeridoDato">*</span></label>
                                         <select  class=" selecCategoria has-success" name="categoria" id="categoria" value="{articulo.categoria.id}"></select>
                                    </div>   
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra">{$.i18n.prop("articulo.tipoCodigo")}</label>
                                        <select  class="form-control selectTipoCodigo campoNumerico" id="tipoCodigo" name="tipoCodigo"  >
                                            <option  each={tipoCodigos}  value="{codigo}" selected="{articulo.tipoCodigo ==codigo?true:false}"  >{descripcion}</option>
                                        </select>
                                    </div>


                                </div>    
                                <div class="row">   
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.costo")} </label>
                                        <input type="number" step="any" class="form-control costo campoNumerico" id="costo" name="costo" value="{articulo.costo}"  onkeyup ={__ActualizarPreciosCosto} autocomplete="off">
                                    </div>
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.gananciaPrecioPublico")} % </label>
                                        <input type="number" step="any" class="form-control gananciaPrecioPublico campoNumerico" id="gananciaPrecioPublico" name="gananciaPrecioPublico" value="{articulo.gananciaPrecioPublico}"  onkeyup ={__CalculoGananciaPublico}>
                                    </div>

                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.precioPublico")}  <span class="requeridoDato">*</span></label>
                                        <input type="number" step="any" class="form-control precioPublico campoNumerico" id="precioPublico" name="precioPublico" onkeyup ={__CalculoPrecioPublico} value="{articulo.precioPublico}" autocomplete="off" >
                                    </div>

                                </div>    
                                <div class="row">
                                     <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.precioMayorista")}  </label>
                                        <input type="number" step="any" class="form-control precioMayorista campoNumerico" id="precioMayorista" name="precioMayorista" value="{articulo.precioMayorista}" onkeyup={__CalculoPrecioMayorista}  autocomplete="off">
                                    </div>  

                                     <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.gananciaPrecioMayorista")} % </label>
                                        <input type="number" step="any" class="form-control gananciaPrecioMayorista campoNumerico" id="gananciaPrecioMayorista" name="gananciaPrecioMayorista" value="{articulo.gananciaPrecioMayorista}"  onkeyup ={__CalculoGananciaMayorista}>
                                    </div>

                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.precioEspecial")}  </label>
                                        <input type="number" step="any" class="form-control precioEspecial campoNumerico" id="precioEspecial" name="precioEspecial" value="{articulo.precioEspecial}"  onkeyup={__CalculoPrecioEspecial} autocomplete="off"> 
                                    </div>                        

                                </div>
                                <div class="row">
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.gananciaPrecioEspecial")} % </label>
                                        <input type="number" sp="any" class="form-control gananciaPrecioEspecial campoNumerico" id="gananciaPrecioEspecial" name="gananciaPrecioEspecial" value="{articulo.gananciaPrecioEspecial}"  onkeyup ={__CalculoGananciaEspecial}>
                                    </div>
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                        <label  class="tamanoLetra">Precio Sugerido </label>
                                        <input type="number" step="any" class="form-control campoNumerico precioSugerido" id="precioSugerido" name="precioSugerido" value="{articulo.precioSugerido}" autocomplete="off" >
                                    </div>

                                    <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">

                                        <label class="tamanoLetra">{$.i18n.prop("articulo.tipoImpuesto")} </label>
                                        <select onchange= {__asignarImpuesto} class="form-control selectTipoImpuesto campoNumerico" id="tipoImpuesto" name="tipoImpuesto"  >
                                            <option  each={impuestos}  value="{codigo}" selected="{articulo.tipoImpuesto ==codigo?true:false}"  >{descripcion}</option>
                                        </select>
                                    </div>

                                    <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra">{$.i18n.prop("articulo.codigoTarifa")}</label>
                                        <select  onchange= {__AsignarTarifa} class="form-control selectCodigoTarifa campoNumerico" id="codigoTarifa" name="codigoTarifa"  >
                                            <option  each={tarifas.aaData}  value="{tarifaIVAI.codigoTarifa}" selected="{articulo.codigoTarifa ==tarifaIVAI.codigoTarifa && articulo.tipoImpuesto ==tipoImpuesto ?true:false}"  >{tarifaIVAI.descripcion}</option>
                                        </select>
                                    </div>

                                </div>    
                                <div class="row">
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra"  >{$.i18n.prop("articulo.impuesto")}  </label>
                                        <input type="number" step="any" class="form-control impuesto campoNumerico" id="impuesto" name="impuesto" value="{articulo.impuesto}"  onkeyup ={__ActualizarPreciosImpuestos}>
                                    </div>
                                      
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.marca")}  <span class="requeridoDato">*</span></label>
                                        <select  class=" selecMarca has-success" name="marca" id="marca" ></select>
                                    </div>
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.unidadMedida")}  <span class="requeridoDato">*</span></label>
                                        <select  class=" selecUnidadMedida has-success" name="unidadMedida" id="unidadMedida"></select>
                                    </div>
                                    
                                </div>    
                            </div>

                        </div>
                    </div>
                </div>
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default" id="cuentas">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" >
                            <div class="panel-heading" style="background: #3c8dbc; color: white;">
                                <h4 class="panel-title"><span class="fa fa-bank col-md-offset-5"></span> Otros</h4>
                            </div>
                        </a>
                        <div id="collapse2" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra">{$.i18n.prop("articulo.contable")}</label>
                                        <select  class="form-control campoNumerico" id="contable" name="contable" >
                                            <option  each={contables}  value="{codigo}" selected="{articulo.contable ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra">{$.i18n.prop("articulo.comanda")}</label>
                                        <select  class="form-control campoNumerico" id="comanda" name="comanda"  >
                                            <option each={comanda}  value="{codigo}" selected="{articulo.comanda ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>                          
                                    <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.prioridad")} </label>
                                        <input type="number" step="any" class="form-control prioridad campoNumerico" id="prioridad" name="prioridad" value="{articulo.prioridad}"  >
                                    </div>
                                    <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.pesoTransporte")} </label>
                                        <input type="number" step="any" class="form-control pesoTransporte campoNumerico" id="pesoTransporte" name="pesoTransporte" value="{articulo.pesoTransporte}"  >
                                    </div>

                                </div>
                                <div class="row">
                                    <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("inventario.minimo")} </label>
                                        <input type="number" step="any" class="form-control minimo campoNumerico" id="minimo" name="minimo" value="{articulo.minimo}"  >
                                    </div>
                                    <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("inventario.maximo")} </label>
                                        <input type="number" step="any" class="form-control maximo campoNumerico" id="maximo" name="maximo" value="{articulo.maximo}"  >
                                    </div>
                                    <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("inventario.cantidad")} </label>
                                        <input type="number" step="any" class="form-control cantidad campoNumerico" id="cantidad" name="cantidad" value="{articulo.cantidad}" readonly = "{articulo.id>0?true:false}" >
                                    </div>                        
                                    <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.consecutivoCompra")} </label>
                                        <input type="text" step="any" class="form-control campoNumerico"  value="{articulo.consecutivoCompra}"  readonly>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra">Base Imponible</label>
                                        <select  class="form-control campoNumerico" id="baseImponible" name="baseImponible" >
                                            <option  each={baseImponibles}  value="{codigo}" selected="{articulo.baseImponible ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>    
                                    <div class="col-md-3 col-sx-3 col-sm-3 col-lg-3has-success">
                                        <label class="tamanoLetra"  >{$.i18n.prop("articulo.fechaUltimaCompra")} </label>
                                        <input type="text" step="any" class="form-control campoNumerico"  value="{articulo.fechaUltimaCompra}"  readonly>
                                    </div>
                                    <div class="col-md-3 col-sx-3 col-sm-3 col-lg-3has-success">
                                        <label class="tamanoLetra">{$.i18n.prop("articulo.estado")}</label>
                                        <select  class="form-control campoNumerico" id="estado" name="estado"  >
                                            <option  each={estados}  value="{codigo}" selected="{articulo.estado ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>     
                                  <!--     <div class="col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra">Facturar</label>
                                        <select  class="form-control campoNumerico" id="tipoFacturar" name="tipoFacturar"  >
                                             <option  each={tipoFacturar}  value="{codigo}" selected="{articulo.tipoFacturar ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>      -->   

                                </div>
                            
                            </div>
                        </div>
                    </div>
                </div>       
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default" id="cuentas">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse3" >
                            <div class="panel-heading" style="background: #3c8dbc; color: white;">
                                <h4 class="panel-title"><span class="fa fa-bank col-md-offset-5"></span> Tarifas Producto Agropecuario y Pesca</h4>
                            </div>
                        </a>
                        <div id="collapse3" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">

                                        <label class="tamanoLetra">Tipo Impuesto MAG </label>
                                        <select onchange= {__asignarImpuestoMag} class="form-control selectTipoImpuestoMag campoNumerico" id="tipoImpuestoMag" name="tipoImpuestoMag"  >
                                            <option  each={impuestosMag}  value="{codigo}" selected="{articulo.tipoImpuestoMag ==codigo?true:false}"  >{descripcion}</option>
                                        </select>
                                    </div>

                                    <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra">Tarifa MAG</label>
                                        <select  onchange= {__AsignarTarifaMag} class="form-control selectCodigoTarifaMag campoNumerico" id="codigoTarifaMag" name="codigoTarifaMag"  >
                                            <option  each={tarifasMag.aaData}  value="{tarifaIVAI.codigoTarifa}" selected="{articulo.codigoTarifaMag ==tarifaIVAI.codigoTarifa && articulo.tipoImpuestoMag ==tipoImpuesto ?true:false}"  >{tarifaIVAI.descripcion}</option>
                                        </select>
                                    </div>
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra"  >Impuesto MAG  </label>
                                        <input type="number" step="any" class="form-control impuestoMag campoNumerico" id="impuestoMag" name="impuestoMag" value="{articulo.impuestoMag}"  readonly>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>                 

            </form>
        </div>
    </div>
</div>                                       

</div>

<!-- Formulario de crear una Entrada-->
<div>
    <div class="row center " show={mostrarFormularioEntrada}>
        <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-search"></i>&nbsp{$.i18n.prop("kardex.agregar.entrada")}  </h1>
                </div>
                <div class="box-body">
                    <form id = "formularioEntrada" name ="formularioEntrada " class="advanced-search-form">
                        <input type="hidden" name="IdArticulo" id="IdArticulo" value="{articulo.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")}</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="labelstyle" >{$.i18n.prop("articulo.codigo")}</label>
                                <input type="text" class="form-control inputStyle"   value="{articulo.codigo}"   readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="labelstyle" >{$.i18n.prop("articulo.descripcion")}</label>
                                <input type="textArea" class="form-control inputStyle" value="{articulo.descripcion}" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="labelstyle">{$.i18n.prop("kardex.motivos")} <span class="requeridoDato">*</span></label>
                                <select  class="form-control selectEntrada inputStyle" id="motivo" name="motivo" data-live-search="true">
                                    <option data-tokens="{descripcion}" each={motivoEntradas.data}  value="{descripcion}"  >{descripcion}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="labelstyle" >{$.i18n.prop("kardex.cantidadActual")}</label>
                                <input type="number" step="any" class="form-control inputStyle"  value="{articulo.cantidad}"  readonly >
                            </div>
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="labelstyle" >{$.i18n.prop("kardex.cantidadNueva")}<span class="requeridoDato">*</span> </label>
                                <input onkeyup= {MostrarBotonAgregarEntrada} type="number" step="any"  class=" inputStyle form-control cantidadNueva_entrada" id="cantidadNueva" name="cantidadNueva"   >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="labelstyle" > {$.i18n.prop("kardex.observacion")}<span class="requeridoDato">*</span></label>
                                <textarea class="form-control observacion_entrada inputStyle" rows="5" id="observacion" name="observacion"></textarea>
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__VolverPantallaPrincipal}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
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
<div>
    <div class="row center " show={mostrarFormularioSalida}>
        <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
            <div class="box box-solid box-danger">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-search"></i>&nbsp {$.i18n.prop("kardex.agregar.salida")}   </h1>
                </div>
                <div class="box-body">
                    <form id = "formularioSalida" name ="formularioSalida " class="advanced-search-form">
                        <input type="hidden" name="IdArticulo" id="IdArticulo" value="{articulo.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")}</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="labelstyle" >{$.i18n.prop("articulo.codigo")}  </label>
                                <input type="text" class="form-control"  value="{articulo.codigo}"  readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="labelstyle" >{$.i18n.prop("articulo.descripcion")}</label>
                                <input type="text" class="form-control " value="{articulo.descripcion}"  readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="labelstyle">{$.i18n.prop("kardex.motivos")} <span class="requeridoDato">*</span></label>
                                <select  class="form-control selectMotivoSalida" id="motivo" name="motivo" data-live-search="true">
                                    <option data-tokens="{descripcion}"  each={motivoSalidas.data}  value="{descripcion}"  >{descripcion}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="labelstyle" >{$.i18n.prop("kardex.cantidadActual")} </label>
                                <input type="number" step="any" class="form-control inputStyle" id="cantidadActual" name="cantidadActual" value="{articulo.cantidad}" disabled readonly >
                            </div>
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="labelstyle" >{$.i18n.prop("kardex.cantidadDismunuir")}<span class="requeridoDato">*</span> </label>
                                <input onkeyup= {MostrarBotonAgregarSalida} type="number" step="any"  class="inputStyle form-control cantidadNueva_salida cantidadNueva" id="cantidadNueva" name="cantidadNueva"   >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="labelstyle" >{$.i18n.prop("kardex.observacion")} <span class="requeridoDato">*</span></label>
                                <textarea class="form-control observacion_salida inputStyle" rows="5" id="observacion" name="observacion"></textarea>
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__VolverPantallaPrincipal}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
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
<!--Modal mostrar Articulos de la empresa -->
<div id='modalHaciendaCabys' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
    <div class="modal-dialog modal-lg" >
        <div class="modal-content" style="width:100%;">
            <div class="modal-header with-border encabezado-pantalla " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> Lista de Cabys desde Hacienda y el Banco Central de Costa Rica(cabys@hacienda.go.cr) </h4>
            </div>
            <div class="modal-body aplicarScroll">
                    <div class= "container">  
                        <form id="formularioParametros" name ="formularioParametros" >
                            <div class="row">
                                <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                    <label  >Digite la descripcion del producto a buscar</label>
                                    <input type="text" class="form-control descArticulo "   id="descArticulo" name="descArticulo" onkeypress={__ConsultaCodigoCabysEnter} autofocus="autofocus" autocomplete="off">
                                </div>
                                <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                    <label  >Digite el codigo 碼</label>
                                    <input type="text" class="form-control codigoCabys  codigoCabysMod"   id="codigoCabys" name="codigoCabys" onkeypress={__ConsultaCodigoCabysEnter} autofocus="autofocus" autocomplete="off">
                                </div>
                                <div class= "col-md-2 col-sx-12 col-sm-2 col-lg-2">
                                    <label>cantidad</label>
                                    <select  class="form-control cantidadCabys" id="cantidadCabys" name="cantidadCabys" >
                                        <option  each={cantidades}  value="{codigo}" selected="{cabys.estado ==codigo?true:false}" >{descripcion}</option>
                                    </select>
                                </div>
                                <div class= "col-md-2 col-sx-12 col-sm-2 col-lg-2">
                                    <button  onclick={__ConsultaCodigoCabys} id = "filtroBotonCabys" type="button" class="btn-green pull-right"  >Consultar</button>
                                    
                                </div>                    
                            </div> 
                        </form>    
                        <br>    
                         <div class = "tablaDiseno">
                           
                                <table id="tableListarHaciendaCabys" class="table table-hover  tableListarHaciendaCabys "  >
                                    <thead>
                                        <th class="table-header colum1"  >{$.i18n.prop("listado.acciones")}</th>
                                        <th class="table-header colum2"  >{$.i18n.prop("articulo.codigo")}</th>
                                        <th class="table-header colum2"  >{$.i18n.prop("articulo.descripcion")}</th>
                                        <th class="table-header colum2"  >Impuesto</th>
                                        
                                    </thead>
                                    <tfoot style="display: table-header-group;">
                                        <tr class="headt">
                                            <th class="colum1"></th>
                                            <th class="colum2">{$.i18n.prop("articulo.codigo")}</th>
                                            <th class="colum2">{$.i18n.prop("articulo.descripcion")}</th>
                                           
                                            <th class="colum2">Impuesto</th>
                                            
                                        </tr>
                                    </tfoot>
                        </table>
                    </div>    
                    </div>
            </div>        
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left" data-dismiss="modal" >{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>



<style type ="text/css">

.aplicarScroll{
   overflow: scroll;
}

table {
    table-layout: unset!important;
}
.tablaDiseno{
    position: relative;
}
.colum1{
    width: 54px!important;
    
    }
    .colum2{
    width: 140px!important;
    
    }

     .container {
    display: flex!important;
    width: 100%!important;
    padding: 10px 0px 20px;
    min-height: 350px;
    flex-direction: column!important;
    flex-wrap: nowrap!important;
}
.contenedor{
    display:flex;
}
    #filtroBotonCabys{
        margin-top: 25px;
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
        
        
        
        .formatoTitulo{
                color: white!important;
        }
        .btn-mostrar {
            background-color: #4cae4c;
            color: #FFF;
            border-radius: 5px;
            padding-bottom: 5px;
            padding-top: 5px;
            padding-left: 10px;
            padding-right: 10px;
            font-size: 12px;
            font-weight: bold;
            /* margin-right: 15px; */
            border: none;
            float: right;
            cursor: pointer;
        }
.form-input {
    display: block;
   
    height: 34px;
    padding: 6px 12px;
    font-size: 12px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
    -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
.selectLeo{
    width: 60px;
}
.textAreaLeo{
    margin: 2px 0px !important;
    width: 189px!important;
    height: 68px !important;
    font-size: 11px!important;
    font-weight: 900!important;
}
	table td {
		text-align: left !important;
		font-size: 12px !important;
	}
	
	table th {
		text-align: !important;
		font-size: 12px !important;
	}
	
	th, td {
		white-space: break-spaces !important;;
	}
    .formCabys{
        width: 100%;
    height: 30px;
    font-size: 16px;
    font-weight: 800;
    }

   
    </style>
<script>
    var self = this;
    self.parametros   = opts.parametros;  
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del Listado de la Tabla 
    self.categorias                = {aaData:[]}
    self.marcas                    = {aaData:[]}
    self.tipoUnidades              = {aaData:[]}
    self.motivoEntradas              = {data:[]}
    self.motivoSalidas              = {data:[]}
    self.impuestos =[]
    self.impuestosMag =[]
    self.tipoCodigos =[]
    self.tipoFacturar =[]
    self.contables                 = []
    self.estados                   = []
    self.baseImponibles            = []
    self.comanda                   = []
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarTituloArticulo     = true
    self.articulo = {
		id:null,
        codigo:"",
		descripcion:"",
        serie:"",
		unidadMedida:"",
		costo:0,
		impuesto1:0,
        impuesto:0,
        codigoTarifa:"",
        codigoTarifa1:"",
        minimo:0,
        maximo:0,
		precioPublico:0,
		gananciaPrecioPublico:0,
		precioMayorista:0,
		gananciaPrecioMayorista:0,
		precioEspecial:0,
		gananciaPrecioEspecial:0,
		estado:"",
		marca:{
            id:null
        },
		categoria:{
            id:null
        },
		empresa:{
            id:null
        },
        comanda:0,
        prioridad:1
    }    
   
   self.tarifas    = {aaData:[]}
   self.tarifasMag    = {aaData:[]}
   
    // variables para modulo de inventario 
    self.mostrarFormularioEntrada    = false
    self.mostrarFormularioSalida     = false
    self.mostrarBotonAgregarEntrada  = false
    self.mostrarBotonAgregarSalida   = false 
    self.botonSalida = false
    self.botonEntrada = false
    self.mostrarFormularioPrincipal = true
   self.listaCabys  = {aaData:[]}
    self.origen = ""
    self.cabys = {
        id:null,
        descripcion:"",
        estado:"",
        codigo:""
    }

    cabysHacienda = {
        codigo:"",
        descripcion:"",
        categorias:[],
        impuesto:0,
        uri:"",
    }  

self.on('mount',function(){
    __ComboCantidades()
    __Eventos()
     inicializarCombos()
    __listadoTipoUnidadesCombo(function(data){
        self.tipoUnidades  = data;
        self.update()
        _evento_refrescar_unidades_medida(self.tipoUnidades); 
    });
     __listadoMarcaCombo(function (data){
         self.marcas = data;
         self.update()
         _evento_refrescar_marca(self.marcas)
     });
    __listadoCategoriasCombo(function (data){
        self.categorias = data
        self.update()
        _evento_refrescar_categorias(self.categorias);
    });
    
    self.estados   = __ComboEstados()
    self.comanda   = __ComboComanda()
    self.contables = __ComboContables()
    self.baseImponibles =__ComboBaseImponibles()
    
    


    self.impuestos = __ComboImpuestos()
    self.impuestosMag = __ComboImpuestosMaG()
    self.tipoCodigos =__CombotipoCodigo()
    self.tipoFacturar =___ComboTipoFacturarArticulo();


   _ListaMotivoEntradasActivas()
   _ListaMotivoSalidasActivas() 
    self.impuestos = __ComboImpuestos()
    self.impuestosMag = __ComboImpuestosMaG()
    self.tipoCodigos =__CombotipoCodigo()
    self.update()
    __Consulta()
    
    
    //configuracionDatePicker()
    $("#collapse1").attr('class',"in");
    $("#collapse2").attr('class',"in");
    inicializarCursorCodigo()

    
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
})

__ConsultaCodigoCabysEnter(e){
    if (e.keyCode != 13) {
        return;
    }
    __ListaDeHaciendaCabys()
}
/*******************************Lista de cabys de hacienda ***************/

__ConsultaHaciendaCabys(e){
    ListarCodigosCabysModal()
    
    
}




function ListarCodigosCabysModal(){
    $("#formularioParametros").validate(reglasDeValidacionParametroCabys());
    $(".tableListarHaciendaCabys").dataTable().fnClearTable();
    $(".tableListarHaciendaCabys").DataTable().destroy();
    $('#modalHaciendaCabys').modal('show')
    $('#modalHaciendaCabys').on('shown.bs.modal', function () {
        $('.codigoCabysMod').select()
        $('.descArticulo').focus()
        $('#descArticulo').val('')
        $('.codigoCabysMod').val('')
    })
 }

 __ConsultaCodigoCabys(e){
    __ListaDeHaciendaCabys()
}
/**
*  Crear el combo de estados
**/
function __ComboCantidades(){
    self.cantidades =[]
    self.update()
    self.cantidades.push({
        codigo: null,
        descripcion: "Todos"
     });
    self.cantidades.push({
        codigo: 5,
        descripcion: 5
     });
    self.cantidades.push({
        codigo: 10,
        descripcion: 10
     });
    self.cantidades.push({
        codigo: 20,
        descripcion: 20
     });
     self.update();
}


function __ListaDeHaciendaCabys(){
  
    try {
    var  encontro = false
    $(".tableListarHaciendaCabys").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
   if ($("#formularioParametros").valid()) {

       var descripcion  = $('#descArticulo').val()
       var  codigo = $('.codigoCabysMod').val() 
       var cantidadTemp = $('.cantidadCabys').val() == 'Todos'?999:$('.cantidadCabys').val()
       if(descripcion != null && descripcion.length > 0){
            getCabysByDescripcion(descripcion,__valorNumerico(cantidadTemp))
            .then(res => {
                unBlockUIStop();
                 __InformacionDataTable_cabys()
                console.log("cabys");
                  __cargarTablaCompras(res)
            })
            .catch(err=>{
                unBlockUIStop();
                console.log(err)
            })
       }else{
            getCabysByCodigo(codigo,__valorNumerico(cantidadTemp))
            .then(res => {
                unBlockUIStop();
                   __InformacionDataTable_cabys()
                console.log("cabys");
                  __cargarTablaCompras(res)
            })
            .catch(err=>{
                unBlockUIStop();
                console.log(err)
            })
           
       }
 
  }
  } catch (error) {
        console.log(error);
    }
}



function __cargarTablaCompras(data) {
    if(data != null && data.cabys.length == 0){
       mensajeAlertErrorOConfirmacion("error","No hay informacion con esos datos.")
       return
    } 
     self.listaCabys.aaData =data.cabys    
    self.update()
                   
    __InicializarTabla('.tableListarHaciendaCabys')  
    $("#tableListarHaciendaCabys").dataTable().fnClearTable();
    __InformacionDataTable_cabys();
    $('#tableListarHaciendaCabys').DataTable().destroy();
    $("#tableListarHaciendaCabys").DataTable({
        destroy: true,
        "aLengthMenu": [
            [5, 10, 15, 25, -1],
            [5, 10, 15, 25, "All"]
        ],
        "language": idioma_espanol,
        "sDom": 'lfrtip',
        "order": [],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        "columns": self.informacion_tabla_cabys ,
    })
    $("#tableListarHaciendaCabys").dataTable().fnAddData(self.listaCabys.aaData);
    agregarInputsCombosCabys()
    ActivarEventoFiltro(".tableListarHaciendaCabys")
    __SeleccionarCabys()
    

}


/**
*Formato del listado de los cambios
**/
function __InformacionDataTable_cabys(){
    self.informacion_tabla_cabys = [ 
                               {'data' : 'id',"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : false,
                                "render":function(id,type, row){
                                      return __OpcionesCabys(id,type,row);
                                 }
	      		            },
                               {'data' : 'codigo',"name":"codigo" ,"title" : "Codigo","autoWidth" :false},
                               {'data' :'descripcion',"name":"descripcion" ,"title" : "Descripcion","autoWidth" :false ,
                                "render":function(descripcion,type, row){
                                      return __DescripcionCabys(descripcion,type,row);
                                 }

                               },
                               {'data' :'impuesto',"name":"impuesto" ,"title" : "Impuesto","autoWidth" :false }, 
];
    self.update();
/*                                   {'data' : 'categorias',"name":"categorias" ,"title" : "Origen del Articulo","bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(categorias,type, row){
                                      return __CategoriasCabys(categorias,type,row);
                                 }},
*/
   
}
function __CategoriasCabys(categorias,type,row){
    var categoriasString =  ""
    $.each(categorias, function( index, modeloTabla ) {
        categoriasString = categoriasString + modeloTabla ;
        categoriasString =  categoriasString +" \n"
    })
    return "<div class= 'categoriasdesc'>"+"<span>"+"<pre>"+categoriasString +"</pre>"+"</span>"+"</div>";

}
function __DescripcionCabys(descripcion,type,row){
   
    return "<div class= 'categoriasdesc'>"+"<textarea class='textAreaLeo' rows='2' readonly>"+descripcion +"</textarea>"+"</div>";

}

/**
* Opciones listado de los cabys
*/
function __OpcionesCabys(){
  var agregar  = '<a href="#"  class="btn btnAgregar btn-success selectLeo" title="Seleccionar" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar ;
}
function __SeleccionarCabys() {
     $('#tableListarHaciendaCabys').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListarHaciendaCabys').DataTable();
		if(table.row(this).child.isShown()){

	       var data = table.row(this).data();
	    }else{
	       var data = table.row($(this).parents("tr")).data();
	     }
        if(data !=null){
            moverDatos(data,function(resultado){
                console.log(resultado)
                $('#modalHaciendaCabys').modal('hide')
            })
            
            return
        }
    });
}
function moverDatos(data,callback){
   self.cabys.codigo = data.codigo
   self.cabys.descripcion = data.descripcion
   self.cabys.impuesto = data.impuesto
   self.cabys.uri = data.uri
    var categoriasString =  ""
    $.each(data.categorias, function( index, modeloTabla ) {
        categoriasString = categoriasString + modeloTabla ;
        categoriasString = categoriasString +" \n"
    })
    self.cabys.origen = categoriasString
    self.cabys.origenSTR = categoriasString
    
    self.cabys.estado = data.estado
    self.articulo.codigoCabys = self.cabys.codigo
    $('#codigoCabys').val(self.cabys.codigo)
    self.update()
    actualizarComboTarifaConCabys(self.cabys.impuesto,function(resultado){
        console.log(resultado);
        asigImpuestos()
    })
    
    callback("Datos movidos")
}
/**
* Asigna el impuesto 13 cuando es valor igual 01
**/
/**
* Asigna el impuesto 13 cuando es valor igual 01
**/
__asignarImpuesto(){
  
 asigImpuestos()
}
function asigImpuestos(){
  if($('.selectTipoImpuesto').val()=="01"){
        self.articulo.tipoImpuesto ="01"
         self.articulo.impuesto = 0
        self.update()
    }else{
        $('.impuesto').val(null)
        self.articulo.impuesto = 0  
        self.articulo.tipoImpuesto =$('#tipoImpuesto').val() == "Exento"?"":$('#tipoImpuesto').val()
        self.update()
        $('#impuesto').val(self.articulo.impuesto)
    } 
     __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto,1)
    self.tarifas1  = {aaData:[]}
    self.update()
    getPublico()
}


function actualizarComboTarifaConCabys(valor,callback){
     $('.selectTipoImpuesto').val("01")
     self.articulo.tipoImpuesto = "01"
	if(valor == 0){
        self.articulo.codigoTarifa = "01"
		$('.selectCodigoTarifa1').val("01")
	}else if(valor == 1){
        self.articulo.codigoTarifa = "02"
		$('.selectCodigoTarifa1').val("02");
	}else if(valor == 2){
        self.articulo.codigoTarifa = "03"
		$('.selectCodigoTarifa1').val("03");
	}else if(valor == 4){
        self.articulo.codigoTarifa = "04"
		$('.selectCodigoTarifa1').val("04")
	}else if(valor == 8){
        self.articulo.codigoTarifa = "07"
		$('.selectCodigoTarifa1').val("07")
	}else if(valor == 13){
        self.articulo.codigoTarifa = "08"
		$('.selectCodigoTarifa1').val("08")
	}
    self.articulo.impuesto = valor
    self.update()
      callback("Datos movidos")
}

function agregarInputsCombosCabys(){
    $('.tableListarHaciendaCabys tfoot th').each( function (e) {
        var title = $('.tableListarHaciendaCabys thead th').eq($(this).index()).text();

        if ( $(this).index() != 0    ){
	      	$(this).html( '<input  type="text" class="form-input"  placeholder="'+title+'" />' );
	    }
    })
}


__LimpiarPantalla(){
   __InicializarDatos()
}

__AplicarSalida(){
    // variables para modulo de inventario 
    self.mostrarFormularioEntrada    = false
    self.mostrarFormularioSalida     = true
    self.mostrarBotonAgregarEntrada  = false
    self.mostrarBotonAgregarSalida   = false 
    self.botonSalida = false
    self.botonEntrada = false
    self.mostrarFormularioPrincipal = false
    self.botonModificar = false
    self.mostrarFormulario = false
    self.update()
    $('.observacion_salida').val(null)
    $('.cantidadNueva_salida').val(null)

}

__AplicarEntrada(){
    // variables para modulo de inventario 
    self.mostrarFormularioEntrada    = true
    self.mostrarFormularioSalida     = false
    self.mostrarBotonAgregarEntrada  = false
    self.mostrarBotonAgregarSalida   = false 
    self.botonSalida = false
    self.botonEntrada = false
    self.mostrarFormularioPrincipal = false
    self.botonModificar = false
    self.mostrarFormulario = false
    self.update()
    $('.observacion_entrada').val(null)
    $('.cantidadNueva_entrada').val(null)

}

__VolverPantallaPrincipal(){
    // variables para modulo de inventario 
    self.mostrarFormularioEntrada    = false
    self.mostrarFormularioSalida     = false
    self.mostrarBotonAgregarEntrada  = false
    self.mostrarBotonAgregarSalida   = false 
    self.botonSalida = true
    self.botonEntrada = true
    self.mostrarFormularioPrincipal = true
    self.botonModificar = true
    self.mostrarFormulario = true
    self.update()
}


function inicializarCursorCodigo(){
    $('.codigo').focus();
    $('.codigo').select();
    return
}

/**
*Consulta hacienda
**/
__ConsultarCodigo(e){
     if (e.keyCode != 13) {
        return;
    } 
    findByCodigo()
}

/**
** Buscar el codigo del articulo en el sistema
**/
function findByCodigo(){
    
    $("#id").val(null)
    self.articulo.id = null
    self.botonSalida = false
    self.botonEntrada = false
    self.update()

    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarArticuloAjax.do",
        datatype: "json",
        data: formulario,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message);
                }
                serverMessageJsonClase(data);
                inicializarCursorCodigo()
                self.mostrarTituloArticulo = true  
                self.mostrarFormulario = true
                self.botonModificar = false
                self.mostrarFormulario = true 
                self.botonModificar   = false;
                self.mostrarFormularioEntrada = false
                self.botonAgregar  = true;            
                self.update()

                __listadoTarifasByTipoImpuesto("",1)
                __listadoTarifasByTipoImpuestoMag("",1)
                
                
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.articulo = modeloTabla
                        self.mostrarTituloArticulo = true  
                        self.mostrarFormulario = true
                        self.botonModificar = true
                        self.mostrarFormulario = true 
                        self.botonModificar   = true;
                        self.mostrarFormularioEntrada = false
                        self.botonAgregar  = false;            
                        self.botonSalida = true
                        self.botonEntrada = true
                        self.update()
                        cargarCombosArticulo(self.categorias,self.marcas,self.tipoUnidades,modeloTabla.categoria.id,modeloTabla.marca.id,modeloTabla.unidadMedida);
                        __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto,1)
                        __listadoTarifasByTipoImpuestoMag(self.articulo.tipoImpuestoMag,1)
                        
                        $("#formulario").validate(reglasDeValidacion());   
                        
                        inicializarCursorCodigo()
                        
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

function __InicializarDatos(){
    self.articulo = {
		id:null,
        tipoCodigo:"",
		descripcion:"",
        serie:"",
		unidadMedida:"",
		costo:null,
		impuesto:null,
        impuestoMag:null,
        codigoTarifa:null,
        codigoTarifaMag:null,
        minimo:0,
        maximo:0,
		precioPublico:null,
		gananciaPrecioPublico:null,
		precioMayorista:null,
		gananciaPrecioMayorista:null,
		precioEspecial:null,
		gananciaPrecioEspecial:null,
        tipoImpuesto:null,
        tipoImpuestoMag:null,
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
   $('.selectTipoImpuesto').prop("selectedIndex", 0);
   $('.selectTipoImpuestoMag').prop("selectedIndex", 0);
   $('.selectTipoCodigo').prop("selectedIndex", 0);
  
   
   
   $("#contable").val($("#contable option:first").val()); 
   $('.descripcion').val(null)
   $('.serie').val(null)
   $('.costo').val(null)
   $('.cantidad').val(null)
   $('.codigoTarifaMag').val(null)
   $('.codigoTarifa').val(null)
   $('.tipoImpuesto').val(null)
   $('.tipoImpuestoMag').val(null)
   $('.impuesto').val(null)
   $('.impuestoMag').val(null)
   $('.precioPublico').val(null)
   $('.gananciaPrecioPublico').val(null)
   $('.precioMayorista').val(null)
   $('.gananciaPrecioMayorista').val(null)
   $('.precioEspecial').val(null)
   $('.gananciaPrecioEspecial').val(null)
   $('.prioridad').val(null)
   $('.pesoTransporte').val(null)
   $('.codigoCabys').val(null)
   
   $(".errorServerSideJgrid").remove();
   $("#formulario").validate(reglasDeValidacion());
  
  
   inicializarCursorCodigo()
}
var reglasDeValidacionParametroCabys = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			codigoCabys : {
                maxlength:13,
			},
            descArticulo : {
				maxlength : 100,
			},                                                
 		},
		ignore : []

	});
	return validationOptions;
};
/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
 function __Consulta(){
        self.mostrarTituloArticulo = true  
        self.mostrarFormulario = true
        self.botonModificar = false
        self.mostrarFormularioEntrada = false
        self.botonAgregar = true
        self.update()
        $("#formulario").validate(reglasDeValidacion());     
    
}

/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
            codigoCabys : {
                maxlength:13,
                required : true,
                minlength:13,
			},
			descripcion : {
				required : true,
                maxlength:80,
                minlength:1,
                
			},
            codigo : {
				required : true,
                maxlength:20,
                minlength:1,
                
			},                                                
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
            //    numeroMayorCero:true,
                number:true,
			} ,                                                
                                                             
            precioMayorista : {
                number:true,
			},                                                
            precioEspecial : {
                number:true,
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
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : 'AgregarEntradaKardex.do',
                    success : function(data) {
                        if (data.status != 200) {
                        	serverMessageJson(data);
                            if (data.message != null && data.message.length > 0) {
                                mensajeAdvertencia(data.message)
                            }
                        } else {
                        	serverMessageJson(data);
                            mensajeToasExito(data.message)
                            $(".errorServerSideJgrid").remove();
                            $('.observacion_entrada').val(null)
                            $('.cantidadNueva_entrada').val(null)
                            if (data.message != null && data.message.length > 0) {
                                 $.each(data.listaObjetos, function( index, modeloTabla ) {
                                     self.articulo = modeloTabla
                                     self.update()
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
}
/**
*  Agregar salida al inventario 
**/
__agregarSalidaInventario(){
   if ($("#formularioSalida").valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formularioSalida").serialize();
        $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : 'AgregarSalidaKardex.do',
                    success : function(data) {
                        if (data.status != 200) {
                        	serverMessageJsonClase(data);
                            if (data.message != null && data.message.length > 0) {
                                mensajeAdvertencia(data.message)
                            }
                        } else {
                        	serverMessageJson(data);
                            mensajeToasExito(data.message)
                            $(".errorServerSideJgrid").remove();
                            $('.observacion_salida').val(null)
                            $('.cantidadNueva_salida').val(null)
                            if (data.message != null && data.message.length > 0) {
                                 $.each(data.listaObjetos, function( index, modeloTabla ) {
                                     self.articulo = modeloTabla
                                     self.update()
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
}
/**
*  Lista de motivos de Salidas activas 
**/
function _ListaMotivoSalidasActivas(){
    $.ajax({
         url: "ListarMotivoSalidasActivasAjax.do",
        datatype: "json",
        global: false,
        method:"GET",
        contentType: "application/json; charset=utf-8",
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
function _ListaMotivoEntradasActivas(){
    $.ajax({
         url: "ListarMotivoActivasEntradasAjax.do",
        datatype: "json",
        global: false,
        method:"GET",
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
 self.mostrarBotonAgregarSalida = cantidad ==0 ? false:self.mostrarBotonAgregarSalida
 self.update()
}
/** Fin  funciones de inventario ----------------------------------------------------------------------------**/
/**
*  Ganancia del precio mayorista
**/
__CalculoGananciaMayorista(e){
    if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioMayorista    =  __valorNumerico($('#precioMayorista').val())
    self.articulo.gananciaPrecioMayorista    = __valorNumerico($('#gananciaPrecioMayorista').val())
    self.articulo.precioMayorista = _ObtenerPrecio(self.articulo.costo,self.articulo.impuesto,0,self.articulo.gananciaPrecioMayorista)
    self.update()
    $('#gananciaPrecioMayorista').val(__valorNumerico(redondeoDecimales(self.articulo.gananciaPrecioMayorista,aplicarRedondeo())))
    $('#precioMayorista').val(__valorNumerico(redondeoDecimales(self.articulo.precioMayorista,aplicarRedondeo())))
}

__CalculoPrecioMayorista(e){
    if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioMayorista    =  __valorNumerico($('#precioMayorista').val())
    self.articulo.gananciaPrecioMayorista    = __CalcularGanancia(impuesto,costo,precioMayorista);
    self.articulo.precioMayorista = precioMayorista
    self.update()
    $('#gananciaPrecioMayorista').val(__valorNumerico(redondeoDecimales(self.articulo.gananciaPrecioMayorista,aplicarRedondeo())))
    $('#precioMayorista').val(__valorNumerico(redondeoDecimales(self.articulo.precioMayorista,aplicarRedondeo())))
}

function getPrecioMayorista(){
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioMayorista    =  __valorNumerico($('#precioMayorista').val())
    self.articulo.gananciaPrecioMayorista    = __CalcularGanancia(impuesto,costo,precioMayorista);
    self.articulo.precioMayorista = precioMayorista > 0 ?precioMayorista:_ObtenerPrecio(self.articulo.costo,self.articulo.impuesto,0,self.articulo.gananciaPrecioMayorista)
    self.update()
    $('#gananciaPrecioMayorista').val(__valorNumerico(redondeoDecimales(self.articulo.gananciaPrecioMayorista,aplicarRedondeo())))
    $('#precioMayorista').val(__valorNumerico(redondeoDecimales(self.articulo.precioMayorista,aplicarRedondeo())))

}
/**
* ganancia del precio especial
**/
__CalculoGananciaEspecial(e){
     if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioEspecial = __valorNumerico($('#precioEspecial').val())
    self.articulo.gananciaPrecioEspecial = __valorNumerico($('#gananciaPrecioEspecial').val());
    self.articulo.precioEspecial = _ObtenerPrecio(self.articulo.costo,self.articulo.impuesto,0,self.articulo.gananciaPrecioEspecial)
    self.update()
    $('#gananciaPrecioEspecial').val(__valorNumerico(redondeoDecimales(self.articulo.gananciaPrecioEspecial,aplicarRedondeo())))
    $('#precioEspecial').val(__valorNumerico(redondeoDecimales(self.articulo.precioEspecial,aplicarRedondeo())))
}

__CalculoPrecioEspecial(e){
    if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioEspecial    =  __valorNumerico($('#precioEspecial').val())
    self.articulo.gananciaPrecioEspecial    = __CalcularGanancia(impuesto,costo,precioEspecial);
    self.articulo.precioEspecial = precioEspecial
    self.update()
    $('#gananciaPrecioEspecial').val(__valorNumerico(redondeoDecimales(self.articulo.gananciaPrecioEspecial,aplicarRedondeo())))
    $('#precioEspecial').val(__valorNumerico(redondeoDecimales(self.articulo.precioEspecial,aplicarRedondeo())))
}
function getPrecioEspecial(){
    
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioEspecial = __valorNumerico($('#precioEspecial').val())
    self.articulo.gananciaPrecioEspecial = __CalcularGanancia(impuesto,costo,precioEspecial);
    self.articulo.precioEspecial = precioEspecial > 0 ?precioEspecial:_ObtenerPrecio(self.articulo.costo,self.articulo.impuesto,0,self.articulo.gananciaPrecioEspecial)
    
    self.update()
    $('#gananciaPrecioEspecial').val(__valorNumerico(redondeoDecimales(self.articulo.gananciaPrecioEspecial,aplicarRedondeo())))
    $('#precioEspecial').val(__valorNumerico(redondeoDecimales(self.articulo.precioEspecial,aplicarRedondeo())))
}
/**
* Porcentaje de ganancia de Precio al Publico
**/
__CalculoGananciaPublico(e){
     if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico    =  __valorNumerico($('#precioPublico').val())
    self.articulo.gananciaPrecioPublico    = __valorNumerico($('#gananciaPrecioPublico').val());
    self.articulo.precioPublico =_ObtenerPrecio(self.articulo.costo,self.articulo.impuesto,0,self.articulo.gananciaPrecioPublico)
    self.update()
    $('#gananciaPrecioPublico').val(__valorNumerico(redondeoDecimales(self.articulo.gananciaPrecioPublico,aplicarRedondeo())))
    $('#precioPublico').val(__valorNumerico(redondeoDecimales(self.articulo.precioPublico,aplicarRedondeo())))

}

__CalculoPrecioPublico(e){
    if (e.keyCode == 8 || e.keyCode == 46){
        return
    }
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico    =  __valorNumerico($('#precioPublico').val())
    self.articulo.gananciaPrecioPublico    = __CalcularGanancia(impuesto,costo,precioPublico);
    self.articulo.precioPublico = precioPublico
    self.update()
    $('#gananciaPrecioPublico').val(__valorNumerico(redondeoDecimales(self.articulo.gananciaPrecioPublico,aplicarRedondeo())))
    $('#precioPublico').val(__valorNumerico(redondeoDecimales(self.articulo.precioPublico,aplicarRedondeo())))

}

function getPublico(){
   
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico    =  __valorNumerico($('#precioPublico').val())
    self.articulo.gananciaPrecioPublico    = __CalcularGanancia(impuesto,costo,precioPublico);
    self.articulo.precioPublico = precioPublico > 0 ?precioPublico:_ObtenerPrecio(self.articulo.costo,self.articulo.impuesto,0,self.articulo.gananciaPrecioPublico)
    self.update()
    $('#gananciaPrecioPublico').val(__valorNumerico(redondeoDecimales(self.articulo.gananciaPrecioPublico,aplicarRedondeo())))
    $('#precioPublico').val(__valorNumerico(redondeoDecimales(self.articulo.precioPublico,aplicarRedondeo())))
    

}

__AsignarTarifa(){
    self.articulo.impuesto = getMontoImpuesto(self.articulo.tipoImpuesto,$('#codigoTarifa').val(),self.tarifas.aaData)
    self.update()
    $('#impuesto').val(self.articulo.impuesto)
    getPublico()
    getPrecioEspecial()
    getPrecioMayorista()
}
__AsignarTarifaMag(){
    self.articulo.impuestoMag = getMontoImpuesto(self.articulo.tipoImpuestoMag,$('#codigoTarifaMag').val(),self.tarifasMag.aaData)
    self.update()
    $('#impuestoMag').val(self.articulo.impuestoMag)
}
/**
* Asigna el impuesto 13 cuando es valor igual 01
**/
__asignarImpuesto(){
    if($('.selectTipoImpuesto').val()=="01"){
        self.articulo.tipoImpuesto ="01"
         self.articulo.impuesto = 0
        self.update()
    }else{
        $('.impuesto').val(null)
        self.articulo.impuesto = 0  
        self.articulo.tipoImpuesto =$('#tipoImpuesto').val() == "Exento"?"":$('#tipoImpuesto').val()
        self.update()
        $('#impuesto').val(self.articulo.impuesto)
    } 
     __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto,1)
    self.tarifas  = {aaData:[]}
    self.update()
    getPublico()
    getPrecioEspecial()
    getPrecioMayorista()

}
/**
* Asigna el impuesto 13 cuando es valor igual 01
**/
__asignarImpuestoMag(){
    if($('.selectTipoImpuestoMag').val()=="01"){
        self.articulo.tipoImpuestoMag ="01"
         self.articulo.impuestoMag = 0
        self.update()
    }else{
        $('.impuestoMag').val(null)
        self.articulo.impuestoMag = 0  
        self.articulo.tipoImpuestoMag =$('#tipoImpuestoMag').val() == "Exento"?"":$('#tipoImpuestoMag').val()
        self.update()
        $('#impuestoMag').val(self.articulo.impuestoMag)
    } 
     __listadoTarifasByTipoImpuestoMag(self.articulo.tipoImpuestoMag,1)
    self.tarifasMag  = {aaData:[]}
    self.update()

}
/**
*  Mostrar listado datatable Categorias Actimpuestos
**/
function __listadoTarifasByTipoImpuestoMag(tipoImpuestoMag,indicador){
    if (typeof tipoImpuestoMag == 'undefined') {
        return
    }
    if (tipoImpuestoMag.length == 0 ){
        return
    }
    var selector = ""
    $.ajax({
         url: "ListarTarifasByTipoImpuestoAjax.do",
        datatype: "json",
         data: {tipoImpuesto:tipoImpuestoMag},
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                // Tipo de impuesto 1
                if(indicador ==1 ){
                    self.tarifasMag =  result
                    self.update()
                    self.articulo.impuestoMag = getMontoImpuesto(self.articulo.tipoImpuestoMag,$('#codigoTarifaMag').val(),self.tarifasMag.aaData)
                    self.update()
                }
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
function __listadoTarifasByTipoImpuesto(tipoImpuesto,indicador){
    if (typeof tipoImpuesto == 'undefined') {
        return
    }
    if (tipoImpuesto.length == 0 ){
        return
    }
    var selector = ""
    $.ajax({
         url: "ListarTarifasByTipoImpuestoAjax.do",
        datatype: "json",
         data: {tipoImpuesto:tipoImpuesto},
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                // Tipo de impuesto 1
                if(indicador ==1 ){
                    self.tarifas =  result
                    self.update()
                    self.articulo.impuesto = getMontoImpuesto(self.articulo.tipoImpuesto,$('#codigoTarifa').val(),self.tarifas.aaData)
                    self.update()
                }
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}
/**
**/
/**
* Actualizar el precio costo
**/
__ActualizarPreciosCosto(e){
    var impuesto  =  __valorNumerico($('#impuesto').val())/100
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico =  __valorNumerico($('#precioPublico').val())
    var gananciaPublica = _porcentajeGanancia(costo,impuesto,0,precioPublico)

    var precioMayorista =  __valorNumerico($('#precioMayorista').val())
    var gananciaPrecioMayorista = precioMayorista > 0 ? _porcentajeGanancia(costo,impuesto,0,precioPublico): 0

    var precioEspecial =  __valorNumerico($('#precioEspecial').val())
    var gananciaPrecioEspecial = precioEspecial > 0 ? _porcentajeGanancia(costo,impuesto,0,precioPublico): 0
   
    self.articulo.costo = costo
    self.articulo.precioPublico = precioPublico > 0 ? precioPublico : _ObtenerPrecio(costo,impuesto,0,gananciaPublica)
    self.articulo.gananciaPublica = gananciaPublica

    self.articulo.precioMayorista = precioMayorista > 0 ? precioMayorista:gananciaPrecioMayorista > 0 ? _ObtenerPrecio(costo,impuesto,0,gananciaPrecioMayorista):0
    self.articulo.gananciaPrecioMayorista = gananciaPrecioMayorista

    self.articulo.precioEspecial = precioEspecial > 0 ? precioEspecial: gananciaPrecioEspecial > 0 ? _ObtenerPrecio(costo,impuesto,0,gananciaPrecioEspecial):0
    self.articulo.gananciaPrecioEspecial = gananciaPrecioEspecial

    self.update()     
    $('#precioPublico').val(self.articulo.precioPublico)
    $('#gananciaPrecioPublico').val(self.articulo.gananciaPublica)

    $('#precioMayorista').val(self.articulo.precioMayorista)
    $('#gananciaPrecioMayorista').val(self.articulo.gananciaPrecioMayorista)

    $('#precioEspecial').val(self.articulo.precioEspecial)
    $('#gananciaPrecioEspecial').val(self.articulo.gananciaPrecioEspecial)
    

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
*   Agregar 
**/
__agregar(){
    if(validarPrecios()){
         return
     }
    var costoProducto =  __valorNumerico($('#costo').val())
    var precioPublico = __valorNumerico($('#precioPublico').val())

        if ($("#formulario").valid()) {
             var tipo = $('#tipoImpuesto').val() == "Exento"?"":$('#tipoImpuesto').val()
            if(tipo == "07"){
                var baseImponible = $('#baseImponible').val()
                if(baseImponible == 0){
                   mensajeAdvertencia("Debe actualizar la base imponible debe ser Activo") 
                   return 
                }
            }
        if(costoProducto > precioPublico){
             mensajeAdvertencia("No se puede agregar el precio Publico es menor al costo") 
            return 
        }
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formulario").serialize();
        $.ajax({
            type : "POST",
            dataType : "json",
            data : formulario,
            url : 'AgregarArticuloAjax.do',
            success : function(data) {
                if (data.status != 200) {
                   	serverMessageJson(data);
                    if (data.message != null && data.message.length > 0) {
                        mensajeAdvertencia(data.message)
                    }
                    inicializarCursorCodigo()
                } else {
                   	serverMessageJson(data);
                    mensajeToasExito(data.message)
                    __InicializarDatos()

                    inicializarCursorCodigo()
                }
            },
            error : function(xhr, status) {
                console.log(xhr);
                mensajeErrorServidor(xhr, status);
            }
        });
    }else{
        inicializarCursorCodigo()
        mensajeAdvertencia( "Falta ingresar datos del articulo que son obligatorios, verificar lo indicado en ROJO");
      }
}
/**
** Modificar la Empresa
**/
__Modificar(){
     if(validarPrecios()){
         return
     }
    var costoProducto =  __valorNumerico($('#costo').val())
    var precioPublico = __valorNumerico($('#precioPublico').val())
 
    if ($("#formulario").valid()) {
        var tipo = $('#tipoImpuesto').val() == "Exento"?"":$('#tipoImpuesto').val()
        if(tipo == "07"){
            var baseImponible = $('#baseImponible').val()
            if(baseImponible == 0){
                mensajeAdvertencia("Debe actualizar la base imponible debe ser Activo")
               return 
            }
        }
        if(costoProducto > precioPublico){
            mensajeAdvertencia("No se puede modificar el Articulo el precio Publico es menor al costo")
            return 
        }
        self.error = false;
        self.exito = false;
        self.update();
        __aplicarModificacion()

    }   else{
          inicializarCursorCodigo()
          mensajeAdvertencia("Falta ingresar datos del articulo que son obligatorios, verificar lo indicado en ROJO");
      }
    
}
function __aplicarModificacion(){
    if(validarPrecios()){
         return
     }
   var formulario = $("#formulario").serialize();
   $.ajax({
        type : "POST",
        dataType : "json",
        data : formulario,
        url : 'ModificarArticuloAjax.do',
        success : function(data) {
            if (data.status != 200) {
              	serverMessageJson(data);
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message)
                    inicializarCursorCodigo()
                }
            } else {
               inicializarCursorCodigo()
                $.each(data.listaObjetos, function( index, modeloTabla ) {
                    self.articulo = modeloTabla
                    self.update()        
                })

               	serverMessageJson(data);
                mensajeToasExito(data.message)
                inicializarCursorCodigo()
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
 
}

function validarPrecios(){
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico    =  __valorNumerico($('#precioPublico').val())
    var precioMayorista    =  __valorNumerico($('#precioMayorista').val())
    var precioEspecial    =  __valorNumerico($('#precioEspecial').val())
    var resultadoImpuesto =  impuesto 
    resultadoImpuesto = resultadoImpuesto > 0 ? resultadoImpuesto /100 : 0
    resultadoImpuesto = resultadoImpuesto > 0 ? resultadoImpuesto + 1 : 0
    var total = resultadoImpuesto > 0  ? precioPublico / resultadoImpuesto : precioPublico
    if(precioPublico > 0 && resultadoImpuesto > 0){
        if(total < costo ){
            mensajeAdvertencia('El Precio Publico es menor al Costo')
            return true
        }

    }
    total = resultadoImpuesto > 0 ? precioMayorista / resultadoImpuesto : precioMayorista
    if(precioMayorista > 0 && resultadoImpuesto > 0){
        if(total < costo ){
            mensajeAdvertencia('El Precio Mayorista es menor al Costo')
            return true
        }

    }
    total = resultadoImpuesto > 0 ?  precioEspecial / resultadoImpuesto : precioEspecial
    if(precioEspecial > 0 && resultadoImpuesto > 0){
        if(total < costo ){
            mensajeAdvertencia('El Precio Especial es menor al Costo')
            return true
        }

    }

    return false
  
}
function sumar(){
    self.totalCosto = 0
    self.totalPrecioPublico = 0
    self.update()
    $.each(self.listaArticulos, function( index, modeloTabla ) {
          self.totalCosto += modeloTabla.costo * modeloTabla.cantidad
          self.totalPrecioPublico += modeloTabla.precioPublico * modeloTabla.cantidad
    })
    self.totalCosto         = redondearDecimales(self.totalCosto,2)
    self.totalPrecioPublico = redondearDecimales(self.totalPrecioPublico,2)
    self.update()
}
/**
* Inicializar el articulo
**/
function _inicializarArticulo(){
    self.articulo = {
		id:null,
        codigo:"",
        tipoCodigo :"",
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
        },
        prioridad:1
    }    
    self.update()
}
</script>
</articulo-nuevo>