package com.brodowski.maciek.tumblrbrowser;

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

    private List<PostDetails> postDetailsList = Collections.emptyList();
    private OnItemClickActionListener onItemClickActionListener;
    private static final int PHOTO_POST = 1;
    private static final int TEXT_POST = 2;
    private static final int VIDEO_POST = 3;
    private static final int AUDIO_POST = 4;
    private static final int LINK_POST = 5;
    private static final int QUOTE_POST = 6;



    public void setOnItemClickActionListener(OnItemClickActionListener onItemClickActionListener) {
        this.onItemClickActionListener = onItemClickActionListener;
    }


    public void setPostDetailsList(List<PostDetails> postDetailsList) {
        this.postDetailsList = postDetailsList;
        notifyDataSetChanged();
    }

    public List<PostDetails> getPostDetailsList() {
        return postDetailsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == PHOTO_POST) {
            View layoutPostsLists = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_photo, parent, false);
            return new PhotoViewHolder(layoutPostsLists);
        } else if (viewType == VIDEO_POST) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_video, parent, false);
            return new VideoViewHolder(layout);
        } else if (viewType == AUDIO_POST) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_audio, parent, false);
            return new AudioViewHolder(layout);
        } else if (viewType == LINK_POST) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_link, parent, false);
            return new LinkViewHolder(layout);
        } else if (viewType == QUOTE_POST) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_quote, parent, false);
            return new QuoteViewHolder(layout);
        } else {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_text, parent, false);
            return new TextViewHolder(layout);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == PHOTO_POST) {

            PhotoViewHolder photoViewHolder = (PhotoViewHolder) holder;
            String url = postDetailsList.get(position).getPhotoUrl();
            //TODO zfixować replace
//            url = url.replace("\\", "");
            Glide.with(photoViewHolder.imagePhotoPost.getContext()).load(url).into(photoViewHolder.imagePhotoPost);
            photoViewHolder.slugPhotoPost.setText(postDetailsList.get(position).getSlug().replace("-", " ") + "...");
            photoViewHolder.datePhotoPost.setText("Poseted on: " + postDetailsList.get(position).getDate());
            photoViewHolder.typePhotoPost.setText("Type: " + postDetailsList.get(position).getType());
            photoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickActionListener != null) {
                        onItemClickActionListener.onItemClick(postDetailsList.get(position).getUrl().replace("\\", ""));
                    }
                }
            });

        } else if (getItemViewType(position) == VIDEO_POST) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            videoViewHolder.dateVideoPost.setText("Poseted on: " + postDetailsList.get(position).getDate());
            videoViewHolder.slugVideoPost.setText(postDetailsList.get(position).getSlug());
            videoViewHolder.typeVideoPost.setText("Type: " + postDetailsList.get(position).getType());
            videoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickActionListener != null) {
                        onItemClickActionListener.onItemClick(postDetailsList.get(position).getUrl().replace("\\", ""));
                    }
                }
            });
        } else if (getItemViewType(position) == AUDIO_POST) {
            AudioViewHolder audioViewHolder = (AudioViewHolder) holder;
            audioViewHolder.dateAudio.setText("Poseted on: " + postDetailsList.get(position).getDate());
            audioViewHolder.slugAudio.setText(postDetailsList.get(position).getSlug());
            audioViewHolder.typeAudio.setText("Type: " + postDetailsList.get(position).getType());
            audioViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickActionListener != null) {
                        onItemClickActionListener.onItemClick(postDetailsList.get(position).getUrl().replace("\\", ""));
                    }
                }
            });
        } else if (getItemViewType(position) == LINK_POST) {
            LinkViewHolder linkViewHolder = (LinkViewHolder) holder;
            linkViewHolder.dateLink.setText("Poseted on: " + postDetailsList.get(position).getDate());
            linkViewHolder.slugLink.setText(postDetailsList.get(position).getSlug());
            linkViewHolder.typeLink.setText("Type: " + postDetailsList.get(position).getType());
            linkViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickActionListener != null) {
                        onItemClickActionListener.onItemClick(postDetailsList.get(position).getUrl().replace("\\", ""));
                    }
                }
            });
        } else if (getItemViewType(position) == QUOTE_POST) {
            QuoteViewHolder quoteViewHolder = (QuoteViewHolder) holder;
            quoteViewHolder.dateQuote.setText("Poseted on: " + postDetailsList.get(position).getDate());
            quoteViewHolder.slugQuote.setText(postDetailsList.get(position).getSlug());
            quoteViewHolder.typeQuote.setText("Type: " + postDetailsList.get(position).getType());
            quoteViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickActionListener != null) {
                        onItemClickActionListener.onItemClick(postDetailsList.get(position).getUrl().replace("\\", ""));
                    }
                }
            });

        } else {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            textViewHolder.slugPostText.setText(postDetailsList.get(position).getSlug().replace("-", " ") + "...");
            textViewHolder.dateTextPost.setText("Poseted on: " + postDetailsList.get(position).getDate());
            textViewHolder.typeTextPost.setText("Type: " + postDetailsList.get(position).getType());
            textViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickActionListener != null) {
                        onItemClickActionListener.onItemClick(postDetailsList.get(position).getUrl().replace("\\", ""));
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (postDetailsList.get(position).getType().equals("photo")) {
            return PHOTO_POST;
        } else if (postDetailsList.get(position).getType().equals("video")) {
            return VIDEO_POST;
        } else if (postDetailsList.get(position).getType().equals("audio")) {
            return AUDIO_POST;
        } else if (postDetailsList.get(position).getType().equals("link")) {
            return LINK_POST;
        } else if (postDetailsList.get(position).getType().equals("quote")) {
            return QUOTE_POST;
        } else {
            return TEXT_POST;
        }
    }

    @Override
    public int getItemCount() {
        return postDetailsList.size();
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

        }
    }

    class AudioViewHolder extends RecyclerView.ViewHolder {

        TextView slugAudio;
        TextView dateAudio;
        TextView typeAudio;

        public AudioViewHolder(View itemView) {
            super(itemView);
            slugAudio = (TextView) itemView.findViewById(R.id.audio_post_slug);
            dateAudio = (TextView) itemView.findViewById(R.id.audio_post_date);
            typeAudio = (TextView) itemView.findViewById(R.id.audio_post_type);

        }
    }

    class LinkViewHolder extends RecyclerView.ViewHolder {

        TextView slugLink;
        TextView dateLink;
        TextView typeLink;

        public LinkViewHolder(View itemView) {
            super(itemView);
            slugLink = (TextView) itemView.findViewById(R.id.link_post_slug);
            dateLink = (TextView) itemView.findViewById(R.id.link_post_date);
            typeLink = (TextView) itemView.findViewById(R.id.link_post_type);

        }
    }

    class QuoteViewHolder extends RecyclerView.ViewHolder {

        TextView slugQuote;
        TextView dateQuote;
        TextView typeQuote;

        public QuoteViewHolder(View itemView) {
            super(itemView);
            slugQuote = (TextView) itemView.findViewById(R.id.quote_post_slug);
            dateQuote = (TextView) itemView.findViewById(R.id.quote_post_date);
            typeQuote = (TextView) itemView.findViewById(R.id.quote_post_type);

        }
    }


}
