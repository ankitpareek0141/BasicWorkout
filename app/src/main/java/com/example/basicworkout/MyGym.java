package com.example.basicworkout;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ArrayAdapter;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

public class MyGym implements Parcelable {
    private int id;
    private String name;
    private String description;
    private String imageURL;

    public MyGym(int id, String name, String description, String imageURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
    }

    public MyGym(){

    }

    protected MyGym(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        imageURL = in.readString();
    }

    public static final Creator<MyGym> CREATOR = new Creator<MyGym>() {
        @Override
        public MyGym createFromParcel(Parcel in) {
            return new MyGym(in);
        }

        @Override
        public MyGym[] newArray(int size) {
            return new MyGym[size];
        }
    };

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "MyGym{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imageURL);
    }
}
