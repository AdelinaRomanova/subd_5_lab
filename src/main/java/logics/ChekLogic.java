package logics;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ChekLogic {
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
        System.out.print("Введите ID продавца:");
        int seller_id = scanner.nextInt();
        System.out.print("Введите дату:");
        String newDate = scanner.next();
        Date date = java.sql.Date.valueOf(newDate);
        try {
            Chek chek = new Chek(session.get(Seller.class, seller_id), date);
            session.save(chek);
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
                List<Chek> chekList = session.createQuery("SELECT c FROM Chek c",
                        Chek.class).getResultList();
                System.out.println("Чек");
                System.out.printf("%-30s%-30s%-30s\n","ID","ID продавца","Дата");
                chekList.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод");
        }

    }
    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID чека:");
        int chek_id = scanner.nextInt();
        System.out.println("Введите дату:");
        scanner.nextLine();
        String newDate = scanner.next();
        Date date = java.sql.Date.valueOf(newDate);
        try {
            Chek chek = session.get(Chek.class, chek_id);
            chek.setDate(date);
            session.save(chek);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void delete(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID чека:");
        int chek_id = scanner.nextInt();
        try {
            Chek chek = session.get(Chek.class, chek_id);
            session.delete(chek);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.Id чека");
        System.out.println("2.Id продавца");
        System.out.println("3.Дата");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Chek> chekList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID чека:");
                int chek_id = scanner.nextInt();
                try {
                    chekList = session.createQuery("SELECT c FROM Chek c WHERE chek_id =" + chek_id,
                            Chek.class).getResultList();
                    System.out.println("Чек");
                    System.out.printf("%-30s%-30s%-30s\n","ID","ID продавца","Дата");
                    chekList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 2 -> {
                System.out.print("Введите ID продавца:");
                int seller_id = scanner.nextInt();
                try {
                    chekList = session.createQuery("SELECT c FROM Chek c WHERE seller_id =" + seller_id,
                            Chek.class).getResultList();
                    System.out.println("Чек");
                    System.out.printf("%-30s%-30s%-30s\n","ID","ID продавца","Дата");
                    chekList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 3 -> {
                System.out.print("Введите дату:");
                int date = scanner.nextInt();
                try {
                    chekList = session.createQuery("SELECT c FROM Chek c WHERE date =" + date,
                            Chek.class).getResultList();
                    System.out.println("Продукт");
                    System.out.printf("%-30s%-30s%-30s\n","ID","ID продавца","Дата");
                    chekList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            default -> System.out.println("Неверный ввод");
        }
    }
}
