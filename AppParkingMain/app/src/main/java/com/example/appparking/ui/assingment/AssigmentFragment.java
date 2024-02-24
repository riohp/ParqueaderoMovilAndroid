package com.example.appparking.ui.assingment;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.appparking.R;
import com.example.appparking.databinding.FragmentAssigmentBinding;


public class AssigmentFragment extends Fragment {

    private FragmentAssigmentBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAssigmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAssigment;
        textView.setText("Se logro xd");
        return root;
    }


    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

}