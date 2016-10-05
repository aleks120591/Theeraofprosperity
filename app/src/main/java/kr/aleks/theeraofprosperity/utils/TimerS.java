package kr.aleks.theeraofprosperity.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
                    long hours = TimeUnit.MILLISECONDS.toHours(l);
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(l - hours * 3600 * 1000);
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(l - (minutes * 60 * 1000 + hours * 3600 * 1000));
                    mTimeRemaining.setHours((int) hours);
                    mTimeRemaining.setMinutes((int) minutes);
                    mTimeRemaining.setSeconds((int) seconds);
                    getTextView().setText(mTimeFormat.format(mTimeRemaining));
                }

                @Override
                public void onFinish() {
                    getTextView().setText("00:00:00");
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
