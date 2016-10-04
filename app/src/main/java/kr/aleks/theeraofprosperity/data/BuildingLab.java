package kr.aleks.theeraofprosperity.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kr.aleks.theeraofprosperity.utils.CONST;

public class BuildingLab {

    private static BuildingLab sBuildingLab;

    private List<AboutBuildings> mList;

    public static BuildingLab get(Context context) {
        if (sBuildingLab == null) {
            sBuildingLab = new BuildingLab(context);
        }
        return sBuildingLab;
    }

    private BuildingLab(Context context) {
        mList = new ArrayList<>();

        for (int i = 0; i < CONST.TITLES.length; i++) {
            AboutBuildings buildings = new AboutBuildings();
            buildings.setImage(CONST.IMAGES[i]);
            buildings.setTitle(CONST.TITLES[i]);
            buildings.setTimer(CONST.TIMERS[i]);
            mList.add(buildings);
        }
    }

    public List<AboutBuildings> getBuilds() {
        return mList;
    }

    public AboutBuildings getBuild(UUID id) {
        for (AboutBuildings buildings : mList) {
            if (buildings.getId().equals(id)) {
                return buildings;
            }
        }
        return null;
    }

}
