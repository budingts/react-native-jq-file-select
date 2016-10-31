package com.chnsys.reactfileselect;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.IllegalViewOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名：android 文件选择模块，
 * 作者： juanqiang
 */

interface ActivityResultInterface {
    void callback(int requestCode, int resultCode, Intent data);
}
public class FileSelectCustomModule extends ReactContextBaseJavaModule {




    private  ReactApplicationContext mReactContext  = null;
    private FilePickerActivityEventListener mActivityEventListener;
    private Callback mCallback;
    WritableMap response;
    private int REQUEST_FILE_CODE =10001;


    public FileSelectCustomModule(ReactApplicationContext reactContext) {
        super(reactContext);

        mReactContext = reactContext;
        mActivityEventListener = new FilePickerActivityEventListener(reactContext, new ActivityResultInterface() {
            @Override
            public void callback(int requestCode, int resultCode, Intent data) {
                onActivityResult(requestCode, resultCode, data);
            }
        });
    }
    @Override
    public String getName() {
        return "JQFileSelectManager";
    }
    /**
     * 开启文件选择
     */
    @ReactMethod
    public void startSelectFile(final Callback callback) {

        Intent libraryIntent;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            response = Arguments.createMap();
            response.putString("error", "can't find current Activity");
            callback.invoke(response);
            return;
        }
        libraryIntent= new Intent(Intent.ACTION_GET_CONTENT);
        libraryIntent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        libraryIntent.addCategory(Intent.CATEGORY_OPENABLE);


        if (libraryIntent.resolveActivity(mReactContext.getPackageManager()) == null) {
            response = Arguments.createMap();
            response.putString("error", "Cannot launch photo library");
            callback.invoke(response);
            return;
        }
        mCallback = callback;
        try {
            currentActivity.startActivityForResult(libraryIntent, REQUEST_FILE_CODE);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            response = Arguments.createMap();
            response.putString("error", "Cannot launch photo library");
            callback.invoke(response);
        }

    }



    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        //robustness code
        if (mCallback == null) {
            return;
        }
        response = Arguments.createMap();
        // 取消了操作没有选取任何文件
        if (resultCode != Activity.RESULT_OK) {
            response.putBoolean("didCancel", true);
            mCallback.invoke(response);
            mCallback = null;
            return;
        }
        Uri _uri =  data.getData();
        response.putString("uri", _uri.toString());
        response.putString("path", _uri.getPath());
        mCallback.invoke(response);
        mCallback = null;

    }
 

}