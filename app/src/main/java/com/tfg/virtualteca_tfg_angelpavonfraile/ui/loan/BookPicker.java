package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.BookAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.book.Book_View;

import java.util.ArrayList;

public class BookPicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_picker);


        ListView book_list = findViewById(R.id.book_list);
        DataBaseBook dbb = new DataBaseBook(this);
        BookAdapter ba = new BookAdapter(this, dbb.bookList());
        book_list.setAdapter(ba);

        book_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                ArrayList<Book> books = dbb.bookList();
                if (pos < books.size()) {
                    Book book = books.get(pos);
                    int id = book.getBook_id();
                    Intent intent = new Intent(BookPicker.this, Loan_add.class);
                    intent.putExtra("BOOK_ID", id);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}