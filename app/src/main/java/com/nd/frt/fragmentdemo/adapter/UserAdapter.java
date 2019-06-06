package com.nd.frt.fragmentdemo.adapter;

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
import com.nd.frt.fragmentdemo.viewholder.UserViewHoler;
import java.util.List;

public class UserAdapter<userviewholder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<userviewholder> {
    private List<UserInfo> muserInfos;
    private UserViewHoler userViewHoler;
    public UserAdapter(List<UserInfo> userInfos) {
        muserInfos = userInfos;
    }

    @NonNull
    @Override
    public userviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater Layoutinflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = Layoutinflater.inflate(R.layout.item_user, viewGroup, false);
        return (userviewholder) new UserViewHoler(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull userviewholder userviewholder, int i) {

        final int position = 0;
        final UserInfo userInfo = muserInfos.get(position);
        Glide.with(userViewHoler.itemView.getContext())
                .load(userInfo.avatarUrl)
                .into(userViewHoler.mIvAvatar);
        userViewHoler.mTvUserName.setText(userInfo.userName);
        userViewHoler.mTvEmail.setText(userInfo.content);
        userViewHoler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                ((FragmentActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flDetail, DetailFragment.newInstance(userInfo, position))
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return muserInfos.size();
    }
    public void edit(UserInfo userInfo, int index) {
        muserInfos.set(index, userInfo);
        notifyDataSetChanged();
    }
}
