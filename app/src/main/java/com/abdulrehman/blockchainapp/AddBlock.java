package com.abdulrehman.blockchainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBlock extends AppCompatActivity {

    Button buttonAdd;
    EditText data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_block);
        buttonAdd = findViewById(R.id.buttonAdd);
        data = findViewById(R.id.inputMessage);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBlock.this, MainActivity.class);
                intent.putExtra("data",data.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}