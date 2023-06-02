package at.fischelmayer.securitydemo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table( name = "roles" )
public class Role {

    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Role role = (Role) o;
        return Objects.equals( name, role.name );
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }
}
