package com.example.myapp.hr.service;

import com.example.myapp.model.Emp;

public interface IEmpService {
	int getEmpCount();
	Emp getEmpInfo(int empid);
}
