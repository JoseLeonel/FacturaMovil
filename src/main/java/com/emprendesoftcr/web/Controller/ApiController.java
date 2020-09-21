package com.emprendesoftcr.web.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emprendesoftcr.Bo.IFEMensajeReceptorAutomaticoBo;
@CrossOrigin
@RestController
@RequestMapping("/api-v1")
public class ApiController {

	@Autowired
	private IFEMensajeReceptorAutomaticoBo	_mrService;

	@Value("${path.upload.files.api}")
	private String															pathUploadFilesApi;

	@GetMapping("/get-all")
	public ResponseEntity<?> findAll(@RequestParam("e") String e, @RequestParam("i") String i, @RequestParam("t") String t) {
		Map<String, Object> resp = new HashMap<>();
		if (t != null && t.equalsIgnoreCase("R24.asd24fg")) {
			resp.put("listRecepcionCompras", _mrService.getAll(e, i));
			resp.put("response", 200);
		} else {
			resp.put("listRecepcionCompras",null );
			resp.put("response", 400);
		}
		return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.OK);
	}

	@GetMapping("/update-estado")
	public ResponseEntity<?> updateEstado(@RequestParam("e") String e, @RequestParam("id") Long id, @RequestParam("t") String t) {

		Map<String, Object> resp = new HashMap<>();

		try {
			if (t != null && t.equalsIgnoreCase("R24.asd24fg")) {
				_mrService.updateEstado(e, id);
				resp.put("response", 200);
			} else {
				
				resp.put("response", 400);
			}

		} catch (Exception e2) {
			resp.put("response", 400);
		}

		return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.OK);

	}
	
	@GetMapping("/base64")
	public ResponseEntity<?> retornaXmlBase64(@RequestParam("ruta") String ruta) {

		Map<String, Object> resp = new HashMap<>();

		try {
			
			String filePath = this.pathUploadFilesApi  +"mr-automatico/"+ ruta;
			String facturaXmlFinal = "";
			
			try {
				facturaXmlFinal = new String ( Files.readAllBytes( Paths.get(filePath) ) );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				facturaXmlFinal = "";
			}
			
			resp.put("xmlString", facturaXmlFinal);
			

		} catch (Exception e2) {
			resp.put("response", 400);
		}

		return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.OK);

	}

	@GetMapping(value = "/repositorio/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {

		Resource recurso = null;
		String ruta = "mr-automatico";
		try {
			recurso = this.renderFile(filename, ruta);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"").body(recurso);
	}

	public Resource renderFile(String filename, String ruta) throws MalformedURLException {
		Resource recurso = new UrlResource(Paths.get(this.pathUploadFilesApi + "/" + ruta).resolve(filename).toAbsolutePath().toUri());
		if (!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error: no se puede cargar la imagen: " + filename.toString());
		}
		return recurso;
	}

}
