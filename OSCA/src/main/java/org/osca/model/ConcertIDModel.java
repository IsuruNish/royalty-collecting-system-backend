package org.osca.model;

public class ConcertIDModel {

    int concertID;

    public ConcertIDModel(int concertID) {
        this.concertID = concertID;
    }

    public int getConcertID() {
        return concertID;
    }

    public void setConcertID(int concertID) {
        this.concertID = concertID;
    }

    @Override
    public String toString() {
        return "ConcertIDModel{" +
                "concertID=" + concertID +
                '}';
    }
}
