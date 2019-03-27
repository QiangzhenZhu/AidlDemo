package com.hzdongcheng.aidldemo.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hzdongcheng.aidldemo.R;
import com.hzdongcheng.aidldemo.bean.BoxStatus;

import java.util.List;

/**
 * Created by zzq on 2018/9/13
 **/
public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.ViewHolder> implements View.OnClickListener {
    private List<BoxStatus> boxStatusList;
    private BoxOnClickListner boxOnClickListner;

    public BoxAdapter(List<BoxStatus> boxStatusList,BoxOnClickListner boxOnClickListner){
        this.boxStatusList = boxStatusList;
        this.boxOnClickListner = boxOnClickListner;
    }

    @NonNull
    @Override
    public BoxAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_button,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoxAdapter.ViewHolder viewHolder, final int i) {
        BoxStatus status = boxStatusList.get(i);
        viewHolder.mBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boxOnClickListner.onClick(i);
            }
        });
        viewHolder.mBox.setText((i+1)+"\n号箱");
        //门关无物
        if (status.getOpenStatus() == 0 && status.getGoodsStatus() == 0){
            viewHolder.mBox.setBackgroundColor(Color.GREEN);
        }
        //门关有物
        if (status.getOpenStatus() == 0&& status.getGoodsStatus() == 1){
            viewHolder.mBox.setBackgroundColor(Color.RED);
        }
        //门开无物
        if (status.getOpenStatus() == 1 && status.getGoodsStatus() == 0){
            viewHolder.mBox.setBackgroundColor(Color.YELLOW);
        }
        //门开有物
        if (status.getOpenStatus() == 1 && status.getGoodsStatus() == 1){
            viewHolder.mBox.setBackgroundColor(Color.BLUE);
        }
    }



    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button mBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBox = itemView.findViewById(R.id.button);

        }
    }

    public interface BoxOnClickListner{
        public void  onClick(int i);
    }
}
