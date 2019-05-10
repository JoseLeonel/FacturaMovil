<articulo-crud>
<div>
    <div class="row center " show ={mostrarFormulario} >
        <div class="col-md-12 col-lg-12 col-sm-12 col-sx-12 col-sm-12">
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
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.created_at")}  </label>
                                <input type="text" class="form-control "  value="{articulo.created_atSTR}" readonly >
                            </div>
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.updated_at")}  </label>
                                <input type="text" class="form-control "  value="{articulo.updated_atSTR}"  readonly>
                            </div>
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.categoria")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control selectCategoria"   name="categoria" >
                                    <option  each={categorias.aaData}  value="{id}" selected="{articulo.categoria.id ==id?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.codigo")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control codigo" id="codigo" name="codigo" value="{articulo.codigo}"  >
                            </div>
                        </div>    
                        <div class="row">
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.descripcion")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control descripcion" id="descripcion" name="descripcion" value="{articulo.descripcion}"  >
                            </div>
                            <div class="col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label >{$.i18n.prop("articulo.tipoImpuesto")}</label>
                                <select onchange= {__asignarImpuesto} class="form-control selectTipoImpuesto" id="tipoImpuesto" name="tipoImpuesto"  >
                                    <option  each={impuestos}  value="{codigo}" selected="{articulo.tipoImpuesto ==codigo?true:false}"  >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.impuesto")}  </label>
                                <input type="number" step="any" class="form-control impuesto" id="impuesto" name="impuesto" value="{articulo.impuesto}"  onkeyup ={__ActualizarPreciosImpuestos}>
                            </div>
                            <div class="col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label >{$.i18n.prop("articulo.tipoImpuesto1")}</label>
                                <select onchange= {__asignarImpuesto1} class="form-control selectTipoImpuesto1" id="tipoImpuesto1" name="tipoImpuesto1"  >
                                    <option  each={impuestos}  value="{codigo}" selected="{articulo.tipoImpuesto1 ==codigo?true:false}"  >{descripcion}</option>
                                </select>
                            </div>
                        </div>    
                        <div class="row">
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.impuesto1")}  </label>
                                <input type="number" step="any" class="form-control impuesto1" id="impuesto1" name="impuesto1" value="{articulo.impuesto1}"  onkeyup ={__ActualizarPreciosImpuestos1}>
                            </div>
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.marca")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control selectMarca"  name="marca">
                                    <option  each={marcas.aaData}  value="{id}" selected="{articulo.marca.id ==id?true:false}"  >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.unidadMedida")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control selecTipoUnidad has-success" name="unidadMedida" >
                                    <option   each={tipoUnidades.aaData}  value="{codigo}"  selected="{articulo.unidadMedida ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                            <div class="col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label >{$.i18n.prop("articulo.contable")}</label>
                                <select  class="form-control" id="contable" name="contable" >
                                    <option  each={contables}  value="{codigo}" selected="{articulo.contable ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        </div>    
                        <div class="row">
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.serie")}  </label>
                                <input type="text" class="form-control serie" id="serie" name="serie" value="{articulo.serie}" >
                            </div>
                            <div class="col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label >{$.i18n.prop("articulo.tipoCodigo")}</label>
                                <select  class="form-control selectTipoCodigo" id="tipoCodigo" name="tipoCodigo"  >
                                    <option  each={tipoCodigos}  value="{codigo}" selected="{articulo.tipoCodigo ==codigo?true:false}"  >{descripcion}</option>
                                </select>
                            </div>
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("inventario.cantidad")} </label>
                                <input type="number" step="any" class="form-control cantidad" id="cantidad" name="cantidad" value="{articulo.cantidad}" readonly = "{articulo.id>0?true:false}" >
                            </div>                        
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.costo")} </label>
                                <input type="number" step="any" class="form-control costo" id="costo" name="costo" value="{articulo.costo}"  onkeyup ={__ActualizarPreciosCosto}>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.gananciaPrecioPublico")} % </label>
                                <input type="number" step="any" class="form-control gananciaPrecioPublico" id="gananciaPrecioPublico" name="gananciaPrecioPublico" value="{articulo.gananciaPrecioPublico}"  onkeyup ={__CalculoGananciaSinPrecioPublico}>
                            </div>
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.precioPublico")}  <span class="requeridoDato">*</span></label>
                                <input type="number" step="any" class="form-control precioPublico" id="precioPublico" name="precioPublico" onkeyup ={__CalculoGananciaPublico} value="{articulo.precioPublico}"  >
                            </div>
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.precioMayorista")}  </label>
                                <input type="number" step="any" class="form-control precioMayorista" id="precioMayorista" name="precioMayorista" value="{articulo.precioMayorista}" onkeyup={__CalculoGananciaMayorista} >
                            </div>  
                           <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.gananciaPrecioMayorista")} % </label>
                                <input type="number" step="any" class="form-control gananciaPrecioMayorista" id="gananciaPrecioMayorista" name="gananciaPrecioMayorista" value="{articulo.gananciaPrecioMayorista}"  >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.precioEspecial")}  </label>
                                <input type="number" step="any" class="form-control precioEspecial" id="precioEspecial" name="precioEspecial" value="{articulo.precioEspecial}"  onkeyup={__CalculoGananciaEspecial}>
                            </div>                        
                            <div class= "col-col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.gananciaPrecioEspecial")} % </label>
                                <input type="number" step="any" class="form-control gananciaPrecioEspecial" id="gananciaPrecioEspecial" name="gananciaPrecioEspecial" value="{articulo.gananciaPrecioEspecial}"  >
                            </div>
                            <div class= "col-md-3 col-sx-4 col-sm-3 col-lg-3 has-success">
                                <label  >{$.i18n.prop("inventario.minimo")} </label>
                                <input type="number" step="any" class="form-control minimo" id="minimo" name="minimo" value="{articulo.minimo}"  >
                            </div>
                            <div class= "col-md-3 col-sx-4 col-sm-4 col-lg-3 has-success">
                                <label  >{$.i18n.prop("inventario.maximo")} </label>
                                <input type="number" step="any" class="form-control maximo" id="maximo" name="maximo" value="{articulo.maximo}"  >
                            </div>
                        </div>
                        <div class="row">
                             <div class="col-md-3 col-sx-3 col-sm-3 col-lg-3 has-success">
                                <label >{$.i18n.prop("articulo.comanda")}</label>
                                <select  class="form-control" id="comanda" name="comanda"  >
                                    <option each={comanda}  value="{codigo}" selected="{articulo.comanda ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>                          
                            <div class= "col-md-3 col-sx-3 col-sm-4 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.prioridad")} </label>
                                <input type="number" step="any" class="form-control prioridad" id="prioridad" name="prioridad" value="{articulo.prioridad}"  >
                            </div>
                            <div class= "col-md-3 col-sx-3 col-sm-4 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.pesoTransporte")} </label>
                                <input type="number" step="any" class="form-control pesoTransporte" id="pesoTransporte" name="pesoTransporte" value="{articulo.pesoTransporte}"  >
                            </div>
                            <div class= "col-md-3 col-sx-3 col-sm-4 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.consecutivoCompra")} </label>
                                <input type="text" step="any" class="form-control "  value="{articulo.consecutivoCompra}"  readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-3 col-sx-3 col-sm-4 col-lg-3 has-success">
                                <label  >{$.i18n.prop("articulo.fechaUltimaCompra")} </label>
                                <input type="text" step="any" class="form-control "  value="{articulo.fechaUltimaCompra}"  readonly>
                            </div>
                             <div class="col-md-3 col-sx-12 col-sm-3 col-lg-3 has-success">
                                <label >{$.i18n.prop("articulo.estado")}</label>
                                <select  class="form-control" id="estado" name="estado"  >
                                    <option  each={estados}  value="{codigo}" selected="{articulo.estado ==codigo?true:false}" >{descripcion}</option>
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
                        <input type="hidden" name="IdArticulo" id="IdArticulo" value="{articulo.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")}</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="form-control" >{$.i18n.prop("articulo.codigo")}</label>
                                <input type="text" class="form-control"   value="{articulo.codigo}"   readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="form-control" >{$.i18n.prop("articulo.descripcion")}</label>
                                <input type="textArea" class="form-control" value="{articulo.descripcion}" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="form-control">{$.i18n.prop("kardex.motivos")} <span class="requeridoDato">*</span></label>
                                <select  class="form-control selectEntrada" id="motivo" name="motivo" data-live-search="true">
                                    <option data-tokens="{descripcion}" each={motivoEntradas.data}  value="{descripcion}"  >{descripcion}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="form-control" >{$.i18n.prop("kardex.cantidadActual")}</label>
                                <input type="number" step="any" class="form-control"  value="{articulo.cantidad}"  readonly >
                            </div>
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="form-control" >{$.i18n.prop("kardex.cantidadNueva")}<span class="requeridoDato">*</span> </label>
                                <input onkeyup= {MostrarBotonAgregarEntrada} type="number" step="any"  class="form-control cantidadNueva_entrada" id="cantidadNueva" name="cantidadNueva"   >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="form-control" > {$.i18n.prop("kardex.observacion")}<span class="requeridoDato">*</span></label>
                                <textarea class="form-control observacion_entrada " rows="5" id="observacion" name="observacion"></textarea>
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
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
        <div class="col-md-2 col-sx-12 col-sm-2 col-lg-2"></div>
        <div class="col-md-8 col-sx-12 col-sm-8 col-lg-8">
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
                                <label class="form-control" >{$.i18n.prop("articulo.codigo")}  </label>
                                <input type="text" class="form-control"  value="{articulo.codigo}"  readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="form-control" >{$.i18n.prop("articulo.descripcion")}</label>
                                <input type="text" class="form-control " value="{articulo.descripcion}"  readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="form-control">{$.i18n.prop("kardex.motivos")} <span class="requeridoDato">*</span></label>
                                <select  class="form-control selectMotivoSalida" id="motivo" name="motivo" data-live-search="true">
                                    <option data-tokens="{descripcion}"  each={motivoSalidas.data}  value="{descripcion}"  >{descripcion}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="form-control" >{$.i18n.prop("kardex.cantidadActual")} </label>
                                <input type="number" step="any" class="form-control" id="cantidadActual" name="cantidadActual" value="{articulo.cantidad}" disabled readonly >
                            </div>
                            <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="form-control" >{$.i18n.prop("kardex.cantidadDismunuir")}<span class="requeridoDato">*</span> </label>
                                <input onkeyup= {MostrarBotonAgregarSalida} type="number" step="any"  class="form-control cantidadNueva_salida cantidadNueva" id="cantidadNueva" name="cantidadNueva"   >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="form-control" >{$.i18n.prop("kardex.observacion")} <span class="requeridoDato">*</span></label>
                                <textarea class="form-control observacion_salida" rows="5" id="observacion" name="observacion"></textarea>
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
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
            text-align: left;
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
    self.parametros   = opts.parametros;  
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del Listado de la Tabla 
    self.categorias                = {aaData:[]}
    self.marcas                    = {aaData:[]}
    self.tipoUnidades              = {aaData:[]}
    self.motivoEntradas              = {data:[]}
    self.motivoSalidas              = {data:[]}
    self.impuestos =[]
    self.tipoCodigos =[]
    self.contables                 = []
    self.estados                   = []
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
        comanda:0,
        prioridad:1
    }    
   
    // variables para modulo de inventario 
    self.mostrarFormularioEntrada    = false
    self.mostrarFormularioSalida     = false
    self.mostrarBotonAgregarEntrada  = false
    self.mostrarBotonAgregarSalida   = false 
   

