package ags.controller;

import ags.model.Order;
import ags.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Agarimo
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST, path = "/createorder")
    public String createOrder(@RequestParam(value = "bricks") int number) {
        return orderService.addOrder(number);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getorder")
    public Order getOrder(@RequestParam(value = "ref") String reference) {
        return orderService.retrieveOrder(reference);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getorders")
    public List<Order> getOrders() {
        return orderService.retrieveOrders();
    }
}
