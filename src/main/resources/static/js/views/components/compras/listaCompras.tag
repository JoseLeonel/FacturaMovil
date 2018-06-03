<lista-compras>
   <!-- Titulos -->
    <div  class="row "  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("compras.compras")} </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
        </div>
    </div>
    <br>
  <!-- Listado  -->
    <div classs="contenedor-listar "  show={mostrarListado} >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                <div class="box">
                    <div class="box-body">
                        <div class="planel-body" >
                            <div class="row" >        
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12" >
                                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar "   cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.proveedor")}      </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.consecutivo")}    </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.formaPago")}      </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.fecha.compra")}   </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.fecha.ingreso")}  </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.fecha.credito")}  </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.fecha.pago")}     </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.total")}          </th>
                                                <th class = "table-header" >{$.i18n.prop("compra.listado.estado")}         </th>
                                                <th class = "table-header" >{$.i18n.prop("listado.acciones")}              </th>
                                            </tr>
                                        </thead>
                                        <tfoot style="display: table-header-group;">
                                            <tr>
                                                <th>{$.i18n.prop("compra.listado.proveedor")}        </th>
                                                <th>{$.i18n.prop("compra.listado.consecutivo")}      </th>
                                                <th>{$.i18n.prop("compra.listado.formaPago")}        </th>
                                                <th>{$.i18n.prop("compra.listado.fecha.compra")}    </th>
                                                <th>{$.i18n.prop("compra.listado.fecha.ingreso")}    </th>
                                                <th>{$.i18n.prop("compra.listado.fecha.credito")}    </th>
                                                <th>{$.i18n.prop("compra.listado.fecha.pago")}       </th>
                                                <th>{$.i18n.prop("compra.listado.total")}            </th>
                                                <th>{$.i18n.prop("compra.listado.estado")}           </th>
                                                <th>                                                 </th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>   
                            </div>   
                        </div>    
                    </div>
                </div>
            </div>
            <div class="col-md-1 col-lg-1 "> </div>
        </div>
    </div>
    <!-- Fin del Listado -->


 <div class="box box-solid box-primary" show={mostrarCompra}>
        <div class="box-body">
             <div class="box-header with-border">
                 <h3 class="box-title">Modulo para Realizar Compras al Inventario {compra.id>0?' Id#:'+compra.id:'' }</h3>
                  <div class="box-tools pull-right">
                    <a href="#"    onclick = {__Regresar} title="Regresar al Listado"> <span class="label label-primary">Regresar</span></a>
                  </div>
             </div>
            <div  class="contenedor-compra " >
                <div class="cabecera-izquierda">
                    <form id="formularioCompra">
                        <div class="row">
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <input   type="hidden" class="form-control" id="proveedor" name="proveedor" value="{proveedor.id}">
                                    <label>{$.i18n.prop("compra.proveedor")}</label> 
                                    <input  type="text"  class="form-control"  value="{compra.proveedor.nombreCompleto}" readonly>
                                </div>
                            </div>
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.consecutivo")}</label> 
                                    <input type="text" class="form-control "  value="{compra.consecutivo}" readonly>
                                </div>
                            </div>
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.fecha.compra")}</label> 
                                    <input type="text" class="form-control "  value="{compra.fechaCompra}" readonly>
                                </div>
                            </div>

                        </div>    
                        <div class="row">
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.fecha.ingreso")}</label> 
                                    <input type="text"  class="form-control"  value="{compra.fechaIngreso}" readonly>
                                </div>
                            </div>
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.fecha.credito")}</label> 
                                    <input type="text"  class="form-control"  value="{compra.fechaCredito}" readonly>
                                </div>
                            </div>
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.tipo.documento")}</label> 
                                    <input type="text"  class="form-control"  value="{compra.descripcionTipoDocumento}" readonly>
                                </div>
                            </div>
                        </div>   
                        <div class="row">
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.forma.pago")}</label> 
                                    <input type="text"  class="form-control"  value="{compra.descripcionFormaPago}" readonly>
                                </div>
                            </div>
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.estado")}</label> 
                                    <input type="text"  class="form-control"  value="{compra.descripcionEstado}" readonly>
                                </div>
                            </div>
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.linea.detalle.subTotal")}</label> 
                                    <input type="text"  class="form-control"  value="{compra.subTotal}" readonly>
                                </div>
                            </div>
                        </div>   
                        <div class="row">
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.linea.detalle.descuento")}</label> 
                                    <input type="text"  class="form-control"  value="{compra.totalDescuento}" readonly>
                                </div>
                            </div>
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.linea.detalle.impuesto")}</label> 
                                    <input type="text"  class="form-control"  value="{compra.totalImpuesto}" readonly>
                                </div>
                            </div>
                            <div class= "col-md-4 col-sx-4 col-sm-4 col-lg-4">
                                <div class="form-group ">
                                    <label>{$.i18n.prop("compra.total")}</label> 
                                    <input type="text"  class="form-control"  value="{compra.totalCompra}" readonly>
                                </div>
                            </div>
                        </div>   

                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <div class="form-group ">
                                    <label >{$.i18n.prop("compra.nota")}</label> 
                                    <input type="text" class="form-control" id="nota" name="nota" value="{compra.nota}" readonly>
                                </div>
                            </div>
                        </div>
                    </form>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>{$.i18n.prop("compra.linea.detalle.linea")}                          </th>
                            <th>{$.i18n.prop("compra.linea.detalle.codigo")}                         </th>
                            <th style="width:20%;">{$.i18n.prop("compra.linea.detalle.descripcion")} </th>
                            <th >{$.i18n.prop("compra.linea.detalle.cantidad")}                      </th>
                            <th >{$.i18n.prop("compra.linea.detalle.costo")}                         </th>
                            <th >{$.i18n.prop("compra.linea.detalle.descuento")}                     </th>
                            <th >{$.i18n.prop("compra.linea.detalle.impuesto")}                      </th>
                            <th >{$.i18n.prop("compra.linea.detalle.subTotal")}                      </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td>{linea}</td>
                            <td>{codigo}</td>
                            <td>{descripcion}</td>
                            <td class="text-right">
                                <input  id= "cantidadDetalle" class="form-control " type="number" placeholder="Cantidad Detalle" value = "{cantidad}"  readonly/>
                            </td>
                            <td class="text-right">
                                <input   class="form-control" type="text"  value = "₡ {costo}" readonly/>
                            </td>
                            <td class="text-right">
                                <input   class="form-control" type="text"  value = "₡ {descuento}" readonly/>
                            </td>
                                                        
                            <td class="text-right">
                                <input  class="form-control" type="text"  value = "₡ {impuesto}" readonly/>
                            </td>

                            <td class="text-right">
                                <input  class="form-control" type="text"  value = "₡ {subTotal}" readonly/>
                            </td>
                        </tr>
                        </tbody>
                    </table>          
                </div>
                <section class="cabecera-derecha">
				    <!--right sidebar-->
                    <aside class="left-sidebar">
                            <!--Booking details-->
                        <article class="booking-details clearfix">
                            <div    onclick = {__MostrarFormularioDePago} id="btnGrandePagar" class="head green well" style="color: #fff; font-size: 12px;  padding-top:8px !important; padding-bottom:8px !important; margin-bottom: 8px;">
                                <table id="pagarTable" width="100%">
                                    <tbody>
                                        <tr>
                                            <td width="30%" id="">
                                                <div id="pagarTitulo">{$.i18n.prop("compra.linea.detalle.subTotal")}</div>
                                            </td>
                                            <td width="70%" id="">
                                            
                                                <div id="">
                                                    <span class="label label-info textShadow" id="total-show">₡ {compra.subTotal}</span>
                                                </div>
                                            </td>
                                        </tr>                                            
                                        <tr>
                                            <td width="30%" id="">
                                                <div id="pagarTitulo">{$.i18n.prop("compra.linea.detalle.descuento")}</div>
                                            </td>
                                            <td width="70%" id="">
                                            
                                                <div id="">
                                                    <span class="label label-info textShadow" id="total-show">₡ {compra.totalDescuento}</span>
                                                </div>
                                            </td>
                                        </tr>                                            
                                        <tr>
                                            <td width="30%" id="">
                                                <div id="pagarTitulo">{$.i18n.prop("compra.linea.detalle.impuesto")}</div>
                                            </td>
                                            <td width="70%" id="">
                                            
                                                <div id="">
                                                    <span class="label label-info textShadow" id="total-show">₡ {compra.totalImpuesto}</span>
                                                </div>
                                            </td>
                                        </tr>         
                                        <tr>
                                            <td width="30%" id="">
                                                <div id="pagarTitulo">{$.i18n.prop("compra.total")}</div>
                                            </td>
                                            <td width="70%" id="">
                                            
                                                <div id="">
                                                    <span class="label label-info textShadow" id="total-show">₡ {compra.totalCompra}</span>
                                                </div>
                                            </td>
                                        </tr>                     
                                    </tbody>
                                </table>
                            </div>
                        </article>
                    </aside>
                    <section   class="lista-compras-espera">
                        <div id="botones"  each={compras_espera.data}  onclick={__CargarCompraEspera}>
                            <a href="#" class="compras-espera"  title="{proveedor !=null?proveedor.nombreCompleto:""}">C# {id}</a>
                        </div>    
                     </section >

                </section>
                      
            </div><!-- fin contenedor-compra-->
            
        </div><!-- fin box-body-->
	</div><!-- fin box -->


 


