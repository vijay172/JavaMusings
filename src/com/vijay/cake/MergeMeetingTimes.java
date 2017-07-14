package com.vijay.cake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vkbalakr on 6/25/17.
 */
public class MergeMeetingTimes {

    public static class Meeting {

        private int startTime;
        private int endTime;

        public Meeting(int startTime, int endTime) {
            // number of 30 min blocks past 9:00 am
            this.startTime = startTime;
            this.endTime   = endTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("startTime:").append(startTime).append("endTime:").append(endTime);
            return sb.toString();
        }
    }

    public static Meeting[] mergeRanges(Meeting[] meetings) {

        //sort meetings
        Arrays.sort(meetings, new Comparator<Meeting>() {
            public int compare(Meeting old, Meeting newer) {
                return old.getStartTime() - newer.getStartTime();
            }}
        );

        Meeting[] mergedMeetings = new Meeting[meetings.length];//TODO:
        int meetingsLen = meetings.length;
        int mergedMeetingIdx = 0;
        for (int currMeetingIdx = 0; currMeetingIdx < meetingsLen; currMeetingIdx++) {
            Meeting currMeeting = meetings[currMeetingIdx];
            Meeting nextMeeting = null;
            boolean mergeHappened = false;
            if (currMeetingIdx + 1 == meetingsLen) {
                mergedMeetings[currMeetingIdx] = meetings[currMeetingIdx];
            } else {
               nextMeeting = meetings[currMeetingIdx + 1];
            }
            int mergedStartTime = 0;
            int mergedEndTime = 0;
            if (currMeeting.getStartTime() < nextMeeting.getStartTime()) {
                mergedStartTime = currMeeting.getStartTime();
            } else {
                mergedStartTime = nextMeeting.getStartTime();
            }
            if (currMeeting.getEndTime() < nextMeeting.getStartTime()) {
                //no merging
                mergedEndTime = currMeeting.getEndTime();
            }  else if (currMeeting.getEndTime() < nextMeeting.getEndTime() ||
                    (currMeeting.getEndTime() == nextMeeting.getStartTime())) {
                //merge happens here
                mergedEndTime = nextMeeting.getEndTime();
                currMeetingIdx++;
                mergeHappened = true;
            } else if (currMeeting.getEndTime() > nextMeeting.getEndTime()) {
                mergedEndTime = currMeeting.getEndTime();
                currMeetingIdx++;
                mergeHappened = true;
            }

                mergedMeetings[mergedMeetingIdx++] = new Meeting(mergedStartTime, mergedEndTime);

        }
        return mergedMeetings;

    }

    public static List<Meeting> mergeRangesCorrect(List<Meeting> meetings) {

        //sort List
        List<Meeting> sortedMeetings = new ArrayList<Meeting>(meetings);
        Collections.sort(sortedMeetings, new Comparator<Meeting>() {
            public int compare(Meeting old, Meeting newM) {
                return old.getStartTime() - newM.getStartTime();
            }
        });

        List<Meeting> mergedMeetings = new ArrayList<Meeting>();
        mergedMeetings.add(sortedMeetings.get(0));//good trick
        for (Meeting currMeeting: sortedMeetings) {
            Meeting lastMergedMeeting = mergedMeetings.get(mergedMeetings.size() - 1);

            //if start time of current <= last merged end time,merge
            if (currMeeting.getStartTime() <= lastMergedMeeting.getEndTime()) {
                lastMergedMeeting.setEndTime(Math.max(currMeeting.getEndTime(), lastMergedMeeting.getEndTime()));//very cool
            } else {//no overlap
                mergedMeetings.add(currMeeting);
            }

        }
        return mergedMeetings;

    }

    public static void main(String[] args) {
        /*Meeting[] meetings = new Meeting[]{new Meeting(0, 1),new Meeting(1, 2), new Meeting(2, 3), new Meeting(3, 5), new Meeting(4, 8), new Meeting(10, 12), new Meeting(9, 10)};
        System.out.println(Arrays.deepToString(mergeRanges(meetings)));*/
        List<Meeting> meetings = new ArrayList<Meeting>();
        meetings.add(new Meeting(0, 1));
        meetings.add(new Meeting(1, 2));
        meetings.add(new Meeting(2, 3));
        meetings.add(new Meeting(3, 5));
        meetings.add(new Meeting(4, 8));
        meetings.add(new Meeting(10, 12));
        meetings.add(new Meeting(9, 10));
        System.out.println(mergeRangesCorrect(meetings));
    }
}
