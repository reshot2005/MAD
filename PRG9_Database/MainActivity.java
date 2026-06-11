package com.example.contactsapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPhone;

    private Button btnAdd;
    private Button btnUpdate;
    private Button btnDelete;

    private ListView listView;

    private List<Contact> contactList =
            new ArrayList<>();

    private ArrayAdapter<Contact> adapter;

    private DBHelper dbHelper;

    private Contact selectedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName =
                findViewById(R.id.editTextName);

        editTextPhone =
                findViewById(R.id.editTextPhone);

        btnAdd =
                findViewById(R.id.btnAdd);

        btnUpdate =
                findViewById(R.id.btnUpdate);

        btnDelete =
                findViewById(R.id.btnDelete);

        listView =
                findViewById(R.id.listView);

        dbHelper = new DBHelper(this);

        loadContacts();

        btnAdd.setOnClickListener(v -> {

            String name =
                    editTextName.getText()
                            .toString();

            String phone =
                    editTextPhone.getText()
                            .toString();

            dbHelper.addContact(
                    new Contact(
                            0,
                            name,
                            phone));

            loadContacts();
            clearInputs();
        });

        btnUpdate.setOnClickListener(v -> {

            if (selectedContact != null) {

                selectedContact.setName(
                        editTextName.getText()
                                .toString());

                selectedContact.setPhoneNumber(
                        editTextPhone.getText()
                                .toString());

                dbHelper.updateContact(
                        selectedContact);

                loadContacts();
                clearInputs();
            }
        });

        btnDelete.setOnClickListener(v -> {

            if (selectedContact != null) {

                dbHelper.deleteContact(
                        selectedContact);

                loadContacts();
                clearInputs();
            }
        });

        listView.setOnItemClickListener(
                (parent, view, position, id) -> {

                    selectedContact =
                            contactList.get(position);

                    editTextName.setText(
                            selectedContact.getName());

                    editTextPhone.setText(
                            selectedContact.getPhoneNumber());
                });
    }

    private void loadContacts() {

        contactList.clear();

        contactList.addAll(
                dbHelper.getAllContacts());

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_single_choice,
                contactList);

        listView.setAdapter(adapter);
    }

    private void clearInputs() {

        editTextName.setText("");
        editTextPhone.setText("");

        selectedContact = null;
    }
}