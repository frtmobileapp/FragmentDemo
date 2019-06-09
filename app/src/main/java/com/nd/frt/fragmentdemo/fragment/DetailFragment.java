package com.nd.frt.fragmentdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nd.frt.fragmentdemo.R;
import com.nd.frt.fragmentdemo.model.UserInfo;

import java.io.Serializable;

public class DetailFragment extends Fragment {

    private static final String PARAM_USER_INFO = "user_info";
    private static final String PARAM_INDEX = "index";
    private OnEditUserInfo mEditUserInfo;


    public static DetailFragment newInstance(UserInfo userInfo, int index) {

        Bundle args = new Bundle();
        args.putSerializable(PARAM_USER_INFO,userInfo);
        args.putInt(PARAM_INDEX,index);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private UserInfo mUserInfo;
    private int mIndex;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        assert view!=null;
        final EditText etUserName = view.findViewById(R.id.etUserName);
        etUserName.setHint(mUserInfo.userName);
        view.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserInfo.userName = etUserName.getText().toString();
                mEditUserInfo.onSuccess(mUserInfo,mIndex);

            }
        });
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        assert args != null;
        mUserInfo = (UserInfo) args.getSerializable(PARAM_USER_INFO);
        mIndex = args.getInt(PARAM_INDEX);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditUserInfo){
            mEditUserInfo = ((OnEditUserInfo) context);
        }
    }

    public  interface OnEditUserInfo {
        void onSuccess(UserInfo userInfo,int index);
    }
}
