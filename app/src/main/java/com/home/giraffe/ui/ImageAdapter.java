package com.home.giraffe.ui;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import com.home.giraffe.interfaces.IImageLoader;
import roboguice.RoboGuice;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mImages;
    IImageLoader mImageLoader;

    public ImageAdapter(Context context, List<String> images) {
        mContext = context;
        mImages = images;

        mImageLoader = RoboGuice.getInjector(context).getProvider(IImageLoader.class).get();
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mImages.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ImageView iv = new ImageView(mContext);
        mImageLoader.DisplayImage(mImages.get(arg0), iv);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        Resources r = mContext.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, r.getDisplayMetrics());
        iv.setLayoutParams(new Gallery.LayoutParams((int)px,(int)px));
        return iv;
    }

}