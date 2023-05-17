package com.tfg.virtualteca_tfg_angelpavonfraile.elements;

public class Partner{
    private int partner_id;
    private String dni;
    private String name;
    private String surname1;
    private String surname2;
    private String phone_number;
    private String email;


    public Partner() {

    }

    public Partner( String dni,String name, String surname1, String surname2, String phone_num, String email) {
        this.dni=dni;
        this.name=name;
        this.surname1=surname1;
        this.surname2=surname2;
        this.phone_number=phone_num;
        this.email=email;
    }

    public int getPartner_id() {
        return partner_id;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getSurname1() {
        return surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setPartner_id(int partner_id) {
        this.partner_id = partner_id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}