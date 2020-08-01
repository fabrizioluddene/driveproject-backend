package it.diriveprojectbe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="   DP_USERS")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="US_ID", nullable = false)
    private  Long id;
    @Column(name="US_USERNAME", nullable = false,length = 100,unique = true)
    private String username;
    @Column(name="US_PASSWORD", nullable = false,length = 30)
    private String password;
    @Column(name="US_FIRSTNAME", nullable = false,length = 50)
    private String firstName;
    @Column(name="US_LASTNAME", nullable = false,length = 50)
    private String lastName;


}
