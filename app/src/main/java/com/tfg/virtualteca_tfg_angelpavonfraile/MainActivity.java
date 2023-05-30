package com.tfg.virtualteca_tfg_angelpavonfraile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tfg.virtualteca_tfg_angelpavonfraile.DBSettings.DataBaseSupport;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.book.Activity_Book;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.loan.Activity_Loan;
import com.tfg.virtualteca_tfg_angelpavonfraile.ui.partner.Activity_Partner;

public class MainActivity extends AppCompatActivity {

    private int db_version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        /*Moving buttons*/
        Button b_book = findViewById(R.id.book_button);
        b_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Activity_Book.class);
                startActivity(intent);
            }
        });
        Button b_partner = findViewById(R.id.partner_button);
        b_partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Activity_Partner.class);
                startActivity(intent);
            }
        });
        Button b_loan = findViewById(R.id.loan_button);
        b_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Activity_Loan.class);
                startActivity(intent);
            }
        });
        
        /* CREATE DB BUTTON*/
        Button b_create_db = findViewById(R.id.create_db);
        b_create_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseSupport dbs = new DataBaseSupport(MainActivity.this);
                SQLiteDatabase db = dbs.getWritableDatabase();
                if (db != null){
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                }
            }
        });

        /* CLEAN DB BUTTON */
        Button b_delete_db = findViewById(R.id.delete_db);
        b_delete_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseSupport dbs = new DataBaseSupport(MainActivity.this);
                SQLiteDatabase db = dbs.getWritableDatabase();
                dbs.clearTables();
                Toast.makeText(MainActivity.this, "BASE DE DATOS VACIADA", Toast.LENGTH_LONG).show();
            }
        });
    }



}
