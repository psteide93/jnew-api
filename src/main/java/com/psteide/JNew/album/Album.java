package com.psteide.JNew.album;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import lombok.Data;

@Entity
@Data
@Table(name = "album")
public class Album {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "year")
  private String year;

  @Column(name = "numberOfSongs")
  private String numberOfSongs;

  @Column(name = "tracklist")
  private String[] tracklist;
}