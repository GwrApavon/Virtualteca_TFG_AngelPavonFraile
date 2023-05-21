package com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.adapters.PartnerAdapter;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;
import java.util.ArrayList;

public class PartnerPicker extends AppCompatActivity implements SearchView.OnQueryTextListener{


    SearchView partnerPickerBrowser;
    ListView partner_list;
    DataBasePartner dbp;
    PartnerAdapter pa;
    Partner partner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_picker);

        partnerPickerBrowser = findViewById(R.id.partnerPickerBrowser);
        partnerPickerBrowser.setOnQueryTextListener(this);

        partner_list = findViewById(R.id.partner_list);
        dbp = new DataBasePartner(this);
        pa = new PartnerAdapter(this, dbp.partnerList());
        partner_list.setAdapter(pa);

        partner_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                ArrayList<Partner> partners = dbp.partnerList();
                if (pos < partners.size()) {
                    partner = partners.get(pos);
                    int id = partner.getPartner_id();
                    Intent intent = new Intent(PartnerPicker.this, Loan_add.class);
                    intent.putExtra("PARTNER_ID", id);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });

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