package com.example.asus.rikao20181007.ui;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.rikao20181007.R;
import com.example.asus.rikao20181007.data.InfoBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VooHoldel> {
   Context  context;
   List<InfoBean.DataBean> data ;
    OnItemClickLisentener onItemClickLisenter;

    //1.声明接口对象
   public interface OnItemClickLisentener {
        //2.声明条目点击方法
        void onItemClick(int layoutPosition);
    }

    //3.声明方法，进行接口对象的传入
    public void setOnItemClickLisenter(OnItemClickLisentener onItemClickLisenter) {
        this.onItemClickLisenter = onItemClickLisenter;
    }

    OnItemLongClickLisenter onItemLongClickLisenter;

    public interface OnItemLongClickLisenter {
        void onItemLongClick(int position);
    }

    public void setOnItemLongClickLisenter(OnItemLongClickLisenter onItemLongClickLisenter) {
        this.onItemLongClickLisenter = onItemLongClickLisenter;
    }

    public MainAdapter(Context context, List<InfoBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VooHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_itme, parent, false);
        VooHoldel vooHoldel = new VooHoldel(view);
        return vooHoldel;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHoldel holder, int position) {
        holder.itmetext.setText(data.get(position).getName());
        String images = data.get(position).getIcon();
        String[] split = images.split("\\|");
        Uri parse = Uri.parse(split[0]);
        holder.itmeimag.setImageURI(parse);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class VooHoldel extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        private SimpleDraweeView itmeimag;
        private  TextView itmetext;

        public VooHoldel(View itemView) {
            super(itemView);
            itmeimag = itemView.findViewById(R.id.itme_img);
            itmetext = itemView.findViewById(R.id.itme_text);
            itemView.setOnLongClickListener(this);

        }
//
//        @Override
//        public void onClick(View view) {
//
//            int layoutPosition = getLayoutPosition();
//            onItemClickLisenter.onItemClick(layoutPosition);
//        }

        @Override
        public boolean onLongClick(View view) {
            int position = getLayoutPosition();
            onItemLongClickLisenter.onItemLongClick(position);
            return false;
        }
    }


}
