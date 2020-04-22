package com.rr.kitchenHelp.ViewHolder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rr.kitchenHelp.R;
import com.rr.kitchenHelp.dto.Recipe;
import com.rr.kitchenHelp.fragments.RecipeDetailFragment;

import java.util.List;

public class RecipeViewHolder extends BaseViewHolder {
    private List<Recipe> recipeList;
    private ImageView recipePicture;
    private TextView recipeTitle;

    public ImageView getRecipePicture() {
        return recipePicture;
    }

    public void setRecipePicture(ImageView recipePicture) {
        this.recipePicture = recipePicture;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public RecipeViewHolder(View itemView) {
        super(itemView);
        this.recipePicture = itemView.findViewById(R.id.recipe_thumbnail);
        this.recipeTitle = itemView.findViewById(R.id.recipe_title);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Was soll passieren wenn man die Karte anklickt
                Log.d("RecipeViewHolder", recipeList.get(getCurrentPosition()).getName());
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                RecipeDetailFragment fragment = new RecipeDetailFragment();
                fragment.setRecipeDetail(recipeList.get(getCurrentPosition()));
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
    }
    @Override
    protected void clear() {
        recipePicture.setImageDrawable(null);
        recipeTitle.setText("");
    }

    public void onBind(int position) {
        super.onBind(position);
        recipeTitle.setText(recipeList.get(position).getName());
    }
}
