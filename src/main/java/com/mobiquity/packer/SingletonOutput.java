package com.mobiquity.packer;

public class SingletonOutput {

    private static final StringBuilder output = new StringBuilder();

    private SingletonOutput(){
    }

    public static StringBuilder getInstance() {
        return output;
    }

}
