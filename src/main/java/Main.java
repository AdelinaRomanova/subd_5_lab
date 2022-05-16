import entities.*;
import logics.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Type.class)
                .addAnnotatedClass(Fabric.class)
                .addAnnotatedClass(Seller.class)
                .addAnnotatedClass(Receipt.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(ReceiptProduct.class)
                .addAnnotatedClass(Chek.class)
                .addAnnotatedClass(ChekProduct.class)
                .buildSessionFactory();

    boolean working = true;
        while (working) {
        System.out.println("\n\t\tМенюшка");
        System.out.println("1.Работать с 'type'");
        System.out.println("2.Работать с 'fabric'");
        System.out.println("3.Работать с 'seller'");
        System.out.println("4.Работать с 'receipt'");
        System.out.println("5.Работать с 'product'");
        System.out.println("6.Работать с 'receiptProduct'");
        System.out.println("7.Работать с 'chek'");
        System.out.println("8.Работать с 'chekProduct'");
        System.out.println("9.Работать с запросами");
        System.out.println("10.Выход");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        switch (i) {
            case 1 -> {
                TypeLogic typeLogic = new TypeLogic();
                typeLogic.work(sessionFactory);
            }
            case 2 -> {
                FabricLogic fabricLogic = new FabricLogic();
                fabricLogic.work(sessionFactory);
            }
            case 3 -> {
                SellerLogic sellerLogic = new SellerLogic();
                sellerLogic.work(sessionFactory);
            }
            case 4 -> {
                ReceiptLogic receiptLogic = new ReceiptLogic();
                receiptLogic.work(sessionFactory);
            }
            case 5 -> {
                ProductLogic productLogic = new ProductLogic();
                productLogic.work(sessionFactory);
            }
            case 6 -> {
                ReceiptProductLogic receiptProductLogic = new ReceiptProductLogic();
                receiptProductLogic.work(sessionFactory);
            }
            case 7 -> {
                ChekLogic chekLogic = new ChekLogic();
                chekLogic.work(sessionFactory);
            }
            case 8 -> {
                ChekProductLogic chekProductLogic = new ChekProductLogic();
                chekProductLogic.work(sessionFactory);
            }
            case 9 -> {
                Requests requests = new Requests();
                requests.work(sessionFactory);
            }
            case 10 -> {
                System.out.println("Exit...");
                working = false;
            }
            default -> System.out.println("Invalid input");
        }
    }
        sessionFactory.close();
}

}
