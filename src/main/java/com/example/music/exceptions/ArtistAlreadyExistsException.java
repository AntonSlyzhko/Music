package com.example.music.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Artist already exists!")
public class ArtistAlreadyExistsException extends RuntimeException{
}
