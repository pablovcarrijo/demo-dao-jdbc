package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {

	public static void main(String[] args) {
		
		Department dep = new Department(1, "Books");
		
		Seller seller = new Seller(1, "Pablo", "pablo.vcarrijo@gmail.com", new Date(), 100000.0, dep);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println(seller);
		
		
	}

}
