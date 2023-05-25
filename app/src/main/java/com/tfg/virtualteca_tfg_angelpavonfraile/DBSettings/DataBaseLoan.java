package com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Loan;

import java.util.ArrayList;
public class DataBaseLoan extends DataBaseSupport {

    Context context;

    public DataBaseLoan(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    /*
        Receives an id to delete a Loan from the database
        also returns true if success
             returns false if failed
     */
    public boolean deleteLoan(int id) {
        boolean result = false;
        SQLiteDatabase db = getWritableDatabase();
        try {
            int numRowsAffected = db.delete(TABLE_LOANS, "id_loan=?", new String[]{String.valueOf(id)});
            result = numRowsAffected > 0;
        } catch (Exception ex) {
            Log.e(TAG, "Error deleting loan", ex);
        } finally {
            Log.e(TAG, "Successfully Deleted");
            db.close();
        }
        return result;
    }


    /*
        Edits the Loan from the database whose id is the one received as parameter
        Receives all the fields in case any of them was edited
        also returns true if success
             returns false if failed
     */
    public boolean editLoan(int id, int id_partner, int id_book, String init_date, String fin_date, boolean returned) {
        boolean result = false;
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("id_partner", id_partner);
            values.put("id_book", id_book);
            values.put("init_date", init_date);
            values.put("fin_date", fin_date);
            values.put("returned", returned ? 1 : 0);
            int numRowsAffected = db.update(TABLE_LOANS, values, "id_loan=?", new String[]{String.valueOf(id)});
            result = numRowsAffected > 0;
        } catch (Exception ex) {
            Log.e(TAG, "Error editing loan", ex);
        } finally {
            Log.e(TAG, "Successfully Edited");
            db.close();
        }
        return result;
    }

