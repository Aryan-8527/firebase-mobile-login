package com.aryan.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class veification extends AppCompatActivity {

    EditText inputtype1 , inputtype2, inputtype3 , inputtype4 ,inputtype5 , inputtype6;
    Button submit;
    String getotpbackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veification);

         final Button submit = findViewById(R.id.submit);

         inputtype1 = findViewById(R.id.inputtype1);
         inputtype2 = findViewById(R.id.inputtype2);
         inputtype3 = findViewById(R.id.inputtype3);
         inputtype4 = findViewById(R.id.inputtype4);
         inputtype5 = findViewById(R.id.inputtype5);
         inputtype6 = findViewById(R.id.inputtype6);

        TextView textView = findViewById(R.id.mobilenumbershow);
        textView.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        getotpbackend = getIntent().getStringExtra("backendotp");

         final ProgressBar progressBarsubmit = findViewById(R.id.progressbarsubmit);





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!inputtype1.getText().toString().trim().isEmpty() &&!inputtype2.getText().toString().trim().isEmpty()
                   && !inputtype3.getText().toString().trim().isEmpty() &&!inputtype4.getText().toString().trim().isEmpty()
                    && !inputtype5.getText().toString().trim().isEmpty() && !inputtype6.getText().toString().trim().isEmpty())
                {
                    String entercodeotp = inputtype1.getText().toString()+
                            inputtype2.getText().toString() +
                            inputtype3.getText().toString() +
                            inputtype4.getText().toString() +
                            inputtype5.getText().toString() +
                            inputtype6.getText().toString();

                    if(getotpbackend!=null){
                        progressBarsubmit.setVisibility(view.VISIBLE);
                        submit.setVisibility(view.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpbackend, entercodeotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBarsubmit.setVisibility(view.GONE);
                                        submit.setVisibility(view.VISIBLE);

                                        if(task.isSuccessful()){
                                            Intent intent =new Intent(getApplicationContext(),dashboard.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(veification.this, "Enter correct OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else{
                        Toast.makeText(veification.this, "Please check Internet connection", Toast.LENGTH_SHORT).show();
                    }


  //                  Toast.makeText(veification.this, "OTP verify", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(veification.this, "Please Enter all Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberotpmove();

        findViewById(R.id.resendotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        veification.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(veification.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(newbackendotp, forceResendingToken);
                                getotpbackend = newbackendotp;
                                Toast.makeText(veification.this, "OTP sended successfully", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });


    }

    private void numberotpmove() {
        inputtype1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int satrt, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputtype2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputtype2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputtype3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputtype3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int satrt, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputtype4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputtype4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int satrt, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputtype5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputtype5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int satrt, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputtype6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}