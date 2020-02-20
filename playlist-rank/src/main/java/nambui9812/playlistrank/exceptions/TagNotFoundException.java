package nambui9812.playlistrank.exceptions;

public class TagNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public TagNotFoundException() {
    super("Tag not found.");
  }
}