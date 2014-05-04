package com.yrkj.util.sys;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

public class SysHelper {

	protected static final String PREFS_FILE = "device_id.xml";
	protected static final String PREFS_DEVICE_ID = "device_id";

	protected static UUID uuid;

	public void DeviceUuidFactory(Context context) {

		if (uuid == null) {
			synchronized (SysHelper.class) {
				if (uuid == null) {
					final SharedPreferences prefs = context
							.getSharedPreferences(PREFS_FILE, 0);
					final String id = prefs.getString(PREFS_DEVICE_ID, null);

					if (id != null) {
						// Use the ids previously computed and stored in the
						// prefs file
						uuid = UUID.fromString(id);

					} else {

						final String androidId = Secure
								.getString(context.getContentResolver(),
										Secure.ANDROID_ID);

						// Use the Android ID unless it's broken, in which case
						// fallback on deviceId,
						// unless it's not available, then fallback on a random
						// number which we store
						// to a prefs file
						try {
							if (!"9774d56d682e549c".equals(androidId)) {
								uuid = UUID.nameUUIDFromBytes(androidId
										.getBytes("utf8"));
							} else {
								final String deviceId = ((TelephonyManager) context
										.getSystemService(Context.TELEPHONY_SERVICE))
										.getDeviceId();
								uuid = deviceId != null ? UUID
										.nameUUIDFromBytes(deviceId
												.getBytes("utf8")) : UUID
										.randomUUID();
							}
						} catch (UnsupportedEncodingException e) {
							throw new RuntimeException(e);
						}

						// Write the value out to the prefs file
						prefs.edit()
								.putString(PREFS_DEVICE_ID, uuid.toString())
								.commit();

					}

				}
			}
		}

	}

	public static UUID getDeviceUuid(Context c) {
		SysHelper sh = new SysHelper();
		sh.DeviceUuidFactory(c);
		return uuid;
	}

	public static int[] getDeviceSize(Activity c){
		DisplayMetrics dm = new DisplayMetrics();
		dm = c.getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;	
		int screenHeight = dm.heightPixels;	
		
//		String s = screenWidth + "x" + screenHeight;
//		dm = new DisplayMetrics();
//		
//		c.getWindowManager().getDefaultDisplay().getMetrics(dm);
//		screenWidth = dm.widthPixels;	
//		screenHeight = dm.heightPixels;
//		s += " " + screenWidth + "x" + screenHeight;
		return new int[] { screenWidth, screenHeight };
	}
	

}

