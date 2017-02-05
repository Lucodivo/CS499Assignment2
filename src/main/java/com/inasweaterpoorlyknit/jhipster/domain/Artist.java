package com.inasweaterpoorlyknit.jhipster.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Artist.
 */
@Entity
@Table(name = "artist")
public class Artist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "arist_name")
    private String aristName;

    @Column(name = "founded")
    private Integer founded;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private Set<Album> albums = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAristName() {
        return aristName;
    }

    public Artist aristName(String aristName) {
        this.aristName = aristName;
        return this;
    }

    public void setAristName(String aristName) {
        this.aristName = aristName;
    }

    public Integer getFounded() {
        return founded;
    }

    public Artist founded(Integer founded) {
        this.founded = founded;
        return this;
    }

    public void setFounded(Integer founded) {
        this.founded = founded;
    }

    public String getLocation() {
        return location;
    }

    public Artist location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public Artist albums(Set<Album> albums) {
        this.albums = albums;
        return this;
    }

    public Artist addAlbums(Album album) {
        this.albums.add(album);
        album.setArtist(this);
        return this;
    }

    public Artist removeAlbums(Album album) {
        this.albums.remove(album);
        album.setArtist(null);
        return this;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artist artist = (Artist) o;
        if (artist.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, artist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Artist{" +
            "id=" + id +
            ", aristName='" + aristName + "'" +
            ", founded='" + founded + "'" +
            ", location='" + location + "'" +
            '}';
    }
}
