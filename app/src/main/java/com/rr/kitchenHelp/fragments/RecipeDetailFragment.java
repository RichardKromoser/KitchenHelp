package com.rr.kitchenHelp.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
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
        if (TextUtils.isEmpty(recipeDetail.getInstructions())) {
            instructions.setText(R.string.no_info);
        } else {
            instructions.setText(recipeDetail.getInstructions());
        }

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://kitchenhelp-58395.appspot.com");
        StorageReference reference = storage.getReference();

        final StorageReference pathReference = reference.child(recipeDetail.getImage());


        Glide.with(getActivity().getApplicationContext())
                .load(pathReference)
                .into((ImageView) rootView.findViewById(R.id.recipe_image_detail));



        return rootView;
    }

    private void createIngredientList() {
        if (TextUtils.isEmpty(recipeDetail.getIngredients())) {
            ingredientList.add(new RecipeIngredient(getString(R.string.no_info), "", ""));
            return;
        }
        String[] allIngredients = recipeDetail.getIngredients().split("\\|");
        for (int i = 0; i < allIngredients.length; i++) {
            String currentIngredient = allIngredients[i].trim();

            String piece = getRemainingString(currentIngredient, 0, currentIngredient.indexOf(" "));
            String rest = getRemainingString(currentIngredient, currentIngredient.indexOf(" "), currentIngredient.length());
            String unit = containsIngredientUnit(getRemainingString(rest, 0, rest.indexOf(" ")));
            String name;
            if (TextUtils.isEmpty(unit)) {
                name = rest;
            } else {
                name = getRemainingString(rest, rest.indexOf(" "), rest.length());
            }
            ingredientList.add(new RecipeIngredient(piece, unit, name));
        }
    }

    private String getRemainingString(String ingredientRow, int begin, int end) {
        if (end == -1) {
            return ingredientRow;
        }
        String remaining = ingredientRow.substring(begin, end);

        return remaining.trim();
    }

    private String containsIngredientUnit(String ingredient) {
        for (Unit value : Unit.values()) {
            // < 3 zum sichergehen dass nicht sowas wie steinpilz gefunden wird, wegen st von stück
            if (value.getShortName().equalsIgnoreCase(ingredient)) {
                return value.getDisplayName();
            }
        }
        return "";
    }

}
