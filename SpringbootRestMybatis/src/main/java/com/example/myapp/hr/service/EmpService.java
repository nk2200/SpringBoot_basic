package com.example.myapp.hr.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.hr.dao.IEmpRepository;
import com.example.myapp.model.Emp;
@Service
public class EmpService implements IEmpService {
	
	@Autowired
	IEmpRepository empRepository;
	
	@Override
	public int getEmpCount() {
		return empRepository.getEmpCount();
	}

	@Override
	public Emp getEmpInfo(int empid) {
		return (Emp) empRepository.getEmpInfo(empid);
	}

}
