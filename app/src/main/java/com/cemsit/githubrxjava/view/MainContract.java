package com.cemsit.githubrxjava.view;

import android.app.ProgressDialog;
import android.util.Log;

import com.cemsit.githubrxjava.callback.ResponseCallback;
import com.cemsit.githubrxjava.model.GitHubRepo;
import com.cemsit.githubrxjava.network.GitHubClient;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cems-it on 7/23/2018.
 */

public class MainContract {

    private Subscription subscription;

    public MainContract(Subscription subscription) {
        this.subscription = subscription;
    }

    public void getStarredRepos(String username, final ResponseCallback callback) {

        subscription = GitHubClient.getInstance()
                .getStarredRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GitHubRepo>>() {
                    @Override public void onCompleted() {
                    }

                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        callback.error(e.getMessage());
                    }

                    @Override public void onNext(List<GitHubRepo> gitHubRepos) {
                        callback.success(gitHubRepos);
                    }
                });
    }

    public void  Destory(){
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            Log.e("destory","destroy");
        }
    }


}
