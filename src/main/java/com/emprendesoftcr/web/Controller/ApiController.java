package com.emprendesoftcr.web.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.emprendesoftcr.Bo.IFEMensajeReceptorAutomaticoBo;
import com.emprendesoftcr.utils.Utils;



@CrossOrigin
@Controller
public class ApiController {

	@Autowired
	private IFEMensajeReceptorAutomaticoBo	_mrService;

	@Value("${path.upload.files.api}")
	private String													pathUploadFilesApi;

	@GetMapping("/get-all")
	public ResponseEntity<?> findAll(@RequestParam("e") String e, @RequestParam("i") String i, @RequestParam("t") String t) {
		Map<String, Object> resp = new HashMap<>();
//		if (t != null && t.equalsIgnoreCase("R24.asd24fg")) {
//			resp.put("listRecepcionCompras", _mrService.getAll(e, i));
//			resp.put("response", 200);
//		} else {
//			resp.put("listRecepcionCompras", null);
//			resp.put("response", 400);
//		}
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

	@RequestMapping(value = "/base64.do", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> retornaXmlBase64(HttpServletRequest request, HttpServletResponse response, ModelMap model,@RequestParam("ruta") String ruta) {

		Map<String, Object> resp = new HashMap<>();

		try {

			String filePath = this.pathUploadFilesApi + "mr-automatico/" + ruta;
			String facturaXmlFinal = "";

			try {
				facturaXmlFinal = new String(Files.readAllBytes(Paths.get(filePath)));
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

	@RequestMapping(value = "/bajarArchivo.do", method = RequestMethod.GET, headers = "Accept=application/json")
	public void getFile(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam("filename") String filename) throws IOException {

		Resource recurso = null;
		String ruta = "mr-automatico";
		try {
			recurso = this.renderFile(filename, ruta);
			
		// ...(file is initialised)...
		byte[] fileContent = Utils.read(recurso.getFile());
		
			
			
	
		if (fileContent != null && fileContent.length > 0) {
			response.setContentType("application/pdf");
			//response.setHeader("Content-Disposition", "attachment;filename=etiquetas.pdf");
			ServletOutputStream outputstream = response.getOutputStream();
			outputstream.write(fileContent, 0, fileContent.length);
			outputstream.flush();
			outputstream.close();

		} else {
			System.out.println("NO trae nada");
		}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public Resource renderFile(String filename, String ruta) throws MalformedURLException {
		Resource recurso = new UrlResource(Paths.get(this.pathUploadFilesApi + "/" + ruta).resolve(filename).toAbsolutePath().toUri());
		if (!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error: no se puede cargar la imagen: " + filename.toString());
		}
		return recurso;
	}

}
