<ver-articulo>
  
<div id='modalConfiguracion' class="modal fade  " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" width=100%;>
    <div class="modal-dialog" style ="width: 80%;">
        <div class="modal-content">
            <div class="modal-header with-border encabezado-pantalla " >
                <h1 class="modal-title modalTitleCambioPrecio" id="title-add-note"> <i class='fa fa-cal '></i> Ver articulo/
見文章</h1>
            </div>
            <div class="modal-body">
                
                    <div class="container-codigo">    
                        <div class= "elemento-codigo"></div>
                        <div class= "elemento-codigo">
                            <span class="" >Digite el codigo del producto/輸入產品代碼  </span>
                            <input onclick={_clickCodigo}   type="text" class="codigoVerPrecio"  id="codigoVerPrecio" name="codigoVerPrecio" autofocus="autofocus"   autocomplete="off" placeholder="XXXXXXXXXXXXXXXXXX">
                        </div>                            
                        <div class= "elemento-codigo"></div>
                    </div>
                    <div class="container-info">    
                        <div class= "elemen-descripcion"></div>
                        <div class= "elemen-descripcion">
                        
                            <span class="" >{articuloMostrar.descripcion} </span>
                            
                        </div>                            
                        <div class= "elemen-descripcion"></div>
                    </div>
                     <div class="container-precio">  
                      <div class= "elemen-precio"></div>  
                        <div class= "elemen-precio">
                            <span class="" >{precioPublico}</span>
                        </div>     
                         <div class= "elemen-precio"></div>                       
                    </div>
               

            </div>
            <div class="modal-footer">
                <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                    <button onclick={__Regresar}   type="button" class="btn-dark-gray btn-back  pull-left modalCambioPrecioBotones"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                </div>
               
            </div>
        </div>
    </div>
</div>




<style type ="text/css">
.container-codigo{
   display:flex;

}
.container-codigo > .elemento-codigo{
    display: flex;
    flex-direction: column;
    flex: 1;
}
.container-codigo > .elemento-codigo > span{
    font-size: 30px;
    font-weight: 500;
}

.container-codigo > .elemento-codigo > input{
    font-size: 40px!important;
    font-weight: 800!important;
}
.container-info > .elemen-descripcion > span{
    color: #66b12f;
    font-size: 45px;
    font-weight: 800;
    flex: 2;
}
.container-info{
    display: flex;
    flex-direction: column;
    
    justify-content: right;
    text-align: center;
}
    
.container-precio{
    border: 2px solid #282626;
    border-radius: 10px 10px;
    background: black;
    padding: 20px;
    font-family: Roboto,sans-serif !important;
    flex: 1;
    border-collapse: separate;
    text-align: center !important;
    box-shadow: 0 0px 22px 0 rgb(0 0 0 / 10%), 0 13px 8px 0 rgb(0 0 0 / 20%);
    border-radius: 7px !important;
        height: 28vh;
}
  
.container-precio >  .elemen-precio{
    display: flex;
    flex: 1;
}

.container-precio >  .elemen-precio > span{
      
   font-weight: 800;
    color: darkblue;
    position: relative;
    color: #30ed17 !important;
    font-size: 124px !important;
    left: 325px;
    top: -20px;
}
.elemen-precio{
       justify-content: flex-start;
}

    </style>
<script>
    var self = this;
    self.parametros   = opts.parametros;
    self.mostrarVerDetalle         = false
    self.mostrarConsultaComanda    = false
    self.articuloMostrar = {
        codigo:"",
        descripcion:"",
        precioPublico :0
    }
   self.precioPublico = 0;
self.on('mount',function(){
    
    $(".errorServerSideJgrid").remove();
    __consultar()
    getPosicionInputCodigo()
    $(".codigoVerPrecio").val(null)
     var xTriggered = 0;
        $( "#codigoVerPrecio" ).keyup(function( event ) {
            xTriggered++;
            var msg = "Handler for .keyup() called " + xTriggered + " time(s).";
        }).keydown(function( event ) {
            if ( event.which == 13 ) {
                lecturaCodigo($('.codigoVerPrecio').val())
            }
          
            
        });
        

})
_clickCodigo(){
    getPosicionInputCodigo()
}
function getPosicionInputCodigo(){
   
  //  $('.codigoVerPrecio').val("XXXXXXXXXXXXXXXXXXXX")
    $('.codigoVerPrecio').focus()
     
      $('.codigoVerPrecio').select()
}



function lecturaCodigo(idArticulo){
  self.articuloMostrar = {
        codigo:"",
        descripcion:"",
        precioPublico :0
    }
    self.precioPublico = 0
    self.update()
    var moneda = self.parametros.codigoMoneda == "USD"?1:0;
    obtenerArticulo(idArticulo).then(r =>{
     self.articuloMostrar =  r;
     self.precioPublico = formatMiles(self.articuloMostrar.precioPublico,moneda)
     self.update()
     unBlockUIStop();
     $('.codigoVerPrecio').focus()
      $('.codigoVerPrecio').select()
    }).catch(() => {
        unBlockUIStop();
     console.log('Algo salió mal');
     self.articuloMostrar = {
        codigo:"",
        descripcion:"No existe el producto/產品不存在",
        precioPublico :0
    }
    self.precioPublico = 0
    self.update()
     $('.codigoVerPrecio').focus()
      $('.codigoVerPrecio').select()
    });

    
}

__Regresar(){
    hidemodal()
}

function hidemodal(){
    $('#modalConfiguracion').modal('hide')
}


/**
*  Regresar al listado
**/
__regresarAlListado(){
    self.mostrarListado     = true;
    self.botonAgregar       = false;
    self.mostrarFormulario  = false 
    self.update()
    __listado();
}
function showModal(){
    $('#modalConfiguracion').modal({backdrop: 'static', keyboard: true}) 
    $('#modalConfiguracion').on('shown.bs.modal', function () {
         $(".codigoVerPrecio").val("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx")
        $(".codigoVerPrecio").focus()
        $(".codigoVerPrecio").select()
    })  

}
/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(){
  showModal();
}







</script>
</ver-articulo>