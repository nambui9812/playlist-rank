package nambui9812.playlistrank.entities;

import lombok.Data;

import org.springframework.data.annotation.Id;

@Data
public class Comment {
  private @Id String id;
  private String authorUsername;
  private String playlistId;
  private String toCommentId;
  private String content;

  // Default constructor
  public Comment() {}

  // Custom constructor
  public Comment(
    String authorUsername,
    String playlistId,
    String toCommentId,
    String content
  ) {
    this.authorUsername = authorUsername;
    this.playlistId = playlistId;
    this.toCommentId = toCommentId;
    this.content = content;
  }

  // All get methods
  public String getId() {
    return id;
  }

  public String getAuthorUsername() {
    return authorUsername;
  }

  public String getPlaylistId() {
    return playlistId;
  }

  public String getToCommentId() {
    return toCommentId;
  }

  public String getContent() {
    return content;
  }
}
