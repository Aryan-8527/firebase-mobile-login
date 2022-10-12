package com.aryan.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    EditText inputmobilenumber;
    Button getotp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputmobilenumber = findViewById(R.id.inputmobilenumber);
        getotp = findViewById(R.id.getotp);
        ProgressBar progressBar = findViewById(R.id.progressbargetotp);


        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputmobilenumber.getText().toString().isEmpty()) {
                    if ((inputmobilenumber.getText().toString().trim()).length() == 10){
                        progressBar.setVisibility(view.VISIBLE);
                    getotp.setVisibility(view.INVISIBLE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + inputmobilenumber.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            MainActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(view.GONE);
                                    getotp.setVisibility(view.INVISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(view.GONE);
                                    getotp.setVisibility(view.INVISIBLE);
                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(backendotp, forceResendingToken);
                                    progressBar.setVisibility(view.GONE);
                                    getotp.setVisibility(view.INVISIBLE);
                                    Intent intent = new Intent(getApplicationContext(), veification.class);
                                    intent.putExtra("mobile", inputmobilenumber.getText().toString());
                                    intent.putExtra("backendotp", backendotp);
                                    startActivity(intent);

                                }
                            }
                    );

                    Intent intent = new Intent(getApplicationContext(), veification.class);
                    intent.putExtra("mobile", inputmobilenumber.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter correct Number", Toast.LENGTH_SHORT).show();
                }
            } else{
                Toast.makeText(MainActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
            } }
        });
    }
}
