package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import model.AutoGen;
import model.Company;
import model.Department;
import model.Employee;
import model.EmployeeBasePrecentageSalary;
import model.EmployeeBaseSalary;
import model.EmployeePerHourSalary;
import model.Preference;
import model.Preference.Preferences;
import model.Role;
import view.View;


//Omer Iny - 206571739

public class Controller implements CompanyBackendable, CompanyUI {
	private View view;
	private Company model;
	
	
	public Controller(View view,Company model) {
		this.model = model;
		this.view = view;
		view.setListener(this);
		model.setListener(this);
	}
	
	

	public static ArrayList<Preference> getPreferencesArray() {
		return Company.getPreferencesArray();
	}



	public void addDepartment(String departmentName,boolean canChangeWorkHours,boolean mustSync,Preference preference) {
		try {
			this.model.addDepartment(departmentName,canChangeWorkHours ,mustSync,preference);
			View.showMsg("Depatment added succesfully");
		}
		catch (Exception e) {
			View.showMsg(e.getMessage());
		}
		
	}
	
	public ArrayList<String> getDepartmentsNames(){
		return this.model.getDepartmentsNames();
	}



	public void autoGenData() {
		this.model = AutoGen.autoGen();		
	}



	public boolean loadFromFile() {
		try {
			this.model = Company.readFromFile();
			View.showMsg("Load From File Succesful");
			return true;
		} catch (Exception e) {
			View.showMsg(e.getMessage());
			return false;
		} 
	}



	public void saveToFile() {
		try {
			model.saveToFile();
			View.showMsg("Save To File Succesful");
		} catch (Exception e) {
			View.showMsg("Somthing Went Wrong the file wasnt Saved\n"+e.getMessage());
		} 
		
	}



	public void addRoleToDepartment(String departmentName, String nameOfRole, boolean canChangeWorkHours) {
		ArrayList<Department> departments = model.getDepartments();
		try {
			for(Department d :departments) {
				if(d.getNameOfDepartment().equals(departmentName)) {
					model.addRole(d, nameOfRole, canChangeWorkHours);
					View.showMsg("Role Was Added to "+departmentName+" Department.");
					break;
				}
					
			}
		}
		catch (Exception e) {
			View.showMsg(e.getMessage());
		}
		
		
		
	}

	public Department getDepartmentByName(String departmentName) {
		ArrayList<Department> departments = model.getDepartments();
		for(Department d : departments) {
			if(d.getNameOfDepartment().equals(departmentName))
				return d;
		}
		return null;
	}

	public String getNameOfCompany() {
		return model.getNameOfCompany();
	}



	public ArrayList<Role> getRolesInDepartment(String departmentName) {
		Department d = getDepartmentByName(departmentName);
		return d.getRolesAsArrayList();
	}



	public Role getRoleByName(Department department, String roleName) {
		ArrayList<Role> roles = department.getRolesAsArrayList();
		for(Role r : roles) {
			if(r.getNameOfRole().equals(roleName))
				return r;
		}
		return null;
		}



	public double getRemainingPrecentOfAllSales() {
		return model.getRemainingPrecentOfAllSales();
	}



	public void addBaseSalaryEmployee(String departmentName, String employeeName, String employeeId, LocalDate birthDate, Preference preference,
			String roleName, int baseSalary) throws Exception {
		Department department = getDepartmentByName(departmentName);
		Role role = getRoleByName(department, roleName);
		EmployeeBaseSalary emp = new EmployeeBaseSalary(employeeName,employeeId,birthDate,preference,role,baseSalary);
		emp.setWorkTimes(8, 0, 17, 0);
		model.addEmployee(department, emp);
	}



	public void addPerHourEmployee(String departmentName, String employeeName, String employeeId, LocalDate birthDate, Preference preference,
			String roleName, int salaryPerHour,int workHoursPerMonth) throws Exception {
		Department department = getDepartmentByName(departmentName);
		Role role = getRoleByName(department, roleName);
		EmployeePerHourSalary emp = new EmployeePerHourSalary(employeeName,employeeId,birthDate,preference,role,salaryPerHour,workHoursPerMonth);
		emp.setWorkTimes(8, 0, 17, 0);
		model.addEmployee(department, emp);
	
	}



