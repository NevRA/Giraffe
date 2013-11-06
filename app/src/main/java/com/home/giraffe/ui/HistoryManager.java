package com.home.giraffe.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.utils.Utils;

public class HistoryManager {
    private FragmentActivity mActivityFragment;
    private ISettingsManager mSettingsManager;

    public HistoryManager(FragmentActivity activityFragment, ISettingsManager settingsManager){
        mActivityFragment = activityFragment;
        mSettingsManager = settingsManager;
    }

    public void proceed() {
        String oldAppVersion = mSettingsManager.getAppVersion();
        String newAppVersion;

        try {
            PackageInfo pInfo = mActivityFragment
                    .getPackageManager()
                            .getPackageInfo(mActivityFragment.getPackageName(), 0);
            newAppVersion = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Utils.e(e);
            return;
        }

        if(     oldAppVersion == null ||
                !oldAppVersion.equals(newAppVersion)){
            mSettingsManager.setAppVersion(newAppVersion);
            showAppHistory();
        }
    }

    private void showAppHistory() {
        HistoryDialog historyDialog = new HistoryDialog();
        historyDialog.show(mActivityFragment.getSupportFragmentManager(), null);
    }
}
