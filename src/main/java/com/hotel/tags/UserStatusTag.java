package com.hotel.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class UserStatusTag extends TagSupport {
    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    private String handleTheParameter(int status) {
        if (status == 0) {
            return "В обработке";
        } else if (status == 1) {
            return "Обработан";
        } else if (status == 2) {
            return "Выполнен";
        } else {
            return "Отменен";
        }
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().println(handleTheParameter(status));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
