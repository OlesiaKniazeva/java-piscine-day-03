package tt;


public class train {
    private static final int NACCOUNTS = 100;
    private static final double INITIAL_BALANCE = 1000;
    private static final double MAX_AMOUNT = 1000;
    private static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);

        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccounts = i;

            Runnable r = () ->
            {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccounts, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    System.out.println("Catched");
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }

}
