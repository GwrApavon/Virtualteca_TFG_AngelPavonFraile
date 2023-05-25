package com.tfg.virtualteca_tfg_angelpavonfraile.ui.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;

public class Book_Edit extends AppCompatActivity {

    private String title, author, language, genre, editorial, pbl_date, synopsis;
    private int ISBN, book_id;

    EditText title_text, ISBN_text, author_text, language_text, genre_text, editorial_text,
            pbl_date_text, synopsis_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);



        /* receives the id from the view activity */
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

        /* ASSIGN VIEWS TO VARIABLES */
        title_text = findViewById(R.id.titleTextEdit);
        ISBN_text = findViewById(R.id.isbnTextEdit);
        author_text = findViewById(R.id.authorTextEdit);
        language_text = findViewById(R.id.languageTextEdit);
        genre_text = findViewById(R.id.genreTextEdit);
        editorial_text = findViewById(R.id.editorialTextEdit);
        pbl_date_text = findViewById(R.id.pbl_dateTextEdit);
        synopsis_text = findViewById(R.id.synopsisTextEdit);

        /* fill fields */
        fillTextView(book_id);

        /* SAVE CHANGES DB */
        Button b_save = findViewById(R.id.saveButton);
        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = false;
                DataBaseBook dbb = new DataBaseBook(Book_Edit.this);
                title = title_text.getText().toString();
                ISBN = Integer.parseInt(ISBN_text.getText().toString());
                author = author_text.getText().toString();
                language = language_text.getText().toString();
                genre = genre_text.getText().toString();
                editorial = editorial_text.getText().toString();
                pbl_date = pbl_date_text.getText().toString();
                synopsis = synopsis_text.getText().toString();
                if (!isEmpty()) {
                    result =  dbb.editBook(book_id,title, ISBN, author, language, genre, editorial, pbl_date, synopsis);

                    if(result){
                        Toast.makeText(Book_Edit.this, "REGISTRO EDITADO", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(Book_Edit.this, "ERROR AL EDITAR REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Book_Edit.this, "DEBE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*check if fields are empty */
    private boolean isEmpty(){
        return title.equals("") && ISBN != 0 && author.equals("") && language.equals("") && genre.equals("")
                && editorial.equals("") && pbl_date.equals("") && synopsis.equals("");
    }

    /* refresh partner fields */
    public void onResume() {
        super.onResume();
        fillTextView(book_id);
    }

    /* fills book fields */
    public void fillTextView(int book_id){
        Book book;
        String title, author, language, genre, editorial, pbl_date, synopsis, ISBN;

        DataBaseBook dbb = new DataBaseBook(Book_Edit.this);

        book = dbb.getBookById(book_id);

        if (book != null) {
            title = book.getTitle();
            title_text.setText(title);
            ISBN = book.getISBN() + "";
            ISBN_text.setText(ISBN);
            author = book.getAuthor();
            author_text.setText(author);
            language = book.getLanguage();
            language_text.setText(language);
            genre = book.getGenre();
            genre_text.setText(genre);
            editorial = book.getEditorial();
            editorial_text.setText(editorial);
            pbl_date = book.getPbl_date();
            pbl_date_text.setText(pbl_date);
            synopsis = book.getSynopsis();
            synopsis_text.setText(synopsis);
        }

    }
}