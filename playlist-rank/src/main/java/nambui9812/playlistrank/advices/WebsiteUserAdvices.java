package nambui9812.playlistrank.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.exceptions.WebsiteUserNotFoundException;

@ControllerAdvice
public class WebsiteUserAdvices {

  @ResponseBody
  @ExceptionHandler(WebsiteUserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String userNotFoundHandler(WebsiteUserNotFoundException ex) {
    return ex.getMessage();
  }

}