package com.rr.kitchenHelp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private RecyclerView.Adapter adapter;

    public RecipeFragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        recyclerView = rootView.findViewById(R.id.recipe_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        List<Recipe> recipeList = ((MainActivity) getActivity()).getRecipeList();
        List<Recipe> data = new ArrayList<>(recipeList);
        //Specify an Adapter
        adapter = new RecipeAdapter(data);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
