package com.example.photosapi.util;

import com.example.photosapi.dto.PhotoDto;
import com.example.photosapi.model.Photo;
import org.springframework.security.core.context.SecurityContextHolder;

public class PhotoMapper {

    public Photo toPhoto(PhotoDto dto) {
        Photo photo = new Photo();
        photo.setAlbumId(dto.getAlbumId());
        photo.setThumbnailUrl(dto.getThumbnailUrl());
        photo.setTitle(dto.getTitle());
        photo.setUrl(dto.getUrl());
        photo.setAuth(SecurityContextHolder.getContext().getAuthentication().getName());
        return photo;
    }

    public PhotoDto toDto(Photo photo) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setAlbumId(photo.getAlbumId());
        photoDto.setThumbnailUrl(photo.getThumbnailUrl());
        photoDto.setTitle(photo.getTitle());
        photoDto.setUrl(photo.getUrl());
        return photoDto;
    }
}
