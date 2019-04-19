package com.example.carles.navigationdraw;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditarLloc.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditarLloc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarLloc extends Fragment implements View.OnClickListener {
    EditText nomEditText, adressEditText, longitudEditText, latitudEditText, urlEditText, comentariEditText,
            telefonEditText, valoracioEditText;
    Button editarLlocBtn, borrarLlocBtn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ID_PARAM = "id", NOM_PARAM = "nom", ADRESS_PARAM = "adreça", LONGITUD_PARAM = "longitud",
            LATITUD_PARAM = "latitud", TELEFON_PARAM = "telefon", WEB_PARAM = "web",
            VALORACIO_PARAM = "valoracio", COMENTARI_PARAM = "comentari";

    // TODO: Rename and change types of parameters
    private String nomAEditar, adressAEditar, webAEditar, comentariAEditar;
    private int telefonAEditar;
    private float valoracioAEditar;
    private double longitudAEditar, latitudAEditar;
    private int id;

    LlocsBD bd;

    private OnFragmentInteractionListener mListener;

    public EditarLloc() {
        // Required empty public constructor
    }


    public static EditarLloc newInstance(Lloc llocAEditar) {
        EditarLloc fragment = new EditarLloc();
        Bundle args = new Bundle();
        args.putInt(ID_PARAM, llocAEditar.get_id());
        args.putString(NOM_PARAM, llocAEditar.getNom());
        args.putString(ADRESS_PARAM, llocAEditar.getAddress());
        args.putDouble(LONGITUD_PARAM, llocAEditar.getLongitud());
        args.putDouble(LATITUD_PARAM, llocAEditar.getLatitud());
        args.putInt(TELEFON_PARAM, llocAEditar.getTelefon());
        args.putString(WEB_PARAM, llocAEditar.getUrl());
        args.putFloat(VALORACIO_PARAM, llocAEditar.getValoracio());
        args.putString(COMENTARI_PARAM, llocAEditar.getComentari());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ID_PARAM);
            nomAEditar = getArguments().getString(NOM_PARAM);
            adressAEditar = getArguments().getString(ADRESS_PARAM);
            longitudAEditar = getArguments().getDouble(LONGITUD_PARAM);
            latitudAEditar = getArguments().getDouble(LATITUD_PARAM);
            telefonAEditar = getArguments().getInt(TELEFON_PARAM);
            webAEditar = getArguments().getString(WEB_PARAM);
            valoracioAEditar = getArguments().getFloat(VALORACIO_PARAM);
            comentariAEditar = getArguments().getString(COMENTARI_PARAM);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View editarLlocFragmentView = inflater.inflate(R.layout.fragment_editar_lloc, container, false);
        bd = new LlocsBD(editarLlocFragmentView.getContext());
        nomEditText = editarLlocFragmentView.findViewById(R.id.nomEditText);
        adressEditText = editarLlocFragmentView.findViewById(R.id.adressEditText);
        longitudEditText = editarLlocFragmentView.findViewById(R.id.longitudEditText);
        latitudEditText = editarLlocFragmentView.findViewById(R.id.latitudEditText);
        urlEditText = editarLlocFragmentView.findViewById(R.id.urlEditText);
        comentariEditText = editarLlocFragmentView.findViewById(R.id.comentariEditText);
        telefonEditText = editarLlocFragmentView.findViewById(R.id.telefonEditText);
        valoracioEditText = editarLlocFragmentView.findViewById(R.id.valoracioEditText);
        editarLlocBtn = editarLlocFragmentView.findViewById(R.id.editarLlocBtn);
        editarLlocBtn.setOnClickListener(this);
        borrarLlocBtn = editarLlocFragmentView.findViewById(R.id.borrarLlocBtn);
        borrarLlocBtn.setOnClickListener(this);

        nomEditText.setText(nomAEditar);
        adressEditText.setText(adressAEditar);
        longitudEditText.setText(String.valueOf(longitudAEditar));
        latitudEditText.setText(String.valueOf(latitudAEditar));
        urlEditText.setText(webAEditar);
        telefonEditText.setText(String.valueOf(telefonAEditar));
        valoracioEditText.setText(String.valueOf(valoracioAEditar));
        comentariEditText.setText(comentariAEditar);

        return editarLlocFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        Lloc lloc = new Lloc();
        lloc.set_id(id);
        lloc.setNom(nomEditText.getText().toString());
        lloc.setAddress(adressEditText.getText().toString());
        lloc.setLongitud(Double.parseDouble(longitudEditText.getText().toString()));
        lloc.setLatitud(Double.parseDouble(latitudEditText.getText().toString()));
        lloc.setTelefon(Integer.parseInt(telefonEditText.getText().toString()));
        lloc.setUrl(urlEditText.getText().toString());
        lloc.setValoracio(Float.parseFloat(valoracioEditText.getText().toString()));
        lloc.setComentari(comentariEditText.getText().toString());

        switch (v.getId()){
            case R.id.editarLlocBtn:

                bd.updateLloc(lloc);
                Toast.makeText(getContext(), "Lloc actualitzat!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.borrarLlocBtn:

                bd.deleteLloc(lloc);
                Toast.makeText(getContext(), "Lloc esborrat!", Toast.LENGTH_SHORT).show();
                break;
        }

        Fragment fragment = null;
        Class fragmentClass = LlistaLlocs.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
