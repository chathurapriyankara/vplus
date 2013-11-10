package com.virtusa.vplus;


public class RaveList {
	
	private String raveType;
	private String raveDis;
	private String ravePerson;
	private int thumbsupID;
	
	public RaveList(String raveType, String raveDis, String ravePerson, int thumbsupID) {
		super();
		this.raveType = raveType;
		this.raveDis = raveDis;
		this.ravePerson = ravePerson;
		this.thumbsupID = thumbsupID;
	}

	public String getRaveType() {
		return raveType;
	}

	public String getRaveDis() {
		return raveDis;
	}
	public String getRavePerson() {
		return ravePerson;
	}

	public int getThumbsupID() {
		return thumbsupID;
	}

}
