package com.abdulrehman.blockchainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MyRvAdapter.OnItemListner{

    public static int REQUEST_CODE = 1;
    blockChain obj;
    FloatingActionButton add_block;
    RecyclerView rv;
    MyRvAdapter adapter;
    int clickPosition;
    TextView chain_validity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_block = findViewById(R.id.add_block);
        chain_validity = findViewById(R.id.chain_validity);
        rv = findViewById(R.id.rv);
        obj = new blockChain();

        adapter = new MyRvAdapter(obj,MainActivity.this,MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        add_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REQUEST_CODE = 1;
                startActivityForResult(new Intent(MainActivity.this, AddBlock.class), REQUEST_CODE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(REQUEST_CODE == 1){
            if(resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Toast.makeText(MainActivity.this, "Data Received", Toast.LENGTH_SHORT).show();
                    String blockData = (String) data.getExtras().getString("data");
                    String timeStamp = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                    int index = obj.length;
                    String prevHash = obj.getLatestBlock().getBlockHash();
                    obj.addBlock(new block(index,timeStamp,blockData,prevHash));
                    if(obj.isChainValid()){
                        chain_validity.setText("Chain is Valid");
                    }
                    else{
                        chain_validity.setText("Chain is Compromised");
                        chain_validity.setTextColor(Color.parseColor("#EE524F"));
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }
        if (REQUEST_CODE == 2) {
            if (resultCode == Activity.RESULT_OK){
                if(data != null){
                    Toast.makeText(MainActivity.this, "Data Received", Toast.LENGTH_SHORT).show();
                    String blockindex = (String) data.getExtras().getString("index");
                    String blocktimestamp = (String) data.getExtras().getString("timestamp");
                    String blockData = (String) data.getExtras().getString("data");
                    String blockprevhash = (String) data.getExtras().getString("prevhash");
                    int block_place = clickPosition;
                    obj.blockChain.get(block_place).setIndex(Integer.parseInt(blockindex));
                    obj.blockChain.get(block_place).setTimeStamp(blocktimestamp);
                    obj.blockChain.get(block_place).setData(blockData);
                    obj.blockChain.get(block_place).setPreviousBlockHash(blockprevhash);
                    obj.blockChain.get(block_place).setBlockHash(obj.blockChain.get(block_place).calculateHash());
                    if(obj.isChainValid()){
                        chain_validity.setText("Chain is Valid");
                    }
                    else{
                        chain_validity.setText("Chain is Compromised");
                        chain_validity.setTextColor(Color.parseColor("#EE524F"));
                    }
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void ONItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, EditBlock.class);
        intent.putExtra("index",String.valueOf(obj.blockChain.get(position).getIndex()));
        intent.putExtra("timestamp",obj.blockChain.get(position).getTimeStamp());
        intent.putExtra("data",obj.blockChain.get(position).getData());
        intent.putExtra("prevhash",obj.blockChain.get(position).getPreviousBlockHash());
        clickPosition = position;
        REQUEST_CODE = 2;
        startActivityForResult(new Intent(MainActivity.this, EditBlock.class), REQUEST_CODE);
    }
}