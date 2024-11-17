package com.mastering.spring.springboot;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.models.auth.In;
import org.springframework.data.rest.webmvc.IncomingRequest;

import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder;
import java.net.Inet4Address;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Test {
    public static  void main(String[] args) {
        String a="Burger King";
        String b="Burger King";
        System.out.println(a.compareTo(b));
        Stack<Integer> stack = new Stack();
    }
}

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        int length=Math.max(list1.length,list2.length);
        Arrays.sort(list1);
        Arrays.sort(list2);
        List<String>  result=new ArrayList();
        int first=0;
        int second=0;
        while (first<list1.length && second<list2.length){
            String word1=list1[first];
            String word2=list2[second];
            if(word1.equals(" ")){
                first++;
            }
            if(word2.equals(" ")){
                second++;
            }
            if (word1.equals(word2)){
                result.add(word1);
                first++;
                second++;
            } else if (word1.compareTo(word2)>0) {
                second++;
            } else {
                first++;
            }
        }
        return result.stream().toArray(String[]::new);
    }
}

class CalPoints {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack();
        for (int i=0;i<operations.length;i++){
            String c=operations[i];
            if (c.equals("C")){
                stack.pop();
            } else if (c.equals("D")){
                int v=stack.peek();
                stack.push(v*2);
            } else if (c.equals("+")){
                int v1=stack.get(stack.size()-1);
                int v2=stack.get(stack.size()-2);
                stack.push(v1+v2);
            } else {
                stack.push(Integer.valueOf(c));
            }
        }
        int value=stack.stream().mapToInt(Integer::intValue).sum();
        return value;
    }

    public static  void main(String[] args) {
        String[] a=new String[]{"5","2","C","D","+"};
        CalPoints calPoints=new CalPoints();
        int result=calPoints.calPoints(a);
        System.out.println(result);
    }

}

class FindShortestSubArray {
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer,int[]> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2]=i;
            } else {
                map.put(nums[i],new int[]{1,i,i});
            }
        }
        int max=0;
        int min=50001;
        for(Map.Entry<Integer,int[]> e: map.entrySet()){
            Integer key=e.getKey();
            int[] value=e.getValue();
            if(value[0]>max){
                max=key;
                min=value[2]-value[1]+1;
            } else if  (key==max){
                Integer in=value[2]-value[1]+1;
                min=Math.min(min,in);
            }
        }
        return min;
//        HashMap<Integer,Integer> map=new HashMap<>();
//        for (int i=0;i<nums.length;i++){
//            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
//        }
//        int max=0;
//        for (Integer i:map.values()){
//            max=Math.max(max,i);
//        }
//        int result=0;
//        List<Integer> numsList= Arrays.stream(nums).boxed()
//                .collect(Collectors.toList());
//        for (Map.Entry e:map.entrySet()){
//            if (e.getValue().equals(max)){
//                int index=numsList.indexOf(e.getKey());
//                int end=numsList.lastIndexOf(e.getKey());
//                result=Math.min(result,end-index+1);
//            }
//        }
//        return result;
    }
}


class MyHashSet {
    private List<String> set;

    public MyHashSet() {
        this.set=new ArrayList<>();
    }

    public void add(int key) {
        String skey=String.valueOf(key);
        if(!set.contains(skey)){
            this.set.add(skey);
        }
    }

    public void remove(int key) {
        String skey=String.valueOf(key);
        this.set.remove(skey);
    }

    public boolean contains(int key) {
        String skey=String.valueOf(key);
        return this.set.contains(skey);
    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        boolean c=myHashSet.contains(1); // 返回 True
        boolean b=myHashSet.contains(3); // 返回 False ，（未找到）
        myHashSet.add(2);      // set = [1, 2]
        boolean d=myHashSet.contains(2); // 返回 True
        myHashSet.remove(2);   // set = [1]
        boolean e=myHashSet.contains(2); // 返回 False ，（已移除）
    }
}

