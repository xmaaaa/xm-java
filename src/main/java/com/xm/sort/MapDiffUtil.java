/*
 * MapDiffUtil.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.xm.sort;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 输出的校验，Json反序列化的检查，后续再做扩展
 *
 * @author hongwan
 */
public class MapDiffUtil {

    private static String commonIgnoreKeysString = "id,generatedTime,previewImg,topId,parentId,quotationItemId,userDefinedId,shell";
    private static String editorIgnoreKeysString = "modelIdRelation";
    private static List<String> CommonIgnoreKeys = Arrays.asList(commonIgnoreKeysString);
    private static List<String> EditorIgnoreKeys = Arrays.asList(editorIgnoreKeysString);
    protected static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        // 用ossUrl查出来的json
        String s1 = "{}";
        String s2 = "{}";
        CheckDiffInfoResult result = checkJson(s1, s2, "", Maps.newHashMap());
        System.out.println(MAPPER.writeValueAsString(result));
    }

    /**
     * 比较json
     *
     * @param expect
     * @param real
     * @param ignoreKeys
     * @param ignoreKVs
     * @return
     * @throws Exception
     */
    public static CheckDiffInfoResult checkJson(final String expect, final String real, String ignoreKeys, Map<String, Map<Object, List<Object>>> ignoreKVs) throws Exception {
        try {
            final Map expectMap = MAPPER.readValue(expect, SortedMap.class);
            final Map realMap = MAPPER.readValue(real, SortedMap.class);

            final MapDiffInfo diff = MapDiffUtil.diff(expectMap, realMap, ignoreKeys, ignoreKVs);

            if (!diff.isSame()) {
                System.out.println("expectMap");
                String expectdiff = printTest(diff, expectMap);
                String realdiff = printTest(diff, realMap);
                //提取出modeid关键信息
                Map<String, Object> expectmap = new HashMap<>();
                Map<String, Object> realmap = new HashMap<>();
                if (!StringUtils.isEmpty(expectdiff)) {
                    expectmap = MAPPER.readValue(expectdiff, expectmap.getClass());
                    realmap = MAPPER.readValue(realdiff, realmap.getClass());
                    StringBuffer expectInfo = new StringBuffer();
                    if (expectmap.containsKey("id")) {
                        expectInfo.append((String) expectmap.get("id"));
                        expectInfo.append("|").append((String) expectmap.get("parentId"));
                        expectInfo.append("|").append((String) expectmap.get("obsBrandGoodId"));
                        expectInfo.append("|").append((String) expectmap.get("modelName"));
                    }
                    StringBuffer realInfo = new StringBuffer();
                    if (realmap.containsKey("id")) {
                        realInfo.append((String) realmap.get("id"));
                        realInfo.append("|").append((String) realmap.get("parentId"));
                        realInfo.append("|").append((String) realmap.get("obsBrandGoodId"));
                        realInfo.append("|").append((String) realmap.get("modelName"));
                    }

                    diff.getDifferences().put("expectdiff", expectInfo);

                    diff.getDifferences().put("realdiff", realInfo);
                }
            }

            CheckDiffInfoResult result = new CheckDiffInfoResult();
            result.setSame(diff.isSame());
            Map<String, String> diffMap = new TreeMap<>();
            diffMap.put("Differences", JSON.toJSONString(diff.getDifferences()));
            if (diff.getOnlyOnLeft() != null) {
                diffMap.put("OnlyOnLeft", subStringValue(MAPPER.writeValueAsString(diff.getOnlyOnLeft()), 500));
            }

            if (diff.getOnlyOnRight() != null) {

                diffMap.put("OnlyOnRight", subStringValue(MAPPER.writeValueAsString(diff.getOnlyOnRight()), 500));
            }
            result.setDifferences(MAPPER.writeValueAsString(diffMap));
            result.setDiffKeyPath(Joiner.on(".").join(diff.getDiffKeyPath()));
            return result;
        } catch (final Exception e) {
            throw new Exception("compare failed! 错误信息：" + e.getMessage());
        }
    }

    private static String subStringValue(String value, int length) {
        if (value.length() > length) {
            return value.substring(0, length) + "...";
        } else {
            return value;
        }
    }

    private static String printTest(MapDiffInfo diff, Map expectMap) throws Exception {
        String res;
        try {
            Object result = expectMap;

            for (int i = 0; i < diff.getDiffKeyPath().size() - 1; i = i + 2) {
                String key = (String) diff.getDiffKeyPath().get(i);
                String next = i + 1 > diff.getDiffKeyPath().size() ? null : (String) diff.getDiffKeyPath().get(i + 1);
                // 0,2,4,6
                if (result instanceof Map) {

                    if ((key.contains("paramModel")
                            || key.contains("subModels")
                            || key.contains("quotationItems")
                            || key.contains("textures")
                            || key.contains("models")
                            || key.contains("resource")
                            || key.contains("assemblyModels"))
                            && !"paramModelIds".equals(key)) {
                        result = ((Map) result).get(key);
                    } else {
                        break;
                    }
                }

                if (result == null) {
                    System.out.println("oh on");
                }

                if (next == null) {
                    return null;
                }
                if (next.contains("size()")) {
                    List<String> fields = Arrays.asList("id", "modelName");
                    System.out.println("NOTE:\n\n\n" + JSONObject.toJSONString(result, createOnlyPropertyFilter(fields)) + "\n\n\n");
                    return null;
                }
                // 1,3,5,7,9
                if (result instanceof ArrayList) {
                    String index;
                    if (next.startsWith("[")) {
                        index = next.replace("[", "").replace("]", "");
                    } else if (key.startsWith("[")) {
                        index = key.replace("[", "").replace("]", "");
                    } else {
                        System.out.println("oh on");
                        continue;
                    }
                    if (!NumberUtils.isParsable(index)) {
                        System.out.println("oh on");
                        continue;
                    }
                    result = ((ArrayList) result).get(Integer.parseInt(index));
                } else if (result instanceof LinkedHashMap) {
                    result = ((LinkedHashMap) result).get(next);
                }
            }
            List<String> fields = Arrays.asList("subModels", "ignoreParameters", "billOutput");
            res = JSONObject.toJSONString(result, createPropertyFilter(fields));

            System.out.println("NOTE:\n\n\n" + JSONObject.toJSONString(result, createPropertyFilter(fields)) + "\n\n\n");
            return res;
        } catch (Throwable e) {
            throw new Exception("get diff falied", e);
        }
    }

    private static PropertyFilter createOnlyPropertyFilter(List<String> fields) {
        return (source, name, value) -> {
            for (String field : fields) {
                if (field.equals(name)) {
                    return true;
                }
            }
            return false;
        };
    }

    private static PropertyFilter createPropertyFilter(List<String> fields) {
        return (source, name, value) -> {
            for (String field : fields) {
                if (field.equals(name)) {
                    return false;
                }
            }
            return true;
        };
    }

    /**
     * 比较入口方法
     * 对比两个Map的不同
     * 1.检查到一个不同即退出
     * 2.left  right 暂时支持sortedMap
     *
     * @param left
     * @param right
     * @param <K>
     * @param <V>
     * @return
     */
    private static <K, V> MapDiffInfo<K, V> diff(
            final Map<K, V> left, final Map<K, V> right, String ignoreKeys, Map<String, Map<Object, List<Object>>> ignoreKVs) throws IOException {
        if (left == null || right == null) {
            throw new NullPointerException();
        }
        final MapDiffInfo<K, V> kvMapDiffInfo = new MapDiffInfo<>();
        kvMapDiffInfo.setIgnoreKeys(ignoreKeys);
        kvMapDiffInfo.setIgnoreKVs(ignoreKVs);
        //init only on right
        //现将值set到OnlyOnRight中，innerDiff方法中会在确定两边都有key之后从OnlyOnRight中移除
        kvMapDiffInfo.getOnlyOnRight().putAll(right);
        List<K> leftKeys = new ArrayList<>();
        for (K key : left.keySet()) {
            leftKeys.add(key);
        }
        innerDiff(left, right, kvMapDiffInfo, leftKeys);
        //清理没有比较的剩余数据以及需要过滤的字段
        for (K leftKey : leftKeys) {
            if (kvMapDiffInfo.getOnlyOnRight().containsKey(leftKey)
                    || ignoreKeys.contains(String.valueOf(leftKey))) {
                kvMapDiffInfo.getOnlyOnRight().remove(leftKey);
            }
        }
        List<K> needRemoveRightKeys = new ArrayList<>();
        for (K rightKey : kvMapDiffInfo.getOnlyOnRight().keySet()) {
            if (ignoreKeys.contains(String.valueOf(rightKey))) {
                needRemoveRightKeys.add(rightKey);
            }
        }
        for (K needRemoveRightKey : needRemoveRightKeys) {
            kvMapDiffInfo.getOnlyOnRight().remove(needRemoveRightKey);
        }

        return kvMapDiffInfo;
    }

    private static <K, V> void innerDiff(
            final Map<K, V> left,
            final Map<K, V> right,
            final MapDiffInfo<K, V> mapDiffInfo,
            List<K> leftAllKeys) throws IOException {
        for (final Map.Entry<? extends K, ? extends V> entry : left.entrySet()) {
            final K leftKey = entry.getKey();
            final V leftValue = entry.getValue();
            if (right.containsKey(leftKey)) {
                //remove only on right
                final V rightValue = mapDiffInfo.getOnlyOnRight().remove(leftKey);
                leftAllKeys.remove(leftKey);
                final boolean hasDiff = checkHasDiffValue((String) leftKey, leftKey.toString(),
                        leftValue, rightValue, mapDiffInfo);
                if (hasDiff) {
                    break;
                }
            } else {
                mapDiffInfo.getOnlyOnLeft().put(leftKey, leftValue);
            }
        }
    }

    /**
     * @param key         检查的Key
     * @param keyName     检查用来记录的Key的名称
     * @param leftValue
     * @param rightValue
     * @param mapDiffInfo
     * @param <K>
     * @param <V>
     * @return has diff
     */
    private static <K, V> boolean checkHasDiffValue(
            final String key,
            final String keyName,
            Object leftValue,
            Object rightValue,
            final MapDiffInfo<K, V> mapDiffInfo) throws IOException {

        if (leftValue == null && rightValue == null) {
            return false;
        }
        leftValue = rebuildData(keyName, leftValue);
        rightValue = rebuildData(keyName, rightValue);
        //新增CheckUtil.isIgnoreKey方法，用于过滤不想要校验的key
        if (isIgnoreKey(key, mapDiffInfo)) {
            return false;
        }
        //判断是否为需要忽略的KV值
        Map<Object, List<Object>> kv = new HashMap<>();
        if (mapDiffInfo.getIgnoreKVs() != null) {
            kv = mapDiffInfo.getIgnoreKVs().get(key);
        }
        if (kv != null) {
            if (leftValue instanceof Double) {
                BigDecimal left = new BigDecimal(String.valueOf(leftValue));
                BigDecimal right = rightValue == null ? null : new BigDecimal(String.valueOf(rightValue));
                if (kv.containsKey(left)
                        && (left.equals(right) || kv.get(left).contains(new BigDecimal(String.valueOf(rightValue))))) {
                    return false;
                }
            } else {
                if (kv.containsKey(leftValue) && kv.get(leftValue).contains(rightValue)) {
                    return false;
                }
            }

        }
        mapDiffInfo.getDiffKeyPath().addLast(keyName);

        if ((leftValue == null && rightValue != null) ||
                (leftValue != null && rightValue == null)) {
            final Pair<Object, Object> diffValue = Pair.of(leftValue, rightValue);
            mapDiffInfo.getDifferences().put(key, diffValue);
            return true;
        }

        //List.class
        if (List.class.isAssignableFrom(leftValue.getClass())) {
            return listCheck(key, (ArrayList) leftValue, (ArrayList) rightValue, mapDiffInfo);
        }
        //LinkedHashMap.class
        else if (LinkedHashMap.class.isAssignableFrom(leftValue.getClass())) {
            LinkedHashMap newLeftValue = (LinkedHashMap) leftValue;
            LinkedHashMap newRightValue = (LinkedHashMap) rightValue;
            return mapCheck(key, newLeftValue, newRightValue, mapDiffInfo);
        }
        //JSONString
        else if (leftValue instanceof String && JSONObject.isValidObject(leftValue.toString())) {
            Map leftJsonValue = MAPPER.readValue(leftValue.toString(), SortedMap.class);
            Map rightJsonValue = MAPPER.readValue(rightValue.toString(), SortedMap.class);
            return hashMapCheck(key, leftJsonValue, rightJsonValue, mapDiffInfo);
        }
        //基础string
        else if (leftValue instanceof String) {
            final boolean checkOk = StringUtils.equals((String) leftValue, (String) rightValue);
            if (!checkOk) {
                //return when find on diff
                final Pair<Object, Object> diffValue = Pair.of(leftValue, rightValue);
                mapDiffInfo.getDifferences().put(key, diffValue);
                return true;
            } else {
                mapDiffInfo.getDiffKeyPath().removeLast();
                return false;
            }
        } else {
            //float类型精度取舍后比较,修改成6位
            if (leftValue instanceof Double) {
                BigDecimal left = new BigDecimal(String.valueOf(leftValue))
                        .setScale(4, BigDecimal.ROUND_HALF_UP);
                BigDecimal right = new BigDecimal(String.valueOf(rightValue))
                        .setScale(4, BigDecimal.ROUND_HALF_UP);
                if (left.equals(right) || isEqualAngle(left, right)) {
                    mapDiffInfo.getDiffKeyPath().removeLast();
                    return false;
                } else {
                    //return when find on diff
                    final Pair<Object, Object> diffValue = Pair.of(left, right);
                    mapDiffInfo.getDifferences().put(key, diffValue);
                    return true;
                }
            }
            //其他类型 包含8种基础类型通过equals判断
            if (Objects.equals(leftValue, rightValue)) {
                mapDiffInfo.getDiffKeyPath().removeLast();
                return false;
            } else {
                //return when find on diff
                final Pair<Object, Object> diffValue = Pair.of(leftValue, rightValue);
                mapDiffInfo.getDifferences().put(key, diffValue);
                return true;
            }
        }
    }

    private static Boolean isIgnoreKey(String key, MapDiffInfo mapDiffInfo) {
        List<String> requestIgnoreKeys;
        if (mapDiffInfo.getIgnoreKeys() != null) {
            requestIgnoreKeys = Arrays.asList(mapDiffInfo.getIgnoreKeys().split(","));
            if (requestIgnoreKeys.size() > 0) {
                return getAllIgnoreKeys().contains(key) || requestIgnoreKeys.contains(key);
            }
        }
        return getAllIgnoreKeys().contains(key);
    }

    private static List<String> getAllIgnoreKeys() {
        List<String> allKeys = new ArrayList<>(CommonIgnoreKeys);
        allKeys.addAll(EditorIgnoreKeys);
        return allKeys;
    }

    private static boolean isEqualAngle(BigDecimal left, BigDecimal right) {
        if (left.doubleValue() > 360 || right.doubleValue() > 360
                || left.doubleValue() < -360 || left.doubleValue() < -360) {
            return false;
        }
        if ((left.doubleValue() + Math.PI) % Math.PI - (right.doubleValue() + Math.PI) % Math.PI < 10e-6) {
            return true;
        }
        if ((left.doubleValue() + 180) % 180 - (right.doubleValue() + 180) % 180 < 10e-5) {
            return true;
        }
        return false;
    }

    private static <K, V> boolean mapCheck(final String key,
                                           final LinkedHashMap leftValue, final LinkedHashMap rightValue,
                                           final MapDiffInfo<K, V> mapDiffInfo) throws IOException {
        final LinkedHashMap leftMap = leftValue;
        final LinkedHashMap rightMap = rightValue;

        //如果所有map的size不一致不一致的key没有被过滤，会直接返回
        if (!checkMapSize(key, leftValue, rightValue, mapDiffInfo)) {
            return true;
        }

        final Iterator<Map.Entry<String, Object>> i = leftMap.entrySet().iterator();
        while (i.hasNext()) {
            final Map.Entry<String, Object> e = i.next();
            final String _leftKey = e.getKey();
            final Object _leftValue = e.getValue();
            final Object _rightValue = rightMap.get(_leftKey);
            final boolean hasDiff = checkHasDiffValue(_leftKey, _leftKey,
                    _leftValue, _rightValue, mapDiffInfo);
            if (hasDiff) {
                return true;
            }
        }
        mapDiffInfo.getDiffKeyPath().removeLast();
        return false;
    }

    private static <K, V> boolean hashMapCheck(final String key,
                                              final Map leftValue, final Map rightValue,
                                              final MapDiffInfo<K, V> mapDiffInfo) throws IOException {
        if (!checkMapSize(key, leftValue, rightValue, mapDiffInfo)) {
            return true;
        }

        for (Object leftKey : leftValue.keySet()) {
            String _leftKey = leftKey.toString();
            final Object _leftValue = leftValue.get(_leftKey);
            final Object _rightValue = rightValue.get(_leftKey);
            final boolean hasDiff = checkHasDiffValue(_leftKey, _leftKey,
                    _leftValue, _rightValue, mapDiffInfo);
            if (hasDiff) {
                return true;
            }
        }
        mapDiffInfo.getDiffKeyPath().removeLast();
        return false;
    }

    private static <K, V> boolean listCheck(final String key, final ArrayList leftValue,
                                           final ArrayList rightValue, final MapDiffInfo<K, V> mapDiffInfo) throws IOException {

        Comparator comparator = listComparator(key, leftValue);
        if (comparator != null) {
            leftValue.sort(comparator);
            rightValue.sort(comparator);
        }

        final List leftList = leftValue;
        final List rightList = rightValue;

        if (leftList.size() != rightList.size()) {
            mapDiffInfo.getDiffKeyPath().add("size(),left.size=" + leftValue.size() + ",right.size=" + rightValue.size());

            final Pair<Object, Object> diffValue = Pair.of(
                    JSON.toJSONString(leftValue).substring(0, leftValue.size() < 500 ? leftValue.size() : 500),
                    JSON.toJSONString(rightList).substring(0, rightList.size() < 500 ? rightList.size() : 500));

            mapDiffInfo.getDifferences().put(key, diffValue);

            //return when find on diff
            return true;
        }
        final Iterator e1 = leftList.iterator();
        final Iterator e2 = rightList.iterator();
        int index = 0;
        while (e1.hasNext() && e2.hasNext()) {
            final Object o1 = e1.next();
            final Object o2 = e2.next();
            final boolean hasDiff = checkHasDiffValue(key, "[" + index + "]", o1, o2,
                    mapDiffInfo);
            if (hasDiff) {
                return true;
            }
            index++;
        }
        mapDiffInfo.getDiffKeyPath().removeLast();
        return false;
    }

    private static <K, V> boolean checkMapSize(final String key,
                                               final Map leftValue,
                                               final Map rightValue,
                                               final MapDiffInfo<K, V> mapDiffInfo) {
        if (leftValue.size() != rightValue.size()) {
            if (!hasDiffKey(leftValue, rightValue, mapDiffInfo)) {
                return true;
            }
            mapDiffInfo.getDiffKeyPath().add("size(),  left.size=" + leftValue.size() + ",  right.size=" + rightValue.size());

            if (mapDiffInfo.getOnlyOnLeft().size() > 0 || mapDiffInfo.getOnlyOnRight().size() > 0) {

                final Pair<Object, Object> diffValue = Pair.of(leftValue.keySet(), rightValue.keySet());
                mapDiffInfo.getDifferences().put(key, diffValue);

                //return when find on diff
                return false;
            }
            mapDiffInfo.getDiffKeyPath().removeLast();
        }
        return true;
    }

    /**
     * 两边size不一样后，遍历map获取两边不一样的key
     */
    private static <K, V> boolean hasDiffKey(final Map leftValue,
                                             final Map rightValue,
                                             final MapDiffInfo<K, V> mapDiffInfo) {
        Map<K, V> onlyOnLeft = new TreeMap<>();
        Map<K, V> onlyOnRight = new TreeMap<>();
        List<String> ignoreKeys = mapDiffInfo.getIgnoreKeys() != null
                ? Arrays.asList(mapDiffInfo.getIgnoreKeys().split(","))
                : Collections.emptyList();
        ArrayList leftKeys = new ArrayList<>(leftValue.keySet());
        ArrayList rightKeys = new ArrayList<>(rightValue.keySet());
        Iterator leftIterator = leftKeys.iterator();
        while (leftIterator.hasNext()) {
            Object leftKey = leftIterator.next();
            if (containAndRemoveElementFromIterator(rightKeys, leftKey)) {
                leftIterator.remove();
            }
        }
        for (Object leftKey : leftKeys) {
            if (!ignoreKeys.contains(leftKey)) {
                onlyOnLeft.put((K) leftKey, (V) leftValue.get(leftKey));
            }
        }
        for (Object rightKey : rightKeys) {
            if (!ignoreKeys.contains(rightKey)) {
                onlyOnRight.put((K) rightKey, (V) rightValue.get(rightKey));
            }
        }
        if (onlyOnLeft.size() != 0 || onlyOnRight.size() != 0) {
            mapDiffInfo.setOnlyOnLeft(onlyOnLeft);
            mapDiffInfo.setOnlyOnRight(onlyOnRight);
            return true;
        } else {
            return false;
        }

    }

    private static boolean containAndRemoveElementFromIterator(ArrayList map, Object element) {
        if (map.contains(element)) {
            map.remove(element);
            return true;
        } else {
            return false;
        }
    }

    private static <K, V> V rebuildData(K keyName, V value) {
        V newValue = value;
        //将清单中brandGoods按照obsBrandGoodId进行排序，转成map
        if (keyName != null) {
            if (keyName.toString().equals("brandGoods")) {
                newValue = sortByObsBrandGood((ArrayList) value);
            }
            if (keyName.toString().equals("parameters")) {
                newValue = convertListToMapByKeyName((ArrayList) value, "name");
            }
            if (keyName.toString().equals("tags")) {
                newValue = convertListToMapByKeyName((ArrayList) value, "obsTagId");
            }
            if (keyName.toString().equals("tagKeys")) {
                newValue = convertListToMapByKeyName((ArrayList) value, "obsTagKeyId");
            }
        }
        return newValue;
    }

    /**
     * 清单中brandGoods数据进行排序
     *
     * @param arrayList
     * @param <V>
     * @return
     */
    private static <V> V sortByObsBrandGood(final ArrayList arrayList) {
        //排序
        Collections.sort(arrayList, (o1, o2) -> {
            LinkedHashMap p1 = (LinkedHashMap) o1;
            LinkedHashMap p2 = (LinkedHashMap) o2;
            return p1.get("obsBrandGoodId").toString().compareTo(p2.get("obsBrandGoodId").toString());
        });
        /*转换成新map输出*/
        LinkedHashMap newMap = convertListToMapByKeyName(arrayList, "obsBrandGoodId");
        return (V) newMap;
    }

    private static <V> V convertListToMapByKeyName(final ArrayList arrayList, String keyName) {
        LinkedHashMap newMap = new LinkedHashMap();
        for (Object o : arrayList) {
            LinkedHashMap jsonOb = (LinkedHashMap) o;
            newMap.put(jsonOb.get(keyName), o);
        }
        return (V) newMap;
    }

    /**
     * 对比 list 进行 sort 的 comparator, 主要
     *
     * @param key
     * @param leftValue
     * @return
     */
    private static Comparator listComparator(String key, ArrayList leftValue) {
        if (!"paramModel".equals(key) && !"resource".equals(key) && !"assemblyModels".equals(key)
                && !"paramModelIds".equals(key) && !"constParameters".equals(key)
                && !"ignoreParameters".equals(key)) {
            return null;
        }
        if (leftValue.size() <= 1) {
            return null;
        }
        Object o = leftValue.get(0);
        if (o instanceof LinkedHashMap) {
            LinkedHashMap obj = (LinkedHashMap) o;
            if (obj.get("id") == null
                    && obj.get("name") == null
                    && obj.get("brandGoodName") == null
                    && obj.get("brandGoodId") == null) {
                return null;
            }

            if (obj.get("modelTypeId") != null && obj.get("modelTypeId").equals(3)) {
                return null;
            }
        }

        return Comparator.comparingInt(l -> {

            if (l instanceof LinkedHashMap) {
                LinkedHashMap obj = (LinkedHashMap) l;
                if (obj.get("id") != null) {
                    return obj.get("id").hashCode();
                } else if (obj.get("name") != null) {
                    return obj.get("name").hashCode();
                } else if (obj.get("brandGoodName") != null) {
                    return obj.get("brandGoodName").hashCode();
                } else if (obj.get("brandGoodId") != null) {
                    return obj.get("brandGoodId").hashCode();
                }
            }
            return l.hashCode();
        });
    }


    @Setter
    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class MapDiffInfo<K, V> {

        private Map<K, V> onlyOnLeft = new TreeMap<>();
        private Map<K, V> onlyOnRight = new TreeMap<>();

        Map<String, Pair<Object, Object>> differences = new TreeMap<>();

        private LinkedList<String> diffKeyPath = new LinkedList<>();

        private Integer id;

        /**
         * 需要忽略的字段
         * 格式为"id,name"
         */
        private String ignoreKeys;

        private Map<String, Map<Object, List<Object>>> ignoreKVs = new TreeMap<>();

        public boolean isSame() {
            return CollectionUtils.isEmpty(diffKeyPath) &&
                    MapUtils.isEmpty(differences) &&
                    MapUtils.isEmpty(onlyOnLeft) &&
                    MapUtils.isEmpty(onlyOnRight);
        }
    }

    @Setter
    @Getter
    public static class CheckDiffInfoResult {
        private boolean isSame;
        private String differences;
        private String diffKeyPath;
        private String expectOssFilePath;
        private String actualOssFilePath;
    }
}
