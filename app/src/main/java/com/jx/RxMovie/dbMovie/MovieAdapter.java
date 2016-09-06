package com.jx.RxMovie.dbMovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jx.RxMovie.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junxu on 2016/7/18.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private DBMovie data;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public MovieAdapter(Context context, DBMovie data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).
                inflate(R.layout.cell_movie_detail, parent, false);
        ViewHolder myVH = new ViewHolder(itemView);
        return myVH;
    }
    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.movieTitle.setText(data.getSubjects()[position].getTitle());
        holder.movieYear.setText(data.getSubjects()[position].getYear());
        holder.movierating.setText("评分：" + data.getSubjects()[position].getRating().getAverage());
        holder.movieRatingBar.setRating((float) data.getSubjects()[position].getRating().getAverage()/2);
        holder.movieGenres.setText(" / "+data.getSubjects()[position].getGenresString()
                .substring(1,data.getSubjects()[position].getGenresString().length()-1));
        if (data.getSubjects()[position].getDirectors().length>0)
            holder.movieDirectors.setText("导演：" + data.getSubjects()[position].getDirectors()[0].getName());
        holder.movieCasts.setText("演员：" + data.getSubjects()[position].getCastsStr());

        holder.movieTitleOriginal.setText(data.getSubjects()[position].getOriginal_title());
        imageLoader.displayImage(data.getSubjects()[position].getImages().getLarge(),
                holder.moviePic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Item:" + position,Toast.LENGTH_SHORT).show();
                //mOnItemClickListener.OnItemClick(holder.itemView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.getCount();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_pic)
        ImageView moviePic;
        @BindView(R.id.movie_title)
        TextView movieTitle;
        @BindView(R.id.movie_rating)
        TextView movierating;
        @BindView(R.id.movie_year)
        TextView movieYear;
        @BindView(R.id.movie_genres)
        TextView movieGenres;
        @BindView(R.id.movie_rating_bar)
        RatingBar movieRatingBar;
        @BindView(R.id.movie_directors)
        TextView movieDirectors;
        @BindView(R.id.movie_casts)
        TextView movieCasts;
        @BindView(R.id.movie_title_original)
        TextView movieTitleOriginal;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
