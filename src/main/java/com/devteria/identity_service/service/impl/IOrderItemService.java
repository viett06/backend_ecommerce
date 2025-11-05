package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.OrderItemRequest;
import com.devteria.identity_service.dto.request.OrderItemUpdateRequest;
import com.devteria.identity_service.dto.response.OrderItemResponse;
import com.devteria.identity_service.entity.Order;
import com.devteria.identity_service.entity.OrderItem;
import com.devteria.identity_service.entity.OrderItemId;
import com.devteria.identity_service.entity.Product;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.OrderItemMapper;
import com.devteria.identity_service.repository.OrderItemRepository;
import com.devteria.identity_service.repository.OrderRepository;
import com.devteria.identity_service.repository.ProductRepository;
import com.devteria.identity_service.service.OrderItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IOrderItemService implements OrderItemService {

    OrderItemRepository orderItemRepository;
    OrderRepository orderRepository;
    ProductRepository productRepository;
    OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderItemResponse addItemToOrder(Long orderId, OrderItemRequest orderItemRequest) {
        // Kiểm tra order tồn tại
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        // Kiểm tra product tồn tại
        Product product = productRepository.findById(orderItemRequest.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        // Kiểm tra item đã tồn tại trong order chưa
        if (orderItemRepository.existsByOrderItemIdOrderIdAndOrderItemIdProductId(orderId, orderItemRequest.getProductId())) {
            throw new AppException(ErrorCode.ORDER_ITEM_ALREADY_EXISTS);
        }

        // Tạo order item
        OrderItem orderItem = orderItemMapper.toOrderItem(orderItemRequest);
        OrderItemId orderItemId = new OrderItemId(orderId, orderItemRequest.getProductId());
        orderItem.setOrderItemId(orderItemId);
        orderItem.setOrder(order);
        orderItem.setProduct(product);

        orderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toOrderItemResponse(orderItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemResponse> getItemsByOrderId(Long orderId) {
        // Kiểm tra order tồn tại
        if (!orderRepository.existsById(orderId)) {
            throw new AppException(ErrorCode.ORDER_NOT_FOUND);
        }

        return orderItemRepository.findByOrderItemIdOrderId(orderId)
                .stream()
                .map(orderItemMapper::toOrderItemResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderItemResponse getOrderItem(Long orderId, Long productId) {
        OrderItem orderItem = orderItemRepository.findByOrderIdAndProductId(orderId, productId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND));
        return orderItemMapper.toOrderItemResponse(orderItem);
    }

    @Override
    @Transactional
    public OrderItemResponse updateOrderItem(Long orderId, Long productId, OrderItemUpdateRequest orderItemUpdateRequest) {
        OrderItem orderItem = orderItemRepository.findByOrderIdAndProductId(orderId, productId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND));

        orderItemMapper.updateOrderItemFromRequest(orderItem, orderItemUpdateRequest);
        orderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toOrderItemResponse(orderItem);
    }

    @Override
    @Transactional
    public void removeItemFromOrder(Long orderId, Long productId) {
        if (!orderItemRepository.existsByOrderItemIdOrderIdAndOrderItemIdProductId(orderId, productId)) {
            throw new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND);
        }

        OrderItemId orderItemId = new OrderItemId(orderId, productId);
        orderItemRepository.deleteById(orderItemId);
    }

    @Override
    @Transactional
    public void removeAllItemsFromOrder(Long orderId) {
        // Kiểm tra order tồn tại
        if (!orderRepository.existsById(orderId)) {
            throw new AppException(ErrorCode.ORDER_NOT_FOUND);
        }
        orderItemRepository.deleteByOrderItemIdOrderId(orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getOrderTotalAmount(Long orderId) {
        // Kiểm tra order tồn tại
        if (!orderRepository.existsById(orderId)) {
            throw new AppException(ErrorCode.ORDER_NOT_FOUND);
        }
        return orderItemRepository.getTotalAmountByOrderId(orderId)
                .orElse(BigDecimal.ZERO);
    }
}