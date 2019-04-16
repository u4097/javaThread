package com.company;

public class Main {

  public static void main(String[] args) {
    System.out.println("Main thread started...");
    CommonResource commonResource = new CommonResource();
    for (int i = 1; i < 6; i++) {
      Thread t = new Thread(new CountThread(commonResource));
      t.setName("Thread " + i);
      t.start();
    }
    System.out.println("Main thread finished...");
  }
}

class CommonResource {

  int x = 0;
}

class CountThread implements Runnable {

 private final CommonResource res;

  CountThread(CommonResource res) {
    this.res = res;
  }


  @Override
  public void run() {
    synchronized (res) {
      res.x = 1;
      System.out.printf("%s started... \n", Thread.currentThread().getName());
      for (int i = 1; i < 5; i++) {
        System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
        res.x++;
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          System.out.println(e);
        }
      }
    }
    System.out.printf("%s finished... \n", Thread.currentThread().getName());
  }
}

