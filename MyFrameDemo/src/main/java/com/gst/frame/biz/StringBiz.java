package com.gst.frame.biz;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import com.gst.frame.utils.DPIUtil;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringBiz {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		boolean isEmpty = true;
		if (value != null && !value.trim().equals("") && !value.trim().equals("null")) {
			isEmpty = false;
		}
		return isEmpty;
	}
	
	// 两个数进行相乘
	public static float multipliedValue(String currentPrice, int num) {
		float price = 0.00f;
		float unitPrice = Float.parseFloat(currentPrice);
		price += (num * unitPrice);
		return price;
	}
	
	// 拼接Json格式字符串
	public static String spliceJson(String[] idArray, String[] numArray) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (int i = 0; i < idArray.length; i++) {
			if (i != idArray.length - 1) {
				sb.append("\"" + idArray[i] + "\":" + numArray[i] + ",");
			} else {
				sb.append("\"" + idArray[i] + "\":" + numArray[i] + "}");
			}
		}
		return sb.toString();
	}
	
	// 拼接Json格式字符串
	public static String spliceJson(int[][] medal_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		int size = medal_id.length;
		for (int i = 0; i < size; i++) {
			if (i != size - 1) {
				sb.append("\"" + medal_id[i][0] + "\":" + medal_id[i][1] + ",");
			} else {
				sb.append("\"" + medal_id[i][0] + "\":" + medal_id[i][1] + "}");
			}
		}
		return sb.toString();
	}
	// 拼接Json格式字符串
	public static String spliceJson(String[][] medal_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		int size = medal_id.length;
		for (int i = 0; i < size; i++) {
			if (i != size - 1) {
				sb.append("\"" + medal_id[i][0] + "\":" + medal_id[i][1] + ",");
			} else {
				sb.append("\"" + medal_id[i][0] + "\":" + medal_id[i][1] + "}");
			}
		}
		return sb.toString();
	}
	// 字体加粗
	public static void setTextPaint(TextView tv) {
		TextPaint tp = tv.getPaint();
		tp.setFakeBoldText(true);
	}
	
	// 拼接选中的购物车商品
