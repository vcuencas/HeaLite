package com.example.healite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class CalendarActivity extends AppCompatActivity {

    //private TextView dateSelected;
    //private CalendarView calendar;
    private ImageView rightIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        rightIcon = findViewById(R.id.right_icon);

        // when the three lines menu is clicked.
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
    }

    // this function will display the pop-up menu items
    private void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(CalendarActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.user_profile:
                        Toast.makeText(CalendarActivity.this, "User Profile selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.user_settings:
                        Toast.makeText(CalendarActivity.this, "User Settings selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.user_activity:
                        Toast.makeText(CalendarActivity.this, "User Activity selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.user_logout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(CalendarActivity.this, MainActivity.class));
                        return true;
                    default:
                        return true;
                }
            }
        });

        popupMenu.show();
    }
}