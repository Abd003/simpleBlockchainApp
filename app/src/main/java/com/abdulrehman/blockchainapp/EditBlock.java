package com.abdulrehman.blockchainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditBlock extends AppCompatActivity {

    EditText edit_block_prev_hash,edit_block_data,edit_block_timestamp,edit_block_index, edit_block_hash;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_block);
        edit_block_index = (EditText) findViewById(R.id.edit_block_index);
        edit_block_timestamp = (EditText) findViewById(R.id.edit_block_timestamp);
        edit_block_data = (EditText) findViewById(R.id.edit_block_data);
        edit_block_prev_hash = (EditText) findViewById(R.id.edit_block_prev_hash);
        save = findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditBlock.this, MainActivity.class);
                intent.putExtra("index",edit_block_index.getText().toString());
                intent.putExtra("timestamp",edit_block_timestamp.getText().toString());
                intent.putExtra("data",edit_block_data.getText().toString());
                intent.putExtra("prevhash",edit_block_prev_hash.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}