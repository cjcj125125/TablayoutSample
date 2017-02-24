package com.rx.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class MainActivity extends AppCompatActivity {
//
//    public static void main(String[] args) {
//        int a = 10;
//        int b = 10;
//        method(a, b);
//        System.out.print("a=" + a);
//        System.out.print("b=" + b);
//    }
//
//    public static void method(int a, int b) {
//        System.out.println("a=" + 100+",b="+100);
//        Runtime.getRuntime().exit(0);
//        //  System.exit(0);
// }
//



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdata();
    }

    private void initdata() {
        //-------------1、基础用法-------------------------
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("---------被观察者发送de消息-----------");
                e.onComplete();
            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.i("TAG", "观察者观察到-------" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);//被观察者绑定观察者
        //-------------华丽的分割线-------------------------
        //-------------2、基础用法-------------------------
        Subscriber<String> subscriber = new Subscriber<String>() {
            private Subscription mSubscription;

            @Override
            public void onSubscribe(Subscription s) {
                //用Subscriber作为观察者的时候必须加上这个，来控制subscriber要接收事件的数量，如果不加，onNext()方法不会被调用
                s.request(Integer.MAX_VALUE);//Long.MAX_VALUE
                mSubscription = s;
            }

            @Override
            public void onNext(String s) {
                Log.i("TAG", "观察者接收到----" + s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
        Flowable.just("被观察者发送个消息试试-------").subscribe(subscriber);
        //------------------------------华丽的分割线---------------------------------------------
        //-------------3、基础用法-------------------------
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("被观察者发出了一个消息-1-------");
                e.onNext("被观察者发出了一个消息-2-------");
                e.onNext("被观察者发出了一个消息-3-------");
                Log.i("TAG", "打印3");
                e.onNext("被观察者发出了一个消息-4-------");
                Log.i("TAG", "打印4");
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                Log.i("TAG", "------Disposable--" + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                Log.i("TAG", value);
                i++;
                if (i == 2) {
                    mDisposable.dispose();//调用后被观察者继续发送消息，但是观察者不再接收消息
                    Log.i("TAG", "------Disposable====--" + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    //------------------------------华丽的分割线---------------------------------------------
Flowable<String>flowable=Flowable.create(new FlowableOnSubscribe<String>() {
        @Override
        public void subscribe(FlowableEmitter<String> e) throws Exception {

        }
    },BackpressureStrategy.BUFFER);
//            .subscribeOn(Schedulers.computation()).observeOn(Schedulers.newThread()).subscribe(new Consumer<String>() {
//        @Override
//        public void accept(String s) throws Exception {
//
//        }
//    });

//ResourceSubscriber  resourceSubscriber=new ResourceSubscriber<String>() {
//    @Override
//    public void onNext(String o) {
//
//    }
//
//    @Override
//    public void onError(Throwable t) {
//
//    }
//
//    @Override
//    public void onComplete() {
//
//    }
//};
//    Subscriber subscriber=new Subscriber() {
//    @Override
//    public void onSubscribe(Subscription s) {
//
//    }
//
//    @Override
//    public void onNext(Object o) {
//
//    }
//
//    @Override
//    public void onError(Throwable t) {
//
//    }
//
//    @Override
//    public void onComplete() {
//
//    }
//};



}
