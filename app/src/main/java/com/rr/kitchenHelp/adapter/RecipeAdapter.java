package com.rr.kitchenHelp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rr.kitchenHelp.R;
import com.rr.kitchenHelp.ViewHolder.RecipeViewHolder;
import com.rr.kitchenHelp.dto.Recipe;

import java.util.ArrayList;
import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> implements Filterable {
    private List<Recipe> dataset;
    private List<Recipe> filteredDataset;

    //Provide a reference to the views for each data item
    //Complex data items need more than one view per item and
    //you provide access to all views for a data item in a view holder

    //Provide a suitable Constructor, depends on the dataset
    public RecipeAdapter(List<Recipe> dataset) {
        this.dataset = dataset;
        this.filteredDataset = dataset;
    }

    // Create new views (used by Layoutmanager)
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create a new View
        RecipeViewHolder vh = new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recipe, parent, false));
        vh.setRecipeList(filteredDataset);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.onBind(position);

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
        return filteredDataset.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterString = constraint.toString();
                if (filterString.isEmpty()) {
                    filteredDataset = dataset;
                } else {
                    ArrayList<Recipe> filteredRecipes = new ArrayList<>();
                    for (Recipe recipe : dataset) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name match
                        if (recipe.getName().toLowerCase().contains(filterString.toLowerCase())) {
                            filteredRecipes.add(recipe);
                        }
                    }
                    filteredDataset = filteredRecipes;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredDataset;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredDataset = (ArrayList<Recipe>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
