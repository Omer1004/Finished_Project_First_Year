package model;

import java.io.Serializable;
import java.time.LocalDate;

public class EmployeePerHourSalary extends Employee implements Salaryable,Serializable{
	
	private static final long serialVersionUID = 4970676840506211820L;
	protected double calculatedSalaryPerMonth;
	protected int workHoursPerMonth;
	protected double salaryPerHour;
	
	

	public EmployeePerHourSalary(String name, String id, LocalDate birthdate, Preference prefernce,Role role,double salaryPerHour,int workHoursPerMonth) {
		super(name, id, birthdate, prefernce,role);
		this.workHoursPerMonth = workHoursPerMonth;
		this.salaryPerHour = salaryPerHour;
		this.calculatedSalaryPerMonth = salaryPerHour*workHoursPerMonth;
	}
	
	public String toString() {
		String s = super.toString()+"Salary per hour: "+this.salaryPerHour+"\nWork hours per month: "+this.workHoursPerMonth+
				"\nCalculated Salary per month: "+this.calculatedSalaryPerMonth+"\n";
		return s;
	}
	public boolean equals(Object obg) {
		if(obg.getClass().getSimpleName().equals("EmployeePerHourSalary")) {
			if(((Employee) (obg)).getId()==this.id) {
				return true;
			}
			
		}
		return false;
	}

	public double getCalculatedSalaryPerMonth() {
		return calculatedSalaryPerMonth;
	}


	public int getWorkHoursPerMonth() {
		return workHoursPerMonth;
	}

	public void setWorkHoursPerMonth(int workHoursPerMonth) {
		this.workHoursPerMonth = workHoursPerMonth;
		this.calculatedSalaryPerMonth = salaryPerHour*this.workHoursPerMonth;
	}

	public double getSalaryPerHour() {
		return salaryPerHour;
	}

	public void setSalaryPerHour(double salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
		this.calculatedSalaryPerMonth = salaryPerHour*this.workHoursPerMonth;
	}

	@Override
	public double getSalary() {
		return this.calculatedSalaryPerMonth;
	}
	

}
