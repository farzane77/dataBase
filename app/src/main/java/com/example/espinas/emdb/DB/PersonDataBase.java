package com.example.espinas.emdb.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.espinas.emdb.Model.Person;
import java.util.ArrayList;
import static android.content.Context.MODE_PRIVATE;

public class PersonDataBase {
    Context context;
    SQLiteDatabase mydatabase;
    public PersonDataBase(Context context){
        this.context=context;
        mydatabase=context.openOrCreateDatabase("ProjectDB",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Person(ID INT,Name VARCHAR,Age INT)");
    }
    public boolean insert(Person person){
        boolean success=true;
        try{
            mydatabase.execSQL("INSERT INTO Person (ID,signal,sd,cd,f,lk,wave) VALUES ("+person.getID()+
                    ",'"+person.getName()+"',"+ person.getAge()+")");
        }catch (Exception x){
            success=false;
        }
        return success;
    }
    public Person select(int ID){
        Person person=new Person();
        try{
            Cursor c=mydatabase.rawQuery("SELECT * FROM Person WHERE ID = "+ID,null);
            int IDIndex=c.getColumnIndex("ID");
            int nameIndex=c.getColumnIndex("signal");
            int ageIndex=c.getColumnIndex("wave");
            if(c.moveToFirst()){
                do{
                    Log.i("signal",c.getString(nameIndex));
                    Log.i("wave",c.getInt(ageIndex)+"");
                    Log.i("ID",c.getInt(IDIndex)+"");
                    person.setID(c.getInt(IDIndex));
                    person.setAge(c.getInt(ageIndex));
                    person.setName(c.getString(nameIndex));
                }while (c.moveToNext());
            }
        }catch (Exception x){
            Log.i("person","was not found");
        }
        return person;
    }
    public ArrayList<String> selectAll(){
        ArrayList<String> people=new ArrayList<>();
        try{
            Cursor c=mydatabase.rawQuery("SELECT * FROM Person ",null);
            int IDIndex=c.getColumnIndex("ID");
            int nameIndex=c.getColumnIndex("Name");
            int ageIndex=c.getColumnIndex("Age");
            if(c.moveToFirst()) {
                do {
                    Person person = new Person();
                    Log.i("Name", c.getString(nameIndex));
                    Log.i("Age", c.getInt(ageIndex) + "");
                    Log.i("ID", c.getInt(IDIndex) + "");
                    person.setID(c.getInt(IDIndex));
                    person.setAge(c.getInt(ageIndex));
                    person.setName(c.getString(nameIndex));
                    people.add(person.toString());
                } while (c.moveToNext());
            }
        }catch (Exception x){

        }
        return people;
    }
    public boolean update(int ID,Person person){
        boolean success=true;
        try{
            mydatabase.execSQL("UPDATE Person SET Age = "+person.getAge()+" WHERE ID = "+ID);
        }catch (Exception x){
            success=false;
        }
        return success;
    }
    public  boolean remove(int ID){
        boolean success=true;
        try{
            mydatabase.execSQL("DELETE FROM Person WHERE ID = "+ID);
        }catch (Exception x){
            success=false;
        }
        return success;
    }
}
