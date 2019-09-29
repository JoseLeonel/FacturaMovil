<debito-credito>



<!--Se creo para no dejarlo a la venta comun sino para casos especiales no valida la cantidad del articulo en el invetario-->


<!--Formulario de Pago-->
<!---Datos Final cuando no es un venta de Crucero -->
<div show={mostrarFormularioPago}>
		<div class="row" >
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
                                            <div class="form-group ">
                                                <label for="pago_tipoVentaL">{$.i18n.prop("factura.tipo.documento")} </label> 
                                                <select onchange= {__formaReferencias} class="form-control tipoDoc" id="tipoDoc" name="tipoDoc"   >
                                                    <option each={comboTipoDocumentos} value="{estado}" selected="{factura.tipoDoc ==estado?true:false}" >{descripcion}</option>
                                                </select>
                                            </div>
                                    <div class="form-group ">
                                        <label for="pago_tipoVentaL">{$.i18n.prop("factura.estado")} </label> 
                                        <select class="form-control estado" id="estado" name="estado"  >
                                            <option each={comboEstados} value="{estado}" selected="{factura.estado ==estado?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>

                                    <div class="form-group ">
                                        <label >{$.i18n.prop("factura.nota")}</label> 
                                        <input type="text" class="form-control nota" id="nota" name="nota" value="{factura.nota}">
                                    </div>
                                    
                                    
                                                   

                                </div>
                                <div  class= "col-md-6 col-sx-6 col-sm-6 col-lg-6" >
                                    <div class="form-group ">
                                        <h3><label class="text-primary">{$.i18n.prop("informacion.referencias")}</label> </h3>
                                    </div>

                                  
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("informacion.codigo")}</label> 
                                        <select class="form-control " id="referenciaCodigo" name="referenciaCodigo" >
                                            <option each={codigosReferencias}  value="{estado}" selected="{factura.referenciaCodigo ==estado?true:false}" >{descripcion}</option>
                                        </select>
                                    </div>
                                    <div class="form-group ">
                                        <label>{$.i18n.prop("informacion.razon")}</label> 
                                        <input type="text" class="form-control " id"referenciaRazon" name= "referenciaRazon" value="{factura.referenciaRazon}"  >
                                    </div>
                                   


                                    
                                </div>
                            </div>
                            <input type="hidden" id='id'                      name='id'                      value="{factura.id}" >
                            <input type="hidden" id='mesa'                    name='mesa'                    value="{factura.mesa}" >
                            <input type="hidden" id='codigoMoneda'            name='codigoMoneda'            value="{factura.codigoMoneda}" >
                            <input type="hidden" id='tipoCambio'              name='tipoCambio'              value="{factura.tipoCambio}" >
                            <input type="hidden" id='montoCambio'             name='montoCambio'              value="{factura.montoCambio}" >
                            <input type="hidden" id='plazoCredito'            name='plazoCredito'            value="{factura.plazoCredito}" >
                            <input type="hidden" id='condicionVenta'          name='condicionVenta'          value="{factura.condicionVenta}" >
                            <input type="hidden" id='fechaCredito'            name='fechaCredito'            value="{factura.fechaCredito}" >
                            <input type="hidden" id='referenciaNumero'        name='referenciaNumero'        value="{factura.numeroConsecutivo}" >
                            <input type="hidden" id='referenciaTipoDoc'       name='referenciaTipoDoc'       value="{factura.tipoDoc}" >
                            <input type="hidden" id='referenciaFechaEmision'  name='referenciaFechaEmision'  value="{factura.fechaEmision}" >
                            <input type="hidden" id="cliente"                 name="cliente"                 value="{cliente.id}">

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
                            <input type="hidden" id='totalDescuentos'          name='totalDescuentos'        value="{factura.totalDescuentos}" >
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
                        <button onclick={__AplicarYcrearFactura}  class="btn-green-aplicar btn-add pull-right"> </i> {$.i18n.prop("btn.aplicar")}</button>
                    </div>
                </div>
            </div>
           
        </div>  
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

  
    <div class="box box-solid box-primary" show={mostarParaCrearNuevaFactura}>
        <div class="box-body">
             <div class="box-header with-border">
                <div class="row">
                  <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12">  
                  <div class="box-tools ">
                    <a class="pull-left" href="#"     onclick = {__MostrarFormularioDePago} title="{$.i18n.prop("crear.documentos.especiales")}"> <span class="label label-limpiar">F8={$.i18n.prop("crear.documentos.especiales")}</span></a>
                    <a class="pull-right" href="#"    onclick = {__Limpiar} title="{$.i18n.prop("btn.limpiar")}"> <span class="label label-limpiar">{$.i18n.prop("btn.limpiar")}</span></a>
                    
                  </div>
                  </div>
                </div>  
                 
             </div>
            <div  class="contenedor-factura " >
                
                <div class="cabecera-izquierda">
                    <div class="row">
                        <div class="col-sx-12 col-sm-4 col-md-4 col-lg-4">
                            <label>Ingrese el Consecutivo</label>
                            <input onkeypress={__agregarArticulosFactura}  id="consecutivoFactura" class="form-control consecutivoFactura" type="text" placeholder="Consecutivo:XXXXXXXXXXXX" />
                            
                        </div>
                   
                            <div class="col-sx-12 col-sm-6 col-md-6 col-lg-2">
                                <button     onclick={__verificarFactura} class="btn-green btn-buscar " id="btn-facturar" >
                                    {$.i18n.prop("btn.consultar")} 
                                </button>
                            </div>
                    </div>
                    <br>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th style="width:5%;">                                                   </th>
                            <th><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.linea")}</div></th>
                            <th><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.codigo")}</label></th>
                            <th style="width:20%;"><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.descripcion")}</label> </th>
                            <th ><div class="tituloDetalle">{$.i18n.prop("factura.linea.detalle.cantidad")}</label></th>
                            <th ><div class="tituloDetalle">Cant. Aplicar</label></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr each={detail}>
                            <td>
                                <button  onclick={__removeProductFromDetail} class="btn btn-danger btn-xs btn-block">X</button>
                            </td>
                            <td>{numeroLinea}</td>
                            <td>{codigo}</td>
                            <td>{descripcion}</td>
                            <td class="text-right">
                                <label> {cantidad.toFixed(3)} </label>
                            </td>
                            <td class="text-right">
                                <input onclick={__CambiarCantidad} id= "cantidadDetalle" class="form-control " type="number" placeholder="Cantidad Detalle" value = {cantidad.toFixed(3)}  />
                            </td>
                        </tr>
                        </tbody>
                    </table>          
                </div>
               
                      
            </div><!-- fin contenedor-factura-->
            
        </div><!-- fin box-body-->
	</div><!-- fin box -->


 

