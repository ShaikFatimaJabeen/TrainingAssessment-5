import java.util.*;
import java.util.stream.*;


class Movie {
    private String title;
    private String genre;
    private double rating;
    private int year;

    public Movie(String title, String genre, double rating, int year) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Movie{title='" + title + "', genre='" + genre + "', rating=" + rating + ", year=" + year + "}";
    }
}

public class MovieStreamingRecommendationSystem {
    public static void main(String[] args) {
        
        List<Movie> movies = Arrays.asList(
            new Movie("Inception", "Sci-Fi", 8.8, 2010),
            new Movie("The Dark Knight", "Action", 9.0, 2008),
            new Movie("The Matrix", "Sci-Fi", 8.7, 1999),
            new Movie("The Godfather", "Crime", 9.2, 1972),
            new Movie("Titanic", "Romance", 7.8, 1997),
            new Movie("The Shawshank Redemption", "Drama", 9.3, 1994),
            new Movie("Avengers: Endgame", "Action", 8.4, 2019),
            new Movie("Interstellar", "Sci-Fi", 8.6, 2014)
        );

       
        String preferredGenre = "Sci-Fi";
        System.out.println("Recommended Movies in the genre: " + preferredGenre);
        movies.stream()
            .filter(movie -> movie.getGenre().equalsIgnoreCase(preferredGenre))
            .forEach(System.out::println);
        System.out.println("----------------------------");

        System.out.println("Top-rated Movies (Rating >= 8.5):");
        movies.stream()
            .filter(movie -> movie.getRating() >= 8.5)
            .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
            .forEach(System.out::println);
        System.out.println("----------------------------");

        
        System.out.println("Movies released after the year 2000, sorted by Year:");
        movies.stream()
            .filter(movie -> movie.getYear() > 2000)
            .sorted(Comparator.comparingInt(Movie::getYear))
            .forEach(System.out::println);
        System.out.println("----------------------------");
        double minRating = 8.5;
        String genreFilter = "Action";
        System.out.println("Movies in the genre: " + genreFilter + " with rating >= " + minRating);
        movies.stream()
            .filter(movie -> movie.getGenre().equalsIgnoreCase(genreFilter))
            .filter(movie -> movie.getRating() >= minRating)
            .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
            .forEach(movie -> System.out.println(movie.getTitle() + " - " + movie.getRating()));
        System.out.println("----------------------------");

       
        System.out.println("Movies grouped by genre:");
        Map<String, List<Movie>> groupedByGenre = movies.stream()
            .collect(Collectors.groupingBy(Movie::getGenre));
        
        groupedByGenre.forEach((genre, genreMovies) -> {
            System.out.println(genre + ":");
            genreMovies.forEach(System.out::println);
            System.out.println("----------------------------");
        });
    }
}
