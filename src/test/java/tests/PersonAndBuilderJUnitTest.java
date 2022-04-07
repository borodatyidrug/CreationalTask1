package tests;

import http.creationaltask1.Person;
import http.creationaltask1.PersonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonAndBuilderJUnitTest {
    
    PersonBuilder personBuilder;
    String blank, firstName, lastName, childName, address, expectedParent, expectedChild;
    int rightAge, wrongAge;
    
    @BeforeEach
    public void init() {
        personBuilder = new PersonBuilder();
        blank = " ";
        firstName = "Виолетта";
        lastName = "Пролежнева";
        childName = "Валентина";
        rightAge = 98;
        wrongAge = -2;
        expectedParent = "{\"Person\":{\"firstName\":\"Виолетта\",\"lastName\":\"Пролежнева\",\"age\":98,\"address\":\"c. Хряково, ул. Борова-Кабана, д. 13\"}}";
        expectedChild = "{\"Person\":{\"firstName\":\"Валентина\",\"lastName\":\"Пролежнева\",\"age\":0,\"address\":\"c. Хряково, ул. Борова-Кабана, д. 13\"}}";
        address = "c. Хряково, ул. Борова-Кабана, д. 13";
    }
    
    // если недостаточно данных для построения объекта
    @Test
    public void personBuilder_build_testIfinsufficientData() {
        assertThrows(IllegalStateException.class, personBuilder::build);
    }
    
    // если имя и фамилия - пусты
    @Test
    public void personBuilder_build_testIfNamesAreBlank() {
        personBuilder
                .setFirstName(blank)
                .setLastName(blank);
        assertThrows(IllegalStateException.class, personBuilder::build);
    }
    
    // если введен некорректный возраст
    @Test
    public void personBuilder_build_testIfWrongAge() {
        personBuilder
                .setFirstName(firstName)
                .setLastName(lastName);
        assertThrows(IllegalArgumentException.class, () -> personBuilder.setAge(wrongAge));
    }
    
    // если все указано верно
    @Test
    public void personBuilder_build_testIfAllIsValid() {
        Person parent = personBuilder
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAge(rightAge)
                .setAddress(address)
                .build();
        Person child = parent
                .getChildBuilder()
                .setFirstName(childName)
                .build();
        assertEquals(expectedParent, parent.toString());
        assertEquals(expectedChild, child.toString());
    }
    
    // проверка инкрементации возраста при вызове соотв. метода
    @Test
    public void Person_happyBirthday_test() {
        // given
        Person parent = personBuilder
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAge(rightAge)
                .setAddress(address)
                .build();
        int expectedAge = parent.getAge() + 1;
        // when
        parent.happyBirthday();
        int actualAge = parent.getAge();
        // then
        assertEquals(expectedAge, actualAge);
    }
    
}
