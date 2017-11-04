package com.example.albert.contact_list1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

//activity that shows all deleted contacts
public class deleted extends AppCompatActivity {


    ArrayList<contact> contacts;

    SQLiteHelper studSQLHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleted);

        studSQLHelp = new SQLiteHelper(this);

        //show all deleted contacts
        contacts = studSQLHelp.all_deleted(studSQLHelp.getReadableDatabase());

        //create array adapter
        ArrayAdapter<contact> adapter = new ArrayAdapter<contact>(this,
                R.layout.activity_listview, contacts);

        //pass the adapter to the listView
        ListView listView = (ListView) findViewById(R.id.label);
        listView.setAdapter(adapter);
    }
}
