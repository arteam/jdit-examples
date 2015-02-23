package jdbi.testing.dao;

import com.github.arteam.jdit.DBIRunner;
import com.github.arteam.jdit.annotations.DataSet;
import com.github.arteam.jdit.annotations.TestedSqlObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Date: 2/23/15
 * Time: 10:33 PM
 *
 * @author Artem Prigoda
 */
@RunWith(DBIRunner.class)
public class MovieDaoTest {

    @TestedSqlObject
    MovieDao movieDao;

    @Test
    @DataSet("movieDao/insert-movies.sql")
    public void testGetMovies() throws Exception {
        List<Movie> movies = movieDao.getMovies(2014);
        for (Movie movie : movies) {
            System.out.println(movie);
            Assert.assertEquals(movie.year, 2014);
        }
        Assert.assertEquals(movies.size(), 3);
    }
}
