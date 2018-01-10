package com.pufose.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AWebController {
	@Autowired
	private IGridService service;
	@RequestMapping("/")
    public String welcome(Map<String,Object> model) {
        return "database";
    }
	@RequestMapping("/viewdb")
	public String viewdb(Model model) {
		List<DatabaseGrid> allGrids=service.getAllGrids();
		model.addAttribute("sizeof",allGrids.size());
		model.addAttribute("gridsList",allGrids);
		return "dbview";
		
	}
}
