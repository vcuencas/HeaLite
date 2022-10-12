package com.example.healite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class ProfileActivity extends AppCompatActivity {

    private Button logout;
    private TextView welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        welcomeMessage = (TextView) findViewById(R.id.welcome);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //TODO: trying to get the user name to be displayed in the profile page.
//        if (user != null) {
//           String displayName = user.getDisplayName();
//
//           for (UserInfo userInfo : user.getProviderData()) {
//               if (displayName == null && userInfo.getDisplayName() != null) {
//                   displayName = userInfo.getDisplayName();
//               }
//           }
//            welcomeMessage.setText("Welcome " + displayName);
//        }


//        Intent intent = getIntent();
//        String str = intent.getStringExtra("message_key");
//        welcomeMessage.setText("Welcome " + str);

        logout = (Button) findViewById(R.id.signOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
    }
}