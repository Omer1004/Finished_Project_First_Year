package model;

import java.io.Serializable;

public class Role implements MustSyncable,CanChangeWorkHoursable,Serializable{


	private static final long serialVersionUID = -1453854664808904104L;
	private String nameOfRole;
	private static int numOfRoles = 0;
	private boolean canChangeWorkHours;
	
	public Role(String nameOfRole,boolean canChangeWorkHours) {
		this.nameOfRole = nameOfRole;
		this.canChangeWorkHours = canChangeWorkHours;
		numOfRoles++;
	}
	
	
	
	
	public void setCanChangeWorkHours(boolean canChangeWorkHours) {
		this.canChangeWorkHours = canChangeWorkHours;
	}
	
	public boolean getCanChangeWorkHours() {
		return canChangeWorkHours;
	}
	
	public boolean getMustSync() {
		return canChangeWorkHours;
	}


	public void setCanChangePreference(boolean canChangeWorkHours) {
		this.canChangeWorkHours = canChangeWorkHours;
	}
	



	public String getNameOfRole() {
		return nameOfRole;
	}

	public void setNameOfRole(String nameOfRole) {
		this.nameOfRole = nameOfRole;
	}

	public static int getNumOfRoles() {
		return numOfRoles;
	}


	
	public boolean equals(Object obg) {
		if(obg.getClass().getSimpleName().equals("Role"))
			if(this.nameOfRole.equals(((Role)(obg)).getNameOfRole()))
				return true;
		return false;
	}
	
	public String toString() {
		String s = "Name Of Role: "+this.nameOfRole+"\nCan Change Work Hours: "+ this.canChangeWorkHours;
		return s;
	}
	 
	public static void dedactNumOfRoles() {
		numOfRoles--;
	}

}
