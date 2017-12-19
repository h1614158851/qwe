package kaoshi.jiyun.com.kaosan;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import kaoshi.jiyun.com.kaosan.adapter.MyAdapter;
import kaoshi.jiyun.com.kaosan.bean.MyDemo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainPageActivity extends AppCompatActivity {
    private RecyclerView mRecy;
    private String urls = "http://v.juhe.cn/weixin/query?key=a332c6b34264527ac142764eaed9364d&pno=1";
    private List<MyDemo.ResultBean.ListBean> mList;

    private MyAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String s= (String) msg.obj;
            Gson gson=new Gson();
            MyDemo myDemo=gson.fromJson(s,MyDemo.class);
            mList=myDemo.getResult().getList();
            MyAdapter myAdapter= new MyAdapter(mList,MainPageActivity.this);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainPageActivity.this);
            mRecy.setLayoutManager(linearLayoutManager);
            mRecy.setAdapter(myAdapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        initView();
        getinof();

    }

    private void initView() {
        mRecy = (RecyclerView) findViewById(R.id.Recy);

    }

    private void getinof() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request request=new Request.Builder().url(urls).build();
                    Response response=okHttpClient.newCall(request).execute();
                    String str=response.body().string();
                    Message message=new Message();
                    message.obj=str;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
