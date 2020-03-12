package nambui9812.playlistrank.entities;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tags")
public class Tag {
  private @Id String id;

  @NotBlank(message = "Name of tag is mandatory.")
  private String name;

  @NotBlank(message = "Username of owner is mandatory.")
  private String authorUsername;

  @NotBlank(message = "Playlist's id is mandatory.")
  private String playlistId;

  private ArrayList<String> likes = new ArrayList<>();      // Contain users's username who like this tag

  private ArrayList<String> dislikes = new ArrayList<>();   // COntain users's username who dislike this tag

  private float popular = 0f;

  // Default constructor
  public Tag() {}

  // Custom constructor
  public Tag(
    String name,
    String authorUsername,
    String playlistId
  ) {
    this.name = name;
    this.authorUsername = authorUsername;
    this.playlistId = playlistId;
  }

  // All get methods
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAuthorUsername() {
    return authorUsername;
  }

  public String getPlaylistId() {
    return playlistId;
  }

  public ArrayList<String> getLikes() {
    return likes;
  }

  public void setLikes(ArrayList<String> likes) {
    this.likes = likes;
  }

  public ArrayList<String> getDislikes() {
    return dislikes;
  }

  public void setDislikes(ArrayList<String> dislikes) {
    this.dislikes = dislikes;
  }

  public float getPopular() {
    return popular;
  }

  public void setPopular(float popular) {
    this.popular = popular;
  }
}
