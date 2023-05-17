package com.tfg.virtualteca_tfg_angelpavonfraile.elements;

public class Book {

    private int book_id;
    private String title;
    private int ISBN;
    private String language;
    private String genre;
    private String author;
    private String editorial;
    private String pbl_date;
    private String synopsis;


    public Book() {

    }

    public Book(String title, int ISBN,String genre, String author, String editorial,
    		   String pbl_date, String synopsis, String language) {
        this.title=title;
        this.ISBN=ISBN;
        this.language=language;
        this.genre=genre;
        this.author=author;
        this.editorial=editorial;
        this.pbl_date=pbl_date;
        this.synopsis=synopsis;
        
    }

    public int getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getPbl_date() {
        return pbl_date;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getLanguage() {
        return language;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setPbl_date(String pbl_date) {
        this.pbl_date = pbl_date;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
