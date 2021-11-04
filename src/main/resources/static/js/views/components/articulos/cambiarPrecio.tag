<cambiar-precio>

    <button show = {botonAgregar} title="Agregar un Nuevo Articulo"  onclick={__agregar}   class="btn-green btn-add pull-right" >&nbsp Nuevo 新</button>
    <button  onclick={__Modificar} title="modificar el Articulo" show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")} 修改</button>
    <button  onclick={__Imprimir} title="Imprimir codigo  y precio" class="btn-imprimirCambioPrecio btn-print pull-right" > &nbsp {$.i18n.prop("btn.imprimir")}</button>
    <div class="row">
            <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">
                <div id="divFormulario" >
                        <!--Form-->
                        <form class="form-horizontal formulario" name= "formulario" id="formulario">
                            <input type="hidden" name="id" id="id" value="{articulo.id}" >
                             <input type="hidden" name="esPaquete" id="esPaquete" value="0">
                             <input type="hidden" name="categoria" id="categoria" value="{articulo.categoria.id}">
                             <input type="hidden" name="marca" id="marca" value="{articulo.marca.id}">
                            <input type="hidden" name="idPaquete" id="idPaquete" value="{articulo.cantidadPaquete}">
                            <input type="hidden" name="codigoSecundario" id="codigoSecundario" value="{articulo.codigoSecundario}">
                            <input type="hidden" name="datosCabys" id="datosCabys" >
                            <input type="hidden" id="precioMayorista" name="precioMayorista" value="{articulo.precioMayorista}"  >
                            <input type="hidden" id="gananciaPrecioMayorista" name="gananciaPrecioMayorista" value="{articulo.gananciaPrecioMayorista}">
                            <input type="hidden"  id="gananciaPrecioEspecial" name="gananciaPrecioEspecial" value="{articulo.gananciaPrecioEspecial}"  >
                            <input type="hidden"  id="precioEspecial" name="precioEspecial" value="{articulo.precioEspecial}" >
                            <input type="hidden"  id="maximo" name="maximo" value="{articulo.maximo}"  >
                            <input type="hidden"  id="minimo" name="minimo" value="{articulo.minimo}"  >
                            <input type="hidden"  id="cantidad" name="cantidad" value="{articulo.cantidad}"  >
                            <input type="hidden"  id="prioridad" name="prioridad" value="{articulo.prioridad}"  >
                            <input type="hidden"  id="pesoTransporte" name="pesoTransporte" value="{articulo.pesoTransporte}"  >
                            <input type="hidden"  id="tipoImpuesto1" name="tipoImpuesto1" value="{articulo.tipoImpuesto1}"  >
                            <input type="hidden"  id="codigoTarifa1" name="codigoTarifa1" value="{articulo.codigoTarifa1}"  >
                            <input type="hidden"  id="impuesto1" name="impuesto1" value="{articulo.impuesto1}"  >
                            <input type="hidden"  id="codigoTarifaMag" name="codigoTarifaMag" value="{articulo.codigoTarifaMag}"  >
                            <input type="hidden"  id="impuestoMag" name="impuestoMag" value="{articulo.impuestoMag}"  >
                            <input type="hidden"  id="tipoImpuestoMag" name="tipoImpuestoMag" value="{articulo.tipoImpuestoMag}"  >

                            <input type="hidden" name="tipoFacturar" id="tipoFacturar" value="{articulo.tipoFacturar}">
                            <div class="panel-group" id="accordion">
                                <div class="panel panel-default" id="cuentas">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" >
                                        <div class="panel-heading" style="background: #3c8dbc; color: white;">
                                            <h4 class="panel-title"><span class="fa fa-bank col-md-offset-5"></span> Precios y IVA impuestos</h4>
                                        </div>
                                    </a>
                                    <div id="collapse1" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                    <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.codigo")}  <span class="requeridoDato">*</span></label>
                                                    <input type="text" class="campoNumerico codigo" id="codigo" name="codigo" value="{articulo.codigo}"  onkeypress={__Consulta} autofocus="autofocus" autocomplete="off">
                                                </div>
                                                 <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                    <label class="tamanoLetraTotales" >Cabys <span class="requeridoDato">*</span></label>
                                                    <input type="text" class="campoNumerico codigoCabys" id="codigoCabys" name="codigoCabys" value="{articulo.codigoCabys}"  onclick={__ConsultaHaciendaCabys} autofocus="autofocus" autocomplete="off">
                                                </div>

                                                <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                    <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.descripcion")}  <span class="requeridoDato">*</span></label>
                                                    <input type="text" class="campo descripcion" id="descripcion" name="descripcion" value="{articulo.descripcion}" autocomplete="off" >
                                                </div>
                                                <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                    <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.categoria")}  <span class="requeridoDato">*</span></label>
                                                    <input type="text" class="form-control campoNumerico " id="descripcionCategoria" name="descripcionCategoria" value="{articulo.categoria.descripcion}"  onclick={__ConsultaCategorias}  autocomplete="off">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                                    <label class="tamanoLetraTotales"  >{$.i18n.prop("articulo.costo")} </label>
                                                    <input type="number" step="any" class="campoNumerico costo" id="costo" name="costo" value="{articulo.costo}"  onkeyup ={__ActualizarPreciosCosto} autocomplete="off">
                                                </div>
                                                <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                                    <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.gananciaPrecioPublico")}%  </label>
                                                    <input type="number" step="any" class="campoNumerico gananciaPrecioPublico" id="gananciaPrecioPublico" name="gananciaPrecioPublico"  value="{articulo.gananciaPrecioPublico}"  onkeyup ={__CalculoGananciaPublico} autocomplete="off">
                                                </div>
                                                <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                                    <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.precioPublico")}  <span class="requeridoDato">*</span></label>
                                                    <input type="number" step="any" class="campoNumerico precioPublico" id="precioPublico" name="precioPublico" onkeyup ={__CalculoPrecioPublico} value="{articulo.precioPublico}"  autocomplete="off">
                                                </div>
                                            </div>    
                                            <div class="row">
                                                <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4 has-success">
                                                    <label class="tamanoLetraTotales">{$.i18n.prop("articulo.tipoImpuesto")}<span class="requeridoDato">*</span></label>
                                                    <select onchange= {__asignarImpuesto} class="campo selectTipoImpuesto" id="tipoImpuesto" name="tipoImpuesto"  >
                                                        <option  each={impuestos}  value="{codigo}" selected="{articulo.tipoImpuesto ==codigo?true:false}"  >{descripcion}</option>
                                                    </select>
                                                </div>
                                                <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4 has-success">
                                                    <label class="tamanoLetraTotales">{$.i18n.prop("articulo.codigoTarifa")}</label>
                                                    <select  onchange= {__AsignarTarifa} class="campo selectCodigoTarifa1" id="codigoTarifa" name="codigoTarifa"  >
                                                        <option  each={tarifas1.aaData}  value="{tarifaIVAI.codigoTarifa}" selected="{articulo.codigoTarifa ==tarifaIVAI.codigoTarifa?true:false}"  >{tarifaIVAI.descripcion}</option>
                                                    </select>
                                                </div>

                                                <div class= "col-md-4 col-sx-6 col-sm-4 col-lg-4 has-success">
                                                    <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.impuesto")}  </label>
                                                    <input type="number" step="any" class="campoNumerico impuesto" id="impuesto" name="impuesto" value="{articulo.impuesto}"  onkeyup ={__ActualizarPreciosImpuestos} autocomplete="off">
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
                                                <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                    <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.unidadMedida")}  <span class="requeridoDato">*</span></label>
                                                    <select  class="campo selecTipoUnidad has-success" name="unidadMedida" >
                                                        <option   each={tipoUnidades.aaData}  value="{codigo}"  selected="{articulo.unidadMedida ==codigo?true:false}" >{descripcion}</option>
                                                    </select>
                                                </div>
                                                <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                    <label class="tamanoLetraTotales">{$.i18n.prop("articulo.contable")}</label>
                                                    <select  class="campo" id="contable" name="contable" >
                                                        <option  each={contables}  value="{codigo}" selected="{articulo.contable ==codigo?true:false}" >{descripcion}</option>
                                                    </select>
                                                </div>
                                                <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                    <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.marca")}  <span class="requeridoDato">*</span></label>
                                                    <input type="text" class="form-control campoNumerico " id="descripcionMarca" name="descripcionMarca" value="{articulo.marca.descripcion}"  onclick={__ConsultaMarcas}  autocomplete="off">
                                                </div>
                                                <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                    <label  class="tamanoLetraTotales">{$.i18n.prop("articulo.pesoTransporte")} </label>
                                                    <input type="number" step="any" class="campoNumerico pesoTransporte" id="pesoTransporte" name="pesoTransporte" value="{articulo.pesoTransporte}" autocomplete="off" >
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.estado")}</label>
                                                    <select  class="campo" id="estado" name="estado"  >
                                                        <option  each={estados}  value="{codigo}" selected="{articulo.estado ==codigo?true:false}" >{descripcion}</option>
                                                    </select>
                                                </div>                          

                                            <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                <label class="tamanoLetraTotales">{$.i18n.prop("articulo.tipoCodigo")}<span class="requeridoDato">*</span></label>
                                                    <select  class="campo selectTipoCodigo" id="tipoCodigo" name="tipoCodigo"  >
                                                        <option  each={tipoCodigos}  value="{codigo}" selected="{articulo.tipoCodigo ==codigo?true:false}"  >{descripcion}</option>
                                                    </select>
                                                </div>
                                                <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                    <label  class="tamanoLetraTotales">{$.i18n.prop("articulo.consecutivoCompra")} </label>
                                                    <input type="text" step="any" class="campo form-control "  value="{articulo.consecutivoCompra}"  readonly>
                                                </div>
                                                <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                        <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.fechaUltimaCompra")} </label>
                                                        <input type="text" step="any" class="campo form-control "  value="{articulo.fechaUltimaCompraSTR}"  readonly>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class= "col-md-3 col-sx-3 col-sm-3 col-lg-3  has-success">
                                                    <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.updated_at")}  </label>
                                                    <input type="text" class="form-control campo"  value="{articulo.updated_atSTR}" readonly >
                                                </div>
                                            <!--     <div class="col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                                        <label class="tamanoLetraTotales">Facturar</label>
                                                        <select  class="form-control campo" id="tipoFacturar" name="tipoFacturar"  >
                                                            <option  each={tipoFacturar}  value="{codigo}" selected="{articulo.tipoFacturar ==codigo?true:false}" >{descripcion}</option>
                                                        </select>
                                                </div>    -->   
                                                 <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                                    <label  class="tamanoLetraTotales">Precio Sugerido </label>
                                                    <input type="number" step="any" class="campoNumerico precioSugerido" id="precioSugerido" name="precioSugerido" value="{articulo.precioSugerido}" autocomplete="off" >
                                                </div>
                                            </div>
                                        </div>
                                    </div>    
                                 </div>
                            </div>        
                        </form> 
                </div>
            </div>              
        <div>
    </div>


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
                                    <select  class="form-control" id="cantidad" name="cantidad" >
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

