package com.example.foodorderbasic.Fragnemt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderbasic.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragnment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragnment extends Fragment {


    public ContactFragnment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment'

        View view =  inflater.inflate(R.layout.fragment_contact_fragnment, container, false);
        return view;
    }
}