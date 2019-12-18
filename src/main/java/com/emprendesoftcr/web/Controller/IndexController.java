package com.emprendesoftcr.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	
	@GetMapping("/login")
    public String login(Model model) {
		
        return "login";
    }

	
	 @RequestMapping("/")
	    public String index() {
	        return "dashboardAdmin/index";
	    }
	 
	 @GetMapping("/403")
	    public String error403() {
	        return "views/error/403";
	    }
	 
	

}