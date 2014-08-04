package org.hy.foundation.utils.text;

import org.apache.commons.lang.StringUtils;
import org.hy.foundation.utils.math.MathUtil;


/**
 * 计算两个字符串的相似程度，这一类的算法有很多，主要有编辑距离算法(Levenshtein Distance)、
 * 最长公共子串算法(CLS)、还有google的余弦算法。<br>
 * <ol>
 * 	<li>
 * 		编辑距离算法(Levenshtein Distance)<br>
 *  		编辑距离算法最先是由俄国科学家Levenshtein提出的，所以这个算法也叫做Levenshtein Distance算法。
 *  		用最简单的一句话来说明这个算法就是：通过插入、删除、替换方法将字符串A变成字符串B所有的步骤就是算法中提到的编辑距离，
 *  		最简单的相似度即编辑距离的倒数。
 *  	</li>
 * <ol>
 * 
 * <br><br>
 * 
 * Levenshtein Distance 例子<br>
 * ======================<br>
 * 例1：<br>
 * 字符串A：	abcde<br>
 * 字符串B：	abbde 	<br>
 * A -> B ：	将字符串B的b替换字符串A的c 	<br>
 * 编辑距离：	1<br>
 * <br><br>
 * 例2：<br>
 * 字符串A：	abcdefg<br>
 * 字符串B：	adcdbfg 	<br>
 * A -> B ：	将字符串A第二位上的b变为d，将第五位上的e变为b	<br>
 * 编辑距离：	2<br>
 * ======================<br>
 * 
 * Levenshtein Distance 算法步骤<br>
 * ======================<br>
 * <ol>
 * 	<li>
 *			获取字符串A和字符串B的长度，分为记为A_Length和B_Length
 * 		如果 A_Length = 0 则编辑距离为B_Length
 * 		如果 B_Length = 0 则编辑距离为A_Length
 * 		如果 A_Length != 0 且 B_Length != 0 那么构造一个(A_Length+1) x (B_Length+1)矩阵Matrix[A_Length+1,B_Length+1]
 * 	</li>
 * 	<li>
 * 		初始化矩阵的第一行和第一列，从0开始逐位递增<br>
 * 		0	1	2	3	.	B<br>
 * 		1	x	x	x	x	x<br>
 * 		2	x	x	x	x	x<br>
 * 		3	x	x	x	x	x<br>
 * 		.	x	x	x	x	x<br>
 * 		A	x	x	x	x	x<br>
 * 	</li>
 * 	<li>
 * 		从Matrix[1,1]开始逐位遍历矩阵Matrix
 * 	</li>
 * 	<li>
 * 		如果A[i] = B[j] 则编辑距离为0    如果A[i] != B[j] 则编辑距离为1
 * 	</li>
 * 	<li>
 * 		更新矩阵单元格Matrix[i,j]的值，算法如下：
 *     	a = Matrix[i-1,j] + 1    
 *     	b = Matrix[i,j-1] + 1    
 *     	c = Matrix[i-1,j-1] + 第四步骤中的编辑距离
 *         Matrix[i,j] = a,b,c中的最小值
 * 	</li>
 * 	<li>
 * 		重复上述从3-5的步骤，最后得到单元格Matrix[A_Length,B_Length]的值即从字符串A到字符串B的编辑距离
 * 	</li>
 * </ol>
 * ======================<br>
 * 
 * 代码实例：
 * String str1 = "引用引用2010十大不了了之抗议案"; 
 * String str2 = "引用2010十大不了了之抗议案(附?,?小者勿入)";
 * System.out.println("sim="+compute(str1, str2));
 * 
 * @date 2011-4-21
 * @author MipatchTeam#hepl
 */
public class StringSimilarUtil {

	// --------------------- 1. --------------------------------
	/**
	 * 计算编辑距离
	 */
	private static int levenshteinDistance(String str1, String str2) {
		int d[][]; // 矩阵
		int n = str1.length();
		int m = str2.length();
		int i; // 遍历str1的
		int j; // 遍历str2的
		char ch1; // str1的
		char ch2; // str2的
		int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) { // 初始化第一列
			d[i][0] = i;
		}
		for (j = 0; j <= m; j++) { // 初始化第一行
			d[0][j] = j;
		}
		for (i = 1; i <= n; i++) { // 遍历str1
			ch1 = str1.charAt(i - 1);
			// 去匹配str2
			for (j = 1; j <= m; j++) {
				ch2 = str2.charAt(j - 1);
				if (ch1 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}
				int[] x={d[i - 1][j] + 1, d[i][j - 1] + 1,d[i - 1][j - 1] + temp};
				// 左边+1,上边+1, 左上角+temp取最小
				d[i][j] = MathUtil.min(x);
			}
		}
		return d[n][m];
	}

	/**
	 * 计算相似度
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static double compute(String str1, String str2) {
		int ld = levenshteinDistance(str1, str2);
		return 1 - (double) ld / Math.max(str1.length(), str2.length());
	}
	
	/**
	 * 
	 * 
	 * @param srcStr
	 * @param length
	 * @param s
	 * @return
	 */
	public static String center(String srcStr,int length,String s){
		return StringUtils.center(" demoNumberUtils ", 31, "=");
	}
	
	// -----------------------------------------------------

}
