package com.xm.record;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 记录阿里一次笔试
 *
 * @author hongwan
 * @date 2023/3/15
 */
public class Ali {

    public static void main(String[] args) {
        System.out.println(step(8, 1));
        System.out.println(findLongest(Lists.newArrayList(1,2,6,3,4,4,4,5,5)));
        List<User> data = Lists.newArrayList();
        User user1 = new User();
        // 使用java8 stream 实现：
        // 按姓名去重(保留年龄最大的)，
        // 过滤掉名字中含有“马”字以及性别未知的人，
        // 并根据年龄段[(无穷小, 20], (20,30], (30,40], (40,50], (50,60], (60,70], (70,80], (80, 无穷大)]进行分类，
        // 计算每个年龄段的平均年龄，最后按照平均年龄升序输出 对象<年龄段，平均年龄>的列表
        data = data.stream()
                .collect(Collectors.groupingBy(User::getName, Collectors.maxBy(Comparator.comparingInt(User::getAge))))
                .values().stream()
                .map(Optional::get)
                .filter(u -> !u.getName().equals("马") && u.getGendar() != 0)
                .collect(Collectors.toList());
        Map<AgeGroup, Double> ageGroupDoubleMap = data.stream()
                .collect(Collectors.groupingBy(u -> getAgeGroupByAge(u.getAge()), Collectors.averagingInt(User::getAge)));
        List<Map.Entry<AgeGroup, Double>> ageList = new ArrayList<>(ageGroupDoubleMap.entrySet());
        ageList.sort(Map.Entry.comparingByValue());


        List<AbstractMap.SimpleEntry<String, Double>> collect = data.stream()
                // 按姓名去重（保留年龄最大的）
                .collect(Collectors.toMap(User::getName, Function.identity(), BinaryOperator.maxBy(Comparator.comparingInt(User::getAge))))
                .values().stream()
                // 过滤掉名字中含有“马”字以及性别未知的人
                .filter(person -> !person.getName().contains("马") && person.getGendar() != 0)
                // 根据年龄段进行分类，计算每个年龄段的平均年龄
                .collect(Collectors.groupingBy(person -> {
                    int age = person.getAge();
                    if (age <= 20) {
                        return "20岁以下";
                    } else if (age <= 30) {
                        return "20-30岁";
                    } else if (age <= 40) {
                        return "30-40岁";
                    } else if (age <= 50) {
                        return "40-50岁";
                    } else if (age <= 60) {
                        return "50-60岁";
                    } else if (age <= 70) {
                        return "60-70岁";
                    } else if (age <= 80) {
                        return "70-80岁";
                    } else {
                        return "80岁以上";
                    }
                }, Collectors.averagingInt(User::getAge)))
                // 按照平均年龄升序输出对象<年龄段，平均年龄>的列表
                .entrySet().stream()
                .sorted(Comparator.comparingDouble(entry -> Double.parseDouble(entry.getValue().toString().substring(0, entry.getValue().toString().indexOf(".")))))
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), Double.parseDouble(entry.getValue().toString())))
                .collect(Collectors.toList());
    }

    public static AgeGroup getAgeGroupByAge(int age) {
        return ageGroupList.stream()
                .filter(ageGroup -> age > ageGroup.minAge && age < ageGroup.maxAge)
                .findAny().orElse(null);
    }

    public static class AgeGroup {
        private int minAge;
        private int maxAge;

        public AgeGroup(int minAge, int maxAge) {
            this.minAge = minAge;
            this.maxAge = maxAge;
        }
    }

    public static final List<AgeGroup> ageGroupList = new ArrayList<>();

    static {
        ageGroupList.add(new AgeGroup(Integer.MIN_VALUE, 20));
        ageGroupList.add(new AgeGroup(20, 30));
        ageGroupList.add(new AgeGroup(30, 40));
        ageGroupList.add(new AgeGroup(40, 50));
        ageGroupList.add(new AgeGroup(50, 60));
        ageGroupList.add(new AgeGroup(60, 70));
        ageGroupList.add(new AgeGroup(70, 80));
        ageGroupList.add(new AgeGroup(80, Integer.MAX_VALUE));
    }


    @Setter
    @Getter
    public static class User {
        public int age;//年龄
        public String name;//姓名
        public short gendar;//性别，0未知，1男，2女

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public short getGendar() {
            return gendar;
        }

        public void setGendar(short gendar) {
            this.gendar = gendar;
        }
    }


    public static int step(int n, int m) {
        // dpi 表示迈上i级台阶的迈法
        int dp[] = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i - 1 >= 0) {
                dp[i] += dp[i - 1];
            }
            if (i - 2 >= 0) {
                dp[i] += dp[i - 2];
            }
            if (m != 1 && m != 2 && i - m >= 0) {
                dp[i] += dp[i - m];
            }
        }
        return dp[n];
    }

    public static List<Integer> findLongest(List<Integer> nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.isEmpty()) {
            return result;
        }
        int min = 0;
        int max = 1;
        int maxNum = 1;
        int cur = nums.get(0);
        int curNum = 1;
        int startIndex = 0;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) == cur) {
                curNum++;
                if (curNum > maxNum) {
                    maxNum = curNum;
                    min = startIndex;
                    max = i;
                }
            } else {
                cur = nums.get(i);
                startIndex = i;
                curNum = 1;
            }
        }
        return nums.subList(min, max + 1);
    }
}
