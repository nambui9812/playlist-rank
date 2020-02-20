package nambui9812.playlistrank.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.exceptions.UserNotFoundException;

@ControllerAdvice
public class UserAdvice {

  @ResponseBody
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String userNotFoundHandler(UserNotFoundException ex) {
    return ex.getMessage();
  }

}