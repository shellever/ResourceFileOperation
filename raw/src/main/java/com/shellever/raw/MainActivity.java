package com.shellever.raw;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mContentTv;
    private Button mReadLineBtn;
    private Button mReadByteBtn;
    private Button mStartBtn;
    private Button mResetBtn;
    private Button mPauseBtn;

    private MediaPlayer mMediaPlayer;
    private boolean isPause = false;
    private boolean isCompletion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContentTv = (TextView) findViewById(R.id.tv_content);
        mReadLineBtn = (Button) findViewById(R.id.btn_read_line);
        mReadByteBtn = (Button) findViewById(R.id.btn_read_byte);
        mStartBtn = (Button) findViewById(R.id.btn_start);
        mResetBtn = (Button) findViewById(R.id.btn_reset);
        mPauseBtn = (Button) findViewById(R.id.btn_pause);

        mReadLineBtn.setOnClickListener(this);
        mReadByteBtn.setOnClickListener(this);
        mStartBtn.setOnClickListener(this);
        mResetBtn.setOnClickListener(this);
        mPauseBtn.setOnClickListener(this);

        initMediaPlayer(R.raw.traveling_light);
    }

    @Override
    public void onClick(View v) {
        String status;
        switch (v.getId()) {
            case R.id.btn_read_line:
                mContentTv.setText(getResourceFromRawByReadLine(R.raw.vocabulary));
                break;
            case R.id.btn_read_byte:
                mContentTv.setText(getResourceFromRawByReadByte(R.raw.joy));
                break;
            case R.id.btn_start:
                status = getResources().getString(R.string.text_media_player) + getResources().getString(R.string.text_start);
                mContentTv.setText(status);
                start();
                break;
            case R.id.btn_reset:
                status = getResources().getString(R.string.text_media_player) + getResources().getString(R.string.text_start);
                mContentTv.setText(status);
                reset();
                break;
            case R.id.btn_pause:
                status = getResources().getString(R.string.text_media_player) + getResources().getString(R.string.text_pause);
                mContentTv.setText(status);
                pause();
                break;
        }
    }

    public String getResourceFromRawByReadLine(int resId) {
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        String result = "";
        try {
            inputReader = new InputStreamReader(getResources().openRawResource(resId));
            bufReader = new BufferedReader(inputReader);
            String line;
            while ((line = bufReader.readLine()) != null) {
                result += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputReader != null) {
                    inputReader.close();
                }
                if (bufReader != null) {
                    bufReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getResourceFromRawByReadByte(int resId) {
        String result = "";
        InputStream in = null;
        ByteArrayOutputStream bos = null;
        try {
            in = getResources().openRawResource(resId);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[10 * 1024];    // 10kB
            int len;
            while ((len = in.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            result = bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void initMediaPlayer(int resId) {
        mMediaPlayer = MediaPlayer.create(this, resId);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isCompletion = true;
            }
        });
    }

    public void start() {
        if (isPause) {
            mMediaPlayer.start();
            isPause = false;
        } else if (isCompletion) {
            mMediaPlayer.start();
            isCompletion = false;
        } else{
            mMediaPlayer.start();
        }
    }

    public void reset() {
        mMediaPlayer.seekTo(0);
        if(isPause) {
            mMediaPlayer.start();
        }
        // mMediaPlayer.sout + tab => System.out.println(mMediaPlayer);
    }

    public void pause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            isPause = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
        }
    }
}
