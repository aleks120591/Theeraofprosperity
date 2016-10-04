package kr.aleks.theeraofprosperity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

import kr.aleks.theeraofprosperity.R;
import kr.aleks.theeraofprosperity.data.AboutBuildings;
import kr.aleks.theeraofprosperity.data.BuildingLab;

public class BuildingFragment extends Fragment {

    private static final String ARG_BUILD_ID = "build_id";
    private static final String DIALOG_TIMER = "DialogTimer";

    private static final int REQUEST_TIMER = 0;

    private static final int TIME_SEC = 1000;

    private AboutBuildings mBuildings;

    private ImageView mImageView;
    private TextView mTitleView;
    private TextView mTimerView;
    private Button mBuildingButton;

    private CountDownTimer mTimer;

    public static BuildingFragment newInstance(UUID buildId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BUILD_ID, buildId);

        BuildingFragment fragment = new BuildingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID buildId = (UUID) getArguments().getSerializable(ARG_BUILD_ID);
        mBuildings = BuildingLab.get(getActivity()).getBuild(buildId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_building, container, false);

        mImageView = (ImageView) v.findViewById(R.id.constr_image);
        mImageView.setImageResource(mBuildings.getImage());

        mTitleView = (TextView) v.findViewById(R.id.constr_title);
        mTitleView.setText(mBuildings.getTitle());

        mTimerView = (TextView) v.findViewById(R.id.times_building);
        mTimerView.setText(mBuildings.getTimer());

        long tim = mBuildings.getTim();
        mTimer = new Timers(tim, TIME_SEC);

        mBuildingButton = (Button) v.findViewById(R.id.building);
        mBuildingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimerView.setText("");
                mTimer.start();
                mBuildingButton.setEnabled(false);
                mBuildingButton.setTextColor(R.color.colorBlack);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public class Timers extends CountDownTimer {

        public Timers(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            mBuildingButton.setText(AboutBuildings.getTime((int) l / 1000));
        }

        @Override
        public void onFinish() {

        }
    }
}