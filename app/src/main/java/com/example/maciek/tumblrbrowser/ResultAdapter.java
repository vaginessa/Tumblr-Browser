package com.example.maciek.tumblrbrowser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

/**
 * Created by Maciek on 2017-03-10.
 */

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIDEO_POST = 3;
    private List<PostDetail> postList = Collections.emptyList();
    private OnItemClickActionListener onItemClickActionListener;
    private static final int PHOTO_POST = 1;
    private static final int TEXT_POST = 2;

    public void setOnItemClickActionListener(OnItemClickActionListener onItemClickActionListener) {
        this.onItemClickActionListener = onItemClickActionListener;
    }


    public void setPostList(List<PostDetail> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    public List<PostDetail> getPostList() {
        return postList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == PHOTO_POST) {
            View layoutPostsLists = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_photo, parent, false);
            return new PhotoViewHolder(layoutPostsLists);
        } else if (viewType == VIDEO_POST) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_video, parent, false);
            return new VideoViewHolder(layout);
        } else {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_text, parent, false);
            return new TextViewHolder(layout);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == PHOTO_POST) {

            PhotoViewHolder photoViewHolder = (PhotoViewHolder) holder;
            String url = postList.get(position).getPhotoUrl();
            //TODO zfixować replace
//            url = url.replace("\\", "");
            Glide.with(photoViewHolder.imagePhotoPost.getContext()).load(url).into(photoViewHolder.imagePhotoPost);
            photoViewHolder.slugPhotoPost.setText(postList.get(position).getSlug().replace("-", " ") + "...");
            photoViewHolder.datePhotoPost.setText("Poseted on: " + postList.get(position).getDate());
            photoViewHolder.typePhotoPost.setText("Type: " + postList.get(position).getType());
            photoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickActionListener != null) {
                        onItemClickActionListener.onItemClick(postList.get(position).getUrl().replace("\\", ""));
                    }
                }
            });

        } else if (getItemViewType(position) == VIDEO_POST) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            videoViewHolder.dateVideoPost.setText("Poseted on: " + postList.get(position).getDate());
            videoViewHolder.slugVideoPost.setText(postList.get(position).getDate());
            videoViewHolder.typeVideoPost.setText("Type: " + postList.get(position).getType());
            videoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickActionListener != null) {
                        onItemClickActionListener.onItemClick(postList.get(position).getUrl().replace("\\", ""));
                    }
                }
            });

        } else {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            textViewHolder.slugPostText.setText(postList.get(position).getSlug().replace("-", " ") + "...");
            textViewHolder.dateTextPost.setText("Poseted on: " + postList.get(position).getDate());
            textViewHolder.typeTextPost.setText("Type: " + postList.get(position).getType());
            textViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickActionListener != null) {
                        onItemClickActionListener.onItemClick(postList.get(position).getUrl().replace("\\", ""));
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (postList.get(position).getType().equals("regular")) {
            return TEXT_POST;
        } else if (postList.get(position).getType().equals("video")) {
            return VIDEO_POST;
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
        TextView slugPhotoPost;
        TextView datePhotoPost;
        TextView typePhotoPost;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            imagePhotoPost = (ImageView) itemView.findViewById(R.id.photo_post_image);
            slugPhotoPost = (TextView) itemView.findViewById(R.id.photo_post_slug);
            datePhotoPost = (TextView) itemView.findViewById(R.id.photo_post_date);
            typePhotoPost = (TextView) itemView.findViewById(R.id.photo_post_type);
        }

    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        TextView slugPostText;
        TextView dateTextPost;
        TextView typeTextPost;

        public TextViewHolder(View itemView) {
            super(itemView);
            slugPostText = (TextView) itemView.findViewById(R.id.text_post_slug);
            dateTextPost = (TextView) itemView.findViewById(R.id.text_post_date);
            typeTextPost = (TextView) itemView.findViewById(R.id.text_post_type);
        }
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView slugVideoPost;
        TextView dateVideoPost;
        TextView typeVideoPost;

        public VideoViewHolder(View itemView) {
            super(itemView);
            slugVideoPost = (TextView) itemView.findViewById(R.id.video_post_slug);
            dateVideoPost = (TextView) itemView.findViewById(R.id.video_post_date);
            typeVideoPost = (TextView) itemView.findViewById(R.id.video_post_type);

        }}

}
