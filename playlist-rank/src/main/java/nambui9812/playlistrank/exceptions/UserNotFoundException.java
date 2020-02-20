package nambui9812.playlistrank.exceptions;

public class UserNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  UserNotFoundException() {
    super("User not found.");
  }
}