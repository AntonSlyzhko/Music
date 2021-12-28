package com.example.music.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Album contains songs and cannot be deleted!")
public class AlbumContainsSongsException extends RuntimeException {
}
