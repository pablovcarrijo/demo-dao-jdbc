package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
			
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1 -SELLER FIND BY ID- ===");
		
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println();
		System.out.println("=== TEST 2 -SELLER FIND BY DEPARTMENT- ===");
		
		Department dep = new Department(1, null);
		
		List<Seller> list = sellerDao.findByDepartment(dep);
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("=== TEST 3 -FIND ALL- ===");
		Department depp = new Department(2, null);
		list = sellerDao.findAll();
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("=== TEST 4 -SELLER INSERT- ===");
		Seller newSeller = new Seller(null, "Pablo", "pablo.vcarrijo@gmail.com", new Date(), 4000.00, dep);
		sellerDao.inset(newSeller);
		System.out.println("Insert with success");
		
		
		
	}

}
