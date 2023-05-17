package com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseBook;
import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBasePartner;
import com.tfg.virtualteca_tfg_angelpavonfraile.R;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.book.Book_Edit;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.book.Book_View;

public class Partner_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_view);


        int partner_id;
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null)
            {
                partner_id = Integer.parseInt(null);
            }
            else
            {
                partner_id = extras.getInt("ID");
            }
        }
        else
        {
            partner_id = (int) savedInstanceState.getSerializable("ID");
        }

        //EDIT PARTNER
        Button b_edit = findViewById(R.id.editButton2);
        b_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Partner_View.this, Partner_Edit.class);
                intent.putExtra("ID", partner_id);
                startActivity(intent);
            }
        });

        //DELETE PARTNER
        Button b_delete = findViewById(R.id.deleteButton2);
        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBasePartner dbp = new DataBasePartner(Partner_View.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(Partner_View.this);
                builder.setMessage("Está a punto de eliminar un socio. ¿Desea continuar?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean deleted = dbp.deletePartner(partner_id);

                                if(deleted){
                                    finish();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        });
    }
}