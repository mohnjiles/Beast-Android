package xyz.jtmiles.beastforgw2.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.jtmiles.beastforgw2.R;
import xyz.jtmiles.beastforgw2.models.Character;
import xyz.jtmiles.beastforgw2.util.Utils;

/**
 * Created by JT on 5/16/2016.
 */
public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {

    private List<Character> mCharacterList;
    private Context mContext;

    public CharactersAdapter(Context context, List<Character> characterList) {

        mCharacterList = characterList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Character character = mCharacterList.get(position);

        holder.ivClassIcon.setImageResource(Utils.getResourceIdByName(mContext, character.getProfession()));
        holder.tvCharacterName.setText(character.getName());
        holder.tvCharacterInfo.setText(String.format(Locale.getDefault(), "%d %s %s",
                character.getLevel(), character.getRace(), character.getProfession()));

        switch(character.getProfession()) {
            case "Revenant":
                holder.rlLayout.setBackgroundColor(Color.parseColor("#360101"));
                break;
            case "Guardian":
                holder.rlLayout.setBackgroundColor(Color.parseColor("#264c55"));
                break;
            case "Engineer":
                holder.rlLayout.setBackgroundColor(Color.parseColor("#79492a"));
                break;
            case "Necromancer":
                holder.rlLayout.setBackgroundColor(Color.parseColor("#175235"));
                break;
            case "Elementalist":
                holder.rlLayout.setBackgroundColor(Color.parseColor("#530f14"));
                break;
            case "Thief":
                holder.rlLayout.setBackgroundColor(Color.parseColor("#533e41"));
                break;
            case "Warrior":
                holder.rlLayout.setBackgroundColor(Color.parseColor("#5a4b29"));
                break;
            case "Mesmer":
                holder.rlLayout.setBackgroundColor(Color.parseColor("#2e1933"));
                break;
            case "Ranger":
                holder.rlLayout.setBackgroundColor(Color.parseColor("#3a4c1d"));
                break;



        }
    }

    @Override
    public int getItemCount() {
        return mCharacterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivClassIcon)
        ImageView ivClassIcon;
        @BindView(R.id.tvCharacterName)
        TextView tvCharacterName;
        @BindView(R.id.tvCharacterInfo)
        TextView tvCharacterInfo;
        @BindView(R.id.rlLayout)
        RelativeLayout rlLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
