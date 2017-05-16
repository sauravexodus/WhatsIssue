package com.frp.whatsissue.api;

import com.frp.whatsissue.models.GitIssue;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Saurav on 16/05/17.
 */

public interface GithubService {

    @GET("repos/{username}/{repo}/issues")
    Observable<List<GitIssue>> getRepoIssues(@Path("username") String username, @Path("repo") String repoName);
}
