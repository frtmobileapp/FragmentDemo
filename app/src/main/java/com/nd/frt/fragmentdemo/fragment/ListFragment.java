package com.nd.frt.fragmentdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nd.frt.fragmentdemo.R;
import com.nd.frt.fragmentdemo.adapter.UserAdapter;
import com.nd.frt.fragmentdemo.cevise.UserInfoService;
import com.nd.frt.fragmentdemo.model.UserInfo;

import java.util.List;

public class ListFragment extends Fragment {
    private UserAdapter mAapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        View view = getView();
        assert view != null;
        RecyclerView recyclerView = view.findViewById(R.id.FlList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        UserInfoService userInfoService = new UserInfoService();
        List<UserInfo> userInfos = userInfoService.getUserInfos(getContext());
        mAapter = new UserAdapter(userInfos);
        recyclerView.setAdapter(mAapter);


    }

    public void edit(UserInfo userInfo, int index) {
        mAapter.edit(userInfo,index);
        mAapter.edit(userInfo,index);

    }
}
