package com.example.musicdemo.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.musicdemo.R;
import com.example.musicdemo.models.UserModel;
import com.example.musicdemo.utils.UserUtils;
import com.example.musicdemo.views.InputView;

public class ChangePasswordActivity extends BaseActivity {
    private InputView mOldPassword, mPassword, mPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView() {
        initNavBar(true, "修改密码", false);

        mOldPassword = fd(R.id.input_old_password);
        mPassword = fd(R.id.input_password);
        mPasswordConfirm = fd(R.id.input_password_confirm);
    }

    public void onChangePasswordClick(View v) {
        String oldPassword = mOldPassword.getInputStr();
        String password = mPassword.getInputStr();
        String passwordConfirm = mPasswordConfirm.getInputStr();

        boolean result = UserUtils.changePassword(this, oldPassword, password, passwordConfirm);
        if (!result) {
            return;
        }

        UserUtils.logout(this);


    }
}