self.on('mount',function(){
    __Eventos()
    __ComboEstados()
    __ComboComanda()
    __ComboContables()
    __listadoTipoUnidadesActivas()   
    __listadoMarcasActivas()
    __Impuestos() 
    __tipoCodigo()
    LimpiarArticulo()
   //Agregar
      if(self.parametros.tipoEjecucion ==1){
          self.mostrarTituloArticulo     = true  
          self.mostrarFormulario = true
          self.botonAgregar = true
          self.update()
      }   
       //modificar
      if(self.parametros.tipoEjecucion ==2){
          self.mostrarTituloArticulo     = true  
          self.mostrarFormulario = true
          self.botonModificar = true
          self.mostrarFormulario  = true 
          self.botonModificar   = true;
          self.mostrarFormularioEntrada    = false
          self.botonAgregar     = false;            
          self.articulo  =  self.parametros.articulo
          self.update()
          $("#formulario").validate(reglasDeValidacion());           
      }  
      //Entrada
      if(self.parametros.tipoEjecucion ==3){
        $("#formularioEntrada").validate(reglasDeValidacionEntrada());
        $(".errorServerSideJgrid").remove();
        $(".observacion_entrada").val(null)
        $(".cantidadNueva_entrada").val(null)
        self.articulo  = self.parametros.articulo
        self.mostrarFormularioEntrada = true
        self.mostrarListado            = false 
        self.botonModificar            = false
        self.botonAgregar              = false
        self.mostrarFormularioEntrada    = true
        self.mostrarFormularioSalida     = false
        self.update()
        _ListaMotivoEntradasActivas()
      }
      //Salida
      if(self.parametros.tipoEjecucion ==4){
        $("#formularioSalida").validate(reglasDeValidacionSalida());
        $(".errorServerSideJgrid").remove();
        $(".observacion_salida").val(null)
        $(".cantidadNueva_salida").val(null)
        self.articulo  = self.parametros.articulo
        self.mostrarListado            = false 
        self.botonModificar            = false
        self.botonAgregar              = false
        self.mostrarTituloArticulo     = false
        // variables para modulo de inventario 
        self.mostrarFormularioEntrada    = false
        self.mostrarFormularioSalida     = true
        self.update()
        _ListaMotivoSalidasActivas()
      }
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
})
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
   $('.selectTipoImpuesto').prop("selectedIndex", 0);
   $('.selectTipoImpuesto1').prop("selectedIndex", 0);
   $('.selectTipoCodigo').prop("selectedIndex", 0);
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
   $('.impuesto').val(null)
   $('.impuesto1').val(null)
   $('.precioPublico').val(null)
   $('.gananciaPrecioPublico').val(null)
   $('.precioMayorista').val(null)
   $('.gananciaPrecioMayorista').val(null)
   $('.precioEspecial').val(null)
   $('.gananciaPrecioEspecial').val(null)
   $('.prioridad').val(1)
   $('.pesoTransporte').val(null)
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
                lettersOnly : true
			},
            codigo : {
				required : true,
                maxlength:20,
                minlength:1,
                lettersOnly : true
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
                            self.mostrarListado            = true
                            self.mostrarTituloArticulo     = true
                            self.botonModificar            = false
                            self.botonAgregar              = false
                            self.mostrarFormularioEntrada    = false
                            self.mostrarFormularioSalida     = false
                           self.update()
                           __mostrarListado()
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
                            self.mostrarListado            = true
                            self.mostrarTituloArticulo     = true
                            self.botonModificar            = false
                            self.botonAgregar              = false
                            self.mostrarFormularioEntrada    = false
                            self.mostrarFormularioSalida     = false
                            self.update()
                            __mostrarListado()
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
  let precio = __valorNumerico(e.target.value)
  if(precio == 0 ){
      return
  }
  let impuesto      = __valorNumerico($('#impuesto').val())
  let impuesto1      = __valorNumerico($('#impuesto1').val())
  let costo         = __valorNumerico($('#costo').val())
  self.articulo.gananciaPrecioMayorista  = _porcentajeGanancia(costo,impuesto,impuesto1,precio)
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
  let impuesto1 = __valorNumerico($('#impuesto1').val())
  let costo    = __valorNumerico($('#costo').val())
  self.articulo.gananciaPrecioEspecial  = _porcentajeGanancia(costo,impuesto,impuesto1,precio)
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
    self.articulo.gananciaPrecioEspecial   = self.articulo.precioEspecial > 0 ? _porcentajeGanancia(costo,impuesto,self.articulo.impuesto1,self.articulo.precioEspecial):0
    self.articulo.gananciaPrecioMayorista  = self.articulo.precioMayorista > 0 ? _porcentajeGanancia(costo,impuesto,self.articulo.impuesto1,self.articulo.precioMayorista):0
    self.articulo.gananciaPrecioPublico    = self.articulo.precioPublico > 0 ? _porcentajeGanancia(costo,impuesto,self.articulo.impuesto1,self.articulo.precioPublico):0
    self.update()
}
/**
* Actualizar ganancias al digitar el impuesto
**/
__ActualizarPreciosImpuestos1(e){
    let impuesto  = __valorNumerico(e.target.value)
    if(impuesto ==0){
        return
    }
    let costo     =  __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioEspecial   = self.articulo.precioEspecial > 0 ? _porcentajeGanancia(costo,self.articulo.impuesto,impuesto,self.articulo.precioEspecial):0
    self.articulo.gananciaPrecioMayorista  = self.articulo.precioMayorista > 0 ? _porcentajeGanancia(costo,self.articulo.impuesto,impuesto,self.articulo.precioMayorista):0
    self.articulo.gananciaPrecioPublico    = self.articulo.precioPublico > 0 ? _porcentajeGanancia(costo,self.articulo.impuesto,impuesto,self.articulo.precioPublico):0
    self.update()
}

