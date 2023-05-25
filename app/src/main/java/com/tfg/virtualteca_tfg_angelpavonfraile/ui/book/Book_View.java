package com.tfg.virtualteca_tfg_angelpavonfraile.ui.book;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseSupport;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;

public class Book_View extends AppCompatActivity {


    TextView title_text, ISBN_text, author_text, language_text, genre_text, editorial_text,
            pbl_date_text, synopsis_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);


        int book_id;
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null)
            {
                book_id = Integer.parseInt(null);
            }
            else
            {
                book_id = extras.getInt("ID");
            }
        }
        else
        {
            book_id = (int) savedInstanceState.getSerializable("ID");
        }

        fillTextView(book_id);


        //EDIT BOOK
        Button b_edit = findViewById(R.id.editButton);
        b_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Book_View.this, Book_Edit.class);
                intent.putExtra("ID", book_id);
                startActivity(intent);
            }
        });

        //DELETE BOOK
        Button b_delete = findViewById(R.id.deleteButton);
        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseBook dbb = new DataBaseBook(Book_View.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(Book_View.this);
                builder.setMessage("Está a punto de eliminar un libro. ¿Desea continuar?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean deleted = dbb.deleteBook(book_id);
                                if(deleted){
                                    finish();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        });
    }

    public void fillTextView(int book_id){
        Book book;
        String title, author, language, genre, editorial, pbl_date, synopsis, ISBN;
        
        DataBaseBook dbb = new DataBaseBook(Book_View.this);

        title_text = findViewById(R.id.titleTextView);
        ISBN_text = findViewById(R.id.isbnTextView);
        author_text = findViewById(R.id.authorTextView);
        language_text = findViewById(R.id.languageTextView);
        genre_text = findViewById(R.id.genreTextView);
        editorial_text = findViewById(R.id.editorialTextView);
        pbl_date_text = findViewById(R.id.pbl_dateTextView);
        synopsis_text = findViewById(R.id.synopsisTextView);

        book = dbb.getBookById(book_id);

        if (book != null) {
            title = "Título:    " +book.getTitle();
            title_text.setText(title);
            ISBN = "ISBN:    " +book.getISBN() + "";
            ISBN_text.setText(ISBN);
            author = "Autor:    " +book.getAuthor();
            author_text.setText(author);
            language = "Idioma:    " +book.getLanguage();
            language_text.setText(language);
            genre = "Género:    " +book.getGenre();
            genre_text.setText(genre);
            editorial = "Editorial:    " + book.getEditorial();
            editorial_text.setText(editorial);
            pbl_date = "Fecha de publicación:    " + book.getPbl_date();
            pbl_date_text.setText(pbl_date);
            synopsis = "Sinopsis:\n" + book.getSynopsis();
            synopsis_text.setText(synopsis);
        }

    }
}