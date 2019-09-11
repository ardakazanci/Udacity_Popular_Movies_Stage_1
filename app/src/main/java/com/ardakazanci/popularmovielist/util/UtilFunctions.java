package com.ardakazanci.popularmovielist.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.ardakazanci.popularmovielist.common.Constants;

public class UtilFunctions {

    public static void watchYoutubeVideo(Context context, String id) {
        String fullYoutubeUrl = Constants.BASE_VIDEO_URL + id;
        Log.e("UtilFunctions", "Video ID" + id);
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

}
