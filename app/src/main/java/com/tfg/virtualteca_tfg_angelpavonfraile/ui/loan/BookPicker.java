package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.BookAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;

import java.util.ArrayList;

public class BookPicker extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView bookPickerBrowser;
    ListView book_list;
    DataBaseBook dbb;
    BookAdapter ba;
    Book book;
    String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_picker);

        /* RECEIVES ID FROM PREV ACTIVITY */
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null)
            {
                from = null;
            }
            else
            {
                from = extras.getString("FROM");
            }
        }
        else {
            from = (String) savedInstanceState.getSerializable("FROM");
        }
        Log.e(TAG, "FROM:" + from);
        
        /* Search bar */
        bookPickerBrowser = findViewById(R.id.bookPickerBrowser);
        bookPickerBrowser.setOnQueryTextListener(this);

        /* List */
        book_list = findViewById(R.id.book_list);
        dbb = new DataBaseBook(this);
        ba = new BookAdapter(this, dbb.bookList());
        book_list.setAdapter(ba);

        /* Item click Listener */
        book_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                ArrayList<Book> books = dbb.bookList();
                if (pos < books.size()) {
                    book = books.get(pos);
                    int id = book.getBook_id();
                    Intent intent = new Intent(BookPicker.this, Loan_Add.class);
                    if (from.equals("LoanEdit")){ //Changes depending on the activity it's called from
                        intent = new Intent(BookPicker.this, Loan_Edit.class);
                    }
                    intent.putExtra("BOOK_ID", id);
                    Log.e(TAG, "book picker edit:" + id);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        ba.filter(s);
        return false;
    }
}
