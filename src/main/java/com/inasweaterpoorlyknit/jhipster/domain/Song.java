package com.inasweaterpoorlyknit.jhipster.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Song.
 */
@Entity
@Table(name = "song")
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "song_title")
    private String songTitle;

    @ManyToOne
    private Album album;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public Song songTitle(String songTitle) {
        this.songTitle = songTitle;
        return this;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public Album getAlbum() {
        return album;
    }

    public Song album(Album album) {
        this.album = album;
        return this;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Song song = (Song) o;
        if (song.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, song.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Song{" +
            "id=" + id +
            ", songTitle='" + songTitle + "'" +
            '}';
    }
}
