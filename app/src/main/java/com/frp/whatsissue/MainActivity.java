package com.frp.whatsissue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.frp.whatsissue.adapters.IssueAdapter;
import com.frp.whatsissue.api.GithubService;
import com.frp.whatsissue.models.GitIssue;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    @BindView(R.id.search_bar)
    EditText searchBar;

    @BindView(R.id.issues_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.empty_screen_layout)
    LinearLayout emptyScreen;

    @BindView(R.id.empty_screen_text)
    TextView emptyScreemText;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    GithubService githubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initializeService();

        searchBar.setOnEditorActionListener(this);
    }

    void initializeService(){
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).baseUrl(Utils.BASE_API_URL).build();

        githubService = retrofit.create(GithubService.class);
    }

    void startSearching(){
        String username = searchBar.getText().toString().split("/")[0];
        String repoName = searchBar.getText().toString().split("/")[1];

        Observable<List<GitIssue>> githubIssue = githubService.getRepoIssues(username, repoName);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        emptyScreen.setVisibility(View.GONE);

        githubIssue.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(issues -> {
            if(issues.size() == 0){
                recyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                emptyScreen.setVisibility(View.VISIBLE);
                emptyScreemText.setText(R.string.error_screen_string);
                return;
            }
            IssueAdapter adapter = new IssueAdapter(MainActivity.this);
            adapter.gitIssueList = issues;
            setupRecyclerView(adapter);
        });

    }

    void setupRecyclerView(IssueAdapter adapter){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        emptyScreen.setVisibility(View.GONE);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_SEARCH)) {
            startSearching();
        }
        return false;
    }
}
