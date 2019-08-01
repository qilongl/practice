/**
 * <p>Title: 数字常用工具方法类</p>
 * @version 1.0
 */
package com.lql.util;


import org.hibernate.validator.internal.util.StringHelper;

import java.util.Random;

/**
 * 数字常用工具方法类

 */
public final class NumberHelper {

    /**
     * 将输入的整数转成指定长度的字符串,不足的用0填充
     * @param iIn 需要填充0的整数

     * @param iLength 转换后的字符串的长度
     * @return 用0填充过的指定长度的字符串
     */
    public static String add0(int iIn, int iLength) {
        long lv = (long) Math.pow(10, iLength);
        if(lv < iIn)	return String.valueOf(iIn);
        return String.valueOf(lv + iIn).substring(1);
    }

    /**
     * 
     * @param strValue
     * @return
     */
    public static int string2Int(String strValue) {
        return string2Int(strValue, -1);
    }

    /**
     * 
     * @param strValue
     * @param defValue
     * @return
     */
    public static int string2Int(String strValue, int defValue) {
        try {
            return Integer.parseInt(strValue);
        } catch (Exception ex) {
            return defValue;
        }
    }
    
    public static int string2Int(Object obj, int defValue) {
    	if(obj==null){
    		return defValue;
    	}
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception ex) {
            return defValue;
        }
    }
    
    /**
     * 判断一个字符串（11.0）是否为整数，是则返回整数，否则返回-1
     * @param strValue
     * @return
     */
    public static int string2Int2(String strValue) {
    	try {
    		if(com.lql.util.StringHelper.isEmpty(strValue)){
    			return 0;
    		}
	    	Double dvalue = Double.valueOf(strValue);
	    	int ivalue = dvalue.intValue();
	    	if(dvalue==ivalue){
	    	   return ivalue;
	    	}
    	}catch (Exception ex) {
            return -1;
        }
    	return -1;
    }

    /**
     * 判断一个字符串（11.0）是否为整数，是则返回整数，否则返回值

     * @param strValue
     * @return
     */
    public static int string2Int2(String strValue,int defValue) {
    	try {
    		if(strValue==null){
    			return 0;
    		}
	    	Double dvalue = Double.valueOf(strValue);
	    	int ivalue = dvalue.intValue();
	    	if(dvalue==ivalue){
	    	   return ivalue;
	    	}
    	}catch (Exception ex) {
            return defValue;
        }
    	
    	return defValue;
    }
    
    public static Integer getIntegerValue(Object strValue, int defValue) {
        try {
            return Integer.valueOf(com.lql.util.StringHelper.null2String(strValue));
        } catch (Exception ex) {
            return new Integer(defValue);
        }
    }

    public static Integer getIntegerValue(Object strValue){
    	return getIntegerValue(strValue,0);
    }


    /**
     * 
     * @param strValue
     * @return
     */
    public static float string2Float(String strValue) {
        return string2Float(strValue, -1);
    }


    /**
     * 
     * @param strValue
     * @param defValue
     * @return
     */
    public static float string2Float(String strValue, float defValue) {
        try {
            return Float.parseFloat(strValue);
        } catch (Exception ex) {
            return defValue;
        }
    } 

    /**
     * 
     * @param strValue
     * @return
     */
    public static double string2Double(String strValue) {
        return string2Double(strValue, -1);
    }

    /**
     * 格式化float数值的字符串显示格式。
     * @param money
     * @param format
     * @return
     */
    public static String floatFormat(String money,String format) {
        double tempmoney = string2Double(money,0);
        return new java.text.DecimalFormat(format).format(tempmoney);
    }

    // 金额每3位加逗号
    public static String moneyAddComma(double money) {
      return new java.text.DecimalFormat("#,##0.00").format(money);
    }
    
    // 金额每3位加逗号 
    public static String moneyAddComma(String money) {
      double tempmoney = string2Double(money,0);
      return new java.text.DecimalFormat("#,##0.00").format(tempmoney);
    }   
    // 金额每3位加逗号 
    public static String moneyAddComma(Object money) {
      double tempmoney = string2Double(money,0);
      return new java.text.DecimalFormat("#,##0.00").format(tempmoney);
    }   
    /**
     * 
     * @param strValue
     * @param defValue
     * @return
     */
    public static double string2Double(String strValue, double defValue) {
        try {
            return Double.parseDouble(strValue);
        } catch (Exception ex) {
            return defValue;
        }
    }
    
    public static double string2Double(Object obj, double defValue) {
    	if(obj==null){
    		return defValue;
    	}
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception ex) {
            return defValue;
        }
    }

    public static int getRandomInt(int min,int max){

    	Random random = new Random();
    	return Math.abs(random.nextInt()) % (max-min)+min;
    }
    /**
     * 浮点数四舍五入为整型
     * */
    public static int float2int(float fValue){
    	int intValue=(int)fValue;
    	float tFloat=fValue-intValue;
    	intValue=(int)(tFloat+fValue);
    	return intValue;
    }

}