package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseLoan;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.Utilities;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;

public class Loan_Add extends AppCompatActivity {

    private boolean returned = false;
    int id_partner;
    int id_book;
    String init_date,fin_date;
    EditText init_dateText, fin_dateText;
    private ActivityResultLauncher<Intent> launcher, launcher2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_add);

        init_dateText = findViewById(R.id.init_dateText);
        fin_dateText = findViewById(R.id.fin_dateText);

        /* RECEIVES THE RESULT FROM THE ACTIVITY */
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            id_book = data.getIntExtra("BOOK_ID", 0);
                            DataBaseBook dbb = new DataBaseBook(Loan_Add.this);
                            Book book = dbb.getBookById(id_book);
                            TextView bptv = findViewById(R.id.bookPickerText);
                            bptv.setText(book.getTitle());
                        }
                    }
                });

        launcher2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            id_partner = data.getIntExtra("PARTNER_ID", 0);
                            DataBasePartner dbp = new DataBasePartner(Loan_Add.this);
                            Partner partner = dbp.getPartnerById(id_partner);
                            TextView pptv = findViewById(R.id.partnerPickerText);
                            String full_name = partner.getName() + " " + partner.getSurname1() + " " + partner.getSurname2();
                            pptv.setText(full_name);
                        }
                    }
                });

        /* BOOK SELECTOR */
        Button bookPicker = findViewById(R.id.bookPicker);
        bookPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Loan_Add.this, BookPicker.class);
                intent.putExtra("FROM", "LoanAdd");
                launcher.launch(intent);
            }
        });

        /* PARTNER SELECTOR */
        Button partnerPicker = findViewById(R.id.partnerPicker);
        partnerPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Loan_Add.this, PartnerPicker.class);
                intent.putExtra("FROM", "LoanAdd");
                launcher2.launch(intent);
            }
        });

        /* ADD LOAN */
        Button b_add_loan = findViewById(R.id.saveButton3);
        b_add_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long result = 0;
                DataBaseLoan dbl = new DataBaseLoan(Loan_Add.this);
                init_date = init_dateText.getText().toString();
                fin_date = fin_dateText.getText().toString();

                if (!isEmpty()) { //CHECK EMPTY
                    if(Utilities.validateDateFormat(init_date)) { //CHECK INIT FORMAT
                        if(Utilities.validateDateFormat(fin_date)) { //CHECK FIN FORMAT
                            result = dbl.insertLoan(id_partner, id_book, init_date, fin_date, returned);

                            if (result != 0) { //Correct insert
                                Toast.makeText(Loan_Add.this, "REGISTRO AÑADIDO", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(Loan_Add.this, "ERROR AL AÑADIR REGISTRO", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Loan_Add.this, "ERROR CON EL FORMATO DE LA FECHA DE FINALIZACION", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Loan_Add.this, "ERROR CON EL FORMATO DE LA FECHA INICIAL", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Loan_Add.this, "DEBE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //Checks if the fields are empty or not
    private boolean isEmpty(){
        return init_date.equals("") && fin_date.equals("") && id_book == 0 && id_partner == 0;
    }
}
