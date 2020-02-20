package nambui9812.playlistrank.exceptions;

public class PlaylistNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PlaylistNotFoundException() {
    super("Playlist not found.");
  }
}