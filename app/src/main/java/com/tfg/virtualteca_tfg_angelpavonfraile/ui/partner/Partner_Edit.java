package com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tfg.virtualteca_tfg_angelpavonfraile.R;

public class Partner_Edit extends AppCompatActivity {

	private String dni, name, surname1, surname2, phone_number, email;
	private int partner_id;
	EditText dni_text, name_text, surname1_text, surname2_text, phone_number_text, email_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_add);
		
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
        phone_number_text = findViewById(R.id.phoneNumberTextEdit);
        email_text = findViewById(R.id.emailTextEdit);
		
		/* SAVE CHANGES DB */
        Button b_save = findViewById(R.id.saveButton);
        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
				
				boolean result = false;
                DataBasePartner dbp = new DataBasePartner(Partner_Add.this);
				dni = dni_text.getText().toString();
                name = name_text.getText().toString());
                surname1 = surname1_text.getText().toString();
                surname2 = surname2_text.getText().toString();
                phone_number = phone_number_text.getText().toString();
                email = email_text.getText().toString();
				
				if (checkEmpty()) {
                    result = dbp.editPartner(partner_id, dni, name, surname1, surname2, phone_numer, email);

                    if(result){
                        Toast.makeText(Book_Add.this, "REGISTRO AÑADIDO", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(Book_Add.this, "ERROR AL AÑADIR REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Book_Add.this, "DEBE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
	/* CHECKS IF THE FIELDS ARE EMPTY */
	private boolean checkEmpty(){
        return !dni.equals("") && name.equals("") && surname1.equals("") && surname2.equals("")
                && phone_number.equals("") && email.equals("");
    }
}
