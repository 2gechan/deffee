package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.presentation.dto.ChangeStateRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderRestController {

    private SimpleOrderService simpleOrderService;

    @Autowired
    public OrderRestController(SimpleOrderService simpleOrderService) {
        this.simpleOrderService = simpleOrderService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody List<OrderProductRequestDto> orderProductRequestDto) {
        OrderResponseDto orderResponseDto = simpleOrderService.createOrder(orderProductRequestDto);

        return ResponseEntity.ok(orderResponseDto);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable(name = "orderId") Long orderId) {
        OrderResponseDto orderResponseDto = simpleOrderService.findById(orderId);

        return ResponseEntity.ok(orderResponseDto);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.PATCH)
    public ResponseEntity<OrderResponseDto> changeOrderState(@PathVariable Long orderId, @RequestBody ChangeStateRequestDto changeStateRequestDto) {
        if (changeStateRequestDto.getState().equals("CREATED") ||
            changeStateRequestDto.getState().equals("SHIPPING") ||
            changeStateRequestDto.getState().equals("COMPLETED") ||
            changeStateRequestDto.getState().equals("CANCELED")) {

            OrderResponseDto orderResponseDto = simpleOrderService.changeState(orderId, changeStateRequestDto);

            return ResponseEntity.ok(orderResponseDto);
        }
        else {
            throw new RuntimeException("존재하지 않는 주문 상태 입니다.");
        }


    }

}