    public boolean editLoan(int id, boolean returned) {
        boolean result = false;
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("returned", returned ? 1 : 0);
            int numRowsAffected = db.update(TABLE_LOANS, values, "id_loan=?", new String[]{String.valueOf(id)});
            result = numRowsAffected > 0;
        } catch (Exception ex) {
            Log.e(TAG, "Error editing loan", ex);
        } finally {
            Log.e(TAG, "Successfully Edited");
            db.close();
        }
        return result;
    }


    /*
        Adds a Loan to the database
        also returns the id if success
             returns 0 if failed
     */
    public long insertLoan(int id_partner, int id_book, String init_date, String fin_date, boolean returned) {
        long id = 0;
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("id_partner", id_partner);
            values.put("id_book", id_book);
            values.put("init_date", init_date);
            values.put("fin_date", fin_date);
            values.put("returned", returned ? 1 : 0);

            id = db.insert(TABLE_LOANS, null, values);
        } catch (SQLiteException ex) {
            Log.e(TAG, "Error inserting loan", ex);
        } finally {
            Log.e(TAG, "Successfully Inserted");
            db.close();
        }
        return id;
    }


    /*
        Returns Loans' ArrayList from the database
     */
    public ArrayList<Loan> loanList() {
        DataBaseSupport dbs = new DataBaseSupport(context);
        SQLiteDatabase db = dbs.getWritableDatabase();
        ArrayList<Loan> loanList = new ArrayList<>();
        Loan loan;
        Cursor loanCursor = null;
        try {
            loanCursor = db.rawQuery("SELECT * FROM " + TABLE_LOANS, null);
            if (loanCursor.moveToFirst()) {
                do {
                    loan = new Loan();
                    loan.setLoan_id(loanCursor.getInt(0));
                    loan.setPartner_id(loanCursor.getInt(1));
                    loan.setBook_id(loanCursor.getInt(2));
                    loan.setInit_date(loanCursor.getString(3));
                    loan.setFin_date(loanCursor.getString(4));
                    loan.setReturned(loanCursor.getInt(5) > 0);
                    loanList.add(loan);
                } while (loanCursor.moveToNext());
            }
        } catch (SQLiteException ex) {
            Log.e(TAG, "Error querying loan list", ex);
        } finally {
            if (loanCursor != null) {
                loanCursor.close();
            }
        }
        return loanList;
    }

    /*
        Returns a Loan from the database whose id is the one received as parameter
     */
    public Loan getLoanById(int id) {
        DataBaseSupport dbs = new DataBaseSupport(context);
        SQLiteDatabase db = dbs.getWritableDatabase();

        Loan loan = null;
        Cursor loanCursor = null;

        try {
            loanCursor = db.rawQuery("SELECT * FROM " + TABLE_LOANS + " WHERE id_loan = ? LIMIT 1", new String[]{String.valueOf(id)});

            if (loanCursor.moveToFirst()) {
                loan = new Loan();
                loan.setLoan_id(loanCursor.getInt(0));
                loan.setPartner_id(loanCursor.getInt(1));
                loan.setBook_id(loanCursor.getInt(2));
                loan.setInit_date(loanCursor.getString(3));
                loan.setFin_date(loanCursor.getString(4));
                loan.setReturned(loanCursor.getInt(5) == 1);
            }
        } catch (SQLiteException ex) {
            Log.e(TAG, "Error querying loan with id " + id, ex);
        } finally {
            if (loanCursor != null) {
                loanCursor.close();
            }
        }

        return loan;
    }


    /*
    Returns all Loans a partner has from the database using the partner's id received as parameter
	*/
    public ArrayList<Loan> loanListByPartner(int partner_id) {
        DataBaseSupport dbs = new DataBaseSupport(context);
        SQLiteDatabase db = dbs.getWritableDatabase();

        ArrayList<Loan> loanList = new ArrayList<>();
        Loan loan;
        Cursor loanCursor = null;

        try {
            String[] selectionArgs = new String[]{String.valueOf(partner_id)};
            loanCursor = db.rawQuery("SELECT * FROM " + TABLE_LOANS + " WHERE id_partner = ?", selectionArgs);

            if (loanCursor.moveToFirst()) {
                do {
                    loan = new Loan();
                    loan.setLoan_id(loanCursor.getInt(0));
                    loan.setPartner_id(loanCursor.getInt(1));
                    loan.setBook_id(loanCursor.getInt(2));
                    loan.setInit_date(loanCursor.getString(3));
                    loan.setFin_date(loanCursor.getString(4));
                    loan.setReturned(loanCursor.getInt(5) == 1);
                    loanList.add(loan);
                } while (loanCursor.moveToNext());
            }
        } catch (SQLiteException ex) {
            Log.e(TAG, "Error querying loans by partner id " + partner_id, ex);
        } finally {
            if (loanCursor != null) {
                loanCursor.close();
            }
        }

        return loanList;
    }

    /*
    Returns all Loans were made for a book from the database using the book's id received as parameter
    */
    public ArrayList<Loan> loanListByBook(int book_id) {
        DataBaseSupport dbs = new DataBaseSupport(context);
        SQLiteDatabase db = dbs.getWritableDatabase();

        ArrayList<Loan> loanList = new ArrayList<>();
        Loan loan;
        Cursor loanCursor = null;

        try {
            String[] selectionArgs = new String[]{String.valueOf(book_id)};
            loanCursor = db.rawQuery("SELECT * FROM " + TABLE_LOANS + " WHERE id_book = ? ORDER BY init_date DESC", selectionArgs);
            if (loanCursor.moveToFirst()) {
                do {
                    loan = new Loan();
                    loan.setLoan_id(loanCursor.getInt(0));
                    loan.setPartner_id(loanCursor.getInt(1));
                    loan.setPartner_id(loanCursor.getInt(2));
                    loan.setInit_date(loanCursor.getString(3));
                    loan.setFin_date(loanCursor.getString(4));
                    loan.setReturned(loanCursor.getInt(5) > 0);
                    loanList.add(loan);
                } while (loanCursor.moveToNext());
            }
        } catch (SQLiteException ex) {
            Log.e(TAG, "Error querying loan list by book", ex);
        } finally {
            if (loanCursor != null) {
                loanCursor.close();
            }
        }
        return loanList;
    }
}