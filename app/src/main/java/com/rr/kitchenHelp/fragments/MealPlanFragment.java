package com.rr.kitchenHelp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.rr.kitchenHelp.R;

public class MealPlanFragment extends Fragment {


    public MealPlanFragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meal_plan, container, false);
        getActivity().setTitle(getString(R.string.nav_meal_plan));

        return rootView;
    }
}
