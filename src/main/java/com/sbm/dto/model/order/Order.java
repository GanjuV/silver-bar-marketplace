package com.sbm.dto.model.order;

import java.util.Objects;

/**
 * Order class with properties.
 * @author HCL
 */
public class Order {

  public enum Type { BUY, SELL }
  String userId;
  String quantity;
  String  pricePerKg;
  Order.Type orderType;

  /**
   * Get UserId.
   * @return String
   */
  public String getUserId() {
    return userId;
  }
  /**
   * Get Quantity.
   * @return String
   */
  public String getQuantity() {
    return quantity;
  }
  /**
   * Get PricePerKg.
   * @return String
   */
  public String getPricePerKg() {
    return pricePerKg;
  }
  /**
   * Get OrderType.
   * @return String
   */
  public Type getOrderType() {
    return orderType;
  }
  /**
   * Get UserId.
   * @param userId
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }
  /**
   * Get Quantity.
   * @param quantity
   */
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }
  /**
   * Get PricePerKg.
   * @param pricePerKg
   */
  public void setPricePerKg(String pricePerKg) {
    this.pricePerKg = pricePerKg;
  }
  /**
   * Get OrderType.
   * @param orderType
   */
  public void setOrderType(Type orderType) {
    this.orderType = orderType;
  }

  /**
   * To String converting object to string.
   * @return String
   */
  @Override
  public String toString() {
    return "Order{" +
      "userId='" + userId + '\'' +
      ", quantity='" + quantity + '\'' +
      ", pricePerKg=" + pricePerKg +
      ", orderType=" + orderType +
      '}';
  }

  /**
   * Equals method comparing two object.
   * @return boolean
   * @param o
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return userId.equals(order.userId) &&
      quantity.equals(order.quantity) &&
      pricePerKg.equals(order.pricePerKg) &&
      orderType == order.orderType;
  }

  /**
   * Hashcode of object.
   * @return int
   */
  @Override
  public int hashCode() {
    return Objects.hash(userId, quantity, pricePerKg, orderType);
  }
}
