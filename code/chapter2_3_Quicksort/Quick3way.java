package chapter2_3_Quicksort;

import algs4.StdOut;
import algs4.StdRandom;

/**
 * @author huang
 * 三向切分的快速排序
 */
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
public class Quick3way {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);    // 消除对输入的依赖
        sort(a,0, a.length - 1);
    }
    
    private static void sort(Comparable[] a, int lo, int hi) {
        if(hi <= lo)
            return;
        int lt = lo, i = lo+1, gt = hi;     // lt: b 开始, gt: b 结束, i: c 开始
        Comparable v = a[lo];
        while(i <= gt) {
            int cmp = a[i].compareTo(v);
            if(cmp < 0)
                exch(a, lt++, i++);
            else if(cmp > 0)
                exch(a, i, gt--);
            else 
                i++;
        }    // 现在 a[lo .. lt-1] < v = a[lt .. gt] < a[gt+1 .. hi] 成立
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
    
    private static int partition(Comparable[] a, int lo, int hi) {
        // 将数组切分为 a[lo .. i-1]，a[i]，a[i+1 .. hi]
        int i = lo, j = hi+1;    // 左右扫描指针
        Comparable v = a[lo];    // 切分元素
        while(true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while(less(a[++i], v))
                if(i == hi)
                    break;
            while(less(v, a[--j]))
                if(j == lo)
                    break;
            if(i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    
    private static boolean less(Comparable v, Comparable w) {
        // 对元素进行比较
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        // 将元素交换位置
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        // 在单行中打印数组
        for(int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        // 测试数组元素是否有序
        for(int i = 1; i < a.length; i++)
            if(less(a[i], a[i - 1]))
                return false;
        return true;
    }
}
