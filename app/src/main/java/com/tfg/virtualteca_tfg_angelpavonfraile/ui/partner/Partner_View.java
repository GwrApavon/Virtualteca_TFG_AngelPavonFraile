package com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;

public class Partner_View extends AppCompatActivity {
	
	TextView dni_text, name_text, surname1_text, surname2_text, phone_number_text, email_text;

    int partner_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_view);



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

        fillTextView(partner_id);

        //EDIT PARTNER
        Button b_edit = findViewById(R.id.editButton2);
        b_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Partner_View.this, Partner_Edit.class);
                intent.putExtra("ID", partner_id);
                startActivity(intent);
            }
        });

        //DELETE PARTNER
        Button b_delete = findViewById(R.id.deleteButton2);
        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBasePartner dbp = new DataBasePartner(Partner_View.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(Partner_View.this);
                builder.setMessage("Está a punto de eliminar un socio. ¿Desea continuar?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean deleted = dbp.deletePartner(partner_id);

                                if(deleted){
                                    finish();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        });
    }

    //fills de text fields in the activity
	public void fillTextView(int partner_id) {
        Partner partner;
        String dni, name, surname1, surname2, phone_number, email;

        DataBasePartner dbp = new DataBasePartner(Partner_View.this);

        dni_text = findViewById(R.id.dniTextPreView);
        name_text = findViewById(R.id.nameTextPreView);
        surname1_text = findViewById(R.id.surname1TextPreView);
        surname2_text = findViewById(R.id.surname2TextPreView);
        phone_number_text = findViewById(R.id.phoneNumTextPreView);
        email_text = findViewById(R.id.emailTextPreView);

        partner = dbp.getPartnerById(partner_id);

        if (partner != null) {
            dni = "DNI:    " + partner.getDni();
            dni_text.setText(dni);
            name = "Nombre:    " + partner.getName();
            name_text.setText(name);
            surname1 = "Primer Apellido:    " + partner.getSurname1();
            surname1_text.setText(surname1);
            surname2 = "Segundo Apellido:    " + partner.getSurname2();
            surname2_text.setText(surname2);
            phone_number ="Telefono:    " +partner.getPhone_number();
            phone_number_text.setText(phone_number);
            email = "Email:" +partner.getEmail();
            email_text.setText(email);
        }
    }

    public void onResume() {
        super.onResume();
        fillTextView(partner_id);
    }

}
