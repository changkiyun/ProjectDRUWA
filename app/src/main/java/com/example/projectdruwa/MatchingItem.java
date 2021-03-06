package com.example.projectdruwa;

//매칭리스트의 RecyclerView에 들어갈 아이템을 설정하는 클래스
public class MatchingItem {
    String profile;
    String matchName;
    String matchDate;
    String matchLocation;

    public MatchingItem(String matchName, String matchDate, String matchLocation){
        this.matchName = matchName;
        this.matchDate = matchDate;
        this.matchLocation = matchLocation;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchLocation() {
        return matchLocation;
    }

    public void setMatchLocation(String matchLocation) {
        this.matchLocation = matchLocation;
    }
}