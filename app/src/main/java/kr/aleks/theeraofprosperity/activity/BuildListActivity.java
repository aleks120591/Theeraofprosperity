package kr.aleks.theeraofprosperity.activity;

import android.support.v4.app.Fragment;

import kr.aleks.theeraofprosperity.fragments.BuildListFragment;

public class BuildListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new BuildListFragment();
    }
}