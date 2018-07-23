package com.cemsit.githubrxjava.callback;

/**
 * Created by cems-it on 7/23/2018.
 */

public interface ResponseCallback {

    public void success(Object object);

    public void error(String error);
}
