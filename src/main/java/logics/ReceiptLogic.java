package logics;

import entities.Receipt;
import entities.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ReceiptLogic {
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
        System.out.print("Введите дату поставки:");
        String newDate = scanner.next();
        Date date = java.sql.Date.valueOf(newDate);

        try {
            Receipt receipt = new Receipt(date);
            session.save(receipt);
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
                List<Receipt> receiptList = session.createQuery("SELECT r FROM Receipt r",
                        Receipt.class).getResultList();
                System.out.println("Поставка");
                System.out.printf("%-15s%s\n","ID","Дата");
                receiptList.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод");
        }

    }
    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID поставки:");
        int receipt_id = scanner.nextInt();
        System.out.println("Введите дату:");
        scanner.nextLine();
        String newDate = scanner.next();
        Date date = java.sql.Date.valueOf(newDate);
        try {
            Receipt receipt = session.get(Receipt.class, receipt_id);
            receipt.setDate(date);
            session.save(receipt);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void delete(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID поставки:");
        int receipt_id = scanner.nextInt();
        try {
            Receipt receipt = session.get(Receipt.class, receipt_id);
            session.delete(receipt);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.Id поставки");
        System.out.println("2.Дата поставки");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Receipt> receiptList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID поставки:");
                int receipt_id = scanner.nextInt();
                try {
                    receiptList = session.createQuery("SELECT r FROM Receipt r WHERE receipt_id =" + receipt_id,
                            Receipt.class).getResultList();
                    System.out.println("Поставка");
                    System.out.printf("%-15s%s\n","ID","Дата");
                    receiptList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 2 -> {
                System.out.print("Введите дату поставки:");
                String newDate = scanner.next();
                Date date = java.sql.Date.valueOf(newDate);
                try {
                    receiptList = session.createQuery("SELECT r FROM Receipt r WHERE date =" + date,
                            Receipt.class).getResultList();
                    System.out.println("Поставка");
                    System.out.printf("%-15s%s\n","ID","Дата");
                    receiptList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            default -> System.out.println("Неверный ввод");
        }
    }
}
