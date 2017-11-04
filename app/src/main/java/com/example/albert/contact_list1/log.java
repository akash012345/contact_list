package com.example.albert.contact_list1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

//activity for showing all log
public class log extends AppCompatActivity {


    //creating arraylist for log_class
    ArrayList<log_class> logs;

    SQLiteHelper studSQLHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        studSQLHelp = new SQLiteHelper(this);

        //creating arraylist for all log rows
        logs = studSQLHelp.all_log(studSQLHelp.getReadableDatabase());


        //creating adapter for all logs
        ArrayAdapter<log_class> adapter = new ArrayAdapter<log_class>(this,
                R.layout.activity_listview, logs);

        //passing the adapter to the listView
        ListView listView = (ListView) findViewById(R.id.label);
        listView.setAdapter(adapter);


    }
}
