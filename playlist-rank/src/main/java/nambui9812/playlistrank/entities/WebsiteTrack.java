package nambui9812.playlistrank.entities;

import javax.validation.constraints.NotNull;

public class WebsiteTrack {
  @NotNull(message = "Author of track is mandatory.")
  private String authorName;

  @NotNull(message = "Name of track is mandatory.")
  private String trackName;

  public WebsiteTrack(
    String authorName,
    String trackName
  ) {
    this.authorName = authorName;
    this.trackName = trackName;
  }

  public String getAuthorName() {
    return authorName;
  }

  public String getTracKName() {
    return trackName;
  }
}
