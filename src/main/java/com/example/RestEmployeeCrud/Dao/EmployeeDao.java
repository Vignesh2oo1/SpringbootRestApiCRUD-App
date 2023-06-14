package com.example.RestEmployeeCrud.Dao;

import java.util.List;

import com.example.RestEmployeeCrud.Entity.Employee;

public interface EmployeeDao {
	List<Employee> GetAll();
	Employee GetbyId(int id);
	Employee Save(Employee emp);
	Employee Update(Employee emp);
	void DeletebyId(int id);
	
}
