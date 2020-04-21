package com.rr.kitchenHelp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.rr.kitchenHelp.R;
import com.rr.kitchenHelp.dto.Recipe;

import java.util.List;

public class RecipeIngredientViewHolder extends BaseViewHolder {
    private List<Recipe> recipeList;
    private TextView ingredientPiece;
    private TextView ingredientUnit;
    private TextView ingredientName;


    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public RecipeIngredientViewHolder(View itemView) {
        super(itemView);
        this.ingredientPiece = itemView.findViewById(R.id.recipe_ingredient_piece);
        this.ingredientUnit = itemView.findViewById(R.id.recipe_ingredient_unit);
        this.ingredientName = itemView.findViewById(R.id.recipe_ingredient_name);
    }

    @Override
    protected void clear() {
        ingredientPiece.setText("");
        ingredientUnit.setText("");
        ingredientName.setText("");
    }

    public void onBind(int position) {
        super.onBind(position);
        Recipe currentRecipe = recipeList.get(position);
        String[] ingredientList = currentRecipe.getIngredients().split("\\|");

    }
}
