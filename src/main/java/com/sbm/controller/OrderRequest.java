package com.sbm.controller;

import com.sbm.dto.model.order.Order;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Order request call for handling validations.
 * @author HCL
 */
public class OrderRequest {

  @NotEmpty(message = "{constraints.NotEmpty.message}")
  private String userId;

  @NotEmpty(message = "{constraints.NotEmpty.message}")
  private String quantity;

  @NotEmpty(message = "{constraints.NotEmpty.message}")
  private String pricePerKg;

  @NotNull(message = "{constraints.NotNull.message}")
  private Order.Type orderType;

  public String getUserId() {
    return userId;
  }

  public String getQuantity() {
    return quantity;
  }

  public String getPricePerKg() {
    return pricePerKg;
  }

  public Order.Type getOrderType() {
    return orderType;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public void setPricePerKg(String pricePerKg) {
    this.pricePerKg = pricePerKg;
  }

  public void setOrderType(Order.Type orderType) {
    this.orderType = orderType;
  }
}
