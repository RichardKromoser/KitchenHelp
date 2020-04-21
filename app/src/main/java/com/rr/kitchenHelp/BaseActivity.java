package com.rr.kitchenHelp;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rr.kitchenHelp.dto.Ingredient;
import com.rr.kitchenHelp.dto.Recipe;
import com.rr.kitchenHelp.fragments.IngredientFragment;
import com.rr.kitchenHelp.fragments.MealPlanFragment;
import com.rr.kitchenHelp.fragments.RecipeFragment;
import com.rr.kitchenHelp.fragments.TestFragment;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private boolean searchVisible = true;
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;
    private static final String TAG = "BaseActivity";

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    private List<Recipe> recipeList = new ArrayList<>();

    public void setSearchVisible(boolean searchVisible) {
        this.searchVisible = searchVisible;
    }

    public void initializeDefaultToolbar() {
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        // Adds the Back Arrow in Sub-Pages
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void initializeDefaultDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        // Test ist die Startseite
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TestFragment()).commit();

        navigationView.setCheckedItem(R.id.fragment_test);
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
                    case R.id.fragment_test:
                        fragment = new TestFragment();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout == null) {
            super.onBackPressed();
            return;
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        if (!searchVisible) {
            searchItem.setVisible(false);
        }
        searchView.setQueryHint("Suchen...");
        // Configure the Search info and add event listener

        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when action item expands
//                item
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapse
                Log.d("BaseActivity", "onMenuItemActionCollapse");
                return true;  // Return true to expand action view
            }
        };

        searchItem.setOnActionExpandListener(expandListener);

        return super.onCreateOptionsMenu(menu);
    }

    public void initializeDatabase() {
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
    }

    private void showdata(DataSnapshot dataSnapshot) {
        DataSnapshot dSh = dataSnapshot.child("ingredients");
        Log.d(TAG, dSh.getValue().toString());
        for (DataSnapshot d : dSh.getChildren()) {
            Ingredient ig = d.getValue(Ingredient.class);
            Log.d(TAG, ig.getName());
        }
        dSh = dataSnapshot.child("recipes");
        Log.d(TAG, dSh.getValue().toString());
        for (DataSnapshot d : dSh.getChildren()) {
            Recipe re = d.getValue(Recipe.class);
            recipeList.add(re);
            Log.d(TAG, re.getName());
            Log.d(TAG, re.getCategory());
        }
    }
}
