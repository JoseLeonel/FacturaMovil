package com.emprendesoftcr.web.Controller;

import org.springframework.mobile.device.Device;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

//	@Autowired
//	UsuarioBo usuarioBo;

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping("/")
	public String index(Device device) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("username: " + auth.getName());

//		Usuario usuario = usuarioBo.buscar(auth.getName());
		String deviceType = "browser";

		String viewName = "dashboardAdmin/index";

		if (device.isNormal()) {
			deviceType = "browser";
		} else if (device.isMobile()) {
			deviceType = "mobile";
			viewName = "dashboardAdmin/index2";
		} else if (device.isTablet()) {
			deviceType = "tablet";
			viewName = "dashboardAdmin/index2";
		}

		return viewName;
	}

	@GetMapping("/403")
	public String error403() {
		return "views/error/403";
	}

}