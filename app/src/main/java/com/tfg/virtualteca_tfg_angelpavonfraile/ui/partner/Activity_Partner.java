package com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.book.Activity_Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan.Activity_Loan;

public class Activity_Partner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner);


        //Moving buttons
        Button b_book = findViewById(R.id.book_button);
        b_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_Partner.this, Activity_Book.class);
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
        Button b_loan = findViewById(R.id.loan_button);
        b_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_Partner.this, Activity_Loan.class);
                startActivity(intent);
                finish();
            }
        });


        Button b_add = findViewById(R.id.b_add2);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_Partner.this, Partner_Add.class);
                startActivity(intent);
            }
        });
    }
}