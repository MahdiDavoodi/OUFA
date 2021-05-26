package davoodi.mahdi.fifa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import davoodi.mahdi.fifa.R;
import davoodi.mahdi.fifa.components.Cup;
import davoodi.mahdi.fifa.data.ClubsData;
import davoodi.mahdi.fifa.data.CupsData;
import davoodi.mahdi.fifa.data.LeaguesData;


public class CupsListAdapter extends RecyclerView.Adapter<CupsListAdapter.ViewHolder> {

    Context context;
    CupsData cupsData;
    ArrayList<Cup> cups;
    ClubsData clubsData;
    LeaguesData leaguesData;

    public CupsListAdapter(Context context) {
        this.context = context;
        readData();
    }

    private void readData() {
        // Owners.
        clubsData = new ClubsData(context);
        leaguesData = new LeaguesData(context);
        cupsData = new CupsData(context);
        cups = cupsData.getAllCups();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cups_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Cup cup = cups.get(position);

        // Images.
        holder.logo.setImageResource(context.getResources().getIdentifier("club" + cup.getWinnerID(),
                "drawable",
                context.getPackageName()));

        // Text.
        holder.season.setText(context.getResources().getString(R.string.season, cup.getSeasonID()));
        holder.winner.setText(context.getResources().getString(R.string.winner, clubsData.getClubFromID(cup.getWinnerID()).getClubName()));
        holder.title.setText(leaguesData.getLeagueFromID(cup.getLeagueID()).getLeagueName());
    }

    @Override
    public int getItemCount() {
        // Items number.
        return cups.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;
        TextView title, season, winner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Widgets.
            logo = itemView.findViewById(R.id.cups_image);
            title = itemView.findViewById(R.id.cups_titleText);
            season = itemView.findViewById(R.id.cups_seasonText);
            winner = itemView.findViewById(R.id.cups_winnerText);
        }
    }
}