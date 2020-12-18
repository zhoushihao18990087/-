package com.example.group;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivitytest extends AppCompatActivity {




    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mToolBar = (Toolbar) this.findViewById(R.id.toolbar);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(MainActivitytest.this,"Navigation Clicked",Toast.LENGTH_LONG).show();

            }
        });



        mToolBar.inflateMenu(R.menu.menu_main);

        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int id = item.getItemId();
                if(id == R.id.action_settings){

                    Toast.makeText(MainActivitytest.this,"action_settings Clicked",Toast.LENGTH_LONG).show();

                    return  true;
                }
                return false;
            }
        });




    }


}
