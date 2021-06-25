package lesson12;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int SIZE = 10000000;
        final int HALF = SIZE/2;

        float[] myArr = new float[SIZE];
        for (float i = 0; i < myArr.length; i++) {
            myArr[(int) i] = 1.00f;
        }
        method1(myArr);
        method2(myArr, HALF);

    }

    static void method1(float[] myArr) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = (float)(myArr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    static void method2(float[] myArr, int half) throws InterruptedException {
        long a = System.currentTimeMillis();
        float[] myArr1 = new float[half];
        float[] myArr2 = new float[myArr.length - myArr1.length];

        System.arraycopy(myArr, 0, myArr1, 0, half);
        System.arraycopy(myArr, half, myArr2, 0, half);

        Thread helpThread = new Thread(() -> {
            for (int i = 0; i < myArr1.length; i++) {
                myArr1[i] = (float)(myArr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        helpThread.start();
        for (int i = 0; i < myArr2.length; i++) {
            myArr2[i] = (float)(myArr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

            helpThread.join();
        System.arraycopy(myArr1, 0, myArr, 0, half);
        System.arraycopy(myArr2, 0, myArr, half, half);
        System.out.println(System.currentTimeMillis() - a);
    }

}
