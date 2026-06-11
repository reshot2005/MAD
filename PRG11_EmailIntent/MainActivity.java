package com.example.emailexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailAddressEditText;
    private EditText emailSubjectEditText;
    private EditText emailMessageEditText;
    private Button sendEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailAddressEditText =
                findViewById(R.id.email_address);

        emailSubjectEditText =
                findViewById(R.id.email_subject);

        emailMessageEditText =
                findViewById(R.id.email_message);

        sendEmailButton =
                findViewById(R.id.send_email);

        sendEmailButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendEmail();
                    }
                });
    }

    private void sendEmail() {

        String emailAddress =
                emailAddressEditText.getText().toString();

        String subject =
                emailSubjectEditText.getText().toString();

        String message =
                emailMessageEditText.getText().toString();

        Intent emailIntent =
                new Intent(Intent.ACTION_SEND);

        emailIntent.setType("message/rfc822");

        emailIntent.putExtra(
                Intent.EXTRA_EMAIL,
                new String[]{emailAddress});

        emailIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                subject);

        emailIntent.putExtra(
                Intent.EXTRA_TEXT,
                message);

        if (emailIntent.resolveActivity(
                getPackageManager()) != null) {

            startActivity(emailIntent);

        } else {

            Toast.makeText(
                    this,
                    "Email Client Not Installed",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}