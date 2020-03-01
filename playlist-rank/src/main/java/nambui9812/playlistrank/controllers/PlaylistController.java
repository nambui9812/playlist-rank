package nambui9812.playlistrank.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Playlist;
import nambui9812.playlistrank.services.impl.PlaylistServiceImpl;

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
  ResponseEntity<Object> createPlaylist(@RequestBody Playlist newPlaylist) throws Exception {
    HashMap<String, Object> res = new HashMap<>();

    try {

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

  // Update a playlist
  @PutMapping("/{id}")
  ResponseEntity<Object> updatePlaylist(@RequestBody Playlist newPlaylist) {
    Playlist existing = playlistServiceImpl.findById(newPlaylist.getId());

    Playlist updated = playlistServiceImpl.updatePlaylist(existing, newPlaylist);

    return ResponseEntity.status(HttpStatus.OK).body(updated);
  }

  // Delete a playlist
  @DeleteMapping("/{id}")
  ResponseEntity<Object> deletePlaylist(@PathVariable String id) {
    HashMap<String, Object> res = new HashMap<>();

    if (id == null) {
      res.put("message", "Cannot delete playlist.");
    }
    else {
      playlistServiceImpl.deletePlaylist(id);

      res.put("message", "Delete playlist successfully.");
    }

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}