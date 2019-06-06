package com.nd.frt.fragmentdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nd.frt.fragmentdemo.R;
import com.nd.frt.fragmentdemo.fragment.DetailFragment;
import com.nd.frt.fragmentdemo.model.UserInfo;
import com.nd.frt.fragmentdemo.viewholder.UserViewHolder;


import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<UserInfo> mUserInfos;
    private int REQUEST_EDIT_USER_INFO = 0x1001;

    public UsersAdapter(List<UserInfo>userInfos) {
        mUserInfos = userInfos;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_user, viewGroup, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, final int position) {
        final UserInfo userInfo =  mUserInfos.get(position);
        Glide.with(userViewHolder.itemView.getContext())
                .load(userInfo.avatarUrl)
                .into(userViewHolder.mIvatar);
        userViewHolder.mtvUserName.setText(userInfo.userName);
        userViewHolder.mtvEmail.setText(userInfo.content);
        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                ((FragmentActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flDetail, DetailFragment.newInstance(userInfo,position))
                        .commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mUserInfos.size();
    }

    public void edit(UserInfo userInfo, int index) {
        mUserInfos.set(index, userInfo);
        notifyDataSetChanged();
    }
}
