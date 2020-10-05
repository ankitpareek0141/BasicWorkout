package com.example.basicworkout;

import android.os.Parcel;
import android.os.Parcelable;

public class Plans implements Parcelable {
    private String days;
    private int minutes;
    private MyGym gym;
    private boolean isAccomplished;

    public Plans(String days, int minutes, MyGym gym, boolean isAccomplished) {
        this.days = days;
        this.minutes = minutes;
        this.gym = gym;
        this.isAccomplished = isAccomplished;
    }

    public Plans(){ }            // default constructor

    protected Plans(Parcel in) {
        days = in.readString();
        minutes = in.readInt();
        gym = in.readParcelable(MyGym.class.getClassLoader());
        isAccomplished = in.readByte() != 0;
    }

    public static final Creator<Plans> CREATOR = new Creator<Plans>() {
        @Override
        public Plans createFromParcel(Parcel in) {
            return new Plans(in);
        }

        @Override
        public Plans[] newArray(int size) {
            return new Plans[size];
        }
    };

    public boolean getIsAccomplished() {
        return isAccomplished;
    }

    public void setIsAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public MyGym getGym() {
        return gym;
    }

    public void setGym(MyGym gymObj) {
        gym = gymObj;
    }

    @Override
    public String toString() {
        return "Plans{" +
                "days='" + days + '\'' +
                ", minutes=" + minutes +
                ", gym=" + gym +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(days);
        dest.writeInt(minutes);
        dest.writeParcelable(gym, flags);
        dest.writeByte((byte) (isAccomplished ? 1 : 0));
    }
}
