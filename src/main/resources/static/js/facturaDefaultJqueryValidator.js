var formValidationDefaults = {
        errorClass: 'errorServerSideJgrid',
        highlight: function(element) {
                $(element).parent().removeClass("errorServerSideJgrid");
        },
        unhighlight: function(element) {
            $(element).parent().removeClass("errorServerSideJgrid");
        },
        ignoreTitle: true
};


jQuery.validator.addMethod("validFecha", function(value, element) {
    return this.optional(element) || moment(value,"YYYY-MM-DD hh:mm:ss ").isValid();
}, "Ingrese una fecha valida con el formato correcto YYYY-MM-DD hh:mm:ss Ejemplo:2019-07-02 08:10:15");

jQuery.extend(jQuery.validator.messages, {
    required: "Campo requerido.",  
    email: "Formato de correo inv\u00E1lido",
    url: "Ingrese una URL v\u00E1lida.",
    date: "Ingrese fecha v\u00E1lida.",  
    number: "Ingrese n\u00FAmero v\u00E1lido",
    digits: "Ingrese solo d\u00EDgitos.",    
    equalTo: "Ingrese el mismo valor de nuevo.",  
    maxlength: jQuery.validator.format("Ingrese {0} caracteres."),
    minlength: jQuery.validator.format("Ingrese al menos {0} caracteres."),
    rangelength: jQuery.validator.format("Ingrese un valor entre {0} y {1} caracteres."),
    range: jQuery.validator.format("Ingrese un valor entre {0} y {1} caracteres."),
    max: jQuery.validator.format("Ingrese un valor menor o igual a {0} caracteres."),
    min: jQuery.validator.format("Ingrese un valor mayor o igual a {0} caracteres.")
});

$.validator.addMethod("emailFormat",
        function (value, element){
                return this.optional(element) || /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,})(\]?)+$/ig.test(value);   
        },
        "Formato de correo inv\u00E1lido"
);


$.validator.addMethod("lettersOnly",
        function(value, element) {
                return this.optional(element) || /^[0-9,a-z,A-Z,Ñ,ñ,Á,á,É,é,Í,í,Ó,ó,Ú,ú,Ü,ü,.,%,$,*,(,),ñ,!,/,+,*,#,	\s]+$/ig.test(value);
        },
        "Ingrese solo letras , numeros  o espacios % $ + * . / % $ * ( ) #"
);


$.validator.addMethod("telefonoFormat",
        function(value, element) {
	       
		    return this.optional(element) || value.length == 8;
        },
        "Formato v\u00E1lido. Ejemplo: 88888888"
);

$.validator.addMethod("numeroMayorCero",
        function(value, element) {
	       
		    return value  > 0 || /^[1-9]\d*(\.\d+)?$/ig.test(value);
        },
        "Ingrese un n\u00FAmero mayor a cero."
);

$.validator.addMethod("numeroMayorIgualCero",
        function(value, element) {
	       
		    return value  >= 0 || /^[1-9]\d*(\.\d+)?$/ig.test(value);
        },
        "Ingrese un n\u00FAmero mayor o igual a cero."
);

$.validator.addMethod("porcentajes",
        function(value, element) {
	       
		    return value <= 100 || /^[1-9]\d*(\.\d+)?$/ig.test(value);
        },
        "Porcentaje menor o igual al 100%."
);
				 
 

function noNumeros(element){
        var last = element.val().substr(element.val().indexOf("-") + 1 );                  
    if( last.length == 3 ) {
        var move = element.val().substr( element.val().indexOf("-") - 1, 1 );
        var lastfour = move + last;                    
        var first = element.val().substr( 0, 9 );                      
        element.val( first + '-' + lastfour );
    }
};