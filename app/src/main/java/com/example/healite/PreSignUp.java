package com.example.healite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healite.Model.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PreSignUp extends Fragment {

    private TextView text;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private FloatingActionButton okButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_pre_sign_up, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        setContentView(R.layout.activity_pre_sign_up);

        text = view.findViewById(R.id.preSignUp);
        okButton = view.findViewById(R.id.okButton);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("ExistingUsers");
        userID = user.getUid();

        // this will display the user's name in the main logged in screen
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userProfile = snapshot.getValue(Users.class);

                if (userProfile != null) {
                    String firstName = userProfile.firstName;

                    text.setText("Thank you for signing up, " + firstName + "! Please take a few minutes to answer the following questionnaire.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        // when clicking the add note button
        okButton.setOnClickListener((v) -> startActivity(new Intent(getActivity(), QuestionnaireActivity.class)));
    }
}