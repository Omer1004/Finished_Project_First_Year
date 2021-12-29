package model;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;
import view.View;

public class AutoGen {
	public static Company autoGen() {
		Company c = new Company("GetCompany'sidea.com");
		System.out.println("Loading........");
		ArrayList<Preference> p = new ArrayList<Preference>(Controller.getPreferencesArray());
		ArrayList<Department> departments = new ArrayList<Department>();
		departments.add(new Department("Managment",true,true,p.get(0)));
		departments.add(new Department("Sales",false,true,p.get(2)));
		departments.add(new Department("Cleaning",true,false,p.get(1)));
		departments.add(new Department("Maintence",true,true,p.get(3)));
		ArrayList<Role> r = new ArrayList<Role>();
		r.add(new Role("CEO",true));					//0
		r.add(new Role("Vice President",true));			//1
		r.add(new Role("Secretery",false));				//2
		r.add(new Role("General Mangager",true));		//3
		r.add(new Role("Sales Represenitive",true));	//4
		r.add(new Role("Janitor",false));				//5
		r.add(new Role("General Mangager",true));		//6
		r.add(new Role("Undefined Role",true));			//7
		r.add(new Role("HandiMan",true));				//8
		r.add(new Role("Undefined Role",true));			//9
		
		
		try {
			departments.get(0).addRole(r.get(0));	//0
			departments.get(0).addRole(r.get(1));	//1
			departments.get(0).addRole(r.get(2));	//2
			departments.get(1).addRole(r.get(3));	//3
			departments.get(1).addRole(r.get(4));	//4
			departments.get(2).addRole(r.get(5));	//5
			departments.get(2).addRole(r.get(6));	//6
			departments.get(2).addRole(r.get(7));	//7
			departments.get(3).addRole(r.get(8));	//8
			departments.get(3).addRole(r.get(9));	//9
			
		}
		catch (Exception e) {
			View.showMsg(e.getMessage());
		}
		c.addDepartment(departments.get(0));
		c.addDepartment(departments.get(1));
		c.addDepartment(departments.get(2));
		c.addDepartment(departments.get(3));
		ArrayList<Employee> employees = new ArrayList<Employee>();
		// EmployeeBasePrecentageSalary(String name, String id, LocalDate birthdate, Preference prefernce,Role role,double baseSalary,double percentFromSale)
		// EmployeeBaseSalary(String name, String id, LocalDate birthdate, Preference prefernce,Role role,double baseSalary)
		//(String name, String id, LocalDate birthdate, Preference prefernce,Role role,double salaryPerHour,int workHoursPerMonth)
		//0-2 menagment 3-4 sales, 5-7 cleaning,8-9 maintnence
		
		employees.add(new EmployeeBasePrecentageSalary("Shlomo PP","123456789",LocalDate.of(1987,1,1),p.get(0),r.get(0),10000,9.5));//0
		employees.add(new EmployeeBasePrecentageSalary("Eli Yaakubi","234567485",LocalDate.of(1994,8,4),p.get(1),r.get(1),9500,4.4));//0
		employees.add(new EmployeeBaseSalary("Mor Chen","987123654",LocalDate.of(1997,10,5),p.get(3),r.get(2),6000));//0
		employees.add(new EmployeeBaseSalary("Dan Profar","541236987",LocalDate.of(1978,8,5),p.get(3),r.get(4),8000));//1
		employees.add(new EmployeeBasePrecentageSalary("Josef Gasperoni","223344558",LocalDate.of(1998,10,8),p.get(0),r.get(3),8000,2.5));//1
		employees.add(new EmployeeBasePrecentageSalary("Josef H","234567891",LocalDate.of(1998,10,8),p.get(0),r.get(6),8000,2));//2
		employees.add(new EmployeePerHourSalary("Alehandro Huan","735698214",LocalDate.of(1970,7,5),p.get(1),r.get(5),20,40));//2
		employees.add(new EmployeeBaseSalary("Dok Brown","852741963",LocalDate.of(1995,8,5),p.get(2),r.get(5),5000));//2
		employees.add(new EmployeeBaseSalary("Dulitel Cohen","753412698",LocalDate.of(1990,10,10),p.get(2),r.get(6),7000));//2
		employees.add(new EmployeeBaseSalary("Yitzhak Haim","456987123",LocalDate.of(1990,9,4),p.get(3),r.get(7),7000));//2
		employees.add(new EmployeeBaseSalary("Donald Duck","159826347",LocalDate.of(1998,9,1),p.get(1),r.get(8),5000));//3
		employees.add(new EmployeeBaseSalary("Adam Haiun","132645978",LocalDate.of(1970,7,4),p.get(1),r.get(9),5000));//3
		employees.add(new EmployeePerHourSalary("Charly Smith","452178936",LocalDate.of(1970,7,6),p.get(1),r.get(9),35,50));//3
		//SET HOURS FOR PER HOURS EMPLOYEES
		employees.add(new EmployeePerHourSalary("Jhon Snow","756489132",LocalDate.of(1978,9,8),p.get(1),r.get(8),65,25));//3
		try {
			c.addEmployee(departments.get(0), employees.get(0));
			c.addEmployee(departments.get(0), employees.get(1));
			c.addEmployee(departments.get(0), employees.get(2));
			c.addEmployee(departments.get(1), employees.get(3));
			c.addEmployee(departments.get(1), employees.get(4));
			c.addEmployee(departments.get(2), employees.get(5));
			c.addEmployee(departments.get(2), employees.get(6));
			c.addEmployee(departments.get(2), employees.get(7));
			c.addEmployee(departments.get(2), employees.get(8));
			c.addEmployee(departments.get(2), employees.get(9));
			c.addEmployee(departments.get(3), employees.get(10));
			c.addEmployee(departments.get(3), employees.get(11));
			c.addEmployee(departments.get(3), employees.get(12));
			c.addEmployee(departments.get(3), employees.get(13));
			c.getAllEmployees().get(0).setWorkTimes(9, 0, 18, 0);
			c.getAllEmployees().get(1).setWorkTimes(9, 0, 18, 0);
			c.getAllEmployees().get(2).setWorkTimes(9, 0, 18, 0);
			c.getAllEmployees().get(3).setWorkFromHome(true);
			c.getAllEmployees().get(5).setWorkTimes(10, 0, 19, 0);
			c.getAllEmployees().get(5).setWorkFromHome(true);
			c.getAllEmployees().get(6).setWorkTimes(11, 30, 20, 30);
			c.getAllEmployees().get(7).setWorkTimes(9, 10, 18, 10);
			c.getAllEmployees().get(8).setWorkTimes(11, 30, 20, 30);
			c.getAllEmployees().get(9).setWorkTimes(9, 10, 18, 10);
			c.getAllEmployees().get(10).setWorkTimes(11, 30, 20, 30);
			c.getAllEmployees().get(11).setWorkTimes(9, 10, 18, 10);
			c.getAllEmployees().get(12).setWorkTimes(11, 30, 20, 30);
			c.getAllEmployees().get(13).setWorkTimes(7, 0, 16, 0);
			
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		
		

		
		System.out.println("AutoLoad Complete");
		return c;
	}
}
