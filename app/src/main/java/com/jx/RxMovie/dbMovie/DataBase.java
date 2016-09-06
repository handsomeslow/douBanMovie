package com.jx.RxMovie.dbMovie;

import com.jx.RxMovie.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by junxu on 2016/7/19.
 */
public class DataBase {
    private static String DATA_FILE_NAME = "data.db";

    private static DataBase INSTANCE;

    File dataFile = new File(App.getInstance().getFilesDir(), DATA_FILE_NAME);
    Gson gson = new Gson();

    private DataBase() {
    }

    public static DataBase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataBase();
        }
        return INSTANCE;
    }

    public DBMovie readdata() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Reader reader = new FileReader(dataFile);
            return gson.fromJson(reader, new TypeToken<DBMovie>(){}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeData(DBMovie items) {
        String json = gson.toJson(items);
        try {
            if (!dataFile.exists()) {
                try {
                    dataFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Writer writer = new FileWriter(dataFile);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        dataFile.delete();
    }
}
