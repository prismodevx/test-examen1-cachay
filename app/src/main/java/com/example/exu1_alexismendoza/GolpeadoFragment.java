package com.example.exu1_alexismendoza;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exu1_alexismendoza.databinding.FragmentGolpeadoBinding;


public class GolpeadoFragment extends Fragment {
    private FragmentGolpeadoBinding binding;
    private String nombreUsuario;
    private int numeroJugadores;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_golpeado, container, false);
    }
}