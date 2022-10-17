package com.example.healite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    //private Button logout;
    private TextView welcomeMessage;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    //private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //ImageView leftIcon = findViewById(R.id.left_icon);
        ImageView rightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);

        welcomeMessage = (TextView) findViewById(R.id.welcome);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("ExistingUsers");
        userID = user.getUid();


//        leftIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Toast.makeText(ProfileActivity.this, "You clicked left icon", Toast.LENGTH_SHORT).show();
//            }
//        });

        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
                //Toast.makeText(ProfileActivity.this, "You clicked right icon", Toast.LENGTH_SHORT).show();
            }
        });

        title.setText("");
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
//            }
//        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userProfile = snapshot.getValue(Users.class);

                if (userProfile != null) {
                    String firstName = userProfile.firstName;
                    String lastName = userProfile.lastName;
                    String email = userProfile.email;

                    welcomeMessage.setText("Welcome, " + firstName + "!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

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
                        Toast.makeText(ProfileActivity.this, "User Settings selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.user_activity:
                        Toast.makeText(ProfileActivity.this, "User Activity selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.user_logout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                        return true;
                    default:
                        return true;
                }
            }
        });

        popupMenu.show();
    }

}