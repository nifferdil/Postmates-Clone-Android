package com.epicodus.postmatesclone.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.postmatesclone.R;
import com.epicodus.postmatesclone.models.Order;
import com.epicodus.postmatesclone.models.User;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable{

    private SharedPreferences mPreferences;
    private User mUser;
    private Button mLogoutButton;
    private Button mGetTotalButton;
    private EditText mOrderText;
    private EditText mFirstNumber;
    private EditText mSecondNumber;
    private TextView mAddResult;
    private TextView mLoggedInView;
    private Button mSubmitOrderButton;
    private Button mViewOrderHistoryButton;
    private ArrayList<Order> mOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getApplicationContext().getSharedPreferences("postmates", Context.MODE_PRIVATE);
        mLogoutButton = (Button) findViewById(R.id.logoutButton);
        mFirstNumber = (EditText) findViewById(R.id.txtNumber1);
        mSecondNumber = (EditText) findViewById(R.id.txtNumber2);
        mOrderText = (EditText) findViewById(R.id.orderText);
        mGetTotalButton = (Button) findViewById(R.id.getTotalButton);
        mAddResult = (TextView) findViewById(R.id.addResult);
        mLoggedInView = (TextView) findViewById(R.id.loggedInView);
        mSubmitOrderButton = (Button) findViewById(R.id.submitOrderButton);
        mViewOrderHistoryButton = (Button) findViewById(R.id.viewOrderHistoryButton);
        mOrders = (ArrayList) Order.all();

        mGetTotalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(mFirstNumber.getText().toString());
                double num2 = Double.parseDouble(mSecondNumber.getText().toString());;
                double sum = num1 * num2;
                mAddResult.setText("$" + String.format("%.2f", sum));
            }
        });

        if (!isRegistered()) {
            navigateToRegister();
        }

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mSubmitOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderContent = mOrderText.getText().toString();
                Order order = new Order(orderContent, mUser);
                order.save();
                Intent intent = new Intent(MainActivity.this, CompletedOrderActivity.class);
                startActivity(intent);
            }
        });

        mViewOrderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra("User", mUser);
                startActivity(intent);
            }
        });

        mLoggedInView.setText("You are logged in as " + mUser.getUsername());
    }

    private boolean isRegistered() {
        String username = mPreferences.getString("username", null);
        String password = mPreferences.getString("password", null);
        if (username == null && password == null){
            return false;
        } else {
            setUser(username, password);
            return true;
        }
    }

    private void setUser(String username, String password) {
        User user = User.find(username, password);
        if (user != null) {
            mUser = user;
        } else {
            mUser = new User(username, password);
            mUser.save();
        }
        Toast.makeText(this, "Welcome " + mUser.getUsername(), Toast.LENGTH_LONG).show();
    }

    private void navigateToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