<!--Modal mostrar marcas -->
<div id='modalMarcas' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header with-border encabezado-pantalla " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> Lista de Marcas </h4>
            </div>
            <div class="modal-body">
                <table id="tableListarMarca" class="table responsive display table-striped table-hover nowrap tableListarMarca "  >
                    <thead>
                        <th class="table-header"  >{$.i18n.prop("listado.acciones")}</th>
                        <th class="table-header"  >{$.i18n.prop("articulo.descripcion")}</th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr class="headt">
                            <th style="width:5%"></th>
                            <th style="width:100%">{$.i18n.prop("articulo.descripcion")}</th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>        
        <div class="modal-footer">
            <button type="button" class="btn-dark-gray btn-back pull-left" data-dismiss="modal" >{$.i18n.prop("btn.volver")}</button>
        </div>
     </div>
    </div>
</div>

<!--Modal mostrar marcas -->
<div id='modalCategorias' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header with-border encabezado-pantalla " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> Lista de Categorias </h4>
            </div>
             <div class="modal-body">
                    <table id="tableListarCategoria" class="table responsive display table-striped table-hover nowrap   tableListarCategoria "  >
                        <thead>
                                <th class="table-header "  >{$.i18n.prop("listado.acciones")}</th>
                                <th class="table-header "  >{$.i18n.prop("articulo.descripcion")}</th>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr class="headt">
                                <th style="width:5%"></th>
                                <th style="width:100%">{$.i18n.prop("articulo.descripcion")}</th>
                            </tr>
                        </tfoot>
                    </table>
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
   height: 65%;
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
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del Listado de la Tabla 
    self.categorias                = {aaData:[]}
    self.marcas                    = {aaData:[]}
    self.tipoUnidades              = {aaData:[]}
    self.impuestos =[]
    self.tipoCodigos =[]
    self.tipoFacturar =[]
    self.contables                 = []
    self.estados                   = []
    self.botonModificar            = true
    self.botonAgregar              = false
    self.mostrarTituloArticulo     = true
    self.precioPantalla            = true
    
    self.impuestosIVAIPantalla     = false
    self.tabprecio = true
    self.tabImpuestos = false
    self.tabOtros = false
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
        tipoImpuesto1:'',
        codigoTarifa1:'',
        impuesto1:0,
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
    self.tarifas1    = {aaData:[]}
    self.tarifas2    = {aaData:[]}
     self.baseImponibles =[]
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
      self.estados   = __ComboEstados()
    self.comanda   = __ComboComanda()
    self.contables = __ComboContables()
    self.baseImponibles =__ComboBaseImponibles()
    self.update()
    setTimeout(__listadoTipoUnidadesActivas(function(resultado){
        self.tipoUnidades.aaData = resultado;
    })
     ,5000);

    


    self.impuestos = __ComboImpuestos()
    self.impuestosMag = __ComboImpuestosMaG()
    self.tipoCodigos =__CombotipoCodigo()
    self.tipoFacturar =___ComboTipoFacturarArticulo();
   
    self.update() 
    LimpiarArticulo()
    __ComboCantidades();
    __seleccionarCategorias()
   __seleccionarMarcas()
    $('.collapse').collapse("show")
    $('.codigo').focus()
    window.addEventListener( "keydown", function(evento){
        $(".errorServerSideJgrid").remove();
        teclas(evento);

        }, false );
})
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

 
__ConsultaCodigoCabys(e){
    __ListaDeHaciendaCabys()
}


