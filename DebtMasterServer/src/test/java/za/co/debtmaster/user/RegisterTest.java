package za.co.debtmaster.user;

import org.junit.jupiter.api.Test;
import za.co.debtmaster.app.db.DataBaseHandler;

import java.sql.SQLException;

public class RegisterTest extends AbstractUserTest{

    @Test
    void testRegisterCorrect() throws SQLException {

        // We register the user
        doRegisterCorrect("mbuso", "mbuso@test.com");

        // We delete the user from the database
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        dataBaseHandler.deleteAll();
        dataBaseHandler.disconnect();
    }

    @Test
    void testRegisterIncorrectName() {
        doRegisterIncorrect("", "mbuso@test.com");
    }
}
