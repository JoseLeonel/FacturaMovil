<resultado-hacienda>

    
<!-- Modal -->
<div class="modal fade" id="ModalCorreoAlternativo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
          
                <h1 class="box-title"><i class="btn-correo"></i>&nbsp {$.i18n.prop("hacienda.titulo.correo.alternativo")}     </h1>
          
      </div>
      <div class="modal-body">
        <form id = "formulario" name ="formulario "   class="advanced-search-form">
            <div class="row">   
                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                    <label class="knob-label" >{$.i18n.prop("hacienda.correo")}</label>
                    <input type="email" class="form-control correoAlternativo" placeHolder ="{$.i18n.prop("hacienda.correo.ejemplo")}" id="correoAlternativo" name="correoAlternativo" value=""  >
                </div>
            </div>
        </form>
      </div>
      <div class="modal-footer">
        <div class="row">
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                    {$.i18n.prop("btn.volver")}
                </button>
            </div>
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                <button  onclick={__Enviar}   class="btn-green btn-correo pull-right" >  {$.i18n.prop("btn.enviar.correo")}</button>
            </div>
         </div>
       
      </div>
    </div>
  </div>
</div>

<style type="text/css">
.clickable {
    cursor: pointer;
}
</style>
<script>
    self                     = this
    self.parametros          = opts.parametros;  
    self.detail              = []
    self.mostrarListado      = true
    self.mostrarDetalle      = false
    self.hacienda            = {}
    self.on('mount',function(){
       $("#formulario").validate(reglasDeValidacion());
       //Enviar correo Alternativo
       if(self.parametros.tipoEjecucion == 1){
           self.hacienda = self.parametros.hacienda
         __EnviarAlternativo()
       }
        window.addEventListener( "keydown", function(evento){
            $(".errorServerSideJgrid").remove();
        }, false );
    })
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			correoAlternativo : {
				required : true,
                email:true,
                maxlength:240,
                minlength:1,
			}                                   
		},
		ignore : []
	});
	return validationOptions;
};
/**
*  Correo alternativo
**/
function __EnviarAlternativo(){
    
    $('#ModalCorreoAlternativo').modal({
	    backdrop: 'static',
	    keyboard: false
	})
	$('.correoAlternativo').val(null)
    $('#ModalCorreoAlternativo').modal('show')    
}
/**
* Enviar el correo
**/
__Enviar(){
    if ($("#formulario").valid()) {
        enviarCorreo()
    }
}
/**
* Enviar correo
**/
function enviarCorreo(){
	$.ajax({
		 url: "EnviarCorreoAlternativoAjax",
		 datatype: "json",
		 data: {idHacienda:self.hacienda.id,correo:$('.correoAlternativo').val()},
		 method:"GET",
		 success: function (data) {
			  if (data.status != 200) {
					if (data.message != null && data.message.length > 0) {
						 sweetAlert("", data.message, "error");
					}
			  }else{
					sweetAlert("", data.message, "info");
			  }
			  
		 },
		 error: function (xhr, status) {
			  mensajeErrorServidor(xhr, status);
			  console.log(xhr);
		 }
	});
}
/**
*  Regresar al listado
**/
__regresarAlListado(){
    $('#ModalCorreoAlternativo').modal('hide')   
}


</script>
</resultado-hacienda>