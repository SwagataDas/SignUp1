package com.example.debjit.signup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {
    Button signupbutton, asubutton;
    EditText dob,firstname,lastname,emailid;
    DatePickerDialog datePickerDialog;
    private AwesomeValidation awesomeValidation;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupbutton = (Button) findViewById(R.id.signup);
        dob = (EditText) findViewById(R.id.dob);
        firstname =(EditText) findViewById(R.id.firstname);
        lastname =(EditText) findViewById(R.id.lastname);
        emailid = (EditText) findViewById(R.id.emailid);
        //email validation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.emailid, Patterns.EMAIL_ADDRESS, R.string.emailerror);

        dob.setShowSoftInputOnFocus(false);
        dob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                int mMonth = c.get(Calendar.MONTH);
                int mYear = c.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(SignupActivity.this, new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        dob.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);
                    }
                },mDay, mMonth, mYear);
                datePickerDialog.show();
            }
        });
        Intent intent2 = getIntent();

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(SignupActivity.this, UserprofileActivity.class);
                startActivity(intent3);
            }
        });

        //asubutton.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {
             //   Intent intent3 = new Intent(SignupActivity.this, UserprofileActivity.class);
             //   startActivity(intent3);
          //  }
       // });

    }
}
