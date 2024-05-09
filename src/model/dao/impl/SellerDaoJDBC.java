package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.Db;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void inset(Seller seller) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(
					
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+"VALUES "
					+ "(?, ?, ?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, seller.getName());
			pst.setString(2, seller.getEmail());
			pst.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			pst.setDouble(4, seller.getBaseSalary());
			pst.setInt(5, seller.getDepartment().getId());
			
			int rowsAffected = pst.executeUpdate();
			
			if(rowsAffected > 0) {
				rs = pst.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					
					seller.setId(id);
					
				}
			}
			else {
				throw new DbException("Unexpected error: No rows affected!");
			}
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closePreparedStatement(pst);
			Db.closeResultSet(rs);
		}
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(
					
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
					
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				Department dep = instanciatyDepartment(rs);
				
				Seller obj = instanciatySeller(rs, dep);
				return obj;
				
			}
			return null;
			
					
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closePreparedStatement(pst);
			Db.closeResultSet(rs);
		}
		
	}
	
	public static Department instanciatyDepartment(ResultSet rs) throws SQLException {
		
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		
		return dep;
		
	}

	public static Seller instanciatySeller(ResultSet rs, Department dep) throws SQLException {
	
		Seller obj = new Seller();
		
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setEmail(rs.getString("Email"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
		
	}
	
	@Override
	public List<Seller> findAll() {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(
					
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"ORDER BY Name "
					
					);
			
			rs = pst.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instanciatyDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instanciatySeller(rs, dep);
				list.add(obj);
				
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closePreparedStatement(pst);
			Db.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department dep) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name" );
			pst.setInt(1, dep.getId());
			rs = pst.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department department = map.get(rs.getInt("DepartmentId"));
				
				if(department == null) {
					department = instanciatyDepartment(rs);
					map.put(rs.getInt("DepartmentId"), department);
				}
				
				Seller seller = instanciatySeller(rs, department);
				
				list.add(seller);
				
			}
			return list;
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closePreparedStatement(pst);
			Db.closeResultSet(rs);
		}
	}

}
