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
    private static final String PARAM_USER_INFO ="user_info";
    private static final String PARAM_INDEX ="index" ;
    public static DetailFragment newInstance(UserInfo userInfo, int index) {

        Bundle args = new Bundle();
        args.putSerializable(PARAM_USER_INFO, (Serializable) userInfo);
        args.putInt(PARAM_INDEX,index);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private UserInfo muserInfo;
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
        assert view != null;
        final EditText setUserName = view.findViewById(R.id.etUserName);
        setUserName.setHint(muserInfo.userName);
        view.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muserInfo.userName = setUserName.getText().toString();
                OnEditUserInfo mEditUserInfo = null;
                mEditUserInfo.onSuccess(muserInfo,mIndex);
            }
        });
    }
    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        assert args != null;
        muserInfo =(UserInfo) args.getSerializable(PARAM_USER_INFO);
        mIndex = args.getInt(PARAM_INDEX);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnEditUserInfo){
            OnEditUserInfo mEditUserInfo = ((OnEditUserInfo) context);
        }
    }
    public interface OnEditUserInfo{
        void onSuccess(UserInfo userInfo,int index);
    }
}
