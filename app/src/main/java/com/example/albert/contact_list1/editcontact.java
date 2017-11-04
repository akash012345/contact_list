package com.example.albert.contact_list1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//activity to edit contact
public class editcontact extends AppCompatActivity {



    SQLiteHelper studSQLHelp;

    EditText name;
    EditText phone_no;
    EditText email;

    contact data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcontact);

        Intent i = getIntent();
        data = (contact) i.getSerializableExtra("sampleObject");//extracting the object that is passed to this activity

        studSQLHelp = new SQLiteHelper(this);

        name = (EditText)findViewById(R.id.editname);
        name.setText(data.getcont_Name());  //setting the text inside editText as the name earlier
        phone_no = (EditText)findViewById(R.id.editphone);
        phone_no.setText(data.getcont_Phone());
        email = (EditText)findViewById(R.id.editemail);
        email.setText(data.getcont_Email());

    }


//onclick method for save button
    public void onsave(View view){
        String Name,Phone_no,Email;
        Name=name.getText().toString();
        Phone_no=phone_no.getText().toString();
        Email=email.getText().toString();


//saving the data from editText to an object of the contact
        contact c = new contact(data.getcont_ID(),Name,Phone_no,Email);
        studSQLHelp.update_contact(c,studSQLHelp.getReadableDatabase());

        Toast myToast = Toast.makeText(getApplicationContext(), "updation successfull", Toast.LENGTH_LONG);
        myToast.show();


        //creating Intent
        Intent intent = new Intent(this, MainActivity.class);
        //starting activity createnew
        startActivity(intent);
    }

//onclick method for cancel button
    public void oncancel(View view){
        //creating Intent
        Intent intent = new Intent(this, MainActivity.class);
        //starting activity createnew
        startActivity(intent);
    }
}
