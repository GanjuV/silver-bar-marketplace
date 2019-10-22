package com.sbm.controller;

import com.sbm.api.OrderController;
import com.sbm.dto.model.order.Order;
import com.sbm.dto.model.response.Response;
import com.sbm.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Order Details Controller
 *
 * @author HCL
 */
@RestController
public class OrderControllerImpl implements OrderController {

  private static final Logger logger = LoggerFactory.getLogger(OrderControllerImpl.class);

  @Autowired
  OrderService orderService;

  /**
   * Create order.
   * @param orderRequest
   * @return Response
   */
  @Override
  public Response registerOrder(@Valid @RequestBody OrderRequest orderRequest){
    logger.info("*******In registerOrder method CONTROLLER**********");
    Order order = new Order();
    order.setUserId(orderRequest.getUserId());
    order.setPricePerKg(orderRequest.getPricePerKg());
    order.setQuantity(orderRequest.getQuantity());
    order.setOrderType(orderRequest.getOrderType());
    return Response.ok(orderService.addOrder(order));
  }

  /**
   * Delete order.
   *
   * @param orderRequest
   * @return Response
   */
  @Override
  public Response cancelOrder(@Valid @RequestBody OrderRequest orderRequest){
    logger.info("*******In cancelOrder method CONTROLLER**********");
    Order order = new Order();
    order.setUserId(orderRequest.getUserId());
    order.setPricePerKg(orderRequest.getPricePerKg());
    order.setQuantity(orderRequest.getQuantity());
    order.setOrderType(orderRequest.getOrderType());
    return Response.ok(orderService.removeOrder(order));
  }

  /**
   * Get all order details.
   * @return Response
   */
  @Override
  public Response getSummary(){
    logger.info("*******In getSummary method CONTROLLER**********");
    return Response.ok(orderService.getAllOrders());
  }
}
