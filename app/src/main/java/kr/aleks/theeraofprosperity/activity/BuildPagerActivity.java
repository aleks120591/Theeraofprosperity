package kr.aleks.theeraofprosperity.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

import kr.aleks.theeraofprosperity.R;
import kr.aleks.theeraofprosperity.data.AboutBuildings;
import kr.aleks.theeraofprosperity.data.BuildingLab;
import kr.aleks.theeraofprosperity.fragments.BuildingFragment;

public class BuildPagerActivity extends FragmentActivity {

    private static final String EXTRA_BUILD_ID = "kr.aleks.theeraofprosperity.activity.build_id";
    private static final String EXTRA_TIMER = "kr.aleks.theeraofprosperity.activity.timer";

    private ViewPager mViewPager;
    private List<AboutBuildings> mLists;

    public static Intent newIntent(Context packegeContext, UUID buildId, String timer) {
        Intent intent = new Intent(packegeContext, BuildPagerActivity.class);
        intent.putExtra(EXTRA_BUILD_ID, buildId);
        intent.putExtra(EXTRA_TIMER, timer);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_build_pager);

        UUID buildId = (UUID) getIntent().getSerializableExtra(EXTRA_BUILD_ID);
        String timer = (String) getIntent().getSerializableExtra(EXTRA_TIMER);

        mViewPager = (ViewPager) findViewById(R.id.activity_build_pager_view_pager);

        mLists = BuildingLab.get(this).getBuilds();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                AboutBuildings buildings = mLists.get(position);
                return BuildingFragment.newInstance(buildings.getId(), buildings.getTimer());
            }

            @Override
            public int getCount() {
                return mLists.size();
            }
        });
        for (int i = 0; i < mLists.size(); i++) {
            if (mLists.get(i).getId().equals(buildId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
