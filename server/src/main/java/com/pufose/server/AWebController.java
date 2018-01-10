package com.pufose.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AWebController {
	@Autowired
	private IGridService service;

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		return "database";
	}

	@RequestMapping("/viewdb")
	public String viewdb(Model model) {
		List<DatabaseGrid> allGrids = service.getAllGrids();
		model.addAttribute("sizeof", allGrids.size());
		model.addAttribute("gridsList", allGrids);
		return "dbview";

	}

	@GetMapping("/addtable")
	public String addtableForm(Model model) {
		model.addAttribute("usercontent", new UserContent());
		return "tableadd";
	}

	@PostMapping("/addtable")
	public String addtable(@ModelAttribute UserContent content) {
		int n = content.getN();
		String cont = content.getContent();
		int[][] matrix = new int[n][n];
		int contiter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				try {
					matrix[i][j] = Integer.parseInt(String.format("%c", cont.charAt(contiter++)));
				} catch (Exception exc) {

					matrix[i][j] = 0;
				}
			}
		}
		service.storeInDb(new DatabaseGrid(matrix, service.nextId()));
		return "database";

	}
	@GetMapping("/remtable")
    public String remtableForm(Model model) {
        model.addAttribute("usercontent", new UserContent());
        return "tablerem";
    }
	@PostMapping("/remtable")
	public String remtable(@ModelAttribute UserContent content) {
	int id=content.getN();
	service.dropTable(id);
	return "database";
	}
}
