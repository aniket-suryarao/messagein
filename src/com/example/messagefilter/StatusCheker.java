package com.example.messagefilter;


public class StatusCheker  {
	
	String status=null;
	
	public String getStaus(String number)
	{
		
		if(isPrimary(number))
		{
			status="I";
		}
		else 
			if(isUpdates(number))
			{
				status="U";
			}
			else 
				
			{
					status="P";
			}
				
		
		
		
		return status;
	}

//	private boolean isPromotional(String number) {
//		// TODO Auto-generated method stub
//		
//		
//		return false;
//	}
	private boolean isUpdates(String number) {
		// TODO Auto-generated method stub
		String regexStr="^[a-z].*$";
		String regexStr1="^[A-Z].*$";
		if(number.matches(regexStr)||number.matches(regexStr1))
			
		{
			return true;
		}
		else
		{
		return false;

		}
		}

	private boolean isPrimary(String number) {
		// TODO Auto-generated method stub
//		String regexStr = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
//			if(number.matches(regexStr))
		String regexStr="[0-9]+";
		number=number.replace("+", "");
		number=number.replace("-", "");
		if(number.length()>=10&& number.matches(regexStr))
	
		   {
				
		
				return true;
			}
		
		
		else
		{
		return false;

		}
	}

}
