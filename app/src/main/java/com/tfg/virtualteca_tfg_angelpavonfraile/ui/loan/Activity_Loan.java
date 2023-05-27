package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseLoan;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.LoanAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Loan;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.book.Activity_Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner.Activity_Partner;

import java.util.ArrayList;

public class Activity_Loan extends AppCompatActivity implements SearchView.OnQueryTextListener{

    Button b_options;
    SearchView loanBrowser;
    ListView loan_list;
    DataBaseLoan dbl;
    LoanAdapter la;
    Loan loan;
    int option = 1;
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

        Button b_add = findViewById(R.id.b_add3);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_Loan.this, Loan_Add.class);
                startActivity(intent);
            }
        });

        b_options = findViewById(R.id.swapOption);
        b_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b_options.getText().equals(getString(R.string.Partners))){
                    b_options.setText(R.string.Books);
                    option = 2;
                }
                else{
                    b_options.setText(R.string.Partners);
                    option = 1;
                }

            }
        });


        loanBrowser = findViewById(R.id.loanBrowser);
        loanBrowser.setOnQueryTextListener(this);

        loan_list = findViewById(R.id.loan_list);
        dbl = new DataBaseLoan(this);
        la = new LoanAdapter(this, dbl.loanList());
        loan_list.setAdapter(la);

        loan_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                ArrayList<Loan> loans = dbl.loanList();
                if (pos < loans.size()) {
                    loan = loans.get(pos);
                    int id = loan.getLoan_id();
                    Intent intent = new Intent(Activity_Loan.this, Loan_View.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                }
            }
        });
    }

    //Refresh the list to show the added loan
    public void onResume() {
        super.onResume();
        loan_list = findViewById(R.id.loan_list);
        dbl = new DataBaseLoan(this);
        la = new LoanAdapter(this, dbl.loanList());
        loan_list.setAdapter(la);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        la.filter(s, option);
        return false;
    }
}