package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.BookAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.PartnerAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.book.Book_View;

import java.util.ArrayList;

public class PartnerPicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_picker);

        ListView partner_list = findViewById(R.id.partner_list);
        DataBasePartner dbp = new DataBasePartner(this);
        PartnerAdapter pa = new PartnerAdapter(this, dbp.partnerList());
        partner_list.setAdapter(pa);

        partner_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                ArrayList<Partner> partners = dbp.partnerList();
                if (pos < partners.size()) {
                    Partner partner = partners.get(pos);
                    int id = partner.getPartner_id();
                    Intent intent = new Intent(PartnerPicker.this, Loan_add.class);
                    intent.putExtra("PARTNER_ID", id);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}