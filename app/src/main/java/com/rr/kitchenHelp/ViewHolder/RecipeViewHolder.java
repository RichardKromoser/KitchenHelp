package com.rr.kitchenHelp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rr.kitchenHelp.R;
import com.rr.kitchenHelp.dto.Recipe;

import java.util.List;

public class RecipeViewHolder extends BaseViewHolder {
    private List<Recipe> recipeList;
    private ImageView recipePicture;
    private TextView recipeTitle;


    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public RecipeViewHolder(View itemView) {
        super(itemView);
        this.recipePicture = itemView.findViewById(R.id.recipe_thumbnail);
        this.recipeTitle = itemView.findViewById(R.id.recipe_title);
    }

    @Override
    protected void clear() {
        recipePicture.setImageDrawable(null);
        recipeTitle.setText("");
    }

    public void onBind(int position) {
        super.onBind(position);
        recipePicture.setImageResource(R.drawable.common_google_signin_btn_icon_dark);
        recipeTitle.setText(recipeList.get(position).getName());
    }
}
