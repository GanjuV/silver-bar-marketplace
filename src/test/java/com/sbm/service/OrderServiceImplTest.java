package com.sbm.service;

import com.sbm.dto.model.order.Order;
import com.sbm.exception.SBMException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceImplTest {

    @Test
    public void testRegisterOrderServiceSuccess() throws Exception {
        OrderServiceImpl orderService = Mockito.mock(OrderServiceImpl.class);
        List<Order> orderList = new ArrayList<>();
        Order ord = new Order();
        ord.setOrderType(Order.Type.SELL);
        ord.setPricePerKg("2.5 Kg");
        ord.setQuantity("USD 300");
        ord.setUserId("John");
        orderList.add(ord);
        Mockito.when(orderService.addOrder(ord)).thenReturn(orderList);
    }

    @Test
    public void testRegisterOrderServiceDuplicateException() throws Exception {
        OrderServiceImpl orderService = Mockito.mock(OrderServiceImpl.class);
        List<Order> orderList = new ArrayList<>();
        Order ord = new Order();
        ord.setOrderType(Order.Type.SELL);
        ord.setPricePerKg("2.5 Kg");
        ord.setQuantity("USD 300");
        ord.setUserId("John");
        orderList.add(ord);
        Mockito.when(orderService.addOrder(ord)).thenReturn(orderList);
        Order ord2 = new Order();
        ord2.setOrderType(Order.Type.SELL);
        ord2.setPricePerKg("2.5 Kg");
        ord2.setQuantity("USD 300");
        ord2.setUserId("John");
        Mockito.when(orderService.addOrder(ord2)).thenThrow(new SBMException.DuplicateEntityException("Duplicate entry"));
    }

    @Test
    public void testCancelOrderServiceNotFoundException() throws Exception {
        OrderServiceImpl orderService = Mockito.mock(OrderServiceImpl.class);
        List<Order> orderList = new ArrayList<>();
        Order ord = new Order();
        ord.setOrderType(Order.Type.SELL);
        ord.setPricePerKg("2.5 Kg");
        ord.setQuantity("USD 300");
        ord.setUserId("John");
        orderList.add(ord);
        Mockito.when(orderService.removeOrder(ord)).thenThrow(new SBMException.EntityNotFoundException("Order not found"));
    }

    @Test
    public void testCancelOrderServiceSuccess() throws Exception {
        Order ord = new Order();
        ord.setOrderType(Order.Type.SELL);
        ord.setPricePerKg("2.5 Kg");
        ord.setQuantity("USD 300");
        ord.setUserId("John");
        List<Order> orderList = new ArrayList<>();
        OrderServiceImpl orderService = Mockito.mock(OrderServiceImpl.class);
        Mockito.when(orderService.removeOrder(ord)).thenReturn(orderList);
    }

    @Test
    public void testServiceSummary() throws Exception {
        Order ord1 = new Order();
        Order ord2 = new Order();

        ord1.setOrderType(Order.Type.SELL);
        ord1.setPricePerKg("1.5 Kg");
        ord1.setQuantity("USD 300");
        ord1.setUserId("Frank");
        ord2.setOrderType(Order.Type.BUY);
        ord2.setPricePerKg("2.5 Kg");
        ord2.setQuantity("USD 200");
        ord2.setUserId("Willson");
        Map finalObj = new TreeMap<String, TreeMap>();

        Map sellMap = new TreeMap<String, String>();
        sellMap.put("USD300", "1.5 Kg");

        Map buyMap = new TreeMap<String, String>();
        buyMap.put("USD200", "2.5 Kg");

        finalObj.put("SELL", sellMap);
        finalObj.put("BUY", buyMap);
        OrderServiceImpl orderService = Mockito.mock(OrderServiceImpl.class);
        orderService.addOrder(ord1);
        orderService.addOrder(ord2);
        Mockito.when(orderService.getAllOrders()).thenReturn(finalObj);
    }
}
