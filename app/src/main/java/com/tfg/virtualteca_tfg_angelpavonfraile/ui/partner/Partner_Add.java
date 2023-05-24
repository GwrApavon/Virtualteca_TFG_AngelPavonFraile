package com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;

public class Partner_Add extends AppCompatActivity {
	
	private String dni, name, surname1, surname2, phone_number, email;
	EditText dni_text, name_text, surname1_text, surname2_text, phone_number_text, email_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_add);
		
		dni_text = findViewById(R.id.dniText);
        name_text = findViewById(R.id.nameText);
        surname1_text = findViewById(R.id.surname1Text);
        surname2_text = findViewById(R.id.surname2Text);
        phone_number_text = findViewById(R.id.phoneNumText);
        email_text = findViewById(R.id.emailText);
		
		/* INSERT NEW PARTNER INTO DB */
        Button b_add_partner = findViewById(R.id.saveButton2);
        b_add_partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
				
				long result = 0;
                DataBasePartner dbp = new DataBasePartner(Partner_Add.this);
				dni = dni_text.getText().toString();
                name = name_text.getText().toString();
                surname1 = surname1_text.getText().toString();
                surname2 = surname2_text.getText().toString();
                phone_number = phone_number_text.getText().toString();
                email = email_text.getText().toString();
				
				if (!isEmpty()) {
                    result = dbp.insertPartner(dni, name, surname1, surname2, phone_number, email);

                    if(result != 0 ){
                        Toast.makeText(Partner_Add.this, "REGISTRO AÑADIDO", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(Partner_Add.this, "ERROR AL AÑADIR REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Partner_Add.this, "DEBE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /* CHECK FIELDS NOT EMPTY */
	private boolean isEmpty(){
        return dni.equals("") && name.equals("") && surname1.equals("") && surname2.equals("")
                && phone_number.equals("") && email.equals("");
    }
}
