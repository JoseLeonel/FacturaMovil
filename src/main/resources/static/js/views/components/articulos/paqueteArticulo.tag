<paquete-articulo>
<div show ={mostrarFormulario}>
<div class="tituloBotones">
    <div class="articulo-title"><i class="fa fa-edit"></i>&nbsp {articulo.id > 0 ? "Modificar Paquete/Caja"   :"Agregar Paquete/Caja"}     </div>
    <div class="botones">
        <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-right"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
            {$.i18n.prop("btn.volver")}
        </button>
        <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
        <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>
    </div>
</div>            
 <div class="row scrollerT "  >
    <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">
        <div id="divFormulario" > 
            <!--Form-->
            <form class="form-horizontal formulario" name= "formulario" id="formulario">
                <input type="hidden" name="id" id="id" value="{articulo.id}">
                <input type="hidden" name="idPaquete" id="idPaquete" value="1">
                
                <div class="panel-group " id="accordion">
                    <div class="panel panel-default" id="cuentas">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" >
                            <div class="panel-heading" style="background: #3c8dbc; color: white;">
                                <h4 class="panel-title"><span class="fa fa-bank col-md-offset-5"></span> Caja/Paquete Articulo</h4>
                            </div>
                        </a>             
                        <div id="collapse1" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                        <label  >{$.i18n.prop("articulo.categoria")}  <span class="requeridoDato">*</span></label>
                                        <select  class="form-control selectCategoria campoNumerico "   name="categoria" data-live-search="true">
                                            <option  each={categorias.aaData}  data-tokens ={descripcion} value="{id}" selected="{articulo.categoria.id ==id?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>   
                                    <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra">{$.i18n.prop("articulo.tipoCodigo")}</label>
                                        <select  class="form-control selectTipoCodigo campoNumerico" id="tipoCodigo" name="tipoCodigo"  >
                                            <option  each={tipoCodigos}  value="{codigo}" selected="{articulo.tipoCodigo ==codigo?true:false}"  >{descripcion}</option>
                                        </select>
                                    </div>
                                     <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra" >Codigo barra unidad    <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control codigoSecundario campoNumerico" id="codigoSecundario" name="codigoSecundario" value="{articulo.codigoSecundario}"  autocomplete="off" autofocus="autofocus">
                                    </div>


                                    <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra" > Codigo barra caja/paquete <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control codigo campoNumerico" id="codigo" name="codigo" value="{articulo.codigo}"  autocomplete="off" autofocus="autofocus" onkeypress = {__ConsultarCodigo} autocomplete="off">
                                    </div>
                                  
                                </div> 
                                <div class="row">   
                                  <div class= "col-md-8 col-sx-8 col-sm-8 col-lg-8 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.descripcion")}  <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control descripcion campoNumerico" id="descripcion" name="descripcion" value="{articulo.descripcion}"  autocomplete="off" >
                                    </div>
                                     <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetraTotales" >Cabys <span class="requeridoDato">*</span></label>
                                        <input type="text" class="form-control campoNumerico codigoCabys" id="codigoCabys" name="codigoCabys" value="{articulo.codigoCabys}"  onclick={__ConsultaHaciendaCabys}  autocomplete="off">
                                    </div>
                                </div>    
                                <div class="row">   
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.costo")} </label>
                                        <input type="number" step="any" class="form-control costo campoNumerico" id="costo" name="costo" value="{articulo.costo}"  onkeyup ={__ActualizarPreciosCosto} autocomplete="off">
                                    </div>
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.precioPublico")}  <span class="requeridoDato">*</span></label>
                                        <input type="number" step="any" class="form-control precioPublico campoNumerico" id="precioPublico" name="precioPublico" onkeyup ={__CalculoPrecioPublico} value="{articulo.precioPublico}"  autocomplete="off" >
                                    </div>
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.gananciaPrecioPublico")} % </label>
                                        <input type="number" step="any" class="form-control gananciaPrecioPublico campoNumerico" id="gananciaPrecioPublico" name="gananciaPrecioPublico" value="{articulo.gananciaPrecioPublico}"  onkeyup ={__CalculoGananciaPublico}>
                                    </div>

                                </div>    
                                <div class="row">
                                     <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.precioMayorista")}  </label>
                                        <input type="number" step="any" class="form-control precioMayorista campoNumerico" id="precioMayorista" name="precioMayorista" value="{articulo.precioMayorista}" onkeyup={__CalculoPrecioMayorista} autocomplete="off" >
                                    </div>  

                                     <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.gananciaPrecioMayorista")} % </label>
                                        <input type="number" step="any" class="form-control gananciaPrecioMayorista campoNumerico" id="gananciaPrecioMayorista" name="gananciaPrecioMayorista" value="{articulo.gananciaPrecioMayorista}"  onkeypress ={__CalculoGananciaMayorista}>
                                    </div>

                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label  class="tamanoLetra">{$.i18n.prop("articulo.precioEspecial")}  </label>
                                        <input type="number" step="any" class="form-control precioEspecial campoNumerico" id="precioEspecial" name="precioEspecial" value="{articulo.precioEspecial}"  onkeyup={__CalculoPrecioEspecial}>
                                    </div>                        

                                </div>
                                <div class="row">
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.gananciaPrecioEspecial")} % </label>
                                        <input type="number" sp="any" class="form-control gananciaPrecioEspecial campoNumerico" id="gananciaPrecioEspecial" name="gananciaPrecioEspecial" value="{articulo.gananciaPrecioEspecial}"  onkeyup ={__CalculoGananciaEspecial}>
                                    </div>
                                    <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                        <label  class="tamanoLetra">Precio Sugerido </label>
                                        <input type="number" step="any" class="campoNumerico precioSugerido" id="precioSugerido" name="precioSugerido" value="{articulo.precioSugerido}" autocomplete="off" >
                                    </div>


                                    <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">

                                        <label class="tamanoLetra">{$.i18n.prop("articulo.tipoImpuesto")} </label>
                                        <select onchange= {__asignarImpuesto} class="form-control selectTipoImpuesto campoNumerico" id="tipoImpuesto" name="tipoImpuesto"  >
                                            <option  each={impuestos}  value="{codigo}" selected="{articulo.tipoImpuesto ==codigo?true:false}"  >{descripcion}</option>
                                        </select>
                                    </div>

                                    <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra">{$.i18n.prop("articulo.codigoTarifa")}</label>
                                        <select  onchange= {__AsignarTarifa} class="form-control selectCodigoTarifa1 campoNumerico" id="codigoTarifa" name="codigoTarifa"  >
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
                                        <select  class="form-control selectMarca campoNumerico"  name="marca" data-live-search="true">
                                            <option  each={marcas.aaData}  value="{id}" data-tokens ={descripcion} selected="{articulo.marca.id ==id?true:false}"  >{descripcion}</option>
                                        </select>
                                    </div>
                                    <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.unidadMedida")}  <span class="requeridoDato">*</span></label>
                                        <select  class="form-control selecTipoUnidad has-success campoNumerico" name="unidadMedida" >
                                            <option   each={tipoUnidades.aaData}  value="{codigo}"  selected="{articulo.unidadMedida ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>
                                    
                                </div>    
                            </div>

                        </div>
                    </div>
                </div>
                <div class="panel-group" id="accordion2">
                    <div class="panel panel-default" id="cuentas">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" >
                            <div class="panel-heading" style="background: #3c8dbc; color: white;">
                                <h4 class="panel-title"><span class="fa fa-bank col-md-offset-5"></span> Otros</h4>
                            </div>
                        </a>
                        <div id="collapse2" class="panel-collapse collapse2">
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
                                        <input type="number" step="any" class="form-control cantidad campoNumerico" id="cantidad" name="cantidad" value="{articulo.cantidad}"  >
                                    </div>                        
                                    <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                        <label class="tamanoLetra" >{$.i18n.prop("articulo.consecutivoCompra")} </label>
                                        <input type="text" step="any" class="form-control campoNumerico"  value="{articulo.consecutivoCompra}"  readonly>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra">Base Imponible</label>
                                        <select  class="form-control campoNumerico" id="baseImponible" name="baseImponible" >
                                            <option  each={baseImponibles}  value="{codigo}" selected="{articulo.baseImponible ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>    
                                    <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra"  >{$.i18n.prop("articulo.fechaUltimaCompra")} </label>
                                        <input type="text" step="any" class="form-control campoNumerico"  value="{articulo.fechaUltimaCompra}"  readonly>
                                    </div>
                                    <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4 has-success">
                                        <label class="tamanoLetra">{$.i18n.prop("articulo.estado")}</label>
                                        <select  class="form-control campoNumerico" id="estado" name="estado"  >
                                            <option  each={estados}  value="{codigo}" selected="{articulo.estado ==codigo?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>     
                                </div>




                            
                            </div>
                        </div>
                    </div>
                </div>            
                <div class="panel-group" id="accordion3">
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


