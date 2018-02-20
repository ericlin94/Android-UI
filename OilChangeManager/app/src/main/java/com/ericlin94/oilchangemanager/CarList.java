package com.ericlin94.oilchangemanager;

public class CarList {// insert, append, remove half-tested
	private CarListNode head;
	private CarListNode tail;
	private CarListNode cursor;
	private int numCars;
	public CarList() {
		head=null;
		tail=null;
		cursor=null;
	}
	public int numCars() {
		return numCars;
	}
	public Car getCursorCar() {
		return cursor.getData();
	}
	public void resetCursorToHead() {
		cursor=head;
	}
	public void resetCursorToTail() {
		cursor=tail;
	}
	public void cursorForward() throws EndOfListException {
		if(cursor==tail)
			throw new EndOfListException();
		else
			cursor=cursor.getNext();
	}
	public void cursorBackward() throws EndOfListException{
		if(cursor==head)
			throw new EndOfListException();
		else
			cursor=cursor.getPrev();
	}
	public void insertBeforeCursor(Car newCar) throws IllegalArgumentException{
		if(newCar==null)
			throw new IllegalArgumentException();
		else {
			CarListNode temp=new CarListNode(newCar);
			if(cursor!=null) {
				if(cursor==head) {
					cursor.setPrev(temp);
					temp.setNext(cursor);
					head=temp;
					numCars++;
				}
				else {
					cursor.getPrev().setNext(temp);
					temp.setPrev(cursor.getPrev());
					cursor.setPrev(temp);
					temp.setNext(cursor);
					numCars++;
				}
			}
			else {
				head=temp;
				tail=temp;
				cursor=temp;
				numCars++;
			}
			
		}
	}
	public void appendToTail(Car newCar) throws IllegalArgumentException{
		if(newCar==null)
			throw new IllegalArgumentException();
		else {
			CarListNode temp=new CarListNode(newCar);
			if(tail!=null) {
				tail.setNext(temp);
				temp.setPrev(tail);
				tail=tail.getNext();
				numCars++;
			}
			else {
				tail=temp;
				head=temp;
				cursor=temp;
				numCars++;
			}
		}
	}
	public Car removeCursor() throws EndOfListException {//untested
		if(cursor==null)
			throw new EndOfListException();
		else {
			Car temp=cursor.getData();
			if(cursor!=head) {
				if(cursor!=tail) {
					cursor.getPrev().setNext(cursor.getNext());
					cursor.getNext().setPrev(cursor.getPrev());
					cursor=cursor.getPrev();
					numCars--;
					return temp;
				}
				else {
					tail=cursor.getPrev();
					cursor=cursor.getPrev();
					cursor.setNext(null);
					numCars--;
					return temp;
				}
			}
			else {
				if(cursor!=tail) {
					head=cursor.getNext();
					cursor=cursor.getNext();
					cursor.setPrev(null);
					numCars--;
					return temp;
				}
				else {
					head=null;
					tail=null;
					cursor=null;
					numCars--;
					return temp;
				}
			}
		}
	}
	public void mergeList(CarList o) throws EndOfListException, IllegalArgumentException{//untested
		this.resetCursorToHead();
		o.resetCursorToHead();
		while(o.cursor!=null&&cursor!=tail) {
			if(cursor!=tail)
				this.cursorForward();
			this.insertBeforeCursor(o.removeCursor());

		}
		while(o.cursor!=null) {
			this.appendToTail(o.removeCursor());
		}
		numCars+=o.numCars;
	}
	public void sortList() throws EndOfListException{//new one
		CarListNode mark=head;
		CarListNode min=head;
		Car temp;
		this.resetCursorToHead();
		while(mark!=tail) {
			while(cursor!=tail) {
				if(cursor!=tail)
					this.cursorForward();
				if(min.getData().getMake().compareTo(cursor.getData().getMake())>0)
					min=cursor;
			}
			if(mark!=min) {
				cursor=min;
				temp=this.removeCursor();
				cursor=mark;
				this.insertBeforeCursor(temp);
			}
			else
				mark=mark.getNext();
			cursor=mark;
			min=mark;

		}

	}
	public String toString() {
		CarListNode nodePtr=head;
		String result="";
		int count=1;
		while(nodePtr!=null) {
			if(nodePtr==cursor)
				result+="->";
			result+=count+". "+nodePtr.getData()+"\n";
			nodePtr=nodePtr.getNext();
			count++;
		}
		if(result.equals(""))
			result+="[Empty]\n";
		return result;
	}
	
}
