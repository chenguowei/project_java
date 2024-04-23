package org.example.springbootjpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_user")
public class TUser {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 使用数据库自增
    private long id;

    @Column
    private String username;

    @Column
    private String age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
