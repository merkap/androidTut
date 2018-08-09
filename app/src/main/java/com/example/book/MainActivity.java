package com.example.book;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    List<BookModel> list;
    BookModelAdapter bookModelAdapter;

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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                list.clear();

                JSONObject jsonObject = new JSONObject(s);
                int count = jsonObject.getInt("totalItems");
                JSONArray array = jsonObject.getJSONArray("items");

                int bias = 0;
                int booksStringsNum = 10;
                for (int i = bias; booksStringsNum > 0 && i < count; i++, booksStringsNum--) {
                    JSONObject item = (JSONObject) array.get(i);

                    String link = (String) item.get("selfLink");
                    JSONObject volumeInfo = item.getJSONObject("volumeInfo");

                    String title = (String) volumeInfo.get("title");
                    String description = (String) volumeInfo.get("description");
                    list.add(new BookModel(title, description, link));
                    bookModelAdapter.notifyDataSetChanged();
                }

//                ((TextView) findViewById(R.id.textView)).setText(jsonObject.getInt("totalItems"));

            } catch (JSONException ignored) {
            }
        }
    }
}