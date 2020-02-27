package nambui9812.playlistrank.entities;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comments")
public class Comment {
  private @Id String id;

  @NotNull(message = "Author username is mandatory.")
  @Size(min = 4, max = 20, message = "Author username must be from 4 to 20 in size.")
  private String authorUsername;

  @NotNull(message = "A comment must be belonged to a playlist.")
  private String playlistId;

  private String toCommentId;

  @NotNull(message = "Content of a comment is mandatory")
  @Size(min = 1, message = "Comment must have at least 1 character in size.")
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
