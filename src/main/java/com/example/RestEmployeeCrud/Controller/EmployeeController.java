package com.example.RestEmployeeCrud.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.RestEmployeeCrud.Entity.Employee;
import com.example.RestEmployeeCrud.Service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private EmployeeService es;
	
	public EmployeeController(EmployeeService es) {
		this.es=es;
	}
	
	@GetMapping("/employee")
	public List<Employee> GetAll(){
		return es.GetAll();
	}
	
	@GetMapping("/employee/{empid}")
	public Employee GetbyId(@PathVariable int empid) {
		return es.GetbyId(empid);
	}
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee emp) {
		
		/*this line is to avoid any update action....if the use sends the json with
		  id of the employee and if the id already exists in db it will update it
		  instead of adding it.....*/
		emp.setId(0);
		//Retrieving the employee after saving/adding in the database...
		Employee theemp=es.Save(emp);
		return theemp;
		
	}
	
	@DeleteMapping("/employee/{empid}")
	public String DeletebyId(@PathVariable int empid) {
		
		Employee emp=es.GetbyId(empid);
		if(emp==null) {
			throw new RuntimeException("no employee exists with id: "+empid);
		}
		es.DeletebyId(empid);
		return "succesfully deleted the employee of id "+empid;
	}
	
	@PutMapping("employee")
	public Employee update(@RequestBody Employee emp){
		
		
		Employee theemp=es.Save(emp);//Instead of 'Update' we can even call 'save'
		return theemp;
		
	}
}
