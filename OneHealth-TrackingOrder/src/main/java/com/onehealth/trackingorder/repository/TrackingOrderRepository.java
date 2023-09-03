package com.onehealth.trackingorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onehealth.trackingorder.entity.TrackingOrder;

public interface TrackingOrderRepository extends JpaRepository<TrackingOrder, Long> {

	TrackingOrder findByConsignmentNumber(String consignmentNumber);

	TrackingOrder findByOrderId(Long orderId);

}
