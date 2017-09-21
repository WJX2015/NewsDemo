package com.example.lenovo_g50_70.newsdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo_g50_70.newsdemo.R;
import com.example.lenovo_g50_70.newsdemo.bean.Jokes;

import java.util.List;

/**
 * Created by wjx on 2017/7/29.
 */

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokesHolder> {

    private List<Jokes.ResultBean.DataBean> mDataBeen;

    public JokesAdapter(List<Jokes.ResultBean.DataBean> dataBeen) {
        mDataBeen = dataBeen;
    }

    @Override
    public JokesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jokes, null);
        JokesHolder holder = new JokesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(JokesHolder holder, int position) {
        holder.mTextView.setText(mDataBeen.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return (mDataBeen == null) ? 0 : mDataBeen.size();
    }

    static class JokesHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public JokesHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.jokes_text);
        }
    }

    public void setJokesDatas(List<Jokes.ResultBean.DataBean> resultBeen) {
        mDataBeen = resultBeen;
        notifyDataSetChanged();
    }

    public void addJokesDataInTop(List<Jokes.ResultBean.DataBean> resultBeen) {
        mDataBeen.addAll(0, resultBeen);
        notifyDataSetChanged();
    }

    public void addJokesDataInBottom(List<Jokes.ResultBean.DataBean> resultBeen) {
        mDataBeen.addAll(resultBeen);
        notifyDataSetChanged();
    }
}
