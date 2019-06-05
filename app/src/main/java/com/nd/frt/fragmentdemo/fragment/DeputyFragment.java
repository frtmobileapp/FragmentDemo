package com.nd.frt.fragmentdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nd.frt.fragmentdemo.R;
import com.nd.frt.fragmentdemo.model.UserInfo;

public class DeputyFragment extends Fragment {

    public static final String PARAM_USER_INFO = "user_info";
    public static final String PARAM_USER_INDEX = "user_index";
    private UserInfo mUserInfo;
    private int mIndex;
    private OnEditUserInfo mEditUserInfo;

    public static DeputyFragment newInstance(UserInfo userInfo, int index) {
        Bundle args = new Bundle();
        args.putSerializable(PARAM_USER_INFO, userInfo);
        args.putInt(PARAM_USER_INDEX, index);
        DeputyFragment fragment = new DeputyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private EditText mEditText;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        mUserInfo = (UserInfo) args.getSerializable(PARAM_USER_INFO);
        mIndex = args.getInt(PARAM_USER_INDEX, 0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditUserInfo) {
            mEditUserInfo = ((OnEditUserInfo) context);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deputy, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        mEditText = view.findViewById(R.id.et_username);
        mEditText.setHint(mUserInfo.userName);
        view.findViewById(R.id.bt_modify)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Editable text = mEditText.getText();
                        mUserInfo.userName = text.toString();
                        mEditUserInfo.onSuccess(mUserInfo, mIndex);
                    }
                });
    }

    public interface OnEditUserInfo {
        void onSuccess(UserInfo userInfo, int index);
    }
}
