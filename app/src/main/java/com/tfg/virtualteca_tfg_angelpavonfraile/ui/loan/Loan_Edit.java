package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Loan;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;

public class Loan_Edit extends AppCompatActivity {

    int loan_id;
    int partner_id;
    int book_id;
    String init_date,fin_date,title, full_name;
    TextView bookPickerTextEdit, partnerPickerTextEdit ;
    EditText init_dateTextEdit, fin_dateTextEdit;
    private ActivityResultLauncher<Intent> launcher, launcher2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_edit);


        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null)
            {
                loan_id = Integer.parseInt(null);
            }
            else
            {
                loan_id = extras.getInt("ID");
            }
        }
        else
        {
            loan_id = (int) savedInstanceState.getSerializable("ID");
        }

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            book_id = data.getIntExtra("BOOK_ID", 0);
                            Log.e(TAG, "book picker edit:" + book_id);
                            Book book = getBookById(book_id);
                            TextView bptv = findViewById(R.id.bookPickerTextEdit);
                            title = book.getTitle();
                            bptv.setText(title);
                        }
                    }
                });

        launcher2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            partner_id = data.getIntExtra("PARTNER_ID", 0);
                            Partner partner = getPartnerById(partner_id);
                            TextView pptv = findViewById(R.id.partnerPickerTextEdit);
                            full_name = partner.getName() + " " + partner.getSurname1() + " " + partner.getSurname2();
                            pptv.setText(full_name);
                        }
                    }
                });

        fillTextView(loan_id);

        Button bookPicker = findViewById(R.id.bookPickerEdit);
        bookPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Loan_Edit.this, BookPicker.class);
                intent.putExtra("FROM", "LoanEdit");
                launcher.launch(intent);
            }
        });

        Button partnerPicker = findViewById(R.id.partnerPickerEdit);
        partnerPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Loan_Edit.this, PartnerPicker.class);
                intent.putExtra("FROM", "LoanEdit");
                launcher2.launch(intent);
            }
        });

        Button b_edit_loan = findViewById(R.id.saveButton3);
        b_edit_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = false;
                DataBaseLoan dbl = new DataBaseLoan(Loan_Edit.this);
                Loan loan = dbl.getLoanById(loan_id);
                init_date = init_dateTextEdit.getText().toString();
                fin_date = fin_dateTextEdit.getText().toString();
                boolean returned = loan.getReturned();
                if (!isEmpty()) {
                    if(Utilities.validateDateFormat(init_date)) { //CHECK INIT FORMAT
                        if(Utilities.validateDateFormat(fin_date)) { //CHECK FIN FORMAT
                            result = dbl.editLoan(loan_id, partner_id, book_id, init_date, fin_date, returned);

                            if (result) {
                                Toast.makeText(Loan_Edit.this, "REGISTRO EDITADO", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(Loan_Edit.this, "ERROR AL EDITAR REGISTRO", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Loan_Edit.this, "ERROR CON EL FORMATO DE LA FECHA DE FINALIZACION", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Loan_Edit.this, "ERROR CON EL FORMATO DE LA FECHA DE INICIO", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Loan_Edit.this, "DEBE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Returns the partner
    private Partner getPartnerById(int partner_id){
        DataBasePartner dbp = new DataBasePartner(Loan_Edit.this);
        Partner partner = dbp.getPartnerById(partner_id);

        return partner;
    }

    //Returns the book
    private Book getBookById(int book_id){
        DataBaseBook dbb = new DataBaseBook(Loan_Edit.this);
        Book book = dbb.getBookById(book_id);

        return book;
    }
    //fills de text fields in the activity
    public void fillTextView(int loan_id) {

        Loan loan;
        Partner partner;
        Book book;

        DataBaseLoan dbl = new DataBaseLoan(Loan_Edit.this);
        DataBaseBook dbb = new DataBaseBook(Loan_Edit.this);
        DataBasePartner dbp = new DataBasePartner(Loan_Edit.this);

        bookPickerTextEdit = findViewById(R.id.bookPickerTextEdit);
        partnerPickerTextEdit = findViewById(R.id.partnerPickerTextEdit);
        init_dateTextEdit = findViewById(R.id.init_dateTextEdit);
        fin_dateTextEdit = findViewById(R.id.fin_dateTextEdit);

        loan = dbl.getLoanById(loan_id);

        if (loan != null) {
            book_id = loan.getBook_id();
            partner_id = loan.getPartner_id();

            book = dbb.getBookById(book_id);
            partner = dbp.getPartnerById(partner_id);

            if (book != null){
                title = book.getTitle();
                bookPickerTextEdit.setText(title);
            }

            if (partner != null){
                full_name = partner.getName() + " " + partner.getSurname1() + " " + partner.getSurname2();
                partnerPickerTextEdit.setText(full_name);
            }

            init_date = loan.getInit_date();
            init_dateTextEdit.setText(init_date);
            fin_date = loan.getFin_date();
            fin_dateTextEdit.setText(fin_date);
        }
    }

    //Checks if the fields are empty or not
    private boolean isEmpty(){
        return init_date.equals("") && fin_date.equals("") && book_id == 0 && partner_id == 0;
    }
}