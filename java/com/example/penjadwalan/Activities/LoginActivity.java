package com.example.penjadwalan.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.penjadwalan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity {

    private EditText userMail, userPw;
    private Button bttnlogin;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private Intent Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      userMail = findViewById(R.id.login_email);
      userPw = findViewById(R.id.pw_login);
      bttnlogin = findViewById(R.id.buttonlogin);
      progressBar = (ProgressBar) findViewById(R.id.progressBar);
      firebaseAuth = FirebaseAuth.getInstance();
      Home = new Intent(this, HomeActivity.class);


      bttnlogin.setVisibility(View.VISIBLE);
      bttnlogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              bttnlogin.setVisibility(View.INVISIBLE);

              final String email = userMail.getText().toString();
              final String pw = userPw.getText().toString();

              if (email.isEmpty() || pw.isEmpty()){
                  showMessage("Please Verify all fields!");
                  bttnlogin.setVisibility(View.VISIBLE);
              }else{
                  sigIn(email,pw);
              }
          }
      });


    }

    private void sigIn(String email, String pw) {

        firebaseAuth.signInWithEmailAndPassword(email,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    bttnlogin.setVisibility(View.VISIBLE);
                    updateUI();


                } else{
                    showMessage(task.getException().getMessage());
                    bttnlogin.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    private void updateUI() {
        Intent homeActivity = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(homeActivity);
        finish();


    }

    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }
}
