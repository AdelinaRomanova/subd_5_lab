package logics;

import entities.Fabric;
import entities.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class FabricLogic {
    public void work(SessionFactory sessionFactory){
        System.out.println("1.Создать");
        System.out.println("2.Прочитать");
        System.out.println("3.Обновить");
        System.out.println("4.Удалить");
        System.out.println("5.Вернуться в меню");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        Session session;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        switch (i) {
            case 1 -> create(session);
            case 2 -> read(session);
            case 3 -> update(session);
            case 4 -> delete(session);
            case 5 -> {
                session.close();
                return;
            }
            default -> System.out.println("Неверный ввод");
        }
        session.getTransaction().commit();
    }

    private void create(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название фабрики:");
        String fabric_name = scanner.nextLine();
        System.out.print("Введите адрес фабрики:");
        String address = scanner.nextLine();
        System.out.print("Введите ФИО контакта:");
        String contactFio = scanner.nextLine();
        System.out.print("Введите телефон:");
        String phone = scanner.nextLine();
        try {
            Fabric fabric = new Fabric(fabric_name, address, contactFio, phone);
            session.save(fabric);
        }
        catch (Exception ex){
            System.out.println("Ошибка создания записи");
        }
    }

    private void read(Session session){
        System.out.println("1.С фильтром");
        System.out.println("2Без фильтра");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        switch (i){
            case 1-> filter(session);
            case 2-> {
                List<Fabric> fabricList = session.createQuery("SELECT f FROM Fabric f",
                        Fabric.class).getResultList();
                System.out.println("Фабрика");
                System.out.printf("%-30s%-30s%-30s%-30s%-30s\n","ID","Название",
                        "Адрес", "Контакт", "Телефон");
                fabricList.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод");
        }

    }
    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID фабрики:");
        int fabric_id = scanner.nextInt();
        System.out.println("Что вы хотите обновить?");
        System.out.println("1.Название фабрики");
        System.out.println("2.Адрес");
        System.out.println("3.Контакт");
        System.out.println("4.Телефон");
        System.out.print(">");
        int i = scanner.nextInt();
        switch (i) {
            case 1 -> {
                System.out.print("Введите название фабрики:");
                scanner.nextLine();
                String fabric_name = scanner.nextLine();
                try {
                    Fabric fabric = session.get(Fabric.class, fabric_id);
                    fabric.setFabric_name(fabric_name);
                    session.save(fabric);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 2 -> {
                System.out.print("Введите адрес:");
                scanner.nextLine();
                String address = scanner.nextLine();
                try {
                    Fabric fabric = session.get(Fabric.class, fabric_id);
                    fabric.setAddress(address);
                    session.save(fabric);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 3 -> {
                System.out.print("Введите ФИО контакта:");
                scanner.nextLine();
                String contactFio = scanner.nextLine();
                try {
                    Fabric fabric = session.get(Fabric.class, fabric_id);
                    fabric.setContact_fio(contactFio);
                    session.save(fabric);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 4 -> {
                System.out.print("Введите телефон:");
                scanner.nextLine();
                String phone = scanner.nextLine();
                try {
                    Fabric fabric = session.get(Fabric.class, fabric_id);
                    fabric.setPhone(phone);
                    session.save(fabric);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            default -> System.out.println("Неверный ввод");
        }

    }

    private void delete(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID фабрики:");
        int fabric_id = scanner.nextInt();
        try {
            Fabric fabric = session.get(Fabric.class, fabric_id);
            session.delete(fabric);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.Id фабрики");
        System.out.println("2.Название");
        System.out.println("3.Адрес");
        System.out.println("4.Контакт");
        System.out.println("5.Телефон");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Type> typeList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID фабрики:");
                int fabric_id = scanner.nextInt();
                try {
                    typeList = session.createQuery("SELECT f FROM Fabric f WHERE fabric_id =" + fabric_id,
                            Type.class).getResultList();
                    System.out.println("Фабрика");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s\n","ID","Название",
                            "Адрес", "Контакт", "Телефон");
                    typeList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 2 -> {
                System.out.print("Введите название:");
                String fabric_name = scanner.nextLine();
                try {
                    typeList = session.createQuery("SELECT f FROM Fabric f WHERE fabric_name =" + fabric_name,
                            Type.class).getResultList();
                    System.out.println("Фабрика");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s\n","ID","Название",
                            "Адрес", "Контакт", "Телефон");
                    typeList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 3 -> {
                System.out.print("Введите адрес:");
                String address = scanner.nextLine();
                try {
                    typeList = session.createQuery("SELECT f FROM Fabric f WHERE address =" + address,
                            Type.class).getResultList();
                    System.out.println("Фабрика");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s\n","ID","Название",
                            "Адрес", "Контакт", "Телефон");
                    typeList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 4 -> {
                System.out.print("Введите ФИО контакта:");
                String contactFio = scanner.nextLine();
                try {
                    typeList = session.createQuery("SELECT f FROM Fabric f WHERE contactFio =" + contactFio,
                            Type.class).getResultList();
                    System.out.println("Фабрика");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s\n","ID","Название",
                            "Адрес", "Контакт", "Телефон");
                    typeList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 5 -> {
                System.out.print("Введите телефон:");
                String phone = scanner.nextLine();
                try {
                    typeList = session.createQuery("SELECT f FROM Fabric f WHERE phone =" + phone,
                            Type.class).getResultList();
                    System.out.println("Фабрика");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s\n","ID","Название",
                            "Адрес", "Контакт", "Телефон");
                    typeList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            default -> System.out.println("Неверный ввод");
        }
    }
}
