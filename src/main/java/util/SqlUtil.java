package util;

public class SqlUtil {
	
	/**
	 * sql注入过滤
	 * @param str
	 * @return
	 */
	public static boolean sql_inj(String str){
		String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
		String inj_stra[] = inj_str.split("\\|");
		for (int i=0 ; i < inj_stra.length ; i++ ){	
			if (str.indexOf(inj_stra[i])>=0){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args){
		sql_inj("id_number");
	}
}
