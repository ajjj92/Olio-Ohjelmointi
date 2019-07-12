package com.example.bankapp_aj;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 7/11/19
 * AtteJantunen
 */
public class XmlMethods {
    private String filename = "data.xml";
    public XmlMethods() {
        //dostuff
    }

    public void saveXml(String filename) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }
        };
    }

    public void loadXml() {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }
        };

    }

    public String readDatatoString() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String line = null, allines=null;
        while (true) {
            try {
                if (!((line = in.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            allines += line+"\n";
        }
        in.close();
        System.out.println(allines);
        return  allines;
    }
}
