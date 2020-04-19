package com.rr.kitchenHelp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.rr.kitchenHelp.R;
import com.rr.kitchenHelp.ViewHolder.BaseViewHolder;
import com.rr.kitchenHelp.ViewHolder.RecipeViewHolder;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<String> dataset;

    //Provide a reference to the views for each data item
    //Complex data items need more than one view per item and
    //you provide access to all views for a data item in a view holder
//    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
    // each data is just a string for this example
//        public TextView textView;
//        public RecipeViewHolder(TextView textView) {
//            super(textView);
//            this.textView = textView;
//        }

//    }

    //Provide a suitable Constructor, depends on the dataset
    public RecipeAdapter(List<String> dataset) {
        this.dataset = dataset;
    }

    // Create new views (used by Layoutmanager)
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create a new View
        RecipeViewHolder vh = new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recipe, parent, false));
        vh.setDataset(dataset);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.onBind(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
