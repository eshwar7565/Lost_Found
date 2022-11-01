package eshwar.user.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotpasswordActivity extends AppCompatActivity {
    private Button buttonresetpassword;
    private EditText editTextPwdresetemail;
    private ProgressBar progressBar;
    private FirebaseAuth authProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        getSupportActionBar().setTitle("RESET PASSWORD");

        editTextPwdresetemail = findViewById(R.id.edittextemailaddressforpasswordreset);
        buttonresetpassword = findViewById(R.id.resetpasswordoption);
        progressBar = findViewById(R.id.progressbarforgotpasswordactivity);
        buttonresetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextPwdresetemail.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(ForgotpasswordActivity.this, "Please enter your registered email address.", Toast.LENGTH_SHORT).show();
                    editTextPwdresetemail.setError("Email is required");
                    editTextPwdresetemail.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(ForgotpasswordActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    editTextPwdresetemail.setError("Valid email is required");
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    resetPassword(email);
                }
            }
        });
    }

    private void resetPassword(String email) {
        authProfile = FirebaseAuth.getInstance();
        authProfile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotpasswordActivity.this, "Please check your inbox for password reset link", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgotpasswordActivity.this,MainActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}