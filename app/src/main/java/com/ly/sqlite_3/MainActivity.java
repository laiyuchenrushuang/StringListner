package com.ly.sqlite_3;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et_text;
    TextView tv_text;
    String flag = "";
    StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_text = findViewById(R.id.et_text);
        tv_text = findViewById(R.id.tv_text);
        new ListenerStringThread().start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(100);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sb.append("开始->");
                        et_text.setText(sb.toString());
                    }
                });
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(1100);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sb.append("进行->");
                        et_text.setText(sb.toString());
                    }
                });
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sb.append("结束->");
                        et_text.setText(sb.toString());
                    }
                });
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(3000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sb.append("以后可以用文字来显示进度->");
                        et_text.setText(sb.toString());
                    }
                });
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(4000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sb.append("开心......");
                        et_text.setText(sb.toString());
                    }
                });
            }
        }).start();
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private class ListenerStringThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                if (!flag.equals(et_text.getText().toString())) {
                    Message msg = new Message();
                    msg.obj = et_text.getText().toString();
                    mHandler.sendMessage(msg);
                }
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            flag = et_text.getText().toString();
            tv_text.setText("监听到字符串变化\n" + "变更的内容:" + msg.obj);
        }
    };
}
