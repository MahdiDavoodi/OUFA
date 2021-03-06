package davoodi.mahdi.oufa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import davoodi.mahdi.oufa.R;
import davoodi.mahdi.oufa.components.Rank;
import davoodi.mahdi.oufa.data.FifaData;

public class SeasonResultsAdapter extends RecyclerView.Adapter<SeasonResultsAdapter.ViewHolder> {

    Context context;
    FifaData fifaData;
    ArrayList<Rank> ranks;

    public SeasonResultsAdapter(Context context) {
        this.context = context;
        readData();
    }

    private void readData() {
        // Ranks.
        fifaData = new FifaData(context);
        ranks = fifaData.getAllRanks();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.statistics_season_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rank rank = ranks.get(position);

        // Team Image.
        holder.team_image.setImageResource(context.getResources().getIdentifier("club" + rank.getClubID(),
                "drawable",
                context.getPackageName()));

        // Rank Info.

        holder.team_name.setText(fifaData.getClubFromID(rank.getClubID()).getClubName());
        holder.played.setText(String.valueOf(rank.getMatchesPlayed()));
        holder.win.setText(String.valueOf(rank.getWin()));
        holder.loss.setText(String.valueOf(rank.getLoss()));
        holder.draw.setText(String.valueOf(rank.getDraw()));
        holder.gd.setText(String.valueOf(rank.getGoalDifference()));
        holder.points.setText(String.valueOf(rank.getPoints()));

        // Color.
        if (fifaData.getClubFromID(rank.getClubID()).getClubOwner() == 1) {
            holder.color.setCardBackgroundColor(context.getResources().getColor(R.color.blue, context.getTheme()));
        } else {
            holder.color.setCardBackgroundColor(context.getResources().getColor(R.color.red, context.getTheme()));
        }
    }

    @Override
    public int getItemCount() {
        // Items number.
        return ranks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView color;
        TextView team_name, played, win, loss, draw, gd, points;
        ImageView team_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Widgets.
            color = itemView.findViewById(R.id.statistics_season_rank_color);
            played = itemView.findViewById(R.id.statistics_season_match_played);
            win = itemView.findViewById(R.id.statistics_season_win);
            loss = itemView.findViewById(R.id.statistics_season_loss);
            draw = itemView.findViewById(R.id.statistics_season_draw);
            gd = itemView.findViewById(R.id.statistics_season_goal_difference);
            points = itemView.findViewById(R.id.statistics_season_points);
            team_name = itemView.findViewById(R.id.statistics_season_team_name);
            team_image = itemView.findViewById(R.id.statistics_season_club_image);
        }
    }
}