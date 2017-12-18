package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ErrorsController {

	  @GetMapping("/403")
	    public String error403() {
	        return "/error/403";
	    }
	
}
