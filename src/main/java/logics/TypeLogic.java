package logics;

import entities.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.Year;
import java.util.List;
import java.util.Scanner;

public class TypeLogic {
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
        System.out.print("Введите название типа:");
        String type_name = scanner.nextLine();
        try {
            Type type = new Type(type_name);
            session.save(type);
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
                List<Type> typeList = session.createQuery("SELECT t FROM Type t",
                        Type.class).getResultList();
                System.out.println("Тип обвуви");
                System.out.printf("%-15s%s\n","type_id","type_name");
                typeList.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод");
        }

    }
    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID типа:");
        int type_id = scanner.nextInt();
        System.out.println("Введите название типа:");
        scanner.nextLine();
        String type_name = scanner.nextLine();
        try {
            Type type = session.get(Type.class, type_id);
            type.setType_name(type_name);
            session.save(type);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void delete(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID типа:");
        int type_id = scanner.nextInt();
        try {
            Type type = session.get(Type.class, type_id);
            session.delete(type);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.Id типа");
        System.out.println("2.Название типа");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Type> typeList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID типа:");
                int type_id = scanner.nextInt();
                try {
                    typeList = session.createQuery("SELECT t FROM Type d WHERE type_id =" + type_id,
                            Type.class).getResultList();
                    System.out.println("Тип обуви");
                    System.out.printf("%-15s%s\n","ID типа","Название");
                    typeList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 2 -> {
                System.out.print("Введите название:");
                String type_name = scanner.nextLine();
                try {
                    typeList = session.createQuery("SELECT t FROM Type d WHERE type_name ='" + type_name + "'",
                            Type.class).getResultList();
                    System.out.println("Тип обуви");
                    System.out.printf("%-15s%s\n","ID типа","Название");
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
