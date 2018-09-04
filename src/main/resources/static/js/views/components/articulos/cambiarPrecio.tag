<cambiar-precio>
 <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-cog "></i>&nbsp {$.i18n.prop("titulo.cambiar.precio")}  </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>

    <div class="row center "  >
   
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>    </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{articulo.id}">
                        <div class="alert alert-primary" role="alert">
                            <h1   class="label-articulos">{articulo.descripcion}</h1>
                        </div>
                                
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12 has-success">
                                <label class="letra" >{$.i18n.prop("articulo.codigo")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="campo codigo" id="codigo" name="codigo" onkeypress={__addPrecioDetail} autofocus="autofocus"   >
                            </div>
                        </div>
                       
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12 has-success">
                                <label class="letra" >{$.i18n.prop("articulo.precioPublico")}  <span class="requeridoDato">*</span></label>
                                <input type="number" step="any" class=" campo precioPublico" id="precioPublico" name="precioPublico"  value="{articulo.precioPublico}"  >
                            </div> 
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                        <button  onclick={__cambiarPrecio} class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
                  
                </div>
            </div>   
        </div>
       
    </div>


<style type ="text/css">
.label-articulos{
        font-weight: 600 !important;
        font-size: 40px !important;
        font-family: Roboto,sans-serif !important;
        color: #30ed17 !important;
        text-shadow: 0px 0px 1px #ffffff;
        font-style: italic;
        text-align: left;
        padding-left: 20px;
        line-height: 30px;
        border-collapse: separate;
        text-align: center;
        cursor: pointer;
        padding: 15px;
        margin: 25px;
        border: none;
        text-align: center !important;
        background-color: black !important;
        box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.1), 0 6px 14px 0 rgba(0, 0, 0, 0.20);
        border-radius: 10px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
        
    }
    .fondoEncabezado {
        background: #00539B;
        color: #f9fafc;
    }
  .requeridoDato {
            color: red;
            text-align: left;
            font-weight: 500;
            font-size: 40px;
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
            font-size: 30px !important;
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
      

 .campo {
    border-radius: 0;
    box-shadow: none;
    border-color: #d2d6de;
}
.letra {
     font: 40px verdana, arial, helvetica, sans-serif;
}
.campo {
    display: block;
    width: 100%;
    height: 50px;
    padding: 15px 25px;
    font-size: 40px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 6px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
     background-color: #fcfcfc;
    border: 1px solid #ccc;
    font: 40px verdana, arial, helvetica, sans-serif;
    margin: 2px 0;
    padding: 2px 4px;
    overflow: visible;
}

    </style>
<script>
    var self = this;

self.on('mount',function(){
    $("#formulario").validate(reglasDeValidacion());   
    $(".codigo").focus() 
     window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
    
});

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
			},                                                
            precioPublico : {
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
* Limpiar Articulo
**/
function Limpiar(){
    $('.codigo').val(null)
    $('.descripcion').val(null)
    $('.precioPublico').val(null)
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
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                    //desahabilita  listado 
                        Limpiar()
                        self.articulo = modeloTabla
                        self.update()
                        $('.codigo').val(modeloTabla.codigo)
                        $('.descripcion').val(modeloTabla.descripcion)
                        $('.precioPublico').val(modeloTabla.precioPublico)
                        $(".precioPublico").focus() 
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



__cambiarPrecio(){
    if ($("#formulario").valid()) {
     var codigo = $('#codigo').val()
     
    var parametros = {
        codigo:$('.codigo').val(),
        precioPublico : $('.precioPublico').val()
    }
    $.ajax({
        url: "CambiarPrecioAjax",
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
                        Limpiar()
                        self.articulo = modeloTabla
                        self.update()
                        $('.codigo').val(modeloTabla.codigo)
                        $('.descripcion').val(modeloTabla.descripcion)
                        $('.precioPublico').val(modeloTabla.precioPublico)
                    });
                    $(".codigo").focus() 
                    $(".codigo").focus() 

                               swal({
	                           title: '',
	                           text: data.message,
	                           type: 'success',
	                           showCancelButton: false,
	                           confirmButtonText: 'Aceptar',
	                                	  
	                         })
                              
                }
            }
            
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
    }
}


</script>    

</cambiar-precio>