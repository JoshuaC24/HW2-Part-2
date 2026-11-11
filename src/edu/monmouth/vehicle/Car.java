package edu.monmouth.vehicle;


public class Car implements Vehicle {
    private int engineSize;
    private PowerTypes powerSource;

    public Car(PowerTypes powerSource, int engineSize) throws CarException {
        setPowerSource(powerSource);
        setEngineSize(engineSize);
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) throws CarException {
        if (engineSize < VehicleConstants.MINENGINESIZE ||
            engineSize > VehicleConstants.MAXENGINESIZE) {
            throw new CarException("Engine size has to be between "
            + VehicleConstants.MINENGINESIZE + " and " + VehicleConstants.MAXENGINESIZE + ".");
        }

        this.engineSize = engineSize;
    }

    @Override
    public void go() {
        System.out.println("The car is driving");
    }

    @Override
    public void stop() {
        System.out.println("The car is stopped");
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
        return "Car [Power Source: " + powerSource + ", Engine Size: " + engineSize + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Car other = (Car) obj;
        return engineSize == other.engineSize && powerSource == other.powerSource;
    }

    

    
    
    
    
    
    
    
    
}