package application;

import java.sql.Connection;

import db.Db;

public class Program {

	public static void main(String[] args) {
		Connection conn = Db.getConnection();
		
		Db.closeConnection();
	}
	
}
