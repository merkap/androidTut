package com.example.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BookModelAdapter extends BaseAdapter {

    private List<BookModel> list;
    private LayoutInflater layoutInflater;

    public BookModelAdapter(Context context, List<BookModel> list) {
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) view = layoutInflater.inflate(R.layout.book_item, parent,false);

        BookModel bookModel = getBookModel(position);

        TextView bookTitle = (TextView) view.findViewById(R.id.book_title);
        bookTitle.setText(bookModel.getTitle());
        TextView bookDesc = (TextView) view.findViewById(R.id.book_description);
        bookDesc.setText(bookModel.getDescription());
        return view;
    }

    private BookModel getBookModel(int position) {
        return (BookModel) getItem(position);
    }
}
