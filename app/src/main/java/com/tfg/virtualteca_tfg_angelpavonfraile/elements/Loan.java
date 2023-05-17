package com.tfg.virtualteca_tfg_angelpavonfraile.elements;

public class Loan {
    private int loan_id;
    private int partner_id;
    private int book_id;
    private String init_date;
    private String fin_date;
    private boolean devuelto;

    public Loan() {
    }

    public Loan(int partner_id, int book_id, String init_date, String fin_date, boolean devuelto) {
        this.partner_id = partner_id;
        this.book_id = book_id;
        this.init_date = init_date;
        this.fin_date = fin_date;
        this.devuelto = devuelto;
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

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

}
