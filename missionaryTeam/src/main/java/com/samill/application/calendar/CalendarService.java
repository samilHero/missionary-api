package com.samill.application.calendar;

public interface CalendarService {
    Calendar createTeamCalendar(Calendar calendar);
    Calendar getTeamCalender(Long teamId);
    Calendar updateTeamCalendar(Calendar calendar);
    void deleteTeamCalendar(Long calendarId);
}
