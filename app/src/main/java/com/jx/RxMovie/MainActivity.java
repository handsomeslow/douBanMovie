package com.jx.RxMovie;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jx.RxMovie.dbMovie.DBMovie;
import com.jx.RxMovie.dbMovie.Data;
import com.jx.RxMovie.dbMovie.MovieAdapter;
import com.jx.RxMovie.service.MethodsForMovie;
import com.jx.RxMovie.service.MethodsForToh;
import com.jx.RxMovie.tohContent.TohEntity;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {
    @BindView(R.id.movie_list)
    RecyclerView movieList;
    @BindView(R.id.bt_get_toh)
    Button btGetToh;
    @BindView(R.id.bt_get_topmovie)
    Button btGetMovie;
    @BindView(R.id.edit_query_movie)
    EditText editQueryMovie;
    @BindView(R.id.bt_search_movie)
    Button btSearchMovie;
    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout swipeLy;

    long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView(this);
        initImageLoader(this);
        loadInitialData();
    }

    private void initView(Context context) {
        movieList.setLayoutManager(new LinearLayoutManager(context));
        movieList.setFocusable(true);
        //swipeLy.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeLy.setEnabled(false);
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .memoryCache(new LruMemoryCache(100 * 1024 * 1024))
                //.memoryCache(new WeakMemoryCache())
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .defaultDisplayImageOptions(new DisplayImageOptions.Builder()
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .showImageForEmptyUri(R.drawable.nullpic)
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .build())
                .denyCacheImageMultipleSizesInMemory()
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    private void getTopMovie() {
        swipeLy.setRefreshing(true);
        Subscriber<DBMovie> subscriber = new Subscriber<DBMovie>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(DBMovie dbMovie) {
                updateView(dbMovie);
                /*
                adapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View view, int position) {
                        Toast.makeText(MainActivity.this,"Item:" + position,Toast.LENGTH_LONG).show();
                    }
                });
                */

            }
        };
        MethodsForMovie.getInstance().getTopMovie(0, 20, subscriber);
    }

    private void getTheaterMovie() {
        swipeLy.setRefreshing(true);
        MethodsForMovie.getInstance().getTheaterMovie("上海", 0, 20,
                new Action1<DBMovie>() {
                    @Override
                    public void call(DBMovie dbMovie) {
                        updateView(dbMovie);
                    }
                });
    }

    private void getComingSoonMovie() {
        swipeLy.setRefreshing(true);
        MethodsForMovie.getInstance().getComingMovie(0, 20,
                new Action1<DBMovie>() {
                    @Override
                    public void call(DBMovie dbMovie) {
                        updateView(dbMovie);
                    }
                });
    }

    private void searchMovie() {
        String titleStr = editQueryMovie.getText().toString();
        if (!titleStr.isEmpty()){
            swipeLy.setRefreshing(true);
            MethodsForMovie.getInstance().getMoiveTitle(titleStr, 0, 10,
                    new Action1<List<String>>() {
                        @Override
                        public void call(List<String> strings) {
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.cell_search_list, strings);
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setAdapter(adapter, null)
                                    .setTitle("搜索结果");
                            swipeLy.setRefreshing(false);
                            dialog.setPositiveButton("确定", null);
                            dialog.show();
                        }
                    });
        }
        else
            Toast.makeText(MainActivity.this, "请输入电影名称", Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.bt_get_toh, R.id.bt_get_topmovie, R.id.bt_get_theatermovie, R.id.bt_search_movie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get_toh:
                getComingSoonMovie();
                break;
            case R.id.bt_get_topmovie:
                getTopMovie();
                break;
            case R.id.bt_get_theatermovie:
                getTheaterMovie();
                break;
            case R.id.bt_search_movie:
                searchMovie();
                break;
        }
    }

    private void getToh() {
        Subscriber<TohEntity> subscriber = new Subscriber<TohEntity>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this,"加载失败",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onNext(TohEntity tohEntity) {
                Toast.makeText(MainActivity.this, "加载成功", Toast.LENGTH_LONG).show();
            }
        };
        MethodsForToh.getInstance().getTohDetail(subscriber, "1.0", 7, 8);
    }


    private void loadInitialData(){
        swipeLy.setRefreshing(true);
        Data.getInstance().subscribeData(new Observer<DBMovie>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this,"加载失败",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(DBMovie dbMovie) {
                updateView(dbMovie);
                //Toast.makeText(MainActivity.this,Data.getInstance().getDataSourceText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateView(DBMovie data){
        getSupportActionBar().setTitle(data.getTitle());
        MovieAdapter adapter = new MovieAdapter(MainActivity.this, data);
        movieList.setAdapter(adapter);
        swipeLy.setRefreshing(false);
        //Toast.makeText(MainActivity.this,"加载成功，来自" + Data.getInstance().getDataSourceText(),Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this,"加载成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
        {
            if((System.currentTimeMillis()-exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            else
            {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_test:
                Log.v("XXXXXXXXXXXXX",MethodsForMovie.getInstance().subscriptions.isUnsubscribed()+"");
                MethodsForMovie.getInstance().unSubscribe();
                Log.v("XXXXXXXXXXXXX2",MethodsForMovie.getInstance().subscriptions.isUnsubscribed()+"");
                break;
            case R.id.action_again_subscribe:
                MethodsForMovie.getInstance().reSubscribe();
                break;
            case R.id.action_add_localpic:
                MethodsForMovie.getInstance().getMovieWithLoaclPic(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> list) {

                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
