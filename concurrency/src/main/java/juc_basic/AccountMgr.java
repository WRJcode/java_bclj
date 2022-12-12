package juc_basic;

import java.util.Random;

public class AccountMgr {
    public static class NoEnoughMoneyException extends Exception{};
    public static boolean tryTransfer(Account from, Account to, double money)
            throws NoEnoughMoneyException {
        if(from.tryLock()) {
            try {
                if(to.tryLock()) {
                    try {
                        if(from.getMoney() >= money) {
                            from.reduce(money);
                            to.add(money);
                        } else {
                            throw new NoEnoughMoneyException();
                        }
                        return true;
                    } finally {
                        to.unlock();
                    }
                }
            } finally {
                from.unlock();
            }
        }
        return false;
    }

    public static void simulateDeadLock() {
        final int accountNum = 10;
        final Account[] accounts = new Account[accountNum];
        final Random rnd = new Random();
        for(int i = 0; i < accountNum; i++) {
            accounts[i] = new Account(rnd.nextInt(10000));
        }
        int threadNum = 100;
        Thread[] threads = new Thread[threadNum];
        for(int i = 0; i < threadNum; i++) {
            threads[i] = new Thread() {
                public void run() {
                    int loopNum = 100;
                    for(int k = 0; k < loopNum; k++) {
                        int i = rnd.nextInt(accountNum);
                        int j = rnd.nextInt(accountNum);
                        int money = rnd.nextInt(10);
                        if(i != j) {
                            try {
                                tryTransfer(accounts[i], accounts[j], money);
                            } catch (NoEnoughMoneyException e) {
                            }
                        }
                    }
                }
            };
            threads[i].start();
        }
    }

    public static void main(String[] args) {
        simulateDeadLock();
    }
}
