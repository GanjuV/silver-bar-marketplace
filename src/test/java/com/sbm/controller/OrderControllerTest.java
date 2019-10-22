package com.sbm.controller;

import com.sbm.api.OrderController;
import com.sbm.dto.model.order.Order;
import com.sbm.dto.model.response.Response;
import com.sbm.exception.SBMException;
import com.sbm.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderControllerTest {

  @Autowired
  private OrderService orderService;

  @Test
  public void testRegisterOrderSuccess() throws Exception {
    OrderRequest ordReq = new OrderRequest();
    ordReq.setOrderType(Order.Type.SELL);
    ordReq.setPricePerKg("2.5 Kg");
    ordReq.setQuantity("USD 300");
    ordReq.setUserId("John");
    OrderController orderController = Mockito.mock(OrderController.class);
    List<Order> orderList = new ArrayList<>();
    Order ord = new Order();
    ord.setOrderType(Order.Type.SELL);
    ord.setPricePerKg("2.5 Kg");
    ord.setQuantity("USD 300");
    ord.setUserId("John");
    orderList.add(ord);
    Mockito.when(orderController.registerOrder(ordReq)).thenReturn(Response.ok(orderList));
  }

  @Test
  public void testRegisterOrderFailedForInvalidData() throws Exception {
    OrderRequest ordReq = new OrderRequest();
    ordReq.setOrderType(Order.Type.SELL);
    ordReq.setPricePerKg(null);
    ordReq.setQuantity(null);
    ordReq.setUserId("John");
    OrderController orderController = Mockito.mock(OrderController.class);
    List<Order> orderList = new ArrayList<>();
    Order ord = new Order();
    ord.setOrderType(Order.Type.SELL);
    ord.setPricePerKg("2.5 Kg");
    ord.setQuantity("USD 300");
    ord.setUserId("John");
    orderList.add(ord);
    Mockito.when(orderController.registerOrder(ordReq)).thenThrow(new SBMException.DuplicateEntityException("Exception"));
  }

  @Test
  public void testRegisterOrderExists() throws Exception {
    OrderRequest ordReq = new OrderRequest();
    ordReq.setOrderType(Order.Type.SELL);
    ordReq.setPricePerKg("");
    ordReq.setQuantity("USD 300");
    ordReq.setUserId("John");
    OrderController orderController = Mockito.mock(OrderController.class);
    List<Order> orderList = new ArrayList<>();
    Order ord = new Order();
    ord.setOrderType(Order.Type.SELL);
    ord.setPricePerKg("2.5 Kg");
    ord.setQuantity("USD 300");
    ord.setUserId("John");
    orderList.add(ord);
    Mockito.when(orderController.registerOrder(ordReq)).thenThrow(new RuntimeException("Order already present, duplicate entry"));
  }

  @Test
  public void testCancelOrderPass() throws Exception {
    OrderRequest ordReq = new OrderRequest();
    ordReq.setOrderType(Order.Type.SELL);
    ordReq.setPricePerKg("2.5 Kg");
    ordReq.setQuantity("USD 300");
    ordReq.setUserId("John");
    List<Order> orderList = new ArrayList<>();
    Order ord = new Order();
    orderList.add(ord);
    OrderController orderController = Mockito.mock(OrderController.class);
    Mockito.when(orderController.cancelOrder(ordReq)).thenReturn(Response.ok(orderList));
  }

  @Test
  public void testCancelOrderFailedInvalidData() throws Exception {
    OrderRequest ordReq = new OrderRequest();
    ordReq.setOrderType(Order.Type.SELL);
    ordReq.setPricePerKg(null);
    ordReq.setQuantity("USD 300");
    ordReq.setUserId("John");
    List<Order> orderList = new ArrayList<>();
    Order ord = new Order();
    orderList.add(ord);
    OrderController orderController = Mockito.mock(OrderController.class);
    Mockito.when(orderController.registerOrder(ordReq)).thenThrow(new SBMException.DuplicateEntityException("Exception"));
  }

  @Test
  public void testCancelOrderNotFound() throws Exception {
    OrderRequest ordReq = new OrderRequest();
    ordReq.setOrderType(Order.Type.SELL);
    ordReq.setPricePerKg("");
    ordReq.setQuantity("USD 300");
    ordReq.setUserId("John");
    OrderController orderController = Mockito.mock(OrderController.class);
    List<Order> orderList = new ArrayList<>();
    Order ord = new Order();
    ord.setOrderType(Order.Type.SELL);
    ord.setPricePerKg("2.5 Kg");
    ord.setQuantity("USD 300");
    ord.setUserId("John");
    orderList.add(ord);
    Mockito.when(orderController.registerOrder(ordReq)).thenThrow(new RuntimeException("Order not found."));
  }

  @Test
  public void testSummary() throws Exception {
    OrderRequest ord1 = new OrderRequest();
    OrderRequest ord2 = new OrderRequest();

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
    OrderController orderController = Mockito.mock(OrderController.class);
    orderController.registerOrder(ord1);
    orderController.registerOrder(ord2);
    Mockito.when(orderController.getSummary()).thenReturn(Response.ok(finalObj));
  }

}
