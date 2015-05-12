package com.mypersonalapp.dnapatternsecure;


import android.content.Context;
import android.content.res.Configuration;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

/**
 * Created by jaspe_000 on 4/29/2015.
 */
public class WIFISecurity {
    private List<ScanResult> scanResultList;
    private WifiManager wifiManager;
    private static final int SECURITY_NONE = 0;
    private static final int SECURITY_WEP = 1;
    private static final int SECURITY_PSK = 2;
    private static final int SECURITY_EAP = 3;
    private enum PskType {UNKNOWN, WPA, WPA2, WPA_WPA2};

    public WIFISecurity(Context context) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }
    public void getScanResult(List<ScanResult> scanResults) {
        scanResults = wifiManager.getScanResults();
        this.scanResultList = scanResults;
    }
    public int getWifiSecurityType(List<ScanResult> scanResults) {
        scanResults = this.scanResultList;
        if (scanResults != null) {
            for (ScanResult scanResult : scanResults) {
                String capabilities = scanResult.capabilities;
                if (capabilities.contains("WEP")) {
                    return SECURITY_WEP;
                } else if (capabilities.contains("PSK")) {
                    return SECURITY_PSK;
                } else if (capabilities.contains("EAP")) {
                    return SECURITY_EAP;
                }
                //Log.w("WifiSecurity: ", scanResult.SSID + " Capabilities: " + capabilities);
            }
        }
        return SECURITY_NONE;
    }
}
