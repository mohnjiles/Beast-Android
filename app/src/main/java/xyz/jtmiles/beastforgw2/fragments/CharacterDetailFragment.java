package xyz.jtmiles.beastforgw2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.jtmiles.beastforgw2.R;
import xyz.jtmiles.beastforgw2.VerticalSpaceItemDecoration;
import xyz.jtmiles.beastforgw2.adapters.CharacterInfoAdapter;
import xyz.jtmiles.beastforgw2.models.Character;
import xyz.jtmiles.beastforgw2.util.Utils;

public class CharacterDetailFragment extends Fragment {

    @BindView(R.id.tvCharacterName)
    TextView tvCharacterName;
    @BindView(R.id.tvCharacterRaceClass)
    TextView tvCharacterRaceClass;
    @BindView(R.id.rvStats)
    RecyclerView rvStats;

    @BindView(R.id.ivClassIcon)
    ImageView ivClassIcon;


    private Character mCharacter;


    public CharacterDetailFragment() {
        // Required empty public constructor
    }

    public static CharacterDetailFragment newInstance(Character character) {
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("character", character);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCharacter = (Character) getArguments().getSerializable("character");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_character_detail, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvStats.setLayoutManager(layoutManager);

        tvCharacterName.setText(mCharacter.getName());
        tvCharacterRaceClass.setText(String.format(Locale.getDefault(), "%d %s %s", mCharacter.getLevel(), mCharacter.getRace(), mCharacter.getProfession()));
        ivClassIcon.setImageResource(Utils.getResourceIdByName(getActivity(), mCharacter.getProfession()));

        rvStats.addItemDecoration(new VerticalSpaceItemDecoration(32));
        rvStats.setAdapter(new CharacterInfoAdapter(mCharacter));


    }
}
