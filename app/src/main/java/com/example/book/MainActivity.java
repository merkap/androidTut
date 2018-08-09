package com.example.book;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.book.dataModel.BookModel;
import com.example.book.dataModel.BookModelAdapter;
import com.example.book.net.JsonHandler;
import com.example.book.net.Request;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<BookModel> list;
    BookModelAdapter bookModelAdapter;
    JsonHandler jsonHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String url = getString(R.string.url);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        bookModelAdapter = new BookModelAdapter(this, list);
        ListView container = findViewById(R.id.container);
        container.setAdapter(bookModelAdapter);

        findViewById(R.id.findButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = ((EditText) findViewById(R.id.bookQuery)).getText().toString();
                new Task().execute(url + query);
            }
        });
    }

    class Task extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return Request.makeRequest(strings[0]);
        }

        @Override
        protected void onPostExecute(String json) {
            try {
                super.onPostExecute(json);
                list.clear();
                jsonHandler = new JsonHandler(MainActivity.this, json, list);
                jsonHandler.invoke();
                bookModelAdapter.notifyDataSetChanged();
            } catch (JSONException ignored) {
            }
        }

    }

}