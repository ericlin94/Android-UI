package com.ericlin94.oilchangemanager;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    AlertDialog dialog=null;
    private static CarList joeList=new CarList();
    private static CarList donnyList=new CarList();
    private static CarList finishedList=new CarList();
    private static CarList listInUse;
    private static Car cutPaste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void editJobListButton(final View view){
        final PopupMenu editPop=new PopupMenu(MainActivity.this,(Button)findViewById(R.id.editJobList));
        editPop.getMenuInflater().inflate(R.menu.editpopup_menu,editPop.getMenu());
        editPop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                if(item.getTitle().toString().equals("Joe List")) {
                    listInUse=joeList;
                    final String inUse="Joe";
                    Toast.makeText(MainActivity.this, "Joe List in use.", Toast.LENGTH_SHORT).show();
                    //same
                    final PopupMenu editContPop=new PopupMenu(MainActivity.this,(Button)findViewById(R.id.editJobList));
                    editContPop.getMenuInflater().inflate(R.menu.editcontpopup_menu,editContPop.getMenu());
                    editContPop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                        @Override
                        public boolean onMenuItemClick(MenuItem item){
                            try {
                                switch (item.getTitle().toString()) {
                                    case "Add to End":
                                        addToEnd(view, inUse);
                                        break;
                                    case "Cursor Forward":
                                        cursorForward(inUse);
                                        break;
                                    case "Cursor to Head":
                                        cursorToHead(inUse);
                                        break;
                                    case "Cursor to Tail":
                                        cursorToTail(inUse);
                                        break;
                                    case "Cursor Backward":
                                        cursorBackward(inUse);
                                        break;
                                    case  "Insert before Cursor":
                                        insertBeforeCursor(view,inUse);
                                        break;
                                    case  "Cut at Cursor":
                                        cutCarAtCursor(inUse);
                                        break;
                                    case  "Paste before Cursor":
                                        pasteCarBeforeCursor(inUse);
                                        break;
                                    case  "Remove cursor":
                                        removeCarAtCursor(inUse);
                                        break;
                                    default:
                                        Toast.makeText(MainActivity.this, "Invalid Choice.", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }catch (EndOfListException ex){
                                Toast.makeText(MainActivity.this, "End of List.", Toast.LENGTH_SHORT).show();
                            }catch (IllegalArgumentException ex){
                                Toast.makeText(MainActivity.this, "Invalid Argument.", Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                    });
                    editContPop.show();
                }
                else {
                    listInUse = donnyList;
                    final String inUse="Donny";
                    Toast.makeText(MainActivity.this, "Donny List in use.", Toast.LENGTH_SHORT).show();
                    //same
                    final PopupMenu editContPop=new PopupMenu(MainActivity.this,(Button)findViewById(R.id.editJobList));
                    editContPop.getMenuInflater().inflate(R.menu.editcontpopup_menu,editContPop.getMenu());
                    editContPop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                        @Override
                        public boolean onMenuItemClick(MenuItem item){
                            try {
                                switch (item.getTitle().toString()) {
                                    case "Add to End":
                                        addToEnd(view, inUse);
                                        break;
                                    case "Cursor Forward":
                                        cursorForward(inUse);
                                        break;
                                    case "Cursor to Head":
                                        cursorToHead(inUse);
                                        break;
                                    case "Cursor to Tail":
                                        cursorToTail(inUse);
                                        break;
                                    case "Cursor Backward":
                                        cursorBackward(inUse);
                                        break;
                                    case  "Insert before Cursor":
                                        insertBeforeCursor(view,inUse);
                                        break;
                                    case  "Cut at Cursor":
                                        cutCarAtCursor(inUse);
                                        break;
                                    case  "Paste before Cursor":
                                        pasteCarBeforeCursor(inUse);
                                        break;
                                    case  "Remove cursor":
                                        removeCarAtCursor(inUse);
                                        break;
                                    default:
                                        Toast.makeText(MainActivity.this, "Invalid Choice.", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }catch (EndOfListException ex){
                                Toast.makeText(MainActivity.this, "End of List.", Toast.LENGTH_SHORT).show();
                            }catch (IllegalArgumentException ex){
                                Toast.makeText(MainActivity.this, "Invalid Argument.", Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                    });
                    editContPop.show();
                }
                return true;
            }
        });
        editPop.show();
    }

    public void addToEnd(View view,String s){
        final String inUse=s;
        final AlertDialog.Builder addDialog=new AlertDialog.Builder(MainActivity.this);
        View myview= getLayoutInflater().inflate(R.layout.dialog_input,null);
        final EditText mMake=(EditText)myview.findViewById(R.id.edMake);
        final EditText mName=(EditText)myview.findViewById(R.id.edName);
        Button okButton=(Button)myview.findViewById(R.id.ok);
        Button cancelButton=(Button)myview.findViewById(R.id.cancel);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean peacefulEnd=false;
                if(!mMake.getText().toString().isEmpty()&&!mName.getText().toString().isEmpty()){
                    String choice=mMake.getText().toString();
                    String owner=mName.getText().toString();
                    try {


                        switch (choice.toLowerCase()) {
                            case "ford":
                                listInUse.appendToTail(new Car(Make.FORD, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Ford has been scheduled for an oil change with " + inUse + " and has been added to his list.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "gmc":
                                listInUse.appendToTail(new Car(Make.GMC, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " GMC has been scheduled for an oil change with " + inUse + " and has been added to his list.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "chevy":
                                listInUse.appendToTail(new Car(Make.CHEVY, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Chevy has been scheduled for an oil change with " + inUse + " and has been added to his list.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "jeep":
                                listInUse.appendToTail(new Car(Make.JEEP, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Jeep has been scheduled for an oil change with " + inUse + " and has been added to his list.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "dodge":
                                listInUse.appendToTail(new Car(Make.DODGE, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Dodge has been scheduled for an oil change with " + inUse + " and has been added to his list.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "chrysler":
                                listInUse.appendToTail(new Car(Make.CHRYSLER, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Chrysler has been scheduled for an oil change with " + inUse + " and has been added to his list.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "lincoln":
                                listInUse.appendToTail(new Car(Make.LINCOLN, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Lincoln has been scheduled for an oil change with " + inUse + " and has been added to his list.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "Invalid model choice.", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }catch (IllegalArgumentException ex){
                        Toast.makeText(MainActivity.this, "Invalid Argument.", Toast.LENGTH_SHORT).show();
                    }

                    if(MainActivity.this.dialog!=null&&peacefulEnd)
                        dialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please fill empty field(s).", Toast.LENGTH_SHORT).show();
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
        addDialog.setView(myview);
        dialog=addDialog.create();
        dialog.show();
    }

    public void cursorForward(String inUse) throws EndOfListException {
        listInUse.cursorForward();
        Toast.makeText(MainActivity.this, "Cursor Moved Forward in "+inUse+"\'s List.", Toast.LENGTH_SHORT).show();
        showList();
    }
    public void cursorToHead(String inUse) {
        listInUse.resetCursorToHead();
        Toast.makeText(MainActivity.this, "Cursor Moved To Head in "+inUse+"\'s List.", Toast.LENGTH_SHORT).show();
        showList();
    }
    public void cursorToTail(String inUse) {
        listInUse.resetCursorToTail();
        Toast.makeText(MainActivity.this, "Cursor Moved To Tail in "+inUse+"\'s List.", Toast.LENGTH_SHORT).show();
        showList();
    }
    public void cursorBackward(String inUse) throws EndOfListException {
        listInUse.cursorBackward();
        Toast.makeText(MainActivity.this, "Cursor Moved Backward in "+inUse+"\'s List.", Toast.LENGTH_SHORT).show();
        showList();
    }

    public void insertBeforeCursor(View view,String s){
        final String inUse=s;
        final AlertDialog.Builder addDialog=new AlertDialog.Builder(MainActivity.this);
        View myview= getLayoutInflater().inflate(R.layout.dialog_input,null);
        final EditText mMake=(EditText)myview.findViewById(R.id.edMake);
        final EditText mName=(EditText)myview.findViewById(R.id.edName);
        Button okButton=(Button)myview.findViewById(R.id.ok);
        Button cancelButton=(Button)myview.findViewById(R.id.cancel);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean peacefulEnd=false;
                if(!mMake.getText().toString().isEmpty()&&!mName.getText().toString().isEmpty()){
                    String choice=mMake.getText().toString();
                    String owner=mName.getText().toString();
                    try {


                        switch (choice.toLowerCase()) {
                            case "ford":
                                listInUse.insertBeforeCursor(new Car(Make.FORD, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Ford has been scheduled for an oil change with " + inUse + " and has been added before the cursor.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "gmc":
                                listInUse.insertBeforeCursor(new Car(Make.GMC, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " GMC has been scheduled for an oil change with " + inUse + " and has been added before the cursor.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "chevy":
                                listInUse.insertBeforeCursor(new Car(Make.CHEVY, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Chevy has been scheduled for an oil change with " + inUse + " and has been added before the cursor.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "jeep":
                                listInUse.insertBeforeCursor(new Car(Make.JEEP, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Jeep has been scheduled for an oil change with " + inUse + " and has been added before the cursor.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "dodge":
                                listInUse.insertBeforeCursor(new Car(Make.DODGE, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Dodge has been scheduled for an oil change with " + inUse + " and has been added before the cursor.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "chrysler":
                                listInUse.insertBeforeCursor(new Car(Make.CHRYSLER, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Chrysler has been scheduled for an oil change with " + inUse + " and has been added before the cursor.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            case "lincoln":
                                listInUse.insertBeforeCursor(new Car(Make.LINCOLN, owner));
                                Toast.makeText(MainActivity.this, owner + "\'s " + " Lincoln has been scheduled for an oil change with " + inUse + " and has been added before the cursor.", Toast.LENGTH_SHORT).show();
                                showList();
                                peacefulEnd = true;
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "Invalid model choice.", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }catch (IllegalArgumentException ex){
                        Toast.makeText(MainActivity.this, "Invalid Argument.", Toast.LENGTH_SHORT).show();
                    }

                    if(MainActivity.this.dialog!=null&&peacefulEnd)
                        dialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please fill empty field(s).", Toast.LENGTH_SHORT).show();
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
        addDialog.setView(myview);
        dialog=addDialog.create();
        dialog.show();
    }

    public void cutCarAtCursor(String inUse) throws EndOfListException {
        cutPaste=listInUse.removeCursor();
        Toast.makeText(MainActivity.this, "Cursor cut in "+inUse+"\' List.", Toast.LENGTH_SHORT).show();
        showList();
    }
    public void pasteCarBeforeCursor(String inUse) throws IllegalArgumentException {
        if(cutPaste==null) {
            Toast.makeText(MainActivity.this, "There is no car to paste.", Toast.LENGTH_SHORT).show();
        }
        else {
            listInUse.insertBeforeCursor(cutPaste);
            Toast.makeText(MainActivity.this, "Cursor paste in "+inUse+"\' List.", Toast.LENGTH_SHORT).show();
            cutPaste=null;
            showList();
        }
    }
    public void removeCarAtCursor(String inUse) throws EndOfListException{
        listInUse.removeCursor();
        Toast.makeText(MainActivity.this, "Cursor removed in "+inUse+"\' List.", Toast.LENGTH_SHORT).show();
        showList();
    }

    public void mergeList(final View view) {
        final PopupMenu editPop=new PopupMenu(MainActivity.this,(Button)findViewById(R.id.mergeList));
        editPop.getMenuInflater().inflate(R.menu.editpopup_menu,editPop.getMenu());
        editPop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                try {
                    if (item.getTitle().toString().equals("Joe List")) {
                        joeList.mergeList(donnyList);
                        Toast.makeText(MainActivity.this, "Donny's list merged into Joe's.", Toast.LENGTH_SHORT).show();
                        showList();
                    } else {
                        donnyList.mergeList(joeList);
                        Toast.makeText(MainActivity.this, "Joe's list merged into Donny's.", Toast.LENGTH_SHORT).show();
                        showList();
                    }
                }catch (EndOfListException ex){
                    Toast.makeText(MainActivity.this, "End of List.", Toast.LENGTH_SHORT).show();
                }catch (IllegalArgumentException ex){
                    Toast.makeText(MainActivity.this, "Invalid Argument.", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        editPop.show();
    }
    public void pasteToFinished(final View view){
        if(cutPaste==null) {
            Toast.makeText(MainActivity.this, "There is no car to paste.", Toast.LENGTH_SHORT).show();
        }
        else {
            finishedList.appendToTail(cutPaste);
            Toast.makeText(MainActivity.this, "Car pasted in finished list.", Toast.LENGTH_SHORT).show();
            cutPaste=null;
            showList();
        }
    }
    public void sort(final View view) {
        final PopupMenu editPop=new PopupMenu(MainActivity.this,(Button)findViewById(R.id.sort));
        editPop.getMenuInflater().inflate(R.menu.editpopup_menu,editPop.getMenu());
        editPop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                    try {
                        if (item.getTitle().toString().equals("Joe List")) {
                            joeList.sortList();
                            Toast.makeText(MainActivity.this, "Joe's list sorted.", Toast.LENGTH_SHORT).show();
                            showList();
                        } else {
                            donnyList.sortList();
                            Toast.makeText(MainActivity.this, "Donny's list sorted.", Toast.LENGTH_SHORT).show();
                            showList();
                        }
                    }catch (EndOfListException ex){
                        Toast.makeText(MainActivity.this, "End of List.", Toast.LENGTH_SHORT).show();
                    }
                return true;
            }
        });
        editPop.show();
    }
    //showList
    public void showList(){
        String outPut="Joe's List:\n"+joeList+"Donny's List:\n"+donnyList+"Finished List:\n"+finishedList;
        ListView reality = (ListView) findViewById(R.id.printList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,outPut.split("\n"));
        reality.setAdapter(adapter);
    }
}
