package com.home.giraffe.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.*;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.Main;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.tasks.SignInTask;
import roboguice.inject.InjectView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SignInActivity extends RoboSherlockFragmentActivity implements LoaderManager.LoaderCallbacks {
    @InjectView(R.id.signin) Button mSignIn;
    @InjectView(R.id.communityUrl) AutoCompleteTextView mCommunityUrl;
    @InjectView(R.id.userName) AutoCompleteTextView mUserName;
    @InjectView(R.id.userPassword) EditText mUserPassword;
    @InjectView(R.id.appVersion) TextView mAppVersion;

    @Inject
    ISettingsManager mSettingsManager;

    @Inject
    IUiManager mUiManager;

    private ProgressDialogFragment mWaiter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        Init();
    }

    private void Init() {
        showAppVersion();
        resetSettings();

        addCommunityAutoComplete();
        addUserNameAutoComplete();

        mSignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUiManager.hideKeyboard(view);
                getSupportLoaderManager().restartLoader(1, null, SignInActivity.this);
            }
        });
    }

    private void addUserNameAutoComplete() {
        Account[] accounts = AccountManager.get(this).getAccounts();
        Set<String> emailSet = new HashSet<String>();
        for (Account account : accounts) {
            if (Constants.EMAIL_PATTERN.matcher(account.name).matches()) {
                emailSet.add(account.name);
            }
        }

        mUserName.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, new ArrayList<String>(emailSet)));

        mUserName.setThreshold(2);
    }

    private void addCommunityAutoComplete() {
        mCommunityUrl.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, mUiManager.getStringArray(R.array.builtin_communities)));

        mCommunityUrl.setThreshold(0);
    }

    private void showAppVersion() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            mAppVersion.setText("v " + pInfo.versionName);

        } catch (PackageManager.NameNotFoundException e) {
            mUiManager.showError(this, e);
        }
    }

    private void resetSettings() {
        mSettingsManager.clear();
    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        mWaiter = new ProgressDialogFragment();
        mWaiter.show(getSupportFragmentManager(), "");

        return new SignInTask(this, mCommunityUrl.getText().toString(), mUserName.getText().toString(), mUserPassword.getText().toString());
    }

    @Override
    public void onLoadFinished(Loader loader, Object o) {
        new Handler().post(new Runnable() {
            public void run() {
                mWaiter.dismiss();
                if(mSettingsManager.isLoggedOn()){
                    startActivity(new Intent(SignInActivity.this, Main.class));
                }
            }
        });
    }

    @Override
    public void onLoaderReset(Loader loader) {
    }
}
