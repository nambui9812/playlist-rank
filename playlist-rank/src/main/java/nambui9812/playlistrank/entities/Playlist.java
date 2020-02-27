package nambui9812.playlistrank.entities;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "playlists")
public class Playlist {
  private @Id String id;

  @NotNull(message = "Author username is mandatory.")
  @NotBlank
  private String authorUsername;      // Name of person who creates this playlist
  
  private String description;         
  
  private ArrayList<String> loves;    // List of users's username who loves this playlist
  
  private ArrayList<String> comments; // List of comments's id
  
  private ArrayList<String> shares;   // List of users's username who shares this playlist
  
  private ArrayList<String> tags;     // List of tags's name
  
  private ArrayList<String> tracks;   // List of name and author of the track

  // Default constructor
  public Playlist() {}

  // Custom constructor
  public Playlist(
    String authorUsername,
    String description,
    ArrayList<String> loves,
    ArrayList<String> comments,
    ArrayList<String> shares,
    ArrayList<String> tags,
    ArrayList<String> tracks
  ) {
    this.authorUsername = authorUsername;
    this.description = description;
    this.loves = loves;
    this.comments = comments;
    this.shares = shares;
    this.tags = tags;
    this.tracks = tracks;
  }

  // All get methods
  public String getId() {
    return id;
  }

  public String getAuthorUsername() {
    return authorUsername;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ArrayList<String> getLoves() {
    return loves;
  }
  
  public void setLoves(ArrayList<String> loves) {
    this.loves = loves;
  }

  public ArrayList<String> getComments() {
    return comments;
  }

  public void setComments(ArrayList<String> comments) {
    this.comments = comments;
  }

  public ArrayList<String> getShares() {
    return shares;
  }

  public void setShares(ArrayList<String> shares) {
    this.shares = shares;
  }

  public ArrayList<String> getTags() {
    return tags;
  }

  public void setTags(ArrayList<String> tags) {
    this.tags = tags;
  }

  public ArrayList<String> getTracks() {
    return tracks;
  }

  public void setTracks(ArrayList<String> tracks) {
    this.tracks = tracks;
  }
}
