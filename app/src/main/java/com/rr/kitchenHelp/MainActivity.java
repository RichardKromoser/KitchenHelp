package com.rr.kitchenHelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.rr.kitchenHelp.cameraUtils.OcrCaptureActivity;
import com.rr.kitchenHelp.fragments.IngredientFragment;
import com.rr.kitchenHelp.fragments.MealPlanFragment;
import com.rr.kitchenHelp.fragments.RecipeFragment;

public class MainActivity extends BaseActivity {

    public static final String EXTRA_MESSAGE = "com.rr.kitchenHelp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDefaultToolbar();
        initializeDefaultDrawer();

        NavigationView navigationView = findViewById(R.id.nav_view);
        // Rezepte ist die Startseite
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RecipeFragment()).commit();

        navigationView.setCheckedItem(R.id.fragment_recipe);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.fragment_recipe:
                        fragment = new RecipeFragment();
                        break;
                    case R.id.fragment_ingredient:
                        fragment = new IngredientFragment();
                        break;
                    case R.id.fragment_meal_plan:
                        fragment = new MealPlanFragment();
                        break;
                    default:
                        break;
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    Log.e("MainActivity", "Error in creating Fragment");
                }
                return true;
            }
        });
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
