package com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseLoan;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.BookAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.LoanAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.PartnerAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.book.Activity_Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.book.Book_View;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan.Activity_Loan;

import java.util.ArrayList;

public class Activity_Partner extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ListView partner_list;
    DataBasePartner dbp;
    PartnerAdapter pa;
    SearchView partnerBrowser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner);

        partnerBrowser = findViewById(R.id.partnerBrowser);
        partnerBrowser.setOnQueryTextListener(Activity_Partner.this);

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

        partner_list = findViewById(R.id.partner_list);
        dbp = new DataBasePartner(this);
        pa = new PartnerAdapter(this, dbp.partnerList());
        partner_list.setAdapter(pa);

        partner_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                ArrayList<Partner> partners = dbp.partnerList();
                if (pos < partners.size()) {
                    Partner partner = partners.get(pos);
                    int id = partner.getPartner_id();
                    Intent intent = new Intent(Activity_Partner.this, Partner_View.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                }
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

    //Refresh the list to show the added partner
    public void onResume() {
        super.onResume();
        partner_list = findViewById(R.id.partner_list);
        dbp = new DataBasePartner(this);
        pa = new PartnerAdapter(this, dbp.partnerList());
        partner_list.setAdapter(pa);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        pa.filter(s);
        return false;
    }
}