package com.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	  @RequestMapping({ "/greeting" })
	    public String welcomePage() {
	        return "Welcome!";
	    }
}
