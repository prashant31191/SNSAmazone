package com.api.model;

import com.google.android.gms.maps.model.Dash;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prashant.patel on 6/7/2017.
 */

public class DashboardItemModel {


    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("img")
    public int img;

    public DashboardItemModel(String id, String title, int image)
    {
        this.id = id;
        this.title = title;
        this.img = image;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public int getImg() {
        return img;
    }

}
