package com.ericlin94.lunchlinesimulator;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * The <code>MainActivity</code> implements user interface and functions that construct the middle school
 * lunch line simulator game for Android app.
 *
 * @author Heng An Lin
 *    e-mail: hengan.lin@stonybrook.edu
 *    Stony Brook ID:111316287
 **/
public class MainActivity extends AppCompatActivity {
    AlertDialog dialog=null;
    StudentLine realityA=new StudentLine();
    StudentLine realityB=new StudentLine();
    StudentLine realityNow=realityA;

    /**
     * onCreate method creates the main user interface layout of the Lunch Line Simulator Android app.
     * @param savedInstanceState
     * The realityNow is set to realityA as default.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Reality Prompt
        final TextView realityNowPrompt=(TextView)findViewById(R.id.realityNowPrompt);
        realityNowPrompt.setText("Reality A:");
        realityNowPrompt.setTextColor(Color.BLUE);
        /**
         * Removes student from the beginning of lunch line.
         * <dt>postcondition:
         * 		<dd>The first student in the line has been removed.
         * @throws EmptyLineException
         * 		Thrown to indicate that an array has no objects to be remove.
         */
        Button serveStudentButton=(Button)findViewById(R.id.serveStudentButton);
        serveStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder serveInterface=new AlertDialog.Builder(MainActivity.this);
                try{
                    serveInterface.setMessage(realityNow.removeStudent(1).getName()+" has been served today's special: Bouncy \"Chicken?\" Nuggets. We hope he/she lives to see another day!").setPositiveButton("OK",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ListView reality = (ListView) findViewById(R.id.realityNow);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, realityNow.toStringArray());
                            reality.setAdapter(adapter);
                            dialog.dismiss();
                        }
                    }).setTitle("Message from Mystery Meat Martha:").create();
                    serveInterface.show();
                }catch(EmptyLineException ex){
                    serveInterface.setMessage("There is nobody to serve. Mystery Meat Martha is sad.").setPositiveButton("OK",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).setTitle("Message from Mystery Meat Martha:").create();
                    serveInterface.show();
                }
            }
        });
        /**
         * Switching reality between realityA and realityB
         * <dt>postcondition:
         * 		<dd>If the realityNow has reference of realityA, it has been assigned with realityB, and vice versa.
         */
        Button switchRealityButton=(Button)findViewById(R.id.switchRealityButton);
        switchRealityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(realityNow==realityA) {
                    realityNowPrompt.setText("Reality B:");
                    realityNowPrompt.setTextColor(Color.RED);
                    realityNow=realityB;
                    ListView reality = (ListView) findViewById(R.id.realityNow);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, realityNow.toStringArray());
                    reality.setAdapter(adapter);
                    AlertDialog.Builder switchInterface=new AlertDialog.Builder(MainActivity.this);
                    switchInterface.setMessage("You are in Reality B. I reject your reality and substitute my own!").setPositiveButton("OK",new DialogInterface.OnClickListener(){
                       @Override
                       public void onClick(DialogInterface dialog, int which){
                           dialog.dismiss();
                       }
                    }).setTitle("Switch Reality!!!").create();
                    switchInterface.show();
                }
                else {
                    realityNowPrompt.setText("Reality A:");
                    realityNowPrompt.setTextColor(Color.BLUE);
                    realityNow=realityA;
                    ListView reality = (ListView) findViewById(R.id.realityNow);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, realityNow.toStringArray());
                    reality.setAdapter(adapter);
                    AlertDialog.Builder switchInterface=new AlertDialog.Builder(MainActivity.this);
                    switchInterface.setMessage("You are in Reality A. I reject your reality and substitute my own!").setPositiveButton("OK",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            dialog.dismiss();
                        }
                    }).setTitle("Switch Reality!!!").create();
                    switchInterface.show();
                }
            }
        });

        /**
         * Checks if both realities are equal.
         * <dt>postcondition:
         * 		<dd>If realities are equal, the method will prints "The realities are equal."
         * 			;otherwise, it will prints "The realities are not equal."
         */
        final Button checkEqualityButton=(Button)findViewById(R.id.checkEqualityButton);
        checkEqualityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder checkInterface=new AlertDialog.Builder(MainActivity.this);
                if(realityA.equals(realityB)){
                    checkInterface.setMessage("The realities are equal.").setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog,int which){
                            dialog.dismiss();
                        }
                    }).setTitle("Check Result: ").create();
                    checkInterface.show();
                }
                else{
                    checkInterface.setMessage("The realities are not equal.").setPositiveButton("OK",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog,int which){
                            dialog.dismiss();
                        }
                    }).setTitle("Check Result: ").create();
                    checkInterface.show();
                }
            }
        });
        /**
         * Copy the reality in use right now to another reality.
         * <dt>postcondition:
         * 		<dd>If reality in use was realityA, it has been copied to realityB, and vice versa.
         */
        Button duplicateButton=(Button)findViewById(R.id.duplicateButton);
        duplicateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder duplicateInterface=new AlertDialog.Builder(MainActivity.this);
                if(realityNow==realityA){
                    realityB=(StudentLine)realityNow.clone();
                    duplicateInterface.setMessage("Reality A has been copied into Reality B").setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog,int which){
                            dialog.dismiss();
                        }
                    }).setTitle("Duplicate Wizard: ").create();
                    duplicateInterface.show();
                }
                else {
                    realityA=(StudentLine)realityNow.clone();
                    duplicateInterface.setMessage("Reality B has been copied into Reality A").setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog,int which){
                            dialog.dismiss();
                        }
                    }).setTitle("Duplicate Wizard: ").create();
                    duplicateInterface.show();
                }
            }
        });
    }
    /**
     * Creates the user instruction when help button is clicked.
     * @param view
     */
    public void showHelp(View view){
        AlertDialog.Builder myHelp=new AlertDialog.Builder(this);
        myHelp.setMessage(      "Add Student: Add a student to the line at the end\n" +
                                "Cut Student: Have a new student cut a friend\n" +
                                "Trade Place: Have two students trade places\n" +
                                "Bully: Have the bully remove a student\n" +
                                "Update Lunch Money: Update a student's money amount\n" +
                                "Serve Student: Serve a student\n"+
                                "Switch reality: Switch to the other reality\n" +
                                "Check Equality: Check if the realities are equal\n" +
                                "Duplicate: Duplicate this reality into the other reality\n").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setTitle("Help:").create();
        myHelp.show();
    }

    /**
     * Adds the students to the end of the lunch line.
     * @param view
     * 		The Scanner object that takes user's input.
     * <dt>precondition:
     * 		<dd>studentCount must be less than 20.
     * 		<dd>Money must larger than 0.
     * <dt>postcondition:
     * 		<dd><code>Student</code> object has been added to the lunch line.
     * @throws InvalidArgumentException
     * 		Thrown to indicate that an array has been accessed with an invalid index.
     * @throws DeanException
     * 		Thrown to indicate that the student line has already reached its maximum capacity.
     **/
    public void addStudentButton(View view){
        final AlertDialog.Builder addInterface=new AlertDialog.Builder(MainActivity.this);
        View myview= getLayoutInflater().inflate(R.layout.dialog_addstudent,null);
        final EditText mName=(EditText)myview.findViewById(R.id.edName);
        final EditText mMoneyAmount=(EditText)myview.findViewById(R.id.edMoneyAmount);
        Button okButton=(Button)myview.findViewById(R.id.ok);
        Button cancelButton=(Button)myview.findViewById(R.id.cancel);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean peacefulEnd=false;
                if(!mName.getText().toString().isEmpty()&&!mMoneyAmount.getText().toString().isEmpty()){

                        try {

                            if (Double.parseDouble(mMoneyAmount.getText().toString()) <= 0) {
                                Toast.makeText(MainActivity.this, "You can't have debt/no money in lunchline. The lunch line was not updated.", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                realityNow.addStudent(realityNow.numStudents() + 1, new Student(mName.getText().toString(), Double.parseDouble(mMoneyAmount.getText().toString())));
                                ListView reality = (ListView) findViewById(R.id.realityNow);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, realityNow.toStringArray());
                                reality.setAdapter(adapter);
                                Toast.makeText(MainActivity.this, mName.getText().toString() + " has been added to the line in position " + realityNow.numStudents() + ". " + mName.getText().toString() + " has " + Double.parseDouble(mMoneyAmount.getText().toString()), Toast.LENGTH_SHORT).show();
                                peacefulEnd=true;
                            }
                        } catch (DeanException ex) {
                            Toast.makeText(MainActivity.this, R.string.deanEx, Toast.LENGTH_SHORT).show();
                        } catch (InvalidArgumentException ex) {
                            Toast.makeText(MainActivity.this, R.string.invalidArgEx, Toast.LENGTH_SHORT).show();
                        }

                    if(MainActivity.this.dialog!=null&&peacefulEnd)
                        dialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.emptyFields, Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(MainActivity.this.dialog!=null)
                    dialog.dismiss();
            }
        });
        addInterface.setView(myview);
        dialog=addInterface.create();
        dialog.show();
    }
    /**
     * Adds student into the the lunch line at given index.
     * @param view
     * 			The Scanner object that takes user's input.
     * <dt>precondition:
     * 		<dd>studentCount must be less than 20.
     * 		<dd>Money must be larger than 0.
     * 		<dd>Index must be smaller than realityNow.numStudents()+1
     * <dt>postcondition:
     * 		<dd><code>Student</code> object has been added to the lunch line.
     * @throws InvalidArgumentException
     * 			Thrown to indicate that an array has been accessed with an invalid index.
     * @throws DeanException
     * 			Thrown to indicate that the student line has already reached its maximum capacity.
     */
    public void cutStudentButton(View view){//Don't forget to edit text
        final AlertDialog.Builder addInterface=new AlertDialog.Builder(MainActivity.this);
        View myview= getLayoutInflater().inflate(R.layout.dialog_cutstudent,null);
        final EditText mName=(EditText)myview.findViewById(R.id.edName);
        final EditText mMoneyAmount=(EditText)myview.findViewById(R.id.edMoneyAmount);
        final EditText mPosition=(EditText)myview.findViewById(R.id.edPosition);
        Button okButton=(Button)myview.findViewById(R.id.ok);
        Button cancelButton=(Button)myview.findViewById(R.id.cancel);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!mName.getText().toString().isEmpty()&&!mMoneyAmount.getText().toString().isEmpty()&&!mPosition.getText().toString().isEmpty()){
                        boolean peacefulEnd=false;
                        try {
                            if (Double.parseDouble(mMoneyAmount.getText().toString()) <= 0) {
                                Toast.makeText(MainActivity.this, "You can't have debt/no money in lunchline. The lunch line was not updated.", Toast.LENGTH_SHORT).show();

                            }
                            else if(Integer.parseInt(mPosition.getText().toString())>=realityNow.numStudents()+1){
                                Toast.makeText(MainActivity.this, "There is no student for you to cut!", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                realityNow.addStudent(Integer.parseInt(mPosition.getText().toString()), new Student(mName.getText().toString(), Double.parseDouble(mMoneyAmount.getText().toString())));
                                ListView reality = (ListView) findViewById(R.id.realityNow);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, realityNow.toStringArray());
                                reality.setAdapter(adapter);
                                Toast.makeText(MainActivity.this, mName.getText().toString() + " has cut " + realityNow.getStudent(Integer.parseInt(mPosition.getText().toString()) + 1).getName() + " and now in position " + Integer.parseInt(mPosition.getText().toString()) + ". Now " + mName.getText().toString() + " has $: " + Double.parseDouble(mMoneyAmount.getText().toString()), Toast.LENGTH_SHORT).show();
                                peacefulEnd=true;
                            }

                        } catch (DeanException ex) {
                            Toast.makeText(MainActivity.this, R.string.deanEx, Toast.LENGTH_SHORT).show();
                        } catch (InvalidArgumentException ex) {
                            Toast.makeText(MainActivity.this, R.string.invalidArgEx, Toast.LENGTH_SHORT).show();
                        }

                    if(MainActivity.this.dialog!=null&&peacefulEnd)
                        dialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.emptyFields, Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(MainActivity.this.dialog!=null)
                    dialog.dismiss();
            }
        });
        addInterface.setView(myview);
        dialog=addInterface.create();
        dialog.show();
    }
    /**
     * Removes student from the lunch line at given index
     * @param view
     * 		The Scanner object that takes user's input.
     * <dt>precondition:
     * 		<dd>The given index must not be bigger than the number of students in the line.
     * <dt>postcondition:
     * 		<dd>The student has been removed from the line.
     * @throws EmptyLineException
     * 		Thrown to indicate that an array has no objects to be remove.
     */
    public void bullyButton(View view){
        final AlertDialog.Builder addInterface=new AlertDialog.Builder(MainActivity.this);
        View myview= getLayoutInflater().inflate(R.layout.dialog_bully,null);
        final EditText mPosition=(EditText)myview.findViewById(R.id.edPosition);
        Button okButton=(Button)myview.findViewById(R.id.ok);
        Button cancelButton=(Button)myview.findViewById(R.id.cancel);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean peacefulEnd=false;
                if(!mPosition.getText().toString().isEmpty()){

                        try {
                            String name=realityNow.removeStudent(Integer.parseInt(mPosition.getText().toString())).getName();
                            Toast.makeText(MainActivity.this, "The bully has stolen " +name+" lunch money, and "+name+" has left, feeling hungry.", Toast.LENGTH_SHORT).show();
                            ListView reality = (ListView) findViewById(R.id.realityNow);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, realityNow.toStringArray());
                            reality.setAdapter(adapter);
                            peacefulEnd=true;

                        } catch (EmptyLineException ex) {
                            Toast.makeText(MainActivity.this, "There is no one to bully!", Toast.LENGTH_SHORT).show();
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            Toast.makeText(MainActivity.this, "Invalit Position.", Toast.LENGTH_SHORT).show();
                        }

                    if(MainActivity.this.dialog!=null&&peacefulEnd)
                        dialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.emptyFields, Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(MainActivity.this.dialog!=null)
                    dialog.dismiss();
            }
        });
        addInterface.setView(myview);
        dialog=addInterface.create();
        dialog.show();
    }
    /**
     * Updates the amount of money that students has at given index.
     * @param view
     * 		The Scanner object that takes user's input.
     * <dt>precondition:
     * 		<dd>The money that user inputs must be non-negative.
     * 		<dd>The index that user gives must be less than or equals the number of student and larger than or equals to 1.
     * <dt>postcondition:
     * 		<dd>The amount of money that student at given index possessed has been updated.
     * 		<dd>If the student being updated has no money, then he/she will be removed from the lunch line.
     * @throws ArrayIndexOutOfBoundsException
     * 		Thrown to indicate that an array has been accessed with an illegal index.
     * @throws EmptyLineException
     * 		Thrown to indicate that an array has no objects to be remove.
     */
    public void updateLunchMoneyButton(View view){
        final AlertDialog.Builder addInterface=new AlertDialog.Builder(MainActivity.this);
        View myview= getLayoutInflater().inflate(R.layout.dialog_updatelunchmoney,null);
        final EditText mMoneyAmount=(EditText)myview.findViewById(R.id.edMoneyAmount);
        final EditText mPosition=(EditText)myview.findViewById(R.id.edPosition);
        Button okButton=(Button)myview.findViewById(R.id.ok);
        Button cancelButton=(Button)myview.findViewById(R.id.cancel);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean peacefulEnd=false;
                if(!mMoneyAmount.getText().toString().isEmpty()&&!mPosition.getText().toString().isEmpty()){

                        try {
                            if (Double.parseDouble(mMoneyAmount.getText().toString()) < 0) {
                                Toast.makeText(MainActivity.this, "You can't have debt in lunchline. The lunch line was not updated.", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                double tempMoney = realityNow.getStudent(Integer.parseInt(mPosition.getText().toString())).getMoney();
                                realityNow.getStudent(Integer.parseInt(mPosition.getText().toString())).setMoney(Double.parseDouble(mMoneyAmount.getText().toString()));


                                if (tempMoney < Double.parseDouble(mMoneyAmount.getText().toString()))
                                    Toast.makeText(MainActivity.this, realityNow.getStudent(Integer.parseInt(mPosition.getText().toString())).getName() + " got some money on the floor. Now he/she has " + Double.parseDouble(mMoneyAmount.getText().toString()), Toast.LENGTH_SHORT).show();
                                else if (tempMoney > Double.parseDouble(mMoneyAmount.getText().toString()))
                                    Toast.makeText(MainActivity.this, realityNow.getStudent(Integer.parseInt(mPosition.getText().toString())).getName() + " dropped some money on the floor. Now he/she has " + Double.parseDouble(mMoneyAmount.getText().toString()), Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(MainActivity.this, realityNow.getStudent(Integer.parseInt(mPosition.getText().toString())).getName() + "\'s money has not changed.", Toast.LENGTH_SHORT).show();
                                if (Double.parseDouble(mMoneyAmount.getText().toString()) == 0)
                                    Toast.makeText(MainActivity.this, "Since " + realityNow.removeStudent(Integer.parseInt(mPosition.getText().toString())).getName() + "has no money right now, he/she has left, feeling hungry.", Toast.LENGTH_SHORT).show();
                                ListView reality = (ListView) findViewById(R.id.realityNow);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, realityNow.toStringArray());
                                reality.setAdapter(adapter);
                                peacefulEnd=true;
                            }

                        } catch (EmptyLineException ex) {
                            Toast.makeText(MainActivity.this, "There is no one in the line!", Toast.LENGTH_SHORT).show();
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            Toast.makeText(MainActivity.this, "Invalit Position.", Toast.LENGTH_SHORT).show();
                        }

                    if(MainActivity.this.dialog!=null&&peacefulEnd)
                        dialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.emptyFields, Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(MainActivity.this.dialog!=null)
                    dialog.dismiss();
            }
        });
        addInterface.setView(myview);
        dialog=addInterface.create();
        dialog.show();
    }
    /**
     * Trade the places of students at given indexes
     * @param view
     * 		The Scanner objects that takes user's inputs.
     * <dt>precondition:
     * 		<dd>Both indexes that user inputs should not be bigger than the number of student, and should not be less than 1.
     * <dt>postcondition:
     * 		<dd>The students has been switched places
     * @throws ArrayIndexOutOfBoundsException
     * 			Thrown to indicate that an array has been accessed with an illegal index.
     */
    public void tradePlaceButton(View view){
        final AlertDialog.Builder addInterface=new AlertDialog.Builder(MainActivity.this);
        View myview= getLayoutInflater().inflate(R.layout.dialog_tradeplace,null);
        final EditText mIndex1=(EditText)myview.findViewById(R.id.edIndex1);
        final EditText mIndex2=(EditText)myview.findViewById(R.id.edIndex2);
        Button okButton=(Button)myview.findViewById(R.id.ok);
        Button cancelButton=(Button)myview.findViewById(R.id.cancel);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean peacefulEnd=false;
                if(!mIndex1.getText().toString().isEmpty()&&!mIndex2.getText().toString().isEmpty()){

                    try {
                        realityNow.swapStudents(Integer.parseInt(mIndex1.getText().toString()),Integer.parseInt(mIndex2.getText().toString()));
                        String name1=realityNow.getStudent(Integer.parseInt(mIndex1.getText().toString())).getName();
                        String name2=realityNow.getStudent(Integer.parseInt(mIndex2.getText().toString())).getName();
                        Toast.makeText(MainActivity.this,name2+" has traded place with "+name1+"." , Toast.LENGTH_SHORT).show();
                        ListView reality = (ListView) findViewById(R.id.realityNow);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, realityNow.toStringArray());
                        reality.setAdapter(adapter);
                        peacefulEnd=true;
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        Toast.makeText(MainActivity.this, "Invalid Position Choice(s)!", Toast.LENGTH_SHORT).show();
                    }
                    if(MainActivity.this.dialog!=null&&peacefulEnd)
                        dialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.emptyFields, Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(MainActivity.this.dialog!=null)
                    dialog.dismiss();
            }
        });
        addInterface.setView(myview);
        dialog=addInterface.create();
        dialog.show();
    }
}
