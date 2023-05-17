package com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;

import java.util.ArrayList;
public class DataBasePartner extends DataBaseSupport {

    Context context;

    public DataBasePartner(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    /*
        Receives an id to delete a Partner from the database
        also returns true if success
             returns false if failed
     */
    public boolean deletePartner(int id) {

		boolean result = false;

		DataBaseSupport dbs = new DataBaseSupport(context);
		SQLiteDatabase db = dbs.getWritableDatabase();

		try {
			int rowsDeleted = db.delete(TABLE_PARTNERS, "id_partner = ?", new String[] { String.valueOf(id) });
			if (rowsDeleted != 0) {
				result = true;
			}
		} catch (Exception ex) {
			Log.e(TAG, "Error deleting partner", ex);
		} finally {
			db.close();
		}

		return result;
	}


    /*
        Edits the Partner from the database whose id is the one received as parameter
        Receives all the fields in case any of them was edited
        also returns true if success
             returns false if failed
     */
    public boolean editPartner(int id, String dni, String name, String surname1, String surname2, String phone_num, String email) {

		boolean result = false;

		DataBaseSupport dbs = new DataBaseSupport(context);
		SQLiteDatabase db = dbs.getWritableDatabase();

		try {
			ContentValues values = new ContentValues();
			values.put("dni", dni);
			values.put("name", name);
			values.put("surname1", surname1);
			values.put("surname2", surname2);
			values.put("phone_number", phone_num);
			values.put("email", email);

			int numRowsAffected = db.update(TABLE_PARTNERS, values, "id_partner = ?", new String[] { String.valueOf(id) });
			result = numRowsAffected > 0;
		} catch (Exception ex) {
			Log.e(TAG, "Error editing partner", ex);
		} finally {
			db.close();
		}

		return result;
	}


    /*
        Adds a Partner to the database
        also returns the id if success
             returns 0 if failed
     */
    public long insertPartner(String dni, String name, String surname1, String surname2, String phone_num, String email) {
		long id = 0;
		SQLiteDatabase db = null;
		try {
			DataBaseSupport dbs = new DataBaseSupport(context);
			db = dbs.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put("dni", dni);
			values.put("name", name);
			values.put("surname1", surname1);
			values.put("surname2", surname2);
			values.put("phone_number", phone_num);
			values.put("email", email);

			id = db.insert(TABLE_PARTNERS, null, values);
		} catch (SQLiteException ex) {
			Log.e(TAG, "Error inserting partner", ex);
		} finally {
			db.close();
		}

		return id;
	}


    /*
        Returns Partners' ArrayList from the database
     */
    public ArrayList<Partner> partnerList() {
		DataBaseSupport dbs = new DataBaseSupport(context);
		SQLiteDatabase db = dbs.getWritableDatabase();

		ArrayList<Partner> partnerList = new ArrayList<>();
		Partner partner;
		Cursor partnerCursor = null;

		try {
			partnerCursor = db.rawQuery("SELECT * FROM " + TABLE_PARTNERS, null);

			if (partnerCursor.moveToFirst()) {
				do {
					partner = new Partner();
					partner.setPartner_id(partnerCursor.getInt(0));
					partner.setDni(partnerCursor.getString(1));
					partner.setName(partnerCursor.getString(2));
					partner.setSurname1(partnerCursor.getString(3));
					partner.setSurname2(partnerCursor.getString(4));
					partner.setPhone_number(partnerCursor.getString(5));
					partner.setEmail(partnerCursor.getString(6));
					partnerList.add(partner);
				} while (partnerCursor.moveToNext());
			}
		} catch (SQLiteException ex) {
			Log.e(TAG, "Error querying partner list", ex);
		} finally {
			if (partnerCursor != null) {
				partnerCursor.close();
			}
		}

		return partnerList;
	}


    /*
        Returns a Partner from the database whose id is the one received as parameter
     */
    public Partner getPartnerById(int id) {
		DataBaseSupport dbs = new DataBaseSupport(context);
		SQLiteDatabase db = dbs.getWritableDatabase();

		Partner partner = null;
		Cursor partnerCursor = null;

		try {
			partnerCursor = db.rawQuery("SELECT * FROM " + TABLE_PARTNERS + " WHERE id_partner = ? LIMIT 1", new String[] { String.valueOf(id) });

			if (partnerCursor.moveToFirst()) {
				partner = new Partner();
				partner.setPartner_id(partnerCursor.getInt(0));
				partner.setDni(partnerCursor.getString(1));
				partner.setName(partnerCursor.getString(2));
				partner.setSurname1(partnerCursor.getString(3));
				partner.setSurname2(partnerCursor.getString(4));
				partner.setPhone_number(partnerCursor.getString(5));
				partner.setEmail(partnerCursor.getString(6));
			}
		} catch (SQLiteException ex) {
			Log.e(TAG, "Error querying partner with id " + id, ex);
		} finally {
			if (partnerCursor != null) {
				partnerCursor.close();
			}
		}

		return partner;
	}


    /*
    Returns all Partners from the database whose title is the one received as parameter
 */
   public ArrayList<Partner> partnerView(String name) {
		DataBaseSupport dbs = new DataBaseSupport(context);
		SQLiteDatabase db = dbs.getWritableDatabase();

		ArrayList<Partner> partnerList = new ArrayList<>();
		Partner partner;
		Cursor partnerCursor = null;

		try {
			String[] selectionArgs = new String[]{name};
			partnerCursor = db.rawQuery("SELECT * FROM " + TABLE_PARTNERS + " WHERE name = ?", selectionArgs);

			if (partnerCursor.moveToFirst()) {
				do {
					partner = new Partner();
					partner.setPartner_id(partnerCursor.getInt(0));
					partner.setDni(partnerCursor.getString(1));
					partner.setName(partnerCursor.getString(2));
					partner.setSurname1(partnerCursor.getString(3));
					partner.setSurname2(partnerCursor.getString(4));
					partner.setPhone_number(partnerCursor.getString(5));
					partner.setEmail(partnerCursor.getString(6));
					partnerList.add(partner);
				} while (partnerCursor.moveToNext());
			}
		} catch (SQLiteException ex) {
			Log.e(TAG, "Error querying partners", ex);
		} finally {
			if (partnerCursor != null) {
				partnerCursor.close();
			}
		}

		return partnerList;
	}

}
