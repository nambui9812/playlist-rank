package nambui9812.playlistrank.entities;

import java.util.ArrayList;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tags")
public class Tag {
  private @Id String id;
  private String name;
  private ArrayList<String> likes;
  private ArrayList<String> dislikes;

  // Default constructor
  public Tag() {}

  // Custom constructor
  public Tag(
    String name,
    ArrayList<String> likes,
    ArrayList<String> dislikes
  ) {
    this.name = name;
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
