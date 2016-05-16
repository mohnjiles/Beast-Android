package xyz.jtmiles.beastforgw2.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.jtmiles.beastforgw2.R;
import xyz.jtmiles.beastforgw2.util.Constants;
import xyz.jtmiles.beastforgw2.util.Utils;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    @BindView(R.id.tvApiKeyHelp)
    TextView tvApiKeyHelp;
    @BindView(R.id.etApiKey)
    EditText etApiKey;

    @OnClick(R.id.btnSaveApiKey) void SaveApiKey() {
        Utils.getSharedPrefs(getActivity())
                .edit()
                .putString(Constants.API_KEY_PREF, etApiKey.getText().toString())
                .commit();
        Snackbar.make(tvApiKeyHelp, "API Key saved successfully.", Snackbar.LENGTH_LONG).show();
    }

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvApiKeyHelp.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
