package ags.controller;

import ags.model.Order;
import ags.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 * @author Agarimo
 */
@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;

    //STAGE 1
    @Test
    public void createOrder() throws Exception {

        Mockito.when(
                service.addOrder(Mockito.anyInt()))
                .thenReturn("1");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/createorder")
                .param("bricks", "115");

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1")));
    }

    @Test
    public void retrieveOrder() throws Exception {
        Order order = new Order("1", 12);
        String exampleJson = "{\"reference\":\"1\",\"bricks\":12}";

        Mockito.when(
                service.retrieveOrder(Mockito.anyString()))
                .thenReturn(order);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getorder")
                .param("ref", "1");

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(exampleJson));
    }

    @Test
    public void retrieveInvalidOrder() throws Exception {
        Mockito.when(
                service.retrieveOrder(Mockito.anyString()))
                .thenReturn(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getorder")
                .param("ref", "1");

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void retrieveOrders() throws Exception {
        List orders = new ArrayList();
        orders.add(new Order("1", 10));
        orders.add(new Order("2", 11));

        String exampleJson = "[{\"reference\":\"1\",\"bricks\":10},{\"reference\":\"2\",\"bricks\":11}]";

        Mockito.when(
                service.retrieveOrders())
                .thenReturn(orders);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getorders");

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(exampleJson));
    }

    //STAGE 2
    @Test
    public void updateOrder() throws Exception {
        Mockito.when(
                service.updateOrder(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/updateorder")
                .param("ref", "1")
                .param("bricks", "115");

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1")));
    }
}
