package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Db;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("INSERT INTO department " + "(Name) " + "VALUES " + "(?)",
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, obj.getName());

			int ra = pst.executeUpdate();

			if (ra > 0) {
				rs = pst.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			} else {
				throw new DbException("Unexpected error!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closePreparedStatement(pst);
			Db.closeResultSet(null);
		}

	}

	@Override
	public void update(Department obj) {

		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement("UPDATE department " + "SET Name = ? " + "WHERE Id = ?");

			pst.setString(1, obj.getName());
			pst.setInt(2, obj.getId());

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closePreparedStatement(pst);
			Db.closeResultSet(null);
		}

	}

	@Override
	public void deleteById(Integer id) {

		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement("DELETE FROM department " + "WHERE " + "Id = ?");
			pst.setInt(1, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closePreparedStatement(pst);
			Db.closeResultSet(null);
		}

	}

	@Override
	public Department findById(Integer id) {

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT * FROM department WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, id);

			rs = pst.executeQuery();

			if (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				return dep;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closePreparedStatement(pst);
			Db.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT * FROM department ORDER BY Id");

			List<Department> list = new ArrayList<>();

			rs = pst.executeQuery();
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				list.add(dep);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closePreparedStatement(pst);
			Db.closeResultSet(rs);
		}
	}

}
