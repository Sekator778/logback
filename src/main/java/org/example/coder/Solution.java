package org.example.coder;
import java.io.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fis = new FileInputStream(reader.readLine());

        reader = new BufferedReader(new InputStreamReader(fis));

        while (reader.ready()) {
            char ch = (char) reader.read();
            System.out.print(ch);
        }
        reader.close();
    }
}