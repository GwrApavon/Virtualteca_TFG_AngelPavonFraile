package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseLoan;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Loan;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner.Partner_Edit;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner.Partner_View;

public class Loan_View extends AppCompatActivity {

    TextView bookPickerTextView, partnerPickerTextView , init_dateTextView, fin_dateTextView;
    int loan_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_view);

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
        Log.e(TAG, "loan id:" + loan_id);
        fillTextView(loan_id);

        //EDIT PARTNER
        Button b_edit = findViewById(R.id.editButton3);
        b_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Loan_View.this, Loan_Edit.class);
                intent.putExtra("ID", loan_id);
                startActivity(intent);
            }
        });

        //DELETE PARTNER
        Button b_delete = findViewById(R.id.deleteButton3);
        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseLoan dbl = new DataBaseLoan(Loan_View.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(Loan_View.this);
                builder.setMessage("Está a punto de eliminar un socio. ¿Desea continuar?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean deleted = dbl.deleteLoan(loan_id);

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
    public void fillTextView(int loan_id) {

        Loan loan;
        Partner partner;
        Book book;

        DataBaseLoan dbl = new DataBaseLoan(Loan_View.this);
        DataBaseBook dbb = new DataBaseBook(Loan_View.this);
        DataBasePartner dbp = new DataBasePartner(Loan_View.this);

        bookPickerTextView = findViewById(R.id.bookPickerTextView);
        partnerPickerTextView = findViewById(R.id.partnerPickerTextView);
        init_dateTextView = findViewById(R.id.init_dateTextView);
        fin_dateTextView = findViewById(R.id.fin_dateTextView);

        loan = dbl.getLoanById(loan_id);

        if (loan != null) {
            int book_id = loan.getBook_id();
            int partner_id = loan.getPartner_id();

            book = dbb.getBookById(book_id);
            partner = dbp.getPartnerById(partner_id);

            if (book != null){
                String title = book.getTitle();
                bookPickerTextView.setText(title);
            }

            if (partner != null){
                String full_name = partner.getName() + " " + partner.getSurname1() + " " + partner.getSurname2();
                partnerPickerTextView.setText(full_name);
            }

            String init_date = loan.getInit_date();
            init_dateTextView.setText(init_date);
            String fin_date = loan.getFin_date();
            fin_dateTextView.setText(fin_date);
        }
    }
}