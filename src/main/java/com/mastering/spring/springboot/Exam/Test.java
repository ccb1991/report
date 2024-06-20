package com.mastering.spring.springboot.Exam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;

import javax.naming.event.ObjectChangeListener;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Test {

    public String replaceJson() {
        String string = "{\"header\":{\"protocol_version\":\"1\",\"productid\":\"3629892982708167306\",\"requestid\":\"ce25b80cb0584b6eb65189f6cae0b95a\",\"$CurActivityId\":\"sasdasd\",\"appid\":\"243650030996486022\",\"servicetag\":\"_openness_log_tag\",\"serviceid\":\"hmshimaintqrt\",\"timestamp\":1680181626911},\"event\":[{\"events_common\":{\"streamId\":\"f8e601e5-6b79-4d4d-8022-5b7c0650aa24\",\"groupId\":\"dadde766-b087-42da-8e67-d2499a520ee7\",\"projectId\":\"06981375190026432f77c01bfca33e32\",\"tags\":{\"uid\":\"ccb1991\",\"userAgent\":\"Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:109.0) Gecko/20100101 Firefox/111.0\",\"dc_sid\":\"4f857b07a3c09cde76b6a7550eb1826c\",\"did\":\"10_37288659000-1613903151165-668743\",\"platform\":\"PC\",\"cid\":\"10_37288659000-1613903151165-668743\",\"sid\":\"10_1680180716326.312840\"}},\"events\":[{\"$CurActivityId\":[1,2,3,4],\"Array\":[1,2,3,4],\"eventtime\":\"1680181626907\",\"event\":\"$WriteLog\",\"properties\":{\"$PrevActivityId\":\"\",\"$CurActivityId\":\"https://blog.csdn.net/m0_51067047/article/details/118336879\",\"$Content\":\"{\\\"pid\\\":\\\"blog\\\",\\\"ref\\\":\\\"https://www.baidu.com/link?url=pE4buIIJZ6OF8vb3ISb3_gDEvfPnzSzet8iUjsvygX5m4sTFl4kVDYuvLsnBZC0iK9KXUVyP9CO_l_QgAwLKbiW6V9kycNt_8dQj5g-pXC3&wd=&eqid=fb4d3e83000daa9d000000046425895b\\\",\\\"curl\\\":\\\"https://blog.csdn.net/m0_51067047/article/details/118336879\\\",\\\"dest\\\":\\\"\\\",\\\"spm\\\":\\\"1001.2101.3001.7788\\\",\\\"extra\\\":\\\"{\\\\\\\"titAb\\\\\\\":\\\\\\\"t_1\\\\\\\",\\\\\\\"tsab\\\\\\\":\\\\\\\"t_3\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"t_old\\\\\\\"}\\\",\\\"t\\\":\\\"1680181627\\\",\\\"eleTop\\\":\\\"189\\\",\\\"cCookie\\\":\\\"c_sid=4f857b07a3c09cde76b6a7550eb1826c;c_ref=https%3A//www.baidu.com/link;c_first_ref=www.baidu.com;c_segment=7;c_pref=https%3A//www.baidu.com/link;c_first_page=https%3A//blog.csdn.net/m0_51067047/article/details/118336879;c_page_id=default;c_adb=1;c_tos=rsc4fs;c_session_id=10_1680180716326.312840;\\\",\\\"__time__\\\":1680181627,\\\"log_id\\\":\\\"1367\\\"}\"}},{\"eventtime\":\"1680181626907\",\"event\":\"$WriteLog\",\"properties\":{\"$PrevActivityId\":\"\",\"$CurActivityId\":\"https://blog.csdn.net/m0_51067047/article/details/118336879\",\"$Content\":\"{\\\"pid\\\":\\\"blog\\\",\\\"ref\\\":\\\"https://www.baidu.com/link?url=pE4buIIJZ6OF8vb3ISb3_gDEvfPnzSzet8iUjsvygX5m4sTFl4kVDYuvLsnBZC0iK9KXUVyP9CO_l_QgAwLKbiW6V9kycNt_8dQj5g-pXC3&wd=&eqid=fb4d3e83000daa9d000000046425895b\\\",\\\"curl\\\":\\\"https://blog.csdn.net/m0_51067047/article/details/118336879\\\",\\\"dest\\\":\\\"\\\",\\\"spm\\\":\\\"1001.2101.3001.7789\\\",\\\"extra\\\":\\\"{\\\\\\\"titAb\\\\\\\":\\\\\\\"t_1\\\\\\\",\\\\\\\"tsab\\\\\\\":\\\\\\\"t_3\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"t_old\\\\\\\"}\\\",\\\"t\\\":\\\"1680181627\\\",\\\"eleTop\\\":\\\"284\\\",\\\"cCookie\\\":\\\"c_sid=4f857b07a3c09cde76b6a7550eb1826c;c_ref=https%3A//www.baidu.com/link;c_first_ref=www.baidu.com;c_segment=7;c_pref=https%3A//www.baidu.com/link;c_first_page=https%3A//blog.csdn.net/m0_51067047/article/details/118336879;c_page_id=default;c_adb=1;c_tos=rsc4fs;c_session_id=10_1680180716326.312840;\\\",\\\"__time__\\\":1680181627,\\\"log_id\\\":\\\"1367\\\"}\"}}]}]}";
        String key = "$CurActivityId";
        String value = "11111111";
        HashMap ha = JSONObject.parseObject(string, HashMap.class);
        map(ha, key, value);
        return JSONObject.toJSONString(ha);
    }

    public void map(HashMap<String, Object> sourceMap, String key, String value) {
        for (Map.Entry<String, Object> e : sourceMap.entrySet()) {
            if (e.getKey().equals(key)) {
                sourceMap.put(key, value);
            } else if (e.getValue().getClass().equals(JSONArray.class)) {
                List ls = (List) e.getValue();
                for (int i = 0; i < ls.size(); i++) {
                    if (ls.get(i).getClass().equals(JSONObject.class)) {
                        JSONObject jo = (JSONObject) ls.get(i);
                        HashMap h = JSONObject.parseObject(jo.toJSONString(), HashMap.class);
                        map(h, key, value);
                        ls.set(i, h);
                    }
                }
                sourceMap.put(e.getKey(), ls);
            } else if (e.getValue().getClass().equals(JSONObject.class)) {
                JSONObject jo = (JSONObject) e.getValue();
                HashMap h = JSONObject.parseObject(jo.toJSONString(), HashMap.class);
                map(h, key, value);
                sourceMap.put(e.getKey(), h);
            }
        }
    }

    public void diff(HashMap<String, Object> sourceMap, HashMap<String, Object> targetMap,
                     HashMap resultMap) {
        for (Map.Entry<String, Object> e : sourceMap.entrySet()) {
            Object sourceObject = sourceMap.get(e.getKey());
            Object targetObject = targetMap.get(e.getKey());
            Class<?> className = sourceObject.getClass();
            if (targetObject == null || (!sourceObject.getClass().equals(targetObject.getClass()))) {
                resultMap.put(e.getKey(), sourceObject);
            } else if (className.equals(JSONObject.class)) {
                HashMap result = new HashMap();
                JSONObject souOb = (JSONObject) sourceObject;
                JSONObject targOb = (JSONObject) targetObject;
                HashMap souHash = JSONObject.parseObject(JSONObject.toJSONString(souOb), HashMap.class);
                HashMap tarHash = JSONObject.parseObject(JSONObject.toJSONString(targOb), HashMap.class);
                diff(souHash, tarHash, result);
                if (result.size() != 0) {
                    resultMap.put(e.getKey(), result);
                }
            } else if (className.equals(JSONArray.class)) {
                List sourceList = (List) sourceObject;
                List targetList = (List) targetObject;
                List result = new ArrayList();
                if (sourceList.size() != targetList.size()) {
                    result = sourceList;
                } else if (sourceList.size() != 0 && sourceList.get(0).
                        getClass().equals(JSONObject.class)) {
                    for (int i = 0; i < sourceList.size(); i++) {
                        JSONObject jo = (JSONObject) sourceList.get(i);
                        HashMap h = JSONObject.parseObject(jo.toJSONString(), HashMap.class);
                        JSONObject targetJO = (JSONObject) targetList.get(i);
                        HashMap targetHash = JSONObject.parseObject(targetJO.toJSONString(), HashMap.class);
                        HashMap re = new HashMap();
                        diff(h, targetHash, re);
                        result.add(i, re);
                    }
                } else if (!sourceList.equals(targetList)) {
                    result = sourceList;
                }
                if (result.size() != 0) {
                    resultMap.put(e.getKey(), result);
                }
            } else if (className.equals(String.class)) {
                String s = (String) sourceObject;
                String t = (String) targetObject;
                if (!s.equals(t)) {
                    resultMap.put(e.getKey(), s);
                }
            } else if (className.equals(long.class)) {
                long s = (long) sourceObject;
                long t = (long) targetObject;
                if (s != t) {
                    resultMap.put(e.getKey(), s);
                }
            }
        }
    }

    public static void main(String[] args) {
        Test t = new Test();
        String s = t.replaceJson();
        String string = "{\"header\":{\"protocol_version\":\"1\",\"productid\":\"3629892982708167306\",\"requestid\":\"ce25b80cb0584b6eb65189f6cae0b95a\",\"$CurActivityId\":\"sasdasd\",\"appid\":\"243650030996486022\",\"servicetag\":\"_openness_log_tag\",\"serviceid\":\"hmshimaintqrt\",\"timestamp\":1680181626911},\"event\":[{\"events_common\":{\"streamId\":\"f8e601e5-6b79-4d4d-8022-5b7c0650aa24\",\"groupId\":\"dadde766-b087-42da-8e67-d2499a520ee7\",\"projectId\":\"06981375190026432f77c01bfca33e32\",\"tags\":{\"uid\":\"ccb1991\",\"userAgent\":\"Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:109.0) Gecko/20100101 Firefox/111.0\",\"dc_sid\":\"4f857b07a3c09cde76b6a7550eb1826c\",\"did\":\"10_37288659000-1613903151165-668743\",\"platform\":\"PC\",\"cid\":\"10_37288659000-1613903151165-668743\",\"sid\":\"10_1680180716326.312840\"}},\"events\":[{\"$CurActivityId\":[1,2,3,4],\"Array\":[1,2,3,4],\"eventtime\":\"1680181626907\",\"event\":\"$WriteLog\",\"properties\":{\"$PrevActivityId\":\"\",\"$CurActivityId\":\"https://blog.csdn.net/m0_51067047/article/details/118336879\",\"$Content\":\"{\\\"pid\\\":\\\"blog\\\",\\\"ref\\\":\\\"https://www.baidu.com/link?url=pE4buIIJZ6OF8vb3ISb3_gDEvfPnzSzet8iUjsvygX5m4sTFl4kVDYuvLsnBZC0iK9KXUVyP9CO_l_QgAwLKbiW6V9kycNt_8dQj5g-pXC3&wd=&eqid=fb4d3e83000daa9d000000046425895b\\\",\\\"curl\\\":\\\"https://blog.csdn.net/m0_51067047/article/details/118336879\\\",\\\"dest\\\":\\\"\\\",\\\"spm\\\":\\\"1001.2101.3001.7788\\\",\\\"extra\\\":\\\"{\\\\\\\"titAb\\\\\\\":\\\\\\\"t_1\\\\\\\",\\\\\\\"tsab\\\\\\\":\\\\\\\"t_3\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"t_old\\\\\\\"}\\\",\\\"t\\\":\\\"1680181627\\\",\\\"eleTop\\\":\\\"189\\\",\\\"cCookie\\\":\\\"c_sid=4f857b07a3c09cde76b6a7550eb1826c;c_ref=https%3A//www.baidu.com/link;c_first_ref=www.baidu.com;c_segment=7;c_pref=https%3A//www.baidu.com/link;c_first_page=https%3A//blog.csdn.net/m0_51067047/article/details/118336879;c_page_id=default;c_adb=1;c_tos=rsc4fs;c_session_id=10_1680180716326.312840;\\\",\\\"__time__\\\":1680181627,\\\"log_id\\\":\\\"1367\\\"}\"}},{\"eventtime\":\"1680181626907\",\"event\":\"$WriteLog\",\"properties\":{\"$PrevActivityId\":\"\",\"$CurActivityId\":\"https://blog.csdn.net/m0_51067047/article/details/118336879\",\"$Content\":\"{\\\"pid\\\":\\\"blog\\\",\\\"ref\\\":\\\"https://www.baidu.com/link?url=pE4buIIJZ6OF8vb3ISb3_gDEvfPnzSzet8iUjsvygX5m4sTFl4kVDYuvLsnBZC0iK9KXUVyP9CO_l_QgAwLKbiW6V9kycNt_8dQj5g-pXC3&wd=&eqid=fb4d3e83000daa9d000000046425895b\\\",\\\"curl\\\":\\\"https://blog.csdn.net/m0_51067047/article/details/118336879\\\",\\\"dest\\\":\\\"\\\",\\\"spm\\\":\\\"1001.2101.3001.7789\\\",\\\"extra\\\":\\\"{\\\\\\\"titAb\\\\\\\":\\\\\\\"t_1\\\\\\\",\\\\\\\"tsab\\\\\\\":\\\\\\\"t_3\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"t_old\\\\\\\"}\\\",\\\"t\\\":\\\"1680181627\\\",\\\"eleTop\\\":\\\"284\\\",\\\"cCookie\\\":\\\"c_sid=4f857b07a3c09cde76b6a7550eb1826c;c_ref=https%3A//www.baidu.com/link;c_first_ref=www.baidu.com;c_segment=7;c_pref=https%3A//www.baidu.com/link;c_first_page=https%3A//blog.csdn.net/m0_51067047/article/details/118336879;c_page_id=default;c_adb=1;c_tos=rsc4fs;c_session_id=10_1680180716326.312840;\\\",\\\"__time__\\\":1680181627,\\\"log_id\\\":\\\"1367\\\"}\"}}]}]}";
        String targetString = "{\"header\":{\"protocol_version\":\"1\",\"productid\":\"3629892982708167306\",\"requestid\":\"ce25b80cb0584b6eb65189f6cae0b95a\",\"$CurActivityId\":\"sasdasd\",\"appid\":\"243650030996486022\",\"servicetag\":\"_openness_log_tag\",\"serviceid\":\"hmshimaintqrt\",\"timestamp\":11},\"event\":[{\"events_common\":{\"streamId\":\"f8e601e5-6b79-4d4d-8022-5b7c0650aa24\",\"groupId\":\"dadde766-b087-42da-8e67-d2499a520ee7\",\"projectId\":\"06981375190026432f77c01bfca33e32\",\"tags\":{\"uid\":\"ccb199111\",\"userAgent\":\"Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:109.0) Gecko/20100101 Firefox/111.0\",\"dc_sid\":\"4f857b07a3c09cde76b6a7550eb1826c\",\"did\":\"10_37288659000-1613903151165-668743\",\"platform\":\"PC\",\"cid\":\"10_37288659000-1613903151165-668743\",\"sid\":\"10_1680180716326.312840\"}},\"events\":[{\"$CurActivityId\":[1,2,4,5],\"Array\":[1,2,3,4],\"eventtime\":\"1680181626907\",\"event\":\"$WriteLog\",\"properties\":{\"$PrevActivityId\":\"\",\"$CurActivityId\":\"https://blog.csdn.net/m0_51067047/article/details/118336879\",\"$Content\":\"{\\\"pid\\\":\\\"blog\\\",\\\"ref\\\":\\\"https://www.baidu.com/link?url=pE4buIIJZ6OF8vb3ISb3_gDEvfPnzSzet8iUjsvygX5m4sTFl4kVDYuvLsnBZC0iK9KXUVyP9CO_l_QgAwLKbiW6V9kycNt_8dQj5g-pXC3&wd=&eqid=fb4d3e83000daa9d000000046425895b\\\",\\\"curl\\\":\\\"https://blog.csdn.net/m0_51067047/article/details/118336879\\\",\\\"dest\\\":\\\"\\\",\\\"spm\\\":\\\"1001.2101.3001.7788\\\",\\\"extra\\\":\\\"{\\\\\\\"titAb\\\\\\\":\\\\\\\"t_1\\\\\\\",\\\\\\\"tsab\\\\\\\":\\\\\\\"t_3\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"t_old\\\\\\\"}\\\",\\\"t\\\":\\\"1680181627\\\",\\\"eleTop\\\":\\\"189\\\",\\\"cCookie\\\":\\\"c_sid=4f857b07a3c09cde76b6a7550eb1826c;c_ref=https%3A//www.baidu.com/link;c_first_ref=www.baidu.com;c_segment=7;c_pref=https%3A//www.baidu.com/link;c_first_page=https%3A//blog.csdn.net/m0_51067047/article/details/118336879;c_page_id=default;c_adb=1;c_tos=rsc4fs;c_session_id=10_1680180716326.312840;\\\",\\\"__time__\\\":1680181627,\\\"log_id\\\":\\\"1367\\\"}\"}},{\"eventtime\":\"1680181626907\",\"event\":\"$WriteLog\",\"properties\":{\"$PrevActivityId\":\"\",\"$CurActivityId\":\"222https://blog.csdn.net/m0_51067047/article/details/118336879\",\"$Content\":\"{\\\"pid\\\":\\\"blog\\\",\\\"ref\\\":\\\"https://www.baidu.com/link?url=pE4buIIJZ6OF8vb3ISb3_gDEvfPnzSzet8iUjsvygX5m4sTFl4kVDYuvLsnBZC0iK9KXUVyP9CO_l_QgAwLKbiW6V9kycNt_8dQj5g-pXC3&wd=&eqid=fb4d3e83000daa9d000000046425895b\\\",\\\"curl\\\":\\\"https://blog.csdn.net/m0_51067047/article/details/118336879\\\",\\\"dest\\\":\\\"\\\",\\\"spm\\\":\\\"1001.2101.3001.7789\\\",\\\"extra\\\":\\\"{\\\\\\\"titAb\\\\\\\":\\\\\\\"t_1\\\\\\\",\\\\\\\"tsab\\\\\\\":\\\\\\\"t_3\\\\\\\",\\\\\\\"tag\\\\\\\":\\\\\\\"t_old\\\\\\\"}\\\",\\\"t\\\":\\\"1680181627\\\",\\\"eleTop\\\":\\\"284\\\",\\\"cCookie\\\":\\\"c_sid=4f857b07a3c09cde76b6a7550eb1826c;c_ref=https%3A//www.baidu.com/link;c_first_ref=www.baidu.com;c_segment=7;c_pref=https%3A//www.baidu.com/link;c_first_page=https%3A//blog.csdn.net/m0_51067047/article/details/118336879;c_page_id=default;c_adb=1;c_tos=rsc4fs;c_session_id=10_1680180716326.312840;\\\",\\\"__time__\\\":1680181627,\\\"log_id\\\":\\\"1367\\\"}\"}}]}]}";
        HashMap sourceMap = JSONObject.parseObject(string, HashMap.class);
        HashMap targetMap = JSONObject.parseObject(targetString, HashMap.class);
        HashMap result = new HashMap();
        t.diff(sourceMap, targetMap, result);
        String resting = JSONObject.toJSONString(result);
        System.out.println();
    }
}
