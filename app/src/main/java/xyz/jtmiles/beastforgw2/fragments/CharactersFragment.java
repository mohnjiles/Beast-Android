package xyz.jtmiles.beastforgw2.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.inject.Inject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import roboguice.fragment.RoboFragment;
import xyz.jtmiles.beastforgw2.R;
import xyz.jtmiles.beastforgw2.managers.CharactersManager;
import xyz.jtmiles.beastforgw2.models.Character;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CharactersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharactersFragment extends RoboFragment {

    @BindView(R.id.rvCharacters)
    RecyclerView rvCharacters;

    @Inject
    CharactersManager charactersManager;

    public CharactersFragment() {
        // Required empty public constructor
    }

    public static CharactersFragment newInstance() {
        return new CharactersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_characters, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        charactersManager.getAllCharacters(new Callback<List<Character>>() {
            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                if (response.isSuccessful()){
                    StringBuilder sb = new StringBuilder();

                    for (Character character : response.body()) {
                        sb.append(character.getName());
                        sb.append(", ");
                    }

                    Log.d("CharactersFragment", sb.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {

            }
        });


    }



}
