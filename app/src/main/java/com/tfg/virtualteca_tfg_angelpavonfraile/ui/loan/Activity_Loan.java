package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.book.Activity_Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner.Activity_Partner;

public class Activity_Loan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);



        //Moving buttons
        Button b_book = findViewById(R.id.book_button);
        b_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_Loan.this, Activity_Book.class);
                startActivity(intent);
                finish();
            }
        });
        Button b_home = findViewById(R.id.home_button);
        b_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button b_partner = findViewById(R.id.partner_button);
        b_partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_Loan.this, Activity_Partner.class);
                startActivity(intent);
                finish();
            }
        });

        android.widget.Button b_add = findViewById(R.id.b_add3);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_Loan.this, Loan_add.class);
                startActivity(intent);
            }
        });
    }
}