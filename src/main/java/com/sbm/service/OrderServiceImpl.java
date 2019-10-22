package com.sbm.service;

import com.sbm.PropertiesConfig;
import com.sbm.dto.model.order.Order;
import com.sbm.exception.SBMException;
import com.sbm.exception.EntityType;
import com.sbm.exception.ExceptionType;
import com.sbm.util.SortComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Order ServiceImpl class.
 * @author HCL
 */
@Component
public class OrderServiceImpl implements OrderService {

  private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

  private static PropertiesConfig propertiesConfig;
  private static final List<Order> registeredOrders = new ArrayList<>();

  /**
   * Retruns List of orders present in in memory storage.
   * @param order
   * @return List<OrderDto>
   */
  @Override
  public List<Order> addOrder(Order order) {
    logger.info("*******In addOrder method SERVICE **********");
    if(registeredOrders.contains(order))
      throw SBMException.throwException(EntityType.ORDER, ExceptionType.DUPLICATE_ENTITY);

    registeredOrders.add(order);
    return registeredOrders;
  }

  /**
   * Retruns List of orders present in in memory storage after deletion.
   * @param order
   * @return List<OrderDto>
   */
  @Override
  public List<Order> removeOrder(Order order) {
    logger.info("*******In removeOrder method of SERVICE **********");
    if(!registeredOrders.contains(order))
      throw SBMException.throwException(EntityType.ORDER, ExceptionType.ENTITY_NOT_FOUND);
    registeredOrders.removeIf(orderDetail -> orderDetail.equals(order));
    return registeredOrders;
  }

  /**
   * Retruns Map of BUY and SELL orders present in in memory storage.
   * @return Map<String, TreeMap>
   */
  @Override
  public Map<String, TreeMap> getAllOrders() {
    logger.info("*******In getAllOrders method of SERVICE **********");
    // Creating a Sell and Buy Modified Map
    Map<String, String> sellMap = getModifiedMap(Order.Type.SELL);
    Map<String, String> buyMap = getModifiedMap(Order.Type.BUY);

    // Creating a Sell Map for sorting ASC
    SortedMap<String , String> sortedMapSell = new TreeMap<>(new SortComparator("ASC"));
    sortedMapSell.putAll(sellMap);

    // Creating a Buy Map for sorting DSC
    SortedMap<String , String> sortedMapBuy = new TreeMap<>(new SortComparator("DEC"));
    sortedMapBuy.putAll(buyMap);

    // Putting the output in final map
    Map finalMap = new HashMap<>();
    finalMap.put("BUY", sortedMapBuy);
    finalMap.put("SELL", sortedMapSell);
    return finalMap;
  }

  private static Map<String, String> getModifiedMap(Order.Type orderType) {
    logger.info("*******In getModifiedMap method of SERVICE **********");
    return registeredOrders.stream()
      // Streaming on the list of array and filtering sell order type map.
      .filter(ele -> orderType.toString().equalsIgnoreCase(ele.getOrderType().toString()))
      // Grouping with price per kg and adding the quantity.
      .collect(Collectors.toMap(Order::getPricePerKg, o -> o.getQuantity(), (q1, q2) -> {
        String value = "[^\\d${.}]";
        String currency = "[0-9./ /]";
        Double sum = Double.parseDouble(q1.replaceAll(value, "")) + Double.parseDouble(q2.replaceAll(value, ""));
        return String.valueOf(sum) + " " + q1.replaceAll(currency, "");
      }, TreeMap::new));
  }

}
