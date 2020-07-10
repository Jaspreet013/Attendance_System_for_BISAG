package com.example.attendancesystem;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SelectRollNo extends AppCompatActivity {
    EditText et;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_roll_no);
        et=(EditText)findViewById(R.id.editText12);
        bt=(Button)findViewById(R.id.button7);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll=et.getText().toString();
                try{
                    if(roll.isEmpty()){
                        Toast.makeText(SelectRollNo.this, "Roll No. cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(SelectRollNo.this, StudentInformationActivity.class);
                        intent.putExtra("Name", "RollNo");
                        intent.putExtra("RollNo", roll);
                        startActivity(intent);
                        finish();
                    }
                }catch (Exception e){
                    Log.e("Error is ",e.toString());
                }
            }
        });

    }
}

