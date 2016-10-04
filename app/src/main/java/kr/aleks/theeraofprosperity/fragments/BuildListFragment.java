package kr.aleks.theeraofprosperity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kr.aleks.theeraofprosperity.R;
import kr.aleks.theeraofprosperity.activity.BuildPagerActivity;
import kr.aleks.theeraofprosperity.data.AboutBuildings;
import kr.aleks.theeraofprosperity.data.BuildingLab;

public class BuildListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private BuildAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_build_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.build_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        BuildingLab buildingLab = BuildingLab.get(getActivity());
        List<AboutBuildings> lists = buildingLab.getBuilds();

        if (mAdapter == null) {
            mAdapter = new BuildAdapter(lists);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class BuildHolder extends ViewHolder implements OnClickListener {

        private AboutBuildings mBuildings;

        public TextView mTitleTextView;
        public ImageView mImageView;
        public TextView mTimerListView;

        public BuildHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_constr_title);
            mImageView = (ImageView) itemView.findViewById(R.id.list_item_constr_image);
            mTimerListView = (TextView) itemView.findViewById(R.id.list_item_constr_timer);
        }

        public void bindBuild(AboutBuildings buildings) {
            mBuildings = buildings;

            mTitleTextView.setText(mBuildings.getTitle());
            mImageView.setImageResource(mBuildings.getImage());
            mTimerListView.setText(mBuildings.getTimer());
        }

        @Override
        public void onClick(View view) {
            Intent intent = BuildPagerActivity
                    .newIntent(getActivity(),
                            mBuildings.getId(),
                            mBuildings.getTimer());
            startActivity(intent);
        }
    }

    private class BuildAdapter extends Adapter<BuildHolder> {

        private List<AboutBuildings> mList;

        public BuildAdapter(List<AboutBuildings> list) {
            //конструктор адаптера
            mList = list;
        }

        @Override
        public BuildHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_build, parent, false);

            return new BuildHolder(view);
        }

        @Override
        public void onBindViewHolder(BuildHolder holder, int position) {
            AboutBuildings buildings = mList.get(position);
            holder.bindBuild(buildings);
        }

        @Override
        public int getItemCount() {
            //размер списка
            return mList.size();
        }
    }
}
