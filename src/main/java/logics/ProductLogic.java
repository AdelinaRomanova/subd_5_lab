package logics;

import entities.Fabric;
import entities.Product;
import entities.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class ProductLogic {
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
        System.out.print("Введите ID типа обуви:");
        int type_id = scanner.nextInt();
        System.out.print("Введите ID фабрики:");
        int fabric_id = scanner.nextInt();
        System.out.print("Введите название продукта:");
        scanner.nextLine();
        String product_name = scanner.nextLine();
        System.out.print("Введите цену:");
        int price = scanner.nextInt();
        System.out.print("Введите цену закупки:");
        int initial_price = scanner.nextInt();
        System.out.print("Введите размер:");
        int size = scanner.nextInt();
        System.out.print("Введите количество:");
        int count = scanner.nextInt();
        try {
            Product product = new Product(session.get(Type.class, type_id), session.get(Fabric.class, fabric_id),
                    product_name, price, initial_price, size, count);
            session.save(product);
        }
        catch (Exception ex){
            System.out.println("Ошибка создания записи");
        }
    }

    private void read(Session session){
        System.out.println("1.С фильтром");
        System.out.println("2.Без фильтра");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        switch (i){
            case 1-> filter(session);
            case 2-> {
                List<Product> productList = session.createQuery("SELECT p FROM Product p",
                        Product.class).getResultList();
                System.out.println("Продукт");
                System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                        "Название", "Цена", "Цена закупки", "Размер", "Количество");
                productList.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод");
        }

    }
    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID продукта:");
        int product_id = scanner.nextInt();
        System.out.println("Что вы хотите обновить?");
        System.out.println("1.ID типа");
        System.out.println("2.ID фабрики");
        System.out.println("3.Название");
        System.out.println("4.Цена");
        System.out.println("5.Цена закупки");
        System.out.println("6.Размер");
        System.out.println("7.Количество");
        System.out.print(">");
        int i = scanner.nextInt();
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID типа:");
                scanner.nextLine();
                int type_id = scanner.nextInt();
                try {
                    Product product = session.get(Product.class, product_id);
                    product.setTypes(session.get(Type.class, type_id));
                    session.save(product);

                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 2 -> {
                System.out.print("Введите ID фабрики:");
                scanner.nextLine();
                int fabric_id = scanner.nextInt();
                try {
                    Product product = session.get(Product.class, product_id);
                    product.setFabrics(session.get(Fabric.class, fabric_id));
                    session.save(product);

                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 3 -> {
                System.out.print("Введите название:");
                scanner.nextLine();
                String product_name = scanner.nextLine();
                try {
                    Product product = session.get(Product.class, product_id);
                    product.setProduct_name(product_name);
                    session.save(product);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 4 -> {
                System.out.print("Введите цену:");
                scanner.nextLine();
                int price = scanner.nextInt();
                try {
                    Product product = session.get(Product.class, product_id);
                    product.setPrice(price);
                    session.save(product);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 5 -> {
                System.out.print("Введите цену закупки:");
                scanner.nextLine();
                int initial_price = scanner.nextInt();
                try {
                    Product product = session.get(Product.class, product_id);
                    product.setInitial_price(initial_price);
                    session.save(product);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 6 -> {
                System.out.print("Введите размер:");
                scanner.nextLine();
                int size = scanner.nextInt();
                try {
                    Product product = session.get(Product.class, product_id);
                    product.setSize(size);
                    session.save(product);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 7 -> {
                System.out.print("Введите количество:");
                scanner.nextLine();
                int count = scanner.nextInt();
                try {
                    Product product = session.get(Product.class, product_id);
                    product.setSize(count);
                    session.save(product);
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
        System.out.print("Введите ID продукта:");
        int product_id = scanner.nextInt();
        try {
            Product product = session.get(Product.class, product_id);
            session.delete(product);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.Id продукта");
        System.out.println("2.Id типа");
        System.out.println("3.Id фабрики");
        System.out.println("4.Название");
        System.out.println("5.Цена");
        System.out.println("6.Цена закупки");
        System.out.println("7.Размер");
        System.out.println("8.Количество");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Product> productList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID продукта:");
                int product_id = scanner.nextInt();
                try {
                    productList = session.createQuery("SELECT p FROM Product p WHERE product_id =" + product_id,
                            Product.class).getResultList();
                    System.out.println("Продукт");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 2 -> {
                System.out.print("Введите ID типа:");
                int type_id = scanner.nextInt();
                try {
                    productList = session.createQuery("SELECT p FROM Product p WHERE type_id =" + type_id,
                            Product.class).getResultList();
                    System.out.println("Продукт");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 3 -> {
                System.out.print("Введите ID фабрики:");
                int fabric_id = scanner.nextInt();
                try {
                    productList = session.createQuery("SELECT p FROM Product p WHERE fabric_id =" + fabric_id,
                            Product.class).getResultList();
                    System.out.println("Продукт");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 4 -> {
                System.out.print("Введите название:");
                int product_name = scanner.nextInt();
                try {
                    productList = session.createQuery("SELECT p FROM Product p WHERE product_name =" + product_name,
                            Product.class).getResultList();
                    System.out.println("Продукт");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 5 -> {
                System.out.print("Введите цену:");
                int price = scanner.nextInt();
                try {
                    productList = session.createQuery("SELECT p FROM Product p WHERE price =" + price,
                            Product.class).getResultList();
                    System.out.println("Продукт");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 6 -> {
                System.out.print("Введите цену закупки:");
                int initial_price = scanner.nextInt();
                try {
                    productList = session.createQuery("SELECT p FROM Product p WHERE initial_price =" + initial_price,
                            Product.class).getResultList();
                    System.out.println("Продукт");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 7 -> {
                System.out.print("Введите размер:");
                int size = scanner.nextInt();
                try {
                    productList = session.createQuery("SELECT p FROM Product p WHERE size =" + size,
                            Product.class).getResultList();
                    System.out.println("Продукт");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 8 -> {
                System.out.print("Введите количество:");
                int count = scanner.nextInt();
                try {
                    productList = session.createQuery("SELECT p FROM Product p WHERE count =" + count,
                            Product.class).getResultList();
                    System.out.println("Продукт");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            default -> System.out.println("Неверный ввод");
        }
    }
}
