package nyc.c4q.mstmusic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by C4Q on 1/15/18.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

//    public List<GetArticles> listNews;
    GetArticles listNews;
    Context context;
    String link;

    public Adapter(GetArticles listNews, Context context) {
        this.listNews = listNews;
        this.context=context;
        Log.d(TAG, "Adapter: " + listNews.getStatus());
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + listNews.getStatus());
        holder.newsAuthor.setText(listNews.getArticles()[position].getAuthor()+"");
        holder.newsSummary.setText(listNews.getArticles()[position].getDescription()+"");
        holder.newsTitle.setText(listNews.getArticles()[position].getTitle()+"");
        String url= listNews.getArticles()[position].getUrlToImage();
        Picasso.with(context).load(url).fit().into(holder.newsBackground);
        link=listNews.getArticles()[position].getUrl();

        holder.prictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNews.getArticles().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsBackground;
        TextView newsTitle;
        TextView  newsSummary;
        TextView  newsAuthor;
        CardView prictureCard;
        public ViewHolder(View itemView) {
            super(itemView);
            newsBackground= (ImageView) itemView.findViewById(R.id.newsBackground);
            newsTitle=(TextView)itemView.findViewById(R.id.newsTitle);
            newsSummary=(TextView)itemView.findViewById(R.id.newsSummary);
            newsAuthor=(TextView)itemView.findViewById(R.id.newsAuthor);
            prictureCard=(CardView)itemView.findViewById(R.id.prictureCard);
        }
    }

}
