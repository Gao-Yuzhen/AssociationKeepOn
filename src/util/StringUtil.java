package util;

public class StringUtil {

	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isInt(String str){

		return str.matches("\\d+"); //ʹ��������ʽ�жϸ��ַ����Ƿ�Ϊ���֣���һ��\��ת�����\d+��ʾƥ��1���� //����������֣�"+"��"*"���ƣ�"*"��ʾ0������
		}
}
