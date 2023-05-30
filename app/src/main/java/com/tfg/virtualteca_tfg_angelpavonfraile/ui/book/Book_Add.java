package com.tfg.virtualteca_tfg_angelpavonfraile.ui.book;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.Utilities;

public class Book_Add extends AppCompatActivity {

    private String title, author, language, genre, editorial, pbl_date, synopsis, ISBN;

    EditText title_text, ISBN_text, author_text, language_text, genre_text, editorial_text,
             pbl_date_text, synopsis_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);

        title_text = findViewById(R.id.titleText);
        ISBN_text = findViewById(R.id.isbnText);
        author_text = findViewById(R.id.authorText);
        language_text = findViewById(R.id.languageText);
        genre_text = findViewById(R.id.genreText);
        editorial_text = findViewById(R.id.editorialText);
        pbl_date_text = findViewById(R.id.pbl_dateText);
        synopsis_text = findViewById(R.id.synopsisText);


        /* INSERT NEW BOOK INTO DB */
        Button b_add_book = findViewById(R.id.saveButton);
        b_add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long result = 0;
                DataBaseBook dbb = new DataBaseBook(Book_Add.this);
                title = title_text.getText().toString();
                ISBN = ISBN_text.getText().toString();
                author = author_text.getText().toString();
                language = language_text.getText().toString();
                genre = genre_text.getText().toString();
                editorial = editorial_text.getText().toString();
                pbl_date = pbl_date_text.getText().toString();
                synopsis = synopsis_text.getText().toString();

                if (!isEmpty()) { //NOT EMPTY
                    if(Utilities.validateDateFormat(pbl_date)) { //Correct Format
                        result = dbb.insertBook(title, ISBN, author, language, genre, editorial, pbl_date, synopsis);

                        if (result != 0) { //Correct insert
                            Toast.makeText(Book_Add.this, "REGISTRO AÑADIDO", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Book_Add.this, "ERROR AL AÑADIR REGISTRO", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                    else{
                        Toast.makeText(Book_Add.this, "ERROR CON EL FORMATO DE LA FECHA", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Book_Add.this, "DEBE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /* Checks if the fields are empty */
    private boolean isEmpty(){
        return title.equals("") && ISBN.equals("") && author.equals("") && language.equals("") && genre.equals("")
                && editorial.equals("") && pbl_date.equals("") && synopsis.equals("");
    }
}
