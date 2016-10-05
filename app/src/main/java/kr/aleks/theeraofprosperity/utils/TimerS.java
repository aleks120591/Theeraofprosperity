package kr.aleks.theeraofprosperity.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.aleks.theeraofprosperity.R;

public class TimerS {

    private static SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm:ss");
    private static Long mCountDownTime;
    private static TextView mTextView;
    private static Date mTimeRemaining;
    private static CountDownTimer mCountDownTimer;
    private Context mContext;

    public TimerS(Context context, TextView textView, Long countDownTime) {
        try {
            mTimeRemaining = mTimeFormat.parse("00:00:00");
            setTextView(textView);
            setCountDownTime(countDownTime);
            setContext(context);

            mCountDownTimer = new CountDownTimer(getCountDownTime(), 100) {

                @Override
                public void onTick(long l) {
                    getTextView().setText(getTimers((int) l / 1000));
                }

                @Override
                public void onFinish() {
                    getTextView().setText(R.string.building);
                }
            };
        } catch (ParseException err) {
            err.printStackTrace();
        }
    }

    public void Start() {
        mCountDownTimer.start();
    }

    public void Stop() {
        mCountDownTimer.cancel();
    }

    private String getTimers(int time) {
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

    public void setTextView(TextView textView) {
        TimerS.mTextView = textView;
    }

    public void setCountDownTime(Long countDownTime) {
        this.mCountDownTime = countDownTime;
    }

    public Context getContext() {
        return mContext;
    }

    public static TextView getTextView() {
        return mTextView;
    }

    public static Long getCountDownTime() {
        return mCountDownTime;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }
}
