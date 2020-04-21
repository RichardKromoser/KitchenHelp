package com.rr.kitchenHelp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rr.kitchenHelp.R;
import com.rr.kitchenHelp.ViewHolder.RecipeViewHolder;
import com.rr.kitchenHelp.dto.Recipe;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    private List<Recipe> dataset;

    //Provide a reference to the views for each data item
    //Complex data items need more than one view per item and
    //you provide access to all views for a data item in a view holder

    //Provide a suitable Constructor, depends on the dataset
    public RecipeAdapter(List<Recipe> dataset) {
        this.dataset = dataset;
    }

    // Create new views (used by Layoutmanager)
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create a new View
        RecipeViewHolder vh = new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recipe, parent, false));
        vh.setRecipeList(dataset);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.onBind(position);

        Recipe currentRecipe = dataset.get(position);

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://kitchenhelp-58395.appspot.com");
        StorageReference reference = storage.getReference();
        final StorageReference pathReference = reference.child("ClarionHotelPrag-2016.PNG");


        Glide.with(holder.getRecipePicture().getContext())
                .load(pathReference)
                .into(holder.getRecipePicture());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
