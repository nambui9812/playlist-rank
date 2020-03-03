package nambui9812.playlistrank.validations;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SharePlaylistValidation {
  @NotNull(message = "Playlist's id is mandatory.")
  private String id;

  @NotNull(message = "Author username is mandatory.")
  @NotBlank
  private String authorUsername;      // Name of person who creates this playlist

  private String description;

  private ArrayList<String> tracks;   // List of names of tracks

  public SharePlaylistValidation(
    String authorUsername,
    String description,
    ArrayList<String> tracks
  ) {
    this.authorUsername = authorUsername;
    this.description = description;
    this.tracks = tracks;
  }

  public String getId() {
    return id;
  }

  public String getAuthorUsername() {
    return authorUsername;
  }

  public String getDescription() {
    return description;
  }

  public ArrayList<String> getTracks() {
    return tracks;
  }
}