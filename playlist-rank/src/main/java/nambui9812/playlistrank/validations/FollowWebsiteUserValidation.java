package nambui9812.playlistrank.validations;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FollowWebsiteUserValidation {
  @NotNull(message = "User's id is mandatory.")
  private String id;

  @NotNull(message = "Username of person whom you want to follow is mandatory.")
  private String followUsername;

  public FollowWebsiteUserValidation(
    String id,
    String followUsername
  ) {
    this.id = id;
    this.followUsername = followUsername;
  }

  public String getId() {
    return id;
  }

  public String getFollowUsername() {
    return followUsername;
  }
}
