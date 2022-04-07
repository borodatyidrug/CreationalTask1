package http.creationaltask1;

public class PersonBuilder {
    private String firstName; 
    private String lastName;
    private int age = -1;
    private String address;

    public PersonBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Человек не может иметь возраст до своего рождения");
        } else {
            this.age = age;
        }
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }
    
    public Person build() {
        if (firstName == null || firstName.isBlank()
                || lastName == null || lastName.isBlank()) 
            throw new IllegalStateException("Человек не может быть без имени и фамилии");
        return new Person(firstName, lastName, age, address);
    }
}
