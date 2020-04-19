package com.rr.kitchenHelp;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.android.material.navigation.NavigationView;
import com.rr.kitchenHelp.cameraUtils.OcrCaptureActivity;
import com.rr.kitchenHelp.dto.Category;
import com.rr.kitchenHelp.dto.Ingredient;
import com.rr.kitchenHelp.dto.Recipe;
import com.rr.kitchenHelp.fragments.IngredientFragment;
import com.rr.kitchenHelp.fragments.MealPlanFragment;
import com.rr.kitchenHelp.fragments.RecipeFragment;

import java.net.DatagramSocket;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    public static final String EXTRA_MESSAGE = "com.rr.kitchenHelp.MESSAGE";
    private static final String TAG = "MainActivity";
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDefaultToolbar();
        initializeDefaultDrawer();

        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                showdata(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*Query qu = mDatabase.child("menu").orderByChild("recipe").equalTo("1");
        qu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Recipe value = dataSnapshot.getValue(Recipe.class);
                Log.d(TAG, "Value is: " + value.getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/

    }

    private void showdata(DataSnapshot dataSnapshot) {
        DataSnapshot dSh = dataSnapshot.child("ingredients");
        Log.d(TAG,dSh.getValue().toString());
        for (DataSnapshot d : dSh.getChildren()) {
            Ingredient ig = d.getValue(Ingredient.class);
            Log.d(TAG,ig.getName());
        }
        dSh = dataSnapshot.child("recipes");
        Log.d(TAG,dSh.getValue().toString());
        for (DataSnapshot d : dSh.getChildren()) {
            Recipe re = d.getValue(Recipe.class);
            Log.d(TAG,re.getName());
            Log.d(TAG,re.getCategory());
        }
        Log.d(TAG,"hello world");
    }


    /**
     * Called when the user taps the Send button
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void openCamera(View view) {
        Intent intent = new Intent(this, OcrCaptureActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String returnedResult = data.getDataString();
                EditText editText = findViewById(R.id.editText);
                editText.setText(returnedResult);
            }
        }
    }
}
