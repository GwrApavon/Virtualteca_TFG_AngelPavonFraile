package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.book.Book_View;

public class BookPreview extends AppCompatActivity {

    TextView title_text, ISBN_text, author_text, language_text, genre_text, editorial_text,
            pbl_date_text, synopsis_text;

    int book_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_preview);

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
        Log.e(TAG, "book id:" + book_id);

        fillTextView(book_id);
    }

    public void fillTextView(int book_id){
        Book book;
        String title, author, language, genre, editorial, pbl_date, synopsis, ISBN;

        DataBaseBook dbb = new DataBaseBook(BookPreview.this);

        title_text = findViewById(R.id.titleTextPreview);
        ISBN_text = findViewById(R.id.isbnTextPreview);
        author_text = findViewById(R.id.authorTextPreview);
        language_text = findViewById(R.id.languageTextPreview);
        genre_text = findViewById(R.id.genreTextPreview);
        editorial_text = findViewById(R.id.editorialTextPreview);
        pbl_date_text = findViewById(R.id.pbl_dateTextPreview);
        synopsis_text = findViewById(R.id.synopsisTextPreview);

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