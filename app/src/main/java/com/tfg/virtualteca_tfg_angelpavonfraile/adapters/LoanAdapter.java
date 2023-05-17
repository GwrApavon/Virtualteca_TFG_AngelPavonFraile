package com.tfg.virtualteca_tfg_angelpavonfraile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Loan;

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
 /*
        //Arreglar para que lo saque de la base de datos
        TextView partner = element.findViewById(R.id.partner);
        int partner_id = Integer.parseInt(String.valueOf(loans.get(i).getId_partner()));
        String partner_full_name = partner_id.get(i).getName() + partner_id.get(i).getSurname1() + partner_id.get(i).getSurname2();
        partner.setText(partner_full_name);
       
        //Lo mismo 
        TextView id_book = element.findViewById(R.id.id_book);
        int partner_id = Integer.parseInt(String.valueOf(loans.get(i).getId_partner()));
        id_book.setText();

        TextView fin_date = element.findViewById(R.id.fin_date);
        fin_date.setText(loans.get(i).getFin_date());

        CheckBox devuelto = element.findViewById(R.id.devuelto);
        //si isDevuelto() checkbox marcada else no marcada
        //devuelto.setText(String.valueOf(loans.get(i).isDevuelto()));
*/
        return element;
    }

}
