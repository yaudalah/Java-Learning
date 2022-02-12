package com.example.belajar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pelanggan")
public class Pelanggan {
    @Id
    private String id;

    @Column(name = "name", length = 25)
    private String name;

    @Column(unique = true, nullable = false)
    private  String email;

    @Column(name = "phoneNumber", unique = true, length = 13, nullable = false)
    private Long phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date joinDate;

    @Column(name = "password", nullable = false)
    private String password;

    public String getId() {
        if (id == null || id.equals("")) {
            id = UUID.randomUUID().toString();
        }
        return id;
    }
}
