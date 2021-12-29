package model;

import java.io.Serializable;
import java.time.LocalDate;

public class EmployeeBasePrecentageSalary extends Employee implements Salaryable,Serializable{

	private static final long serialVersionUID = -8657569841917701087L;
	protected double salesThisMonth;
	protected double percentFromSale;
	protected double calculatedBonus;
	protected double baseSalary;
	protected double calculatedSalaryPerMonth;
	protected final int workHoursPerMonth = 160;
	
	

	public EmployeeBasePrecentageSalary(String name, String id, LocalDate birthdate, Preference prefernce,Role role,double baseSalary,double percentFromSale) {
		super(name, id, birthdate, prefernce,role);
		this.baseSalary = baseSalary;
		this.salesThisMonth = 0;
		this.percentFromSale = percentFromSale;
		this.calculatedBonus = 0;
		this.calculatedSalaryPerMonth = baseSalary;
	}
	
	
	public boolean equals(Object obg) {
		if(obg.getClass().getSimpleName().equals("EmployeeBasePrecentageSalary")) {
			if(((Employee) (obg)).getId()==this.id) {
				return true;
			}
			
		}
		return false;
	}
	
	public String toString() {
		String s = super.toString()+"Base Salary: "+this.baseSalary+"\nPercent from sales: "+percentFromSale
				+"\nCalculated bonus for Employee: "+this.calculatedBonus+"\nCalculated Salary Per Month: "+this.calculatedSalaryPerMonth+"\n";
		return s;
	}


	public double getSalesThisMonth() {
		return salesThisMonth;
	}


	public void setSalesThisMonth(double sales) {
		this.salesThisMonth = sales;
		this.calculatedBonus = sales*this.percentFromSale;
		this.calculatedSalaryPerMonth = calculatedBonus+this.baseSalary;
	}


	public double getPercentFromSale() {
		return percentFromSale;
	}


	public void setPercentFromSale(double percentFromSale) {
		this.percentFromSale = percentFromSale;
		this.calculatedBonus = salesThisMonth*this.percentFromSale;
		this.calculatedSalaryPerMonth = this.baseSalary+this.calculatedBonus;
	}


	public double getCalculatedBonus() {
		return calculatedBonus;
	}


	public double getBaseSalary() {
		return baseSalary;
	}


	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
		this.calculatedSalaryPerMonth = this.baseSalary+this.calculatedBonus;
	}


	public double getCalculatedSalaryPerMonth() {
		return calculatedSalaryPerMonth;
	}



	public int getWorkHoursPerMonth() {
		return workHoursPerMonth;
	}


	@Override
	public double getSalary() {
		return this.calculatedSalaryPerMonth;
	}

}
