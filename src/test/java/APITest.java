import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class APITest {

    Movie movieLocal;

    @Before
    public void setup(){
        movieLocal = new Movie("Guardians of the Galaxy Vol. 2", 2017, "James Gunn", 7.6);
    }

    @Test
    public void apiTest(){
        try {
            JSONObject json = API.readJsonFromUrl("http://www.omdbapi.com/?i=tt3896198&apikey=acced38d");
            Movie movieRemote = new Movie(json.getString("Title"), json.getInt("Year"), json.getString("Director"),
                    json.getDouble("imdbRating"));
            assertMovieEquals(movieLocal,movieRemote);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void assertMovieEquals(Movie movieLocal, Movie movieRemote){
        Assert.assertEquals(movieLocal.getTitle(), movieRemote.getTitle());
        Assert.assertEquals(movieLocal.getYear(), movieRemote.getYear());
        Assert.assertEquals(movieLocal.getDirector(), movieRemote.getDirector());
        Assert.assertEquals(movieLocal.getImdbRating(), movieRemote.getImdbRating(), 0);

    }
}
