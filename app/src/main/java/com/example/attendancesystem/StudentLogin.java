package com.example.attendancesystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentLogin extends AppCompatActivity {
    private EditText emails, passwords;
    Button signbutton;
    FirebaseAuth firebaseAuth;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_student_login);
        emails = (EditText) findViewById(R.id.editText3);
        passwords = (EditText) findViewById(R.id.editText4);
        signbutton = (Button) findViewById(R.id.signinButton);
        pd = new ProgressDialog(this);
        Button button=(Button)findViewById(R.id.signinButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emails.getText().toString().trim();
                String password = passwords.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(StudentLogin.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(StudentLogin.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                pd.setMessage("Signing In");
                pd.setCancelable(false);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(StudentLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "SignIn Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(StudentLogin.this, StudentInformationActivity.class);
                            intent.putExtra("Name", "Email");
                            intent.putExtra("Email", email);
                            firebaseAuth.signOut();
                            startActivity(intent);
                            pd.dismiss();
                        } else {
                            Toast.makeText(StudentLogin.this, "Oops!! Wrong credentials", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }
                });
            }
        });
    }
}