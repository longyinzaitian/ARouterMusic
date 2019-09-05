package com.husy.network.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author husy
 * @date 2019/9/5
 */
public class ItemList implements Parcelable {
    public String type;
    public Data data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeParcelable(this.data, flags);
    }

    public ItemList() {
    }

    protected ItemList(Parcel in) {
        this.type = in.readString();
        this.data = in.readParcelable(Data.class.getClassLoader());
    }

    public static final Parcelable.Creator<ItemList> CREATOR = new Parcelable.Creator<ItemList>() {
        @Override
        public ItemList createFromParcel(Parcel source) {
            return new ItemList(source);
        }

        @Override
        public ItemList[] newArray(int size) {
            return new ItemList[size];
        }
    };
}
