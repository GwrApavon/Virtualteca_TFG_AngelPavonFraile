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
        DataBaseBook dbb = new DataBaseBook(LoanAdapter.this);
        DataBasePartner dbp = new DataBasePartner(LoanAdapter.this);
        
        Book book = dbb.getBookById(loans.get(i).getBook_id());
        Partner partner = dbp.getPartnerById(loans.get(i).getPartner_id());
 /*
        //PARTNER
        TextView full_name_tv = element.findViewById(R.id.full_name);
        full_name = partner.getName() + partner.getSurname1() + partner.getSurname2() ;
        full_name_tv.setText(full_name);
        
        TextView phn = element.findViewById(R.id.phone_number);
        phn.setText(partner.getPhone_number());

        TextView email = element.findViewById(R.id.email);
        email.setText(partner.getEmail());
       
        //BOOK
        TextView title = element.findViewById(R.id.book_title);
        title.setText(book.getTitle());

        TextView author = element.findViewById(R.id.book_author);
        author.setText(book.getAuthor());

        TextView editorial = element.findViewById(R.id.book_editorial);a
        editorial.setText(book.getEditorial());
        
        //LOAN
        TextView fin_date = element.findViewById(R.id.fin_date);
        fin_date.setText(loans.get(i).getFin_date());

        CheckBox devuelto = element.findViewById(R.id.devuelto);
        //si isDevuelto() checkbox marcada else no marcada
        //devuelto.setText(String.valueOf(loans.get(i).isDevuelto()));
*/
        return element;
    }

}
