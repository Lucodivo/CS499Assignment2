package com.inasweaterpoorlyknit.jhipster.repository;

import com.inasweaterpoorlyknit.jhipster.domain.Song;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Song entity.
 */
@SuppressWarnings("unused")
public interface SongRepository extends JpaRepository<Song,Long> {

}
