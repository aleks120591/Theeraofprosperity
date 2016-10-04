package kr.aleks.theeraofprosperity.data;

import java.util.UUID;

public class AboutBuildings {

    private UUID mId;
    private String mTitle;
    private String mTimer;
    private int mImage;

    public AboutBuildings() {
        //
        mId = UUID.randomUUID();
    }

    public AboutBuildings(String title, String timer, int image) {
        mTitle = title;
        mTimer = timer;
        mImage = image;
    }

    public UUID getId() {
        //
        return mId;
    }

    public String getTitle() {
        //
        return mTitle;
    }

    public void setTitle(String title) {
        //
        mTitle = title;
    }

    public int getImage() {
        //
        return mImage;
    }

    public void setImage(int image) {
        //
        mImage = image;
    }

    public String getTimer() {
        //
        return mTimer;
    }

    public void setTimer(String timer) {
        //
        mTimer = timer;
    }

    public static String getTime(int time) {
        String result = "";
        int hours = time / 3600;
        int minutes = (time - (hours * 3600)) / 60;
        int seconds = (time - (hours * 3600) - (minutes * 60));
        result = String.valueOf(hours) + ":";
        if (minutes < 10) {
            result += "0" + String.valueOf(minutes) + ":";
        } else {
            result += String.valueOf(minutes) + ":";
        }
        if (seconds < 10) {
            result += "0" + String.valueOf(seconds);
        } else {
            result += String.valueOf(seconds);
        }
        return result;
    }
}
