package edu.monmouth.vehicle;

public class Airplane implements Vehicle {

    private PowerTypes powerSource;

    public Airplane(PowerTypes powerSource) {
        setPowerSource(powerSource);
    }

    public PowerTypes getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(PowerTypes powerSource) {
        this.powerSource = powerSource;
    }

    @Override
    public void go() {
        System.out.println("The airplane is flying");
    }

    @Override
    public void stop() {
        System.out.println("The airplane is grounded");
    }

    @Override
    public PowerTypes powerBy() {
        return getPowerSource();
    }

    @Override
    public String toString() {
        return "Airplane power source: " + powerSource;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Airplane other = (Airplane) obj;
        return powerSource == other.powerSource;
    }

   

    
    
    
    
    
    
    
}