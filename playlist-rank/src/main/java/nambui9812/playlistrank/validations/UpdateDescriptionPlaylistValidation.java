package nambui9812.playlistrank.validations;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateDescriptionPlaylistValidation {
  @NotNull(message = "Playlist's id is mandatory.")
  private String id;

  private String description;

  public UpdateDescriptionPlaylistValidation(
    String id,
    String description
  ) {
    this.id = id;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }
}