	public void addBasePercentEmployee(String departmentName, String employeeName, String employeeId, LocalDate birthDate, Preference preference,
			String roleName, int baseSalary, double percentOfAllSales) throws Exception {
		Department department = getDepartmentByName(departmentName);
		Role role = getRoleByName(department, roleName);
		EmployeeBasePrecentageSalary emp = new EmployeeBasePrecentageSalary(employeeName,employeeId,birthDate,preference,role,baseSalary,percentOfAllSales);
		emp.setWorkTimes(8, 0, 17, 0);
		model.addEmployee(department, emp);
		
		
	}



	public void UpdateMontlySales(int monthlySales) {
		model.updateMonthlySales(monthlySales);
		View.showMsg("Monthly Sales Was Updated Succecfully");
	}



	public ArrayList<Department> getDepartments() {
		return this.model.getDepartments();
	}



	public void setEmployeeWorkTimes(Employee emp, int startH, int startM, int endH, int endM) {
		try {
			emp.setWorkTimes(startH, startM, endH, endM);
		} catch (Exception e) {
			View.showMsg(e.getMessage());
		}
		
	}



	public void setPerHourEmployeeWorkTimes(String departmentName,String employeeName,
			String StartTime, String endTime,boolean workFromHome) {
		try {
			setEmployeeWorkTimes(getEmployeeByName(departmentName, employeeName),
					getHourFromString(StartTime),getMinFromString(StartTime),
					getHourFromString(endTime),getMinFromString(endTime));	
			getEmployeeByName(departmentName, employeeName).setWorkFromHome(workFromHome);
			View.showMsg("Employee's Hours Was Set!");
		}
		
		catch (Exception e) {
			View.showMsg(e.getMessage());
		}
	}
	
	public static int getHourFromString(String Time)throws Exception{
		if(Time.length()!=5||Time.charAt(2)!=':') {
			throw new Exception("Invalid Time, Time Must Be In A Format NN:NN");
		}
		if(Time.charAt(0)<'0'||Time.charAt(0)>'9')
			throw new Exception("Invalid Time, Time Must Be In A Format NN:NN");
		if(Time.charAt(1)<'0'||Time.charAt(1)>'9')
			throw new Exception("Invalid Time, Time Must Be In A Format NN:NN");
		String sHour = ""+Time.charAt(0)+Time.charAt(1);
		int hour = Integer.parseInt(sHour);
		if(hour<0||hour>24)
			throw new Exception("Invalid Time, Time Must Be In A Format NN:NN");
		return hour;
	}
	
	public static int getMinFromString(String Time)throws Exception{
		if(Time.charAt(3)<'0'||Time.charAt(3)>'9')
			throw new Exception("Invalid Time, Time Must Be In A Format NN:NN");
		if(Time.charAt(4)<'0'||Time.charAt(4)>'9')
			throw new Exception("Invalid Time, Time Must Be In A Format NN:NN");
		String sMin = ""+Time.charAt(3)+Time.charAt(4);
		int min = Integer.parseInt(sMin);
		if(min<0||min>60)
			throw new Exception("Invalid Time, Time Must Be In A Format NN:NN");
		return min;
	}



	public void setEmployeeWorkTimes(String departmentName,String employeeName, String startTime,boolean workFromHome) {
		try {
			int hour = getHourFromString(startTime) ;
			int min = getMinFromString(startTime);
			int endHour = hour+9;
			if(endHour>24)
				endHour-=24;
			setEmployeeWorkTimes(getEmployeeByName(departmentName, employeeName),hour,min ,endHour, min);
			getEmployeeByName(departmentName, employeeName).setWorkFromHome(workFromHome);
			View.showMsg("Employee's Hours Was Set!");
		}
		catch (Exception e) {
			View.showMsg("Somthing went Wrong...\n"+e.getMessage());
		}
		
	}

/*
 * public void setDepartmentHours(Department department, String startTime) throws Exception {
		int hour = getHourFromString(startTime);
		int min = getMinFromString(startTime);
		int endHour = hour+9;
		if(endHour>24)
			endHour-=24;
		if(department.getEmployeesAsArrayList().size()!=0) {
			for(Employee emp:department.getEmployeesAsArrayList()) {
				emp.setWorkTimes(hour, min, endHour, min);
			}
		}
		
		
	}
 */

	



	public String getAllEmployeesString() {
		return model.getAllEmployeesToString(model.getAllEmployees());
	}



	public String getAllDepartmentsString() {
		return model.getAllDepartmentsString();
	}



