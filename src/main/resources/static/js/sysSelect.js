function sysSelect(htmlId, object, identifier, name, searchable){
  //Si el SysSelect no existe
  if($("#" + htmlId.attr("id") + "-sysSelect").length == 0){
    //Inicia Armado de Contenedor de SysSelect
    var htmlSysSelect = "";

    htmlSysSelect += "<div id='"+htmlId.attr("id")+"-sysSelect' class='sysSelectContainer hide'>";
    if(searchable == true) htmlSysSelect += "<input class='sysSelectSearch' placeholder='"+object.length+" registros' type='text'>";
    htmlSysSelect += "<div class='options'>";

    $.each(object, function(key, value) {
      htmlSysSelect += "<span id='"+value[identifier]+"' name='"+value[name]+"'>"+value[name]+"</span>";
    })

    htmlSysSelect += "</div></div>";

    //Agrega el Contenedor del SysSelect al final del Documento
    $("body").append(htmlSysSelect);

    //Agrega el Listener a las Opciones
    $("#" + htmlId.attr("id") + "-sysSelect .options span").click(sysSelectOptionsClick);
  }

  htmlId.each(function(){
    $(this).addClass("hiddenInput");
    $(this).parent().find("input").remove();
    
    var required = htmlId.attr("required") != undefined ? htmlId.attr("required") : "";

    if(htmlId.find(":selected").val()){
      var filteredObject = object.filter(a=>a.id == htmlId.find(":selected").val());
      $(this).after("<input id='"+htmlId.attr("id")+"-input' name='"+htmlId.attr("id")+"-input' " + required + " type='text' style='margin-top: 0px;' value='"+filteredObject[0].nombre+"'>");
    }else{
      $(this).after("<input id='"+htmlId.attr("id")+"-input' name='"+htmlId.attr("id")+"-input' " + required + " type='text' style='margin-top: 0px;'>");
    }
    $(this).parent().find("input").click(sysSelectClick);
  })

  if(searchable == true){
    $("#" + htmlId.attr("id") + "-sysSelect .sysSelectSearch").keyup(function() {
      var filteredObject = object.filter(a=>a[name].toUpperCase().includes($(this).val().toUpperCase()));

      var options = "";
      $("#" + htmlId.attr("id") + "-sysSelect .options").html("");
      $.each(filteredObject, function(key, value) {
        options += "<span id='"+value[identifier]+"' name='"+value[name]+"'>"+value[name]+"</span>";
      })

      $("#" + htmlId.attr("id") + "-sysSelect .options").append(options);
      $("#" + htmlId.attr("id") + "-sysSelect .options span").unbind();
      $("#" + htmlId.attr("id") + "-sysSelect .options span").click(sysSelectOptionsClick);
    });
  }

  //Cerrar cuando se hace click afuera
  document.addEventListener("click", function(evt) {
    var thisSysSelect = htmlId.attr("id") + "-sysSelect";
    var thisSysInput = htmlId.attr("id") + "-input";
    var thisSelect = htmlId.attr("id");
    var targetElement = $(evt.target).attr("id");
    var parentTarget = $(evt.target).parent().attr("id");

    if (targetElement == thisSelect || targetElement == thisSysSelect || parentTarget == thisSysSelect || targetElement == thisSysInput) {
    }else{
      $("#" + htmlId.attr("id") + "-sysSelect").addClass("hide");
    }
  });
}
function sysSelectOptionsClick(){
	  sysInput = $("#" + $(this).parent().parent().attr("id").replace('-sysSelect','') + "-input[rel='1']");
	  sysRealSelect = sysInput.parent().find("select");

	  sysInput.val($(this).attr("name"));
	  sysRealSelect.html("<option value='"+$(this).attr("id")+"' selected hidden>"+$(this).attr("name")+"</option>");
	  $(this).parent().parent().addClass("hide");
	  sysInput.attr("rel", "2");
	}

function sysSelectClick(){
	  var sysSelect = $("#" + $(this).attr("id").replace("-input","") + "-sysSelect");
	  var sysSelectSearch = $("#" + $(this).attr("id").replace("-input","") + "-sysSelect .sysSelectSearch");
	  $(this).attr("rel", "1");
	  sysSelect.css("top", $(this).offset().top);
	  sysSelect.css("left", $(this).offset().left);
	  sysSelect.toggleClass("hide");
	  if(sysSelectSearch) sysSelectSearch.focus();
	}


	$.fn.extend({
	  sysSelect: function(object, identifier, name, searchable) {
	    sysSelect($(this), object, identifier, name, searchable);
	  }
	});