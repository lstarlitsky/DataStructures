package com.mit.sparseArray;

/**
 * 稀疏数组
 *
 * @author mit
 * @date 2021/6/29 下午12:55
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11*11
        int[][] chessArr1 = new int[11][11];
        // 0:表示没有棋子, 1:表示黑子, 2:表示蓝子
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        System.out.println("原始二维数组：");
        for (int[] row : chessArr1) {
            for (int i : row) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }

        // 将原始二维数组转换为稀疏数组
        int count = 0;
        for (int[] row : chessArr1) {
            for (int i : row) {
                if (i != 0) {
                    count++;
                }
            }
        }
        System.out.println("棋子总数: " + count);
        // 创建稀疏数组
        int[][] sparseArr = new int[count + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = count;
        int rowIndex = 1;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sparseArr[rowIndex][0] = i;
                    sparseArr[rowIndex][1] = j;
                    sparseArr[rowIndex][2] = chessArr1[i][j];
                    rowIndex++;
                }
            }
        }
        System.out.println("转换出的稀疏数组：");
        for (int[] row: sparseArr){
            System.out.printf("%d\t%d\t%d\n", row[0], row[1], row[2]);
        }


    }
}
