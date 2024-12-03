package application;

import java.sql.Connection;
import java.util.Date;

import db.DB;
import model.entitites.Department;
import model.entitites.Seller;

public class App {

	public static void main(String[] args) {
		Connection conn = DB.getConnection();
		DB.closeConnection();
		
		Department obj = new Department(1, "Books");
		
		Seller seller = new Seller(21, "Bob", "bob@gmail.com",  new Date(), 3000.0, obj);
		System.out.println(seller);
	}

}
