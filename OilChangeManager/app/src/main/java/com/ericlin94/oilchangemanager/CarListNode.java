package com.ericlin94.oilchangemanager;

public class CarListNode {
	private Car data;
	private CarListNode next;
	private CarListNode prev;
	public CarListNode(Car initData)throws IllegalArgumentException {
		if(initData==null)
			throw new IllegalArgumentException();
		else {
			data=initData;
			next=null;
			prev=null;
		}
	}
	public Car getData() {return data;}
	public CarListNode getPrev() {return prev;}
	public CarListNode getNext() {return next;}
	public void setData(Car data) {this.data=data;}
	public void setPrev(CarListNode prev) {this.prev=prev;}
	public void setNext(CarListNode next) {this.next=next;}
	
	
}
