package com.example.book.net;

import com.example.book.MainActivity;
import com.example.book.dataModel.BookModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonHandler {
    private MainActivity mainActivity;
    private String json;
    private int booksCount;
    private JSONArray booksArray;
    private List<BookModel> list;

    public JsonHandler(MainActivity mainActivity, String json, List<BookModel> list) {
        this.mainActivity = mainActivity;
        this.json = json;
        this.list = list;
    }

    public List<BookModel> invoke() throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        booksCount = jsonObject.getInt("totalItems");
        booksArray = jsonObject.getJSONArray("items");

        int bias = 0;
        int booksStringsNum = 10;
        for (int i = bias; booksStringsNum > 0 && i < booksCount; i++, booksStringsNum--) {
            JSONObject item = (JSONObject) booksArray.get(i);

            String link = (String) item.get("selfLink");
            JSONObject volumeInfo = item.getJSONObject("volumeInfo");

            String title = (String) volumeInfo.get("title");
            String description = (String) volumeInfo.get("description");
            list.add(new BookModel(title, description, link));
        }
        return list;
    }
}