<!--Modal mostrar Articulos de la empresa -->
<div id='modalHaciendaCabys' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
    <div class="modal-dialog modal-lg" >
        <div class="modal-content" style="width:100%;">
            <div class="modal-header with-border table-header" >
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
                                    <input type="text" class="form-control codigoCabys  codigoCabysMod"   id="codigoCabysMod" name="codigoCabysMod" onkeypress={__ConsultaCodigoCabysEnter} autofocus="autofocus" autocomplete="off">
                                </div>
                                <div class= "col-md-2 col-sx-12 col-sm-2 col-lg-2">
                                    <label>cantidad</label>
                                    <select  class="form-control" id="cantidadCabys" name="cantidadCabys" >
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
    width: 100%;
   height: 500px;
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
    self.impuestos1 =[]
    self.tipoCodigos =[]
    self.contables                 = []
    self.estados                   = []
    self.baseImponibles            = []
    self.comanda                   = []
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarTituloArticulo     = true
    self.precioPantalla = true
    self.impuestosIVAIPantalla = false;
    self.otrosPantalla = false;
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
    self.impuestosMag =[]
   
    // variables para modulo de inventario 
    self.mostrarFormularioEntrada    = false
    self.mostrarFormularioSalida     = false
    self.mostrarBotonAgregarEntrada  = false
    self.mostrarBotonAgregarSalida   = false 
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
    __Eventos()
    __ComboEstados()
    __ComboComanda()
    __ComboContables()
    __ComboBaseImponibles()
    __listadoTipoUnidadesActivas()   
    __listadoMarcasActivas()
    self.impuestos = __ComboImpuestos()
    self.impuestosMag = __ComboImpuestosMaG()
    self.tipoCodigos =__CombotipoCodigo()
    self.update()
     LimpiarArticulo()
    __Consulta()
    
   

    $('.collapse').collapse("show")
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
})


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
                enviarCargarCombos()
                
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
                        __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto,1)
                        __listadoTarifasByTipoImpuestoMag(self.articulo.tipoImpuestoMag,1)
                        
                        $("#formulario").validate(reglasDeValidacion());   
                        enviarCargarCombos()
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

