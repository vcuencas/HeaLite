package com.example.healite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healite.Model.Questionnaire;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    private TextView title;
    private ImageView rightIcon;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rightIcon = findViewById(R.id.right_icon);
        title = findViewById(R.id.toolbar_title);
        navigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.body_container,new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        // for selecting the different menu options in the bottom navigation bar.
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_calendar:
                        fragment = new CalendarFragment();
                        break;
                    case R.id.nav_events:
                        fragment = new EventsFragment();
                        break;
                    case R.id.nav_recommendation:
                        fragment = new RecommendationFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container,fragment).commit();
                return true;
            }
        });


        // when the three lines menu is clicked.
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });

        title.setText("");

    }

    // this function will display the pop-up menu items
    private void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(ProfileActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.user_profile:
                        Toast.makeText(ProfileActivity.this, "User Profile selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.user_settings:
                        startActivity(new Intent(ProfileActivity.this, QuestionnaireActivity.class));
                        Toast.makeText(ProfileActivity.this, "User Questionnaire selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.user_activity:
                        Toast.makeText(ProfileActivity.this, "User Activity selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.user_logout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                        return true;
                    default:
                        return true;
                }
            }
        });

        popupMenu.show();
    }

}