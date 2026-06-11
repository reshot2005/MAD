package com.example.newactivitydemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOpenExplicit =
                findViewById(R.id.button_open_explicit);

        buttonOpenExplicit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent explicitIntent =
                                new Intent(
                                        MainActivity.this,
                                        SecondActivity.class);

                        startActivity(explicitIntent);
                    }
                });

        Button buttonOpenImplicit =
                findViewById(R.id.button_open_implicit);

        buttonOpenImplicit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent implicitIntent =
                                new Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://www.google.com"));

                        if (implicitIntent.resolveActivity(
                                getPackageManager()) != null) {

                            startActivity(implicitIntent);
                        }
                    }
                });

        Button buttonOpenContacts =
                findViewById(R.id.button_open_contacts);

        buttonOpenContacts.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent contactsIntent =
                                new Intent(Intent.ACTION_VIEW);

                        contactsIntent.setData(
                                ContactsContract.Contacts.CONTENT_URI);

                        if (contactsIntent.resolveActivity(
                                getPackageManager()) != null) {

                            startActivity(contactsIntent);
                        }
                    }
                });
    }
}