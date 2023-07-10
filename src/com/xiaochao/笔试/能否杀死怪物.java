package com.xiaochao.笔试;

/**
 * 为了毁灭人类，混沌亲王派出了n只怪物来到地球表面。每只怪物都有生命值health和力量power。
 * 你身为人类的救世主，每一次可以对所有活着的怪物造成initialAttack点伤害。换句话说，你可以减少所有怪物 initialAttack 点生命值(如果initialAttack>0)。然而，你每次攻击后，怪物都会前进，你的力量也会随着每次攻击随之减弱。减弱的值取决于每次活下来的力量值最小的怪物的力量值。
 * 你能否杀死所有的怪物?
 * Input
 * 每个测试用例的第一行包含一个整数 initialAttack ，代表你的初始攻击力。然后是两行，每行包含n个整数的数组 monsterHealths(怪物们的生命值)和 monsterPowers(怪物们的力量值)怪物的生命值和力量值严格大于0。怪物的生命值和力量值严格小于1000。
 * Output
 * 对于每个测试用例，返回答案一-如果你可以杀死所有怪物则返回true，否则返回false。
 * 输入
 * 7,[18,5,13,9,10,1]，[2,7,2,1,2,6]
 * 输出 true
 */

import java.util.Arrays;

/**
 * 为了解决这个问题，我们可以采用模拟的方法来实现。
 * 首先，我们可以对所有怪物按照力量值进行升序排序。这样，在每次攻击之后，我们只需要减去当前活着的怪物中力量值最小的那个怪物的力量值。接下来，我们可以进行以下步骤：
 * 对所有怪物按照力量值进行升序排序。
 * 初始化当前攻击力为 initialAttack。
 * 遍历排序后的怪物数组：
 * 对当前怪物造成当前攻击力的伤害。
 * 如果当前怪物的生命值小于等于0，说明它已经死亡，继续处理下一个怪物。
 * 如果当前怪物仍然活着，那么减去它的力量值，更新当前攻击力。
 * 如果当前攻击力小于等于0，说明无法继续攻击，返回 false。
 * 如果处理完所有怪物，说明可以杀死所有怪物，返回 true。
 */
public class 能否杀死怪物 {
    public static void main(String[] args) {
        int initialAttack = 7;
        int[] monsterHealths = {18, 5, 13, 9, 10, 1};
        int[] monsterPowers = {2, 7, 2, 1, 2, 6};
        System.out.println(canDefeatAllMonsters(initialAttack, monsterHealths, monsterPowers)); // 输出: true
    }
    public static boolean canDefeatAllMonsters(int initialAttack, int[] monsterHealths, int[] monsterPowers) {
        int[][] monsters = new int[monsterHealths.length][2];

        for (int i = 0; i < monsterHealths.length; i++) {
            monsters[i][0] = monsterHealths[i];
            monsters[i][1] = monsterPowers[i];
        }

        Arrays.sort(monsters, (m1, m2) -> Integer.compare(m1[1], m2[1]));

        int currentAttack = initialAttack;
        for (int[] monster : monsters) {
            int remainingHealth = monster[0] - currentAttack;
            if (remainingHealth > 0) {
                currentAttack -= monster[1];
                if (currentAttack <= 0) {
                    return true;
                }
            } else {
                currentAttack += monster[1];
            }
        }

        return false;
    }
}
