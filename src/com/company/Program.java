package com.company;

public class Program {

  public static void main(String[] args) {

    Store store = new Store();
    Producer producer = new Producer(store);
    Consumer consumer = new Consumer(store);
    new Thread(producer).start();
    new Thread(consumer).start();
  }
}

class Store {

  private int product = 0;

  synchronized void get() {
    while (product < 1) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println(e);
      }
      product--;
      System.out.println("Покупатель купил 1 товар");
      System.out.println("Товаров на складе: " + product);
      notify();
    }
  }

  synchronized void put() {
    while (product >= 3) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println(e);
      }
    }
    product++;
    System.out.println("Производитель добавил 1 товар");
    System.out.println("Товаров на складе: " + product);
    notify();
  }
}

// Производитель
class Producer implements Runnable {

  Store store;

  Producer(Store store) {
    this.store = store;
  }

  @Override
  public void run() {
    for (int i = 1; i < 6; i++) {
      store.put();
    }

  }
}

// Покупатель
class Consumer implements Runnable {

  Store store;

  Consumer(Store store) {
    this.store = store;
  }

  @Override
  public void run() {
    for (int i = 1; i < 6; i++) {
      store.get();
    }
  }
}

