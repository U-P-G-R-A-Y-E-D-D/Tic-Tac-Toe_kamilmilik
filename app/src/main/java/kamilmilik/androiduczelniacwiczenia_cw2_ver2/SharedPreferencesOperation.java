package kamilmilik.androiduczelniacwiczenia_cw2_ver2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kamil on 10.01.2018.
 */

class SharedPreferencesOperation {
    private static final String TAG = "SharedPreferences";
    private SharedPreferences sharedPreferences;
    private Activity activity;
    public SharedPreferencesOperation(Activity activity, String key) {
        this.activity = activity;
        sharedPreferences =  this.activity.getSharedPreferences(key,(MODE_PRIVATE));;
    }
    public void saveToSharedPreferences(String key ,int[][] value){
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        List<Integer> object = getTransformed2DArrayToList(value);
        String json = gson.toJson(object);
        prefsEditor.putString(key, json);
        prefsEditor.commit();
        Log.i(TAG,json.toString());
    }
    public List<Integer> getTransformed2DArrayToList(int[][] array){
        List<Integer> listOfInt = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                listOfInt.add(array[i][j]);
            }
        }
        return listOfInt;
    }
    public Integer[][] getItem(String key){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, "");
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        ArrayList<Integer> arrayList = gson.fromJson(json, type);
        Integer[][] array = new Integer[3][3];
        int k = 0;
        if (arrayList != null) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    array[i][j] = arrayList.get(k);
                    k++;
                }
            }
        }
        return array;
    }
    public void saveIntToShared(String key, int value){
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(key,value);
        prefsEditor.commit();
    }
    public int  getIntFromShared(String key){
        return sharedPreferences.getInt( key, -1);
    }
    public void deleteAll(){
        sharedPreferences.edit().clear().commit();
    }

}
