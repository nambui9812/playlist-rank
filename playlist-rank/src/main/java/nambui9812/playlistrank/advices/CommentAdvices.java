package nambui9812.playlistrank.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.exceptions.CommentNotFoundException;

@ControllerAdvice
public class CommentAdvices {

  @ResponseBody
  @ExceptionHandler(CommentNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String commentNotFoundHandler(CommentNotFoundException ex) {
    return ex.getMessage();
  }

}