<!--Modal Cambiar Cantidad-->
<div id='modalCambiarCantidad' class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
       <div class="modal-content">
            <div class="modal-header with-border " >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i>&nbsp;{$.i18n.prop("titulo.cambiar.cantidad")}</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sx-6 col-md-6 col-lg-6 col-sm-6">
                        <div class="form-group has-success">
                            <label >Cantidad:</label>
                            <input  type="number" class="form-control cambiarCantidadArticulo" id="cambiarCantidadArticulo" name = "cambiarCantidadArticulo" autofocus="autofocus">
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



<style type="text/css"  >
.btn-green-aplicar {
    background-color: #4cae4c;
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
#pagarTitulo {
    font-weight: 600 !important;
    font-size: 20px !important;
    font-family: Roboto,sans-serif !important;
    color: #ffffff !important;
    text-shadow: 0px 0px 1px #ffffff;
    font-style: italic;
    text-align: left;
    line-height: 30px;
}

.contenedor-factura {
  display:flex;
}
.campoDetalle {
    display: block;
    width: 100%;
    height: 34px;
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
    font-size: 18px;
    font-weight: 600;
    text-align: center;
}
.teclashift {
    font-weight: 700;
    font-size: 27px !important;
    text-align: center;
    color: red;

}
.teclaFuncion{

}
.opcionPrecioPublico{
    display: flex;
    flex-direction: column;
}
.tituloTipoCambio{
    color: #0c3f65 !important;
    float: left !important;
    font-size: 13px !important;
    background-color: transparent !important;
    font-weight: normal;
    text-align: left;
}
.ssCambioCentral{
    color: #0c3f65 !important;
    display: block;
    text-align: center;
        font-weight: 900;

}
.ssCambio{
    color: #0c3f65 !important;
    display: block;
    text-align: left;
}
@media (min-width: 992px){
.modal-lg {
    width: 1024px !important;
}
}
.facturaDiaContainer{
  display:flex;
}
  .tamanoClienteNuevo{
    font-size: 30px;
    font-weight: 600;
    color: black;
    height: 10%;

  }
  .tituloClienteNuevo{
    display: inline-block;
    max-width: 100%;
    margin-bottom: 5px;
    font-weight: 600;
    font-size: 30px;
    font-weight: 600;
    color: black;
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
   .campoLabel{
    font-size: 18px;
    font-weight: 600;
    text-align: center;
}
   .btn-green {
    background-color: #4cae4c;
    color: #FFF;
    border-radius: 5px;
    padding-bottom: 5px;
    padding-top: 5px;
    padding-left: 20px;
    padding-right: 20px;
    font-size: 12px !important;
    font-weight: bold;
    margin-right: 15px;
    border: none;
    float: right;
    margin-top: 26px;
    cursor: pointer;
}
    .botonesContainer{
       display:flex;
    }
    .boton{
       flex: 1;
    }
    .imagenesBilletes{
      height: 90px;
      width: 170px;
    }
   .pantallaBilletes{
       display:flex;

   }
   .billeteContainer{
       display:flex;
       flex-flow: wrap;
       flex-direction: row;
   }
   .billete{
    cursor: pointer;
    margin-right: 4%;
    margin-top: 2%;

   }
.input-group {
    position: relative;
    display: flex;
    border-collapse: separate;
}
     .input-group-addon.btnClientes {
       color: #66b12f;
        cursor: pointer;
    }
    .elementoTotalesChino{
        font-weight: 600 !important;
        font-size: 32px !important;
        font-family: Roboto,sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        margin-left: 4%;
        margin-bottom: 2%;
        margin-top: 2%;
        margin-right: 2%;
    }
    .elementoTotales{
        font-weight: 600 !important;
        font-size: 20px !important;
        font-family: Roboto,sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        align-items: center;
        text-align: left;
        margin-left: 2%;
    }
    .TotalesContainer{
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
    .contenedorExoneracion{

    }
    .label-totalesComprobanteChino {
        display: flex;
        flex: 1;
        font-weight: 600 !important;
        font-size: 37px !important;
        font-family: Roboto,sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        border-collapse: separate;
        cursor: pointer;
        margin: 2%!important;
        text-align: center !important;
        background-color: black !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20);
        border-radius: 25px !important;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
    }
    .titleListaPrecio{
        color:#0c3f65;
       
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

    .fondoVentaEspera{
        background: black;
        text-align: center;
        text-decoration: none;
        text-shadow: rgb(255, 255, 255) 0px 0px 1px;
        font-style: italic;
        color: #e2f312 !important;
        font-weight: 600;
        font-size: 14px;
        
    }
    .ventaEsperaSeleccionada{
        display: flex;
        padding-bottom: 0.2%;
    }  
    .ventaEsperaSeleccionada .tituloVentaEspera{
        color: yellow;
        background: black;
        font-weight: 700;
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

    .labelBotones {
        font-weight: 600 !important;
        font-size: 19px !important;
        font-family: Roboto,sans-serif !important;
        color: #ffffff !important;
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
    @media screen and (max-width: 1024px) {
    .labelBotones {
        font-size: 14px !important;
    }
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

    .botonesFuncionalContainer{
        display:flex;
        flex:1;

    }
    .contenedorFactura .cabecera-izquierda .botonesFuncionalContainer{
    display:flex;
    }

    .contenedorFactura .cabecera-izquierda .botonesFuncionalContainer .botonesFuncional{
    flex:1;
    padding-right: 1%;
    padding-bottom: 2%;
    }




    .gananciaContainer{
        display:flex;
        flex:1;
    }
    .gananciaContainer .formatoTituloGanancia{
        flex:1;
        color: black;
        font-size: 15px;
        font-weight: bolder;
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
        /* padding-left: 20px; */
        line-height: 30px;
        border-collapse: separate;
        text-align: center;
        cursor: pointer;
        /* padding: 5px; */
        /* margin: 5px; */
        /* border: none; */
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

    .tituloProductoIngresadoContainer{
        display:flex;
        flex:1;
    }
    .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado{
        flex: 1;
        display: flex;
        padding-left: 2%;
        padding-right: 2%;
    
    }




    .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_1{
        flex: 0.15;
        font-weight: 600 !important;
        font-size: 36px !important;
        font-family: Roboto,sans-serif !important;
        color: yellow !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        /* padding-left: 20px; */
        /* line-height: 100%; */
        /* border-collapse: separate; */
        text-align: center;
        /* margin: 5px; */
        border: none;
        text-align: center !important;
        background-color: black !important;
        /* box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20); */
        /* border-radius: 5.tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_1{px; */
        /* -webkit-transition: background-color 100ms linear; */
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        /* transition: background-color 100ms linear; */
    }

    .tituloProductoIngresadoContainer .tituloDescripcionProductoIngresado .ultimo_2{
        flex: 1;
        font-weight: 600 !important;
        font-size: 36px !important;
        font-family: Roboto,sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        /* padding-left: 20px; */
        /* line-height: 100%; */
        /* border-collapse: separate; */
        text-align: center;
        /* margin: 5px; */
        border: none;
        text-align: center !important;
        background-color: black !important;
        /* box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 8px 0 rgba(0, 0, 0, 0.20); */
        /* border-radius: 5px; */
        /* -webkit-transition: background-color 100ms linear; */
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        /* transition: background-color 100ms linear; */
    }



</style>    

<script>
    var self = this;
    // Detalle de la factura es una coleccion de articulos
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
	    estado:0,
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
    self.subTotalGeneral               = 0
    self.totalDescuentos               = 0
    self.totalImpuesto                 = 0
    self.totalComprobante              = 0
    self.totalCambioPagar              = 0
    self.tipoCambio = {
        total:0,
        id:null
    }
    self.on('mount',function(){
        _INIT()
        $("#formularioFactura").validate(reglasDeValidacionFactura());
        __comboCondicionPago()
        __ComboTipoDocumentos()
        __ComboEstados()
       __Teclas()
       __TipoCambio()
       __comboCondicionPagoRef()
       __Eventos()
        $(".nota").attr("maxlength", 80);
        $('.datepickerFechaEmision').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-30d',
              todayHighlight:true,
            }
        );
         window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
     
    })
__LimpiarFormulario(){
     _INIT()    
}
function _INIT(){
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
	    estado:0,
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
  $(".totalBanco").val(null)   
    $(".totalTarjeta").val(null)   
    $(".totalEfectivo").val(null)   


    $(".plazoCreditoL").val(null)   
    $(".fechaCredito").val(null)   
    $(".nota").val(null)  
    $(".nota").attr("maxlength", 80);
    $('.datepickerFechaEmision').datepicker(
            {
              format: 'yyyy-mm-dd',
              startDate: '-30d',
              todayHighlight:true,
            }
    ); 
    $(".direccion").val(null)   
    $('.referenciaRazon').val(null)
    $('.referenciaCodigo').prop("selectedIndex", 0);
    $('.condicionVenta').prop("selectedIndex", 0);
    $('.tipoDoc').prop("selectedIndex", 0);
    $("#referenciaRazon").attr("maxlength", 200);
    self.cliente               = {}
    self.vendedor              = {
        id:0,
        nombreCompleto:""
    };
    self.mostrarCamposIngresoContado = true

    self.update()
    __Eventos()

}
__formaReferencias(e){
    if($('#tipoDoc').val() !="01" && $('#tipoDoc').val() !="04"){
       self.mostrarReferencias            = true
       self.update()  
    }else{
        self.mostrarReferencias            = false
        self.update()
        $(".referenciaFechaEmision").val(null)
        $('.referenciaNumero').val(null)
        $('.referenciaRazon').val(null)
        $('.referenciaTipoDoc').prop("selectedIndex", 0);
        $('.referenciaCodigo').prop("selectedIndex", 0);
    }
}
/**
*  Actimpuestor validaciones del formulario
**/
function __Eventos(){
    $("#formularioFactura").validate(reglasDeValidacionFactura());
    $("#referenciaRazon").attr("maxlength", 240);
    $("#codigo").attr("maxlength", 20);
    $('#referenciaNumero').mask('00000000000000000000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
    });

     $('#consecutivoFactura').mask('00000000000000000000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
    });
    $('.referenciaFechaEmision').mask('0000-00-00');

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
             },
             referenciaRazon:{
                 required : true,
                 maxlength:240,
             },
             referenciaFechaEmision:{
                 required : true,
             },
             referenciaTipoDoc:{
                 required : true,
                 
             },
             referenciaNumero:{
                 required : true,
                 maxlength:20,
             }    

		},
		ignore : []

	});
	return validationOptions;
};
/**
Consultar el consecutivo que se hace referencia
**/
__consultarConsecutivo(e){
    if (e.keyCode != 13) {
        return;
    } 
    __referenciaConsecutivo(e.currentTarget.value);
}
/**
*  Informacion del consecutivo de la factura para las notas de creditos y debito
**/
function __referenciaConsecutivo(consecutivo){
    $(".referenciaFechaEmision").val(null)
    $('.referenciaTipoDoc').prop("selectedIndex", 0);
    $('.referenciaCodigo').prop("selectedIndex", 0);
     
   $.ajax({
        url: "ConsultarConsecutivoAjax",
        datatype: "json",
        data: {consecutivo:consecutivo},
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.factura.referenciaFechaEmision = modeloTabla.fechaEmision
                       self.factura.referenciaTipoDoc      = modeloTabla.referenciaTipoDoc
                      
                       self.update()
                        
                        $("referenciaFechaEmision").val(modeloTabla.fechaEmision)
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
    $('#modalCambiarDescuento').modal('show')      
}
/**
*Cambiar Cantidad del Articulo
**/
__CambiarCantidad(e){
   var cantidad = e.currentTarget.value;
   self.item = e.item; 
   self.update()
   $( "#cambiarCantidadArticulo" ).focus()
   $( "#cambiarCantidadArticulo" ).val(cantidad)
   $('#modalCambiarCantidad').modal('show')      
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
    
     var parametros = {
                          factura:self.factura,
                          facturaDia:0
                      }
                      console.log("consultaFactura")
                      riot.mount('ptv-imprimir',{parametros:parametros});
   
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
    $('.descArticulo').val(null)
    $('.codigoArt').val(null)
    $(".tableListarArticulos").dataTable().fnClearTable();
    $(".tableListarArticulos").DataTable().destroy();
    $('#modalInventario').modal('show')   
 }
/**
*  Buscar la Factura Pendiente en espera
**/
__CargarFacturaEspera(e){
    self.factura = e.item
    self.update()
   __FacturaEnEspera(e.item)
}
/**
** Se aplica o se crea una Factura cargada en la pantalla
**/
__AplicarYcrearFactura(){
 aplicarFactura()
}

/**
* Aplicar la factura
**/
function aplicarFactura(){
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
        if($('#estado').val() !=1){
            if(self.factura.totalTarjeta == 0 && self.factura.totalBanco == 0 && self.factura.totalEfectivo == 0){
                mensajeError($.i18n.prop("error.factura.monto.ingresado"))
                return
            }
            var montoEntregado = self.factura.totalTarjeta + self.factura.totalBanco + self.factura.totalEfectivo
                var resultado  = __valorNumerico(self.factura.totalComprobante)
                if(__valorNumerico(resultado) > __valorNumerico(montoEntregado)  ){
                    mensajeError($.i18n.prop("error.factura.monto.ingresado.es.menor.ala.venta"))
                    return
                }
            //Si el cliente esta pagando con tajeta, banco debe ser igual a la venta
                var tarjeta = __valorNumerico(self.factura.totalTarjeta)
                var banco = __valorNumerico(self.factura.totalBanco)
                if(tarjeta != 0 || banco !=0){
                if(self.factura.totalComprobante != montoEntregado  ){
                    mensajeError($.i18n.prop("error.factura.monto.tarjeta.banco.igual.venta"))
                return
                    
                }
            }
            
        }
    } 
    if ($("#formularioFactura").valid()) {
        swal({
           title: '',
           text: $.i18n.prop("factura.alert.crear"),
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
               crearFactura()  
              
            }
        });
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
    
    self.detail                = []
    self.mensajesBackEnd       = []
    self.error                 = false
    self.comboEstados          = []
    self.comboCondicionPagos        = []
    self.comboTipoDocumentos   = []
    self.facturas_espera       = {data:[]}  
    self.item                  = null;
    self.articulo              = null;
    self.detalleFactura        ={data:[]}
    self.cliente               = {};
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
    self.tipoCambio                    = {}
    self.informacion_tabla             = []
    self.informacion_tabla_articulo    = []
    self.informacion_tabla_clientes    = []
    self.idiomaDataTable               = {}
    self.mostrarFormularioPago         = false
    self.mostarParaCrearNuevaFactura    = true
    self.mostrarCamposIngresoContado   = true;
    self.subTotalGeneral               = 0
    self.totalDescuentos               = 0
    self.totalImpuesto                 = 0
    self.totalComprobante              = 0
    self.update();
    $(".totalBanco").val(null)   
    $(".totalTarjeta").val(null)   
    $(".totalEfectivo").val(null)   
    $(".referenciaNumero").val(null)
    $(".referenciaFechaEmision").val(null)
    $('.referenciaTipoDoc').prop("selectedIndex", 0);
    $('.referenciaCodigo').prop("selectedIndex", 0);
    $('#condicionVenta').prop("selectedIndex", 0);
    $('#tipoDoc').prop("selectedIndex", 0);
    $('#estado').prop("selectedIndex", 0);
    $("#plazoCredito").val(null)
    $("#nota").val(null)
    $("#fechaCredito").val(null)
    $("#cambiarCantidadArticulo").val(null)
    $("#aplicarDescuento").val(null)
    // Tipo de Pagos
     __comboCondicionPago()
     __comboCondicionPagoRef()
     //Tipos de Documentos
      __ComboTipoDocumentos()
      //Estados
      __ComboEstados()
}
/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(factura){
    if(factura == null){
        return
    }
     __Init()
    $.ajax({
        url: "ListarDetlleByFacturaAjax", 
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
}
/**
*  Cargar detalles Factura en espera
**/
function cargarDetallesFacturaEnEspera(data){
    self.detail = [];
    self.factura = null
    self.update()
     $.each(data, function( index, modeloTabla ) {
        if(self.factura  == null){
            self.factura = modeloTabla.factura
            self.factura.fechaCredito = self.factura.fechaCredito !=null?__displayDate_detail(self.factura.fechaCredito):null
            self.cliente  = modeloTabla.factura.cliente
            self.vendedor = modeloTabla.factura.vendedor
            self.update()
        } 
        self.detail.push({
            codigo          : modeloTabla.codigo,
            tipoImpuesto    : modeloTabla.tipoImpuesto,
            descripcion     : modeloTabla.descripcion,
            cantidad        : parseFloat(modeloTabla.cantidad),
            precioUnitario  : parseFloat(modeloTabla.precioUnitario),
            impuesto        : parseFloat(modeloTabla.impuesto),
            montoImpuesto   : parseFloat(modeloTabla.montoImpuesto),
            montoDescuento  : parseFloat(modeloTabla.montoDescuento),
            porcentajeDesc  : parseFloat(modeloTabla.porcentajeDesc),
            subTotal        : parseFloat(modeloTabla.subTotal),
            montoTotalLinea : parseFloat(modeloTabla.montoTotalLinea),
            montoTotal      : parseFloat(modeloTabla.montoTotal)
        });
        self.update()
    })
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
function formatoFechaHora(fecha) {
    return fecha == null?"":moment(fecha).format('YYYY-MM-DD HH:mm:ss');
}
/**
*  Crear Factura nueva
**/
function crearFactura(){
    self.detalleFactura.data =self.detail
    self.update() 
    var fechaCreditoTemporal =condicionVenta.value == "02"?fechaCredito.value:new Date() 
    var fechaReferencia =$('#referenciaFechaEmision').val() !=null?formatoFechaHora(referenciaFechaEmision.value):new Date() 
    var JSONDetalles = JSON.stringify( self.detalleFactura );
    self.factura.id = self.factura.id
    self.factura.condicionVenta = $('#condicionVenta').val()
    self.factura.fechaCredito =fechaCreditoTemporal.toString()
    self.factura.referenciaFechaEmision =fechaReferencia
    self.factura.totalEfectivo =redondearDecimales(__valorNumerico($('#totalEfectivo').val()))
    self.factura.totalTarjeta = redondearDecimales(__valorNumerico($('#totalTarjeta').val())) 
    self.factura.totalBanco = redondearDecimales(__valorNumerico($('#totalBanco').val()))
    self.factura.detalleFactura =JSONDetalles
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
                  var parametros = {
                          factura:modeloTabla,
                          facturaDia:0
                      }
                      console.log("consultaFactura")
                      riot.mount('ptv-imprimir',{parametros:parametros});
                 
            }else{
                swal({
	                title: '',
	                text: data.message,
	                type: 'success',
	                showCancelButton: false,
	                confirmButtonText: $.i18n.prop("btn.aceptar"),
                })
                __Init()
            }
        });
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
* Mostrar Pago
**/
function mostrarPAgo(){
     //No hay detalles registrados en la Factura
    if(self.detail.length == 0 ){
        swal("Verificar","No hay detalles en la factura ", "info")
        return
    }
    
    $('#totalEfectivo').val(null)
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    getSubTotalGeneral()
    self.factura.totalCambioPagar =0
    self.mostarParaCrearNuevaFactura = false
    self.mostrarFormularioPago = true
    self.update()
    $('#totalEfectivo').focus()
    self.factura.cambioMoneda = self.factura.totalVentaNeta / self.tipoCambio.total
    self.update()

}
/**
* Consultar el consecutivo de una factura para agregar los articulos relacionados
**/
__agregarArticulosFactura(e){
     if (e.keyCode != 13) {
        return;
    } 
    verificarConsecutivo()

}
__verificarFactura(){
  
    verificarConsecutivo()

}


function verificarConsecutivo(){
    var numero  = $('.consecutivoFactura').val()
    $.ajax({
        url: "ListarDetlleByFacturaConsecutivoAjax.do",
        datatype: "json",
        data: {consecutivo:numero},
        method:"POST",
        success: function (data) {
             if(data.aaData.length > 0){
                cargarDetallesFacturaEnEspera(data.aaData)
                self.factura.referenciaNumero = numero
                self.factura.totalTarjeta = 0 
                self.factura.totalBanco = 0 
                self.factura.totalEfectivo = 0
                self.update()
                $('.referenciaNumero').val(numero)
                if(self.factura.estado ==5){
                    sweetAlert("", "Ya se encuentra anulada", "error");
                    return
                }
                if(self.factura.id == null){
                    sweetAlert("", $.i18n.prop("error.factura.no.existe"), "error");
                }else{
                    self.factura.id= null
                    self.update()
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}
/** 
*
*Agregar codigos al detalle de la Factura
*
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
    $('#codigo').val(null)
    $('#codigo').focus()
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
   __buscarcodigo($( "#codigo" ).val(),1);
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
                __agregarArticulos()
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
* Buscar el codigo del codigo  en la base de datos
**/
function __buscarcodigo(idArticulo,cantidad){
  //  self.articulo = null;
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
                        //Articulo no puede agregarse si no hay en el inventario
                       
                        self.articulo  = modeloTabla
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
              self.update();
            }
        }
    
    }
    // si no existe se agrega como un codigo nuevo
    if(encontrado == false){ // add elemen
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
* Actualizar Monto Total
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
    agregarCantidadAlaVenta(cantidad)
    
  }
/**
* Monto del descuento
**/
function getMontoDescuento(precioUnitario,cantidad,porcentajeDesc){
    var porcentaje = porcentajeDesc / 100;
    var total =  precioUnitario * cantidad
    var resultado = total * porcentaje
    return resultado
}
/**
* Actualizar la linea de detalle
**/
function ActualizarLineaDEtalle(){
  var montoTotal             = getMontoTotal(self.item.precioUnitario,self.item.cantidad)
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
* Agregar la cantidad nueva
**/
function agregarCantidadAlaVenta(cantidad){
    self.item.cantidadNotaCredito = cantidad
    self.update()
    ActualizarLineaDEtalle()
    aplicarCambioLineaDetalle() 
    cambiarCantidadArticulo.value = 0
    $('#modalCambiarCantidad').modal('hide') 
}
/**
* Actualizar el cambio de linea de detalle
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
* Actualizar el Descuento
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
    self.factura.total           = 0;
    self.factura.totalDescuentos  = 0;
    self.factura.totalImpuesto   = 0;
    self.factura.subTotal        = 0;
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
    self.factura.totalMercanciasGravadas = redondearDecimales(__valorNumerico(totalMercanciasGravadas),5)
    self.factura.totalMercanciasExentas  = redondearDecimales(__valorNumerico(totalMercanciasExentas),5)
    self.factura.totalServGravados       = redondearDecimales(__valorNumerico(totalServGravados),5)
    self.factura.totalServExentos        = redondearDecimales(__valorNumerico(totalServExentos),5)
    self.factura.totalGravado            = redondearDecimales(__valorNumerico(totalGravado),5)
    self.factura.totalExento             = redondearDecimales(__valorNumerico(totalExento),5)
    self.factura.totalVenta              = redondearDecimales(__valorNumerico(totalVenta),5)
    self.factura.totalDescuentos          = redondearDecimales(__valorNumerico(totalDescuento),5)
    self.factura.subTotal                = redondearDecimales(__valorNumerico(subTotal),5)
    self.factura.totalImpuesto           = redondearDecimales(__valorNumerico(totalImpuesto),5)
    self.factura.totalVentaNeta          = redondearDecimales(__valorNumerico(totalVenta-totalDescuento),5)
    self.factura.totalComprobante        = redondearDecimales(__valorNumerico(totalComprobante),5)
    self.totalComprobante                = formatoDecimales(self.factura.totalComprobante,2);
    self.totalDescuentos                 = formatoDecimales(self.factura.totalDescuentos,2);
    self.totalImpuesto                   = formatoDecimales(self.factura.totalImpuesto,2);  
    self.update(); 
    $( "#codigo" ).val(null);
    $( "#quantity" ).val(null);
    getSubTotalGeneral()
}
/**
* Actualizar el Total General
**/
function getSubTotalGeneral(){
    var resultado = __valorNumerico(self.factura.subTotal) + __valorNumerico(self.factura.totalDescuentos)
    self.subTotalGeneral = redondearDecimales(resultado,5)
    self.update()
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
* cargar los codigos de referencias
**/
function __comboCondicionPagoRef(){
     self.codigosReferencias = []
    self.update()
    self.codigosReferencias.push({
        estado:"01",
        descripcion:$.i18n.prop("referencia.anula.documento")
    })
    self.codigosReferencias.push({
        estado:"03",
        descripcion:$.i18n.prop("referencia.corrige.monto.documento")
    })   
    self.update()
}
/**
* cargar los tipos de Documento de la factura
**/
function __ComboTipoDocumentos(){
    self.comboTipoDocumentos = []
    self.comboTipoDocumentosRef = []
    self.update()
    self.comboTipoDocumentos.push({
         estado:"03",
        descripcion:$.i18n.prop("factura.tipo.documento.nota.credito")
    })
    
   self.comboTipoDocumentosRef.push({
         estado:"01",
        descripcion:$.i18n.prop("referencia.tipo.documento.factura.electronica")
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
        if ( $(this).index() != 6    ){
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
        if ( $(this).index() != 5    ){
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
        mostrarPAgo()     
    }   
    }, false );
}
</script>
</debito-credito>