package com.example.namsom.adminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.namsom.adminapp.R.id.delsignbutton;
import static com.example.namsom.adminapp.R.id.editsignbutton;

public class Center_Page extends AppCompatActivity {

    //Explicit
    private Button addsignbutton, editsignbutton, delsignbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center__page);

        //Bind Widget
        addsignbutton = (Button) findViewById(R.id.addsignbutton);
        editsignbutton = (Button) findViewById(R.id.editsignbutton);
        delsignbutton = (Button) findViewById(R.id.delsignbutton);

        //Add Sign Controller
        addsignbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Center_Page.this, AddSign.class));
            }
        });

        //Edit Sign Controller
        editsignbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Center_Page.this, EditSign.class));
            }
        });


        //Del Sign Controller
        delsignbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Center_Page.this, DelSign.class));
            }
        });



    } //Main Method
}   //Main class
