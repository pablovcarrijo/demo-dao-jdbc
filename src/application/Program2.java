package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TEST 1 -FIND BY ID- ===");
		Department dep = departmentDao.findById(1);
		System.out.println(dep);
		
		System.out.println("=== TEST 2 -UPDATE- ===");
		Department dep2 = departmentDao.findById(2);
		dep2.setName("Teaclado");
		departmentDao.update(dep2);
		System.out.println("Update completed");
		
		
		System.out.println("=== TEST 3 -INSERT- ===");
		Department daoInsert = new Department(null, "Music");
		departmentDao.insert(daoInsert);
		System.out.println("Insert!");
		
		System.out.println("=== TEST 4 -DELETE BY ID- ===");
		departmentDao.deleteById(13);
		System.out.println("Deleted");
		
		System.out.println("=== TEST 5 -FIND ALL- ===");
		List<Department> depList = departmentDao.findAll();
		for(Department deplist : depList) {
			System.out.println(deplist);
		}
		
	}
	
}
