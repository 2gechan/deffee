package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.exception.CanNotCancellableStateException;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;

import java.util.List;

public class Order {
    private Long id;
    private List<Product> orderedProducts;
    private Integer totalPrice;
    private String state;

    public void changeStateForce(String state) {
        this.state = state;
    }

    public Boolean sameId(Long id) {
        return this.id.equals(id);
    }

    private Integer calculateTotalPrice(List<Product> orderedProducts) {
        return orderedProducts.stream()
                .mapToInt(orderedProduct -> orderedProduct.getPrice() * orderedProduct.getAmount())
                .sum();
    }

    public Order(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
        this.totalPrice = calculateTotalPrice(orderedProducts);
        this.state = "CREATED";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public String getState() {
        return state;
    }

    public boolean sameState(String state) {
        return this.state.equals(state);
    }

    public void cancel() {
        if (!this.state.equals("CREATED")) throw new CanNotCancellableStateException("이미 취소되었건 취소할 수 없는 주문상태입니다.");

        this.state = "CANCELED";
    }
}
