package com.cemsit.githubrxjava.network;

import com.cemsit.githubrxjava.model.GitHubRepo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by cems-it on 7/23/2018.
 */

public interface GitHubService {
    @GET("users/{user}/starred")
    Observable<List<GitHubRepo>> getStarredRepositories
            (@Path("user") String userName);

}
