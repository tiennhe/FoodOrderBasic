package com.example.foodorderbasic.Fragnemt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderbasic.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackFragnment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFragnment extends Fragment {


    public FeedbackFragnment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_feedback_fragnment, container, false);
        return view;
    }
}