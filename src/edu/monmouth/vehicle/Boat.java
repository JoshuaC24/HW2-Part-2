package edu.monmouth.vehicle;


public class Boat implements Vehicle {
    private int length;
    private PowerTypes powerSource;

    public Boat(PowerTypes powerSource, int length) throws BoatException {
        setPowerSource(powerSource);
        setLength(length);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) throws BoatException {
        if (length < VehicleConstants.MINLENGTH ||
            length > VehicleConstants.MAXLENGTH) {
            throw new BoatException("Boat length has to be between "
            + VehicleConstants.MINLENGTH + " and " + VehicleConstants.MAXLENGTH + ".");
        }

        this.length = length;
    }

    @Override
    public void go() {
        System.out.println("The boat is sailing");
    }

    @Override
    public void stop() {
        System.out.println("The boat is docked");
    }

    @Override
    public PowerTypes powerBy() {
        return getPowerSource();
    }

    public void setPowerSource(PowerTypes powerSource) {
        this.powerSource = powerSource;
    }

    public PowerTypes getPowerSource() {
        return powerSource;
    }

    @Override
    public String toString() {
        return "Boat [Power Source: " + powerSource + ", Boat Length: " + length + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Boat other = (Boat) obj;
        return length == other.length && powerSource == other.powerSource;
    }

    

    
    
    
    
    
    
    
    
    
}