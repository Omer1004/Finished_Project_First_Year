package model;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import controller.CompanyBackendable;
import controller.Controller;
import model.Preference.Preferences;


//Omer Iny - 206571739
public class Company implements Serializable{
	private static final long serialVersionUID = -2888749915304645660L;
	private String nameOfCompany;
	private ArrayList<Department> allDepartments;
	private double salesForTheMonth;
	private Set<Employee> allEmployees;
	private CompanyBackendable controller;
	private final static double EFFICIENTHOUR = 1.2;
	private final static double NOTEFFICIENTHOUR = 0.8;
	private final static double WORKFROMHOME = 1.1;
	private static final String FILENAME = "Company.bin";
	
	
	

	public void setListener(Controller c) {
		this.setController(c);
	}
	public Company(String nameOfCompany) {
		this.allDepartments = new ArrayList<Department>();
		this.nameOfCompany = nameOfCompany;
		this.allEmployees = new HashSet<Employee>();
		this.setController(null);
		this.salesForTheMonth = 0;
	}
	
	public ArrayList<Employee> getAllEmployees(){
		ArrayList<Employee> emps = new ArrayList<Employee>();
		for(Employee emp:this.allEmployees) {
			emps.add(emp);
		}
		return emps;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("Company Name: "+this.nameOfCompany+"\n\n");
		for(Department d: this.allDepartments) {
			sb.append(d.toString()+"\n");
		}
		return sb.toString();
	}

	public String getNameOfCompany() {
		return nameOfCompany;
	}
	
	public void setSalesForTheMonth(double sales) {
		this.salesForTheMonth = sales;
		for(Employee emp :this.allEmployees) {
			if(emp.getClass().getSimpleName().equals("EmployeeBasePrecentageSalary"))
				((EmployeeBasePrecentageSalary)(emp)).setSalesThisMonth(sales);
		}
	}
	
	public double getSalesForTheMonth() {
		return this.salesForTheMonth;
	}
	
	public ArrayList<Department> getDepartments() {
		return allDepartments;
	}

	public void addDepartment(String departmentName,boolean canChangeWorkHours,boolean mustSync,Preference preference) throws Exception {
		for(Department d : this.allDepartments){
			if(d.getNameOfDepartment().equals(departmentName))
				throw new Exception("There is already a department named "+departmentName);
		}
		Department department =new Department(departmentName,canChangeWorkHours,mustSync,preference);
		if(!this.allDepartments.add(department))
			throw new Exception("Somthing went wrong Department was not added");
		System.out.println("added");	
	}
	
	public void addRole(Department department,String nameOfRole,boolean canChangeWorkHours) throws Exception {
		if(!department.addRole(new Role(nameOfRole,canChangeWorkHours)))
			throw new Exception("Somthing went wrong Role was not added");
			
	}
	
	
	public void addEmployee(Department department,Employee emp) throws Exception {
		if(!this.allEmployees.add(emp))
			throw new Exception("an Employee with this ID Already exists\nemployee was not added");
		else {
				department.addRole(emp.getRole());
				department.addEmployee(emp);
		}
		
		
	}

	

	public static ArrayList<Preference> getPreferencesArray() {
		ArrayList<Preference> p = new ArrayList<Preference>();
		p.add(new Preference(Preferences.StartWorkingEarlier));
		p.add(new Preference(Preferences.StartWorkingLater));
		p.add(new Preference(Preferences.StayTheSame));
		p.add(new Preference(Preferences.WorkFromHome));
		return p;
	}
	

	public void setEmployeeWorkTime(Employee emp,int startHour,int startMin,int endHour,int endMin) throws Exception {
		emp.setWorkTimes(startHour, startMin, endHour, endMin);
	}
	
