package model;
import java.io.Serializable;
import java.time.LocalDate;
public abstract class Employee implements Serializable{


	private static final long serialVersionUID = 3153424356028272949L;
	protected String name;
	protected String id;
	protected LocalDate birthdate;
	protected Preference prefernce;
	protected Role role;
	protected int startHour, startMin,endHour,endMin;
	protected boolean workFromHome;
	
	public Employee(String name,String id,LocalDate birthdate,Preference prefernce,Role role) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.role = role;
		startHour=8;
		startMin=0;
		endHour=17;
		endMin=0;
		workFromHome = false;
		this.prefernce = prefernce;
		
	}
	
	public boolean equals(Object obg) {
		if(obg.getClass().getSimpleName().equals("Employee")) {
			if(((Employee) (obg)).getId()==this.id) {
				return true;
			}
			
		}
		return false;
	}
	
	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getStartMin() {
		return startMin;
	}

	public void setStartMin(int startMin) {
		this.startMin = startMin;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getEndMin() {
		return endMin;
	}

	public void setEndMin(int endMin) {
		this.endMin = endMin;
	}

	public void setWorkTimes( int startHour,int startMin,int endHour,int endMin)throws Exception {
		if(startHour>endHour) {
			throw new Exception("End Time Cant be before Start Time");
		}
		else if(startHour==endHour&&startMin>endMin){
			throw new Exception("End Time Cant be before Start Time");
		}
		this.startHour = startHour;
		this.startMin = startMin;
		this.endHour = endHour;
		this.endMin = endMin;
	}
	
	public boolean isWorkFromHome() {
		return workFromHome;
	}

	public void setWorkFromHome(boolean workFromHome) {
		this.workFromHome = workFromHome;
	}

	public String toString() {
		String kind="";
		if(this.getClass().getSimpleName().equals("EmployeeBasePrecentageSalary"))
			kind = "\nKind Of Employee: base salary + bonuses\n";
		else if(this.getClass().getSimpleName().equals("EmployeeBaseSalary"))
			kind = "\nKind Of Employee: base salary\n";
		else if(this.getClass().getSimpleName().equals("EmployeePerHourSalary"))
			kind = "\nKind Of Employee: per hour salary\n";
		String s = "Role: "+role.getNameOfRole()+kind +"Name: "+this.name+"\nID: "+this.id+"\nBirth Day: "+this.birthdate.getDayOfMonth()+
				"/"+this.birthdate.getMonthValue()+"/"+this.birthdate.getYear()+"\nPreference: "+this.prefernce+"\nStart and End Time: "+getAssambledTime()+
				"Work From Home: "+this.workFromHome+"\n";
		
		return s;
		
	}

	public String getAssambledTime() {
		String s ="";
		if(startHour<10) {
			s = s+"0"+this.startHour+":";
		}
		else
			s = s+this.startHour+":";
		
		if(startMin<10) {
			s = s+"0"+this.startMin+"----";
		}
		else
			s = s+this.startMin+"----";
		if(endHour<10) {
			s=s+"0"+this.endHour+":";
		}
		else
			s = s+this.endHour+":";
		if(endMin<10)
			s = s+"0"+this.endMin+"\n";
		else
			s = s+this.endMin+"\n";
		return s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Preference getPrefernce() {
		return prefernce;
	}
	
	public Role getRole() {
		return this.role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public void setPrefernce(Preference prefernce) {
		this.prefernce = prefernce;
	}
}
