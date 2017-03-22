package com.example.catherine.javetest;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by catherine on 16/2/3.
 */
public class MyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void getDoubleInterger() {
        Integer[] numbers = new Integer[]{1, 3, 5, 7, 9};
        
        /*Observable写法，一切都好多了*/
        List<Integer> funactionalList = Observable.from(numbers).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer * 2;
            }
        }).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer < 10;
            }
        }).toList().toBlocking().first();
    }
}
