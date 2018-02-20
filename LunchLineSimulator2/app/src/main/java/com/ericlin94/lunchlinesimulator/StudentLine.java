package com.ericlin94.lunchlinesimulator;

/**
 * The <code>StudentLine</code> class implements an arrayList-like data structure that holds <code>Student</code> objects.
 *
 *
 * @author Heng An Lin
 *    e-mail: hengan.lin@stonybrook.edu
 *    Stony Brook ID:111316287
 **/

public class StudentLine implements Cloneable {
    private Student[]  students;//An array that holds Student objects.
    private int studentCount;//The number of students in the array.
    final int CAPACITY=20;//The maximum capacity of the array.
    /**
     * Returns a instance of <code>StudentLine</code>.
     **/
    public StudentLine() {
        //For coding convenience, the size of the actual array is CAPACITY+1, the students[0] will always be maintained to be null, and the
        //the actual capacity will still be 20.
        this.students=new Student[21];

    }
    /**
     * Returns the number of students in the student line.
     * @return Returns the number of students in the student line.
     */
    public int numStudents() {
        return studentCount;
    }
    /**
     * Returns a <code>Student</code> object's reference at the given index in the student line.
     * @param index
     * <dt>precondition:
     * 		<dd>The index must be less or equal to studentCount, and should not be less than one.
     * @return Student object's reference at the given index in the student line.
     * @throws ArrayIndexOutOfBoundsException
     * 			Thrown to indicate that an array has been accessed with an illegal index.
     */
    public Student getStudent(int index) throws ArrayIndexOutOfBoundsException{
        if(index>studentCount||index<1)
            throw  new ArrayIndexOutOfBoundsException();
        else
            return students[index];
    }
    /**
     * Removes a <code>Student</code> object's reference at the given index in the student line.
     * @param index
     * <dt>precondition:
     * 		<dd>studentCount must be larger than 0.
     * 		<dd>The index must be less or equal to studentCount, and should not be less than one.
     * @return Returns the reference of the removed Student object.
     * @throws ArrayIndexOutOfBoundsException
     * 			Thrown to indicate that an array has been accessed with an illegal index.
     * @throws EmptyLineException
     * 			Thrown to indicate that an array has no objects to be remove.
     */
    public Student removeStudent(int index) throws ArrayIndexOutOfBoundsException, EmptyLineException {//untested
        if(studentCount<=0)
            throw new EmptyLineException();
        else if(index>studentCount||index<1)
            throw new ArrayIndexOutOfBoundsException();
        else {
            Student result=students[index];//temporary variable that stores the reference of the removed student at given index.
            for(int i=index;i<students.length-1;i++) {
                //The loop moves students objects after the index forward one position
                students[i]=students[i+1];
            }
            students[studentCount]=null;//Removes the duplicate second to last object in the array.
            studentCount--;//number of students-1 because it has been removed.
            return result;
        }
    }
    /**
     * Adds <code>Student</code> object's reference at the given index in the student line.
     * @param index student
     * <dt>precondition:
     * 		<dd>studentCount must be less than 20.
     * 		<dd>The index must be less or equal to studentCount+1, and should not be less than one.
     * @return Returns the reference of the Student object that is being added.
     * @throws InvalidArgumentException
     * 			Thrown to indicate that an array has been accessed with an invalid index.
     * @throws DeanException
     * 			Thrown to indicate that the student line has already reached its maximum capacity.
     */
    public Student addStudent(int index, Student student) throws InvalidArgumentException,DeanException {//untested
        if(index>studentCount+1||index<1)
            throw new InvalidArgumentException();
        else if(studentCount>=CAPACITY)
            throw new DeanException();
        else {
            for(int i=students.length-1;i>index;i--) {
                //The loop moves students objects after the index backward one position.
                students[i]=students[i-1];
            }
            students[index]=student;// Add the Student object reference into the array.
            studentCount++;//Number of students+1 because it has been added.
            return students[index];
        }

    }
    /**
     * Swaps the positions of two students in the student line.
     * @param index1, index2
     * <dt>precondition:
     * 		<dd>Both indexes must be less or equal to studentCount, and should not be less than one.
     * <dt>postcondition:
     * 		<dd>The positions of tow students in the array has been swapped.
     * @throws ArrayIndexOutOfBoundsException
     * 			Thrown to indicate that an array has been accessed with an illegal index.
     */
    public void swapStudents(int index1, int index2) throws ArrayIndexOutOfBoundsException{
        if(index1>studentCount||index1<1||index2>studentCount||index2<1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            //The codes swaps the positions of two Student Objects.
            Student temp=students[index1];
            students[index1]=students[index2];
            students[index2]=temp;
        }
    }

    /**
     * Returns a deep copy of the <code>StudentLine</code> object
     * @return Return a deep copy of the <code>StudentLine</code> object.
     * @throws CloneNotSupportedException
     * 		Thrown to indicate that the clone method in class Object has been called to clone an
     * 		object, but that the object's class does not implement the Cloneable interface.
     */
    @Override
    public Object clone() {
        try {
            StudentLine r=(StudentLine)super.clone();//The shallow copy of StudentLine object.
            Student[] temp=new Student[students.length];//The temporary variable for the students array.
            for(int i=1;i<=studentCount;i++) {
                //Copy each Student object in to the temporary array variable.
                temp[i]=(Student)students[i].clone();
            }
            r.students=temp;//Assign the duplicated array to the shallow copy.
            return r;
        }catch(CloneNotSupportedException ex) {return null;}
    }
    /**
     * This method determines if the two <code>StudentLine</code> objects are equal.
     * @param o
     * 		 The object to compare.
     * @return Returns true if the studentCounts, the content
     *		   of students arrays, and the order of the Students object in the student arrays of both <code>StudentLine</code> objects are equal;otherwise,
     *		   returns false.
     */
    @Override
    public boolean equals(Object o) {//untested
        if(!(o instanceof StudentLine))
            return false;
        if(studentCount!=((StudentLine)o).studentCount)
            return false;
        for(int i=1;i<studentCount+1;i++) {
            //The loop Compares the content in the array
            if(!students[i].equals(((StudentLine)o).students[i]))
                return false;
        }
        return true;
    }
    /**
     * This method returns a string representation of a <code>StudentLine</code> object.
     * @return Returns a string representation of a <code>StudentLine</code> object.
     **/
    @Override
    public String toString() {
        String result="";//The variable for storing the result.
        for(int i=1;i<=studentCount;i++)
            //The loop prints each student object in the students array.
            result+=i+": "+students[i].toString()+"\n";
        return result;
    }
    /**
     * This method returns a string array representation of a <code>StudentLine</code> object for Android UI.
     * @return Returns a string array representation of a <code>StudentLine</code> object for Android UI.
     **/
    public String[] toStringArray(){
        String[]result= new String[studentCount];
        for(int i=1;i<=studentCount;i++){
            result[i-1]=i+"."+students[i].toString();
        }
        return result;
    }
}




