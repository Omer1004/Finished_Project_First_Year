package model;

import java.io.Serializable;
import java.time.LocalDate;

public class EmployeeBaseSalary extends Employee implements Salaryable,Serializable {

	private static final long serialVersionUID = -712690460819942616L;
	protected double baseSalary;
	protected final int workHoursPerMonth = 160;
	
	public EmployeeBaseSalary(String name, String id, LocalDate birthdate, Preference prefernce,Role role,double baseSalary) {
		super(name, id, birthdate, prefernce,role);
		this.baseSalary = baseSalary;
	}

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public int getWorkHoursPerMonth() {
		return workHoursPerMonth;
	}
	public String toString() {
		String s = super.toString()+"Base Salary: "+this.baseSalary+"\n";
		return s;
	}
	public boolean equals(Object obg) {
		if(obg.getClass().getSimpleName().equals("EmployeeBaseSalary")) {
			if(((Employee) (obg)).getId()==this.id) {
				return true;
			}
			
		}
		return false;
	}

	@Override
	public double getSalary() {
		return this.baseSalary;
	}
}
