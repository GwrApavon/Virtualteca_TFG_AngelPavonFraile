package com.tfg.virtualteca_tfg_angelpavonfraile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookAdapter extends BaseAdapter {
    Context context;
    ArrayList<Book> books;
    ArrayList<Book> booksOriginal;

    public BookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
        booksOriginal = new ArrayList<>();
        booksOriginal.addAll(books);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater shown = LayoutInflater.from(context);
        View element = shown.inflate(R.layout.book, viewGroup, false);

        TextView title = element.findViewById(R.id.book_title);
        title.setText(books.get(i).getTitle());

        TextView author = element.findViewById(R.id.book_author);
        author.setText(books.get(i).getAuthor());

        TextView editorial = element.findViewById(R.id.book_editorial);
        editorial.setText(books.get(i).getEditorial());

        return element;
    }

    public void filter (String search){
        int length = search.length();
        if(length == 0){
            books.clear();
            books.addAll(booksOriginal);
        }
        else{
            List<Book> collection =  books.stream()
                    .filter(i -> i.getTitle().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
            books.clear();
            books.addAll(collection);
        }

        notifyDataSetChanged();
    }

}
