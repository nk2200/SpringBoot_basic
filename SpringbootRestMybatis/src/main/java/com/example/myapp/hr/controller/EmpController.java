package com.example.myapp.hr.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.hr.service.IEmpService;
import com.example.myapp.model.Emp;

@Controller
@RestController("/hr")
public class EmpController {
	
	@Autowired
	IEmpService empService;
	
	@GetMapping("/count")
	public int empCount(Model model) {
		return empService.getEmpCount();
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<Emp> getEmpInfo(@PathVariable int employeeId) {
		Emp emp = empService.getEmpInfo(employeeId);
		if (emp != null) {
	        return ResponseEntity.ok(emp); // emp 객체를 응답 본문에 포함시킴
	    } else {
	        return ResponseEntity.notFound().build(); // emp가 없을 경우 404 상태 코드 반환
	    }
	}
	
}
