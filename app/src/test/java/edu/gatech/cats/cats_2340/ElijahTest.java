package edu.gatech.cats.cats_2340;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cats.cats_2340.model.Model;
import edu.gatech.cats.cats_2340.model.User;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ElijahTest {
    private Model m;

    @Before
    public void setUp() {
        m = Model.getInstance();
    }

    @Test
    public void testCorrectLogin() throws Exception {
        assertEquals(true, m.attemptLogin("Name!", "Password!"));
    }

    @Test
    public void testBadLogin() throws Exception {
        assertEquals(false, m.attemptLogin("ksjdfh", "dfuhjw"));
    }

    @Test
    public void testAlreadyLoggedIn() throws Exception {
        m.setCurrentUser(new User("eli", "pass", true));
        assertEquals(false, m.attemptLogin("Name!", "Password!"));
    }
}