package com.example.pessoa.api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_group_user")
public class GroupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_group")
    private Group group;

    public GroupUser(User user, Group group) {
        this.user = user;
        this.group = group;
    }
}
