package com.example.espinas.emdb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.espinas.emdb.DB.PersonDataBase;
import com.example.espinas.emdb.Model.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    EditText edtId;
    EditText edtAge;
    EditText edtName;
    Button btnInsert;
    Button btnDelete;
    Button btnUpdate;
    Button btnSelect;
    Button btnSelectall;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person=new Person();
                person.setID(Integer.parseInt(edtId.getText().toString()));
                person.setAge(Integer.parseInt(edtAge.getText().toString()));
                person.setName(edtName.getText().toString());
                PersonDataBase personDatabase=new PersonDataBase(getApplicationContext());
                if(personDatabase.insert(person)){
                    Toast.makeText(getApplicationContext(),"insert  done",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"insert  eror",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person=new Person();
                PersonDataBase personDatabase=new PersonDataBase(getApplicationContext());
                person=personDatabase.select(Integer.parseInt(edtId.getText().toString()));
                edtAge.setText(person.getAge()+"");
                edtName.setText(person.getName());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person=new Person();
                person.setID(Integer.parseInt(edtId.getText().toString()));
                person.setAge(Integer.parseInt(edtAge.getText().toString()));
                person.setName(edtName.getText().toString());
                PersonDataBase personDatabase=new PersonDataBase(getApplicationContext());
                if(personDatabase.update(Integer.parseInt(edtId.getText().toString()),person)){
                    Toast.makeText(getApplicationContext(),"update  done",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"update  eror",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonDataBase personDatabase=new PersonDataBase(getApplicationContext());
                if(personDatabase.remove(Integer.parseInt(edtId.getText().toString()))){
                    Toast.makeText(getApplicationContext(),"delete  done",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"delete  eror",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSelectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonDataBase personDatabase=new PersonDataBase(getApplicationContext());
                ArrayList<String> arrayList=new ArrayList<>();
                arrayList=personDatabase.selectAll();
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(adapter);
            }
        });
    }

    private void init() {
        edtId=(EditText)findViewById(R.id.edtId);
        edtAge=(EditText)findViewById(R.id.edtAge);
        edtName=(EditText)findViewById(R.id.edtName);
        btnInsert=(Button)findViewById(R.id.btnInsert);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnSelect=(Button)findViewById(R.id.btnSelect);
        btnSelectall=(Button)findViewById(R.id.btnSelectall);
        listView=(ListView)findViewById(R.id.listView);
    }
}