<style type ="text/css">
    .wrap{
        max-width:100%;
        width:100%;
    }
    body {
        overflow: hidden;
        background:white;
        font-size: 14px !important;
        margin:0; 
        padding:0; 
        height:100%
    }
    .contenedor-listar{
        width:100%;
        max-width:1100px;
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

    /* Lista de facturas en espera*/
    .cabecera-derecha .lista-compras-espera{
        width:100%;
        display:flex;
        flex-wrap:wrap;
    }
    .cabecera-derecha .lista-compras-espera .compras-espera{
        display:block;
        width:90%;
        padding:6px 0;
        margin-bottom:20px;
        margin-left:15px;
        margin-right:5px;
        background:red;
        text-align:center;
        text-decoration:none;
        color:#ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
    }
    #pagarTitulo{
        font-weight: 600 !important;
        font-size: 16px !important;
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
    #total-show {
        padding: 0px;
        font-weight: 400;
        background: none!important;
        font-size: 16px;
        color: #ffffff !important;
        text-shadow: 0px 0px 1px #ffffff;
        padding-top: 0px;
        line-height: 40px;
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
        font-size: 16px !important;
    }
    #pagarInfo{
        font-size: 16px !important;
    }
    *{
       margin:0;
       padding:0;
       box-sizing:border-box;
    }
    body{
        background:white;
    }
    .wrap{
        max-width:1100px;
        width:100%;
        margin:auto;
    }
    .contenedor-compra {
        display:flex;
        width:100%;
        margin :auto;
    }
    .cabecera-izquierda {
       margin-right:5%;
       width:85%;
    }

    .cabecera-derecha {
        width:25%;
    }
    .contenedor-detalle   {
        display:flex;
        width:100%;
        margin :auto;
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
    .total{
        font-weight:bold;
        font-size:20px;
    }
    .precioTotalFactura{
        font-weight:bold;
        font-size:20px;
        color: #0C9C22;
        border-top: 1px solid #DFDCD1;
        padding: 0 0 5px;
        padding: 15px 0 0;
        margin: 10px 0 0;
    }
    label {
        display: inline-block;
        max-width: 100%;
        margin-bottom: 5px;
        font-weight: 600;
    }
    
</style>

<script>
self = this
self.formato_tabla         = []
self.detail                = []
self.compra                = {
        consecutivo:"",
        fechaCredito    : null,
        fechaCompra     : null,
        id : 0,
        totalImpuesto: 0,
        totalCompra:0,
        estado:0,
        tipoDocumento:0,
        formaPago:0,
        totalDescuento:0,
        subTotal:0,  
        total:0,
        nota:""
    }              
self.detalleCompra         ={data:[]}    
self.mostrarCompra         = false
self.mostrarListado        = true
self.on('mount',function(){
    __InformacionDataTable()
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    __listado()

})

/**
 * Regresar al listado
 * **/
__Regresar(){
    self.detail                = []
    self.compra                = {
            consecutivo:"",
            fechaCredito    : null,
            fechaCompra     : null,
            id : 0,
            totalImpuesto: 0,
            totalCompra:0,
            estado:0,
            tipoDocumento:0,
            formaPago:0,
            totalDescuento:0,
            subTotal:0,  
            total:0,
            nota:""
        }              

    self.mostrarCompra         = false
   self.mostrarListado        = true
   self.update()
}

/** Listado de inventario **/  
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    __InicializarTabla('.tableListar')  
    $.ajax({
        url: "ListarComprasAjax",
        datatype: "json",
        method:"GET",
        success: function (result) {
            console.log(result)
             if(result.aaData.length > 0){
                __InformacionDataTable();
                loadListar(".tableListar",idioma_espanol,self.formato_tabla,result.aaData)
                agregarInputsCombos();
                ActivarEventoFiltro(".tableListar")
                __MostrarCompra()

             }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
}


  
/**
*Formato del listado 
**/
function __InformacionDataTable(){
    self.formato_tabla = [ 
                               {'data' :'proveedor'   ,"name":"proveedor"    ,"title" : $.i18n.prop("compra.listado.proveedor")     ,"autoWidth" :true ,
                                "render":function(proveedor,type, row){
                                        return row.proveedor !=null?row.proveedor.nombreCompleto:"Sin Asociar";
                                    }
                               },
                               {'data' :'consecutivo'                ,"name":"consecutivo"                 ,"title" : $.i18n.prop("compra.listado.consecutivo")   ,"autoWidth" :true },
                               {'data' :'descripcionFormaPago'       ,"name":"descripcionFormaPago"        ,"title" : $.i18n.prop("compra.listado.formaPago")     ,"autoWidth" :true },
                               {'data' :'fechaCompra'                ,"name":"fechaCompra"                 ,"title" : $.i18n.prop("compra.listado.fecha.compra")  ,"autoWidth" :true ,
                                    "render":function(fechaCompra,type, row){
									    return __displayDate_detail(fechaCompra);
	 							    }
                               },
                               {'data' :'fechaIngreso'               ,"name":"fechaIngreso"                ,"title" : $.i18n.prop("compra.listado.fecha.ingreso") ,"autoWidth" :true ,
                                    "render":function(fechaIngreso,type, row){
									    return fechaIngreso !=null?formatoFechaHora(fechaIngreso):null;
	 							    }
                               
                               },
                               {'data' :'fechaCredito'               ,"name":"fechaCredito"                ,"title" : $.i18n.prop("compra.listado.fecha.credito") ,"autoWidth" :true ,
                                    "render":function(fechaCredito,type, row){
									    return fechaCredito !=null? __displayDate_detail(fechaCredito):null;
	 							    }
                               
                               },
                               {'data' :'fechaPago'                  ,"name":"fechaPago"                   ,"title" : $.i18n.prop("compra.listado.fecha.pago")    ,"autoWidth" :true ,
                                    "render":function(fechaPago,type, row){
									    return fechaPago !=null?__displayDate_detail(fechaPago):null;
	 							    }
                               
                               },
                               {'data' :'totalCompra'         ,"name":"totalCompra"          ,"title" : $.i18n.prop("compra.listado.total")         ,"autoWidth" :true ,
                                    "render":function(totalCompra,type, row){
                                        return  "₡" + totalCompra.toLocaleString('de-DE');
                                    }
                               },
                               {'data' :'descripcionEstado'          ,"name":"descripcionEstado"           ,"title" : $.i18n.prop("compra.listado.estado")        ,"autoWidth" :true },
                               {'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}

/**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
    let menu = '<div class="dropdown">' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    
    menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
     menu += "</ul></div>"  

     return menu;          
}


function __MostrarCompra(){
	$('#tableListar').on('click','.btnMostrar',function(e){
        var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        
        self.compra  = data;
        self.compra.fechaPago = self.compra.fechaPago !=null?__displayDate_detail(self.compra.fechaPago):null;
        self.compra.fechaCredito = self.compra.fechaCredito !=null?__displayDate_detail(self.compra.fechaCredito):null;
        self.compra.fechaCompra = self.compra.fechaCompra !=null?__displayDate_detail(self.compra.fechaCompra):null;
        self.compra.fechaIngreso  =self.compra.fechaIngreso !=null?formatoFechaHora(self.compra.fechaIngreso):null;
        self.update()
        cargarDetallesCompra()
	});
}

/**
*  Cargar detalles Compra Espera
**/
function cargarDetallesCompra(){
    self.detail = []
    self.update()
    self.compra.detalleCompras.forEach(function(e){
        self.detail.push({
            linea           : e.numeroLinea,
            articulo_id     : e.articulo.id,
            codigo          : e.articulo.codigo,
            descripcion     : e.articulo.descripcion,
            cantidad        : parseFloat(e.cantidad),
            costo           : parseFloat(e.costo),
            impuesto        : e.impuesto,
            descuento       : e.descuento,
            subTotal        : parseFloat(e.subTotal)
        });
    })
    self.mostrarCompra         = true
    self.mostrarListado        = false
    self.update()
}


/**
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY ');
}

function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 9    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 

</script>
</lista-compras>