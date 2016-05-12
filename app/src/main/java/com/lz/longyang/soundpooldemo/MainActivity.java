package com.lz.longyang.soundpooldemo;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button btn_newqqmsg;
    private SoundPool pool;
    private Map<String, Integer> poolMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_newqqmsg = (Button) findViewById(R.id.btn_newqqmsg);
      /*  btn_newweibontf = (Button) findViewById(R.id.btn_newweibontf);
        btn_newweibotoast = (Button) findViewById(R.id.btn_newweibotoast);*/

        poolMap = new HashMap<String, Integer>();//容器
        // 实例化SoundPool，大小为3
        pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        // 装载音频进音频池，并且把ID记录在Map中
       // poolMap.put("newqqmsg", pool.load(this, R.raw.Baby1, 1));
      //  poolMap.put("newweibontf", pool.load(this, R.raw.Baby2, 1));
        poolMap.put("newweibotoast", pool.load(this, R.raw.ceshi, 1));

        pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                // 每次装载完成均会回调
               // Log.i("main", "音频池资源id为：" + sampleId + "的资源装载完成");
                // 当前装载完成ID为map的最大值，即为最后一次装载完成
                if (sampleId == poolMap.size()) {
                   // Toast.makeText(MainActivity.this, "加载声音池完成!",
                           // Toast.LENGTH_SHORT).show();
                    btn_newqqmsg.setOnClickListener(click);
                    //btn_newweibontf.setOnClickListener(click);
                   // btn_newweibotoast.setOnClickListener(click);
                    // 进入应用播放四次声音
                    pool.play(poolMap.get("newweibotoast"), 1.0f, 1.0f, 0, 3,
                            1.0f);
                }
            }
        });
    }

    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_newqqmsg:
                    if (pool != null) {
                        pool.play(poolMap.get("newweibotoast"), 1.0f, 1.0f, 0, 0, 1.0f);
                    }
                    break;
               /* case R.id.btn_newweibontf:
                    if (pool != null) {
                        pool.play(poolMap.get("newweibontf"), 1.0f, 1.0f, 0, 0,
                                1.0f);
                    }
                    break;
                case R.id.btn_newweibotoast:
                    if (pool != null) {
                        pool.play(poolMap.get("newweibotoast"), 1.0f, 1.0f, 0, 0,
                                1.0f);
                    }
                    break;*/
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        // 销毁的时候释放SoundPool资源
        if (pool != null) {
            pool.release();
            pool = null;
        }
        super.onDestroy();
    }
}