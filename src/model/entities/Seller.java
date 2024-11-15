package model.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Integer id;
	private String name;
	private String email;
	private Date birthDate;
	private Double baseSalary;
	private Department department;
	
	public Seller(Integer id, String name, String email, Date birthDate, Double baseSalary, Department department) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(id, other.id);
	}
	
	public String toString() {
		return "Seller id: " + getId()
		+ " - Name: " + getName() 
		+ " - Email: " + getEmail() 
		+ " - BirthDate: " + sdf.format(getBirthDate()) 
		+ " - Department: " + getDepartment() 
		+" - Salary: US$" + String.format("%.2f", getBaseSalary());
	}
	
}
