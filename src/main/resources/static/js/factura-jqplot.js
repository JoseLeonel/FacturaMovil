	var dataEstadosXLotePie = null;

	var ajaxDataRenderer = function(url, plot) {
//		alert("ajaxDataRenderer url: " + url + " plot: " + plot);
		var ret = null;
		$.ajax({
			// have to use synchronous here, else returns before data is fetched
			async: false,
		    url: url,
		    dataType:'json',
		    success: function(data) {
		        ret = data;
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
		        alert("Ha ocurrido un error, intente luego.");
		    }
		});
		return ret;
	};


	var convertir = function(url, plot) {
//		alert ("convertir url: " + url + " plot: " + plot);
		dataEstadosXLotePie = ajaxDataRenderer(url, plot);
		
		if (dataEstadosXLotePie != null && dataEstadosXLotePie != '') {
			array = $.map(dataEstadosXLotePie, function(el) {
				return [[el.estadoDescripcion, el.cantidad]];
			});
			return [array];
		} else {
			return [[null]];
		}
	};
	
	var convertirLote = function(url, plot) {
//		alert ("convertirLote url: " + url + " plot: " + plot);
		dataEstadosXLotePie = ajaxDataRenderer(url, plot);
		
		if (dataEstadosXLotePie != null && dataEstadosXLotePie != '') {
			array = $.map(dataEstadosXLotePie, function(el) {
				return [[el.estadoLoteDescripcion, el.cantidad]];
			});
			return [array];
		} else {
			return [[null]];
		}
	};
	
	function mostrarGraficoCantidadSIMXEstado() {
		var jsonurl = "GraficoDatoSimXEstado.do";
		var graficosEstadoSIMxLotes = $.jqplot('graficosEstadoSIMxLotes', jsonurl,{
			title: "Estados de SIM's en todos los Lotes",
			dataRenderer: convertir,
	        seriesDefaults: {
	            shadow: false, 
	            renderer: jQuery.jqplot.PieRenderer, 
	            rendererOptions: { dataLabelThreshold: 3, padding: 2, sliceMargin: 2, showDataLabels: true }
	        },
	        legend: {
	            show: true,
	            location: 'e',
	            renderer: $.jqplot.EnhancedPieLegendRenderer,
	            rendererOptions: {
	                numberColumns: 1
	            }
	        }
		});
//		alert("mostrarGraficoCantidadSIMXEstado() dataEstadosXLotePie = " + dataEstadosXLotePie);
		if (dataEstadosXLotePie == null || dataEstadosXLotePie == '' || dataEstadosXLotePie == [[null]]) {
			graficosEstadoSIMxLotes.destroy();
		}
	}

	function mostrarGraficoCantidadSIMXEstadoXLote() {
		var jsonurl = "GraficoDatoSimXEstadoXLote.do?idLote="+ $('#idLote').val();
		var graficosEstadoSIMxIDLote = $.jqplot('graficosEstadoSIMxIDLote', jsonurl,{
//			title: 'ESTADOS POR LOTE',
			title: "Estados de SIM's por Lote",
			dataRenderer: convertir,

			seriesDefaults: {
				renderer: $.jqplot.PieRenderer,
				rendererOptions: {
					showDataLabels: true
				}
			},
		    legend: { show: true, location: 'e' }
		});
		
		if (dataEstadosXLotePie == null || dataEstadosXLotePie == '' || dataEstadosXLotePie == [[null]]) {
			graficosEstadoSIMxIDLote.destroy();
		}
	}
	
	function mostrarGraficoCantidadLotePorEstado() {
		var jsonurl = "GraficoLotePorEstado.do";
		var graficosEstadoLote = $.jqplot('graficosEstadoLote', jsonurl,{
			title: 'Estados de todos los Lotes',
			dataRenderer: convertirLote,
	        seriesDefaults: {
	            shadow: false, 
	            renderer: jQuery.jqplot.PieRenderer, 
	            rendererOptions: { dataLabelThreshold: 3, padding: 2, sliceMargin: 2, showDataLabels: true }
	        },
	        legend: {
	            show: true,
	            location: 'e',
	            renderer: $.jqplot.EnhancedPieLegendRenderer,
	            rendererOptions: {
	                numberColumns: 1
	            }
	        }
		});
//		alert("mostrarGraficoCantidadLotePorEstado() dataEstadosXLotePie = " + dataEstadosXLotePie);
		if (dataEstadosXLotePie == null || dataEstadosXLotePie == '' || dataEstadosXLotePie == [[null]]) {
			graficosEstadoLote.destroy();
		}
	}	

$(document).ready(function(){
    $('#detalleLoteTabs').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href") // activated tab
        if (target == '#box_tab4') {
            mostrarGraficoCantidadSIMXEstadoXLote();
        }
    });
    $('#inicioTabs').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href") // activated tab
        if (target == '#pro_edit') {
        	mostrarGraficoCantidadLotePorEstado();
        	mostrarGraficoCantidadSIMXEstado();
        } 
    });
});

