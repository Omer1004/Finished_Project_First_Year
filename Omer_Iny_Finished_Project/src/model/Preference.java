package model;

import java.io.Serializable;

public class Preference implements Serializable{
	private static final long serialVersionUID = 6267890835902267970L;

	public enum Preferences {StartWorkingEarlier,StartWorkingLater,StayTheSame,WorkFromHome};
	private Preferences preference;
	
	public Preference(Preferences preference) {
		this.preference = preference;
	}
	
	public boolean equals(Object obg) {
		if(obg==null)
			return false;
		if(!obg.getClass().getSimpleName().equals("Preference"))
			return false;
		else {
			if(this.preference==((Preference)obg).getPreference())
				return true;
			return false;
				
		}
	}

	public Preferences getPreference() {
		return this.preference;
	}
	
	public String toString() {
		return this.preference.name();
	}
}
