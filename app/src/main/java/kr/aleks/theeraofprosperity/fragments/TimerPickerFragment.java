package kr.aleks.theeraofprosperity.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kr.aleks.theeraofprosperity.R;
import kr.aleks.theeraofprosperity.data.AboutBuildings;

public class TimerPickerFragment extends DialogFragment {

    public static final String EXTRA_TEMER = "kr.aleks.theeraofprosperity.fragments.timer";
    private static final String ARG_TIMER = "timer";

    private ImageView mImageView;
    private TextView mTimerView;

    private List<AboutBuildings> mLists;

    public static TimerPickerFragment newInstance(String timer) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIMER, timer);

        TimerPickerFragment fragment = new TimerPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String timer = (String) getArguments().getSerializable(ARG_TIMER);
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_timer, null);
        mTimerView = (TextView) v.findViewById(R.id.dialog_constr_timer);
        mImageView = (ImageView) v.findViewById(R.id.dialog_constr_image);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok,null)
                .create();
    }

    private void sendResult(int resultCode, String timer) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TEMER, timer);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
