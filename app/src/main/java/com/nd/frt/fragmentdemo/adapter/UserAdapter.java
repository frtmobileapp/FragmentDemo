package com.nd.frt.fragmentdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nd.frt.fragmentdemo.R;
import com.nd.frt.fragmentdemo.fragment.DetailFragment;
import com.nd.frt.fragmentdemo.model.UserInfo;
import com.nd.frt.fragmentdemo.viewholder.UserViewHoldere;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHoldere> {

    private List<UserInfo> mUserInfos;

    public UserAdapter(List<UserInfo> userInfos) {
        mUserInfos = userInfos;
    }

    @NonNull
    @Override
    public UserViewHoldere onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = layoutInflater.inflate(R.layout.item_user, viewGroup, false);
        return new UserViewHoldere(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHoldere userViewHoldere, final int position) {
        final UserInfo userInfo = mUserInfos.get(position);
        Glide.with(userViewHoldere.itemView.getContext())
                .load(userInfo.avatarUrl)
                .into(userViewHoldere.mIvAvatar);
        userViewHoldere.mTvUserName.setText(userInfo.userName);
        userViewHoldere.mTvEmail.setText(userInfo.content);
        userViewHoldere.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = view.getContext();
                ((FragmentActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fldetail, DetailFragment.newInstance(userInfo,position))
                        .commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mUserInfos.size();
    }

    public void edit(UserInfo userInfo, int index) {
        mUserInfos.set(index,userInfo);
        notifyDataSetChanged();

    }
}
