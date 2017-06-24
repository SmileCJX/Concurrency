/**
 * 循环次数count分别改成10000,100000,1000000，10000000,100000000
 * 当并发执行累加操作不超过百万次时，速度会比串行执行累加操作要慢。
 * 原因是线程有创建和上下文切换的开销。
 */
public class ConcurrencyTest {

    public static final long count = 100000001;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++){
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++){
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for(long i = 0; i < count; i++){
            a += 5;
        }
        int b = 0;
        for(long i = 0; i < count; i++){
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }
}
