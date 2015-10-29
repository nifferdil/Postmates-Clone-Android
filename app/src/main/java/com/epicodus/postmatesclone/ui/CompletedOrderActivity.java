package com.epicodus.postmatesclone.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.models.User;

import java.io.Serializable;

public class CompletedOrderActivity extends AppCompatActivity implements Serializable{

    private Button mOrdersButton;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_order);

        mOrdersButton = (Button) findViewById(R.id.ordersButton);

        // Find user from intent
        mUser = (User) getIntent().getSerializableExtra("User");
        User currentUser = (User) getIntent().getSerializableExtra("User");
//        mUser = User.find(currentUser.getUsername(), currentUser.getPassword());

        mOrdersButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(CompletedOrderActivity.this, OrderActivity.class);
            intent.putExtra("User", mUser);
            startActivity(intent);
        }
    });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_completed_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
