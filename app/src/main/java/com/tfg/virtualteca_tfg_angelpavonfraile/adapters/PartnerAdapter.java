package com.tfg.virtualteca_tfg_angelpavonfraile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;

import java.util.ArrayList;

public class PartnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<Partner> partners = new ArrayList<>();

    public PartnerAdapter(Context context, ArrayList<Partner> partners) {
        this.context = context;
        this.partners = partners;
    }

    @Override
    public int getCount() {
        return partners.size();
    }

    @Override
    public Object getItem(int i) {
        return partners.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      
        String full_name;
        LayoutInflater shown = LayoutInflater.from(context);
        View element = shown.inflate(R.layout.partner, viewGroup, false);
/*
        TextView full_name_tv = element.findViewById(R.id.full_name);
        full_name = partners.get(i).getName() + partners.get(i).getSurname1() + partners.get(i).getSurname2() ;
        full_name_tv.setText(full_name);
        
        TextView dni = element.findViewById(R.id.dni);
        dni.setText(partners.get(i).getDni());

        TextView email = element.findViewById(R.id.email);
        email.setText(partners.get(i).getEmail());
*/
        return element;
    }



}
