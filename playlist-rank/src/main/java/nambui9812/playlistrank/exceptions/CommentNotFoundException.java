package nambui9812.playlistrank.exceptions;

public class CommentNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public CommentNotFoundException() {
    super("Comment not found.");
  }
}