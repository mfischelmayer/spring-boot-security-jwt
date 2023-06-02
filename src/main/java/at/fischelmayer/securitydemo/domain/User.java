package at.fischelmayer.securitydemo.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table( name = "users" )
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn( name = "role_name" )
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole( Role role ) {
        this.role = role;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        User user = (User) o;
        return Objects.equals( id, user.id );
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }
}
