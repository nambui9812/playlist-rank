package nambui9812.playlistrank.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Tag;
import nambui9812.playlistrank.repositories.TagRepository;
import nambui9812.playlistrank.exceptions.TagNotFoundException;

@RestController
@RequestMapping("/tags")
public class TagController {
  private final TagRepository tagsRepository;

  // Constructor
  public TagController(TagRepository tagsRepository) {
    this.tagsRepository = tagsRepository;
  }

  // Get all tags
  @GetMapping("/")
  ResponseEntity<List<Tag>> getAllTags() {
    List<Tag> tags = tagsRepository.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(tags);
  }

  // Get a tag by id
  @GetMapping("/{id}")
  ResponseEntity<Tag> getTag(@PathVariable String id) {
    Tag tag = tagsRepository.findById(id).orElseThrow(() -> new TagNotFoundException());

    return ResponseEntity.status(HttpStatus.OK).body(tag);
  }

  // Create a new tag
  @PostMapping("/")
  ResponseEntity<Tag> createTag(@RequestBody Tag newTag) {
    tagsRepository.save(newTag);

    return ResponseEntity.status(HttpStatus.CREATED).body(newTag);
  }

  // Update a tag
  @PutMapping("/{id}")
  ResponseEntity<Tag> updateTag(@RequestBody Tag newTag) {
    Tag existing = tagsRepository.findById(newTag.getId()).orElseThrow(() -> new TagNotFoundException());

    existing.setLikes(newTag.getLikes());
    existing.setDislikes(newTag.getDislikes());

    tagsRepository.save(existing);

    return ResponseEntity.status(HttpStatus.OK).body(existing);
  }

  // Delete a tag
  @DeleteMapping("/{id}")
  void deleteTag(@PathVariable String id) {
    tagsRepository.deleteById(id);
  }
}