package logics;

import entities.Fabric;
import entities.Seller;
import entities.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class SellerLogic {
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
        System.out.print("Введите имя:");
        String first_name = scanner.nextLine();
        System.out.print("Введите фамилию:");
        String last_name = scanner.nextLine();
        System.out.print("Введите отчество:");
        String patronymic = scanner.nextLine();
        try {
            Seller seller = new Seller(first_name, last_name, patronymic);
            session.save(seller);
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
                List<Seller> sellerList = session.createQuery("SELECT s FROM Seller s",
                        Seller.class).getResultList();
                System.out.println("Продавец");
                System.out.printf("%-25s%-25s%-25s%-25s\n","ID","Имя","Фамилия", "Отчество");
                sellerList.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод");
        }

    }
    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID продавца:");
        int seller_id = scanner.nextInt();
        System.out.println("Что вы хотите обновить?");
        System.out.println("1.Имя");
        System.out.println("2.Фамилия");
        System.out.println("3.Отчество");
        System.out.print(">");
        int i = scanner.nextInt();
        switch (i) {
            case 1 -> {
                System.out.print("Введите имя:");
                scanner.nextLine();
                String first_name = scanner.nextLine();
                try {
                    Seller seller = session.get(Seller.class, seller_id);
                    seller.setFirst_name(first_name);
                    session.save(seller);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 2 -> {
                System.out.print("Введите фамилию:");
                scanner.nextLine();
                String last_name = scanner.nextLine();
                try {
                    Seller seller = session.get(Seller.class, seller_id);
                    seller.setLast_name(last_name);
                    session.save(seller);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 3 -> {
                System.out.print("Введите отчество:");
                scanner.nextLine();
                String patronymic = scanner.nextLine();
                try {
                    Seller seller = session.get(Seller.class, seller_id);
                    seller.setPatronymic(patronymic);
                    session.save(seller);
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
        System.out.print("Введите ID продавца:");
        int sellerId = scanner.nextInt();
        try {
            Seller seller = session.get(Seller.class, sellerId);
            session.delete(seller);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.Id продавца");
        System.out.println("2.Имя");
        System.out.println("3.Фамидия");
        System.out.println("4.Отчество");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Seller> sellerList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID продавца:");
                int seller_id = scanner.nextInt();
                try {
                    sellerList = session.createQuery("SELECT s FROM Seller s WHERE seller_id =" + seller_id,
                            Seller.class).getResultList();
                    System.out.println("Продавец");
                    System.out.printf("%-25s%-25s%-25s%-25s\n", "ID", "Имя", "Фамилия", "Отчество");
                    sellerList.forEach(System.out::println);
                } catch (Exception ex) {
                    System.out.println("Этой записи не существует");
                }
            }
            case 2 -> {
                System.out.print("Введите имя:");
                int first_name = scanner.nextInt();
                try {
                    sellerList = session.createQuery("SELECT s FROM Seller s WHERE first_name =" + first_name,
                            Seller.class).getResultList();
                    System.out.println("Продавец");
                    System.out.printf("%-25s%-25s%-25s%-25s\n", "ID", "Имя", "Фамилия", "Отчество");
                    sellerList.forEach(System.out::println);
                } catch (Exception ex) {
                    System.out.println("Этой записи не существует");
                }
            }
            case 3 -> {
                System.out.print("Введите фамилию:");
                int last_name = scanner.nextInt();
                try {
                    sellerList = session.createQuery("SELECT s FROM Seller s WHERE last_name =" + last_name,
                            Seller.class).getResultList();
                    System.out.println("Продавец");
                    System.out.printf("%-25s%-25s%-25s%-25s\n", "ID", "Имя", "Фамилия", "Отчество");
                    sellerList.forEach(System.out::println);
                } catch (Exception ex) {
                    System.out.println("Этой записи не существует");
                }
            }
            case 4 -> {
                System.out.print("Введите отчество:");
                int patronymic = scanner.nextInt();
                try {
                    sellerList = session.createQuery("SELECT s FROM Seller s WHERE patronymic =" + patronymic,
                            Seller.class).getResultList();
                    System.out.println("Продавец");
                    System.out.printf("%-25s%-25s%-25s%-25s\n", "ID", "Имя", "Фамилия", "Отчество");
                    sellerList.forEach(System.out::println);
                } catch (Exception ex) {
                    System.out.println("Этой записи не существует");
                }
            }
            default -> System.out.println("Неверный ввод");
        }
    }
}
