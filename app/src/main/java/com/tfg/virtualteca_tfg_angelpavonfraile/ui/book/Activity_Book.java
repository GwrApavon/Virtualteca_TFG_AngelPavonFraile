package com.tfg.virtualteca_tfg_angelpavonfraile.ui.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.BookAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.PartnerAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan.Activity_Loan;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner.Activity_Partner;

import java.util.ArrayList;

public class Activity_Book extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView bookBrowser;
    ListView book_list;
    DataBaseBook dbb;
    BookAdapter ba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //Search bar
        bookBrowser = findViewById(R.id.bookBrowser);
        bookBrowser.setOnQueryTextListener(this);

        //Moving buttons
        Button b_loan = findViewById(R.id.loan_button);
        b_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_Book.this, Activity_Loan.class);
                startActivity(intent);
                finish();
            }
        });
        Button b_home = findViewById(R.id.home_button);
        b_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button b_partner = findViewById(R.id.partner_button);
        b_partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_Book.this, Activity_Partner.class);
                startActivity(intent);
                finish();
            }
        });


        /* BOOK LIST */
        book_list = findViewById(R.id.book_list);
        dbb = new DataBaseBook(this);
        ba = new BookAdapter(this, dbb.bookList());
        book_list.setAdapter(ba);

        book_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                ArrayList<Book> books = dbb.bookList();
                if (pos < books.size()) {
                    Book book = books.get(pos);
                    int id = book.getBook_id();
                    Intent intent = new Intent(Activity_Book.this, Book_View.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                }
            }
        });
        
        /* ADD BUTTON */
        Button b_add = findViewById(R.id.b_add1);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_Book.this, Book_Add.class);
                startActivity(intent);
            }
        });
    }

    //Refresh the list to show the added partner
    public void onResume() {
        super.onResume();
        book_list = findViewById(R.id.book_list);
        dbb = new DataBaseBook(this);
        ba = new BookAdapter(this, dbb.bookList());
        book_list.setAdapter(ba);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    /* Checks changes in Search Bar */
    @Override
    public boolean onQueryTextChange(String s) {
        ba.filter(s);
        return false;
    }
}
