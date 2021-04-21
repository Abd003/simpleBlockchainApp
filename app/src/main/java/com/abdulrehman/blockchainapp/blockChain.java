package com.abdulrehman.blockchainapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class blockChain {
    ArrayList<block> blockChain;
    int length;
    public blockChain() {
        this.blockChain = new ArrayList<block>();
        this.blockChain.add(initialBlock());
        length = 1;
    }

    private block initialBlock(){
        return new block(0,new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()),"Shad has $700", "0");
    }

    public block getLatestBlock(){
        return this.blockChain.get(this.blockChain.size()-1);
    }

    public void addBlock(block newBlock){
        newBlock.setPreviousBlockHash(this.getLatestBlock().getBlockHash());
        newBlock.setBlockHash(newBlock.calculateHash());
        this.blockChain.add(newBlock);
        this.length+=1;
    }

    public boolean isChainValid(){
        for(int i = 1;i < this.length;i++){
            block currBlock = this.blockChain.get(i);
            block prevBlock = this.blockChain.get(i-1);
            if(!currBlock.getBlockHash().equals(currBlock.calculateHash())){
                return false;
            }
            if(!currBlock.getPreviousBlockHash().equals(prevBlock.getBlockHash())){
                return false;
            }
        }
        return true;
    }
}
