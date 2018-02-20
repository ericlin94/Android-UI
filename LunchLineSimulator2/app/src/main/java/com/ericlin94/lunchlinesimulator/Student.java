package com.ericlin94.lunchlinesimulator;

/**
 * The <code>Student</code> class generates <code>Student</code>
 * objects for the lunch line simulator.
 *
 *
 * @author Heng An Lin
 *    e-mail: hengan.lin@stonybrook.edu
 *    Stony Brook ID:111316287
 **/

public class Student implements Cloneable {
    private String name;// 	The student's name
    private double money;// The amount of money that student has
    /**
     *Returns an instance of <code>Student</code>.
     * @param name, money
     * 		Parameters are for storing student's information.
     **/
    public Student(String name, double money) {
        this.name=name;
        this.money=money;
    }
    /**
     * Returns an String object of student's name.
     * @return Returns an String object of student's name.
     **/
    public String getName() {return name;}
    /**
     * Returns the amount of money that student has.
     * @return Returns the amount of money that student has.
     **/
    public double getMoney() {return money;}
    /**
     * This method is for modifying student's name.
     * @param name
     * <dt>Postconditions:
     * 		<dd>The student's name has been changed.
     **/
    public void setName(String name) {this.name=name;}
    /**
     * This method is for modifying the amount of money that student has.
     * @param money
     * <dt>Postconditions:
     * 		<dd>The amount of money that student possessed has been changed.
     **/
    public void setMoney(double money) {this.money=money;}

    /**
     * This method determines if two <code>Student</code> objects' name variables and money variables are equal
     * @param obj
     * @return Returns true if two objects' name variables and money variables are equal;otherwise, returns false.
     **/
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student)
            return name.equals(((Student)obj).getName())&&money==((Student)obj).getMoney();
        else
            return false;
    }
    /**
     * This method gives a copy of a <code>Student</code> object.
     * @return Returns a copy of a <code>Student</code> object.
     * @throws CloneNotSupportedException
     * 		Thrown to indicate that the clone method in class Object has been called to clone an
     * 		object, but that the object's class does not implement the Cloneable interface.
     **/
    @Override
    public Object clone() {
        try {
            return super.clone();
        }catch(CloneNotSupportedException ex) {
            return null;
        }
    }
    /**
     * This method returns a string representation of a <code>Student</code> object.
     * @return Returns a string representation of a <code>Student</code> object.
     **/
    @Override
    public String toString() {
        return "Name: "+name+"\t"+"$: "+money;
    }
}
