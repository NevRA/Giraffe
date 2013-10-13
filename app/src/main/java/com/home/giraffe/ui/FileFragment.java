package com.home.giraffe.ui;


import android.support.v4.content.Loader;
import android.view.View;
import android.widget.TextView;
import com.home.giraffe.R;
import com.home.giraffe.objects.File;
import com.home.giraffe.objects.Post;
import com.home.giraffe.tasks.LoadFileTask;
import roboguice.inject.InjectView;

public class FileFragment extends PostFragment {
    @InjectView(R.id.file_layout)
    View mFileView;

    @InjectView(R.id.file_link)
    View mFileLink;

    @InjectView(R.id.file_name)
    TextView mFileName;

    private File mFile;

    @Override
    public void onLoadFinished(Loader<Post> postLoader, Post post) {
        super.onLoadFinished(postLoader, post);

        if(post instanceof File){
            mFile = (File)post;
            mFileView.setVisibility(View.VISIBLE);
            mFileName.setText(mFile.getName());
            mFileLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openFile();
                }
            });
        }
    }

    private void openFile() {
        mUiManager.showToast(this, mUiManager.getString(R.string.trying_to_open_file_message));

        new LoadFileTask(this, mFile.getBinaryUrl(), mFile.getContentType()).execute();
    }
}
