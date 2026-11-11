package edu.monmouth.hw1;

import edu.monmouth.vehicle.Vehicle;

public class VehicleList {
    private VehicleNode head;

    
    public VehicleList() {
        head = null;
    }

    
    
    public void insert(Vehicle vehicle) {
        VehicleNode newNode = new VehicleNode(vehicle);
        newNode.setNext(head);
        head = newNode;
    }

    
    
    public Vehicle first() {
        
    	if (head == null) {
            return null;
        }

        return head.getVehicle();
    }

    
    
    
    
    public Vehicle last() {
       
    	if (head == null) {
            return null;
        }

        VehicleNode current = head;

        while (current.getNext() != null) {
            current = current.getNext();
        
        }

        return current.getVehicle();
    }

    
    
    
    
    public Vehicle removeFirst() {
        
    	if (head == null) {
            return null;
        }

        Vehicle vehicle = head.getVehicle();
        head = head.getNext();

        return vehicle;
    }

    
    
    
    public void insertEnd(Vehicle vehicle) {
        VehicleNode newNode = new VehicleNode(vehicle);

        if (head == null) {
            head = newNode;
            return;
        }

        VehicleNode current = head;

        while (current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(newNode);
    }

    
    
    
    public boolean isEmpty() {
        return head == null;
    }

    
    
    public int size() {
        int count = 0;
        VehicleNode current = head;

        while (current != null) {
            count++;
            current = current.getNext();
        }

        return count;
    }

    
    
    @Override
    public String toString() {
        String result = "";
        VehicleNode current = head;

        while (current != null) {
            result += current.getVehicle() + "\n";
            current = current.getNext();
        }

        if (result.equals("")) {
            return "The vehicle list is empty.";
        }

        return result;
   
    }
    
    
    
    
    
    
    
    
    
}