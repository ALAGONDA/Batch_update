package com.drc.pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drc.pack.dao.EmpDepDao;
import com.drc.pack.dto.Department;

@RestController
public class EmpDepController {
	
	@Autowired
	private EmpDepDao empDepDao;
	
	@PostMapping("/addrow")
	public void insert(@RequestBody Department department){
		empDepDao.insert(department);
		
	}
	
	@PostMapping("/addList")
	public void listAdd(@RequestBody List<Department> departments) {
		empDepDao.listAdd(departments);
	}

}
