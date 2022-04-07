package http.creationaltask1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.Objects;

public class Person {
    
    private final String firstName; 
    private final String lastName;
    private int age;
    private String address;
    private final ObjectMapper mapper;

    public Person(
            @JsonProperty("firstName") String firstName, 
            @JsonProperty("lastName") String lastName, 
            @JsonProperty("age") int age, 
            @JsonProperty("address") String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
    }
    
    public void happyBirthday() {
        if (hasAge()) age++;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return hasAge() ? age : -1;
    }
    
    public boolean hasAge() {
        return age >= 0;
    }

    public String getAddress() {
        return address;
    }

    public boolean hasAddress() {
        return address != null;
    }
    
    @Override
    public String toString() {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.firstName);
        hash = 61 * hash + Objects.hashCode(this.lastName);
        hash = 61 * hash + this.age;
        hash = 61 * hash + Objects.hashCode(this.address);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return Objects.equals(this.address, other.address);
    }
    
    @JsonIgnore
    public PersonBuilder getChildBuilder() {
        return new PersonBuilder()
                .setAge(0)
                .setLastName(lastName)
                .setAddress(address);
    }
}
