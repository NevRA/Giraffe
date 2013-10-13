package com.fedorvlasov.lazylist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;

public class FileCache {
    
    private File cacheDir;

    public FileCache(Context context)
    {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))
            cacheDir = context.getExternalCacheDir();
        else
            cacheDir = context.getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }

    public File getFile(String url){
        String filename=String.valueOf(url.hashCode());
        File f = new File(cacheDir, filename);
        return f;
    }

    public File saveToCache(String url, InputStream is) throws Exception{
        File f = getFile(url);
        OutputStream os = new FileOutputStream(f);
        Utils.CopyStream(is, os);
        os.close();
        return f;
    }
    
    public void clear(){
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }

}