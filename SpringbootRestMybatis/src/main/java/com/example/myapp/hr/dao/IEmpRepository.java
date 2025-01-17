package com.example.myapp.hr.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.myapp.model.Emp;

@Mapper
@Repository
public interface IEmpRepository {
	int getEmpCount();
	Emp getEmpInfo(int empid);
}
