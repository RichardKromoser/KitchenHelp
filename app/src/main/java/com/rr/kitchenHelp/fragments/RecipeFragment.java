package com.rr.kitchenHelp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rr.kitchenHelp.MainActivity;
import com.rr.kitchenHelp.R;
import com.rr.kitchenHelp.adapter.RecipeAdapter;
import com.rr.kitchenHelp.dto.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecipeAdapter adapter;

    public RecipeFragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        recyclerView = rootView.findViewById(R.id.recipe_view);
        setHasOptionsMenu(true);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        List<Recipe> recipeList = ((MainActivity) getActivity()).getRecipeList();
        List<Recipe> data = new ArrayList<>(recipeList);
        //Specify an Adapter
        adapter = new RecipeAdapter(data);
        recyclerView.setAdapter(adapter);

        getActivity().setTitle(getString(R.string.nav_recipe));

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menu.clear();
        menuInflater.inflate(R.menu.header_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("Search Submit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                System.out.println("Search Change");
                return false;
            }
        });
    }
}
