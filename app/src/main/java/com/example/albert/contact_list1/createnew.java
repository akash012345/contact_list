package com.example.albert.contact_list1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


//activity to create new contact
public class createnew extends AppCompatActivity {


    SQLiteHelper studSQLHelp;

    EditText name;
    EditText phone_no;
    EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnew);

        studSQLHelp = new SQLiteHelper(this);

        name = (EditText)findViewById(R.id.addname);
        phone_no = (EditText)findViewById(R.id.addphone);
        email = (EditText)findViewById(R.id.addemail);




    }

//onclic function for save button
    public void onsave(View view){
        String Name,Phone_no,Email;
        Name=name.getText().toString();
        Phone_no=phone_no.getText().toString();
        Email=email.getText().toString();

        //saving data from editText to an object of contact
        contact c = new contact(0,Name,Phone_no,Email);

        //inserting the contact to database
        studSQLHelp.insert_contact(c,studSQLHelp.getReadableDatabase());

        Toast myToast = Toast.makeText(getApplicationContext(), "successfully created", Toast.LENGTH_LONG);
        myToast.show();


        //creating Intent
        Intent intent = new Intent(this, MainActivity.class);
        //starting activity
        startActivity(intent);
    }


//onclick method for cancel button
    public void oncancel(View view){
        //creating Intent
        Intent intent = new Intent(this, MainActivity.class);
        //starting activity
        startActivity(intent);
    }
}

