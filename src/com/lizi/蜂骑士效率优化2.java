package com.lizi;

/**
 * 题目描述：
 * 在便利蜂的外卖业务中，每位蜂骑士会负责一片区域的多家便利蜂门店，每一家门店总有路线可以到达。
 *
 * 为了提高蜂骑士的效率，小蜂决定将部分门店间的道路规划为蜂骑士畅享道。
 *
 * 进行规划前，小蜂向蜂骑士搜集了某个区域所有门店之间的道路和道路的长度。
 *
 * 请聪明机智的你帮小蜂找出连接这些门店最少需要几条路，这些路的总长度之和最短为多少。
 * 输入描述
 * 第一行：输入一个数字n，代表有n个门店
 * 之后有n行，每行有n个数字，每个数字代表第i家门店到第j家门店的距离，如果第i家门店与第j家门店不可达，则输入-1，第i家门店到自己的距离为0
 * 其中，0 <= n <= 100，-1 <= 距离 <= 10000
 * 输出描述
 * 输出两个数字，用空格隔开。第一个数字代表连通所有门店所需的路的总个数，第二个数字代表这些路加起来的总长度。
 * 样例输入
 * 2
 * 0 4
 * 4 0
 * 样例输出
 * 1 4
 * 提示
 * 样例 2：
 * 输入：
 * 3
 * 0 -1 4
 * -1 0 2
 * 4 2 0
 * 输出：
 * 2 6
 */
public class 蜂骑士效率优化2 {

}
