package com.lind.bookshop.utils;

import com.lind.bookshop.util.BooleanMutex;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 互斥锁测试.
 */
public class MutexTest {
  private static final Logger logger = LoggerFactory.getLogger(MutexTest.class);

  @Test
  public void lockOneThread() throws Exception {
    Timer timer = new Timer();
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        timer.add("zzl");
      }).start();
    }
    Thread.sleep(1000);
  }

  @Test
  public void lockMutexTest() throws Exception {
    BooleanMutex mutex = new BooleanMutex(true);
    int numTotal = 0;

    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        try {
          mutex.get(); //当前状态为true, 不会被阻塞
          mutex.set(false);
          System.out.println("你是第使用timer的线程" + Thread.currentThread().getId());
          mutex.set(true);
        } catch (InterruptedException e) {
          e.getStackTrace();
        }
      }).start();
    }
    Thread.sleep(1000);
  }

  static class Timer {
    private static int num = 0;

    public void add(String name) {
      synchronized (this) {
        num++;
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.getStackTrace();
        }
        System.out.println(name + ":你是第" + num + "使用timer的线程");
      }
    }
  }
}
