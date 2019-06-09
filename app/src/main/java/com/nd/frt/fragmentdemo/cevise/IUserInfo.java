package com.nd.frt.fragmentdemo.cevise;

import android.content.Context;


import com.nd.frt.fragmentdemo.model.UserInfo;

import java.util.List;

public interface IUserInfo {

    List<UserInfo> getUserInfos(Context context);

}
