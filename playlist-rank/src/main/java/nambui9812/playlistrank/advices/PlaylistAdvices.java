package nambui9812.playlistrank.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.exceptions.PlaylistNotFoundException;

@ControllerAdvice
public class PlaylistAdvices {

  @ResponseBody
  @ExceptionHandler(PlaylistNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String playlistNotFoundHandler(PlaylistNotFoundException ex) {
    return ex.getMessage();
  }

}