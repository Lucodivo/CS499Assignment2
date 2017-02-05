package com.inasweaterpoorlyknit.jhipster.repository;

import com.inasweaterpoorlyknit.jhipster.domain.Artist;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Artist entity.
 */
@SuppressWarnings("unused")
public interface ArtistRepository extends JpaRepository<Artist,Long> {

}
