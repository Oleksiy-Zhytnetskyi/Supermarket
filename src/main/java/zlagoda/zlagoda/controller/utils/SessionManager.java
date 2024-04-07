package zlagoda.zlagoda.controller.utils;

import jakarta.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.entity.UserEntity;

public class SessionManager {

    private static final Logger LOGGER = LogManager.getLogger(SessionManager.class);

    static final String USER_HAS_LOGGED_IN = "User has logged in: ";
    static final String USER_HAS_LOGGED_OUT = "User has logged out: ";

    SessionManager() {
    }

    private static final class Holder {
        static final SessionManager INSTANCE = new SessionManager();
    }

    public static SessionManager getInstance() {
        return Holder.INSTANCE;
    }

    public boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute(Attribute.USER) != null;
    }

    public void addUserToSession(HttpSession session, UserEntity user) {
        LOGGER.info(String.format(USER_HAS_LOGGED_IN, user.getId()));
        session.setAttribute(Attribute.USER, user);
    }

    public UserEntity getUserFromSession(HttpSession session) {
        return (UserEntity) session.getAttribute(Attribute.USER);
    }

    public void invalidateSession(HttpSession session) {
        if (session != null && session.getAttribute(Attribute.USER) != null) {
            executeSessionInvalidation(session);
        }
    }

    private void executeSessionInvalidation(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute(Attribute.USER);
        LOGGER.info(String.format(USER_HAS_LOGGED_OUT, user.getId()));
        session.invalidate();
    }
}
