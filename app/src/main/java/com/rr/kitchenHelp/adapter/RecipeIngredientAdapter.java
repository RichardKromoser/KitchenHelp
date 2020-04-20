package com.rr.kitchenHelp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rr.kitchenHelp.R;
import com.rr.kitchenHelp.RecipeIngredient;

import java.util.ArrayList;

public class RecipeIngredientAdapter extends ArrayAdapter<RecipeIngredient> {

    public RecipeIngredientAdapter(@NonNull Context context, @NonNull ArrayList<RecipeIngredient> ingredients) {
        super(context, 0, ingredients);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecipeIngredient recipeIngredient = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_single_ingredient, parent, false);
        }

        TextView piece = convertView.findViewById(R.id.recipe_ingredient_piece);
        TextView unit = convertView.findViewById(R.id.recipe_ingredient_unit);
        TextView name = convertView.findViewById(R.id.recipe_ingredient_name);

        piece.setText(recipeIngredient.getPiece());
        unit.setText(recipeIngredient.getUnit());
        if (recipeIngredient.getUnit().equals("")) {
            unit.setVisibility(View.VISIBLE);
        }
        name.setText(recipeIngredient.getName());

        return convertView;
    }
}
