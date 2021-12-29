package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Department;
import model.Employee;
import model.Preference;
import model.Role;

public interface CompanyUI {
	public String getCompanyProfit();
	public String getDepartmentProfit(String departmentName);
	public String getEmployeesProfit() ;
	public String getStringRolesInDepartment(String departmentName);
	public Employee getEmployeeByName(String departmentName, String employeeName);
	public boolean isEmployeePerHour(String departmentName, String employeeName);
	public Department getDepartmentByName(String departmentName);
	public void setDepartmentHours(String departmentName, String startTime, boolean workFromHome);
	public void setPerHourEmployeeWorkTimes(String departmentName,String employeeName,String StartTime, String endTime,boolean workFromHome);
	public void setEmployeeWorkTimes(String departmentName,String employeeName, String startTime,boolean workFromHome);
	public void UpdateMontlySales(int monthlySales);
	public ArrayList<Role> getRolesInDepartment(String departmentName);
	public void addBaseSalaryEmployee(String departmentName, String employeeName, String employeeId, LocalDate birthDate, Preference preference,String roleName, int baseSalary)throws Exception ;
	public void addPerHourEmployee(String departmentName, String employeeName, String employeeId, LocalDate birthDate, Preference preference,String roleName, int salaryPerHour,int workHoursPerMonth)throws Exception ;
	public void addBasePercentEmployee(String departmentName, String employeeName, String employeeId, LocalDate birthDate, Preference preference,String roleName, int baseSalary, double percentOfAllSales)throws Exception ;
	public Role getRoleByName(Department department, String roleName) ;
	public double getRemainingPrecentOfAllSales();
	public void updateCanChangeWorkHoursRole(String departmentName, String roleName, boolean canChangeWorkHours) throws Exception;
	public ArrayList<String> getStringRolesArrByDepartment(String departmentName);
	public void updateDepartmentPreference(String departmentName, boolean canChangeHours, boolean MustSync,Preference pref) ;
	public void autoGenData();
	public boolean loadFromFile();
	public void setCompanyName(String companyName);
	public void saveToFile();
	public void addDepartment(String departmentName,boolean canChangeWorkHours,boolean mustSync,Preference preference);
	public void addRoleToDepartment(String departmentName, String nameOfRole, boolean canChangeWorkHours) ;
	public String getAllEmployeesString();
	public String getAllDepartmentsString();
	public ArrayList<String> getDepartmentsNames();
	public ArrayList<String> getEmployeesInDepartment(String departmentName);
	public String getCompanyDataString();
	

}
