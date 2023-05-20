package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner.Activity_Partner;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner.Partner_Add;

public class Loan_add extends AppCompatActivity {

    private static final int REQUEST_CODE_BOOK_PICKER = 1;
    private static final int REQUEST_CODE_PARTNER_PICKER = 2;
    private boolean returned = false;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_add);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            int book_id = data.getIntExtra("ID", 0);
                            // Aquí puedes usar el resultado devuelto como desees
                        }
                    }
                });

        Button bookPicker = findViewById(R.id.bookPicker);
        bookPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Loan_add.this, BookPicker.class);
                launcher.launch(intent);
            }
        });

        Button partnerPicker = findViewById(R.id.partnerPicker);
        partnerPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Loan_add.this, PartnerPicker.class);
                launcher.launch(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_BOOK_PICKER) {
            if (resultCode == Activity.RESULT_OK) {
                int bookId = data.getIntExtra("BOOK_ID", 0);
                // Realiza las operaciones necesarias con el ID del libro
            }
        } else if (requestCode == REQUEST_CODE_PARTNER_PICKER) {
            if (resultCode == Activity.RESULT_OK) {
                int partnerId = data.getIntExtra("PARTNER_ID", 0);
                // Realiza las operaciones necesarias con el ID del socio
            }
        }
    }
}
