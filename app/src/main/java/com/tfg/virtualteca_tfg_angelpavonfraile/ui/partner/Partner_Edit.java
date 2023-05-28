package com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.Utilities;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.BookAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;

public class Partner_Edit extends AppCompatActivity {

	private String dni, name, surname1, surname2, phone_number, email;
	private int partner_id;
	EditText dni_text, name_text, surname1_text, surname2_text, phone_number_text, email_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_edit);
		
	/* receives the id from the view activity */
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

		
	    dni_text = findViewById(R.id.dniTextEdit);
        name_text = findViewById(R.id.nameTextEdit);
        surname1_text = findViewById(R.id.surname1TextEdit);
        surname2_text = findViewById(R.id.surname2TextEdit);
        phone_number_text = findViewById(R.id.phoneNumTextEdit);
        email_text = findViewById(R.id.emailTextEdit);

        fillTextView(partner_id);
		/* SAVE CHANGES DB */
        Button b_save = findViewById(R.id.saveButton2);
        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
				
				boolean result = false;
                DataBasePartner dbp = new DataBasePartner(Partner_Edit.this);
				dni = dni_text.getText().toString();
                name = name_text.getText().toString();
                surname1 = surname1_text.getText().toString();
                surname2 = surname2_text.getText().toString();
                phone_number = phone_number_text.getText().toString();
                email = email_text.getText().toString();
				
				if (!isEmpty()) {
                    if (Utilities.validateDni(dni)) {
                        result = dbp.editPartner(partner_id, dni, name, surname1, surname2, phone_number, email);

                        if (result) {
                            Toast.makeText(Partner_Edit.this, "REGISTRO EDITADO", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Partner_Edit.this, "ERROR AL EDITAR REGISTRO", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Partner_Edit.this, "EL DNI NO ES VALIDO", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Partner_Edit.this, "DEBE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
	/* CHECKS IF THE FIELDS ARE EMPTY */
	private boolean isEmpty(){
        return dni.equals("") && name.equals("") && surname1.equals("") && surname2.equals("")
                && phone_number.equals("") && email.equals("");
    }

    //refresh partner fields
    public void onResume() {
        super.onResume();
        fillTextView(partner_id);
    }

    //fills de text fields in the activity
    public void fillTextView(int partner_id) {
        Partner partner;
        String dni, name, surname1, surname2, phone_number, email;

        DataBasePartner dbp = new DataBasePartner(Partner_Edit.this);


        partner = dbp.getPartnerById(partner_id);

        if (partner != null) {
            dni = partner.getDni();
            dni_text.setText(dni);
            name = partner.getName();
            name_text.setText(name);
            surname1 = partner.getSurname1();
            surname1_text.setText(surname1);
            surname2 = partner.getSurname2();
            surname2_text.setText(surname2);
            phone_number = partner.getPhone_number();
            phone_number_text.setText(phone_number);
            email = partner.getEmail();
            email_text.setText(email);
        }
    }
}
