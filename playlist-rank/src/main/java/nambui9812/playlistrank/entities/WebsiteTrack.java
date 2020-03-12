package nambui9812.playlistrank.entities;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
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

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public String getTrackName() {
    return trackName;
  }

  public void setTrackName(String trackName) {
    this.trackName = trackName;
  }
}
