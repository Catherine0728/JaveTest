package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Rxjava练习
 */
public class MyClass {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.merge();
//        myClass.subscribeObserVable();
//        myClass.just();
//        myClass.defer();
//        myClass.from();
//        myClass.empty();
//        myClass.repeat();
//        myClass.range();
//        myClass.timer();
//        myClass.start();
//        myClass.getDoubleInterger();
    }

    // • Create — 通过调用观察者的方法从头创建一个Observable
    public Observable<Integer> create() {
        String TAG = "create:";
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 1; i < 5; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }


    /**
     * 订阅observable
     */
    public void subscribeObserVable() {
        String TAG = "subscribeObserVable:";
        new MyClass().create().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println(TAG + "completed:");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(TAG + "error:" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(TAG + "next:" + integer);
            }
        });
    }

    int i = 10;

    //  • Defer — 在观察者订阅之前不创建这个Observable，为每一个观察者创建一个新的Observable
    public void defer() {
        String TAG = "defer:";
        Observable justObservable = Observable.just(i);
        i = 12;
        Observable deferObservable = Observable.defer(new Func0<Observable<Object>>() {
            @Override
            public Observable<Object> call() {
                System.out.println(TAG + i);
                return Observable.just(i);
            }
        });
        deferObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("defer result:" + o.toString());
            }
        });
        i = 15;

        justObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("just result:" + o.toString());
            }
        });

        deferObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("defer result:" + o.toString());
            }
        });
    }

    // • Empty/Never/Throw — 创建行为受限的特殊Observable
    public void empty() {
        String TAG = "empty:";
    }

    // • From — 将其它的对象或数据结构转换为Observable
    public void from() {
        String TAG = "from:";
    }

    //• Interval — 创建一个定时发射整数序列的Observable

    public void interval() {
        String TAG = "interval:";
        //产生从3开始，个数为10个的连续数字
        Observable.range(3, 10).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println(TAG + " complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(TAG + "error:" + e.getMessage());
            }

            @Override
            public void onNext(Integer i) {
                System.out.println(TAG + "Next:" + i.toString());
            }
        });
    }

    //  • Just — 将对象或者对象集合转换为一个会发射这些对象的Observable
    public void just() {
        String TAG = "just:";
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        Observable.just(stringList).subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> strings) {
                System.out.println(TAG + strings);
            }
        });

    }

    //  • Range — 创建发射指定范围的整数序列的Observable
    public void range() {
        String TAG = "range:";
        //连续产生两组(3,4,5)的数字
        Observable.range(11, 3).repeat(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println(TAG + " complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(TAG + "error:" + e.getMessage());
            }

            @Override
            public void onNext(Integer i) {
                System.out.println(TAG + "Next:" + i.toString());
            }
        });
    }

    // • Repeat — 创建重复发射特定的数据或数据序列的Observable
    public void repeat() {
        String TAG = "repeat:";
        //连续产生两组(3,4,5)的数字
        Observable.range(3, 3).repeat(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println(TAG + " complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(TAG + "error:" + e.getMessage());
            }

            @Override
            public void onNext(Integer i) {
                System.out.println(TAG + "Next:" + i.toString());
            }
        });
    }

    // • Start — 创建发射一个函数的返回值的Observable
    public void start() {
        String TAG = "start:";
    }

    //  • Timer — 创建在一个指定的延迟之后发射单个数据的Observable
    public void timer() {
        String TAG = "timer:";
        //每隔两秒产生一个数字
        Observable.timer(2, 2, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println(TAG + " complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(TAG + "error:" + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println(TAG + "Next:" + aLong.toString());
            }
        });
    }

    public void getDoubleInterger() {
        String TAG = "getDoubleInterger:";
        Integer[] numbers = new Integer[]{
                1, 3, 5, 7, 9
        };
        List<Integer> lists = Observable.from(numbers).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer * 2;
            }
        }).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 10;
            }
        }).toList().toBlocking().first();
        System.out.println(TAG + lists);
//        for (Integer integer : lists) {
//            System.out.println(integer);
//        }
    }

    public void merge() {
        String TAG = "MERGE";
        String[] str = new String[]{"hello", "world", "what's up"};
        Observable s = Observable.create(subscriber -> {
            for (String s1 : str) {
                System.out.println(s1);
            }
        });
        Observable sec = Observable.create(subscriber -> {
            new Thread(() -> {
                for (int i1 = 0; i1 <10; i1++){
                    System.out.println(i1);
                }
            }).start();
            for (String s1 : str) {
                System.out.println(TAG+s1);
            }
        });
        Observable.merge(sec, s).subscribe(o -> {
            System.out.println(o.toString());
        });
    }
}
