package http.creationaltask1;

public class CreationalTask1 {

    public static void main(String[] args) {
        Person parent = new PersonBuilder()
                .setFirstName("Виолетта")
                .setLastName("Пролежнева")
                .setAge(98)
                .setAddress("c. Хряково, ул. Борова-Кабана, д. 13")
                .build();
        Person child = parent.getChildBuilder()
                .setFirstName("Валентина")
                .build();
        System.out.println("Некая гражданка, чье представление в формате JSON мы имеем честь лицезреть ниже:\n"
                + parent + ",\n разродилась дочерью на склоне лет, чье представление в формате JSON мы так же не применем увидеть ниже:\n"
                + child + "\nБольше нечего добавить. Иное - в автотестах, куда - милости просим. :)");
    }
}