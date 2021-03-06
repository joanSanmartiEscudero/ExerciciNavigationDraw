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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AfegirLloc.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AfegirLloc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AfegirLloc extends Fragment implements View.OnClickListener {

    EditText nomEditText, adressEditText, urlEditText, comentariEditText ,telefonEditText, longitudEditText, latitudEditText, valoracioEditText;
    Button afegirLlocBtn;
    LlocsBD bd;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AfegirLloc() {
        // Required empty public constructor
    }


    public static AfegirLloc newInstance(String param1, String param2) {
        AfegirLloc fragment = new AfegirLloc();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View afegirLlocFragmentView = inflater.inflate(R.layout.fragment_afegir_lloc, container, false);
        nomEditText = afegirLlocFragmentView.findViewById(R.id.nomEditText);
        adressEditText = afegirLlocFragmentView.findViewById(R.id.adressEditText);
        urlEditText = afegirLlocFragmentView.findViewById(R.id.urlEditText);
        comentariEditText = afegirLlocFragmentView.findViewById(R.id.comentariEditText);
        telefonEditText = afegirLlocFragmentView.findViewById(R.id.telefonEditText);
        longitudEditText = afegirLlocFragmentView.findViewById(R.id.longitudEditText);
        latitudEditText = afegirLlocFragmentView.findViewById(R.id.latitudEditText);
        valoracioEditText = afegirLlocFragmentView.findViewById(R.id.valoracioEditText);
        afegirLlocBtn = afegirLlocFragmentView.findViewById(R.id.afegirLlocBtn);
        afegirLlocBtn.setOnClickListener(this);
        bd = new LlocsBD(afegirLlocFragmentView.getContext());
        return afegirLlocFragmentView;
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
        switch (v.getId()){
            case R.id.afegirLlocBtn:
                bd.createLloc(new Lloc(0,
                        Integer.parseInt(telefonEditText.getText().toString()),
                        nomEditText.getText().toString(),
                        adressEditText.getText().toString(),
                        Double.parseDouble(longitudEditText.getText().toString()),
                        Double.parseDouble(latitudEditText.getText().toString()),
                        Float.parseFloat(valoracioEditText.getText().toString()),
                        comentariEditText.getText().toString(),
                        urlEditText.getText().toString(),
                        "uwu"
                        ));
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
