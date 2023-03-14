package com.example.photosapi.model;

import jakarta.persistence.*;

@Entity
public class Photo {

    @Id
    @SequenceGenerator(
            name = "photo_id_sequence",
            sequenceName = "photo_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "photo_id_sequence"
    )
    private Integer id;
    private Integer albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
    private String auth;

    public Photo(Integer id, Integer albumId, String title, String url, String thumbnailUrl, String auth) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.auth = auth;
    }

    public Photo() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Photo clone() {
        Photo photo = new Photo();

        photo.setId(this.id);
        photo.setUrl(this.url);
        photo.setTitle(this.title);
        photo.setThumbnailUrl(this.thumbnailUrl);
        photo.setAlbumId(this.albumId);
        photo.setAuth(this.auth);

        return photo;
    }

    public String[] getDifferences(Photo newPhoto) {
        StringBuilder sbOld = new StringBuilder();
        StringBuilder sbNew = new StringBuilder();

        if (!this.albumId.equals(newPhoto.getAlbumId())) {
            sbNew.append("albumId=").append(newPhoto.getAlbumId()).append(", ");
            sbOld.append("albumId=").append(this.getAlbumId()).append(", ");
        };
        if (!this.title.equals(newPhoto.getTitle())) {
            sbNew.append("title=").append(newPhoto.getTitle()).append(", ");
            sbOld.append("title=").append(this.title).append(", ");
        };
        if (!this.url.equals(newPhoto.getUrl())) {
            sbNew.append("url=").append(newPhoto.getUrl()).append(", ");
            sbOld.append("url=").append(this.getUrl()).append(", ");
        };
        if (!this.thumbnailUrl.equals(newPhoto.getThumbnailUrl())) {
            sbNew.append("thumbnailUrl=").append(newPhoto.getThumbnailUrl()).append(", ");
            sbOld.append("thumbnailUrl=").append(this.getThumbnailUrl()).append(", ");
        };

        sbOld.setLength(Math.max(sbOld.length() - 2, 0));
        sbNew.setLength(Math.max(sbNew.length() - 2, 0));
        return new String[]{
                "{" + sbNew + "}",
                "{" + sbOld + "}"};
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
