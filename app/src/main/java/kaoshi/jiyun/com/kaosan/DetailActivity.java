package kaoshi.jiyun.com.kaosan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImage;
    private TextView mText;
    private Button mBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        Intent intent = getIntent();
//       String str= intent.getStringExtra("mige");
        String str2 = intent.getStringExtra("text");
//        Picasso.with(this).load(str).into(mImage);
        mText.setText(str2);

    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.Image);
        mText = (TextView) findViewById(R.id.Textq);
        mBut = (Button) findViewById(R.id.But);
        mBut.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.But:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                UMImage image = new UMImage(DetailActivity.this, R.mipmap.qq);
                new ShareAction(DetailActivity.this)
                        .withMedia(image)
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        }).open();
                

                break;
        }
    }
}

