package com.example.musicdemo.activitys.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.example.musicdemo.R;
import com.example.musicdemo.activitys.WelcomeActivity;
import com.example.musicdemo.helps.MediaPlayerHelp;
import com.example.musicdemo.models.MusicModel;

/**
 * 1.通过service连接playMusic和MediaPlayHelp
 * 2.Play Music View -- service
 *      1.播放音乐和暂停音乐
 *      2.启动service，绑定service，解除绑定service
 * 3.MediaPlayHelp--service
 *      1.播放音乐，暂停音乐
 *      2.监听音乐播放完成，停止service
 */
public class MusicService extends Service {

    private MediaPlayerHelp mMediaPlayerHelp;
    private MusicModel mMusicModel;
    //不可为0
    public static final int NOTIFICATION_ID=1;

    public MusicService() {
    }

    public class MusicBind extends Binder{
        /**
         * 设置音乐（musicModel)
         */

        public void setMusic(MusicModel musicModel ){
            mMusicModel=musicModel;
            startForeground();
        }

        /**
         * 播放音乐
         */

        public void playMusic(){
            /**
             * 1.判断当前音乐是否是已经在播放的音乐
             * 2.如果当前的音乐是已经在播放的音乐，那么就直接执行start方法
             * 3.如果当前播放的音乐不是需要播放的音乐，调用setpath方法
             */
            if (mMediaPlayerHelp.getPath() != null
                    && mMediaPlayerHelp.getPath().equals(mMusicModel.getPath())) {
                mMediaPlayerHelp.start();

            } else {
                mMediaPlayerHelp.setPath(mMusicModel.getPath());
                mMediaPlayerHelp.setOnMeidaPlayerHelperListener(new MediaPlayerHelp.OnMeidaPlayerHelperListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayerHelp.start();
                    }

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopSelf();
                    }
                });
            }
        }

        /**
         * 暂停播放
         */

        public void stopMusic(){
            mMediaPlayerHelp.pause();
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBind();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayerHelp=MediaPlayerHelp.getInstance(this);
    }

    /**
     * 系统默认不允许不可见的服务播放音乐，
     * Notification,
     */
    /**
     * 设置服务在前台可见
     */
    private void startForeground(){

        /**
         * 通知栏点击跳转的intent
         */
        PendingIntent pendingIntent=PendingIntent.
                getActivity(this,0,new Intent(this, WelcomeActivity.class),PendingIntent.FLAG_CANCEL_CURRENT);

        /**
         * 创建Notification
         */
        Notification notification=new Notification.Builder(this)
                .setContentTitle(mMusicModel.getName())
                .setContentText(mMusicModel.getAuthor())
                .setSmallIcon(R.mipmap.logo)
                .setContentIntent(pendingIntent)
                .build();
        /**
         * 设置notification在前台展示
         */
        startForeground(NOTIFICATION_ID,notification);
    }

}












