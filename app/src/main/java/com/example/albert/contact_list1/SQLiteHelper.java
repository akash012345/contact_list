package com.example.albert.contact_list1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by akash on 06-07-2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Contacts.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CONTACT_TABLE_NAME = "Contact";
    private static final String DELETED_TABLE_NAME = "DeletedContacts";
    private static final String LOG_TABLE_NAME = "Log";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        //creating contact table
        String contactTableSQL = "CREATE TABLE " + CONTACT_TABLE_NAME + " (" + contact.contactAttr.cont_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        contactTableSQL += contact.contactAttr.cont_NAME + " TEXT, " + contact.contactAttr.cont_PHONE + " TEXT, ";
        contactTableSQL += contact.contactAttr.cont_EMAIL + " TEXT)";

// CREATE TABLE Contact (cont_ID INTERGER PRIMARY KEY AUTOINCREMENT, cont_NAME TEXT, cont_PHONE TEXT, cont_EMAIL TEXT)
        db.execSQL(contactTableSQL);

        //creating deletedContacts table
        String deleteTableSQL = "CREATE TABLE " + DELETED_TABLE_NAME + " (" + contact.contactAttr.cont_ID + " INTERGER, ";
        deleteTableSQL += contact.contactAttr.cont_NAME + " TEXT, " + contact.contactAttr.cont_PHONE + " TEXT, ";
        deleteTableSQL += contact.contactAttr.cont_EMAIL + " TEXT)";

// CREATE TABLE DeletedContacts (cont_ID INTERGER PRIMARY KEY AUTOINCREMENT, cont_NAME TEXT, cont_PHONE TEXT, cont_EMAIL TEXT)
        db.execSQL(deleteTableSQL);


        //creating Log table
        String logTableSQL = "CREATE TABLE " + LOG_TABLE_NAME + " (" + log_class.logAttr.cont_NAME +" TEXT, ";
        logTableSQL += log_class.logAttr.OPERATION +" TEXT, ";
        logTableSQL += log_class.logAttr.TIMESTAMP+" TEXT)";

// CREATE TABLE log (cont_Name TEXT, operation TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP);

        db.execSQL(logTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//to be completed

    }



//method to insery new contact to the contact table
    public long insert_contact(contact Contact, SQLiteDatabase db) {

        android.content.ContentValues contValues = new android.content.ContentValues();

        contValues.put(contact.contactAttr.cont_NAME, Contact.getcont_Name());
        contValues.put(contact.contactAttr.cont_PHONE, Contact.getcont_Phone());
        contValues.put(contact.contactAttr.cont_EMAIL, Contact.getcont_Email());

        //insert to log table
        log_class l= new log_class(Contact.getcont_Name(),"insert_contact");
        insert_log(l,db);

        //insert to contact table
        return db.insert(CONTACT_TABLE_NAME, null, contValues);


    }


