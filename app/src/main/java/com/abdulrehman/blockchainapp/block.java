package com.abdulrehman.blockchainapp;

import java.util.Arrays;

public class block {
    private String data;
    private String timeStamp;
    private  int index;
    private String blockHash;
    private String previousBlockHash;

    public block(int index, String timeStamp, String data, String previousBlockHash) {
        this.data = data;
        this.index = index;
        this.timeStamp = timeStamp;
        this.previousBlockHash = previousBlockHash;
        this.blockHash = calculateHash();
    }

    public String calculateHash(){
        return String.valueOf(Arrays.hashCode(new int[]{this.index, this.previousBlockHash.hashCode(),this.timeStamp.hashCode(), this.data.hashCode()}));
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    @Override
    public String toString() {
        return "block{" +
                "data='" + data + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", index=" + index +
                ", blockHash='" + blockHash + '\'' +
                ", previousBlockHash='" + previousBlockHash + '\'' +
                '}';
    }
}