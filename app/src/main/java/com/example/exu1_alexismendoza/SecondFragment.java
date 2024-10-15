package com.example.exu1_alexismendoza;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.exu1_alexismendoza.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private int numeroAleatorio;
    private int intentosRestantes = 10;
    private List<Integer> numerosPropuestos = new ArrayList<>();
    private String nombreUsuario;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nombreUsuario = SecondFragmentArgs.fromBundle(getArguments()).getNombre();
        binding.txtUsuario.setText(nombreUsuario);

        generarRandomNumber();

        binding.btnEnviarIntento.setOnClickListener(v -> enviarIntento());

        binding.btnReiniciar.setOnClickListener(v -> reiniciarJuego());

        binding.btnReiniciar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void generarRandomNumber() {
        numeroAleatorio = new Random().nextInt(100) + 1;
        intentosRestantes = 10;
        numerosPropuestos.clear();

        binding.txtIntentos.setText("");
        binding.txtResultado.setText("");
        binding.txtResultado.setBackgroundColor(Color.TRANSPARENT);
        binding.btnReiniciar.setVisibility(View.GONE);
        binding.edtIntento.setEnabled(true);
        binding.btnEnviarIntento.setEnabled(true);
    }

    private void enviarIntento() {
        String intentoStr = binding.edtIntento.getText().toString().trim();
        if (intentoStr.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, ingresa un número.", Toast.LENGTH_LONG).show();
            return;
        }

        int intento;
        try {
            intento = Integer.parseInt(intentoStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Entrada inválida. Ingresa un número válido.", Toast.LENGTH_LONG).show();
            return;
        }

        if (intento < 1 || intento > 100) {
            Toast.makeText(getContext(), "El número debe estar entre 1 y 100.", Toast.LENGTH_LONG).show();
            return;
        }

        procesarIntento(intento);
    }

    private void procesarIntento(int intento) {
        intentosRestantes--;
        numerosPropuestos.add(intento);
        binding.txtIntentos.setText(numerosPropuestos.toString());

        if (intento == numeroAleatorio) {
            // Acertó
            binding.txtResultado.setText("¡Has acertado!");
            binding.txtResultado.setBackgroundColor(Color.GREEN);
            finalizarJuego();
        } else {
            binding.txtResultado.setText("No has acertado.");
            binding.txtResultado.setBackgroundColor(Color.RED);

            if (intento < numeroAleatorio) {
                Toast.makeText(getContext(), "El número es muy bajo.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "El número es muy alto.", Toast.LENGTH_LONG).show();
            }

            if (intentosRestantes == 0) {
                binding.txtResultado.setText("Has agotado todos los intentos. El número era " + numeroAleatorio);
                binding.txtResultado.setBackgroundColor(Color.RED);
                finalizarJuego();
            }
        }
        binding.edtIntento.setText("");
    }

    private void finalizarJuego() {
        binding.edtIntento.setEnabled(false);
        binding.btnEnviarIntento.setEnabled(false);
        binding.btnReiniciar.setVisibility(View.VISIBLE);
    }

    private void reiniciarJuego() {
        generarRandomNumber();
    }
}