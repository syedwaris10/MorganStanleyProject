package com.ms.project.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.http.HttpStatus;

import com.ms.project.constants.Messages;
import com.ms.project.dto.MessageDto;
import com.ms.project.exceptions.InvalidInputFault;

/**
 * 
 * @author Syed.Waris
 * @version 1.0
 * @since 30-03-2020
 *
 */
public class CommonUtils {

	/**
	 * decodes string from encoded base64
	 * 
	 * @param encoded
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeBase64(String encoded) throws UnsupportedEncodingException {

		String decoded = "";
		if (!ServiceUtil.isEmpty(encoded)) {
			byte[] bytearray = Base64.getDecoder().decode(encoded);
			decoded = new String(bytearray, "UTF-8");
		}
		return decoded;
	}

	/**
	 * encodes string to base64
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeToBase64(String str) throws UnsupportedEncodingException {
		String encoded = "";
		if (!ServiceUtil.isEmpty(str)) {
			encoded = Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
		}
		return encoded;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static MessageDto getSuccessMessage(Object data) {
		MessageDto dto = new MessageDto();
		dto.setStatusCode(HttpStatus.SC_OK);
		dto.setData(data);
		dto.setMessage(Messages.success);
		dto.setMessageType("Success");
		return dto;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static MessageDto getFailedMessage(Object data, String msg) {
		MessageDto dto = new MessageDto();
		dto.setStatusCode(HttpStatus.SC_BAD_REQUEST);
		dto.setData(data);
		dto.setMessage(msg);
		dto.setMessageType(Messages.error);
		return dto;
	}

	/**
	 * get Date in dd-MM-yyyy format
	 * 
	 * @param strDate
	 * @return
	 * @throws InvalidInputFault
	 * @throws ParseException
	 */
	public static Date getDate(Date date) throws InvalidInputFault {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if (!ServiceUtil.isEmpty(date)) {
			try {
				String strDate = sdf.format(date);
				return sdf.parse(strDate.trim());
			} catch (ParseException e) {
				throw new InvalidInputFault("Unable to parse date : " + date);
			}
		}
		return null;
	}

	/**
	 * generates a integer random number
	 * 
	 * @return
	 */
	public static int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}

	/**
	 * return unique 36 char uuid unique value
	 * 
	 * @return
	 */
	public static String getRandomUUIDKey() {
		UUID uid = UUID.randomUUID();
		return uid.toString();
	}

	public static Date getCurrentDate() throws ParseException {
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String d = sdf.format(date.getTime());
		return sdf.parse(d);

	}

	/**
	 * validates email id
	 * 
	 * @param emailId
	 * @return
	 */
	public static boolean isEmailValid(String emailId) {
		if (!ServiceUtil.isEmpty(emailId)) {
			String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
			if (emailId.trim().matches(regex)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isPasswordValid(String pwd) {
		String regex = "[aA-zZ0-9]{8,16}";
		if (!ServiceUtil.isEmpty(pwd)) {
			if (pwd.matches(regex)) {
				return true;
			}
		}
		return false;
	}
}
