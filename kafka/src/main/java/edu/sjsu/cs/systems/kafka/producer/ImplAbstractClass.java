package edu.sjsu.cs.systems.kafka.producer;

public class ImplAbstractClass extends AbstractClass {

    public void display() {
        System.out.println("ImplAbstractClass:Display");
    }

    public static void main(String[] args) {
        ImplAbstractClass implAbstractClass = new ImplAbstractClass();
        implAbstractClass.print();
    }

}
