package com.example.albert.contact_list1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//activity to view the contact
public class viewcontact extends AppCompatActivity {


    contact data;
    SQLiteHelper studSQLHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcontact);

        Intent i = getIntent();
        data = (contact) i.getSerializableExtra("sampleObject");// extracting the data from the object pass to this activity


        TextView name_1= (TextView) findViewById(R.id.text_name);
        TextView phone_1= (TextView) findViewById(R.id.text_phone);
        TextView email_2= (TextView) findViewById(R.id.text_email);


        //showing data from the object on screen
        name_1.setText(data.getcont_Name());
        phone_1.setText(data.getcont_Phone());
        email_2.setText(data.getcont_Email());


    }


//callback method for edit button
    public void onedit(View view){

        Intent j;
        j = new Intent(viewcontact.this, editcontact.class);
        j.putExtra("sampleObject",  data);//sending the object that contains the contact to the editcontact activity
        startActivity(j);

    }


//callback method for edit button
    public void ondelete(View view){
        studSQLHelp = new SQLiteHelper(this);

        studSQLHelp.delete_contact(data,studSQLHelp.getReadableDatabase());

        Toast myToast = Toast.makeText(getApplicationContext(), "contact deleted", Toast.LENGTH_LONG);
        myToast.show();
        //creating Intent
        Intent intent = new Intent(this, MainActivity.class);
        //starting activity createnew
        startActivity(intent);

    }


}
