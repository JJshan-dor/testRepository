package com.example.musicdemo.activitys;

import android.os.Bundle;

import com.example.musicdemo.R;
import com.example.musicdemo.adapters.MusicGridAdapter;
import com.example.musicdemo.adapters.MusicListAdapter;
import com.example.musicdemo.helps.RealmHelp;
import com.example.musicdemo.models.MusicSourceModel;
import com.example.musicdemo.views.GridSpaceItemDecoration;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity {

    private RecyclerView mRvGrid, mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;
    private RealmHelp mRealHelp;
    private MusicSourceModel mMusicSourceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData(){
        mRealHelp=new RealmHelp();
        mMusicSourceModel=mRealHelp.getMusicModel();

    }


    private void initView() {
        initNavBar(false, "音乐", true);

        mRvGrid = fd(R.id.rv_grid);
        mRvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        //分割线，默认1dp
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelOffset(R.dimen.albumMarginSize), mRvGrid));
        mRvGrid.setNestedScrollingEnabled(false);
        mGridAdapter = new MusicGridAdapter(this,mMusicSourceModel.getAlbum());
        mRvGrid.setAdapter(mGridAdapter);

        /**
         * 1.假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2.不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */
        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRvList.setNestedScrollingEnabled(false);
        mListAdapter = new MusicListAdapter(this,mRvList,mMusicSourceModel.getHot());
        mRvList.setAdapter(mListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealHelp.close();
    }
}
