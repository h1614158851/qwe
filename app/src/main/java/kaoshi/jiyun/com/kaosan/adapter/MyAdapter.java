package kaoshi.jiyun.com.kaosan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kaoshi.jiyun.com.kaosan.DetailActivity;
import kaoshi.jiyun.com.kaosan.R;
import kaoshi.jiyun.com.kaosan.bean.MyDemo;

/**
 * Created by 何启明 on 2017/12/6.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyDemo.ResultBean.ListBean> mList;
    private Context context;
    private final static int ITEM_ONE = 1;
    private final static int ITEM_TWO = 2;

    public MyAdapter(List<MyDemo.ResultBean.ListBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return ITEM_ONE;
        } else {
            return ITEM_TWO;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case ITEM_ONE:
                view = LayoutInflater.from(context).inflate(R.layout.adapter_one, parent, false);
                holder = new holderOne(view);

                break;
            case ITEM_TWO:
                view = LayoutInflater.from(context).inflate(R.layout.adapter_two, parent, false);
                holder = new holderTwo(view);

                break;


        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case ITEM_ONE:
                holderOne holderOne = (MyAdapter.holderOne) holder;
                holderOne.mText.setText(mList.get(position).getTitle());
                Picasso.with(context).load(mList.get(position).getFirstImg()).into(holderOne.mimage);

                break;
            case ITEM_TWO:
                holderTwo holderTwo = (MyAdapter.holderTwo) holder;
                holderTwo.mText2.setText(mList.get(position).getTitle());
                Picasso.with(context).load(mList.get(position).getFirstImg()).into(holderTwo.mimage2);
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("mige", mList.get(position).getFirstImg());
                intent.putExtra("text", mList.get(position).getTitle());
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.isEmpty() ? 0 : mList.size();
    }

    class holderOne extends RecyclerView.ViewHolder {
        private ImageView mimage;
        private TextView mText;

        public holderOne(View itemView) {
            super(itemView);
            mimage = itemView.findViewById(R.id.image_one);
            mText = itemView.findViewById(R.id.text_one);
        }
    }

    class holderTwo extends RecyclerView.ViewHolder {
        private ImageView mimage2;
        private TextView mText2;

        public holderTwo(View itemView) {
            super(itemView);
            mimage2 = itemView.findViewById(R.id.image_two);
            mText2 = itemView.findViewById(R.id.text_two);
        }
    }
}
