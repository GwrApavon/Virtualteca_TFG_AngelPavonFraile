package com.tfg.virtualteca_tfg_angelpavonfraile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.elements.Partner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<Partner> partners;
    ArrayList<Partner> partnersOriginal;

    public PartnerAdapter(Context context, ArrayList<Partner> partners) {
        this.context = context;
        this.partners = partners;
        partnersOriginal = new ArrayList<>();
        partnersOriginal.addAll(partners);
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

        TextView full_name_tv = element.findViewById(R.id.partner_name);
        full_name = partners.get(i).getName() + partners.get(i).getSurname1() + partners.get(i).getSurname2() ;
        full_name_tv.setText(full_name);
        
        TextView phn = element.findViewById(R.id.partner_phoneNum);
        phn.setText(partners.get(i).getPhone_number());

        TextView email = element.findViewById(R.id.partner_email);
        email.setText(partners.get(i).getEmail());

        return element;
    }

    public void filter (String search){
        int length = search.length();
        if(length == 0){
            partners.clear();
            partners.addAll(partnersOriginal);
        }
        else{
            List<Partner> collection =  partners.stream()
                    .filter(i -> i.getName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
            partners.clear();
            partners.addAll(collection);
        }

        notifyDataSetChanged();
    }

}
