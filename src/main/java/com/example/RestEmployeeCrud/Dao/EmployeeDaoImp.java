package com.example.RestEmployeeCrud.Dao;

import java.util.List;


import org.springframework.stereotype.Repository;
import com.example.RestEmployeeCrud.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDaoImp implements  EmployeeDao{

	
	private EntityManager em;
	
	public EmployeeDaoImp(EntityManager em) {
		this.em=em;
	}
	
	@Override
	public List<Employee> GetAll() {
												//should match with the entity class..
		TypedQuery<Employee> theQuery=em.createQuery("from Employee",Employee.class);
		List<Employee> employees=theQuery.getResultList();
		return employees;
		
	}

	@Override
	public Employee GetbyId(int id) {
		Employee theemp=em.find(Employee.class, id);
		if(theemp==null) {
			throw new RuntimeException("inga paar da panni, "
					+ "kareektaana id ya anupchu vidu");
		}
		return theemp;
	}

	@Override
	public void DeletebyId(int id) {
		
		Employee emp=new Employee();
		emp=em.find(Employee.class, id);
		em.remove(emp);
	}

	@Override
	public Employee Save(Employee emp) {
		Employee theemp=em.merge(emp);
		
		return theemp;
	}

	//No need of update method we can use the save method for the same purpose...
	@Override
	public Employee Update(Employee emp) {
		Employee theemp=em.merge(emp);
		return theemp;
	}
	
	

}
