package com.onehealth.pharmainventory.exception;

public class RecordNotFoundException extends InventoryException {
	 
	private static final long serialVersionUID = 1L;
	 
    public RecordNotFoundException(String message) {
        super(message);
    }
}
