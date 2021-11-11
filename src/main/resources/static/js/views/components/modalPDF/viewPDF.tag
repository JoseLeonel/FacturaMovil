<view-pdf>
    <div class="modal fade" id="mostrarPDFVIEW" tabindex="-1" role="dialog" aria-labelledby="mostraPDFModalLabel" aria-hidden="true">
        <div class="modal-dialog {stylemodal}">
            <div class="modal-content ">
                <div class="modal-header">
                    <div id = "titulo-viewPDF"> 
                        <span> Documento </span>
                    </div>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>    
                <div class="modal-body">
                    <iframe  style="top:0;left:0;width: 100%; height: 500px" id="loadPdf" src="">
                     <p style="font-size: 110%;"><em><strong>ERROR: </strong>  
                            An &#105;frame should be displayed here but your browser version does not support &#105;frames. </em>Please update your browser to its most recent version and try again.</p>
                    </iframe>
                    
                </div>
                <div class="modal-footer">
                    <button  onclick={deleteModalViewPDF} type="button" class="btn-dark-gray btn-back pull-left"  >{$.i18n.prop("btn.volver")}</button>
                </div>
            </div>    
        </div>
    </div>
<style type ="text/css">
.modal-header > span{
    font-size: 18px;
    font-weight: 600;
    text-align: center;

}
#titulo-viewPDF{
  display:flex;
}

#titulo-viewPDF > span{
  font-size: 30px;
  font-weight: 800;
}
.modal-dialog {
    width: 300vh% !important;
}


</style>
<script>
var selfView = this;
selfView.stylemodal = 'modal-medium'
selfView.datos   = opts.datos;  
selfView.on('mount',function(){
    if(typeof selfView.datos.direccion != 'undefined'){
       mostarModal()
    }
})


deleteModalViewPDF(){
   // $( '#mostrarPDF' ).remove();
   //$("#mostrarPDF .close").click()
   
   $('#mostrarPDFVIEW').modal('hide')
   $('.modal-backdrop').remove();//eliminamos el backdrop del modal
   $('#mostrarPDFVIEW').remove();//eliminamos el backdrop del modal
   
    

}

function mostarModal(){
    
    if($('#mostrarPDFVIEW').is(':visible')){
        return
    }
    var href =  selfView.datos.direccion + '&t=' + $.now() 
    //location.href = "PDFGondolaAjax.do?idArticulo=" + data.id
    selfView.stylemodal = 	selfView.datos.stylemodal
    selfView.update()	
   
	$('#loadPdf').attr("src", href );	

    $("#mostrarPDFVIEW").modal("show");
    //document.getElementById('loadPdf').contentWindow.print();
     
	
}




</script>
</view-pdf>