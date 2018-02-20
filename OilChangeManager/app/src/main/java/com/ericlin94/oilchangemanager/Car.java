package com.ericlin94.oilchangemanager;

public class Car {
	private Make make;
	private String owner;
	public Car(Make make,String owner) {
		this.make=make;
		this.owner=owner;
	}
	public Make getMake() {return make;}
	public String getOwner() {return owner;}
	public void setMake(Make make) {this.make=make;}
	public void setOwner(String owner) {this.owner=owner;}
	public String toString() {
		return "Make: "+make+"    Owner: "+owner;
	}
	
}