//method to update contact to the contact table
    public boolean update_contact (contact c, SQLiteDatabase db){
        ContentValues contValues = new ContentValues();
        contValues.put(contact.contactAttr.cont_NAME, c.getcont_Name());
        contValues.put(contact.contactAttr.cont_PHONE, c.getcont_Phone());
        contValues.put(contact.contactAttr.cont_EMAIL, c.getcont_Email());

        //insert to log
        log_class l= new log_class(c.getcont_Name(),"update_contact");
        insert_log(l,db);

        //update to contact table
        return db.update(CONTACT_TABLE_NAME, contValues, contact.contactAttr.cont_ID +"="+ c.getcont_ID(), null)>0;
    }


    //method to delete contact from the contact table

    public void delete_contact(contact data,SQLiteDatabase db){


        //insert the contact to deletedcontacts table before deleting it from the contact table
        insert_deleted(data,db);

        //insert to log table
        log_class l= new log_class(data.getcont_Name(),"delete_contact");
        insert_log(l,db);

        //delete from contact table
        db.delete(CONTACT_TABLE_NAME, contact.contactAttr.cont_ID+"="+data.getcont_ID(),null);

    }


    //search for a contact using name
    public ArrayList<contact> search(String Name, SQLiteDatabase db) {


        String[] projection = {contact.contactAttr.cont_ID,
                contact.contactAttr.cont_NAME,
                contact.contactAttr.cont_PHONE,
                contact.contactAttr.cont_EMAIL
        };


// How you want the results sorted in the resulting Cursor
        String sortOrder = contact.contactAttr.cont_NAME + " DESC";

        Cursor cursor = db.query(
                CONTACT_TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                contact.contactAttr.cont_NAME+" LIKE '%"+Name+"%'",               // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );



        //define an arraylist for the contact
        ArrayList<contact> Contacts = new ArrayList<contact>();

        if (cursor == null)
            return Contacts; // can't do anything with a null cursor.

        while(cursor.moveToNext())
            Contacts.add(new contact(cursor.getInt(0), //add each element in the curser to contact object using its constructor
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)));


        cursor.close();

        return Contacts; //returning the arraylist
    }




    //function to find all contacts in the contact table
    public ArrayList<contact> get_all(SQLiteDatabase db) {

        String[] projection = {contact.contactAttr.cont_ID,
                contact.contactAttr.cont_NAME,
                contact.contactAttr.cont_PHONE,
                contact.contactAttr.cont_EMAIL
        };


// How you want the results sorted in the resulting Cursor
        String sortOrder = contact.contactAttr.cont_NAME + " DESC";

        Cursor cursor = db.query(
                CONTACT_TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );



        //arraylist for contact class objects
        ArrayList<contact> Contacts = new ArrayList<contact>();

        if (cursor == null)
            return Contacts; // can't do anything with a null cursor.

        while(cursor.moveToNext())
            Contacts.add(new contact(cursor.getInt(0),   //add each element in the curser to contact object using its constructor
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)));


        cursor.close();

        return Contacts;    //returning the arraylist
    }





//insert to table deleted_contacts
    public long insert_deleted(contact Contact, SQLiteDatabase db){
        android.content.ContentValues contValues = new android.content.ContentValues();

        contValues.put(contact.contactAttr.cont_NAME, Contact.getcont_Name());
        contValues.put(contact.contactAttr.cont_PHONE, Contact.getcont_Phone());
        contValues.put(contact.contactAttr.cont_EMAIL, Contact.getcont_Email());

        return db.insert(DELETED_TABLE_NAME, null, contValues);
    }


//get all rows in the table deleted_contacts
    public ArrayList<contact> all_deleted(SQLiteDatabase db) {

        contact Contact = new contact();

        String[] projection = {contact.contactAttr.cont_ID,
                contact.contactAttr.cont_NAME,
                contact.contactAttr.cont_PHONE,
                contact.contactAttr.cont_EMAIL
        };


// How you want the results sorted in the resulting Cursor
        String sortOrder = contact.contactAttr.cont_NAME + " DESC";

        Cursor cursor = db.query(
                DELETED_TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );



        //creation of arraylist for contact
        ArrayList<contact> Contacts = new ArrayList<contact>();

        if (cursor == null)
            return Contacts; // can't do anything with a null cursor.

        while(cursor.moveToNext())
            Contacts.add(new contact(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)));


        cursor.close();

        return Contacts;  //return the arraylist
    }



//insert to log table
    public long insert_log(log_class l,SQLiteDatabase db){

        android.content.ContentValues logValues = new android.content.ContentValues();

        logValues.put(log_class.logAttr.cont_NAME, l.getcont_Name());
        logValues.put(log_class.logAttr.OPERATION, l.getOperation());


        //finding current timestamp
        //Long tsLong = System.currentTimeMillis()/1000;
        //String ts = tsLong.toString();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String timestamp=date;

        logValues.put(log_class.logAttr.TIMESTAMP,timestamp);

        //insertion
        return db.insert(LOG_TABLE_NAME, null, logValues);

    }


//method to find all log rows
    public ArrayList<log_class> all_log(SQLiteDatabase db) {


        String[] projection = {log_class.logAttr.cont_NAME,
               log_class.logAttr.OPERATION,
                log_class.logAttr.TIMESTAMP
        };


// How you want the results sorted in the resulting Cursor
        String sortOrder = log_class.logAttr.TIMESTAMP + " DESC";

        Cursor cursor = db.query(
                LOG_TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );



        //arraylist for log_class
        ArrayList<log_class> logs = new ArrayList<log_class>();

        if (cursor == null)
            return logs; // can't do anything with a null cursor.

        while(cursor.moveToNext())
            logs.add(new log_class(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)));


        cursor.close();

        return logs;   //returning arraylist for log_class
    }



}
