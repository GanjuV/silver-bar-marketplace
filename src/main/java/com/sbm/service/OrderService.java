package com.sbm.service;

import com.sbm.dto.model.order.Order;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Order service interface
 */

public interface OrderService {

  List<Order> addOrder(Order order);

  List<Order> removeOrder(Order order);

  Map<String, TreeMap> getAllOrders();
}
