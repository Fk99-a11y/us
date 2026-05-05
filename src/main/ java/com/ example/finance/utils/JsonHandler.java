package com.example.finance.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonHandler {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final String DATA_DIR = "data/";

    // ===== Ensure data folder =====
    public static void ensureDataDirectoryExists() {
        java.io.File dir = new java.io.File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // ===== SAVE ANY OBJECT =====
    public static <T> void saveToFile(String fileName, T data) {
        ensureDataDirectoryExists();
        try (FileWriter writer = new FileWriter(DATA_DIR + fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ===== LOAD OBJECT =====
    public static <T> T loadFromFile(String fileName, Class<T> clazz) {
        try (FileReader reader = new FileReader(DATA_DIR + fileName)) {
            return gson.fromJson(reader, clazz);
        } catch (IOException e) {
            return null;
        }
    }

    // ===== LOAD LIST =====
    public static <T> List<T> loadListFromFile(String fileName, Class<T> classType) {
        ensureDataDirectoryExists();

        java.io.File file = new java.io.File(DATA_DIR + fileName);
        if (!file.exists()) {
            return new java.util.ArrayList<>();
        }

        try (FileReader reader = new FileReader(DATA_DIR + fileName)) {
            Type type = TypeToken.getParameterized(List.class, classType).getType();
            List<T> result = gson.fromJson(reader, type);
            return result != null ? result : new java.util.ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
}
