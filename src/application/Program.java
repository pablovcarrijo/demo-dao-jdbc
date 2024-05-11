package application;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
			
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		
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
		
	/*
		System.out.println("=== TEST 4 -SELLER INSERT- ===");
		Seller newSeller = new Seller(null, "Pablo", "pablo.vcarrijo@gmail.com", new Date(), 4000.00, dep);
		sellerDao.inset(newSeller);
		System.out.println("Insert with success! New id: " + newSeller.getId());
		
		System.out.println("=== TEST 5 -SELLER UPDATE- ===");
		Seller updateSeller = new Seller(35, "Pablo Carrijo", "pablo.vcarrijo@gmail.com", new Date(), 4000.00, dep);
		sellerDao.update(updateSeller);
		System.out.println("Update with success!");
		*/
		
		System.out.println("=== TEST 6 -DELETE SELLER- ===");
		System.out.print("Enter id for delete: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Deleted with success!");
		
		sc.close();
		
	}

}
