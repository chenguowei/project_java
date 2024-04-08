package com.testclass;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StoringAndRevoceringData {

    public static void main(String[] args) {
        try (
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data.txt")))
                ){
            out.writeDouble(2.13);
            out.writeUTF("This is a");
            out.writeDouble(1.232);
            out.writeUTF("fsdfs");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
