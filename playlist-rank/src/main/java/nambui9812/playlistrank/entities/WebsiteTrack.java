package nambui9812.playlistrank.entities;

import javax.validation.constraints.NotBlank;

public class WebsiteTrack {
  @NotBlank(message = "Author of track is mandatory.")
  private String authorName;

  @NotBlank(message = "Name of track is mandatory.")
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
