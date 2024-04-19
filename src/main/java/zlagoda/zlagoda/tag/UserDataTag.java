package zlagoda.zlagoda.tag;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.locale.MessageLocale;
import zlagoda.zlagoda.locale.MessageUtils;

public class UserDataTag extends TagSupport {
    private UserEntity user;

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(showUserData());
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }


    private String showUserData() {
        return new StringBuffer().append(MessageLocale.BUNDLE.getString(Message.LOGGED_IN_AS)).append(user.getEmail())
                .append(MessageUtils.LEFT_PARENTHESIS)
                .append(MessageLocale.BUNDLE.getString(user.getRole().toString()))
                .append(MessageUtils.RIGHT_PARANTHESIS).toString();
    }
}