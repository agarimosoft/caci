package ags.controller;

import ags.model.Order;
import ags.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(method = RequestMethod.PUT, path = "/updateorder")
    public ResponseEntity updateOrder(@RequestParam(value = "ref") String reference, @RequestParam(value = "bricks") int bricks) {
        if (orderService.updateOrder(reference, bricks)) {
            return new ResponseEntity(reference, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/fulfilorder")
    public ResponseEntity fulfilOrder(@RequestParam(value = "ref") String reference) {
        if (orderService.fulfilOrder(reference)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
