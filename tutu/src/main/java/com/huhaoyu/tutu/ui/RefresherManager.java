package com.huhaoyu.tutu.ui;

import android.support.annotation.NonNull;

import com.rey.material.widget.ProgressView;

/**
 * Refresher manager
 * Created by coderhuhy on 15/11/28.
 */
public class RefresherManager {

    protected ProgressView progress;
    protected boolean refreshing = false;
    protected int count = 0;

    private RefresherManager(@NonNull ProgressView progress) {
        this.progress = progress;
    }

    public static RefresherManager newInstance(@NonNull ProgressView progress) {
        return new RefresherManager(progress);
    }

    public boolean start() {
        synchronized (RefresherManager.this) {
            refreshing = true;
            if (++count == 1) {
                progress.start();
                return true;
            }
            return false;
        }
    }

    public boolean stop() {
        synchronized (RefresherManager.this) {
            if (--count == 0) {
                refreshing = false;
                progress.stop();
                return true;
            }
            return false;
        }
    }

    public boolean isRefreshing() {
        return this.refreshing;
    }

}
