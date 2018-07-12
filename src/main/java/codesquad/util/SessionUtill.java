package codesquad.util;

import codesquad.domain.User;
import codesquad.exception.UserNotLoginException;

import javax.servlet.http.HttpSession;


public class SessionUtill {
    public final static String SESSION_ID = "sessionedUser";

    public static User getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute(SESSION_ID);
        if(user == null)
            throw new UserNotLoginException();
        return  user;
    }

    public static void setSessionUser(HttpSession session, User user){
        session.setAttribute(SESSION_ID,user);
    }

    public static void removeSessionUser(HttpSession session){
        session.removeAttribute(SESSION_ID);
    }
}
