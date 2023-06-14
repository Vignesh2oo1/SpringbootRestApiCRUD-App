package com.example.RestEmployeeCrud.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RestEmployeeCrud.Entity.Employee;
import com.example.RestEmployeeCrud.Dao.EmployeeDao;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImp implements EmployeeService {

	private EmployeeDao edao;
	
	@Autowired
	public EmployeeServiceImp(EmployeeDao edao) {
		this.edao=edao;
	}
	
	@Override
	public List<Employee> GetAll() {
		return edao.GetAll();
	}

	@Override
	public Employee GetbyId(int id) {
		return edao.GetbyId(id);
	}

	@Override
	@Transactional
	public Employee Save(Employee emp) {
		// TODO Auto-generated method stub
		return edao.Save(emp);
	}
	@Override
	@Transactional
	public void DeletebyId(int id) {
		edao.DeletebyId(id); 
	}

	@Override
	@Transactional
	public Employee Update(Employee emp){
		return edao.Update(emp);
	}

	

}
