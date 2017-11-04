package com.example.albert.contact_list1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    SQLiteHelper studSQLHelp;

    ListView lsvcontact;
    EditText namesearch;

    ArrayList<contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studSQLHelp = new SQLiteHelper(this);

        lsvcontact = (android.widget.ListView) findViewById(R.id.label);
        namesearch = (EditText)findViewById(R.id.editText);
        studSQLHelp.getReadableDatabase();
        //show all contacts
        show_all();



//onclick method when a item in ListView is pressed
        lsvcontact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                contact data = (contact) lsvcontact.getAdapter().getItem(position);


                Intent i;
                i = new Intent(MainActivity.this, viewcontact.class);
                i.putExtra("sampleObject",  data);
                startActivity(i);


            }
        });

    }

//method to show all contacts
    public void show_all(){
        contacts = studSQLHelp.get_all(studSQLHelp.getReadableDatabase());

        //creating adapter
        ArrayAdapter<contact> adapter = new ArrayAdapter<contact>(this,
                R.layout.activity_listview, contacts);

        //passing adapter to the listView
        ListView listView = (ListView) findViewById(R.id.label);
        listView.setAdapter(adapter);
    }

    public void createNew(View view){
//creating Intent
        Intent intent = new Intent(this, createnew.class);
        //starting activity createnew
        startActivity(intent);
    }



//callback function for search button
    public void onSearch(View view){
        String name;
        name = namesearch.getText().toString();

        contacts = studSQLHelp.search(name,studSQLHelp.getReadableDatabase());

        //creating adapter
        ArrayAdapter<contact> adapter = new ArrayAdapter<contact>(this,
                R.layout.activity_listview, contacts);


        //passing adapter to the listView
        ListView listView = (ListView) findViewById(R.id.label);
        listView.setAdapter(adapter);
    }



//callback function for deleted button
    public void ondeleted(View view){

        //creating Intent
        Intent intent = new Intent(this, deleted.class);
        //starting activity createnew
        startActivity(intent);

    }


//callback function for log button
    public void onlog(View view){
        //creating Intent
        Intent intent = new Intent(this, log.class);
        //starting activity createnew
        startActivity(intent);
    }
}


