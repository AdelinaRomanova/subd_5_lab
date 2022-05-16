package logics;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class ReceiptProductLogic {
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
            case 5 -> session.close();
            default -> System.out.println("Неверный ввод");
        }
    }

    private void create(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID поставки:");
        int receipt_id = scanner.nextInt();
        System.out.print("Введите ID продукта:");
        int product_id = scanner.nextInt();
        System.out.print("Введите количество:");
        int count = scanner.nextInt();
        try {
            ReceiptProduct receiptProduct = new ReceiptProduct(session.get(Receipt.class, receipt_id),
                    session.get(Product.class, product_id), count);
            session.save(receiptProduct);
            session.getTransaction().commit();
        }
        catch (Exception ex){
            System.out.println("Ошибка создания записи");
            session.close();
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
                List<ReceiptProduct> receiptProducts = session.createQuery("SELECT rp FROM ReceiptProduct rp",
                        ReceiptProduct.class).getResultList();
                System.out.println("Поставка продукта");
                System.out.printf("%-15s%-15s%s\n","ID поставки","ID продукта","Количество");
                receiptProducts.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод");
        }
        session.getTransaction().commit();
    }

    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID чека продукта:");
        int receipt_product_pkey = scanner.nextInt();
        System.out.println("Что вы хотите обновить?");
        System.out.println("1.ID поставки");
        System.out.println("2.ID продукта");
        System.out.println("3.Количество");
        System.out.print(">");
        int i = scanner.nextInt();
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID поставки:");
                scanner.nextLine();
                int receipt_id = scanner.nextInt();
                try {
                    ReceiptProduct receiptProduct = session.get(ReceiptProduct.class, receipt_product_pkey);
                    receiptProduct.setReceipt(session.get(Receipt.class, receipt_id));
                    session.save(receiptProduct);
                    session.getTransaction().commit();
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                    session.close();
                }
            }
            case 2 -> {
                System.out.print("Введите ID продукта:");
                scanner.nextLine();
                int product_id = scanner.nextInt();
                try {
                    ReceiptProduct receiptProduct = session.get(ReceiptProduct.class, receipt_product_pkey);
                    receiptProduct.setProduct(session.get(Product.class, product_id));
                    session.save(receiptProduct);
                    session.getTransaction().commit();
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                    session.close();
                }
            }
            case 3 -> {
                System.out.print("Введите количество:");
                scanner.nextLine();
                int count = scanner.nextInt();
                try {
                    ReceiptProduct receiptProduct = session.get(ReceiptProduct.class, receipt_product_pkey);
                    receiptProduct.setCount(count);
                    session.save(receiptProduct);
                    session.getTransaction().commit();
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                    session.close();
                }
            }
            default -> System.out.println("Неверный ввод");
        }
    }

    private void delete(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID поставки:");
        int receipt_product_pkey = scanner.nextInt();
        try {
            ReceiptProduct receiptProduct = session.get(ReceiptProduct.class, receipt_product_pkey);
            session.delete(receiptProduct);
            session.getTransaction().commit();
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
            session.close();
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.ID поставки");
        System.out.println("2.ID продукта");
        System.out.println("3.Количество");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<ReceiptProduct> serviceReceiptList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID поставки:");
                int receipt_id = scanner.nextInt();
                try {
                    serviceReceiptList = session.createQuery("SELECT rp FROM ReceiptProduct rp " +
                            "WHERE receipt_id =" + receipt_id, ReceiptProduct.class).getResultList();
                    System.out.println("Поставка продукта");
                    System.out.printf("%-15s%-15s%s\n","ID поставки","ID продукта","Количество");
                    serviceReceiptList.forEach(System.out::println);
                    session.getTransaction().commit();
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                    session.close();
                }
            }
            case 2 -> {
                System.out.print("Введите ID продукта:");
                int product_id = scanner.nextInt();
                try {
                    serviceReceiptList = session.createQuery("SELECT rp FROM ReceiptProduct rp " +
                            "WHERE product_id =" + product_id, ReceiptProduct.class).getResultList();
                    System.out.println("Поставка продукта");
                    System.out.printf("%-15s%-15s%s\n","ID поставки","ID продукта","Количество");
                    serviceReceiptList.forEach(System.out::println);
                    session.getTransaction().commit();
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                    session.close();
                }
            }
            case 3 -> {
                System.out.print("Введите количество:");
                int count = scanner.nextInt();
                try {
                    serviceReceiptList = session.createQuery("SELECT rp FROM ReceiptProduct rp " +
                            "WHERE count =" + count, ReceiptProduct.class).getResultList();
                    System.out.println("Поставка продукта");
                    System.out.printf("%-15s%-15s%s\n","ID поставки","ID продукта","Количество");
                    serviceReceiptList.forEach(System.out::println);
                    session.getTransaction().commit();
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                    session.close();
                }
            }
            default -> System.out.println("Неверный ввод");
        }
    }
}
