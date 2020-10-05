package com.example.basicworkout;

import android.util.Log;

import java.util.ArrayList;

public class Utils {
    private static final String TAG = "Utils";
    private static ArrayList<MyGym> allTrainings;
    private static ArrayList<Plans> usersPlan;

    public Utils(){
        Log.d(TAG, "Utils: called");
        if(null==allTrainings)
            allTrainings = new ArrayList<>();
        if(null==usersPlan)
            usersPlan = new ArrayList<>();
        MyGym squatsTrain = new MyGym();
        squatsTrain.setName("Squats");
        squatsTrain.setDescription("Stand tall with your feet at hip-width distance apart, shoulders relaxed. Look ahead to keep your neck aligned with your spine, and hold your arms straight in front of you or on your hips.");
        squatsTrain.setImageURL("https://experiencelife.com/wp-content/uploads/2017/03/Squat-1280x720.jpg");
        allTrainings.add(squatsTrain);

        MyGym plankTrain = new MyGym();
        plankTrain.setName("Plank");
        plankTrain.setDescription("Keep your body a straight line from the top of your head to the tips of your heels. Cup your wrists together if they hurt from the pressure.");
        plankTrain.setImageURL("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/hdm119918mh15842-1545237096.png");
        allTrainings.add(plankTrain);

        MyGym pushupTrain = new MyGym();
        pushupTrain.setName("Push-up");
        pushupTrain.setDescription("A push-up (or press-up if the hands are wider than shoulders placing more emphasis on the pectoral muscles[citation needed]) is a common calisthenics exercise beginning from the prone position");
        pushupTrain.setImageURL("https://miro.medium.com/proxy/1*fofv1IipvCgYDFA3WwZS9g.jpeg");
        allTrainings.add(pushupTrain);
    }

    public static ArrayList<MyGym> getAllTrainings() {
        return allTrainings;
    }

    public static void setAllTrainings(ArrayList<MyGym> allTrainings) {
        Utils.allTrainings = allTrainings;
    }

    public static ArrayList<Plans> getUsersPlan() {
        return usersPlan;
    }

    public static void setUsersPlan(ArrayList<Plans> usersPlan) {
        Utils.usersPlan = usersPlan;
    }

    public static boolean addUsersPlan(Plans plan){
        return usersPlan.add(plan);
    }

    public static boolean removeUsersPlan(Plans plan){
        return usersPlan.remove(plan);
    }
}
