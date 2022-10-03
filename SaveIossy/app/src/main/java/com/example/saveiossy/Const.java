package com.example.saveiossy;

import android.media.MediaPlayer;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

class Const {

    public static String playerName = "";
    public static boolean libraryKeyA = false;
    public static boolean libraryKeyAB = false;
    public static boolean sharatKey = false;
    public static boolean approomKey = false;
    public static boolean hasName = false;
    public static boolean fourDigitPass = false;
    public static String shownPlayerName = "";
    public static boolean finishedGame = false;
    ////
    // all Sounds
    public static Hashtable<String, Integer> soundTrack = makeSoundTrack();

    public static Hashtable<String, Integer> makeSoundTrack () {
        Hashtable<String, Integer> dict = new Hashtable<>();

        dict.put("end music", R.raw.atmos1);
        dict.put("in appclass", R.raw.hopefulrobot);
        dict.put("light solved", R.raw.keycardsound);
        dict.put("light switch", R.raw.lightswitch);
        dict.put("locked", R.raw.locked);
        dict.put("redgreen button", R.raw.switchlit);
        dict.put("unlock", R.raw.unlock);
        dict.put("win xp shutdown", R.raw.windowsxpshutdown);
        dict.put("win xp startup", R.raw.windowsxpstartup);
        dict.put("win xp error", R.raw.windowsxperror);
        dict.put("sharat puzzle solved", R.raw.fallingpiece);
        dict.put("grab", R.raw.grab);
        dict.put("platepress", R.raw.platepress);
        dict.put("metalpress", R.raw.metalpress);
        dict.put("mediumpress", R.raw.mediumpress);
        dict.put("beep", R.raw.beepbutton);
        dict.put("hint", R.raw.hint);
        dict.put("wrong", R.raw.wrong);

        return dict;
    }


    public static void resetGame() {
        playerName = "";
        libraryKeyA = false;
        libraryKeyAB = false;
        sharatKey = false;
        approomKey = false;
        hasName = false;
        fourDigitPass = false;
        shownPlayerName = "";
        finishedGame = false;
    }

    public static void setShownPlayerName(String playerName) {
        shownPlayerName = "";
        char[] playerNameChar = playerName.toCharArray();
        if(playerNameChar.length>10) {
            int shownCharNum;
            if(playerNameChar.length<12)
                shownCharNum = 8;
            else
                shownCharNum = 10;
            for (int i = 0; i < shownCharNum; i++) {
                shownPlayerName += playerNameChar[i];
            }

            shownPlayerName += "...";
        }
        else {
            for (int i = 0; i < playerNameChar.length; i++) {
                shownPlayerName += playerNameChar[i];
            }
        }

    }


}
