package com.frp.whatsissue.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.frp.whatsissue.R;
import com.frp.whatsissue.models.GitIssue;
import com.joooonho.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saurav on 16/05/17.
 */

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {

    public List<GitIssue> gitIssueList = new ArrayList<>();
    Context context;

    public IssueAdapter(Context context){
        this.context = context;
    }

    @Override
    public IssueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.issue_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IssueAdapter.ViewHolder holder, int position) {
        GitIssue issue = gitIssueList.get(position);

        Picasso.with(context).load(issue.getUser().getAvatar()).into(holder.profilePicture);
        holder.username.setText(issue.getUser().getUsername());

        holder.issueBody.setText(issue.getBody());
        holder.issueTitle.setText(issue.getTitle());
        holder.issueState.setText(issue.getState());
    }

    @Override
    public int getItemCount() {
        return gitIssueList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile_picture)
        SelectableRoundedImageView profilePicture;

        @BindView(R.id.username)
        TextView username;

        @BindView(R.id.issue_state)
        RoundTextView issueState;

        @BindView(R.id.issue_title)
        TextView issueTitle;

        @BindView(R.id.issue_body)
        TextView issueBody;



        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