	public ArrayList<String> getDepartmentsNames(){
		ArrayList<String> s = new ArrayList<String>() ;
		for(Department d : this.allDepartments) {
			s.add(d.getNameOfDepartment());
		}
		return s;
			
	}
	public double getRemainingPrecentOfAllSales() {
		double percent =100;
		for(Employee emp : this.allEmployees) {
			if(emp.getClass().getSimpleName().equals("EmployeeBasePrecentageSalary")) {
				percent=percent - ((EmployeeBasePrecentageSalary)(emp)).getPercentFromSale();			}
		}
		if(percent<0)
			return 0;
		return percent;
	}
	public void addDepartment(Department department) {
		this.allDepartments.add(department);		
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
	public void updateMonthlySales(int monthlySales) {
		this.salesForTheMonth = monthlySales;
		updateEmployeesSalary();
	}
	private void updateEmployeesSalary() {
		for(Employee emp :this.allEmployees) {
			if(emp.getClass().getSimpleName().equals("EmployeeBasePrecentageSalary")) {
				((EmployeeBasePrecentageSalary)(emp)).setSalesThisMonth(salesForTheMonth);
			}
		}
		
	}
	
	public double getCompanyMoneyProfit() {
		double profit = this.salesForTheMonth;
		for(Employee emp:this.allEmployees) {
			if(emp.getClass().getSimpleName().equals("EmployeeBaseSalary")) {
				profit = profit -((EmployeeBaseSalary)(emp)).getSalary();
			}
			else if(emp.getClass().getSimpleName().equals("EmployeeBasePrecentageSalary")) {
				profit = profit -((EmployeeBasePrecentageSalary)(emp)).getSalary();
			}
			else if(emp.getClass().getSimpleName().equals("EmployeePerHourSalary")) {
				profit = profit -((EmployeePerHourSalary)(emp)).getSalary();
			}
		}
		return profit;
	}
	
	
	
	public String getAllEmployeesToString(ArrayList<Employee> emps) {
		StringBuffer sb = new StringBuffer("");
		for(Employee emp:emps) {
			if(emp.getClass().getSimpleName().equals("EmployeeBaseSalary")){
				sb.append("\n"+((EmployeeBaseSalary)(emp)).toString());
			}
			else if(emp.getClass().getSimpleName().equals("EmployeeBasePrecentageSalary")){
				sb.append("\n"+((EmployeeBasePrecentageSalary)(emp)).toString());
			}
			else if(emp.getClass().getSimpleName().equals("EmployeePerHourSalary")){
				sb.append("\n"+((EmployeePerHourSalary)(emp)).toString());
			}
		}
		return sb.toString();
	}
	
	public String getAllDepartmentsString() {
		StringBuffer sb = new StringBuffer("");
		for(Department d:this.allDepartments) {
			sb.append(d.toString()+"\n\n");
		}
		
		return sb.toString();
	}
	
	public String getCompanyData() {
		StringBuffer sb = new StringBuffer("Company Name: "+this.nameOfCompany+"\nSales This Month: "+
				this.salesForTheMonth+"\nNumber Of Employees: "+this.allEmployees.size()+
				"\nNumber Of Departments: "+this.allDepartments.size()+"\n\nAll Departments:\n"+getAllDepartmentsString());
		return sb.toString();
	}
	
	public String getAllRolesInDepartment(Department d) {
		return d.getRolesAsString();
	}
	public void updateDepartmentWorkHours(Department department,Role role) {
		for(Employee emp:department.getEmployees()) {
			if(emp.getRole().equals(role)) {
				try {
					emp.setWorkTimes(8, 0, 17, 0);
				} catch (Exception e) {
					controller.showMsg(e.getMessage());
				}
			}
		}
		
	}
	
	
	
	public String getEmployeesDailyEfficiently() {
		double normalWorkHours = 0;
		double efficientlyHoursTotal = 0 ;
		double effitentHoursEmployee = 0;
		double normalWorkHoursEmployee = 0;
		double effitiencyGain = 0;
		StringBuffer sb = new StringBuffer("Here Is a List Of All Of the employees work hours from where \nand efficient hours\n\n");
		for(Employee emp:this.allEmployees) {
			if(emp!=null) {
				if(emp.getClass().getSimpleName().equals("EmployeePerHourSalary")){
					normalWorkHoursEmployee =calculateWorkHours((EmployeePerHourSalary)(emp));
					normalWorkHours+= normalWorkHoursEmployee;
					effitentHoursEmployee =  calculateEfitientHours(emp,emp.getPrefernce().getPreference()) ;
					efficientlyHoursTotal +=effitentHoursEmployee;
					effitiencyGain = (effitentHoursEmployee-normalWorkHoursEmployee);
					effitiencyGain = Math.round(effitiencyGain * 100.0) / 100.0;
					sb.append("Employee Name: "+emp.getName()+"\nPreference: "+emp.getPrefernce().toString()+
							"\nWork Hours: "+emp.getAssambledTime()+"Normal Work Hours: "+normalWorkHoursEmployee+
							"\nEffitent Work Hours: "+effitentHoursEmployee+"\nEffitency Gain: "+
							effitiencyGain+"\n\n");
					
				}
				else {
					normalWorkHours+=9;
					normalWorkHoursEmployee=9;
					effitentHoursEmployee = calculateEfitientHours(emp,emp.getPrefernce().getPreference()) ;
					efficientlyHoursTotal += effitentHoursEmployee;
					effitiencyGain = (effitentHoursEmployee-normalWorkHoursEmployee);
					effitiencyGain = Math.round(effitiencyGain * 100.0) / 100.0;
					sb.append("Employee Name: "+emp.getName()+"\nPreference: "+emp.getPrefernce().toString()
							+"\nWork Hours: "+emp.getAssambledTime()+"Normal Work Hours: "+normalWorkHoursEmployee+
							"\nEffitent Work Hours: "+effitentHoursEmployee+"\nEffitency Gain: "+
							effitiencyGain+"\n\n");
					
				}
			}
			
		}
		sb.append("In total the employees worked today "+normalWorkHours+" Hours.\nIn total the Effitiency Hours Were: "
		+efficientlyHoursTotal+"\nThe Effitiency Gain Was "+(efficientlyHoursTotal-normalWorkHours)+" hours.");
		return sb.toString();
	}
	
	
	
	public String getDepartmentDailyEffitiency(Department d) {
		double normalWorkHours = 0;
		double efficientlyHoursTotal = 0 ;
		double effitentHoursEmployee = 0;
		double normalWorkHoursEmployee = 0;
		double departmentPrefEffitiencyHours =0;
		double effitiencyGain = 0;
		double departmentEffitiencyHoursTotal =0;
		double departmentEffitiencyGain = 0;
		StringBuffer sb = new StringBuffer("Here Is a List Of the employees from "+d.getNameOfDepartment()+" work hours from where \nand efficient hours\n\n");
		for(Employee emp:d.getEmployeesAsArrayList()) {
			if(emp.getClass().getSimpleName().equals("EmployeePerHourSalary")){
				normalWorkHoursEmployee =calculateWorkHours((EmployeePerHourSalary)(emp));
				normalWorkHours+= normalWorkHoursEmployee;
				effitentHoursEmployee =  calculateEfitientHours(emp,emp.getPrefernce().getPreference()) ;
				efficientlyHoursTotal +=effitentHoursEmployee;
				departmentPrefEffitiencyHours = calculateEfitientHours(emp,d.getDepartmentPreference().getPreference()) ;
				departmentEffitiencyHoursTotal+=departmentPrefEffitiencyHours;
				effitiencyGain = (effitentHoursEmployee-normalWorkHoursEmployee);
				effitiencyGain = Math.round(effitiencyGain * 100.0) / 100.0;
				departmentEffitiencyGain = departmentPrefEffitiencyHours-effitentHoursEmployee;
				departmentEffitiencyGain = Math.round(departmentEffitiencyGain * 100.0) / 100.0;
				sb.append("Employee Name: "+emp.getName()+"\nPreference: "+emp.getPrefernce().toString()
						+"\nDepartment Preference: "+d.getDepartmentPreference().getPreference()+"\nWork Hours: "+emp.getAssambledTime()+"Normal Work Hours: "+normalWorkHoursEmployee+
						"\nEffitent Work Hours: "+effitentHoursEmployee+"\nEffitency Gain: "+
						effitiencyGain+"\nDepartment Effitient Work Hours: "+
						departmentPrefEffitiencyHours+"\nDepartment Effitience Gain: "+
						departmentEffitiencyGain+"\n\n");
				
			}
			else {
				normalWorkHours+=9;
				normalWorkHoursEmployee=9;
				effitentHoursEmployee = calculateEfitientHours(emp,emp.getPrefernce().getPreference()) ;
				efficientlyHoursTotal += effitentHoursEmployee;
				departmentPrefEffitiencyHours = calculateEfitientHours(emp,d.getDepartmentPreference().getPreference()) ;
				departmentEffitiencyHoursTotal+=departmentPrefEffitiencyHours;
				departmentEffitiencyGain = departmentPrefEffitiencyHours-effitentHoursEmployee;
				departmentEffitiencyGain = Math.round(departmentEffitiencyGain * 100.0) / 100.0;
				effitiencyGain = (effitentHoursEmployee-normalWorkHoursEmployee);
				effitiencyGain = Math.round(effitiencyGain * 100.0) / 100.0;
				sb.append("Employee Name: "+emp.getName()+"\nPreference: "+emp.getPrefernce().toString()
						+"\nDepartment Preference: "+d.getDepartmentPreference().getPreference()+"\nWork Hours: "+emp.getAssambledTime()+"Normal Work Hours: "+normalWorkHoursEmployee+
						"\nEffitent Work Hours: "+effitentHoursEmployee+"\nEffitency Gain: "+
						effitiencyGain+"\nDepartment Effitient Work Hours: "+
						departmentPrefEffitiencyHours+"\nDepartment Effitience Gain: "+
						(departmentPrefEffitiencyHours-effitentHoursEmployee)+"\n\n");
				
			}
		}
		
		sb.append("In total the employees in "+d.getNameOfDepartment()+" worked today "+normalWorkHours+" Hours.\nIn total the Effitiency Hours Were: "
				+efficientlyHoursTotal+"\nThe Effitiency Gain Was "+(efficientlyHoursTotal-normalWorkHours)+" hours.\nIf Calculated By Department preference: "+
				departmentEffitiencyHoursTotal+"\nTotal Effiticency Gain Was: "+(departmentEffitiencyHoursTotal-normalWorkHours));
				return sb.toString();
	}
	
	public String getCompanyEffitencyGain() {
		double normalWorkHours = 0;
		double efficientlyHoursTotal = 0 ;
		double effitentHoursEmployee = 0;
		double normalWorkHoursEmployee = 0;
		double effitiencyGain = 0;
		StringBuffer sb = new StringBuffer(this.nameOfCompany+"\n\nTotal Sales: "+this.salesForTheMonth+"\nMoney Profit Of Company: "
		+getCompanyMoneyProfit()+"\nHere Is a List Of all the employees in the company work hours \nfrom where and efficient hours\n\n");
		for(Department d:this.allDepartments) {
			sb.append("\n\n"+d.getNameOfDepartment()+":\n");
			for(Employee emp:d.getEmployees()) {
				if(emp.getClass().getSimpleName().equals("EmployeePerHourSalary")){
					normalWorkHoursEmployee =calculateWorkHours((EmployeePerHourSalary)(emp));
					normalWorkHours+= normalWorkHoursEmployee;
					effitentHoursEmployee =  calculateEfitientHours(emp,emp.getPrefernce().getPreference()) ;
					efficientlyHoursTotal +=effitentHoursEmployee;
					effitiencyGain = (effitentHoursEmployee-normalWorkHoursEmployee);
					effitiencyGain = Math.round(effitiencyGain * 100.0) / 100.0;
					
					sb.append("Employee Name: "+emp.getName()+"\nPreference: "+emp.getPrefernce().toString()
							+"\nWork Hours: "+emp.getAssambledTime()+"Normal Work Hours: "+normalWorkHoursEmployee+
							"\nEffitent Work Hours: "+effitentHoursEmployee+"\nEffitency Gain: "+
							effitiencyGain+"\n\n");
					
				}
				else {
					normalWorkHours+=9;
					normalWorkHoursEmployee=9;
					effitentHoursEmployee = calculateEfitientHours(emp,emp.getPrefernce().getPreference()) ;
					efficientlyHoursTotal += effitentHoursEmployee;
					effitiencyGain = (effitentHoursEmployee-normalWorkHoursEmployee);
					effitiencyGain = Math.round(effitiencyGain * 100.0) / 100.0;
					sb.append("Employee Name: "+emp.getName()+"\nPreference: "+emp.getPrefernce().toString()
							+"\nWork Hours: "+emp.getAssambledTime()+"Normal Work Hours: "+normalWorkHoursEmployee+
							"\nEffitent Work Hours: "+effitentHoursEmployee+"\nEffitency Gain: "+
							effitiencyGain+"\n\n");
					
				}
			
			}
		}
		sb.append("In total all employees worked today "+normalWorkHours+" Hours.\nIn total the Effitiency Hours Were: "
		+efficientlyHoursTotal+"\nThe Effitiency Gain For the Company Was "+(efficientlyHoursTotal-normalWorkHours)+" Hours.");
		return sb.toString();
		
	}
	
	
	
	

	private double calculateEfitientHours(Employee emp, Preferences preference) {
		double effitenceHours=0;
		int startHour = emp.getStartHour();
		double startMinConverted = emp.getStartMin()/60.0;
		int endHour = emp.getEndHour();
		double endMinConverted = emp.getEndMin()/60.0;
		double hours8to17 = getHowManyHours8To17(startHour, startMinConverted, endHour, endMinConverted);
		double hoursAfter17 = getHowManyHoursAfter17(startHour, startMinConverted, endHour, endMinConverted);
		double hoursBefore8 = getHowManyWorkHoursBefore8(startHour, startMinConverted, endHour, endMinConverted);
		if(preference.equals(Preferences.StartWorkingEarlier)) {
			effitenceHours = hours8to17+(hoursAfter17*NOTEFFICIENTHOUR)+(hoursBefore8*EFFICIENTHOUR);
			effitenceHours = Math.round(effitenceHours * 100.0) / 100.0;
		}
		else if(preference.equals(Preferences.StartWorkingLater)) {
			effitenceHours = hours8to17+(hoursAfter17*EFFICIENTHOUR)+(hoursBefore8*NOTEFFICIENTHOUR);
			effitenceHours = Math.round(effitenceHours * 100.0) / 100.0;
			return effitenceHours;
		}
		
		else if(preference.equals(Preferences.StayTheSame)) {
			effitenceHours = ((hoursBefore8+hoursAfter17)*NOTEFFICIENTHOUR)+hours8to17;
			effitenceHours = Math.round(effitenceHours * 100.0) / 100.0;
		}
		else if(preference.equals(Preferences.WorkFromHome)) {
			effitenceHours = endHour+endMinConverted-startHour-startMinConverted;
			if(emp.isWorkFromHome()) {
				effitenceHours = effitenceHours*WORKFROMHOME;
				effitenceHours = Math.round(effitenceHours * 100.0) / 100.0;
			}
		}
		effitenceHours = Math.round(effitenceHours * 100.0) / 100.0;
		return effitenceHours;
	}

	 
	private double getHowManyWorkHoursBefore8(int sHour,double startMinConverted,int eHour,double endMinConverted) {
		double howManyH=0;
		if(sHour>=8)
			return 0;
		else {
			if(eHour<8) {
				howManyH = eHour+endMinConverted - ( sHour+startMinConverted);
			}
			else {
				howManyH = 8 - sHour-startMinConverted;
			}
		}
		return howManyH;
	}
	
	
	private double getHowManyHours8To17(int sHour,double sMinConverted,int eHour,double eMinConverted) {
		double howMany = 0;
		if(eHour<8||sHour>=17)
			return 0;
		else if(sHour<8) {
			if(eHour>=17)
				howMany = 9;
			else 
				howMany = eHour+eMinConverted-8;
		}
		else {
			if(eHour>=17)
				howMany = 17 - (sHour+sMinConverted);
			else 
				howMany = eHour + eMinConverted - (sHour + sMinConverted);
		}
		return howMany;
	}
	
	private double getHowManyHoursAfter17(int sHour,double sMinConverted,int eHour,double eMinConverted) {
		double howManyH=0;
		if(eHour<17)
			return 0;
		else {
			if(sHour<17) {
				howManyH = eHour+eMinConverted - 17;
			}
			else {
				howManyH = eHour+eMinConverted - sHour-sMinConverted;
			}
		}
		return howManyH;
		
	}

	

	
	
	
	private double calculateWorkHours(EmployeePerHourSalary emp) {
		double workHours = (emp.getEndHour()+emp.getEndMin()/60.0)-(emp.getStartHour()+(emp.getStartMin()/60.0));
		return workHours;
				
	}
	
	public static Company readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(FILENAME));
		Company c = (Company) inFile.readObject();
		inFile.close();
		return c;
	}

	public void saveToFile() throws FileNotFoundException, IOException {
		ObjectOutputStream OutFile = new ObjectOutputStream(new FileOutputStream(FILENAME));
		OutFile.writeObject(this);
		OutFile.close();
	}
	public void setCompanyName(String companyName) {
		this.nameOfCompany = companyName;
		
	}

	
}
