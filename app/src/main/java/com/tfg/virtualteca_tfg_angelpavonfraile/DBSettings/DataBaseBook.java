package com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.AdapterView;

import androidx.annotation.Nullable;

import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;

import java.util.ArrayList;
public class DataBaseBook extends DataBaseSupport {

    Context context;

    public DataBaseBook(@Nullable Context context) {
        super(context);
        this.context = context;
    }

	/*
        Receives an id to delete a Book from the database
        also returns true if success
             returns false if failed
     */
    public boolean deleteBook(int id) {
		boolean result = false;
		SQLiteDatabase db = getWritableDatabase();
		try {
			int rowsDeleted = db.delete(TABLE_BOOKS, "id_book=?", new String[]{String.valueOf(id)});
			if (rowsDeleted != 0) {
				result = true;
			}
		} catch (Exception ex) {
			Log.e(TAG, "Error deleting book", ex);
		} finally {
			db.close();
		}
		return result;
	}



    /*
        Edits the Book from the database whose id is the one received as parameter
        Receives all the fields in case any of them was edited
        also returns true if success
             returns false if failed
     */
    public boolean editBook(int id, String title, int ISBN, String author, String language, String genre, String editorial, String pbl_date, String synopsis) {
		boolean result = false;
		SQLiteDatabase db = getWritableDatabase();
		try {
			ContentValues values = new ContentValues();
			values.put("title", title);
			values.put("ISBN", ISBN);
			values.put("author", author);
			values.put("language", language);
			values.put("genre", genre);
			values.put("editorial", editorial);
			values.put("pbl_date", pbl_date);
			values.put("synopsis", synopsis);
			int numRowsAffected = db.update(TABLE_BOOKS, values, "id_book=?", new String[]{String.valueOf(id)});
			result = numRowsAffected > 0;
		} catch (Exception ex) {
			Log.e(TAG, "Error editing book", ex);
		} finally {
			db.close();
		}
		return result;
	}


    /*
        Adds a Book to the database
        also returns the id if success
             returns 0 if failed
     */
    public long insertBook(String title, int ISBN, String author, String language, String genre, String editorial, String pbl_date, String synopsis) {
		long id = 0;
		SQLiteDatabase db = null;
		try {
			DataBaseSupport dbs = new DataBaseSupport(context);
			db = dbs.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put("title", title);
			values.put("ISBN", ISBN);
			values.put("author", author);
			values.put("language", language);
			values.put("genre", genre);
			values.put("editorial", editorial);
			values.put("pbl_date", pbl_date);
			values.put("synopsis", synopsis);

			id = db.insert(TABLE_BOOKS, null, values);
		} catch (SQLiteException ex) {
			Log.e(TAG, "Error inserting book", ex);
		} finally {
			db.close();
		}

		return id;
	}


    /*
        Returns Books' ArrayList from the database
     */
    public ArrayList<Book> bookList() {
		DataBaseSupport dbs = new DataBaseSupport(context);
		SQLiteDatabase db = dbs.getWritableDatabase();
		ArrayList<Book> bookList = new ArrayList<>();
		Book book;
		Cursor bookCursor = null;
		try {
			bookCursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS, null);
			if (bookCursor.moveToFirst()) {
				do {
					book = new Book();
					book.setBook_id(bookCursor.getInt(0));
					book.setTitle(bookCursor.getString(1));
					book.setISBN(bookCursor.getInt(2));
					book.setAuthor(bookCursor.getString(3));
					book.setLanguage(bookCursor.getString(4));
					book.setGenre(bookCursor.getString(5));
					book.setEditorial(bookCursor.getString(6));
					book.setPbl_date(bookCursor.getString(7));
					book.setSynopsis(bookCursor.getString(8));
					bookList.add(book);
				} while (bookCursor.moveToNext());
			}
		} catch (SQLiteException ex) {
			Log.e(TAG, "Error querying book list", ex);
		} finally {
			if (bookCursor != null) {
				bookCursor.close();
			}
		}
		return bookList;
	}


    /*
        Returns a Book from the database whose id is the one received as parameter
     */
    public Book getBookById(int id) {
		DataBaseSupport dbs = new DataBaseSupport(context);
		SQLiteDatabase db = dbs.getWritableDatabase();

		Book book = null;
		Cursor bookCursor = null;

		try {
			bookCursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS + " WHERE id_book = ? LIMIT 1", new String[] { String.valueOf(id) });

			if (bookCursor.moveToFirst()) {
				book = new Book();
				book.setBook_id(bookCursor.getInt(0));
				book.setTitle(bookCursor.getString(1));
				book.setISBN(bookCursor.getInt(2));
				book.setAuthor(bookCursor.getString(3));
				book.setLanguage(bookCursor.getString(4));
				book.setGenre(bookCursor.getString(5));
				book.setEditorial(bookCursor.getString(6));
				book.setPbl_date(bookCursor.getString(7));
				book.setSynopsis(bookCursor.getString(8));
			}
		} catch (SQLiteException ex) {
			Log.e(TAG, "Error querying book with id " + id, ex);
		} finally {
			if (bookCursor != null) {
				bookCursor.close();
			}
		}

		return book;
	}



    /*
    Returns all Books from the database whose title is the one received as parameter
 */
   public ArrayList<Book> bookView(String title) {
		DataBaseSupport dbs = new DataBaseSupport(context);
		SQLiteDatabase db = dbs.getWritableDatabase();

		ArrayList<Book> bookList = new ArrayList<>();
		Book book;
		Cursor bookCursor = null;

		try {
			String[] selectionArgs = new String[]{title};
			bookCursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS + " WHERE title = ?", selectionArgs);

			if (bookCursor.moveToFirst()) {
				do {
					book = new Book();
					book.setBook_id(bookCursor.getInt(0));
					book.setTitle(bookCursor.getString(1));
					book.setISBN(bookCursor.getInt(2));
					book.setAuthor(bookCursor.getString(3));
					book.setLanguage(bookCursor.getString(4));
					book.setGenre(bookCursor.getString(5));
					book.setEditorial(bookCursor.getString(6));
					book.setPbl_date(bookCursor.getString(7));
					book.setSynopsis(bookCursor.getString(8));
					bookList.add(book);
				} while (bookCursor.moveToNext());
			}
		} catch (SQLiteException ex) {
			Log.e(TAG, "Error querying books", ex);
		} finally {
			if (bookCursor != null) {
				bookCursor.close();
			}
		}

		return bookList;
	}


}
