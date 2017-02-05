package com.inasweaterpoorlyknit.jhipster.repository;

import com.inasweaterpoorlyknit.jhipster.domain.Album;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Album entity.
 */
@SuppressWarnings("unused")
public interface AlbumRepository extends JpaRepository<Album,Long> {

}
