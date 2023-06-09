package com.tfg.virtualteca_tfg_angelpavonfraile.elements;

import android.content.Context;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;

public class Loan {
    private int loan_id;
    private int partner_id;

    private Partner partner;
    private int book_id;
    private Book book;
    private String init_date;
    private String fin_date;
    private boolean returned;

    public Loan() {
    }

    public Loan(int partner_id, int book_id, String init_date, String fin_date, boolean returned) {
        this.partner_id = partner_id;
        this.book_id = book_id;
        this.init_date = init_date;
        this.fin_date = fin_date;
        this.returned = returned;
    }

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public int getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(int partner_id) {
        this.partner_id = partner_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getInit_date() {
        return init_date;
    }

    public void setInit_date(String init_date) {
        this.init_date = init_date;
    }

    public String getFin_date() {
        return fin_date;
    }

    public void setFin_date(String fin_date) {
        this.fin_date = fin_date;
    }

    public boolean getReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }


    public Partner getPartner(Context context) {

        DataBasePartner dbp = new DataBasePartner(context);
        partner = dbp.getPartnerById(partner_id);
        return partner;
    }

    public Book getBook(Context context) {
        DataBaseBook dbb = new DataBaseBook(context);
        book = dbb.getBookById(book_id);
        return book;
    }

}
