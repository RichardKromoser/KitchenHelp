package com.rr.kitchenHelp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rr.kitchenHelp.R;
import com.rr.kitchenHelp.RecipeIngredient;
import com.rr.kitchenHelp.adapter.RecipeIngredientAdapter;
import com.rr.kitchenHelp.dto.Recipe;
import com.rr.kitchenHelp.dto.Unit;

import java.util.ArrayList;

public class RecipeDetailFragment extends Fragment {
    private Recipe recipeDetail;
    private ArrayList<RecipeIngredient> ingredientList = new ArrayList<>();


    public void setRecipeDetail(Recipe recipeDetail) {
        this.recipeDetail = recipeDetail;
    }

    public RecipeDetailFragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        TextView title = rootView.findViewById(R.id.recipe_detail_title);
        if (recipeDetail == null) {
            return rootView;
        }
        title.setText(recipeDetail.getName());
        createIngredientList();

        LinearLayout listViewReplacer = rootView.findViewById(R.id.recipe_ingredient_list);
        RecipeIngredientAdapter adapter = new RecipeIngredientAdapter(getActivity().getApplicationContext(), ingredientList);

        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, listViewReplacer);
            listViewReplacer.addView(view);
        }

        TextView instructions = rootView.findViewById(R.id.recipe_instructions);
        instructions.setText(recipeDetail.getInstructions());

        return rootView;
    }

    private void createIngredientList() {
        String[] allIngredients = recipeDetail.getIngredients().split("\\|");
        for (int i = 0; i < allIngredients.length; i++) {
            String currentIngredient = allIngredients[i].trim();
            String[] splittedIngredient = currentIngredient.split(" ", 3);
            String piece = splittedIngredient[0];
            String unit = containsIngredientUnit(splittedIngredient[1]);
            String name;
            if (unit.equalsIgnoreCase("")) {
                name = splittedIngredient[1];
            } else {
                name = splittedIngredient[2];
            }
            ingredientList.add(new RecipeIngredient(piece, unit, name));
        }
    }

    private String containsIngredientUnit(String ingredient) {
        for (Unit value : Unit.values()) {
            // < 3 zum sichergehen dass nicht sowas wie steinpilz gefunden wird, wegen st von stück
            if (value.getShortName().equalsIgnoreCase(ingredient) && ingredient.length() < 3) {
                return ingredient;
            }
        }
        return "";
    }

}
