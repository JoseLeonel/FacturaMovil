<proveedor-articulo>

   
<div show={mostrarPantalla == true}>
    <div class="row center container-fluid"  >
        <div class="col-md-12 col-lg-12 col-sm-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 show = {botonAgregar} class="box-title"><i class="fa fa-edit"></i>&nbsp {$.i18n.prop("titulo.agregar.proveedorArticulo")}     </h1>
                    <h1 show={botonModificar} class="box-title"><i class="fa fa-edit"></i>&nbsp {$.i18n.prop("titulo.modificar.proveedorArticulo")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{proveedorArticulo.id}">
                        <input type="hidden" id="proveedor" name="proveedor" value="{parametros.idProveedor}"  >
                        <input type="hidden" id="articulo" name="articulo" value="{articulo.id}"  >
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales" >{$.i18n.prop("articulo.codigo")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="campo codigo" id="codigo" name="codigo" value="{articulo.codigo}"  onkeypress={__addPrecioDetail} autofocus="autofocus"  >
                                <label class="tamanoLetraTotales" >{articulo.descripcion}  </label>
                            </div>
                            
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4 has-success">
                                <label class="tamanoLetraTotales"  >{$.i18n.prop("articulo.costo")} </label>
                                <input type="number" step="any" class="campo costo" id="costo" name="costo" value="{proveedorArticulo.costo}"  onkeyup ={__ActualizarPreciosCosto}>
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                    <button show = {botonAgregar} title="Agregar un Nuevo Articulo"  onclick={__agregar}   class="btn-green btn-add pull-right" >&nbsp Nuevo</button>
                    <button  onclick={__Modificar} title="modificar el Articulo" show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
                  
                </div>
            </div>   
        </div>
       
    </div>
</div>
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
    self.botonModificar            = false
    self.botonAgregar              = true
    self.mostrarTituloArticulo     = true
    self.mostrarPantalla = true
    self.actualizar = false
    self.proveedorArticulo = {
        id:null,
        codigo:"",
        descripcion:"",
        costo:0

    }
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
self.on('mount',function(){
    console.log(self.parametros.proveedorArticulo)
    __Eventos()
    LimpiarArticulo()
    if(self.parametros.tipoEjecucion = 2){
      //modificar
      self.botonModificar            = true
      self.botonAgregar              = false
      self.mostrarTituloArticulo     = true
      self.articulo = self.parametros.proveedorArticulo.articulo
      self.proveedorArticulo = self.parametros.proveedorArticulo
      self.update()
      document.getElementById('codigo').readOnly = true;
     
    }else{
        
        document.getElementById('codigo').readOnly = false;
    }
    
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
})

__regresarAlListado(){
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarTituloArticulo     = false
    self.mostrarPantalla = false
    self.update()
    //proveedorArticulo.js
    __mostrarRegresarAlListado()
}

/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
 __addPrecioDetail(e){
    if (e.keyCode != 13) {
        return;
    } 
     var codigo = $('#codigo').val()
     
    var parametros = {
        codigo:$('.codigo').val(),
        precioPublico : 0
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
                   
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                    //desahabilita  listado 
                        LimpiarArticulo()
                        self.articulo = modeloTabla
                        if(self.articulo !=null){
                            self.botonModificar            = false
                            self.botonAgregar              = true
                        }
                        self.update()
                        $('.codigo').val(modeloTabla.codigo)
                        $('.descripcion').val(modeloTabla.descripcion)
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
   $('.codigo').val(null)
   $('.descripcion').val(null)
   $('.costo').val(0)
   $(".errorServerSideJgrid").remove();
   $("#formulario").validate(reglasDeValidacion());
}


/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
		   codigo : {
				required : true,
                maxlength:20,
                minlength:1,
                lettersOnly : true
			},                                                
            costo : {
				required : true,
                numeroMayorCero:true,
                number:true,
			} ,                                                
                                                   
                        
		},
		ignore : []

	});
	return validationOptions;
};


/**
*  Actimpuestor validaciones del formulario
**/
function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#descripcion").attr("maxlength", 80);
    $("#codigo").attr("maxlength", 20);
    

}
/**
*   Agregar 
**/
__agregar(){
        if ($("#formulario").valid()) {
            
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formulario").serialize();
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : 'AgregarProveedorArticuloAjax.do',
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
                            LimpiarArticulo()
                              __Eventos()
                            self.botonModificar            = false
                            self.botonAgregar              = false
                            self.mostrarTituloArticulo     = false
                            self.mostrarPantalla = false
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
** Modificar la Empresa
**/
__Modificar(){
    
    self.error = false;
    self.exito = false;
    self.update();
    if ($("#formulario").valid()) {
        var formulario = $("#formulario").serialize();
        $.ajax({
            type : "POST",
            dataType : "json",
            data : formulario,
            url : "modificarProveedorArticuloAjax.do",
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
                    serverMessageJson(data)
                    swal({
	               	    title: '',
	                    text: data.message,
	                    type: 'success',
	                    showCancelButton: false,
	                    confirmButtonText: 'Aceptar',
	                })
                    self.botonModificar            = false
                    self.botonAgregar              = false
                    self.mostrarTituloArticulo     = false
                    self.mostrarPantalla = false
                    self.update()
                    __mostrarListado()
                }
            },
            error : function(xhr, status) {
                 mensajeErrorServidor(xhr, status);
            }
        });
    }
}





</script>

</proveedor-articulo>