/**
* Porcentaje de ganancia de Precio al Publico
**/
__CalculoGananciaPublico(e){
    var precioPublico = __valorNumerico(e.target.value)
    if(precioPublico ==0){
       return
    }
    var impuesto      = __valorNumerico($('#impuesto').val())
    var impuesto1     = __valorNumerico($('#impuesto1').val())
    var costo         = __valorNumerico($('#costo').val())
    if(precioPublico == costo){
        self.articulo.tipoImpuesto =$('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
        self.articulo.tipoImpuesto1 =$('#tipoImpuesto1').val() == "Sin impuesto"?"":$('#tipoImpuesto1').val()
        $('#tipoImpuesto').val("Sin impuesto")  
        $('#tipoImpuesto1').val("Sin impuesto")  
        self.articulo.impuesto = 0
        self.articulo.impuesto1 = 0
        impuesto = 0 
    }
    self.articulo.gananciaPrecioPublico  = _porcentajeGanancia(costo,impuesto,impuesto1,precioPublico)
    self.articulo.precioPublico = precioPublico
    self.update()
}
/**
* Porcentaje de ganancia de Precio al Publico
**/
__CalculoGananciaSinPrecioPublico(e){
    var ganancia = __valorNumerico(e.target.value)
    var impuesto   = __valorNumerico($('#impuesto').val())
    var impuesto1  = __valorNumerico($('#impuesto1').val())
    var costo      = __valorNumerico($('#costo').val())
    self.articulo.gananciaPrecioPublico  = ganancia
    self.articulo.precioPublico = _PrecioPublicoConGanancia(costo,impuesto,impuesto1,ganancia)
    self.update()
}
/**
* Asigna el impuesto 13 cuando es valor igual 01
**/
__asignarImpuesto(){
    if($('.selectTipoImpuesto').val()=="01"){
        self.articulo.tipoImpuesto ="01"
        self.articulo.impuesto = 13
        self.update()
        var resultado = __valorNumerico(self.articulo.impuesto1) + __valorNumerico(self.articulo.impuesto)
            resultado = resultado /100  
            resultado = 1 + resultado 
            if(self.articulo.precioPublico > self.articulo.costo){
                self.articulo.gananciaPrecioPublico = self.articulo.precioPublico >0?_porcentajeGanancia(self.articulo.costo,__valorNumerico(self.articulo.impuesto),__valorNumerico(self.articulo.impuesto1),self.articulo.precioPublico):0
            }else{
                self.articulo.precioPublico = __valorNumerico(redondeoDecimales(self.articulo.precioPublico * resultado,8)); 
                self.articulo.gananciaPrecioPublico = 0
            }
            if(self.articulo.precioEspecial > self.articulo.costo){
                self.articulo.gananciaPrecioEspecial = self.articulo.precioEspecial >0?_porcentajeGanancia(self.articulo.costo,__valorNumerico(self.articulo.impuesto),__valorNumerico(self.articulo.impuesto1),self.articulo.precioEspecial):0
            }else{
                self.articulo.precioEspecial = __valorNumerico(redondeoDecimales(self.articulo.precioEspecial * resultado,8)); 
                self.articulo.gananciaPrecioEspecial = 0
            }
            if(self.articulo.precioMayorista > self.articulo.costo){
                self.articulo.gananciaPrecioMayorista = self.articulo.precioMayorista >0?_porcentajeGanancia(self.articulo.costo,__valorNumerico(self.articulo.impuesto),__valorNumerico(self.articulo.impuesto1),self.articulo.precioMayorista):0
            }else{
                self.articulo.precioMayorista = __valorNumerico(redondeoDecimales(self.articulo.precioMayorista * resultado,8)); 
                self.articulo.gananciaPrecioMayorista = 0
            }
            self.update()   
    }else{
         $('.impuesto').val(null)
        self.articulo.tipoImpuesto =$('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
    
         self.update()
    }
    ActualizarPrecios()
}
/**
* Actualizar los precios
**/
function ActualizarPrecios(){
    var resultado = __valorNumerico(self.articulo.impuesto1) + __valorNumerico(self.articulo.impuesto)
        resultado = resultado /100  
        resultado = resultado == 0 ?0:1 + resultado 
        if(self.articulo.precioPublico > 0 && self.articulo.gananciaPrecioPublico == 0 ){
           self.articulo.precioPublico =  __valorNumerico(redondeoDecimales(self.articulo.precioPublico / resultado,8));               
        }
        if(self.articulo.precioMayorista > 0 && self.articulo.gananciaPrecioMayorista == 0 ){
           self.articulo.precioMayorista =  __valorNumerico(redondeoDecimales(self.articulo.precioMayorista / resultado,8));               
        }
        if(self.articulo.precioEspecial > 0 && self.articulo.gananciaPrecioEspecial == 0 ){
           self.articulo.precioEspecial =  __valorNumerico(redondeoDecimales(self.articulo.precioEspecial / resultado,8));               
        }

        if(self.articulo.precioPublico > self.articulo.costo){
            self.articulo.gananciaPrecioPublico = self.articulo.precioPublico >0?_porcentajeGanancia(self.articulo.costo,__valorNumerico(self.articulo.impuesto),__valorNumerico(self.articulo.precioPublico)):0
        }else{
            self.articulo.gananciaPrecioPublico = 0
        }
         if(self.articulo.precioMayorista > self.articulo.costo){
            self.articulo.gananciaPrecioMayorista = self.articulo.precioMayorista >0?_porcentajeGanancia(self.articulo.costo,__valorNumerico(self.articulo.impuesto),__valorNumerico(self.articulo.precioMayorista)):0
        }else{
            self.articulo.gananciaPrecioMayorista = 0
        }
         if(self.articulo.precioEspecial > self.articulo.costo){
            self.articulo.gananciaPrecioEspecial = self.articulo.precioEspecial >0?_porcentajeGanancia(self.articulo.costo,__valorNumerico(self.articulo.impuesto),__valorNumerico(self.articulo.precioEspecial)):0
        }else{
            self.articulo.gananciaPrecioEspecial = 0
        }

}
/**
* Asignar el Impuesto
**/
__asignarImpuesto1(){
    if($('.selectTipoImpuesto1').val()=="01"){
        self.articulo.tipoImpuesto1 ="01"
        self.articulo.impuesto1 = 13
        self.update()
        var resultado = __valorNumerico(self.articulo.impuesto1) + __valorNumerico(self.articulo.impuesto)
            resultado = resultado /100  
            resultado = 1 + resultado 
            if(self.articulo.precioPublico > self.articulo.costo){
                self.articulo.gananciaPrecioPublico = self.articulo.precioPublico >0?_porcentajeGanancia(self.articulo.costo,__valorNumerico(self.articulo.impuesto),__valorNumerico(self.articulo.impuesto1),self.articulo.precioPublico):0
            }else{
                self.articulo.precioPublico = __valorNumerico(redondeoDecimales(self.articulo.precioPublico * resultado,8)); 
                self.articulo.gananciaPrecioPublico = 0
            }
            if(self.articulo.precioEspecial > self.articulo.costo){
                self.articulo.gananciaPrecioEspecial = self.articulo.precioEspecial >0?_porcentajeGanancia(self.articulo.costo,__valorNumerico(self.articulo.impuesto),__valorNumerico(self.articulo.impuesto1),self.articulo.precioEspecial):0
            }else{
                self.articulo.precioEspecial = __valorNumerico(redondeoDecimales(self.articulo.precioEspecial * resultado,8)); 
                self.articulo.gananciaPrecioEspecial = 0
            }
            if(self.articulo.precioMayorista > self.articulo.costo){
                self.articulo.gananciaPrecioMayorista = self.articulo.precioMayorista >0?_porcentajeGanancia(self.articulo.costo,__valorNumerico(self.articulo.impuesto),__valorNumerico(self.articulo.impuesto1),self.articulo.precioMayorista):0
            }else{
                self.articulo.precioMayorista = __valorNumerico(redondeoDecimales(self.articulo.precioMayorista * resultado,8)); 
                self.articulo.gananciaPrecioMayorista = 0
            }

            self.update()   
    }else{
        $('.impuesto1').val(null)
        self.articulo.tipoImpuesto1 =$('#tipoImpuesto1').val() == "Sin impuesto"?"":$('#tipoImpuesto1').val()
        self.update()
    }
    ActualizarPrecios()
}
/**
* Actualizar el precio costo
**/
__ActualizarPreciosCosto(e){
    let costo    = __valorNumerico(e.target.value)
    let impuesto =  __valorNumerico($('#impuesto').val())
    let impuesto1 =  __valorNumerico($('#impuesto1').val())
    self.articulo.costo = costo 
    self.articulo.gananciaPrecioEspecial   = self.articulo.precioEspecial > 0?_porcentajeGanancia(costo,impuesto,impuesto1,self.articulo.precioEspecial):0
    self.articulo.gananciaPrecioMayorista  = self.articulo.precioMayorista>0?_porcentajeGanancia(costo,impuesto,impuesto1,self.articulo.precioMayorista):0
    self.articulo.gananciaPrecioPublico    = self.articulo.precioPublico >0?_porcentajeGanancia(costo,impuesto,impuesto1,self.articulo.precioPublico):0
    self.update()
    _CalculoPrecio(costo,impuesto,impuesto1)
}
/**
* Calculo de precio
**/
function _CalculoPrecio(costo,impuesto,impuesto1){
    self.articulo.precioPublico = _PrecioPublicoConGanancia(costo,impuesto,impuesto1,self.articulo.gananciaPrecioPublico)
    self.articulo.precioMayorista = _PrecioPublicoConGanancia(costo,impuesto,impuesto1,self.articulo.gananciaPrecioMayorista)
    self.articulo.precioEspecial = _PrecioPublicoConGanancia(costo,impuesto,impuesto1,self.articulo.gananciaPrecioEspecial)
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
function __ComboComanda(){
    self.comanda =[]
    self.update()
    self.comanda.push({
        codigo: $.i18n.prop("combo.comanda.no.valor"),
        descripcion: $.i18n.prop("combo.comanda.no")
     });
    self.comanda.push({
        codigo: $.i18n.prop("combo.comanda.si.valor"),
        descripcion:$.i18n.prop("combo.comanda.si")
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
* Combo para verificar si es contabilizado en el inventario o no
**/
function __Impuestos(){
    self.impuestos =[]
    self.update()
     self.impuestos.push({
        codigo: "",
        descripcion:"Sin impuesto"
     });

    self.impuestos.push({
        codigo: '01',
        descripcion:$.i18n.prop("tipo.impuesto.ventas")
     });
      self.impuestos.push({
        codigo: '02',
        descripcion:$.i18n.prop("tipo.impuesto.consumo")
     });
    self.impuestos.push({
        codigo: '07',
        descripcion:$.i18n.prop("tipo.impuesto.servicio")
     });
     self.impuestos.push({
        codigo: '06',
        descripcion:$.i18n.prop("tipo.impuesto.tabaco")
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
* Tipo codigo del producto/servicio del articulo
**/
function __tipoCodigo(){
    self.tipoCodigos =[]
    self.update()
    self.tipoCodigos.push({
        codigo: '01',
        descripcion:$.i18n.prop("articulo.tipo.codigo.vendedor")
     });

    self.tipoCodigos.push({
        codigo: '02',
        descripcion:$.i18n.prop("articulo.tipo.codigo.comprador")
     });
    self.tipoCodigos.push({
        codigo: '03',
        descripcion:$.i18n.prop("articulo.tipo.codigo.asignado.por.industria")
     });
    self.tipoCodigos.push({
        codigo: '04',
        descripcion:$.i18n.prop("articulo.tipo.codigo.uso.interno")
     });
    self.tipoCodigos.push({
        codigo: '99',
        descripcion:$.i18n.prop("articulo.tipo.codigo.otros")
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
    LimpiarArticulo()
}
/**
*   Agregar 
**/
__agregar(){
        if ($("#formulario").valid()) {
             var tipo = $('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
            if (tipo !=""){
                if($('#impuesto').val()==0){
                    mensajeError($.i18n.prop("error.articulo.indicar.tipo.impuesto"))
                    return 
                }
            }else{
                if($('#impuesto').val()>0){
                    mensajeError($.i18n.prop("error.articulo.no.tipo.impuesto"))
                    return 
            }
        }
        if(self.articulo.costo > self.articulo.precioPublico){
            mensajeError("No se puede agregar el precio Publico es menor al costo")
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
}
/**
** Modificar la Empresa
**/
__Modificar(){
    var tipo = $('#tipoImpuesto').val() == "Sin impuesto"?"":$('#tipoImpuesto').val()
    if (tipo !=""){
        if($('#impuesto').val()==0){
            mensajeError($.i18n.prop("error.articulo.indicar.tipo.impuesto"))
            return 
        }
    }else{
        if($('#impuesto').val()>0){
            mensajeError($.i18n.prop("error.articulo.no.tipo.impuesto"))
            return 
        }
    }
    if(self.articulo.costo > self.articulo.precioPublico){
            mensajeError("No se puede modificar el Articulo el precio Publico es menor al costo")
            return 
        }
    self.error = false;
    self.exito = false;
    self.update();
    __modificarRegistro("#formulario",$.i18n.prop("articulo.mensaje.alert.modificar"),'ModificarArticuloAjax.do','ListarArticuloAjax.do','#tableListar')
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
</articulo-crud>