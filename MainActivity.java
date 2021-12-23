package com.example.myapplication3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Movies{
    String name;
    String year;
}

class M {
    ArrayList<Movies> movies;
}

public class MainActivity extends AppCompatActivity {
    private TextView textView; // Имя
    private TextView textView2; // Год
   ArrayList<Movies> movies;

    public ArrayList<Movies> loadMovies() throws IOException {
        InputStream stream = getAssets().open("movies.json");
        InputStreamReader reader = new InputStreamReader(stream);
        Gson gson = new Gson();
        M m = gson.fromJson(reader, M.class);
        return m.movies;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            movies = loadMovies();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

    }
    public void clickButton(View v) throws IOException {
        String res;
        int a = (int)(Math.random()*movies.size());
        if (movies.isEmpty()) {
            res = "Фильмы закончились!";
            textView.setText((CharSequence) res);
            res = " ";
            textView2.setText((CharSequence) res);
        }
        else {
            res = movies.get(a).name;
            textView.setText((CharSequence)res);
            res = movies.get(a).year;
            textView2.setText((CharSequence)res);
            movies.remove(a);
        }
    }
}