//	public static String parseProducts(List<Integer> selId,
//			List<Cart> listCart, boolean isDel) {
//		String[] idArray = new String[selId.size()];
//		String[] numArray = new String[selId.size()];
//		for (int i = 0; i < selId.size(); i++) {
//			idArray[i] = listCart.get(selId.get(i)).getId();
//			if (isDel) {
//				numArray[i] = "0";
//			} else {
//				numArray[i] = "" + listCart.get(selId.get(i)).getNum();
//			}
//		}
//		return spliceJson(idArray, numArray);
//	}
	
	public static String parseProducts(Context context, String[] idArray, int[] beforeNum, int[] afterNum) {
		String[] numArray = new String[afterNum.length];
		for(int i=0; i<afterNum.length; i++) {
			int afNum = afterNum[i];
			int beNum = beforeNum[i];
			if(beNum >= afNum) {
				numArray[i] = "" + (beNum - afNum);
			} else {
				numArray[i] = "0";
			}
		}
		return spliceJson(idArray, numArray);
	}
	
	/**
	 * 判断字符串是否是数字(0.0)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		char[] p = str.toCharArray();
		for (int i = 0; i < p.length; i++) {
			if (!isNum("" + p[i])) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9.]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	// 对String进行转码 URLEncoded
	public static String toURLEncoded(String paramString) {  
        if (paramString == null || paramString.equals("")) {  
            return "";  
        }  
        try  
        {  
            String str = new String(paramString.getBytes(), "UTF-8");  
            str = URLEncoder.encode(str, "UTF-8");  
            return str;  
        }  
        catch (Exception e)  
        {  
        	e.printStackTrace();
        }  
        return "";  
    }  
	
	// 保留两位小数
	public static String strToDouble(String value) {
		DecimalFormat df = new DecimalFormat("0.00");
		double d = Double.valueOf(value); 
		return df.format(d);
	}
	
	// 拼接不同颜色及字体的字符串
		public static SpannableStringBuilder mosaicString(Context context, String content1, String content2, int color1, int color2) {
			int len1 = !StringBiz.isEmpty(content1) ? content1.length() : 0;
			int len2 = !StringBiz.isEmpty(content2) ? content2.length() : 0;
			ColorStateList blackColors = ColorStateList.valueOf(color1);
			ColorStateList red2Colors = ColorStateList.valueOf(color2);
			SpannableStringBuilder spanBuilder = new SpannableStringBuilder(content1 + content2);
			//style 为0 即是正常的，还有Typeface.BOLD(粗体) Typeface.ITALIC(斜体)等
			//size  为0 即采用原始的正常的 size大小
			//TextAppearanceSpan 五个参数分别是，字体名称，字体样式，字体大小--单位是px，字体颜色，字体链接颜色
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, 25.0f), blackColors, null), 0, len1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, DPIUtil.sp2px(context, 15.0f), red2Colors, null), len1, (len1 + len2), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			System.out.println("------spanBuilder :" + spanBuilder);
			return spanBuilder;
		}

		// 购买点评次数文本
		public static SpannableStringBuilder buyCommentsTimesString(Context context, String contentTime, String times, int color1, int color2, int color3) {
			String content1 = "您的";
			String content2 = " “求点评” ";
			String content3 = "将在 ";
			String content4 = contentTime;
			String content5 = " 到期，如不续费，您将损失剩余 ";
			String content6 = times;
			String content7 = " 次点评机会";
			String content8 = "\n(若续费成功，剩余点评次数将累计到次月继续使用)";
			int len1 = !StringBiz.isEmpty(content1) ? content1.length() : 0;
			int len2 = !StringBiz.isEmpty(content2) ? content2.length() : 0;
			int len3 = !StringBiz.isEmpty(content3) ? content3.length() : 0;
			int len4 = !StringBiz.isEmpty(content4) ? content4.length() : 0;
			int len5 = !StringBiz.isEmpty(content5) ? content5.length() : 0;
			int len6 = !StringBiz.isEmpty(content6) ? content6.length() : 0;
			int len7 = !StringBiz.isEmpty(content7) ? content7.length() : 0;
			int len8 = !StringBiz.isEmpty(content8) ? content8.length() : 0;

			ColorStateList blackColors = ColorStateList.valueOf(color1);
			ColorStateList red2Colors = ColorStateList.valueOf(color2);
			ColorStateList grayColor3 = ColorStateList.valueOf(color3);
			SpannableStringBuilder spanBuilder = new SpannableStringBuilder(content1 + content2 + content3 + content4 + content5 + content6 + content7 + content8);
			//style 为0/NORMAL 即是正常的，还有Typeface.BOLD(粗体) Typeface.ITALIC(斜体)等
			//size  为0 即采用原始的正常的 size大小
			//TextAppearanceSpan 五个参数分别是，字体名称，字体样式，字体大小--单位是px，字体颜色，字体链接颜色
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, 15.0f), blackColors, null), 0, len1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, 15.0f), red2Colors, null), len1, (len1 + len2), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, 15.0f), blackColors, null), (len1 + len2), (len1 + len2 + len3), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, 15.0f), red2Colors, null), (len1 + len2 + len3), (len1 + len2 + len3 + len4), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, 15.0f), blackColors, null), (len1 + len2 + len3 + len4), (len1 + len2 + len3 + len4 + len5), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, 15.0f), red2Colors, null), (len1 + len2 + len3 + len4 + len5), (len1 + len2 + len3 + len4 + len5 + len6), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, 15.0f), blackColors, null), (len1 + len2 + len3 + len4 + len5 + len6), (len1 + len2 + len3 + len4 + len5 + len6 + len7), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, DPIUtil.sp2px(context, 11.0f), grayColor3, null), (len1 + len2 + len3 + len4 + len5 + len6 + len7), (len1 + len2 + len3 + len4 + len5 + len6 + len7 + len8), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			System.out.println("------spanBuilder :" + spanBuilder);
			return spanBuilder;
		}


		// 购买点评次数文本
		public static SpannableStringBuilder buyCommentsTimesSorryString(Context context, int color1, int color2) {
			String content1 = "Sorry！\n您本月的 ";
			String content2 = "“求点评”";
			String content3 = " 次数已全部使用完，如果您想继续使用，请另购使用次数哦";
			int len1 = !StringBiz.isEmpty(content1) ? content1.length() : 0;
			int len2 = !StringBiz.isEmpty(content2) ? content2.length() : 0;
			int len3 = !StringBiz.isEmpty(content3) ? content3.length() : 0;

			ColorStateList red2Colors = ColorStateList.valueOf(color1);
			ColorStateList grayColor3 = ColorStateList.valueOf(color2);
			SpannableStringBuilder spanBuilder = new SpannableStringBuilder(content1 + content2 + content3);
			//style 为0/NORMAL 即是正常的，还有Typeface.BOLD(粗体) Typeface.ITALIC(斜体)等
			//size  为0 即采用原始的正常的 size大小
			//TextAppearanceSpan 五个参数分别是，字体名称，字体样式，字体大小--单位是px，字体颜色，字体链接颜色
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, 15.0f), grayColor3, null), 0, len1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, 15.0f), red2Colors, null), len1, (len1 + len2), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, 15.0f), grayColor3, null), (len1 + len2), (len1 + len2 + len3), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			System.out.println("------spanBuilder :" + spanBuilder);
			return spanBuilder;
		}

		// 点评次数
		public static SpannableStringBuilder commentsTimesString(Context context, String content1, 
				String content2, String content3, int color1, int color2, float smallSize, float bigSize) {
			int len1 = !StringBiz.isEmpty(content1) ? content1.length() : 0;
			int len2 = !StringBiz.isEmpty(content2) ? content2.length() : 0;
			int len3 = !StringBiz.isEmpty(content3) ? content3.length() : 0;;
			ColorStateList blackColors = ColorStateList.valueOf(color1);
			ColorStateList redColors = ColorStateList.valueOf(color2);
			SpannableStringBuilder spanBuilder = new SpannableStringBuilder(content1 + content2 + content3);
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, DPIUtil.sp2px(context, smallSize), blackColors, null), 0, len1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			if(!StringBiz.isEmpty(content2)) {
				spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DPIUtil.sp2px(context, bigSize), redColors, null), len1, (len1 + len2), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			}
			if(!StringBiz.isEmpty(content3)) {
				spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, DPIUtil.sp2px(context, smallSize), blackColors, null), (len1 + len2), (len1 + len2 + len3), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
			}
			return spanBuilder;
		}
		
		// 根据勋章Id(2_1)得到索引2 1
	public static int[] subValue(String medalId) {
		int[] indexArray = new int[2];
		int mItemIndex = -1;
		int mChildIndex = -1;
		if (!StringBiz.isEmpty(medalId)) {
			String[] array = medalId.split("_");
			if (array != null && array.length == 2) {
				mItemIndex = Integer.valueOf(array[0]).intValue();
				mChildIndex = Integer.valueOf(array[1]).intValue();
			}
		}
		indexArray[0] = mItemIndex;
		indexArray[1] = mChildIndex;
		return indexArray;
	}
	
	// 半角字符转换为全角字符(解决文本行显示数参差不齐的问题)
	public static String toSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }
	
	public static String[] cardName = new String[] { "apple", "ant", "alligator",
			"bus", "banana", "bag", "cat", "car", "cake", "duck", "dog", "desk", "elk", "elevator", "elephant", "four", "fish", "fan",
			"goose", "gift", "gate", "hen", "hand", "hammer", "insect", "ink", "igloo", "juice", "jelly", "jar", "kite", "king", "key", "lion",
			"lemon", "leaf", "mouse", "moon", "milk", "noodle", "net", "nest", "ox", "otter", "ostrich", "pizza", "peach", "pan", "quilt",
			"question", "queen", "rose", "rock", "rabbit", "sofa", "snake", "seven", "turtle", "tiger", "table", "underwear", "under",
			"umbrella", "vest", "vase", "van", "worm", "wings", "window", "six", "fox", "box", "yoyo", "yellow", "yarn", "zoo", "zero",
			"zebra", "rain", "autumn", "paw", "say", "leaf", "bee", "weight", "lie", "boat", "low", "down", "boil", "zoo", "loud", "soup", "boy",
			"fork", "bird", "father", "bark", "curl", "late", "came", "cape", "cake", "cave", "kite", "like", "line", "dive", "poke", "nose",
			"hope", "bone", "fuse", "cute", "cube", "sail", "pause", "saw", "lay", "meat", "beef", "veil", "tie", "road", "snow", "now",
			"coin", "moon", "mouse", "you", "toy", "horn", "girl", "mother", "jar", "fur", "gate", "game", "tape", "make", "save", "site",
			"bike", "mine", "five", "joke", "hose", "rope", "cone", "muse", "mute", "tube", "wait", "hay", "seat", "feet", "pie", "soap",
			"bowl", "cow", "soil", "pool", "out", "port", "dirt", "car", "burn", "date", "nape", "lake", "wave", "bite", "mike", "nine", "coke", "rose",
	};
	
	// 马戏团题库
	public static String[] circusTroupeQuestions = new String[] {
			"bait", "gaw", "fail", "haw", "gait", "jaw", "hail", "law", "jail", "paw", "kail", "raw", "foy", "hurt", "joy", "soy", "toy",
			"mail", "saw", "nail", "yaw", "pail", "bay", "quail", "cay", "rail", "day", "sail", "fay", "tail", "hay", "vail", "jay", "wail",
			"lay", "wait", "may", "bard", "nay", "bark", "pay", "card", "ray", "cark", "say", "dark", "way", "fard", "yay", "hard", "beet",
			"lard", "feet", "lark", "feed", "mark", "meet", "pard", "need", "park", "reed", "ward", "seed", "wark", "weed", "yard", "die",
			"beal", "lie", "beat", "pie", "deal", "tie", "feat", "bird", "heal", "dirt", "heat", "load", "meal", "road", "meat", "soap",
			"neat", "toad", "peal", "boil", "peat", "coil", "real", "coin", "seal", "foin", "seat", "join", "teal", "loin", "teat", "moil",
			"veal", "roil", "weal", "soil", "zeal", "toil", "boom", "born", "coom", "cork", "coon", "corn", "doom", "dork", "loom", "fork",
			"moon", "horn", "noon", "morn", "room", "pork", "soon", "torn", "toom", "work", "zoom", "worn", "boy", "burn", "coy", "burt"
	};
	
	// 拆分单词
	public static String[] subWordArray(String word) {
		String[] wordArray = null;
		if(!StringBiz.isEmpty(word)) {
			if(word.length() == 3) {
				wordArray = new String[2];
				wordArray[0] = word.substring(0, 1);
				wordArray[1] = word.substring(1, word.length());
			} else if(word.length() > 3){
				wordArray = new String[3];
				wordArray[0] = word.substring(0, 1);
				wordArray[1] = word.substring(1, 3);
				wordArray[2] = word.substring(3, word.length());
			}
		}
		return wordArray;
	}
	
	// 取整Math的 floor,round和ceil的总结 （floor 返回不大于的最大整数   round 则是4舍5入的计算，入的时候是到大于它的整数   ceil 则是不小于他的最小整数）
	public static String numChange(String value) {
		if(!isEmpty(value) && isNumeric(value)) {
			double v = Double.valueOf(value);
			value = "" + (int) Math.floor(v);
		}
		return value;
	}
	
		
}
