package com.oracledigitalassistant;

import java.util.ArrayList;
import java.util.Date;

public class MessageDTO {
    public MessageDTO(String headerText, String footerText, Date createdDate, Boolean isRead, ArrayList<String> actions) {
        this.headerText = headerText;
        this.footerText = footerText;
        this.createdDate = createdDate;
        this.isRead = isRead;
        this.actions = actions;
    }

    String headerText;
    String footerText;
    Date createdDate;
    Boolean isRead;
    ArrayList<String> actions;
}
