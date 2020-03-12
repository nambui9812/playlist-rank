package nambui9812.playlistrank.advices;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
  // Error handler for @Valid
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException e,
    HttpHeaders headers,
    HttpStatus status,
    WebRequest req
  ) {
    HashMap<String, Object> res = new HashMap<>();
    res.put("success", false);

    // Get all errors
    List<String> errors = e.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(x -> x.getDefaultMessage())
      .collect(Collectors.toList());

      res.put("messages", errors);

      return new ResponseEntity<>(res, headers, status);
  }
}