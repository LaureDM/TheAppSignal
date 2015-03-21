package com.theappsignal.lauresoft.theappsignal.util;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.theappsignal.lauresoft.theappsignal.R;
import com.theappsignal.lauresoft.theappsignal.dal.ContactMapper;
import com.theappsignal.lauresoft.theappsignal.domain.Contact;
import com.theappsignal.lauresoft.theappsignal.message.SMSActivity;

import java.util.List;

/**
 * Created by Laure on 19/12/2014.
 */
public class MyWidgetProvider extends AppWidgetProvider
{
    private static final String ACTION_CLICK = "ACTION_CLICK";

    private List<Contact> contacts = ContactMapper.getInstance().getContacts();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {


        // Get all ids
        ComponentName thisWidget = new ComponentName(context,
                MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {
            Intent intent = new Intent(context, SMSActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setOnClickPendingIntent(R.id.widget_button, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, views);

        }
    }

}
