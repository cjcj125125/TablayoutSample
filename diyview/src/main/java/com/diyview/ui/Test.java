package com.diyview.ui;


import java.util.Scanner;

/**
 * Created by Right on 2017/3/30.
 */

public class Test {
    public static void main(String[] args) {
//        Integer score[] = {67, 69, 75, 87, 89, 90, 99, 100,99,95};
//        List list = Arrays.asList(score);
//        Set set=new HashSet(list);
//        Iterator<Integer>it=set.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next()+"------");
//        }
//      List<Integer>list1=new ArrayList<>();
//        list1.addAll(set);
//        Collections.sort(list1);
//        for(Integer integer:list1){
//            System.out.print("排序："+integer);
//        }

//        for (int i = 0; i < score.length - 1; i++) {    //最多做n-1趟排序
//            for (int j = 0; j < score.length - i - 1; j++) {    //对当前无序区间score[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
//                if (score[j] < score[j + 1]) {    //把小的值交换到后面
//                    int temp = score[j];
//                    score[j] = score[j + 1];
//                    score[j + 1] = temp;
//                }
//            }
//            System.out.print("第" + (i + 1) + "次排序结果：");
//            for (int a = 0; a < score.length; a++) {
//                System.out.print(score[a] + "\t");
//            }
//            System.out.println("");
//        }
//        System.out.print("最终排序结果：");
//        for (int a = 0; a < score.length; a++) {
//            System.out.print(score[a] + "\t");
//        }

        ra2an();

    }
    public static void ra2an() {
        System.out.println("1、角度转为弧度\n2、弧度转换为角度");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("输入角度:");
                Scanner scanner1 = new Scanner(System.in);
                double radian = scanner1.nextDouble() * (Math.PI / 180);
                System.out.println("输入角度:" + scanner1.nextDouble() + "---转换为弧度为:" + radian);
                break;
            case 2:
                System.out.println("输入弧度:");
                Scanner scanner2 = new Scanner(System.in);
                double angle = scanner2.nextDouble() * (180 / Math.PI);
                System.out.println("输入弧度:" + scanner2.nextDouble() + "---转换为角度为:" + angle);
                break;
            default:
                System.out.println("输入有误，请重试！");
                ra2an();
                break;
        }
        ra2an();
    }

}
