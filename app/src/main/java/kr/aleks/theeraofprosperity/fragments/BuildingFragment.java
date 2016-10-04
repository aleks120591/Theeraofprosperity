package kr.aleks.theeraofprosperity.fragments;

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

import static kr.aleks.theeraofprosperity.R.color.primary_dark_material_dark;

public class BuildingFragment extends Fragment {

    private static final String ARG_BUILD_ID = "build_id";
    private static final String ARG_TIMER = "timer";

    private static final int TIME_SEC = 1000;

    private AboutBuildings mBuildings;

    private ImageView mImageView;
    private TextView mTitleView;
    private TextView mTimerView;
    private Button mBuildingButton;

    private CountDownTimer mTimer;

    public static BuildingFragment newInstance(UUID buildId,String timer) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BUILD_ID, buildId);
        args.putSerializable(ARG_TIMER,timer);

        BuildingFragment fragment = new BuildingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID buildId = (UUID) getArguments().getSerializable(ARG_BUILD_ID);
        String timer=(String)getArguments().getSerializable(ARG_TIMER);
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
        mTimer = new Timers(300000, TIME_SEC);

        mBuildingButton = (Button) v.findViewById(R.id.building);
        mBuildingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimerView.setText("");

                mTimer.start();
                mBuildingButton.setEnabled(false);
                mBuildingButton.setTextColor(primary_dark_material_dark);
            }
        });
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public class Timers extends CountDownTimer {

        public Timers(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            mBuildingButton.setText(AboutBuildings.getTime((int)l / 1000));
        }

        @Override
        public void onFinish() {

        }
    }
}