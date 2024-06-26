package com.genius.ifbretailer.utility;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

public class TimeDifferenceCalculator {
    public static String calculateTimeDifference(String startTime, String endTime) {
        // Assuming time format is HH:mm:ss
        String timeFormat = "HH:mm:ss";

        SimpleDateFormat format = new SimpleDateFormat(timeFormat);

        try {
            // Parse the input strings to Date objects
            Date startDate = format.parse(startTime);
            Date endDate = format.parse(endTime);

            // Calculate the time difference in milliseconds
            long timeDifferenceMillis = endDate.getTime() - startDate.getTime();

            // Calculate hours, minutes, and seconds
            long seconds = timeDifferenceMillis / 1000 % 60;
            long minutes = timeDifferenceMillis / (60 * 1000) % 60;
            long hours = timeDifferenceMillis / (60 * 60 * 1000);

            // Format the output
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);

        } catch (ParseException e) {
            e.printStackTrace();
            return "Error parsing dates";
        }
    }
}
