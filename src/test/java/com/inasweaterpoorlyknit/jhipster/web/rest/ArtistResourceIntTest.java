package com.inasweaterpoorlyknit.jhipster.web.rest;

import com.inasweaterpoorlyknit.jhipster.Cs499Assignment2App;

import com.inasweaterpoorlyknit.jhipster.domain.Artist;
import com.inasweaterpoorlyknit.jhipster.repository.ArtistRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ArtistResource REST controller.
 *
 * @see ArtistResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cs499Assignment2App.class)
public class ArtistResourceIntTest {

    private static final String DEFAULT_ARIST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ARIST_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_FOUNDED = 1;
    private static final Integer UPDATED_FOUNDED = 2;

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restArtistMockMvc;

    private Artist artist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
            ArtistResource artistResource = new ArtistResource(artistRepository);
        this.restArtistMockMvc = MockMvcBuilders.standaloneSetup(artistResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Artist createEntity(EntityManager em) {
        Artist artist = new Artist()
                .aristName(DEFAULT_ARIST_NAME)
                .founded(DEFAULT_FOUNDED)
                .location(DEFAULT_LOCATION);
        return artist;
    }

    @Before
    public void initTest() {
        artist = createEntity(em);
    }

    @Test
    @Transactional
    public void createArtist() throws Exception {
        int databaseSizeBeforeCreate = artistRepository.findAll().size();

        // Create the Artist

        restArtistMockMvc.perform(post("/api/artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(artist)))
            .andExpect(status().isCreated());

        // Validate the Artist in the database
        List<Artist> artistList = artistRepository.findAll();
        assertThat(artistList).hasSize(databaseSizeBeforeCreate + 1);
        Artist testArtist = artistList.get(artistList.size() - 1);
        assertThat(testArtist.getAristName()).isEqualTo(DEFAULT_ARIST_NAME);
        assertThat(testArtist.getFounded()).isEqualTo(DEFAULT_FOUNDED);
        assertThat(testArtist.getLocation()).isEqualTo(DEFAULT_LOCATION);
    }

    @Test
    @Transactional
    public void createArtistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = artistRepository.findAll().size();

        // Create the Artist with an existing ID
        Artist existingArtist = new Artist();
        existingArtist.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArtistMockMvc.perform(post("/api/artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingArtist)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Artist> artistList = artistRepository.findAll();
        assertThat(artistList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllArtists() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList
        restArtistMockMvc.perform(get("/api/artists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(artist.getId().intValue())))
            .andExpect(jsonPath("$.[*].aristName").value(hasItem(DEFAULT_ARIST_NAME.toString())))
            .andExpect(jsonPath("$.[*].founded").value(hasItem(DEFAULT_FOUNDED)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())));
    }

    @Test
    @Transactional
    public void getArtist() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get the artist
        restArtistMockMvc.perform(get("/api/artists/{id}", artist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(artist.getId().intValue()))
            .andExpect(jsonPath("$.aristName").value(DEFAULT_ARIST_NAME.toString()))
            .andExpect(jsonPath("$.founded").value(DEFAULT_FOUNDED))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingArtist() throws Exception {
        // Get the artist
        restArtistMockMvc.perform(get("/api/artists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArtist() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);
        int databaseSizeBeforeUpdate = artistRepository.findAll().size();

        // Update the artist
        Artist updatedArtist = artistRepository.findOne(artist.getId());
        updatedArtist
                .aristName(UPDATED_ARIST_NAME)
                .founded(UPDATED_FOUNDED)
                .location(UPDATED_LOCATION);

        restArtistMockMvc.perform(put("/api/artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedArtist)))
            .andExpect(status().isOk());

        // Validate the Artist in the database
        List<Artist> artistList = artistRepository.findAll();
        assertThat(artistList).hasSize(databaseSizeBeforeUpdate);
        Artist testArtist = artistList.get(artistList.size() - 1);
        assertThat(testArtist.getAristName()).isEqualTo(UPDATED_ARIST_NAME);
        assertThat(testArtist.getFounded()).isEqualTo(UPDATED_FOUNDED);
        assertThat(testArtist.getLocation()).isEqualTo(UPDATED_LOCATION);
    }

    @Test
    @Transactional
    public void updateNonExistingArtist() throws Exception {
        int databaseSizeBeforeUpdate = artistRepository.findAll().size();

        // Create the Artist

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restArtistMockMvc.perform(put("/api/artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(artist)))
            .andExpect(status().isCreated());

        // Validate the Artist in the database
        List<Artist> artistList = artistRepository.findAll();
        assertThat(artistList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteArtist() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);
        int databaseSizeBeforeDelete = artistRepository.findAll().size();

        // Get the artist
        restArtistMockMvc.perform(delete("/api/artists/{id}", artist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Artist> artistList = artistRepository.findAll();
        assertThat(artistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Artist.class);
    }
}
