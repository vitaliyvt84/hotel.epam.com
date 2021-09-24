package com.hotel.tags;

import com.hotel.entity.PreOrderStatus;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ManagerStatusTag extends TagSupport {
    private PreOrderStatus status;

    public void setStatus(PreOrderStatus status) {
        this.status = status;
    }
    private String chooseOption(int param) {
        if (param == 0) {
            return "Новый";
        } else if (param == 1) {
            return "Обработан";
        } else if (param == 2) {
            return "Выполнен";
        } else {
            return "Отменен";
        }
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().println(chooseOption(status.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
