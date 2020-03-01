package nambui9812.playlistrank.entities;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tags")
public class Tag {
  private @Id String id;

  @NotNull(message = "Name of tag is mandatory.")
  private String name;

  @NotNull(message = "Playlist's id is mandatory.")
  private String playlistId;

  private ArrayList<String> likes;

  private ArrayList<String> dislikes;

  // Default constructor
  public Tag() {}

  // Custom constructor
  public Tag(
    String name,
    String playlistId,
    ArrayList<String> likes,
    ArrayList<String> dislikes
  ) {
    this.name = name;
    this.playlistId = playlistId;
    this.likes = likes;
    this.dislikes = dislikes;
  }

  // All get methods
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
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
}
