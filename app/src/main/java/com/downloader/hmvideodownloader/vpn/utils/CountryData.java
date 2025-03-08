package com.downloader.hmvideodownloader.vpn.utils;


import com.downloader.hmvideodownloader.vpn.adapter.LocationListAdapter;

public class CountryData {

    private boolean pro = false;
    private LocationListAdapter.Region countryvalue;

    public boolean isPro() {
        return pro;
    }

    public void setPro(boolean pro) {
        this.pro = pro;
    }

    public LocationListAdapter.Region getCountryvalue() {
        return countryvalue;
    }

    public void setCountryvalue(LocationListAdapter.Region countryvalue) {
        this.countryvalue = countryvalue;
    }

}
