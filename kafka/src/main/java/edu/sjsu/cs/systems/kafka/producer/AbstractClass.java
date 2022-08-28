package edu.sjsu.cs.systems.kafka.producer;

public abstract class AbstractClass {

    public void print() {
        System.out.println("AbstractClass:Print");
        display();
    }

    public void display() {
        System.out.println("AbstractClass:Display");
    }
}
