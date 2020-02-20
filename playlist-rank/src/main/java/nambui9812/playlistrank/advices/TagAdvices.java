package nambui9812.playlistrank.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.exceptions.TagNotFoundException;

@ControllerAdvice
public class TagAdvices {

  @ResponseBody
  @ExceptionHandler(TagNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String tagNotFoundHandler(TagNotFoundException ex) {
    return ex.getMessage();
  }

}