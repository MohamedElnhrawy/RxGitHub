package com.cemsit.githubrxjava;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.cemsit.githubrxjava.callback.ResponseCallback;
import com.cemsit.githubrxjava.model.GitHubRepo;
import com.cemsit.githubrxjava.network.GitHubClient;
import com.cemsit.githubrxjava.view.GitHubRepoAdapter;
import com.cemsit.githubrxjava.view.MainContract;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private GitHubRepoAdapter adapter = new GitHubRepoAdapter();

    MainContract mainContract;
    private Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ProgressDialog dialog=new ProgressDialog(this);
        mainContract=new MainContract(subscription);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.list_view_repos);
        listView.setAdapter(adapter);

        final EditText editTextUsername = (EditText) findViewById(R.id.edit_text_username);
        final Button buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final String username = editTextUsername.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    dialog.show();
                    mainContract.getStarredRepos(username, new ResponseCallback() {
                        @Override
                        public void success(Object object) {
                            dialog.dismiss();
                            adapter.setGitHubRepos((List<GitHubRepo>) object);
                        }

                        @Override
                        public void error(String error) {
                            Log.e("error",error);
                            dialog.dismiss();
                        }
                    });

                }
            }
        });
    }

    @Override protected void onDestroy() {
        mainContract.Destory();
        super.onDestroy();
    }

}
