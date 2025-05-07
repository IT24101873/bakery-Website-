package com.bakery.BakeryWebsite.models;

public class Review extends Feedback {
    private String comment;
    private int rating;

    public Review() {
        super();
    }

    public Review(String customerName, String comment, int rating) {
        super(customerName);
        this.comment = comment;
        this.rating = rating;
    }

    // Getters and Setters
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return id + "|" + customerName + "|" + createdAt + "|" + comment + "|" + rating;
    }
}

