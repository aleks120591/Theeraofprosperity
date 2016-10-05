package kr.aleks.theeraofprosperity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import kr.aleks.theeraofprosperity.R;
import kr.aleks.theeraofprosperity.data.AboutBuildings;
import kr.aleks.theeraofprosperity.data.BuildingLab;
import kr.aleks.theeraofprosperity.utils.TimerS;

public class BuildingFragment extends Fragment {

    private static final String ARG_BUILD_ID = "build_id";

    private AboutBuildings mBuildings;

    private ImageView mImageView;
    private TextView mTitleView;
    private TextView mTimerView;
    private Button mBuildingButton;

    private TimerS mTimerS;
    private long time = 0;
    private int hours = 0;
    private int minutes = 1;

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

        mBuildingButton = (Button) v.findViewById(R.id.building);
        mBuildingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimerView.setText("");

                time = TimeUnit.HOURS.toMillis(Long.valueOf(hours)) + TimeUnit.MINUTES.toMillis(Long.valueOf(minutes));
                mTimerS = new TimerS(getContext(), mBuildingButton, time);
                mTimerS.Start();

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
}