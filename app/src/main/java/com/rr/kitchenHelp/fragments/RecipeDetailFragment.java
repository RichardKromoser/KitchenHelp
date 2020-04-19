package com.rr.kitchenHelp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rr.kitchenHelp.R;
import com.rr.kitchenHelp.dto.Recipe;

public class RecipeDetailFragment extends Fragment {
    private Recipe recipeDetail;

    public void setRecipeDetail(Recipe recipeDetail) {
        this.recipeDetail = recipeDetail;
    }

    public RecipeDetailFragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        TextView title = rootView.findViewById(R.id.recipe_detail_title);
        title.setText(recipeDetail.getName());
        return rootView;
    }


}
