package nambui9812.playlistrank.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Playlist;
import nambui9812.playlistrank.services.impl.PlaylistServiceImpl;
import nambui9812.playlistrank.validations.UpdateDescriptionPlaylistValidation;
import nambui9812.playlistrank.validations.SharePlaylistValidation;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
  @Autowired
  private PlaylistServiceImpl playlistServiceImpl;

  // Get all playlists
  @GetMapping("/")
  ResponseEntity<Object> getAllPlaylists() {
    List<Playlist> playlists = playlistServiceImpl.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(playlists);
  }

  // Get a playlist by id
  @GetMapping("/{id}")
  ResponseEntity<Object> getPlaylist(@PathVariable String id) {
    Playlist playlist = playlistServiceImpl.findById(id);

    return ResponseEntity.status(HttpStatus.OK).body(playlist);
  }

  // Create a new playlist
  @PostMapping("/")
  ResponseEntity<Object> createPlaylist(@RequestBody Playlist newPlaylist, Authentication authentication) throws Exception {
    HashMap<String, Object> res = new HashMap<>();

    try {

      if (!newPlaylist.getAuthorUsername().equals(authentication.getName())) {
        res.put("error", true);
        res.put("message", "Cannot create playlist for other person.");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
      }

      newPlaylist = playlistServiceImpl.createPlaylist(newPlaylist);

    } catch (ConstraintViolationException e) {

      HashMap<String, Object> messages = new HashMap<>();
      e.getConstraintViolations().stream().forEach((violation) -> {
        messages.put(violation.getPropertyPath().toString(), violation.getMessage());
      });
      res.put("error", true);
      res.put("messages", messages);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    res.put("message", "Create a new playlist successfully");
    res.put("playlist", newPlaylist);

    return ResponseEntity.status(HttpStatus.CREATED).body(res);
  }

  // Share a playlist
  @PostMapping("/share-playlist")
  ResponseEntity<Object> sharePlaylist(@RequestBody SharePlaylistValidation info, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Playlist existing = playlistServiceImpl.findById(info.getId());

    if (existing == null) {
      res.put("message", "Cannot share an invalid playlist.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    if (existing.getAuthorUsername().equals(authentication.getName())) {
      res.put("message", "Cannot share your own playlist.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    Playlist shared = playlistServiceImpl.sharePlaylist(authentication.getName(), info);

    res.put("message", "Share playlist successfully.");
    res.put("playlist", shared);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Update playlist's description
  @PutMapping("/update-description")
  ResponseEntity<Object> updatePlaylist(@RequestBody UpdateDescriptionPlaylistValidation info, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Playlist existing = playlistServiceImpl.findById(info.getId());

    if (existing == null) {
      res.put("message", "Cannot update an invalid playlist.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    if (!existing.getAuthorUsername().equals(authentication.getName())) {
      res.put("message", "Cannot update a playlist that not belong to you.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    Playlist updated = playlistServiceImpl.updateDescription(existing, info);

    res.put("message", "Update description successfully.");
    res.put("playlist", updated);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Love a playlist
  @PutMapping("/love/{id}")
  ResponseEntity<Object> lovePlaylist(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Playlist existing = playlistServiceImpl.findById(id);

    if (existing == null) {
      res.put("message", "Cannot love an invalid playlist.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    Playlist loved = playlistServiceImpl.lovePlaylist(authentication.getName(), existing);

    res.put("message", "Love a playlist successfully.");
    res.put("playlist", loved);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Delete a playlist
  @DeleteMapping("/{id}")
  ResponseEntity<Object> deletePlaylist(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Playlist existing = playlistServiceImpl.findById(id);

    if (existing == null) {
      res.put("message", "Cannot delete an invalid playlist.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username of owner
    String fromExisting = existing.getAuthorUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {

      res.put("message", "Unauthorization for deleting playlist.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
    
    playlistServiceImpl.deletePlaylist(id);

    res.put("message", "Delete playlist successfully.");

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}