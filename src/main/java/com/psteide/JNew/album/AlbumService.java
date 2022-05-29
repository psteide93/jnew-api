package com.psteide.JNew.album;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
  @Autowired
  private AlbumRepository albumRepository;

  public Iterable<Album> list(){
    return albumRepository.findAll();
  }

  public Optional<Album> findById(Long id){
    return albumRepository.findById(id);
  }

  public Album create(Album album) {
    return albumRepository.save(album);
  }

  public Optional<Album> update(Album album) {
    Optional<Album> foundAlbum = albumRepository.findById(album.getId());

    if (foundAlbum.isPresent()) {
        Album updatedAlbum = foundAlbum.get();
        updatedAlbum.setTitle(album.getTitle());
        updatedAlbum.setYear(album.getYear());
        updatedAlbum.setNumberOfSongs(album.getNumberOfSongs());
        updatedAlbum.setTracklist(album.getTracklist());

        albumRepository.save(updatedAlbum);
        return Optional.of(updatedAlbum);
      } else {
        return Optional.empty();
      }
  }

  public void deleteById(Long id) {
    albumRepository.deleteById(id);
  }
}