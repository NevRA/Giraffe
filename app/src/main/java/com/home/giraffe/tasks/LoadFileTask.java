package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.fedorvlasov.lazylist.FileCache;
import com.home.giraffe.events.OpenFileEvent;
import com.home.giraffe.utils.Utils;

import java.io.ByteArrayInputStream;
import java.io.File;

public class LoadFileTask extends BaseTask {
    private String mUrl;
    private String mType;

    public LoadFileTask(FragmentActivity activity, String url, String type) {
        super(activity);
        mUrl = url;
        mType = type;
    }

    @Override
    public Object call() throws Exception {
        Utils.d("Started LoadFileTask");

        try {
            FileCache fileCache = new FileCache(mUiManager.getContext());
            File file = fileCache.getFile(mUrl);
            if(!file.isFile()){
                byte[] data = mRequestsManager.getData(mUrl);
                Utils.d("Data loaded from network. Size: " + Utils.readableFileSize(data.length));

                file = fileCache.saveToCache(mUrl, new ByteArrayInputStream(data));
            }
            else{
                Utils.d("Data loaded from file cache.");
            }

            mBus.post(new OpenFileEvent(file, mType));

        } catch (Exception e) {
            mUiManager.showError(getActivity(), e);
        }

        finally {
            Utils.d("Finished LoadFileTask");
        }

        return null;
    }
}
