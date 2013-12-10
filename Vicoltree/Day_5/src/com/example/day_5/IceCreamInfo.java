package com.example.day_5;

public class IceCreamInfo {

	private String iceName;
	private int icePhotoid;
	private String details;

	public IceCreamInfo() {
		// TODO Auto-generated constructor stub
	}

	public IceCreamInfo(String iceName, int icePhotoid, String details) {
		super();
		this.iceName = iceName;
		this.icePhotoid = icePhotoid;
		this.details = details;
	}

	public String getIceName() {
		return iceName;
	}

	public void setIceName(String iceName) {
		this.iceName = iceName;
	}

	public int getIcePhotoid() {
		return icePhotoid;
	}

	public void setIcePhotoid(int icePhotoid) {
		this.icePhotoid = icePhotoid;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
