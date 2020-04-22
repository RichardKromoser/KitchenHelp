package com.rr.kitchenHelp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.rr.kitchenHelp.R;

public class IngredientFragment extends Fragment {


    public IngredientFragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);
        getActivity().setTitle(getString(R.string.nav_ingredient));

        return rootView;
    }
}
