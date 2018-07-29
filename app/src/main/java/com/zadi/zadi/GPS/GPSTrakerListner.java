package com.zadi.zadi.GPS;

/**
 * Created by mahmoud on 12/03/2017.
 */

public interface GPSTrakerListner {
    void onTrackerSuccess(Double lat, Double log);

    void onStartTracker();
}
