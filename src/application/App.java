package application;

import java.sql.Connection;

import db.DB;
import model.entitites.Department;

public class App {

	public static void main(String[] args) {
		Connection conn = DB.getConnection();
		DB.closeConnection();
		
		Department dep = new Department(1, "Books");
		System.out.println(dep);
	}

}
