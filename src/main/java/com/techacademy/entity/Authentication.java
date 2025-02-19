package com.techacademy.entity;

import java.sql.Date;
import lombok.Data;
import lombok.ToString;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {
    /** ログインユーザ名 */
    @Id
    private String loginUser;

    /** パスワード */
    private String password;

    /** 有効日付 */
    private Date validDate;

    /** ユーザID */
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    @ToString.Exclude  //ループ回避に追加
    private User user;
}