package com.example.preferencesdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner languageSpinner;
    private Switch notificationsSwitch;
    private TextView preferencesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languageSpinner = findViewById(R.id.languageSpinner);
        notificationsSwitch = findViewById(R.id.notificationsSwitch);
        preferencesTextView = findViewById(R.id.preferencesTextView);
        Button saveButton = findViewById(R.id.saveButton);

        // Load saved preferences when the app starts
        loadUserPreferences();

        // Save preferences when the button is clicked
        saveButton.setOnClickListener(v -> saveUserPreferences());
    }

    private void saveUserPreferences() {

        // Get selected language
        String language = languageSpinner.getSelectedItem().toString();

        // Get notification preference
        boolean notificationsEnabled = notificationsSwitch.isChecked();

        // Create or open Shared Preferences
        SharedPreferences sharedPreferences =
                getSharedPreferences("UserPreferences",
                        Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Put data into preferences
        editor.putString("language", language);
        editor.putBoolean("notificationsEnabled",
                notificationsEnabled);

        // Commit changes
        editor.apply();

        // Update the preferences TextView
        preferencesTextView.setText(
                String.format(
                        "Language: %s\nNotificationsEnabled: %b",
                        language,
                        notificationsEnabled
                )
        );
    }

    private void loadUserPreferences() {

        // Create or open Shared Preferences
        SharedPreferences sharedPreferences =
                getSharedPreferences("UserPreferences",
                        Context.MODE_PRIVATE);

        // Retrieve data from Shared Preferences
        String language =
                sharedPreferences.getString("language",
                        "English");

        boolean notificationsEnabled =
                sharedPreferences.getBoolean(
                        "notificationsEnabled",
                        false
                );

        // Set the selected language in the spinner
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.languages,
                        android.R.layout.simple_spinner_item
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        languageSpinner.setAdapter(adapter);

        int spinnerPosition =
                adapter.getPosition(language);

        languageSpinner.setSelection(spinnerPosition);

        // Set the notification switch state
        notificationsSwitch.setChecked(
                notificationsEnabled);

        // Update the preferences TextView
        preferencesTextView.setText(
                String.format(
                        "Language: %s\nNotifications Enabled: %b",
                        language,
                        notificationsEnabled
                )
        );
    }
}