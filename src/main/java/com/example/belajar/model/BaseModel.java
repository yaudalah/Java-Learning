package com.example.belajar.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    private Date createDateTime;
    private Date updateDateTime;
}
