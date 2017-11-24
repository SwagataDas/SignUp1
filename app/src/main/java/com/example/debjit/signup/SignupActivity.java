package com.example.debjit.signup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {
    Button signupbutton, asubutton;
    ImageButton imageButton;
    ImageView imageView1;
    EditText dob,firstname,lastname,emailid;
    DatePickerDialog datePickerDialog;
    private AwesomeValidation awesomeValidation;
    private int PICK_IMAGE_REQUEST =1;
    private int IMAGE_CAPTURE=1;
    Integer REQUEST_CAMERA=1, SELECT_FILE=0;
    ImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        imageView1 = (ImageView) findViewById(R.id.imageView);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                SelectImage();
            }
        });
        signupbutton = (Button) findViewById(R.id.signup);
        dob = (EditText) findViewById(R.id.dob1);
        firstname =(EditText) findViewById(R.id.firstname);
        lastname =(EditText) findViewById(R.id.lastname);
        emailid = (EditText) findViewById(R.id.emailid);
        imageButton = (ImageButton) findViewById(R.id.dobbtn);
        //email validation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.emailid, Patterns.EMAIL_ADDRESS, R.string.emailerror);

        imageButton.setOnClickListener(new View.OnClickListener() {
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

        }

    private void SelectImage(){

        final CharSequence[] items={"Camera", "Gallery", "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(SignupActivity.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("Camera")){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);

                }else if (items[i].equals("Gallery")){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("/image");
                    startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

                }else if (items[i].equals("Cancel")){
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        if(requestCode== Activity.RESULT_OK){

            if (resultCode==REQUEST_CAMERA){

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                imageView.setImageBitmap(bmp);
            }else if (requestCode==SELECT_FILE){
                Uri selectedImageUri = data.getData();
                imageView.setImageURI(selectedImageUri);
            }
        }
    }

        //asubutton.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {
             //   Intent intent3 = new Intent(SignupActivity.this, UserprofileActivity.class);
             //   startActivity(intent3);
          //  }
       // });

    }

