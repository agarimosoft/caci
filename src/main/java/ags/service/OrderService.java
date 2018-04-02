package ags.service;

import ags.model.Order;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Agarimo
 */
@Component
public class OrderService {

    private static List<Order> orders = new ArrayList();
    private int counter;

    public String addOrder(int bricks) {
        Order order = new Order(generateReference(), bricks);
        orders.add(order);

        return order.getReference();
    }

    private String generateReference() {
        counter++;
        return Integer.toString(counter);
    }

    public Order retrieveOrder(String reference) {
        int index = orders.indexOf(new Order(reference));

        if (index >= 0) {
            return orders.get(index);
        }

        return null;
    }

    public List<Order> retrieveOrders() {
        return orders;
    }
}
