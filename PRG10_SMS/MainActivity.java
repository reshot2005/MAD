package com.example.smsexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;

    private EditText phoneNumberEditText;
    private EditText messageEditText;
    private Button sendSmsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberEditText = findViewById(R.id.phone_number);
        messageEditText = findViewById(R.id.message);
        sendSmsButton = findViewById(R.id.send_sms);

        sendSmsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.SEND_SMS},
                            PERMISSION_REQUEST_CODE);

                } else {
                    sendSms();
                }
            }
        });
    }

    private void sendSms() {

        String phoneNumber =
                phoneNumberEditText.getText().toString();

        String message =
                messageEditText.getText().toString();

        try {

            SmsManager smsManager =
                    SmsManager.getDefault();

            smsManager.sendTextMessage(
                    phoneNumber,
                    null,
                    message,
                    null,
                    null);

            Toast.makeText(
                    this,
                    "SMS Sent",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "SMS Failed to Send",
                    Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {

            if (grantResults.length > 0
                    && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED) {

                sendSms();

            } else {

                Toast.makeText(
                        this,
                        "Permission Denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}