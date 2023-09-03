package com.onehealth.trackingorder.service;

import java.util.List;
import com.onehealth.trackingorder.entity.TrackingOrder;

public interface TrackingOrderService {

	List<TrackingOrder> getAllTrackingOrders();

	TrackingOrder createTrackingOrder(TrackingOrder trackingOrder);

	TrackingOrder getTrackingOrderById(Long trackingId);

	TrackingOrder updateTrackingOrder(Long trackingId, TrackingOrder trackingOrder);

	void deleteTrackingOrder(Long trackingId);

	TrackingOrder getTrackingOrderByConsignmentNumber(String consignmentNumber);

	TrackingOrder getTrackingOrderByOrderId(Long orderId);

	TrackingOrder checkedExistOrNot(String consignmentNumber);

}
