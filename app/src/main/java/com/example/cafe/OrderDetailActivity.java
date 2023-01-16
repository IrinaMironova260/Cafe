package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "user name";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_DRINK_TYPE = "drinkType";
    private static final String EXTRA_ADDITIVES = "additives";

    private TextView textVievName;
    private TextView textVievDrink;
    private TextView textVievDrinkType;
    private TextView textVievAdditives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initViews();

        Intent intent = getIntent();
        textVievName.setText(intent.getStringExtra(EXTRA_USER_NAME));
        textVievDrink.setText(intent.getStringExtra(EXTRA_DRINK));
        textVievDrinkType.setText(intent.getStringExtra(EXTRA_DRINK_TYPE));
        textVievAdditives.setText(intent.getStringExtra(EXTRA_ADDITIVES));
    }

    private void initViews() {
        textVievName = findViewById(R.id.textVievName);
        textVievDrink = findViewById(R.id.textVievDrink);
        textVievDrinkType = findViewById(R.id.textVievDrinkType);
        textVievAdditives = findViewById(R.id.textVievAdditives);
    }

    public static Intent newIntent(
                                     Context context,
                                     String userName,
                                     String drink,
                                     String drinkType,
                                     String additives
    ) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        intent.putExtra(EXTRA_ADDITIVES, additives);
        return intent;
    }
}