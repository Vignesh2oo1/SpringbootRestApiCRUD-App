package com.example.RestEmployeeCrud.Service;

import java.util.List;
import com.example.RestEmployeeCrud.Entity.Employee;

public interface EmployeeService {

	List<Employee> GetAll();
	Employee GetbyId(int id); 
	Employee Save(Employee emp);
	void DeletebyId(int id);
	Employee Update(Employee emp);
}
