package com.example.maciek.tumblrbrowser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

/**
 * Created by Maciek on 2017-03-10.
 */

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostDetail> postList = Collections.emptyList();
    private static final int PHOTO_POST = 1;
    private static final int TEXT_POST = 2;

    public void setPostList(List<Post> postList) {
        this.postList = postList.get(0).getPosts();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==PHOTO_POST) {
            View layoutPostsLists = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_photo, parent, false);
            return new PhotoViewHolder(layoutPostsLists);
        } else {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_text,parent,false);
            return new TextViewHolder(layout);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TEXT_POST) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            textViewHolder.textPostText.setText(postList.get(position).getRegularText());
            textViewHolder.dateTextPost.setText(postList.get(position).getDate());
        } else {
            PhotoViewHolder photoViewHolder = (PhotoViewHolder) holder;
            String url = postList.get(position).getPhotoUrl();
            url = url.replace("\\", "");
            Glide.with(photoViewHolder.imagePhotoPost.getContext()).load(url).into(photoViewHolder.imagePhotoPost);
            photoViewHolder.textPhotoPost.setText(postList.get(position).getPhotoText());
            photoViewHolder.datePhotoPost.setText(postList.get(position).getDate());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (postList.get(position).getType().equals("regular")){
            return TEXT_POST;
        } else {
            return PHOTO_POST;
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    class PhotoViewHolder extends RecyclerView.ViewHolder {

        ImageView imagePhotoPost;
        TextView textPhotoPost;
        TextView datePhotoPost;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            imagePhotoPost = (ImageView) itemView.findViewById(R.id.photo_post_image);
            textPhotoPost = (TextView) itemView.findViewById(R.id.photo_post_text);
            datePhotoPost = (TextView) itemView.findViewById(R.id.photo_post_date);
        }

    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        TextView textPostText;
        TextView dateTextPost;

        public TextViewHolder(View itemView) {
            super(itemView);
            textPostText = (TextView) itemView.findViewById(R.id.text_post_text);
            dateTextPost = (TextView) itemView.findViewById(R.id.text_post_date);
        }
    }
}