__ConsultaCategorias(e){
 __listadoCategoriasActivas()
    
}

/**
*  Mostrar listado datatable Categorias activas
**/
function __listadoCategoriasActivas(){
     self.categorias                = {aaData:[]}
     self.informacion_tabla_categorias    = []
     self.update()
    $.ajax({
         url: "ListarCategoriasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.categorias.aaData =  result.aaData
                self.update();
                self.informacion_tabla_categorias =__informacionData_formato_categoria()
                self.update()
                loadListar(".tableListarCategoria",idioma_espanol,self.informacion_tabla_categorias,result.aaData)
                agregarInputsCombos_categorias()
                ActivarEventoFiltro(".tableListarCategoria")
                
                 $('#modalCategorias').modal('show')
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}

function agregarInputsCombos_categorias(){

    $('.tableListarCategoria tfoot th').each( function (e) {
        var title = $('.tableListarCategoria thead th').eq($(this).index()).text();

        if ( $(this).index() != 0    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}

function __seleccionarCategorias() {
     $('#tableListarCategoria').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListarCategoria').DataTable();
		if(table.row(this).child.isShown()){

	       var data = table.row(this).data();
	    }else{
	       var data = table.row($(this).parents("tr")).data();
	     }
        self.categoria = data
        self.articulo.categoria = data
        self.update();
        $('#modalCategorias').modal('hide')
    });

}

__ConsultaMarcas(e){
 __listadoMarcaActivas()
    
}
/**
*  Mostrar listado datatable marcas activas
**/
function __listadoMarcaActivas(){
     self.marcas                = {aaData:[]}
     self.informacion_tabla_marca    = []
     self.update()
    $.ajax({
         url: "ListarMarcasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.marcas.aaData =  result.aaData
                self.update();
                self.informacion_tabla_marca =__informacionData_formato_marca()
                self.update()
                loadListar(".tableListarMarca",idioma_espanol,self.informacion_tabla_marca,result.aaData)
                agregarInputsCombos_Marca()
                ActivarEventoFiltro(".tableListarMarca")
                
                 $('#modalMarcas').modal('show')
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}

function agregarInputsCombos_Marca(){

    $('.tableListarMarca tfoot th').each( function (e) {
        var title = $('.tableListarMarca thead th').eq($(this).index()).text();

        if ( $(this).index() != 0    ){
	      	$(this).html( '<input  type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}

function __seleccionarMarcas() {
     $('#tableListarMarca').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListarMarca').DataTable();
		if(table.row(this).child.isShown()){

	       var data = table.row(this).data();
	    }else{
	       var data = table.row($(this).parents("tr")).data();
	     }
        self.marca = data
        self.articulo.marca = data
        self.update();
        $('#modalMarcas').modal('hide')
    });

}

function __ListaDeHaciendaCabys(){
   // if( $('#descArticulo').val() =='' && $('.codigoCabysMod').val() =='' ){
   //     return
   // }
    var cantidadTemp = $('#cantidad').val() == 'Todos'?0:$('#cantidad').val()
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
    self.articulo.costo = costo
    self.articulo.precioPublico = precioPublico > 0 ? precioPublico : _ObtenerPrecio(costo,impuesto,0,gananciaPublica)
    self.articulo.gananciaPublica = gananciaPublica
    self.update()     
    $('#precioPublico').val(self.articulo.precioPublico)
    $('#gananciaPrecioPublico').val(self.articulo.gananciaPublica)

    

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



function teclas(tecla){
    if(tecla.keyCode ==27){
      _volverCampoCodigo()
      
    }

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

__AsignarTarifa(){
    self.articulo.impuesto = getMontoImpuesto(self.articulo.tipoImpuesto,$('#codigoTarifa').val(),self.tarifas1.aaData)
    self.update()
    $('#impuesto').val(self.articulo.impuesto)
    getPublico()
  
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
   if (typeof tipoImpuesto == 'undefined') {
        return
    }

    if(tipoImpuesto.length ==0){
        return 0
    }
    var valor = getMontoTarifa(tipoImpuesto,codigoTarifa,array);
    valor = valor !=null?valor[0]:null
    return valor == null?0:valor.monto
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
                    self.tarifas1 =  result
                    self.update()
                    self.articulo.impuesto = getMontoImpuesto(self.articulo.tipoImpuesto,$('#codigoTarifa').val(),self.tarifas1.aaData)
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
    $('.precioPublico').val(self.articulo.precioPublico1)


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
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
 __Consulta(e){
    self.tabprecio = true
    self.tabImpuestos = false
    self.tabOtros = false

    if (e.keyCode != 13) {
        return;
    } 
     var codigo = $('#codigo').val()
     
    var parametros = {
        codigo:$('.codigo').val(),
        precioPublico : $('.precioPublico').val()
    }
    $.ajax({
        url: "MostrarPorCodigoAjax",
        datatype: "json",
        data: parametros,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    serverMessageJsonClase(data);
                    self.botonModificar            = false
                    self.botonAgregar              = true
                    self.update()
                    sweetAlert("", data.message, "error");
                    _volverCampoCodigo()
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                    //desahabilita  listado 
                        LimpiarArticulo()
                        self.articulo = modeloTabla
                        if(self.articulo !=null){
                            self.botonModificar            = true
                            self.botonAgregar              = false
                            self.tabprecio = true
                            self.tabImpuestos = true
                            self.tabOtros = true

                        }
                        self.tarifas1    = {aaData:[]}
                        self.update()
                        $('.codigo').val(modeloTabla.codigo)
                        $('.descripcion').val(modeloTabla.descripcion)
                        $('.precioPublico').val(modeloTabla.precioPublico)
                        $('.impuesto').val(modeloTabla.impuesto)
                        $('.precioPublico').focus().select()
                        __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto,1)
                        __listadoTarifasByTipoImpuesto(self.articulo.tipoImpuesto1,2)
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
*volver al codigo
**/
function _volverCampoCodigo(){
    $('.codigo').select()
    $(".codigo").focus()   
    return 
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
		costo:"",
		impuesto:0,
        impuesto1:0,
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
    self.tarifas1    = {aaData:[]}
   self.tarifas2    = {aaData:[]}
   self.update() 
   $('.selectTipoImpuesto').prop("selectedIndex", 0);
   $('.selectTipoImpuesto1').prop("selectedIndex", 0);
   $('.selectTipoCodigo').prop("selectedIndex", 0);
   $('.selecTipoUnidad').prop("selectedIndex", 0);
  
   $("#unidadMedida").val($("#unidadMedida option:first").val()); 
   $("#contable").val($("#contable option:first").val()); 
   $('.codigo').val(null)
   $('.descripcion').val(null)
   $('.costo').val(0)
   $('.impuesto').val(0)
   $('.impuesto1').val(0)
   $('.precioPublico').val(0)
   $('.gananciaPrecioPublico').val(0)
   $(".errorServerSideJgrid").remove();
   $("#formulario").validate(reglasDeValidacion());
   enviarCargarCombos()
   _volverCampoCodigo()
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
  
}
/**
*imprimir el codigo y precio
**/
__Imprimir(){
   if(self.articulo.id == null){
       return
   }
   location.href = "PDFGondolaAjax.do?idArticulo=" + self.articulo.id
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
               
			},                                                
            descripcionCategoria : {
				required : true,
			},     
                                                      
            codigoCabys : {
				required : true,
			},                                                
            descripcionMarca : {
				required : true,
			},                                                
            unidadMedida : {
				required : true,
			},                                                
            precioPublico : {
				required : true,
//                numeroMayorCero:true,
                number:true,
			} ,                                                
 		},
		ignore : []

	});
	return validationOptions;
};

/** Fin  funciones de inventario ----------------------------------------------------------------------------**/

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
       if ($("#formulario").valid()) {
        var tipo = $('#tipoImpuesto').val() == "Exento"?"":$('#tipoImpuesto').val()
        if(tipo == "07"){
                var baseImponible = $('#baseImponible').val()
                if(baseImponible == 0){
                   mensajeAdvertencia("Debe actualizar la base imponible debe ser Activo")   
                   _volverCampoCodigo()
                   return 
                }
                
        }
        if(tipo =="" ){
            var valorimpuesto = $('#impuesto').val() !=null?$('#impuesto').val():0
             if(valorimpuesto > 0 ){
                mensajeAdvertencia("No se puede agregar el articulo si no indica el tipo de impuesto")  
                _volverCampoCodigo() 
                return 

             }
        }
        var costoProducto =  __valorNumerico($('#costo').val())
        var precioPublico = __valorNumerico($('#precioPublico').val())
 
        
        if(costoProducto > precioPublico){
            mensajeAdvertencia("No se puede agregar el precio Publico es menor al costo")   
            _volverCampoCodigo()
            return 
        }
        self.articulo.id = null
        self.update()    

        var JSONDetalles = JSON.stringify( self.cabys );
         $('#datosCabys').val(JSONDetalles)
        
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
                                _volverCampoCodigo()
                            }
                            
                        } else {
                        	serverMessageJson(data);
                            mensajeToasExito(data.message) 
	                        $("#formulario").validate(reglasDeValidacion());
                            $(".errorServerSideJgrid").remove();
                            LimpiarArticulo()
                              __Eventos()
                            self.botonModificar            = true
                            self.botonAgregar              = false
                            self.update()

                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
                    }
                });
    }  else{
        mensajeAdvertencia("Falta ingresar datos del articulo que son obligatorios, verificar lo indicado en ROJO")
        _volverCampoCodigo()
        
          
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
                mensajeAdvertencia("Debe actualizar la base imponible debe ser Activo")
                _volverCampoCodigo()
                return 
            }
                
        }
         if(tipo =="" ){
            var valorimpuesto = $('#impuesto').val() !=null?$('#impuesto').val():0
             if(valorimpuesto > 0 ){
                mensajeAdvertencia("No se puede agregar el articulo si no indica el tipo de impuesto")
                _volverCampoCodigo()
                return 

             }
        }

     if(self.articulo.costo > self.articulo.precioPublico){
         mensajeAdvertencia("No se puede modificar el Articulo el precio Publico es menor al costo")
         _volverCampoCodigo()
            return 
        }

    self.error = false;
    self.exito = false;
    self.update();
    if ($("#formulario").valid()) {
        var formulario = $("#formulario").serialize();
        var JSONDetalles = JSON.stringify( self.cabys );
         $('#datosCabys').val(JSONDetalles)
        $.ajax({
            type : "POST",
            dataType : "json",
            data : formulario,
            url : "ModificarArticuloAjax.do",
            success : function(data) {
                if (data.status != 200) {
                    serverMessageJson(data);
                    if (data.message != null && data.message.length > 0) {
                        mensajeAdvertencia(data.message)
                        _volverCampoCodigo()
                    }
                } else {
                    serverMessageJson(data)
                    mensajeToasExito(data.message)
                    self.botonModificar = true
                    self.botonAgregar   = false
                    self.update()
                    _volverCampoCodigo()
                }
            },
            error : function(xhr, status) {
                 mensajeErrorServidor(xhr, status);
            }
        });
    }
    }  else{
        mensajeAdvertencia("Falta ingresar datos del articulo que son obligatorios para modificar, verificar lo indicado en ROJO")
        _volverCampoCodigo()
        
          
      }
    
}

function validarPrecios(){
    var impuesto  =  __valorNumerico($('#impuesto').val())
    var costo     =  __valorNumerico($('#costo').val())
    var precioPublico    =  __valorNumerico($('#precioPublico').val())
    var resultadoImpuesto =  impuesto 
    resultadoImpuesto = resultadoImpuesto > 0 ? resultadoImpuesto /100 : 0
    resultadoImpuesto = resultadoImpuesto > 0 ? resultadoImpuesto + 1 : 0
    var total = resultadoImpuesto > 0 ? precioPublico / resultadoImpuesto : precioPublico
    if(precioPublico > 0 && resultadoImpuesto > 0){
        if(total < costo ){
            mensajeAdvertencia('El Precio Publico es menor al Costo')
            _volverCampoCodigo()
            
        }

    }
   
}

</script>
</cambiar-precio>