	public String getStringRolesInDepartment(String departmentName) {
		
		return model.getAllRolesInDepartment(getDepartmentByName(departmentName));
	}



	public String getCompanyDataString() {
		return model.getCompanyData();
	}



	public ArrayList<String> getStringRolesArrByDepartment(String departmentName) {
		ArrayList<String> roles = new ArrayList<String>();
		Department d = getDepartmentByName(departmentName);
		for(Role r :d.getRoles()) {
			roles.add(r.getNameOfRole());
		}
		return roles;
	}



	public void updateCanChangeWorkHoursRole(String departmentName, String roleName, boolean canChangeWorkHours) throws Exception {
		Department department = getDepartmentByName(departmentName);
		Role role = getRoleByName(department, roleName);
		if(!department.getCanChangeWorkHours()) {
			throw new Exception("Department cant change work hours\nthe update was not made");
		}
		else if(department.getMustSync()&&!canChangeWorkHours) {
			throw new Exception("Department must sync work hours so there is roles \nthat cant change work hours"
					+ "\nthe update was not made");
		}
		else {
			role.setCanChangePreference(canChangeWorkHours);
			if(!canChangeWorkHours)
				model.updateDepartmentWorkHours(department,role);
		}
		
	}



	public void updateDepartmentPreference(String departmentName, boolean canChangeHours, boolean MustSync,Preference pref) {
	try {
		Department d = getDepartmentByName(departmentName);
		d.setCanChangeWorkHours(canChangeHours);
		d.setMustSync(MustSync);
		d.setDepartmentPreference(pref);
		
		
		if(!canChangeHours) {
	
				d.setMustSync(true);
				for(Employee emp :d.getEmployeesAsArrayList()) {
					emp.setWorkTimes(8, 0, 17, 0);
				}
		}
		else if(MustSync) {
			if(d.getEmployees().size()!=0) {
				Employee first = d.getEmployeesAsArrayList().get(0);
				int sHour = first.getStartHour();
				int sMin = first.getStartMin();
				int eHour = first.getEndHour();
				int eMin = first.getEndMin();
					for(Employee emp :d.getEmployeesAsArrayList()) {
						emp.setWorkTimes(sHour, sMin, eHour, eMin);
					}
			}
		}
		
		View.showMsg("Department Preference was Updated");
		
	}
		catch (Exception e) {
			View.showMsg(e.getMessage());
		}
	}



	public ArrayList<String> getEmployeesInDepartment(String departmentName) {
		Department d = getDepartmentByName(departmentName);
		ArrayList<Employee> emps = d.getEmployeesAsArrayList();
		ArrayList<String> empsNames = new ArrayList<String>();
		for(Employee emp:emps) {
			empsNames.add(emp.getName());
		}
		
		return empsNames;
	}



	public Employee getEmployeeByName(String departmentName, String employeeName) {
		Department d = getDepartmentByName(departmentName);
		for(Employee emp:d.getEmployeesAsArrayList()) {
			if(emp.getName().equals(employeeName))
				return emp;
		}
		return null;
	}



	public boolean isEmployeePerHour(String departmentName, String employeeName) {
		if(getEmployeeByName(departmentName, employeeName).getClass().getSimpleName().equals("EmployeePerHourSalary"))
			return true;
		return false;
	}



	public void setDepartmentHours(String departmentName, String startTime, boolean workFromHome) {
		try {
			int sHour = getHourFromString(startTime);
			int sMin = getMinFromString(startTime);
			for(Employee emp:getDepartmentByName(departmentName).getEmployeesAsArrayList()) {
				setEmployeeWorkTimes(emp, sHour, sMin, sHour+9,sMin);
				emp.setWorkFromHome(workFromHome);
			}
			View.showMsg("Department Hours Was Set!");

		}
		catch (Exception e) {
			View.showMsg(e.getMessage());
		}
		
		
	}
	public double getCompanyMoneyProfit() {
		return model.getCompanyMoneyProfit();
	}



	public String getEmployeesProfit() {
		return model.getEmployeesDailyEfficiently();
		}



	public String getCompanyProfit() {
		return model.getCompanyEffitencyGain();
	}



	public String getDepartmentProfit(String departmentName) {
		return model.getDepartmentDailyEffitiency(getDepartmentByName(departmentName));
	}



	public void showMsg(String message) {
		View.showMsg(message);
		
	}



	public void setCompanyName(String companyName) {
		model.setCompanyName(companyName);
		
	}
	


}
