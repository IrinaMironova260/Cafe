package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";

    private TextView textVievGreetings;
    private RadioGroup radioGroupDrinks;
    private RadioButton radioButtonTea;
    private RadioButton radioButtonTCoffee;
    private TextView textVievAdditives;
    private CheckBox checkboxSugar;
    private CheckBox checkboxMilk;
    private CheckBox checkboxLimon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private Button buttonMakeOrder;

    private String drink;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        initVievs();
        setupUserName();

        radioGroupDrinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == radioButtonTea.getId()){
                    onUserChooseTea();
                }else if (id == radioButtonTCoffee.getId()){
                    onUserChooseCoffee();
                }
            }
        });
        radioButtonTea.setChecked(true);

        buttonMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUserMadeOrder();
            }
        });

    }

    private void onUserMadeOrder() {
        ArrayList<String> additive = new ArrayList<>();
        if (checkboxSugar.isChecked()) {
            additive.add(checkboxSugar.getText().toString());
        }
        if (checkboxMilk.isChecked()) {
            additive.add(checkboxMilk.getText().toString());
        }
        if (radioButtonTea.isChecked() && checkboxLimon.isChecked()) {
            additive.add(checkboxLimon.getText().toString());
        }

        String drinkType = "";
        if (radioButtonTea.isChecked()) {
            drinkType = spinnerTea.getSelectedItem().toString();
        } else if (radioButtonTCoffee.isChecked()) {
            drinkType = spinnerCoffee.getSelectedItem().toString();
        }

        Intent intent = OrderDetailActivity.newIntent(
                this,
                userName,
                drink,
                drinkType,
                additive.toString()
        );
        startActivity(intent);
    }

    private void  onUserChooseTea(){
        drink = getString(R.string.tea);
        String additiveLabel = getString(R.string.additives, drink);
        textVievAdditives.setText(additiveLabel);
        checkboxLimon.setVisibility(View.VISIBLE);
        spinnerTea.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
    }

    private void  onUserChooseCoffee(){
        drink = getString(R.string.coffee);
        String additiveLabel = getString(R.string.additives, drink);
        textVievAdditives.setText(additiveLabel);
        checkboxLimon.setVisibility(View.INVISIBLE);
        spinnerTea.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.VISIBLE);
    }

    private void setupUserName(){
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greetings = getString(
                R.string.greetings, userName
        );
        textVievGreetings.setText(greetings);
    }

    public static Intent newIntent(Context context, String userName) {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        return intent;
    }

    private void initVievs() {
        textVievGreetings = findViewById(R.id.textVievGreetings);
        radioGroupDrinks = findViewById(R.id.radioGroupDrinks);
        radioButtonTea = findViewById(R.id.radioButtonTea);
        radioButtonTCoffee = findViewById(R.id.radioButtonTCoffee);
        textVievAdditives = findViewById(R.id.textVievAdditives);
        checkboxSugar = findViewById(R.id.checkboxSugar);
        checkboxMilk = findViewById(R.id.checkboxMilk);
        checkboxLimon = findViewById(R.id.checkboxLimon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        buttonMakeOrder = findViewById(R.id.buttonMakeOrder);


    }

}