class ImageSmoother {
    public int[][] imageSmoother(int[][] img) {
        int row=img.length;
        int col=img[0].length;
        int[][] result=new int[row][col];
        for(int i=0;i<row;i++){
            int l1=-1,l2=-1,l3=-1,m1=-1,m2=-1,m3=-1,r1=-1,r2=-1,r3=-1;
            for(int j=0;j<col;j++){
                if (j-1>=0){
                    if(i-1>=0){
                        l1=img[i-1][j-1];
                    }
                    l2=img[i][j-1];
                    if(i+1<row){
                        l3=img[i+1][j-1];
                    }
                }
                if(i-1>=0){
                    m1=img[i-1][j];
                }
                m2=img[i][j];
                if(i+1<row){
                    m3=img[i+1][j];
                }
                if(j+1<col){
                    if(i-1>=0){
                        r1=img[i-1][j+1];
                    }
                    if(i+1<row){
                        r3=img[i+1][j+1];
                    }
                    r2=img[i][j+1];
                }

                int mod=9;
                mod=l1==-1?mod-1:mod;
                mod=l2==-1?mod-1:mod;
                mod=l3==-1?mod-1:mod;
                mod=m1==-1?mod-1:mod;
                mod=m2==-1?mod-1:mod;
                mod=m3==-1?mod-1:mod;
                mod=r1==-1?mod-1:mod;
                mod=r2==-1?mod-1:mod;
                mod=r3==-1?mod-1:mod;
                int sum=0;
                sum=l1!=-1?sum+l1:sum;
                sum=l2!=-1?sum+l2:sum;
                sum=l3!=-1?sum+l3:sum;
                sum=r1!=-1?sum+r1:sum;
                sum=r2!=-1?sum+r2:sum;
                sum=r3!=-1?sum+r3:sum;
                sum=m1!=-1?sum+m1:sum;
                sum=m2!=-1?sum+m2:sum;
                sum=m3!=-1?sum+m3:sum;
                result[i][j]=sum/mod;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] v=new int[][]{{100,200,100},{200,50,200},{100,200,100}};
        ImageSmoother i=new ImageSmoother();
        i.imageSmoother(v);
    }
}

class RotateString {
    public boolean rotateString(String s, String goal) {
        if(s.length()!=goal.length()){
            return false;
        }
        for(int j=0;j<s.length();j++){
            if(s.charAt(j)==goal.charAt(0)){
                String sub=s.substring(j,s.length())+s.substring(0,j);
                if(sub.equals(goal)){
                    return true;
                }
            }
        }
        return false;
    }
}

class ToLowerCase {
    public String toLowerCase(String s) {
        StringBuilder sb=new StringBuilder();
        for (char c:s.toCharArray()){
            if (c<97){
                c= (char) (c+32);
            }
            sb.append(c);
        }
        return sb.toString();
    }
}

class PivotIndex {
    public int pivotIndex(int[] nums) {
        List<Integer> in=Arrays.stream(nums).boxed().
                collect(Collectors.toList());
        Integer sum=in.stream().reduce(0,(a,b)->a+b);
        Integer leftValue=0;
        for(int i=0;i<in.size();i++){
            leftValue+=nums[i];
            if ((sum==2*leftValue+nums[i])){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        PivotIndex p=new PivotIndex();
        int v=p.pivotIndex(new int[]{-1,-1,-1,-1,-1,0});
        System.out.println(v);
    }
}

class ShortestCompletingWord {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        licensePlate=licensePlate.toLowerCase();
        StringBuilder stringBuilder=new StringBuilder();
        for(char c:licensePlate.toCharArray()){
            if (c>='a' && c<='z'){
                stringBuilder.append(c);
            }
        }
        String result=null;
        Long minlength=100000000000001L;
        licensePlate=stringBuilder.toString();
        char[] lplist=licensePlate.toCharArray();
        Arrays.sort(lplist);
        for (String w:words){
            char[] wlist=w.toCharArray();
            Arrays.sort(wlist);
            int startlp=0;
            int startW=0;
            while (startlp<licensePlate.length() && startW<w.length()){
                char clp=lplist[startlp];
                char cw=wlist[startW];
                if(clp!=cw){
                    startW++;
                } else if (clp==cw){
                    startW++;
                    startlp++;
                    if(startlp==licensePlate.length() && w.length()<minlength){
                        minlength=Long.valueOf(w.length());
                        result=w;
                    }
                }
            }
        }
        return result;
//        HashMap<String,Integer> LPmap=countChar(licensePlate);
//        int lengthMin=1001;
//        String result=null;
//        for (String w:words){
//            boolean flag=true;
//            HashMap<String,Integer> wMap=countChar(w);
//            for(Map.Entry<String,Integer> e:LPmap.entrySet()){
//                String k= e.getKey();
//                Integer v=e.getValue();
//                if (!k.equals("lengths")){
//                    Integer wv=wMap.getOrDefault(k,null);
//                    if (wv==null || wMap.get(k)<v){
//                        flag=false;
//                        break ;
//                    }
//                }
//            }
//            if (flag){
//                Integer length=wMap.get("lengths");
//                if(length<lengthMin){
//                    lengthMin=length;
//                    result=w;
//                }
//            }
//        }
//        return result;
    }

//    public HashMap countChar(String word){
//        word=word.toLowerCase();
//        HashMap<String,Integer> LP=new HashMap();
//        Integer length=0;
//        for(char c:word.toCharArray()){
//            if (c>='a' && c<='z'){
//                LP.put(String.valueOf(c),LP.getOrDefault(String.valueOf(c),0)+1);
//                length++;
//            }
//        }
//        LP.put("lengths",length);
//        return LP;
//    }

    public static void main(String[] args) {
        ShortestCompletingWord s=new ShortestCompletingWord();
        s.shortestCompletingWord(
                "1s3 PSt",new String[]{"step","steps","stripe","stepple"});
    }
}

class UniqueMorseRepresentations {
    public int uniqueMorseRepresentations(String[] words) {
        List<String> code=Arrays.asList(".-","-...","-.-.","-..",".","..-.",
                "--.","....","..",".---","-.-",".-..","--","-.","---",".--.",
                "--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..");
        HashSet results=new HashSet();
        for(String w:words){
            StringBuilder stringBuilder=new StringBuilder();
            for(char c:w.toCharArray()){
                int index=c-97;
                stringBuilder.append(code.get(index));
            }
            results.add(stringBuilder.toString());
        }
        return results.size();
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

class MaxDepth {
    public int maxDepth(Node root) {
        int result=1;
        countDeep(root,result);
        return result;
    }

    public void countDeep(Node node,int deep){
        if (node.children==null){
            return;
        }
        deep++;
        for (Node n:node.children){
            countDeep(n,deep);
        }
    }

    public static void main(String[] args) {
        MaxDepth maxDepth=new MaxDepth();
        Node n6=new Node(6);
        Node n5=new Node(5);
        Node n4=new Node(4);
        Node n3=new Node(3,Arrays.asList(n5,n6));
        Node n2=new Node(2);
        Node n1=new Node(1,Arrays.asList(n2,n3,n4));
        maxDepth.maxDepth(n1);
        int value=2/10;
        System.out.println();
    }

}

class SelfDividingNumbers {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result=new ArrayList<>();
        for(int i=left;i<=right;i++){
            int value=i;
            while(value/10>=0 && value%10!=0){
                int yushu=value%10;
                if(i%yushu!=0){
                    break;
                } else {
                    value=value/10;
                    if (value==0){
                        result.add(i);
                        break;
                    }
                }
            }


        }
        return result;
    }

    public static void main(String[] args) {
        SelfDividingNumbers selfDividingNumbers=new SelfDividingNumbers();
        selfDividingNumbers.selfDividingNumbers(1,22);
    }
}

class NumberOfPoints {
    public int numberOfPoints(List<List<Integer>> nums) {
//        HashSet<Integer> hashSet=new HashSet<>();
//        for (int i=0;i<nums.size();i++){
//            List<Integer> integers=nums.get(i);
//            for(Integer j=integers.get(0);j<=integers.get(1);j++){
//                hashSet.add(j);
//            }
//        }
//        return hashSet.size();
//        Collections.sort(nums,(a,b)->a.get(0).compareTo(b.get(0)));
//        int result=0;
//        int max=0;
//        for (List<Integer> num:nums){
//            Integer start=num.get(0);
//            Integer end=num.get(1);
//            if (start>max){
//                result+=(end-start+1);
//                max=end;
//            } else if (start<=max && end>max) {
//                result+=(end-max);
//                max=end;
//            }
//
//
//        }
//        return result;
        Integer max=0;
        for (List<Integer> list:nums){
            max=Math.max(max,list.get(1));
        }
        int[] c=new int[max+2];
        for (List<Integer> list:nums){
            ++c[list.get(0)];
            --c[list.get(1)+1];
        }
        int ans=0;
        int count=0;
        for (int i=1;i<c.length;i++){
            count+=c[i];
            if(count>0){
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        NumberOfPoints numberOfPoints=new NumberOfPoints();
        List<List<Integer>> lists=new ArrayList<>();
        lists.add(Arrays.asList(3,6));
        lists.add(Arrays.asList(1,5));
        lists.add(Arrays.asList(4,7));
//        Collections.sort(lists,(a,b)->a.get(0).compareTo(b.get(0)));
        numberOfPoints.numberOfPoints(lists);
    }
}


class DistanceBetweenBusStops {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start>destination){
            int tempValue=destination;
            destination=start;
            start=tempValue;
        }
        Integer result=0,second=0;
        for(int i=0;i<distance.length;i++){
            if (i<start || i>= destination){
                second+=distance[i];
            } else {
                result+=distance[i];;
            }
        }
        return Math.min(result,second);
    }