function inicializarCursorCodigo(){
    $('.codigo').focus();
    $('.codigo').select();
    return
}


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


function __ListaDeHaciendaCabys(){
   // if( $('#descArticulo').val() =='' && $('.codigoCabysMod').val() =='' ){
   //     return
   // }
    var cantidadTemp = $('#cantidadCabys').val() == 'Todos'?0:$('#cantidadCabys').val()
    var  encontro = false
    $(".tableListarHaciendaCabys").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
    var parametros = {
        descArticulo :$('#descArticulo').val(),
        cantidad: cantidadTemp,
        codigo: $('.codigoCabysMod').val()
    };
  if ($("#formularioParametros").valid()) {
    $.ajax({
        url: 'ListarCabysDeHaciendaAjax.do',
        datatype: "json",
        method:"GET",
        data :parametros,
        success: function (result) {
            if(result.aaData.length > 0){
                __InformacionDataTable_cabys()
                $.each(result.aaData, function( index, modeloTabla ) {
                   if(modeloTabla.cabys.length){
                      self.listaCabys.aaData =modeloTabla.cabys    
                      encontro = true 
                   } 
                   
                })
                self.update()
                if(encontro == true){
                   __cargarTablaCompras()
                }
                

            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
  }
}

function __cargarTablaCompras() {
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
precioPantallaClick(){
    self.precioPantalla = true
    self.impuestosIVAIPantalla = false
    self.otrosPantalla = false
    self.update()
    $( ".nav-itemPrecio" ).addClass( "Active" );
}
impuestosIVAIPantallaClick(){
    self.impuestosIVAIPantalla = true
    self.precioPantalla = false
    self.otrosPantalla = false
    self.update()
}
otrosPantallaClick(){
    self.impuestosIVAIPantalla = false
    self.precioPantalla = false
    self.otrosPantalla = true
    self.update()

}
function getMontoTarifa(tipoImpuesto,codigoTarifa,array) {
  return array.filter(
    function(data) {
      return data.tipoImpuesto == tipoImpuesto && data.tarifaIVAI.codigoTarifa == codigoTarifa?data.monto:0
    }
  );
}
function getMontoImpuesto(tipoImpuesto,codigoTarifa,array){
    if(tipoImpuesto.length ==0){
        return 0
    }
    if(tipoImpuesto ==null){
        return 0
    }
    var valor = getMontoTarifa(tipoImpuesto,codigoTarifa,array);
    valor = valor !=null?valor[0]:null
    return valor == null?0:valor.monto
}

/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
 function __Consulta(){
    //Agregar
    if(self.parametros.tipoEjecucion ==1){
        LimpiarArticulo()
        self.mostrarTituloArticulo = true  
        self.mostrarFormulario = true
        self.botonModificar = false
        self.mostrarFormularioEntrada = false
        self.botonAgregar = true
        self.precioPantalla = true
        self.impuestosIVAIPantalla = false
        self.otrosPantalla = false                        
        self.update()
        $("#formulario").validate(reglasDeValidacion());     
    }   
    //modificar
    if(self.parametros.tipoEjecucion ==2){
        self.articulo = self.parametros.articulo
        self.mostrarTituloArticulo = true  
        self.mostrarFormulario = true
        self.botonModificar = true
        self.mostrarFormulario = true 
        self.botonModificar   = true;
        self.mostrarFormularioEntrada = false
        self.botonAgregar  = false;            
        self.update()
        __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto,1)
        __listadoTarifasByTipoImpuestoMag(self.articulo.tipoImpuestoMag,1)
        $("#formulario").validate(reglasDeValidacion());     
       
    }  
}

/**
* Limpiar Articulo
**/
function LimpiarArticulo(){
   self.articulo = {
		id:null,
        codigo:"",
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
   $('.selectTipoCodigo1').prop("selectedIndex", 0);
   $('.selecTipoUnidad').prop("selectedIndex", 0);
   $('.selectMarca').prop("selectedIndex", 0);
   $('.selectCategoria').prop("selectedIndex", 0);
   $("#categoria").val($("#categoria option:first").val()); 
   $("#marca").val($("#marca option:first").val()); 
   $("#unidadMedida").val($("#unidadMedida option:first").val()); 
   $("#contable").val($("#contable option:first").val()); 
   $('.codigo').val(null)
   $('.descripcion').val(null)
   $('.serie').val(null)
   $('.costo').val(null)
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
   enviarCargarCombos()
}
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
    __listadoCategoriasActivas()
    __listadoMarcasActivas()
   
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
* Camps requeridos 
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
              codigoCabys : {
                maxlength:13,
                required : true,
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
            codigoSecundario : {
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
                numeroMayorCero:true,
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
    self.tarifas1  = {aaData:[]}
    self.update()
    getPublico()
    getPrecioEspecial()
    getPrecioMayorista()

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
function actualizarPreciosImpuestosPublico(){
    var ganancia = __valorNumerico($('#gananciaPrecioPublico').val())
    var impuesto   = __valorNumerico($('#impuesto').val())/100
    impuesto = impuesto > 0 ? 1+impuesto:0
    var costo      = __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioPublico  = ganancia
    self.update()
    //calcular el precio publico
    var resultado = ganancia / 100
    resultado = 1-resultado
    //resultado costo + ganancia
    var total = costo  / resultado
    if(impuesto>0){
        total = total * impuesto
    }
    self.articulo.precioPublico = total>0?total:self.articulo.precioPublico
    self.update()
    $('.precioPublico').val(self.articulo.precioPublico)


}

function actualizarPreciosImpuestosMayorista(){
    var ganancia = __valorNumerico($('#gananciaPrecioMayorista').val())
    if(ganancia == 0){
        return
    }
    var impuesto   = __valorNumerico($('#impuesto').val())/100
    impuesto = impuesto > 0 ? 1+impuesto:0
    var costo      = __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioMayorista  = ganancia
    self.update()
    //calcular el precio publico
    var resultado = ganancia / 100
    resultado = 1-resultado
    //resultado costo + ganancia
    var total = costo  / resultado
    if(impuesto>0){
        total = total * impuesto
    }
    self.articulo.precioMayorista = total>0?total:self.articulo.precioMayorista
    self.articulo.precioMayorista =__valorNumerico(self.articulo.precioMayorista)
    self.articulo.precioMayorista =  self.articulo.precioMayorista.toFixed(2) 
    self.update()
    $('.precioMayorista').val(self.articulo.precioMayorista)
}

function actualizarPreciosImpuestosEspecial(){
    var ganancia = __valorNumerico($('#gananciaPrecioEspecial').val())
    if(ganancia == 0){
        return
    }
    var impuesto   = __valorNumerico($('#impuesto').val())/100
    impuesto = impuesto > 0 ? 1+impuesto:0
    var costo      = __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioEspecial  = ganancia
    self.update()
    //calcular el precio publico
    var resultado = ganancia / 100
    resultado = 1-resultado
    //resultado costo + ganancia
    var total = costo  / resultado
    if(impuesto > 0){
        total = total * impuesto
    }
    self.articulo.precioEspecial = total>0?total:self.articulo.precioEspecial
    self.update()
    $('.precioEspecial').val(self.articulo.precioEspecial)
}
/**
* Actualizar el precio costo
**/
__ActualizarPreciosCosto(e){
    var impuesto  =  __valorNumerico($('#impuesto').val())/100
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico =  __valorNumerico($('#precioPublico').val())
    var gananciaPublica = _porcentajeGanancia(costo,impuesto,0,precioPublico)
    gananciaPublica = gananciaPublica >0?gananciaPublica/100:0
    
     //  Costo , ganancia digitada , impuestos digitados  Altera el precio
       var resultadoPorcentajeGanancia = gananciaPublica>0?1-gananciaPublica:0
       var resultadoImpuesto = 0
       precioPublico =  costo > 0 && resultadoPorcentajeGanancia >0? costo /resultadoPorcentajeGanancia:precioPublico
       if(impuesto > 0){
          resultadoImpuesto =  impuesto + 1 
          precioPublico = precioPublico * resultadoImpuesto  
       }
       self.articulo.gananciaPrecioPublico    =  gananciaPublica * 100
       self.articulo.gananciaPrecioPublico    = __valorNumerico(self.articulo.gananciaPrecioPublico)
       self.articulo.gananciaPrecioPublico = self.articulo.gananciaPrecioPublico.toFixed()
       self.articulo.precioPublico = precioPublico > 0?precioPublico:self.articulo.precioPublico
       self.articulo.costo = costo
       self.update()     
    

}
/**
* calculo de Precio
**/
function _CalculoPrecio(){
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico    =  __valorNumerico($('#precioPublico').val())
    self.articulo.gananciaPrecioPublico    = precioPublico >0?_porcentajeGanancia(costo,impuesto,0,precioPublico):0
    self.articulo.precioPublico = _PrecioPublicoConGanancia(costo,impuesto,0,self.articulo.gananciaPrecioPublico)
     var precio = __valorNumerico($('#precioMayorista').val())
    if(precio > 0){
        self.articulo.gananciaPrecioMayorista    = precio >0?_porcentajeGanancia(costo,impuesto,0,precio):0
        self.articulo.precioMayorista = _PrecioPublicoConGanancia(costo,impuesto,0,self.articulo.gananciaPrecioMayorista)
    }
    precio = __valorNumerico($('#precioEspecial').val())
    if(precio > 0){
        self.articulo.gananciaPrecioEspecial = precio >0?_porcentajeGanancia(costo,impuesto,0,precio):0
        self.articulo.precioEspecial = _PrecioPublicoConGanancia(costo,impuesto,0,self.articulo.gananciaPrecioEspecial)
    }
    self.update()
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
*  Mostrar listado datatable Categorias activas
**/
function __listadoCategoriasActivas(){
     self.categorias                = {aaData:[]}
     self.update()
    $.ajax({
         url: "ListarCategoriasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.categorias.aaData =  result.aaData
                self.update();
                $('.selectCategoria').selectpicker(
                    {
                        style: 'btn-info',
                        size:10,
                        liveSearch: true
                    }
                );
                $('.selectCategoria').selectpicker('refresh');
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
function __listadoMarcasActivas(){
    self.marcas                    = {aaData:[]}
    self.update()
    $.ajax({
         url: "ListarMarcasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.marcas.aaData =  result.aaData
                self.update();
                $('.selectMarca').selectpicker(
                    {
                        style: 'btn-info',
                        size:10,
                        liveSearch: true
                    }
                );
                $('.selectMarca').selectpicker('refresh');
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
        global: false,
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
function __ComboComanda(){
    self.comanda =[]
    self.update()
    self.comanda.push({
        codigo: 0,
        descripcion: "No enviar"
     });

    self.comanda.push({
        codigo: 1,
        descripcion: $.i18n.prop("combo.comanda.cocina.1")
     });
    self.comanda.push({
        codigo:2,
        descripcion:$.i18n.prop("combo.comanda.cocina.2")
     });
     self.update();
}
/**
*  Crear el combo comanda
**/
function __ComboEstados(){
    self.estados =[]
    self.update()
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
*  Crear el combo base imponible
**/
function __ComboBaseImponibles(){
    self.baseImponibles =[]
    self.update()
    
    self.baseImponibles.push({
        codigo: 0,
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
     self.baseImponibles.push({
        codigo: 1,
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
     self.update();
}
/**
* Combo para verificar si es contabilizado en el inventario o no
**/
function __ComboContables(){
    self.contables =[]
    self.update()
    self.contables.push({
        codigo: $.i18n.prop("boolean.no"),
        descripcion: $.i18n.prop("boolean.no") 
     });
    self.contables.push({
        codigo: $.i18n.prop("boolean.si"),
        descripcion:$.i18n.prop("boolean.si")
     });
     self.update();
}


/**
*  Regresar al listado
**/
__regresarAlListado(){
    self.mostrarTituloArticulo = true
    self.mostrarListado     = true;
    self.botonAgregar       = false;
    self.botonModificar     = false;
    self.mostrarFormulario  = false 
    self.mostrarFormularioEntrada    = false
    self.mostrarFormularioSalida     = false
    self.update()
    //articulo.js se encuentra rutina
    __mostrarListado()
}
/**
*   Agregar 
**/
__agregar(){
    if(validarPrecios()){
         return
     }

        if ($("#formulario").valid()) {
             var tipo = $('#tipoImpuesto').val() == "Exento"?"":$('#tipoImpuesto').val()
            if(tipo == "07"){
                var baseImponible = $('#baseImponible').val()
                if(baseImponible == 0){
                   mensajeEstatico("Debe actualizar la base imponible debe ser Activo") 
                   return 
                }
                
            }
            
        var costoProducto =  __valorNumerico($('#costo').val())
        var precioPublico = __valorNumerico($('#precioPublico').val())
 
        if(costoProducto > precioPublico){
             mensajeEstatico("No se puede agregar el precio Publico es menor al costo") 
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
                                mensajeEstatico(data.message)
                            }
                        } else {
                        	serverMessageJson(data);
                            mensajeToasExito(data.message)
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
    }else{

        mensajeEstatico( "Falta ingresar datos del articulo que son obligatorios, verificar lo indicado en ROJO");
      }
}
/**
** Modificar la Empresa
**/
__Modificar(){
     if(validarPrecios()){
         return
     }

    if ($("#formulario").valid()) {
        var tipo = $('#tipoImpuesto').val() == "Exento"?"":$('#tipoImpuesto').val()
        if(tipo == "07"){
            var baseImponible = $('#baseImponible').val()
            if(baseImponible == 0){
                mensajeEstatico("Debe actualizar la base imponible debe ser Activo")
               return 
            }
        }
        var costoProducto =  __valorNumerico($('#costo').val())
        var precioPublico = __valorNumerico($('#precioPublico').val())


        if(costoProducto > precioPublico){
            mensajeAdvertencia("No se puede modificar el Articulo el precio Publico es menor al costo")
            return 
        }
        self.error = false;
        self.exito = false;
        self.update();
        __modificarRegistro("#formulario",$.i18n.prop("articulo.mensaje.alert.modificar"),'ModificarArticuloAjax.do','ListarArticuloPaquetesAjax.do','#tableListar')

    }   else{
          mensajeEstatico("Falta ingresar datos del articulo que son obligatorios, verificar lo indicado en ROJO");
      }
    
}

function validarPrecios(){
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico    =  __valorNumerico($('#precioPublico').val())
    var precioMayorista    =  __valorNumerico($('#precioMayorista').val())
    var precioEspecial    =  __valorNumerico($('#precioEspecial').val())
    var resultadoImpuesto =  impuesto 
    resultadoImpuesto =resultadoImpuesto > 0 ? resultadoImpuesto /100 : 0
    resultadoImpuesto = resultadoImpuesto > 0 ? resultadoImpuesto + 1 : 0
    var total = resultadoImpuesto >  0  ? precioPublico / resultadoImpuesto : precioPublico
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
    total = resultadoImpuesto > 0 ? precioEspecial / resultadoImpuesto: precioEspecial
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
</paquete-articulo>