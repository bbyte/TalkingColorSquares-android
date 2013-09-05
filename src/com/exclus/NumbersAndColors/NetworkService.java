package com.exclus.NumbersAndColors;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created with IntelliJ IDEA.
 * User: bbyte
 * Date: 9/2/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class NetworkService extends Service
{
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        //TODO do something useful
        return Service.START_NOT_STICKY;
    }
}