    public static void main(String[] args) {
        DistanceBetweenBusStops distanceBetweenBusStops=new DistanceBetweenBusStops();
        distanceBetweenBusStops.distanceBetweenBusStops(new int[]{1,2,3,4},0,2);
    }
}

class NumberOfLines {
    public int[] numberOfLines(int[] widths, String s) {
        int[] result=new int[]{0,0};
        int pos=0;
        for(int i=0;i<s.length();i++){
            int c=s.charAt(i-97);
            if (pos>=100){
                pos-=100;
            }
            pos+=widths[c];
            if (pos>100){
                result[0]+=1;
                pos=widths[c-97];
            } else if (pos==100){
                result[0]+=1;
            }

        }
        result[1]=pos;
        if (pos!=0){
            result[1]+=1;
        }
        return  result;
    }
}

class CalculateLength{
    public Integer calculate(String s){
        if(s.length()==0){
            return 0;
        }
        String[] slist=s.split(" ");
        return slist[slist.length-1].length();
    }

    public static void main(String[] args) {
        CalculateLength c=new CalculateLength();
        Integer length=c.calculate("Hello  World          ");
        System.out.println(length);
    }

}

//class Day{
//    public boolean calculateDay(Integer nums){
//        if (nums>100000000 || nums<10000000){
//            return false;
//        }
//        String s=String.valueOf(nums);
//        Integer month=Integer.valueOf(s.substring(4,6));
//        Integer day=Integer.valueOf(s.substring(6,8));
//        if (month>12 || month==0){
//            return false;
//        }
//        if(month==1 || month ==3 || )
//    }
//}

class ShortestToChar {
    public int[] shortestToChar(String s, char c) {
        int[] result=new int[s.length()];
        int preIndex=s.indexOf(c);
        for(int i=0;i<s.length();i++){
            int firstIndex=s.indexOf(c,i);
            result[i]=Math.min(Math.abs(firstIndex-i),Math.abs(preIndex-i));
            if (s.charAt(i)==c){
                preIndex=i;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ShortestToChar shortestToChar=new ShortestToChar();
        shortestToChar.shortestToChar("loveleetcode",'e');

    }
}


class ToGoatLatin {
    public String toGoatLatin(String sentence) {
        StringBuilder stringBuilder=new StringBuilder();
        String[] words=sentence.split(" ");
        List<String> o=Arrays.asList("a", "e", "i", "o", "u");
        int count=0;
        for (int i=0;i<words.length;i++){
            count++;
            StringBuilder w= new StringBuilder(words[i]);
            if (!w.toString().equals(" ")){
                char c=Character.toLowerCase(w.charAt(0));
                if (!o.contains(String.valueOf(c))){
                    char fc=w.charAt(0);
                    w.deleteCharAt(0);
                    w.append(fc);
                }

            }
            w.append("ma");
            for (int j=1;j<=count;j++){
                w.append("a");
            }
            stringBuilder.append(w).append(" ");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }

    public static void main(String[] args) {
        ToGoatLatin toGoatLatin=new ToGoatLatin();
        toGoatLatin.toGoatLatin("The quick brown fox jumped over the lazy dog");
    }
}

class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        Stack<Integer> five =new Stack();
        Stack<Integer> ten=new Stack();
        for(int i:bills){
            if(i==5){
                five.push(i);
            }   else if(i==10){
                if (five.isEmpty()){
                    return false;
                }
                five.pop();
                ten.push(i);
            } else {
                if (ten.isEmpty()){
                    if (five.size()<3){
                        return false;
                    }
                    five.pop();
                    five.pop();
                    five.pop();
                } else {
                    ten.pop();
                    if(five.isEmpty()){
                        return false;
                    }
                    five.pop();
                }

            }
        }
        return true;
    }
}

class LargeGroupPositions {
    public List<List<Integer>> largeGroupPositions(String s) {
//       List<List<Integer>> result=new ArrayList<>();
//       Integer start=0,end=0;
//       while(start<s.length()&& end<s.length()){
//           if (s.charAt(start)==s.charAt(end)){
//               if (end-start>=3 && end==s.length()-1){
//                    result.add(Arrays.asList(start,end));
//               }
//               end++;
//           } else {
//               if (end-start>=3){
//                   result.add(Arrays.asList(start,end-1));
//               }
//               start=end;
//               end++;
//           }
//       }
//       return result;
        int l = 0, r = 0, n = s.length();
        List<List<Integer>> ans = new ArrayList<>();
        while (r < n) {
            char c = s.charAt(l);
            while (r < n && s.charAt(r) == c) {
                r++;
            }
            if ((r - l) >= 3) {
                List<Integer> list = new ArrayList<>();
                list.add(l);
                list.add(r - 1);
                ans.add(list);
            }
            l = r;
        }
        return ans;
    }

