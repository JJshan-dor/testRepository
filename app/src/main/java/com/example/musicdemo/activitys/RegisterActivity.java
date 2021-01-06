package com.example.musicdemo.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.musicdemo.R;
import com.example.musicdemo.utils.UserUtils;
import com.example.musicdemo.views.InputView;

public class RegisterActivity extends BaseActivity {

    private InputView mInputPhone, mInputPassword, mInputPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        initNavBar(true, "注册", false);

        mInputPhone = fd(R.id.input_phone);
        mInputPassword = fd(R.id.input_password);
        mInputPasswordConfirm = fd(R.id.input_password_confirm);
    }


    /**
     * 注册按钮点击事件
     * 1.用户输入合法性验证
     * 1.用户输入的手机号是不是合法的
     * 2.用户是否输入密码和输入确认密码，两次输入是否一样
     * 3.用户输入的手机号是否已经被注册
     * 2.保存用户输入的手机号和密码（MD5加密密码）
     */
    public void onRegisterClick(View v) {

        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();
        String passwordConfirm = mInputPasswordConfirm.getInputStr();

        boolean result=UserUtils.registerUser(this, phone, password, passwordConfirm);
        if (result) return;

        //后退页面
        onBackPressed();


    }


}
