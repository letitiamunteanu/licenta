package com.example.CreateAccount.Registration.Token;

import com.example.CreateAccount.AppUser.Model.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime localDateTime;

    @Column(nullable = false)
    private LocalDateTime expiresAtTime;

    @Column(nullable = true)
    private LocalDateTime confirmedEmailAt;

    @ManyToOne()
    @JoinColumn(
            nullable = false,
            name = "username"
    )
    private Users user;

    public ConfirmationToken(String token,
                             LocalDateTime localDateTime,
                             LocalDateTime expiresAtTime,
                             Users user){
        this.token = token;
        this.localDateTime = localDateTime;
        this.expiresAtTime = expiresAtTime;
        this.user = user;
    }

}
