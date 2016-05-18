package xyz.jtmiles.beastforgw2.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import xyz.jtmiles.beastforgw2.R;
import xyz.jtmiles.beastforgw2.models.Character;
import xyz.jtmiles.beastforgw2.models.Guild;
import xyz.jtmiles.beastforgw2.services.GuildService;
import xyz.jtmiles.beastforgw2.util.Utils;

/**
 * Created by JT on 5/17/2016.
 */
public class CharacterInfoAdapter extends RecyclerView.Adapter<CharacterInfoAdapter.ViewHolder> {

    private Character mCharacter;

    public CharacterInfoAdapter(Character character) {
        mCharacter = character;
    }

    @Override
    public CharacterInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_info_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CharacterInfoAdapter.ViewHolder holder, int position) {
        switch(position){
            case 0:
                holder.ivInfoImage.setImageResource(R.drawable.skull);
                holder.tvTitle.setText("Deaths");
                holder.tvContent.setText(String.valueOf(mCharacter.getDeaths()));
                holder.rlLayout.setBackgroundColor(Color.parseColor("#b71c1c"));
                break;
            case 1:
                holder.ivInfoImage.setImageResource(R.drawable.ic_timer_white_48dp_2x);
                holder.tvTitle.setText("Time Played");
                holder.tvContent.setText(String.format(Locale.getDefault(), "%d Hours", mCharacter.getAge() / 60 / 60));
                holder.rlLayout.setBackgroundColor(Color.parseColor("#311b92"));
                break;
            case 2:
                holder.ivInfoImage.setImageResource(R.drawable.ic_people_white_48dp_2x);
                holder.tvTitle.setText("Guild");
                Retrofit retrofit = Utils.getRetrofit(false);
                GuildService guildService = retrofit.create(GuildService.class);
                holder.rlLayout.setBackgroundColor(Color.parseColor("#F9A825"));

                guildService.getGuildDetails(String.format("https://api.guildwars2.com/v1/guild_details.json?guild_id=%s", mCharacter.getGuild())).enqueue(new Callback<Guild>() {
                    @Override
                    public void onResponse(Call<Guild> call, Response<Guild> response) {
                        if (response.isSuccessful()){
                            Guild guild = response.body();
                            holder.tvContent.setText(String.format(Locale.getDefault(), "%s [%s]", guild.getGuildName(), guild.getTag()));

                        }
                    }

                    @Override
                    public void onFailure(Call<Guild> call, Throwable t) {
                        Log.w("CharacterDetailFrag", t.toString());
                    }
                });
                break;
            case 3:
                holder.ivInfoImage.setImageResource(R.drawable.ic_add_white_48dp_2x);
                holder.tvTitle.setText("Created");
                holder.rlLayout.setBackgroundColor(Color.parseColor("#1b5e20"));

                PrettyTime t = new PrettyTime();
                List<Duration> durations = t.calculatePreciseDuration(Utils.getCalendarFromISO(mCharacter.getCreated()).getTime());
                try {
                    durations.remove(durations.size() - 1);
                    durations.remove(durations.size() - 2);
                    durations.remove(durations.size() - 3);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    Log.w("CharacterInfoAdapter", ex);
                }

                holder.tvContent.setText(String.format("%s", t.format(durations)));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivInfoImage)
        ImageView ivInfoImage;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.rlLayout)
        RelativeLayout rlLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
