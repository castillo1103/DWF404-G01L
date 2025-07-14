package sv.edu.udb.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.repository.domain.Movie;
import sv.edu.udb.service.implementation.MovieServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Por cuestiones didacticas utilizamos la anotacion
 * de @SpringBootTest para poder levantar un contexto de spring
 * y poder hacer las pruebas de integracion sobre inyeccion de
 * dependencias. EN LA PRACTICA SE HACE TESTING POR CAPAS
 */


@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieServiceImpl movieService;

    @Test
    void shouldMovieServiceNotNull_When_SpringContextWorks() {
        assertNotNull(movieService);
    }

    @Test
    void shouldMovieRepositoryNotNul_When_DIWorks() {
        assertNotNull(movieService.getMovieRepository());
    }

    @Test
    void shouldGetAMovie_When_TheMovieIdExists() {
        final Long expectedMovieId = (Long) 1L;
        final String expectedMovieName = "Inception";
        final Integer expectedReleaseYear = (Integer) 2010;
        final Movie actualMovie = movieService.findMovieById(expectedMovieId);
        assertEquals(actualMovie.getId(), expectedMovieId);
        assertEquals(actualMovie.getName(), expectedMovieName);
        assertEquals(actualMovie.getReleaseYear(), expectedReleaseYear);
    }

    @Test
    void shouldThrowNoSuchElementException_When_MovieIdDoesNotExists() {
        final Long fakeId = (Long) 4L;
        final String expectedErrorMessage = "Movie doesn't exists";
        final Exception exception = assertThrows(NoSuchElementException.class,
                () -> movieService.findMovieById(fakeId));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
    @Test
    void shouldMovieNameNotBeEmpty_When_MovieExists() {
        Movie movie = movieService.findMovieById(Long.valueOf(1L));
        assertFalse(movie.getName().isEmpty(), "El nombre de la película no debería estar vacío");
    }

    @Test
    void shouldReturnCorrectNumberOfMovies_When_ListAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        assertTrue(movies.size() >= 1, "Debería haber al menos una película en la base de datos");
    }

    @Test
    void shouldValidateAllMovieFields_When_MovieIsFetched() {
        Movie movie = movieService.findMovieById(Long.valueOf(1L));
        assertAll("movie",
                () -> assertEquals("Inception", movie.getName()),
                () -> assertEquals(2010, movie.getReleaseYear()),
                () -> assertNotNull(movie.getType(), "El género no debe ser null")
        );
    }
}
