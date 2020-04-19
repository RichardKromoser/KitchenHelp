package com.rr.kitchenHelp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rr.kitchenHelp.R;

import java.util.List;

public class RecipeViewHolder extends BaseViewHolder {
    private List<String> dataset;
    private ImageView recipePicture;
    private TextView recipeTitle;


    public void setDataset(List<String> dataset) {
        this.dataset = dataset;
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
        recipeTitle.setText(dataset.get(position));
    }
}
