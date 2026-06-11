package com.example.orientationchange;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView orientationMessage = findViewById(R.id.orientation_message);

        // ---get the current display info---
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display d = wm.getDefaultDisplay();

        Point size = new Point();
        d.getSize(size);

        int width = size.x;
        int height = size.y;

        if (width > height) {
            // ---landscape mode---
            orientationMessage.setText("Landscape mode");
        } else {
            // ---portrait mode---
            orientationMessage.setText("Portrait mode");
        }
    }
}