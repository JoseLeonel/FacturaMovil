<nota-credito>
 <!-- Titulos -->
    <div  class="row titulo-encabezado" show="{mostrarCrearNota == true}" >
        <div  class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h1 ><i class="fa fa-calculator"></i>&nbsp { parametros.tipoEjecucion ==2 ?"Nota de Debito":"Nota de Credito"}   </h1>
        </div>
        <div class=" col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
<!--validar rol de usuario-->

<!---Datos Final cuando no es un venta de Crucero -->
<div show={mostrarFormularioPago}>
		<div class="row " >
			<div class="col-md-8 col-sm-8 col-lg-8 col-sx-12 ">
				<div class="box">
					<div class="box-header with-border fondoEncabezado">
						<h3 class="box-title">{parametros.tipoEjecucion ==2 ?"Nota de Debito":"Nota de Credito"} </h3>
					</div>
					<div class="box-body">
                        <form id="formularioFactura">
                            <div class="row">
                                <div class= "col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                    <div class="row">
                                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                            <div class="form-group ">
                                                <label for="pago_tipoVentaL">{$.i18n.prop("factura.tipo.documento")} </label> 
                                                <select class="form-control tipoDoc" id="tipoDoc" name="tipoDoc"   >
                                                    <option each={comboTipoDocumentos} value="{estado}" selected="{factura.tipoDoc ==estado?true:false}" >{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                            <div class="form-group ">
                                                <label for="pago_tipoVentaL">Aplica Inventario </label> 
                                                <select class="form-control rebajaInventario" id="rebajaInventario" name="rebajaInventario"   >
                                                    <option each={comboRebajaInventario} value="{estado}"  >{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                            <div class="form-group ">
                                                <label for="pago_tipoVentaL">Codigo Aplicar </label> 
                                                <select class="form-control referenciaCodigo" id="referenciaCodigo" name="referenciaCodigo"   >
                                                    <option each={comboCodigosReferencia} value="{estado}"  >{descripcion}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group ">
                                        <label >Motivo </label> 
                                        <input type="text" class="campo nota" id="nota" name="nota" value="">
                                    </div>
                                </div>
                                <div  class= "col-md-6 col-sx-6 col-sm-6 col-lg-6" >
                                    <div class="form-group ">
                                        <label class="{labelTotales}">{$.i18n.prop("factura.resumen.efectivo")} </label> 
                                        <input onclick={_SeleccionarEfectivo}   onkeyup={ __TotalDeEfectivoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control totalEfectivo " id="totalEfectivo" name="totalEfectivo" value="{factura.totalEfectivo}" >
                                    </div>
                                    <div  class="form-group ">
                                        <label class="{labelTotales}">{$.i18n.prop("factura.resumen.tarjeta")}  </label> 
                                        <input onclick={_SeleccionarTarjeta} onkeyup={ __TotalDeTarjetaAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control totalTarjeta" id="totalTarjeta" name="totalTarjeta"  value="{factura.totalTarjeta}" >
                                    </div> 
                                    <div  class="form-group " >
                                        <label class="{labelTotales} ">{$.i18n.prop("factura.resumen.banco")} </label> 
                                        <input onclick={_SeleccionarBanco} onkeyup={ __TotalDeBancoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number"  onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control  totalBanco"  id="totalBanco" name="totalBanco"  value="{factura.totalBanco}" >
                                    </div>
                                </div>
                            </div>
                            
                            <input type="hidden" id='referenciaTipoDoc' name='referenciaTipoDoc'  value="03" >
                            <input type="hidden" id='referenciaNumero' name='referenciaNumero'  value="{factura.numeroConsecutivo}" >
                            <input type="hidden" id='estado'                  name='estado'                  value="{factura.estado}" >
                            <input type="hidden" id='subTotal'                name='subTotal'                value="{factura.subTotal}" >
                            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
                            <input type="hidden" id='totalComprobante'        name='totalComprobante'        value="{factura.totalComprobante}" >
                            <input type="hidden" id='totalMercanciasGravadas' name='totalMercanciasGravadas' value="{factura.totalMercanciasGravadas}" >
                            <input type="hidden" id='totalMercanciasExentas'  name='totalMercanciasExentas'  value="{factura.totalMercanciasExentas}" >
                            <input type="hidden" id='totalServGravados'       name='totalServGravados'       value="{factura.totalServGravados}" >
                            <input type="hidden" id='totalServExentos'        name='totalServExentos'        value="{factura.totalServExentos}" >
                            <input type="hidden" id='totalGravado'            name='totalGravado'            value="{factura.totalGravado}" >
                            <input type="hidden" id='totalExento'             name='totalExento'             value="{factura.totalExento}" >
                            <input type="hidden" id='totalVenta'              name='totalVenta'              value="{factura.totalVenta}" >
                            <input type="hidden" id='totalDescuentos'         name='totalDescuentos'         value="{factura.totalDescuentos}" >
                            <input type="hidden" id='totalVentaNeta'          name='totalVentaNeta'          value="{factura.totalVentaNeta}" >
                            <input type="hidden" id='totalImpuesto'           name='totalImpuesto'           value="{factura.totalImpuesto}" >
                            <input type="hidden" id='totalImpuestoServ'       name='totalImpuestoServ'       value="{factura.totalImpuestoServ}" >
                            <input type="hidden" id='totalCambioPagar'        name='totalCambioPagar'        value="{factura.totalCambioPagar}" >
                            <input type="hidden" id='detalleFactura'          name='detalleFactura'          value="{factura.detalleFactura}" >
                        </form>   
                        <div class="botonesContainer">
                            <div class="boton">
                                <button onclick={_AtrasFacturaFinal} class="btn-dark-gray btn-back pull-left">  {$.i18n.prop("btn.volver")}</button>
                            </div> 
                            <div class="boton">
                                <button onclick={__AplicarYcrearFactura}  class="btn-green btn-add pull-right"> </i> {$.i18n.prop("btn.aplicar")}</button>
                            </div>
                        </div>
                    </div>
                  
                </div>
                  
            </div>
            <div class="col-md-4 col-sm-4 col-lg-4 col-sx-12 ">
		        <div class="box">
				    <div class="box-body">
                        <aside class="right-sidebar">
                            <!--Booking details-->
                            <article class="booking-details clearfix">
                                <h1><span id="lblSCS">{$.i18n.prop("factura.resumen.venta")}</span></h1>
                                <div class="TotalesContainer"  onclick = {__MostrarFormularioDePago}>
                                 <table class="table table-striped">
                                  <thead>
                                    <tr ><th class="totales" style="width:5%;">{$.i18n.prop("factura.resumen.subTotal")}</th> <th><span class="totales"> {subTotalGeneral} </span></th></tr>
                                    <tr><th class="totales"  style="width:5%;">{$.i18n.prop("factura.resumen.descuento")}</th><th><span class="totales"> {totalDescuentos}   </span></th> </tr>
                                    <tr><th class="totales"  style="width:5%;">{$.i18n.prop("factura.resumen.impuesto")}</th> <th><span class="totales"> <span id="lblSubtotal"> {totalImpuesto}    </span>   </th></tr>
                                    <tr><th class="totales"  style="width:5%;">{$.i18n.prop("factura.resumen.exoneracion")}</th> <th><span class="totales"> <span id="lblSubtotal"> {montoExoneracion} </span>   </th></tr>
                                    <tr><th class="totales"  style="width:5%;">{$.i18n.prop("factura.resumen.total")} </th> <th><span class="totales"> <span id="lblTotal">{totalComprobante}     </span> </th></tr>
                                    <tr><th class="totales"  style="width:5%;">{$.i18n.prop("factura.resumen.cambio")} </th> <th><span id="lblTotal">{totalCambioPagarSTR}</span> </th></tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                    </table>     
                                </div>
      
                            </article>
                        </aside>
                    </div><!-- fin box-body-->
				</div><!-- fin box -->
		    </div>
        </div>  
         
</div>  
 <div  class="contenedorFactura" show={mostrarCrearNota}>
                <div class="cabecera-izquierda">
                
                    <form >
                    <div class="row">
                        <div class="col-md-3 col-sm-3 col-lg-3 col-sx-12 ">
                            <div class="form-group ">
                                <label class="label-campos">Numero de Factura</label> 
                                <input type="text" class="form-campos  disabled"  value="{factura.numeroConsecutivo}" readonly> 
                            </div>
                        </div>
                        <div class="col-md-5 col-sm-5 col-lg-5 col-sx-12 ">
                            <div class="form-group ">
                                <label class="label-campos">clave </label> 
                                <input type="text" class="form-campos disabled "  value="{factura.clave}" readonly>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-lg-6 col-sx-12 ">
                            <div class="form-group ">
                                <label class="label-campos">Cliente :{cliente.nombreCompleto} </label> 
                                
                            </div>
                        </div>
                    </div>
                    </form >
                    <div class="detalles-factura">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th style="width:5%;"> </th>
                                <th style="width:5%;"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.linea")}</div></th>
                                <th><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.codigo")}</div></th>
                                <th style="width:20%;"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.descripcion")}</div></th>
                                <th style="width:8%;"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.cantidad")}</div> </th>
                                <th ><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.precio")}</div></th>
                                <th style="width:4%"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.descuento")} </div></th>
                                <th style="width:4%"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.impuesto")}</div></th>
                                <th ><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.subTotal")}</div> </th> 
                            </tr>
                            </thead>
                            <tbody>
                            <tr each={detail}>
                                <td>
                                   <button  onclick={__removeProductFromDetail} class="btn btn-danger btn-xs btn-block">X</button>
                                </td>
                                <td style="width:5%;"  class="campoLabel"><label >{numeroLinea}</label> </td>
                                <td  style="width:4%" class="campoLabel"> <label >{codigo}</label></td>
                                <td class="campoLabel"><label >{descripcion}</label></td>
                                <td class="text-right" style="width:8%;">
                                    <input onkeyup={__CambiarCantidad} onBlur={__CambiarCantidadBlur}  class="campoDetalle " type="number" step="any"  value = {cantidad.toFixed(3)} />
                                </td>
                                <td class="campoLabel">
                                    <input  onkeyup={__CambiarPrecio} onBlur ={__CambiarPrecioBlur}  class="campoDetalle" type="number"  step="any"  value = "{precioUnitario.toFixed(2)}" />
                                </td>
                                <td class="text-right" style="width:8%">
                                    <input  onkeyup={__CambiarDescuento} onBlur ={__CambiarDescuentoBlur} class="campoDetalle" type="number"  step="any"  value = "{porcentajeDesc.toFixed(2)}" />
                                </td>
                                <td  style="width:4%" class="campoLabel">
                                     <label >{impuesto.toFixed(2)}</label>
                                </td>
                                <td class="campoLabel">
                                    <label >{montoTotalLinea.toFixed(2)}</label>
                                </td>
                            </tr>
                            </tbody>
                        </table>          
                    </div>
                       <div class="botonesContainer">
                            <div class="boton">
                                <button onclick={_AtrasListado} class="btn-dark-gray-final btn-back pull-left">  {$.i18n.prop("btn.volver")}</button>
                            </div> 
                        </div>
                </div>
                <section class="cabecera-derecha">
                     <aside class="left-sidebar">
                        <article class="clearfix">
                            <div class="TotalesContainer"  onclick = {__MostrarFormularioDePago}>
                                 <table class="table table-striped">
                                  <thead>
                                    <tr ><th class="totales" style="width:5%;">{$.i18n.prop("factura.resumen.subTotal")}</th> <th><span class="totales"> {subTotalGeneral} </span></th></tr>
                                    <tr><th class="totales"  style="width:5%;">{$.i18n.prop("factura.resumen.descuento")}</th><th><span class="totales"> {totalDescuentos}   </span></th> </tr>
                                    <tr><th class="totales"  style="width:5%;">{$.i18n.prop("factura.resumen.impuesto")}</th> <th><span class="totales"> <span id="lblSubtotal"> {totalImpuesto}    </span>   </th></tr>
                                    <tr><th class="totales"  style="width:5%;">{$.i18n.prop("factura.resumen.exoneracion")}</th> <th><span class="totales"> <span id="lblSubtotal"> {montoExoneracion} </span>   </th></tr>
                                    <tr><th class="totales"  style="width:5%;">{$.i18n.prop("factura.resumen.total")} </th> <th><span class="totales"> <span id="lblTotal">{totalComprobante}     </span> </th></tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                    </table>          
                            </div>
                           
                        </article>
                    </aside>
                </section>
</div><!-- fin contenedor-factura-->

<style type="text/css"  >
    .btn-dark-gray-final {
        background-color: #3D3E42;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 10px;
        padding-top: 5px;
        padding-left: 5px;
        padding-right: 5px;
        font-size: 20px!important;
        font-weight: bold;
        margin-right: 15px;
        border: none;
        float: right;
        cursor: pointer;
    }
    .btn-dark-gray {
        background-color: #3D3E42;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-left: 20px;
        padding-right: 20px;
        font-size: 14px;
        font-weight: bold;
        margin-right: 15px;
        border: none;
        float: right;
        cursor: pointer;
    }
    .btn-dark-gray {
        background-color: #3D3E42;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-left: 20px;
        padding-right: 20px;
        font-size: 14px;
        font-weight: bold;
        margin-right: 15px;
        border: none;
        float: right;
        cursor: pointer;
    }
    .disabled { 
        position : relative; 
        color: grey;
    }
    .disabled:after {
        content:' ';
        width: 100%;
        height: 100%;
        left:0;
        top: 0;
        position:absolute;
    }
    .label-campos{
       margin-left: 2%;
    }
    .form-campos {
        display: block;
        width: 100%;
        background: #dddddd;
        margin-left: 2%;
        height: 34px;
        font-size: 12px;
        line-height: 1.42857143;
        color: #555;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        border-radius: 4px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    }
    .totales{
        font-size: 14px;
        color: black;
    }
    .campoDetalle {
        display: block;
        width: 100%;
        height: 25px;
        padding: 8px 18px;
        font-size: 10px;
        line-height: 1.42857143;
        color: #555;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        border-radius: 2px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        background-color: #fcfcfc;
        border: 1px solid #ccc;
        font: 20px verdana, arial, helvetica, sans-serif;
        /* margin: 2px 0; */
        padding: 1px 2px;
        overflow: visible;
    }
    .campoLabel{
        font-size: 12px;
        
        text-align: center;
    }

    .opcionPrecioPublico{
        display: flex;
        flex-direction: column;
    }
    @media (min-width: 992px){
    .modal-lg {
        width: 1024px !important;
    }
    }

    .tamanoClienteNuevo{
        font-size: 30px;
        font-weight: 600;
        color: black;
        height: 10%;

    }
  
    .btn-dark-gray {
        background-color: #3D3E42;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-left: 20px;
        padding-right: 20px;
        font-size: 30px!important;
        font-weight: bold;
        margin-right: 15px;
        border: none;
        float: right;
        cursor: pointer;
    }
    .btn-green {
        background-color: #4cae4c;
        color: #FFF;
        border-radius: 5px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-left: 20px;
        padding-right: 20px;
        font-size: 30px !important;
        font-weight: bold;
        margin-right: 15px;
        border: none;
        float: right;
        cursor: pointer;
    }
    .botonesContainer{
       display:flex;
    }
    .boton{
       flex: 1;
    }
   

    .elementoTotales{
        font-weight: 600 !important;
        font-size: 20px !important;
        font-family: Roboto,sans-serif !important;
        color: black!important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        align-items: center;
        text-align: left;
        margin-left: 2%;
    }
    .TotalesContainer{
        display:flex;
        flex-direction: column;
            margin: 6%;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
    }
   
    
    .tituloDetalle{
        text-align: center;
        text-decoration: none;
        font-style: italic;
        color: black;
        font-weight: 600;
        font-size: 20px;
    }
    .btn-block {
        display: block;
        width: 100%;
        margin-top: 30%;
        }    

                            
    .contenedorFactura{
        display: flex;
        flex: 1;
        border: 1px solid #3c8dbc;
        background: #ffffff;
    }

    .precioTotalFacturaContainer{
        display:flex;
        flex:1;
    }
    .codigoBarraPrecioContainer{
        display:flex;
        flex:1;
    }
    .codigoBarraPrecioContainer .inputCodigoPrecio{
        flex:1;
        padding-left: 2%;
        padding-right: 2%;
        padding-bottom: 1%;
    }
    @media screen and (max-width: 1024px) {
    
    .label-totalesComprobante{
        font-size: 18px !important;
    }
    .cantidadArticulosTitulo{
        font-size: 10px !important;
    }
    .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_1{
        font-size: 14px !important;
    }
    .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_2{
        font-size: 14px !important;
    }
    }
    .contenedorFactura .cabecera-izquierda .botonesFuncionalContainer{
    display:flex;
    }

    .contenedorFactura .cabecera-izquierda .botonesFuncionalContainer .botonesFuncional{
    flex:1;
    padding-right: 1%;
    padding-bottom: 2%;
    }
    .tituloCantidadArticulos{
        display:flex;
    }
    .contenedorFactura .cabecera-derecha .tituloCantidadArticulos .cantidadArticulosTitulo{
        font-weight: 600 !important;
        font-size: 30px !important;
        font-family: Roboto,sans-serif !important;
        color: #edea17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        line-height: 30px;
        border-collapse: separate;
        text-align: center;
        cursor: pointer;
        text-align: center !important;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 5px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;

    }
</style>    
<script>
    var self = this;
    self.parametros   = opts.parametros; 
    // Detalle de la factura es una coleccion de articulos
    self.detail                = []
    self.mostrarCrearNota      = true
    self.error                 = false
    self.comboTipoDocumentos   = []
    self.comboRebajaInventario = []
    self.comboCodigosReferencia = []
    self.subTotalGeneral       = 0
     self.totalDescuentos       = 0
    self.totalImpuesto         = 0
    self.montoExoneracion     = 0
    self.montoExoneracion1     = 0
    self.pesoPrioridad =  0
    self.numeroLinea =0
    self.precioUltimo = ""
    self.itemEliminar = {}
    self.factura                = {
        id:null,
	   fechaCredito:null,
	   fechaEmision:null,
	   condicionVenta:"",
       codigoActividad:"",
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
    self.cantidadEnterFacturar =0          
    self.item                  = null;
    self.articulo              = null;
    self.detalleFactura        = {data:[]}
    self.facturas_espera       = {data:[]}
    self.mostrarFormularioPago         = false
    self.mostarParaCrearNuevaFactura   = true
     self.subTotalGeneral               = 0
    self.vueltoImprimir               = 0
    self.empresa              = {}
    self.subTotalGeneral               = 0
    self.totalDescuentos               = 0
    self.totalImpuesto                 = 0
    self.montoExoneracion              = 0
    self.montoExoneracion1             = 0
    self.totalComprobante              = 0
    self.totalCambioPagar              = 0
    self.facturaImpresa={
        cliente:{
            cedula:""
        },
        nombreFactura:"",
        correoAlternativo:"",
        correoElectronico:"",
        nota:"",
        referenciaNumero:"",
        subTotalGeneralSTR:"",
        totalDescuentos:0,
        totalDescuentosSTR:"",
        totalComprobanteSTR:"",
        totalCambioPagarSTR:"",
        tipoCambio:0,
        tipoCambioSTR:"",
        estado:0,
        empresa:{
            noFacturaElectronica:"",

        },
        tipoDoc:"",
        consecutivoProforma:"",

    }
    self.transaccion = false
    self.on('mount',function(){
        _Empresa()
        __ComboRebajoInventario()
        __ComboTipoDocumentos()
        __FacturaEnEspera()
       __Teclas()
       __ComboCodigoReferencia()
        $(".nota").attr("maxlength", 80);
    })
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
                       self.vueltoImprimir = self.empresa.vueltoImprimir
                       self.update()
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
**/
_AtrasListado(){
  self.mostrarCrearNota      = false
  self.mostrarFormularioPago = false
   __MostrarListado() 
}

_SeleccionarEfectivo(){
    $('.totalEfectivo').select()
    $(".totalEfectivo").focus()
}    

_SeleccionarTarjeta(){
    $('.totalTarjeta').select()
    $(".totalTarjeta").focus()
}   
_SeleccionarBanco(){
    $('.totalBanco').select()
    $(".totalBanco").focus()
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
}
/**
* Aplicar el descuento
**/
__CambiarDescuento(e){
   if(e.keyCode == 13){
        var valor = __valorNumerico(e.currentTarget.value);
        self.item = e.item; 
        self.item.porcentajeDesc = valor
        self.update()
        ActualizarLineaDEtalle()
        aplicarCambioLineaDetalle()
     }
}
/**
*Cambiar Cantidad del Articulo
**/
__CambiarCantidad(e){
     if(e.keyCode == 13){
        var cantidad = __valorNumerico(e.currentTarget.value);
        if(cantidad  == 0){
            cantidad = 1
        }
        self.item = e.item; 
        self.item.cantidad = cantidad
        self.update()
        ActualizarLineaDEtalle()
        aplicarCambioLineaDetalle()
     }
 }
 /**
*Cambiar Precio Unitario
**/
__CambiarPrecio(e){
     if(e.keyCode == 13){
        var valor = __valorNumerico(e.currentTarget.value);
        if(valor  == 0) {
            return false
        }
        self.item = e.item; 
        self.item.precioUnitario = valor
        self.update()
        ActualizarLineaDEtalle()
        aplicarCambioLineaDetalle()
    }
 }
/**
* Aplicar el descuento
**/
__CambiarDescuentoBlur(e){
    var valor = __valorNumerico(e.currentTarget.value);
    self.item = e.item; 
    self.item.porcentajeDesc = valor
    self.update()
    ActualizarLineaDEtalle()
    aplicarCambioLineaDetalle()
}
/**
*Cambiar Cantidad del Articulo
**/
__CambiarCantidadBlur(e){
        var cantidad = __valorNumerico(e.currentTarget.value);
        if(cantidad  == 0){
            cantidad = 1
        }
        self.item = e.item; 
        self.item.cantidad = cantidad
        self.update()
        ActualizarLineaDEtalle()
        aplicarCambioLineaDetalle()
 }
 /**
*Cambiar Precio Unitario
**/
__CambiarPrecioBlur(e){
        var valor = __valorNumerico(e.currentTarget.value);
        if(valor  == 0) {
            return false
        }
        self.item = e.item; 
        self.item.precioUnitario = valor
        self.update()
        ActualizarLineaDEtalle()
        aplicarCambioLineaDetalle()
 }
/**
*  Obtiene el valor de lo digitado en el campo de efectivo
**/
__TotalDeEfectivoAPagar(e){
    self.totalCambioPagarSTR = 0
    self.factura.totalEfectivo = __valorNumerico(e.target.value) 
    if(self.factura.totalEfectivo ==0 ){
        self.cantidadEnterFacturar = 0
    }
    self.update()
    _calculoEnterPago()
}
/**
*  Obtiene el valor de lo digitado en el campo de Tarjeta
**/
__TotalDeTarjetaAPagar(e){
    self.factura.totalTarjeta = __valorNumerico(e.target.value) 
    if(self.factura.totalTarjeta ==0 ){
        self.cantidadEnterFacturar = 0
    }
    self.update()
    _calculoEnterPago()
}
/**
*  Obtiene el valor de lo digitado en el campo de Banco
**/
__TotalDeBancoAPagar(e){
    self.factura.totalBanco = __valorNumerico(e.target.value) 
    if(self.factura.totalBanco ==0 ){
        self.cantidadEnterFacturar = 0
    }
    self.update()
    _calculoEnterPago()
}
/**
*   Calculo del cambio entregar en el evento onblur
**/
__CalculaCambioAEntregarOnblur(e){
    _calculoEnterPago()
}
/**
*   Calculo del cambio entregar en el evento keyPress
**/
__CalculaCambioAEntregarKeyPress(e){
    if (e.keyCode == 13) {
        __Calcular()
    }
}

function __Calcular(){
       _calculoEnterPago()
       if(self.totalCambioPagar == 0){
            if(self.cantidadEnterFacturar >= 1){
               __EnterFacturar()
            }else{
                self.cantidadEnterFacturar = self.cantidadEnterFacturar + 1
                self.update()
            }
        }else{
            var totalFactura   = __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2))
            if(self.totalCambioPagar > 0 || self.totalCambioPagar > totalFactura || self.totalCambioPagar == totalFactura  ){
                if(self.cantidadEnterFacturar >= 1){
                __EnterFacturar()
                }else{
                    self.cantidadEnterFacturar = self.cantidadEnterFacturar + 1
                    self.update()
               }
            }else{
                self.cantidadEnterFacturar = 0
                self.update()

            }
        }
}
function _calculoEnterPago(){
        var sumaMontosEntregadosParaCambios  = __valorNumerico($('.totalTarjeta').val())
        sumaMontosEntregadosParaCambios += __valorNumerico($('#totalBanco').val()) 
        sumaMontosEntregadosParaCambios += __valorNumerico($('.totalEfectivo').val())   
        if(sumaMontosEntregadosParaCambios == 0){
            self.factura.totalCambioPagar =  __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2)) * -1
            self.totalCambioPagar =  __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2)) * -1
            self.totalCambioPagarSTR = formatoDecimales(self.totalCambioPagar,2)    
            self.update()
            return
        }
        self.factura.totalCambioPagar = 0
        var totalEntregado = __valorNumerico(redondeoDecimales(sumaMontosEntregadosParaCambios,2))
        var totalFactura   = __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2))
        totalEntregado     = __valorNumerico(totalEntregado)
        totalFactura       = __valorNumerico(totalFactura)  
        self.factura.totalCambioPagar = totalEntregado - totalFactura
        self.factura.totalCambioPagar =__valorNumerico(self.factura.totalCambioPagar)   
        self.totalCambioPagar = __valorNumerico(redondeoDecimales(self.factura.totalCambioPagar,2))
        self.totalCambioPagarSTR = formatoDecimales(self.totalCambioPagar,2)
        self.update()
}
/**
* Enter es para verificar si el cliente presiono dos veces enter y mandalo a facturar
**/
function __EnterFacturar(){
    swal({
           title: '',
           text: $.i18n.prop("factura.mensaje.alert.enter"),
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:"Enter=" +$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
           
    }).then(function (isConfirm) {
            if(isConfirm){
                 aplicarFactura(2)
            }
    } 
        );   
        self.cantidadEnterFacturar = 0
        self.update()
}
/**
** Se aplica o se crea una Factura cargada en la pantalla
**/
__AplicarYcrearFactura(e){
 aplicarFactura(2)
}
/**
* Aplicar la factura
**/
function aplicarFactura(estado){
    if($(".referenciaCodigo").val() == 0){
        mensajeError("Seleccione codigo aplicar")
        return
    }
    if($(".rebajaInventario").val() == 0){
        mensajeError("Seleccione Si actualiza el inventario")
        return
    }
    var notaVerificar  = $('.nota').val()
    if(notaVerificar.length ==0){
        mensajeError(self.parametros.tipoEjecucion  == 1?"Digite el motivo de la nota de credito":"Digite el motivo de la nota de debito")
        return
    }
    if(self.detail.length == 0 ){
         swal({
                type: 'error',
                title:$.i18n.prop("factura.alert.sin.detalles"),
                showConfirmButton: false,
                timer: 1500
                })
        return
    }
    crearFactura(estado)  
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
    self.facturaImpresa={
        cliente:{
            cedula:""
        },
        nombreFactura:"",
        correoAlternativo:"",
        correoElectronico:"",
        nota:"",
        referenciaNumero:"",
        subTotalGeneralSTR:"",
        totalDescuentos:0,
        totalDescuentosSTR:"",
        totalComprobanteSTR:"",
        totalCambioPagarSTR:"",
        tipoCambio:0,
        tipoCambioSTR:"",
        estado:0,
        empresa:{
            noFacturaElectronica:"",

        },
        tipoDoc:"",
        consecutivoProforma:"",

    }
    self.factura                = {
        id:null,
        codigoActividad:"",
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
    self.cantidadEnterFacturar = 0
    self.numeroLinea =0
    self.detail                = []
    self.error                 = false
    self.item                  = null;
    self.articulo              = null;
    self.detalleFactura        ={data:[]}
    self.mostrarFormularioPago         = false
     self.subTotalGeneral               = 0
    self.totalDescuentos               = 0
    self.totalImpuesto                 = 0
    self.montoExoneracion              = 0
    self.totalComprobante              = 0
    self.totalCambioPagar              = 0
    self.totalCambioPagarSTR           = 0
    self.update();
    $('#tipoDoc').prop("selectedIndex", 0);
    $(".nota").attr("maxlength", 80);
    $("#totalBanco").val(null)   
    $(".totalTarjeta").val(null)   
    $(".totalEfectivo").val(null)   
    $("#nota").val(null)
   
  
}
/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(){
    var numero  = self.parametros.consecutivo
    self.detail = [];
    self.factura = null
    self.update()
     __Init()
    $.ajax({
        url: "ListarDetlleByFacturaConsecutivoAjax.do",
        data: {consecutivo:numero},
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
}
/**
*  Cargar detalles Factura en espera
**/
function cargarDetallesFacturaEnEspera(data){
    $('.nota').val(null);
    self.detail = [];
    self.numeroLinea =  0
    self.cantArticulos =  0
    self.pesoPrioridad = 0
    self.update()
    $.each(data, function( index, modeloTabla ) {
        self.factura = modeloTabla.factura
        $('.nota').val(null);
        self.cliente              = modeloTabla.factura.cliente
        self.vendedor             = modeloTabla.factura.vendedor
        self.update()
        self.descripcionArticulo = modeloTabla.descripcion
        self.detail.push({
            unidadMedida    : modeloTabla.unidadMedida,
            numeroLinea     : modeloTabla.numeroLinea,
            pesoPrioridad    :modeloTabla.numeroLinea,
            codigo          : modeloTabla.codigo,
            tipoImpuesto    : modeloTabla.tipoImpuesto,
            tipoImpuesto1   : modeloTabla.tipoImpuesto1,
            descripcion     : modeloTabla.descripcion,
            cantidad        : __valorNumerico(modeloTabla.cantidad),
            precioUnitario  : __valorNumerico(modeloTabla.precioUnitario),
            impuesto        : __valorNumerico(modeloTabla.impuesto),
            impuesto1       : __valorNumerico(modeloTabla.impuesto1),
            montoImpuesto   : __valorNumerico(modeloTabla.montoImpuesto),
            montoImpuesto1  : __valorNumerico(modeloTabla.montoImpuesto1),
            montoDescuento  : __valorNumerico(modeloTabla.montoDescuento),
            porcentajeDesc  : __valorNumerico(modeloTabla.porcentajeDesc),
            subTotal        : __valorNumerico(modeloTabla.subTotal),
            montoTotalLinea : __valorNumerico(modeloTabla.montoTotalLinea),
            montoTotal      : __valorNumerico(modeloTabla.montoTotal),
            costo           : __valorNumerico(modeloTabla.costo),
            porcentajeGanancia :__valorNumerico(modeloTabla.porcentajeGanancia),
            montoGanancia :__valorNumerico(modeloTabla.montoGanancia),
            ganancia :__valorNumerico(__valorNumerico(modeloTabla.ganancia)),
            pesoTransporte :  __valorNumerico(modeloTabla.pesoTransporte),
            pesoTransporteTotal :__valorNumerico(modeloTabla.pesoTransporteTotal),
            montoExoneracion:__valorNumerico(modeloTabla.montoExoneracion),
            montoExoneracion1:__valorNumerico(modeloTabla.montoExoneracion1),
            porcentajeExoneracion:__valorNumerico(modeloTabla.porcentajeExoneracion),
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
   
    $('#totalEfectivo').val(self.factura.totalComprobante)
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    $('#totalEfectivo').focus()
    $('#totalEfectivo').select()
    __aplicarExoneracionPorCliente()
}
/**
* Aplicar la exoneracion de detalles
**/
function __aplicarExoneracionPorCliente(){
   

    var aplicaExo = false
    var porcentaje = __valorNumerico(self.cliente.porcentajeExoneracion / 100)
    var valorTotal = 0
    for (var count = 0; count < self.detail.length; count++) {
        self.item  = self.detail[count];
        self.cliente.porcentajeExoneracion = __valorNumerico(self.cliente.porcentajeExoneracion)
            if(self.item.montoImpuesto > 0 || self.item.montoImpuesto1 > 0 ){
                if(self.cliente.porcentajeExoneracion > 0  ){
                    self.item.porcentajeExoneracion = __valorNumerico(self.cliente.porcentajeExoneracion)
                    self.item.fechaEmisionExoneracion = self.cliente.fechaEmisionExoneracion
                    self.item.nombreInstitucionExoneracion = self.cliente.nombreInstitucionExoneracion
                    self.item.numeroDocumentoExoneracion = self.cliente.numeroDocumentoExoneracion
                    self.item.tipoDocumentoExoneracion = self.cliente.tipoDocumentoExoneracion
                    valorTotal = __valorNumerico(self.item.montoImpuesto1) * __valorNumerico(porcentaje)  
                    self.item.montoExoneracion1 = valorTotal
                     valorTotal = __valorNumerico(self.item.montoImpuesto) * __valorNumerico(porcentaje)  
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
                    self.item.ImpuestoNeto = __valorNumerico(self.item.montoImpuesto) + __valorNumerico(self.item.montoImpuesto1) 
                    self.item.montoTotalLinea = self.item.subTotal +  self.item.ImpuestoNeto
                    self.detail[count] = self.item;
                    self.totalCambioPagar = 0
                    self.totalCambioPagarSTR = 0
                    self.factura.totalEfectivo =0
                    self.factura.totalTarjeta =0
                    self.factura.totalBanco =0
                    self.factura.totalCambioPagar = self.factura.totalComprobante
                    self.update();
                 
                    aplicaExo = true
                }
               
            }
    }
    __calculate()
    if(aplicaExo == true){
       self.factura.totalCambioPagar = self.factura.totalComprobante
       self.factura.totalEfectivo =0
       self.factura.totalTarjeta =0
       self.factura.totalBanco =0
       self.totalCambioPagar = 0
       self.totalCambioPagarSTR = 0
       self.update();
    }
}
/**
*  Crear Factura nueva
**/
function crearFactura(estado){

     if (self.transaccion == true ){
        return false
    }
    self.transaccion = true 
    self.update()
    self.detalleFactura.data =self.detail
    self.update() 
    var JSONDetalles = JSON.stringify( self.detalleFactura );
    self.factura.id = self.factura.id
    self.factura.totalEfectivo =__valorNumerico($('#totalEfectivo').val())
    self.factura.totalTarjeta = __valorNumerico($('#totalTarjeta').val()) 
    self.factura.totalBanco = __valorNumerico($('#totalBanco').val())
    self.factura.detalleFactura =JSONDetalles
    self.factura.estado = 2
    self.factura.tipoDoc = self.parametros.tipoEjecucion = 2 ?"02":"03"
    self.update();
    var dataTemporal = null
    var formulario = $("#formularioFactura").serialize();
    $.ajax({
        type : "POST",
        dataType : "json",
        async: false,
        data : formulario,
        url : "CrearNotaCreditoAjax.do",
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                     mensajeAlertErrorOConfirmacion('error',data.message);    	
                }
                self.transaccion = false
                self.update()
            } else {
               	self.cantidadEnterFacturar =0
                self.update()
                serverMessageJsonClase(data);
                dataTemporal = data
                evaluarFactura(data)
                self.transaccion = false
                self.mostrarCrearNota = false 
                self.update()
                __MostrarListado()

            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
            self.transaccion = false
            self.update()
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
            self.bloqueoFactura = 1;
            self.update()
        });
        if(self.facturaImprimir.estado == 2 || self.facturaImprimir.estado == 3 || self.facturaImprimir.estado == 4){
                __Init()
                //Envia a la pantalla de impresion
                self.facturaReimprimir = self.facturaImprimir
                self.update()
                    swal({
                        type: 'success',
                        title: "Cons:" + self.facturaImprimir.numeroConsecutivo,
                        showConfirmButton: false,
                        timer: 1500
                     })
                   
                  var parametros = {
                          factura: self.facturaReimprimir ,
                          facturaDia:1
                      }
                      riot.mount('ptv-imprimir',{parametros:parametros});
        
        }else{
            swal({
                type: 'success',
                title:mostrarMensajeCreacionConsecutivo(self.facturaImprimir),
                showConfirmButton: false,
                timer: 1000
            })
            __Init()
        }
          
    }
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
   self.mostrarCrearNota      = true
   self.cantidadEnterFacturar = 0
   self.error = false
   self.update()
 }
/**
*   funcion para grabar la Factura en el back end
**/
__MostrarFormularioDePago(){
    mostrarPAgo()
}
/**
*Mostrar Pago
**/
function mostrarPAgo(){
     //No hay detalles registrados en la Factura
    if(self.detail.length == 0 ){
        swal({
            type: 'error',
            title:$.i18n.prop("factura.alert.sin.detalles"),
            showConfirmButton: false,
            timer: 1500
        })
        return
    }
    if(self.vueltoImprimir == 0){
        $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
        $('#totalTarjeta').val(null)
        $('#totalBanco').val(null)
    }else{
        $('#totalEfectivo').val(null)
        $('#totalTarjeta').val(null)
        $('#totalBanco').val(null)
    }
    getSubTotalGeneral()
    self.totalCambioPagar =0
    self.factura.totalCambioPagar =0
     self.mostrarCrearNota      = false
    self.mostrarFormularioPago = true
    self.update()
    $('#totalEfectivo').focus()
    $('#totalEfectivo').select()
}
/**
* eliminar un detalle factura
**/
__removeProductFromDetail(e) {
    self.itemEliminar = e.item;
    self.update()
        eliminarDetalle()
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
    self.update()
     __calculate();
 }

 
/**
* Monto de descuento sobre la ganancia
*/
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
* Actualiza la linea del detalle de la factura
**/
function ActualizarLineaDEtalle(){
    var montoTotal             = getMontoTotal(self.item.precioUnitario,self.item.cantidad)
    var montoDescuento         = getMontoDescuento(self.item.precioUnitario,self.item.cantidad,self.item.porcentajeDesc,self.item.porcentajeGanancia)
    var subTotal               = montoTotal > montoDescuento?montoTotal - montoDescuento: montoDescuento-montoTotal
    montoImpuesto1             = _calcularImpuesto(subTotal,self.item.impuesto1 ==null?0:self.item.impuesto1)
    var resultadoMontoImpuesto1 = montoImpuesto1 + subTotal;
    var montoImpuesto          = _calcularImpuesto(resultadoMontoImpuesto1,self.item.impuesto ==null?0:self.item.impuesto)
    var montoTotalLinea        = subTotal + montoImpuesto + montoImpuesto1   
    self.item.pesoTransporteTotal = __valorNumerico(self.item.cantidad) *  __valorNumerico(self.item.pesoTransporte)
    self.item.montoTotal       = montoTotal
    self.item.montoDescuento   = montoDescuento
    self.item.subTotal         = subTotal
    self.item.montoImpuesto    = montoImpuesto
    self.item.montoImpuesto1   = montoImpuesto1
    self.item.montoTotalLinea  = montoTotalLinea
    self.item.ganancia         = __ObtenerGananciaProductoNuevoIngresado(montoDescuento,self.item.precioUnitario,self.item.costo ==null?0:parseFloat(self.item.costo),self.item.cantidad)
    self.item.montoGanancia    = self.item.ganancia 
    self.update()
}
/**
* Aplicar el cambio de linea Detalle
**/
function aplicarCambioLineaDetalle(){
    var index    = self.detail.indexOf(self.item);
    self.detail[index] = self.item;
    self.update()
    __calculate()
}
/**
* calculacion de los detalle de la factura 
**/
function __calculate() {
    self.factura.total            = 0;
    self.factura.totalDescuentos  = 0;
    self.factura.totalImpuesto    = 0;
    self.factura.subTotal         = 0;
    self.update()
    var totalVenta     = 0
    var subTotal       = 0
    var totalDescuento = 0
    var totalImpuesto  = 0
    var totalMercanciasGravadas = 0
    var totalMercanciasExentas  = 0
    var totalServGravados       = 0
    var totalServExentos        = 0
    var totalGravado            = 0
    var totalExento             = 0
    var totalComprobante        = 0
    var totalventaNeta          = 0
    var totalGanancia           = 0
    self.cantArticulos      = 0
    var montoExoneracion = 0
    self.detail.forEach(function(e){
        totalMercanciasGravadas += e.tipoImpuesto.length > 0  && e.unidadMedida !="Sp"?e.montoTotal:0
        totalMercanciasExentas  += e.tipoImpuesto.length == 0 && e.unidadMedida =="Sp"?e.montoTotal:0
        totalServGravados       += e.tipoImpuesto.length > 0 && e.unidadMedida =="Sp"?e.montoTotal:0
        totalServExentos        += e.tipoImpuesto.length == 0 && e.unidadMedida =="Sp"?e.montoTotal:0
        totalGravado            += e.tipoImpuesto.length > 0 ?e.montoTotal:0
        totalExento             += e.tipoImpuesto.length == 0 ?e.montoTotal:0
        totalComprobante        += e.montoTotalLinea
        subTotal                += e.subTotal >0?e.subTotal:0
        totalDescuento          += e.montoDescuento >0?e.montoDescuento:0
        totalImpuesto           += __valorNumerico(e.montoImpuesto)
        totalVenta              += e.montoTotal
        totalGanancia           +=__valorNumerico(e.ganancia)
        self.cantArticulos      += esEntero(e.cantidad) == true? e.cantidad:1 
        montoExoneracion        = montoExoneracion + __valorNumerico(e.montoExoneracion) 
    });
    self.totalGananciaByProducto = formatoDecimales(parseFloat(totalGanancia),2)
    self.factura.totalMercanciasGravadas = __valorNumerico(totalMercanciasGravadas)
    self.factura.totalMercanciasExentas  = __valorNumerico(totalMercanciasExentas)
    self.factura.totalServGravados       = __valorNumerico(totalServGravados)
    self.factura.totalServExentos        = __valorNumerico(totalServExentos)
    self.factura.totalGravado            = __valorNumerico(totalGravado)
    self.factura.totalExento             = __valorNumerico(totalExento)
    self.factura.totalVenta              = __valorNumerico(totalVenta)
    self.factura.totalDescuentos         = __valorNumerico(totalDescuento)
    self.factura.subTotal                = __valorNumerico(subTotal)
    self.factura.totalImpuesto           = __valorNumerico(totalImpuesto) 
    self.factura.totalVentaNeta          = __valorNumerico(totalVenta-totalDescuento)
//Se verifica si la mesa tiene impuestos

    var tieneMesa = typeof self.factura.mesa !== 'undefined'?true:false;
    tieneMesa = self.factura.mesa == null?false:true


    var tieneImpuestoServiciot = false
    if(tieneMesa){
      tieneImpuestoServiciot = typeof self.factura.mesa.impuestoServicio !== 'undefined'?true:false;  
    }
    if (tieneMesa && tieneImpuestoServiciot){
        if(self.factura.mesa.impuestoServicio  == true){
            self.factura.totalImpuestoServ = Math.round(__valorNumerico(subTotal * 0.10))
            self.factura.totalVentaNeta    = Math.round(__valorNumerico(totalVenta-totalDescuento) + __valorNumerico(self.factura.totalImpuestoServ))
            totalComprobante          = Math.round(__valorNumerico(totalComprobante) + __valorNumerico(self.factura.totalImpuestoServ))
        }
    }    
    self.factura.totalComprobante        = __valorNumerico(totalComprobante)
    self.totalComprobante                = formatoDecimales(self.factura.totalComprobante,2);
    self.totalDescuentos                 = formatoDecimales(self.factura.totalDescuentos,2);
    self.totalImpuesto                   = formatoDecimales(self.factura.totalImpuesto,2);
    self.montoExoneracion                = formatoDecimales(montoExoneracion,2);
    self.update(); 
    getSubTotalGeneral()
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    $('#totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
}
/**
*  Sub Total Generar
**/
function getSubTotalGeneral(){
    var resultado = __valorNumerico(self.factura.subTotal) + __valorNumerico(self.factura.totalDescuentos)
    self.subTotalGeneral = formatoDecimales(resultado,2)
    self.factura.subtotal =  resultado
    self.totalDescuentos = formatoDecimales(self.factura.totalDescuentos,2)
    var resultadoTotalImpuesto = __valorNumerico(self.factura.totalImpuesto) 
    self.totalImpuesto   = formatoDecimales(resultadoTotalImpuesto,2)
    self.update()
}



function __ComboCodigoReferencia(){
    self.comboCodigosReferencia = []
    self.update()
    self.comboCodigosReferencia.push({
        estado:0,
        descripcion:"Seleccionar"
    })
    self.comboCodigosReferencia.push({
            estado:'01',
            descripcion:$.i18n.prop("referencia.anula.documento")
    })
    if(self.parametros.tipoEjecucion == 1){
        self.comboCodigosReferencia.push({
            estado:'02',
            descripcion:$.i18n.prop("referencia.corrige.monto.documento")
        })

    }

    
   self.update()
}


/**
* cargar los tipos de Documento de la factura
**/
function __ComboTipoDocumentos(){
    self.comboTipoDocumentos = []
    self.update()
    self.comboTipoDocumentos.push({
        estado:self.parametros.tipoEjecucion == 2?"02":"03",
        descripcion:self.parametros.tipoEjecucion == 2?$.i18n.prop("factura.tipo.documento.nota.debito"):$.i18n.prop("factura.tipo.documento.nota.credito")
    })
    self.update()
}

function __ComboRebajoInventario(){
    self.comboRebajaInventario = []
    self.update()
    self.comboRebajaInventario.push({
        estado:0,
        descripcion:"Seleccionar"
    })
    if(self.parametros.tipoEjecucion ==2){
        self.comboRebajaInventario.push({
            estado:1,
            descripcion:"Si Rebajar Inventario"
        })
        self.comboRebajaInventario.push({
            estado:2,
            descripcion:"No Rebajar Inventario"
        })

    } else if(self.parametros.tipoEjecucion ==1){
        self.comboRebajaInventario.push({
            estado:3,
            descripcion:"Sumar al Inventario"
        })
        self.comboRebajaInventario.push({
            estado:4,
            descripcion:"No sumar al Inventario"
        })
    }
   self.update()
}
/**
* Mostrar el pago con F8 o en blanco
**/
function __EnviarFacturar(){
      if(self.mostrarFormularioPago == false && self.mostarParaCrearNuevaFactura == true){
        if(self.vueltoImprimir == 0){
            self.factura.totalCambioPagar =__valorNumerico(self.factura.totalComprobante)   
            self.totalCambioPagar = redondeoDecimales(self.factura.totalComprobante,2)
            self.update()
            $(".totalEfectivo").val(self.totalCambioPagar)
        }  
         mostrarPAgo()     
      }else if (self.mostrarFormularioPago == true && self.mostarParaCrearNuevaFactura == false ){
          self.update()
            aplicarFactura(2)   
        } 
}
/**
*  teclas de la pantalla
**/      
function __Teclas(tecla){
     if(tecla ==119){
        __EnviarFacturar()
        return 
        
    } 
 }
</script>
</nota-credito>