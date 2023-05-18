package com.tfg.virtualteca_tfg_angelpavonfraile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseLoan;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Loan;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;

import java.util.ArrayList;

public class LoanAdapter extends BaseAdapter {
    Context context;
    ArrayList<Loan> loans = new ArrayList<>();

    public LoanAdapter(Context context, ArrayList<Loan> loans) {
        this.context = context;
        this.loans = loans;
    }

    @Override
    public int getCount() {
        return loans.size();
    }

    @Override
    public Object getItem(int i) {
        return loans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater shown = LayoutInflater.from(context);
        View element = shown.inflate(R.layout.loan, viewGroup, false);
        DataBaseBook dbb = new DataBaseBook(context);
        DataBasePartner dbp = new DataBasePartner(context);
        
        Book book = dbb.getBookById(loans.get(i).getBook_id());
        Partner partner = dbp.getPartnerById(loans.get(i).getPartner_id());

        //PARTNER
        TextView full_name_tv = element.findViewById(R.id.partner_name);
        String full_name = partner.getName() + partner.getSurname1() + partner.getSurname2() ;
        full_name_tv.setText(full_name);
       
        //BOOK
        TextView title = element.findViewById(R.id.book_title);
        title.setText(book.getTitle());
        
        //LOAN
        TextView fin_date = element.findViewById(R.id.fin_date);
        fin_date.setText(loans.get(i).getFin_date());

        CheckBox returned = element.findViewById(R.id.returned);
        //si isDevuelto() checkbox marcada else no marcada
        returned.setChecked(loans.get(i).getReturned());

        returned.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                DataBaseLoan dbl = new DataBaseLoan(context);
                int loan_id = loans.get(i).getLoan_id();
                dbl.editLoan(loan_id, returned.isChecked());
            }
        });

        return element;
    }

}
