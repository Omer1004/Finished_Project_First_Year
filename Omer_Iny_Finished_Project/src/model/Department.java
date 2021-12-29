package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Department implements Syncable,MustSyncable,CanChangeWorkHoursable,Serializable{
	
	
	
	/*
	 * KEY GUILDLINES 
	 * 
	 * IF DEPARTMENT CANT CHANGE WORK HOURS ALL OF THE ROLES WILL CHANGE CHANGE WORK HOURS TO FALSE 
	 * IF DEPARTMENT MUST BE SYNCED ALL ROLES WILL CHANGE CHANGE WORK HOURS TO TRUE
	 * IF ROLE CHANGE WORK HOURS = FALSE THEN EMPLOYEE WORK HOURS WILL BE AUTOMATICLY 8:00-17:00 AND EFFITIUNCHI WILL NOT CHANGE
	 * ROLES ARE DEFINED BY THERE DEPARTMENT 2 ROLES BY THE SAME NAME ARE NOT THE SAME ROLES IN DIFFRENT DEPARTMENTS
	 */

	private static final long serialVersionUID = 4349177879931064329L;
	private Set<Role> roles;
	private Set<Employee> employees;
	private boolean mustSync;
	private boolean canChangeWorkHours;
	private Preference departmentPreference;
	private String nameOfDepartment;
	
	public Department(String nameOfDepartment,boolean canChangeWorkHours,boolean mustSync,Preference preference) {
		this.employees = new HashSet<Employee>();
		this.canChangeWorkHours = canChangeWorkHours;
		this.mustSync = mustSync;
		this.departmentPreference = preference;
		this.roles = new HashSet<Role>();
		this.nameOfDepartment = nameOfDepartment;
	}
	
	public boolean getCanChangeWorkHours() {
		return canChangeWorkHours;
	}

	

	public boolean getMustSync() {
		return mustSync;
	}
	
	public void setCanChangeWorkHours(boolean canChangeWorkHours) {
		this.canChangeWorkHours = canChangeWorkHours;
		SyncRolesPreferences();
	}
	
	public void setMustSync(boolean mustSync) {
		this.mustSync = mustSync;
		SyncRolesPreferences();
	}

	public Preference getDepartmentPreference() {
		return departmentPreference;
	}

	public void setDepartmentPreference(Preference departmentPreference) throws Exception {
		this.departmentPreference = departmentPreference;
		if(mustSync)
			Sync();
	}
	
	public void Sync() throws Exception {
		SyncRolesPreferences();
		for(Employee emp:this.employees) {
			emp.setPrefernce(departmentPreference);
		}
	}

	
	
	public void SyncRolesPreferences() {
		for(Role role: this.roles) {
			if(!this.canChangeWorkHours)
				role.setCanChangeWorkHours(false);
			else if(this.mustSync)
				role.setCanChangeWorkHours(true);
		}
	}
	public String toString() {
		StringBuffer sb = new StringBuffer("Department Name: "+this.nameOfDepartment+"\n");
		if(this.mustSync)
			sb.append("This Department works at Synced hours\n");
		else
			sb.append("This Department does not works at Synced hours\n");
		for(Employee emp:this.employees) {
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
	
	public boolean equals(Object obg) {
		if(obg.getClass().getSimpleName().equals("Department"))
			if(((Department)(obg)).getNameOfDepartment().equals(this.nameOfDepartment))
				return true;
		return false;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	
	public ArrayList<Role> getRolesAsArrayList(){
		ArrayList<Role> rolesArr = new ArrayList<Role>();
		for(Role r : this.roles) {
			rolesArr.add(r);
		}
		return rolesArr;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}
	
	public ArrayList<Employee> getEmployeesAsArrayList(){
		ArrayList<Employee> emps = new ArrayList<Employee>();
		for(Employee e:this.employees) {
			emps.add(e);
		}
		return emps;
	}


	public String getNameOfDepartment() {
		return nameOfDepartment;
	}
	
	//####################################3
	
	public boolean addRole(Role role) throws Exception {
		if(!this.canChangeWorkHours)
			role.setCanChangeWorkHours(false);
		else if(this.mustSync)
			role.setCanChangeWorkHours(true);
		return this.roles.add(role);	
	}

	public void addEmployee(Employee emp) {
		this.employees.add(emp);
	}

	public String getRolesAsString() {
		StringBuffer sb = new StringBuffer("Roles in "+this.nameOfDepartment+":\n");
		for(Role r : this.roles) {
			sb.append(r.toString()+"\n");
		}
		return sb.toString();
	}

}