    public static void main(String[] args) {
        LargeGroupPositions largeGroupPositions=new LargeGroupPositions();
        largeGroupPositions.largeGroupPositions("abcdddeeeeaabbbcddd");
    }
}

class FairCandySwap {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int[] result=new int[2];
        Integer aliceSum= Arrays.stream(aliceSizes).sum();
        Integer bobSum= Arrays.stream(bobSizes).sum();
        Integer avg=(aliceSum+bobSum)/2;
        Integer value=aliceSum-avg;
        List<Integer> bobArray=Arrays.stream(bobSizes).boxed().collect(Collectors.toList());
        for(int i=0;i<aliceSizes.length;i++){
            int b=aliceSizes[i]-value;
            if (bobArray.contains(Integer.valueOf(b))){
                result[0]=aliceSizes[i];
                result[1]=b;
                break;
            }
        }
        return result;
    }
}

class IsAlienSorted {
    public boolean isAlienSorted(String[] words, String order) {
        for(int i=0;i<words.length-1;i++){
            String wordBefore=words[i];
            String afterWord=words[i+1];
            for (int j=0;j<wordBefore.length();j++){
                if (j<afterWord.length()){
                    char bfc=wordBefore.charAt(j);
                    char afc=afterWord.charAt(j);
                    Integer bIndex=order.indexOf(String.valueOf(bfc));
                    Integer aIndex=order.indexOf(String.valueOf(afc));
                    if (bIndex>aIndex){
                        return false;
                    } else if (bIndex.equals(aIndex)){
                    } else {
                        break;
                    }
                } else {
                    return false;
                }

            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsAlienSorted isAlienSorted=new IsAlienSorted();
        isAlienSorted.isAlienSorted(new String[]{"word","world","row"},"worldabcefghijkmnpqstuvxyz");
    }
}

class UncommonFromSentences {
    public String[] uncommonFromSentences(String s1, String s2) {
        String s=s1+" "+s2;
        String[] slist=s.split(" ");
        HashMap<String,Integer> count=new HashMap();
        for (String string:slist){
            Integer value= count.getOrDefault(string,0);
            count.put(string,value+1);
        }
        String[] strings=new String[count.size()];
        int i=0;
        for(Map.Entry<String,Integer> entity:count.entrySet()){
            if (entity.getValue()==1){
                strings[i]=entity.getKey();
                i++;
            }
        }
        List<String> stringList=Arrays.stream(strings).filter(st->st!=null).collect(Collectors.toList());
        return stringList.toArray(new String[stringList.size()]);
    }
}

class SortArrayByParity {
    public int[] sortArrayByParity(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while (left<right){
            if (nums[left]%2!=0 && nums[right]%2==0){
                int temp=nums[left];
                nums[left]=nums[right];
                nums[right]=temp;
                left++;
                right--;
            } else if (nums[left]%2==0){
                left++;
            } else if(nums[right]%2!=0){
                right--;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        SortArrayByParity sortArrayByParity=new SortArrayByParity();
        sortArrayByParity.sortArrayByParity(new int[]{1,3,5,7,11,13,2,4});
    }
}

class CommonChars {
    public List<String> commonChars(String[] words) {
        int[] minfreq=new int[26];
        Arrays.fill(minfreq,Integer.MAX_VALUE);
        for(String w:words){
            int[] freq=new int[26];
            for (char c:w.toCharArray()){
                ++freq[c-'a'];
            }
            for (int i=0;i<26;i++){
                minfreq[i]=Math.min(minfreq[i],freq[i]);
            }
        }
        List<String> result=new ArrayList<>();
        for(int i=0;i<26;i++){
            for(int j=0;j<minfreq[i];j++){
                result.add(String.valueOf((char) (i+'a')));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CommonChars commonChars=new CommonChars();
        commonChars.commonChars(new String[]{"bella","label","roller"});
    }
}


class RecentCounter {
    PriorityQueue<Integer> q1;
    public RecentCounter() {
         q1 = new PriorityQueue<>();
    }

    public int ping(int t) {
        q1.add(t);
        while(q1.peek()+3000<t){
            q1.poll();
        }
        return q1.size();
    }
}

class Merge {
    public int[] merge(int[] nums1,   int[] nums2 ) {
        int start=0;
        int end=0;
        int cus=0;
        int[] result=new int[nums1.length+ nums2.length];
        while (start<nums1.length || end<nums2.length){
            int value1=nums1[start<nums1.length?start:start-1];
            int value2=nums2[end< nums2.length?end:end-1];
            if (value1>value2){
                result[cus]=value1;
                start++;
            } else if (value1<value2){
                result[cus]=value2;
                end++;
            } else if (value1==value2){
                result[cus]=value2;
                result[cus+1]=value2;
                start++;
                end++;
                cus+=1;
            }
            cus++;
        }
        return result;
    }

    public static void main(String[] args) {
        Merge merge=new Merge();
        merge.merge(new int[]{9,7,6,5,3,1},new int []{8,5,4,3,1});
    }
}

class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        List<Integer> stonesList=Arrays.stream(stones).
                boxed().collect(Collectors.toList());
        Collections.sort(stonesList);
        while(stonesList.size()>1){
            Integer value=stonesList.remove(stonesList.size()-1);
            Integer value2=stonesList.remove(stonesList.size()-1);
            Integer cha=value-value2;
            stonesList.add(cha);
            Collections.sort(stonesList);
        }
        return stonesList.get(0);
    }

    public static void main(String[] args) {
        LastStoneWeight stoneWeight=new LastStoneWeight();
        stoneWeight.lastStoneWeight(new int[]{4,2,7,1,8,1});
    }
}

class IsBoomerang {
    public boolean isBoomerang(int[][] points) {

        int count = 0;
        double k= -1;
        for (int[] point : points) {
            if (point[0]!=0){
                k= (double) point[1]/point[0];
                break;
            }
        }
        if (k == -1){
            return false;
        }
        for (int[] point : points) {
            if (point[0] * k == point[1]) {
                count++;
            }
        }
        return count != 3;
    }

    public static void main(String[] args) {
        IsBoomerang isBoomerang=new IsBoomerang();
        isBoomerang.isBoomerang(new int[][]{{0,1},{0,1},{2,1}});
    }
}

class DayOfTheWeek {
    public String dayOfTheWeek(int day, int month, int year) {
        String[] week={"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] monthDay=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        int days=365*(year-1971)+(year-1969)/4;
        for (int i=0;i<month;i++){
            days+=monthDay[i];
        }
        if ((year % 400==0 || (year%4==0 && year %100!=0) )&&month>3){
            days++;
        }
        days+=day;
        return week[(days+3)%7];
    }
}

class NumRookCaptures {
    public int numRookCaptures(char[][] board) {
        int result=0;
        int[] p=new int[2];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if (board[i][j]=='R'){
                    p[0]=i;
                    p[1]=j;
                }
            }
        }
        int value=p[0];
        while(value>=0){
            char c=board[value][p[1]];
            if (c=='p'){
                result++;
                break;
            } else if (c =='B') {
                break;
            } else {
                value--;
            }
        }
        value=p[0];
        while(value<board.length){
            char c=board[value][p[1]];
            if(c=='p'){
                result++;
                break;
            } else if(c== 'B') {
                break;
            } else {
                value++;
            }
        }
        value=p[1];
        while(value<board[value].length){
            char c=board[p[0]][value];
            if (c=='p'){
                result++;
                break;
            } else if(c== 'B') {
                break;
            } else {
                value++;
            }
        }
        value=p[1];
        while(value>=0){
            char c=board[p[0]][value];
            if (c=='p'){
                result++;
                break;
            } else if (c =='B') {
                break;
            } else {
                value--;
            }
        }
        return result;
    }
}


class CountCompleteDayPairs {
    public int countCompleteDayPairs(int[] hours) {
        int count=0;
        for (int i=0;i<hours.length;i++){
            for (int j=i+1;j<hours.length;j++){
                if ((hours[i]+hours[j])%24==0){
                    count++;
                }
            }
        }
        return count;
    }
}

class DiStringMatch {
    public int[] diStringMatch(String s) {
        int lo=0,hi=s.length();
        int[] result=new int[s.length()+1];
        for (int i=0;i<s.length();i++) {
            result[i]=s.charAt(i)=='I'?lo++:hi--;
        }
        result[s.length()]=lo;
        return result;
    }

    public static void main(String[] args) {
        DiStringMatch diStringMatch=new DiStringMatch();
        diStringMatch.diStringMatch("III");
    }
}

class RemoveOuterParentheses {
    public String removeOuterParentheses(String s) {
        Stack stack=new Stack();
        StringBuilder stringBuilder=new StringBuilder();
        StringBuilder strings=new StringBuilder();
        for (char c:s.toCharArray()){
            if (c=='('){
                stack.push(c);
                stringBuilder.append(c);
            } else {
                stack.pop();
                stringBuilder.append(c);
                if (stack.size()==0){
                    String string=stringBuilder.toString();
                    strings.append(string.substring(1,string.length()-1));
                    stringBuilder=new StringBuilder();
                }
            }
        }
        return strings.toString();
    }

    public static void main(String[] args) {
        RemoveOuterParentheses removeOuterParentheses=new RemoveOuterParentheses();
        removeOuterParentheses.removeOuterParentheses("(()())(())");
        List<Integer> words = new ArrayList<>();
        words.add(1);
        words.add(3);
        words.add(2);
        List<Integer> s = new ArrayList<>();
        s.add(3);
        s.add(4);
        s.add(3);
        s.retainAll(words);
        System.out.println(s);
    }
}

class UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        ConcurrentHashMap<Integer,Integer> integerHashMap=new ConcurrentHashMap<>();
        Arrays.stream(arr).forEach(a->{
            Integer value=integerHashMap.getOrDefault(a,0)+1;
            integerHashMap.put(a,value);
        });
        HashSet<Integer> set=new HashSet<>();
        integerHashMap.values().forEach(v->set.add(v));
        return set.size()==integerHashMap.values().size();
    }

    public static void main(String[] args) {
        UniqueOccurrences uniqueOccurrences=new UniqueOccurrences();
        uniqueOccurrences.uniqueOccurrences(new int[]{1,2,1});
    }
}

class MaxNumberOfBalloons {
    public int maxNumberOfBalloons(String text) {
        HashMap<String,Integer> hashMap=new HashMap<>();
        hashMap.put("a",0);
        hashMap.put("b",0);
        hashMap.put("o",0);
        hashMap.put("l",0);
        hashMap.put("n",0);
        for (char c:text.toCharArray()){
            if (c=='b' || c=='a' || c=='o' || c=='n' || c=='l'){
                String cs=String.valueOf(c);
                Integer v=hashMap.get(cs)+1;
                hashMap.put(cs,v);
            }
        }
        hashMap.put("l",hashMap.get("l")/2);
        hashMap.put("o",hashMap.get("o")/2);
        return hashMap.values().stream().sorted().
                limit(1).collect(Collectors.toList()).get(0);
    }
}

class MinDeletionSize {
    public int minDeletionSize(String[] strs) {
        int result=0;
        for(int i=0;i<strs[0].length();i++){
            char c=strs[0].charAt(i);
            for (String s:strs){
                if (s.charAt(i)<c){
                    result++;
                    break;
                } else {
                    c=s.charAt(i);
                }
            }
        }
        return result;

    }
}

class RepeatedNTimes {
    public int repeatedNTimes(int[] nums) {
        HashSet<Integer> hashSet=new HashSet<>();
        for(int i:nums){
            if (hashSet.contains(i)){
                return i;
            } else {
                hashSet.add(i);
            }
        }
        return 0;
    }
}

class CountCharacters {
    public int countCharacters(String[] words, String chars) {
        Integer result=0;
        HashMap<String,Integer> hashMap=new HashMap<>();
        for (char c:chars.toCharArray()){
            String cs=String.valueOf(c);
            Integer value=hashMap.getOrDefault(cs,0)+1;
            hashMap.put(cs,value);
        }
        for (String w:words){
            HashMap<String,Integer> wHashMap=new HashMap();
            for (char wc:w.toCharArray()){
                String wcs=String.valueOf(wc);
                Integer value=wHashMap.getOrDefault(wcs,0)+1;
                wHashMap.put(wcs,value);
            }
            Boolean flag=true;
            for(Map.Entry<String,Integer> entry:wHashMap.entrySet()){
                if (entry.getValue()>hashMap.getOrDefault(
                        entry.getKey(),0)){
                    flag=false;
                    break;
                }
            }
            if (flag){
                result+=w.length();
            }
        }
        return result;
    }
}


class DistributeCandies {
    public int[] distributeCandies(int candies, int num_people) {
        int[] result=new int[num_people];
        int position=0,count=1;
        while(candies>0){
            if (count>=candies){
                count=candies;
            }
            result[position]+=count;
            candies-=count;
            position++;
            count++;
            if (position>num_people-1){
                position=0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        DistributeCandies distributeCandies=new DistributeCandies();
        distributeCandies.distributeCandies(7,4);
    }
}


class MinimumAbsDifference {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int start=0,next=1;
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(arr);
        int min=999999999;
        while(start<arr.length-1){
            int value=Math.abs(arr[start]-arr[next]);
            if (value<min){
                min=value;
                result=new ArrayList<>();
                result.add(Arrays.asList(arr[start],arr[next]));
            } else if (value==min){
                result.add(Arrays.asList(arr[start],arr[next]));
            }
            start++;
            next++;
        }
        return result;
    }

    public static void main(String[] args) {
        MinimumAbsDifference minimumAbsDifference=new MinimumAbsDifference();
        minimumAbsDifference.minimumAbsDifference(new int[]{4,2,1,3});
    }
}