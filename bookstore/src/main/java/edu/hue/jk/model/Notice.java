package edu.hue.jk.model;

import lombok.Data;

@Data
public class Notice {
    private Integer nId;
    private String title;
    private String details;
    private String nTime;
}
