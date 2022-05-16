import entities.ChekProduct;
import entities.Product;
import entities.Receipt;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class Requests {
    public void work(SessionFactory sessionFactory){
        System.out.println("1.Первый запрос");
        System.out.println("2.Второй запрос");
        System.out.println("3.Третий запрос");
        System.out.println("4.Вернуться в меню");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        Session session;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        switch (i) {
            case 1 -> firstRequest(session);
            case 2 -> secondRequest(session);
            case 3 -> thirdRequest(session);
            case 4 -> {
                session.close();
                return;
            }
            default -> System.out.println("Неверный ввод");
        }
        session.getTransaction().commit();
    }

    private void firstRequest(Session session) {
        List<ChekProduct> chekProducts = session.createQuery("SELECT cp FROM ChekProduct cp",
                ChekProduct.class).getResultList();
        System.out.printf("%-25s%-20s%-15s\n", "Название продукта", "Дата", "Количество");
        for (ChekProduct chekProduct : chekProducts)
            System.out.printf("%-25s%-20tF%-15d\n",chekProduct.getProduct().getProduct_name(),
                    chekProduct.getChek().getDate(), chekProduct.getCount());
    }


    private void secondRequest(Session session){
        List<Product> productList = session.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        System.out.printf("%-25s%-25s\n", "Название", "Цена");
        for (Product product : productList)
            if (product.getPrice()>1000 && product.getPrice()<5000)
            System.out.printf("%-25s%-25d \n",product.getProduct_name(),product.getPrice());
    }

    private void thirdRequest(Session session){
        List<Receipt> receiptList = session.createQuery("SELECT r FROM Receipt r", Receipt.class).getResultList();
        System.out.printf("%-25s%-25s \n", "Поставка", "Дата");
        for (Receipt receipt : receiptList)
            if(receipt.getDate().after(java.sql.Date.valueOf("2022-05-05")))
                System.out.printf("%-25s%-25tF \n",receipt.getReceipt_id(),receipt.getDate());
    }

}
