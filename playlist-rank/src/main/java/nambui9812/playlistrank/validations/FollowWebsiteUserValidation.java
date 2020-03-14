package nambui9812.playlistrank.validations;

import javax.validation.constraints.NotNull;

public class FollowWebsiteUserValidation {
  @NotNull(message = "Username id is mandatory.")
  private String username;

  @NotNull(message = "Username of person whom you want to follow is mandatory.")
  private String followUsername;

  public FollowWebsiteUserValidation(
    String username,
    String followUsername
  ) {
    this.username = username;
    this.followUsername = followUsername;
  }

  public String getUsername() {
    return username;
  }

  public String getFollowUsername() {
    return followUsername;
  }
}
