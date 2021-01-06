package com.example.musicdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.musicdemo.R;
import com.example.musicdemo.activitys.PlayMusicActivity;
import com.example.musicdemo.models.MusicModel;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalcaulationRvHeight;
    private List<MusicModel> mDatasource;

    public MusicListAdapter(Context context, RecyclerView recyclerView, List<MusicModel> datasource) {
        mContext = context;
        mRv = recyclerView;
        this.mDatasource = datasource;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_music, viewGroup, false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        setRecyclerViewHeight();

        final MusicModel musicModel=mDatasource.get(position);


        Glide.with(mContext)
                .load(musicModel.getPoster())
                .into(viewHolder.ivIcon);

        viewHolder.tvName.setText(musicModel.getName());
        viewHolder.tvAuthor.setText(musicModel.getAuthor());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlayMusicActivity.class);
                intent.putExtra(PlayMusicActivity.MUSIC_ID,musicModel.getMusicId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatasource.size();
    }

    /**
     * 1。获取item View的高度，
     * 2.获取item View的数量，
     * 3.使用itemViewHeight*itemViewNum=RecyclerView的高度
     */
    private void setRecyclerViewHeight() {

        if (isCalcaulationRvHeight || mRv == null) return;

        isCalcaulationRvHeight = true;
        //获取itemView的高度
        RecyclerView.LayoutParams itemViewLp = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        //获取itemView的数量
        int itemCount = getItemCount();
        //使用itemViewHeight*itemViewNum=RecyclerView的高度
        int recyclerViewHeight = itemViewLp.height * itemCount;
        //设置recyclerView的高度
        LinearLayout.LayoutParams rvlp = (LinearLayout.LayoutParams) mRv.getLayoutParams();
        rvlp.height = recyclerViewHeight;
        mRv.setLayoutParams(rvlp);


    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        ImageView ivIcon;
        TextView tvName, tvAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAuthor=itemView.findViewById(R.id.tv_author);

        }
    }


}
