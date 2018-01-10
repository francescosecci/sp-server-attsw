package com.pufose.server;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AWebController {
	@Autowired
	private IGridService service;
	@RequestMapping("/")
    public String welcome(Map<String,Object> model) {
        return "database";
    }

}
