package com.psteide.JNew.album;

import java.util.Map;
import java.util.HashMap;

import com.psteide.JNew.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("api/albums")
public class AlbumController {
  @Autowired
  private AlbumService albumService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Iterable<Album>> list(){
    Iterable<Album> albums = albumService.list();
    return createHashPlural(albums);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Album> read(@PathVariable Long id) {
    Album album = albumService
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("No album with that ID"));
    return createHashSingular(album);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Album> create(@Validated @RequestBody Album album) {
    Album createdAlbum = albumService.create(album);
    return createHashSingular(createdAlbum);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Album> update(@RequestBody Album album, @PathVariable Long id) {
    Album updatedAlbum = albumService
      .update(album)
      .orElseThrow(() -> new ResourceNotFoundException("No resource with that ID"));

    return createHashSingular(updatedAlbum);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    albumService.deleteById(id);
  }

  private Map<String, Album> createHashSingular(Album album){
    Map<String, Album> response = new HashMap<String, Album>();
    response.put("album", album);

    return response;
  }

  private Map<String, Iterable<Album>> createHashPlural(Iterable<Album> albums){
    Map<String, Iterable<Album>> response = new HashMap<String, Iterable<Album>>();
    response.put("albums", albums);

    return response;
  }
}