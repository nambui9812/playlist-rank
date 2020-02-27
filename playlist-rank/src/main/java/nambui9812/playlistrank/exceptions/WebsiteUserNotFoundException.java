package nambui9812.playlistrank.exceptions;

public class WebsiteUserNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public WebsiteUserNotFoundException() {
    super("User not found.");
  }
}