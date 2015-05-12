package com.mypersonalapp.dnapatternsecure;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by jaspe_000 on 4/27/2015.
 */
//to add people
public class SMSBlock extends BroadcastReceiver {
            final SmsManager sms = SmsManager.getDefault();
			private List<String> blackListNumber;
			private MonitorList monitorList;
            @Override
            public void onReceive(Context context, Intent intent) {
				for (int i = 0; i < monitorList.getBlackList().size(); i++) {
					blackListNumber.add(monitorList.getBlackList().get(i));
				}
                final Bundle bundle = intent.getExtras();
                try {
                    if (bundle != null) {
                        final Object[] pdusObj = (Object[]) bundle.get("pdus");
                        for (int i = 0; i < pdusObj.length; i++) {
                            SmsMessage currentMessage = SmsMessage
                                    .createFromPdu((byte[]) pdusObj[i]);
                            String phoneNumber = currentMessage
                                    .getDisplayOriginatingAddress();
                            String senderNum = phoneNumber;
                            String message = currentMessage.getDisplayMessageBody();
                            Log.i("SmsReceiver", "senderNum: " + senderNum
                                    + "; message: " + message);
                            // Show Alert
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, "senderNum: "
                                    + senderNum + ", message: " + message, duration);
                            toast.show();	
							for (int index = 0; index < blackListNumber.size(); index++) {
								if(senderNum.contains(blackListNumber.get(index))){
									Toast.makeText(context, "You r in if condition", Toast.LENGTH_LONG).show();
									abortBroadcast();
								}
							}
                        } 
                    } 

                } catch (Exception e) {
                    Log.e("SmsReceiver", "Exception smsReceiver" + e);
                }
            }
        } 
