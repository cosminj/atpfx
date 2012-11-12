package com.google.code.atpfx.data;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.google.code.atpfx.GlobalProperties;
import com.google.code.db.HibernateUtil;
import org.hibernate.Session;
import com.google.code.atpfx.data.Interval;

public class Intervals {

	private static int NUMBER_OF_INTERVALS_TO_TEST = 3;
	private static HashMap<String, Long> intervalsIDs = new HashMap<String, Long>();
	private static HashMap<Long, String> intervalsNames = new HashMap<Long, String>();
	private static HashMap<Long, Integer> intervalsMinutes = new HashMap<Long, Integer>();
	private static ArrayList<Interval> intervalsList = new ArrayList<Interval>(
			40);

	public Intervals() {
        if(GlobalProperties.isPersistenceEnabled()) {
            initDBIntervals(HibernateUtil.getSessionFactory().getCurrentSession());
        }
	}

	// populate the lists only once, from database
	@SuppressWarnings("unchecked")
	private void initDBIntervals(Session session) {
		if (0 == intervalsList.size()) {
			session.beginTransaction();
			intervalsList = new ArrayList<Interval>(session.createQuery(
					"from Interval").list());
			for (Interval i : intervalsList) {
				intervalsIDs.put(i.getIntervalName(), i.getIntervalId());
				intervalsNames.put(i.getIntervalId(), i.getIntervalName());
				intervalsMinutes.put(i.getIntervalId(), i.getIntervalMinutes());
			}
			session.getTransaction().commit();
		}
	}

	public static long getIntervalIdByMinutes(int minutes) {
		if (0 == intervalsMinutes.size())
			return 0L;
		if (!intervalsMinutes.containsKey(minutes))
			return 0L;
		return intervalsMinutes.get(minutes);
	}

	public static String getIntervalNameById(long id) {
		if (0 == intervalsNames.size())
			return "";
		if (!intervalsNames.containsKey(id))
			return "";
		return (String) intervalsNames.get(id);
	}

	public static int getIntervalMinutesById(long id) {
		if (0 == intervalsNames.size())
			return 0;
		if (!intervalsNames.containsKey(id))
			return 0;
		return (int) intervalsMinutes.get(id);
	}

	public static ArrayList<Interval> getIntervalsList() {
		ArrayList<Interval> temp = new ArrayList<Interval>();
		for (int j = 0; j < NUMBER_OF_INTERVALS_TO_TEST; j++)
			temp.add(intervalsList.get(j));
		return temp; // return intervalsList;
	}

	public static long getIntervalStart(long givenTimeStamp, int intervalId) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy M d H:m:s");
		Calendar now;
		Date d = new Date(givenTimeStamp);
		String s;
		switch (intervalId) {
		case 1:// 1 min
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " " + now.get(Calendar.DAY_OF_MONTH);
			s = s + " " + now.get(Calendar.HOUR_OF_DAY);
			s = s + ":" + now.get(Calendar.MINUTE);
			s = s + ":00";
			break;
		case 2:// 5 min
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " " + now.get(Calendar.DAY_OF_MONTH);
			s = s + " " + now.get(Calendar.HOUR_OF_DAY);
			s = s + ":"
					+ (now.get(Calendar.MINUTE) - now.get(Calendar.MINUTE) % 5);
			s = s + ":00";
			break;
		case 3:// 10 min
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " " + now.get(Calendar.DAY_OF_MONTH);
			s = s + " " + now.get(Calendar.HOUR_OF_DAY);
			s = s
					+ ":"
					+ (now.get(Calendar.MINUTE) - now.get(Calendar.MINUTE) % 10);
			s = s + ":00";
			break;
		case 4:// 15 min
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " " + now.get(Calendar.DAY_OF_MONTH);
			s = s + " " + now.get(Calendar.HOUR_OF_DAY);
			s = s
					+ ":"
					+ (now.get(Calendar.MINUTE) - now.get(Calendar.MINUTE) % 15);
			s = s + ":00";
			break;
		case 5:// 30 min
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " " + now.get(Calendar.DAY_OF_MONTH);
			s = s + " " + now.get(Calendar.HOUR_OF_DAY);
			s = s
					+ ":"
					+ (now.get(Calendar.MINUTE) - now.get(Calendar.MINUTE) % 30);
			s = s + ":00";
			break;
		case 6:// 1 hr
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " " + now.get(Calendar.DAY_OF_MONTH);
			s = s + " " + now.get(Calendar.HOUR_OF_DAY);
			s = s + ":00:00";
			break;
		case 7:// 2 hr
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " " + now.get(Calendar.DAY_OF_MONTH);
			s = s
					+ " "
					+ (now.get(Calendar.HOUR_OF_DAY) - now
							.get(Calendar.HOUR_OF_DAY) % 2);
			s = s + ":00:00";
			break;
		case 8:// 4 hr
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " " + now.get(Calendar.DAY_OF_MONTH);
			s = s
					+ " "
					+ (now.get(Calendar.HOUR_OF_DAY)
							- now.get(Calendar.HOUR_OF_DAY) % 4 + 2);
			s = s + ":00:00";
			break;
		case 9:// 8 hr
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " " + now.get(Calendar.DAY_OF_MONTH);
			s = s
					+ " "
					+ (now.get(Calendar.HOUR_OF_DAY)
							- now.get(Calendar.HOUR_OF_DAY) % 8 + 2);
			s = s + ":00:00";
			break;
		case 10:// daily
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " " + now.get(Calendar.DAY_OF_MONTH);
			s = s + " 07:00:00";
			break;
		case 11:// weekly
			// the logic is Monday at 7am
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			// DAY_OF_WEEK = Sunday is 1, Monday is 2, etc.
			s = s
					+ " "
					+ (now.get(Calendar.DAY_OF_MONTH)
							- now.get(Calendar.DAY_OF_WEEK) + 2);
			s = s + " 07:00:00";
			break;
		case 12:// monthly
			// the logic is first of the month at 7am
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " 01 07:00:00";
			break;
		case 13:// yearly
			// the logic is first of the month at 7am
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " 01";
			s = s + " 01 07:00:00";
			break;
		default:
			now = Calendar.getInstance();
			now.setTime(d);
			s = "" + now.get(Calendar.YEAR);
			s = s + " " + (now.get(Calendar.MONTH) + 1);
			s = s + " " + now.get(Calendar.DAY_OF_MONTH);
			s = s + " " + now.get(Calendar.HOUR_OF_DAY);
			s = s + ":" + now.get(Calendar.MINUTE);
			s = s + ":" + now.get(Calendar.SECOND);
		}
		try {
			return format.parse(s).getTime();
		} catch (ParseException e) {
			// TODO What can we do if parse of this string fails
			// e.printStackTrace();
		}
		return 0L;
	}

	public static long getIntervalStart(long givenTimeStamp, long intervalId) {
		return getIntervalStart(givenTimeStamp, (int) intervalId);
	}
}
