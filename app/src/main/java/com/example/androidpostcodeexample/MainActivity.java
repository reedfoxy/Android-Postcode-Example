package com.example.androidpostcodeexample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 1000;

    private Button search;
    private EditText address1;
    private EditText address2;
    private EditText address3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search); // search button
        address1 = findViewById(R.id.address1); // address result Text
        address2 = findViewById(R.id.address2); // address result Text
        address3 = findViewById(R.id.address3); // address result Text


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), WebViewActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Log.d("onActivityResult","onActivityResult1");

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){

            String arg1 = data.getStringExtra("arg1"); // postcode
            String arg2 = data.getStringExtra("arg2"); // address
            String arg3 = data.getStringExtra("arg3"); // building

            if(!arg1.isEmpty()){
                address1.setText(arg1);
            } else {
                address1.setText("NONE");
            }

            if(!arg2.isEmpty()){
                address2.setText(arg2);
            } else {
                address2.setText("NONE");
            }

            if(!arg3.isEmpty()){
                address3.setText(arg3);
            } else {
                address3.setText("NONE");
            }

        }
    }
}
