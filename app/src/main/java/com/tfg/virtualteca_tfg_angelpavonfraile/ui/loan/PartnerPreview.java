package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner.Partner_View;

public class PartnerPreview extends AppCompatActivity {
    TextView dni_textPreview, name_textPreview, surname1_textPreview, surname2_textPreview, phone_number_textPreview, email_textPreview;

    int partner_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_preview);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null)
            {
                partner_id = Integer.parseInt(null);
            }
            else
            {
                partner_id = extras.getInt("ID");
            }
        }
        else
        {
            partner_id = (int) savedInstanceState.getSerializable("ID");
        }
        Log.e(TAG, "partner id:" + partner_id);

        fillTextView(partner_id);
    }

    public void fillTextView(int partner_id) {
        Partner partner;
        String dni, name, surname1, surname2, phone_number, email;

        DataBasePartner dbp = new DataBasePartner(PartnerPreview.this);

        dni_textPreview = findViewById(R.id.dniTextPreView);
        name_textPreview = findViewById(R.id.nameTextPreView);
        surname1_textPreview = findViewById(R.id.surname1TextPreView);
        surname2_textPreview = findViewById(R.id.surname2TextPreView);
        phone_number_textPreview = findViewById(R.id.phoneNumTextPreView);
        email_textPreview = findViewById(R.id.emailTextPreView);

        partner = dbp.getPartnerById(partner_id);

        if (partner != null) {
            dni = "DNI:    " + partner.getDni();
            dni_textPreview.setText(dni);
            name = "Nombre:    " + partner.getName();
            name_textPreview.setText(name);
            surname1 = "Primer Apellido:    " + partner.getSurname1();
            surname1_textPreview.setText(surname1);
            surname2 = "Segundo Apellido:    " + partner.getSurname2();
            surname2_textPreview.setText(surname2);
            phone_number ="Telefono:    " +partner.getPhone_number();
            phone_number_textPreview.setText(phone_number);
            email = "Email:\n" +partner.getEmail();
            email_textPreview.setText(email);
        }
    }
}