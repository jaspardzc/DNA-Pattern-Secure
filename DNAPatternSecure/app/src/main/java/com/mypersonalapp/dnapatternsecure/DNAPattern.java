package com.mypersonalapp.dnapatternsecure;

/**
 * Created by jaspe_000 on 4/29/2015.
 */

import java.util.HashMap;
import java.util.List;

/**
 * Build DNA Pattern based on DNADatabase and UserHistory and DNA Algorithms
 */
public class DNAPattern {
	private int userID;
	private String userName;
	private String userEmailAddress;
	private String hashValue;
	private List<HashMap<String, String>> dataList;
    public DNAPattern() {

    }
    public DNAPattern(int userID, String userName, String userEmailAddress) {

    }
    public DNAPattern(int userID, String userName, String userEmailAddress, String hashValue) {

    }

    public boolean isEncrypted() {

        return true;
    }

    public boolean compareHashValue(String hashValue1, String hashValue2) {

        return true;
    }

    public List<HashMap<String, String>> storeUserData(List<HashMap<String, String>> dataList, String userName) {
        //HashMap<date, records>
        return dataList;
    }

    public int calculateHammingDistance(String arg0, String arg1) {
        int distance = 0;

        return distance;
    }

}
